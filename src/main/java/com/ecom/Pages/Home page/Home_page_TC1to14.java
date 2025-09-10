package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home_page_TC1to14 {
    WebDriver driver;
    WebDriverWait wait;

    public Home_page_TC1to14(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Header & Navigation
    By logoText = By.xpath("//div[@class='logo pull-left']/a"); // "Automation Engineer"
    By productsBtn = By.xpath("//a[@href='/products']");
    By cartBtn = By.xpath("//a[@href='/view_cart']");
    By signupLoginBtn = By.xpath("//a[contains(text(),'Signup / Login')]");
    By videoTutorialsBtn = By.xpath("//a[contains(text(),'Video Tutorials')]");
    By contactUsBtn = By.xpath("//a[@href='/contact_us']");
    By testCasesBtn = By.xpath("//a[@href='/test_cases']");
    By apiTestingBtn = By.xpath("//a[@href='/api_list']");

    // Locators — Footer & Scroll
    By scrollToTopArrow = By.id("scrollUp");
    By subscriptionEmailBox = By.id("susbscribe_email"); // Typo in site
    By subscriptionBtn = By.id("subscribe");

  
    // Element Presence Checks
 

    public boolean isLogoPresent() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(logoText)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isProductsButtonPresent() {
        return isElementVisible(productsBtn);
    }

    public boolean isCartButtonPresent() {
        return isElementVisible(cartBtn);
    }

    public boolean isSignupLoginButtonPresent() {
        return isElementVisible(signupLoginBtn);
    }

    public boolean isVideoTutorialsButtonPresent() {
        return isElementVisible(videoTutorialsBtn);
    }

    public boolean isContactUsButtonPresent() {
        return isElementVisible(contactUsBtn);
    }

    public boolean isTestCasesButtonPresent() {
        return isElementVisible(testCasesBtn);
    }

    public boolean isApiTestingButtonPresent() {
        return isElementVisible(apiTestingBtn);
    }

    public boolean isSubscriptionButtonPresent() {
        return isElementVisible(subscriptionBtn);
    }

    public boolean isScrollToTopArrowPresent() {
        return isElementVisible(scrollToTopArrow);
    }


    public void clickSignupLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(signupLoginBtn)).click();
    }

    public void clickScrollToTopArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollToTopArrow)).click();
    }

    public void enterSubscriptionEmail(String email) {
        WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmailBox));
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void clickSubscribeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionBtn)).click();
    }


    public void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public boolean isScrollbarWorking() {
        Long scrollHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        return scrollHeight > 0;
    }
    // Utility

    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}
