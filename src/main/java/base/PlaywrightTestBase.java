package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import utils.ExtentReportManager;

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

    @BeforeClass
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
        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                ExtentReportManager.getTest().fail(result.getThrowable());
                break;
            case ITestResult.SUCCESS:
                ExtentReportManager.getTest().pass("Test passed");
                break;
            case ITestResult.SKIP:
                ExtentReportManager.getTest().skip(result.getThrowable());
                break;
            default:
                break;
        }
        ExtentReportManager.flushReports();
        
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
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
