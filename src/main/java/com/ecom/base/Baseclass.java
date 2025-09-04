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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.ecom.utilities.extentmanager;
import com.ecom.utilities.screenshotutilities;

public class Baseclass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static Properties config;

    // 🔹 Initialize Extent Reports only once before the suite
    @BeforeSuite
    public void startReport() {
        extent = extentmanager.getInstance();
    }

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
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            try {
                if (result.getStatus() == ITestResult.FAILURE) {
                    screenshotutilities.capturescreen(driver, result.getName());
                }
            } catch (Exception e) {
                System.out.println("Screenshot capture skipped: " + e.getMessage());
            } finally {
                driver.quit();
            }
        }
    }

    // 🔹 Flush report only once after the suite finishes
    @AfterSuite
    public void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
