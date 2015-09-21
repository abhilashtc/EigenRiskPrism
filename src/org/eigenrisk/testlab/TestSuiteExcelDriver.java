package org.eigenrisk.testlab;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;																																				
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.google.common.collect.Table.Cell;

public class TestSuiteExcelDriver {
	static String sArray[][]=new String[3][2];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String inputFile = "TestSuiteDriver.xlsx";
		String sArray1[][] = ReadExcel(inputFile);
		PrintArray(sArray1);

	}
	
	public static void PrintArray(String sArrays[][])
	{
		int len = sArrays.length;
		System.out.println("Printing Details...");
		
		for(int x=0;x<len;x++)
		{
			if (sArrays[x][0] != null && (sArrays[x][0].equals("Abhi")))
			{
				int colCnt = sArrays[x].length;
				System.out.println(colCnt);
				for(int y=0;y<colCnt;y++)
					System.out.println(sArrays[x][y]);
			}
		}
	}
	


	public static String[][] ReadExcel(String inputFile) throws Exception
	{	
		System.out.println("Inside readExcel");
		File f = new File(inputFile);
		System.out.println(f.isFile());
		
		if (f.isFile() && f.canRead()) 
		{
			FileInputStream fis = new FileInputStream(inputFile);
			Workbook wb = WorkbookFactory.create(fis);
			
			Sheet s = wb.getSheet("Address Book");
			int rCount, cellCount;
			rCount = s.getLastRowNum();	
			
			System.out.println("RowCount is " + rCount);
	
			
			String sArray1[][]=new String[rCount][2];
			
			
			for(int rNum =1; rNum<=rCount;rNum++)
			{
				Row r = s.getRow(rNum);
				int c = r.getLastCellNum();
				
				for(int cNum=0;cNum<c;cNum++)
				{
					Cell cobj1 = (Cell) r.getCell(cNum);
					sArray[rNum-1][cNum] = cobj1.toString();
					sArray1[rNum-1][cNum] = cobj1.toString();
				}
			}
			
			System.out.println(sArray1.length);
			return(sArray1);
		}
		else
			System.out.println("File dont exist..");
		return sArray;
		
		
	}
}

