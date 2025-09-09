package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Test13_coreplace {
    WebDriver driver;
    WebDriverWait wait;

    ExtentReports extent;
    ExtentTest test;

    // Replace with valid credentials
    String email = "aasharaja305@gmail.com";
    String password = "Aasharaja@2468";

    @BeforeClass
    public void setup() {
        // Setup Extent Report
        String projectPath = System.getProperty("user.dir");
        File reportDir = new File(projectPath + "\\reports");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(projectPath + "\\reports\\PlaceOrderReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        test = extent.createTest("Place Order Test", "Verify login, add to cart, and place order flow");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void loginAddToCartAndPlaceOrder() throws IOException, InterruptedException {
        try {
            // Step 1: Login
            driver.get("https://automationexercise.com/login");
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();

            WebElement loggedInMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Logged in as')]")));
            Assert.assertTrue(loggedInMsg.isDisplayed(), "Login failed.");
            test.pass("✅ Login successful");

            // Step 2: Go to Products page and add first product to cart
            driver.get("https://automationexercise.com/products");
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Add to cart')])[1]")));
            addToCart.click();
            test.pass("✅ Product added to cart");

            // Step 3: Click View Cart from modal
            WebElement viewCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//u[text()='View Cart']")));
            viewCart.click();

            Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Not on cart page.");
            test.pass("✅ Navigated to Cart page");

            // Step 4: Click Proceed To Checkout
            WebElement proceedCheckout = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-default.check_out")));
            takeScreenshot("BeforeCheckout.png");
            test.info("📸 Screenshot before checkout").addScreenCaptureFromPath("BeforeCheckout.png");
            proceedCheckout.click();
            test.pass("✅ Clicked Proceed To Checkout");

            // Step 5: On Checkout page → Click Place Order
            WebElement placeOrderBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Place Order')]")));
            takeScreenshot("BeforePlaceOrder.png");
            test.info("📸 Screenshot before placing order").addScreenCaptureFromPath("BeforePlaceOrder.png");
            placeOrderBtn.click();
            test.pass("✅ Clicked Place Order");

            // Step 6: Wait for Payment section
            WebElement paymentSection = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Payment')]")));
            Assert.assertTrue(paymentSection.isDisplayed(), "Payment section did not appear.");
            takeScreenshot("PaymentSection.png");
            test.pass("✅ Payment section displayed")
                .addScreenCaptureFromPath("PaymentSection.png");

            System.out.println("✅ Login, add to cart, and Place Order flow executed successfully.");
        } catch (Exception e) {
            String screenshotPath = takeScreenshot("Error_PlaceOrder.png");
            test.fail("❌ Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotPath);
            throw e;
        }
    }

    // Utility method to take screenshots
    public String takeScreenshot(String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "\\reports\\screenshots\\" + fileName;
        File dest = new File(path);

        // create folder if not exists
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        FileUtils.copyFile(src, dest);
        System.out.println("📸 Screenshot saved: " + path);
        return path;
    }

    @AfterClass
    public void tearDown() {
        if (extent != null) {
            extent.flush();
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
