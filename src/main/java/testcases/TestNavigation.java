package testcases;

import static org.testng.Assert.assertEquals;
import static utilities.HelpStrings.*;

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

@Test(groups = { "Navigation" })
public class TestNavigation {

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
		total=0;
	}

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

	@AfterMethod
	public void logSeparator() {
		logger.info("**********************************");
		total++;
	}

	@AfterClass
	public void logStuff() {
		logger.warn(passes + " tests passed and " + fails + " failed" + " total: " + total);
		logger.info("**********************************");
	}

	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
		driver = null;
		chatbot = null;
	}

	@Test(dependsOnGroups = { "Login" })
	public void createModifyTicket() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTModifyTicket, chatbot);
		logger.info("step: button clicked");
		assertEquals(chatbot.lastResponseContains("Ok, what do you want to do?"), true);
		if (chatbot.lastResponseContains("Ok, what do you want to do?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "Login" }, groups = {})
	public void submitARequest() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTSubmitARequest, chatbot);
		assertEquals(chatbot.lastResponseContains("Please select one of"), true);
		if (chatbot.lastResponseContains("Please select one of")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnGroups = { "Login" }, groups = {})
	public void reportAnIncident() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton(NTReportAnIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Alright! Now, lets narrow things down a bit."), true);
		if (chatbot.lastResponseContains("Alright! Now, lets narrow things down a bit.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = {}, groups = {})
	public void createTicket() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCreateTicket, chatbot);
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("First of all"), true);
		if (chatbot.lastResponseContains("First of all")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "createModifyTicket" }, groups = {})
	public void modifyTicket() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTModifyTicket, chatbot);
		chatbot.waitForResponse();
		try {
			chatbot.wait(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(chatbot.lastResponseContains("Do you want to update"), true);
		if (chatbot.lastResponseContains("Do you want to update")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "modifyTicket" }, groups = {})
	public void modifyRequest() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton(NTRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("It starts with \"REQ\" then is followed by 7 numbers."), true);
		if (chatbot.lastResponseContains("It starts with \\\"REQ\\\" then is followed by 7 numbers.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "modifyTicket" }, groups = {})
	public void modifyIncident() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		NavigationTree.clickButton(NTIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("It starts with \"INC\" then is followed by 7 numbers."), true);
		if (chatbot.lastResponseContains("It starts with \\\"INC\\\" then is followed by 7 numbers.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void communication() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCommunication, chatbot);
		assertEquals(chatbot.lastResponseContains("And now a subcategory, please"), true);
		if (chatbot.lastResponseContains("And now a subcategory, please")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void dataManagement() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTDataManagement, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Allright, the only thing I can do for you here is requesting a folder access."), true);
		if (chatbot.lastResponseContains(
				"Allright, the only thing I can do for you here is requesting a folder access.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void hardwareRequest() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTHardwareRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("I can only process printer related requests."), true);
		if (chatbot.lastResponseContains("I can only process printer related requests.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void infrastructure() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTInfrastructure, chatbot);
		assertEquals(chatbot.lastResponseContains("You have selected Infrastructure."), true);
		if (chatbot.lastResponseContains("You have selected Infrastructure.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void managedBusinessApplication() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTManagedBusinessApplication, chatbot);
		assertEquals(chatbot.lastResponseContains("For which application do you wish to submit a request?"), true);
		if (chatbot.lastResponseContains("For which application do you wish to submit a request?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "communication" }, groups = {})
	public void emailMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTEmailMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("These are the Email mailbox I can handle for you:"), true);
		if (chatbot.lastResponseContains("These are the Email mailbox I can handle for you:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createDistributionList() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCreateDistributionList, chatbot);
		assertEquals(chatbot.lastResponseContains("Create a Distribution List"), true);
		if (chatbot.lastResponseContains("Create a Distribution List")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createSharedMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCreateSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox?"), true);
		if (chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void deleteSharedMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTDeleteSharedMailbox, chatbot);
		assertEquals(
				chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox u wish to delete?"),
				true);
		if (chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox u wish to delete?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void revokeSharedMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTRevokeSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox you wish to revoke the access of?"), true);
		if (chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox you wish to revoke the access of?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void disableSharedMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTDisableSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox u wish to disable the access?"), true);
		if (chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox u wish to disable the access?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createEmailIntoFolder() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCreateEmailInFolder, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you tell me which path you use to access this public folder?"),
				true);
		if (chatbot.lastResponseContains("Can you tell me which path you use to access this public folder?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void grantUserMailboxAccess() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTGrantUserMailboxAccess, chatbot);
		assertEquals(chatbot.lastResponseContains("What is the name of the user of whom you wish access the mailbox?"),
				true);
		if (chatbot.lastResponseContains("What is the name of the user of whom you wish access the mailbox?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}

	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void grantSharedMailboxAccess() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTGrantSharedMailboxAccess, chatbot);
		assertEquals(
				chatbot.lastResponseContains("What is the name of the shared mailbox to which you require access?"),
				true);
		if (chatbot.lastResponseContains("What is the name of the shared mailbox to which you require access?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void modifyExistingMailbox() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTModifyMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("What is the name of the shared mailbox that you wish to modify?"),
				true);
		if (chatbot.lastResponseContains("What is the name of the shared mailbox that you wish to modify?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void otherEmailRequest() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTOtherEmailRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("something with request"), true);
		if (chatbot.lastResponseContains("something with request")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void registerNewDomain() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTRegisterEmailDomain, chatbot);
		assertEquals(chatbot.lastResponseContains("FYI: the email domain is what comes after the @-sign"), true);
		if (chatbot.lastResponseContains("FYI: the email domain is what comes after the @-sign")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "communication" }, groups = {})
	public void ipTelephonyServices() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTIPTelephonyServices, chatbot);
		assertEquals(chatbot.lastResponseContains("I can only process the following request:"), true);
		if (chatbot.lastResponseContains("I can only process the following request:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void bPC() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTBPC, chatbot);
		assertEquals(chatbot.responseContains("You'll find the BPC forms there:", 1), true);
		if (chatbot.lastResponseContains("You'll find the BPC forms there:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void clickview() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTClickview, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Clickview forms there:", 1), true);
		if (chatbot.lastResponseContains("You'll find the Clickview forms there:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void clockworks() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTClockworks, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Clockworks forms there:", 1), true);
		if (chatbot.lastResponseContains("You'll find the Clockworks forms there:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void general() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTGeneral, chatbot);
		assertEquals(chatbot.responseContains("Is there anything else", 1), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void imis() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTImis, chatbot);
		assertEquals(chatbot.responseContains("Is there anything else", 1), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void lyris() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTLyris, chatbot);
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void mailchimp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTMailchimp, chatbot);
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void mobatime() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTMobatime, chatbot);
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void proconcept() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTProConcept, chatbot);
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void scorecards() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTScorecards, chatbot);
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentHardware() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTHardwareIncident, chatbot);
		try {
			System.out.println(chatbot.getResponse(0).getText());
		} catch (Exception e) {

			e.printStackTrace();
		}
		assertEquals(chatbot.lastResponseContains("Is there anything else"), true);
		if (chatbot.lastResponseContains("Is there anything else")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentSoftware() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTSoftwareIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Which application is causing you troubles?"), true);
		if (chatbot.lastResponseContains("Which application is causing you troubles?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentSecurity() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTSecurityIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Which security topic do you wish to address?"), true);
		if (chatbot.lastResponseContains("Which security topic do you wish to address?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentNetwork() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTNetworkIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Is the connection slow (does it take a long"), true);
		if (chatbot.lastResponseContains("Is the connection slow (does it take a long")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void officeApp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTOfficeApp, chatbot);
		assertEquals(chatbot.lastResponseContains("4. Powerpoint"), true);
		if (chatbot.lastResponseContains("4. Powerpoint")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void citrix() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCitrix, chatbot);
		assertEquals(chatbot.lastResponseContains("Are you experiencing performance issues or is it another problem?"),
				true);
		if (chatbot.lastResponseContains("Are you experiencing performance issues or is it another problem?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void communicationApp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCommunicationApp, chatbot);
		assertEquals(chatbot.lastResponseContains("Are you using Skype for business or an IP phone?"), true);
		if (chatbot.lastResponseContains("Are you using Skype for business or an IP phone?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void businessApp() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTBusinessApplication, chatbot);
		assertEquals(chatbot.lastResponseContains("Choose the application that isn't working:")
				&& chatbot.lastResponseContains("MailChimp"), true);
		if (chatbot.lastResponseContains("Choose the application that isn't working:")
				&& chatbot.lastResponseContains("MailChimp")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void otherSoftware() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTOtherSoftwareIncident, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Could you give me the name of the application for which you'd like to report an issue please?"), true);
		if (chatbot.lastResponseContains(
				"Could you give me the name of the application for which you'd like to report an issue please?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void antivirus() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTAntivirus, chatbot);
		assertEquals(chatbot.lastResponseContains("UhOh! sounds like trouble!"), true);
		if (chatbot.lastResponseContains("UhOh! sounds like trouble!")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void exchange() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTExchange, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Emailing is a broad topic, so I need you to select one of the categories below:"), true);
		if (chatbot.lastResponseContains(
				"Emailing is a broad topic, so I need you to select one of the categories below:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void activeDirectory() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTActiveDirectory, chatbot);
		assertEquals(chatbot.lastResponseContains("There's quite a few things I can do for you regarding this topic."),
				true);
		if (chatbot.lastResponseContains("There's quite a few things I can do for you regarding this topic.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void vPN() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTVPN, chatbot);
		assertEquals(chatbot.lastResponseContains("Bad performance of VPN connection"), true);
		if (chatbot.lastResponseContains("Bad performance of VPN connection")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "antivirus" }, groups = {})
	public void virusFound() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTVirusFound, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"I need to create a ticket right away and someone will contact you in order to have a look into this."),
				true);
		if (chatbot.lastResponseContains(
				"I need to create a ticket right away and someone will contact you in order to have a look into this.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "antivirus" }, groups = {})
	public void problemAV() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTProblemAVSoftware, chatbot);
		assertEquals(chatbot.lastResponseContains("UhOh! sounds like trouble!"), true);
		if (chatbot.lastResponseContains("UhOh! sounds like trouble!")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void issueEmails() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTIssueSendingReceivingEmails, chatbot);
		assertEquals(chatbot.lastResponseContains("The easiest solution for this is to restart your PC"), true);
		if (chatbot.lastResponseContains("The easiest solution for this is to restart your PC")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void spam() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTSpam, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Do you wish to obtain some information and some tips on how to deal with SPAM?"), true);
		if (chatbot.lastResponseContains(
				"Do you wish to obtain some information and some tips on how to deal with SPAM?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void notSpam() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTNotSpam, chatbot);
		assertEquals(chatbot.lastResponseContains("Ah! so the filter worked a little too well in that case"), true);
		if (chatbot.lastResponseContains("Ah! so the filter worked a little too well in that case")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void accessMailboxExhange() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTAccessSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("You need to be aware that an access to a shared mailbox"), true);
		if (chatbot.lastResponseContains("You need to be aware that an access to a shared mailbox")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void calendarAccess() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTCalendarAccesses, chatbot);
		assertEquals(chatbot.lastResponseContains("Does this concern your own calendar or someone else's calendar?"),
				true);
		if (chatbot.lastResponseContains("Does this concern your own calendar or someone else's calendar?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void accessSharedCalendar() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTAccessSharedCalendar, chatbot);
		assertEquals(chatbot.lastResponseContains("Do you need access to a shared calendar or do you have"), true);
		if (chatbot.lastResponseContains("Do you need access to a shared calendar or do you have")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void sendAsPermissions() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTSendAsPermission, chatbot);
		assertEquals(chatbot.lastResponseContains("Do you need to send emails on behalf of:"), true);
		if (chatbot.lastResponseContains("Do you need to send emails on behalf of:")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void otherExchange() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTOtherExchangeSoftware, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you give me a detailed description of your problem please?"),
				true);
		if (chatbot.lastResponseContains("Can you give me a detailed description of your problem please?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void grantAccessFolder() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTGrantFolderAccess, chatbot);
		assertEquals(chatbot.lastResponseContains("That's a tricky one for me."), true);
		if (chatbot.lastResponseContains("That's a tricky one for me.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeLastName() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTChangeLastName, chatbot);
		assertEquals(chatbot.lastResponseContains("Allright. So which last name"), true);
		if (chatbot.lastResponseContains("Allright. So which last name")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void addEmail() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTAddEmailToAccount, chatbot);
		assertEquals(chatbot.lastResponseContains("Ah! that's an easy one"), true);
		if (chatbot.lastResponseContains("Ah! that's an easy one")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeNameManager() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTChangeNameManager, chatbot);
		assertEquals(chatbot.lastResponseContains("I can do that! What is the name of your new manager?"), true);
		if (chatbot.lastResponseContains("I can do that! What is the name of your new manager?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeOfficeAddress() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTChangeOfficeAddress, chatbot);
		assertEquals(chatbot.lastResponseContains("Unfortunately I cannot change your office location myself"), true);
		if (chatbot.lastResponseContains("Unfortunately I cannot change your office location myself")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void unlockAccount() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTUnlockAccount, chatbot);
		assertEquals(chatbot.lastResponseContains("Oh, let's fix that. Can I have your username please?"), true);
		if (chatbot.lastResponseContains("Oh, let's fix that. Can I have your username please?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void resetPassword() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTResetPassword, chatbot);
		assertEquals(chatbot.lastResponseContains("Sure, what is your username please?"), true);
		if (chatbot.lastResponseContains("Sure, what is your username please?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void badPerformance() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTBadPerformanceVPN, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Are you experiencing the same performance issues while you're not logged on via VPN?"), true);
		if (chatbot.lastResponseContains(
				"Are you experiencing the same performance issues while you're not logged on via VPN?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void unableLogin() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTUnableToLoginVPN, chatbot);
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.responseContains("OK. Let me try something very quickly.", 2), true);
		if (chatbot.lastResponseContains("OK. Let me try something very quickly.")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void otherVPN() {
		logger.info("test: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		NavigationTree.clickButton(NTOtherVPNIssue, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you give me a description of your problem please?"), true);
		if (chatbot.lastResponseContains("Can you give me a description of your problem please?")) {
			logger.warn("Test passed");
			passes++;
		} else {
			logger.warn("Test failed");
			fails++;
		}
	}

}
