package com.qa.webELEMENTS;

import com.microsoft.playwright.Page;

public class LoginPage {
    private Page page;

    public LoginPage(Page page) {
        if (page == null) {
            System.out.println("❌ ERROR: Playwright `Page` is NULL in LoginPage constructor!");
            throw new IllegalArgumentException("Playwright `Page` object cannot be null!");
        }
        this.page = page;
    }

    public void navigateToUrl(String usernameData, String passwordData) {
        String urlFIN = "https://" + usernameData + ":" + passwordData + "@idt2-claims-webapp.oneamerica.fineos.com";
        System.out.println("🔄 Navigating to: " + urlFIN);
        page.navigate(urlFIN);
        
        System.out.println("✅ Navigation complete.");
        
    }
   
}
