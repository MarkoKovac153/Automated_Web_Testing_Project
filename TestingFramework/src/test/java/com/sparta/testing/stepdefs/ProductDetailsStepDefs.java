package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductDetailsStepDefs {

    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";


    @Given("I am on the page {string}")
    public void iAmOnThePage(String pageAddress) {
        website = TestSetup.getWebsite(BASE_URL + pageAddress);
    }

    @When("I click on the {int}th product")
    public void iClickOnTheProductNumberThProduct(int productIndex) {
        website.getProductsPage();
    }

    @Then("I should be on the details page {string}")
    public void iShouldBeOnTheDetailsPage(String page) {
    }
}
