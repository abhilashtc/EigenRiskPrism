package com.eigenRisk.functionalities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcelToString_WriteToExcel {
	static String sArray[][]=new String[10][102];
	static String outputFile, current;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//String inputFile = "SampleData.xlsx";
		
		current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+ current);
		
		String inputFile = "C:\\Abhilash\\Directory.xlsx";
		
		String sArray1[][] = ReadExcel(inputFile);
		
		System.out.println("Printing array...");
//		PrintArray(sArray1);
		
		WriteToExcel(sArray1);
		
		//PrintArray(sArray1);
		
		System.out.println("Done......");
		/*
		System.out.println("===========================================================================");
		System.out.println(sArray1[0][0]);
		System.out.println(sArray1[1][0]);
		System.out.println(sArray1[2][0]);
		System.out.println(sArray1[3][0]);
		System.out.println(sArray1[4][0]);
		System.out.println(sArray1[5][0]);
		System.out.println(sArray1[6][0]);
		System.out.println(sArray1[7][0]);
		System.out.println("===========================================================================");
		*/
	}
	
	
	public static void WriteToExcel(String sArrays[][]) throws Exception
	{
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		//PrintArray(sArrays);
		
		System.out.println("Write to Excel...");
		outputFile = current + "\\Temp\\Test\\SampleData_2.xlsx";
		int fileFlag = CheckWorkBookExist(outputFile);
		
		fileFlag = 0;
		
		if (fileFlag == 0)
		{
			XSSFWorkbook workbook = new XSSFWorkbook();
	    	XSSFSheet sheet = workbook.createSheet("Assets");
	    	int flag = CheckWorkBookExist(outputFile);
	    	
	    	int len = sArrays.length;
			System.out.println("Inside IF...");
			
			Row row;
	    	Cell cell;
	    	System.out.println("********************************************************************");
	    	
			for(int x=0;x<len;x++)
			{
				
				//if (sArrays[x][0] != null)
				//{
					int colCnt = sArrays[x].length;
					//System.out.println(colCnt);
					row = sheet.createRow(x);
					//System.out.println();
					for(int y=0;y<colCnt;y++)
					{
						//System.out.println(sArrays[x][y]);
			    		cell = row.createCell(y);
			    		cell.setCellValue(sArrays[x][y]);
			    		
			    		CreationHelper creationHelper = workbook.getCreationHelper();
		    			CellStyle style = workbook.createCellStyle();
		    			if((x > 3) && 
			    				((y == 4) || (y == 5) || (y == 6) || 
			    						(y == 21) || y == 24 || y == 29 ||
			    						(y == 32) || (y == 33) || (y == 34) || (y == 35) || (y == 37) || 
			    						(y == 40) || (y == 42) || (y == 43) || (y == 44) || (y == 45) || (y == 46)  || (y == 47) || (y == 48) ||
			    						(y == 50) || (y == 52) || (y == 54) || (y == 55) || (y == 56) || (y == 57)  || (y == 58) ||
			    						(y == 63) || (y == 66) || (y == 68) || (y == 69) ||
			    						(y == 71) || (y == 74) || (y == 75) || (y == 76) || (y == 77) || (y == 78))) {	
			    			//Integer Format
//			    			System.out.println(" $$$$$$$ [" + x + "] [" + y + "]");
//			    			System.out.println(sArrays[x][y]);
//			    			System.out.println(" $$$$$$$ ");
			    			
			    			String s1 = sArrays[x][y];
//			    			System.out.println("S1 -> " + s1.length());
			    			//int intVal = Integer.parseInt(s1);
			    			if(s1.length() > 0) {
				    			float flVal = Float.parseFloat(s1);
				    			int intVal = (int) flVal;
//				    			System.out.println("intVal -> " + intVal);
				    			if (y == 24 || y == 29) {
				    				DecimalFormat df = new DecimalFormat();
				    				df.setMaximumFractionDigits(3);
//				    				System.out.println(" ^^^ " + intVal);
				    				df.format(intVal);
//				    				System.out.println(" ^^^ " + intVal);
				    			}
				    			cell.setCellValue(intVal);
			    			}
			    		}
			    		else if((x > 3) && (y == 18)) {
			    			//Date Format
			    	        style.setDataFormat(creationHelper.createDataFormat().getFormat("mm/dd/yyyy"));
			    	        cell.setCellStyle(style);
			    	        //System.out.println("~~~~~~~~~ " + sArrays[x][y]);
			    		}
					}
				//}
			}
			
			
			
			FileOutputStream out = new FileOutputStream(new File(outputFile));
	    	workbook.write(out);
	    	out.close();
			System.out.println("Data written successfully on to Excel Sheet " + outputFile);
			
			/*
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(sArrays[0][0]);
			System.out.println();
			System.out.println(sArrays[1][17]);
			System.out.println(sArrays[1][20]);
			System.out.println(sArrays[1][28]);
			System.out.println(sArrays[1][31]);
			System.out.println();
			System.out.println(sArrays[2][21]);
			System.out.println(sArrays[2][24]);
			System.out.println(sArrays[2][28]);
			System.out.println(sArrays[2][31]);
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			PrintArray(sArrays);
			
			*/
			
		}
	}
	
	public static int CheckWorkBookExist(String outputFile) throws Exception
	{
		File f = new File(outputFile);
		if (f.isFile() && f.canRead()) 
		{
			System.out.println("File " + outputFile + " Exists, hence Exiting the program...");
			return 1;
		}
		else
		{
			System.out.println("File dont exist");
			return 0;
		}
	}
	
	
	
	public static void PrintArray(String sArrays[][])
	{
		int len = sArrays.length;
		System.out.println("Printing Array..." + sArrays.length);
		
		for(int x=0;x<len;x++)
		{
			//if (sArrays[x][0] != null && (sArrays[x][0].equals("Abhi")))
			//{
				int colCnt = sArrays[x].length;
				//System.out.println("--> " + colCnt);
				System.out.println();
				for(int y=0;y<colCnt;y++)
					if(sArrays[x][y] != null)
						System.out.print(sArrays[x][y] + " -- ");
					else
						System.out.print(" -- ");
			//}
		}
		
		
	}
	


	public static String[][] ReadExcel(String templateFile) throws Exception
	{	
		String inputFile = current + "\\Temp\\Test\\EventsToCopy.xlsx";
		templateFile = current + "\\Temp\\Test\\Import_ExcelTemplate_1435662416834.xlsm";
		
		System.out.println("Inside readExcel ->" + inputFile);
		File f = new File(inputFile);
		File f1 = new File(templateFile);
		
		if (f.isFile() && f.canRead() && f1.isFile() && f1.canRead()) 
		{
			FileInputStream fis = new FileInputStream(inputFile);
			Workbook wb = WorkbookFactory.create(fis);
			
			Sheet s = wb.getSheet("Assets");
			int rCount, cellCount;
			rCount = s.getLastRowNum();
	
			System.out.println("Row Count is -> " + rCount);
			String sArray1[][]=new String[rCount+1][102];
			String events[][]=new String[rCount+1][102];
			
			int arrayCtr = 0;
			int row=0, col=0;
			int row1=0, col1=0;
			for(int rNum =3; rNum<=rCount;rNum++)
			{
				if (rNum == 3) {
					System.out.println("Header is from the downloaded Template");
					FileInputStream fis1 = new FileInputStream(templateFile);
					Workbook wb1 = WorkbookFactory.create(fis1);
					
					Sheet s1 = wb1.getSheet("Assets");
					int rCount1, cellCount1;
					rCount1 = s1.getLastRowNum();
					
					Row r1 = s1.getRow(rNum);
					int c1 = r1.getLastCellNum();
					
					System.out.println(c1);
					
					events[row1][0] = "General";
					events[row1][8] = "Geography";
					events[row1][17] = "Value by Coverage Type";
					events[row1][32] = "Characteristics";
					events[row1][63] = "GeoSpatial";
					row1++;
					events[row1][17] = "General Valuation";
					events[row1][20] = "Property Damage";
					events[row1][28] = "Time Element";
					events[row1][31] = "Life";
					row1++;
					events[row1][21] = "Building";
					events[row1][24] = "Content";
					events[row1][28] = "Time Element";
					events[row1][31] = "Life";
					row1++;
					

					System.out.println("*******************************************************************");
					System.out.println(events.length);
					for(int k=0;k < (events.length); k++){
						for(int cNum=0;cNum<100;cNum++) {
							if(events[k][cNum] != null)
								System.out.print(events[k][cNum]);
							else
								System.out.print(" ## ");
						}
						System.out.println("---");
					}
					
					
					System.out.println("*******************************************************************");
					
					for(int cNum=0;cNum<c1;cNum++)
					{
						try {
							Cell cobj = r1.getCell(cNum);
							String cellValue1 = cobj.toString();
							events[row1][col1++] = cellValue1; 
						}
						catch (NullPointerException NP) {
							System.out.print(" -- ");
							events[row1][col1++] = "";
						}
					}
					
					
					for(int cNum=0;cNum<c1;cNum++)
						System.out.print(events[0][cNum]);
					
					row1++;
				}
				else {
					Row r = s.getRow(rNum);
					int c = r.getLastCellNum();
					
					
					col1 = 0;
					for(int cNum=0;cNum<c;cNum++)
					{
						try {
							Cell cobj1 = r.getCell(cNum);
							String cellValue = cobj1.toString();
							events[row1][col1++] = cellValue;
						}
						catch (NullPointerException NP) {
							System.out.print(" -- ");
							events[row1][col1++] = "";
						}
						
						/*System.out.print(sArray1[0][arrayCtr-1] + " -- ");
						arrayCtr = 0;
						for(int ctr=0; ctr < 102; ctr++) {
							sArray1[0][ctr] = "";
							sArray1[1][ctr] = "";
						}*/
						
					}
					//row++;
					row1++;
				}
				System.out.println();
			}
			
			//
			
			System.out.println("Event Length is -> " + events.length);
			
			for(int k=0;k < (events.length); k++){
				for(int cNum=0;cNum<100;cNum++)
					System.out.print(events[k][cNum]);
				System.out.println("---");
			}
			
			return(events);
		}
		else
			System.out.println("File dont exist..");
		return sArray;
		
		
	}
	
	
	public static void testExcelWrite() {
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		    FileInputStream inp = new FileInputStream(current + "\\Temp\\Test\\EventsToCopy.xlsx");  


		            Workbook wb = null;
		            try {
		                wb = WorkbookFactory.create(inp);
		            } catch (InvalidFormatException e) {
		                e.printStackTrace();
		            }  
		            Sheet sheet = wb.getSheetAt(0);  
		            Row row = sheet.getRow(3);  
		            Cell cell = row.getCell(4);  
		            if (cell == null)  
		                cell = row.createCell(4);  
		            cell.setCellType(Cell.CELL_TYPE_STRING);  
		            cell.setCellValue("a test");  

		            // Write the output to a file  
		            FileOutputStream fileOut = new FileOutputStream(current + "\\Temp\\Test\\ExposureTemplate.xlsm");  
		            wb.write(fileOut);  
		            fileOut.close();  
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }		
	}
	
	
	public static void InsertRowsToExcel(String sArrays[][], String outputFile) throws Exception
	{
		outputFile = current + "\\Temp\\Import_ExcelTemplate_1.xlsm";
		try {
		    FileInputStream inp = new FileInputStream(outputFile);  

		    Workbook wb = null;
		    try {
		    	wb = WorkbookFactory.create(inp);
		    } catch (InvalidFormatException e) {
		        e.printStackTrace();
		    }  
		    Sheet sheet = wb.getSheetAt(0);  
		    Row row = sheet.getRow(3);  
		    Cell cell = row.getCell(4);  
		    if (cell == null)  
		    	cell = row.createCell(4);  
		    cell.setCellType(Cell.CELL_TYPE_STRING);  
		    cell.setCellValue("a test");  

		    // Write the output to a file  
		    FileOutputStream fileOut = new FileOutputStream(outputFile);  
		    wb.write(fileOut);  
		    fileOut.close();  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/*
		outputFile = current + "\\Temp\\Import_ExcelTemplate_1.xlsm";
		int fileFlag = CheckWorkBookExist(outputFile);
		
		if (fileFlag == 0)
		{
			XSSFWorkbook workbook = new XSSFWorkbook();
	    	XSSFSheet sheet = workbook.createSheet("New Address Book");
	    	int flag = CheckWorkBookExist(outputFile);
	    	
	    	int len = sArrays.length;
			System.out.println("Printing Details...");
			
			Row row;
	    	Cell cell;
	    	
			for(int x=0;x<len;x++)
			{
				if (sArrays[x][0] != null && (sArrays[x][0].equals("Abhi")))
				{
					int colCnt = sArrays[x].length;
					System.out.println(colCnt);
					row = sheet.createRow(x);
					
					for(int y=0;y<colCnt;y++)
					{
						System.out.println(sArrays[x][y]);
			    		cell = row.createCell(y);
			    		cell.setCellValue(sArrays[x][y]);
					}
				}
			}
			FileOutputStream out = new FileOutputStream(new File(outputFile));
	    	workbook.write(out);
	    	out.close();
			System.out.println("Data written successfully on to Excel Sheet " + outputFile);
			
		}
	*/
	}
}

