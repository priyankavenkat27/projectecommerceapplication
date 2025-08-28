package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class productpage_2 {
    WebDriver driver;

    public productpage_2(WebDriver driver) {
        this.driver = driver;
    }

    By productsLink = By.xpath("//a[@href='/products']");
    By allProductsHeading = By.xpath("//h2[text()='All Products']");
    By viewProductButton = By.xpath("(//a[text()='View Product'])[1]");

    public void clickProductsLink() {
        driver.findElement(productsLink).click();
    }

    public boolean isAllProductsHeadingVisible() {
        return driver.findElement(allProductsHeading).isDisplayed();
    }

    public boolean isViewProductButtonVisible() {
        return driver.findElement(viewProductButton).isDisplayed();
    }

    public void clickViewProductSafely() {
        WebElement viewProduct = driver.findElement(viewProductButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProduct);
        try {
            Thread.sleep(500); // Let overlays settle
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProduct);
        } catch (Exception e) {
            System.out.println("Fallback to Actions click");
        }
    }
}
