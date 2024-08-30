Feature: Logout
  As a logged-in user,
  I want to log out of my account
  So that I can ensure my account information is secure and prevent unauthorized access.

  Scenario: Logging out successfully
    Given I am on any page of the application
    And I am logged into my account
    When I click the LogOut button
    Then I should be redirected to the logged out successfully page
