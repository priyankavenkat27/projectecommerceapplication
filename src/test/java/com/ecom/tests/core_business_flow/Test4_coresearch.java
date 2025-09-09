package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test4_coresearch {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setup Extent Report
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/SearchReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifySearchButtonWorking() throws IOException {
        test = extent.createTest("Verify Search Button Working");

        try {
            // Go to Products page
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            test.info("Navigated to Products page");

            // Verify search button is present
            WebElement searchButton = driver.findElement(By.id("submit_search"));
            Assert.assertTrue(searchButton.isDisplayed(), "Search button not visible!");
            test.pass("Search button is visible");

            // Enter product name
            driver.findElement(By.id("search_product")).sendKeys("Tshirt");
            test.info("Entered product name: Tshirt");

            // Click search button
            searchButton.click();
            test.info("Clicked search button");

            // Take screenshot
            String screenshotPath = takeScreenshot("SearchResult");
            test.addScreenCaptureFromPath(screenshotPath);

            // Verify search results
            WebElement resultsHeader = driver.findElement(By.xpath("//h2[text()='Searched Products']"));
            Assert.assertTrue(resultsHeader.isDisplayed(), "Search results page not displayed!");
            test.pass("Search results page displayed successfully");

        } catch (Exception e) {
            String errorPath = takeScreenshot("SearchError");
            test.fail("Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(errorPath);
            throw e;
        }
    }

    // Reusable screenshot method
    public String takeScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File folder = new File(System.getProperty("user.dir") + "/screenshots");
        if (!folder.exists()) folder.mkdir();

        String destPath = folder.getAbsolutePath() + "/" + name + "_" + timestamp + ".png";
        File dest = new File(destPath);

        Files.copy(src.toPath(), new FileOutputStream(dest));
        return destPath;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush(); // write the report
    }
}
