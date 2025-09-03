package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    // Locators
     By productsLink = By.partialLinkText("Products");
     By searchBox = By.id("search_product");
     By searchBtn = By.id("submit_search");
     By productName = By.xpath("//div[@class='productinfo text-center']/p");
     By firstAddToCart = By.xpath("(//a[text()='Add to cart'])[1]");
     By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void clickProductsLink() {
        driver.findElement(productsLink).click();
    }

    public void searchProduct(String product) {
        driver.findElement(searchBox).sendKeys(product);
        driver.findElement(searchBtn).click();
    }

    public String getFirstProductName() {
        return driver.findElement(productName).getText();
    }

    public void clickFirstAddToCart() {
        driver.findElement(firstAddToCart).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }

    // Getters for locators if needed in tests
    public By firstAddToCartLocator() {
        return firstAddToCart;
    }

    public By continueShoppingLocator() {
        return continueShoppingBtn;
    }

	public By productsLinkLocator() {
		// TODO Auto-generated method stub
		return productsLink ;
	}

	 
}
