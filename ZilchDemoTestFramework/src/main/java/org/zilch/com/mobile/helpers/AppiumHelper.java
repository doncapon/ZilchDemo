package org.zilch.com.mobile.helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.zilch.com.utilities.ConfigurationManager;

import java.io.File;
import java.net.URL;

public class AppiumHelper {
    private static AppiumHelper instance; // Singleton instance
    private AppiumDriver driver;         // Single driver instance
    private ConfigurationManager configManager;

    // Private constructor to prevent instantiation
    private AppiumHelper() {
        configManager = new ConfigurationManager("mobile");
    }

    // Public method to provide access to the Singleton instance
    public static synchronized AppiumHelper getInstance() {
        if (instance == null) {
            instance = new AppiumHelper();
        }
        return instance;
    }

    // Method to get the Appium driver
    public synchronized AppiumDriver getDriver() {
        try {
            if (driver == null) {
                DesiredCapabilities caps = new DesiredCapabilities();

                // Common Capabilities
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, configManager.getConfigProperty("PLATFORM_NAME"));
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, configManager.getConfigProperty("DEVICE_NAME"));
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, configManager.getConfigProperty("AUTOMATION_NAME"));
                caps.setCapability("newCommandTimeout", Integer.parseInt(configManager.getConfigProperty("NEW_COMMAND_TIMEOUT")));
                caps.setCapability("uiautomator2ServerLaunchTimeout", Integer.parseInt(configManager.getConfigProperty("DRIVER_LAUNCH_TIMEOUT")));
                caps.setCapability("adbExecTimeout", Integer.parseInt(configManager.getConfigProperty("ADB_EXEC_TIMEOUT")));
                caps.setCapability("noReset", false); // Resets app data
                caps.setCapability("fullReset", false); // Resets app state without uninstalling
                // Determine Physical or Emulator
                if (configManager.getConfigProperty("DEVICE_TYPE").equalsIgnoreCase("physical")) {
                    // Physical device-specific capabilities
                    caps.setCapability("udid", configManager.getConfigProperty("DEVICE_UDID"));
                } else {
                    // Emulator-specific capabilities
                    caps.setCapability("avdLaunchTimeout", configManager.getConfigProperty("AVD_LAUNCH_TIMEOUT"));
                    String appExtension = configManager.getConfigProperty("PLATFORM_NAME").equalsIgnoreCase("Android") ? ".apk" : ".ipa";
                    caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + File.separator + "apps" + File.separator
                            +configManager.getConfigProperty("PLATFORM_NAME").toLowerCase()+ File.separator+
                            configManager.getConfigProperty("APP_NAME") + appExtension);
                    caps.setCapability("autoGrantPermissions", true);
                }

                // Platform-Specific Capabilities
                if (configManager.getConfigProperty("PLATFORM_NAME").equalsIgnoreCase("Android")) {
                    caps.setCapability("androidInstallTimeout", Integer.parseInt(configManager.getConfigProperty("ANDROID_INSTALL_TIMEOUT")));
                    caps.setCapability("autoGrantPermissions", true); // Automatically grant app permissions
                    caps.setCapability("uiautomator2ServerLaunchTimeout", Integer.parseInt(configManager.getConfigProperty("DRIVER_LAUNCH_TIMEOUT")));
                    driver = new AndroidDriver(new URL(configManager.getConfigProperty("APPIUM_SERVER_URL")), caps);
                } else if (configManager.getConfigProperty("PLATFORM_NAME").equalsIgnoreCase("iOS")) {
                    caps.setCapability("autoAcceptAlerts", true); // Automatically accept alerts
                    caps.setCapability("wdaLaunchTimeout", Integer.parseInt(configManager.getConfigProperty("DRIVER_LAUNCH_TIMEOUT"))); // iOS-specific timeout
                    driver = new IOSDriver(new URL(configManager.getConfigProperty("APPIUM_SERVER_URL")), caps);
                } else {
                    throw new IllegalArgumentException("Invalid platform name specified: " + configManager.getConfigProperty("PLATFORM_NAME"));
                }
            }
            return driver;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to quit the driver
    public synchronized void quitMobileDriver() {
        if (driver != null) {
            try{
                driver.quit();
            }catch(Exception e){
                System.err.println("Error while quitting the driver: " + e.getMessage());
            }finally {
                driver = null;
            }
        }
    }
}
