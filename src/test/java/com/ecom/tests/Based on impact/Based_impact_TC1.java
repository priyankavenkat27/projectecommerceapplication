package com.automationexercise.tests;

import org.testng.annotations.Test;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Based_impact_TC1 {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.automationexercise.com";

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void verifyNavigationLinks() {
        driver.get(baseUrl);

        checkNavigation("//a[@href='/products']", "All Products");
        checkNavigation("//a[@href='/login']", "Login to your account");
        checkNavigation("//a[@href='/contact_us']", "Get In Touch");
        checkNavigation("//a[@href='/view_cart']", "Shopping Cart");
        checkNavigation("//a[@href='/test_cases']", "Test Cases");
        checkNavigation("//a[@href='/api_list']", "APIs List for practice");
        checkNavigation("//a[@href='/video_tutorials']", "Video Tutorials");
    }

    public void checkNavigation(String xpath, String expectedText) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'" + expectedText + "')]"))).isDisplayed();
            System.out.println("✅ Navigation to [" + expectedText + "] successful.");
        } catch (Exception e) {
            System.out.println("❌ Navigation to [" + expectedText + "] failed: " + e.getMessage());
        }
        driver.navigate().back();
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
