package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final By navbar = new By.ByClassName("navigation");
    private final By list = new By.ByTagName("li");
    private final By passwordField = new By.ById("password");
    private final By loginButton = new By.ById("login-button");
    private final By errorBox = new By.ByClassName("error-message-container");

    private String username;
    private String password;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> getNavbarElements() {
        WebElement navbarElement = driver.findElement(navbar);
        return navbarElement.findElements(list);
    }

    public void clickWhatsNewButtonOnNavbar() {
        List<WebElement> navbarLinks = getNavbarElements();
        ClickLink(navbarLinks, "What's New");
    }

    private static void ClickLink(List<WebElement> navbarLinks, String linkText) {
        for (WebElement navbarLink : navbarLinks) {
            String text = navbarLink.getText();
            if (text.contains(linkText)) {
                navbarLink.click();
                return;
            }
        }
        System.out.println("Link with text '" + linkText + "' not found.");
    }
//    public void login(String username, String password) {
//        driver.findElement(usernameField).sendKeys(username);
//        driver.findElement(passwordField).sendKeys(password);
//        driver.findElement(loginButton).click();
//    }
//
//    public String waitForErrorMessage(){
//        Wait<WebDriver> webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(errorBox));
//        return driver.findElement(errorBox).findElement(By.tagName("h3")).getText();
//    }
//
//    public void enterUserName(String username) {
//        this.username = username;
//    }
//
//    public void enterPassword(String password) {
//        this.password = password;
//    }
//
//    public void clickLoginButton() {
//        driver.findElement(usernameField).sendKeys(username);
//        driver.findElement(passwordField).sendKeys(password);
//        driver.findElement(loginButton).click();
//    }
}
