package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailsPage {
    private final WebDriver webDriver;
    private final By colourOption = By.cssSelector("[id*='option-label-color-93-item']");
    private final By sizeOption = By.cssSelector("[id*='option-label-size-143-item']");
    private final By quantityOption = new By.ById("qty");
    private final By addToCartButton = new By.ById("product-addtocart-button");
    private final By messageBox = By.cssSelector(".message-success > div");
    private final By cartCounter = new By.ByClassName("counter-number");

    public ProductDetailsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private List<WebElement> getColourOptions(){
        return webDriver.findElements(colourOption);
    }

    public void clickColourOption(int indexOfColour){
        List<WebElement> colourOptions = getColourOptions();
        colourOptions.get(indexOfColour).click();
    }

    private List<WebElement> getSizeOptions(){
        return webDriver.findElements(sizeOption);
    }

    public void clickSizeOption(int indexOfSize){
        List<WebElement> sizeOptions = getSizeOptions();
        sizeOptions.get(indexOfSize).click();
    }

    private WebElement getQuantityOption(){
        return webDriver.findElement(quantityOption);
    }

    public void enterQuantity(int quantity){
        getQuantityOption().clear();
        getQuantityOption().sendKeys(Integer.toString(quantity));
    }

    private WebElement getAddToCartButton(){
        return webDriver.findElement(addToCartButton);
    }

    public void clickAddToCartButton(){
        getAddToCartButton().click();
    }

    private WebElement getMessageBox(){
        return webDriver.findElement(messageBox);
    }

    public String getMessageBoxText(){
        return getMessageBox().getText();
    }

    public int getCartCounter() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartCounter));

        String cartCountText = webDriver.findElement(cartCounter).getText();
        if(cartCountText.isEmpty()){
            return 0;
        }
        return Integer.parseInt(cartCountText);
    }
}
