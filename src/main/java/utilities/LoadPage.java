package utilities;

import org.openqa.selenium.WebDriver;

public class LoadPage {
	public static boolean goToHomePage(WebDriver driver){
		driver.get("https://alicedemo.sogeti.be/#!/");
		return true;
	}
}
