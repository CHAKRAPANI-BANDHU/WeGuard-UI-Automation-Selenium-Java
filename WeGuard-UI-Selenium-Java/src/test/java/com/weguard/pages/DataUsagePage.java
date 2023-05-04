package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class DataUsagePage {
	WebDriver dudriver;
	ExtentTest test;
	Logger logger;

	public DataUsagePage(WebDriver rdriver, ExtentTest test, Logger logger) {
		dudriver = rdriver;
		this.test = test;
		this.logger = logger; 
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//*[@href=\"#/devicedatausage\"]")
	@CacheLookup
	WebElement duURL;
	
	@FindBy(xpath = "//span[text()='NETWORK']")
	@CacheLookup
	WebElement duNetwork;
	
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> DataUsageDevicesonNetwork;

	@FindBy(xpath = "//span[text()='WIFI']")
	@CacheLookup
	WebElement duWiFi;
	
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> DataUsageDevicesonWiFi;
	
	@FindBy(xpath = "//mat-icon[text()='cloud_download']")
	@CacheLookup
	WebElement DownloadCSVofNetwork;
	
	@FindBy(xpath = "//mat-icon[text()='cloud_download']")
	@CacheLookup
	WebElement DownloadCSVofWiFi; 
	
	@FindBy(xpath = "//input[@type='search']")
	@CacheLookup
	WebElement Search;
	
	public void duClick() {
		duURL.click();
	}
	
	public void duNetworkClick() {
		duNetwork.click();
	}
	
	public void duWiFiClick() {
		duWiFi.click();
	}
	
	
	public void duDevicesonNetwork() {
    // Find all the mat-row elements inside the element for Network
		int rows1 = DataUsageDevicesonNetwork.size();
	    for (int i=0; i < rows1; i++)
	     {
	    	DataUsageDevicesonNetwork.get(i).isDisplayed();
	        test.info(DataUsageDevicesonNetwork.get(i).getText());
	        logger.info("Data Usage devices on Network is displayed.");
	     }		
	}
	
	public void duDevicesonWiFi() {
	    // Find all the mat-row elements inside the element for WiFi
		int rows2 = DataUsageDevicesonNetwork.size();
		for (int j=0; j < rows2; j++) {
		    DataUsageDevicesonWiFi.get(j).isDisplayed();
		        test.info(DataUsageDevicesonWiFi.get(j).getText());
		        logger.info("Data Usage devices on WiFi is displayed.");
		    }
		}

	
	public void duDownloadCSVofNetwork() {
		DownloadCSVofNetwork.click();
		}
	
	public void duDownloadCSVofWiFi() {
		DownloadCSVofWiFi.click();
		}
}


