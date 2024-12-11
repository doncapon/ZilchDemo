package org.zilch.com.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.zilch.com.mobile.helpers.AppiumHelper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class MobileBaseTest {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    public ExtentTest logger;
    public static AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupSuite() throws Exception {
        if (driver == null || ((AppiumDriver) driver).getSessionId() == null) {
            driver = AppiumHelper.getInstance().getDriver();
        }
    }
    @BeforeTest
    public void beforeTest() {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports" +File.separator + "mobile" +
                File.separator + "extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        sparkReporter.config().setTheme(Theme.DARK);
        extent.setSystemInfo("HostName", "RHEL8");
        extent.setSystemInfo("UserName", "root");
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Automation Tests Results by Olusegun Akintimehin");
    }

    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        logger = extent.createTest(testMethod.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

            // Take a screenshot if the test fails
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
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }

    public String takeScreenshot(String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Define the screenshots directory
        String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshots"+ File.separator + "mobile";

        // Ensure the screenshots directory exists
        File directory = new File(screenshotDir);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // Create directory
            if (!isCreated) {
                System.err.println("Failed to create directory: " + screenshotDir);
                return null; // Return null if directory creation fails
            }
        }

        // Define the destination path for the screenshot
        String destination = screenshotDir + File.separator + testName + ".png";
        File finalDestination = new File(destination);

        try {
            FileHandler.copy(source, finalDestination);
            System.out.println("Screenshot saved at: " + destination);
            return destination; // Return the screenshot path
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @AfterClass
    public void tearDown() {
        AppiumHelper.getInstance().quitMobileDriver();
    }

}
