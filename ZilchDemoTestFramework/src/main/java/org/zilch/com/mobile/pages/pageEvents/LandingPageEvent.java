package org.zilch.com.mobile.pages.pageEvents;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.zilch.com.mobile.pages.BasePage;
import org.zilch.com.mobile.pages.PageObjects.LandingPageElements;
import org.zilch.com.utilities.enums.Locator;

public class LandingPageEvent extends BasePage {

    public LandingPageEvent() {
        super();
        wait.waitForClickability(new AppiumBy.ByAccessibilityId(LandingPageElements.androidLoginBtn));
    }

    public WebElement getElementByAndroidXpathInCarousel(String text){
        String uiAutomator = "new UiSelector().text(\""+text+"\")";
        wait.waitForVisibility(AppiumBy.androidUIAutomator(uiAutomator));
        return ele.getWebElement(Locator.UIAUTOMATOR,uiAutomator);
    }

    private boolean createAccountButtonIsVisible(){
        return   ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidCreateAccountBtn).isDisplayed() &&
                        ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidCreateAccountBtn).isEnabled();
    }

    private boolean loginButtonIsVisible(){
        return   ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidLoginBtn).isDisplayed() &&
                ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidLoginBtn).isEnabled();
    }

    public void clickLoginButton(){
        wait.waitForVisibility(new AppiumBy.ByAccessibilityId(LandingPageElements.androidLoginBtn));
        mobileActor.safeTap(ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidLoginBtn));
//        ele.getWebElement(Locator.ACCESSIBILITYID, LandingPageElements.androidLoginBtn).click();
    }

    public boolean buttonsAreVisible(){
        return loginButtonIsVisible() && createAccountButtonIsVisible();
    }
}
