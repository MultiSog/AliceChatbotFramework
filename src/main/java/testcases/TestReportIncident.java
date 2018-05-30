package testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import drivers.DriverManager;
import model.Chatbot;
import utilities.LoginUser;
import utilities.NavigationTree;

public class TestReportIncident {

	private WebDriver driver;
	private Chatbot chatbot;

	@BeforeTest
	public void loadPage() {
		driver = DriverManager.getChromeDriver();
		LoginUser.loginUser(driver);
	}

	@BeforeMethod
	public void reload() {
		driver.navigate().refresh();
		chatbot = new Chatbot(driver);
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
		driver = null;
		chatbot = null;
	}

	// @Test(dependsOnGroups = {}, groups = { "stuff" })
	// public void logout() {
	// chatbot.openMenu();
	// chatbot.waitForResponse(5000);
	// chatbot.logout();
	//
	// }

	// office apps tests
	@Test(dependsOnGroups = {}, groups = { "officeapp" })
	public void reportOfficeAppIncident() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		assertEquals(chatbot.lastResponseContains("Choose the application"), true);
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportExcelIncident() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("1. Excel");
		chatbot.waitForResponse();

		chatbot.inputMessage("excel is broken");
		chatbot.waitForResponse();

		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportWordIncident() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("2. Word");
		chatbot.waitForResponse();
		chatbot.inputMessage("word is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportOutlookIncidentIcon() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("3. Outlook");
		chatbot.waitForResponse();
		chatbot.clickButton("1. MS Outlook Icon");
		chatbot.waitForResponse();
		chatbot.inputMessage("Outlook is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportOutlookIncidentBrowser() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("3. Outlook");
		chatbot.waitForResponse();
		chatbot.clickButton("2. Via a browser");
		chatbot.waitForResponse();
		chatbot.inputMessage("Outlook is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportPowerpointIncident() {
		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("4. Powerpoint");
		chatbot.waitForResponse();
		chatbot.inputMessage("powerpoint is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	// Citrix tests
	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOneApp() {
		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Performance / Slowness");
		chatbot.waitForResponse();
		chatbot.clickButton("One specific app");
		chatbot.waitForResponse();
		chatbot.inputMessage("oops");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixSeveralApps() {
		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Performance / Slowness");
		chatbot.waitForResponse();
		chatbot.clickButton("All of them /several");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherOneApp() {
		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Other");
		chatbot.waitForResponse();
		chatbot.clickButton("One specific app");
		chatbot.waitForResponse();
		chatbot.inputMessage("oops");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherSeveralApps() {
		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Other");
		chatbot.waitForResponse();
		chatbot.clickButton("Several");
		chatbot.waitForResponse();
		chatbot.inputMessage("one,two,three");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherAllApps() {
		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Other");
		chatbot.waitForResponse();
		chatbot.clickButton("All of them");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
	}

	// Skype tests
	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeFunctionalities() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("Skype for Business");
		chatbot.waitForResponse();
		chatbot.clickButton("Problems with one of the functionalities");
		chatbot.waitForResponse();
		chatbot.inputMessage("something");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeOther() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("Skype for Business");
		chatbot.waitForResponse();
		chatbot.clickButton("Other");
		chatbot.waitForResponse();
		chatbot.inputMessage("something");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeCredentials() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("Skype for Business");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Not accepting credentials");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeErrorMessage() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("Skype for Business");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Error message");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	// IP Phone tests
	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneFunctionalities() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("IP Phone");
		chatbot.waitForResponse();
		chatbot.clickButton("Problems with one of the functionalities");
		chatbot.waitForResponse();
		chatbot.inputMessage("something");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneOther() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("IP Phone");
		chatbot.waitForResponse();
		chatbot.clickButton("Other");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneLogonCredentials() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("IP Phone");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Not accepting credentials");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneLogonErrorMessage() {
		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("IP Phone");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Error message");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	// business app tests
	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportScorecards() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("Scorecards");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportHRForms() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("HR Forms");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportProConcept() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ProConcept");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportMobaTime() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("MobaTime");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportMailChimp() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("MailChimp");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportLyris() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("Lyris");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportClockWorks() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ClockWorks");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportClickView() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ClickView");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportBPC() {
		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("BPC");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);

	}
}
