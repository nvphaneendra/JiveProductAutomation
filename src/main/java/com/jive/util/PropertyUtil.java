package com.jive.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author : Phani
 * File Created on :  Jan 27, 2018
 * File Name : PropertyUtil.java 
 */
public class PropertyUtil {

	private static final Properties prop = new Properties();
	
	static {
		try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/jive/qa/config/jive.properties"))
		{
			prop.load(fis); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}

	public static void putProperty(String key, String value){
		prop.put(key, value);
	}
	
	public static boolean getBooleanProperty(String key){
		String value = prop.getProperty(key, "false");
		return Boolean.valueOf(value);
	}

}
