package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.PolicyGroupsPage;


public class PolicyGroups extends Login {

	@Test(priority = 4)
	void policygroups() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("Policy_Groups_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Policy Groups page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		PolicyGroupsPage policies = new PolicyGroupsPage(driver, test, logger);
		
		// Policy Groups 
		try {
			test.pass("Policy Groups module is displayed on the left navigation bar");
			policies.policygroupsURLClick();
			
			// Policy Groups List
			try {
				policies.policiesList();
				test.pass("Policies list is displayed.");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "policiesListFailedScreenshot");
				test.fail("Policies list is not displayed.", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "policygroupsFailedScreenshot");
			test.fail("PolicyGroups module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}