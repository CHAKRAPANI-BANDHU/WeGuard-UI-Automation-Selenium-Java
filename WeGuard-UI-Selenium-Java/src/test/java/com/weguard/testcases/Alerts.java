package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.*;


public class Alerts extends Login {
	
	@Test(priority = 11)
	void alerts() throws InterruptedException, IOException 
	{
	    ExtentTest test = extent.createTest("Alerts_Testcases").assignAuthor("QA_Wenable");
	    logger.info("Navigating to the Alerts page");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    AlertsPage ap = new AlertsPage(driver, test, logger);
	    
	    // Alerts Page
	    try {
	    	
	        ap.getAlertsURL();
	        Thread.sleep(3000);
	        test.pass("Alerts module is displayed on the left navigation bar");
	     
	        // Print all the alerts of Alerts page with page size is 100
			try {
	         ap.displayAllAlerts();
	         test.pass("All the Alerts are displayed");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "alertsAllFailedScreenshot");
				test.fail("All the Alerts are not displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

	        // Filter by Alert Type
			try {
	         ap.clickFilterByAlertType();
	         test.pass("Filter by alert type is present in Alerts page");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "alertsFilterAlertTypeFailedScreenshot");
				test.fail("Filter by alert type is not present in Alerts page", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
	        // Get all the drop-down options from Filter by Alert Type and print them
			try {
             test.info("Filter by Alert Type dropdown options are: ");
	         ap.getAllDropdownOptions();
	         test.pass("Dropdown options in Filter by Alert Type are displayed.");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "alertsFilterAlertTypeFailedScreenshot");
				test.fail("Dropdown options in Filter by Alert Type are not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
                     
	    } catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "dashboardFailedScreenshot");
			test.fail("Alerts module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    }
	}
}
	