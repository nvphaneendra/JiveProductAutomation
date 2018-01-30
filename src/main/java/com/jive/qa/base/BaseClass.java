package com.jive.qa.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jive.util.Constants;
import com.jive.util.PropertyUtil;
import com.jive.util.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author : Phani
 * File Created on :  Jan 11, 2018
 * File Name : BaseClass.java
 */

public class BaseClass {
	
	private static final Logger logger = Logger.getLogger(BaseClass.class);
	
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String screen;
	
	public ITestResult result;	
		
	public static void initialization() {
		
		try {
		String osName = System.getProperty("os.name");
		logger.info("Current OS name ::: =====>" +osName);
		String browserName = PropertyUtil.getProperty("browser");
		
		if(osName.startsWith("Windows")) {
			
			if(PropertyUtil.getProperty("browser").equals("chrome")) {
			
			System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, System.getProperty("user.dir")+"/src/main/resources/win/chromedriver.exe");	
			driver = new ChromeDriver();
			
			} 
			else if(browserName.equals("firefox")){
			
			System.setProperty(Constants.WEBDRIVER_GECKO_DRIVER, System.getProperty("user.dir")+"/src/main/resources/win/geckodriver.exe");	
			driver = new FirefoxDriver();
			
		    }
			else if(browserName.equals("IE")){
				
			System.setProperty(Constants.WEBDRIVER_IE_DRIVER, System.getProperty("user.dir")+"/src/main/resources/win/IEDriverServer.exe");	
			driver = new InternetExplorerDriver();
		
	        } else {
	        	logger.info("No proper browser driver was not found... Please add required driver properly.. !!!");
	        }						
		} else if (osName.startsWith("Linux")) {
			
			if(PropertyUtil.getProperty("browser").equals("chrome")) {				
				System.setProperty(Constants.WEBDRIVER_CHROME_DRIVER, System.getProperty("user.dir")+"/src/main/resources/linux/chromedriver");	
				driver = new ChromeDriver();			
			
		}
	} else {
		logger.info("Current OS requirement was not configured corretly... Please contact Admin or Automation Test Engineer to setup environment properly...");
	}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(TestUtil.SCRIPT_TIMEOUT, TimeUnit.SECONDS);
		
		logger.info("Launching the browser " +PropertyUtil.getProperty("browser")+ " and enter required URL " +PropertyUtil.getProperty("URL"));
		driver.get(PropertyUtil.getProperty("URL"));
		Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void extentReport() {
		initialization();
		extent = new ExtentReports(System.getProperty("user.dir")+"/src/main/java/com/jive/qa/TestReport/JiveProduct.html", true);
		String log4jConfPath = "log4j.proprties";
		PropertyConfigurator.configure(log4jConfPath);
	}
	
	public void getResult(ITestResult result) {
	if(result.getStatus()==ITestResult.SUCCESS) {
		test.log(LogStatus.PASS,result.getName()+" test is pass");
		captureScreen("");
		test.log(LogStatus.PASS, test.addScreenCapture(captureScreen(screen)));
	}
	else if(result.getStatus()==ITestResult.SKIP) {
		test.log(LogStatus.SKIP,result.getName()+" test is skipped and because of" +result.getThrowable());
		captureScreen("");
		test.log(LogStatus.SKIP, test.addScreenCapture(captureScreen(screen)));
	}
	else if(result.getStatus()==ITestResult.FAILURE) {
		test.log(LogStatus.ERROR,result.getName()+" test is failed" +result.getThrowable());
		captureScreen("");
		test.log(LogStatus.FAIL, test.addScreenCapture(captureScreen(screen)));
	}
	else if(result.getStatus()==ITestResult.STARTED) {
		test.log(LogStatus.INFO, result.getName()+"test is stated");
	}
  }
	
	public String captureScreen(String fileName) {
		
		if(fileName=="") {
			fileName="blank";
		}
		
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			
				try {
					String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/com/jive/qa/TestReport/Screenshot/";
					destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calender.getTime()) + ".png");
					FileUtils.copyFile(scrFile, destFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Reporter.log("<a href='" +destFile.getAbsolutePath() +"'> <img src='" +destFile.getAbsolutePath() + "' height='100' width='100'/> <a/>");
				
			return destFile.toString();
	}
	
	public void closeBrowser() {
		driver.quit();
		logger.info("Close browser");
		extent.endTest(test);
		extent.flush();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		getResult(result);
	}
	
	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+" test started");
	}
	
	@AfterClass(alwaysRun=true) 
	public void endTest() {
		closeBrowser();
	}			
}