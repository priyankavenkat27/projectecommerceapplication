package com.ecom.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extentmanager {
    private static ExtentReports extent;
    static String projectPath = System.getProperty("user.dir");

    public static ExtentReports getInstance() {
        if (extent == null) {
            // Unique timestamp for report name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // Create reports folder if not exists
            String reportPath = projectPath + "\\reports\\TestReport_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
