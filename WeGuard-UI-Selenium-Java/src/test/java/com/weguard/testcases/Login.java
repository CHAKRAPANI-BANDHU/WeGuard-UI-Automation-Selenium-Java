package com.weguard.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.LoginPage;

public class Login extends BaseClass {

	@Test(priority = 1)
	public void login() throws InterruptedException, IOException {

		ExtentTest test = extent.createTest("Login_Testcases").assignAuthor("QA_Wenable");

		// Accessing the WeGuard URL
		driver.get("https://qa-cloud.weguard.io/#/login");
//		driver.get(System.getenv("WEGUARD_URL"));
		
		logger.info("Navigating to WeGuard Login page");
		logger.info("WeGuard URL is launched");
		test.info("Executing Login Testcase");
		test.info("Title of the page: " + driver.getTitle());
		LoginPage lp = new LoginPage(driver);
		
		// portal version
		String pv = lp.portalVersion();
		test.info(pv);
		
		lp.setUserId("chakrapani.bandhu@weguard.com");
//		lp.setUserId(System.getenv("WEGUARD_USERNAME"));
		test.info("Entered User ID");
		lp.setPassword("Wenable@473");
//		lp.setPassword(System.getenv("WEGUARD_PASSWORD"));
		test.info("Entered Password");
		lp.rememberMe(("RememberMe"));
		lp.clickLogin();
		Thread.sleep(8000);
		if (driver.getCurrentUrl().equals("https://qa-cloud.weguard.io/#/dashboard"))
		{
			test.pass("Successfully Logged In.");
		}
		else if (driver.findElement(By.xpath("//*[@href=\"#/dashboard\"]")).isDisplayed()) {
			test.pass("Dashboard page is launched");
			Thread.sleep(3000);
			logger.info("Dashboard page is launched");
		} else {
			test.info("Page is loading");
			String screenshotPath = getScreenshot(driver, "dashboardisnotnavigatedFailedScreenshot");
			test.fail("Failed to Login", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			driver.quit();
		}
	}
}