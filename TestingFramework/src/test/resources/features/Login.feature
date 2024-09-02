Feature: Login

  As a returning user,
  I want to log in to my account
  So that I can view my order history and manage my account details.

  Background:
    Given I am on the login page

  @Happy
  Scenario Outline: Login with valid username and valid password
    When I enter the following login details "<email>" "<password>"
    Then I should be taken to my account dashboard
    Examples:
      | email           | password        |
      | test1@email.com | password123!    |


  @Sad
  Scenario Outline: Login with valid username and invalid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "account sign-in was incorrect"
    Examples:
      | email           | password    |
      | test1@email.com | correct     |
      | test1@email.com | 12345       |

  @Sad
  Scenario Outline: Login with valid username and no password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "required"
    Examples:
      | email           | password    |
      | test1@email.com |             |

  @Sad
  Scenario Outline: Login with no email and a valid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "required"
    Examples:
      | email           | password    |
      |                 | password123!|
  @Sad
  Scenario Outline: Login with not an email and a valid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "enter a valid email"
    Examples:
      | email           | password    |
      | k               | password123!|
  @Sad
  Scenario Outline: Login with an unaccounted username and a valid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "account sign-in was incorrect"
    Examples:
      | email           | password    |
      | Kat@email.com   | password123!|
