package com.automationexercise.pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Test_case_page {
    WebDriver driver;
    WebDriverWait wait;

    public Test_case_page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Navigation & Page Identity
    By homePageIdentifier = By.xpath("//div[@class='logo pull-left']/a");
    By testCasesBtn = By.xpath("//a[@href='/test_cases']");
    By testCasesTitle = By.xpath("//h2[contains(text(),'Test Cases')]");

    // Locators — Test Case Content
    By testCaseHeadings = By.cssSelector(".panel-title a"); // Collapsible headings
    By testCaseDescriptions = By.cssSelector(".panel-body"); // Expanded content
    By feedbackSection = By.xpath("//h2[contains(text(),'Feedback For Us')]");
    By feedbackEmail = By.xpath("//a[contains(@href,'mailto')]");

    // Locators — Hyperlinks
    By testCaseLinks = By.cssSelector(".panel-body a");

    // Locators — Scroll
    By scrollToTopArrow = By.id("scrollUp");

    // Locators — Subscription
    By subscriptionEmailBox = By.id("susbscribe_email"); // Typo in site
    By subscriptionBtn = By.id("subscribe");
    By subscriptionSuccessMsg = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    // Locators — Popups
    By adPopup = By.id("ad_position_bottom"); // Assumed ID
    By suggestionPopup = By.id("suggestion_box"); // Assumed ID


    public boolean isHomePageVisible() {
        return isElementVisible(homePageIdentifier);
    }

    public boolean isTestCasesButtonVisible() {
        return isElementVisible(testCasesBtn);
    }

    public void clickTestCasesButton() {
        wait.until(ExpectedConditions.elementToBeClickable(testCasesBtn)).click();
    }

    public boolean isTestCasesTitleVisible() {
        return isElementVisible(testCasesTitle);
    }

 

    public boolean isTestCaseContentVisible() {
        List<WebElement> descriptions = driver.findElements(testCaseDescriptions);
        return !descriptions.isEmpty();
    }

    public void clickFirstTestCaseHeading() {
        List<WebElement> headings = driver.findElements(testCaseHeadings);
        if (!headings.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(headings.get(0))).click();
        }
    }

    public boolean isDetailedStepsVisible() {
        List<WebElement> descriptions = driver.findElements(testCaseDescriptions);
        for (WebElement desc : descriptions) {
            if (desc.isDisplayed()) return true;
        }
        return false;
    }

    public boolean isFeedbackSectionVisible() {
        return isElementVisible(feedbackSection);
    }

    public boolean isFeedbackEmailVisible() {
        return isElementVisible(feedbackEmail);
    }

    public boolean areTestCaseLinksWorking() {
        List<WebElement> links = driver.findElements(testCaseLinks);
        for (WebElement link : links) {
            if (!link.isDisplayed() || !link.isEnabled()) return false;
        }
        return true;
    }


    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void clickScrollToTopArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(scrollToTopArrow)).click();
    }

    public boolean isScrollToTopArrowVisible() {
        return isElementVisible(scrollToTopArrow);
    }

    public boolean isScrollbarWorking() {
        Long scrollHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        return scrollHeight > 0;
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

 

    public boolean isAdPopupVisible() {
        return isElementVisible(adPopup);
    }

    public boolean isSuggestionPopupVisible() {
        return isElementVisible(suggestionPopup);
    }


    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
}

