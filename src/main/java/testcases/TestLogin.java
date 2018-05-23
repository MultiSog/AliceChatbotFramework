package testcases;

import static org.testng.Assert.assertEquals;
import static utilities.HelpStrings.PASSWORD;
import static utilities.HelpStrings.USERID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import drivers.DriverManager;
import model.LoginPage;
import utilities.LoadPage;

@Test(suiteName = "Login")
public class TestLogin {

	private WebDriver driver;
	private LoginPage page;
	Logger logger = Logger.getLogger("TestLogin");
	private int passes;
	private int fails;

	@BeforeMethod
	public void loadVariables() {
		driver = DriverManager.getChromeDriver();
		LoadPage.goToHomePage(driver);
		page = new LoginPage(driver);

	}

	@AfterTest
	public void logResults() {
		logger.info(passes+" tests passed and "+fails+" failed"+" total: "+(passes+fails));
		logger.info("**********************************");

	}
	
	@BeforeClass
	public void configLogger() {
		PropertyConfigurator.configure("Log4j.properties");

	}

	@AfterMethod
	public void closeDriver() {
		driver.close();
		driver.quit();
		driver = null;
		page = null;
		logger.info("**********************************");

	}

	@Test(testName = "CHAT-28.1 Correct Login", groups = { "Login" })
	public void correctLogin() {
		logger.info("test: correct login");
		page.enterUserId(USERID);
		page.enterPassword(PASSWORD);
		page.clickLoginButton();
		logger.info("step: login request submitted");
		boolean loaded;
		try {
			loaded = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.urlMatches("https://alicedemo.sogeti.be/#!/alice"));
		} catch (TimeoutException e) {
			loaded = false;
		}

		assertEquals(loaded, true);

		if (loaded) {
			logger.info("result: test passed");
			passes++;
		} else {
			logger.info("result: test failed");
			fails++;
		}

	}

	@Test(testName = "Chat-28.2 Wrong Login")
	public void wrongLogin() {
		logger.info("test: incorrect login");
		page.enterUserId(USERID);
		page.enterPassword("wrongPassword");
		page.clickLoginButton();
		logger.info("step: login request submitted");
		boolean loaded;
		try {
			loaded = new WebDriverWait(driver, 10)
					.until(ExpectedConditions.urlMatches("https://alicedemo.sogeti.be/#!/#%2Falice"));
		} catch (TimeoutException e) {
			loaded = false;
		}
		assertEquals(loaded, true);
		if (loaded) {
			logger.info("result: test passed");
			passes++;
		} else {
			logger.info("result: test failed");
			fails++;
		}
	}

}
