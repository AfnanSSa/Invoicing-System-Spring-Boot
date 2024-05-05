package com.TRA.TRA24Sprintboot;

public interface InvoiceHandler {
    void createInvoice();
    void reportItemStatistics();
    void reportAllInvoices();
    void searchInvoices();
    Item findItemByID(Integer itemID);
    void displayAllItems();

}
