package testCase;

import org.testng.annotations.Test;
import base.PlaywrightTestBase;
import pages.LoginPage;

public class LoginTest extends PlaywrightTestBase {

    @Test
    public void validateLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("testUser", "testPassword");
    }
}
