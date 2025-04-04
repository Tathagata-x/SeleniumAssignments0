package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;
    private String usernameField = "input#username";
    private String passwordField = "input#password";
    private String loginButton = "button#login";

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        page.fill(usernameField, username);
        page.fill(passwordField, password);
        page.click(loginButton);
    }
}
