package com.autoex.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test10_corehyperlink {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        // ✅ Setup Extent Report
        String projectPath = System.getProperty("user.dir");
        ExtentSparkReporter spark = new ExtentSparkReporter(projectPath + "/reports/HyperlinkReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Verify All Hyperlinks on Test Cases Page");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        test.info("Browser launched and maximized");
    }

    @Test
    public void verifyAllHyperlinksOnTestCasesPage() {
        driver.get("https://automationexercise.com/test_cases");
        test.info("Navigated to Automation Exercise Test Cases page");

        List<WebElement> links = driver.findElements(By.tagName("a"));
        test.info("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) {
                test.warning("Empty or null href found.");
                continue;
            }

            if (!url.startsWith("http")) {
                test.info("Skipping non-http link: " + url);
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.connect();
                int respCode = connection.getResponseCode();

                if (respCode < 400) {
                    test.pass(url + " --> " + respCode);
                } else {
                    test.fail("Broken link: " + url + " --> " + respCode);
                }

                Assert.assertTrue(respCode < 400, "Broken link: " + url);
            } catch (Exception e) {
                test.fail("Exception for link: " + url + " | " + e.getMessage());
                Assert.fail("Exception for link: " + url + " | " + e.getMessage());
            }
        }

        // ✅ Screenshot at end
        String screenshotPath = takeScreenshot("hyperlink");
        if (screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, "Final Screenshot after hyperlink test");
            } catch (Exception e) {
                test.warning("Could not attach screenshot: " + e.getMessage());
            }
        } else {
            test.warning("Screenshot could not be captured.");
        }
    }

    // ✅ Screenshot method
    public String takeScreenshot(String name) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File("screenshots/" + name + ".png");
            dest.getParentFile().mkdirs();
            org.openqa.selenium.io.FileHandler.copy(src, dest);
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
            return dest.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush();
    }
}

