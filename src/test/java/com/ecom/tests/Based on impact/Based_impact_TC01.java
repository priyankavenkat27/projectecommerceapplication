package com.automationexercise.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Based_impact_TC01 {

    public static void main(String[] args) {
        String browser = "chrome"; // Change to "edge" or "firefox" as needed
        String url = "https://www.automationexercise.com";
        String name = "kaviya";
        String email = "kaviyanjali17@gmail.com";

        WebDriver driver = setupBrowser(browser);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1 & 2: Open the application
            driver.get(url);
            driver.manage().window().maximize();

            // Step 3: Click on Signup/Login link
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Signup / Login')]"))).click();

            // Step 4: Enter name and email
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-qa='signup-name']"))).sendKeys(name);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);

            // Step 5: Click Signup button
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

            // ✅ Validation: Check if navigated to 'Enter Account Information' page
            boolean isCreateAccountVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//b[text()='Enter Account Information']"))).isDisplayed();

            if (isCreateAccountVisible) {
                System.out.println("✅ Test Passed: User is able to signup and navigate to Create Account page.");
            } else {
                System.out.println("❌ Test Failed: Signup did not navigate to Create Account page.");
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // 🔄 Browser setup method
    public static WebDriver setupBrowser(String browser) {
        WebDriver driver = null;
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
        return driver;
    }
}
