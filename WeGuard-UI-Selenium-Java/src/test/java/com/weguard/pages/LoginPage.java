package com.weguard.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//input[@id='mat-input-0']")
	@CacheLookup
	WebElement txtUserId;

	@FindBy(xpath = "//input[@id='mat-input-1']")
	@CacheLookup
	WebElement txtPassword;
	
    @FindBy(xpath = "//mat-slide-toggle[@id='mat-slide-toggle-1']/label/div")
	@CacheLookup
    WebElement RememberMe;

	@FindBy(xpath = "//*[@id=\"mat-tab-content-0-0\"]/div/div/div/form/button")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath = "(//ul[@class='footerUl']//span)[2]")
	@CacheLookup
	WebElement PortalVersion;

	public void setUserId(String uid) {
		txtUserId.sendKeys(uid);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void rememberMe(String rememberme) {
		RememberMe.click();
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public String portalVersion() {
		String value = PortalVersion.getText();
		return value;
	}
}
