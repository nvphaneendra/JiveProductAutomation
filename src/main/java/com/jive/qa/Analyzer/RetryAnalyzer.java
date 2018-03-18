package com.jive.qa.Analyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author : Phani
 * File Created on :  Jan 25, 2018
 * File Name : RetryAnalyzer.java 
 */

public class RetryAnalyzer implements IRetryAnalyzer {
	
	int counter = 0;
	int retryLimit = 1;
	
	public boolean retry(ITestResult result) {
		
		if(counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}

}
