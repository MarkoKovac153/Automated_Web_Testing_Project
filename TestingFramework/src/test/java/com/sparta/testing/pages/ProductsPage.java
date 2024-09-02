package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private final WebDriver webDriver;
    private final By product = By.cssSelector(".product-image-photo");

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private List<WebElement> getAllProducts(){
        return webDriver.findElements(product);
    }

    public void clickNthProduct(int productIndex){
        WebElement productToClick = getAllProducts().get(productIndex);
        Wait<WebDriver> webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productToClick));
        productToClick.click();
    }
}
