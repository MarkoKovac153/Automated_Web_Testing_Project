package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Arrays;

public class LoginPageStepdefs {

    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/customer/account/login";

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @When("I enter the following login details {string} {string}")
    public void iEnterTheFollowingLoginDetails(String username, String password) {
        website.getLoginPage().enterLoginDetails(username, password);
    }
    @Then("I should see a login error message that contains {string}")
    public void iShouldSeeAnErrorMessageThatContains(String errorMessage) {
        Assert.assertTrue(Arrays.stream(website.getLoginPage().getErrors()).anyMatch(e -> e.contains(errorMessage)));
    }

    @Then("I should be taken to my account dashboard")
    public void iShouldBeTakenToMyAccountDashboard() {
        Assert.assertEquals("https://magento.softwaretestingboard.com/customer/account/", website.getCurrentUrl());
    }

}
