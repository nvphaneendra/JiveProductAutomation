package com.jive.qa.atcs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jive.qa.base.BaseClass;
import com.jive.qa.pages.LoginPage;
import com.jive.qa.pages.RegisterPage;
import com.jive.util.ReadData;
import com.jive.util.ReadExcel;

/**
 * @author : Phani
 * File Created on :  Jan 25, 2018
 * File Name : RegisterPageTest.java 
 */

public class RegisterPageTest extends BaseClass {
	
	private static String path = System.getProperty("user.dir")+"\\data\\JiveTestData.xlsx";
	private static String sheetName ="Register";
	private static int userNameCol = 0;
	private static final Logger logger = Logger.getLogger(RegisterPageTest.class);
	
	LoginPage loginPage;
	RegisterPage registerPage;
	
	public RegisterPageTest() throws IOException {
		super();
	}

	@BeforeClass
	public void setUp() throws IOException, InterruptedException {
		logger.info("===== SetUp method initialization is going on =====");
		initialization();
		extentReport();
	}
	
	@Test
	public void clickOnRegisterLink() {
		logger.info(" Click on Register Link on home page");
	}
	
	@Test
	public void enterEmailIdTest(String regName) throws Exception {
		logger.info("Get required username");
		int rowCount = ReadData.getRowCount(sheetName);
		
		for(int row =1; row <rowCount; row++){
			String username = ReadData.getCellData(row, userNameCol, sheetName);
			System.out.println("Enter Registered UserName" +username);
		}
	}
	
	@Test
	public void clickOnConformAddress() {
		logger.info("Click on Conform Address button.");
	}

}
