package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home_page_TC15to28 {
    WebDriver driver;
    WebDriverWait wait;

    public Home_page_TC15to28(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Subscription
    By subscriptionEmailBox = By.id("susbscribe_email"); // Typo in site
    By subscriptionBtn = By.id("subscribe");
    By subscriptionSuccessMsg = By.xpath("//div[contains(text(),'You have been successfully subscribed!')]");

    // Locators — Product & Sections
    By viewProductBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");
    By brandsSection = By.xpath("//div[@class='brands_products']");
    By categorySection = By.xpath("//div[@class='left-sidebar']");
    By featuredItemsSection = By.xpath("//h2[contains(text(),'Features Items')]");
    By recommendedItemsSection = By.xpath("//h2[contains(text(),'recommended items')]");

    // Locators — Carousel
    By carouselLeftArrow = By.className("left");
    By carouselRightArrow = By.className("right");
    By carouselDot = By.cssSelector(".carousel-indicators li");

    // Locators — Google Ads
    By googleAdSection = By.id("google_ads_iframe");
    By googleAdInfoIcon = By.cssSelector("img[alt='AdChoices']");

 

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

    public boolean isSubscriptionButtonPresent() {
        return isElementVisible(subscriptionBtn);
    }



    public boolean isViewProductButtonPresent() {
        return isElementVisible(viewProductBtn);
    }

    public boolean isBrandsSectionPresent() {
        return isElementVisible(brandsSection);
    }

    public boolean isCategorySectionPresent() {
        return isElementVisible(categorySection);
    }

    public boolean isFeaturedItemsSectionPresent() {
        return isElementVisible(featuredItemsSection);
    }

    public boolean isRecommendedItemsSectionPresent() {
        return isElementVisible(recommendedItemsSection);
    }

 

    public boolean isCarouselLeftArrowPresent() {
        return isElementVisible(carouselLeftArrow);
    }

    public boolean isCarouselRightArrowPresent() {
        return isElementVisible(carouselRightArrow);
    }

    public boolean isCarouselDotPresent() {
        return isElementVisible(carouselDot);
    }

    public void clickCarouselLeftArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(carouselLeftArrow)).click();
    }

    public void clickCarouselRightArrow() {
        wait.until(ExpectedConditions.elementToBeClickable(carouselRightArrow)).click();
    }

    public void clickCarouselDot() {
        wait.until(ExpectedConditions.elementToBeClickable(carouselDot)).click();
    }



    public boolean isGoogleAdSectionPresent() {
        try {
            return driver.findElement(googleAdSection).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isGoogleAdInfoIconPresent() {
        return isElementVisible(googleAdInfoIcon);
    }

    private boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // Optional scroll helpers
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollByPixels(int pixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")");
    }
}
