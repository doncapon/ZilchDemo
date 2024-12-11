package org.zilch.com.web.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.zilch.com.utilities.ConfigurationManager;

public class BrowserDriver {

    private static WebDriver driver;
    private static ConfigurationManager configManager;

    // Private constructor to prevent instantiation
    private BrowserDriver() {

    }
    public static WebDriver getDriver() {
        configManager = new ConfigurationManager("config");
        if (driver == null) {
            setupDriver(configManager.getConfigProperty("browser", "chrome" ));
        }
        return driver;
    }

    public static void quitWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset the instance so it can be reinitialized later
        }
    }

    private static void setupDriver(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
    }
}
