package testcases;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
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
	private int passes;
	private int fails;
	private int total;
	Logger logger = Logger.getLogger("TestNavigation");

	@BeforeClass
	public void configLogger() {
		PropertyConfigurator.configure("log4j.properties");
		passes = 0;
		fails = 0;
		total = 0;
	}

	@AfterClass
	public void logStuff() {
		logger.warn(passes + " tests passed and " + fails + " failed" + " total: " + total);
		logger.info("**********************************");
	}

	@BeforeTest
	public void loadPage() {
		driver = DriverManager.getChromeDriver();
		LoginUser.loginUser(driver);
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
		driver = null;
		chatbot = null;
	}

	@AfterMethod
	public void logSeparator() {
		logger.info("**********************************");
		total++;
	}

	@BeforeMethod
	public void reload() {
		driver.navigate().refresh();
		chatbot = new Chatbot(driver);
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
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		assertEquals(chatbot.lastResponseContains("Choose the application"), true);
		if (chatbot.lastResponseContains("Choose the application")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportExcelIncident() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("1. Excel");
		chatbot.waitForResponse();

		chatbot.inputMessage("excel is broken");
		chatbot.waitForResponse();

		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportWordIncident() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("2. Word");
		chatbot.waitForResponse();
		chatbot.inputMessage("word is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportOutlookIncidentIcon() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportOutlookIncidentBrowser() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "officeapp" }, groups = { "officeapps" })
	public void reportPowerpointIncident() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("1. An Office app (e.g. Excel, Word, Outlook or Powerpoint)", chatbot);
		chatbot.clickButton("4. Powerpoint");
		chatbot.waitForResponse();
		chatbot.inputMessage("powerpoint is broken");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	// Citrix tests
	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOneApp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixSeveralApps() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("2. Citrix", chatbot);
		chatbot.clickButton("Performance / Slowness");
		chatbot.waitForResponse();
		chatbot.clickButton("All of them /several");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherOneApp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherSeveralApps() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = {}, groups = { "citrix" })
	public void reportCitrixOtherAllApps() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	// Skype tests
	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeFunctionalities() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeOther() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeCredentials() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("Skype for Business");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Not accepting credentials");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "skype" })
	public void reportSkypeErrorMessage() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	// IP Phone tests
	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneFunctionalities() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneOther() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneLogonCredentials() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("3. A communication app (e.g. Skype or IP phone)", chatbot);
		chatbot.clickButton("IP Phone");
		chatbot.waitForResponse();
		chatbot.clickButton("Logon /startup issues");
		chatbot.waitForResponse();
		chatbot.clickButton("Not accepting credentials");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "ip phone" })
	public void ipPhoneLogonErrorMessage() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

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
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	// business app tests
	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportScorecards() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("Scorecards");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportHRForms() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("HR Forms");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportProConcept() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ProConcept");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportMobaTime() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("MobaTime");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportMailChimp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("MailChimp");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportLyris() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("Lyris");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportClockWorks() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ClockWorks");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportClickView() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("ClickView");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnGroups = {}, groups = { "business app" })
	public void reportBPC() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton("4. A Business application", chatbot);
		chatbot.clickButton("BPC");
		chatbot.waitForResponse();
		chatbot.inputMessage("problem");
		chatbot.waitForResponse();
		chatbot.inputMessage("yes");
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Is there anything"), true);
		if (chatbot.lastResponseContains("Is there anything")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}
}
