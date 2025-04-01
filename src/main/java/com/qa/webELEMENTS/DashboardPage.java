package com.qa.webELEMENTS;

import com.microsoft.playwright.Page;

public class DashboardPage {

    private Page page;

    // 1. String Locators - OR
    private String partiesTab = "label:has-text('Parties')";
    private String firstNameField = "label:has-text('First Name')";
    private String lastNameField = "label:has-text('Last Name')";
    private String searchButton = "button:has-text('Search')";
    private String noRecordsText = "text='There are no records found'";
    private String addButton = "button:has-text('Add')";

    // 2. Page Constructor
    public DashboardPage(Page page) {
        this.page = page;
    }


    public void searchForParty(String firstName, String lastName) {
        page.click(partiesTab);
        page.fill(firstNameField, firstName);
        page.press(firstNameField, "Tab");
        page.fill(lastNameField, lastName);
        page.click(searchButton);
    }

    public boolean isNoRecordsFoundVisible() {
        return page.isVisible(noRecordsText);
    }

    public void clickAddParty() {
        page.click(addButton);
    }
}
