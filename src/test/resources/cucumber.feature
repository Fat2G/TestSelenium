Feature: Login
  Try to login to swaglabs

  Scenario: Login with the right username and password
    Given I am on the website login page
    When I enter correct login information
    And I click on the login button
    Then I should be on the inventory page
