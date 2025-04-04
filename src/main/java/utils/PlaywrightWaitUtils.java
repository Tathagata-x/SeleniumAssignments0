package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightWaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(PlaywrightWaitUtils.class);
    protected final Page page;
    private static final int WAIT_TIMEOUT_SECONDS = 6;
    private final int waitTimeoutSeconds;


    /**
     * Constructor for PlaywrightWaitUtils.
     * @param page The Playwright Page instance.
     * @param waitTimeoutSeconds The default timeout (in seconds) to wait for elements.
     */
    public PlaywrightWaitUtils(Page page) {
        this.page = page;
        this.waitTimeoutSeconds = WAIT_TIMEOUT_SECONDS;
    }

    /**
     * Waits for an element to become visible.
     * @param locator The Playwright Locator object for the element.
     */
    public void waitForElementToDisplay(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element is displayed: {}", locator);
            ExtentReportManager.getTest().info("Element is displayed: " + locator);
        } catch (Exception e) {
            String errMsg = "Timeout waiting for element to be displayed: " + locator + ". Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Waits for an element to become enabled.
     * @param locator The Playwright Locator.
     */
    public void waitForElementToEnable(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            ElementHandle handle = locator.elementHandle();
            page.waitForFunction("element => !element.disabled", handle,
                    new Page.WaitForFunctionOptions().setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element is enabled: {}", locator);
            ExtentReportManager.getTest().info("Element is enabled: " + locator);
        } catch (Exception e) {
            String errMsg = "Timeout waiting for element to enable: " + locator + ". Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Waits for an element to be visible, then clicks it.
     * @param locator The Playwright Locator.
     */
    public void waitAndClick(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            locator.click();
            logger.info("Clicked element: {}", locator);
            ExtentReportManager.getTest().info("Clicked element: " + locator);
        } catch (Exception e) {
            String errMsg = "Timeout waiting for element to be clickable: " + locator + ". Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Waits for element to be visible, then returns the Locator.
     * @param locator The Playwright Locator.
     * @return The same locator if it's visible.
     */
    public Locator waitForElementVisible(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element is visible: {}", locator);
            ExtentReportManager.getTest().info("Element is visible: " + locator);
            return locator;
        } catch (Exception e) {
            String errMsg = "Timeout waiting for element to be visible: " + locator + ". Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Waits until the locator is attached to the DOM.
     * @param locator The Playwright Locator.
     * @return The locator if attached.
     */
    public Locator fluentWaitForElement(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.ATTACHED)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element found using 'attached' wait: {}", locator);
            ExtentReportManager.getTest().info("Element found using 'attached' wait: " + locator);
            return locator;
        } catch (Exception e) {
            String errMsg = "Timeout waiting for element using fluent wait: " + locator + ". Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Waits for the page title to contain a certain substring.
     * @param substring The substring to look for in the page title.
     */
    public void waitForTitleContains(String substring) {
        try {
            page.waitForFunction("title => document.title.includes(title)", substring,
                    new Page.WaitForFunctionOptions().setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            String currentTitle = page.title();
            logger.info("Title contains '{}': {}", substring, currentTitle);
            ExtentReportManager.getTest().info("Title contains '" + substring + "': " + currentTitle);
        } catch (Exception e) {
            String msg = "Timeout waiting for title to contain '" + substring + "'. Current title: " + page.title();
            logger.error(msg, e);
            ExtentReportManager.getTest().fail(msg);
            throw new RuntimeException(msg, e);
        }
    }

    /**
     * Waits until the page load state is complete.
     */
    public void waitForPageLoad() {
        try {
            page.waitForLoadState();
            logger.info("Page loaded: {}", page.url());
            ExtentReportManager.getTest().info("Page loaded: " + page.url());
        } catch (Exception e) {
            String errMsg = "Timeout waiting for page load. Exception: " + e.getMessage();
            logger.error(errMsg, e);
            ExtentReportManager.getTest().fail(errMsg);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     * Checks if the page's title contains a specific substring within the default timeout.
     * @param substring The substring to look for in the title.
     * @return true if the title contains the substring, false otherwise.
     */
    public boolean isPageLoaded(String substring) {
        try {
            page.waitForFunction("title => document.title.includes(title)", substring,
                    new Page.WaitForFunctionOptions().setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Page is loaded with title containing '{}': {}", substring, page.title());
            ExtentReportManager.getTest().info("Page is loaded with title containing '" + substring + "': " + page.title());
            return true;
        } catch (Exception e) {
            String msg = "Timeout waiting for page title to contain '" + substring + "'. Current title: " + page.title();
            logger.error(msg, e);
            ExtentReportManager.getTest().fail(msg);
            return false;
        }
    }
}