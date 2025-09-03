package com.automationexercise.tests;



import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import com.automationexercise.Base.BaseTest;
import com.automationexercise.pages.Based_impact_TC008;
import com.automationexercise.utilities.ExcelUtilities;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import com.aventstack.extentreports.*;

import java.io.IOException;

public class Based_impact_TC08 extends BaseTest {
    static String projectPath = System.getProperty("user.dir");

    @Test(dataProvider = "addToCartData")
    public void verifyAddToCartButton(String label) {
        ExtentReports extent = ExtentManager.createInstance("AddToCartReport.html");
        ExtentTest test = extent.createTest("Add to Cart Test: " + label);
        test.info("Current URL: " + driver.getCurrentUrl());


        try {
            Based_impact_TC008 page = new  Based_impact_TC008(driver);

            page.goToProductsPage();
            Assert.assertTrue(driver.getCurrentUrl().contains("/products"), "Navigation to Products page failed");

            test.info("Navigated to Products page");

            Assert.assertTrue(page.isAllProductsVisible(), "'ALL PRODUCTS' section not visible");
            test.pass("'ALL PRODUCTS' section is visible");

            page.clickAddToCart();
            test.info("Clicked 'Add to cart' button");

            if (page.isCartModalVisible()) {
                test.pass("Add to cart button is working and product was added");
                test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_AddToCartSuccess"));
            } else {
                test.fail("Cart modal not visible after clicking 'Add to cart'");
                test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, label + "_CartModalMissing"));
            }

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, label + "_Error");
            test.fail("Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
        }

        extent.flush();
    }

    @DataProvider(name = "addToCartData")
    public Object[][] addToCartData() throws IOException {
        return ExcelUtilities.getdata(projectPath + "/src/test/resources/automationexercise_Testdata/Based_impact_data.xlsx", "Sheet7");
    }
}


