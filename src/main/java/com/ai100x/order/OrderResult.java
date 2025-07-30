package com.ai100x.order;

import java.util.*;

public class OrderResult {
    private int totalAmount;
    private List<OrderItem> receivedItems;

    public OrderResult(int totalAmount, List<OrderItem> receivedItems) {
        this.totalAmount = totalAmount;
        this.receivedItems = receivedItems;
    }
    public int getTotalAmount() { return totalAmount; }
    public List<OrderItem> getReceivedItems() { return receivedItems; }
}
