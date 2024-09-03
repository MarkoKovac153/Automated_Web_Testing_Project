package com.sparta.testing.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private final WebDriver webDriver;
    private final HomePage homePage;
    private final InventoryPage inventoryPage;
    private final SearchPage searchPage;
    private final ProductsPage productsPage;
    private final RegistrationPage registrationPage;
    private final AccountPage accountPage;
    private final LoginPage loginPage;
    private final ProductDetailsPage productDetailsPage;

    public Website(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        accountPage = new AccountPage(webDriver);
        searchPage = new SearchPage(webDriver);
        productsPage = new ProductsPage(webDriver);
        loginPage = new LoginPage(webDriver);
        productDetailsPage = new ProductDetailsPage(webDriver);
    }

    public HomePage getHomePage(){
        return homePage;
    }
    public InventoryPage getInventoryPage(){
        return inventoryPage;
    }

    public ProductsPage getProductsPage(){
        return productsPage;
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
    public LoginPage getLoginPage(){
        return loginPage;
    }
    public ProductDetailsPage getProductDetailsPage() {
        return productDetailsPage;
    }
    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }
    public String getPageTitle(){
        return webDriver.getTitle();
    }


}
