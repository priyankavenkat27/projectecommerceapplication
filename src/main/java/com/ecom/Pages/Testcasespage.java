package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Testcasespage {
    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Locators
    private By testCasesLink = By.partialLinkText("Test Cases");
    private By testCasesHeading = By.xpath("//b[contains(text(),'Test Cases')]");

    // 🔹 Constructor
    public Testcasespage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void openTestCasesPage() {
        wait.until(ExpectedConditions.elementToBeClickable(testCasesLink)).click();
    }

    public boolean isTestCasesPageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(testCasesHeading)).isDisplayed();
    }
}
