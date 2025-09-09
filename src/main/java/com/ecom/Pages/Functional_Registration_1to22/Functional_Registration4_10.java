package com.autoex.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

public class Functional_Registration4_10 {

    WebDriver driver;
    WebDriverWait wait;

    ExtentReports extent;
    ExtentTest test;

    static int screenshotCounter = 1;

    // ======== Utility: Capture Screenshot ========
    public String captureScreenshot() {
        String path = "screenshots/reg" + screenshotCounter + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File dest = new File(path);
            dest.getParentFile().mkdirs();
            Files.copy(src.toPath(), dest.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        screenshotCounter++;
        return path;
    }

    // ======== Extent Report Setup ========
    @BeforeSuite
    public void startReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/Functiona_Registration4_10.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }

    // ======== Browser Setup ========
    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        test = extent.createTest(method.getName());

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    // ======== TEST CASES (FTC01 → FTC09) ========

    @Test(priority = 1)
    public void FTC01_verifyLaunchOnChrome() {
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        test.info("Launched on Chrome and title verified.");
    }
    //Testcase 5

    @Test(priority = 2)
    public void FTC02_verifyHomePageVisibility() {
        WebElement logo = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
        Assert.assertTrue(logo.isDisplayed(), "Home page logo not visible!");
        test.info("Home page visible with main elements.");
    }
//Testcase 6
    @Test(priority = 3)
    public void FTC03_verifySignupLoginButtonPresence() {
        WebElement signupLoginBtn = driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]"));
        Assert.assertTrue(signupLoginBtn.isDisplayed(), "Signup/Login button not visible!");
        test.info("'Signup / Login' button present.");
    }
//Testcase 7
    @Test(priority = 4)
    public void FTC04_verifySignupLoginNavigation() {
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Did not navigate to login page!");
        test.info("Navigated to Signup/Login page.");
    }
    //Testcase 8

    @Test(priority = 5)
    public void FTC05_verifyNewUserSignupSection() {
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        WebElement newUserText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='New User Signup!']")));
        Assert.assertTrue(newUserText.isDisplayed(), "'New User Signup!' section not visible!");
        test.info("'New User Signup!' section visible.");
    }
    //Testcase 9 

    @Test(priority = 6)
    public void FTC06_verifyExistingEmailError() {
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        driver.findElement(By.name("name")).sendKeys("Test User");
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys("aasharaja305@gmail.com");
        driver.findElement(By.xpath("//button[text()='Signup']")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Email Address already exist!')]")));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
        test.info("Existing email validation works.");
    }
    //Testcase 10

    @Test(priority = 7)
    public void FTC07_verifyLoginFormPresence() {
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        WebElement loginForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='Login to your account']")));
        Assert.assertTrue(loginForm.isDisplayed(), "Login form not visible!");
        test.info("Login form visible.");
    }
    //Testcase 10

    @Test(priority = 8)
    public void FTC08_verifyLoginWithValidCredentials() {
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("aasharaja305@gmail.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("Aasharaja@2468");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebElement loggedInMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as')]")));
        Assert.assertTrue(loggedInMsg.isDisplayed(), "User login failed!");
        test.info("User logged in successfully.");
    }

    @Test(priority = 9)
    public void FTC09_verifyLogoutButtonPresence() {
        // Make sure user is logged in first
        FTC08_verifyLoginWithValidCredentials();
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logout')]")));
        Assert.assertTrue(logoutBtn.isDisplayed(), "Logout button not visible!");
        test.info("Logout button is present after login.");
    }

    // ======== Screenshot & Reporting ========
    @AfterMethod
    public void captureResults(ITestResult result) {
        String screenshotPath = captureScreenshot();

        if (result.getStatus() == ITestResult.SUCCESS)
            test.pass("Test Passed").addScreenCaptureFromPath(screenshotPath);
        else if (result.getStatus() == ITestResult.FAILURE)
            test.fail(result.getThrowable()).addScreenCaptureFromPath(screenshotPath);
        else if (result.getStatus() == ITestResult.SKIP)
            test.skip("Test Skipped");

        if (driver != null) driver.quit();
    }
}


