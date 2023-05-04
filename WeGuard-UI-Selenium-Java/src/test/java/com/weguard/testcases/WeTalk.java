package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class WeTalk extends Login {

	@Test(priority = 12)
	void wetalk() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("WeTalk_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the WeTalk page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// WeTalk Page
		try {
			driver.findElement(By.xpath("//*[@href=\"#/wetalk\"]")).click();
			test.pass("WeTalk module is displayed on the left navigation bar in this account");
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "wetalkFailedScreenshot");
			test.fail("WeTalk module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}