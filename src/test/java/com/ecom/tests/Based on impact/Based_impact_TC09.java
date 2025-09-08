package com.automationexercise.tests;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.*;
import org.testng.Assert;

import com.aventstack.extentreports.*;
import com.automationexercise.utilities.ExtentManager;
import com.automationexercise.utilities.ScreenshotUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class Based_impact_TC09 {

    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    String baseUrl = "https://www.automationexercise.com";
    String name = "Keerthana V S";
    String email = "keerthivs126@gmail.com";
    String message = "Cant able to view product review";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        extent = ExtentManager.createInstance("ContactUsChromeReport.html");
    }

    @Test
    public void verifyContactUsFields() {
        test = extent.createTest("Verify Contact Us Fields - Chrome");

        try {
            driver.get(baseUrl);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/contact_us']"))).click();
            test.info("Navigated to Contact Us page");

            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
            WebElement emailField = driver.findElement(By.name("email"));
            WebElement messageField = driver.findElement(By.name("message"));

            nameField.sendKeys(name);
            emailField.sendKeys(email);
            messageField.sendKeys(message);

            test.pass("✅ All fields accepted input");
            test.addScreenCaptureFromPath(ScreenshotUtilities.capture(driver, "ContactUsFieldsFilled"));

            Assert.assertEquals(nameField.getAttribute("value"), name, "Name field mismatch");
            Assert.assertEquals(emailField.getAttribute("value"), email, "Email field mismatch");
            Assert.assertEquals(messageField.getAttribute("value"), message, "Message field mismatch");

        } catch (Exception e) {
            String screenshotPath = ScreenshotUtilities.capture(driver, "ContactUsFieldError");
            test.fail("❌ Test failed: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath);
            Assert.fail("Test execution failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}



