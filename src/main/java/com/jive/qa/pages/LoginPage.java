package com.jive.qa.pages;

import com.jive.qa.base.BaseClass;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {
	
	@FindBy(xpath="//span[text()='Log in']")
	WebElement logIn;
	
	@FindBy(xpath="//input[contains(@id,'username')]")
	WebElement userName;
	
	@FindBy(xpath="//input[contains(@id,'password')]")
	WebElement passWord;
	
	@FindBy(name="submit")
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
	
	public HomePage logIn(String un, String pwd) {
		userName.sendKeys(un);
		passWord.sendKeys(pwd);
		submit.click();
		return new HomePage();
	}


}
