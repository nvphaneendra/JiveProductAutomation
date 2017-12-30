package com.jive.qa.pages;

import com.jive.qa.base.BaseClass;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BaseClass {

	@FindBy(id = "navLogin")
	WebElement logIn;

	@FindBy(name = "username")
	WebElement userName;

	@FindBy(name = "password")
	WebElement passWord;

	@FindBy(xpath = "//*[@value='Log in']")
	WebElement submit;

	public LoginPage() throws IOException {

		PageFactory.initElements(driver, this);
	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public HomePage clickOnLogIn() {
		logIn.click();
		return null;
	}
	
	public void clickOnSubmit()
	{
		if(driver.getCurrentUrl().toString().contains("login.jspa"))
		{
			boolean t = submit.isDisplayed();
			Assert.assertTrue(false);
			// logIn(String un, String pwd);
			System.out.println("if executed");
		}
		else
		{
			boolean t1 = submit.isDisplayed();
			Assert.assertTrue(true);
			System.out.println("else executed");
		}
	}

	public HomePage logIn(String un, String pwd) {
		clickOnLogIn();
		userName.sendKeys(un);
		passWord.sendKeys(pwd);
		submit.click();
		return new HomePage();
	}

}
