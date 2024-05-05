package com.TRA.TRA24Sprintboot;


import java.util.List;
import java.util.Scanner;

public interface ShopManagement {
    void loadData();
    void setShopName();
    void setInvoiceHeader();
    void addInvoice(Invoice invoice);
    Integer inputValidation(Scanner scanner);
    String stringInputValidation(Scanner scanner);
    Double doubleInputValidation(Scanner scanner);
    void setName(String name);
    List<Item> getItems();
    List<Invoice> getInvoices();

}
