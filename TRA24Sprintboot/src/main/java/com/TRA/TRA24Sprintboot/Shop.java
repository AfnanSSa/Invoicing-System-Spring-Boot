package com.TRA.TRA24Sprintboot;


import java.util.*;

public class Shop implements ShopManagement{
    public static Scanner scanner = new Scanner(System.in);
    private Integer id; //class ID
    private String name; //shop name
    private static Map<String, String> header = new HashMap<>(); //shop header
    public static List<Item> items = new ArrayList<>();
    public static List<Invoice> invoices = new ArrayList<>();

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> items) {
        Shop.items = items;
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public static void setInvoices(List<Invoice> invoices) {
        Shop.invoices = invoices;
    }

    //methods related to shop
    //method to load data (option 1 in Shop Settings Menu)
    @Override
    public void loadData() {
        System.out.println("Loading Data . .");
        //instances of Item Class
        Item firstItem = new Item(7, "Bread", 1.0, 2);
        Item secondItem = new Item(9, "Cheese", 2.0, 3);

        //adding items to the item list
        items.add(firstItem);
        items.add(secondItem);

        //instance of Invoice Class
        Invoice invoice = new Invoice(9, "Noura", "3342454", "25-04-2024", items, firstItem.totalAmount(2, 1.0));

        //adding invoice to invoice list
        invoices.add(invoice);

        System.out.println("Data loaded successfully");

    }

    //method to set shop name (option 2 in Shop Settings Menu)
    @Override
    public void setShopName() {
        Shop shop = new Shop();
        System.out.println("\nEnter new shop name: ");
        String newShopName = scanner.nextLine();
        System.out.println("Shop Name: " + newShopName); //setting the new name
        shop.setName(newShopName);
    }

    //method to set invoice header (option 3 in Shop Settings Menu)
    @Override
    public void setInvoiceHeader() {
        System.out.println("\nEnter new invoice header:");
        System.out.print("Tel: ");
        String telNumber = stringInputValidation(scanner);
        scanner.nextLine();
        header.put("Phone number", telNumber);
        System.out.print("Fax: ");
        String fax = stringInputValidation(scanner);
        scanner.nextLine();
        header.put("Fax", fax);
        System.out.print("Email: ");
        String email = stringInputValidation(scanner);
        scanner.nextLine();
        header.put("Email", email);
        System.out.print("Website: ");
        String website = stringInputValidation(scanner);
        scanner.nextLine();
        header.put("Website", website);
        //setting shop header
        System.out.println("\nInvoice header set to: Tel: " +
                telNumber + " | Fax: " + fax + " | Email " +
                email + " | Website: " + website);
    }

    //method to add invoice to the list
    @Override
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    //method for validation check
    @Override
    public Integer inputValidation(Scanner scanner) {
        Integer invoiceID = null;
        Boolean isValid = Boolean.TRUE;
        while (isValid) {
            try {
                invoiceID = scanner.nextInt();
                if (invoiceID <= 0) {
                    System.out.println("Invalid invoice ID. Enter a positive integer:");
                    //continue; //continue the loop to prompt for input again
                } else {
                    break; //exit the loop if a valid ID is entered
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a valid integer:");
                scanner.nextLine(); //consuming the invalid input
            }
        }
        return invoiceID;
    }

    //method to check if string input is not empty
    @Override
    public String stringInputValidation(Scanner scanner) {
        String input = " ";
        while (input.isEmpty()) {
            input = scanner.nextLine().trim();
            System.out.println("Input cannot be empty. Please enter a valid value: ");
        }
        return input;
    }

    //method to check if double input is valid
    @Override
    public Double doubleInputValidation(Scanner scanner) {
        Double value = 0.0;
        while (value <= 0) {
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Enter a valid double value:");
                scanner.next(); //consuming invalid input
            }
            value = scanner.nextDouble();
            if (value <= 0) {
                System.out.println("Value must be greater than zero. Enter a valid value: ");
            }
        }
        return value;
    }

    // equals(), hashCode() and toString() methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(name, shop.name) && Objects.equals(header, shop.header);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, header);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", header=" + header +
                '}';
    }
}