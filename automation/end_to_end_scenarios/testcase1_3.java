package pack1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class testcase1_3{

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.automationexercise.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void verifySignupButton() {
        WebElement signupLoginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Signup / Login')]")));
        Assert.assertTrue(signupLoginBtn.isDisplayed(), "Signup / Login button is not visible");
        signupLoginBtn.click();

        // Validate navigation to login page
        String expectedUrl = "https://www.automationexercise.com/login";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Signup/Login navigation failed");
        
        // Check that signup form is displayed
        WebElement signupForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[text()='New User Signup!']")));
        Assert.assertTrue(signupForm.isDisplayed(), "Signup form is not visible");
    }

    @Test(priority = 2)
    public void verifyLoginButton() {
        // Enter login credentials
        WebElement email = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
        WebElement password = driver.findElement(By.xpath("//input[@data-qa='login-password']"));
        email.sendKeys("aasharaja305@gmail.com");  // Use valid test account
        password.sendKeys("Aasharaja@2468");

        WebElement loginBtn = driver.findElement(By.xpath("//button[@data-qa='login-button']"));
        Assert.assertTrue(loginBtn.isDisplayed(), "Login button is not visible");
        loginBtn.click();

        // Validate user is logged in (check Logout link)
        WebElement logoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[text()=' Logout']")));
        Assert.assertTrue(logoutBtn.isDisplayed(), "Logout button not displayed after login");
    }

    @Test(priority = 3)
    public void verifyLogoutButton() {
        WebElement logoutBtn = driver.findElement(By.xpath("//a[text()=' Logout']"));
        logoutBtn.click();

        // Validate redirected back to login page
        String expectedUrl = "https://www.automationexercise.com/login";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Logout navigation failed");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
