package com.autoex.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1_corelogin {
    WebDriver driver;

    // Method to take screenshot
    public String takeScreenshot(String projectpath, String filename) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create reports/screenshots folder if not exists
        File screenshotDir = new File(projectpath + "\\reports\\screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String screenpath = projectpath + "\\reports\\screenshots\\" + filename;
        File dest = new File(screenpath);
        FileUtils.copyFile(src, dest);

        return screenpath;
    }

    @Test
    public void screenshottest() throws InterruptedException, IOException {
        String projectpath = System.getProperty("user.dir");

        // 🔹 Create reports folder if not exists
        File reportDir = new File(projectpath + "\\reports");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        // Extent Report setup → save inside /reports folder
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\reports\\LoginReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Verify the login");

        // 🔹 Hardcoded login credentials
        String email = "aasharaja305@gmail.com";
        String password = "Aasharaja@2468";

        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);

        // Verify title
        if (driver.getTitle().equalsIgnoreCase("Automation Exercise - Signup / Login")) {
            System.out.println("Title is matched");
            test.pass("Title is matched");
        } else {
            String screenpath = takeScreenshot(projectpath, "screenshot_title_mismatch.png");
            test.fail("Title is not matched").addScreenCaptureFromPath(screenpath);
        }

        // Perform login with hardcoded credentials
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // ✅ Always take screenshot after login
        String screenpathAfterLogin = takeScreenshot(projectpath, "screenshot_after_login.png");
        test.info("Captured screenshot after login").addScreenCaptureFromPath(screenpathAfterLogin);

        extent.flush();
        driver.quit();
    }
}

