package com.TRA.TRA24Sprintboot;


public interface ItemManagement {
    Double totalAmount(Integer quantity, Double unitPrice);
    void addItem();
    void deleteItem();
    void changeItemPrice();
    void reportAllItems();

}
