/**
 * 
 */
package com.eigenRisk.functionalities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.eigenRisk.Utilities.CommonUtilities;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * @author abhilashtc
 *
 */
public class ExposureDrive {
	
	public static String exportedError[][], expectedError[][];
	public static String exportedCountries[], expectedCountries[];
	public static boolean exportedList;
	public static String errorString, selectConfig;
	
	//Importing Exposure
	public static void importExposure(WebDriver driver, String exposureFile, String configName) throws Exception {
		System.out.println("Calling selectExposureFile..");
		selectExposureFile(driver, exposureFile, configName);
		
		System.out.println("Going to Click on Go..");
		String EX_ImportGo = CommonUtilities.readElement("", "EX_ImportGo");
		driver.findElement(By.xpath(EX_ImportGo)).click();
		System.out.println("Clicked on Go..");
		
		CommonUtilities.getNotificationText(driver);
		String msg = CommonUtilities.getNotificationText(driver);
		
		String EX_ErrorDownArrow = CommonUtilities.readElement("", "EX_ErrorDownArrow");
		CommonUtilities.waitForElementToLoad(driver, EX_ErrorDownArrow);
		
		Thread.sleep(5000);
		driver.findElement(By.xpath(EX_ErrorDownArrow)).click();
		System.out.println("Clicked on the Down Arrow..");
		Thread.sleep(5000);
		
		System.out.println("Msg-> " + msg);
		if(msg.equals("Failed Validations")) {
			String EX_ErrorExcel = CommonUtilities.readElement("", "EX_ErrorExcel");
			CommonUtilities.waitForElementToLoad(driver, EX_ErrorExcel);
			System.out.println("CommonUtilities.elementExist ->" + CommonUtilities.elementExist);
			System.out.println(driver.findElement(By.xpath(EX_ErrorExcel)).getText());
			if(CommonUtilities.elementExist == true) {
				System.out.println("Export Error to Excel Exists...");
				Thread.sleep(10000);
				driver.findElement(By.xpath(EX_ErrorExcel)).click();
				System.out.println("Exported the Error Excel...");
				Thread.sleep(25000);
				
				String userName = System.getProperty("user.name");
				System.out.println("User Name -> " + userName);
				String downloadLocation  = "C:\\Users\\" + userName + "\\Downloads";
				System.out.println("Download Location -> " + downloadLocation);
				
				File latestFile = lastFileModified(downloadLocation);
				System.out.println(latestFile.getName());
				
				String Error_Expected = "./TestData/ErrorReport.csv";
				String Error_Actual = downloadLocation + "//" + latestFile.getName();
				
				System.out.println("=============================================================");
				System.out.println(Error_Expected);
				System.out.println(Error_Actual);
				System.out.println("=============================================================");
				
				System.out.println(compareFile(Error_Expected, Error_Actual));
				String result = compareFile(Error_Expected, Error_Actual);
				
				Assert.assertTrue(result.equals("Pass"));
				System.out.println("TEST CASE -> Passed");

			}
		}
		
	}
	
	public static void selectExposureFile(WebDriver driver, String exposureFile, String configName) throws Exception {
		CommonUtilities.setExposurePath(exposureFile);
		System.out.println("Inside importExposure Drive");
		
		clickImportExposure(driver);
		System.out.println("selectConfig..." + selectConfig);
		
		boolean flag;
		if(!selectConfig.equals("false"))
			flag = selectConfig(driver, configName);
		else
			flag = true;
		
		System.out.println("Flag ->" + flag );
		if(flag) {
			System.out.println(">> EX_Import_Browse");
			String EX_Import_Browse = CommonUtilities.readElement("", "EX_Import_Browse");
			CommonUtilities.waitForElementToLoad(driver, EX_Import_Browse);
			System.out.println("<< EX_Import_Browse");
			
			Thread.sleep(12000);
			System.out.println("XPath (EX_Import_Browse)->" + EX_Import_Browse);
			driver.findElement(By.xpath(EX_Import_Browse)).click();
			Thread.sleep(12000);
			getPathDetails();
			Thread.sleep(5000);
		}
		else {
			Assert.assertTrue(flag);
		}
	}
	
	
	public static void createConfig (WebDriver driver) throws Exception {
		String EX_CreateConfig = CommonUtilities.readElement("", "EX_CreateConfig");
		CommonUtilities.waitForElementToLoad(driver, EX_CreateConfig);
		driver.findElement(By.xpath(EX_CreateConfig)).click();
		
		//Checking whether the cancel Button Exists.
		String EX_HeaderSelectCancel = CommonUtilities.readElement("", "EX_HeaderSelectCancel");
		CommonUtilities.waitForElementToLoad(driver, "EX_HeaderSelectCancel");
		
		String EX_CreateConfigHeader = CommonUtilities.readElement("", "EX_CreateConfigHeader");
		driver.findElement(By.xpath(EX_CreateConfigHeader));
		
	}
	
	public static void clickImportExposure(WebDriver driver) throws Exception {
		String importNewExposureXpath = CommonUtilities.readElement("locatorPaths.properties", "importNewExposureXpath");
		CommonUtilities.waitForElementToLoad(driver, "importNewExposureXpath");
		
		String EX_Import = CommonUtilities.readElement("locatorPaths.properties", "EX_Import");
		System.out.println("XPath (EX_Import) ->" + EX_Import);
		driver.findElement(By.xpath(EX_Import)).click();
		
		System.out.println("Clicked on Import Exposure Button...");
		
	}
	
	
	//Deleting the Local Queue
	public static void deleteLocalQueue(WebDriver driver) throws Exception {
		try {
			String EX_LocalQueueDel = CommonUtilities.readElement("", "EX_LocalQueueDel");
			CommonUtilities.waitForElementToLoad(driver, EX_LocalQueueDel);
			driver.findElement(By.xpath(EX_LocalQueueDel)).click();
			String EX_LocalQueueDelConf = CommonUtilities.readElement("", "EX_LocalQueueDelConf");
			CommonUtilities.waitForElementToLoad(driver, EX_LocalQueueDelConf);
			driver.findElement(By.xpath(EX_LocalQueueDelConf)).click();
		}
		catch (ElementNotFoundException e) {
			System.out.println("Local Queue is empty, hence nothing to delete...");
		}		
	}
	
	//Selecting the Config
	public static boolean selectConfig(WebDriver driver, String configName) throws Exception {
		String EX_SelectConfig = CommonUtilities.readElement("", "EX_SelectConfig");
		driver.findElement(By.xpath(EX_SelectConfig)).click();
		
		Thread.sleep(2000);
		String EX_ConfigList = CommonUtilities.readElement("", "EX_ConfigList");
		List<WebElement> configList = driver.findElements(By.xpath(EX_ConfigList));
		
		int rowSize = configList.size();
		System.out.println("Looking for Config-> " + configName);
		System.out.println("Row Size is " + rowSize);
		int i=0;
		for(; i < rowSize; i++) {
			String conFig = configList.get(i).getText();
			String userName = configList.get(i+1).getText();
			//System.out.println(i+1 + ". " + conFig + " <" + userName + ">");
			System.out.println(i + "...<" + configList.get(i).getText() + "><" + configName);
			if(configList.get(i).getText().equals(configName)) {
				System.out.println("Clicking on " + conFig + " created by " + userName);
				configList.get(i).click();
				return (true);
			}
		}
		System.out.println(i + " Returning False...");
		return (false);
	}
	
	public static String compareFile(String fILE_ONE2, String fILE_TWO2)throws Exception 
	{
		File f1 = new File(fILE_ONE2); //OUTFILE
		File f2 = new File(fILE_TWO2); //INPUT
	
		FileReader fR1 = new FileReader(f1);
		FileReader fR2 = new FileReader(f2);
	
		BufferedReader reader1 = new BufferedReader(fR1);
		BufferedReader reader2 = new BufferedReader(fR2);
	
		String line1 = null;
		String line2 = null;
		String flag="Pass";
		while ((flag.equals("Pass")) &&((line1 = reader1.readLine()) != null)&&((line2 = reader2.readLine()) != null)) 
		{
		    if (!line1.equalsIgnoreCase(line2)){  
		        flag="Fail";
		        System.out.println(flag + " -> " + line1 + " ## " + line2);
		        break;
		    }
		    else{
		        flag="Pass";   
		        System.out.println(flag + " -> " + line1 + " ## " + line2);
		    }
		}
		reader1.close();
		reader2.close();
		return flag;


	}
	

	public static File lastFileModified(String dir) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    for (File file : files) {
	        if (file.lastModified() > lastMod && file.getName().startsWith("ErrorReport ")) {
	            choice = file;
	            lastMod = file.lastModified();
	        }
	    }
	    return choice;
	}
	
	
	
	/*
	public static void validateErrorFile() throws Exception {
		System.out.println("<<Inside validateErrorFile...>>");
		String userName = System.getProperty("user.name");
		System.out.println("User Name -> " + userName);
		
		String downloadLocation  = "C:\\Users\\" + userName + "\\Downloads";
		System.out.println("Download Location -> " + downloadLocation);
		
		File latestFile = lastFileModified(downloadLocation);
		System.out.println(latestFile.getName());
		
		String Error_Expected = "./TestData/ErrorReport.csv";
		String Error_Actual = downloadLocation + "//" + latestFile.getName();
		
		exportedList = false;
		System.out.println("Loading Events from -> " + Error_Expected);
		getData(Error_Expected);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		exportedList = true;
		System.out.println("Loading Events from -> " + Error_Actual);
		getData(Error_Actual);
		
	}
	

	
	public static void getData(String fileName) throws IOException, InvalidFormatException{
//		FileInputStream fis = new FileInputStream(".//Automation.xlsx");
		FileInputStream fis = new FileInputStream(fileName);
		
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet 1");
		
		Row row1 = sh.getRow(0);
//		for(int i=0;i<=sh.getLastRowNum();i++){
		//for(int i=0;i<1;i++){
			Cell cell1 = row1.getCell(1);
			
			System.out.println("Number of Rows -> " + sh.getLastRowNum());
			System.out.println("Number of Columns -> " + sh.getRow(0).getLastCellNum());
			
			if(exportedList == true)
				exportedError = new String[sh.getLastRowNum() + 1][sh.getRow(0).getLastCellNum()];
			else
				expectedError = new String[sh.getLastRowNum() + 1][sh.getRow(0).getLastCellNum()];
			
			System.out.println("Is this an exported list ->" + exportedList);
			int x=0, y=0;
			
			//System.out.print("cell1.getStringCellValue() -> " + cell1.getStringCellValue());
			//System.out.print("    ***   ");
			if(cell1.getStringCellValue().equalsIgnoreCase("Asset #")){
				//System.out.println("----------------------------------------- " + sh.getLastRowNum());
				for(int k =0;k<=sh.getLastRowNum();k++,x++){
					Row r = sh.getRow(k);
//					System.out.println();
					y = 0;
					for(int j=0;j<r.getLastCellNum();j++,y++){
						Cell cell = r.getCell(j);
						int type = cell.getCellType();
						
						String str1;
						double str2;
						
//						System.out.println("X -> " + x + ", Y -> " + y);
						
						
						if (type == HSSFCell.CELL_TYPE_STRING) {
							str1 = cell.getStringCellValue();
//							System.out.print(str1 + "  ***   ");
							if(exportedList == true)
								exportedError[x][y] = str1.trim();
							else 
								expectedError[x][y] = str1.trim();
						}
						else if (type == HSSFCell.CELL_TYPE_NUMERIC) {
							str2 = cell.getNumericCellValue();
//							System.out.print(str2 + "  ***   ");
							if(exportedList == true)
								exportedError[x][y] = String.valueOf(str2).trim();
							else
								expectedError[x][y] = String.valueOf(str2).trim();
						}
						//System.out.println("Written " + exportedTopN[x][y]);
					}
				}
			}
		//}
		System.out.println("------------------------------------------------------------------------------------------------");
	}
	
	public static void printArray(String arrayToPrint[][]) {
		int arrayLength = arrayToPrint.length;
		int arrayBreadth = arrayToPrint[0].length;
		
		System.out.println("Array Length is -> " + arrayLength);
		System.out.println("Array Breadth is -> " + arrayBreadth);
		
		for(int x=0; x < arrayLength; x++) {
			for(int y=0; y < arrayBreadth ; y++)
				System.out.print(arrayToPrint[x][y] + " -- ");
			System.out.println();
		}
	}
	*/
	
	public static void getPathDetails() throws Exception {
		Thread.sleep(1000);
		String LaptopMake = "Lenovo";
		String ReadExposurePath;
		
		if (LaptopMake.equals("HP"))
			ReadExposurePath = "./Tools/ReadExposurePath_HP.exe";
		else
			ReadExposurePath = "./Tools/ReadExposurePath_Lenovo.exe";
		
		Runtime.getRuntime().exec(ReadExposurePath);
		Thread.sleep(2000);
		
		
	}

}
