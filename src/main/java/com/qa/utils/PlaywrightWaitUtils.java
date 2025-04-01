package com.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PlaywrightWaitUtils {
    private static final Logger logger = LoggerFactory.getLogger(PlaywrightWaitUtils.class);
    private final Page page;
    private final int WAIT_TIMEOUT_SECONDS;

    /**
     * Constructor for PlaywrightWaitUtils.
     * @param page The Playwright Page instance.
     * @param waitTimeoutSeconds The default timeout (in seconds) to wait for elements.
     */
    public PlaywrightWaitUtils(Page page, int waitTimeoutSeconds) {
        this.page = page;
        this.WAIT_TIMEOUT_SECONDS = waitTimeoutSeconds;
    }

    /**
     * Waits for an element to become visible (analogous to Selenium's visibilityOf).
     * @param locator The Playwright Locator object for the element.
     * @throws RuntimeException if the element does not become visible before the timeout.
     */
    public void waitForElementToDisplay(Locator locator) {
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element is displayed: {}", locator);
        } catch (Exception e) {
            logger.error("Timeout waiting for element to be displayed: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Waits for an element to become enabled/clickable.
     * Playwright doesn't provide a direct "clickable" state, but we can wait for it to be visible and not disabled.
     * @param locator The Playwright Locator.
     */
    public void waitForElementToEnable(Locator locator) {
        try {
            // Wait for visibility
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            // Check "disabled" attribute or the "enabled" property
            if (!locator.isEnabled()) {
                // We can actively poll for the element to become enabled
                long startTime = System.currentTimeMillis();
                while (!locator.isEnabled() 
                        && (System.currentTimeMillis() - startTime) < WAIT_TIMEOUT_SECONDS * 1000L) {
                    Thread.sleep(200); // small pause
                }
            }
            if (!locator.isEnabled()) {
                throw new RuntimeException("Element is still disabled after timeout.");
            }
            logger.info("Element is enabled: {}", locator);
        } catch (Exception e) {
            logger.error("Timeout waiting for element to enable: {}", locator, e);
            throw new RuntimeException(e);
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
        } catch (Exception e) {
            logger.error("Timeout waiting for element to be clickable: {}", locator, e);
            throw new RuntimeException(e);
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
            return locator;
        } catch (Exception e) {
            logger.error("Timeout waiting for element to be visible: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Fluent wait concept is somewhat built-in with waitFor and repeated polling in Playwright.
     * This method just demonstrates polling the locator until it's attached to the DOM.
     * @param locator The Playwright locator.
     */
    public Locator fluentWaitForElement(Locator locator) {
        // Use waitFor with ATTACHED if you want to ensure it's in DOM (like presenceOfElement).
        try {
            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.ATTACHED)
                    .setTimeout(WAIT_TIMEOUT_SECONDS * 1000L));
            logger.info("Element found using 'attached' wait: {}", locator);
            return locator;
        } catch (Exception e) {
            logger.error("Timeout waiting for element using fluent wait: {}", locator, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Waits for the page title to contain a certain substring.
     * @param substring The substring to look for in the page title.
     */
    public void waitForTitleContains(String substring) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < WAIT_TIMEOUT_SECONDS * 1000L) {
            String currentTitle = page.title();
            if (currentTitle.contains(substring)) {
                logger.info("Title contains '{}': {}", substring, currentTitle);
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        String msg = "Timeout waiting for title to contain '" + substring + "'. Current title: " + page.title();
        logger.error(msg);
        throw new RuntimeException(msg);
    }

    /**
     * Waits until the document.readyState is 'complete' indicating page load.
     * (Playwright's waitForLoadState is typically enough, but here's a custom approach.)
     */
    public void waitForPageLoad() {
        page.waitForLoadState(); 
        logger.info("Page loaded: {}", page.url());
    }

    /**
     * Utility method to check if the page's title contains a specific string within the default timeout.
     * @param substring The substring to look for in title.
     * @return true if found within the timeout, otherwise false.
     */
    public boolean isPageLoaded(String substring) {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < WAIT_TIMEOUT_SECONDS * 1000L) {
            if (page.title().contains(substring)) {
                logger.info("Page is loaded with title containing '{}': {}", substring, page.title());
                return true;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted while waiting for page load", e);
                return false;
            }
        }
        logger.error("Timeout waiting for page title to contain '{}'. Current title: {}", substring, page.title());
        return false;
    }
}
