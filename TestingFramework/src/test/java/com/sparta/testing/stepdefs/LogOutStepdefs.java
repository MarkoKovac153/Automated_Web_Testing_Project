package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

public class LogOutStepdefs {
    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com";

    @Given("I am on any page of the application")
    public void iAmOnAnyPageOfTheApplication() {
        website = TestSetup.getWebsite(BASE_URL);
    }
    @And("I am logged into my account")
    public void iAmLoggedIntoMyAccount() {
        website.getHomePage().clickLoginOrLogoutButton();
        website.getLoginPage().enterLoginDetails("test1@email.com", "password123!");
        MatcherAssert.assertThat(website.getHomePage().accountSignedIn(), org.hamcrest.Matchers.equalTo(true));
    }

    @When("I am not logged into my account")
    public void iAmNotLoggedIntoMyAccount() {
        MatcherAssert.assertThat(website.getHomePage().accountSignedIn(), org.hamcrest.Matchers.equalTo(false));
    }
    @When("I click the LogOut button")
    public void iClickTheButton() {
        website.getHomePage().clickUserField();
        website.getHomePage().clickLoginOrLogoutButton();
    }

    @Then("I should be redirected to the logged out successfully page")
    public void iShouldBeRedirectedToTheLoggedOutSuccessfullyPage() {
        MatcherAssert.assertThat(website.getCurrentUrl(),
                org.hamcrest.Matchers.equalTo(
                        "https://magento.softwaretestingboard.com/customer/account/logoutSuccess/"));
    }
    @Then("the LogOut button should not be present")
    public void theButtonShouldNotBePresent() {
        MatcherAssert.assertThat(website.getHomePage().isLoginOrLogoutButtonPresent(), org.hamcrest.Matchers.equalTo(false));
    }
}
