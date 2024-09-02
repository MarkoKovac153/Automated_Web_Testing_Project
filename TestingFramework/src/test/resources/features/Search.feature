Feature: Search

  In order to find items on the site we can search for them
  we do this using the search bar


  @Happy
  Scenario Outline: Search for a product on the home page that is available
    Given I am on the homepage
    When I enter "<product>" into the search bar
    And I click on the search icon
    Then I should see the product "<product>"
    Examples:
      |product|
      |Proteus Fitness Jackshirt|
      |Circe Hooded Ice Fleece|
      |Compete Track Tote|

  @Sad
  Scenario Outline: Search for a product on the homepage that is not available
    Given I am on the homepage
    When I enter "<incorrectProduct>" into the search bar
    And I click on the search icon
    Then I should see a message saying "Your search returned no results."
    Examples:
      |incorrectProduct|
      |sploink|
      |AvAAAAAAA-Awnd dhfdgh|
      |ooooooooooooooooooooo|

  @Sad
  Scenario Outline: Search for an item with a query shorter than three characters
    Given I am on the homepage
    When I enter "<shortSearchTerm>" into the search bar
    And I click on the search icon
    Then I should see a message saying "Minimum Search query length is 3"
    Examples:
      |shortSearchTerm|
      |4|
      |j|
      |lo|