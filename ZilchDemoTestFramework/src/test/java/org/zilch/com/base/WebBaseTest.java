package org.zilch.com.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.zilch.com.utilities.ConfigurationManager;
import org.zilch.com.web.drivers.BrowserDriver;

public class WebBaseTest {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    public ConfigurationManager configManager = new ConfigurationManager("supersix");
    public ExtentTest logger;
    public WebDriver driver = null;

    @BeforeTest(alwaysRun = true)
    public void setupReport() {
        // Initialize Extent Report
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator + "web" +
                File.separator + "extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("HostName", "RHEL8");
        extent.setSystemInfo("UserName", "root");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Tests Results by Olusegun Akintimehin");
    }

    @BeforeMethod(alwaysRun = true)
    public void setupWebDriver(Method testMethod) {
        // Initialize WebDriver before each test
        logger = extent.createTest(testMethod.getName());
            driver = BrowserDriver.getDriver(); // Reinitialize WebDriver
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().window().maximize();
            driver.get(configManager.getConfigProperty("ui_baseURL"));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestResults(ITestResult result) {
        // Log results in Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = takeScreenshot(result.getName());
            if (screenshotPath != null) {
                try {
                    logger.addScreenCaptureFromPath(screenshotPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
        }

        // Quit the WebDriver after each test
        BrowserDriver.quitWebDriver();
    }

    @AfterTest(alwaysRun = true)
    public void teardownReport() {
        // Flush Extent Report
        if (extent != null) {
            extent.flush();
        }
    }

    public String takeScreenshot(String testName) {
        if (driver == null) {
            return null; // No driver available for taking screenshots
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Define the screenshots directory
        String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots"+ File.separator + "web";

        // Ensure the screenshots directory exists
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (!isCreated) {
                System.err.println("Failed to create directory: " + screenshotDir);
                return null;
            }
        }

        // Define the destination path for the screenshot
        String destination = screenshotDir + File.separator + testName + ".png";
        File finalDestination = new File(destination);

        try {
            FileHandler.copy(source, finalDestination);
            System.out.println("Screenshot saved at: " + destination);
            return destination;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
