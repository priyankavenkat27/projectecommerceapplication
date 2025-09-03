package com.ecom.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.apache.commons.io.FileUtils;

public class screenshotutilities {
    static String projectpath = System.getProperty("user.dir");

    public static String capturescreen(WebDriver driver, String testName) throws IOException {
        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create timestamp for uniqueness
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Create folder if not exists
        String folderPath = projectpath + "\\screenshots\\";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Unique screenshot name
        String screenPath = folderPath + testName + "_" + timestamp + ".png";

        // Save file
        File dest = new File(screenPath);
        FileUtils.copyFile(src, dest);

        return screenPath;
    }
}
