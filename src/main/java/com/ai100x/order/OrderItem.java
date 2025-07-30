package com.ai100x.order;

public class OrderItem {
    private String productName;
    private String category;
    private int quantity;
    private int unitPrice;

    public OrderItem(String productName, String category, int quantity, int unitPrice) {
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItem(String productName, int quantity, int unitPrice) {
        this(productName, null, quantity, unitPrice);
    }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public int getUnitPrice() { return unitPrice; }
}
