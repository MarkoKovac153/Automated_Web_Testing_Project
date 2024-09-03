Feature: Cart

  As a user i want to be able to add and remove items from my cart and view it

  @Happy
  Scenario Outline: Creating a new account with valid information
    Given I am logged into my account
    And I am on the product details page "<product_page>"
    When I select the <size_index>th size and <colour_index>th colour
    And I enter a quantity of <quantity>
    And I click the Add to Cart button
    Then I should see a message on the details page saying "You added <product_name> to your shopping cart."
    And the shopping cart icon should update to reflect the number of items in the cart

    Examples:
      | product_page              | size_index | colour_index | quantity | product_name           |
      | breathe-easy-tank.html    | 0          | 0            | 2        | Breathe-Easy Tank      |
      | circe-hooded-ice-fleece.html    | 1          | 2            | 1        | Circe Hooded Ice Fleece      |
      | aether-gym-pant.html      | 2          | 1            | 3        | Aether Gym Pant        |
