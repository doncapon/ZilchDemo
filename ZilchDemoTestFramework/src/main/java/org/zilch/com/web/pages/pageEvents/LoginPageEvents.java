package org.zilch.com.web.pages.pageEvents;

import org.openqa.selenium.By;
import org.zilch.com.utilities.enums.Locator;
import org.zilch.com.web.pages.BasePage;
import org.zilch.com.web.pages.pageObjects.LoginPageElements;

public class LoginPageEvents extends BasePage {
	public LoginPageEvents() {
		super();
		wait.waitForVisibility(new By.ById(LoginPageElements.loginBtn));
	}

	public void enterCredentials(String username, String pin) {
		EnterUsername(username);
		EnterPassword(pin);
		ele.getWebElement(Locator.ID, LoginPageElements.loginBtn).click();
	}

	public void EnterUsername(String username) {
		ele.getWebElement(Locator.ID, LoginPageElements.emailInp).clear();
		ele.getWebElement(Locator.ID, LoginPageElements.emailInp).sendKeys(username);
	}

	public void EnterPassword(String passwprd) {
		ele.getWebElement(Locator.ID, LoginPageElements.passwordInp).clear();
		ele.getWebElement(Locator.ID, LoginPageElements.passwordInp).sendKeys(passwprd);
	}

}
