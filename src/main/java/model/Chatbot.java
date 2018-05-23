package model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.StyleOptions;

public class Chatbot {
	private static final String inputFieldPath = "//*[@id='userInput']";
	private static final String inputButtonPath = "//*[@id='textForm']/div[2]/button";
	private static final String openMenuButtonPath = "//*[@id='menuBtn']";
	private static final String closeMenuButtonPath = "//*[@id='menuCloseBtn']";
	private static final String logoutButtonPath = "/html/body/div[2]/div/div/main/div[2]/div/div[2]/div[2]/button";

	@FindBy(xpath = inputFieldPath)
	private WebElement inputField;
	@FindBy(xpath = inputButtonPath)
	private WebElement inputButton;

	@FindBy(xpath = openMenuButtonPath)
	private WebElement openMenuButton;
	@FindBy(xpath = closeMenuButtonPath)
	private WebElement closeMenuButton;

	@FindBy(xpath = logoutButtonPath)
	private WebElement logoutButton;

	private TreeMap<StyleOptions, WebElement> styleButtons;

	private WebDriver driver;

	public Chatbot(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	private void initStyleButtons(WebDriver driver) {
		styleButtons = new TreeMap<StyleOptions, WebElement>();
		styleButtons.put(StyleOptions.ACCOR, driver.findElement(By.xpath("//*[@id='style_accor']")));
		styleButtons.put(StyleOptions.MODERN, driver.findElement(By.xpath("//*[@id='style_modern']")));
		styleButtons.put(StyleOptions.RIPPLE, driver.findElement(By.xpath("//*[@id='style_ripple']")));
		styleButtons.put(StyleOptions.MCI, driver.findElement(By.xpath("//*[@id='style_mci']")));
		styleButtons.put(StyleOptions.SKYFALL, driver.findElement(By.xpath("//*[@id='style_skyfall']")));
	}

	public Chatbot changeStyle(StyleOptions style) {
		openMenu();
		initStyleButtons(driver);
		styleButtons.get(style).click();
		closeMenuButton.click();
		return this;
	}

	public WebElement findButton(String buttonText) throws Exception {
		WebElement response = getResponse(0);
		List<WebElement> buttons = response.findElements(By.className("choiceBtn"));
		for (WebElement btn : buttons) {
			if (btn.getText().equals(buttonText)) {
				return btn;
			}
		}
		throw new Exception("Button not found");
	}

	public boolean clickButton(String buttonText) {
		WebElement element;
		try {
			element = findButton(buttonText);
		} catch (Exception e) {
			return false;
		}
		new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		return true;
	}

	public WebElement findRadioButton(String buttonText) throws Exception {
		List<WebElement> radioButtons = new ArrayList<>();
		driver.findElements(By.cssSelector("input")).forEach(btn -> radioButtons.add(btn));
		for (WebElement btn : radioButtons) {
			if (btn.isEnabled()) {
				if (btn.getAttribute("value").equals(buttonText)) {
					return btn;
				}
			}
		}
		throw new Exception("Button not found");
	}

	public Chatbot clickRadioButton(String buttonText) throws Exception {
		WebElement element = findRadioButton(buttonText);
		element.click();
		return this;
	}

	public WebElement findTextField() throws Exception {
		List<WebElement> elements = driver.findElements(By.className("ng-pristine"));
		for (WebElement element : elements) {
			if (element.isEnabled()) {
				if (element.getAttribute("placeholder") != null) {
					return element;
				}
			}
		}
		throw new Exception("Text field not found");
	}

	public Chatbot fillInResponseTextarea(String text) throws Exception {
		findTextField().sendKeys(text);
		return this;
	}

	public Chatbot inputMessage(String message) {
		inputField.sendKeys(message);
		sendMessage();
		return this;
	}

	private Chatbot sendMessage() {
		inputButton.click();
		return this;
	}

	public boolean waitForMultipleResponses(int responses) {
		return waitForMultipleResponses(responses, 5000);
	}

	public boolean waitForMultipleResponses(int responses, int waitInMillis) {
		for (int i = 0; i < responses; i++) {
			if (!waitForResponse(waitInMillis)) {
				return false;
			}
		}
		return true;
	}

	public boolean waitForResponse() {
		return waitForResponse(5000);
	}

	public boolean waitForResponse(int waitInMillis) {
		int elements = driver.findElements(By.className("message-in")).size();
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < waitInMillis) {
			if (elements < driver.findElements(By.className("message-in")).size()) {
				return true;
			}
		}
		return false;
	}

	public Chatbot logout() {
		logoutButton.click();
		return this;
	}

	public Chatbot openMenu() {
		openMenuButton.click();
		new WebDriverWait(driver, 60)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='style_accor']")));
		initStyleButtons(driver);
		return this;
	}

	public Chatbot closeMenu() {
		closeMenuButton.click();
		new WebDriverWait(driver, 60).until(driver -> {
			while (closeMenuButton.isDisplayed()) {

			}
			return true;
		});
		return this;
	}

	public boolean responseContains(String text, int messageNumber) {
		try {
			WebElement element = getResponse(messageNumber);
			return element.getText().contains(text);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean lastResponseContains(String text) {
		return responseContains(text, 0);
	}

	public WebElement getResponse(int response) throws Exception {
		List<WebElement> elements = driver.findElements(By.className("message"));
		if (elements.size() == 0) {
			throw new Exception("No responses found");
		}
		return elements.get(elements.size() - 1 - response);
	}

	public boolean waitForGivenResponse(String text) {
		return waitForGivenResponse(text, 5000);
	}

	public boolean waitForGivenResponse(String text, long waitInMillis) {
		long startTime = System.currentTimeMillis();
		while (System.currentTimeMillis() - startTime > waitInMillis) {
			try {
				if (getResponse(0).getText().contains(text)) {
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
