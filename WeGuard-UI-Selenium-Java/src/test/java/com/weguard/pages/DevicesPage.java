package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class DevicesPage {
	WebDriver devpdriver;
	ExtentTest test;
	Logger logger;

	public DevicesPage(WebDriver rdriver, ExtentTest test, Logger logger) {
		devpdriver = rdriver;
		this.test = test;
		this.logger = logger; 
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//*[@href=\"#/devices\"]")
	@CacheLookup
	WebElement DevicesURL;
	
	@FindBy(xpath = "//mat-header-row[@role='row']")
	@CacheLookup
	WebElement ColumnsOfDevicesPage;
	
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> DevicesList;
	
	
	public void devicesURLClick() {
		DevicesURL.click();
	}
	
	
	public String columnsOfDevicesInfo() {
		String columnText = ColumnsOfDevicesPage.getText();
		return columnText;
	}
	
	public void devicesList() {
    // Find all the mat-row elements inside the element for devices
		int rows1 = DevicesList.size();
	    for (int i=0; i < rows1; i++)
	     {
	    	DevicesList.get(i).isDisplayed();
	        test.info(DevicesList.get(i).getText());
	        logger.info("Devices List is displayed.");
	     }		
	}
	
}



