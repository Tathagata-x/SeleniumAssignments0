package com.qa.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Page;


public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static final ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private static String reportPath;

    public static void initializeReport() {
        String currentDateTime = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new Date());
        reportPath = "./Reports/ExtentReport_" + currentDateTime + ".html";
        
        File reportDir = new File("./Reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("FINEOS Automation Report");
        spark.config().setDocumentTitle("Automation Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Environment", "FT Server");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        // Add other system info as needed
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            initializeReport();
        }
        return extent;
    }


    public static ExtentTest createTest(String testName) {
        ExtentTest test = getInstance().createTest(testName);
        testThread.set(test);
        System.out.println("✅ Created ExtentTest: " + testName);
        return test;
    }

    public static ExtentTest getTest() {
        ExtentTest test = testThread.get();
        if (test == null) {
            throw new IllegalStateException("❌ ERROR: ExtentTest is NULL when retrieved from getTest(). It must be created at the test level.");
        }
        return test;
    }


    public static void flushReports() {
        if (extent != null) {
            System.out.println("🔄 Flushing ExtentReports...");
            extent.flush();
            
            File reportFile = new File(reportPath);
            if (reportFile.exists() && reportFile.length() > 0) {
                System.out.println("✅ ExtentReports successfully saved at: " + reportFile.getAbsolutePath());
            } else {
                System.out.println("❌ ERROR: ExtentReports file is empty! No logs were written.");
            }
        } else {
            System.out.println("❌ ERROR: `extent` is NULL, cannot flush reports!");
        }
    }
    public static String captureScreenshot(Page page){
        byte[] screenshotBytes = page.screenshot();
        return Base64.getEncoder().encodeToString(screenshotBytes);
    }
}
