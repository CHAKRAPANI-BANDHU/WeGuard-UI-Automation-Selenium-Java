package com.weguard.pages;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentTest;

public class ReportsPage {
	WebDriver rpdriver;
	ExtentTest test;
	Logger logger;	
	
	public ReportsPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		rpdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}
	
	// To get the reports URL
	@FindBy(xpath = "//a[@href='#/reports']")
	@CacheLookup
	WebElement ReportsURL;
	
	// To get the reports URL
	@FindBy(xpath = "//*[@class = 'reports-card-inner']")
	@CacheLookup
	List<WebElement> TotalReports;
	
	public WebElement getReportsURL() {
		ReportsURL.click();
		return ReportsURL;
	}
	
	public int getTotalReports() {
		int i = TotalReports.size();
		return i;
	}
	
}
