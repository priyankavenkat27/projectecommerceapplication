package project;

import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testcase1 {
    WebDriver driver;
    String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "searchdata")
    public void f(String productName) {
        System.out.println("This is the test");

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\SearchReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Verify Search Button for: " + productName);

        driver.get("https://automationexercise.com/");
        productpage objSearch = new productpage(driver);
        objSearch.goToProductsPage();
        test.info("Navigated to Products page");

        if (objSearch.searchInputIsDisplayed()) {
            objSearch.enterProduct(productName);
            test.pass("Entered product name: " + productName);
        } else {
            test.fail("Search input not displayed");
        }

        if (objSearch.searchButtonIsDisplayed()) {
            objSearch.clickSearch();
            test.pass("Clicked search button");
            if (objSearch.resultsAreVisible()) {
                test.pass("Search results are visible");
            } else {
                test.fail("Search results not visible");
                Assert.fail("Search results not found");
            }
        } else {
            test.fail("Search button not displayed");
            Assert.fail("Search button not found");
        }

        extent.flush();
    }

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is After Method");
        driver.quit();
    }

    @DataProvider
    public String[][] searchdata() throws IOException {
        String[][] data = new String[4][1];
        File file1 = new File(projectpath + "\\searchdata.xlsx");
        FileInputStream fs = new FileInputStream(file1);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet worksheet = workbook.getSheetAt(0);
        int rowcount = worksheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rowcount; i++) {
            data[i][0] = worksheet.getRow(i).getCell(0).getStringCellValue();
        }
        workbook.close();
        fs.close();
        return data;
    }

    @BeforeClass 
    public void beforeClass()
    { 
    	System.out.println("This is Before Class"); 
    }
    @AfterClass 
    public void afterClass() 
    { 
    	System.out.println("This is After Class"); 
    	}
    @BeforeTest 
    public void beforeTest() 
    { 
    	System.out.println("This is Before Test"); 
    	}
    @AfterTest public void afterTest() 
    { 
    	System.out.println("This is After Test"); 
    	}
    @BeforeSuite public void beforeSuite() 
    { 
    	System.out.println("This is Before Suite"); 
    	}
    @AfterSuite public void afterSuite() 
    { 
    	System.out.println("This is After Suite"); 
    	}
}
