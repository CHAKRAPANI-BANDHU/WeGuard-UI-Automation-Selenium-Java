package com.weguard.testcases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.weguard.pages.ReportsPage;
import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;

public class Reports extends Login {

	@Test(priority = 4)
	void reports() throws InterruptedException, IOException {
		ExtentTest test = extent.createTest("Reports_Testcases").assignAuthor("QA_Wenable").assignDevice("Chrome");
		logger.info("Navigating to the Reports page");
		ReportsPage rp = new ReportsPage(driver, test, logger);
		try {
			WebElement reports = rp.getReportsURL();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(reports));
			// reports.click();
			test.info("Reports page is launched");
			Thread.sleep(10000);
			test.info("Executing Reports Test cases");
			logger.info("Executing Reports Test cases");
			test.info(("The no. of report types present in this account are: ") + rp.getTotalReports());

// 1. WeGuard Version Distribution Reports

			try {
				logger.info("Executing WeGuard Version Distribution Reports Test cases");
				test.info("WeGuard Version Distribution Reports is displayed in this account.");
				WebElement reports0 = driver
						.findElement(By.xpath("//p[text() ='WeGuard Version Distribution Reports']"));
				String title = reports0.getText();
				test.info(title);
				test.pass("Launching the WeGuard Version Distribution Reports");
				WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(40));
				wait1.until(ExpectedConditions.elementToBeClickable(reports0));
				reports0.click();

				// Cache the Report Information
				test.info("Please wait while we cache the devices");
				Thread.sleep(8000);
				WebElement text = driver.findElement(
						By.xpath("(//p[@class='categ-heading'])[1]"));
				String WVDR = text.getText();
				test.info(WVDR);
				test.info("Devices are cached, you can try reports now");
				Thread.sleep(5000);
				WebElement element = driver
						.findElement(By.xpath("(//p[@class='categ-heading'])[1]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Thread.sleep(8000);

				// Display of pie-chart
				WebElement piechartElement = driver.findElement(By.xpath("//div[@class='pie-chart']"));
				String piechartData = piechartElement.getText();
				test.info(piechartData);

				test.info(" Pie-chart of WeGuard Version Distribution Reports is displayed");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

				// Print all the Devices with WeGuard App Versions
				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with WeGuard App Versions are displayed");
				} else {
					test.fail("Devices List with WeGuard App Versions are not displayed");
				}

				// Verifying column headers in tabular data
				String[] expectedColheader = { "DEVICE ID", "VERSION" };
				List<WebElement> actualColheader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expectedColheader.length != actualColheader.size()) {
					test.info("Column header count is not matched with the expected.");
				}
				if (actualColheader.size() != expectedColheader.length) {
					test.fail("Actual column header count does not match expected count");
				} else {
					for (int i = 0; i < expectedColheader.length; i++) {
						String optionValue_CH = actualColheader.get(i).getText();
						if (optionValue_CH.equals(expectedColheader[i])) {
							test.info("Passed on: " + optionValue_CH);
						} else {
							test.fail("Failed on: " + optionValue_CH);
						}
					}
				}

				Thread.sleep(2000);

				// Columns/Label/Headers Names
				WebElement columns = driver.findElement(By.xpath("//mat-header-row[@role='row']"));
				String WVDRHeaders = columns.getText();
				test.info(WVDRHeaders);

				// Rows Information
				List<WebElement> rows = driver.findElements(By.xpath("//mat-row[@role='row']"));
				for (int i = 1; i < rows.size(); i++) {
					test.info(rows.get(i).getText());
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

				// Download button
				WebElement downloadbutton1 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in WeGuard Version Distribution Reports is displayed");
				downloadbutton1.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Include devices by state drop-down list options
				driver.findElement(By.xpath("(//span[text()='Include devices by state'])[1]")).click();

				// Find the drop-down element
				WebElement dropdown = driver.findElement(By.xpath("(//span[text()='Include devices by state'])[1]"));

				// Find all the options in the drop-down
				List<WebElement> options = dropdown.findElements(By.tagName("a"));

				// Retrieve the text of each option and print it
				for (WebElement option : options) {
					test.info(option.getText());
				}
				String[] expectedOptions = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions.length != actualOptions.size()) {
					test.fail("The count of options in the drop-down list is not matched.");
				}
				for (int j = 0; j < expectedOptions.length; j++) {
					String optionValue = actualOptions.get(j).getText();
					if (optionValue.equals(expectedOptions[j])) {
						test.info("The drop-down option value is matched with: " + optionValue);
					} else {
						test.fail("The drop-down option value is not matched with " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsWVDRFailedScreenshot");
				test.fail("WeGuard Version Distribution Reports is not displayed in this account",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Navigating to Reports page form WeGuard distribution page
			Actions act = new Actions(driver);
			WebElement back_btn1 = driver.findElement(By.xpath("//*[@mat-tooltip= 'Back']"));
			act.moveToElement(back_btn1).click().perform();
			Thread.sleep(2000);
			back_btn1.click();
			Thread.sleep(2000);

// 2. Device Enrollments Reports

			try {
				logger.info("Executing Devices Enrollment Reports Test cases");
				driver.findElement(By.xpath("//*[text()='Device Enrollment Reports']")).click();
				test.info("Device Enrollment Reports is displayed in this account.");
				test.info("Device Enrollment Reports");
				test.pass("Launching the Device Enrollment Reports");
				Thread.sleep(5000);

				// Display of pie chart
				WebElement piechart2 = driver.findElement(By.xpath("//div[@class='pie-chart']//h3[1]"));
				String chart2 = piechart2.getText();
				test.info(chart2);
				test.info("The pie-chart of Device Enrollment Reports is displayed");

				// Verifying column headers in tabular data
				String[] expectedColheader2 = { "DEVICE ID", "ACTIVATION TIME", "GROUP NAME" };
				List<WebElement> actualColheader2 = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expectedColheader2.length != actualColheader2.size()) {
					test.fail("Column header count is not matched with the expected.");
				}
				for (int k = 0; k < expectedColheader2.length; k++) {
					String optionValue_CH2 = actualColheader2.get(k).getText();
					if (optionValue_CH2.equals(expectedColheader2[k])) {
						test.info("Passed on : " + optionValue_CH2);
					} else {
						test.fail("Failed on : " + optionValue_CH2);
					}
				}

				// Columns/Label/Headers Names
				WebElement columns2 = driver.findElement(By.xpath("//mat-header-row[@role='row']"));
				String DERHeaders = columns2.getText();
				test.info(DERHeaders);

				// Find all the mat-row elements inside the element
				List<WebElement> rows = driver.findElements(By.xpath("//mat-row[@role='row']"));
				for (int l = 1; l < rows.size(); l++) {
					test.info(rows.get(l).getText());
				}

				// Download button is present or not in Device enrollment reports
				WebElement downloadbutton2 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Device Enrollments Reports is displayed");
				downloadbutton2.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Filter by group names field
				try {
					// locate the dropdown/multi-select element
					WebElement dropdown = driver.findElement(By.xpath("//div[text()='Filter by group names']"));

					// click the dropdown/multi-select element to open the options
					dropdown.click();

					// locate the options container element
					WebElement optionsContainer1 = driver
							.findElement(By.xpath("//ul[@role='listbox']//p-multiselectitem"));

					// create a list of option elements in the container
					List<WebElement> options = optionsContainer1
							.findElements(By.xpath("//ul[@role='listbox']//p-multiselectitem"));

					// create an empty list to hold the option values
					List<String> optionValues = new ArrayList<String>();

					// iterate over the option elements and get their text
					for (WebElement option : options) {
						String text = option.getText();
						optionValues.add(text);
						// You can also print the option value to the console or a log file
						test.info(text);
					}
					test.info("Filter by group names is present in Device Enrollment Reports");

				} catch (NoSuchElementException e) {
					test.fail("Filter by group names is not present in Device Enrollment Reports.");
				}

				// Include devices by state drop-down list options
				driver.findElement(By.xpath("(//span[text()='Include devices by state'])[1]")).click();
				String[] expectedOptions1 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions1 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions1.length != actualOptions1.size()) {
					test.fail("The no.of options count in the drop-down list are not matched");
				}
				for (int m = 0; m < expectedOptions1.length; m++) {
					String optionValue = actualOptions1.get(m).getText();
					if (optionValue.equals(expectedOptions1[m])) {
						test.info("The drop-down option value is matched with: " + optionValue + "\n");
					} else {
						test.fail("The drop-down option value is not matched with: " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsDERFailedScreenshot");
				test.fail("Device Enrollment Reports is not displayed in this account",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			
			// Navigating to Reports page form WeGuard Version Distribution Reports page
			Actions act2 = new Actions(driver);
			WebElement back_btn2 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			// Navigating to Reports page form Device enrollment reports page
			act2.moveToElement(back_btn2).click().perform();
			Thread.sleep(2000);
			back_btn2.click();
			Thread.sleep(2000);

// 3. Custom Application Distribution Reports

			try {
				logger.info("Executing Custom Application Distribution Reports Test cases");
				driver.findElement(By.xpath("//*[text()='Custom Application Distribution Reports']")).click();
				test.info("Custom Application Distribution Reports is displayed in this account.");
				test.info("Custom Application Distribution Reports");
				test.pass("Launching the Custom Application Distribution Reports");

				// Locate and click the custom app list drop down element
				WebElement customappDropdown = driver.findElement(By.xpath("(//*[@role='combobox'])[2]"));
				customappDropdown.click();

				List<WebElement> options1 = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
				
				for (int n = 1; n < options1.size(); n++) {

					WebElement customappOption = driver
							.findElement(By.xpath("(//span[@class='mat-option-text'])[" + n + "]"));
					customappOption.click();
					customappOption.getText();

					// get the rows of the table
					List<WebElement> rows = driver.findElements(By.xpath("//mat-row[@role='row']"));

					// iterate over the rows and print the data
					for (WebElement row : rows) {
						List<WebElement> cells = row.findElements(By.xpath("//mat-row[@role='row']"));
						for (WebElement cell : cells) {
							test.info(cell.getText());
						}
					}

					// Display of pie chart
					WebElement piechart3 = driver.findElement(By.xpath("//div[@class='pie-chart']//h3[1]"));
					String chart3 = piechart3.getText();
					test.info(chart3);
					test.info("The Pie-chart of Custom Application Distribution Reports is displayed");

					// locate the download reports button element
					WebElement downloadButton = driver
							.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
					test.info("The Download Reports button in Custom Application Distribution Reports is displayed");

					// click on the download reports button
					downloadButton.click();
					Thread.sleep(1000);

					// Click on the Download button
					WebElement downloadButton3 = driver.findElement(By.xpath("//span[text()='DOWNLOAD']"));
					downloadButton3.click();
					test.info("The Download button in Custom Application Distribution Reports is displayed");
					Thread.sleep(2000); // wait for download to complete
					customappDropdown.click();
				}

				// Search field
				test.info("The search is present in Custom Application Distribution Reports."
						+ driver.findElement(By.xpath("//input[@data-placeholder='Search']")).isDisplayed());
				Thread.sleep(5000);

				// Devices by state drop down
				driver.findElement(By.xpath("//span[contains(@class, 'ng-tns-c175-284')]")).click();
				Thread.sleep(3000);
				String[] expectedOptions3 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions3 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions3.length != actualOptions3.size()) {
					test.fail("The options count in the Custom Application Distribution Reports");
				}
				for (int p = 1; p < expectedOptions3.length; p++) {
					String optionValue3 = actualOptions3.get(p).getText();
					if (optionValue3.equals(expectedOptions3[p])) {
						test.info("The drop down value matched with: " + optionValue3);
					} else {
						test.fail("The DDB option not matched with: " + optionValue3);
					}
				}

			}

			catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsCADRFailedScreenshot");
				test.fail("Custom Application Distribution Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			Thread.sleep(5000);
			// Navigating back to Reports page
			Actions act3 = new Actions(driver);
			WebElement back_btn3 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act3.moveToElement(back_btn3).click().perform();

			// Re-locate the back button WebElement
			back_btn3 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));

			// Click the back button
			back_btn3.click();
			Thread.sleep(3000);

// 4. Store Application Distribution Reports
			
			try {
				logger.info("Executing Store Application Distribution Reports Test cases");
				driver.findElement(By.xpath("//p[text() ='Store Application Distribution Reports']")).click();
				test.info("Store Application Distribution Reports is displayed in this account");
				test.info("Store Application Distribution Reports");
				test.pass("Launching the Store Application Distribution Reports");

				// Locate and click the Store App list drop-down
				//WebElement storeappDropdown = driver.findElement(By.xpath("(//*[@role='combobox'])[2]")); 
				WebElement storeappDropdown = driver.findElement(By.xpath("//span[@class='mat-select-placeholder mat-select-min-line ng-tns-c175-51 ng-star-inserted']"));
				Thread.sleep(2000);
				storeappDropdown.click();

				List<WebElement> options1 = driver.findElements(By.xpath("//span[@class='mat-option-text']"));

				for (int q = 1; q < options1.size(); q++) {

					WebElement storeappOption = driver
							.findElement(By.xpath("(//span[@class='mat-option-text'])[" + q + "]"));
					storeappOption.click();
					storeappOption.getText();

					// get the rows of the table
					List<WebElement> rows5 = driver.findElements(By.xpath("//mat-row[@role='row']"));

					// iterate over the rows and print the data
					for (WebElement row5 : rows5) {
						List<WebElement> cells = row5.findElements(By.xpath("//mat-row[@role='row']"));
						for (WebElement cell : cells) {
							test.info(cell.getText());
						}
					}

					// Display of pie chart
					WebElement piechart4 = driver.findElement(By.xpath("//div[@class='pie-chart']//h3[1]"));
					String chart4 = piechart4.getText();
					test.info(chart4);
					test.info("The Pie-chart of Store Application Distribution Reports is displayed");

					// locate the download reports button element
					WebElement downloadButton4 = driver
							.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
					test.info("The Download Reports button in Store Application Distribution Reports is displayed");

					// Click on the download reports button
					downloadButton4.click();
					Thread.sleep(1000);

					// Click on the Download button
					WebElement downloadButton5 = driver.findElement(By.xpath("//span[text()='DOWNLOAD']"));
					downloadButton5.click();
					test.info("The Download button in Store Application Distribution Reports is displayed");
					Thread.sleep(2000); // wait for download to complete
					storeappDropdown.click();
				}

				// Search field
				test.info("The search is present in Store application distribution reports :"
						+ driver.findElement(By.xpath("//input[@data-placeholder='Search']")).isDisplayed());
				Thread.sleep(3000);

				// Devices by state drop down options
				driver.findElement(By.xpath("//span[contains(@class, 'ng-tns-c175-284')]")).click();
				Thread.sleep(3000);
				String[] expectedOptions4 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions4 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions4.length != actualOptions4.size()) {
					test.fail("The options in the drop-down list are not matched.");
				}
				for (int r = 0; r < expectedOptions4.length; r++) {
					String optionValue4 = actualOptions4.get(r).getText();
					if (optionValue4.equals(expectedOptions4[r])) {
						test.info("The drop down value is matched with: " + optionValue4);
					} else {
						test.fail("The drop down value is not matched with: " + optionValue4);
					}
				}
			}

			catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsSADRFailedScreenshot");
				test.fail("Store Application Distribution Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			Thread.sleep(5000);

			// Navigating back to Reports page
			Actions act4 = new Actions(driver);
			WebElement back_btn4 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act4.moveToElement(back_btn4).click().perform();
			back_btn4.click();
			Thread.sleep(2000);

// 5. Devices OS Reports

			try {
				logger.info("Executing Devices OS Reports Test cases");
				driver.findElement(By.xpath("//*[text()='Devices OS Reports']")).click();
				Thread.sleep(2000);
				test.info("Devices OS Reports is displayed in this account.");
				test.info("Devices OS Reports");
				test.pass("Launching the Devices OS Reports");

				// Print all the Devices Type Versions
				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with OS versions are displayed");
				} else {
					test.fail("Devices List with OS versions are not displayed");
				}

				// Download button is present or not
				WebElement downloadbutton5 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Devices OS Reports is displayed");
				downloadbutton5.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart
				test.info("The pie-chart is displayed under Device OS reports"
						+ driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed());

				// Tabular data default column header

				String[] expected_Colheader = { "DEVICE ID", "OS" };
				List<WebElement> actual_Colheader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_Colheader.length != actual_Colheader.size()) {
					test.fail("the column header count is not matched.");
				}
				for (int s = 0; s < expected_Colheader.length; s++) {
					String optionValue_CH = actual_Colheader.get(s).getText();
					if (optionValue_CH.equals(expected_Colheader[s])) {
						test.info("The column header value is matched with: " + optionValue_CH);
					} else {
						test.fail("The column header value is not matched with: " + optionValue_CH);
					}
				}

				// Include devices type drop-down list options
				driver.findElement(By.xpath("(//span[text()='Include devices by state'])[1]")).click();
				Thread.sleep(3000);
				String[] exp_options = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actual_options = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (exp_options.length != actual_options.size()) {
					test.fail("The option count in the drop down are not matched.");
				}
				for (int t = 0; t < exp_options.length; t++) {
					String optionValue = actual_options.get(t).getText();
					if (optionValue.equals(exp_options[t])) {
						test.info("The drop down value is matched with: " + optionValue);
					} else {
						test.fail("The drop down value is not matched with: " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsDORFailedScreenshot");
				test.fail("Devices OS Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Navigating back to Reports page
			Actions act5 = new Actions(driver);
			WebElement back_btn5 = driver.findElement(By.xpath("//*[@mat-tooltip= 'Back']"));
			act5.moveToElement(back_btn5).click().perform();
			back_btn5.click();
			Thread.sleep(2000);

// 6. Devices Type Reports

			try {
				logger.info("Executing Devices Type Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Devices Type Reports']")).click();
				test.info("Devices Type Reports is displayed in this account");
				test.info("Devices Type Reports");
				test.pass("Launching the Devices Type Reports");

				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with type are displayed");
				} else {
					test.fail("Devices List with type are not displayed");
				}

				// Download button
				WebElement downloadbutton6 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Device Type Reports is displayed");
				downloadbutton6.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart display
				test.info(("DTR--The pie-chart is displayed in this account is displayed")
						+ (driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed()) + "\n");
				// Column header in tabular data
				String[] exp_ColHeader = { "DEVICE ID", "TYPE" };
				List<WebElement> act_ColHeader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (exp_ColHeader.length != act_ColHeader.size()) {
					test.fail("The count of column header is not matching.");
				}
				for (int u = 0; u < exp_ColHeader.length; u++) {
					String optionValue_CH = act_ColHeader.get(u).getText();
					if (optionValue_CH.equals(exp_ColHeader[u])) {
						test.info("The column header value is matched with: " + optionValue_CH);
					} else {
						test.fail("The column header value is not matched with: " + optionValue_CH);
					}
				}

				// Include devices by state drop down options
				driver.findElement(By.xpath("//span[contains(@class, 'ng-tns-c175-284')]")).click();
				Thread.sleep(5000);
				String[] expectedOptions5 = { "Unprovisioned", "Lost", "Stolen", "Replaced", "Deleted" };
				List<WebElement> actualOptions5 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expectedOptions5.length != actualOptions5.size()) {
					test.fail("The count of options in the drop-down list is not matched.");
				}
				for (int v = 0; v < expectedOptions5.length; v++) {
					String optionValue = actualOptions5.get(v).getText();
					if (optionValue.equals(expectedOptions5[v])) {
						test.info("The DDB option is matched with: " + optionValue);
					} else {
						test.fail("The DDB value is not matched with: " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsDTRFailedScreenshot");
				test.fail("Devices Type Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Navigating back to Reports page
			Actions act6 = new Actions(driver);
			WebElement Back_btn = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act6.moveToElement(Back_btn).click().perform();
			Back_btn.click();
			Thread.sleep(2000);

// 7. Devices Status Reports

			try {
				logger.info("Executing Devices Status Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Devices Status Reports']")).click();
				test.info("Devices Status Reports is displayed in this account");
				test.info("Devices Status Reports");
				test.pass("Launching the Devices Status Reports");

				if (driver.findElement(By.xpath("//mat-row[@role='row']")).isDisplayed()) {
					test.info("Devices List with status are displayed");
				} else {
					test.fail("Devices List with status are not displayed");
				}

				// Download button
				WebElement downloadbutton7 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Devices Status Reports is displayed");
				downloadbutton7.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Pie-chart
				test.info(("The pie-chart is displayed in devices status reports page")
						+ (driver.findElement(By.xpath("//*[@class='pie-chart']")).isDisplayed()) + "\n");
				
				// Column headers in tabular data
				String[] expected_ColHeader = { "DEVICE ID", "LAST REPORT TIME", "STATUS" };
				List<WebElement> actual_ColHeader = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_ColHeader.length != actual_ColHeader.size()) {
					test.fail("Column header count in the tabular data is not matched.");
				}
				for (int u = 0; u < expected_ColHeader.length; u++) {
					String optionValue = actual_ColHeader.get(u).getText();
					if (optionValue.equals(expected_ColHeader[u])) {
						test.info("The column header value is matched with: " + optionValue);
					} else {
						test.fail("the column Header value is not matched with: " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsDSRFailedScreenshot");
				test.fail("Devices Status Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());

			}

			// Navigating back to REPORTS page
			Actions act7 = new Actions(driver);
			WebElement Back_btn7 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act7.moveToElement(Back_btn7).click().perform();
			Back_btn.click();
			Thread.sleep(2000);

// 8. Geofence Reports
			
			try {
				logger.info("Executing Geofence Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Geofence Reports']")).click();
				test.info("Geofence Reports is displayed in this account");
				test.info("Geofence Reports");
				test.pass("Launching the Geofence Reports");
				Thread.sleep(2000);

				// Download button
				WebElement downloadbutton8 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Geofence Reports is displayed");
				downloadbutton8.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Select Group filter
				test.info("Select group filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Groups']")).isDisplayed());
				// Select Devices filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Devices']")).isDisplayed());
				// Choose Geofence filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Choose Geofence']")).isDisplayed());
				// Submit button
				test.info("Submit button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' SUBMIT ']")).isDisplayed());

				// Clear button
				test.info("Clear button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' CLEAR ']")).isDisplayed());
				
				// Filter by date range options
				driver.findElement(By.xpath("//*[@aria-label='Filter by date range']")).click();
				Thread.sleep(2000);
				String[] expected_filters = { "Today", "Yesterday", "Custom" };
				List<WebElement> actual_filters = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expected_filters.length != actual_filters.size()) {
					test.fail("The options count in the filter by date range is not matched.");
				}
				for (int w = 0; w < expected_filters.length; w++) {
					String optionValue = actual_filters.get(w).getText();
					if (optionValue.equals(expected_filters[w])) {
						test.info("The filter by date range option is matched with: " + optionValue);
					} else {
						test.fail("The filter by date range option is not matched with: " + optionValue);
					}
				}
			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsGRFailedScreenshot");
				test.fail("Geofence Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}

			// Navigating back to Reports page
			Actions act8 = new Actions(driver);
			WebElement Back_btn8 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act8.moveToElement(Back_btn8).click().perform();
			Back_btn8.click();
			Thread.sleep(2000);

// 9. Screen Time Reports

			try {
				logger.info("Executing Screen Time Reports Test cases");
				driver.findElement(By.xpath("//p[text()='Screen Time Reports']")).click();
				Thread.sleep(2000);
				test.info("Screen Time Reports is displayed in this account");
				test.info("Screen Time Reports");
				test.pass("Launching the Screen Time Reports");

				// Download button
				WebElement downloadbutton9 = driver
						.findElement(By.xpath("//span[text()[normalize-space()='DOWNLOAD REPORT']]"));
				test.info("The Download button in Screen Time Reports is displayed");
				downloadbutton9.click();
				driver.findElement(By.xpath("//span[text()='DOWNLOAD']")).click();

				// Select Group filter
				test.info("Select groups filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Groups']")).isDisplayed());
				// Select Devices filter
				test.info("Select Devices filter is displayed    : "
						+ driver.findElement(By.xpath("//*[text()='Select Devices']")).isDisplayed());
				// Submit button
				test.info("Submit button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' SUBMIT ']")).isDisplayed());

				// Clear button
				test.info("Clear button displayed:"
						+ driver.findElement(By.xpath("//*[text()=' CLEAR ']")).isDisplayed());
				// Column headers in tabular data
				String[] expected_ColHeader2 = { "DEVICE ID", "SCREEN TIME", "UP TIME" };
				List<WebElement> actual_ColHeader2 = driver.findElements(By.xpath("//*[@role='columnheader']"));
				if (expected_ColHeader2.length != actual_ColHeader2.size()) {
					test.fail("Column header count in the tabular data is not matched.");
				}
				for (int x = 0; x < expected_ColHeader2.length; x++) {
					String optionValue = actual_ColHeader2.get(x).getText();
					if (optionValue.equals(expected_ColHeader2[x])) {
						test.info("The column header value is matched with: " + optionValue);
					} else {
						test.fail("the column Header value is not matched with: " + optionValue);
					}
				}

				// Filter by date range options
				driver.findElement(By.xpath("//*[@aria-label='Filter by date range']")).click();
				Thread.sleep(2000);
				String[] expected_filters2 = { "Today", "Yesterday", "Custom" };
				List<WebElement> actual_filters2 = driver.findElements(By.xpath("//*[@class='mat-option-text']"));
				if (expected_filters2.length != actual_filters2.size()) {
					test.fail("The options count in the filter by date range is not matched.");
				}
				for (int y = 0; y < expected_filters2.length; y++) {
					String optionValue = actual_filters2.get(y).getText();
					if (optionValue.equals(expected_filters2[y])) {
						test.info("The filter by date range option is matched with: " + optionValue);
					} else {
						test.fail("The filter by date range option is not matched with: " + optionValue);
					}
				}

			} catch (NoSuchElementException e) {
				test.info(e);
				String screenshotPath = getScreenshot(driver, "reportsSTRFailedScreenshot");
				test.fail("Screen Time Reports is not displayed in this account.",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
			// Navigating back to Reports page
			Actions act9 = new Actions(driver);
			WebElement Back_btn9 = driver.findElement(By.xpath("//*[@mat-tooltip='Back']"));
			act9.moveToElement(Back_btn9).click().perform();
			Back_btn9.click();
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {
			test.info(e);
			String screenshotPath = getScreenshot(driver, "reportsFailedScreenshot");
			test.fail("Reports module is not displayed on the left navigation bar in this account",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}
	}
}
