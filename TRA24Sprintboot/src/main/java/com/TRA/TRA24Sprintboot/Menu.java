package com.TRA.TRA24Sprintboot;


import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    public static Shop shop = new Shop();

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    //method to display and handle main menu
    public Integer displayMainMenu() {
        System.out.println("\n:::Menu:::\nSelect option:" +
                            "\n1. Shop Settings" +
                            "\n2. Manage Shop Items" +
                            "\n3. Create New Invoice" +
                            "\n4. Report: Statistics" +
                            "\n5. Report: All Invoices" +
                            "\n6. Search Invoices" +
                            "\n7. Program Statistics" +
                            "\n8. Exit");

        System.out.print("\nEnter your choice: ");
        return shop.inputValidation(scanner);
    }

    //method to display and handle shop settings menu
    public Integer displayShopSettingsMenu() {
        System.out.println("\nShop Settings:" +
                            "\n1. Load Data (Items and invoices)" +
                            "\n2. Set Shop Name" +
                            "\n3. Set Invoice Header" +
                            "\n4. Go Back");

        System.out.print("\nEnter your choice: ");
        return shop.inputValidation(scanner);
    }

    //method to display and handle manage shop items menu
    public Integer displayManageItemsMenu() {
        System.out.println("\nManage Shop Items:" +
                            "\n1. Add Items" +
                            "\n2. Delete Items" +
                            "\n3. Change Item Price" +
                            "\n4. Report All Items" +
                            "\n5. Go Back");

        System.out.print("\nEnter your choice: ");
        return shop.inputValidation(scanner);
    }
}
