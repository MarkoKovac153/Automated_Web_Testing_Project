package com.sparta.testing.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private final WebDriver webDriver;
    private final HomePage homePage;
    private final InventoryPage inventoryPage;
    private final SearchPage searchPage;
    private final ProductsPage productsPage;

    public Website(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
        searchPage = new SearchPage(webDriver);
        productsPage = new ProductsPage(webDriver);
    }

    public SearchPage getSearchPage() {
        return searchPage;
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

    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }
}