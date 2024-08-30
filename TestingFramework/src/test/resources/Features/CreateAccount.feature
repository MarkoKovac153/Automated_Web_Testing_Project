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
  Scenario Outline: Creating a new account with invalid password
    Given I am on the registration page
    When I enter my first name "Test"
    And I enter my last name "McGee"
    And I enter my email "Test1@email.com"
    And I enter my password "<passwords>"
    And I enter my password confirmation "<passwords>"
    And I click the Create an Account button
    Then I should see an error message that contains "length of this field must be equal or greater than 8 symbols"
    Examples:
      | passwords |
      | wrong     |
      | 12345     |
      | Nishy     |

    # TODO: change login credentials into a table
  @Sad
  Scenario: Creating a new account with invalid password
    Given I am on the registration page
    When I enter my registration details <RegistrationPage>
    Then I should see an error message that contains "length of this field must be equal or greater than 8 symbols"
    Examples:
    | firstname , lastname  , email           , password , password_confirmation  |
    | Test      , McGee     , Test1@email.com , password , password               |
  @Sad
  Scenario: Creating a new account with invalid email
    Given I am on the registration page
    When I enter my first name "Test"
    And I enter my last name "McGee"
    And I enter my email "Test1"
    And I enter my password "password123!"
    And I enter my password confirmation "password123!"
    And I click the Create an Account button
    Then I should see an error message that contains "enter a valid email address"
