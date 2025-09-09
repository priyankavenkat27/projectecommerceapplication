package com.ecom.tests;

import com.ecom.base.Baseclass;
import com.ecom.Pages.Cartpage;
import com.ecom.Pages.ProductPage;
import com.ecom.Pages.signup_login_page;
import com.ecom.utilities.screenshotutilities;
import com.aventstack.extentreports.Status;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class cart_functionality_testcases extends Baseclass {

    private void handleFailure(String testName, Exception e) {
        test.log(Status.FAIL, e.getMessage());
        try {
            String path = screenshotutilities.capturescreen(driver, testName);
            test.addScreenCaptureFromPath(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Assert.fail(e.getMessage());
    }
    
    
    public void closeOverlaysIfPresent() {
        try {
            List<WebElement> overlays = driver.findElements(By.cssSelector(".overlay, .modal, iframe"));
            for (WebElement overlay : overlays) {
                if (overlay.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='none';", overlay);
                }
            }
        } catch (Exception e) {
            System.out.println("No overlays found: " + e.getMessage());
        }
    }

    public void ensureCartHasItem() {
        try {
            driver.get(config.getProperty("baseUrl") + "/product_details/1");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn.btn-default.cart")
            ));

            // Close overlays if any
            closeOverlaysIfPresent();

            addToCartBtn.click();

            // Close modal
            WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".btn.btn-success.close-modal")
            ));
            continueShopping.click();
        } catch (Exception e) {
            System.out.println("Failed to add item to cart: " + e.getMessage());
        }
    }


    @Test(priority = 1)
    public void TC_ECOM_Cart_001() {
        test = extent.createTest("TC_ECOM_Cart_001 - Verify cart icon/button is available and working on home page");
        try {
            WebElement cartIcon = driver.findElement(By.cssSelector("a[href='/view_cart']"));
            Assert.assertTrue(cartIcon.isDisplayed(), "Cart icon is not displayed");
            cartIcon.click();
            Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Did not navigate to cart page");
            test.log(Status.PASS, "Cart icon is working as expected");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_001", e);
        }
    }

    @Test(priority = 2)
    public void TC_ECOM_Cart_002() {
        test = extent.createTest("TC_ECOM_Cart_002 - Verify Home breadcrumb navigation");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);

            // Close overlays if any
            closeOverlaysIfPresent();

            // Click Home breadcrumb
            cartPage.clickHomeBreadcrumb();

            // Verify navigation to home page
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.equals(config.getProperty("baseUrl") + "/"),
                    "Navigation to home page failed!");

            test.log(Status.PASS, "Navigated to home page successfully using Home breadcrumb");

        } catch (Exception e) {
            test.log(Status.FAIL, "Home breadcrumb navigation test failed: " + e.getMessage());
            try {
                if (driver != null) {
                    String path = screenshotutilities.capturescreen(driver, "TC_ECOM_Cart_002");
                    test.addScreenCaptureFromPath(path);
                }
            } catch (Exception ex) {
                test.log(Status.WARNING, "Screenshot skipped: " + ex.getMessage());
            }
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3)
    public void TC_ECOM_Cart_003() {
        test = extent.createTest("TC_ECOM_Cart_003 - Verify shopping cart breadcrumb navigation icon/button on cart page");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);
            cartPage.clickCartBreadcrumb();
            Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Did not stay on cart page");
            test.log(Status.PASS, "Shopping cart breadcrumb navigation icon is working");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_003", e);
        }
    }

    @Test(priority = 4)
    public void TC_ECOM_Cart_004() {
        test = extent.createTest("TC_ECOM_Cart_004 - Verify empty cart 'here' anchor tag/hyperlink on cart page");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Optional: Ensure cart is empty
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();

            // Correct XPath for <a><u>here</u></a>
            By hereLinkBy = By.xpath("//a/u[text()='here']");

            // Wait until visible
            WebElement hereLink = wait.until(ExpectedConditions.visibilityOfElementLocated(hereLinkBy));
            Assert.assertTrue(hereLink.isDisplayed(), "'here' link is not displayed");

            // Scroll into view safely
            js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", hereLink);

            // Wait until clickable and click
            wait.until(ExpectedConditions.elementToBeClickable(hereLink)).click();

            // Verify navigation to products page
            wait.until(ExpectedConditions.urlContains("/products"));
            Assert.assertTrue(driver.getCurrentUrl().contains("/products"), "Did not navigate to product page");

            test.log(Status.PASS, "'here' anchor tag navigates to product page successfully");

        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_004", e);
        }
    }


    @Test(priority = 5)
    public void TC_ECOM_Cart_005() {
        test = extent.createTest("TC_ECOM_Cart_005 - Verify delete icon works");
        try {
            driver.get(config.getProperty("baseUrl") + "/product_details/1");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn.btn-default.cart")
            ));
            addToCartBtn.click();

            WebElement continueShopping = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success.close-modal"))
            );
            continueShopping.click();

            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);

            Assert.assertTrue(cartPage.isDeleteIconPresent(), "Delete icon not present");

            // call your page method
            cartPage.clickDeleteIcon();

            // wait until cart item disappears
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".cart_quantity_delete")
            ));

            test.log(Status.PASS, "Delete icon removes item successfully");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_005", e);
        }
    }

    @Test(priority = 6)
    public void TC_ECOM_Cart_006() {
        test = extent.createTest("TC_ECOM_Cart_006 - Verify Proceed to Checkout button works");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1️⃣ Go to products page
            driver.get(config.getProperty("baseUrl") + "/products");

            // 2️⃣ Add first product to cart
            WebElement addToCartBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]"))
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartBtn);
            addToCartBtn.click();
            test.log(Status.PASS, "Clicked 'Add to cart'");

            // 3️⃣ Handle modal -> Click Continue Shopping
            WebElement continueShoppingBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))
            );
            continueShoppingBtn.click();
            test.log(Status.INFO, "Closed Add to Cart modal by clicking Continue Shopping");

            // 4️⃣ Now go to cart page
            driver.get(config.getProperty("baseUrl") + "/view_cart");

            // 5️⃣ Wait until product is visible in cart
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));
            test.log(Status.PASS, "Product is visible in cart");

            // 6️⃣ Click Proceed to Checkout
            WebElement proceedBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("a.btn.btn-default.check_out")));
            try {
                proceedBtn.click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", proceedBtn);
            }

            // 7️⃣ Verify next page
            try {
                // Case 1: Checkout page loads
                WebElement checkoutHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h2.title.text-center")));
                Assert.assertTrue(checkoutHeading.isDisplayed(), "Checkout page did not load");
                test.log(Status.PASS, "Checkout page loaded successfully");
            } catch (TimeoutException e) {
                // Case 2: Register/Login modal shows up
                WebElement registerLoginLink = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.linkText("Register / Login")));
                Assert.assertTrue(registerLoginLink.isDisplayed(), "Register/Login modal did not appear");
                test.log(Status.PASS, "Register/Login alert appeared after Proceed to Checkout");
            }

        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_006", e);
        }
    }


    @Test(priority = 7)
    public void TC_ECOM_Cart_007() {
        test = extent.createTest("TC_ECOM_Cart_007 - Verify Register/Login alert on checkout when NOT logged in");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1️⃣ Go to products page
            driver.get(config.getProperty("baseUrl") + "/products");

            // 2️⃣ Add first product to cart
            WebElement addToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]"))
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", addToCart);
            addToCart.click();
            test.log(Status.PASS, "Clicked 'Add to cart'");

            // 3️⃣ Handle modal -> Click Continue Shopping
            WebElement continueShoppingBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Continue Shopping']"))
            );
            continueShoppingBtn.click();
            test.log(Status.INFO, "Closed Add to Cart modal by clicking Continue Shopping");

            // 4️⃣ Now go to cart page
            driver.get(config.getProperty("baseUrl") + "/view_cart");

            // 5️⃣ Ensure product is present in cart
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tbody tr")));
            test.log(Status.PASS, "Product is visible in cart");

            // 6️⃣ Click Proceed to Checkout
            WebElement proceedBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default.check_out"))
            );
            try {
                proceedBtn.click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", proceedBtn);
            }

            // 7️⃣ Verify Register/Login modal appears
            WebElement registerLoginLink = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.linkText("Register / Login"))
            );
            Assert.assertTrue(registerLoginLink.isDisplayed(), "Register/Login modal did not appear");
            test.log(Status.PASS, "Register/Login modal appeared successfully");

        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_007", e);
        }
    }


    @Test(priority = 8)
    public void TC_ECOM_Cart_008() throws IOException {
        test = extent.createTest("TC_ECOM_Cart_008 - Verify register/signup while clicking proceed to checkout button");
        try {
            // Step 0: Ensure cart has at least 1 item
            ensureCartHasItem();

            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);

            // Close overlays if present
            closeOverlaysIfPresent();

            // Scroll to Proceed to Checkout
            WebElement checkoutBtn = cartPage.getProceedToCheckoutBtn();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);

            cartPage.clickProceedToCheckout();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement registerLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register / Login")));
            registerLoginLink.click();

            // 🔹 Fill signup name & email
            driver.findElement(By.name("name")).sendKeys("zebuuu");
            driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("zebuu" + System.currentTimeMillis() + "@gmail.com");
            driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

            // 🔹 Fill account details (sample values)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender1"))).click(); // Mr.
            driver.findElement(By.id("password")).sendKeys("Password123");
            driver.findElement(By.id("days")).sendKeys("10");
            driver.findElement(By.id("months")).sendKeys("May");
            driver.findElement(By.id("years")).sendKeys("1995");

            driver.findElement(By.id("first_name")).sendKeys("Zebuuu");
            driver.findElement(By.id("last_name")).sendKeys("Test");
            driver.findElement(By.id("address1")).sendKeys("123 Street, City");
            driver.findElement(By.id("country")).sendKeys("India");
            driver.findElement(By.id("state")).sendKeys("TN");
            driver.findElement(By.id("city")).sendKeys("Chennai");
            driver.findElement(By.id("zipcode")).sendKeys("600001");
            driver.findElement(By.id("mobile_number")).sendKeys("9876543210");

            // 🔹 Click Create Account
            driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();

            // 🔹 Wait for success & click Continue
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-qa='continue-button']")));
            continueBtn.click();

            // 🔹 Back to cart and proceed to checkout
            driver.get(config.getProperty("baseUrl") + "/view_cart");

            checkoutBtn = cartPage.getProceedToCheckoutBtn();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
            cartPage.clickProceedToCheckout();

            Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Did not navigate to checkout area");
            test.log(Status.PASS, "Register/signup while clicking proceed to checkout works as expected");

        } catch (Exception e) {
            test.log(Status.FAIL, "Register/signup while clicking proceed to checkout test failed: " + e.getMessage());
            String path = screenshotutilities.capturescreen(driver, "TC_ECOM_Cart_008");
            test.addScreenCaptureFromPath(path);
            Assert.fail(e.getMessage());
        }
    }


@Test(priority = 9)
public void TC_ECOM_Cart_009() throws IOException {
    test = extent.createTest("TC_ECOM_Cart_009 - Verify scroll bar is working properly on cart page");
    try {
        driver.get(config.getProperty("baseUrl") + "/view_cart");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 250);");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0, -250);");
        test.log(Status.PASS, "Scroll bar is working properly");
    } catch (Exception e) {
        test.log(Status.FAIL, "Scroll bar test failed: " + e.getMessage());
        String path = screenshotutilities.capturescreen(driver, "TC_ECOM_Cart_009");
        test.addScreenCaptureFromPath(path);
        Assert.fail(e.getMessage());
    }
}

@Test(priority = 10)
public void TC_ECOM_Cart_010() {
    test = extent.createTest("TC_ECOM_Cart_010 - Verify scroll up button works");
    try {
        driver.get(config.getProperty("baseUrl"));
        
        // Scroll down
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement scrollUpBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("scrollUp")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", scrollUpBtn);

        wait.until(ExpectedConditions.invisibilityOf(scrollUpBtn));
        test.log(Status.PASS, "Scroll up button is working as expected");
    } catch (Exception e) {
        handleFailure("TC_ECOM_Cart_010", e);
    }
}


@Test(priority = 11)
public void TC_ECOM_Cart_011() throws IOException {
    test = extent.createTest("TC_ECOM_Cart_011 - Verify subscription icon/button is working on cart page");
    try {
        driver.get(config.getProperty("baseUrl") + "/view_cart");
        Cartpage cartPage = new Cartpage(driver);
        Assert.assertTrue(cartPage.isSubscriptionIconPresent(), "Subscription icon is not displayed");
        cartPage.clickSubscribe();
        test.log(Status.PASS, "Subscription icon is working on cart page");
    } catch (Exception e) {
        test.log(Status.FAIL, "Subscription icon test failed: " + e.getMessage());
        String path = screenshotutilities.capturescreen(driver, "TC_ECOM_Cart_011");
        test.addScreenCaptureFromPath(path);
        Assert.fail(e.getMessage());
    }
}

@Test(priority = 12)
public void TC_ECOM_Cart_012() throws IOException {
    test = extent.createTest("TC_ECOM_Cart_012 - Verify subscription with valid email");
    try {
        driver.get(config.getProperty("baseUrl") + "/view_cart");
        Cartpage cartPage = new Cartpage(driver);

        cartPage.enterSubscriptionEmail("abc@gmail.com");
        cartPage.clickSubscribe();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement messageElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("success-subscribe"))
        );

        String actualMessage = messageElement.getText();
        Assert.assertTrue(actualMessage.toLowerCase().contains("successfully"),
            "Expected success message not shown. Actual: " + actualMessage);

        test.log(Status.PASS, "Valid subscription works");
    } catch (Exception e) {
        handleFailure("TC_ECOM_Cart_012", e);
    }
}


@Test(priority = 13)
public void TC_ECOM_Cart_013() {
    test = extent.createTest("TC_ECOM_Cart_013 - Verify subscription with invalid email");
    try {
        driver.get(config.getProperty("baseUrl") + "/view_cart");
        Cartpage cartPage = new Cartpage(driver);

        // Enter invalid email
        cartPage.enterSubscriptionEmail("hgdkhg");
        
        // Trigger validation
        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        cartPage.clickSubscribe();
        String validationMsg = emailField.getAttribute("validationMessage");

        Assert.assertTrue(validationMsg.toLowerCase().contains("include an '@'")
                || validationMsg.toLowerCase().contains("valid email"),
            "Expected invalid email validation. Actual: " + validationMsg);

        test.log(Status.PASS, "Invalid email shows browser validation message: " + validationMsg);
    } catch (Exception e) {
        handleFailure("TC_ECOM_Cart_013", e);
    }
}



@Test(priority = 14)
public void TC_ECOM_Cart_014() {
    test = extent.createTest("TC_ECOM_Cart_014 - Verify subscription with empty email");
    try {
        driver.get(config.getProperty("baseUrl") + "/view_cart");
        Cartpage cartPage = new Cartpage(driver);

        // Enter empty email
        cartPage.enterSubscriptionEmail("");
        
        // Trigger validation
        WebElement emailField = driver.findElement(By.id("susbscribe_email"));
        cartPage.clickSubscribe();
        String validationMsg = emailField.getAttribute("validationMessage");

        Assert.assertTrue(validationMsg.toLowerCase().contains("fill out this field")
                || validationMsg.toLowerCase().contains("email"),
            "Expected required field validation. Actual: " + validationMsg);

        test.log(Status.PASS, "Empty email shows browser validation message: " + validationMsg);
    } catch (Exception e) {
        handleFailure("TC_ECOM_Cart_014", e);
    }
}


    @Test(priority = 15)
    public void TC_ECOM_Cart_015() {
        test = extent.createTest("TC_ECOM_Cart_015 - Verify subscription icon with no data on email address text box on cart page");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);

            // Leave email empty
            WebElement emailInput = driver.findElement(By.id("susbscribe_email")); // <-- confirm correct ID!
            emailInput.clear();
            cartPage.clickSubscribe();

            // Get HTML5 validation message
            String validationMessage = emailInput.getAttribute("validationMessage");

            // Assertion (case-insensitive to handle browser variations)
            Assert.assertTrue(validationMessage.toLowerCase().contains("please fill out this field"),
                    "Expected validation message not shown. Actual: " + validationMessage);

            test.log(Status.PASS, "Subscription with empty email shows proper validation message");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_015", e);
        }
    }


    @Test(priority = 16)
    public void TC_ECOM_Cart_016() {
        test = extent.createTest("TC_ECOM_Cart_016 - Verify Home button works");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);

            cartPage.clickHomeBreadcrumb();

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.startsWith("https://automationexercise.com"),
                "Did not navigate to home page. Actual: " + currentUrl);

            test.log(Status.PASS, "Home button navigation works");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_016", e);
        }
    }


    @Test(priority = 17)
    public void TC_ECOM_Cart_017() {
        test = extent.createTest("TC_ECOM_Cart_017 - Verify shopping cart breadcrumb navigation icon/button on cart page");
        try {
            driver.get(config.getProperty("baseUrl") + "/view_cart");
            Cartpage cartPage = new Cartpage(driver);
            cartPage.clickCartBreadcrumb();
            Assert.assertTrue(driver.getCurrentUrl().contains("view_cart"), "Did not stay on cart page");
            test.log(Status.PASS, "Shopping cart breadcrumb navigation icon is working");
        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_017", e);
        }
    }

    @Test(priority = 18)
    public void TC_ECOM_Cart_018() {
        test = extent.createTest("TC_ECOM_Cart_018 - Verify comments field on cart page");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // 1️⃣ Login
            driver.get(config.getProperty("baseUrl") + "/login");
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("daryl@gmail.com");
            loginPage.enterLoginPassword("daryl123");
            loginPage.clickLoginButton();
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");

            // 2️⃣ Go to products page
            driver.get(config.getProperty("baseUrl") + "/products");

            // 3️⃣ Add first product to cart
            WebElement addToCartBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Add to cart'])[1]"))
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartBtn);
            js.executeScript("arguments[0].click();", addToCartBtn);
            test.log(Status.PASS, "First product added to cart");

            // 4️⃣ Go to cart page
            driver.get(config.getProperty("baseUrl") + "/view_cart");

            // 5️⃣ Click 'Proceed to Checkout'
            WebElement proceedBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Proceed To Checkout')]"))
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", proceedBtn);
            js.executeScript("arguments[0].click();", proceedBtn);

            // 6️⃣ Enter comment in comments field
            WebElement commentBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ordermsg']/textarea"))
            );
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", commentBox);

            String commentText = "Good product - please deliver fast!";
            js.executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input'));", 
                commentBox, commentText
            );

            // ✅ Validate text really went in
            Assert.assertEquals(commentBox.getAttribute("value"), commentText, "Comment not entered correctly");
            test.log(Status.PASS, "User can enter comments successfully");

            // 7️⃣ Place Order button
            WebElement placeOrderBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Place Order']"))
            );
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrderBtn);
            js.executeScript("arguments[0].click();", placeOrderBtn);

            test.log(Status.PASS, "Order placed successfully");

        } catch (Exception e) {
            handleFailure("TC_ECOM_Cart_018", e);
        }
    }

}