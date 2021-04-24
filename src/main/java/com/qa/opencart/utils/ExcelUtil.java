package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static String filePath = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getExcelData(String sheetName){
		
		FileInputStream ip;
		try {
			ip = new FileInputStream(filePath);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int rowLength = sheet.getLastRowNum();
		int colLength = sheet.getRow(0).getLastCellNum();
		Object data[][] = new Object[rowLength][colLength];
		for(int i=0; i<rowLength;i++) {
			for(int j=0;j<colLength;j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
		
	}

}
