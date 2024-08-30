package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPageStepDefs {
    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    @Given("I am on the registration page")
    public void iAmOnTheRegistrationPage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @When ("I enter the registration details")
    public void iEnterMyRegistrationDetails(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);

        Map<String, String> dataMap = new HashMap<>();

        for (List<String> row : rows) {
            if (row.size() == 2) {
                String field = row.get(0).trim();
                String value = row.get(1).trim();
                dataMap.put(field, value);
            }
        }
        website.getRegistrationPage().registerAccount(
                dataMap.get("First Name"),
                dataMap.get("Last Name"),
                dataMap.get("Email"),
                dataMap.get("Password"),
                dataMap.get("Confirmation"));
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

    @Then("I should see an account error message that contains {string}")
    public void iShouldSeeAnErrorMessageThatContains(String errorMessage) {
        Assert.assertTrue(Arrays.stream(website.getRegistrationPage().getErrors()).anyMatch(e -> e.contains(errorMessage)));
    }

    @Then("I should be taken to my new account dashboard")
    public void iShouldBeTakenToMyAccountDashboard() {
        Assert.assertEquals("https://magento.softwaretestingboard.com/customer/account/", website.getCurrentUrl());
    }
    @And("my account should be created successfully")
    public void myAccountShouldBeCreatedSuccessfully() {
        Assert.assertTrue(website.getAccountPage().getMessage().contains("Thank you for registering"));
    }
}
