package com.ai100x.order;

import java.util.*;

public class OrderService {
    public OrderResult placeOrder(List<OrderItem> items) {
        int totalAmount = 0;
        List<OrderItem> received = new ArrayList<>();
        for (OrderItem item : items) {
            totalAmount += item.getUnitPrice() * item.getQuantity();
            received.add(new OrderItem(item.getProductName(), item.getQuantity(), item.getUnitPrice()));
        }
        return new OrderResult(totalAmount, received);
    }
}
