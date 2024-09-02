Feature: NavBar

  In order to be able to buy items
  As a registered user of the Sauce Labs website
  I want to be able to sign in to my account

  Background:
    Given I am on any non-checkout page

  @Happy
  Scenario Outline: Open Each Page From Navbar
    When I click the <button> button
    Then I should land on the <page> page
    Examples:
      | button       | page          |
      | "What's New" | "what-is-new" |
      | "Women"      | "women"       |
      | "Men"        | "men"         |
      | "Gear"       | "gear"        |
      | "Training"   | "training"    |
      | "Sale"       | "sale"        |

  @Happy
  Scenario Outline: Hover over dropdowns
    When I hover over the <button> button
    Then I should see the <submenu> dropdown appear
    Examples:
      | button     | submenu    |
      | "Women"    | "Women"    |
      | "Men"      | "Men"      |
      | "Gear"     | "Gear"     |
      | "Training" | "Training" |

  @Happy
  Scenario Outline: Click on dropdowns
    When I hover over the <button> button
    And I click on the <dropdown> dropdown button
    Then I should land on the <button>'s <page> page
    Examples:
      | button     | dropdown            | page                |
      | "Women"    | "Tops"              | "tops-women"        |
      | "Women"    | "Bottoms"           | "bottoms-women"     |
      | "Men"      | "Tops"              | "tops-men"          |
      | "Men"      | "Bottoms"           | "bottoms-men"       |
      | "Gear"     | "Bags"              | "bags"              |
      | "Gear"     | "Fitness Equipment" | "fitness-equipment" |
      | "Gear"     | "Watches"           | "watches"           |
      | "Training" | "Video Download"    | "training-video"    |

  @Happy
  Scenario Outline: Hover over dropdown submenus
    When I hover over the <button> button
    And I hover over the <dropdown> dropdown option
    Then I should see the <dropdown> dropdown appear under <button>
    Examples:
      | button  | dropdown  |
      | "Men"   | "Tops"    |
      | "Men"   | "Bottoms" |
      | "Women" | "Tops"    |
      | "Women" | "Bottoms" |

  @Happy
  Scenario Outline: Click on submenus
    When I hover over the <button> button
    And I hover over the <dropdown> dropdown option
    And I click on the <dropdown>'s <link> dropdown button
    Then I should land on the <button>'s <page> page
    Examples:
      | button  | dropdown  | link                    | page                                       |
      | "Women" | "Tops"    | "Jackets"               | "tops-women/jackets-women"                 |
      | "Women" | "Tops"    | "Hoodies & Sweatshirts" | "tops-women/hoodies-and-sweatshirts-women" |
      | "Women" | "Tops"    | "Tees"                  | "tops-women/tees-women"                    |
      | "Women" | "Tops"    | "Bras & Tanks"          | "tops-women/tanks-women"                   |
      | "Women" | "Bottoms" | "Pants"                 | "bottoms-women/pants-women"                |
      | "Women" | "Bottoms" | "Shorts"                | "bottoms-women/shorts-women"               |
      | "Men"   | "Tops"    | "Jackets"               | "tops-men/jackets-men"                     |
      | "Men"   | "Tops"    | "Hoodies & Sweatshirts" | "tops-men/hoodies-and-sweatshirts-men"     |
      | "Men"   | "Tops"    | "Tees"                  | "tops-men/tees-men"                        |
      | "Men"   | "Tops"    | "Tanks"                 | "tops-men/tanks-men"                       |
      | "Men"   | "Bottoms" | "Pants"                 | "bottoms-men/pants-men"                    |
      | "Men"   | "Bottoms" | "Shorts"                | "bottoms-men/shorts-men"                   |