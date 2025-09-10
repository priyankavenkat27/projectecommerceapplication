package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Loginpage_TC18to33 {
    WebDriver driver;
    WebDriverWait wait;

    public Loginpage_TC18to33(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Scroll
    By scrollToTopArrow = By.id("scrollUp");

    // Subscription
    By subscriptionEmailBox = By.id("susbscribe_email"); // Typo in site
    By subscriptionBtn = By.id("subscribe");
    By subscriptionSuccessMsg = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    // Navigation Icons
    By productsBtn = By.xpath("//a[@href='/products']");
    By cartBtn = By.xpath("//a[@href='/view_cart']");
    By videoTutorialsBtn = By.xpath("//a[contains(text(),'Video Tutorials')]");
    By contactUsBtn = By.xpath("//a[@href='/contact_us']");
    By testCasesBtn = By.xpath("//a[@href='/test_cases']");
    By apiTestingBtn = By.xpath("//a[@href='/api_list']");

    // Signup Form
    By signupNameInput = By.xpath("//input[@data-qa='signup-name']");
    By signupEmailInput = By.xpath("//input[@data-qa='signup-email']");
    By signupSubmitBtn = By.xpath("//button[@data-qa='signup-button']");
    By signupErrorMsg = By.xpath("//p[contains(text(),'Email Address already exist!')]");

    // Static Texts
    By newUserSignupText = By.xpath("//h2[contains(text(),'New User Signup!')]");
    By loginToAccountText = By.xpath("//h2[contains(text(),'Login to your account')]");
    By orText = By.xpath("//div[@class='signup-form']//h2/following-sibling::form//div[contains(text(),'OR')]");

    // Scroll Actions
 

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickScrollToTopArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollToTopArrow)).click();
    }

    public boolean isScrollToTopArrowVisible() {
        return isElementVisible(scrollToTopArrow);
    }

  

    public void enterSubscriptionEmail(String email) {
        WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmailBox));
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void clickSubscribeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionBtn)).click();
    }

    public boolean isSubscriptionSuccessMessageVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionSuccessMsg)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSubscriptionButtonVisible() {
        return isElementVisible(subscriptionBtn);
    }



    public boolean isProductsButtonVisible() {
        return isElementVisible(productsBtn);
    }

    public boolean isCartButtonVisible() {
        return isElementVisible(cartBtn);
    }

    public boolean isVideoTutorialsButtonVisible() {
        return isElementVisible(videoTutorialsBtn);
    }

    public boolean isContactUsButtonVisible() {
        return isElementVisible(contactUsBtn);
    }

    public boolean isTestCasesButtonVisible() {
        return isElementVisible(testCasesBtn);
    }

    public boolean isApiTestingButtonVisible() {
        return isElementVisible(apiTestingBtn);
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

   

    public boolean isNewUserSignupTextVisible() {
        return isElementVisible(newUserSignupText);
    }

    public boolean isLoginToAccountTextVisible() {
        return isElementVisible(loginToAccountText);
    }

    public boolean isOrTextVisible() {
        return isElementVisible(orText);
    }



    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}

