package com.eigenRisk.functionalities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.eigenrisk.businesslogic.TopN;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.common.base.Verify;

public class TopN_Settings extends AccumulationSummary{
	public static boolean validateFlag = true;
	
	public static void Top_N_Settings (WebDriver driver) throws Exception {
		org.eigenrisk.testlab.Log.startTestCase("TEST SET : TopNSettings");
		
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		
		//Test Cases
		
		org.eigenrisk.testlab.Log.info(">>>> Executing Test Case :- Top N - Clicked on Settings");
		click_Settings(driver, "TopN");
		printLog("");
		
		addSingleMeasure(driver, "Value Buildings", "TopN");
		
		/*
		org.eigenrisk.testlab.Log.info(">>>> Executing Test Case :- Top N - Default Selected Measures");
		checkDefaultMeasures(driver, prop);
		printLog("");
		
		org.eigenrisk.testlab.Log.info(">>>> Executing Test Case :- Top N - Add Single Measure");
		addSingleMeasure(driver, prop, "Value Buildings");
		printLog("");
		
		org.eigenrisk.testlab.Log.info(">>>> Executing Test Case :- Top N - Remove Single Measure");
		removeSingleMeasure(driver, prop, "TIV");
		printLog("");
		
		printLog(">>>> Executing Test Case :- Top N - Add Multiple Measures");
		addMultipleMeasure(driver, prop, "Area", "Value Buildings");
		printLog("");
		
		printLog(">>>> Executing Test Case :- Top N - Remove Multiple Measures");
		removeMultipleMeasure(driver, prop, "TIV", "Ground Up Loss");
		*/
		
		System.out.println("ALL Measures");
		//addALLMeasures(driver, prop);
		
		org.eigenrisk.testlab.Log.endTestCase("TEST SET : TopNSettings");
		
		
		
		
	}
	
	public static void click_Settings (WebDriver driver, String widgetName) throws Exception {
		System.out.println("Inside click_TopN_Settings()");
		Thread.sleep(2000);
		String TopNSettings="";
		System.out.println(">>> " + widgetName);
		if (widgetName.equals("TopN")) {
			TopNSettings = readElement("locatorPaths.properties","TopNSettings");
			System.out.println("1.. " + TopNSettings);
			driver.findElement(By.xpath(TopNSettings)).click();
		} else if(widgetName.equals("accuSummary") || widgetName.equals("AccuSummary")) {
			TopNSettings = readElement("locatorPaths.properties","accuSummarySettings");
			System.out.println("2.. " + TopNSettings);
			System.out.println("5000 Milli Seconds...");
//			Thread.sleep(5000);
			ProgramBuilder.waitForElementToLoad(driver, TopNSettings);
			driver.findElement(By.xpath(TopNSettings)).click();
		}
	    Thread.sleep(1000);
	   // System.out.println(TopNSettings);
	    
	   //System.out.println("Exiting");
	    
	    /*
	    String TopN_SelectedMeasures = readElement("locatorPaths.properties","TopN_SelectedMeasures");
	    Boolean isPresent = driver.findElements(By.xpath(TopN_SelectedMeasures)).size() > 0;
		System.out.println("Settings Windows " + isPresent);
		
		
		if(isPresent)
			org.eigenrisk.testlab.Log.info("Test Case - PASSED");
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		Assert.assertTrue(isPresent);
		*/
	}
	
	
	public static void checkDefaultMeasures (WebDriver driver, String sMeasures[], String widgetName) throws Exception {
		System.out.println("Inside Check Default Selected Measures");
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";
		
		String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
		
		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
		
		System.out.println("### " + selectedMeasures.size());
		
		System.out.println("[" + TopN_SelectedMeasures + "]" + ">>> " + (selectedMeasures.get(1).getText()));
		/*
		String sMeasures[] = {	"TIV",
								"Ground Up Loss",
								"Damage (%)",
								"Intensity",
								//"# People"
							};
		*/
		int ctr = selectedMeasures.size();
		for(int x=0; x < ctr; x++) {
			System.out.println(x+1 + ". " + selectedMeasures.get(x).getText() + " -----------> " + sMeasures[x]);
			if (sMeasures[x].equals(selectedMeasures.get(x).getText())) {
				//org.eigenrisk.testlab.Log.info("Test Case - PASSED");
				System.out.println("Test Case - PASSED");
			}
			else {
				org.eigenrisk.testlab.Log.info("Test Case - FAILED");
				System.out.println("Test Case - FAILED");
			}
			Assert.assertTrue(sMeasures[x].equals(selectedMeasures.get(x).getText()));
			//org.eigenrisk.testlab.Log.info(selectedMeasures.get(x).getText() + " -----------> " + sMeasures[x]);
			Verify.verify((sMeasures[x].equals(selectedMeasures.get(x).getText())));
		}
		
		
		String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
		driver.findElement(By.xpath(TopNSettingsApply)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Apply Button");
		org.eigenrisk.testlab.Log.info("Test Case - PASSED");
		Thread.sleep(2000);
	}
	
	
	public static void addSingleMeasure (WebDriver driver, String measureName, String widgetName) throws Exception {
		boolean flag = false;
		System.out.println("Inside Check Default Selected Measures - " + measureName);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		
		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		System.out.println("AccuReset -> " + TopNSettingsReset);
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");

		String xPath = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName + "')]";
		Boolean isPresent = driver.findElements(By.xpath(xPath)).size() > 0;
		int ctr=0;
		System.out.println("Measure Name is " + isPresent);
		
		if(isPresent) {
			System.out.println("XPath is -> " + xPath);
			driver.findElement(By.xpath(xPath)).click();
			printLog("Selected Measure " + measureName + " from All Measures.");
			
			String TopNSettingsAdd = Header + readElement("locatorPaths.properties","TopNSettingsAdd");
			driver.findElement(By.xpath(TopNSettingsAdd)).click();
			printLog("Clicked on the Right Arrow to move the measure to the Selected Measure.");
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			driver.findElement(By.xpath(TopNSettings)).click();
			Thread.sleep(500);
			
			/*
			 * System.out.println("Widget Name is " + widgetName);
			
			if (widgetName.equals("accuSummary")) {
				String Accu_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
				System.out.println("XXX " + Accu_SelectedMeasures);
				List<WebElement> selectedMeasures = driver.findElements(By.xpath(Accu_SelectedMeasures));
			}
			else {
				String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
				System.out.println("XXX " + TopN_SelectedMeasures);
				List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
			}
			 * 
			 */
			
			String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
			System.out.println("XXX " + TopN_SelectedMeasures);
			List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
			
			
			
			ctr = selectedMeasures.size();
			System.out.println("Selected Measures is " + ctr);
			
			for(int x=0; x < ctr; x++) {
				System.out.println(x + ". " + selectedMeasures.get(x).getText());
				if (measureName.equals(selectedMeasures.get(x).getText())) {
					printLog("Validated Successfully that the added measure is listed under the Selected Measures.");
					System.out.println("Validated Successfully that the added measure is listed under the Selected Measures.");
					printLog("Clicked on Apply Button.");
					org.eigenrisk.testlab.Log.info("Test Case - PASSED");
					System.out.println("Passed " + x);
					flag = true;
					break;
				}
				
			}
		}
		else if (ctr == 5){
			flag = true;
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flag == false && ctr != 5) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED " + ctr);
		}
		else {
			flag = true;
			org.eigenrisk.testlab.Log.info("Test Case - PASSED");
			System.out.println("Test Case - PASSED " + ctr);
		}
		
		org.eigenrisk.testlab.Log.info("Flag is " + flag);
		System.out.println("Flag is " + flag + " and Ctr is " + ctr);
		Assert.assertTrue(flag);
			
	}
	
	
	public static void addMultipleMeasures (WebDriver driver, String measureName1, String measureName2, String measureName3, String widgetName ) throws Exception {
		int flagCount = 0;
		System.out.println("Inside Check Default Selected Measures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";
		
		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");

		String xPath1 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName1 + "')]";
		String xPath2 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName2 + "')]";
		String xPath3 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName3 + "')]";
		
		Boolean isPresent1 = driver.findElements(By.xpath(xPath1)).size() > 0;
		Boolean isPresent2 = driver.findElements(By.xpath(xPath2)).size() > 0;
		Boolean isPresent3 = driver.findElements(By.xpath(xPath3)).size() > 0;
		
		int sMeasureSize = 0;
		boolean flag = false;
		
		System.out.println("Measure Name is " + isPresent1 + ", " + isPresent2 + " & " + isPresent3);
		
		if(isPresent1 && isPresent2 && isPresent3) { // && validateFlag == true
			driver.findElement(By.xpath(xPath1)).click();
			driver.findElement(By.xpath(xPath2)).click();
			driver.findElement(By.xpath(xPath3)).click();
			
			printLog("Selected Measure " + measureName1 + ", " + measureName2 + " & " + measureName3 + " from All Measures.");
			
			String TopNSettingsAdd = Header + readElement("locatorPaths.properties","TopNSettingsAdd");
			driver.findElement(By.xpath(TopNSettingsAdd)).click();
			printLog("Clicked on the Right Arrow to move the measure to the Selected Measure.");
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			
			if (validateFlag == true) {
				driver.findElement(By.xpath(TopNSettings)).click();
				
				String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
				List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
				
				sMeasureSize = selectedMeasures.size();
				System.out.println("Selected measure size is " + sMeasureSize);
				
				for (int x=0; x < sMeasureSize; x++) {
					System.out.println(measureName1 + " - " + measureName2 + " - " + measureName3 + " [" +  selectedMeasures.get(x).getText() + "]");
					if (measureName1.equals(selectedMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName1 + " is listed under the Selected Measures.");
						flagCount++;
					}
					else if (measureName2.equals(selectedMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName2 + " is listed under the Selected Measures.");
						flagCount++;
					}
					else if (measureName3.equals(selectedMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName3 + " is listed under the Selected Measures.");
						flagCount++;
					}
				}
			}
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flagCount != 3 && sMeasureSize != 7) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		else if(sMeasureSize == 7) {
			flag = true;
			org.eigenrisk.testlab.Log.info("Test Case - PASSED");
			System.out.println("Test Case - PASSED");
		}
		System.out.println("Flag Count is " + flagCount);
		
		if (validateFlag == true) 
			Assert.assertTrue(flag);
			
	}
	

	
	public static void addMultipleMeasuresOneByOne (WebDriver driver, String measureName1, String measureName2, String measureName3, String widgetName ) throws Exception {
		int flagCount = 0;
		System.out.println("Inside Check Default Selected Measures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		
		String Header = "";
		int sMeasureSize;
		boolean flag = false;
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
		
		String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
		List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
		
		sMeasureSize = selectedMeasures.size();
		System.out.println("Selected measure size is " + sMeasureSize);

		String xPath1 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName1 + "')]";
		String xPath2 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName2 + "')]";
		String xPath3 = TopN_AvailableMeasures + "/span[contains(text(),'" + measureName3 + "')]";
		
		Boolean isPresent1 = driver.findElements(By.xpath(xPath1)).size() > 0;
		Boolean isPresent2 = driver.findElements(By.xpath(xPath2)).size() > 0;
		Boolean isPresent3 = driver.findElements(By.xpath(xPath3)).size() > 0;
		
		System.out.println("Measure Name is " + isPresent1 + ", " + isPresent2 + " & " + isPresent3);
		
		if(isPresent1 && isPresent2 && isPresent3) {
			String TopNSettingsAdd = Header + readElement("locatorPaths.properties","TopNSettingsAdd");
			driver.findElement(By.xpath(xPath1)).click();
			driver.findElement(By.xpath(TopNSettingsAdd)).click();
			
			driver.findElement(By.xpath(xPath2)).click();
			driver.findElement(By.xpath(TopNSettingsAdd)).click();
			
			driver.findElement(By.xpath(xPath3)).click();
			driver.findElement(By.xpath(TopNSettingsAdd)).click();
			
			printLog("Selected Measure " + measureName1 + ", " + measureName2 + " & " + measureName3 + " from All Measures.");
			printLog("Clicked on the Right Arrow to move the measure to the Selected Measure.");
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			driver.findElement(By.xpath(TopNSettings)).click();
			
			selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
			
			sMeasureSize = selectedMeasures.size();
			System.out.println("Selected measure size is " + sMeasureSize);
			
			for (int x=0; x < sMeasureSize; x++) {
				/*System.out.println(measureName1 + " - " + selectedMeasures.get(x).getText());
				System.out.println(measureName2);
				System.out.println(measureName3);*/
				if (measureName1.equals(selectedMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName1 + " is listed under the Selected Measures.");
					flagCount++;
				}
				else if (measureName2.equals(selectedMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName2 + " is listed under the Selected Measures.");
					flagCount++;
				}
				else if (measureName3.equals(selectedMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName3 + " is listed under the Selected Measures.");
					flagCount++;
				}
			}
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flagCount != 3 && sMeasureSize != 7) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		else if(sMeasureSize == 7) {
			flag = true;
			org.eigenrisk.testlab.Log.info("Test Case - PASSED");
			System.out.println("Test Case - PASSED");
		}
		System.out.println("Flag Count is " + flagCount);
		Assert.assertTrue(flag);
			
	}
	

	

	public static void addAllMeasures (WebDriver driver, String widgetName) throws Exception {
		int flagCount = 0;
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";
		
		//System.out.println("Inside Check Default Selected Measures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
		List<WebElement> availableMeasures = driver.findElements(By.xpath(TopN_AvailableMeasures));
		
		int aMeasureSize = availableMeasures.size();
		System.out.println("Total Available Measures are " + aMeasureSize);
		for (int y=0; y < aMeasureSize; y++)
			availableMeasures.get(y).click();
			
		String TopNSettingsAdd = Header + readElement("locatorPaths.properties","TopNSettingsAdd");
		driver.findElement(By.xpath(TopNSettingsAdd)).click();
		
		String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
		driver.findElement(By.xpath(TopNSettingsApply)).click();
		Thread.sleep(200);
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		driver.findElement(By.xpath(TopNSettings)).click();
		
		
		//String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
		availableMeasures = driver.findElements(By.xpath(TopN_AvailableMeasures));
		aMeasureSize = availableMeasures.size();
		
		System.out.println("Now the Available Measures is only " + aMeasureSize);
		
		if (aMeasureSize != 0) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		//System.out.println("Flag Count is " + flagCount);
		Assert.assertTrue(aMeasureSize == 0);

	}
	
	
	public static void removeSingleMeasure (WebDriver driver, String measureName, String widgetName) throws Exception {
		boolean flag = false;
		System.out.println("Inside Check Default Selected Measures - " + measureName);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_SelectedMeasures = readElement("locatorPaths.properties","TopN_SelectedMeasures");

		String xPath = Header + TopN_SelectedMeasures + "/span[contains(text(),'" + measureName + "')]";
		Boolean isPresent = driver.findElements(By.xpath(xPath)).size() > 0;
		
		System.out.println("Measure Name is " + isPresent);
		
		if(isPresent) {
			driver.findElement(By.xpath(xPath)).click();
			printLog("Selected Measure " + measureName + " from All Measures.");
			
			String TopNSettingsRem = Header + readElement("locatorPaths.properties","TopNSettingsRem");
			driver.findElement(By.xpath(TopNSettingsRem)).click();
			printLog("Clicked on the Left Arrow to move the measure to the Available Measures.");
			
			String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
			List<WebElement> availableMeasures = driver.findElements(By.xpath(TopN_AvailableMeasures));
			
			int ctr = availableMeasures.size();
			for(int x=0; x < ctr; x++) {
				System.out.println(availableMeasures.get(x).getText());
			}
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			driver.findElement(By.xpath(TopNSettings)).click();
			
			int sMeasureSize = availableMeasures.size();
			
			for (int x=0; x < sMeasureSize; x++) {
				if (measureName.equals(availableMeasures.get(x).getText())) {
					printLog("Validated Successfully that the measure been successfully removed and is listed under the Selected Measures.");
					org.eigenrisk.testlab.Log.info("Test Case - PASSED");
					System.out.println("Passed " + x);
					flag = true;
					break;
				}
			}
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flag == false) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		
		org.eigenrisk.testlab.Log.info("Flag is " + flag);
		System.out.println("Flag is " + flag);
		Assert.assertTrue(flag);
			
	}
	

	public static void removeMultipleMeasures (WebDriver driver, String measureName1, String measureName2, String measureName3, String widgetName ) throws Exception {
		int flagCount = 0;
		System.out.println("Inside removeMultipleMeasures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";
		
		//Clicking on Reset Button
		if (validateFlag == true) {
			String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
			driver.findElement(By.xpath(TopNSettingsReset)).click();
			org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		}
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");

		String xPath1 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName1 + "')]";
		String xPath2 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName2 + "')]";
		String xPath3 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName3 + "')]";
		
		Boolean isPresent1 = driver.findElements(By.xpath(xPath1)).size() > 0;
		Boolean isPresent2 = driver.findElements(By.xpath(xPath2)).size() > 0;
		Boolean isPresent3 = driver.findElements(By.xpath(xPath3)).size() > 0;
		
		System.out.println("Measure Name is " + isPresent1 + ", " + isPresent2 + " & " + isPresent3);
		
		if(isPresent1 && isPresent2 && isPresent3) {
			driver.findElement(By.xpath(xPath1)).click();
			driver.findElement(By.xpath(xPath2)).click();
			driver.findElement(By.xpath(xPath3)).click();
			
			printLog("Selected Measure " + measureName1 + ", " + measureName2 + " & " + measureName3 + " from All Measures.");
			
			String TopNSettingsRem = Header + readElement("locatorPaths.properties","TopNSettingsRem");
			driver.findElement(By.xpath(TopNSettingsRem)).click();
			printLog("Clicked on the Right Arrow to move the measure to the Selected Measure.");
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			
			if (validateFlag == true) {
				driver.findElement(By.xpath(TopNSettings)).click();
				
				String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
				List<WebElement> availableMeasures = driver.findElements(By.xpath(TopN_AvailableMeasures));
				
				int sMeasureSize = availableMeasures.size();
				
				for (int x=0; x < sMeasureSize; x++) {
					if (measureName1.equals(availableMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName1 + " is listed under the Selected Measures.");
						flagCount++;
					}
					else if (measureName2.equals(availableMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName2 + " is listed under the Selected Measures.");
						flagCount++;
					}
					else if (measureName3.equals(availableMeasures.get(x).getText())) {
						printLog("Validated Successfully that " + measureName3 + " is listed under the Selected Measures.");
						flagCount++;
					}
				}
			}
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flagCount != 3) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		System.out.println("Flag Count is " + flagCount);
		
		if (validateFlag == true) 
			Assert.assertTrue(flagCount == 3);
			
	}
	
	
	public static void removeMultipleMeasuresOneByOne (WebDriver driver, String measureName1, String measureName2, String measureName3, String widgetName ) throws Exception {
		int flagCount = 0;
		System.out.println("Inside Check Default Selected Measures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		
		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");

		String xPath1 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName1 + "')]";
		String xPath2 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName2 + "')]";
		String xPath3 = TopN_SelectedMeasures + "/span[contains(text(),'" + measureName3 + "')]";
		
		Boolean isPresent1 = driver.findElements(By.xpath(xPath1)).size() > 0;
		Boolean isPresent2 = driver.findElements(By.xpath(xPath2)).size() > 0;
		Boolean isPresent3 = driver.findElements(By.xpath(xPath3)).size() > 0;
		
		System.out.println("Measure Name is " + isPresent1 + ", " + isPresent2 + " & " + isPresent3);
		
		if(isPresent1 && isPresent2 && isPresent3) {
			String TopNSettingsRem = Header + readElement("locatorPaths.properties","TopNSettingsRem");
			
			driver.findElement(By.xpath(xPath1)).click();
			driver.findElement(By.xpath(TopNSettingsRem)).click();
			
			driver.findElement(By.xpath(xPath2)).click();
			driver.findElement(By.xpath(TopNSettingsRem)).click();
			
			driver.findElement(By.xpath(xPath3)).click();
			driver.findElement(By.xpath(TopNSettingsRem)).click();
			
			printLog("Selected Measure " + measureName1 + ", " + measureName2 + " & " + measureName3 + " from All Measures.");
			
			
			printLog("Clicked on the Right Arrow to move the measure to the Selected Measure.");
			
			String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
			driver.findElement(By.xpath(TopNSettingsApply)).click();
			Thread.sleep(200);
			driver.findElement(By.xpath(TopNSettings)).click();
			
			String TopN_AvailableMeasures = Header + readElement("locatorPaths.properties","TopN_AvailableMeasures");
			List<WebElement> availableMeasures = driver.findElements(By.xpath(TopN_AvailableMeasures));
			
			int sMeasureSize = availableMeasures.size();
			
			for (int x=0; x < sMeasureSize; x++) {
				if (measureName1.equals(availableMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName1 + " is listed under the Selected Measures.");
					flagCount++;
				}
				else if (measureName2.equals(availableMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName2 + " is listed under the Selected Measures.");
					flagCount++;
				}
				else if (measureName3.equals(availableMeasures.get(x).getText())) {
					printLog("Validated Successfully that " + measureName3 + " is listed under the Selected Measures.");
					flagCount++;
				}
			}
		}
		else
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
		
		if (flagCount != 3) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		System.out.println("Flag Count is " + flagCount);
		Assert.assertTrue(flagCount == 3);
			
	}
	
	


	public static void removeAllMeasures (WebDriver driver, String widgetName) throws Exception {
		int flagCount = 0;
		//System.out.println("Inside Check Default Selected Measures - " + measureName1 + ", " + measureName2 + " & " + measureName3);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";
		
		System.out.println("Header is  " + Header);

		String TopNSettingsReset = Header + readElement("locatorPaths.properties","TopNSettingsReset");
		System.out.println("TopNSettingsReset -> " + TopNSettingsReset);
		driver.findElement(By.xpath(TopNSettingsReset)).click();
		org.eigenrisk.testlab.Log.info("Clicked on Reset Button...");
		
		String TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
		List<WebElement> selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
		
		int sMeasureSize = selectedMeasures.size();
		System.out.println("Total Selected Measures are " + sMeasureSize);
		for (int y=0; y < sMeasureSize; y++)
			selectedMeasures.get(y).click();
			
		String TopNSettingsRem = Header + readElement("locatorPaths.properties","TopNSettingsRem");
		driver.findElement(By.xpath(TopNSettingsRem)).click();
		
		String TopNSettingsApply = Header + readElement("locatorPaths.properties","TopNSettingsApply");
		driver.findElement(By.xpath(TopNSettingsApply)).click();
		Thread.sleep(200);
		
		String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		driver.findElement(By.xpath(TopNSettings)).click();
		
		
		//String TopNSettings = readElement("locatorPaths.properties","TopNSettings");
		TopN_SelectedMeasures = Header + readElement("locatorPaths.properties","TopN_SelectedMeasures");
		selectedMeasures = driver.findElements(By.xpath(TopN_SelectedMeasures));
		sMeasureSize = selectedMeasures.size();
		
		System.out.println("Now the Available Measures is only " + sMeasureSize);
		
		if (sMeasureSize != 1) {
			org.eigenrisk.testlab.Log.info("Test Case - FAILED");
			System.out.println("Test Case - FAILED");
		}
		//System.out.println("Flag Count is " + flagCount);
		Assert.assertTrue(sMeasureSize == 1);

	}
	
	public static void setTopNDigit(WebDriver driver, String selectTopN, String widgetName) throws Exception {
		String topNXpath = readElement("locatorPaths.properties","topNXpath");
		driver.findElement(By.xpath(topNXpath)).click();
		
		String defaultTopNDigit = readElement("locatorPaths.properties","defaultTopNDigit");
		System.out.println(defaultTopNDigit);
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//input[@ng-model='model.assetCount']")).click();
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.BACK_SPACE).perform();
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys("4").perform();
		System.out.println(selectTopN);
		keyboardAndMouseActions.sendKeys(Keys.ENTER).perform();
		
		/*
		 * Setting the Top N to 5
		 * driver.findElement(By.xpath("//li[@class='selectAssetCount ng-scope']/a[contains(text(),'5')]")).click();
		 */

		

	}
	
	/*
	public static void setTopNDigit(WebDriver driver, String selectTopN) throws Exception {
		String topNSetDigit = readElement("locatorPaths.properties","topNSetDigit");
		driver.findElements(By.xpath(topNSetDigit)).;
		
		
		
		Actions action = new Actions(driver);
		List<WebElement> topNDigit = driver.findElements(By.xpath(topNSetDigit));

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
	}*/
	
	/*
	public static void removeSingleMeasure (WebDriver driver, Properties prop, String measureName) throws Exception {
		System.out.println("Inside Check Remove Selected Measures" + measureName);
		driver.findElement(By.xpath(prop.getProperty("TopNSettings"))).click();
		//WebElement settingsIcon = driver.findElement(By.xpath(prop.getProperty("TopNSettings")));
//		Boolean isPresent = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures"))).size() > 0;
		
		String xPath = "//span[contains(text(),'" + measureName + "')]";
		Boolean isPresent = driver.findElements(By.xpath(xPath)).size() > 0;
		
		System.out.println(isPresent);
		if(isPresent) {
			System.out.println("Printing 1");
			//driver.findElement(By.xpath(xPath)).click();
			printLog("Selected Measure " + measureName + " from All Measures.");
			
			Thread.sleep(1500);
			System.out.println("Printing 2");
			//driver.findElement(By.xpath(prop.getProperty("TonNSettingsRem"))).click();
			
			List<WebElement> selectedMeasures = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures")));
			
			//System.out.println("~~~~~~~~~~~ " + measureName.equals(selectedMeasures.get(4).getText()));
			System.out.println("Printing 3 " + selectedMeasures.size());
			System.out.println(selectedMeasures.get(1).getText());
			
			int ctr = selectedMeasures.size();
			for(int x=0; x < ctr; x++) {
				System.out.println("Inside For Loop " + measureName + " :: " + selectedMeasures.get(x).getText());
				if(measureName.equals(selectedMeasures.get(x).getText())) {
					System.out.println("Clicking on the list element");
					selectedMeasures.get(x).click();
					//System.out.println("Clicking on the <");
					//driver.findElement(By.xpath("//button[contains(text(),'<')]")).click();
					//validateElementInList(2, "REMOVE", measureName, prop, driver);
					
					driver.findElement(By.xpath("//button[@ng-disabled='model.topNMeasureCount.selected===1 || model.topNMeasureCount.rightMarked < 1']")).click();
					printLog("Clicked on the Left Arrow to remove the measure from the Selected Measure.");
					Thread.sleep(1000);
					
					//validateElementInList(2, "REMOVE", measureName, prop, driver);
					Thread.sleep(3000);
					
					System.out.println("Clicking on Apply Button");
					driver.findElement(By.xpath(prop.getProperty("TopNSettingsApply"))).click();
					break;
				}
						
			}
			
			if (measureName.equals(selectedMeasures.get(4).getText())) {
				printLog("Validated Successfully that the added measure is listed under the Selected Measures.");
				driver.findElement(By.xpath(prop.getProperty("TopNSettingsApply"))).click();
				printLog("Clicked on Apply Button.");
				org.eigenrisk.testlab.Log.info("Test Case - PASSED");
			}
		}
		else
			org.eigenrisk.testlab.Log.info("*** Test Case - FAILED <Measure " + measureName + " is not in the list of Selected Measures.");
		
		
	}
	
	
	public static void removeMultipleMeasure (WebDriver driver, Properties prop, String measureName1, String measureName2) throws Exception {
		System.out.println("Inside Check Default Selected Measures - " + measureName1 + " - " + measureName2);
		driver.findElement(By.xpath(prop.getProperty("TopNSettings"))).click();
		//WebElement settingsIcon = driver.findElement(By.xpath(prop.getProperty("TopNSettings")));
//		Boolean isPresent = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures"))).size() > 0;
		
		
		driver.findElement(By.xpath(prop.getProperty("TopNSettingsReset"))).click();
		printLog("Clicked on Reset Button...");
		String rem1 = "//span[@class='ng-binding'][contains(text(),'" + measureName1 +"')]";
		String rem2 = "//span[@class='ng-binding'][contains(text(),'" + measureName2 +"')]";
		
		driver.findElement(By.xpath(rem1)).click();
		driver.findElement(By.xpath(rem2)).click();
		
		driver.findElement(By.xpath("//button[@ng-disabled='model.topNMeasureCount.selected===1 || model.topNMeasureCount.rightMarked < 1']")).click();
		printLog("Removed " + measureName1 + " and " + measureName2 + " from the Selected Measures");
		
		String xPath1 = "//span[@class='ng-binding'][contains(text(),'" + measureName1 + "')]";
		String xPath2 = "//span[@class='ng-binding'][contains(text(),'" + measureName2 + "')]";
		
		driver.findElement(By.xpath(xPath1)).click();
		driver.findElement(By.xpath(xPath2)).click();
		driver.findElement(By.xpath("//button[contains(text(),'>')]")).click();
		
		
		List<WebElement> selectedMeasures = driver.findElements(By.xpath(prop.getProperty("TopN_SelectedMeasures")));
		if((measureName1 != selectedMeasures.get(0).getText() || measureName1 != selectedMeasures.get(1).getText()) && (measureName2 != selectedMeasures.get(0).getText() || measureName2 != selectedMeasures.get(1).getText())) {
			driver.findElement(By.xpath(prop.getProperty("TopNSettingsApply"))).click();
			org.eigenrisk.testlab.Log.info("Clicked on Apply Button");
			printLog("PASSED->>>>>>>>>>>");
		}
		else {
			org.eigenrisk.testlab.Log.warn("Failed to Remove Measures " + measureName1 + " and " + measureName2);
			printLog("*** Test Case FAILED");
		}
	}
	*/
	
	public static void validateElementInList(int sourceList, String action, String measureName, Properties prop, WebDriver driver, String widgetName) {
		System.out.println("Inside validateElementInList " + sourceList + " - " + action + " - " + measureName);
		
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		
		List<WebElement> selectedMeasures = driver.findElements(By.xpath(Header + prop.getProperty("TopN_SelectedMeasures")));
		List<WebElement> availableMeasures = driver.findElements(By.xpath(Header + prop.getProperty("TopN_AvailableMeasures")));
		
		int selMeasureSize = selectedMeasures.size();
		int availMeasureSize = availableMeasures.size();
		
		System.out.println(selectedMeasures.size());
		System.out.println(availableMeasures.size());
		
		Boolean selMeasures = false, availMeasures = false;
		
		for(int i=0; i < availMeasureSize;i++) {
			System.out.println("Inside 4 Loop " + availableMeasures.get(i).getText());
			if(measureName.equals(availableMeasures.get(i).getText())) {
				availMeasures = true;
				break;
			}
		}
			
		for(int j=0; j < selMeasureSize;j++) {
			if(measureName.equals(selectedMeasures.get(j).getText())) {
				selMeasures = true;
				break;
			}
		}
			
		if (sourceList == 1) {
			if (action.equals("REMOVE")) {
				if(availMeasures == true) {
					org.eigenrisk.testlab.Log.warn("Measure >" + measureName + "> still shows up in the List of Available Measures.");
					org.eigenrisk.testlab.Log.warn("*** Test Case FAILED");
				}
			}
			else if (action.equals("ADD")) {
				if(availMeasures == true) {
					org.eigenrisk.testlab.Log.warn("Measure >" + measureName + "> shows up in the List of Available Measures.");
					org.eigenrisk.testlab.Log.warn("*** Test Case PASSED");
				}
			}
		}
		else {
			if (action.equals("REMOVE")) {
				if(selMeasures == true) {
					org.eigenrisk.testlab.Log.warn("Measure >" + measureName + "> still shows up in the List of Selected Measures.");
					org.eigenrisk.testlab.Log.info("*** Test Case FAILED");
				}
			}
			else if (action.equals("ADD")) {
				if(selMeasures == true) {
					org.eigenrisk.testlab.Log.warn("Measure >" + measureName + "> shows up in the List of Selected Measures.");
					org.eigenrisk.testlab.Log.info("*** Test Case PASSED");
				}
			}
		}
			
		
			
	}
	
	
	public static void Model (WebDriver driver, Properties prop, String widgetName) throws Exception {
		System.out.println("Inside Check Default Selected Measures");
		String Header = "";
		
		if(widgetName.equals("TopN"))
			Header = "//div[@id='nspopover-14']";
		else if(widgetName.equals("accuSummary"))
			Header = "//div[@id='nspopover-8']";

		WebElement settingsIcon = driver.findElement(By.xpath(prop.getProperty("TopNSettings")));
		//Actions action = new Actions(driver);
	    //action.moveToElement(settingsIcon).perform();
	    //action.moveToElement(settingsIcon).click();
		
		
	    
	    List<WebElement> availableMeasures = driver.findElements(By.xpath(Header + prop.getProperty("TopN_SelectedMeasures")));
		Iterator<WebElement> itr1 = availableMeasures.iterator();
		//driver.findElement(By.xpath("//span[contains(text(),'# People')]")).click();
		//driver.findElement(By.xpath("//span[contains(text(),'# Area')]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(text(),'>')]")).click();
	    
	    List<WebElement> selectedMeasures = driver.findElements(By.xpath(Header + prop.getProperty("TopN_SelectedMeasures")));
		Iterator<WebElement> itr = selectedMeasures.iterator();
		System.out.println("Following Measures are currently selected...");
		System.out.println("List Size of Selected Measures is " + selectedMeasures.size());
		
		int ctr = selectedMeasures.size();
		for(int x=0; x < ctr; x++)
			System.out.println(x+1 + ". " + selectedMeasures.get(x).getText());
		
		/*while(itr.hasNext()) {
		   // System.out.println("---> " + itr.next().getText());
			accuSummary[ctr] = itr.next().getText();
			System.out.println(accuSummary[ctr++]);
		}*/
		System.out.println("Clicking on Cancel Button");
		driver.findElement(By.xpath(Header + prop.getProperty("TopNSettingsApply"))).click();
	}
	
	public static void printLog(String message) {
		org.eigenrisk.testlab.Log.info(message);
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
}	
