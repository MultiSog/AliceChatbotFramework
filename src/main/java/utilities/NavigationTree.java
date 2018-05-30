package utilities;

import java.util.TreeMap;
import static utilities.HelpStrings.*;

import model.Chatbot;

public class NavigationTree {
	private static TreeMap<String, String> responseButtons;
	
	public static TreeMap<String, String> initNavigation() {
		if (responseButtons == null) {
			responseButtons = new TreeMap<>();
			responseButtons.put(NTReportAnIncident, "startNav"); 
			responseButtons.put(NTSubmitARequest, "startNav"); 
			responseButtons.put(NTModifyTicket, "startNav"); 
			responseButtons.put(NTCreateTicket, NTModifyTicket); 
			responseButtons.put(NTIncident, NTModifyTicket); 
			responseButtons.put(NTRequest, NTModifyTicket); 
			responseButtons.put(NTCommunication, NTSubmitARequest); 
			responseButtons.put(NTDataManagement, NTSubmitARequest); 
			responseButtons.put(NTHardwareRequest, NTSubmitARequest); 
			responseButtons.put(NTInfrastructure, NTSubmitARequest); 
			responseButtons.put(NTManagedBusinessApplication, NTSubmitARequest); 
			responseButtons.put(NTEmailMailbox, NTCommunication); 
			responseButtons.put(NTIPTelephonyServices, NTCommunication);
			responseButtons.put(NTCreateDistributionList, NTEmailMailbox); 
			responseButtons.put(NTCreateSharedMailbox, NTEmailMailbox); 
			responseButtons.put(NTDeleteSharedMailbox, NTEmailMailbox); 
			responseButtons.put(NTRevokeSharedMailbox, NTEmailMailbox); 
			responseButtons.put(NTDisableSharedMailbox, NTEmailMailbox); 
			responseButtons.put(NTCreateEmailInFolder, NTEmailMailbox); 
			responseButtons.put(NTGrantUserMailboxAccess, NTEmailMailbox); 
			responseButtons.put(NTGrantSharedMailboxAccess, NTEmailMailbox); 
			responseButtons.put(NTModifyMailbox, NTEmailMailbox); 
			responseButtons.put(NTOtherEmailRequest, NTEmailMailbox); 
			responseButtons.put(NTRegisterEmailDomain, NTEmailMailbox); 
			responseButtons.put(NTBPC, NTManagedBusinessApplication); 
			responseButtons.put(NTClickview, NTManagedBusinessApplication); 
			responseButtons.put(NTClockworks, NTManagedBusinessApplication); 
			responseButtons.put(NTGeneral, NTManagedBusinessApplication); 
			responseButtons.put(NTImis, NTManagedBusinessApplication); 
			responseButtons.put(NTLyris, NTManagedBusinessApplication); 
			responseButtons.put(NTMailchimp, NTManagedBusinessApplication); 
			responseButtons.put(NTMobatime, NTManagedBusinessApplication); 
			responseButtons.put(NTProConcept, NTManagedBusinessApplication); 
			responseButtons.put(NTScorecards, NTManagedBusinessApplication); 
			responseButtons.put(NTHardwareIncident, NTReportAnIncident); 
			responseButtons.put(NTSoftwareIncident, NTReportAnIncident); 
			responseButtons.put(NTSecurityIncident, NTReportAnIncident); 
			responseButtons.put(NTNetworkIncident, NTReportAnIncident); 
			responseButtons.put(NTOfficeApp, NTSoftwareIncident); 
			responseButtons.put(NTCitrix, NTSoftwareIncident); 
			responseButtons.put(NTCommunicationApp, NTSoftwareIncident); 
			responseButtons.put(NTBusinessApplication, NTSoftwareIncident); 
			responseButtons.put(NTAntivirus, NTSecurityIncident); 
			responseButtons.put(NTExchange, NTSecurityIncident); 
			responseButtons.put(NTActiveDirectory, NTSecurityIncident); 
			responseButtons.put(NTVPN, NTSecurityIncident); 
			responseButtons.put(NTVirusFound, NTAntivirus); 
			responseButtons.put(NTProblemAVSoftware, NTAntivirus); 
			responseButtons.put(NTIssueSendingReceivingEmails, NTExchange); 
			responseButtons.put(NTSpam, NTExchange); 
			responseButtons.put(NTNotSpam, NTExchange); 
			responseButtons.put(NTAccessSharedMailbox, NTExchange); 
			responseButtons.put(NTCalendarAccesses, NTExchange); 
			responseButtons.put(NTAccessSharedCalendar, NTExchange); 
			responseButtons.put(NTSendAsPermission, NTExchange); 
			responseButtons.put(NTGrantFolderAccess, NTActiveDirectory); 
			responseButtons.put(NTChangeLastName, NTActiveDirectory);
			responseButtons.put(NTAddEmailToAccount, NTActiveDirectory);
			responseButtons.put(NTChangeNameManager, NTActiveDirectory);
			responseButtons.put(NTChangeOfficeAddress, NTActiveDirectory);
			responseButtons.put(NTUnlockAccount, NTActiveDirectory);
			responseButtons.put(NTResetPassword, NTActiveDirectory);
			responseButtons.put(NTBadPerformanceVPN, NTVPN);
			responseButtons.put(NTUnableToLoginVPN, NTVPN);
			responseButtons.put(NTOtherSoftwareIncident, NTSoftwareIncident);
			responseButtons.put(NTOtherExchangeSoftware, NTExchange);
			responseButtons.put(NTOtherVPNIssue, NTVPN);
		}
		return responseButtons;
	}

	public static void clickButton(String button, Chatbot chatbot) {
		String previous = initNavigation().get(button);
		if (previous.equals("startNav")) {
			chatbot.inputMessage("help");
			chatbot.waitForResponse();
			chatbot.clickButton(button);
			chatbot.waitForResponse();
			return;
		}
		clickButton(previous, chatbot);
		chatbot.clickButton(button);
		chatbot.waitForResponse();
	}
}
