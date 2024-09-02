package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private final WebDriver driver;
    private final By searchBar = new By.ById("search");
    private final By searchIcon = By.cssSelector(".actions > .action");
    private final By productLink = By.cssSelector(".product-item-link");
    private final By noticeMessage = By.cssSelector(".notice > div");

    public SearchPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterInSearchBar(String searchTerm){
        WebElement search = driver.findElement(searchBar);
        search.click();
        search.sendKeys(searchTerm);
    }

    public void clickSearchIcon() {
        Wait<WebDriver> webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchIcon));
        driver.findElement(searchIcon).click();
    }

    public String getFirstProductText(){
        return driver.findElement(productLink).getText();
    }

    public List<String> getAllProductTexts(){
        try{
            return driver.findElements(productLink).stream()
                    .map(WebElement::getText)
                    .toList();
        } catch (StaleElementReferenceException e){
            return getAllProductTexts();
        }

    }

    public String getNoticeMessage(){
        return driver.findElement(noticeMessage).getText();
    }

}
