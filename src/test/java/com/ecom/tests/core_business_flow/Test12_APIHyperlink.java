package com.autoex.tests;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test12_APIHyperlink {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        String projectpath = System.getProperty("user.dir");

        // ✅ Setup Extent Report
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "/reports/APIHyperlinkReport.html");
        extent.attachReporter(spark);
        test = extent.createTest("Verify All API Hyperlinks");

        // ✅ Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/api_list");
    }

    @Test
    public void verifyAllHyperlinksOnAPITestingPage() throws IOException {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        test.info("Total links found: " + links.size());

        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url == null || url.isEmpty()) continue;

            if (!(url.startsWith("http://") || url.startsWith("https://"))) {
                test.warning("Skipping non-HTTP link: " + url);
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();

                if (responseCode == 200 || responseCode == 301 || responseCode == 302 || responseCode == 405) {
                    test.pass(url + " --> " + responseCode);
                } else {
                    test.fail("Broken link: " + url + " --> " + responseCode);
                    Assert.fail("Broken link: " + url);
                }

            } catch (Exception e) {
                test.fail("Exception for URL: " + url + " | " + e.getMessage());
            }
        }

        // ✅ Screenshot after checking links
        String screenshotPath = takeScreenshot(System.getProperty("user.dir"), "APIHyperlink.png");
        test.addScreenCaptureFromPath(screenshotPath);
    }

    // 📸 Screenshot Helper
    public String takeScreenshot(String projectpath, String filename) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File screenshotDir = new File(projectpath + "\\screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String screenpath = projectpath + "\\screenshots\\" + filename;
        File dest = new File(screenpath);
        FileUtils.copyFile(src, dest);

        return screenpath;
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush(); // ✅ generate report
    }
}
