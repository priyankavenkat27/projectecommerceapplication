// Cartpage.java
package com.ecom.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Cartpage {
    WebDriver driver;
    WebDriverWait wait;

    public Cartpage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By homeBreadcrumb = By.cssSelector("a[href='/']");
    By cartBreadcrumb = By.cssSelector("li.active");
    By hereLink = By.linkText("here");
    By deleteIcon = By.cssSelector("a.cart_quantity_delete");
    By proceedToCheckoutBtn = By.cssSelector("a.btn.btn-default.check_out");
    By subscriptionEmailBox = By.id("susbscribe_email"); 
    By subscriptionBtn = By.id("subscribe");
    By commentsTextBox = By.name("message");
    By scrollToTopArrow = By.id("scrollUp");

    // Actions
    public void clickHomeBreadcrumb() {
        wait.until(ExpectedConditions.elementToBeClickable(homeBreadcrumb)).click();
    }

    public void clickCartBreadcrumb() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBreadcrumb)).click();
    }

    public void clickHereLink() {
        wait.until(ExpectedConditions.elementToBeClickable(hereLink)).click();
    }
    
 // Getter for proceed to checkout button
    public WebElement getProceedToCheckoutBtn() {
        return wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
    }
    
 // In Cartpage.java
    public WebElement getHereLinkElement() {
         return driver.findElement(By.xpath("//a[@href='/products']"));

    }


    public void clickDeleteIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
    }

    public void clickProceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn)).click();
    }

    public void enterSubscriptionEmail(String email) {
        WebElement emailBox = wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionEmailBox));
        emailBox.clear();
        emailBox.sendKeys(email);
    }

    public void clickSubscribe() {
        wait.until(ExpectedConditions.elementToBeClickable(subscriptionBtn)).click();
    }

    public void enterComments(String comments) {
        WebElement commentsBox = wait.until(ExpectedConditions.visibilityOfElementLocated(commentsTextBox));
        commentsBox.clear();
        commentsBox.sendKeys(comments);
    }

    public String getEnteredComments() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(commentsTextBox)).getAttribute("value");
    }

    public void clickScrollToTopArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollToTopArrow)).click();
    }

    public boolean isDeleteIconPresent() {
        try {
            return driver.findElement(deleteIcon).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isHereLinkPresent() {
        try {
            return driver.findElement(hereLink).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSubscriptionIconPresent() {
        try {
            return driver.findElement(subscriptionBtn).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Extra scroll methods (optional, if tests need them)
    public void scrollPageDown() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)");
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
