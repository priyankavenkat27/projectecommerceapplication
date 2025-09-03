package com.ecom.tests;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ecom.base.Baseclass;
import com.ecom.Pages.signup_login_page;
import com.ecom.utilities.excelutilities;
import com.ecom.utilities.screenshotutilities;

public class LoginTest extends Baseclass {
    
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "loginData")
    public void verifyLogin(String username, String password) throws IOException {
        
        driver.get(config.getProperty("baseUrl") + "/login");
        test = extent.createTest("Login test with user: " + username);

        signup_login_page loginPage = new signup_login_page(driver);
        loginPage.enterLoginEmail(username);
        loginPage.enterLoginPassword(password);
        loginPage.clickLoginButton();

        // Dashboard validation (dummy check)
        if (driver.getPageSource().contains("Logged in as")) {
            test.pass("Login successful for user: " + username);
        } else {
            test.fail("Login failed for user: " + username)
                .addScreenCaptureFromPath(screenshotutilities.capturescreen(driver, "Login_" + username));
        }

        // Title check
        if (driver.getTitle().contains("Automation Exercise")) {
            test.pass("Title matched after login");
        } else {
            test.fail("Title not matched")
                .addScreenCaptureFromPath(screenshotutilities.capturescreen(driver, "Title_" + username));
        }
    }

    @DataProvider
    public Object[][] loginData() throws IOException {
        return excelutilities.getdata(
            projectPath + "\\train_data.xlsx", "LoginData");
    }
}


