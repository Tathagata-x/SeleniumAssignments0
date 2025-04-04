package actions;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import utils.CommonPlaywrightActions;
import webELEMENTS.FINEOSWebPageElements;

public class CreateBenefitActions extends FINEOSWebPageElements{

    private CommonPlaywrightActions utils;
    private SoftAssert softAssert;

	public CreateBenefitActions(Page page, SoftAssert softAssert) {
	    super(page); // Pass `Page` to parent class
        if (page == null) {
            throw new IllegalArgumentException("❌ ERROR: Playwright `Page` is NULL in FINEOSActions");
        }
        this.softAssert = softAssert;
        this.utils = new CommonPlaywrightActions(page, this.softAssert);
        System.out.println("✅ `CommonPlaywrightActions` initialized in FINEOSActions.");
	}
	public CommonPlaywrightActions getUtils() {
		return utils;
	}
	public void clickPartiesIcon() {
        utils.clickElement(getPartiesIcon(), "Parties ICON");
    }

    public String getPartySearchHeader() {
        String text = getHeaderPartySearch().textContent();
        return text;
    }

    public void searchForParty(String firstName, String lastName) {
        utils.enterText(getInputFirstName(), firstName, "First Name");
        utils.enterText(getInputLastName(), lastName, "Last Name");
        utils.clickElement(getButtonPartySearch(), "Search Button");
    }
	// ✅ Closes the Current Session
    public void closeCurrentSession(BrowserContext context) {
        context.close();

    }
}
