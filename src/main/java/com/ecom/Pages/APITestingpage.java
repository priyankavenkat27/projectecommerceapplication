package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class APITestingpage {
    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Locators
    private By apiTestingLink = By.linkText("API Testing");
    private By apiListHeading = By.xpath("//h2[contains(text(),'APIs List')]");
    private By productsApiLink = By.linkText("/api/productsList");

    // 🔹 Constructor
    public APITestingpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void openApiTestingPage() {
        wait.until(ExpectedConditions.elementToBeClickable(apiTestingLink)).click();
    }

    public boolean isApiListDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(apiListHeading)).isDisplayed();
    }

    public void clickProductsApi() {
        wait.until(ExpectedConditions.elementToBeClickable(productsApiLink)).click();
    }
}
