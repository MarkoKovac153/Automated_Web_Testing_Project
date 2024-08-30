Feature: Login

  In order to be able to buy items
  As a registered user of the Sauce Labs website
  I want to be able to sign in to my account

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
      | test1@email.com | wrong       |
      | test1@email.com | 12345       |
      | test1@email.com | Nishy       |

  @Sad
  Scenario Outline: Login with valid username and no password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "required field"
    Examples:
      | email           | password    |
      | test1@email.com |             |

  @Sad
  Scenario Outline: Login with no username and a valid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "required field"
    Examples:
      | email           | password    |
      |                 | password123! |
  @Sad
  Scenario Outline: Login with an invalid username and a valid password
    When I enter the following login details "<email>" "<password>"
    Then I should see a login error message that contains "account sign-in was incorrect"
    Examples:
      | email           | password    |
      | Kat@email.com   | password123! |
