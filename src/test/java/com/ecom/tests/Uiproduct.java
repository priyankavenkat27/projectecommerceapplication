package com.autoex.tests;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
public class Uiproduct {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;
    int screenshotCounter = 23;  // Start from prod_23

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/Uiproduct.html");
        extent.attachReporter(spark);

        driver.get("https://automationexercise.com/products");
    }
    

    @Test( priority=1)
    public void verifyLogo() {
        test = extent.createTest("Verify Logo Visibility");
        try {
            By logoLocator = By.xpath("//div[@class='logo pull-left']/a/img");
            WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(logoLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logo);

            Assert.assertTrue(logo.isDisplayed(), "Logo 'Automation Engineer' is not visible on the product page");

            test.pass("Logo 'Automation Engineer' is visible on the product page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (TimeoutException e) {
            test.fail("Logo 'Automation Engineer' not found: " + e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 2)
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
    @Test(priority=3)
    public void verifyHomeIconPresence() {
        test = extent.createTest("Verify Home Icon/Button Presence on Products Page");
        try {
            By homeIconLocator = By.xpath("//i[@class='fa fa-home']");
            WebElement homeIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(homeIconLocator));

            Assert.assertTrue(homeIcon.isDisplayed(), "Home icon is not visible on the products page");

            test.pass("Home icon/button is visible on the products page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Home icon/button not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail("Home icon/button not found: " + e.getMessage());
        }
    }
    @Test(priority = 4)
    public void verifyCartIconPresence() {
        test = extent.createTest("Verify Cart Icon/Button Presence on Products Page");
        try {
            By cartIconLocator = By.xpath("//a[@href='/view_cart']//i[@class='fa fa-shopping-cart']");
            WebElement cartIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(cartIconLocator));

            Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is not visible on the products page");

            test.pass("Cart icon/button is visible on the products page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Cart icon/button not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail("Cart icon/button not found: " + e.getMessage());
        }
    }
    @Test(priority = 5)
    public void verifySignupLoginIconPresence() {
        test = extent.createTest("Verify Signup / Login Icon/Button Presence on Products Page");
        try {
            // More robust XPath to target Signup/Login button/icon
            By signupLoginLocator = By.xpath("//a[contains(@href,'/login') and contains(text(),'Signup / Login')]");

            WebElement signupLoginIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(signupLoginLocator));

            Assert.assertTrue(signupLoginIcon.isDisplayed(), "Signup/Login icon is not visible on the products page");

            test.pass("Signup / Login icon/button is visible on the products page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Signup / Login icon/button not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail("Signup / Login icon/button not found: " + e.getMessage());
        }
    }
    @Test(priority = 6)
    public void verifyVideoTutorialsIconPresence() {
        test = extent.createTest("Verify Video Tutorials Icon/Button Presence on Products Page");
        try {
            // Updated XPath to target the Video Tutorials button more robustly by visible text
            By videoTutorialsLocator = By.xpath("//a[contains(text(),'Video Tutorials')]");

            WebElement videoTutorialsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(videoTutorialsLocator));

            Assert.assertTrue(videoTutorialsIcon.isDisplayed(), "Video Tutorials icon/button is not visible on the products page");

            test.pass("Video Tutorials icon/button is visible on the products page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Video Tutorials icon/button not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail("Video Tutorials icon/button not found: " + e.getMessage());
        }
    }
    @Test(priority = 7)
    public void verifyContactUsIconPresence() {
        test = extent.createTest("Verify Contact Us Icon/Button Presence on Products Page");
        try {
            // Robust XPath targeting the Contact Us button by visible text
            By contactUsLocator = By.xpath("//a[contains(text(),'Contact us')]");

            WebElement contactUsIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsLocator));

            Assert.assertTrue(contactUsIcon.isDisplayed(), "Contact Us icon/button is not visible on the products page");

            test.pass("Contact Us icon/button is visible on the products page",
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            test.fail("Contact Us icon/button not found: " + e.getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
            Assert.fail("Contact Us icon/button not found: " + e.getMessage());
        }
    }
    @Test(priority=8)
    public void verifyButtonOnProductPage() {
        test = extent.createTest("Verify Button/Icon on Product Page");

        try {
            driver.get("https://automationexercise.com");
            test.info("Launched Automation Exercise website");

            // Navigate to Products page
            driver.findElement(By.xpath("//a[@href='/products']")).click();
            test.info("Clicked on Products button");

            // Click 'View Product' for first product
            driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]")).click();
            test.info("Clicked on View Product of first product");

            // Wait for the button/icon to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@href,'/contact_us')]"))); // example button

            Assert.assertTrue(button.isDisplayed(), "Button/Icon is not visible!");
            test.pass("Button/Icon is visible on the product page");

        } catch (Exception e) {
            test.fail("Test failed due to exception: " + e.getMessage());
            takeScreenshot();
            Assert.fail(e.getMessage());
        }
    }
    @Test(priority=9)
   
 
    public void verifyAPITestIconOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        try {
            // Scroll to bottom of page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Try multiple locator strategies
            WebElement apiTestIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'API Testing')]")
            ));

            Assert.assertTrue(apiTestIcon.isDisplayed(), "API Testing icon/button is displayed.");
        } catch (TimeoutException e) {
            Assert.fail("API Testing icon/button not found on Product Page: " + e.getMessage());
        }
    }
    @Test(priority=10)
    
    public void verifyScrollBarOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Find an element expected to appear after scroll (e.g., footer or a product at the bottom)
            WebElement footer = driver.findElement(By.cssSelector("footer"));  // Or any element reliably at the bottom

            // Scroll the element into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);

            // Wait until footer is visible
            wait.until(ExpectedConditions.visibilityOf(footer));

            Assert.assertTrue(footer.isDisplayed(), "Scroll bar is working: Footer is visible after scroll.");

        } catch (Exception e) {
            Assert.fail("Scroll bar is not working: " + e.getMessage());
        }
    }
    @Test(priority=11)
    public void verifyScrollToTopButtonOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll down by 1000 pixels to make the Back to Top button appear
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");
            Thread.sleep(1000);  // Allow time for button to appear

            // Locate the "Back to Top" button
            WebElement backToTopButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("scrollUp")  // Example: Assuming the button has id 'scrollUp'; adjust if needed
            ));

            // Click the "Back to Top" button
            backToTopButton.click();
            Thread.sleep(1000);  // Wait for scroll action to complete

            // Get the current scroll position
            Long scrollAfterClick = (Long) ((JavascriptExecutor) driver).executeScript("return window.pageYOffset;");

            // Assert that the page is scrolled to top
            Assert.assertTrue(scrollAfterClick == 0, "Scroll to Top button works properly: Page scrolled back to top.");

        } catch (Exception e) {
            Assert.fail("Scroll to Top button is not working: " + e.getMessage());
        }
    }
    @Test(priority=11)
    public void verifySubscriptionIconOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Scroll to the bottom of the page where subscription section is usually present
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Wait until the subscription icon/button becomes visible
            WebElement subscriptionIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='susbscribe_email']")  // Example: input field for subscription email
            ));
            
            // Validate that the element is displayed
            Assert.assertTrue(subscriptionIcon.isDisplayed(), "Subscription icon/button is displayed on product page.");

        } catch (TimeoutException e) {
            Assert.fail("Subscription icon/button not found on product page: " + e.getMessage());
        }
    }
    @Test(priority = 12)
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
    @Test(priority=13)
    public void verifySubscriptionWithAlreadySubscribedEmailOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll to the subscription section at the bottom of the page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Locate the subscription email input field
            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("susbscribe_email")  // Adjust locator if needed
            ));

            // Enter an already subscribed email address
            emailInput.clear();
            emailInput.sendKeys("already_subscribed@example.com");  // Replace with actual test email

            // Locate and click the subscribe button
            WebElement subscribeButton = driver.findElement(By.id("subscribe"));  // Adjust locator if needed
            subscribeButton.click();

            // Wait for the expected message to appear (adjust message text according to your application)
            WebElement existingEmailMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Email Address already exist!')]")  // Example message
            ));

            // Validate that the existing email message is displayed
            Assert.assertTrue(existingEmailMessage.isDisplayed(), "Message shown for already subscribed email.");

        } catch (TimeoutException e) {
            Assert.fail("Subscription with already subscribed email validation failed: " + e.getMessage());
        }
    }
    @Test(priority=14)
    public void verifySubscriptionWithEmptyEmailOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll to subscription section
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("susbscribe_email")  // Update if needed
            ));

            // Clear the email input field
            emailInput.clear();

            WebElement subscribeButton = driver.findElement(By.id("subscribe"));  // Update locator if needed

            // Try clicking the button
            subscribeButton.click();
            Thread.sleep(1000);  // Give time for any validation to trigger

            // Option 1: Check for in-page error message
            List<WebElement> errorMessages = driver.findElements(By.xpath("//*[contains(text(),'Email Address cannot be empty')]"));

            if (!errorMessages.isEmpty() && errorMessages.get(0).isDisplayed()) {
                Assert.assertTrue(true, "Error message displayed for empty email.");
                return;
            }

            // Option 2: Check browser validation message (HTML5)
            String validationMessage = emailInput.getAttribute("validationMessage");

            Assert.assertTrue(validationMessage != null && !validationMessage.isEmpty(),
                "Validation message from browser is shown: " + validationMessage);

        } catch (Exception e) {
            Assert.fail("Subscription empty email validation failed: " + e.getMessage());
        }
    }
    @Test(priority=15)
    public void verifyBrandsIconOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll to the section where Brands are typically displayed
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Wait for the Brands icon or section to be visible
            WebElement brandsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='brands_products']")  // Example locator: adjust as per actual HTML structure
            ));

            // Validate that the brands section/icon is displayed
            Assert.assertTrue(brandsSection.isDisplayed(), "Brands icon/section is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("Brands icon/section not found on the product page: " + e.getMessage());
        }
    }
    @Test(priority=16)
    public void verifyCategoryIconOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Scroll to the category section
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Wait for the Category icon or section to be visible
            WebElement categorySection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='left-sidebar']")  // Example locator: adjust as per actual HTML structure
            ));

            // Validate that the category section/icon is displayed
            Assert.assertTrue(categorySection.isDisplayed(), "Category icon/section is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("Category icon/section not found on the product page: " + e.getMessage());
        }
    }
    @Test(priority=17)
   
   
    public void verifyAllProductsSectionOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1 – Navigate to the Products page
            driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();

            // Step 2 – Wait for full page load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Step 3 – Verify we are on the correct URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/products"), "Expected to be on the Products page.");

            // Step 4 – Scroll a bit to trigger lazy-loading (if any)
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            Thread.sleep(1000);

            // Step 5 – Validate "All Products" section header is visible
            WebElement allProductsHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'All Products')]")
            ));

            Assert.assertTrue(allProductsHeading.isDisplayed(), "'All Products' section is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("'All Products' section not found or not visible: " + e.getMessage());
        } catch (InterruptedException e) {
            Assert.fail("Interrupted while waiting: " + e.getMessage());
        }
    }
    @Test(priority=18)
    public void verifySearchBarOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1 – Navigate to the Products page
            driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();

            // Step 2 – Wait for full page load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Step 3 – Verify we are on the correct URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/products"), "Expected to be on the Products page.");

            // Step 4 – Wait for the search input field to be visible
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("search_product")  // Example locator, commonly used id for search input
            ));

            // Validate that the search input field is displayed
            Assert.assertTrue(searchInput.isDisplayed(), "Search bar (input field) is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("Search bar (input field) not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=19)
    public void verifySearchButtonOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1 – Navigate to the Products page
            driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();

            // Step 2 – Wait for full page load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Step 3 – Verify we are on the correct URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/products"), "Expected to be on the Products page.");

            // Step 4 – Wait for the search button to be visible
            WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("submit_search")  // Example locator; commonly used id for search button
            ));

            // Validate that the search button is displayed
            Assert.assertTrue(searchButton.isDisplayed(), "Search button is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("Search button not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=20)
    public void verifyAddToCartButtonOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1 – Navigate to the Products page
            driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();

            // Step 2 – Wait for full page load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Step 3 – Verify we are on the correct URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/products"), "Expected to be on the Products page.");

            // Step 4 – Scroll a bit to ensure elements load
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            Thread.sleep(1000);

            // Step 5 – Wait for the "Add to Cart" button of first product to be visible
            WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[contains(@class,'add-to-cart')])[1]")  // Example XPath to first product's Add to Cart button
            ));

            // Validate that the "Add to Cart" button is displayed
            Assert.assertTrue(addToCartButton.isDisplayed(), "'Add to Cart' button is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("'Add to Cart' button not found or not visible: " + e.getMessage());
        } catch (InterruptedException e) {
            Assert.fail("Interrupted while waiting: " + e.getMessage());
        }
    }
    @Test(priority=21)
    public void verifyViewProductButtonOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 1 – Navigate to the Products page
            driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();

            // Step 2 – Wait for full page load
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

            // Step 3 – Verify we are on the correct URL
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("/products"), "Expected to be on the Products page.");

            // Step 4 – Scroll a bit to ensure all elements load
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
            Thread.sleep(1000);

            // Step 5 – Wait for the "View Product" button of first product to be visible
            WebElement viewProductButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//a[contains(text(),'View Product')])[1]") // XPath for the first product's View Product button
            ));

            // Validate that the "View Product" button is displayed
            Assert.assertTrue(viewProductButton.isDisplayed(), "'View Product' button is present on the product page.");

        } catch (TimeoutException e) {
            Assert.fail("'View Product' button not found or not visible: " + e.getMessage());
        } catch (InterruptedException e) {
            Assert.fail("Interrupted while waiting: " + e.getMessage());
        }
    }
    @Test(priority=22)
    public void verifyCategorySectionWomenOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Navigate to Products page
        driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

        try {
            // Wait for the WOMEN category link (case-insensitive)
            WebElement womenCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='left-sidebar']//a[contains(translate(@href,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'women')]")
            ));

            // Scroll into view if needed
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", womenCategory);

            Assert.assertTrue(womenCategory.isDisplayed(), "'WOMEN' category is visible under CATEGORY section.");

        } catch (TimeoutException e) {
            // Optional: take screenshot here
            Assert.fail("'CATEGORY' section or 'WOMEN' category not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=23)
    public void verifyCategorySectionMenOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Navigate to Products page
        driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

        try {
            // Wait for the MEN category link (case-insensitive)
            WebElement menCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='left-sidebar']//a[contains(translate(@href,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'men')]")
            ));

            // Scroll into view if needed
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menCategory);

            Assert.assertTrue(menCategory.isDisplayed(), "'MEN' category is visible under CATEGORY section.");

        } catch (TimeoutException e) {
            Assert.fail("'CATEGORY' section or 'MEN' category not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=24)
    public void verifyCategorySectionKidsOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Navigate to Products page
        driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

        try {
            // Wait for the KIDS category link (case-insensitive)
            WebElement kidsCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='left-sidebar']//a[contains(translate(@href,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'kids')]")
            ));

            // Scroll into view if needed
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", kidsCategory);

            Assert.assertTrue(kidsCategory.isDisplayed(), "'KIDS' category is visible under CATEGORY section.");

        } catch (TimeoutException e) {
            Assert.fail("'CATEGORY' section or 'KIDS' category not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=25)
    public void verifyBrandsSectionLinksOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Navigate to Products page
        driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));

        try {
            // Wait for the BRANDS section container
            WebElement brandsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".brands_products")
            ));

            // Get all brand links under BRANDS section
            List<WebElement> brandLinks = brandsSection.findElements(By.tagName("a"));

            // Check that each brand link is displayed
            for (WebElement brand : brandLinks) {
                Assert.assertTrue(brand.isDisplayed(), "Brand link '" + brand.getText() + "' is visible in BRANDS section.");
            }

            // Optional: fail if no brands are found
            Assert.assertFalse(brandLinks.isEmpty(), "No brand links found in BRANDS section.");

        } catch (TimeoutException e) {
            Assert.fail("'BRANDS' section or brand links not found or not visible: " + e.getMessage());
        }
    }
    @Test(priority=26)
   
    public void verifyCategoryToggleIconsOnProductPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Step 1 & 2: Open the Products page
        driver.findElement(By.xpath("//a[contains(@href,'/products')]")).click();
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        // Categories to check
        String[] categories = {"Women", "Men", "Kids"};

        try {
            WebElement panelGroup = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".panel-group.category-products")));

            List<WebElement> panels = panelGroup.findElements(By.cssSelector(".panel.panel-default"));

            for (String category : categories) {
                boolean found = false;

                for (WebElement panel : panels) {
                    WebElement link = panel.findElement(By.tagName("a"));
                    if (link.getText().trim().equalsIgnoreCase(category)) {
                        found = true;
                        Assert.assertTrue(link.isDisplayed(), category + " category is visible.");

                        // Verify the (+) toggle icon inside <a>
                        WebElement toggle = link.findElement(By.cssSelector("span.pull-right"));
                        Assert.assertTrue(toggle.isDisplayed(), "(+) toggle icon is displayed for " + category);
                        break;
                    }
                }

                if (!found) {
                    Assert.fail(category + " category not found in CATEGORY section.");
                }
            }

        } catch (TimeoutException | NoSuchElementException e) {
            Assert.fail("'CATEGORY' section or (+) toggle icons not found: " + e.getMessage());
        }
    }
    public String takeScreenshot() {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String filename = String.format("uiproduct_%d.png", screenshotCounter++);
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