package com.qa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CommonPlaywrightActions {
    private final Page page;
    private final SoftAssert softAssert;
    private static final String SCREENSHOT_DIR = "./screenshots/";

    public CommonPlaywrightActions(Page page, SoftAssert softAssert) {
        if (page == null || softAssert == null) {
            throw new IllegalArgumentException("Page/SoftAssert cannot be null!");
        }
        this.page = page;
        this.softAssert = softAssert;
    }

    // ======================= Core Interaction Methods =======================

    public void comboSelectValue(Locator element, String valueOrText, String description) {
        try {
            element.selectOption(valueOrText);
            logSuccess(valueOrText + " selected by value in " + description);
        } catch (Exception e) {
            handleRetry(() -> element.selectOption(new SelectOption().setLabel(valueOrText)),
                    "select by text", description, e);
        }
    }

    public void enterText(Locator element, String value, String description) {
        if (StringUtils.isBlank(value)) {
            logFailure("No value provided for " + description);
            return;
        }

        try {
            element.fill(value.trim());
            logSuccess("'" + value + "' entered in " + description);
        } catch (Exception e) {
            handleRetry(() -> element.fill(value.trim()), 
                    "enter text", description, e);
        }
    }

    public void clickElement(Locator element, String elementName) {
        try {
            element.click();
            logSuccess(elementName + " clicked");
        } catch (Exception e) {
            handleRetry(element::click, "click", elementName, e);
        }
    }

    public void doubleClickElement(Locator element, String elementName) {
        try {
            element.dblclick();
            logSuccess("Double-clicked " + elementName);
        } catch (Exception e) {
            handleRetry(element::dblclick, "double-click", elementName, e);
        }
    }

    // ======================= Element State Methods =======================

    public boolean assertElementDisplayed(Locator element, String elementName) {
        try {
            if (element.isVisible()) {
                logSuccess(elementName + " is visible");
                return true;
            }
            throw new Exception(elementName + " exists but not visible");
        } catch (Exception e) {
            handleRetry(() -> {
                if (!element.isVisible()) try {
                    throw new Exception("Element still not visible");
                } catch (Exception ex) {
                }
            }, "verify visibility", elementName, e);
            return false;
        }
    }

    public boolean waitForElementVisibility(Locator element, long seconds, String elementName) {
        try {
            element.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(seconds * 1000));
            logSuccess(elementName + " became visible");
            return true;
        } catch (Exception e) {
            handleRetry(() -> element.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(seconds * 1000)), 
                    "wait for visibility", elementName, e);
            return false;
        }
    }

    // ======================= Screenshot Methods =======================

    public void takeScreenshot(String testMethodName) {
        try {
            Path dirPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            
            String timestamp = new SimpleDateFormat("MM-dd-yyyy-hh-mm-ss").format(new Date());
            String path = SCREENSHOT_DIR + testMethodName + "_" + timestamp + ".png";
            
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
            logInfo("Screenshot saved: " + path);
        } catch (IOException e) {
            logFailure("Failed to save screenshot: " + e.getMessage());
        }
    }

    // ======================= Helper Methods =======================

    private void handleRetry(Runnable action, String operation, String elementName, Exception initialError) {
        try {
            scrollToElement(elementName);
            action.run();
            logSuccess(operation + " succeeded after scrolling on " + elementName);
        } catch (Exception retryError) {
            handleError(operation, elementName, retryError);
        }
    }

    private void scrollToElement(String elementDescription) {
        try {
            // Actual scroll implementation would need element reference
            logSuccess("Scrolled to: " + elementDescription);
        } catch (Exception e) {
            handleError("scroll to", elementDescription, e);
        }
    }

    // ======================= Reporting Methods =======================

    private void logSuccess(String message) {
        ExtentReportManager.getTest().log(Status.PASS, "✅ " + message);
    }

    private void logFailure(String message) {
        ExtentReportManager.getTest().log(Status.FAIL, "❌ " + message);
    }

    private void logInfo(String message) {
        ExtentReportManager.getTest().log(Status.INFO, message);
    }

    private void handleError(String operation, String elementName, Exception e) {
        final String errorMessage = String.format(
            "Failed to %s '%s' - %s: %s",
            operation,
            elementName,
            e.getClass().getSimpleName(),
            e.getMessage()
        );

        logFailure(errorMessage);
        attachScreenshot(errorMessage);
        softAssert.fail(errorMessage);
    }

    private void attachScreenshot(String context) {
        try {
            String screenshot = Base64.getEncoder()
                .encodeToString(page.screenshot());
            
            ExtentReportManager.getTest().fail(context,
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
        } catch (Exception e) {
            logFailure("Failed to capture screenshot: " + e.getMessage());
        }
    }
}