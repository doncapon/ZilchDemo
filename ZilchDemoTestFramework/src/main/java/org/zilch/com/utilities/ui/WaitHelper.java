package org.zilch.com.utilities.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitHelper {
    private WebDriver driver; // Supports both WebDriver and AppiumDriver
    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this(driver, 5); // Default timeout set to 10 seconds
    }

    public WaitHelper(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WebElement waitForVisibility(By locator) {
        try{
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public WebElement waitForClickability(By locator) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean waitForInvisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public WebElement waitForTextToBePresent(By locator, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
            return driver.findElement(locator);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void staticWait(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000L); // Explicit sleep for static wait
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
