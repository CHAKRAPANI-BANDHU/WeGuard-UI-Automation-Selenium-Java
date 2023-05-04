package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class PolicyGroupsPage {
	WebDriver pgdriver;
	ExtentTest test;
	Logger logger;

	public PolicyGroupsPage(WebDriver rdriver, ExtentTest test, Logger logger) {
		pgdriver = rdriver;
		this.test = test;
		this.logger = logger; 
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//*[@href=\"#/groups\"]")
	@CacheLookup
	WebElement PolicyGroupsURL;
	
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> PoliciesList;
	
	
	public void policygroupsURLClick() {
		PolicyGroupsURL.click();
	}
	
	public void policiesList() {
    // Find all the mat-row elements inside the element for devices
		int rows1 = PoliciesList.size();
	    for (int i=0; i < rows1; i++)
	     {
	    	PoliciesList.get(i).isDisplayed();
	        test.info(PoliciesList.get(i).getText());
	        logger.info("Policies List is displayed.");
	     }		
	}
}




