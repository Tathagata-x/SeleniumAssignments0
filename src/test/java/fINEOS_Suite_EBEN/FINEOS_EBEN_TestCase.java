package fINEOS_Suite_EBEN;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.actions.FINEOSActions;
import com.qa.base.PlaywrightTestBase;
import com.qa.utils.ExtentReportManager;
import com.qa.utils.TestUtils;
import com.qa.webELEMENTS.LoginPage;

/**
 * Test Name : validateFINEOSAccountActivityFeature
 * Purpose   : To validate the FINEOS Account Activity feature via FINEOSActions.
 * Author    : 11-FEB-2025 by TathaB
 */
public class FINEOS_EBEN_TestCase extends PlaywrightTestBase {

    private LoginPage login;
    private FINEOSActions fineosActions;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUpTest() {
        if (page == null) {
            throw new IllegalStateException("❌ ERROR: Playwright `Page` is NULL in FINEOS_EBEN_TestCase");
        }

        System.out.println("✅ Page is initialized in FINEOS_EBEN_TestCase: " + page);
        softAssert = new SoftAssert();
        ExtentReportManager.createTest("validateFINEOSAccountActivityFeature");
        login = new LoginPage(page);
        fineosActions = new FINEOSActions(page, softAssert);
    }


    @DataProvider(name = "FINEOSLoginDATA")
    public Object[][] getFINEOSTestData() throws FileNotFoundException, IOException {
        return TestUtils.getTestData("FINEOSLoginDATA");
    }

    @Test(dataProvider = "FINEOSLoginDATA")
    public void validateFINEOSAccountActivityFeature(
            String usernameData, String passwordData, String firstName,
            String lastName, String ssnId, String dob, String gender,
            String partyType, String addLineOne, String cityData,
            String state, String zipCode, String claimType, String acciDateData, String acciORSick, String unableToWorkDate, String lastWorkedEmployerCalendarDate, String lastWorkedEmployeeCalendarDate, String PartyNameData, String dateOfHireData, String occupationData, String exertionValue, String weeklyAmt, String policyNumber) throws InterruptedException, AWTException {
            
        

        // Step 1: Login to FINEOS
        login.navigateToUrl(usernameData, passwordData);
        

        // Step 2: Navigate to Parties section
        fineosActions.clickPartiesIcon();
        fineosActions.getPartySearchHeader();
        

        // Step 3: Search for an existing party
        fineosActions.searchForParty(firstName, lastName);
        

        // Step 4: If no records exist, add a new party
        if (fineosActions.isNoRecordMessageVisible()) {

            fineosActions.clickAddParty();

            // Step 5: Enter personal details
            fineosActions.enterIdentificationNumber(ssnId);
            fineosActions.enterPersonalDetails(dob, gender, partyType);
            // Step 6: Enter address details
            fineosActions.addAddress(addLineOne, cityData, state, zipCode);
            // Step 7: Confirm the addition
            fineosActions.clickCasesTab();
            fineosActions.clickAddButton();
            fineosActions.validateIntakeSection();
            fineosActions.selectClaimType(claimType);
            fineosActions.clickNextButton();                // Clicks "Next" button in the intake section
           
            fineosActions.selectIncurredDate(acciDateData);
            fineosActions.selectAccidentSickness(acciORSick);      // Selects an option from the "Accident / Sickness" dropdown
            fineosActions.openUnableToWorkCalendar(unableToWorkDate);       // Opens "Date First Unable to Work" calendar
            fineosActions.openLastWorkedEmployerCalendar(lastWorkedEmployerCalendarDate); // Opens "Date Last Worked (Employer)" calendar
            fineosActions.openLastWorkedEmployeeCalendar(lastWorkedEmployeeCalendarDate); // Opens "Date Last Worked (Employee)" calendar
            fineosActions.clickSelectParty();               // Clicks "Select Party" button
            fineosActions.enterEmployerName(PartyNameData); // Fills "Name" input with "Albion Borough"
            fineosActions.clickSearchButton();
            fineosActions.clickFooterOkButton();
            fineosActions.openDateOfHireCalendar(dateOfHireData);
            fineosActions.enterOccupation(occupationData);
            fineosActions.selectStrengthCategory(exertionValue);
            fineosActions.enterEarningsAmount(weeklyAmt);
            fineosActions.clickAddEarningsRecord();
            fineosActions.clickAddContract();
            fineosActions.enterPolicyNumber(policyNumber);
            fineosActions.clickSearchButton();
            fineosActions.clickFooterOkButton();
            fineosActions.clickStdCell();
            fineosActions.clickLinkButton();
            fineosActions.clickDiagnosisSearchBox();
            fineosActions.enterDiagnosisSearch("Diab");
            fineosActions.enterDiagnosisDropdown("D");
            fineosActions.enterDiagnosisSearch("diab");
            fineosActions.doubleClickDiagnosisSearch();
            fineosActions.clickQuickAddButton();
            fineosActions.clickNextButton1();
            fineosActions.clickManagedRequirementsSpan();
            fineosActions.clickNextButton2();
            fineosActions.clickYesButton();
            fineosActions.closeAlerts();
            fineosActions.verifyCaseKeyInfoBar();
            fineosActions.clickBaaCSR();
            fineosActions.logout();
        }
    }
}
