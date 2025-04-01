package com.qa.actions;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.qa.utils.CommonPlaywrightActions;
import com.qa.webELEMENTS.FINEOSWebPageElements;

public class FINEOSActions extends FINEOSWebPageElements {

	private CommonPlaywrightActions utils;
	private SoftAssert softAssert;

	public FINEOSActions(Page page, SoftAssert softAssert) {
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

	public boolean isNoRecordMessageVisible() {
		utils.waitForElementVisibility(getRecordMessage(), 5, "No Records Found Message");
		boolean visible = getRecordMessage().isVisible();
 
		return visible;
	}

	public void clickAddParty() {
		utils.clickElement(getButtonAdd(), "Add Party Button");
 
	}

	// 3. Page Actions/Methods
	public void enterIdentificationNumber(String idNumber) {
		utils.enterText(getInputSSNID(), idNumber, "Identification Number");
	}

	public void enterPersonalDetails(String dob, String gender, String typeParty) {
		utils.enterText(getinputDOB(), dob, "Date of Birth");
		utils.comboSelectValue(getselectGender(), gender, "Gender");
		utils.comboSelectValue(getselectPartyType(), typeParty, "Party Type");
	}

	public void addAddress(String address, String city, String state, String zip) {
		utils.clickElement(getaddAddressButton(), "Add Address Link");
		utils.enterText(getaddressLineOne(), address, "Address Line 1");
		utils.enterText(getcityInput(), city, "City");
		utils.clickElement(getcheckMailingAddress(), "Mailing Address Checkbox");
		utils.comboSelectValue(getselectState(), state, "State Dropdown");
		utils.enterText(getinputZipCode(), zip, "Zip Code");
		utils.clickElement(getokButton(), "Confirm Button");
	}

	// ✅ Clicks "14 Cases"
	public void clickCasesTab() {
		utils.clickElement(getCasesTab(), "Cases Tab (14Cases)");
 
	}

	// ✅ Clicks "Add" button
	public void clickAddButton() {
		utils.clickElement(getAddButton(), "Add Button");
 
	}

	// ✅ Validates "Intake" and "Claim Intake Opening" text visibility
	public void validateIntakeSection() {
		utils.assertElementDisplayed(getIntakeText(), "Intake Section");
		utils.assertElementDisplayed(getClaimIntakeOpeningText(), "Claim Intake Opening Section");
		utils.assertElementDisplayed(getClaimIntakeHeading(), "Claim Intake Opening Heading");

		}

	// ✅ Selects Type (Dropdown)
	public void selectClaimType(String typeValue) {
		utils.comboSelectValue(getTypeDropdown(), typeValue, "Claim Type");
 
	}

	// ✅ Clicks "Next" button in the intake section
	public void clickNextButton() {
		utils.clickElement(getNextButton(), "Next Button in Intake Section");
 
	}

	// ✅ Single reusable method to select Incurred Date (or any calendar date)
	public void selectIncurredDate(String acciDate) {
        utils.enterText(getIncurredDateCalendar(), acciDate, "Date of accident.");
	}



	// ✅ Selects an option from the "Accident / Sickness" dropdown
	public void selectAccidentSickness(String value) {
		utils.comboSelectValue(getAccidentSicknessDropdown(), value, "Accident / Sickness");
 
	}

	// ✅ Opens "Date First Unable to Work" calendar
	public void openUnableToWorkCalendar(String unableToWorkDate) {
		utils.enterText(getUnableToWorkCalendar(), unableToWorkDate, "Date First Unable to Work Calendar");
 
	}

	// ✅ Opens "Date Last Worked (Employer)" calendar
	public void openLastWorkedEmployerCalendar(String lastWorkedEmployerCalendarDate) {
		utils.enterText(getLastWorkedEmployerCalendar(), lastWorkedEmployerCalendarDate, "Date Last Worked (Employer) Calendar");
 
	}

	// ✅ Opens "Date Last Worked (Employee)" calendar
	public void openLastWorkedEmployeeCalendar(String lastWorkedEmployeeCalendarDate) {
		utils.enterText(getLastWorkedEmployeeCalendar(), lastWorkedEmployeeCalendarDate, "Date Last Worked (Employee) Calendar");

	}

	// ✅ Clicks "Select Party" button
	public void clickSelectParty() {
		utils.clickElement(getSelectPartyButton(), "Select Party Button");
	}

	// ✅ Fills "Name" input with provided value
	public void enterEmployerName(String partyName) {
		utils.enterText(getNameInput(), partyName, "Party Name Input");
 
	}

	// ✅ Clicks "Search" button
	public void clickSearchButton() {
		utils.clickElement(getSearchButton(), "Search Button");
 
	}


	// ✅ Clicks "OK" button in the footer
	public void clickFooterOkButton() {
		utils.clickElement(getFooterOkButton(), "Footer OK Button");
 
	}

	// ✅ Opens "Date of Hire" calendar
	public void openDateOfHireCalendar(String dateOfHireData) {
		utils.enterText(getDateOfHireCalendar(), dateOfHireData, "Date of Hire Calendar");
 
	}


	// ✅ Fills "Occupation" field with given text
	public void enterOccupation(String occupation) {
		utils.enterText(getOccupationTextbox(), occupation, "Occupation Textbox");
 
	}

	// ✅ Selects strength category
	public void selectStrengthCategory(String value) {
		utils.comboSelectValue(getStrengthCategoryDropdown(), value, "Strength Category");
 
	}

	// ✅ Fills earnings amount field
	public void enterEarningsAmount(String amount) {
		utils.enterText(getEarningsAmountField(), amount, "Earnings Amount");
 
	}

	// ✅ Clicks "Add a new Earnings Record" button
	public void clickAddEarningsRecord() {
		utils.clickElement(getAddEarningsRecordButton(), "Add Earnings Record Button");
 
	}

	// ✅ Clicks "Add Contract" button
	public void clickAddContract() {
		utils.clickElement(getAddContractButton(), "Add Contract Button");
 
	}

	// ✅ Fills policy number
	public void enterPolicyNumber(String policyNumber) {
		utils.enterText(getPolicyNumberTextbox(), policyNumber, "Policy Number Textbox");
 
		utils.clickElement(getSearchButton(), "Search Button");
 
		utils.clickElement(getFooterOkButton(), "Footer OK Button");
	}

	// ✅ Clicks the STD Cell
	public void clickStdCell() {
		utils.clickElement(getStdCell(), "12440-STD-11/01/2015 Cell");
 
	}

	// ✅ Clicks "Link" Button
	public void clickLinkButton() {
		utils.clickElement(getLinkButton(), "Link Button");
 
	}

	// ✅ Clicks on the "Search Diagnosis Code ID"
	public void clickDiagnosisSearchBox() {
		utils.clickElement(getDiagnosisSearchBox(), "Search Diagnosis Code ID Box");
 
	}

	// ✅ Fills Diagnosis Search Input
	public void enterDiagnosisSearch(String text) {
		utils.enterText(getDiagnosisSearchButton(), text, "Diagnosis Search Input");
 
	}

	// ✅ Fills Diagnosis Dropdown
	public void enterDiagnosisDropdown(String text) {
		utils.enterText(getDiagnosisDropdown(), text, "Diagnosis Dropdown");
 
	}

	

	    // ✅ Double clicks the Diagnosis Search Input
	    public void doubleClickDiagnosisSearch() {
	        utils.clickElement(getDiagnosisTextbox(), "Diagnosis Search Input");
 
	    }

	    // ✅ Clicks the Quick Add Button
	    public void clickQuickAddButton() {
	        utils.clickElement(getQuickAddButton(), "Quick Add Button");
 
	    }

	    // ✅ Clicks the Next Button (Step 1)
	    public void clickNextButton1() {
	        utils.clickElement(getNextButton1(), "Next Button - Step 1");
 
	    }

	    // ✅ Clicks Managed Requirements Span Twice
	    public void clickManagedRequirementsSpan() {
	        utils.clickElement(getManagedRequirementsSpan(), "Managed Requirements Span");
	        utils.clickElement(getManagedRequirementsSpan(), "Managed Requirements Span");
 
	    }

	    // ✅ Clicks the Next Button (Step 2)
	    public void clickNextButton2() {
	        utils.clickElement(getNextButton2(), "Next Button - Step 2");
 
	    }

	    // ✅ Clicks "Yes" button
	    public void clickYesButton() {
	        utils.clickElement(getYesButton(), "Yes Button");
 
	    }

	    // ✅ Closes Alerts
	    public void closeAlerts() {
	        utils.clickElement(getAlertsCloseButton(), "Alerts Close Button");
 
	    }

	    // ✅ Validates the "Open" case key info bar
	    public void verifyCaseKeyInfoBar() {
	        utils.assertElementDisplayed(getCaseKeyInfoBar(), "Case Key Info Bar");
 
	    }

	    // ✅ Clicks "BAaCSR" Label
	    public void clickBaaCSR() {
	        utils.clickElement(getBaaCSR(), "BAaCSR Label");
 
	    }

	    // ✅ Logs out from the system
	    public void logout() {
	        utils.clickElement(getLogoutButton(), "Logout Button");
	        utils.clickElement(getLogoutConfirmationButton(), "Logout Confirmation Button");
 
	    }

	    // ✅ Closes the Current Session
	    public void closeCurrentSession(BrowserContext context) {
	        context.close();
 
	    }

	    // ✅ Opens a New Browser Session with a Different User
	    public Page openNewSession(Browser browser, String username, String password) {
	        BrowserContext context2 = browser.newContext();  // Creating a fresh session
	        Page page2 = context2.newPage();
	        String url2 = "https://" + username + ":" + password + "@idt2-claims-webapp.oneamerica.fineos.com";
	        page2.navigate(url2);
 
	        return page2;
	    }
	}


