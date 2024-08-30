package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Arrays;

public class RegistrationPageStepDefs {
    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    @Before
    public void setup(){
        TestSetup.startServiceWithDefaultBrowser();
        TestSetup.createWebDriver();
    }
    @After
    public void afterEach(){
        TestSetup.stopService();
    }
    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @When ("I enter the registration details {string}{string}{string}{string}{string}")
    public void iEnterMyRegistrationDetails(String firstname, String lastname, String email, String password, String password_confirmation) {
        website.getRegistrationPage().registerAccount(firstname, lastname, email, password, password_confirmation);
    }

    @When("I enter my first name {string}")
    public void iEnterMyFirstName(String firstname) {
        website.getRegistrationPage().enterFirstName(firstname);
    }
    @And("I enter my last name {string}")
    public void iEnterMyLastName(String lastname) {
        website.getRegistrationPage().enterLastName(lastname);
    }
    @And("I enter my email {string}")
    public void iEnterMyEmail(String email) {
        website.getRegistrationPage().enterEmail(email);
    }
    @And("I enter my password {string}")
    public void iEnterMyPassword(String password) {
        website.getRegistrationPage().enterPassword(password);
    }
    @And("I enter my password confirmation {string}")
    public void iEnterMyPasswordConfirmation(String password_confirmation) {
        website.getRegistrationPage().enterPasswordConfirmation(password_confirmation);
    }
    @And("I click the Create an Account button")
    public void iClickTheButton() {
        website.getRegistrationPage().clickRegister();
    }

    @Then("I should see an error message that contains {string}")
    public void iShouldSeeAnErrorMessageThatContains(String errorMessage) {
        Assert.assertTrue(Arrays.stream(website.getRegistrationPage().getErrors()).anyMatch(e -> e.contains(errorMessage)));
    }

    @Then("I should be taken to my account dashboard")
    public void iShouldBeTakenToMyAccountDashboard() {
        Assert.assertEquals("https://magento.softwaretestingboard.com/customer/account/", website.getCurrentUrl());
    }
    @And("my account should be created successfully")
    public void myAccountShouldBeCreatedSuccessfully() {
        Assert.assertTrue(website.getAccountPage().getMessage().contains("Thank you for registering"));
    }
}
