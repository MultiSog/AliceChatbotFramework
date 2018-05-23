package testcases;

import static org.testng.Assert.assertEquals;
import static utilities.HelpStrings.*;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
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

	@Test(dependsOnGroups = {"Login"})
	public void createModifyTicket() {
		NavigationTree.clickButton(NTModifyCreateTicket, chatbot);
		assertEquals(chatbot.lastResponseContains("Ok, what do you want to do?"), true);
	}

	@Test(dependsOnGroups = {"Login"}, groups = {})
	public void submitARequest() {
		NavigationTree.clickButton(NTSubmitARequest, chatbot);
		assertEquals(chatbot.lastResponseContains("Please select one of"), true);
	}

	@Test(dependsOnGroups = {"Login"}, groups = {})
	public void reportAnIncident() {
		NavigationTree.clickButton(NTReportAnIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Alright! Now, lets narrow things down a bit."), true);
	}

	@Test(dependsOnMethods = { "createModifyTicket" }, groups = {})
	public void createTicket() {
		NavigationTree.clickButton(NTCreateTicket, chatbot);
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("First of all"), true);
	}

	@Test(dependsOnMethods = { "createModifyTicket" }, groups = {})
	public void modifyTicket() {
		NavigationTree.clickButton(NTModifyTicket, chatbot);
		chatbot.waitForResponse();
		assertEquals(chatbot.lastResponseContains("Do you want to update a request or an incident ticket?"), true);
	}

	@Test(dependsOnMethods = { "modifyTicket" }, groups = {})
	public void modifyRequest() {
		NavigationTree.clickButton(NTRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("It starts with \"REQ\" then is followed by 7 numbers."), true);
	}

	@Test(dependsOnMethods = { "modifyTicket" }, groups = {})
	public void modifyIncident() {
		NavigationTree.clickButton(NTIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("It starts with \"INC\" then is followed by 7 numbers."), true);
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void communication() {
		NavigationTree.clickButton(NTCommunication, chatbot);
		assertEquals(chatbot.lastResponseContains("And now a subcategory, please"), true);
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void dataManagement() {
		NavigationTree.clickButton(NTDataManagement, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Allright, the only thing I can do for you here is requesting a folder access."), true);
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void hardwareRequest() {
		NavigationTree.clickButton(NTHardwareRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("I can only process printer related requests."), true);
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void infrastructure() {
		NavigationTree.clickButton(NTInfrastructure, chatbot);
		assertEquals(chatbot.lastResponseContains("You have selected Infrastructure."), true);
	}

	@Test(dependsOnMethods = { "submitARequest" }, groups = {})
	public void managedBusinessApplication() {
		NavigationTree.clickButton(NTManagedBusinessApplication, chatbot);
		assertEquals(chatbot.lastResponseContains("For which application do you wish to submit a request?"), true);
	}

	@Test(dependsOnMethods = { "communication" }, groups = {})
	public void emailMailbox() {
		NavigationTree.clickButton(NTEmailMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("These are the Email mailbox I can handle for you:"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createDistributionList() {
		NavigationTree.clickButton(NTCreateDistributionList, chatbot);
		assertEquals(chatbot.lastResponseContains("Create a Distribution List"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createSharedMailbox() {
		NavigationTree.clickButton(NTCreateSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox?"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void deleteSharedMailbox() {
		NavigationTree.clickButton(NTDeleteSharedMailbox, chatbot);
		assertEquals(
				chatbot.lastResponseContains("Can I have the preferred name of the shared mailbox u wish to delete?"),
				true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void revokeSharedMailbox() {
		NavigationTree.clickButton(NTRevokeSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox u wish to revoke the access?"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void disableSharedMailbox() {
		NavigationTree.clickButton(NTDisableSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Can I have the preferred name of the shared mailbox u wish to disable the access?"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void createEmailIntoFolder() {
		NavigationTree.clickButton(NTCreateEmailInFolder, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you tell me which path you use to access this public folder?"),
				true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void grantUserMailboxAccess() {
		NavigationTree.clickButton(NTGrantUserMailboxAccess, chatbot);
		assertEquals(chatbot.lastResponseContains("What is the name of the user of whom you wish access the mailbox?"),
				true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void grantSharedMailboxAccess() {
		NavigationTree.clickButton(NTGrantSharedMailboxAccess, chatbot);
		assertEquals(
				chatbot.lastResponseContains("What is the name of the shared mailbox to which you require access?"),
				true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void modifyExistingMailbox() {
		NavigationTree.clickButton(NTModifyMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("What is the name of the shared mailbox that you wish to modify?"),
				true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void otherEmailRequest() {
		NavigationTree.clickButton(NTOtherEmailRequest, chatbot);
		assertEquals(chatbot.lastResponseContains("something with request"), true);
	}

	@Test(dependsOnMethods = { "emailMailbox" }, groups = {})
	public void registerNewDomain() {
		NavigationTree.clickButton(NTRegisterEmailDomain, chatbot);
		assertEquals(chatbot.lastResponseContains("FYI: the email domain is what comes after the @-sign"), true);
	}

	@Test(dependsOnMethods = { "communication" }, groups = {})
	public void ipTelephonyServices() {
		NavigationTree.clickButton(NTIPTelephonyServices, chatbot);
		assertEquals(chatbot.lastResponseContains("I can only process the following request:"), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void bPC() {
		NavigationTree.clickButton(NTBPC, chatbot);
		assertEquals(chatbot.responseContains("You'll find the BPC forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void clickview() {
		NavigationTree.clickButton(NTClickview, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Clickview forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void clockworks() {
		NavigationTree.clickButton(NTClockworks, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Clockworks forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void general() {
		NavigationTree.clickButton(NTGeneral, chatbot);
		assertEquals(chatbot.responseContains("You'll find the General forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void imis() {
		NavigationTree.clickButton(NTImis, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Imis forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void lyris() {
		NavigationTree.clickButton(NTLyris, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Lyris forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void mailchimp() {
		NavigationTree.clickButton(NTMailchimp, chatbot);
		assertEquals(chatbot.responseContains("You'll find the MailChimp forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void mobatime() {
		NavigationTree.clickButton(NTMobatime, chatbot);
		assertEquals(chatbot.responseContains("You'll find the MobaTime forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void proconcept() {
		NavigationTree.clickButton(NTProConcept, chatbot);
		assertEquals(chatbot.responseContains("You'll find the ProConcept forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "managedBusinessApplication" }, groups = { "MBA" })
	public void scorecards() {
		NavigationTree.clickButton(NTScorecards, chatbot);
		assertEquals(chatbot.responseContains("You'll find the Scorecards forms there:", 1), true);
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentHardware() {
		NavigationTree.clickButton(NTHardwareIncident, chatbot);
		try {
			System.out.println(chatbot.getResponse(0).getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(chatbot.lastResponseContains("In order to make sure I understand which component is "), true);
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentSoftware() {
		NavigationTree.clickButton(NTSoftwareIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Which application is causing you troubles?"), true);
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentSecurity() {
		NavigationTree.clickButton(NTSecurityIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Which security topic do you wish to address?"), true);
	}

	@Test(dependsOnMethods = { "reportAnIncident" }, groups = {})
	public void incidentNetwork() {
		NavigationTree.clickButton(NTNetworkIncident, chatbot);
		assertEquals(chatbot.lastResponseContains("Is the connection slow (does it take a long"), true);
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void officeApp() {
		NavigationTree.clickButton(NTOfficeApp, chatbot);
		assertEquals(chatbot.lastResponseContains("4. Powerpoint"), true);
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void citrix() {
		NavigationTree.clickButton(NTCitrix, chatbot);
		assertEquals(chatbot.lastResponseContains("Are you experiencing performance issues or is it another problem?"),
				true);
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void communicationApp() {
		NavigationTree.clickButton(NTCommunicationApp, chatbot);
		assertEquals(chatbot.lastResponseContains("Are you using Skype for business or an IP phone?"), true);
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void businessApp() {
		NavigationTree.clickButton(NTBusinessApplication, chatbot);
		assertEquals(chatbot.lastResponseContains("Choose the application that isn't working:")
				&& chatbot.lastResponseContains("MailChimp"), true);
	}

	@Test(dependsOnMethods = { "incidentSoftware" }, groups = {})
	public void otherSoftware() {
		NavigationTree.clickButton(NTOtherSoftwareIncident, chatbot);
		assertEquals(
				chatbot.lastResponseContains(
						"Could you give me the name of the application for which you'd like to report an issue please?"),
				true);
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void antivirus() {
		NavigationTree.clickButton(NTAntivirus, chatbot);
		assertEquals(chatbot.lastResponseContains("UhOh! sounds like trouble!"), true);
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void exchange() {
		NavigationTree.clickButton(NTExchange, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Emailing is a broad topic, so I need you to select one of the categories below:"), true);
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void activeDirectory() {
		NavigationTree.clickButton(NTActiveDirectory, chatbot);
		assertEquals(chatbot.lastResponseContains("There's quite a few things I can do for you regarding this topic."),
				true);
	}

	@Test(dependsOnMethods = { "incidentSecurity" }, groups = {})
	public void vPN() {
		NavigationTree.clickButton(NTVPN, chatbot);
		assertEquals(chatbot.lastResponseContains("Bad performance of VPN connection"), true);
	}

	@Test(dependsOnMethods = { "antivirus" }, groups = {})
	public void virusFound() {
		NavigationTree.clickButton(NTVirusFound, chatbot);
		assertEquals(
				chatbot.lastResponseContains(
						"I need to create a ticket right away and someone will contact you in order to have a look into this."),
				true);
	}

	@Test(dependsOnMethods = { "antivirus" }, groups = {})
	public void problemAV() {
		NavigationTree.clickButton(NTProblemAVSoftware, chatbot);
		assertEquals(chatbot.lastResponseContains("This will have to be fixed rather fast, since"), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void issueEmails() {
		NavigationTree.clickButton(NTIssueSendingReceivingEmails, chatbot);
		assertEquals(chatbot.lastResponseContains("The easiest solution for this is to restart your PC"), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void spam() {
		NavigationTree.clickButton(NTSpam, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Do you wish to obtain some information and some tips on how to deal with SPAM?"), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void notSpam() {
		NavigationTree.clickButton(NTNotSpam, chatbot);
		assertEquals(chatbot.lastResponseContains("Ah! so the filter worked a little too well in that case"), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void accessMailboxExhange() {
		NavigationTree.clickButton(NTAccessSharedMailbox, chatbot);
		assertEquals(chatbot.lastResponseContains("You need to be aware that an access to a shared mailbox "), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void calendarAccess() {
		NavigationTree.clickButton(NTCalendarAccesses, chatbot);
		assertEquals(chatbot.lastResponseContains("Does this concern your own calendar or someone else's calendar?"),
				true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void accessSharedCalendar() {
		NavigationTree.clickButton(NTAccessSharedCalendar, chatbot);
		assertEquals(chatbot.lastResponseContains("Do you need access to a shared calendar or do you have "), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void sendAsPermissions() {
		NavigationTree.clickButton(NTSendAsPermission, chatbot);
		assertEquals(chatbot.lastResponseContains("Do you need to send emails on behalf of:"), true);
	}

	@Test(dependsOnMethods = { "exchange" }, groups = {})
	public void otherExchange() {
		NavigationTree.clickButton(NTOtherExchangeSoftware, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you give me a detailed description of your problem please?"),
				true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void grantAccessFolder() {
		NavigationTree.clickButton(NTGrantFolderAccess, chatbot);
		assertEquals(chatbot.lastResponseContains("That's a tricky one for me."), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeLastName() {
		NavigationTree.clickButton(NTChangeLastName, chatbot);
		assertEquals(chatbot.lastResponseContains("Allright. So which last name"), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void addEmail() {
		NavigationTree.clickButton(NTAddEmailToAccount, chatbot);
		assertEquals(chatbot.lastResponseContains("Ah! that's an easy one"), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeNameManager() {
		NavigationTree.clickButton(NTChangeNameManager, chatbot);
		assertEquals(chatbot.lastResponseContains("I can do that! What is the name of your new manager?"), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void changeOfficeAddress() {
		NavigationTree.clickButton(NTChangeOfficeAddress, chatbot);
		assertEquals(chatbot.lastResponseContains("Unfortunately I cannot change your office location myself "), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void unlockAccount() {
		NavigationTree.clickButton(NTUnlockAccount, chatbot);
		assertEquals(chatbot.lastResponseContains("Oh, let's fix that. Can I have your username please?"), true);
	}

	@Test(dependsOnMethods = { "activeDirectory" }, groups = {})
	public void resetPassword() {
		NavigationTree.clickButton(NTResetPassword, chatbot);
		assertEquals(chatbot.lastResponseContains("Sure, what is your username please?"), true);
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void badPerformance() {
		NavigationTree.clickButton(NTBadPerformanceVPN, chatbot);
		assertEquals(chatbot.lastResponseContains(
				"Are you experiencing the same performance issues while you're not logged on via VPN?"), true);
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void unableLogin() {
		NavigationTree.clickButton(NTUnableToLoginVPN, chatbot);
		chatbot.waitForResponse();
		chatbot.waitForResponse();
		assertEquals(chatbot.responseContains("OK. Let me try something very quickly.", 2), true);
	}

	@Test(dependsOnMethods = { "vPN" }, groups = {})
	public void otherVPN() {
		NavigationTree.clickButton(NTOtherVPNIssue, chatbot);
		assertEquals(chatbot.lastResponseContains("Can you give me a description of your problem please?"), true);
	}

}
