package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class BulkActions extends Login {

	@Test(priority = 12)
	void bulkactions() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("Bulk_Actions_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Bulk Actions page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Devices
		try {
			driver.findElement(By.xpath("//*[@href=\"#/bulkactions\"]")).click();
			test.pass("Bulk Actions module is displayed on the left navigation bar");
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "bulkactionsFailedScreenshot");
			test.fail("Bulk Actions module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}