@testPractise1
  Feature: My Stock Upload cucumber acceptance feature


  Scenario Outline: Stock upload using sftp
    Given I <brand name> have an article already on sale with <retailer>
    Then I uploaded the updated stock feed to the <brand name> endpoint
    Then the system has been updated with the new stock level
    And the <retailer> receives the new stock levels


    Examples:
      | brand name | retailer   |
      | 9999999    | retailer |
