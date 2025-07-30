Feature: Order discount logic

  Scenario: Dummy walking skeleton scenario
    Given an order with no items
    When I calculate the total
    Then the total should be 0
