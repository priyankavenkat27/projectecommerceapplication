package project;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cartpage {
    WebDriver driver;
    WebDriverWait wait;

    public Cartpage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By productsLink = By.xpath("//a[@href='/products']");
    By allProductsHeading = By.xpath("//h2[text()='All Products']");
    By addToCartButton = By.xpath("(//a[text()='Add to cart'])[1]");
    By modalPopup = By.id("cartModal"); // Confirmation modal
    By continueButton = By.xpath("//button[text()='Continue Shopping']");

    public void goToProductsPage() {
        driver.findElement(productsLink).click();
    }

    public boolean isAllProductsHeadingVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(allProductsHeading)).isDisplayed();
    }

    public boolean isAddToCartButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton)).isDisplayed();
    }

    public void clickAddToCartSafely() {
        WebElement addBtn = driver.findElement(addToCartButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
        try {
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
        } catch (Exception e) {
            new Actions(driver).moveToElement(addBtn).click().perform();
        }
    }

    public boolean isConfirmationVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(modalPopup)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
