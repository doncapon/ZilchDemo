package org.zilch.com.mobile.pages.pageEvents;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import org.zilch.com.mobile.pages.BasePage;
import org.zilch.com.mobile.pages.PageObjects.LoginPageElements;
import org.zilch.com.utilities.enums.Locator;

public class LoginPageEvent extends BasePage {

    public LoginPageEvent() {
        super();
        wait.waitForVisibility(new AppiumBy.ByAndroidUIAutomator(LoginPageElements.androidForgotPasswordLink));
    }

    private boolean welcomeBackTextIsVisible(){
        return   ele.getWebElement(Locator.XPATH, LoginPageElements.androidWelcomeBackTxt).isDisplayed();
    }

    private boolean promptTextIsVisible(){
        return   ele.getWebElement(Locator.XPATH, LoginPageElements.androidLoginPromptTxt).isDisplayed();
    }

    public boolean textsAreVisible(){
        return welcomeBackTextIsVisible() && promptTextIsVisible();
    }

    public void attemptLogin(String email, String password){
        wait.waitForClickability(MobileBy.androidUIAutomator(LoginPageElements.androidEmailInput));

        ele.getWebElement(Locator.UIAUTOMATOR, LoginPageElements.androidEmailInput).sendKeys(email);
        ele.getWebElement(Locator.UIAUTOMATOR, LoginPageElements.androidPasswordInput).sendKeys(password);
        ele.getWebElement(Locator.UIAUTOMATOR, LoginPageElements.androidLoginButton).click();

    }

    public void clickForgotPasswordLink(){
        ele.getWebElement(Locator.UIAUTOMATOR, LoginPageElements.androidForgotPasswordLink).click();
    }
}