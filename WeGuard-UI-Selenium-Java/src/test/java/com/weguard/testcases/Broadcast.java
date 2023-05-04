package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.BroadcastPage;


public class Broadcast extends Login {

	@Test(priority = 9)
	void broadcast() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("Broadcast_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Broadcast page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		BroadcastPage bp = new BroadcastPage(driver, test, logger);
		
		// Broadcast Page
		try {
			bp.getBroadcastURL();
			test.pass("Broadcast module is displayed on the left navigation bar");
			
			try {
	        // Print all the message of the Broadcast page with page size is 100
	        bp.displayAllBroadcastMessages();
	        test.pass("All the broadcast messages are displayed");
			    } 
			   catch (NoSuchElementException e) {
				String screenshotPath = getScreenshot(driver, "broadcastMessagesFailedScreenshot");
				test.fail("All the broadcast messages are displayed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			   }
			
			
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "broadcastFailedScreenshot");
			test.fail("Broadcast module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}