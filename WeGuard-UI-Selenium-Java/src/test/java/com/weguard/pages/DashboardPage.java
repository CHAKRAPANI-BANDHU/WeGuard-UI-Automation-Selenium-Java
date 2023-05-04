package com.weguard.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class DashboardPage {
	WebDriver dpdriver;
	ExtentTest test;
	Logger logger;
	

	public DashboardPage(WebDriver ddriver, ExtentTest test, Logger logger) {
		dpdriver = ddriver;
		this.test = test;
		this.logger = logger;
		PageFactory.initElements(ddriver, this);
}

	@FindBy(xpath = "//*[@href=\"#/dashboard\"]")
	@CacheLookup
	WebElement dbURL;

	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav/div/div/img")
	@CacheLookup
	WebElement dashboardLogo; 
	
	@FindBy(xpath = "//*[text()='menu']")
	@CacheLookup
	WebElement HamburgerIcon;
	
	@FindBy(xpath = "//div[text()[normalize-space()='Active Users']]")
	@CacheLookup
	WebElement ActiveUsers; 
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[1]/div[1]/div[1]")
	@CacheLookup
	WebElement ActiveUsersCount;
		
	@FindBy(xpath = "//div[normalize-space()='Active Devices']")
	@CacheLookup
	WebElement ActiveDevices;
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[1]/div[2]/div[1]")
	@CacheLookup
	WebElement ActiveDevicesCount;
	
	@FindBy(xpath = "//div[normalize-space()='Non-Compliant']")
	@CacheLookup
	WebElement NonCompliant;
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[1]/div[3]/div[1]")
	@CacheLookup
	WebElement NonCompliantCount;
	
	@FindBy(xpath = "//div[normalize-space()='Files']")
	@CacheLookup
	WebElement Files;
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[2]/div[1]/div[1]")
	@CacheLookup
	WebElement FilesCount;
	
	@FindBy(xpath = "//div[normalize-space()='Messages']")
	@CacheLookup
	WebElement Messages;
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[2]/div[2]/div[1]")
	@CacheLookup
	WebElement MessagesCount;
	
	@FindBy(xpath = "//div[normalize-space()='Calls']")
	@CacheLookup
	WebElement Calls;
	
	@FindBy(xpath = "//*[@id='drop-mini']/mat-sidenav-content/div[1]/ng-component/mat-card/div/div[1]/app-dashboard-header/div/div[2]/div[3]/div[1]")
	@CacheLookup
	WebElement CallsCount;
	
	@FindBy(xpath = "(//*[@class='chartjs-render-monitor'])[1]")
	@CacheLookup
	WebElement DevicesStatusPieChart;
	
	@FindBy(xpath = "(//*[@class='chartjs-render-monitor'])[2]")
	@CacheLookup
	WebElement DevicesCheckinBarGraph;
	
	@FindBy(css = "foreignobject")
	@CacheLookup
	WebElement ManufacturerPieChart;
	
	@FindBy(xpath = "//mat-row[@role='row']")
	@CacheLookup
	List<WebElement> RecentlyEnrolledDevicesList;
	
	@FindBy(xpath = "//*[@role='row']")
	@CacheLookup
	List<WebElement> HighDataConsumingDevicesList;
	
	@FindBy(xpath = "//app-activity-alert")
	@CacheLookup
	List<WebElement> RecentActivity;


	public void getdashboardURL() {
		dbURL.click();
	}

	public void getLogo() {
		dashboardLogo.isDisplayed();
	}
	
	public void hamburgerIcon() {
		HamburgerIcon.isDisplayed();
	}
	
	public void activeUsers() {
		ActiveUsers.isDisplayed();
	}
		
	public String activeUsersCount() {
		String value1 = ActiveUsersCount.getText();	
		return value1;
	}
		
	public void activeDevices() {
		ActiveDevices.isDisplayed();
	}
		
	public String activeDevicesCount() {
		String value2 = ActiveDevicesCount.getText();
		return value2;
	}
	
	public void nonCompliant() {
		NonCompliant.isDisplayed();
	}
		
	public String nonCompliantCount() {
		String value3 = NonCompliantCount.getText();
		return value3;
	}
	
	public void files() {
		Files.isDisplayed();
	}
		
	public String filesCount() {
		String value4 = FilesCount.getText();
		return value4;
	}
	
	public void messages() {
		Messages.isDisplayed();
	}
		
	public String messagesCount() {
		String value5 = MessagesCount.getText();
		return value5;
	}
	
	public void calls() {
		Calls.isDisplayed();
	}
		
	public String callsCount() {
		String value6 = CallsCount.getText();	
		return value6;
	}
	
	public void devicesStatusPieChart() {
		DevicesStatusPieChart.isDisplayed();
	}
		
	public void devicesCheckinBarGraph() {
		DevicesCheckinBarGraph.isDisplayed();		
	}
	
	public void manufacturerPieChart() {
		ManufacturerPieChart.isDisplayed();
	}
	
	public void recentlyEnrolledDevicesList() {
		int rows = RecentlyEnrolledDevicesList.size();
		for (int i = 1; i < rows; i++) {
		    RecentlyEnrolledDevicesList.get(i).isDisplayed();
		    test.info(RecentlyEnrolledDevicesList.get(i).getText());
		    logger.info("Recently enrolled devices list is displayed.");
		    if ((i + 1) % 6 == 0) {
		       break;
		    }
		    
		}
	}

	public void highDataConsumingDevicesList() {
		int rows2 = HighDataConsumingDevicesList.size();
	    for (int j=8; j < rows2; j++)
	     {
	    	HighDataConsumingDevicesList.get(j).isDisplayed();
	        test.info(HighDataConsumingDevicesList.get(j).getText());
	        logger.info("High Network Consuming Devices list is displayed.");
		    if ((j + 1) % 13 == 0) {
			       break;
			    }
	     }		
	}
	
	public int recentActivity() {
		int i = RecentActivity.size();
        return i;
	}

}
