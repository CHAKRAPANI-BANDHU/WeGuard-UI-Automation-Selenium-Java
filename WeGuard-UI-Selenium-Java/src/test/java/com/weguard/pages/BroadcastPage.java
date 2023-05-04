package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class BroadcastPage {
	WebDriver bpdriver;
	ExtentTest test;
	Logger logger;	
	
	public BroadcastPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		bpdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}
	
	// To get all the alerts on Alerts page
	@FindBy(xpath = "//*[@href=\"#/broadcast\"]")
	@CacheLookup
	WebElement BroadcastURL;

	// To get all the alerts on Alerts page
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> BroadcastMessages;
	
	
	public void getBroadcastURL() {
		BroadcastURL.click();
	}
	
	
	public void displayAllBroadcastMessages() {
		int rows = BroadcastMessages.size();
		for (int i = 1; i < rows; i++) {
			BroadcastMessages.get(i).isDisplayed();
		    test.info(BroadcastMessages.get(i).getText());	    
		}
	}
}
