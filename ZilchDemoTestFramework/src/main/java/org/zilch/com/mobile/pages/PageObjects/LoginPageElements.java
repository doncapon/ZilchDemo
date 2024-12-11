package org.zilch.com.mobile.pages.PageObjects;

public interface LoginPageElements {
    String androidWelcomeBackTxt = "//android.widget.TextView[@text=\"Welcome back!\"]"; //xpath
    String androidLoginPromptTxt = "//android.widget.TextView[@text=\"Enter your details below to log in.\"]"; //xpath
    String androidEmailInput = "new UiSelector().resourceId(\"login-email\")"; //uiautomator
    String androidPasswordInput = "new UiSelector().resourceId(\"login-password\")"; //uiautomator
    String androidForgotPasswordLink = "new UiSelector().text(\"Forgot your password?\")"; //uiautomator
    String androidLoginButton = "new UiSelector().text(\"Log in\")"; //uiautomator
}
