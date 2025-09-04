package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class signup_login_page {
    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Locators for Login
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");

    // 🔹 Locators for Signup
    private By signupNameField = By.name("name");
    private By signupEmailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");

    // 🔹 Common
    private By signupLoginLink = By.linkText("Signup / Login");
    private By loggedInAs = By.xpath("//a[contains(text(),'Logged in as')]");

    // 🔹 Constructor
    public signup_login_page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= LOGIN Methods =================
    public void clickSignupLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(signupLoginLink));
        link.click();
    }

    public void enterLoginEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginEmailField)).clear();
        driver.findElement(loginEmailField).sendKeys(email);
    }

    public void enterLoginPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPasswordField)).clear();
        driver.findElement(loginPasswordField).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElements(loggedInAs).size() > 0;
    }

    // ================= SIGNUP Methods =================
    public void enterSignupDetails(String name, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupNameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signupEmailField)).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }

    public boolean isSignupButtonVisible() {
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(signupButton));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
        
    }
    public WebElement getEmailField() {
        return driver.findElement(By.xpath("//input[@data-qa='login-email']"));
    }
    public WebElement getpasswordField() {
    		return driver.findElement(By.xpath("//input[@data-qa='login-password']"));
    }
}
