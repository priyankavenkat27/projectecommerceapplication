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

public class Test7_coreaddtocart {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        // Save report in the same folder where other reports are kept
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/AddToCartReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void verifyAddToCartButton() throws IOException, InterruptedException {
        test = extent.createTest("Verify Add To Cart Functionality");

        try {
            // Go to Products page
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            test.info("Navigated to Products page");

            // Click on 'Add to Cart' of first product
            WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
            Assert.assertTrue(addToCartBtn.isDisplayed(), "Add to Cart button is not visible!");
            test.pass("Add to Cart button is visible");
            addToCartBtn.click();

            // Wait for modal to appear
            Thread.sleep(2000);

            // Verify "Added!" popup is visible
            WebElement modal = driver.findElement(By.xpath("//h4[contains(text(),'Added!')]"));
            Assert.assertTrue(modal.isDisplayed(), "Product was not added to cart!");
            test.pass("Product successfully added to cart");

            // Take screenshot
            String addToCartScreenshot = takeScreenshot("AddToCart");
            test.addScreenCaptureFromPath(addToCartScreenshot);

            // Click 'View Cart'
            driver.findElement(By.xpath("//u[text()='View Cart']")).click();
            test.info("Clicked on View Cart");

            // Verify cart page shows product
            WebElement cartTable = driver.findElement(By.xpath("//table[@id='cart_info_table']"));
            Assert.assertTrue(cartTable.isDisplayed(), "Cart page does not show the added product!");
            test.pass("Cart page displays the added product");

            // Another screenshot
            String cartPageScreenshot = takeScreenshot("CartPage");
            test.addScreenCaptureFromPath(cartPageScreenshot);

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            String errorScreenshot = takeScreenshot("Error");
            test.addScreenCaptureFromPath(errorScreenshot);
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
        extent.flush(); // write everything to report
    }
}
