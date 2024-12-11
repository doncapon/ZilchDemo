package org.zilch.com.web.pages.pageEvents;

import org.openqa.selenium.By;
import org.zilch.com.utilities.DriverExtensions;
import org.zilch.com.utilities.enums.Locator;
import org.zilch.com.web.drivers.BrowserDriver;
import org.zilch.com.web.pages.BasePage;
import org.zilch.com.web.pages.pageObjects.HomePageElements;

public class HomePageEvents extends BasePage {

	public HomePageEvents() {
		super();
		wait.waitForVisibility(new By.ById(HomePageElements.playLink));
	}
	public void clickLogin() {

		if (!ele.getWebElements(Locator.ID, HomePageElements.allowCookie).isEmpty()) {
			DriverExtensions.JavascriptClick(BrowserDriver.getDriver(), ele.getWebElement(Locator.ID, HomePageElements.allowCookie));
		}

		ele.getWebElement(Locator.ID, HomePageElements.loginBtn).click();
	}
}
