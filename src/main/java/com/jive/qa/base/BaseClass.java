package com.jive.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.jive.util.TestUtil;

public class BaseClass {
	
	private static final Logger logger = Logger.getLogger(BaseClass.class);
	
	public static WebDriver driver;
	public static Properties prop;
	
	public BaseClass() throws IOException {
		
		try 
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/jive/qa/config/jive.properties");
			prop.load(fis); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() throws InterruptedException {
		
		String osName = System.getProperty("os.name").toLowerCase();
		System.out.println("Current OS name ==>" +osName);
		String browserName = prop.getProperty("browser");
		
		logger.info("Current OS name ::: =====>" +osName);
		if(osName.startsWith("windows")) {
			
			if(prop.getProperty("browser").equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/win/chromedriver.exe");	
			driver = new ChromeDriver();
			
			} 
			else if(browserName.equals("firefox")){
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/main/resources/win/geckodriver.exe");	
			driver = new FirefoxDriver();
			
		    }
			else if(browserName.equals("IE")){
				
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/src/main/resources/win/IEDriverServer.exe");	
			driver = new InternetExplorerDriver();
		
	        } else {
	        	System.out.println("No proper browser driver was not found... Please add required driver properly.. !!!");
	        }						
		} else if (osName.startsWith("Linux")) {
			
			if(prop.getProperty("browser").equals("chrome")) {				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/linux/chromedriver");	
				driver = new ChromeDriver();			
			ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(System.getProperty("user.dir")+"/src/test/resources/linux/chromedriver")).usingAnyFreePort().build();
			try {
				service.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} else {
		System.out.println("Current OS requirement was not configured corretly... Please contact Admin or Automation Test Engineer to setup environment properly...");
	}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICT_WAIT, TimeUnit.SECONDS);
		
		logger.info("Launching the browser " +prop.getProperty("browser")+ " and enter required URL " +prop.getProperty("URL"));
		driver.get(prop.getProperty("URL"));
		Thread.sleep(5000);
	}
		
}