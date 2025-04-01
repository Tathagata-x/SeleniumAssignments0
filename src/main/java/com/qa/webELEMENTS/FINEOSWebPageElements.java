package com.qa.webELEMENTS;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FINEOSWebPageElements {
	protected Page page; // ✅ Ensure page is properly stored

	// 🔹 Navigation & Search
	protected Locator partiesIcon;
	protected Locator headerPartySearch;
	protected Locator inputFirstName;
	protected Locator inputLastName;
	protected Locator buttonPartySearch;
	protected Locator recordMessage;
	protected Locator buttonAdd;
	protected Locator headerAddParty;

	// 🔹 Personal Details
	protected Locator inputSSN_ID;
	protected Locator inputDOB;
	protected Locator selectGender;
	protected Locator selectPartyType;

	// 🔹 Address Information
	protected Locator addAddressButton;
	protected Locator addressLineOne;
	protected Locator cityInput;
	protected Locator selectState;
	protected Locator inputZipCode;
	protected Locator checkMailingAddress;

	// 🔹 Confirmation
	protected Locator okButton;

	protected Locator addButton;
	protected Locator casesTab;
	protected Locator intakeText;
	protected Locator claimIntakeOpeningText;
	protected Locator claimIntakeHeading;
	protected Locator typeDropdown;
	protected Locator nextButton;
	protected Locator partyLink;
	protected Locator incurredDateCalendar;
	protected Locator previousMonthButton;
	protected Locator dateCell5;
	protected Locator accidentSicknessDropdown;
	protected Locator unableToWorkCalendar;
	protected Locator previousMonthNav;
	protected Locator lastWorkedEmployerCalendar;
	protected Locator lastWorkedEmployeeCalendar;
	protected Locator selectPartyButton;

	protected Locator nameInput;
	protected Locator searchButton;
	protected Locator okButtonPolicy;
	protected Locator unknownCell;
	protected Locator footerOkButton;
	protected Locator dateOfHireCalendar;
	protected Locator march2025Cell;
	protected Locator year2025Cell;
	protected Locator year2021Text;
	protected Locator monthMayText;
	protected Locator date20Cell;
	protected Locator enterOccupationCodeDiv;
	protected Locator occupationTextbox;
	protected Locator strengthCategoryDropdown;
	protected Locator earningsAmountField;
	protected Locator addEarningsRecordButton;
	protected Locator addContractButton;
	protected Locator policyNumberTextbox;
	protected Locator firstContractSelection;

	protected Locator stdCell;
	protected Locator linkButton;
	protected Locator diagnosisSearchBox;
	protected Locator diagnosisDropdown;
	protected Locator diagnosisSearchButton;
	protected Locator diagnosisTextbox;
	protected Locator quickAddButton;
	protected Locator nextButton1;
	protected Locator nextButton2;
	protected Locator managedRequirementsSpan;
	protected Locator yesButton;
	protected Locator alertsCloseButton;
	protected Locator caseKeyInfoBar;
	protected Locator logoutButton;
	protected Locator logoutConfirmationButton;
	protected Locator baaCSR;

	// ✅ Getter Methods for Each Locator

	public FINEOSWebPageElements(Page page) {
		if (page == null) {
			System.out.println("❌ ERROR: Playwright `Page` is NULL in FINEOSWebPageElements constructor!");
			throw new IllegalArgumentException("Playwright `Page` object cannot be null!");
		}
		this.page = page;
		System.out.println("✅ Page is initialized in FINEOSWebPageElements: " + page);

		// ✅ Initialize locators inside the constructor
		partiesIcon = page.locator("//span[contains(@id, 'link_Parties')]/parent::span/a");
		headerPartySearch = page.locator("//div[contains(@class, '_title')]/h2");
		inputFirstName = page.locator("//input[contains(@id, 'First_Name')]");
		inputLastName = page.locator("//input[contains(@id, 'Last_Name')]");
		buttonPartySearch = page.locator("//input[contains(@id, 'searchButton')][@type='submit']");
		recordMessage = page.locator(
				"//div[@id='page_messages_container']/table//td[contains(@class, 'raiseMessageText')]//li/span");
		buttonAdd = page.locator("//input[@type='submit'][contains(@id, 'addButton')]");
		headerAddParty = page
				.locator("//div[contains(@id, 'addPersonPopupWidget')]//div[contains(@class, 'title-area')]/span");

		inputSSN_ID = page.locator(
				"//label[contains(text(), 'Identification number')]/parent::div/following-sibling::span/input[@size='9']");
		inputDOB = page.locator(
				"//div[contains(@id, 'dateOfBirth_lbl')]/following-sibling::span[contains(@id, 'dateOfBirth')]/input[@type='text']");
		selectGender = page.locator("//select[contains(@id, 'gender')]");
		selectPartyType = page.locator("//select[contains(@id, '_partyType')]");

		addAddressButton = page.locator("//a[contains(text(), '+ Add address')]");
		addressLineOne = page.locator("//input[contains(@id, 'addressLine1')]");
		cityInput = page.locator("//input[contains(@id, 'city')]");
		selectState = page.locator("//select[contains(@id, 'state')]");
		inputZipCode = page.locator("//input[contains(@id, 'zipCode')]");
		checkMailingAddress = page.getByLabel("Mailing address");

		okButton = page.getByTitle("OK");
		casesTab = page.getByText("14Cases");
		addButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add"));
		intakeText = page.getByText("Intake", new Page.GetByTextOptions().setExact(true));
		claimIntakeOpeningText = page.getByText("Claim Intake Opening", new Page.GetByTextOptions().setExact(true));
		claimIntakeHeading = page.getByRole(AriaRole.HEADING,
				new Page.GetByRoleOptions().setName("CLAIM INTAKE OPENING"));
		typeDropdown = page.getByLabel("Type");
		nextButton = page
				.getByRole(AriaRole.CELL,
						new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true))
				.locator("#DriverDialogWidget_un14_theNextButton");

		partyLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("12MarRockTha Party"));

		incurredDateCalendar = page.getByLabel("Disability Date");

		previousMonthButton = page.getByTitle("Previous month");

		dateCell5 = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("5").setExact(true)).first();

		accidentSicknessDropdown = page.getByLabel("Accident / Sickness");

		unableToWorkCalendar = page.getByLabel("Date first unable to work");

		previousMonthNav = page.getByRole(AriaRole.CELL,
				new Page.GetByRoleOptions().setName(" Previous Month").setExact(true));

		lastWorkedEmployerCalendar = page.locator("#claimEmploymentDetailsWidgetTag_un48_DateLastWorkedEmplr_WRAPPER");
		lastWorkedEmployeeCalendar = page.locator("#claimEmploymentDetailsWidgetTag_un48_DateLastWorkedEmplee_WRAPPER");

		selectPartyButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select Party"));

		nameInput = page.getByLabel("Name");
		searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true));

		unknownCell = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Unknown")).nth(1);

		footerOkButton = page.locator("#footerButtonsBar").getByRole(AriaRole.BUTTON,
				new Locator.GetByRoleOptions().setName("OK"));

		dateOfHireCalendar = page.locator("#OccupationDetailsWidget_un49_DateOfHire_WRAPPER");

		march2025Cell = page.getByRole(AriaRole.CELL,
				new Page.GetByRoleOptions().setName("March 2025 Toggle Date and Time Screens").setExact(true));

		year2025Cell = page.getByRole(AriaRole.CELL,
				new Page.GetByRoleOptions().setName("2025 Toggle Date and Time Screens").setExact(true));

		year2021Text = page.getByText("2021", new Page.GetByTextOptions().setExact(true));

		monthMayText = page.getByText("May");

		date20Cell = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("20").setExact(true));

		enterOccupationCodeDiv = page.getByTitle("Enter Occupation Code").locator("div").nth(1);

		occupationTextbox = page.getByRole(AriaRole.TEXTBOX,
				new Page.GetByRoleOptions().setName("Type at least 3 characters..."));
		strengthCategoryDropdown = page.locator("#OccupationDetailsWidget_un49_strengthCategoryEnum");

		earningsAmountField = page.locator("#DatedEarningsListviewWidget_un52_Earnings_Amount");

		addEarningsRecordButton = page.getByTitle("Add a new Earnings Record",
				new Page.GetByTitleOptions().setExact(true));

		addContractButton = page.getByTitle("Add Contract");

		policyNumberTextbox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Policy Number"));
		okButtonPolicy = page.locator("#p10_un7_searchPageOk_cloned");

		firstContractSelection = page.locator("#ContractsListviewWidget_un15_contractslistviewMemberId0");
		stdCell = page.getByRole(AriaRole.CELL,
				new Page.GetByRoleOptions().setName("12440-STD-11/01/2015").setExact(true));

		linkButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Link").setExact(true));

		diagnosisSearchBox = page.getByTitle("Search diagnosis code id and").locator("div").nth(1);

		diagnosisDropdown = page.locator(
				"#diagnosisCodesListviewWidget_un60_diagnosisCodeIdDynamicDropDownBean_diagnosisCodeIdDynamicDropDownBean_input_transient");

		diagnosisSearchButton = page.getByRole(AriaRole.TEXTBOX,
				new Page.GetByRoleOptions().setName("Type at least 3 characters..."));
		diagnosisTextbox=page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Type at least 3 characters..."));

		quickAddButton=page.locator("#diagnosisCodesListviewWidget_un60_quickaddbutton");

		nextButton1=page.getByRole(AriaRole.CELL,new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un26_theNextButton");

		nextButton2=page.getByRole(AriaRole.CELL,new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un75_theNextButton");

		managedRequirementsSpan=page.locator("#generateManagedRequirementsWidgetTag_un80_090f66cc-bd29-4178-8a72-aa3b9fca3669_WRAPPER span");

		yesButton=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Yes"));

		alertsCloseButton=page.locator("#AlertsHeaderWidget").getByText("Close");

		caseKeyInfoBar=page.locator("[id=\"com\\.fineos\\.frontoffice\\.casemanager\\.casekeyinformation\\.CaseKeyInfoBar\"]").getByText("Open");

		logoutButton=page.getByLabel("Logout");

		logoutConfirmationButton=page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Yes"));

		baaCSR=page.getByLabel("BAaCSR");

		// ✅ Getter Methods

		/*
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 * private Locator = page.locator(""); private Locator = page.locator("");
		 */
		//

		
	} public Locator getPartiesIcon(){
		return partiesIcon;
	}

	public Locator getHeaderPartySearch() {
		return headerPartySearch;
	}

	public Locator getInputFirstName() {
		return inputFirstName;
	}

	public Locator getInputLastName() {
		return inputLastName;
	}

	public Locator getButtonPartySearch() {
		return buttonPartySearch;
	}

	public Locator getRecordMessage() {
		return recordMessage;
	}

	public Locator getButtonAdd() {
		return buttonAdd;
	}

	public Locator getHeaderAddParty() {
		return headerAddParty;
	}

	public Locator getInputSSNID() {
		return inputSSN_ID;
	}

	public Locator getinputDOB() {
		return inputDOB;
	}

	public Locator getselectGender() {
		return selectGender;
	}

	public Locator getselectPartyType() {
		return selectPartyType;
	}

	public Locator getaddAddressButton() {
		return addAddressButton;
	}

	public Locator getaddressLineOne() {
		return addressLineOne;
	}

	public Locator getcityInput() {
		return cityInput;
	}

	public Locator getselectState() {
		return selectState;
	}

	public Locator getinputZipCode() {
		return inputZipCode;
	}

	public Locator getcheckMailingAddress() {
		return checkMailingAddress;
	}

	public Locator getokButton() {
		return okButton;
	}

	public Locator getCasesTab() {
		return casesTab;
	}

	public Locator getAddButton() {
		return addButton;
	}

	public Locator getIntakeText() {
		return intakeText;
	}

	public Locator getClaimIntakeOpeningText() {
		return claimIntakeOpeningText;
	}

	public Locator getClaimIntakeHeading() {
		return claimIntakeHeading;
	}

	public Locator getTypeDropdown() {
		return typeDropdown;
	}

	public Locator getNextButton() {
		return nextButton;
	}

	public Locator getPartyLink(String partyName) {
	    return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(partyName));
	}


	public Locator getIncurredDateCalendar() {
		return incurredDateCalendar;
	}

	public Locator getAccidentSicknessDropdown() {
		return accidentSicknessDropdown;
	}

	public Locator getUnableToWorkCalendar() {
		return unableToWorkCalendar;
	}

	public Locator getPreviousMonthNav() {
		return previousMonthNav;
	}

	public Locator getLastWorkedEmployerCalendar() {
		return lastWorkedEmployerCalendar;
	}

	public Locator getLastWorkedEmployeeCalendar() {
		return lastWorkedEmployeeCalendar;
	}

	public Locator getSelectPartyButton() {
		return selectPartyButton;
	}

	public Locator getNameInput() {
		return nameInput;
	}

	public Locator getSearchButton() {
		return searchButton;
	}

	public Locator getUnknownCell() {
		return unknownCell;
	}

	public Locator getFooterOkButton() {
		return footerOkButton;
	}

	public Locator getDateOfHireCalendar() {
		return dateOfHireCalendar;
	}



	public Locator getEnterOccupationCodeDiv() {
		return enterOccupationCodeDiv;
	}

	public Locator getOccupationTextbox() {
		return occupationTextbox;
	}

	public Locator getStrengthCategoryDropdown() {
		return strengthCategoryDropdown;
	}

	public Locator getEarningsAmountField() {
		return earningsAmountField;
	}

	public Locator getAddEarningsRecordButton() {
		return addEarningsRecordButton;
	}

	public Locator getAddContractButton() {
		return addContractButton;
	}

	public Locator getPolicyNumberTextbox() {
		return policyNumberTextbox;
	}

	public Locator getFirstContractSelection() {
		return okButtonPolicy; //okbutton within the policy search page. 
	}

	public Locator getStdCell() {
		return stdCell;
	}

	public Locator getLinkButton() {
		return linkButton;
	}

	public Locator getDiagnosisSearchBox() {
		return diagnosisSearchBox;
	}

	public Locator getDiagnosisDropdown() {
		return diagnosisDropdown;
	}

	public Locator getDiagnosisSearchButton() {
		return diagnosisSearchButton;
	}

	// ✅ Getter Methods for Each Locator
	public Locator getDiagnosisTextbox() {
		return diagnosisTextbox;
	}

	public Locator getQuickAddButton() {
		return quickAddButton;
	}

	public Locator getNextButton1() {
		return nextButton1;
	}

	public Locator getNextButton2() {
		return nextButton2;
	}

	public Locator getManagedRequirementsSpan() {
		return managedRequirementsSpan;
	}

	public Locator getYesButton() {
		return yesButton;
	}

	public Locator getAlertsCloseButton() {
		return alertsCloseButton;
	}

	public Locator getCaseKeyInfoBar() {
		return caseKeyInfoBar;
	}

	public Locator getLogoutButton() {
		return logoutButton;
	}

	public Locator getLogoutConfirmationButton() {
		return logoutConfirmationButton;
	}

	public Locator getBaaCSR() {
		return baaCSR;
	}

	/*
	 * 
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;} public Locator get() {return ;}
	 * public Locator get() {return ;} public Locator get() {return ;} public
	 * Locator get() {return ;} public Locator get() {return ;} public Locator get()
	 * {return ;} public Locator get() {return ;}
	 */

}
