package org.zilch.com.web.pages;

import org.zilch.com.utilities.ui.ElementRetrieve;
import org.zilch.com.utilities.ui.WaitHelper;
import org.zilch.com.web.drivers.BrowserDriver;

public abstract class BasePage {
    public ElementRetrieve ele;
    public WaitHelper wait;

    public BasePage() {
        ele = new ElementRetrieve();
        wait = new WaitHelper(BrowserDriver.getDriver());
    }
}
