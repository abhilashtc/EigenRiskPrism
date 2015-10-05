package com.eigenRisk.functionalities;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.eigenRisk.Utilities.CommonUtilities;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class TopN_DataValidation {
	public static String exportedTopN[][], expectedTopN[][];
	public static String exportedCountries[], expectedCountries[];
	public static boolean exportedList;
	public static String errorString;
	
	public static void EvaluateTopN_Assets() throws Exception {
		String userName = System.getProperty("user.name");
		System.out.println("User Name -> " + userName);
		
		String downloadLocation  = "C:\\Users\\" + userName + "\\Downloads";
		System.out.println("Download Location -> " + downloadLocation);
		
		File latestFile = lastFileModified(downloadLocation);
		System.out.println(latestFile.getName());
		
		String Top_N_Expected = "./TestData/51_Records_TIV_with_ValueFields.xlsx";
		String Top_N_Actual = downloadLocation + "//" + latestFile.getName();
		
		exportedList = false;
		System.out.println("Loading Events from -> " + Top_N_Expected);
		getData(Top_N_Expected);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		exportedList = true;
		System.out.println("Loading Events from -> " + Top_N_Actual);
		getData(Top_N_Actual);
		
		/*
		
		System.out.println("Printing from Actual List...");
		printArray(exportedTopN);
		
		System.out.println("<..................................................................................................................>");
		
		System.out.println("Printing from Expected List...");
		printArray(expectedTopN);
		*/
		
		System.out.println("*****************Validating Top N");
		validateTopN();
	}
	
	public static File lastFileModified(String dir) {
		System.out.println("Inside lastFileModified");
	    File fl = new File(dir);
	    File[] files = fl.listFiles(new FileFilter() {          
	        public boolean accept(File file) {
	            return file.isFile();
	        }
	    });
	    
	    long lastMod = Long.MIN_VALUE;
	    File choice = null;
	    System.out.println("Before Try");
	    int x = 1;
	    try {
		    for (File file : files) {
		    	try {
		    		System.out.println("Before IF " + x++  + " " + file.getName());
			        if (file.lastModified() > lastMod && file.getName().startsWith("Top N Report")) {
			        	System.out.println("Inside IF ");
			            choice = file;
			            lastMod = file.lastModified();
			        }
		    	}
		    	catch (Exception e) {
			    	System.out.println("Returning NULL...");
			    	return choice;
			    }
		    }
	    }
	    catch (Exception e) {
	    	System.out.println("Returning NULL...");
	    	return choice;
	    }
	    return choice;
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
				exportedTopN = new String[sh.getLastRowNum() + 1][sh.getRow(0).getLastCellNum()];
			else
				expectedTopN = new String[sh.getLastRowNum() + 1][sh.getRow(0).getLastCellNum()];
			
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
								exportedTopN[x][y] = str1.trim();
							else 
								expectedTopN[x][y] = str1.trim();
						}
						else if (type == HSSFCell.CELL_TYPE_NUMERIC) {
							str2 = cell.getNumericCellValue();
//							System.out.print(str2 + "  ***   ");
							if(exportedList == true)
								exportedTopN[x][y] = String.valueOf(str2).trim();
							else
								expectedTopN[x][y] = String.valueOf(str2).trim();
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
	
	public static void validateTopN() throws Exception {
		int arrayLengthActual = exportedTopN.length;
		int arrayBreadthActual = exportedTopN[0].length;
		String testResults = "Passed";
		
		int arrayLengthExpected = expectedTopN.length;
		int arrayBreadthExpected = expectedTopN[0].length;
		
		
		System.out.println("Array Length is -> " + arrayLengthActual + " for Exported Top N and " + arrayLengthExpected + " for Expected Top N");
		System.out.println("Array Breadth is -> " + arrayBreadthActual + " for Exported Top N and " + arrayBreadthExpected + " for Expected Top N");
		
		
		String testSummary;
		if (arrayLengthActual == arrayLengthExpected) {
			if (arrayBreadthActual == arrayBreadthExpected) {
				checkColumnHeaders();
				for(int x=1; x < arrayLengthExpected; x++) {
					System.out.println("************************************************ (" + x + ") Asset #" + expectedTopN[x][2] + " *****************************************************");
					testSummary = "";
					for(int y=1; y < arrayBreadthExpected ; y++) {
		//				System.out.print(arrayToPrint[x][y] + " -- ");
						String expectedValue = expectedTopN[x][y];
						String actualValue = exportedTopN[x][y];
						
//						System.out.println("..................................................................................");
//						System.out.println("<" + exportedTopN[0][y] + ">, [Expected -> " + expectedValue + ", Actual -> " + actualValue + "]");
						testSummary = testSummary + "<" + exportedTopN[0][y] + ">, [Expected -> " + expectedValue + ", Actual -> " + actualValue + "]\n";
						
						if(expectedValue.equals(actualValue))
							testResults = "Passed";
						else 
							testResults = "Failed";
						//System.out.println(testResults + " for " + expectedTopN[x][2] + " " + expectedTopN[0][y] + " Actual Value " + actualValue + " Expected Value " + expectedValue );
						testSummary = testSummary + "\t" + testResults + " for " + expectedTopN[0][y] + " Actual Value " + actualValue + " Expected Value " + expectedValue + "\n";
					}
					System.out.println(testSummary);
				}
			}
			else {
				testResults = "Failed";
				System.out.println("Number of Columns Expected is " + arrayBreadthExpected + ", where as Actual is " + arrayBreadthActual);
			}
		} 
		else {
			testResults = "Failed";
			System.out.println("Number of Rows Expected is " + arrayLengthExpected + ", where as Actual is " + arrayLengthActual);
		}
		System.out.println("Test Case -> " + testResults);
		Assert.assertTrue(testResults.equals("Passed"));
	}
	
	
	public static void checkColumnHeaders() throws Exception {
		int arrayLengthActual = exportedTopN.length;
		int arrayBreadthActual = exportedTopN[0].length;
		String testResults = "Passed";
		
		int arrayLengthExpected = expectedTopN.length;
		int arrayBreadthExpected = expectedTopN[0].length;
		
		/*
		String actualFields[] = new String [arrayBreadthActual];
		String expectedFields[] = new String [arrayBreadthActual];
		*/
		
		String actualFields="", expectedFields="";
		
		for(int x=0; x < arrayBreadthActual; x++) {
			if ((x+1) == arrayBreadthActual) {
				expectedFields = expectedFields + expectedTopN [0][x];
				actualFields = actualFields + exportedTopN[0][x];
			}
			else {
				expectedFields = expectedFields + expectedTopN [0][x] + ", ";
				actualFields = actualFields + exportedTopN[0][x] + ", ";
			}
			
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Actual Column Headers -> " + actualFields);
		System.out.println("Expected Column Headers -> " + expectedFields);
		
		if (!actualFields.equals(expectedFields))
			System.out.println("Column Headers are not same in both the Excel... Test Case Failed.");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		Assert.assertTrue(actualFields.equals(expectedFields));
	}
	
	
	
	public static void printTable (WebDriver driver) throws Exception {
		WebElement table = driver.findElement(By.xpath("//table[@id='topNAssetsTable']"));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    System.out.println(num++ + "--------------------------------");
		    int x=1;
		    for (WebElement cell : cells) {
		        System.out.println(x + " - content >>   " + cell.getText());
		        x++;
		    }
		}
	}	
	
	public static void evaluateLocalCurrency (String currency, String assetNum, String expectedCurrencyValue, WebDriver driver, String testCaseName) throws Exception {
		System.out.println("Inside Evaluate Local Currency");
		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties", "accuSummaryTIV");
		String TIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		if(!currency.equals("USD")){
			selectCurrency(currency, expectedCurrencyValue, assetNum, driver);
			CommonUtilities.waitForTIV_ToChange(driver, TIV, expectedCurrencyValue);
			driver.findElement(By.xpath(accuSummaryTIV)).click();
		}
		
		WebElement table = driver.findElement(By.xpath("//table[@id='topNAssetsTable']"));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
//		    System.out.println(num++ + "--------------------------------");
		    int x=1;
		    String assetNumTable = "", TIV_Table = "";
		    
		    for (WebElement cell : cells) {
//		        System.out.println(x + " - content >>   " + cell.getText());
		        if(x == 2)
		        	assetNumTable = cell.getText();
		        else if (x == 4) {
		        	TIV_Table = cell.getText();
		        	if(assetNumTable.equals(assetNum)) {
		        		System.out.println("Expected TIV for " + assetNum + " is -> " + expectedCurrencyValue + ", but the actual value is " + TIV_Table);
		        		Assert.assertTrue(TIV_Table.equals(expectedCurrencyValue));
		        		//Taking Screen Shot
		        		//String targetFile = "F:\\ScreenShots\\" + "Asset" + assetNum + "_" + currency;
		        		String targetFile = "F:\\ScreenShots\\" + "PASSED-" + testCaseName;
		        		
						CommonUtilities.takeScreenShot(driver, targetFile);
		        		System.out.println("Asset -> " + assetNum + " = " + assetNumTable);
		        		System.out.println("TIV -> " + expectedCurrencyValue + " = " + TIV_Table);
		        		break;
		        	}
		        }
		        x++;
		    }
		}
		
	}
	
	public static void selectCurrency(String currency, String expectedCurrencyValue, String assetNum, WebDriver driver) throws Exception {
		String CurrencyListButton = ProgramBuilder.readElement("locatorPaths.properties", "CurrencyListButton");
//		driver.findElement(By.xpath(CurrencyListButton)).click();
//		Thread.sleep(3000);
		
		Actions actions = new Actions(driver);
		WebElement moveonmenu = driver.findElement(By.xpath(CurrencyListButton));
		actions.moveToElement(moveonmenu);
		actions.perform();
		//Thread.sleep(4000);
		
		String CurrencyList = ProgramBuilder.readElement("locatorPaths.properties", "CurrencyList");
		System.out.println(CurrencyList);
		
		List<WebElement> currencyList = driver.findElements(By.xpath(CurrencyList));
		int listSize = currencyList.size();
		System.out.println("List size is " + listSize);
		
		System.out.println("Looking for Currency " + currency);
		
		for(int x=0; x < listSize; x++) {
			System.out.println(x + " <" + currencyList.get(x).getText() + ">");
			if(currencyList.get(x).getText().equals("")){
				System.out.println("Clicked...");
				driver.findElement(By.xpath(CurrencyListButton)).click();
			}
			if(currencyList.get(x).getText().equals(currency)) {
				System.out.println("Clicking on currency " + currency);
				
				String clickCurrency = CurrencyList + "[" + (x+1) + "]";
				String goToCurrency = CurrencyList + "[" + (x-3) + "]";
				System.out.println(clickCurrency);
				WebElement element = driver.findElement(By.xpath(clickCurrency));
				WebElement element1 = driver.findElement(By.xpath(goToCurrency));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element1);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

//				Thread.sleep(10000);
				break;

			}
		}	
	}	
	
	
	public static void checkListedCountries(WebDriver driver) throws Exception {
		System.out.println("Obtaining the list of the Countries...");
		
		String By_Link = CommonUtilities.readElement("locatorPaths.properties", "Cont_By_Link");
		String By_Country = CommonUtilities.readElement("locatorPaths.properties", "Cont_By_Country");
		String Cont_Table_Icon = CommonUtilities.readElement("locatorPaths.properties", "Cont_Table_Icon");
		String Contrib_Table = CommonUtilities.readElement("locatorPaths.properties", "Cont_Contrib_Table");
		
		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties", "accuSummaryTIV");
		String TIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		System.out.println("TIV -> " + TIV);
		
		driver.findElement(By.xpath(By_Link)).click();
		Thread.sleep(1000);
		System.out.println("Clicked on By LINK...");
		driver.findElement(By.xpath(By_Country)).click();
		System.out.println("Clicked on Country...");
		Thread.sleep(1000);
		
		CommonUtilities.waitForElementToLoad(driver, Cont_Table_Icon);
		//driver.findElement(By.xpath(Cont_Table_Icon)).click();
		System.out.println(Cont_Table_Icon);
		WebElement element = driver.findElement(By.xpath(Cont_Table_Icon));
		System.out.println("Going to Click on Table " + Cont_Table_Icon);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		System.out.println("Clicked on Table");
		
		Thread.sleep(5000);
		getCountriesFromContribution(driver);
		
		validateCountries();
		
		
		
	}
	
	public static void getCountriesFromContribution(WebDriver driver) throws Exception {
		String Cont_Contrib_Table = CommonUtilities.readElement("locatorPaths.properties", "Cont_Contrib_Table");
		List<WebElement> rows = driver.findElements(By.xpath(Cont_Contrib_Table));
		
		int rowSize = rows.size();
		System.out.println("Row Size is " + rowSize);
		expectedCountries = new String[rowSize];
		exportedCountries = new String[rowSize];
		
		for(int i =0;i < rowSize; i++) {
			exportedCountries[i] = rows.get(i).getText();
		}
	}
	
	public static void printCountries() throws Exception {
		int ctryLength = expectedCountries.length;;
		for(int x=0; x < ctryLength; x++) {
			System.out.println((x+1) + ". " + expectedCountries[x] + " ---> " + exportedCountries[x]);
		}
		System.out.println("***********************************************************************************");
	}
	
	
	public static void validateCountries() throws Exception {
		int ctryLength = expectedCountries.length;
		System.out.println("Listing Countries...");
		initializeExpectedCountries();
		printCountries();
		boolean result = false;
		
//		Arrays.sort(exportedCountries);
		for(int x=0; x < ctryLength; x++) {
//			System.out.println((x+1) + ". " + expectedCountries[x]);
//			System.out.println((x+1) + ". " + exportedCountries[x]);
			String expectedCountry = expectedCountries[x];
			int ctryLength1 = expectedCountries.length;
			for(int y=0; y< ctryLength1; y++) {
				String exportedCountry = exportedCountries[y];
				if(expectedCountry.equals(exportedCountry)) {
					System.out.println("Passed -> " + expectedCountry + " is listed at [" + (x+1) + " - " + (y+1) + "]");
					result = true;
					break;
				}
				
				if(y == (ctryLength1 - 1)) {
					System.out.println("Failed -> " + expectedCountry + " at " + (x+1) + " is not listed among the Country List from Contribution.");
					result = false;
				}
			}
			
			Assert.assertTrue(result);
		}
		System.out.println("Test Case Passed...");
	}
	
	
	public static void initializeExpectedCountries() throws Exception {
		expectedCountries [0] = "Antigua and Barbuda";
		expectedCountries [1] = "Azerbaijan";
		expectedCountries [2] = "Bulgaria";
		expectedCountries [3] = "Cambodia";
		expectedCountries [4] = "Canada";
		expectedCountries [5] = "Chile";
		expectedCountries [6] = "Finland";
		expectedCountries [7] = "France";
		expectedCountries [8] = "Guernsey";
		expectedCountries [9] = "Isle of Man";
		expectedCountries [10] = "Japan";
		expectedCountries [11] = "Lesotho";
		expectedCountries [12] = "Libya";
		expectedCountries [13] = "Morocco";
		expectedCountries [14] = "Netherlands";
		expectedCountries [15] = "Nigeria";
		expectedCountries [16] = "Papua New Guinea";
		expectedCountries [17] = "Singapore";
		expectedCountries [18] = "Somalia";
		expectedCountries [19] = "Sri Lanka";
		expectedCountries [20] = "United Kingdom";
		expectedCountries [21] = "United States";
		expectedCountries [22] = "Uzbekistan";
	}
	
	public static void addRequiredTopNSettings(WebDriver driver) throws Exception {
		TopN_Settings.validateFlag = false;
		Thread.sleep(500);
		TopN_Settings.click_Settings(driver, "TopN");
		TopN_Settings.addMultipleMeasures(driver, "Value Buildings", "Value Contents", "Value Business Interruption", "TopN");
		TopN_Settings.click_Settings(driver, "TopN");
		TopN_Settings.removeMultipleMeasures(driver, "Damage (%)", "Intensity", "Ground Up Loss", "TopN");
		TopN_Settings.validateFlag = true;
	}
	
	
	public static boolean exportToExcel(WebDriver driver) throws Exception {
		//Obtaining the current "Top N Report" from the download location
		String userName = System.getProperty("user.name");
		String downloadLocation  = "C:\\Users\\" + userName + "\\Downloads";
		System.out.println("Download Location -> " + downloadLocation);
		File currentFile = lastFileModified(downloadLocation);
		System.out.println("Current File Name is -> " + currentFile.getName());
		String currentFileName = currentFile.getName();
		
		//Clicking on the Export to Excel
		String TopN_ExportToExcel = CommonUtilities.readElement("", "TopN_ExportToExcel");
		driver.findElement(By.xpath(TopN_ExportToExcel)).click();
		System.out.println("Clicked on Export to Excel ->" + TopN_ExportToExcel);
		Thread.sleep(5000);
		
		boolean flag = true;
		while(true) {
			//Obtaining the latest "Top N Report" from the download location
			File latestFile = lastFileModified(downloadLocation);
			System.out.println("Latest File Name is -> " + latestFile.getName());
			String latestFileName = latestFile.getName();
			System.out.println("#### " + currentFileName + " -- " + latestFileName);
			
			if(latestFileName.equals(currentFileName))
				flag = false;
			else {
				System.out.println("New File ->" + latestFileName);
				flag = true;
				break;
			}
		}
		
		return flag;
	
	}
	
}
