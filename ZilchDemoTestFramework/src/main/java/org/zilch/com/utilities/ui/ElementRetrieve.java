package org.zilch.com.utilities.ui;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.zilch.com.utilities.enums.Locator;
import org.zilch.com.web.drivers.BrowserDriver;
import org.zilch.com.mobile.helpers.AppiumHelper;

import java.util.List;

public class ElementRetrieve {

	private final boolean isMobile;
	private final String platformName;
	WebDriver driver;
	// Default constructor for web only
	public ElementRetrieve(){
		this(false, "");
	}

	// Constructor to determine platform (Web or Mobile)
	public ElementRetrieve(boolean isMobile, String platformName) {
		this.isMobile = isMobile;
		this.platformName = platformName;
		driver = getDriver();
	}
    // Get a single WebElement
	public WebElement getWebElement(Locator type, String identifier) {
		WebDriver driver = getDriver();
		return findElement(type, identifier);
	}

	// Get multiple WebElements
	public List<WebElement> getWebElements(Locator type, String identifier) {
		return findElements(type, identifier);
	}

	// Helper to fetch the appropriate driver
	private WebDriver getDriver() {
		try{
			return isMobile ? AppiumHelper.getInstance().getDriver() : BrowserDriver.getDriver();

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	// Helper to find a single WebElement
	private WebElement findElement(Locator type, String identifier) {
		switch (type) {
			case XPATH:
				return driver.findElement(By.xpath(identifier));
			case ID:
				return driver.findElement(By.id(identifier));
			case CSS:
				return driver.findElement(By.cssSelector(identifier));
			case CLASSNAME:
				return driver.findElement(By.className(identifier));
			case NAME:
				return driver.findElement(By.name(identifier));
			case TAGNAME:
				return driver.findElement(By.tagName(identifier));
			case ACCESSIBILITYID: // Cross-platform (iOS and Android)
				if (platformName.equals("Android") || platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElement(MobileBy.AccessibilityId(identifier));
				}
				throw new UnsupportedOperationException("ACCESSIBILITYID is not supported for WebDriver");
			case PREDICATE: // iOS-specific
				if (platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElement(MobileBy.iOSNsPredicateString(identifier));
				}
				throw new UnsupportedOperationException("PREDICATE is only supported for iOS");
			case CLASSCHAIN: // iOS-specific
				if (platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElement(MobileBy.iOSClassChain(identifier));
				}
				throw new UnsupportedOperationException("CLASSCHAIN is only supported for iOS");

			case UIAUTOMATOR: // iOS-specific
				if (platformName.equals("Android")) {
					return ((AppiumDriver) driver).findElement(new AppiumBy.ByAndroidUIAutomator(identifier));
				}
				throw new UnsupportedOperationException("CLASSCHAIN is only supported for iOS");
			default:
				throw new IllegalArgumentException("Invalid locator type: " + type);
		}
	}

	// Helper to find multiple WebElements
	private List<WebElement> findElements(Locator type, String identifier) {
		switch (type) {
			case XPATH:
				return driver.findElements(By.xpath(identifier));
			case ID:
				return driver.findElements(By.id(identifier));
			case CSS:
				return driver.findElements(By.cssSelector(identifier));
			case NAME:
				return driver.findElements(By.name(identifier));
			case CLASSNAME:
				return driver.findElements(By.className(identifier));
			case TAGNAME:
				return driver.findElements(By.tagName(identifier));
			case ACCESSIBILITYID: // Cross-platform (iOS and Android)
				if (platformName.equals("Android") || platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElements(MobileBy.AccessibilityId(identifier));
				}
				throw new UnsupportedOperationException("ACCESSIBILITYID is not supported for WebDriver");
			case PREDICATE: // iOS-specific
				if (platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElements(MobileBy.iOSNsPredicateString(identifier));
				}
				throw new UnsupportedOperationException("PREDICATE is only supported for iOS");
			case CLASSCHAIN: // iOS-specific
				if (platformName.equals("iOS")) {
					return ((AppiumDriver) driver).findElements(MobileBy.iOSClassChain(identifier));
				}
				throw new UnsupportedOperationException("CLASSCHAIN is only supported for iOS");
			default:
				throw new IllegalArgumentException("Invalid locator type: " + type);
		}
	}
}
