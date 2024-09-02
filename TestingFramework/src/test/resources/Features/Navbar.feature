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
        | button       | submenu          |
        | "Women"      | "Women"       |
        | "Men"        | "Men"         |
        | "Gear"       | "Gear"        |
        | "Training"   | "Training"    |