package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class End_end_testcase {
    WebDriver driver;
    WebDriverWait wait;

    public End_end_testcase(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Signup/Login
    By signupLoginLink = By.xpath("//a[contains(text(),'Signup / Login')]");
    By signupButton = By.xpath("//button[contains(text(),'Signup')]");
    By loginButton = By.xpath("//button[contains(text(),'Login')]");

    // Locators — Home
    By logoutButton = By.xpath("//a[contains(text(),'Logout')]");
    By subscriptionEmailBox = By.id("susbscribe_email"); // Typo in site
    By subscriptionBtn = By.id("subscribe");

    // Locators — Product Page
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");
    By viewProductBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    By addToCartBtn = By.xpath("(//a[contains(text(),'Add to cart')])[1]");
    By quantityInput = By.name("quantity");

    // Locators — Cart Page
    By deleteIcon = By.cssSelector("a.cart_quantity_delete");
    By checkoutBtn = By.cssSelector("a.btn.btn-default.check_out");
    By placeOrderBtn = By.xpath("//a[contains(text(),'Place Order')]");

    // Locators — Contact Us
    By contactName = By.name("name");
    By contactEmail = By.name("email");
    By contactSubject = By.name("subject");
    By contactMessage = By.name("message");
    By contactSubmitBtn = By.name("submit");

    // Actions — Signup/Login
    public void clickSignupLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink)).click();
    }

    public void clickSignupButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // Actions — Home
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void enterSubscriptionEmail(String email) {
        WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmailBox));
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void clickSubscribeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionBtn)).click();
    }

    public boolean isSubscriptionIconPresent() {
        try {
            return driver.findElement(subscriptionBtn).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Actions — Product Page
    public void searchProduct(String productName) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public void clickViewProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(viewProductBtn)).click();
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
    }

    public void updateProductQuantity(String qty) {
        WebElement qtyBox = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInput));
        qtyBox.clear();
        qtyBox.sendKeys(qty);
    }

    // Actions — Cart Page
    public void clickDeleteIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    public void clickPlaceOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
    }

    public boolean isDeleteIconPresent() {
        try {
            return driver.findElement(deleteIcon).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Actions — Contact Us
    public void fillContactForm(String name, String email, String subject, String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactName)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactEmail)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactSubject)).sendKeys(subject);
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactMessage)).sendKeys(message);
    }

    public void clickContactSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(contactSubmitBtn)).click();
    }

    // Scroll helpers
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
    }
}

