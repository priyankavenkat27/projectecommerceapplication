package Automation;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class tc_verifyconandsubworking{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        // Path to your ChromeDriver
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open your site
        driver.get("https://automationexercise.com/"); 
    }

    @Test(priority = 1)
    public void verifyContactUsTextField() {
        // Click "Contact Us"
        WebElement contactLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactLink.click();

        // Fill text fields
        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
        WebElement email = driver.findElement(By.name("email"));
        WebElement subject = driver.findElement(By.name("subject"));
        WebElement message = driver.findElement(By.name("message"));

        name.sendKeys("Priyanka");
        email.sendKeys("priya@test.com");
        subject.sendKeys("Test Subject");
        message.sendKeys("This is a test message.");

        // Assert entered text
        Assert.assertEquals(name.getAttribute("value"), "Priyanka");
        Assert.assertEquals(email.getAttribute("value"), "priya@test.com");
    }

    @Test(priority = 2)
    public void verifyContactUsSubmitButton() {
        // Navigate to Contact Us
        WebElement contactLink = wait.until(
                ExpectedConditions.elementToBeClickable(By.linkText("Contact us")));
        contactLink.click();

        // Fill fields
        driver.findElement(By.name("name")).sendKeys("Priya");
        driver.findElement(By.name("email")).sendKeys("priya@test.com");
        driver.findElement(By.name("subject")).sendKeys("Testing");
        driver.findElement(By.name("message")).sendKeys("This is a test");

        // Click submit
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        
     // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Print alert text (optional)
        System.out.println("Alert message: " + alert.getText());

        // Accept the alert window
        alert.accept();


        // Verify success message
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Success!')]")));
        Assert.assertTrue(success.isDisplayed(), "Submit button did not work!");
    }

    @Test(priority = 3)
    public void verifySubscriptionIcon() {
        // Scroll down to subscription section
        WebElement subscriptionBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("susbscribe_email")));

        subscriptionBox.sendKeys("test@gmail.com");
        driver.findElement(By.id("subscribe")).click();

        // Verify success message
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'You have been successfully subscribed!')]")));
        Assert.assertTrue(success.isDisplayed(), "Subscription failed!");
    }

    @AfterMethod
    public void finish() {
    	if(driver!=null) {
        driver.quit();
    }}
}
