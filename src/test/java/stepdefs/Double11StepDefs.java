package test.java.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.OrderService;
import static org.junit.jupiter.api.Assertions.*;

public class Double11StepDefs {
    private List<Map<String, String>> orderItems;
    private int totalAmount;
    private OrderService orderService = new OrderService();

    @Given("the Double 11 promotion is active")
    public void the_double_11_promotion_is_active() {
        // Promotion active, nothing to do for now
    }

    @When("a customer places an order with:")
    public void a_customer_places_an_order_with(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> fullItems = dataTable.asMaps(String.class, String.class);
        orderItems = new ArrayList<>();
        for (Map<String, String> item : fullItems) {
            Map<String, String> simple = new HashMap<>();
            simple.put("productName", item.get("productName"));
            simple.put("quantity", item.get("quantity"));
            orderItems.add(simple);
        }
        totalAmount = orderService.calculateTotalAmount(fullItems);
    }

    @Then("the order summary should be:")
    public void the_order_summary_should_be(io.cucumber.datatable.DataTable dataTable) {
        int expected = Integer.parseInt(dataTable.asMaps(String.class, String.class).get(0).get("totalAmount"));
        assertEquals(expected, totalAmount);
    }

    @Then("the customer should receive:")
    public void the_customer_should_receive(io.cucumber.datatable.DataTable dataTable) {
        // 只驗證商品名稱與數量
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        assertEquals(expected, orderItems);
    }
}
