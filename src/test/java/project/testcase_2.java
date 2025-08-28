package project;

import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class testcase_2 {
    WebDriver driver;
    String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "viewproductdata")
    public void f(String testLabel) {
        System.out.println("Running test: " + testLabel);

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\ViewProductReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Verify 'View Product' Button - " + testLabel);

        driver.get("https://automationexercise.com/");
        productpage_2 objProduct = new productpage_2(driver);
        objProduct.clickProductsLink();
        test.info("Clicked on Products link");

        if (objProduct.isAllProductsHeadingVisible()) {
            test.pass("'ALL PRODUCTS' heading is visible");
        } else {
            test.fail("'ALL PRODUCTS' heading is not visible");
            Assert.fail("Heading not found");
        }

        if (objProduct.isViewProductButtonVisible()) {
            test.pass("'View Product' button is visible");
            objProduct.clickViewProductSafely();
            test.pass("Clicked on 'View Product' button");
        } else {
            test.fail("'View Product' button is not visible");
            Assert.fail("View Product button not found");
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
    public String[][] viewproductdata() throws IOException {
        String[][] data = new String[4][1];
        File file1 = new File(projectpath + "\\viewproduct.xlsx");
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
    @AfterTest 
    public void afterTest() 
    { 
    	System.out.println("This is After Test"); 
    	}
    @BeforeSuite 
    public void beforeSuite() 
    { 
    	System.out.println("This is Before Suite"); 
    	}
    @AfterSuite 
    public void afterSuite() 
    { 
    	System.out.println("This is After Suite"); 
    	}
}
