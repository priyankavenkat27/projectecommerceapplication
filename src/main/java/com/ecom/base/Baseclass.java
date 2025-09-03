package com.ecom.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ecom.utilities.extentmanager;
import com.ecom.utilities.screenshotutilities;

public class Baseclass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Properties config;

    @BeforeMethod
    public void setup() throws IOException {
        // Load config.properties
        config = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "\\src\\main\\java\\com\\ecom\\config\\config.properties");
        config.load(fis);

        String browser = config.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();

        // Always open homepage before every test
        driver.get(config.getProperty("baseUrl"));

        // Setup Extent Reports (singleton)
        extent = extentmanager.getInstance();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        // Capture screenshot if test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = screenshotutilities.capturescreen(driver, result.getName());
            test.fail("Test Failed: " + result.getThrowable())
                .addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed ");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped: " + result.getThrowable());
        }

        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }
}
