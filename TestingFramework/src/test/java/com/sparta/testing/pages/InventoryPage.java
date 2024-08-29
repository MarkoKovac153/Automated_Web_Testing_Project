package com.sparta.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class InventoryPage {
    private final WebDriver driver;
    private final By inventoryItem = new By.ByClassName("inventory_item");
    private final By productName = new By.ByClassName("inventory_item_name");
    private final By cartLabel = new By.ByClassName("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public String addRandomItemToCart(){
        Random random = new Random();
        int randomNumber = random.nextInt(6);

        List<WebElement> items = driver.findElements(inventoryItem);
        WebElement item = items.get(randomNumber);
        item.findElement(By.tagName("button")).click();

        return item.findElement(productName).getText();
    }

    public String addItemToCart(int itemId){
        List<WebElement> items = driver.findElements(inventoryItem);
        WebElement item = items.get(itemId);
        item.findElement(By.tagName("button")).click();

        return item.findElement(productName).getText();
    }

    public int getInventoryItemCount(){
        return driver.findElements(inventoryItem).size();
    }

    public int getCartItemCount(){
        boolean labelExists = !driver.findElements(cartLabel).isEmpty();
        return labelExists ? Integer.parseInt(driver.findElement(cartLabel).getText()) : 0;
    }
}
