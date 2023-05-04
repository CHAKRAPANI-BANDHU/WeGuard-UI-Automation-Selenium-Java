package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.AuditLogsPage;

import org.openqa.selenium.interactions.Actions;


public class AuditLogs extends Login {

	@Test(priority = 10)
	void auditlogs() throws InterruptedException, IOException
	{
		ExtentTest test = extent.createTest("Audit_Logs_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to the Audit Logs page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		AuditLogsPage au = new AuditLogsPage(driver, test, logger);
		
		// Audit Logs Page
		try {
			au.auditlogsURLClick();
			test.pass("Audit Logs module is displayed on the left navigation bar");
			Thread.sleep(5000);
			
			// Search field on top left
			if (driver.findElement(By.xpath("//*[@placeholder='Search']")).isDisplayed()) {
				test.info("Search field is Enabled under Audit Logs page");
			} else {
				System.out.println("Search field is not present under Audit Logs page.");
				test.fail("Search field is not present in Audit Logs page");
			}

			// Refresh button present or not
			if (driver.findElement(By.xpath("//*[text()='refresh']")).isDisplayed()) {
				test.info("Refresh button is present in audit Logs page");
			} else {
				test.fail("Refresh button is not displayed in Audit Logs page");
			}
			
			// Filter by Log level
			driver.findElement(By.xpath("//*[@aria-label='Filter by log level']")).click();
			Thread.sleep(2000);
			String[] expected_f1 = { "ALL", "Info", "Warn", "Debug", "Error" };
			List<WebElement> actual_f1 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));

			if (expected_f1.length != actual_f1.size()) {
				System.out.println("The no.of options count in the log level filter is not tallied.");
				test.fail("The no.of options in the log level filter is not tallied.");
			}
			for (int i = 0; i < expected_f1.length; i++) {
				String optionValue = actual_f1.get(i).getText();
				if (optionValue.equals(expected_f1[i])) {
					test.info("The log levele filter option is tallied with :" + optionValue);
				} else {
					test.fail("The log level filter option is not tallied with :" + optionValue);
				}
			}
			// Filter by events
			WebElement f2 = driver.findElement(By.xpath("//*[@aria-label='Filter by events']"));
			Actions act = new Actions(driver);
			act.moveToElement(f2).click().perform();
			f2.click();
			Thread.sleep(2000);
			String[] expected_f2 = { "All", "Login", "Logout", "Start Impersonate", "End Impersonate", "Policy", "Device",
					"Group", "Upload", "Roles and Permissions", "Command", "Broadcast", "WeTalk", "WeBox Passcode",
					"Kiosk Passcode" };
			List<WebElement> actual_f2 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
			if (expected_f2.length != actual_f2.size()) {
				test.fail("The options count in the events filter is not tallied.");
			}
			for (int i = 0; i < expected_f2.length; i++) {
				String optionValue = actual_f2.get(i).getText();
				if (optionValue.equals(expected_f2[i])) {
					test.info("The option in the events filter is tallied with : " + optionValue);
				} else {
					test.fail("The option in the events filter is not tallied with :" + optionValue);
				}
			}
			// Filter by date range
			WebElement f3 = driver.findElement(By.xpath("//*[@aria-label='Filter by date range']"));
			act.moveToElement(f3).click().perform();
			f3.click();
			Thread.sleep(2000);
			String[] expected_f3 = { "Today", "Yesterday", "Custom" };
			List<WebElement> actual_f3 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
			if (expected_f3.length != actual_f3.size()) {
				test.fail("The options count in the date range filter is not tallied.");
			}
			for (int i = 0; i < expected_f3.length; i++) {
				String optionValue = actual_f3.get(i).getText();
				if (optionValue.equals(expected_f3[i])) {
					test.info("The option in the date range filter is tallied with :" + optionValue);
				} else {
					test.fail("The option in the date range filter is not tallied with :" + optionValue);
				}
			}
			actual_f3.get(0).click();
			Thread.sleep(5000);
			// Tabular data is present or not
			String[] expected_CH = { "DATE", "SOURCE IP", "ACTOR", "LEVEL", "EVENT TYPE", "ACTIONS", "DETAILS" };
			List<WebElement> actual_CH = driver.findElements(By.xpath("//*[@role='columnheader']"));
			if (expected_CH.length != actual_CH.size()) {
				test.fail("The column header count is not tallied");
			}
			for (int i = 0; i < expected_CH.length; i++) {
				String optionValue = actual_CH.get(i).getText();
				if (optionValue.equals(expected_CH[i])) {
					test.info("The column header value is tallied with :" + optionValue);
				} else {
					test.fail("The column header value is not tallied with :" + optionValue);
				}
			}
			
			// Device logs
			driver.findElement(By.xpath("//*[text()='Device Logs']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[text()='Filter by Device OS']")).click();
			Thread.sleep(2000);
			String[] expected_options = { "Android", "iOS" };
			List<WebElement> actual_options = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
			if (expected_options.length != actual_options.size()) {
				test.fail("The options count in the device OS type filter is not tallied");
			}
			for (int i = 0; i < expected_options.length; i++) {
				String optionValue = actual_options.get(i).getText();
				if (optionValue.equals(expected_options[i])) {
					System.out.println("The option in the device OS type filter is tallied with :" + optionValue);
				} else {
					test.fail("The option in the devoice OS type filter is not tallied with :" + optionValue);
				}
			}
			actual_options.get(0).click();
			Thread.sleep(2000);
			if (driver.findElement(By.xpath("//*[@placeholder='Search with IMEI']")).isDisplayed()) {
				test.info("Search field is present in Device logs.");
			} else {
				test.fail("Search field is not displayed in device logs.");
			}	
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "auditLogsFailedScreenshot");
			test.fail("Audit Logs module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}