package org.zilch.com.mobile.pages.PageObjects;

public interface ForgotPasswordPageElements {
    String androidQuestionTxt = "//android.widget.TextView[@text=\"Forgot your password?\"]"; //xpath
    String androidAffirmationTxt = "//android.widget.TextView[@text=\"Not to worry, let's get you a new one\"]"; //xpath
    String androidEmailInput = "new UiSelector().resourceId(\"forgot-password-email\")"; //uiautomator
    String androidSendLinkButton = "new UiSelector().text(\"Send Link\")"; //uiautomator
}
