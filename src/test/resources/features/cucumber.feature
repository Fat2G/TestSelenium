Feature: Login
  Try to login to swaglabs

  Scenario Outline: Login with the right username and password
    Given I am on the website login page with "<driver>"
    When I enter correct login information
    And I click on the login button
    Then I should be on the inventory page

    Examples:
      | driver  |
      | chrome  |
      | firefox |


  Scenario Outline: Open item detail page by clicking image
    Given I am on the website login page with "<driver>"
    When I enter correct login information
    And I click on the login button
    And I click on the image of product with <id>
    Then I should be on the item detail page of product with <id>

    Examples:
      | driver  | id |
      | chrome  | 3  |
      | chrome  | 4  |
      | chrome  | 2  |
      | firefox | 3  |
      | firefox | 2  |
      | firefox | 5  |
