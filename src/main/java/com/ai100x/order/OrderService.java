package com.ai100x.order;

import java.util.*;

public class OrderService {
    private int threshold;
    private int discount;

    public OrderService() {
        this.threshold = 0;
        this.discount = 0;
    }
    public OrderService(int threshold, int discount) {
        this.threshold = threshold;
        this.discount = discount;
    }
    public OrderResult placeOrder(List<OrderItem> items) {
        int originalAmount = 0;
        for (OrderItem item : items) {
            originalAmount += item.getUnitPrice() * item.getQuantity();
        }
        int appliedDiscount = (threshold > 0 && originalAmount >= threshold) ? discount : 0;
        int totalAmount = originalAmount - appliedDiscount;
        return new OrderResult(originalAmount, appliedDiscount, totalAmount, items);
    }
}
