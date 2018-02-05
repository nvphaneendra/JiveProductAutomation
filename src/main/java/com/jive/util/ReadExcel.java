package com.jive.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author : Phani
 * File Created on :  Jan 30, 2018
 * File Name : ReadExcel.java 
 */
public class ReadExcel {
	
	public static void readExcel(String filePath, String fileName, String sheetName) throws IOException {
		
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook getWorkBook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		if(fileExtensionName.equals(".xlsx")) {
			getWorkBook = new XSSFWorkbook(inputStream);
		}
		
		else if(fileExtensionName.equals(".xls")) {
			getWorkBook = new XSSFWorkbook(inputStream);
		}
		
		Sheet sheet = getWorkBook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		for(int i=0; i<rowCount+1; i++) {
			Row row = sheet.getRow(i);
			for(int j=0; j<row.getLastCellNum(); j++) {
				System.out.println(row.getCell(j).getStringCellValue());
			}
			System.out.println();
		}
		
	}

		public static void main(String[] args) {
			
			ReadExcel objExcelRead = new ReadExcel();
			String filePath = System.getProperty("user.dir")+"\\data";
			try {
				ReadExcel.readExcel(filePath, "JiveTestData.xlsx", "Register");
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
}