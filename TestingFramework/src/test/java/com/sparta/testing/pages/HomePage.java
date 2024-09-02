package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {
    private final WebDriver driver;
    private final By navbar = new By.ByClassName("navigation");
    private final By listItem = new By.ByTagName("li");
    private WebElement dropdownElement;

    private final WebDriverWait wait;
    private String username;
    private String password;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private List<WebElement> getNavbarElements() {
        WebElement navbarElement = wait.until(ExpectedConditions.visibilityOfElementLocated(navbar));
        return navbarElement.findElements(listItem);
    }

    public void waitForPageToLoad() {
        wait.until(driver -> {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return "complete".equals(js.executeScript("return document.readyState").toString());
        });
        wait.until(ExpectedConditions.visibilityOfElementLocated(navbar));
    }

    public void clickButtonOnNavbar(String button) {
        List<WebElement> navbarLinks = getNavbarElements();
        clickLink(navbarLinks, button);
    }

    public void hoverButtonOnNavbar(String button) {
        waitForPageToLoad();
        List<WebElement> navbarLinks = getNavbarElements();

        for (WebElement navbarLink : navbarLinks) {
            if (navbarLink.getText().contains(button)) {
                wait.until(ExpectedConditions.visibilityOf(navbarLink));
                String script = "var event = new MouseEvent('mouseover', { bubbles: true }); arguments[0].dispatchEvent(event);";
                ((JavascriptExecutor) driver).executeScript(script, navbarLink);

                By submenuXPath = By.xpath("//a/span[text()='" + button + "']/ancestor::li//ul[contains(@class, 'submenu')]");

                try {
                    dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(submenuXPath));
                    System.out.println("Submenu is visible.");
                } catch (TimeoutException e) {
                    System.err.println("Submenu not visible for: " + button + " - " + e.getMessage());
                }
                return;
            }
        }
        System.err.println("Button not found: " + button);
    }

    public boolean isDropDownVisible(String button) {
        By submenuXPath = By.xpath("//a/span[text()='" + button + "']/ancestor::li//ul[contains(@class, 'submenu')]");

        try {
            dropdownElement = driver.findElement(submenuXPath);
            return dropdownElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Dropdown not visible for: " + button + " - " + e.getMessage());
            return false;
        }
    }

    private static void clickLink(List<WebElement> navbarLinks, String linkText) {
        for (WebElement navbarLink : navbarLinks) {
            String text = navbarLink.getText();
            if (text.contains(linkText)) {
                navbarLink.click();
                return;
            }
        }
        System.out.println("Link with text '" + linkText + "' not found.");
    }

    public void clickLinkOnDropdownMenu(String button) {
        List<WebElement> dropdownLinks = getDropdownElements();
        clickLink(dropdownLinks, button);
    }

    private List<WebElement> getDropdownElements() {
        return dropdownElement.findElements(listItem);
    }
}
