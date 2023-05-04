package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.WeBoxPage;

public class WeBox extends Login {

	@Test(priority = 8)
	void webox() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("WeBox_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the WeBox page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WeBoxPage weboxpage = new WeBoxPage(driver, test, logger);

		// WeBox Page
		try {
			weboxpage.weboxURLClick();
			test.pass("WeBox module is displayed on the left navigation bar in this account");

			// Policies List in WeBox
			try {
				weboxpage.policiesListInWeBox();
				test.pass("Policies list in WeBox is displayed.");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "rolesandpermissionsFailedScreenshot");
				test.fail("Policies list in WeBox is not displayed.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "weboxFailedScreenshot");
			test.fail("WeBox module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}