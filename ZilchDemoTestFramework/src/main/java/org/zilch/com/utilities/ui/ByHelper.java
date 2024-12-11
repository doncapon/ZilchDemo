package org.zilch.com.utilities.ui;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.zilch.com.utilities.enums.Locator;

public class ByHelper {

    public static By getBy(Locator type, String value) {
        switch (type) {
            case XPATH:
                return By.xpath(value);
            case ID:
                return By.id(value);
            case CSS:
                return By.cssSelector(value);
            case NAME:
                return By.name(value);
            case TAGNAME:
                return By.tagName(value);
            case CLASSNAME:
                return By.className(value);
            case ACCESSIBILITYID:
                return MobileBy.accessibilityId(value);
            case PREDICATE:
                return MobileBy.iOSNsPredicateString(value);
            case CLASSCHAIN:
                return MobileBy.iOSClassChain(value);
                default:
                    return null;
        }
    }
}
