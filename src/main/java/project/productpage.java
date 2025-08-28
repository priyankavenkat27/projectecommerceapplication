package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class productpage {
    WebDriver driver;

    public productpage(WebDriver driver) {
        this.driver = driver;
    }

    By productsLink = By.xpath("//a[@href='/products']");
    By searchInput = By.id("search_product");
    By searchButton = By.id("submit_search");
    By resultsSection = By.xpath("//div[@class='features_items']");

    public void goToProductsPage() {
        driver.findElement(productsLink).click();
    }

    public boolean searchInputIsDisplayed() {
        return driver.findElement(searchInput).isDisplayed();
    }

    public boolean searchButtonIsDisplayed() {
        return driver.findElement(searchButton).isDisplayed();
    }

    public void enterProduct(String productName) {
        driver.findElement(searchInput).sendKeys(productName);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public boolean resultsAreVisible() {
        return driver.findElement(resultsSection).isDisplayed();
    }
}
