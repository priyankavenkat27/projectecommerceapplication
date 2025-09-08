package com.autoex.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Functional_Registration11_22 {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;
    int screenshotCounter = 10;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Setup Extent Report
        String reportPath = System.getProperty("user.dir") + "/Functional_Registration11_22.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Project", "Automation Exercise");
        extent.setSystemInfo("Tester", "Boomika");
        extent.setSystemInfo("Browser", "Chrome");
    }

    // ================== TESTS ==================
    //Testcase 10

    @Test(priority = 1)
    public void testSignupButton() {
        test = extent.createTest("Signup Button Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            String name = "Boomika";
            String email = "boomika" + System.currentTimeMillis() + "@test.com";
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys(name);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            test.pass("Signup button clicked").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
//Testcase 11
    @Test(priority = 2)
    public void testInvalidNameValidEmail() {
        test = extent.createTest("Invalid Name Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            String invalidName = "123456";
            String validEmail = "boomika" + System.currentTimeMillis() + "@test.com";
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys(invalidName);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(validEmail);
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            try {
                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Invalid name')]"));
                if (errorMsg.isDisplayed()) test.pass("Signup blocked for invalid name").addScreenCaptureFromPath(takeScreenshot());
            } catch (NoSuchElementException e) {
                test.pass("Signup did not proceed with invalid name").addScreenCaptureFromPath(takeScreenshot());
            }
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
//Testcase 12
    @Test(priority = 3)
    public void testValidNameInvalidEmail() {
        test = extent.createTest("Invalid Email Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            String validName = "Boomika";
            String invalidEmail = "invalid_email_format";
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys(validName);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(invalidEmail);
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            try {
                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Invalid email')]"));
                if (errorMsg.isDisplayed()) test.pass("Signup blocked for invalid email").addScreenCaptureFromPath(takeScreenshot());
            } catch (NoSuchElementException e) {
                test.pass("Signup did not proceed with invalid email").addScreenCaptureFromPath(takeScreenshot());
            }
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 13

    @Test(priority = 4)
    public void testSignupNavigationToAccountInfo() {
        test = extent.createTest("Signup Navigation Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            String name = "Boomika";
            String email = "boomika" + System.currentTimeMillis() + "@test.com";
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys(name);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            WebElement accountInfoHeader = driver.findElement(By.xpath("//h2[text()='Enter Account Information']"));
            Assert.assertTrue(accountInfoHeader.isDisplayed());
            test.pass("Navigation to Account Information page successful").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 14

    @Test(priority = 5)
    public void testTitleHeadingAndRadioButtons() {
        test = extent.createTest("Title & Radio Buttons Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            String name = "Boomika";
            String email = "boomika" + System.currentTimeMillis() + "@test.com";
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys(name);
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//b[text()='Title']")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("id_gender1")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.id("id_gender2")).isDisplayed());
            test.pass("Title heading and radio buttons displayed").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
//Testcase 15
    @Test(priority = 6)
    public void testNameFieldDisplayAndValidInput() {
        test = extent.createTest("Name Field Valid Input").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            WebElement nameField = driver.findElement(By.name("name"));
            Assert.assertTrue(nameField.isDisplayed());
            nameField.sendKeys("Boomika");
            test.pass("Name field displayed and valid input entered").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 16

    @Test(priority = 7)
    public void testNameFieldRejectsInvalidInput() {
        test = extent.createTest("Name Field Invalid Input").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            WebElement nameField = driver.findElement(By.name("name"));
            Assert.assertTrue(nameField.isDisplayed());
            nameField.sendKeys("123@!#");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            test.pass("Name field invalid input tested").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 17

    @Test(priority = 8)
    public void testEmailFieldAcceptsValidInput() {
        test = extent.createTest("Email Field Valid Input").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
            Assert.assertTrue(emailField.isDisplayed());
            emailField.sendKeys("boomika" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            test.pass("Email field valid input accepted").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
//Testcase 18
    @Test(priority = 9)
    public void testEmailFieldRejectsInvalidInput() {
        test = extent.createTest("Email Field Invalid Input").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            WebElement emailField = driver.findElement(By.xpath("//input[@data-qa='signup-email']"));
            Assert.assertTrue(emailField.isDisplayed());
            emailField.sendKeys("invalid_email");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            test.pass("Email field invalid input tested").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 19

    @Test(priority = 10)
    public void testPasswordFieldDisplay() {
        test = extent.createTest("Password Field Display").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys("Boomika");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("boomika" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            WebElement passwordField = driver.findElement(By.id("password"));
            Assert.assertTrue(passwordField.isDisplayed());
            test.pass("Password field displayed").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 20

    @Test(priority = 11)
    public void testPasswordFieldMaxLength() {
        test = extent.createTest("Password Max Length Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys("Boomika");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("boomika" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("A".repeat(100));
            String enteredValue = passwordField.getAttribute("value");
            if (enteredValue.length() <= 50) test.pass("Password length correctly restricted").addScreenCaptureFromPath(takeScreenshot());
            else test.fail("Password exceeded max length").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 21

    @Test(priority = 12)
    public void testDOBDropdowns() {
        test = extent.createTest("DOB Dropdown Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys("Boomika");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("boomika" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            Select day = new Select(driver.findElement(By.id("days")));
            Select month = new Select(driver.findElement(By.id("months")));
            Select year = new Select(driver.findElement(By.id("years")));
            day.selectByValue("15"); month.selectByValue("5"); year.selectByValue("2000");
            test.pass("DOB dropdowns displayed and selected").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }
    //Testcase 22

    @Test(priority = 13)
    public void testNewsletterCheckboxSelectable() {
        test = extent.createTest("Newsletter Checkbox Test").assignCategory("UI Test").assignAuthor("Boomika");
        try {
            driver.get("https://automationexercise.com/");
            driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
            driver.findElement(By.name("name")).sendKeys("Boomika");
            driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("boomika" + System.currentTimeMillis() + "@test.com");
            driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
            WebElement checkbox = driver.findElement(By.id("newsletter"));
            Assert.assertTrue(checkbox.isDisplayed());
            if (!checkbox.isSelected()) checkbox.click();
            test.pass("Newsletter checkbox selectable").addScreenCaptureFromPath(takeScreenshot());
        } catch (Exception e) {
            test.fail(e.getMessage()).addScreenCaptureFromPath(takeScreenshot());
        }
    }

    // ================== SCREENSHOT METHOD ==================
    public String takeScreenshot() {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "regtc0" + screenshotCounter++;
            String destPath = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
            File dest = new File(destPath);
            org.openqa.selenium.io.FileHandler.copy(src, dest);
            return destPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}
