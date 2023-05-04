package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class AlertsPage {
	WebDriver apdriver;
	ExtentTest test;
	Logger logger;	
	
	public AlertsPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		apdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}
	
	// To get all the alerts on Alerts page
	@FindBy(xpath = "//*[@href=\"#/alerts\"]")
	@CacheLookup
	WebElement AlertsURL;

	// To get all the alerts on Alerts page
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> ViewAlerts;
	
	// To click the filter by alert type drop-down
	@FindBy(xpath = "//*[@id=\"mat-select-value-5\"]")
	@CacheLookup
	WebElement ClickFATDropdownOption; 
	
	
	// To click the filter by alert type drop-down
	@FindBy(xpath = "//span[@class='mat-option-text']")
	@CacheLookup
	List<WebElement> FATDropdownOptions;
	
	
	public void getAlertsURL() {
		AlertsURL.click();
	}
	
	
	public void displayAllAlerts() {
		int rows = ViewAlerts.size();
		for (int i = 1; i < rows; i++) {
		       ViewAlerts.get(i).isDisplayed();
		    test.info(ViewAlerts.get(i).getText());	    
		}
	}
	
	
	public void clickFilterByAlertType() {
		ClickFATDropdownOption.click();
	}
	
	public void getAllDropdownOptions() {
		int rows = FATDropdownOptions.size();
		for (int i = 1; i < rows; i++) {
			FATDropdownOptions.get(i).isDisplayed();
		    test.info(FATDropdownOptions.get(i).getText());
		    
		}
	}
}
