package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private final WebDriver driver;
    private final By firstnameField = new By.ById("firstname");
    private final By lastnameField = new By.ById("lastname");
    private final By emailField = new By.ById("email_address");
    private final By passwordField = new By.ById("password");
    private final By password_confirmationField = new By.ById("password_confirmation");
    private final By registerButton = new By.ByClassName("action submit primary");
    private final By firstnameErrorField = new By.ById("firstname-error");
    private final By lastnameErrorField = new By.ById("lastname-error");
    private final By emailErrorField = new By.ById("email_address-error");
    private final By passwordStrengthField = new By.ById("password-strength-meter");
    private final By passwordErrorField = new By.ById("password-error");
    private final By password_confirmationErrorField = new By.ById("password_confirmation");

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String password_confirmation;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstname) {
        this.firstname = firstname;
    }
    public void enterLastName(String lastname) {
        this.lastname = lastname;
    }
    public void enterEmail(String email) {
        this.email = email;
    }
    public void enterPassword(String password) {
        this.password = password;
    }
    public void enterPasswordConfirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
    public void registerAccount(String firstname, String lastname, String email, String password, String password_confirmation) {
        driver.findElement(firstnameField).sendKeys(firstname);
        driver.findElement(lastnameField).sendKeys(lastname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(password_confirmationField).sendKeys(password_confirmation);
        driver.findElement(registerButton).click();
    }
    public void registerAccount() {
        driver.findElement(firstnameField).sendKeys(firstname);
        driver.findElement(lastnameField).sendKeys(lastname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(password_confirmationField).sendKeys(password_confirmation);
    }
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String[] getErrors() {
        return new String[] {
                firstnameErrorMessage(),
                lastnameErrorMessage(),
                emailErrorMessage(),
                passwordErrorMessage(),
                passwordStrengthErrorMessage(),
                passwordConfirmationErrorMessage()
        };
    }
    public String firstnameErrorMessage() {
        return driver.findElement(firstnameErrorField).getText();
    }
    public String lastnameErrorMessage() {
        return driver.findElement(lastnameErrorField).getText();
    }
    public String emailErrorMessage() {
        return driver.findElement(emailErrorField).getText();
    }
    public String passwordErrorMessage() {
        return driver.findElement(passwordErrorField).getText();
    }
    public String passwordStrengthErrorMessage() {
        return driver.findElement(passwordStrengthField).getText();
    }
    public String passwordConfirmationErrorMessage() {
        return driver.findElement(password_confirmationErrorField).getText();
    }
}
