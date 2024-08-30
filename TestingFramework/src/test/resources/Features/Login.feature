#Feature: Login
#
#  In order to be able to buy items
#  As a registered user of the Sauce Labs website
#  I want to be able to sign in to my account
#
#  Background:
#    Given I am on the home page
#
#  @Happy
#  Scenario: Login with valid username and valid password
#    And I have entered the username "standard_user"
#    And I have entered the password "secret_sauce"
#    When I click the login button
#    Then I should land on the inventory page
#
#  @Sad
#  Scenario Outline: Login with valid username and invalid password
#    And I have entered the username "standard_user"
#    And I have entered the password "<passwords>"
#    When I click the login button
#    Then I should see an error message that contains "Epic sadface"
#    Examples:
#      | passwords |
#      | wrong     |
#      | 12345     |
#      | Nishy     |
