package com.eigenRisk.functionalities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.commonutilities.Fluent_Wait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.collect.Iterators;


public class Exposure_Import_Load {
	public static String notify_msg1, notify_msg2, raw_message;
	public static Boolean notify_conditon;
	
	public static void clickExposure(WebDriver driver) throws IOException, Exception{
		System.out.println("clickExposure - START");
		String selectExposureXpath = readElement("locatorPaths.properties","selectExposureXpath");
		//selectExposureXpath = "//span[@class='dropdown input-group-addon ng-scope'][@ng-controller='LoadExposureCtrl']/a/i[1]";
		//selectExposureXpath = "//span[@class='dropdown input-group-addon ng-scope'][@ng-controller='LoadExposureCtrl']/a/i[1]";
		
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		System.out.println("Waiting for Element to Load...");
		//Fluent_Wait.webDriverWait(driver, selectExposureXpath);
		ProgramBuilder.waitForElementToLoad(driver, selectExposureXpath);
		System.out.println("Done...");
		WebElement selectExpo = driver.findElement(By.xpath(selectExposureXpath));
		actions.moveToElement(selectExpo).perform();
		selectExpo.click();
		System.out.println("clickExposure - END");
		//actions.perform();
		//Thread.sleep(2000);
	}	
	
	public static String readElement(String fileName, String fieldName) throws Exception {
		FileReader propertyFile = new FileReader(fileName);
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		String fieldValue = configProperty.getProperty(fieldName);
		propertyFile.close();
		return fieldValue;
		
	}
	
	public static void readErrorOnImport(WebDriver driver) throws Exception {
		String errorText = "//p[@class='ng-binding']";
		String importNewExposureXpath = readElement("locatorPaths.properties","importNewExposureXpath");
		
		System.out.println("Inside readErrorOnImport...........................");
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		System.out.println(importNewExposureXpath);
		Fluent_Wait.webDriverWait(driver, importNewExposureXpath);
		
		WebElement importNewExpo = driver.findElement(By.xpath(importNewExposureXpath));
		Thread.sleep(1000);
		actions.moveToElement(importNewExpo).perform();
		importNewExpo.click();
		
		//String showAll = "//upload-status/div[1]/span[2]/input[@class='ng-pristine ng-valid ng-touched']";
		String showAll = "html/body/div[50]/div/div/div/div[2]/upload-status/div[1]/span[2]/input";
		//waitForElementToLoad(driver, showAll);
		Thread.sleep(3000);
		driver.findElement(By.xpath(showAll));
		Thread.sleep(1000);
		waitForElementToLoad(driver, errorText);
		
		List<WebElement> errorList = driver.findElements(By.xpath(errorText));
		int rowSize = errorList.size();
		System.out.println("Row Size is " + rowSize);
		String errString = "Error is " + errorList.get(1).getText();
		System.out.println(errString);
	}
	
	
	public static void importNewExposure(WebDriver driver, String fileName) throws Exception {
		String importNewExposureXpath = readElement("locatorPaths.properties","importNewExposureXpath");
		setExposurePath(fileName);
		
		System.out.println("Inside importNewExposure...........................");
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		System.out.println(importNewExposureXpath);
		Fluent_Wait.webDriverWait(driver, importNewExposureXpath);
		
		WebElement importNewExpo = driver.findElement(By.xpath(importNewExposureXpath));
		Thread.sleep(1000);
		actions.moveToElement(importNewExpo).perform();
		importNewExpo.click();
		
		Thread.sleep(1000);
		String LaptopMake = readElement("locatorPaths.properties","LaptopMake");
		String ReadExposurePath;
		
		if (LaptopMake.equals("HP"))
			ReadExposurePath = "./Tools/ReadExposurePath_HP.exe";
		else
			ReadExposurePath = "./Tools/ReadExposurePath_Lenovo.exe";
		
		Runtime.getRuntime().exec(ReadExposurePath);
		Thread.sleep(2000);
		
		//Thread.sleep(3000);
		String importNewExposureBrowse = readElement("locatorPaths.properties","importNewExposureBrowse");
		String importButtonXPath = readElement("locatorPaths.properties","ImportButton");
		Thread.sleep(2000);
		
		Boolean isPresent = driver.findElements(By.xpath(importNewExposureBrowse)).size() > 0;
		driver.findElement(By.xpath(importNewExposureBrowse)).click();
		//Thread.sleep(2500);
		String elementXPath = readElement("locatorPaths.properties","import_ValidateData");
		waitForElementToLoad(driver, elementXPath);
		
		driver.findElement(By.xpath("//input[@ng-model='showAll'][@type='checkbox']")).click();
		
		
		WebElement browseButton = driver.findElement(By.xpath(importNewExposureBrowse));
		WebElement importButton = driver.findElement(By.xpath(importButtonXPath));
		
		actions.moveToElement(browseButton).perform();
		actions.moveToElement(browseButton).perform();
		//browseButton.click();
		//Thread.sleep(500);
		actions.moveToElement(importButton).perform();
		
		actions.moveToElement(importButton).perform();
		importButton.click();

		

		long start = System.currentTimeMillis();
		//SelectExposure.loadTimeChecker(driver, start, "Import New Exposure");
		//Thread.sleep(7000);
		
		boolean present;
		String msg;
		int ctr=0;
		while(true) {
			try {
				ctr++;
				/*
				try {
					WebElement LoadStage = driver.findElement(By.xpath("//b[@class='ng-binding']"));
					System.out.println("Load Stage is " + LoadStage.getText());
					String cLoadStage = LoadStage.getText(); 
					if (cLoadStage.equals("3 / 4 : "))
						System.out.println("Its at 3rd Stage..");
					else if (cLoadStage.equals("1 / 4 : ") || cLoadStage.equals("2 / 4 : "))
						System.out.println("Importing in Progress..");
					else {
						System.out.println("Clicking again on Import Button >>>>>>>>>>>>>>>>>>>");
						importButton.click();
					}
						
						
				} catch (ElementNotFoundException eNF) {
					
					importButton.click();
					Thread.sleep(500);
				}
				*/
				
				if(ctr == 15) {
					WebElement LoadNULL = driver.findElement(By.xpath("//span[@class='pull-left'][@ng-show='!filtered.length']"));
					System.out.println("Load NULL " + LoadNULL.getText());
					
					if (LoadNULL.getText().length() > 5) {
						start = System.currentTimeMillis();
						actions.moveToElement(importButton).perform();
						importButton.click();
					}
				}
				driver.findElement(By.xpath("//span[@class='noty_text']"));
                WebElement WE = driver.findElement(By.xpath("//span[@class='noty_text']"));
                msg = WE.getText();
                System.out.println("Message is -> " + msg);
                raw_message = msg;
                if(msg.length() > 0)
                       break;
			} catch (NoSuchElementException e) {
				present = false;
			}
		}
		
		String exposureFile = readElement("locatorPaths.properties","importNewExposureFile");
		notify_msg1 = exposureFile.substring(9, exposureFile.length());
		notify_msg2 = msg.substring(28,msg.length());
		
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		System.out.println("Raw Message - " + Exposure_Import_Load.raw_message);
		
		

	}

	
	public static void waitForElementToLoad(WebDriver driver, String elementXPath) throws Exception {
		//String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
		System.out.println("Wait for Element to Load");
		int ctr = 0;
		while(true) {
			ctr++;
			try {
				if (ctr > 200) {
					System.out.println("Wait for Element Timed Out!!!");
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
	
	
	
	
	public static void setExposurePath() throws Exception {
		File currentDirFile = new File(".");
		String exposureLoc = currentDirFile.getAbsolutePath();
		exposureLoc = exposureLoc + readElement("locatorPaths.properties","importNewExposureFile");
		
		exposureLoc = exposureLoc.replace(".", "");
		exposureLoc = exposureLoc.replace("xls", ".xls");
		
		exposureLoc = exposureLoc.replace("/", "\\");
		BufferedWriter out = new BufferedWriter(new FileWriter("C:/TestRun/FileToOpen.txt")); 
		out.write(exposureLoc); 
		out.close();
		System.out.println("Exposure to be imported is > " + exposureLoc);
	}
	
	public static void setExposurePath(String exposureFile) throws Exception {
		File currentDirFile = new File(".");
		String exposureLoc = currentDirFile.getAbsolutePath();
		exposureLoc = exposureLoc + exposureFile;
		
		System.out.println("Exposure Loc is - " + exposureLoc);
		exposureLoc = exposureLoc.replace(".", "");
		
		if(exposureLoc.contains("zip"))
			exposureLoc = exposureLoc.replace("zip", ".zip");
		else
			exposureLoc = exposureLoc.replace("xls", ".xls");
		
		exposureLoc = exposureLoc.replace("/", "\\");
		BufferedWriter out = new BufferedWriter(new FileWriter("C:/TestRun/FileToOpen.txt")); 
		out.write(exposureLoc); 
		out.close();
		System.out.println("Exposure to be imported is > " + exposureLoc);
	}
	
	public static void loadExposure(WebDriver driver, String exposureToLoad) throws Exception {
//		String selectExposureXpath = "//div[@class='pull-left'][@ng-click='loadExposure(f)']/div/span";
		String selectExposureXpath = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureXpath");
		
		waitForElementToLoad(driver, selectExposureXpath);
		WebElement selectExpo = driver.findElement(By.xpath(selectExposureXpath));
		
		System.out.println("Inside loadExposure...");
		
		List<WebElement> TotalColumnCount = driver.findElements(By.xpath(selectExposureXpath));
		int listSize = TotalColumnCount.size();
		
		notify_conditon = false;
		Thread.sleep(100);
		int x=0;
		for(x=0; x < listSize; x++) {
			System.out.println(x+1 + ". " + TotalColumnCount.get(x).getText() + " --> " + exposureToLoad);
			String exposureName = TotalColumnCount.get(x).getText();
			if(exposureToLoad.contains(exposureName)) {
				TotalColumnCount.get(x).click();
				System.out.println("Clicked on " + TotalColumnCount.get(x));
				notify_conditon = true;
				break;
			}
			
		}
		 
		
		System.out.println("-----------------------------------------------------" + x);
		if(x == listSize) {
			System.out.println("Couldn't locate the exposure " + exposureToLoad + " in the list of Exposures that can be loaded.");
		}
					
		int ctr=0;
		while(true) {
			try {
				String assetsNum = "//span[@class='accumulationSummaryDetail ng-binding']";
				String assetNumValue = driver.findElement(By.xpath(assetsNum)).getText();
				/*
				if(assetNumValue.length() != 1);
					System.out.println("assetNumValue is -> " + assetNumValue + " - " + assetNumValue.length());
				*/	
				ctr++;
				if(ctr > 300) {
					System.out.println("Loading TimedOut...");
					//notify_conditon = false;
					break;
				}
					
				
				if (!assetNumValue.equals("0"))
					break;
			}
			catch (Exception e1) {
				System.out.println("Exception " + e1);
			}
		}
		
	}
	
	
	
	public static void listAllExposures(WebDriver driver) throws Exception {
		WebElement selectExpo = driver.findElement(By.xpath(readElement("locatorPaths.properties","selectExposureXpath")));
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadExposureXpath"))).click();
		Thread.sleep(2000);
		
		System.out.println("%%% " + readElement("locatorPaths.properties","LoadExposureList"));
		
		List<WebElement> exposureRowList = driver.findElements(By.xpath(readElement("locatorPaths.properties","LoadExposureList")));
		int ctr = 0;
		Iterator<WebElement> itr = exposureRowList.iterator();
		
		int size = Iterators.size(itr);
		String[] accuSummary = new String [size];
		System.out.println("Number of Exposures listed is " + size);
		String lText;
		
		int RowIndex=1;
		for(WebElement rowElement:exposureRowList)
		{
		      List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      for(WebElement colElement:TotalColumnCount)
		      {
		           System.out.println("Row "+RowIndex+" Column "+ColumnIndex+" Data " + colElement.getText());
		           ColumnIndex=ColumnIndex+1;
		       }
		      RowIndex=RowIndex+1;
		      System.out.println("-----------------------------------------------------");
		 }
		
		WebElement cancelButton = driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadCancelButton")));
		cancelButton.click();
		Thread.sleep(250);
	}
	
	
	public static void DeleteSingleExposure(WebDriver driver, String exposureToDelete) throws Exception {
		WebElement selectExpo = driver.findElement(By.xpath(readElement("locatorPaths.properties","selectExposureXpath")));
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadExposureXpath"))).click();
		Thread.sleep(2000);
		
		System.out.println("%%% " + readElement("locatorPaths.properties","LoadExposureList"));
		
		List<WebElement> exposureRowList = driver.findElements(By.xpath(readElement("locatorPaths.properties","LoadExposureList")));
		int ctr = 0;
		Iterator<WebElement> itr = exposureRowList.iterator();
		
		int size = Iterators.size(itr);
		String[] accuSummary = new String [size];
		System.out.println("Number of Exposures listed is " + size);
		String lText;
		
		int RowIndex=1;
		for(WebElement rowElement:exposureRowList)
		{
		      List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      for(WebElement colElement:TotalColumnCount)
		      {
		    	  if(ColumnIndex == 2) {
		    		  String importSetName = colElement.getText();
		    		  System.out.println(exposureToDelete);
		    		  System.out.println(importSetName);
		    		  if(importSetName.equals(exposureToDelete))
		    		  	System.out.println("Row "+RowIndex+" Column "+ ColumnIndex +" Data " + colElement.getText());
		    	  }
		           ColumnIndex=ColumnIndex+1;
		       }
		      RowIndex=RowIndex+1;
		      System.out.println("-----------------------------------------------------");
		 }
		
		WebElement cancelButton = driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadCancelButton")));
		cancelButton.click();
		Thread.sleep(250);
	}
	
	

	public static void DeleteAllExposure(WebDriver driver, String exposureToDelete) throws Exception {
		WebElement selectExpo = driver.findElement(By.xpath(readElement("locatorPaths.properties","selectExposureXpath")));
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadExposureXpath"))).click();
		Thread.sleep(2000);
		
		System.out.println("%%% " + readElement("locatorPaths.properties","LoadExposureList"));
		
		List<WebElement> exposureRowList = driver.findElements(By.xpath(readElement("locatorPaths.properties","LoadExposureList")));
		int ctr = 0;
		Iterator<WebElement> itr = exposureRowList.iterator();
		
		int size = Iterators.size(itr);
		String[] accuSummary = new String [size];
		System.out.println("Number of Exposures listed is " + size);
		String lText;
		
		int RowIndex=1;
		for(WebElement rowElement:exposureRowList)
		{
		      List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
		      int ColumnIndex=1;
		      for(WebElement colElement:TotalColumnCount)
		      {
		    	  if(ColumnIndex == 2) {
		    		  String importSetName = colElement.getText();
		    		  System.out.println(exposureToDelete);
		    		  System.out.println(importSetName);
		    		  if(importSetName.equals(exposureToDelete))
		    		  	System.out.println("Row "+RowIndex+" Column "+ ColumnIndex +" Data " + colElement.getText());
		    	  }
		    	  else if(ColumnIndex == 6){
		    		  System.out.println("Deleting " + colElement.getText());
		    		  colElement.click();
//		    		  driver.findElement(By.xpath("//i[@class='fa fa-trash-o fa-fw']")).click();
//		    		  Thread.sleep(1500);
		    	  }
		    	  try {
			    	  driver.findElement(By.xpath("//i[@class='fa fa-trash-o fa-fw']")).click();
			    	  Thread.sleep(1500);
		    	  }
		    	  catch (ElementNotFoundException ENF) {
		    		  System.out.println(ENF);
		    	  }
		    	  	
		          ColumnIndex=ColumnIndex+1;
		       }
		      RowIndex=RowIndex+1;
		      System.out.println("-----------------------------------------------------");
		 }
		
		WebElement cancelButton = driver.findElement(By.xpath(readElement("locatorPaths.properties","LoadCancelButton")));
		cancelButton.click();
		Thread.sleep(250);
	}
	
}
 