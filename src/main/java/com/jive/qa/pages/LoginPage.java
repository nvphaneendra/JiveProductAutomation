package com.jive.qa.pages;

import com.jive.qa.base.BaseClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author : Phani
 * File Created on :  Jan 11, 2018
 * File Name : LoginPage.java
 */

public class LoginPage extends BaseClass {
	
	private static final Logger logger = Logger.getLogger(LoginPage.class);

	@FindBy(className="login")
	WebElement logIn;

	@FindBy(name="username")
	WebElement userName;

	@FindBy(name="password")
	WebElement passWord;

	@FindBy(xpath="//input[@name='login']")
	WebElement submit;
	
	@FindBy(xpath="//input[@name='submit']")
	WebElement loginSubmit;
	
	@FindBy(xpath="//*[contains(@id, 'logout')]")
	WebElement logOut;
	
	public LoginPage() throws IOException {
		
//What is Page Factory?
//Page Factory is an inbuilt Page Object Model concept for Selenium WebDriver but it is very optimized.
//Here as well, we follow the concept of separation of Page Object Repository and Test Methods. 
//Additionally, with the help of PageFactory class, we use annotations @FindBy to find WebElement. 
//We use initElements method to initialize web elements

		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void clickOnLogIn() {
		logIn.click();
		
	}

	public HomePage logIn(final String un, final String pwd) {
		clickOnLogIn();
		userName.sendKeys(un);
		passWord.sendKeys(pwd);
		if(loginSubmit.isEnabled()) {
			logger.info("It's calling the URL = 'https://caro-20171-reg3-release-east.jivelandia.com/'");
			loginSubmit.submit();		
		}
		else {
			logger.info("It's calling the URL = 'https://caro-20171-reg3-release-east.jivelandia.com/login.jspa'");
			submit.submit();
		}
		return new HomePage();		
	}
	
	public void clickOnLogOut() {
		logOut.click();
	}
}