package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private final WebDriver webDriver;
    private final By example = new By.ById("example");

    public ProductsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
