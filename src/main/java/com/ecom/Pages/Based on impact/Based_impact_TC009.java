package com.automationexercise.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

public class Based_impact_TC009 {

    WebDriver driver;

    public Based_impact_TC009(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By contactUsLink = By.xpath("//a[@href='/contact_us']");
    By nameField = By.name("name");
    By emailField = By.name("email");
    By messageField = By.name("message");
    By submitButton = By.name("submit");

    // Actions
    public void clickContactUsLink() {
        driver.findElement(contactUsLink).click();
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterMessage(String message) {
        driver.findElement(messageField).sendKeys(message);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            WebElement successMsg = driver.findElement(By.xpath("//div[contains(text(),'Success! Your details have been submitted successfully.')]"));
            return successMsg.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}


