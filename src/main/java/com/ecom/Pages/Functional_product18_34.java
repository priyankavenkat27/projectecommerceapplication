package com.autoex.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

public class Functional_product {

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    ExtentReports extent;
    ExtentTest test;
    int screenshotCounter = 23; // Start from prod_23

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        actions = new Actions(driver);

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/Functional_product.html");
        extent.attachReporter(spark);

        driver.get("https://automationexercise.com");
    }
    //Testcase 23

    @Test(priority = 1)
    public void verifyProductPageBannerVisibility() {
        test = extent.createTest("Verify Product Page Banner Visibility");
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
            WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='features_items']")));
            Assert.assertTrue(banner.isDisplayed());
            test.pass("Product page banner is visible",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Failed to verify product page banner: " + e.getMessage());
            Assert.fail();
        }
    }
    //Testcase 24

    @Test(priority = 2)
    public void verifyHoverOnProductThumbnails() {
        test = extent.createTest("Verify Hover On Product Thumbnails");
        try {
            List<WebElement> products = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));
            Assert.assertTrue(products.size() > 0);
            actions.moveToElement(products.get(0)).perform();
            test.pass("Hover on product thumbnail works",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Failed hover on product thumbnail: " + e.getMessage());
            Assert.fail();
        }
    }
    //Testcase 25

    @Test(priority = 3)
    public void verifySubscriptionWithInvalidEmail() {
        test = extent.createTest("Subscription with Invalid Email Test");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            WebElement emailBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("susbscribe_email")));
            emailBox.clear();
            emailBox.sendKeys("invalid-email");

            WebElement subscribeBtn = driver.findElement(By.id("subscribe"));
            subscribeBtn.click();

            Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript("return document.getElementById('susbscribe_email').checkValidity();");
            Assert.assertFalse(isValid);
            test.pass("HTML5 validation prevented subscription with invalid email",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Subscription invalid email test failed: " + e.getMessage());
            takeScreenshot();
            Assert.fail();
        }
    }
    //Testcase 26

    @Test(priority = 4)
    public void verifyWriteYourReviewSection() {
        test = extent.createTest("Verify Write Your Review Section");
        try {
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'View Product')])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review")));
            Assert.assertTrue(reviewSection.isDisplayed());
            test.pass("Write your review section is visible",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Failed to verify review section: " + e.getMessage());
            Assert.fail();
        }
    }
    //Testcase 27

    @Test(priority = 5)
    public void verifyAddToCartFunctionality() {
        test = extent.createTest("Verify Add To Cart Functionality");
        try {
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            WebElement addToCartBtn = driver.findElement(By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]"));
            Assert.assertTrue(addToCartBtn.isDisplayed());
            addToCartBtn.click();
            Thread.sleep(2000);

            WebElement modal = driver.findElement(By.xpath("//h4[contains(text(),'Added!')]"));
            Assert.assertTrue(modal.isDisplayed());
            test.pass("Product added to cart",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            driver.findElement(By.xpath("//u[text()='View Cart']")).click();
            WebElement cartTable = driver.findElement(By.xpath("//table[@id='cart_info_table']"));
            Assert.assertTrue(cartTable.isDisplayed());
            test.pass("Cart page displays added product",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Add To Cart test failed: " + e.getMessage());
            takeScreenshot();
            Assert.fail();
        }
    }
    //Testcase 28

    @Test(priority = 6)
    public void verifyReviewNameField() {
        test = extent.createTest("Verify Review Name Field");
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'View Product')])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
            nameField.clear();
            nameField.sendKeys("Test User");
            Assert.assertEquals(nameField.getAttribute("value"), "Test User");
            test.pass("Name field accepts input correctly",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Review Name field test failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
    //Testcase 29

    @Test(priority = 7)
    public void verifyReviewEmailField() {
        test = extent.createTest("Verify Review Email Field");
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'View Product')])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.clear();
            emailField.sendKeys("testuser@example.com");
            Assert.assertEquals(emailField.getAttribute("value"), "testuser@example.com");
            test.pass("Email field accepts input correctly",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Review Email field test failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
    //Testcase 30

    @Test(priority = 8)
    public void verifyReviewFields() {
        test = extent.createTest("Verify Review Fields (Name, Email, Review Text)");
        try {
            WebElement productsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Products')]")));
            productsBtn.click();

            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'View Product')])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review")));
            Assert.assertTrue(reviewSection.isDisplayed());
            test.pass("Review section is visible", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            WebElement nameField = driver.findElement(By.id("name"));
            nameField.clear();
            nameField.sendKeys("Test User");
            Assert.assertEquals(nameField.getAttribute("value"), "Test User");
            test.pass("Name field accepts input", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            WebElement emailField = driver.findElement(By.id("email"));
            emailField.clear();
            emailField.sendKeys("testuser@example.com");
            Assert.assertEquals(emailField.getAttribute("value"), "testuser@example.com");
            test.pass("Email field accepts input", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            WebElement reviewTextArea = driver.findElement(By.id("review"));
            reviewTextArea.clear();
            reviewTextArea.sendKeys("This is a test review.");
            Assert.assertEquals(reviewTextArea.getAttribute("value"), "This is a test review.");
            test.pass("Review text area accepts input", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        } catch (Exception e) {
            test.fail("Full review field test failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
    //Testcase 31

    @Test(priority = 9)
    public void verifyReviewSubmitButton() {
        test = extent.createTest("Verify Review Submit Button Functionality");
        try {
            WebElement productsBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Products')]")));
            productsBtn.click();

            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'View Product')])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", firstProduct);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstProduct);

            WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review")));
            Assert.assertTrue(reviewSection.isDisplayed());

            driver.findElement(By.id("name")).sendKeys("Test User");
            driver.findElement(By.id("email")).sendKeys("testuser@example.com");
            driver.findElement(By.id("review")).sendKeys("This is a test review.");

            WebElement submitBtn = driver.findElement(By.id("button-review"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitBtn);

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Thank you for your review.')]")));
            Assert.assertTrue(successMsg.isDisplayed());
            test.pass("Review submitted successfully", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Review submit button test failed: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
    //Testcase 32

    @Test(priority = 10)
    public void verifyReviewSubmissionFlow() {
        test = extent.createTest("Verify Review Submission Flow");
        try {
            driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();

            WebElement firstViewProduct = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[contains(text(),'View Product')])[1]")));
            firstViewProduct.click();

            WebElement reviewSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review")));
            Assert.assertTrue(reviewSection.isDisplayed());
            test.pass("Review section is visible", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            WebElement nameField = driver.findElement(By.id("name"));
            WebElement emailField = driver.findElement(By.id("email"));
            WebElement reviewField = driver.findElement(By.id("review"));

            nameField.sendKeys("Test User");
            emailField.sendKeys("testuser@example.com");
            reviewField.sendKeys("This is an automated test review.");

            test.pass("Review form filled successfully", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            WebElement submitBtn = driver.findElement(By.id("button-review"));
            submitBtn.click();

            WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(),'Thank you for your review.')]")));
            Assert.assertTrue(successMsg.isDisplayed());

            test.pass("Success message displayed after review submission", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

        } catch (Exception e) {
            test.fail("Review submission flow failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
    //Testcase 33

    @Test(priority = 11)
    public void verifySearchWithInvalidProduct() {
        test = extent.createTest("Verify Search Functionality with Invalid Product Name");
        try {
            WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));
            searchBox.clear();
            searchBox.sendKeys("invalidProduct123");

            driver.findElement(By.id("submit_search")).click();

            WebElement searchedProductsHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[text()='Searched Products']")));
            Assert.assertTrue(searchedProductsHeading.isDisplayed());
            test.pass("'Searched Products' heading is visible", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());

            List<WebElement> products = driver.findElements(By.xpath("//div[@class='features_items']/div"));
            Assert.assertTrue(products.size() == 0);
            test.pass("No products displayed for invalid search input", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Search with invalid product test failed: " + e.getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail();
        }
    }
   

  

    
    public String takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = String.format("prod_%02d_%s.png", screenshotCounter++, timestamp);
        String path = System.getProperty("user.dir") + "/screenshots/" + filename;
        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}

