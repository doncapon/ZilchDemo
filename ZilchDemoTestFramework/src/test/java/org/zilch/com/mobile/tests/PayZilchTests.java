package org.zilch.com.mobile.tests;

import org.testng.annotations.Test;
import org.zilch.com.base.MobileBaseTest;
import org.zilch.com.mobile.helpers.AppiumHelper;
import org.zilch.com.mobile.helpers.SwipeHelper;
import org.zilch.com.mobile.pages.pageEvents.ForgotPasswordPageEvent;
import org.zilch.com.mobile.pages.pageEvents.LandingPageEvent;
import org.zilch.com.mobile.pages.pageEvents.LoginPageEvent;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PayZilchTests extends MobileBaseTest {

    LandingPageEvent landingPageEvent = new LandingPageEvent();
    LoginPageEvent loginPageEvent;
    ForgotPasswordPageEvent forgotPasswordPageEvent;

    @Test(priority=1)
    public void testLandingPageTextAndLogin() {

        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Pay Zilch."));
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Start with credit up to £2250. No interest. No late fees. From 18.6% APR REP (fees may apply)."));

        SwipeHelper.swipeLeft(AppiumHelper.getInstance().getDriver());
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Flexible ways to pay."));
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Pay over 6 weeks, 3 months or pay in full at checkout for Zilch Rewards."));

        SwipeHelper.swipeLeft(AppiumHelper.getInstance().getDriver());
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Shop online or in-store."));
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Pay online or add your Zilch card to your digital wallet to Tap and Pay on the go."));

        SwipeHelper.swipeLeft(AppiumHelper.getInstance().getDriver());
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Get more in the app."));
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Exclusive deals, no fees at thousands of stores and up to 5% back in Zilch Rewards using Pay now."));

        SwipeHelper.swipeLeft(AppiumHelper.getInstance().getDriver());
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("Tried and trusted."));
        assertNotNull(landingPageEvent.getElementByAndroidXpathInCarousel("We have over 40,000 5* reviews on Trustpilot."));

        assertTrue(landingPageEvent.buttonsAreVisible());
    }

    @Test(priority = 3)
    public void testLoginFunctionality(){
        landingPageEvent.clickLoginButton();
        loginPageEvent = new LoginPageEvent();
        assertTrue(loginPageEvent.textsAreVisible());
        loginPageEvent.attemptLogin("olusegun.akintimehin@gmail.com", "password123");
    }

    @Test(priority = 2)
    public void testForgotPassword(){
        landingPageEvent.clickLoginButton();
        loginPageEvent = new LoginPageEvent();
        loginPageEvent.clickForgotPasswordLink();
        forgotPasswordPageEvent = new ForgotPasswordPageEvent();
        assertTrue(forgotPasswordPageEvent.textsAreVisible());

        forgotPasswordPageEvent.sendResetEmail("olusegun.akintimehin@gmail.com");
    }

}
