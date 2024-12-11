package org.zilch.com.web.pages.pageEvents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.zilch.com.utilities.enums.Locator;
import org.zilch.com.web.drivers.BrowserDriver;
import org.zilch.com.web.pages.BasePage;;
import org.zilch.com.web.pages.pageObjects.PlayLandPageElements;

public class PlayLandPageEvents extends BasePage {

	public PlayLandPageEvents() {
		super();
		wait.waitForVisibility(new By.ByXPath(PlayLandPageElements.playBtn));
	}
	 public void ClickPlayButton() {
		 ele.getWebElement(Locator.XPATH, PlayLandPageElements.playBtn).click();
	     BrowserDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	     String title = BrowserDriver.getDriver().getTitle();
	     if (title.toLowerCase().contains("edit"))
	     {
	    	 ele.getWebElement(Locator.ID, "js-fixtures-edit-entry").click();
	     }
	 }
}
