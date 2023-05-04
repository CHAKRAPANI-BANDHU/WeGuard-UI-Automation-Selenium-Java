package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.*;

public class DataUsage extends Login {

	@Test(priority = 6)
	void datausage() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("Data_Usage_Testcases").assignAuthor("QA_Wenable");
		logger.info("Navigating to Data Usage page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		DataUsagePage du = new DataUsagePage(driver, test, logger);

		// Data Usage Page
		try {
			du.duClick();
			test.pass("Data Usage module is displayed on the left navigation bar");

			// Click on the Network tab
			try {
				du.duNetworkClick();
				test.pass("Data Usage devices list on Network page are: ");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesNetworkClickFailedScreenshot");
				test.fail("Devices that are consumed on Network are not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Data Usage Devices List on Network
			try {
				du.duDevicesonNetwork();
				test.pass("Devices that are consumed on Network is displayed");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesOnNetworkFailedScreenshot");
				test.fail("Devices that are consumed on Network is not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Download CSV of Network connected devices
			try {
				du.duDownloadCSVofNetwork();
				test.info("Downloaded CSV for data usage devices on network");
				logger.info("Downloaded CSV for data usage devices on network");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesOnNetworkFailedScreenshot");
				test.fail("Unable to download CSV for data usage devices on network",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			Thread.sleep(3000);

			// Click on the WiFi tab
			try {
				du.duWiFiClick();
				test.pass("Data Usage devices list on WiFi page are: ");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesNetworkClickFailedScreenshot");
				test.fail("Devices that are consumed on WiFi are not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Data Usage Devices List on WiFi
			try {
				du.duDevicesonWiFi();
				test.pass("Devices that are consumed on WiFi are displayed");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesOnNetworkFailedScreenshot");
				test.fail("Devices that are consumed on WiFi are not displayed",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Download CSV of WiFi connected devices
			try {
				du.duDownloadCSVofWiFi();
				test.info("Downloaded CSV for data usage devices on WiFi");
				logger.info("Downloaded CSV for data usage devices on WiFi");
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "devicesOnNetworkFailedScreenshot");
				test.fail("Unable to download CSV for data usage devices on network",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "datausageFailedScreenshot");
			test.fail("Data Usage module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
		Thread.sleep(5000);
	}
}