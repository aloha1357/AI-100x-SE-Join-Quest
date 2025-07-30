package com.ai100x.order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import java.util.*;
import com.ai100x.order.*;

public class OrderStepDefinitions {
    private OrderService orderService;
    private List<OrderItem> orderItems;
    private OrderResult orderResult;

    @Given("no promotions are applied")
    public void no_promotions_are_applied() {
        orderService = new OrderService();
    }

    @When("a customer places an order with:")
    public void a_customer_places_an_order_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        orderItems = new ArrayList<>();
        for (Map<String, String> row : rows) {
            String productName = row.get("productName");
            int quantity = Integer.parseInt(row.get("quantity"));
            int unitPrice = Integer.parseInt(row.get("unitPrice"));
            orderItems.add(new OrderItem(productName, quantity, unitPrice));
        }
        orderResult = orderService.placeOrder(orderItems);
    }

    @Then("the order summary should be:")
    public void the_order_summary_should_be(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        int expectedTotal = Integer.parseInt(expected.get(0).get("totalAmount"));
        if (orderResult.getTotalAmount() != expectedTotal) {
            throw new AssertionError("Expected totalAmount: " + expectedTotal + ", but got: " + orderResult.getTotalAmount());
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
