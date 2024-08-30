package com.sparta.testing.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private final WebDriver webDriver;
    private final HomePage homePage;
    private final InventoryPage inventoryPage;
    private final SearchPage searchPage;
    private final RegistrationPage registrationPage;
    private final AccountPage accountPage;


    public Website(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        accountPage = new AccountPage(webDriver);
        searchPage = new SearchPage(webDriver);
    }

    public HomePage getHomePage(){
        return homePage;
    }
    public InventoryPage getInventoryPage(){
        return inventoryPage;
    }
    public SearchPage getSearchPage() {
        return searchPage;
    }
    public RegistrationPage getRegistrationPage(){
        return registrationPage;
    }
    public AccountPage getAccountPage(){
        return accountPage;
    }
    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }
    public String getPageTitle(){
        return webDriver.getTitle();
    }
}
