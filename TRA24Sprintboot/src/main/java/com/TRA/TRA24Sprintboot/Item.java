package com.TRA.TRA24Sprintboot;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Item implements ItemManagement{
    private Integer id; //class ID
    private Integer itemID; //item ID
    private String name; //item name
    private Double unitPrice;
    private Integer quantity;
    public static Shop shop = new Shop();

    public Item() {
    }

    public Item(Integer itemID, String name, Double unitPrice, Integer quantity) {
        this.itemID = itemID;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    //method to calculate total amount
    @Override
    public Double totalAmount(Integer quantity, Double unitPrice) {
        return quantity * unitPrice;
    }


    //method to add items (option 1 in Manage Items Menu)
    @Override
    public void addItem() {
        System.out.println("Adding Item . .");
        List<Item> itemList = shop.getItems();
        //asking user to enter item details
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item ID: ");
        Integer itemID = shop.inputValidation(scanner);
        scanner.nextLine(); //consuming newline character after reading Integer
        System.out.print("Enter item name: ");
        String name = shop.stringInputValidation(scanner);
        scanner.nextLine();
        System.out.print("Enter unit price: ");
        double unitPrice = shop.doubleInputValidation(scanner);
        System.out.print("Enter quantity: ");
        int quantity = shop.inputValidation(scanner);

        Item newItem = new Item(itemID, name, unitPrice, quantity);
        itemList.add(newItem); //adding the item to the items list

        System.out.println("Item Added Successfully..");
    }

    //method to delete items (option 2 in Manage Items Menu)
    @Override
    public void deleteItem() {
        System.out.println("Deleting Item . .");
        List<Item> itemList = shop.getItems();
        reportAllItems();
        //asking user to select and delete an item from the items list
        Scanner scanner = new Scanner(System.in);
        Boolean validInput = Boolean.TRUE;
        Integer deleteItem;
        while (validInput) {
            System.out.println("Enter item ID to delete: ");
            deleteItem = shop.inputValidation(scanner);


            //finding and removing the item from the items list
            Boolean isFound = Boolean.FALSE;
            Item itemToRemove = null;

            for (Item item : itemList) {
                if (item != null && item.getItemID() != null && item.getItemID().equals(deleteItem)) {
                    itemToRemove = item;
                    isFound = Boolean.TRUE;
                    break;
                }
            }
            if (isFound) {
                itemList.remove(itemToRemove);
                System.out.println("Item deleted successfully");
                break;
            } else {
                System.out.println("Item with ID " + deleteItem + " not found");
            }
        }

    }

    //method to change item price (option 3 in Manage Items Menu)
    @Override
    public void changeItemPrice() {
        System.out.println("Updating Price . .");
        Scanner scanner = new Scanner(System.in);
        List<Item> itemList = shop.getItems();
        reportAllItems();
        Boolean validInput = Boolean.TRUE;
        Integer updatePrice;

        while (validInput) {
            System.out.println("Enter item ID to update the price: ");
            updatePrice = shop.inputValidation(scanner);

            Boolean isFound = Boolean.FALSE;


            for (Item item : itemList) {
                if (item.getItemID().equals(updatePrice)) {
                    System.out.println("Enter new price: ");
                    Double newPrice = scanner.nextDouble();

                    //updating the price
                    item.setUnitPrice(newPrice);
                    System.out.println("Item price updated successfully");

                    isFound = Boolean.TRUE;
                    break;
                }
            }
            if (isFound) {
                break;
            }else {
                System.out.println("Item with ID " + updatePrice + " not found");
            }
        }
    }

    //method to report all items (option 4 in Manage Items Menu)
    @Override
    public void reportAllItems() {
        List<Item> itemList = shop.getItems();
        System.out.println("Displaying All Items . .");
        //displaying report of all items in items list
        if (itemList.isEmpty()) {
            System.out.println("No items available");
        } else {
            System.out.println("List of items: ");
            for (Item item : itemList) {
                System.out.println(
                        "Item ID: " + item.getItemID() +
                                "| Name: " + item.getName() +
                                "| Unit Price: $" + item.getUnitPrice() +
                                "| Quantity: " + item.getQuantity()
                );
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

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemID, item.itemID);
    }

    //hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(itemID);
    }

    //toString()
    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
