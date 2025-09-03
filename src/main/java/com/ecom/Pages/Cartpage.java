package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Cartpage {
    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Locators
    private By cartLink = By.xpath("//a[contains(text(),'Cart')]");
    private By cartItems = By.cssSelector("table.cart tbody tr");
    private By proceedToCheckoutBtn = By.xpath("//a[contains(text(),'Proceed To Checkout')]");
    private By removeItemBtn = By.xpath("//a[@class='cart_quantity_delete']");
    private By emptyCartMsg = By.xpath("//b[contains(text(),'Cart is empty')]");

    // 🔹 Constructor
    public Cartpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void clickCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
    }

    public int getCartItemCount() {
        List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
        return items.size();
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(removeItemBtn)).click();
    }

    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }

    public boolean isCartEmpty() {
        return driver.findElements(emptyCartMsg).size() > 0;
    }
}
