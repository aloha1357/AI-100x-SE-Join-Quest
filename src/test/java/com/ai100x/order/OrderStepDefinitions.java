package com.ai100x.order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.*;

public class OrderStepDefinitions {
    private int threshold;
    private int discount;
    private OrderService orderService;
    private List<OrderItem> orderItems;
    private OrderResult orderResult;
    private int originalAmount;

    @Given("no promotions are applied")
    public void no_promotions_are_applied() {
        orderService = new OrderService();
    }

    @Given("the threshold discount promotion is configured:")
    public void the_threshold_discount_promotion_is_configured(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        threshold = Integer.parseInt(rows.get(0).get("threshold"));
        discount = Integer.parseInt(rows.get(0).get("discount"));
        orderService = new OrderService(threshold, discount);
    }

    @When("a customer places an order with:")
    public void a_customer_places_an_order_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        orderItems = new ArrayList<>();
        originalAmount = 0;
        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            int quantity = Integer.parseInt(row.get("quantity"));
            int unitPrice = Integer.parseInt(row.get("unitPrice"));
            orderItems.add(new OrderItem(productName, quantity, unitPrice));
            originalAmount += quantity * unitPrice;
        }
        orderResult = orderService.placeOrder(orderItems);
    }

    @Then("the order summary should be:")
    public void the_order_summary_should_be(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        if (expected.get(0).containsKey("originalAmount")) {
            int expOriginal = Integer.parseInt(expected.get(0).get("originalAmount"));
            int expDiscount = Integer.parseInt(expected.get(0).get("discount"));
            int expTotal = Integer.parseInt(expected.get(0).get("totalAmount"));
            if (originalAmount != expOriginal) {
                throw new AssertionError("Expected originalAmount: " + expOriginal + ", but got: " + originalAmount);
            }
            if (orderResult.getDiscount() != expDiscount) {
                throw new AssertionError("Expected discount: " + expDiscount + ", but got: " + orderResult.getDiscount());
            }
            if (orderResult.getTotalAmount() != expTotal) {
                throw new AssertionError("Expected totalAmount: " + expTotal + ", but got: " + orderResult.getTotalAmount());
            }
        } else {
            int expTotal = Integer.parseInt(expected.get(0).get("totalAmount"));
            if (orderResult.getTotalAmount() != expTotal) {
                throw new AssertionError("Expected totalAmount: " + expTotal + ", but got: " + orderResult.getTotalAmount());
            }
        }
    }

    @And("the customer should receive:")
    public void the_customer_should_receive(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        List<OrderItem> received = orderResult.getReceivedItems();
        if (received.size() != expected.size()) {
            throw new AssertionError("Expected received items size: " + expected.size() + ", but got: " + received.size());
        }
        for (int i = 0; i < expected.size(); i++) {
            String expName = expected.get(i).get("productName");
            int expQty = Integer.parseInt(expected.get(i).get("quantity"));
            OrderItem item = received.get(i);
            if (!item.getProductName().equals(expName) || item.getQuantity() != expQty) {
                throw new AssertionError("Expected: " + expName + ", " + expQty + "; but got: " + item.getProductName() + ", " + item.getQuantity());
            }
        }
    }
}
