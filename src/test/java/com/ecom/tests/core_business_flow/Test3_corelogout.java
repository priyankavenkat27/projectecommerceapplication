package com.autoex.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3_corelogout {
    WebDriver driver;

    // ✅ Read Excel Data Safely
    public String[] getLoginData(String filePath, String sheetName) {
        try {
            File file = new File(filePath);
            if (!file.exists() || file.length() == 0) {
                System.out.println("⚠ Excel file not found or empty: " + filePath);
                return null; // return null if no data
            }

            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row row = sheet.getRow(1); // row 0 = header
            String email = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();

            workbook.close();
            fis.close();

            return new String[]{email, password};
        } catch (Exception e) {
            System.out.println("⚠ Error reading Excel file: " + e.getMessage());
            return null;
        }
    }

    // ✅ Take Screenshot
    public String takeScreenshot(String projectpath, String filename) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File screenshotDir = new File(projectpath + "\\screenshots");
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }

        String screenpath = projectpath + "\\screenshots\\" + filename;
        File dest = new File(screenpath);
        FileUtils.copyFile(src, dest);

        return screenpath;
    }

    @Test
    public void screenshottest() throws IOException {
        String projectpath = System.getProperty("user.dir");

        // ✅ Extent Report setup
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "/reports/logoutReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Verify Login and Logout with Screenshot");

        // ✅ Get login data from Excel or use fallback
        String excelPath = projectpath + "\\data.xlsx";
        String[] loginData = getLoginData(excelPath, "Sheet1");

        String email, password;
        if (loginData != null) {
            email = loginData[0];
            password = loginData[1];
        } else {
            // fallback credentials if excel is missing
            email = "test123@mail.com";
            password = "test123";
            test.info("⚠ Using fallback credentials since Excel data not found");
        }

        // ✅ Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/login");
        driver.manage().window().maximize();

        // Verify title
        if (driver.getTitle().contains("Automation Exercise")) {
            test.pass("Title is matched");
        } else {
            test.fail("Title is not matched");
        }

        // ✅ Login
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // ✅ Wait for Logout link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logout')]")));

        // ✅ Perform logout
        logoutBtn.click();

        // ✅ Wait until redirected back to login page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        // ✅ Screenshot after logout
        String screenpathAfterLogout = takeScreenshot(projectpath, "screenshot_after_logout.png");
        test.pass("Captured screenshot after logout").addScreenCaptureFromPath(screenpathAfterLogout);

        extent.flush();
        driver.quit();
    }
}
