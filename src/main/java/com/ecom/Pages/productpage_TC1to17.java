package com.automationexercise.pages;



import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class productpage_TC1to17 {
    WebDriver driver;
    WebDriverWait wait;

    public productpage_TC1to17(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators — Home & Navigation
    By homeLogo = By.xpath("//div[@class='logo pull-left']/a");
    By productsBtn = By.xpath("//a[@href='/products']");
    By allProductsTitle = By.xpath("//h2[contains(text(),'All Products')]");

    // Locators — Product List
    By productCards = By.cssSelector(".product-image-wrapper");
    By firstViewProductBtn = By.xpath("(//a[contains(text(),'View Product')])[1]");

    // Locators — Product Detail Page
    By productName = By.xpath("//div[@class='product-information']/h2");
    By productCategory = By.xpath("//div[@class='product-information']/p[contains(text(),'Category')]");
    By productPrice = By.xpath("//div[@class='product-information']/span/span");
    By productAvailability = By.xpath("//div[@class='product-information']/p[contains(text(),'Availability')]");
    By productCondition = By.xpath("//div[@class='product-information']/p[contains(text(),'Condition')]");
    By productBrand = By.xpath("//div[@class='product-information']/p[contains(text(),'Brand')]");

    // Locators — Search
    By searchBox = By.id("search_product");
    By searchBtn = By.id("submit_search");
    By searchedProductsHeading = By.xpath("//h2[contains(text(),'Searched Products')]");



    public boolean isHomePageVisible() {
        return isElementVisible(homeLogo);
    }

    public boolean isProductsButtonVisible() {
        return isElementVisible(productsBtn);
    }

    public void clickProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(productsBtn)).click();
    }

    public boolean isAllProductsTitleVisible() {
        return isElementVisible(allProductsTitle);
    }



    public boolean isProductListVisible() {
        List<WebElement> products = driver.findElements(productCards);
        return !products.isEmpty();
    }

    public boolean isFirstViewProductButtonVisible() {
        return isElementVisible(firstViewProductBtn);
    }

    public void clickFirstViewProductButton() {
        wait.until(ExpectedConditions.elementToBeClickable(firstViewProductBtn)).click();
    }

    public boolean isProductNameVisible() {
        return isElementVisible(productName);
    }

    public boolean isProductCategoryVisible() {
        return isElementVisible(productCategory);
    }

    public boolean isProductPriceVisible() {
        return isElementVisible(productPrice);
    }

    public boolean isProductAvailabilityVisible() {
        return isElementVisible(productAvailability);
    }

    public boolean isProductConditionVisible() {
        return isElementVisible(productCondition);
    }

    public boolean isProductBrandVisible() {
        return isElementVisible(productBrand);
    }

  

    public void searchProduct(String productName) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
    }

    public boolean isSearchedProductsHeadingVisible() {
        return isElementVisible(searchedProductsHeading);
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

