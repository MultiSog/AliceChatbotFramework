package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import model.LoginPage;

public class LoginUser {
	public static boolean loginUser(WebDriver driver){
		try{
			LoadPage.goToHomePage(driver);
			LoginPage page = new LoginPage(driver);
			page.enterUserId(HelpStrings.USERID);
			page.enterPassword(HelpStrings.PASSWORD);
			page.clickLoginButton();
			new WebDriverWait(driver, 10).until(ExpectedConditions.urlMatches("https://alicedemo.sogeti.be/#!/alice"));
		} catch(Exception e){
			return false;
		}
		return true;
	}
}
