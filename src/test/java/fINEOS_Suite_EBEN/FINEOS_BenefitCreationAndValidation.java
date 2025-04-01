package fINEOS_Suite_EBEN;

import java.awt.AWTException;
import java.io.FileNotFoundException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.qa.actions.CreateBenefitActions;
import com.qa.base.PlaywrightTestBase;
import com.qa.utils.ExtentReportManager;
import com.qa.utils.TestUtils;
import com.qa.webELEMENTS.LoginPage;


/**
 * Test Name : validateFINEOSCrateBenefitFeature
 * Purpose   : To validate the FINEOS benefit creation feature via FINEOSActions.
 * Author    : 21-MAR-2025 by TathaB
 */

public class FINEOS_BenefitCreationAndValidation extends PlaywrightTestBase{
	
	
	private LoginPage login;
    private CreateBenefitActions cbActions;
    private SoftAssert softAssert;
    
    
    @BeforeMethod
    public void setUpTest() {
        if (page == null) {
            throw new IllegalStateException("❌ ERROR: Playwright `Page` is NULL in FINEOS_BenefitCreationAndValidation_TestCase");
        }

        System.out.println("✅ Page is initialized in FINEOS_BenefitCreationAndValidation_TestCase: " + page);
        softAssert = new SoftAssert();

        // ✅ Ensure `ExtentTest` is properly initialized
        extentTest = ExtentReportManager.createTest("validateFINEOSBenefitCreationFeature");

        if (extentTest == null) {
            System.out.println("❌ ERROR: ExtentTest is NULL! Trying to fetch again...");
            extentTest = ExtentReportManager.getTest();  // 🔄 Fetch after storing
            
            if (extentTest == null) {
                System.out.println("❌ Still NULL! This should not happen.");
            }
        } else {
            System.out.println("✅ ExtentTest Retrieved Successfully: " + extentTest);
        }

        // ✅ Pass ExtentTest & SoftAssert correctly
        login = new LoginPage(page);
        cbActions = new CreateBenefitActions(page, softAssert, extentTest);
    }
    @DataProvider(name = "ClaimBenefit")
    public Object[][] getFINEOSTestData() throws FileNotFoundException {
        return TestUtils.getTestData("ClaimBenefitDATA");
    }

    @Test(dataProvider = "ClaimBenefit")
    public void validateFINEOSBenefitCreationFeature(
            String usernameData, String passwordData) throws InterruptedException, AWTException {
    	logInfo("Starting test for user: " + usernameData);
    	// Step 1: Login to FINEOS
        login.navigateToUrl(usernameData, passwordData);
        logInfo("Logged into FINEOS with user: " + usernameData);
        
    	
    }
    
    
    
    
    
    
    
    
    
    private void logInfo(String message) {
        if (extentTest != null) {
            extentTest.log(Status.INFO, message);
        } else {
            System.out.println("[LOG] " + message);
        }
    }

    private void logPass(String message) {
        if (extentTest != null) {
            extentTest.log(Status.PASS, message);
        } else {
            System.out.println("[LOG] " + message);
        }
    }
}
    

