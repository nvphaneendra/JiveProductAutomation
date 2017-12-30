package com.jive.qa.atcs;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jive.qa.base.BaseClass;
import com.jive.qa.pages.HomePage;
import com.jive.qa.pages.LoginPage;

public class LoginPageTest extends BaseClass {
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeMethod
	public void setUp() throws IOException, InterruptedException {
		initialization();
		loginPage = new LoginPage();
	}
	
	//@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Welcome | community & Aurea&Jive");
	}
	
	//@Test(priority=2)
	public void clickOnLogInLink() {
		
	}
	
	@Test(priority=3)
	public void loginTest() {
		loginPage.logIn(prop.getProperty("UserName"), prop.getProperty("Password"));
		homePage = loginPage.clickOnLogIn();
	}
	
	@AfterMethod
	public void tearDown() {		
		driver.quit();		
	}

}
