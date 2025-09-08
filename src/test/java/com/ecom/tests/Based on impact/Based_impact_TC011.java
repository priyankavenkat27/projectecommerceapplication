package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import com.automationexercise.utilities.ScreenshotUtilities;
import com.automationexercise.utilities.ExtentManager;

import com.aventstack.extentreports.*;

import java.io.File;
import java.time.Duration;

public class Based_impact_TC011 {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        new File(System.getProperty("user.dir") + "/screenshots").mkdirs();

        // ✅ Initialize Extent Report
        extent = ExtentManager.createInstance("SubscriptionReportforimpact.html");
    }

    @Test
    public void verifySubscriptionIconOnHomePage() {
        test = extent.createTest("Verify Subscription Icon on Home Page");

        try {
            driver.get("https://automationexercise.com");
            test.info("Opened automationexercise.com");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));
            test.info("Homepage loaded");

            WebElement subscriptionHeader = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='single-widget']/h2[text()='Subscription']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
            Thread.sleep(500);
            test.info("Scrolled to Subscription section");

            WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Your email address']")
            ));
            emailField.clear();
            emailField.sendKeys("keerthivs126@gmail.com");
            test.info("Entered email");

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailField);
            Thread.sleep(500);
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "AfterEmailEntered"));

            WebElement subscribeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='subscribe']")));
            subscribeBtn.click();
            test.info("Clicked Subscribe");

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", successMsg);
            Thread.sleep(500);
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "AfterSubscriptionSuccess"));

            Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message not displayed");
            test.pass("✅ Subscription success message displayed");
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "AfterSubscription"));

            System.out.println("Subscription icon is working on home page.");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, "Exception_Subscription");
            test.fail("❌ Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush(); // ✅ Write report to file
        }
    }
}





