package com.ecom.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    // Locators
    By subscriptionEmail = By.id("susbscribe_email"); // update with correct id if different
    By subscribeBtn = By.id("subscribe"); // update with actual locator
    By successMsg = By.xpath("//div[contains(text(),'You have been successfully subscribed')]"); // example

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSubscriptionEmail(String email) {
        driver.findElement(subscriptionEmail).sendKeys(email);
    }

    public void clickSubscribe() {
        driver.findElement(subscribeBtn).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMsg).getText();
    }
}
