package com.qa.webELEMENTS;

import com.microsoft.playwright.Page;

public class AddPartyPage {

    private Page page;

    // 1. String Locators - OR
    private String idNumberField = "#personIdentificationFrameWidget_un29_idNumber_idNumber_0";
    private String dobField = "label:has-text('Date of birth')";
    private String genderDropdown = "label:has-text('Gender')";
    private String partyTypeDropdown = "label:has-text('Party Type')";
    private String addAddressLink = "a:has-text('+ Add address')";
    private String addressField = "label:has-text('Address line 1')";
    private String zipCodeField = "label:has-text('Zip code')";
    private String mailingCheckbox = "label:has-text('Mailing address')";
    private String stateDropdown = "label:has-text('State')";
    private String okButton = "button[title='OK']";

    // 2. Page Constructor
    public AddPartyPage(Page page) {
        this.page = page;
    }

    // 3. Page Actions/Methods
    public void enterIdentificationNumber(String idNumber) {
        page.fill(idNumberField, idNumber);
    }

    public void enterPersonalDetails(String dob, String gender, String typeParty) {
        page.fill(dobField, dob);
        page.selectOption(genderDropdown, gender);
        page.selectOption(partyTypeDropdown, typeParty);
        
    }

    public void addAddress(String address, String zip, String state) {
        page.click(addAddressLink);
        page.fill(addressField, address);
        page.fill(zipCodeField, zip);
        page.check(mailingCheckbox);
        page.selectOption(stateDropdown, state);
    }

    public void confirm() {
        page.click(okButton);
    }
}

