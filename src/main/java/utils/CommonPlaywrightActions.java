package utils;

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

public class CommonPlaywrightActions extends PlaywrightWaitUtils {

    private final SoftAssert softAssert;
    private static final String SCREENSHOT_DIR = "./screenshots/";
    private static final int DEFAULT_RETRY_LIMIT = 2; // Increased retry limit
    private int retryLimit;
    private static final int POLLING_INTERVAL = 200; // Polling interval in milliseconds
    private static final int MAX_WAIT_TIME = 5000; // Maximum wait time in milliseconds

    /**
     * Constructor for EnhancedPlaywrightActions.
     *
     * @param page The Playwright Page instance
     * @param softAssert The TestNG SoftAssert for test validations
     */
    public CommonPlaywrightActions(Page page, SoftAssert softAssert) {
        super(page); // Call the parent constructor with the page object

        if (softAssert == null) {
            throw new IllegalArgumentException("SoftAssert cannot be null!");
        }
        this.softAssert = softAssert;
        this.retryLimit = DEFAULT_RETRY_LIMIT;
    }

    public void setRetryLimit(int retryLimit) {
        this.retryLimit = retryLimit;
    }

    // ======================= Core Interaction Methods =======================
    public void comboSelectValue(Locator element, String valueOrText, String description) {
        try {
            waitForElementToDisplay(element); // Using inherited wait method
            element.selectOption(valueOrText);
            logSuccess(valueOrText + " selected by value in " + description);
        } catch (Exception e) {
            handleRetry(() -> element.selectOption(new SelectOption().setLabel(valueOrText)),
                    element, description, e);
        }
    }

    public void enterText(Locator element, String value, String description) {
        if (StringUtils.isBlank(value)) {
            logFailure("No value provided for " + description);
            return;
        }

        try {
            waitForElementToDisplay(element); // Using inherited wait method
            element.fill(value.trim());
            logSuccess("'" + value + "' entered in " + description);
        } catch (Exception e) {
            handleRetry(() -> element.fill(value.trim()),
                    element, description, e);
        }
    }

    public void clickElement(Locator element, String elementName) {
        robustClick(element, elementName, DEFAULT_RETRY_LIMIT);
    }

    private void robustClick(Locator element, String elementName, int maxRetries) {
        int attempt = 0;
        while (attempt <= maxRetries) {
            try {
                if (isElementReady(element, MAX_WAIT_TIME)) {
                    scrollToElement(element, elementName); // Ensure element is in view
                    element.click();
                    logSuccess(elementName + " clicked on attempt " + (attempt + 1));
                    return; // Click successful, exit the loop
                } else {
                    logFailure(elementName + " not ready after " + MAX_WAIT_TIME + "ms on attempt " + (attempt + 1));
                    attachScreenshot(elementName + " not ready after " + MAX_WAIT_TIME + "ms on attempt " + (attempt + 1));
                }
            } catch (Exception e) {
                logFailure("Click failed on attempt " + attempt + " for " + elementName + ": " + e.getMessage());
                attachScreenshot("Click failure on attempt " + attempt + " for " + elementName);
            }
            attempt++;
            if (attempt > maxRetries) {
                handleError("click", elementName, new Exception("Element not clickable after multiple retries")); // If all retries fail, handle the error
            }
        }
    }

    private boolean isElementReady(Locator element, int maxWaitTime) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < maxWaitTime) {
            try {
                if (element.isVisible() && !element.isDisabled()) {
                    return true;
                }
            } catch (Exception e) {
                // Ignore exceptions during polling
            }
            try {
                Thread.sleep(POLLING_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    public void doubleClickElement(Locator element, String elementName) {
        try {
            waitForElementToDisplay(element);
            element.dblclick();
            logSuccess("Double-clicked " + elementName);
        } catch (Exception e) {
            handleRetry(element::dblclick, element, elementName, e);
        }
    }

    // =================== NEW KEYBOARD INTERACTIONS ===================
    public void pressKey(String key, String description) {
        try {
            page.keyboard().press(key);
            logSuccess("Pressed key: " + key + " " + description);
        } catch (Exception e) {
            handleError("press key", description, e);
        }
    }

    public void typeText(String text, String description) {
        try {
            page.keyboard().type(text);
            logSuccess("Typed text: " + text + " " + description);
        } catch (Exception e) {
            handleError("type text", description, e);
        }
    }

    // =================== NEW MOUSE INTERACTIONS ===================
    /* 
    public void rightClick(Locator element, String description) {
        try {
            waitForElementToDisplay(element);
            element.click(new Locator.ClickOptions().setButton("right"));
            logSuccess("Right-clicked on: " + description);
        } catch (Exception e) {
            handleRetry(() -> element.click(new Locator.ClickOptions().setButton(MouseButton, "right")), 
                    element, description, e);
        }
    }
     */
    public void hoverElement(Locator element, String description) {
        try {
            waitForElementToDisplay(element);
            element.hover();
            logSuccess("Hovered over: " + description);
        } catch (Exception e) {
            handleRetry(element::hover, element, description, e);
        }
    }

    public void dragAndDrop(Locator source, Locator target, String description) {
        try {
            waitForElementToDisplay(source);
            waitForElementToDisplay(target);
            source.dragTo(target);
            logSuccess("Dragged " + description + " to target");
        } catch (Exception e) {
            handleError("drag and drop", description, e);
        }
    }

    // =================== NEW TEXT AND ATTRIBUTE VALIDATIONS ===================
    public String getElementText(Locator element, String description) {
        try {
            waitForElementToDisplay(element);
            String text = element.textContent();
            logInfo("Got text from " + description + ": " + text);
            return text;
        } catch (Exception e) {
            handleError("get text from", description, e);
            return "";
        }
    }

    public void assertElementText(Locator element, String expectedText, String description) {
        try {
            waitForElementToDisplay(element);
            String actualText = element.textContent().trim();
            if (actualText.equals(expectedText)) {
                logSuccess("Text verification passed for " + description + ": '" + expectedText + "'");
            } else {
                logFailure("Text verification failed for " + description
                        + ". Expected: '" + expectedText + "', Actual: '" + actualText + "'");
                softAssert.assertEquals(actualText, expectedText,
                        "Text verification failed for " + description);
            }
        } catch (Exception e) {
            handleError("verify text of", description, e);
        }
    }

    public String getAttribute(Locator element, String attribute, String description) {
        try {
            waitForElementToDisplay(element);
            String value = element.getAttribute(attribute);
            logInfo("Got attribute " + attribute + " from " + description + ": " + value);
            return value;
        } catch (Exception e) {
            handleError("get attribute from", description, e);
            return "";
        }
    }

    // =================== NEW FILE UPLOAD HANDLING ===================
    public void uploadFile(Locator fileInput, String filePath, String description) {
        try {
            waitForElementToDisplay(fileInput);
            fileInput.setInputFiles(Paths.get(filePath));
            logSuccess("Uploaded file: " + filePath + " to " + description);
        } catch (Exception e) {
            handleError("upload file to", description, e);
        }
    }

    // =================== NEW FRAME AND DIALOG HANDLING ===================
    public void switchToFrame(Locator frameLocator, String description) {
        try {
            waitForElementToDisplay(frameLocator);
            page.frameLocator(frameLocator.toString()).locator("body");
            logSuccess("Switched to frame: " + description);
        } catch (Exception e) {
            handleError("switch to frame", description, e);
        }
    }

    public void handleAlert(boolean accept, String description) {
        try {
            page.onDialog(dialog -> {
                if (accept) {
                    dialog.accept();
                    logSuccess("Accepted alert: " + dialog.message());
                } else {
                    dialog.dismiss();
                    logSuccess("Dismissed alert: " + dialog.message());
                }
            });
            logInfo("Alert handler set up for: " + description);
        } catch (Exception e) {
            handleError("handle alert", description, e);
        }
    }

    // =================== NEW JAVASCRIPT EXECUTION ===================
    public Object executeJavaScript(String script, String description) {
        try {
            Object result = page.evaluate(script);
            logSuccess("Executed JavaScript: " + description);
            return result;
        } catch (Exception e) {
            handleError("execute JavaScript", description, e);
            return null;
        }
    }

    // ======================= Element State Methods =======================
    public boolean assertElementDisplayed(Locator element, String elementName) {
        try {
            waitForElementToDisplay(element);
            logSuccess(elementName + " is visible");
            return true;
        } catch (Exception e) {
            handleRetry(() -> {
                if (!element.isVisible()) try {
                    throw new Exception("Element still not visible");
                } catch (Exception ex) {
                }
            }, element, elementName, e);
            return false;
        }
    }

    // Using the parent class's waitForElementVisibility with our reporting
    @Override
    public Locator waitForElementVisible(Locator locator) {
        try {
            return super.waitForElementVisible(locator);
        } catch (Exception e) {
            attachScreenshot("Failed to wait for element to be visible");
            throw e;
        }
    }

    // ======================= Screenshot Methods =======================
    public void takeScreenshot(String testMethodName) {
        try {
            Path dirPath = Paths.get(SCREENSHOT_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            String path = SCREENSHOT_DIR + testMethodName + "_" + timestamp + ".png";

            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
            logInfo("Screenshot saved: " + path);
        } catch (IOException e) {
            logFailure("Failed to save screenshot: " + e.getMessage());
        }
    }

    // ======================= Helper Methods =======================
    private void handleRetry(Runnable action, Locator element, String elementName, Exception initialError) {
        int attempt = 0;
        while (attempt < retryLimit) {
            try {
                scrollToElement(element, elementName);
                action.run();
                logSuccess("Succeeded after scrolling to " + elementName);
                return;
            } catch (Exception retryError) {
                attempt++;
                if (attempt >= retryLimit) {
                    handleError("operation", elementName, retryError);
                }
            }
        }
    }

    public void scrollToElement(Locator element, String elementDescription) {
        try {
            element.scrollIntoViewIfNeeded();
            logInfo("Scrolled to: " + elementDescription);
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
