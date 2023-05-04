//package com.weguard.utilities;
//
//import java.io.File;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.testng.ITestContext;
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.MediaEntityBuilder;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//import org.openqa.selenium.WebDriver;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//
//
//public class ExtentReport extends TestListenerAdapter {
//	public ExtentSparkReporter spark;
//	public ExtentReports extent;
//	public ExtentTest logger;
//	public WebDriver driver;
//
//	public void onstart(ITestContext testContext) throws IOException {
//		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date()); // time stamp
//		File file= new File("QA_Test_Report.html");
//		spark = new ExtentSparkReporter(file);
//		String reportName = "QA_Test_Report_" + timestamp + ".html";
//		spark = new ExtentSparkReporter(reportName);
//		extent = new ExtentReports();
//		extent.attachReporter(spark);
//		extent.setSystemInfo("Host Name", "QA");
//		extent.setSystemInfo("Users", "QA_Wenable");
//		spark.config().setDocumentTitle("WeGuard Test Automation Reort"); // Title of the Report																		
//		spark.config().setReportName("QA Test report"); // Name of the Report	
//		spark.config().setTheme(Theme.STANDARD);
//	}
//
//	public void onTestSuccess(ITestResult tr) {
//		logger = extent.createTest(tr.getName()); // create a new entry in the report
//		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed information
//	}
//
//	public void onTestFailure(ITestResult trw) {
//		logger = extent.createTest(trw.getName()); // create a new entry in the report
//		logger.log(Status.FAIL, MarkupHelper.createLabel(trw.getName(), ExtentColor.RED)); // Send the failed information 
//	    String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + trw.getName() + ".png";
//		File f = new File(screenshotPath);
//		if (f.exists()) {
//		  try {
//		   logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
//		} catch (Exception e) { 
//		// TODO Auto-generated catch block
//		       e.printStackTrace();
//				}
//			}
//	}
//
//	
//	public void onTestSkipped(ITestResult tr) {
//		logger = extent.createTest(tr.getName());
//		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
//	}
//	
//	
//	
//	public void onFinish(ITestContext testContext)
//	{
//		extent.flush();
//	}
//}
//
//
//
//


package com.weguard.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class ExtentReport extends TestListenerAdapter {
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest logger;
	public WebDriver driver;

	public void onstart(ITestContext testContext) throws IOException {
		String timestamp = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss").format(new Date()); // time stamp
		File file= new File("QA_Test_Report.html");
		spark = new ExtentSparkReporter(file);
		String reportName = "QA_Test_Report_" + timestamp + ".html";
		spark = new ExtentSparkReporter(reportName);
		extent = new ExtentReports();
		extent.attachReporter(spark);
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("URL", "https://qa-cloud.weguard.io/#/");
//		extent.setSystemInfo("Reporter", "Chakrapani Bandhu");
//		spark.config().setDocumentTitle("WeGuard Automation Test Report"); // Title of the Report																		
//		spark.config().setReportName("QA Test Report"); // Name of the Report	
//		spark.config().setTheme(Theme.STANDARD);

	}

	public void onTestSuccess(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create a new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // Send the passed information
	}

	public void onTestFailure(ITestResult trw) {
		logger = extent.createTest(trw.getName()); // create a new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(trw.getName(), ExtentColor.RED)); // Send the failed information 
	    String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + trw.getName() + ".png";
		File f = new File(screenshotPath);
		if (f.exists()) {
		  try {
		   logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		} catch (Exception e) { 
		       e.printStackTrace();
		  }
		} else {
		  try {
		      TakesScreenshot screenshot = (TakesScreenshot) driver;
		      File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		      FileUtils.copyFile(srcFile, new File(screenshotPath));
		      logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
		  } catch (Exception e) {
		      e.printStackTrace();
		  }
		}
	}	
	
	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
}

