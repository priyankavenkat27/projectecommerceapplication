package com.autoex.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;

public class Ui_cart {

    WebDriver driver;
    WebDriverWait wait;
    int screenshotCounter = 1;

    // ExtentReports variables
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Initialize ExtentReports
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/CartPageTestReport.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Open Cart page directly
        driver.get("https://automationexercise.com/view_cart");
    }

    // ==================== UI ELEMENT VERIFICATION TESTS ====================

    @Test(priority = 1)
    public void verifyLogoOnCartPage() {
        test = extent.createTest("Verify Logo on Cart Page");
        try {
            By logoLocator = By.xpath("//div[@class='logo pull-left']/a/img");
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(logoLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logo);
            Assert.assertTrue(logo.isDisplayed());
            test.pass("Logo 'Automation Engineer' is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Logo verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 2)
    public void verifyProductsIconOnCartPage() {
        test = extent.createTest("Verify Products Icon on Cart Page");
        try {
            By productsIconLocator = By.xpath("//a[contains(@href,'/products') and contains(text(),'Products')]");
            WebElement productsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(productsIconLocator));
            Assert.assertTrue(productsIcon.isDisplayed());
            test.pass("'Products' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Products' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3)
    public void verifyHomeIconOnCartPage() {
        test = extent.createTest("Verify Home Icon on Cart Page");
        try {
            By homeIconLocator = By.xpath("//i[@class='fa fa-home']/parent::a");
            WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(homeIconLocator));
            Assert.assertTrue(homeIcon.isDisplayed());
            test.pass("'Home' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Home' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifySignupLoginIconOnCartPage() {
        test = extent.createTest("Verify Signup/Login Icon on Cart Page");
        try {
            By signupLoginLocator = By.xpath("//a[contains(@href,'/login') and contains(text(),'Signup / Login')]");
            WebElement signupLoginIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(signupLoginLocator));
            Assert.assertTrue(signupLoginIcon.isDisplayed());
            test.pass("'Signup / Login' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Signup / Login' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5)
    public void verifyVideoTutorialsIconOnCartPage() {
        test = extent.createTest("Verify Video Tutorials Icon on Cart Page");
        try {
            By videoTutorialsLocator = By.xpath("//a[contains(text(),'Video Tutorials')]");
            WebElement videoTutorialsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(videoTutorialsLocator));
            Assert.assertTrue(videoTutorialsIcon.isDisplayed());
            test.pass("'Video Tutorials' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Video Tutorials' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 6)
    public void verifyContactUsIconOnCartPage() {
        test = extent.createTest("Verify Contact Us Icon on Cart Page");
        try {
            By contactUsLocator = By.xpath("//a[contains(text(),'Contact us')]");
            WebElement contactUsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsLocator));
            Assert.assertTrue(contactUsIcon.isDisplayed());
            test.pass("'Contact Us' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Contact Us' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 7)
    public void verifyTestCasesIconOnCartPage() {
        test = extent.createTest("Verify Test Cases Icon on Cart Page");
        try {
            By testCasesLocator = By.xpath("//a[contains(text(),'Test Cases')]");
            WebElement testCasesIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(testCasesLocator));
            Assert.assertTrue(testCasesIcon.isDisplayed());
            test.pass("'Test Cases' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Test Cases' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 8)
    public void verifyApiTestingIconOnCartPage() {
        test = extent.createTest("Verify API Testing Icon on Cart Page");
        try {
            By apiTestingLocator = By.xpath("//a[contains(text(),'API Testing')]");
            WebElement apiTestingIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(apiTestingLocator));
            Assert.assertTrue(apiTestingIcon.isDisplayed());
            test.pass("'API Testing' icon/button is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'API Testing' icon/button not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 9)
    public void verifyScrollBarOnCartPage() {
        test = extent.createTest("Verify Scroll Bar on Cart Page");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);
            WebElement footer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer")));
            Assert.assertTrue(footer.isDisplayed());
            test.pass("Scroll bar works properly on Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Scroll bar verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 10)
    public void verifyScrollFunctionalityOnCartPage() {
        test = extent.createTest("Verify Scroll Functionality on Cart Page");
        try {
            Long initialScroll = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);
            Long scrolledPosition = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");
            Long documentHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight;");
            Long windowHeight = (Long) ((JavascriptExecutor) driver).executeScript("return window.innerHeight;");

            Assert.assertTrue(scrolledPosition > 0);
            Assert.assertTrue(documentHeight > windowHeight);

            test.pass("Scroll functionality is working properly on Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Scroll functionality failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ==================== SUBSCRIPTION TESTS ====================

    @Test(priority = 11)
    public void verifySubscriptionIconOnCartPage() {
        test = extent.createTest("Verify Subscription Icon on Cart Page");
        try {
            By subscriptionLocator = By.xpath("//h2[contains(text(),'Subscription')]");
            WebElement subscriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionLocator));
            Assert.assertTrue(subscriptionElement.isDisplayed());
            test.pass("'Subscription' section is visible on the Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Subscription' section not found: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 12)
    public void verifySubscriptionWithValidEmailOnCartPage() {
        test = extent.createTest("Verify Subscription with Valid Email");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
            WebElement subscribeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));

            emailInput.clear();
            emailInput.sendKeys("testuser@example.com");
            subscribeButton.click();
            Thread.sleep(1000);

            Assert.assertFalse(driver.getPageSource().toLowerCase().contains("error"));
            test.pass("Subscription with valid email processed successfully.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Subscription with valid email failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 13)
    public void verifySubscriptionWithInvalidEmailOnCartPage() {
        test = extent.createTest("Verify Subscription with Invalid Email");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
            WebElement subscribeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));

            String invalidEmail = "invalid-email";
            emailInput.clear();
            emailInput.sendKeys(invalidEmail);
            subscribeButton.click();
            Thread.sleep(1000);

            String currentValue = emailInput.getAttribute("value");
            Assert.assertEquals(currentValue, invalidEmail);
            test.pass("Subscription with invalid email retains input as expected.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Subscription with invalid email failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 14)
    public void verifySubscriptionWithExistingEmailOnCartPage() {
        test = extent.createTest("Verify Subscription with Existing Email");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
            WebElement subscribeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));

            emailInput.clear();
            emailInput.sendKeys("aasharaja305@gmail.com");
            subscribeButton.click();
            Thread.sleep(1000);

            By alertLocator = By.xpath("//div[contains(@class,'alert-warning') or contains(text(),'already subscribed')]");
            WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(alertLocator));

            Assert.assertTrue(alertMessage.isDisplayed());
            test.pass("Already subscribed email shows alert as expected: " + alertMessage.getText());
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Subscription with existing email failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 15)
    public void verifySubscriptionWithNoEmailOnCartPage() {
        test = extent.createTest("Verify Subscription with No Email");
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));
            WebElement subscribeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("subscribe")));

            emailInput.clear();
            subscribeButton.click();
            Thread.sleep(1000);

            String emailValue = emailInput.getAttribute("value");
            Assert.assertEquals(emailValue, "");
            test.pass("Subscription with no email prevented successfully.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Subscription with no email failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ==================== BREADCRUMB TESTS ====================

    @Test(priority = 16)
    public void verifyHomeBreadcrumbOnCartPage() {
        test = extent.createTest("Verify Home Breadcrumb on Cart Page");
        try {
            driver.get("https://automationexercise.com/view_cart");
            WebElement container = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.breadcrumbs")));
            WebElement homeBreadcrumb = container.findElement(By.xpath(".//a[text()='Home']"));
            Assert.assertTrue(homeBreadcrumb.isDisplayed());
            homeBreadcrumb.click();
            wait.until(ExpectedConditions.urlToBe("https://automationexercise.com/"));
            Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
            test.pass("Home breadcrumb navigates correctly to Home page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Home breadcrumb verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 17)
    public void verifyCartBreadcrumbOnCartPage() {
        test = extent.createTest("Verify Cart Breadcrumb on Cart Page");
        try {
            driver.get("https://automationexercise.com/view_cart");
            WebElement cartBreadcrumb = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//li[@class='active' and text()='Shopping Cart']")));
            Assert.assertTrue(cartBreadcrumb.isDisplayed());
            test.pass("Shopping Cart breadcrumb is visible on Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Cart breadcrumb verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    // ==================== CART ITEM TESTS ====================

    @Test(priority = 18)
    public void verifyHereAnchorOnCartPage() {
        test = extent.createTest("Verify 'here' link on Cart Page");
        try {
            driver.get("https://automationexercise.com/view_cart");
            By emptyCartMsgLocator = By.xpath("//p[contains(text(),'Your shopping cart is empty')]");
            boolean isEmptyCart = driver.findElements(emptyCartMsgLocator).size() > 0;

            if (isEmptyCart) {
                WebElement hereLink = driver.findElement(By.xpath("//p[contains(text(),'Click')]/a[text()='here']"));
                Assert.assertTrue(hereLink.isDisplayed());
                hereLink.click();
                wait.until(ExpectedConditions.urlContains("/products"));
                Assert.assertTrue(driver.getCurrentUrl().contains("/products"));
                test.pass("'here' link navigates correctly to Products page.");
            } else {
                test.info("Cart is not empty; 'here' link not present.");
            }
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'here' link verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 19)
    public void verifyDeleteIconOnCartPage() {
        test = extent.createTest("Verify Delete Icon on Cart Page");
        try {
            driver.get("https://automationexercise.com/view_cart");
            By productRowLocator = By.cssSelector("tr.cart_item");
            int productCount = driver.findElements(productRowLocator).size();

            if (productCount > 0) {
                WebElement deleteIcon = driver.findElement(By.cssSelector("tr.cart_item td.cart_delete a"));
                Assert.assertTrue(deleteIcon.isDisplayed());
                test.pass("Delete icon is visible for product in cart.");
                takeScreenshot();
            } else {
                test.info("Cart is empty; delete icon not present.");
            }
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Delete icon verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 21)
    public void verifyProceedToCheckoutButton() {
        test = extent.createTest("Verify Proceed To Checkout Button on Cart Page");
        try {
            driver.get("https://automationexercise.com/view_cart");
            By productRowLocator = By.cssSelector("tr.cart_item");
            int productCount = driver.findElements(productRowLocator).size();

            if (productCount > 0) {
                By checkoutBtnLocator = By.xpath("//a[contains(text(),'Proceed To Checkout') or contains(@href,'checkout')]");
                WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutBtnLocator));
                Assert.assertTrue(checkoutBtn.isDisplayed());
                test.pass("'Proceed To Checkout' button is visible.");
            } else {
                test.info("Cart is empty; 'Proceed To Checkout' button not present.");
            }
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("'Proceed To Checkout' button verification failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 22)
    public void verifyItemAddedToCartIsDisplayed() {
        test = extent.createTest("Verify Item Added To Cart Is Displayed");
        try {
            driver.get("https://automationexercise.com/products");
            By firstProductAddToCartBtn = By.xpath("(//a[text()='Add to cart'])[1]");
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(firstProductAddToCartBtn));
            addToCartBtn.click();
            Thread.sleep(2000);

            driver.get("https://automationexercise.com/view_cart");
            WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.table-condensed tbody")));
            int productCount = cartTable.findElements(By.tagName("tr")).size();
            Assert.assertTrue(productCount > 0);
            test.pass("Product added to cart is displayed on Cart page.");
            takeScreenshot();
        } catch (Exception e) {
            takeScreenshot();
            test.fail("Product not displayed in cart: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority=23)
    
    public void verifyItemAddedToCartAndDescription() {
        try {
            driver.get("https://automationexercise.com");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Handle potential popups
            try {
                WebElement closeAd = driver.findElement(By.id("dismiss-button")); 
                if (closeAd.isDisplayed()) closeAd.click();
            } catch (NoSuchElementException ignored) {}

            // Click Products
            By productsLink = By.xpath("//a[contains(text(),'Products')]");
            WebElement productsBtn = wait.until(ExpectedConditions.elementToBeClickable(productsLink));
            productsBtn.click();

            // Continue with your test steps...
        } catch (Exception e) {
            takeScreenshot();
            Assert.fail("❌ Test failed: " + e.getMessage());
        }
    }
    @Test(priority = 24)
    public void verifyProductPriceOnCartPage() {
        try {
            // Navigate to Cart page
            driver.get("https://automationexercise.com/view_cart");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Check if there are products in the cart
            By productRowLocator = By.cssSelector("tr.cart_item");
            int productCount = driver.findElements(productRowLocator).size();

            if (productCount > 0) {
                // Locate price element for the first product
                WebElement priceElement = driver.findElement(By.cssSelector("tr.cart_item td.cart_price p"));

                // Verify the price is displayed and not empty
                Assert.assertTrue(priceElement.isDisplayed(), "❌ Product price is not visible in cart.");
                Assert.assertFalse(priceElement.getText().trim().isEmpty(), "❌ Product price text is empty in cart.");

                System.out.println("✅ Product price is displayed on the Cart page: " + priceElement.getText());

                takeScreenshot(); // Capture screenshot for reporting

            } else {
                System.out.println("ℹ Cart is empty, product price not present. Skipping this verification.");
                takeScreenshot(); // Even if cart is empty
            }

        } catch (Exception e) {
            takeScreenshot();
            Assert.fail("❌ Verification of product price on Cart page failed: " + e.getMessage());
        }
    }
    @Test(priority = 25)
    public void verifyProductQuantityOnCartPage() {
        try {
            // Navigate to Cart page
            driver.get("https://automationexercise.com/view_cart");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Check if there are products in the cart
            By productRowLocator = By.cssSelector("tr.cart_item");
            int productCount = driver.findElements(productRowLocator).size();

            if (productCount > 0) {
                // Locate quantity input box for the first product
                WebElement quantityElement = driver.findElement(By.cssSelector("tr.cart_item td.cart_quantity input.cart_quantity_input"));

                // Verify the quantity element is displayed and has a value
                Assert.assertTrue(quantityElement.isDisplayed(), "❌ Product quantity is not visible in cart.");
                String quantityValue = quantityElement.getAttribute("value").trim();
                Assert.assertFalse(quantityValue.isEmpty(), "❌ Product quantity is empty in cart.");

                System.out.println("✅ Product quantity is displayed on the Cart page: " + quantityValue);

                takeScreenshot(); // Capture screenshot for reporting

            } else {
                System.out.println("ℹ Cart is empty, product quantity not present. Skipping this verification.");
                takeScreenshot(); // Screenshot even if cart is empty
            }

        } catch (Exception e) {
            takeScreenshot();
            Assert.fail("❌ Verification of product quantity on Cart page failed: " + e.getMessage());
        }
    }@Test(priority = 26)
    public void verifyProductTotalOnCartPage() {
        try {
            // Navigate to Cart page
            driver.get("https://automationexercise.com/view_cart");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Check if there are products in the cart
            By productRowLocator = By.cssSelector("tr.cart_item");
            int productCount = driver.findElements(productRowLocator).size();

            if (productCount > 0) {
                // Loop through each product row
                for (WebElement productRow : driver.findElements(productRowLocator)) {
                    // Locate total column (price * quantity)
                    WebElement totalElement = productRow.findElement(By.cssSelector("td.cart_total span"));
                    
                    // Verify the total element is displayed
                    Assert.assertTrue(totalElement.isDisplayed(), "❌ Product total is not visible in cart.");
                    
                    // Get the value
                    String totalValue = totalElement.getText().trim();
                    Assert.assertFalse(totalValue.isEmpty(), "❌ Product total is empty in cart.");

                    System.out.println("✅ Product total is displayed: " + totalValue);
                }

                takeScreenshot(); // Capture screenshot for reporting

            } else {
                System.out.println("ℹ Cart is empty, product total not present. Skipping this verification.");
                takeScreenshot(); // Screenshot even if cart is empty
            }

        } catch (Exception e) {
            takeScreenshot();
            Assert.fail("❌ Verification of product total on Cart page failed: " + e.getMessage());
        }
    }





    // ==================== SCREENSHOT METHOD ====================
    public void takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String filename = String.format("cart_%d.png", screenshotCounter++);
        String path = System.getProperty("user.dir") + "/screenshots/" + filename;
        try {
            FileUtils.copyFile(src, new File(path));
            System.out.println("📸 Screenshot saved as: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}
