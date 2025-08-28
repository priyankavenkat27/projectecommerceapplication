package Regression_Test_Suite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
public class TC_Reg_End_to_End_Scen_004_5_6 {
	WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/");
        driver.findElement(By.xpath("//a[@href='/products']")).click();
    }

    @Test(priority = 1)
    public void verifySearchBoxWorking() {
        
        WebElement searchBox = driver.findElement(By.id("search_product"));
        searchBox.sendKeys("Dress");
        driver.findElement(By.id("submit_search")).click();

        WebElement heading = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/h2"));
        Assert.assertTrue(heading.isDisplayed(), "❌ Search result not displayed!");
    }

    @Test(priority = 2)
    public void verifyViewProductWorking() {
    	
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li")).click();
        WebElement productName = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/h2"));
        Assert.assertTrue(productName.isDisplayed(), "❌ Product detail page not opened!");
        driver.navigate().back();
    }

    @Test(priority = 3)
    public void verifyAddToCartWorking() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/a")).click();
        Thread.sleep(2000); 
        WebElement popup = driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[1]/div/div/div[1]/h4"));
        Assert.assertTrue(popup.isDisplayed(), "❌ Add to Cart confirmation not shown!");
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[1]/div/div/div[2]/p[2]/a")).click();
        WebElement cartItem = driver.findElement(By.xpath("/html/body/section/div/div[2]/table/tbody/tr/td[2]/h4/a"));
        Assert.assertTrue(cartItem.isDisplayed(), "❌ Item not found in Cart!");
    }

    @AfterClass
     public void tearDown() {
        driver.quit();
   }
}

