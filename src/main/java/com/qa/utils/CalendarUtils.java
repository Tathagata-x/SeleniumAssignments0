package com.qa.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;

public class CalendarUtils {

    private Page page;

    public CalendarUtils(Page page) {
        this.page = page;
    }

    public void selectDate(Locator calendarOpenButton, String year, String month, String day) {
        // Open calendar picker using the provided locator
        calendarOpenButton.click();

        // Navigate to year selection view
        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Toggle Date and Time Screens")).click();
        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Toggle Date and Time Screens")).click();

        // Adjust decades as needed
        navigateToYear(year);

        // Select desired year
        page.getByText(year).click();

        // Select desired month
        page.getByText(month).click();

        // Select desired day
        page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(day).setExact(true)).click();
    }


    private void navigateToYear(String targetYear) {
        int year = Integer.parseInt(targetYear);
        int currentYear = java.time.Year.now().getValue();

        // Find out how many times to click next/previous decade
        int clicks = (year - currentYear) / 10;
        Locator nextDecade = page.getByTitle("Next Decade");
        Locator prevDecade = page.getByTitle("Previous Decade");

        if (clicks > 0) {
            for (int i = 0; i < clicks; i++) {
                nextDecade.click();
            }
        } else {
            for (int i = 0; i < Math.abs(clicks); i++) {
                prevDecade.click();
            }
        }
    }
}
