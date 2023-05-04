package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class WeTrack extends Login {

	@Test(priority = 7)
	void wetrack() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("WeTrack_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the WeTrack page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// WeTrack Page
		try {
			driver.findElement(By.xpath("//*[@href=\"#/geocoordinates\"]")).click();
			test.pass("WeTrack module is displayed on the left navigation bar");
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "wetrackFailedScreenshot");
			test.fail("WeTrack module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}