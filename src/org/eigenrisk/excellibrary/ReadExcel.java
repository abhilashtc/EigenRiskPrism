package org.eigenrisk.excellibrary;

import java.io.FileInputStream;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel{
	
	public static void getData() throws IOException, InvalidFormatException{
		FileInputStream fis = new FileInputStream(".//Automation.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Automation");
		
		Row row1 = sh.getRow(0);
		for(int i=0;i<=sh.getLastRowNum();i++){
			Cell cell1 = row1.getCell(3);
			if(cell1.getStringCellValue().equalsIgnoreCase("Asset Name")){
				for(int k =0;k<=sh.getLastRowNum();k++){
					Row r = sh.getRow(k);
					for(int j=0;j<r.getLastCellNum();j++){
						Cell cell = r.getCell(j);
						String str = cell.getStringCellValue();
						System.out.println(str);
					}
				}
				
				
			}
			
			
		}
			
		
	}
		
		
		
		
		
		
	public static void main(String[] args) throws InvalidFormatException, IOException {
		ReadExcel.getData();
		
		
	}
		
		
		
		
		
		
		
		
	}
	
	
	
	


