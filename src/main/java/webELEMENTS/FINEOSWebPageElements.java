package webELEMENTS;

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
    protected Locator lastEntry;
    protected Locator searchButton;
    protected Locator okButtonPolicy;
    protected Locator footerOkButton;
    protected Locator dateOfHireCalendar;

    protected Locator enterOccupationCodeDiv;
    protected Locator occupationTextbox;
    protected Locator strengthCategoryDropdown;
    protected Locator earningsAmountField;
    protected Locator addEarningsRecordButton;
    protected Locator addContractButton;
    protected Locator policyNumberTextbox;

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

    // ----------- Protected Locators -----------
	protected Locator searchPageOKButton;
    protected Locator buttonOpen;
    protected Locator alertInfoPleaseEnter;
    protected Locator alertGeneralClaim8;
    protected Locator buttonCloseAlert;
    protected Locator linkGeneralClaim8;
    protected Locator buttonEditClaimDetails;
    protected Locator calendarIconDisabilityDate;
    protected Locator buttonCalendarToday;
    protected Locator buttonSaveClaimGeneral;
    protected Locator textOccupation11;
    protected Locator buttonEditOccupationDetails;
    protected Locator inputHoursWorkedPerWeek;
    protected Locator dropdownPlaceOfWork;
    protected Locator buttonSaveOccupation;
    protected Locator headingEarnings;
    protected Locator labelType;
    protected Locator inputType;
    protected Locator tabClaimHub6;
    protected Locator linkComplete;
    protected Locator inputCompletionNotes;
    protected Locator buttonOkNotes;
    protected Locator buttonClaimBenefit;
    protected Locator textSTDBenefitVisible;
    protected Locator linkSTDBenefit;
    protected Locator headingSTDBenefit;
    protected Locator iconAlert;
    protected Locator textInformationPremiumPaid;
    protected Locator textEligibilityMemberEffective;
    protected Locator textApprovalMessage;
    protected Locator textClaimantHaloStatus;
    protected Locator buttonBenefitApprovalEdit;
    protected Locator calendarIconDateAllDataCollected;
    protected Locator buttonFooterOk;
    protected Locator tabTasks10;
    protected Locator buttonClosePanel;
    protected Locator buttonCancelEdit;
    protected Locator tabAlerts2;
    protected Locator linkSuppressValidation;
    protected Locator inputDescription;
    protected Locator tabAlerts1;
    protected Locator linkSuppress;
    protected Locator statusCellApproved;
    protected Locator widgetChooseNextStep;

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

        lastWorkedEmployerCalendar = page.getByLabel("Date last worked employer");
        lastWorkedEmployeeCalendar = page.getByLabel("Date last worked employee");

        selectPartyButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select Party"));

        nameInput = page.getByLabel("Name");
        searchButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true));
        lastEntry = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Client"));

        footerOkButton = page.locator("#footerButtonsBar").getByRole(AriaRole.BUTTON,
                new Locator.GetByRoleOptions().setName("OK"));

        dateOfHireCalendar = page.locator("#OccupationDetailsWidget_un49_DateOfHire_WRAPPER");

        enterOccupationCodeDiv = page.getByTitle("Enter Occupation Code").locator("div").nth(1);

        occupationTextbox = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Type at least 3 characters..."));
        strengthCategoryDropdown = page.locator("#OccupationDetailsWidget_un49_strengthCategoryEnum");

        earningsAmountField = page.locator("#DatedEarningsListviewWidget_un52_Earnings_Amount");

        addEarningsRecordButton = page.getByTitle("Add a new Earnings Record",
                new Page.GetByTitleOptions().setExact(true));

        addContractButton = page.getByTitle("Add Contract");

        policyNumberTextbox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Policy Number"));
        stdCell = page.getByRole(AriaRole.CELL,
                new Page.GetByRoleOptions().setName("12440-STD-11/01/2015").setExact(true));

        linkButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Link").setExact(true));

        diagnosisSearchBox = page.getByTitle("Search diagnosis code id and").locator("div").nth(1);

        diagnosisDropdown = page.locator(
                "#diagnosisCodesListviewWidget_un60_diagnosisCodeIdDynamicDropDownBean_diagnosisCodeIdDynamicDropDownBean_input_transient");

        diagnosisSearchButton = page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Type at least 3 characters..."));
        diagnosisTextbox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type at least 3 characters..."));

        quickAddButton = page.locator("#diagnosisCodesListviewWidget_un60_quickaddbutton");

        nextButton1 = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un26_theNextButton");

        nextButton2 = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un75_theNextButton");

        managedRequirementsSpan = page.locator("#generateManagedRequirementsWidgetTag_un80_090f66cc-bd29-4178-8a72-aa3b9fca3669_WRAPPER span");

        yesButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes"));

        alertsCloseButton = page.locator("#AlertsHeaderWidget").getByText("Close");

        caseKeyInfoBar = page.locator("[id=\"com\\.fineos\\.frontoffice\\.casemanager\\.casekeyinformation\\.CaseKeyInfoBar\"]").getByText("Open");

        logoutButton = page.getByLabel("Logout");

        logoutConfirmationButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes"));

        baaCSR = page.getByLabel("BAaCSR");

        //NEW BENEFIT CREATION
		searchPageOKButton = page.locator("#p5_un7_searchPageOk_cloned");
        buttonOpen = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Open"));
        alertInfoPleaseEnter = page.getByText("Information: Please enter the");
        alertGeneralClaim8 = page.getByText("8General Claim");
        buttonCloseAlert = page.locator("#AlertsHeaderWidget").getByText("Close");
        linkGeneralClaim8 = page.getByText("8General Claim");
        buttonEditClaimDetails = page.locator("[id='ClaimGeneralWidget_un66_com\\.fineos\\.claims\\.generaldetails\\.ClaimGeneralWidgetEDIT_LINKEDIT_BUTTONClaim_Details']");
        calendarIconDisabilityDate = page.locator("#DisabilityInformationReceivedWidget_un19_employeeStatReceivedDateBean_WRAPPER").getByLabel("Open calendar");
        buttonCalendarToday = page.getByTitle("Go to today");
        buttonSaveClaimGeneral = page.locator("#EditClaimGeneralWidget_un16_customSaveButton_cloned");
        textOccupation11 = page.getByText("11Occupation");
        buttonEditOccupationDetails = page.locator("#OccupationListviewWidget_un80_OccupationList_cmdEdit");
        inputHoursWorkedPerWeek = page.getByLabel("Hours worked per week");
        dropdownPlaceOfWork = page.getByLabel("Place of Work");
        buttonSaveOccupation = page.locator("#p10_un7_editPageSave_cloned");
        headingEarnings = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Earnings"));
        labelType = page.locator("label").filter(new Locator.FilterOptions().setHasText("Type"));
        inputType = page.getByLabel("Type");
        tabClaimHub6 = page.getByText("6Claim Hub");
        linkComplete = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Complete"));
        inputCompletionNotes = page.getByLabel("Completion Notes");
        buttonOkNotes = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ok"));
        buttonClaimBenefit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Claim Benefit"));
        textSTDBenefitVisible = page.getByText("STD Benefit number DI-18472-");
        linkSTDBenefit = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("STD Benefit").setExact(true));
        headingSTDBenefit = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("STD Benefit DI-18472-STD-01"));
        iconAlert = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(""));
        textInformationPremiumPaid = page.getByText("Information: Premium Paid");
        textEligibilityMemberEffective = page.getByText("Eligibility: Member Effective");
        textApprovalMessage = page.getByText("Approvals: Please enter the");
        textClaimantHaloStatus = page.getByText("Claimant Halo InReverse Disability Date 03/11/2025 Status Open");
        buttonBenefitApprovalEdit = page.locator("[id='BenefitApprovalWidget_un33_com\\.fineos\\.samples\\.claims\\.benefits\\.benefitapproval\\.DILBenefitApprovalWidgetEDIT_LINKEDIT_BUTTONBenefit_Approval_Details']");
        calendarIconDateAllDataCollected = page.locator("#BenefitApprovalWidget_un17_Date_All_Data_Collected_WRAPPER").getByLabel("Open calendar");
        buttonFooterOk = page.locator("#footerButtonsBar").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("OK"));
        tabTasks10 = page.getByText("10Tasks");
        buttonClosePanel = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close"));
        buttonCancelEdit = page.locator("#p16_un27_editPageCancel_cloned");
        tabAlerts2 = page.getByText("Alerts (2)");
        linkSuppressValidation = page.locator("#AlertsHeaderWidget_un21_suppressValidationLink_63");
        inputDescription = page.getByLabel("Description");
        tabAlerts1 = page.getByText("Alerts (1)");
        linkSuppress = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Suppress"));
        statusCellApproved = page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Approved"));
        widgetChooseNextStep = page.locator("#CHOOSE_NEXT_STEP_WIDGET_un10_StepListView_ScrollPane");


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
        //-*-*-*-*-*-*-*-*//Getter Methods.//-*-*-*-*-*-*-*-*//
    }

    public Locator getPartiesIcon() {
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

    public Locator getlastEntry() {
        return lastEntry;
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

    // -------------- Getters FOR BENEFIT Creation ---------------- //

	public Locator getsearchPageOKButton(){
		return searchPageOKButton;
	}
    public Locator getButtonOpen() {
        return buttonOpen;
    }

    public Locator getAlertInfoPleaseEnter() {
        return alertInfoPleaseEnter;
    }

    public Locator getAlertGeneralClaim8() {
        return alertGeneralClaim8;
    }

    public Locator getButtonCloseAlert() {
        return buttonCloseAlert;
    }

    public Locator getLinkGeneralClaim8() {
        return linkGeneralClaim8;
    }

    public Locator getButtonEditClaimDetails() {
        return buttonEditClaimDetails;
    }

    public Locator getCalendarIconDisabilityDate() {
        return calendarIconDisabilityDate;
    }

    public Locator getButtonCalendarToday() {
        return buttonCalendarToday;
    }

    public Locator getButtonSaveClaimGeneral() {
        return buttonSaveClaimGeneral;
    }

    public Locator getTextOccupation11() {
        return textOccupation11;
    }

    public Locator getButtonEditOccupationDetails() {
        return buttonEditOccupationDetails;
    }

    public Locator getInputHoursWorkedPerWeek() {
        return inputHoursWorkedPerWeek;
    }

    public Locator getDropdownPlaceOfWork() {
        return dropdownPlaceOfWork;
    }

    public Locator getButtonSaveOccupation() {
        return buttonSaveOccupation;
    }

    public Locator getHeadingEarnings() {
        return headingEarnings;
    }

    public Locator getLabelType() {
        return labelType;
    }

    public Locator getInputType() {
        return inputType;
    }

    public Locator getTabClaimHub6() {
        return tabClaimHub6;
    }

    public Locator getLinkComplete() {
        return linkComplete;
    }

    public Locator getInputCompletionNotes() {
        return inputCompletionNotes;
    }

    public Locator getButtonOkNotes() {
        return buttonOkNotes;
    }

    public Locator getButtonClaimBenefit() {
        return buttonClaimBenefit;
    }

    public Locator getTextSTDBenefitVisible() {
        return textSTDBenefitVisible;
    }

    public Locator getLinkSTDBenefit() {
        return linkSTDBenefit;
    }

    public Locator getHeadingSTDBenefit() {
        return headingSTDBenefit;
    }

    public Locator getIconAlert() {
        return iconAlert;
    }

    public Locator getTextInformationPremiumPaid() {
        return textInformationPremiumPaid;
    }

    public Locator getTextEligibilityMemberEffective() {
        return textEligibilityMemberEffective;
    }

    public Locator getTextApprovalMessage() {
        return textApprovalMessage;
    }

    public Locator getTextClaimantHaloStatus() {
        return textClaimantHaloStatus;
    }

    public Locator getButtonBenefitApprovalEdit() {
        return buttonBenefitApprovalEdit;
    }

    public Locator getCalendarIconDateAllDataCollected() {
        return calendarIconDateAllDataCollected;
    }

    public Locator getButtonFooterOk() {
        return buttonFooterOk;
    }

    public Locator getTabTasks10() {
        return tabTasks10;
    }

    public Locator getButtonClosePanel() {
        return buttonClosePanel;
    }

    public Locator getButtonCancelEdit() {
        return buttonCancelEdit;
    }

    public Locator getTabAlerts2() {
        return tabAlerts2;
    }

    public Locator getLinkSuppressValidation() {
        return linkSuppressValidation;
    }

    public Locator getInputDescription() {
        return inputDescription;
    }

    public Locator getTabAlerts1() {
        return tabAlerts1;
    }

    public Locator getLinkSuppress() {
        return linkSuppress;
    }

    public Locator getStatusCellApproved() {
        return statusCellApproved;
    }

    public Locator getWidgetChooseNextStep() {
        return widgetChooseNextStep;
    }
}
