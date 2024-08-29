Feature: CreateAccount

  As a new user,
  I want to create an account
  So that I can place orders and track my purchases.


#  @Happy
#  Scenario: Creating a new account with valid information
#    Given I am on the registration page
#    When I enter my first name "Test"
#    And I enter my last name "McGee"
#    And I enter my email "Test1@email.com"
#    And I enter my password "password123!"
#    And I enter my password confirmation "password123!"
#    And I click the Create an Account button
#    Then my account should be created successfully
#    And I should be taken to my account dashboard
#    And I should see a welcome message

  @Sad
  Scenario: Creating a new account with invalid password
    Given I am on the registration page
    When I enter my first name "Test"
    And I enter my last name "McGee"
    And I enter my email "Test1@email.com"
    And I enter my password "P"
    And I enter my password confirmation "P"
    And I click the Create an Account button
    Then I should see an error message that contains "length of this field must be equal or greater than 8 symbols"
