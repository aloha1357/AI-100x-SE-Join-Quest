package com.ai100x.order;

import java.util.*;

public class OrderService {
    public OrderResult placeOrderWithBogo(List<OrderItem> items, boolean bogoCosmeticsActive) {
        List<OrderItem> received = new ArrayList<>();
        int originalAmount = 0;
        for (OrderItem item : items) {
            int qty = item.getQuantity();
            if (bogoCosmeticsActive && "cosmetics".equals(item.getCategory())) {
                // 單件買一送一，2件收到3件
                if (qty == 1) {
                    qty = 2;
                } else {
                    qty = qty + 1;
                }
            }
            received.add(new OrderItem(item.getProductName(), item.getCategory(), qty, item.getUnitPrice()));
            originalAmount += item.getUnitPrice() * item.getQuantity();
        }
        int appliedDiscount = (threshold > 0 && originalAmount >= threshold) ? discount : 0;
        int totalAmount = originalAmount - appliedDiscount;
        return new OrderResult(originalAmount, appliedDiscount, totalAmount, received);
    }
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
