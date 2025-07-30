package com.ai100x.order;

import java.util.*;

public class OrderResult {
    private int originalAmount;
    private int discount;
    private int totalAmount;
    private List<OrderItem> receivedItems;

    public OrderResult(int originalAmount, int discount, int totalAmount, List<OrderItem> receivedItems) {
        this.originalAmount = originalAmount;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.receivedItems = receivedItems;
    }
    public int getOriginalAmount() { return originalAmount; }
    public int getDiscount() { return discount; }
    public int getTotalAmount() { return totalAmount; }
    public List<OrderItem> getReceivedItems() { return receivedItems; }
}
