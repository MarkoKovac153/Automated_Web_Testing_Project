package com.sparta.testing.stepdefs;

import com.sparta.testing.browser_annotation.Browser;
import com.sparta.testing.browser_annotation.BrowserType;
import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

@Browser(BrowserType.EDGE)
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
        MatcherAssert.assertThat(website.getHomePage().isDropdownVisible(submenu), is(true));
    }

    @And("I click on the {string} dropdown button")
    public void iClickOnTheDropdownDropdownButton(String dropdown) {
        website.getHomePage().clickLinkOnDropdownMenu(dropdown);
    }

    @Then("I should land on the {string}'s {string} page")
    public void iShouldLandOnThePage(String category, String page) {
        MatcherAssert.assertThat(website.getCurrentUrl(), containsString("/" + category.toLowerCase() + "/" + page + ".html"));
    }

    @And("I hover over the {string} dropdown option")
    public void iHoverOverTheDropdownOption(String dropdown) {
        website.getHomePage().hoverButtonOnDropdown(dropdown);
    }

    @Then("I should see the {string} dropdown appear under {string}")
    public void iShouldSeeTheDropdownAppearUnder(String dropdown, String button) {
        MatcherAssert.assertThat(website.getHomePage().isSubmenuVisible(button, dropdown), is(true));
    }

    @And("I click on the {string}'s {string} dropdown button")
    public void iClickOnTheDropdownButton(String dropdown, String button) {
        website.getHomePage().clickLinkOnSubmenu(dropdown, button);
    }
}
