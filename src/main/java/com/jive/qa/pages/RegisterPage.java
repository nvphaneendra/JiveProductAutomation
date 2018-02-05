package com.jive.qa.pages;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jive.qa.base.BaseClass;

/**
 * @author : Phani
 * File Created on :  Jan 21, 2018
 * File Name : RegisterPage.java 
 */
public class RegisterPage extends BaseClass {
	
	private static final Logger logger = Logger.getLogger(RegisterPage.class);
	
	@FindBy(className="register")
	WebElement register;
	
	@FindBy(id="emailAddress")
	WebElement regEmail;
	
	@FindBy(name="regsubmit")
	WebElement regButton;
	
	public RegisterPage() throws IOException {

		PageFactory.initElements(driver, this);
	}
	
	public void clickRegister() {
		register.click();
	}
	
	public void enterEmailAddress(final String emailID) {
		regEmail.sendKeys(emailID);
	}
	public void clickConformButton(){
		regButton.submit();
	}

}