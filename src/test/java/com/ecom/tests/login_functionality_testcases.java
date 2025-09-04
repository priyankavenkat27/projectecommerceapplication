package com.ecom.tests;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ecom.base.Baseclass;
import com.ecom.Pages.signup_login_page;
import com.ecom.utilities.screenshotutilities;
import com.aventstack.extentreports.Status;

public class login_functionality_testcases extends Baseclass {

    // Test 1: Verify URL launch
    @Test(priority = 1, groups = {"loginTests"})
    public void Tc_Ecom_Login_01_verifyUrlLaunch() {
        test = extent.createTest("Tc_Ecom_Login_01 - Verify URL launch");
        try {
            driver.get(config.getProperty("baseUrl"));
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("automationexercise"), "URL did not launch correctly!");
            test.pass("URL launched successfully: " + currentUrl);
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_01", e);
        }
    }

    // Test 2: Verify home page displayed
    @Test(priority = 2, groups = {"loginTests"})
    public void Tc_Ecom_Login_02_verifyHomePageDisplayed() {
        test = extent.createTest("Tc_Ecom_Login_02 - Verify home page displayed");
        try {
            String title = driver.getTitle();
            Assert.assertTrue(title.contains("Automation Exercise"), "Home page not loaded!");
            test.pass("Home page loaded successfully with title: " + title);
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_02", e);
        }
    }

    // Test 3: Navigate to Login/Signup page
    @Test(priority = 3, groups = {"loginTests"})
    public void Tc_Ecom_Login_03_navigateToLoginSignupPage() {
        test = extent.createTest("Tc_Ecom_Login_03 - Navigate to Login/Signup page");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Login/Signup page not opened!");
            test.pass("Navigated to Login/Signup page successfully.");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_03", e);
        }
    }

    // Test 4: Verify 'Login to your account' text
    @Test(priority = 4, groups = {"loginTests"})
    public void Tc_Ecom_Login_04_verifyLoginToYourAccountText() {
        test = extent.createTest("Tc_Ecom_Login_04 - Verify 'Login to your account' text");
        try {
        		signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[text()='Login to your account']")));
            Assert.assertTrue(loginText.isDisplayed(), "'Login to your account' text not found!");
            test.pass("'Login to your account' text is visible");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_04", e);
        }
    }

    // Test 5: Verify valid email and password can be entered
    @Test(priority = 5, groups = {"loginTests"})
    public void Tc_Ecom_Login_05_enterValidEmailPassword() {
        test = extent.createTest("Tc_Ecom_Login_05 - Enter valid email and password");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("test@gmail.com");
            loginPage.enterLoginPassword("Test@123");
            test.pass("Valid email and password entered successfully");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_05", e);
        }
    }

    // Test 6: Login with valid credentials
    @Test(priority = 6, groups = {"loginTests"})
    public void Tc_Ecom_Login_06_loginWithValidCredentials() {
        test = extent.createTest("Tc_Ecom_Login_06 - Login with valid credentials");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("viji514@gmail.com");
            loginPage.enterLoginPassword("viji514");
            loginPage.clickLoginButton();
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            test = extent.createTest("Tc_Ecom_Login_07 - Logged in as text visible");
            WebElement loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Logged in as')]")));
            Assert.assertTrue(loggedInText.isDisplayed(), "'Logged in as' text not visible");
            test.pass("Logged in successfully with valid credentials.");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_06", e);
        }
    }
 // Test 7: Verify 'Logged in as username' text
    @Test(priority = 7, groups = {"loginTests"})
    public void Tc_Ecom_Login_07_verifyLoggedInAsUserText() {
        test = extent.createTest("Tc_Ecom_Login_07 - Logged in as text visible");
        try {
        	signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("viji514@gmail.com");
            loginPage.enterLoginPassword("viji514");
            loginPage.clickLoginButton();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Logged in as')]")));

            Assert.assertTrue(loggedInText.isDisplayed(), "'Logged in as' text not visible");
            test.pass("Logged in successfully, 'Logged in as' text is visible.");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_07", e);  
        }
    }

    // Test 8: Login with invalid credentials
    @Test(priority = 8, groups = {"loginTests"})
    public void Tc_Ecom_Login_07_loginWithInvalidCredentials() {
        test = extent.createTest("Tc_Ecom_Login_07 - Login with invalid credentials");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            loginPage.enterLoginEmail("testt@gmail.com");
            loginPage.enterLoginPassword("Testhdfkjgfdkhjl123");
            loginPage.clickLoginButton();
            
            WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//p[contains(text(),'Your email or password is incorrect!')]")
            ));

            Assert.assertTrue(errorMsg.isDisplayed(), "Expected validation message not displayed for invalid credentials");

            
            test.pass("Validation message displayed for invalid credentials.");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_08", e);
        }
    }

 // Test 9: Login with valid email and invalid password
    @Test(priority = 9, groups = {"loginTests"})
    public void Tc_Ecom_Login_08_loginWithInvalidPassword() {
        test = extent.createTest("Tc_Ecom_Login_08 - Login with valid email and invalid password");
        try {
    signup_login_page loginPage = new signup_login_page(driver);
    loginPage.clickSignupLoginLink();
    loginPage.enterLoginEmail("test@gmail.com");  // valid email
    loginPage.enterLoginPassword("Testhjfgkjfjtyurfyygfhjgdgfhsfsdgd"); // invalid password
    loginPage.clickLoginButton();

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Wait until the red error message is visible
    WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//p[@style='color: red;']")));

    Assert.assertTrue(errorMsg.isDisplayed(),
            "Expected validation message not displayed for invalid password");

    test.pass("Validation message displayed for invalid password: " + errorMsg.getText());

} catch (Exception e) {
    captureFailure("Tc_Ecom_Login_08", e);
}}
        
    // Test 10: Login with invalid email and valid password
    @Test(priority = 10, groups = {"loginTests"})
    public void Tc_Ecom_Login_09_loginWithInvalidEmail() {
        test = extent.createTest("Tc_Ecom_Login_09 - Login with invalid email and valid password");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            loginPage.enterLoginEmail("testt");   // still using POM methods
            loginPage.enterLoginPassword("Test@hgfg12ghf3");
            loginPage.clickLoginButton();

            // Use the getter instead of findElement
            WebElement emailField = loginPage.getEmailField();

            // Fetch browser's validation message
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String validationMsg = (String) js.executeScript(
                    "return arguments[0].validationMessage;", emailField);

            Assert.assertFalse(validationMsg.isEmpty(),
                    "Expected a browser validation prompt but got none!");

            test.pass("Validation message displayed for invalid email: " + validationMsg);
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_10", e);
        }
    }

    
    // Test 11: Logout functionality
    @Test(priority = 11, groups = {"loginTests"})
    public void Tc_Ecom_Login_11_verifyLogout() {
        test = extent.createTest("Tc_Ecom_Login_11 - Logout functionality");
        try {
        		signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("viji514@gmail.com");
            loginPage.enterLoginPassword("viji514");
            loginPage.clickLoginButton();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Logout")));

            // Scroll into view and click via JS to avoid click issues
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logoutBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);

            WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@data-qa='login-email']")));
            Assert.assertTrue(emailInput.isDisplayed(), "Email input not visible after logout");
            test.pass("Logout successful, email input visible");
        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_11", e);
        }
    }

 // Test 12: Delete account and verify 'ACCOUNT DELETED!' message
    @Test(priority = 12, groups = {"loginTests"})
    public void Tc_Ecom_Login_12_deleteAccountAndVerifyMessage() {
        test = extent.createTest("Tc_Ecom_Login_12 - Delete Account and verify 'ACCOUNT DELETED!' message");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            loginPage.enterLoginEmail("viji514@gmail.com");
            loginPage.enterLoginPassword("viji514");
            loginPage.clickLoginButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Scroll + click Delete Account button
            WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.partialLinkText("Delete Account")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);

            // Wait until redirected and verify message
            wait.until(ExpectedConditions.urlContains("delete_account"));
            WebElement deletedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[normalize-space(text())='Account Deleted!']")));

            Assert.assertTrue(deletedMsg.isDisplayed(), "'ACCOUNT DELETED!' message not visible!");
            test.pass("Delete account successful. 'ACCOUNT DELETED!' message displayed.");
            
         // 🔹 Click Continue button after deletion
            WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(text(),'Continue')]")));
            continueBtn.click();
            test.pass("Clicked 'Continue' button after account deletion");


        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_12", e);
        }
    }

 // Test 13: Login with blank fields
    @Test(priority = 13, groups = {"loginTests"})
    public void Tc_Ecom_Login_14_loginWithBlankFields() {
        test = extent.createTest("Tc_Ecom_Login_14 - Login with blank fields");
        try {
            signup_login_page loginPage = new signup_login_page(driver);
            loginPage.clickSignupLoginLink();
            
            // Keep email and password empty
            WebElement emailField = loginPage.getEmailField();
            WebElement passwordField = loginPage.getpasswordField();
            loginPage.clickLoginButton();

            // ✅ Get browser's native validation message for blank field
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String validationMsg = (String) js.executeScript(
                    "return arguments[0].validationMessage;", emailField);

            Assert.assertTrue(!validationMsg.isEmpty(), 
                    "Expected validation message not displayed for blank fields");

            test.pass("Validation message displayed for blank fields: " + validationMsg);

        } catch (Exception e) {
            captureFailure("Tc_Ecom_Login_13", e);
        }
    }

    // Utility method to capture screenshot and log failure in ExtentReports
    private void captureFailure(String screenshotName, Exception e) {
        try {
            String path = screenshotutilities.capturescreen(driver, screenshotName);
            test.log(Status.FAIL, "Test failed: " + e.getMessage())
                .addScreenCaptureFromPath(path);
        } catch (IOException io) {
            test.log(Status.FAIL, "Failed to capture screenshot: " + io.getMessage());
        }
        Assert.fail("Test failed: " + e.getMessage());
    }
}
