package com.jive.qa.atcs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jive.qa.base.BaseClass;
import com.jive.qa.pages.HomePage;
import com.jive.qa.pages.LoginPage;

/**
 * 
 * @author : Phani
 * File Created on :  Jan 11, 2018
 * File Name : LoginPageTest.java
 */

public class LoginPageTest extends BaseClass {
	
	protected JavascriptExecutor js = (JavascriptExecutor) driver;
	private static final Logger logger = Logger.getLogger(LoginPageTest.class);
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeTest
	public void setUp() throws IOException, InterruptedException {
		logger.info("===== SetUp method initialization is going on =====");
		initialization();
		loginPage = new LoginPage();
	}
	
	@Test(priority=1)
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Welcome | community & Aurea&Jive");
		logger.info("Assert passed...");
	}
	
	@Test(priority=2)
	public void clickOnLogInLink() {		
	}
	
	@Test(priority=3)
	public void loginTest() {
		loginPage.logIn(prop.getProperty("UserName"), prop.getProperty("Password"));
	}
	
	@AfterTest
	public void tearDown() {		
		driver.quit();		
	}

}
