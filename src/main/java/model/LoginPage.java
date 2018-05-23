package model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private static final String userIdPath = "/html/body/div[2]/div/div[3]/form/input[1]";
	private static final String passwordPath = "/html/body/div[2]/div/div[3]/form/input[2]";
	private static final String loginButtonPath = "/html/body/div[2]/div/div[3]/form/button";

	@FindBy(xpath = userIdPath)
	private WebElement userId;
	@FindBy(xpath = passwordPath)
	private WebElement password;
	@FindBy(xpath = loginButtonPath)
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public LoginPage enterUserId(String input) {
		userId.sendKeys(input);
		return this;
	}

	public LoginPage enterPassword(String input) {
		password.sendKeys(input);
		return this;
	}

	public LoginPage clickLoginButton() {
		loginButton.click();
		return this;
	}

}
