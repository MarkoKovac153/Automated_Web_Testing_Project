package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.ProductDetailsPage;
import com.sparta.testing.pages.Website;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class CartStepDefs {

    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private int itemsInCartExpected = 0;


    @Given("I am on the product details page {string}")
    public void iAmOnTheProductDetailsPage(String pageAddress) {
        website = TestSetup.getWebsite(BASE_URL + pageAddress);
    }

    @When("I select the {int}th size and {int}th colour")
    public void iSelectTheSizeAndColor(int size, int colour) {
        ProductDetailsPage productDetailsPage = website.getProductDetailsPage();
        productDetailsPage.clickSizeOption(size);
        productDetailsPage.clickColourOption(colour);
    }

    @And("I enter a quantity of {int}")
    public void iEnterAQuantityOf(int quantity) {
        itemsInCartExpected = website.getProductDetailsPage().getCartCounter() + quantity;
        website.getProductDetailsPage().enterQuantity(quantity);
    }

    @And("I click the Add to Cart button")
    public void iClickTheAddToCartButton() {
        website.getProductDetailsPage().clickAddToCartButton();
    }

    @And("the shopping cart icon should update to reflect the number of items in the cart")
    public void theShoppingCartIconShouldUpdateToReflectTheNumberOfItemsInTheCart() {
        int itemsInCartActual = website.getProductDetailsPage().getCartCounter();
        Assertions.assertEquals(itemsInCartExpected, itemsInCartActual);
    }

    @Then("I should see a message on the details page saying {string}")
    public void iShouldSeeAMessageOnTheDetailsPageSaying(String expectedMessage) {
        String actualMessage = website.getProductDetailsPage().getMessageBoxText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
