package org.zilch.com.mobile.pages.pageEvents;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import org.zilch.com.mobile.pages.BasePage;
import org.zilch.com.mobile.pages.PageObjects.ForgotPasswordPageElements;
import org.zilch.com.utilities.enums.Locator;

public class ForgotPasswordPageEvent extends BasePage {

    public ForgotPasswordPageEvent() {
        super();
        wait.waitForVisibility(new AppiumBy.ByAndroidUIAutomator(ForgotPasswordPageElements.androidSendLinkButton));
    }

    private boolean questionTextIsVisible(){
        return   ele.getWebElement(Locator.XPATH, ForgotPasswordPageElements.androidQuestionTxt).isDisplayed();
    }

    private boolean affirmationTextIsVisible(){
        return   ele.getWebElement(Locator.XPATH, ForgotPasswordPageElements.androidAffirmationTxt).isDisplayed();
    }

    public boolean textsAreVisible(){
        return questionTextIsVisible() && affirmationTextIsVisible();
    }

    public void sendResetEmail(String email){
        wait.waitForClickability(MobileBy.androidUIAutomator(ForgotPasswordPageElements.androidEmailInput));

        ele.getWebElement(Locator.UIAUTOMATOR, ForgotPasswordPageElements.androidEmailInput).sendKeys(email);
        ele.getWebElement(Locator.UIAUTOMATOR, ForgotPasswordPageElements.androidSendLinkButton).click();

    }

}
