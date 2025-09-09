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

public class Test9_coresubmit {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setup Extent Report
        String projectPath = System.getProperty("user.dir");
        String reportPath = projectPath + "\\reports\\contactus_report.html"; // Save report in reports folder
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        test = extent.createTest("Verify Contact Us Form Submission");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
        test.info("Browser launched and navigated to Automation Exercise site");
    }

    @Test
    public void verifyContactUsFormSubmission() throws IOException, InterruptedException {
        try {
            // Navigate to Contact Us page
            driver.findElement(By.xpath("//a[@href='/contact_us']")).click();
            test.info("Navigated to Contact Us page");

            // Enter Name
            WebElement nameField = driver.findElement(By.name("name"));
            nameField.clear();
            nameField.sendKeys("Asha");

            // Enter Email
            WebElement emailField = driver.findElement(By.name("email"));
            emailField.clear();
            emailField.sendKeys("aasharaja305@gmail.com");

            // Enter Subject
            WebElement subjectField = driver.findElement(By.name("subject"));
            subjectField.clear();
            subjectField.sendKeys("Automation Test");

            // Enter Message
            WebElement messageField = driver.findElement(By.id("message"));
            messageField.clear();
            messageField.sendKeys("This is a test message.");

            test.info("Filled in form fields");

            // Upload a file (required field on this page)
            WebElement uploadFile = driver.findElement(By.name("upload_file"));
            uploadFile.sendKeys(System.getProperty("user.dir") + "/ContactUsTextFields.png"); 
            test.info("Uploaded a file");

            // Click Submit button
            driver.findElement(By.name("submit")).click();
            test.info("Clicked Submit button");

            // Handle the alert popup (Click OK)
            Alert alert = driver.switchTo().alert();
            alert.accept();
            test.info("Accepted alert popup");

            // Verify success message
            WebElement successMsg = driver.findElement(By.cssSelector(".status.alert.alert-success"));
            String actualMsg = successMsg.getText().trim();
            Assert.assertEquals(actualMsg, "Success! Your details have been submitted successfully.");
            test.pass("Verified success message");

            // Take screenshot after submission
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "\\reports\\ContactUsSubmission.png";
            FileUtils.copyFile(src, new File(screenshotPath));
            test.pass("Screenshot captured after submission")
                .addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {
            String screenshotPath = System.getProperty("user.dir") + "\\reports\\ContactUsSubmission_Fail.png";
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));
            test.fail("Test failed with exception: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
            throw e; // rethrow for TestNG to mark it failed
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

