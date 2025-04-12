Feature: Kafka XML Message Consumption and REST API Integration

  Scenario: Consume XML message from Kafka and retrieve account details
    Given a valid XML message with accountId "4566534" and currency "USD"
    When the message is consumed by Kafka listener
    Then the REST API should return the account details for "12345"
    And the console should print the account details and currency
