package com.autoex.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2_coresignup {
    WebDriver driver;

    // 🔹 ExtentReports objects
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // ✅ Setup Extent Report
        String reportPath = System.getProperty("user.dir") + "/reports/SignupReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "Automation Exercise");
        extent.setSystemInfo("Tester", "Boomika");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Test
    public void testSignupButton() {
        test = extent.createTest("Signup Button Test")
                     .assignCategory("UI Test")
                     .assignAuthor("Boomika");

        try {
            // ✅ Test data
            String name = "Boomika";
            String email = "boomika" + System.currentTimeMillis() + "@test.com"; // unique email

            // ✅ Navigate to Automation Exercise
            driver.get("https://automationexercise.com/");
            test.info("Navigated to Automation Exercise");

            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            test.info("Clicked on Signup/Login link");

            // ✅ Enter Name & Email in Signup section
            driver.findElement(By.name("name")).sendKeys(name);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
            test.pass("Entered Name and Email");

            // ✅ Click on Signup button
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            test.pass("Clicked Signup button");

            // ✅ Take screenshot after clicking Signup
            String screenshotPath = takeScreenshot("signup_button");
            test.addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            String screenshotPath = takeScreenshot("signup_error");
            test.fail("Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
        }
    }

    public String takeScreenshot(String fileName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = System.getProperty("user.dir") + "/screenshots/" + fileName + "_" + timestamp + ".png";
            File dest = new File(destPath);
            org.openqa.selenium.io.FileHandler.copy(src, dest);
            System.out.println("📸 Screenshot saved: " + dest.getAbsolutePath());
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        // ✅ Flush the report
        if (extent != null) {
            extent.flush();
        }
    }
}
