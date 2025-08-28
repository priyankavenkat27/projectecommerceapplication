package project;

import org.testng.annotations.Test;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;
import java.io.*;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class testcase_3 {
    WebDriver driver;
    String projectpath = System.getProperty("user.dir");

    @Test(dataProvider = "cartdata")
    public void f(String testLabel) {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(projectpath + "\\AddToCartReport.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Verify 'Add to Cart' Button - " + testLabel);

        driver.get("https://automationexercise.com/");
        Cartpage objCart = new Cartpage(driver);
        objCart.goToProductsPage();
        test.info("Navigated to Products page");

        if (objCart.isAllProductsHeadingVisible()) {
            test.pass("'ALL PRODUCTS' heading is visible");
        } else {
            test.fail("'ALL PRODUCTS' heading is not visible");
            Assert.fail("Heading not found");
        }

        if (objCart.isAddToCartButtonVisible()) {
            test.pass("'Add to cart' button is visible");
            objCart.clickAddToCartSafely();
            test.pass("Clicked 'Add to cart' button");
        } else {
            test.fail("'Add to cart' button is not visible");
            Assert.fail("Add to cart button not found");
        }

        if (objCart.isConfirmationVisible()) {
            test.pass("Product added to cart and confirmation popup is visible");
        } else {
            test.fail("Product not added to cart");
            Assert.fail("Product not added to cart");
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
        driver.quit();
    }

    @DataProvider
    public String[][] cartdata() throws IOException {
        String[][] data = new String[4][1];
        File file1 = new File(projectpath + "\\addtocart.xlsx");
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
