package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.DevicesPage;


public class Devices extends Login {

	@Test(priority = 3)
	void devices() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("Devices_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Devices page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DevicesPage devicespage = new DevicesPage(driver, test, logger);
		
		try {
			devicespage.devicesURLClick();
			test.pass("Devices module is displayed on the left navigation bar");
			
			// Devices Page
			try {
				String columns = devicespage.columnsOfDevicesInfo();
				test.pass(columns);
				devicespage.devicesList();
				test.pass("Devices list is displayed.");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesListFailedScreenshot");
				test.fail("Devices list is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "devicesFailedScreenshot");
			test.fail("Devices module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}