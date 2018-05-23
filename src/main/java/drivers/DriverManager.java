package drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class DriverManager {

	public static ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Drivers\\chromedriver.exe");
		ChromeDriver chromeDriver = new ChromeDriver();
		return chromeDriver;
	}

	public static FirefoxDriver getFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Drivers\\geckodriver.exe");
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
		FirefoxDriver firefoxDriver = new FirefoxDriver();
		return firefoxDriver;
	}

}
