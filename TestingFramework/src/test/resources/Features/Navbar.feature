Feature: Login

  In order to be able to buy items
  As a registered user of the Sauce Labs website
  I want to be able to sign in to my account

  Background:
    Given I am on any non-checkout page

  @Happy
  Scenario: Open the what's new page
    When I click the what's new button
    Then I should land on the what's new page

  @Sad
  Scenario Outline: Login with valid username and invalid password
    And I have entered the username "standard_user"
    And I have entered the password "<passwords>"
    When I click the login button
    Then I should see an error message that contains "Epic sadface"
    Examples:
      | passwords |
      | wrong     |
      | 12345     |
      | Nishy     |