Feature: Product Details

 The user must be able access relevant product details when they
  try to view an item


  @Happy
  Scenario Outline: a user should be able to click on a product and be brought to it's product page
    Given I am on the page "<generalPage>"
    When I click on the <productNumber>th product
    Then I should be on the details page "<productPage>"
    Examples:
      |generalPage|productNumber|productPage|
      |women/tops-women.html|0|portia-capri|
      |women/tops-women.html|2|sylvia-capri|
      |women/tops-women.html|5|bardot-capri|
