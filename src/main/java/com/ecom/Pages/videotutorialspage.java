package com.ecom.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class videotutorialspage {
    WebDriver driver;
    WebDriverWait wait;

    // 🔹 Locators
    private By videoTutorialsLink = By.partialLinkText("Video Tutorials");
    private By youtubeIframe = By.cssSelector("iframe[src*='youtube.com']");

    // 🔹 Constructor
    public videotutorialspage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 🔹 Actions
    public void clickVideoTutorialsLink() {
        wait.until(ExpectedConditions.elementToBeClickable(videoTutorialsLink)).click();
    }

    public boolean isVideoDisplayed() {
        try {
            WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(youtubeIframe));
            return iframe.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
