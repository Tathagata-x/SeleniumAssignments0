package fINEOS_Suite_EBEN;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.options.AriaRole;

public class Example {
  public static void main(String[] args) {
    try (Playwright playwright = Playwright.create()) {
      Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
        .setHeadless(false));
      BrowserContext contextOne = browser.newContext();
      Page page = contextOne.newPage();
      String username = "BACSR";
      String password = "P8EXS2PCMvTnf";
      String url = "https://" + username + ":" + password + "@idt2-claims-webapp.oneamerica.fineos.com";
      page.navigate(url);
      page.getByLabel("Parties").click();
      page.getByLabel("First Name").fill("12MArchUser1");
      page.getByLabel("First Name").press("Tab");
      page.getByLabel("Last Name").fill("Party");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true)).click();
      assertThat(page.getByText("There are no records found")).isVisible();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
      page.locator("#personIdentificationFrameWidget_un29_idNumber_idNumber_0").click();
      page.locator("#personIdentificationFrameWidget_un29_idNumber_idNumber_0").fill("730000816");
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).click();
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).fill("09/08/2998");
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).press("ArrowLeft");
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).press("ArrowLeft");
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).press("ArrowLeft");
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).dblclick();
      page.getByLabel("Date of birth", new Page.GetByLabelOptions().setExact(true)).fill("03/09/1999");
      page.getByLabel("Gender").selectOption("1");
      page.getByLabel("Party Type").selectOption("1");
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("+ Add address")).click();
      page.getByLabel("Address line 1").click();
      page.getByLabel("Address line 1").fill("AddressLineOne");
      page.getByLabel("Address line 1").press("Tab");
      page.getByLabel("Address line 2").fill("LineTwo");
      page.getByLabel("Address line 2").press("Tab");
      page.getByLabel("Address line 3").fill("Linethree");
      page.getByLabel("Address line 3").press("Tab");
      page.getByLabel("City").fill("Crown Point");
      page.getByLabel("City").press("Tab");
      page.getByLabel("State").selectOption("16");
      page.getByLabel("Zip code", new Page.GetByLabelOptions().setExact(true)).click();
      page.getByLabel("Zip code", new Page.GetByLabelOptions().setExact(true)).fill("46307");
      page.getByLabel("Mailing address").check();
      page.getByTitle("OK").click();
      page.getByText("14Cases").click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add")).click();
      assertThat(page.getByText("Intake", new Page.GetByTextOptions().setExact(true))).isVisible();
      assertThat(page.getByText("Claim Intake Opening", new Page.GetByTextOptions().setExact(true))).isVisible();
      assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("CLAIM INTAKE OPENING"))).isVisible();
      page.getByLabel("Type").selectOption("1");
      page.getByLabel("Type").selectOption("0");
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un14_theNextButton").click();
      
      page.locator("#claimGeneralWidgetTag_un45_IncurredDate_WRAPPER").getByLabel("Open calendar").click();
      page.getByTitle("Previous month").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("5").setExact(true)).first().click();
      page.getByLabel("Accident / Sickness").selectOption("3");
      page.locator("#ClaimDisabilityGeneralWidget_un47_DateFirstUnableToWork_WRAPPER").getByLabel("Open calendar").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(" Previous Month").setExact(true)).click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("5").setExact(true)).first().click();
      page.locator("#claimEmploymentDetailsWidgetTag_un48_DateLastWorkedEmplr_WRAPPER").getByLabel("Open calendar").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(" Previous Month").setExact(true)).click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("4").setExact(true)).first().click();
      page.locator("#claimEmploymentDetailsWidgetTag_un48_DateLastWorkedEmplee_WRAPPER").getByLabel("Open calendar").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName(" Previous Month").setExact(true)).click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("4").setExact(true)).first().click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select Party")).click();
      page.getByLabel("Name").fill("Albion Borough");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true)).click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Unknown")).nth(1).click();
      page.locator("#footerButtonsBar").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("OK")).click();
      page.locator("#OccupationDetailsWidget_un49_DateOfHire_WRAPPER").getByLabel("Open calendar").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("March 2025 Toggle Date and Time Screens").setExact(true)).click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("2025 Toggle Date and Time Screens").setExact(true)).click();
      page.getByText("2021", new Page.GetByTextOptions().setExact(true)).click();
      page.getByText("May").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("20").setExact(true)).click();
      page.getByTitle("Enter Occupation Code").locator("div").nth(1).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type at least 3 characters...")).fill("Eng");
      page.locator("#OccupationDetailsWidget_un49_occupationCodeDynamicDropDownBean_occupationCodeDynamicDropDownBean_input_transient").fill("Engi");
      page.navigate("https://idt2-claims-webapp.oneamerica.fineos.com/sharedpages/workmanager/scripting/processstatuspage.jsp?PageID=p9&plist=p1013&vsid=15071");
      page.locator("#OccupationDetailsWidget_un49_strengthCategoryEnum").selectOption("2");
      page.locator("#OccupationDetailsWidget_un49_strengthCategoryEnum").selectOption("0");
      page.locator("#DatedEarningsListviewWidget_un52_Earnings_Amount").click();
      page.locator("#DatedEarningsListviewWidget_un52_Earnings_Amount").fill("400.00");
      page.getByTitle("Add a new Earnings Record", new Page.GetByTitleOptions().setExact(true)).click();
      page.getByTitle("Add Contract").click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Policy Number")).fill("006122240000000");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true)).click();
      page.locator("#ContractsListviewWidget_un15_contractslistviewMemberId0").click();
      page.locator("#footerButtonsBar").getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("OK")).click();
      page.navigate("https://idt2-claims-webapp.oneamerica.fineos.com/sharedpages/workmanager/scripting/processstatuspage.jsp?PageID=p9&plist=p1115&vsid=15071");
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("12440-STD-11/01/2015").setExact(true)).click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Link").setExact(true)).click();
      page.navigate("https://idt2-claims-webapp.oneamerica.fineos.com/sharedpages/workmanager/scripting/processstatuspage.jsp?PageID=p9&plist=p1115&vsid=15071");
      page.getByTitle("Search diagnosis code id and").locator("div").nth(1).click();
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type at least 3 characters...")).fill("Diab");
      page.locator("#diagnosisCodesListviewWidget_un60_diagnosisCodeIdDynamicDropDownBean_diagnosisCodeIdDynamicDropDownBean_input_transient").fill("D");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type at least 3 characters...")).fill("diab");
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Type at least 3 characters...")).dblclick();
      page.locator("#diagnosisCodesListviewWidget_un60_quickaddbutton").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un26_theNextButton").click();
      page.locator("#generateManagedRequirementsWidgetTag_un80_090f66cc-bd29-4178-8a72-aa3b9fca3669_WRAPPER span").click();
      page.locator("#generateManagedRequirementsWidgetTag_un80_090f66cc-bd29-4178-8a72-aa3b9fca3669_WRAPPER span").click();
      page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Incomplete   Edit row Previous   Next").setExact(true)).locator("#DriverDialogWidget_un75_theNextButton").click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes")).click();
      page.locator("#AlertsHeaderWidget").getByText("Close").click();
      assertThat(page.locator("[id=\"com\\.fineos\\.frontoffice\\.casemanager\\.casekeyinformation\\.CaseKeyInfoBar\"]").getByText("Open")).isVisible();
      page.getByLabel("BAaCSR").click();
      page.getByLabel("Logout").click();
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes")).click();
      contextOne.close();
      BrowserContext context2 = browser.newContext(); // Creating a fresh session
      Page page2 = context2.newPage();

      String username2 = "STDEXAM3";  // Change to second user
      String password2 = "P8EXS2PCMvTnf";
      String url2 = "https://" + username2 + ":" + password2 + "@idt2-claims-webapp.oneamerica.fineos.com";
      page2.navigate(url2);
      
      
    }
  }
}