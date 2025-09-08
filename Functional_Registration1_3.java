package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.*;
import org.testng.Assert;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class Functional_Registration1_3 {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    String baseUrl = "https://example.com";  // <-- Replace with your actual URL

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./TestReportRegistartion1_3.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @AfterSuite
    public void teardownReport() {
        extent.flush();
    }

    public void takeScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String dest = "./Screenshots/" + testName + "_" + timestamp + ".png";

            File target = new File(dest);
            FileUtils.copyFile(src, target);

            test.addScreenCaptureFromPath(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void FTC01_verifyChromeLaunch() {
        test = extent.createTest("Priority 1 - Chrome Browser Launch Test");

        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(baseUrl);

            String currentUrl = driver.getCurrentUrl();
            String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
            String normalizedCurrentUrl = currentUrl.endsWith("/") ? currentUrl : currentUrl + "/";

            Assert.assertEquals(normalizedCurrentUrl, normalizedBaseUrl);
            test.pass("Chrome launched successfully and URL loaded.");

        } catch (Exception e) {
            test.fail("Chrome failed to launch or load URL: " + e.getMessage());
            takeScreenshot("FTC01_Chrome");
            Assert.fail(e.getMessage());
        } finally {
            if (driver != null) driver.quit();
        }
    }

    @Test(priority = 2)
    public void FTC02_verifyFirefoxLaunch() {
        test = extent.createTest("Priority 2 - Firefox Browser Launch Test");

        try {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(baseUrl);

            String currentUrl = driver.getCurrentUrl();
            String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
            String normalizedCurrentUrl = currentUrl.endsWith("/") ? currentUrl : currentUrl + "/";

            Assert.assertEquals(normalizedCurrentUrl, normalizedBaseUrl);
            test.pass("Firefox launched successfully and URL loaded.");

        } catch (Exception e) {
            test.fail("Firefox failed to launch or load URL: " + e.getMessage());
            takeScreenshot("FTC02_Firefox");
            Assert.fail(e.getMessage());
        } finally {
            if (driver != null) driver.quit();
        }
    }

    @Test(priority = 3)
    public void FTC03_verifyEdgeLaunch() {
        test = extent.createTest("Priority 3 - Edge Browser Launch Test");

        try {
            driver = new EdgeDriver();
            driver.manage().window().maximize();
            driver.get(baseUrl);

            String currentUrl = driver.getCurrentUrl();
            String normalizedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
            String normalizedCurrentUrl = currentUrl.endsWith("/") ? currentUrl : currentUrl + "/";

            Assert.assertEquals(normalizedCurrentUrl, normalizedBaseUrl);
            test.pass("Edge launched successfully and URL loaded.");

        } catch (Exception e) {
            test.fail("Edge failed to launch or load URL: " + e.getMessage());
            takeScreenshot("FTC03_Edge");
            Assert.fail(e.getMessage());
        } finally {
            if (driver != null) driver.quit();
        }
    }

}
