@double11_promotion
Feature: Double 11 Promotion for Bulk Purchase Discounts
  As a shopper
  I want the system to apply Double 11 bulk purchase discounts
  So that I can enjoy special pricing when buying in large quantities

  Scenario: Buy 12 pairs of socks at 100 each
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | 襪子          | 12       | 100       |
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    And the customer should receive:
      | productName | quantity |
      | 襪子          | 12       |
