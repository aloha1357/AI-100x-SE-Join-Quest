package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class DummyStepDefs {
    private int total;

    @Given("an order with no items")
    public void an_order_with_no_items() {
        total = 0;
    }

    @When("I calculate the total")
    public void i_calculate_the_total() {
        // No items, total remains 0
    }

    @Then("the total should be 0")
    public void the_total_should_be_0() {
        assertEquals(0, total);
    }
}
