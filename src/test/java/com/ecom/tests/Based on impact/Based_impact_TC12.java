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

public class Based_impact_TC12 {
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
        extent = ExtentManager.createInstance("ReviewSubmitReportforimpact.html");
    }

    @Test
    public void verifyReviewSubmitButtonWorks() {
        test = extent.createTest("Verify Review Submit Button");

        try {
            driver.get("https://automationexercise.com");
            test.info("Opened automationexercise.com");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));
            test.info("Homepage loaded");

            WebElement productsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=' Products']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsLink);
            wait.until(ExpectedConditions.elementToBeClickable(productsLink)).click();
            test.info("Clicked on Products");

            WebElement viewProductBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[text()='View Product'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProductBtn);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewProductBtn);
            test.info("Clicked on first View Product");

            WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nameField);
            Thread.sleep(500);
            test.info("Scrolled to review section");

            nameField.sendKeys("keerthana");
            driver.findElement(By.id("email")).sendKeys("keerthivs126@gmail.com");
            driver.findElement(By.id("review")).sendKeys("good product");
            test.info("Filled review form");

            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "FilledForm_ReviewSection"));

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("button-review")));
            Assert.assertTrue(submitBtn.isDisplayed(), "Submit button is not visible");
            submitBtn.click();
            test.info("Clicked Submit");

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Thank you for your review.']")
            ));
            Assert.assertTrue(successMsg.isDisplayed(), "Review success message not displayed");
            test.pass("✅ Review submitted successfully");

            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "AfterSubmit_ReviewSuccess"));

            System.out.println("Review submitted successfully.");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, "Exception_ReviewSubmit");
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






