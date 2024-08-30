package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.en.Given;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class HomePageStepDefs {

    protected Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @Before
    public void beforeEach() {
        TestSetup.startService(this.getClass());
        TestSetup.createWebDriver();
    }

    @After
    public void afterEach() {
        TestSetup.stopService();
    }


}
