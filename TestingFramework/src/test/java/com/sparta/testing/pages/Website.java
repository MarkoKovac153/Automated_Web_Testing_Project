package com.sparta.testing.pages;

import org.openqa.selenium.WebDriver;

public class Website {
    private final WebDriver webDriver;
    private final HomePage homePage;
    private final InventoryPage inventoryPage;

    public Website(WebDriver webDriver){
        this.webDriver = webDriver;
        homePage = new HomePage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
    }

    public HomePage getHomePage(){
        return homePage;
    }

    public InventoryPage getInventoryPage(){
        return inventoryPage;
    }

    public String getCurrentUrl(){
        return webDriver.getCurrentUrl();
    }

    public String getPageTitle(){
        return webDriver.getTitle();
    }
}