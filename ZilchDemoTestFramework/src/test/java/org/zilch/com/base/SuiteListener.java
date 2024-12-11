package org.zilch.com.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.zilch.com.web.drivers.BrowserDriver;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

	public void onTestFailure(ITestResult result) {
		String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + result.getMethod().getMethodName();
		File fi = ((TakesScreenshot) BrowserDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(fi, new  File (fileName + ".png") );
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	  public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		    annotation.setRetryAnalyzer(RetryAnalyzer.class);
	  }
}
