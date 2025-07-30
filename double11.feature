  Scenario: Buy 10 different products, 1 each at 100
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | A           | 1        | 100       |
      | B           | 1        | 100       |
      | C           | 1        | 100       |
      | D           | 1        | 100       |
      | E           | 1        | 100       |
      | F           | 1        | 100       |
      | G           | 1        | 100       |
      | H           | 1        | 100       |
      | I           | 1        | 100       |
      | J           | 1        | 100       |
    Then the order summary should be:
      | totalAmount |
      | 1000        |
    And the customer should receive:
      | productName | quantity |
      | A           | 1        |
      | B           | 1        |
      | C           | 1        |
      | D           | 1        |
      | E           | 1        |
      | F           | 1        |
      | G           | 1        |
      | H           | 1        |
      | I           | 1        |
      | J           | 1        |
  Scenario: Buy 27 pairs of socks at 100 each
    Given the Double 11 promotion is active
    When a customer places an order with:
      | productName | quantity | unitPrice |
      | 襪子          | 27       | 100       |
    Then the order summary should be:
      | totalAmount |
      | 2300        |
    And the customer should receive:
      | productName | quantity |
      | 襪子          | 27       |
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
