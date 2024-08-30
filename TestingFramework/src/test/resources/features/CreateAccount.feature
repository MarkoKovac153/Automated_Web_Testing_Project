Feature: CreateAccount

  As a new user,
  I want to create an account
  So that I can place orders and track my purchases.

  Background:
    Given I am on the registration page

  @SoloHappy
  Scenario Outline: Creating a new account with valid information
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |


    Then I should be taken to my new account dashboard
    And my account should be created successfully
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      | McGee    | Test5@email.com | password123! | password123!          |

  @Sad
  Scenario Outline: Creating a new account without a firstname
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "This is a required field"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      |           | McGee    | Test1@email.com | password123! | password123!          |
  @Sad
  Scenario Outline: Creating a new account without a lastname
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "This is a required field"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      |          | Test1@email.com     | password123! | password123!          |
  @Sad
  Scenario Outline: Creating a new account with invalid email
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "enter a valid email address"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      | McGee    | Test1           | password123! | password123!          |
      | Test      | McGee    | @email          | password123! | password123!          |
  @Sad
  Scenario Outline: Creating a new account with a used email
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "already an account with this email address"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      | McGee    | Test1@email.com | password123! | password123!      |
  @Sad
  Scenario Outline: Creating a new account with invalid password
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "length of this field must be equal or greater than 8 symbols"
    Examples:
      | firstname | lastname  | email           | password  | password_confirmation |
      | Test      | McGee     | Test1@email.com | p         | p                     |
      | Test      | McGee     | Test1@email.com | wrong     | wrong                 |
      | Test      | McGee     | Test1@email.com | 12345     | 12345                 |
      | Test      | McGee     | Test1@email.com | Nishy     | p                     |
  @Sad
  Scenario Outline: Creating a new account without a password confirmation
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "required field"
    Examples:
      | firstname | lastname  | email           | password      | password_confirmation |
      | Test      | McGee     | Test1@email.com | password123!  |                       |
  @Sad
  Scenario Outline: Creating a new account without matching password and confirmation
    When I enter the registration details
      | Field         | Value           |
      | First Name    | <firstname>     |
      | Last Name     | <lastname>      |
      | Email         | <email>         |
      | Password      | <password>      |
      | Confirmation  | <password_confirmation>      |
    Then I should see an account error message that contains "enter the same value again"
    Examples:
      | firstname | lastname  | email           | password      | password_confirmation |
      | Test      | McGee     | Test1@email.com | password123!  | p                     |
