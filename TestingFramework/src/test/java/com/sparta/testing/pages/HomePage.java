package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By usernameField = new By.ById("user-name");
    private final By passwordField = new By.ById("password");
    private final By loginButton = new By.ById("login-button");
    private final By errorBox = new By.ByClassName("error-message-container");

    private String username;
    private String password;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String waitForErrorMessage(){
        Wait<WebDriver> webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(errorBox));
        return driver.findElement(errorBox).findElement(By.tagName("h3")).getText();
    }

    public void enterUserName(String username) {
        this.username = username;
    }

    public void enterPassword(String password) {
        this.password = password;
    }

    public void clickLoginButton() {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
