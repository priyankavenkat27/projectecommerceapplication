package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test8_corecontactusfield {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setup Extent Report
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + "\\reports\\contactus_fields_report.html"; // report saved inside reports folder
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        test = extent.createTest("Verify Contact Us Text Fields");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
        test.info("Launched browser and navigated to Automation Exercise site");
    }

    @Test
    public void verifyContactUsTextFields() throws IOException {
        try {
            // Navigate to Contact Us page
            driver.findElement(By.xpath("//a[@href='/contact_us']")).click();
            test.info("Navigated to Contact Us page");

            // Enter Name
            WebElement nameField = driver.findElement(By.name("name"));
            nameField.clear();
            nameField.sendKeys("Asha");
            Assert.assertEquals(nameField.getAttribute("value"), "Asha");
            test.pass("Entered Name successfully");

            // Enter Email
            WebElement emailField = driver.findElement(By.name("email"));
            emailField.clear();
            emailField.sendKeys("aasharaja305@gmail.com");
            Assert.assertEquals(emailField.getAttribute("value"), "aasharaja305@gmail.com");
            test.pass("Entered Email successfully");

            // Enter Subject
            WebElement subjectField = driver.findElement(By.name("subject"));
            subjectField.clear();
            subjectField.sendKeys("Automation Test");
            Assert.assertEquals(subjectField.getAttribute("value"), "Automation Test");
            test.pass("Entered Subject successfully");

            // Enter Message
            WebElement messageField = driver.findElement(By.id("message"));
            messageField.clear();
            messageField.sendKeys("This is a test message.");
            Assert.assertEquals(messageField.getAttribute("value"), "This is a test message.");
            test.pass("Entered Message successfully");

            // Take screenshot
            String screenshotPath = System.getProperty("user.dir") + "\\reports\\ContactUsTextFields.png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));
            test.pass("Captured screenshot of filled form")
                .addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            String screenshotPath = System.getProperty("user.dir") + "\\reports\\ContactUsTextFields_Fail.png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));
            test.fail("Test failed with exception: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
            throw e; // rethrow so TestNG marks it failed
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            test.info("Browser closed");
        }
        extent.flush(); // write results to report
    }
}

