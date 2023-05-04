package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class WeShield extends Login {

	@Test(priority = 13)
	void weshield() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("WeShield_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the WeShield page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// WeShield Page
		try {
			driver.findElement(By.xpath("//*[@href=\"#/weshield\"]")).click();
             test.pass("WeShield module is displayed on the left navigation bar in this account");
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "weshieldFailedScreenshot");
			test.fail("WeShield module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}