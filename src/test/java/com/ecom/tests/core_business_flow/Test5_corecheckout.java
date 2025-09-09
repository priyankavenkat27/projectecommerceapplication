package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test5_corecheckout {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setup Extent Report in reports folder
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/CheckoutReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyCheckoutButtonWorking() throws IOException, InterruptedException {
        test = extent.createTest("Verify Checkout Button Working");

        try {
            // Add first product to cart
            driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]")).click();
            test.info("Added first product to cart");

            // Wait for modal and click 'View Cart'
            Thread.sleep(2000); 
            driver.findElement(By.xpath("//u[text()='View Cart']")).click();
            test.info("Navigated to cart page");

            // Verify Checkout button is present
            WebElement checkoutBtn = driver.findElement(By.xpath("//a[@class='btn btn-default check_out']"));
            Assert.assertTrue(checkoutBtn.isDisplayed(), "Checkout button not visible!");
            test.pass("Checkout button is visible");

            // Click Checkout button
            checkoutBtn.click();
            test.info("Clicked Checkout button");

            // Take screenshot
            String screenshotPath = takeScreenshot("CheckoutResult");
            test.addScreenCaptureFromPath(screenshotPath);

            // Verify navigation → should show "Register / Login"
            WebElement loginPrompt = driver.findElement(By.xpath("//u[text()='Register / Login']"));
            Assert.assertTrue(loginPrompt.isDisplayed(), "Checkout flow did not reach login/registration step!");
            test.pass("Checkout flow reached login/registration step");

        } catch (Exception e) {
            String errorPath = takeScreenshot("CheckoutError");
            test.fail("Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(errorPath);
            throw e; // rethrow so TestNG marks it failed
        }
    }

    // Reusable screenshot method with timestamp
    public String takeScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File folder = new File(System.getProperty("user.dir") + "/screenshots");
        if (!folder.exists()) folder.mkdir();

        String destPath = folder.getAbsolutePath() + "/" + name + "_" + timestamp + ".png";
        File dest = new File(destPath);
        Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

        return destPath;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush(); // write the report to HTML
    }
}
