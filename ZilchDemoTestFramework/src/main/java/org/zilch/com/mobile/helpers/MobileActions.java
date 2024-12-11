package org.zilch.com.mobile.helpers;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

public class MobileActions {
    private AppiumDriver driver;

    public MobileActions(AppiumDriver driver) {
        this.driver = driver;
    }

    public void safeTap(WebElement element) {
        try {
            // Standard click
            element.click();
        } catch (Exception e) {
            System.out.println("Standard click failed. Trying TouchAction...");
            try {
                // TouchAction for tap
                TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
                touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
            } catch (Exception ex) {
                System.out.println("TouchAction also failed: " + ex.getMessage());
            }
        }
    }
}
