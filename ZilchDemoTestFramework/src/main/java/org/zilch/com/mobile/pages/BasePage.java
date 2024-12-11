package org.zilch.com.mobile.pages;

import org.zilch.com.mobile.helpers.AppiumHelper;
import org.zilch.com.mobile.helpers.MobileActions;
import org.zilch.com.utilities.ConfigurationManager;
import org.zilch.com.utilities.ui.ElementRetrieve;
import org.zilch.com.utilities.ui.WaitHelper;

public abstract class BasePage {
    public ConfigurationManager configManager;
    public ElementRetrieve ele;
    public WaitHelper wait;
    public String platformName;
    public MobileActions mobileActor;

    public BasePage() {
        configManager = new ConfigurationManager("mobile");
        platformName  = configManager.getConfigProperty("PLATFORM_NAME");
        ele = new ElementRetrieve(true, platformName);
        wait = new WaitHelper(AppiumHelper.getInstance().getDriver());
        mobileActor = new MobileActions(AppiumHelper.getInstance().getDriver());
    }
}
