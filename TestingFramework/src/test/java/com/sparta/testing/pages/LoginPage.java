package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class LoginPage {

    private final WebDriver driver;
    private final By emailField = new By.ById("email");
    private final By passwordField = new By.ById("pass");
    private final By loginButton = new By.ById("send2");
    private final By emailErrorField = new By.ById("email-error");
    private final By passwordErrorField= new By.ById("pass-error");
    private final By messageField = new By.ByCssSelector("div.message-error.error.message");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLoginDetails(String username, String password) {
        enterEmail(username);
        enterPassword(password);
        clickLoginButton();
    }
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String[] getErrors() {
        // Turns off the wait when not finding an element
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
        String[] output = {
                getMessage(),
                emailErrorMessage(),
                passwordErrorMessage(),
        };
        // Turns the wait back on
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return output;
    }
    public String getMessage(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(driver -> driver.findElement(messageField).isDisplayed());
            return driver.findElement(messageField).getText();
        } catch (TimeoutException | NoSuchElementException ignored) {
            return "";
        }
    }
    public String emailErrorMessage() {
        if (driver.findElements(emailErrorField).isEmpty()) {
            return "";
        } else{
            return driver.findElement(emailErrorField).getText();
        }
    }
    public String passwordErrorMessage() {
        if (driver.findElements(passwordErrorField).isEmpty()) {
            return "";
        } else {
            return driver.findElement(passwordErrorField).getText();
        }
    }


}
