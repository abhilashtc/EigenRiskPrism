package org.eigenrisk.businesslogic;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromExcel_To_Array_Print {
	static String sArray[][]=new String[3][5];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String inputFile = "report.xlsx";
		String sArray1[][] = ReadExcel(inputFile);
		PrintArray(sArray1);

	}
	
	public static void PrintArray(String sArrays[][])
	{
		int len = sArrays.length;
		System.out.println("\nPrinting Details>>>>>>>");
		
		for(int x=0;x<len;x++)
		{
			//if (sArrays[x][0] != null && (sArrays[x][0].equals("Abhi")))
			System.out.println();
			{
				int colCnt = sArrays[x].length;
				//System.out.println(colCnt);
				for(int y=0;y<colCnt;y++)
					System.out.print(sArrays[x][y] + " -- ");
			}
		}
	}
	


	public static String[][] ReadExcel(String inputFile) throws Exception
	{	
		System.out.println("Inside readExcel");
		File f = new File(inputFile);
		if (f.isFile() && f.canRead()) 
		{
			FileInputStream fis = new FileInputStream(inputFile);
			Workbook wb = WorkbookFactory.create(fis);
			
			Sheet s = wb.getSheet("Sheet 1");
			int rCount, cellCount;
			rCount = s.getLastRowNum();
			System.out.println("RCount is " + rCount);
	
			
			String sArray1[][]=new String[rCount][5];
			
			
			for(int rNum =1; rNum<=rCount;rNum++)
			{
				Row r = s.getRow(rNum);
				int c = r.getLastCellNum();
				
				System.out.println("Last Cell Num is " + c);
				
				for(int cNum=0;cNum<c;cNum++)
				{
					Cell cobj1 = r.getCell(cNum);
					System.out.print(cobj1.toString() + " -- ");
					//sArray[rNum-1][cNum] = cobj1.toString();
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

