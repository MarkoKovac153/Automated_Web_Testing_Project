package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegistrationPage {

    private final WebDriver driver;
    private final By firstnameField = new By.ById("firstname");
    private final By lastnameField = new By.ById("lastname");
    private final By emailField = new By.ById("email_address");
    private final By passwordField = new By.ById("password");
    private final By password_confirmationField = new By.ById("password-confirmation");
    private final By registerButton = new By.ByCssSelector("button.action.submit.primary");
    private final By firstnameErrorField = new By.ById("firstname-error");
    private final By lastnameErrorField = new By.ById("lastname-error");
    private final By emailErrorField = new By.ById("email_address-error");
    private final By passwordStrengthField = new By.ById("password-strength-meter");
    private final By passwordErrorField = new By.ById("password-error");
    private final By password_confirmationErrorField = new By.ById("password-confirmation-error");

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String password_confirmation;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstname) {
        driver.findElement(this.firstnameField).sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        driver.findElement(this.lastnameField).sendKeys(lastname);
    }
    public void enterEmail(String email) {
        driver.findElement(this.emailField).sendKeys(email);
    }
    public void enterPassword(String password) {
        driver.findElement(this.passwordField).sendKeys(password);
    }
    public void enterPasswordConfirmation(String password_confirmation) {
        driver.findElement(this.password_confirmationField).sendKeys(password_confirmation);
    }
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }
    public void registerAccount(String firstname, String lastname, String email, String password, String password_confirmation) {
        driver.findElement(firstnameField).sendKeys(firstname);
        driver.findElement(lastnameField).sendKeys(lastname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(password_confirmationField).sendKeys(password_confirmation);
        driver.findElement(registerButton).click();
    }

    public String[] getErrors() {
        // Turns off the wait when not finding an element
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        String[] output = {
                firstnameErrorMessage(),
                lastnameErrorMessage(),
                emailErrorMessage(),
                passwordErrorMessage(),
                passwordStrengthErrorMessage(),
                passwordConfirmationErrorMessage()
        };
        // Turns the wait back on
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        return output;
    }
    public String firstnameErrorMessage() {
        if (driver.findElements(firstnameErrorField).isEmpty()) {
            return "";
        } else {
            return driver.findElement(firstnameErrorField).getText();
        }

    }
    public String lastnameErrorMessage() {
        if (driver.findElements(lastnameErrorField).isEmpty()) {
            return "";
        } else {
        return driver.findElement(lastnameErrorField).getText();
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
    public String passwordStrengthErrorMessage() {
        if (driver.findElements(passwordStrengthField).isEmpty()) {
            return "";
        } else {
            return driver.findElement(passwordStrengthField).getText();
        }
    }
    public String passwordConfirmationErrorMessage() {
        if (driver.findElements(password_confirmationErrorField).isEmpty()) {
            return "";
        } else {
            return driver.findElement(password_confirmationErrorField).getText();
        }
    }
}
