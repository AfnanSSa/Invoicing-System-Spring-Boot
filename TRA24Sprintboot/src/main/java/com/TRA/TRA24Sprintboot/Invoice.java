package com.TRA.TRA24Sprintboot;

import java.util.*;


public class Invoice implements InvoiceHandler {
    private Integer id; //class ID
    private Integer invoiceID; //invoice ID
    private String costumerName;
    private String phoneNumber; //costumer phone number
    private String date; //invoice date
    public static List<Item> items = new ArrayList<>();
    public static Shop shop = new Shop();
    public static List<Invoice> invoices = shop.getInvoices();
    public static Invoice newInvoice;
    private Double totalAmount;
    private Double paidAmount;
    private Double balance;

    //constructor

    public Invoice() {
    }

    public Invoice(Integer invoiceID, String costumerName, String phoneNumber, String date, List<Item> itemList, Double totalAmount) {
        this.invoiceID = invoiceID;
        this.costumerName = costumerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.items = itemList;
        this.totalAmount = totalAmount;
    }

    //method to handle option 3 in Main Menu (create nwe invoice)
    @Override
    public void createInvoice() {
        System.out.println("Creating New Invoice . .");
        Scanner scanner = new Scanner(System.in);
        Invoice invoice = new Invoice();
        List<Item> selectedItems = new ArrayList<>();

        //asking user to input invoice details
        System.out.print("Enter invoice ID: ");
        Integer invoiceID = shop.inputValidation(scanner);
        scanner.nextLine(); //consume new line
        System.out.print("Enter customer name: ");
        String customerName = shop.stringInputValidation(scanner);
        scanner.nextLine(); //consume new line
        System.out.print("Enter customer phone number: ");
        String phoneNumber = shop.stringInputValidation(scanner);
        scanner.nextLine(); //consume new line
        System.out.print("Enter invoice date (e.g., DD-MM-YYYY): ");
        String date = shop.stringInputValidation(scanner);
        scanner.nextLine(); //consume new line

        if (items.isEmpty()) { //no items in list
            System.out.println("No Items Available");
        } else {
            //showing list of items
            System.out.println("Available Items:");
            displayAllItems();

            while (true) {
                //prompting user to select item ID to add it to the invoice
                System.out.println("Enter Item ID to add it to the invoice (press 0 to quit): ");

                Integer itemID = scanner.nextInt();
                if (itemID == 0) {
                    break; //exit while loop
                }

                Item itemToBeAdded = findItemByID(itemID); //finding to item by its ID
                if (itemToBeAdded != null) {
                    selectedItems.add(itemToBeAdded); //adding the item to selected items list
                    System.out.println("Item: " + itemToBeAdded.getName() + " is added to the invoice");
                } else {
                    System.out.println("Item not found");
                }
            }

            //creating and adding the invoice to the list of invoices
            newInvoice = new Invoice(invoiceID, customerName, phoneNumber, date, selectedItems, invoice.totalAmount(selectedItems));

            shop.addInvoice(newInvoice);
            System.out.println("Invoice is created successfully");

            //printing the invoice
            System.out.println("\nInvoice Details:");
            System.out.println("-------------------------------------------");
            System.out.println("Item ID \t  Item Name\t Quantity\t  Unit Price\t    Total Amount");
            for (Item item : selectedItems) {
                System.out.printf("%d   \t%s    \t%d    \t%.2f  \t%.2f%n",
                        item.getItemID(),
                        item.getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getQuantity() * item.getUnitPrice());
            }
            System.out.println("Total Amount: $" + newInvoice.totalAmount(selectedItems));
        }
    }

    //method to find item by ID
    @Override
    public Item findItemByID(Integer itemID) {
        for (Item item : items) {
            if (item.getItemID() == itemID) {
                return item;
            }
        }
        return null; //if item is not found
    }

    //method to display items
    @Override
    public void displayAllItems() {
        for (Item item : items) {
            System.out.println(
                    "Item ID: " + item.getItemID() +
                            "| Name: " + item.getName() +
                            "| Unit Price: $" + item.getUnitPrice() +
                            "| Quantity: " + item.getQuantity()
            );
        }
    }

    //method to handle option 4 in Main Menu (Report Item Statistics)
    @Override
    public void reportItemStatistics() {
        List<Item> items = shop.getItems();
        System.out.println("Displaying statistics...");
        System.out.println("Number of items: " + items.size());
        System.out.println("Number of invoices: " + invoices.size());

        //calculating the total sales
        Double totalSales = 0.0;
        for (Invoice invoice : invoices) {
            totalSales += invoice.getTotalAmount();
        }

        //displaying total sales
        System.out.println("Total sales: $" + totalSales);
    }

    //method to handle option 5 in Main Menu (Report All Invoices)
    @Override
    public void reportAllInvoices() {
        List<Invoice> invoices = shop.getInvoices();
        System.out.println("Reporting All Invoices..");
        System.out.println("--------------------------------------------------------------------------------------");

        if (invoices.isEmpty()) {
            System.out.println("No invoices found.");
            System.out.println("--------------------------------------------------------------------------------------");
            return;
        }

        System.out.printf("%-12s %-20s %-15s %-12s %-12s%n",
                "Invoice ID", "Customer Name", "Phone Number", "Date", "Total Amount");
        System.out.println("--------------------------------------------------------------------------------------");

        for (Invoice invoice : invoices) {
            System.out.printf("%-12d %-20s %-15s %-12s $%-10.2f%n",
                    invoice.getInvoiceID(),
                    invoice.getCostumerName(),
                    invoice.getPhoneNumber(),
                    invoice.getDate(),
                    invoice.getTotalAmount());
        }

        System.out.println("--------------------------------------------------------------------------------------");
    }

    //method to handle option 6 in Main Menu (Search Invoices)
    @Override
    public void searchInvoices() {
        List<Invoice> invoices = shop.getInvoices();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Searching Invoices . .");

        Boolean validInput = Boolean.TRUE;
        while (validInput){
            //asking user to enter invoice ID
            System.out.println("Enter invoice ID: ");
            Integer invoiceToFind = shop.inputValidation(scanner);

            if (invoiceToFind == 0){
                return;
            }

            Boolean isFound = Boolean.FALSE;
            //iterating through invoices list to find the matching invoice
            for (Invoice invoice : invoices) {

                if (invoice.getInvoiceID() == invoiceToFind) {
                    isFound = Boolean.TRUE;

                    //displaying found invoice details
                    System.out.println("Invoice Details:");
                    System.out.println("Invoice ID: " + invoice.getInvoiceID());
                    System.out.println("Customer Name: " + invoice.getCostumerName());
                    System.out.println("Phone Number: " + invoice.getPhoneNumber());
                    System.out.println("Date: " + invoice.getDate());
                    System.out.println("Total Amount: $" + invoice.getTotalAmount());

                    //displaying items associated with the invoice
                    System.out.println("\nItems:");
                    System.out.printf("%-8s %-15s %-8s %-10s%n",
                            "Item ID", "Name", "Quantity", "Unit Price");
                    System.out.println("-------------------------------------------");
                    for (Item item : invoice.getItemList()) {
                        System.out.printf("%-8d %-15s %-8d $%-10.2f%n",
                                item.getItemID(), item.getName(),
                                item.getQuantity(), item.getUnitPrice());
                    }
                    System.out.println("-------------------------------------------");
                    break;
                }
            }
            //if ID is not found
            if (!isFound) {
                System.out.println("Invoice with ID " + invoiceToFind + " is not found");
            } else{
                break;
            }
        }
    }

    //Getters & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getCostumerName() {
        return costumerName;
    }

    public void setCostumerName(String costumerName) {
        this.costumerName = costumerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Item> getItemList() {
        return items;
    }

    public void setItemList(List<Item> itemList) {
        this.items = itemList;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    //methode to calculate total amount of invoice
    public Double totalAmount(List<Item> itemList) {

        /* Error Handling */
        if (itemList == null || itemList.isEmpty()) { //if item list is empty
            //prompting user that item list is empty
            System.out.println("\nError: Item list is empty. Cannot calculate total amount.");
            return 0.0; //quitting method
        }

        double total = 0.0;
        for (Item item : itemList) {
            //checking each item
            if (item != null) {
                //validating item quantity and price
                if (item.getQuantity() != null && item.getUnitPrice() != null) {
                    total = total + (item.getUnitPrice() * item.getQuantity());
                } else {
                    //null item data (price, quantity)
                    System.out.println("Error: Invalid item data found. Skipping calculation for this item.");
                }
            } else {
                //null item
                System.out.println("Error: Null item found in itemList. Skipping calculation for this item.");
            }
        }

        return total;
    }

    //method to update balance
    public void updateBalance() {
        this.balance = this.totalAmount - this.paidAmount;
    }

    //equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceID, invoice.invoiceID);
    }

    //hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(invoiceID);
    }

    //toString()
    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceID +
                ", costumerName='" + costumerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date='" + date + '\'' +
                ", itemList=" + items +
                ", totalAmount=" + totalAmount +
                ", payedAmount=" + paidAmount +
                ", balance=" + balance +
                '}';
    }
}
