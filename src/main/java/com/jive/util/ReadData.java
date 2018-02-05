package com.jive.util;

import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/*
 * XSSF is used for .xlsx extensions
 * HSSF is used for .xls extensions
 */
/**
 * @author : Phani
 * File Created on :  Jan 30, 2018
 * File Name : ReadData.java 
 */

public class ReadData {
    	private static XSSFSheet ExcelWSheet;
    	private static XSSFWorkbook ExcelWBook;
    	private static org.apache.poi.ss.usermodel.Cell Cell;
                
    public static void setExcelFile(String Path) throws Exception {
    	try {
    		FileInputStream ExcelFile = new FileInputStream(Path);
    		ExcelWBook = new XSSFWorkbook(ExcelFile);
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
            
    public static String getCellData(int RowNum, int ColNum, String sheetName) throws Exception{
    	String CellData = null;
    	try{
    		ExcelWSheet = ExcelWBook.getSheet(sheetName);
    		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
    		CellData = Cell.getStringCellValue();
    		return CellData;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
		return CellData;
    }
            
        	
    public static int getRowCount(String sheetName){
    	int iNumber=0;
    	try {
    		ExcelWSheet = ExcelWBook.getSheet(sheetName);
    		iNumber=ExcelWSheet.getLastRowNum()+1;
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    	return iNumber;
    }
 }