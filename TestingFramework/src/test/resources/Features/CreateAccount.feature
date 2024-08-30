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

  Background:
    Given I am on the registration page

  @Sad
  Scenario Outline: Creating a new account with invalid password
    When I enter the registration details "<firstname>""<lastname>""<email>""<password>""<password_confirmation>"
    Then I should see an error message that contains "length of this field must be equal or greater than 8 symbols"
    Examples:
      | firstname | lastname  | email           | password  | password_confirmation |
      | Test      | McGee     | Test1@email.com | p         | p                     |
      | Test      | McGee     | Test1@email.com | wrong     | wrong                 |
      | Test      | McGee     | Test1@email.com | 12345     | 12345                 |
      | Test      | McGee     | Test1@email.com | Nishy     | p                     |

  @Sad
  Scenario Outline: Creating a new account with invalid email
    When I enter the registration details "<firstname>""<lastname>""<email>""<password>""<password_confirmation>"
    Then I should see an error message that contains "enter a valid email address"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      | McGee    | Test1           | password123! | password123!          |
      | Test      | McGee    | @email          | password123! | password123!          |

  @Sad
  Scenario Outline: Creating a new account without a firstname
    When I enter the registration details "<firstname>""<lastname>""<email>""<password>""<password_confirmation>"
    Then I should see an error message that contains "this is a required field"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      |       | McGee    | Test1@email.com     | password123! | password123!          |

  @Sad
  Scenario Outline: Creating a new account without a lastname
    When I enter the registration details "<firstname>""<lastname>""<email>""<password>""<password_confirmation>"
    Then I should see an error message that contains "this is a required field"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      |          | Test1@email.com     | password123! | password123!          |
  @Sad
  Scenario Outline: Creating a new account with a used email
    When I enter the registration details "<firstname>""<lastname>""<email>""<password>""<password_confirmation>"
    Then I should see an error message that contains "already an account with this email address"
    Examples:
      | firstname | lastname | email           | password     | password_confirmation |
      | Test      | McGee    | Test1@email.com | password123! | password123!      |
