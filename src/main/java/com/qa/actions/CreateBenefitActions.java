package com.qa.actions;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.qa.utils.CommonPlaywrightActions;
import com.qa.utils.ExtentReportManager;
import com.qa.webELEMENTS.FINEOSWebPageElements;

public class CreateBenefitActions extends FINEOSWebPageElements {

	private CommonPlaywrightActions utils;
	private SoftAssert softAssert;
	private ExtentTest extentTest;
	public CreateBenefitActions(Page page, SoftAssert softAssert, ExtentTest extentTest) {
	    super(page); // Pass the Page to the parent class

	    if (page == null) {
	        throw new IllegalArgumentException("❌ ERROR: Playwright `Page` is NULL in CreateBenefitActions");
	    }
	    
	    if (extentTest == null) {
	        throw new IllegalArgumentException("❌ ERROR: ExtentTest is NULL! It must be created at the test case level and passed into the actions class.");
	    }
	    
	    this.softAssert = softAssert;
	    this.extentTest = extentTest;
	    this.utils = new CommonPlaywrightActions(page, softAssert, extentTest);
	    
	    System.out.println("✅ CommonPlaywrightActions initialized in CreateBenefitActions.");
	}


	public CommonPlaywrightActions getUtils() {
		return utils;
	}
	

}
