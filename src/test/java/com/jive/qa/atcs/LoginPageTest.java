package com.jive.qa.atcs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jive.qa.base.BaseClass;
import com.jive.qa.pages.HomePage;
import com.jive.qa.pages.LoginPage;
import com.jive.util.PropertyUtil;

/**
 * @author : Phani
 * File Created on :  Jan 11, 2018
 * File Name : LoginPageTest.java
 */

public class LoginPageTest extends BaseClass {
	
	private static final Logger logger = Logger.getLogger(LoginPageTest.class);
	
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() throws IOException {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		logger.info("===== SetUp method initialization is going on =====");
		initialization();
		extentReport();
		loginPage = new LoginPage();
	}
	
	@Test()
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Welcome | community & Aurea&Jive");
		logger.info("Assert passed...");
	}
	
	@Test()
	public void clickOnLogInLink() {
		logger.info("Click on Login link.");
	}
	
	@Test()
	public void loginTest() {
		logger.info("Entering UserName and Password.");
		loginPage.logIn(PropertyUtil.getProperty("UserName"), PropertyUtil.getProperty("Password"));
		clickOnLogOutBut();
	}
	
	@Test()
	public void clickOnLogOutBut() {
		logger.info("Click on logout button.");
		
	}
}