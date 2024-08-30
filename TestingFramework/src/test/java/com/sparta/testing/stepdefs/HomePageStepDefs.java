package com.sparta.testing.stepdefs;

import com.sparta.testing.pages.Website;
import io.cucumber.java.en.Given;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

public class HomePageStepDefs {

    protected Website website;
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    @Before
    public void setup() throws IOException {
        TestSetup.startServiceWithDefaultBrowser();
        TestSetup.createWebDriver();
    }
    @After
    public void afterEach(){
        TestSetup.stopService();
    }


}
