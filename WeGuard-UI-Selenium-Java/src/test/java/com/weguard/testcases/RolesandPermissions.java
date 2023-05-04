package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


public class RolesandPermissions extends Login {

	@Test(priority = 14)
	void rolesandpermissions() throws InterruptedException, IOException 
	{
		ExtentTest test = extent.createTest("Roles_and_Permissions_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Roles and Permissions page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Roles and Permissions Page
		try {
			driver.findElement(By.xpath("//*[@href=\"#/roles\"]")).click();
			test.pass("Roles and Permissions module is displayed on the left navigation bar in this account");
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "rolesandpermissionsFailedScreenshot");
			test.fail("Roles and Permissions module is not displayed on the left navigation bar in this account", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}