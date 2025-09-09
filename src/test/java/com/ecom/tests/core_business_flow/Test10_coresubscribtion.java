package com.autoex.tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

public class Test10_coresubscribtion {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Report path for Subscription
        String reportPath = System.getProperty("user.dir") + "/reports/SubscriptionReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void verifySubscriptionIcon() {
        test = extent.createTest("Subscription Test");

        try {
            // Scroll down
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            test.info("Scrolled down to footer");

            WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("footer")));
            Assert.assertTrue(footer.isDisplayed(), "Footer not displayed!");
            test.pass("Footer displayed");

            WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("susbscribe_email")));
            emailBox.sendKeys("testuser@gmail.com");
            test.info("Entered email");

            WebElement subscribeBtn = driver.findElement(By.id("subscribe"));
            subscribeBtn.click();
            test.info("Clicked Subscribe");

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'You have been successfully subscribed!')]")));
            Assert.assertTrue(successMsg.isDisplayed(), "Subscription success message not found!");
            test.pass("Subscription success message verified");

            String screenshotPath = takeScreenshot("SubscriptionTest");
            test.addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            try {
                String screenshotPath = takeScreenshot("SubscriptionTest_Failed");
                test.fail("Subscription Test Failed: " + e.getMessage())
                        .addScreenCaptureFromPath(screenshotPath);
            } catch (IOException ioException) {
                test.fail("Could not attach screenshot: " + ioException.getMessage());
            }
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }

    public String takeScreenshot(String name) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "/reports/" + name + ".png";
        FileUtils.copyFile(src, new File(destPath));
        return destPath;
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
