package com.eigenRisk.functionalities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eigenrisk.commonutilities.Fluent_Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.io.Files;

public class HelpButton {
	static String templateName;
	
	public static void DownloadTemplate(WebDriver driver) throws Exception {
		System.out.println("Inside Help Button Method");
		String HelpButton = readElement("locatorPaths.properties","HelpButton");
		String Templates = readElement("locatorPaths.properties","Templates");
		String TemplatesTable = readElement("locatorPaths.properties","TemplatesTable");
		Thread.sleep(4000);
		
		System.out.println(HelpButton);
		System.out.println(Templates);
		System.out.println(TemplatesTable);
		
		waitForElementToLoad(driver, HelpButton);
		Actions actions = new Actions(driver);
		Fluent_Wait.webDriverWait(driver, HelpButton);
		WebElement selectHelp = driver.findElement(By.xpath(HelpButton));
		actions.moveToElement(selectHelp).perform();
		
		Fluent_Wait.webDriverWait(driver, Templates);
		WebElement selectTemplates = driver.findElement(By.xpath(Templates));
		actions.moveToElement(selectTemplates).perform();
		driver.findElement(By.xpath(Templates)).click();
		
		waitForElementToLoad(driver, TemplatesTable);
		List<WebElement> TemplatesList = driver.findElements(By.xpath(TemplatesTable));
		int ctr = TemplatesList.size();
		System.out.println("CTR -> " + ctr);
		for(int i=0; i<ctr; i++) {
			String str = TemplatesList.get(i).getText();
			System.out.println("Str -> " + str);
			String columnDetails = TemplatesTable + "[" + (i+1) + "]" + "/td[" + (2) + "]/a";
			
			if(str.contains("Asset Schedule Excel")) {
				WebElement excelDownloadLink = driver.findElement(By.xpath(columnDetails));
				
				Actions keyboardAndMouseActions = new Actions(driver);
				keyboardAndMouseActions.contextClick(excelDownloadLink).perform();
				for(int x=0; x<5; x++) {
					keyboardAndMouseActions.sendKeys(Keys.ARROW_DOWN).perform();
				}
				keyboardAndMouseActions.sendKeys(Keys.ENTER).perform();
				
				System.out.println("### " + columnDetails);
			}
			
			String ReadTemplatePath = "./Tools/ReadTemplatePath.exe";
			
//			Runtime.getRuntime().exec(ReadTemplatePath);
//			Thread.sleep(4000);
			
			String current = new java.io.File( "." ).getCanonicalPath();
	        System.out.println("Current dir:"+ current);
			System.out.println(columnDetails);
			
			setTemplateSavePath();
			Thread.sleep(4000);
			Runtime.getRuntime().exec(ReadTemplatePath);
			Thread.sleep(4000);
		}
	}
	
	
	
	public static void setTemplateSavePath() throws Exception {
		String current = new java.io.File( "." ).getCanonicalPath();
		templateName = current + "\\Temp\\DownloadedTemplate\\Import_ExcelTemplate_" + System.currentTimeMillis() + ".xlsm";
		templateName = templateName.replace("/", "\\");
		System.out.println("Template Name is " + templateName);
		
		File file = new File(current + "\\Temp");
		File file2 = new File(current + "\\Temp\\DownloadedTemplate");
		
		if (file.exists())
			System.out.println("Folder Exists " +  current + "\\Temp");
		else {
			System.out.println("Folder don't exist " +  current + "\\Temp");
			file.mkdir();
			file2.mkdir();
			System.out.println("hence created the folder.");
		}
		
		if (!file2.exists())
			file2.mkdir();
		
		File currentDirFile = new File(templateName);
		
		BufferedWriter out = new BufferedWriter(new FileWriter(current + "/Temp/TemplateDetails.txt")); 
		out.write(templateName); 
		out.close();
		System.out.println("Exposure to be imported is > " + templateName);
		
		File file3 = new File("c:\\Temp_Path");
		if (!file3.exists())
			file3.mkdir();
		
		BufferedWriter out2 = new BufferedWriter(new FileWriter("c://Temp_Path/TemplateDetails.txt")); 
		out2.write(templateName); 
		out2.close();
		
	}
	

	//public static void addEventsToTemplate() throws Exception {
	public static void main(String[] arg) throws Exception {
		try {
			// Specify the path of file
			//C:\EigenRisk_Prism_Project\EigenRiskPrism\Temp\DownloadedTemplate\Import_ExcelTemplate_1435662416834.xlsm
			String templateName = "C:\\EigenRisk_Prism_Project\\EigenRiskPrism\\Temp\\DownloadedTemplate\\Import_ExcelTemplate_1435662416834.xlsm";
			File src=new File(templateName);
			
			System.out.println("Opened 1");
			 
			// load file
			FileInputStream fis=new FileInputStream(src);
			System.out.println("Opened 2"); 
			
			// Load workbook
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			System.out.println("Opened 3");
			
			// Load sheet- Here we are loading first sheetonly
			XSSFSheet sh1= wb.getSheetAt(0);
			 
			// getRow() specify which row we want to read.
			 
			// and getCell() specify which column to read.
			// getStringCellValue() specify that we are reading String data.
			
			System.out.println("1.. -> " + sh1.getRow(0).getLastCellNum());
			System.out.println("2.. -> " + sh1.getRow(0).getRowNum());
			
			     
			System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());
			/* 
			System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
			 
			System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());
			 
			System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());
			 
			System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());
			 
			System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());
			 */
		}catch (Exception e) {
				System.out.println(e.getMessage());
		}
			  
	}
	/*Example
	String selectExposureXpath = readElement("locatorPaths.properties","selectExposureXpath");
	*/
	public static String readElement(String fileName, String fieldName) throws Exception {
		FileReader propertyFile = new FileReader(fileName);
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		String fieldValue = configProperty.getProperty(fieldName);
		propertyFile.close();
		return fieldValue;
		
	}
	

	
	public static void waitForElementToLoad(WebDriver driver, String elementXPath) throws Exception {
		//String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
		int ctr = 0;
		while(true) {
			ctr++;
			try {
				if (ctr > 200) {
					System.out.println("Wait for Element Timed Out!!! " + elementXPath);
					break;
				}
				//driver.findElement(By.xpath("elementXPath"));
				Boolean isPresent = driver.findElements(By.xpath(elementXPath)).size() > 0;
				if (isPresent) {
					System.out.println("Element Visible...");
					break;
				}
				
				
	    	  }
	    	  catch (ElementNotFoundException ENF) {
	    		  System.out.println(ENF);
	    	  }
		}
	}
		
	
	
}
