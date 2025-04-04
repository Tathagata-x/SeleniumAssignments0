package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentSparkReporter sparkReporter;
    private static final String REPORT_PATH = "./test-output/ExtentReports.html";

    public static synchronized void initializeReport() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter(REPORT_PATH);
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setReportName("Test Report");
            sparkReporter.config().setTheme(Theme.DARK);
    
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
    }
    
    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            initializeReport();
        }
        return extent;
    }
    
    public static synchronized ExtentTest createTest(String testName) {
        ExtentTest extentTest = getInstance().createTest(testName);
        test.set(extentTest);
        return extentTest;
    }
    
    public static synchronized ExtentTest getTest() {
        return test.get();
    }
    
    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}    