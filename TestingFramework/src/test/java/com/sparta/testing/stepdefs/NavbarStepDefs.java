package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

//@Browser(BrowserType.EDGE)
//@Browser(BrowserType.CHROME)
//@Browser(BrowserType.FIREFOX)
public class NavbarStepDefs {
    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @BeforeAll
    public static void beforeAll() {
        TestSetup.startService(NavbarStepDefs.class);
        TestSetup.createWebDriver();
    }

    @AfterAll
    public static void afterAll() {
        TestSetup.stopService();
    }

//    @Before
//    public void beforeEach() {
//        TestSetup.startService(this.getClass());
//        TestSetup.createWebDriver();
//    }
//
//    @After
//    public void afterEach() {
//        TestSetup.stopService();
//    }

    @Given("I am on any non-checkout page")
    public void iAmOnAnyNonCheckoutPage() {
        website = TestSetup.getWebsite(BASE_URL);
        website.getHomePage().waitForPageToLoad();
    }

    @When("I click the {string} button")
    public void iClickTheButton(String button) {
        website.getHomePage().clickButtonOnNavbar(button);
    }

    @Then("I should land on the {string} page")
    public void iShouldLandOnThePage(String page) {
        MatcherAssert.assertThat(website.getCurrentUrl(), containsString("/" + page + ".html"));
    }

    @When("I hover over the {string} button")
    public void iHoverOverTheButtonButton(String button) {
        website.getHomePage().hoverButtonOnNavbar(button);
    }

    @Then("I should see the {string} dropdown appear")
    public void iShouldSeeTheSubmenuAppear(String submenu) {
        MatcherAssert.assertThat(website.getHomePage().isDropDownVisible(submenu), is(true));
    }
}
