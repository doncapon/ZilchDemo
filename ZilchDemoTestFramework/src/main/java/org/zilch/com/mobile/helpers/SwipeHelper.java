package org.zilch.com.mobile.helpers;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class SwipeHelper {
    public static void swipeLeft(AppiumDriver driver) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        // Define start and end points for the swipe
        int startX = (int) (screenWidth * 0.8); // Start from 80% width
        int endX = (int) (screenWidth * 0.2);   // End at 20% width
        int centerY = screenHeight / 2;        // Vertical center

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Move to start point
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, centerY));
        // Press down
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Move to end point
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, centerY));
        // Release finger
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the action
        driver.perform(Arrays.asList(swipe));
    }
}
