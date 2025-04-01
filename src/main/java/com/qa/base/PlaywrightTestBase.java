package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.qa.utils.ExtentReportManager;

public class PlaywrightTestBase {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected Properties prop;
    protected ExtentReports extent;

    public PlaywrightTestBase() {
        prop = new Properties();
        try (FileInputStream config = new FileInputStream("src/resource/java/com/qa/config/config.properties")) {
            prop.load(config);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    @BeforeSuite
    public void initReport() {
        ExtentReportManager.initializeReport();
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        // Read browser type and headless mode from config, using defaults if not set.
        String browserType = prop.getProperty("browser", "chromium").toLowerCase();
        boolean headless = Boolean.parseBoolean(prop.getProperty("headless", "false"));

        // Common launch options
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setArgs(Arrays.asList("--start-maximized"));

        // Launch browser and create context based on browser type
        switch (browserType) {
            case "chrome":
            case "chromium":
                // Launch a persistent context for Chromium browsers.
                context = playwright.chromium().launchPersistentContext(
                        Paths.get("user-data-dir"),
                        new BrowserType.LaunchPersistentContextOptions()
                                .setHeadless(headless)
                                .setViewportSize(null)
                                .setArgs(Arrays.asList("--start-maximized")));
                break;
            case "firefox":
                browser = playwright.firefox().launch(launchOptions);
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 720));
                break;
            case "webkit":
            case "edge":
                browser = playwright.webkit().launch(launchOptions);
                context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1280, 720));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }

        // Start tracing to capture screenshots, snapshots, and sources during the test
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        // Use an existing page if available, otherwise create a new page
        page = context.pages().isEmpty() ? context.newPage() : context.pages().get(0);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest test = ExtentReportManager.getTest();
        try {
            // Log test result in the report
            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    test.fail("Test Failed",
                            MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
                    test.fail(result.getThrowable());
                    test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Failed", ExtentColor.RED));
                    break;
                case ITestResult.SUCCESS:
                    test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Passed", ExtentColor.GREEN));
                    break;
                case ITestResult.SKIP:
                    test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Skipped", ExtentColor.ORANGE));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            test.log(Status.ERROR, "Error during tearDown: " + e.getMessage());
        } finally {
            // Clean up Playwright resources safely
            try {
                if (context != null) {
                    context.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing context: " + e.getMessage());
            }
            try {
                if (browser != null) {
                    browser.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing browser: " + e.getMessage());
            }
            try {
                if (playwright != null) {
                    playwright.close();
                }
            } catch (Exception e) {
                System.out.println("Error closing Playwright: " + e.getMessage());
            }
        }
    }

    @AfterSuite
    public void endReport() {
        ExtentReportManager.flushReports();
    }

    public String takeScreenshot() {
        if (page == null) {
            return "";
        }
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        return java.util.Base64.getEncoder().encodeToString(screenshotBytes);
    }
}
