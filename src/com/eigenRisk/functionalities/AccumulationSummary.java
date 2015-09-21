package com.eigenRisk.functionalities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AccumulationSummary {
	public static void topN_Assets(WebDriver driver) throws Exception {
		Thread.sleep(3000);
		
		String topN2011_byTIV[][] = {
				{"14141", "Site", "Asset 14141", "10.6 M"}, 
				{"511", "Site", "Asset 511", "8.4 M"}, 
				{"4251", "Site", "Asset 4251", "8.1 M"}, 
				{"9834", "Site", "Asset 9834", "6.7 M"}, 
				{"13887", "Site", "Asset 13887", "5.1 M"}, 
				{"16930", "Site", "Asset 16930", "3.6 M"}, 
				{"13356", "Site", "Asset 13356", "3.5 M"}, 
				{"14121", "Site", "Asset 14121", "3.5 M"}, 
				{"23572", "Site", "Asset 23572", "3.3 M"}, 
				{"5485", "Site", "Asset 5485", "3.3 M"}, 
			};

		String topN2012_byTIV[][] = {
				{"14141", "Site", "Asset 14141", "12.8 M"}, 
				{"4251", "Site", "Asset 4251", "8.2 M"}, 
				{"511", "Site", "Asset 511", "8.1 M"}, 
				{"9834", "Site", "Asset 9834", "6.9 M"}, 
				{"13887", "Site", "Asset 13887", "4.9 M"}, 
				{"13503", "Site", "Asset 13503", "3.7 M"}, 
				{"19051", "Site", "Asset 19051", "3.6 M"}, 
				{"16930", "Site", "Asset 16930", "3.6 M"}, 
				{"14121", "Site", "Asset 14121", "3.5 M"}, 
				{"24000", "Site", "Asset 24000", "3.5 M"}
			};
		
		String topN2013_byTIV[][] = {
					{"1", "14141", "Site", "Asset 14141", "13.5 M"},
					{"7456", "Site", "Asset 7456", "11.6 M"},
					{"4251", "Site", "Asset 4251", "11.1 M"},
					{"511", "Site", "Asset 511", "9.2 M"},
					{"9834", "Site", "Asset 9834", "7.9 M"},
					{"13887", "Site", "Asset 13887", "6.1 M"},
					{"27019", "Site", "Asset 27019", "4.9 M"},
					{"12992", "Site", "Asset 12992", "4.7 M"},
					{"19047", "Site", "Asset 19047", "4.4 M"},
					{"14142", "Site", "Asset 14142", "4.4 M"}
				};
		
		
		String topN2014_byTIV[][] = {
				{"14141", "Site", "Asset 14141", "17.7 M"},
				{"4251", "Site", "Asset 4251", "12.4 M"}, 
				{"7456", "Site", "Asset 7456", "10.6 M"}, 
				{"511", "Site", "Asset 511", "10.5 M"}, 
				{"11757", "Site", "Asset 11757", "8.7 M"}, 
				{"17482", "Site", "Asset 17482", "7.6 M"}, 
				{"40000", "Site", "Asset 40000", "6.7 M"}, 
				{"13887", "Site", "Asset 13887", "5.8 M"}, 
				{"40001", "Site", "Asset 40001", "5.3 M"}, 
				{"12992", "Site", "Asset 12992", "5.0 M"}
			};
		
		String topN2015_byTIV[][] = {
				{"14141", "Site", "Asset 14141", "17.7 M"},
				{"4251", "Site", "Asset 4251", "12.4 M"}, 
				{"7456", "Site", "Asset 7456", "10.6 M"}, 
				{"511", "Site", "Asset 511", "10.5 M"}, 
				{"11757", "Site", "Asset 11757", "8.7 M"}, 
				{"17482", "Site", "Asset 17482", "7.6 M"}, 
				{"40000", "Site", "Asset 40000", "6.7 M"}, 
				{"13887", "Site", "Asset 13887", "5.8 M"}, 
				{"40001", "Site", "Asset 40001", "5.3 M"}, 
				{"12992", "Site", "Asset 12992", "5.0 M"}
			};
		
		WebElement wEl = driver.findElement(By.xpath("//span[@title='Select Analysis Date']"));
		System.out.println("Getting Accumulation Summary for --> " + wEl.getText());
		String year1 = wEl.getText();
		System.out.println("The selected year is " + year1);
		
		
		//reading properties file
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("topNXpath")));
		
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath(prop.getProperty("topNXpath"))).click();
		System.out.println(".............................................................................");
		//printTable(driver);
		
		if(year1.equals("01/01/2011"))
			validateTopN(driver, topN2011_byTIV);
		else if(year1.equals("01/01/2012"))
			validateTopN(driver, topN2012_byTIV);
		else if(year1.equals("01/01/2013"))
			validateTopN(driver, topN2013_byTIV);
		else if(year1.equals("01/01/2014"))
			validateTopN(driver, topN2014_byTIV);
		else if(year1.equals("01/01/2015"))
			validateTopN(driver, topN2015_byTIV);
		
		/*
		String selectTopN = "10";
		List<WebElement> topNDigit = driver.findElements(By.xpath(prop.getProperty("topNDigit")));

		Iterator<WebElement> itr = topNDigit.iterator();
		while(itr.hasNext()) {
		    //System.out.println(itr.next());
		    WebElement row = itr.next();
		    String rVal = row.getText();
		    
		    if(rVal.equals(selectTopN)) {
		    	System.out.println("The selected index is " + rVal);
		    	action.moveToElement(row).perform();
		    	row.click();
		    }
		    //System.out.println("#### " + row.getText());
		}
		*/
		
		
		System.out.println(".............................................................................");
		//printTable(driver);
		
	}
	
	public static void setTopNDigit(WebDriver driver, String selectTopN) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		Actions action = new Actions(driver);
		
		//Clicking on Top10
		driver.findElement(By.xpath(prop.getProperty("topNXpath"))).click();
		
		WebElement set_topNSetDigit = driver.findElement(By.xpath(prop.getProperty("topNSetDigit")));
		set_topNSetDigit.click();
		
		action.sendKeys(Keys.CONTROL,"a");
		set_topNSetDigit.sendKeys(selectTopN);
		/*
		List<WebElement> topNDigit = driver.findElements(By.xpath(prop.getProperty("topNDigit")));

		Iterator<WebElement> itr = topNDigit.iterator();
		while(itr.hasNext()) {
		    //System.out.println(itr.next());
		    WebElement row = itr.next();
		    String rVal = row.getText();
		    
		    if(rVal.equals(selectTopN)) {
		    	System.out.println("The selected index is " + rVal);
		    	action.moveToElement(row).perform();
		    	row.click();
		    }
		    //System.out.println("#### " + row.getText());
		    
		} */
	}
	
	
	public static void validateTopN(WebDriver driver, String[][] topNList){
		WebElement table = driver.findElement(By.xpath("//table[@id='topNAssetsTable']"));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		int x=0, y=0;
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    System.out.println(num++ + "--------------------------------");
		    for (WebElement cell : cells) {
		        System.out.println("content >>   " + cell.getText());
		    }
		}
	}
	
	
	
/*	public static void printTable(WebDriver driver) {
		WebElement table = driver.findElement(By.xpath("//table[@id='topNAssetsTable']"));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
		 List<WebElement> cells = row.findElements(By.tagName("td"));
		 for (WebElement cell : cells) {
			 System.out.println(row.findElements(By.tagName("td")).);
		 // And so on
		 }
		}*/
		
	public static void printTable(WebDriver driver) {
		WebElement table = driver.findElement(By.xpath("//table[@id='topNAssetsTable']"));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    System.out.println(num++ + "--------------------------------");
		    for (WebElement cell : cells) {
		        System.out.println("content >>   " + cell.getText());
		    }
		}
	}
	
	public static void assetAccumlationSummary(WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("assetAccuElements")));
		
		Iterator<WebElement> itr = assetSumm.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
		System.out.println("Exited from Iterator");
	}
	
	public static void ExportToExcel(WebDriver driver) throws Exception{
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("exportToExcel")));
		
		Iterator<WebElement> itr = assetSumm.iterator();
		while(itr.hasNext()) {
		    System.out.println(itr.next());
		}
		printTable(driver);
		System.out.println("Exited from ExportToExcel");
	}
	
	public static void accumulationSummary (WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("accumulationSummary")));
		
		String[] accuSummary = new String [3];
		int ctr = 0;
		
		System.out.println("Inside accumulationSummary");
		Iterator<WebElement> itr = assetSumm.iterator();
		while(itr.hasNext()) {
		   // System.out.println("---> " + itr.next().getText());
			accuSummary[ctr] = itr.next().getText();
			System.out.println(accuSummary[ctr++]);
		}
		//printTable(driver);
		
		WebElement wEl = driver.findElement(By.xpath("//span[@title='Select Analysis Date']"));
		System.out.println("Getting Accumulation Summary for --> " + wEl.getText());
		String year1 = wEl.getText();
		
		String exposureName = prop.getProperty("LoadSpecification");
		System.out.println(exposureName);
		//System.out.println(exposureName.startsWith(",'"));
		
		/*StringTokenizer st2 = new StringTokenizer(exposureName, ",");
		while (st2.hasMoreElements()) {
			System.out.println("@@@@@ > " + st2.nextElement());
		}*/
		String accuAssets = accuSummary[0], accuTIV = accuSummary[1], accuGU_Loss = accuSummary[2];
		
		String[] split = exposureName.split(",");
		String[] split2 = split[1].split("'");
		String dataSheetName = split2[1];
		System.out.println("Data Sheet Name -> " + dataSheetName);
		if(dataSheetName.equals("Global_Demo_Data V 0 7-Tornado.xlsx")) {
			System.out.println("------------------------------------------------------------");
			if (year1.equals("01/01/2011"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("2,425"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				
				if(accuTIV.equals("810.9 M"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				
				if(accuGU_Loss.equals("810.9 M"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
			}
			else if (year1.equals("01/01/2012"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("4,938"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				
				if(accuTIV.equals("1.6 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				
				if(accuGU_Loss.equals("1.6 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
			}
			else if (year1.equals("01/01/2013"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
//				if(accuAssets.equals("9,862"))
				if(accuAssets.equals("9,961"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				
				if(accuTIV.equals("3.3 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				
				if(accuGU_Loss.equals("3.3 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
			}
			else if (year1.equals("01/01/2014"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("15,027"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				
				if(accuTIV.equals("5.0 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				
				if(accuGU_Loss.equals("5.0 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
			}
			else if (year1.equals("01/01/2015"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("15,027"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				
				if(accuTIV.equals("4.8 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				
				if(accuGU_Loss.equals("4.8 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
			}
			System.out.println("------------------------------------------------------------");
		}
		else
			System.out.println("Data Sheet Name is not Global_Demo_Data V 0 7-Tornado.xlsx");
		
		
		
		System.out.println("Exited from ExportToExcel");
		/*
		For 1/1/2011
			Assets#	 		2,425
			TIV		 		810.4 M
			Ground Up Loss	810.4 M 
			
		For 1/1/2012
			Assets#	 		4,938
			TIV		 		1.6 B
			Ground Up Loss	1.6 B 
			
		For 1/1/2013
			Assets#	 		9,862 
			TIV		 		3.3 B
			Ground Up Loss	3.3 B 
			
		For 1/1/2014
			Assets#	 		14,882
			TIV		 		4.9 B
			Ground Up Loss	4.9 B 
			
		For 1/1/2015
			Assets#	 		14,882
			TIV		 		4.9 B
			Ground Up Loss	4.9 B 
			
		
		*/
	}
	
/*	public static void click_TopN_Settings (WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		//List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("TopNSettings")));
	    
	    System.out.println("Inside AssetList.click_TopN_Settings()");
	    System.out.println(prop.getProperty("TopNSettings"));
	    
		String[] accuSummary = new String [3];
		
		WebElement settingsIcon = driver.findElement(By.xpath(prop.getProperty("TopNSettings")));
		//Actions action = new Actions(driver);
	    //action.moveToElement(settingsIcon).perform();
	    //action.moveToElement(settingsIcon).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath(prop.getProperty("TopNSettings"))).click();
	    
	    List<WebElement> availableMeasures = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures")));
		Iterator<WebElement> itr1 = availableMeasures.iterator();
		//driver.findElement(By.xpath("//span[contains(text(),'# People')]")).click();
		//driver.findElement(By.xpath("//span[contains(text(),'# Area')]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(text(),'>')]")).click();
	    
	    List<WebElement> selectedMeasures = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures")));
		Iterator<WebElement> itr = selectedMeasures.iterator();
		System.out.println("Following Measures are currently selected...");
		System.out.println("List Size of Selected Measures is " + selectedMeasures.size());
		
		int ctr = selectedMeasures.size();
		for(int x=0; x < ctr; x++)
			System.out.println(x+1 + ". " + selectedMeasures.get(x).getText());
		
		while(itr.hasNext()) {
		   // System.out.println("---> " + itr.next().getText());
			accuSummary[ctr] = itr.next().getText();
			System.out.println(accuSummary[ctr++]);
		}
		System.out.println("Clicking on Cancel Button");
		driver.findElement(By.xpath(prop.getProperty("TopNSettingsApply"))).click();
	}*/
	
	
	public static void accumulationSummary (WebDriver driver, String exposureFile) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("accumulationSummary")));
		
		String[] accuSummary = new String [3];
		int ctr = 0;
		
		System.out.println("Inside accumulationSummary");
		Iterator<WebElement> itr = assetSumm.iterator();
		while(itr.hasNext()) {
		   // System.out.println("---> " + itr.next().getText());
			accuSummary[ctr] = itr.next().getText();
			System.out.println(accuSummary[ctr++]);
		}
		//printTable(driver);
		
		WebElement wEl = driver.findElement(By.xpath("//span[@title='Select Analysis Date']"));
		System.out.println("Getting Accumulation Summary for --> " + wEl.getText());
		String year1 = wEl.getText();
		
		String exposureName = prop.getProperty("LoadSpecification");
		System.out.println(exposureName);
		//System.out.println(exposureName.startsWith(",'"));
		
		/*StringTokenizer st2 = new StringTokenizer(exposureName, ",");
		while (st2.hasMoreElements()) {
			System.out.println("@@@@@ > " + st2.nextElement());
		}*/
		String accuAssets = accuSummary[0], accuTIV = accuSummary[1], accuGU_Loss = accuSummary[2];
		
		String[] split = exposureName.split(",");
		String[] split2 = split[1].split("'");
		String dataSheetName = split2[1];
		
		System.out.println("Before IF..");
		
//		if(dataSheetName.equals("Global_Demo_Data V 0 7-Tornado.xlsx")) {
		if(exposureFile.contains("Karthika_Global_Demo_Data")) {
			System.out.println("------------------------------------------------------------");
			if (year1.equals("01/01/2011"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("2,425"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				Assert.assertTrue(accuAssets.equals("2,425"));
				
				if(accuTIV.equals("810.9 M"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				Assert.assertTrue(accuTIV.equals("810.9 M"));
				
				if(accuGU_Loss.equals("810.9 M"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("810.9 M"));
			}
			else if (year1.equals("01/01/2012"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("4,938"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				Assert.assertTrue(accuAssets.equals("4,938"));
				
				if(accuTIV.equals("1.6 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				Assert.assertTrue(accuTIV.equals("1.6 B"));
				
				if(accuGU_Loss.equals("1.6 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("1.6 B"));
			}
			else if (year1.equals("01/01/2013"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("9,862"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				Assert.assertTrue(accuAssets.equals("9,862"));
				
				if(accuTIV.equals("3.3 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				Assert.assertTrue(accuTIV.equals("3.3 B"));
				
				if(accuGU_Loss.equals("3.3 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("3.3 B"));
			}
			else if (year1.equals("01/01/2014"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("14,882"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				Assert.assertTrue(accuAssets.equals("14,882"));
				
				if(accuTIV.equals("4.9 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				Assert.assertTrue(accuTIV.equals("4.9 B"));
				
				if(accuGU_Loss.equals("4.9 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("4.9 B"));
			}
			else if (year1.equals("01/01/2015"))
			{
				System.out.println("Data Sheet Name is -> " + dataSheetName);
				if(accuAssets.equals("14,882"))
					System.out.println("Accumulation Summary is Correct <" + accuAssets + "> for " + wEl.getText());
				else
					System.out.println("Accumulation Summary is Wrong <" + accuAssets + "> for " + wEl.getText());
				Assert.assertTrue(accuAssets.equals("14,882"));
				
				if(accuTIV.equals("4.9 B"))
					System.out.println("TIV is Correct <" + accuTIV + "> for " + wEl.getText());
				else
					System.out.println("TIV is Wrong <" + accuTIV + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("4.9 B"));
				
				if(accuGU_Loss.equals("4.9 B"))
					System.out.println("Ground up Loss is Correct <" + accuGU_Loss + "> for " + wEl.getText());
				else
					System.out.println("Ground up Loss is Wrong <" + accuGU_Loss + "> for " + wEl.getText());
				Assert.assertTrue(accuGU_Loss.equals("4.9 B"));
			}
			System.out.println("------------------------------------------------------------");
		}
		else
			System.out.println("Accumulation Summmary is wrong for " + exposureFile);
		
		
		
		System.out.println("Exited from ExportToExcel");
		/*
		For 1/1/2011
			Assets#	 		2,425
			TIV		 		810.4 M
			Ground Up Loss	810.4 M 
			
		For 1/1/2012
			Assets#	 		4,938
			TIV		 		1.6 B
			Ground Up Loss	1.6 B 
			
		For 1/1/2013
			Assets#	 		9,862 
			TIV		 		3.3 B
			Ground Up Loss	3.3 B 
			
		For 1/1/2014
			Assets#	 		14,882
			TIV		 		4.9 B
			Ground Up Loss	4.9 B 
			
		For 1/1/2015
			Assets#	 		14,882
			TIV		 		4.9 B
			Ground Up Loss	4.9 B 
			
		
		*/
	}
}	
