package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Loginpage_TC1to17 {
    WebDriver driver;
    WebDriverWait wait;

    public Loginpage_TC1to17(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Logo & Buttons
    By logoText = By.xpath("//div[@class='logo pull-left']/a"); // "Automation Engineer"
    By signupButton = By.xpath("//button[contains(text(),'Signup')]");
    By loginButton = By.xpath("//button[contains(text(),'Login')]");

    // Locators — Signup Form
    By signupNameInput = By.xpath("//input[@data-qa='signup-name']");
    By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    By signupSubmitBtn = By.xpath("//button[@data-qa='signup-button']");
    By signupErrorMsg = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    // Locators — Login Form
    By loginEmailInput = By.xpath("//input[@data-qa='login-email']");
    By loginPasswordInput = By.xpath("//input[@data-qa='login-password']");
    By loginSubmitBtn = By.xpath("//button[@data-qa='login-button']");
    By loginErrorMsg = By.xpath("//p[contains(text(),'Your email or password is incorrect!')]");

    // Scroll
    By scrollToTopArrow = By.id("scrollUp");


    public boolean isLogoPresent() {
        return isElementVisible(logoText);
    }

    public boolean isSignupButtonPresent() {
        return isElementVisible(signupButton);
    }

    public boolean isLoginButtonPresent() {
        return isElementVisible(loginButton);
    }

    public boolean isScrollToTopArrowPresent() {
        return isElementVisible(scrollToTopArrow);
    }



    public void enterSignupName(String name) {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(signupNameInput));
        nameField.clear();
        nameField.sendKeys(name);
    }

    public void enterSignupEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(signupEmailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickSignupSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(signupSubmitBtn)).click();
    }

    public boolean isSignupErrorMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(signupErrorMsg)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }


    public void enterLoginEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPasswordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitBtn)).click();
    }

    public boolean isLoginErrorMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginErrorMsg)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }



    public void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickScrollToTopArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollToTopArrow)).click();
    }

    public boolean isScrollbarWorking() {
        Long scrollHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        return scrollHeight > 0;
    }


    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
