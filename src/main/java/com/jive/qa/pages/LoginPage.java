package com.jive.qa.pages;

import com.jive.qa.base.BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author : Phani
 * File Created on :  Jan 11, 2018
 * File Name : LoginPage.java
 */

public class LoginPage extends BaseClass {

	@FindBy(className="login")
	WebElement logIn;

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement passWord;

	@FindBy(id="login-submit")
	WebElement submit;

	public LoginPage() throws IOException {

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
		if(submit.isEnabled()) {
		submit.submit();		
		}
		else {
			System.out.println("Button is not enabled...");
		}
		return new HomePage();
		
	}

}
