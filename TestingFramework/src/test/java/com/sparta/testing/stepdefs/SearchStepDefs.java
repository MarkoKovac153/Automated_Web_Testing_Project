package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

public class SearchStepDefs{

    protected Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @When("I enter {string} into the search bar")
    public void iEnterIntoTheSearchBar(String searchTerm) {
        website.getSearchPage().enterInSearchBar(searchTerm);
    }

    @And("I click on the search icon")
    public void iClickOnTheSearchIcon() {
        website.getSearchPage().clickSearchIcon();
    }

    @Then("I should see the product {string}")
    public void iShouldSeeTheProduct(String expectedProduct) {
        List<String> products = website.getSearchPage().getAllProductTexts();
        Assertions.assertTrue(products.contains(expectedProduct));
    }

    @Then("I should see a message saying {string}")
    public void iShouldSeeAMessageSaying(String expectedMessage) {
        String actualMessage = website.getSearchPage().getNoticeMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
