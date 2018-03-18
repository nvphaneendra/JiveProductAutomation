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
	WebElement registerLink;
	
	@FindBy(id="emailAddress")
	WebElement regEmail;
	
	@FindBy(name="regsubmit")
	WebElement regButton;
	
	public RegisterPage() throws IOException {

		PageFactory.initElements(driver, this);
	}
	
	public void clickRegister() {
		registerLink.click();
	}
	
	public void enterEmailAddress(String emailID) {
		clickRegister();
		regEmail.sendKeys(emailID);
	}
	public void clickConformButton(){
		regButton.submit();
	}

}