package com.ecom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ecom.base.Baseclass;
import com.ecom.Pages.*;
import com.ecom.utilities.screenshotutilities;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.io.IOException;
import java.time.Duration;

public class high_priority_testcases extends Baseclass {

    // 🔹 TC1 - Verify Signup button
    @Test(groups = {"High_Risk_Priority"})
    public void TC001_VerifySignupButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_001", "Verify Signup button is working on Signup/Login page");
        try {
            signup_login_page signupPage = new signup_login_page(driver);
            signupPage.clickSignupLoginLink();
            String email = "priya" + System.currentTimeMillis() + "@test.com";
            signupPage.enterSignupDetails("PriyaTest", email);

            Assert.assertTrue(driver.getPageSource().contains("Enter Account Information"));
            test.log(Status.PASS, "Signup successful with email: " + email);

        } catch (Exception e) {
            captureFailure("SignupException", e);
        }
    }

    // 🔹 TC2 - Verify Logout button
    @Test(groups = {"High_Risk_Priority"})
    public void TC002_VerifyLogoutButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_002", "Verify Logout button works after login");
        try {
            driver.get(config.getProperty("baseUrl") + "/login");
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.enterLoginEmail("priyankavenk27@gmail.com");
            loginPage.enterLoginPassword("hello@27");
            loginPage.clickLoginButton();

            Assert.assertTrue(driver.getPageSource().contains("Logged in as"));
            driver.findElement(By.linkText("Logout")).click();

            Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
            test.log(Status.PASS, "Logout successful");

        } catch (Exception e) {
            captureFailure("LogoutException", e);
        }
    }

    // 🔹 TC3 - Verify Search button
    @Test(groups = {"High_Risk_Priority"})
    public void TC003_VerifySearchButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_003", "Verify search button on product page");
        try {
            ProductPage productPage = new ProductPage(driver);
            productPage.clickProductsLink();
            productPage.searchProduct("T-Shirt");

            String firstProduct = productPage.getFirstProductName();
            Assert.assertTrue(firstProduct.toLowerCase().contains("t-shirt"));
            test.log(Status.PASS, "Search worked. Found: " + firstProduct);

        } catch (Exception e) {
            captureFailure("SearchException", e);
        }
    }

    // 🔹 TC4 - Verify Add to Cart button
    @Test(groups = {"High_Risk_Priority"})
    public void TC004_VerifyAddToCartButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_004", "Verify 'Add to Cart' button");
        try {
            ProductPage productPage = new ProductPage(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement productsLink = wait.until(ExpectedConditions.elementToBeClickable(productPage.productsLinkLocator()));
            productsLink.click();

            WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(productPage.firstAddToCartLocator()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartBtn);
            addToCartBtn.click();

            test.log(Status.PASS, "Product added to cart successfully");

        } catch (Exception e) {
            captureFailure("AddToCartException", e);
        }
    }

    // 🔹 TC5 - Verify Checkout button 
    @Test(groups = {"High_Risk_Priority"})
    public void TC005_VerifyCheckoutButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_005", "Verify checkout button works");
        try {
            driver.findElement(By.partialLinkText("Products")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Add product
            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[text()='Add to cart'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
            addToCart.click();

            // Close modal if it appears
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Continue Shopping']")));
                continueBtn.click();
            } catch (Exception ignored) {
                test.log(Status.INFO, "No modal appeared after adding product");
            }

            // Navigate to Cart
            driver.findElement(By.linkText("Cart")).click();

            // Wait for checkout button
            WebElement checkoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[contains(text(),'Proceed To Checkout')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
            checkoutBtn.click();

            test.log(Status.PASS, "Checkout button worked successfully");

        } catch (Exception e) {
            captureFailure("CheckoutException", e);
        }
    }

    // 🔹 TC6 - Verify Delete icon 
    @Test(groups = {"High_Risk_Priority"})
    public void TC006_VerifyDeleteIcon() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_006", "Verify delete icon in cart");
        try {
            driver.findElement(By.partialLinkText("Products")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Add product
            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("(//a[text()='Add to cart'])[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
            addToCart.click();

            // Close modal if it appears
            try {
                WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[text()='Continue Shopping']")));
                continueBtn.click();
            } catch (Exception ignored) {
                test.log(Status.INFO, "No modal appeared after adding product");
            }

            // Navigate to Cart
            driver.findElement(By.linkText("Cart")).click();

            // Wait for delete button
            WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("a.cart_quantity_delete[data-product-id='1']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
            deleteBtn.click();

            // Verify cart empty
            wait.until(ExpectedConditions.invisibilityOf(deleteBtn));
            Assert.assertEquals(driver.findElements(By.xpath("//tr[contains(@id,'product')]")).size(), 0);

            test.log(Status.PASS, "Delete icon worked, cart is empty");

        } catch (Exception e) {
            captureFailure("DeleteCartException", e);
        }
    }

    // 🔹 TC7 - Verify Contact Us
    @Test(groups = {"High_Risk_Priority"})
    public void TC007_VerifyContactUsForm() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_007", "Verify Contact Us form");
        try {
            Contactuspage contactPage = new Contactuspage(driver);
            contactPage.clickContactUsLink();
            contactPage.enterName("maria");
            contactPage.enterEmail("maria@gmail.com");
            contactPage.enterMessage("Cant able to view product review");

            test.log(Status.PASS, "Contact form details entered successfully");

        } catch (Exception e) {
            captureFailure("ContactUsException", e);
        }
    }

    // 🔹 TC8 - Verify Submit button (with Alert handling FIXED)
    @Test(groups = {"High_Risk_Priority"})
    public void TC008_VerifySubmitButton() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_008", "Verify Submit button works");
        try {
            driver.get(config.getProperty("baseUrl") + "/contact_us");
            Contactuspage contactPage = new Contactuspage(driver);
            contactPage.enterName("bonnie");
            contactPage.enterEmail("bonnie@gmail.com");
            contactPage.enterSubject("Product Issue");
            contactPage.enterMessage("Cant able to view product review");
            contactPage.clickSubmit();

            // Handle alert if appears
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();
                test.log(Status.INFO, "Alert accepted after submit");
            } catch (Exception ignored) {}

            String successMsg = contactPage.getSuccessMsg();
            Assert.assertTrue(successMsg.contains("Success"));
            test.log(Status.PASS, "Submit button worked. Message: " + successMsg);

        } catch (Exception e) {
            captureFailure("SubmitException", e);
        }
    }

    // 🔹 TC9 - Verify Subscription icon
    @Test(groups = {"High_Risk_Priority"})
    public void TC009_VerifySubscriptionIcon() {
        test = extent.createTest("TC_Reg_High_Risk_Prior_009", "Verify subscription icon");
        try {
            HomePage home = new HomePage(driver);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            home.enterSubscriptionEmail("kaviyanjali17@gmail.com");
            home.clickSubscribe();

            String msg = home.getSuccessMessage();
            Assert.assertTrue(msg.contains("successfully"));
            test.log(Status.PASS, "Subscription successful. Message: " + msg);

        } catch (Exception e) {
            captureFailure("SubscriptionException", e);
        }
    }

    // 🔹 Utility Method for Failures
    private void captureFailure(String screenshotName, Exception e) {
        try {
            test.log(Status.FAIL, "Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(screenshotutilities.capturescreen(driver, screenshotName));
        } catch (IOException io) {
            test.log(Status.FAIL, "Screenshot capture failed: " + io.getMessage());
        }
        Assert.fail("Test failed: " + e.getMessage());
    }
}
