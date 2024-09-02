package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class HomePageStepDefs {

    protected Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @Before
    public void beforeEach() {
        TestSetup.startService(this.getClass());
        TestSetup.createWebDriver();
    }
    @AfterAll
    public static void afterAll() {
        TestSetup.stopService();
    }
    @After
    public void afterEach() {
        TestSetup.stopService();
    }
}
