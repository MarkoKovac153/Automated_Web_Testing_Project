package com.sparta.testing.stepdefs;

import com.sparta.testing.browser_annotation.Browser;
import com.sparta.testing.browser_annotation.BrowserType;
import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.containsString;

//@Browser(BrowserType.EDGE)
//@Browser(BrowserType.CHROME)
//@Browser(BrowserType.FIREFOX)
public class NavbarStepDefs {
    private Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @After
    public void afterEach() {
        TestSetup.stopService();
    }

    @Before
    public void beforeEach() {
        TestSetup.startService(this.getClass());
        TestSetup.createWebDriver();
    }

    @Given("I am on any non-checkout page")
    public void iAmOnAnyNonCheckoutPage() {
        website = TestSetup.getWebsite(BASE_URL);
    }

    @When("I click the what's new button")
    public void iClickTheWhatSNewButton() {
        website.getHomePage().clickWhatsNewButtonOnNavbar();
    }

    @Then("I should land on the what's new page")
    public void iShouldLandOnTheWhatSNewPage() {
        MatcherAssert.assertThat(website.getCurrentUrl(), containsString("/what-is-new.html"));
    }
}
