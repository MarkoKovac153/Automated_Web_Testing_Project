package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private final WebDriver driver;
    private final By messageField = new By.ByCssSelector("div.message-success.success.message");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return driver.findElement(messageField).getText();
    }

}
