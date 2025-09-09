package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test6_coredeletecart {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Save report in the same folder as other reports
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/DeleteCartReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyDeleteIconFromCart() throws IOException, InterruptedException {
        test = extent.createTest("Verify Delete From Cart Functionality");

        try {
            // Add first product to cart
            driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]")).click();
            test.info("First product added to cart");

            // Wait for modal and click 'View Cart'
            Thread.sleep(2000);
            driver.findElement(By.xpath("//u[text()='View Cart']")).click();
            test.info("Navigated to cart page");

            // Verify delete icon is present
            WebElement deleteIcon = driver.findElement(By.xpath("//a[@class='cart_quantity_delete']"));
            Assert.assertTrue(deleteIcon.isDisplayed(), "Delete icon not visible in cart!");
            test.pass("Delete icon is visible");

            // Click delete icon
            deleteIcon.click();
            Thread.sleep(2000); // wait for deletion
            test.info("Clicked delete icon");

            // Take screenshot
            String screenshotPath = takeScreenshot("DeleteCartResult");
            test.addScreenCaptureFromPath(screenshotPath);

            // Verify cart is empty
            WebElement emptyMsg = driver.findElement(By.xpath("//b[text()='Cart is empty!']"));
            Assert.assertTrue(emptyMsg.isDisplayed(), "Cart is not empty after deleting item!");
            test.pass("Cart is empty after deleting item");

        } catch (Exception e) {
            String errorPath = takeScreenshot("DeleteCartError");
            test.fail("Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(errorPath);
            throw e; // rethrow so TestNG marks it failed
        }
    }

    // Reusable screenshot method
    public String takeScreenshot(String name) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = System.getProperty("user.dir") + "/screenshots/" + name + "_" + timestamp + ".png";
        File dest = new File(destPath);
        FileUtils.copyFile(src, dest);
        return destPath;
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush(); // write report to HTML
    }
}

