package com.eigenRisk.functionalities;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.collect.Iterators;

public class DamageModelManager {
	public static String damageModelName;
	public static boolean addGraphRows = false, Mean_1_STD_0 = false;
	
	public static void checkDMM_ToolTipText(WebDriver driver) throws Exception {
		System.out.println("Inside checkDMM_ToolTipText");
		String DMM_ToolTipText = readElement("locatorPaths.properties","DMM_ToolTipText");
		
		//Getting the Tool Tip Text for DMM Icon
		waitForElementToLoad(driver, DMM_ToolTipText);
		String toolTipText = driver.findElement(By.xpath(DMM_ToolTipText)).getText();
		
		System.out.println(DMM_ToolTipText);
		System.out.println("Toop Tip Text is " + toolTipText);
	}
	
	public static void Check_DamageModel_DamageFunction(WebDriver driver) throws Exception {
		//String DMM_ToolTipText = readElement("locatorPaths.properties","DMM_ToolTipText");
		
		//Waiting for the DMM Icon to be Visible and then Clicking on it...
		//waitForElementToLoad(driver, DMM_ToolTipText);
		//Commenting Click since its not enabled
		//driver.findElement(By.xpath(DMM_ToolTipText)).click();
		
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);
		
		//Getting the logo name
		String logoFeatureName = readElement("locatorPaths.properties","LogoFeatureName");
		waitForElementToLoad(driver, logoFeatureName);
		String logoTextdriv = driver.findElement(By.xpath(logoFeatureName)).getText();
		System.out.println("Logo Text is -> " + logoTextdriv);
		
		//Verifying whether the Damage Model Manager been loaded Correctly
		Assert.assertTrue(logoTextdriv.equals("Damage Model Manager")); 
		
		//Verify that there are 2 grids. Damage Model and Damage Function
		String DMM_GridList = readElement("locatorPaths.properties","DMM_GridList");
		List<WebElement> gridRows = driver.findElements(By.xpath(DMM_GridList));
		Assert.assertTrue(gridRows.get(0).getText().equals("Damage Model"));
		Assert.assertTrue(gridRows.get(1).getText().equals("Damage Function"));
		System.out.println("Verified that there are 2 grids. Damage Model and Damage Function");
		
		
		//Verify that the both the grids are collapsible and expandable
		String DMM_DamageModelDown = readElement("locatorPaths.properties","DMM_DamageModelDown");
		List<WebElement> arrowList = driver.findElements(By.xpath(DMM_DamageModelDown));

	
		//collapsible 
		arrowList.get(0).click();
		arrowList.get(1).click();
		
		try {
			String DMM_DamageModelRight = readElement("locatorPaths.properties","DMM_DamageModelRight");
			List<WebElement> arrowList2 = driver.findElements(By.xpath(DMM_DamageModelRight));
			//Boolean b1 = arrowList..
//			System.out.println(arrowList2.size());
			
			if(arrowList2.size() > 1) {
				//Expandable
//				System.out.println(arrowList2.get(0).getAttribute("ng-click"));
//				System.out.println(arrowList2.get(1).getAttribute("ng-click"));
				arrowList.get(0).click();
				arrowList.get(1).click();
				System.out.println("Verified that the both the grids are collapsible and expandable");
			}
		} catch (ElementNotFoundException e){
			System.out.println("Exception is " + e);
		}

	} 
	
	public static void addDamageModelBlankName (WebDriver driver) throws Exception {
		addDamageModel(driver);
		
		//Clicking on Save Button
		String DMM_DamageModelSave = readElement("locatorPaths.properties","DMM_DamageModelSave");
		driver.findElement(By.xpath(DMM_DamageModelSave)).click();
				
		//Validating the Notification Message
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Missing/Incorrect detail(s) in Damage Model"));
		
		String DMM_DamageModelCancel = readElement("locatorPaths.properties","DMM_DamageModelCancel");
		//driver.findElement(By.xpath(DMM_DamageModelCancel)).click();
	}
	
	
	public static void addDamageModelWithName (WebDriver driver, String dmgModelName, String perilName, String sourceName, String callingMethod) throws Exception {
		System.out.println("Inside addDamageModelWithName");
		addDamageModel(driver);
		System.out.println("1...");
		damageModelName = dmgModelName; // + System.currentTimeMillis();
		String DMM_DamageModelName = readElement("locatorPaths.properties","DMM_DamageModelName");
		driver.findElement(By.xpath(DMM_DamageModelName)).sendKeys(damageModelName);
		System.out.println("2...");
		
		//Selecting Peril
		String DMM_DamageModel_Add_Peril = readElement("locatorPaths.properties","DMM_DamageModel_Add_Peril");
		List <WebElement> pList = driver.findElements(By.xpath(DMM_DamageModel_Add_Peril));
		System.out.println("-->" + pList.size());
		
		for(int x=0;x<pList.size();x++) {
			String pName = pList.get(x).getText();
			System.out.println("Peril Name is " + pName);
			
			if (pName.equals(perilName)) {
				System.out.println(x + "th Element " + pName + " matches " + perilName);
				pList.get(x).click();
				System.out.println("Clicked on " + perilName);
				break;
			}
		}
		
		
		//Selecting Source
		System.out.println("Selecting Source");
		String DMM_DamageModel_Add_Source = readElement("locatorPaths.properties","DMM_DamageModel_Add_Source");
		List <WebElement> sList = driver.findElements(By.xpath(DMM_DamageModel_Add_Source));
		System.out.println(sList.size());
		
		for(int x=0;x<sList.size();x++) {
			String sName = sList.get(x).getText();
			System.out.println("Source Name is " + sName);
			
			if (sName.equals(sourceName)) {
				System.out.println(x + "th Element " + sName + " matches " + sourceName);
				pList.get(x).click();
				System.out.println("Clicked on ->" + sourceName);
				break;
			}
		}
		
		String DMM_DamageModelSave = readElement("locatorPaths.properties","DMM_DamageModelSave");
		driver.findElement(By.xpath(DMM_DamageModelSave)).click();
		System.out.println("Clicked on Save Button ->" + DMM_DamageModelSave);
		
		String msg = getNotificationText(driver);
		System.out.println("Notification Message is ->" + msg);
		
		if (callingMethod.equals("ExistingModel")) {
			String expectedMsg = "Damage Model Exists";
			Assert.assertTrue(msg.equals(expectedMsg));
		}
		else {
			String expectedMsg = "Damage Model '" + damageModelName + "' created successfully";
			Assert.assertTrue(msg.equals(expectedMsg));
		}
		System.out.println("Test Case : PASSED");
		
	}
	
	public static void addDamageModelWithoutAnyData (WebDriver driver) throws Exception {
		addDamageModel(driver);
		
		/*String DMM_DamageModelName = readElement("locatorPaths.properties","DMM_DamageModelName");
		driver.findElement(By.xpath(DMM_DamageModelName)).sendKeys(damageModelName);
		*/
		String DMM_DamageModelSave = readElement("locatorPaths.properties","DMM_DamageModelSave");
		driver.findElement(By.xpath(DMM_DamageModelSave)).click();
		
		String msg = getNotificationText(driver);
		
		String expectedMsg = "Missing/Incorrect detail(s) in Damage Model";
		Assert.assertTrue(msg.equals(expectedMsg));
		
	}
	
	
	public static void editDamageModel (WebDriver driver, String dmgName) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		String DMM_DamageModelTable_TR = readElement("locatorPaths.properties", "DMM_DamageModelTable_TR");
		waitForElementToLoad(driver, DMM_DamageModelTable_TR);
		//Thread.sleep(5000);
		
		findDamageModel(driver, dmgName);
		
		
		//Clicking on Edit Button
		String DMM_DamageModelTable_Edit =  readElement("locatorPaths.properties","DMM_DamageModelTable_Edit");
		driver.findElement(By.xpath(DMM_DamageModelTable_Edit)).click();
		
		//Clicking on Drow down for Peril
		String DMM_DM_Edit_Peril = readElement("locatorPaths.properties","DMM_DM_Edit_Peril");
		driver.findElement(By.xpath(DMM_DM_Edit_Peril)).click();
		System.out.println("Clicked on Peril <DropDown>");
		
		//Selecting EarthQuake
		String DMM_DM_Edit_Peril_EarthQuake = readElement("locatorPaths.properties","DMM_DM_Edit_Peril_EarthQuake");
		driver.findElement(By.xpath(DMM_DM_Edit_Peril_EarthQuake)).click();
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.ENTER).perform();
		System.out.println("Clicked on EarthQuake...");
		
		String DMM_DM_Edit_Save = readElement("locatorPaths.properties","DMM_DM_Edit_Save");
		driver.findElement(By.xpath(DMM_DM_Edit_Save)).click();
		System.out.println("Clicked on Save");
		
//		String DMM_DamageModelAdd = readElement("locatorPaths.properties","DMM_DamageModelAdd");
//		driver.findElement(By.xpath(DMM_DamageModelAdd)).click();
		
		String notifMessage = getNotificationText(driver);
		System.out.println(notifMessage);
		
		//Asserting that the notification message is correct
		String expectedMsg = "Damage Model '" + dmgName + "' saved successfully";
		Assert.assertTrue(notifMessage.equals(expectedMsg));
		
		System.out.println("Test Case: PASSED");
		
	}
	
	
	
	
	
	public static void deleteDamageModel (WebDriver driver, String dmgName) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		String DMM_DamageModelTable_TR = readElement("locatorPaths.properties", "DMM_DamageModelTable_TR");
		waitForElementToLoad(driver, DMM_DamageModelTable_TR);
		//Thread.sleep(5000);
		
		findDamageModel(driver, dmgName);
		
		
		//Clicking on Edit Button
		String DMM_DamageModelDel =  readElement("locatorPaths.properties","DMM_DamageModelDel");
		driver.findElement(By.xpath(DMM_DamageModelDel)).click();
		
		//Clicking on Yes Button
		String DMM_DamageModelDelConf =  readElement("locatorPaths.properties","DMM_DamageModelDelConf");
		driver.findElement(By.xpath(DMM_DamageModelDelConf)).click();
		
		//Validating the Notification Message
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Selected Damage Model deleted successfully"));
		
	}
	
	
	public static void addDamageFunction (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DamageModelTable_TR = readElement("locatorPaths.properties", "DMM_DamageModelTable_TR");
		waitForElementToLoad(driver, DMM_DamageModelTable_TR);
		
		System.out.println("Calling findDamageModel() ->" + dmgName);
		findDamageModel(driver, dmgName);
		System.out.println("Done with findDamageModel() ->" + dmgName);
		String DMM_DF_Add = readElement("locatorPaths.properties", "DMM_DF_Add");
		
		//Click the + Button to Add Damage Function
		driver.findElement(By.xpath(DMM_DF_Add)).click();
		
		//System.out.println("---> " + driver.findElement(By.xpath("(//div[@class='col-sm-7']/span)[1]")).getText());
		
		//Checking Damage Model Name
		Thread.sleep(1000);
		String DMM_DF_DamageModelName = readElement("locatorPaths.properties", "DMM_DF_DamageModelName");
		System.out.println("Looking for " + DMM_DF_DamageModelName);
		waitForElementToLoad(driver, DMM_DF_DamageModelName);
		String damageModelName = driver.findElement(By.xpath(DMM_DF_DamageModelName)).getText();
		System.out.println("Damage Model Name (While Adding Damage Function) is showing up as -> " + damageModelName);
		System.out.println("dmgName -> " + damageModelName);
		Assert.assertTrue(dmgName.equals(damageModelName));
		System.out.println("Damage Model is showing up as expected.");

		//Checking Damage Function Number
		String DMM_DF_DamageFunctionNum = readElement("locatorPaths.properties", "DMM_DF_DamageFunctionNum");
		String damageFunctionNumber = driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).getText();
		System.out.println("------------------------");
		//driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).sendKeys("101");
		System.out.println("Damage Function Number is showing up as -> " + damageFunctionNumber);
		//Assert.assertTrue(damageFunctionNumber.equals("0"));
				
		
		
		//Clicking on Save Button
		String DMM_DF_Save = readElement("locatorPaths.properties", "DMM_DF_Save");
		waitForElementToLoad(driver, DMM_DF_Save);
		driver.findElement(By.xpath(DMM_DF_Save)).click();
		
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Damage Function '" + funcNumber + "' has been created."));
		System.out.println("Damage Function '" + funcNumber + "' created Successfully.");
		System.out.println("Test Case : PASSED");
		
	}
	
	
	public static void addDamageFunction_OLD (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DamageModelTable_TR = readElement("locatorPaths.properties", "DMM_DamageModelTable_TR");
		waitForElementToLoad(driver, DMM_DamageModelTable_TR);
		
		System.out.println("Calling findDamageModel() ->" + dmgName);
		findDamageModel(driver, dmgName);
		System.out.println("Done with findDamageModel() ->" + dmgName);
		String DMM_DF_Add = readElement("locatorPaths.properties", "DMM_DF_Add");
		
		//Click the + Button to Add Damage Function
		driver.findElement(By.xpath(DMM_DF_Add)).click();
		
		//Checking Damage Model Name
		Thread.sleep(1000);
		String DMM_DF_DamageModelName = readElement("locatorPaths.properties", "DMM_DF_DamageModelName");
		System.out.println("Looking for " + DMM_DF_DamageModelName);
		waitForElementToLoad(driver, DMM_DF_DamageModelName);
		String damageModelName = driver.findElement(By.xpath(DMM_DF_DamageModelName)).getText();
		System.out.println("Damage Model Name -> " + damageModelName);
		System.out.println("dmgName -> " + damageModelName);
		Assert.assertTrue(dmgName.equals(damageModelName));
		System.out.println("Damage Model is showing up as expected.");
		
		//Checking Damage Function Number
		String DMM_DF_DamageFunctionNum = readElement("locatorPaths.properties", "DMM_DF_DamageFunctionNum");
		String damageFunctionNumber = driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).getText();
		//driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).sendKeys("101");
		System.out.println("Damage Function Number is showing up as -> " + damageFunctionNumber);
		//Assert.assertTrue(damageFunctionNumber.equals("0"));
		
		
		//Checking X Definition
		String DMM_DF_xDefinition  = readElement("locatorPaths.properties", "DMM_DF_DamageFunctionNum");
		List<WebElement> xFDefinition = driver.findElements(By.xpath(DMM_DF_xDefinition));
		int leng = xFDefinition.size();
		System.out.println("Length is " + leng);
		for(int x=0; x<leng; x++) {
			xFDefinition.get(x).getText().equals("Damage Model");
			System.out.println("++++++++++++> " + xFDefinition.get(x).getAttribute("selected"));
			System.out.println(xFDefinition.get(x).getText());
			System.out.println(xFDefinition.get(x));
		}
		
		Thread.sleep(1500);
		//Clicking on Save Button
		String DMM_DF_Save = readElement("locatorPaths.properties", "DMM_DF_Save");
		waitForElementToLoad(driver, DMM_DF_Save);
		driver.findElement(By.xpath(DMM_DF_Save)).click();
		
		
		String notifyMsg = getNotificationText(driver);
		System.out.println(notifyMsg);
		

		String DMM_DF_Table_TR =   readElement("locatorPaths.properties","DMM_DF_Table_TR");
		List<WebElement> TotalRowCount = driver.findElements(By.xpath(DMM_DF_Table_TR));
		System.out.println("Number of Damage Functions are " + TotalRowCount.size());
		
		if (funcNumber == 0) {
			Assert.assertTrue(TotalRowCount.size() == 1);
			Assert.assertTrue(notifyMsg.equals("Damage Function '0' has been created."));
		}
		else if (funcNumber == 1){
			Assert.assertTrue(TotalRowCount.size() == 2);
			Assert.assertTrue(notifyMsg.equals("Damage Function '1' has been created."));
		}
	}
	
	public static void editDamageModelGraphValidData (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String Intensity = "10";
		String Mean = "0.6";
		String STD = "0.3";
		
		editDamageModelGraph (driver, dmgName, funcNumber, Intensity, Mean, STD);
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Successfully saved damage function graph data"));
		
	}
	
	
	public static void AddingDuplicateGraphRows (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		//First Set
		Boolean TestCasePass = false;
		String Intensity = "10";
		String Mean = ".40";
		String STD = "0.20";
		
		addGraphRows = true;
		editDamageModelGraph (driver, dmgName, funcNumber, Intensity, Mean, STD);
		String msg = getNotificationText(driver);
		//Assert.assertTrue(msg.equals("Successfully saved damage function graph data"));
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = false;
		}
	
		//Second Set
		Intensity = "10";
		Mean = "0.40";
		STD = "0.20";
		
		addGraphRow (driver, dmgName, funcNumber, Intensity, Mean, STD);
		msg = getNotificationText(driver);
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		
		Assert.assertTrue(TestCasePass);
		System.out.println("Test Case Passed");
		
		addGraphRows = false;
		
	}
	
	
	
	public static void AddingMoreGraphRows (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		//First Set
		Boolean TestCasePass = false;
		
		
		String Intensity = "10";
		String Mean = ".50";
		String STD = "0.10";
		
		addGraphRows = false;
		editDamageModelGraph (driver, dmgName, funcNumber, Intensity, Mean, STD);
		String msg = getNotificationText(driver);
		//Assert.assertTrue(msg.equals("Successfully saved damage function graph data"));
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = false;
		}
		
		//Second Set
		Intensity = "20";
		Mean = "0.60";
		STD = "0.20";
		addGraphRows = true;
		
		addGraphRow (driver, dmgName, funcNumber, Intensity, Mean, STD);
		msg = getNotificationText(driver);
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = false;
		}
		
		
		
		//Third Set
		Intensity = "30";
		Mean = "0.70";
		STD = "0.30";
		addGraphRows = true;
		
		addGraphRow (driver, dmgName, funcNumber, Intensity, Mean, STD);
		msg = getNotificationText(driver);
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = false;
		}
		

		//Fourth Set
		Intensity = "40";
		Mean = "0.50";
		STD = "0.40";
		addGraphRows = true;
		
		addGraphRow (driver, dmgName, funcNumber, Intensity, Mean, STD);
		msg = getNotificationText(driver);
		if(msg.equals("Successfully saved damage function graph data")) {
			System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = true;
		}
		else {
			System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
			TestCasePass = false;
		}
		
		
		//Adding Extra Rows
		if (DamageModelManager.Mean_1_STD_0 == true) {
			Intensity = "0";
			Mean = "1";
			STD = "0";
			
			addGraphRows = true;
			
			addGraphRow (driver, dmgName, funcNumber, Intensity, Mean, STD);
			msg = getNotificationText(driver);
			if(msg.equals("Successfully saved damage function graph data")) {
				System.out.println("Passed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
				TestCasePass = true;
			}
			else {
				System.out.println("Failed with Intensity -> " + Intensity + ", Mean -> " + Mean + " and STD -> " + STD);
				TestCasePass = false;
			}
			
			DamageModelManager.Mean_1_STD_0 = false;
		}
		
		Assert.assertTrue(TestCasePass);
		
		addGraphRows = false;
		//String msg = getNotificationText(driver);
		//Assert.assertTrue(msg.equals("Successfully saved damage function graph data"));
		
	}
	
	public static void addGraphRow(WebDriver driver, String dmgName, int funcNumber, String Intensity, String Mean, String STD) throws Exception {
		//Click on Damage Model Edit Button
		System.out.println("Clicking on Damage Model Edit Button");
		String DM_Edit = readElement("locatorPaths.properties", "DM_Edit");
		waitForElementToLoad(driver, DM_Edit);
		driver.findElement(By.xpath(DM_Edit)).click();
		System.out.println("Clicked... on Edit");
		
		//Checking whether we need to add rows
		if(addGraphRows == true) {
			String DM_GraphRowsAdd = readElement("locatorPaths.properties", "DM_GraphRowsAdd");
			System.out.println(DM_GraphRowsAdd);
			waitForElementToLoad(driver, DM_GraphRowsAdd);
			driver.findElement(By.xpath(DM_GraphRowsAdd)).click();
		}
			
//		
		String DM_IntensityValue = readElement("locatorPaths.properties", "DM_IntensityValue");
		System.out.println(DM_IntensityValue);
		waitForElementToLoad(driver, DM_IntensityValue);
//		driver.findElement(By.xpath(DM_IntensityValue)).sendKeys("10");
		
		
		waitForElementToLoad(driver, "(//button[@class='btn btn-default dropdown-toggle bgwhite event-catalog-select ng-binding'])[5]");
		driver.findElement(By.xpath("(//button[@class='btn btn-default dropdown-toggle bgwhite event-catalog-select ng-binding'])[5]")).click();
		
		Thread.sleep(500);
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys("10").perform();
		Thread.sleep(4000);
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(Keys.BACK_SPACE).perform();
		keyboardAndMouseActions.sendKeys(Intensity).perform();
		Thread.sleep(2000);
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		//keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(Mean).perform();
		Thread.sleep(2000);
		
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		//keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(STD).perform();
		Thread.sleep(2000);
		
		String DM_Preview = readElement("locatorPaths.properties", "DM_Preview");
		System.out.println(DM_Preview);
		waitForElementToLoad(driver, DM_Preview);
		driver.findElement(By.xpath(DM_Preview)).click();
		Thread.sleep(5000);

		String DM_Save = readElement("locatorPaths.properties", "DM_Save");
		System.out.println(DM_Save);
		waitForElementToLoad(driver, DM_Save);
		driver.findElement(By.xpath(DM_Save)).click();
				
	}
	
	
	public static void editDamageModelGraphInValidData (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String Intensity = "10";
		String Mean = "0.4";
		String STD = "1";
		
		editDamageModelGraph (driver, dmgName, funcNumber, Intensity, Mean, STD);
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Missing/Invalid numbers in Graph data grid."));
		
	}
	
	
	public static void deleteDamageFunction (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DF_Table_TR = readElement("locatorPaths.properties", "DMM_DF_Table_TR");
		waitForElementToLoad(driver, DMM_DF_Table_TR);
		
		//Selecting the respecting damage model
		findDamageModel(driver, dmgName);
		//Thread.sleep(1500);
		
		//Selecting the respecting damage function
		findDamageFunction(driver, funcNumber);
		
		String DM_Delete = readElement("locatorPaths.properties", "DM_Delete");
		waitForElementToLoad(driver, DM_Delete);
		driver.findElement(By.xpath(DM_Delete)).click();
		
		String DM_Delete_Save = readElement("locatorPaths.properties", "DM_Delete_Save");
		waitForElementToLoad(driver, DM_Delete_Save);
		driver.findElement(By.xpath(DM_Delete_Save)).click();
		
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Selected Damage Function deleted successfully"));
	}
	
	
	public static void CopyPasteGraphDataFromExcel (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DF_Table_TR = readElement("locatorPaths.properties", "DMM_DF_Table_TR");
		waitForElementToLoad(driver, DMM_DF_Table_TR);
		
		//Selecting the respecting damage model
		findDamageModel(driver, dmgName);
		//Thread.sleep(1500);
		
		//Selecting the respecting damage function
		findDamageFunction(driver, funcNumber);
		
		
		//Click on Damage Model Edit Button
		System.out.println("Clicking on Damage Model Edit Button");
		String DM_Edit = readElement("locatorPaths.properties", "DM_Edit");
		waitForElementToLoad(driver, DM_Edit);
		driver.findElement(By.xpath(DM_Edit)).click();
		System.out.println("Clicked... on Edit");
		
		//Clicking on paste graph from excel
		String DM_PasteGraph = readElement("locatorPaths.properties", "DM_PasteGraph");
		waitForElementToLoad(driver, DM_PasteGraph);
		driver.findElement(By.xpath(DM_PasteGraph)).click();
		
		
		//Pasting on Text Area
		String DM_PasteGraphTArea = readElement("locatorPaths.properties", "DM_PasteGraphTArea");
		waitForElementToLoad(driver, DM_PasteGraphTArea);
		WebElement gTxtArea = driver.findElement(By.xpath(DM_PasteGraphTArea));
		gTxtArea.sendKeys("10 0.1 0.1\n20 0.2 0.2\n30 0.3 0.3");
		
		
		//Click on Update Button
		String DM_PasteGraphUpdate = readElement("locatorPaths.properties", "DM_PasteGraphUpdate");
		waitForElementToLoad(driver, DM_PasteGraphUpdate);
		driver.findElement(By.xpath(DM_PasteGraphUpdate)).click();
		
		//Clicking on Preview
		String DM_Preview = readElement("locatorPaths.properties", "DM_Preview");
		System.out.println(DM_Preview);
		waitForElementToLoad(driver, DM_Preview);
		driver.findElement(By.xpath(DM_Preview)).click();
		Thread.sleep(5000);

		//Clicking on Save
		String DM_Save = readElement("locatorPaths.properties", "DM_Save");
		System.out.println(DM_Save);
		waitForElementToLoad(driver, DM_Save);
		driver.findElement(By.xpath(DM_Save)).click();
			
		String msg = getNotificationText(driver);
		Assert.assertTrue(msg.equals("Successfully saved damage function graph data"));
		System.out.println("Test Case : PASSED");
		
	}
	
	public static void editDamageModelGraph (WebDriver driver, String dmgName, int funcNumber, String Intensity, String Mean, String STD) throws Exception {
		System.out.println("Inside Edit Damage Model Graph...");
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DF_Table_TR = readElement("locatorPaths.properties", "DMM_DF_Table_TR");
		waitForElementToLoad(driver, DMM_DF_Table_TR);
		
		//Selecting the respecting damage model
		findDamageModel(driver, dmgName);
		System.out.println("Clicked on Damage Model -> " + dmgName);
		Thread.sleep(1500);
		
		//Selecting the respecting damage function
		findDamageFunction(driver, funcNumber);
		
		
		//Click on Damage Model Edit Button
		System.out.println("Clicking on Damage Model Edit Button");
		String DM_Edit = readElement("locatorPaths.properties", "DM_Edit");
		waitForElementToLoad(driver, DM_Edit);
		driver.findElement(By.xpath(DM_Edit)).click();
		System.out.println("Clicked... on Edit");
		
		//Checking whether we need to add rows
		System.out.println("addGraphRows status is -> " + addGraphRows);
		if(addGraphRows == true) {
			String DM_GraphRowsAdd = readElement("locatorPaths.properties", "DM_GraphRowsAdd");
			System.out.println(DM_GraphRowsAdd);
			waitForElementToLoad(driver, DM_GraphRowsAdd);
			driver.findElement(By.xpath(DM_GraphRowsAdd)).click();
			System.out.println("Clicked on + Button to Add Graph Rows");
		}
			
//		
		String DM_IntensityValue = readElement("locatorPaths.properties", "DM_IntensityValue");
		System.out.println(DM_IntensityValue);
		waitForElementToLoad(driver, DM_IntensityValue);
//		driver.findElement(By.xpath(DM_IntensityValue)).sendKeys("10");

		waitForElementToLoad(driver, "(//button[@class='btn btn-default dropdown-toggle bgwhite event-catalog-select ng-binding'])[5]");
		driver.findElement(By.xpath("(//button[@class='btn btn-default dropdown-toggle bgwhite event-catalog-select ng-binding'])[5]")).click();
		
		
		Thread.sleep(500);
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys("10").perform();
		Thread.sleep(4000);
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(Keys.BACK_SPACE).perform();
		keyboardAndMouseActions.sendKeys(Intensity).perform();
		Thread.sleep(2000);
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		//keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(Mean).perform();
		Thread.sleep(2000);
		
		
		keyboardAndMouseActions.sendKeys(Keys.TAB).perform();
		//keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys(Keys.DELETE).perform();
		keyboardAndMouseActions.sendKeys(STD).perform();
		Thread.sleep(2000);
		
		String DM_Preview = readElement("locatorPaths.properties", "DM_Preview");
		System.out.println(DM_Preview);
		waitForElementToLoad(driver, DM_Preview);
		driver.findElement(By.xpath(DM_Preview)).click();
		Thread.sleep(5000);

		String DM_Save = readElement("locatorPaths.properties", "DM_Save");
		System.out.println(DM_Save);
		waitForElementToLoad(driver, DM_Save);
		driver.findElement(By.xpath(DM_Save)).click();
		
		
	}
	
	public static void editDamageFunctionNumberForOneToThree (WebDriver driver, String dmgName, int funcNumber) throws Exception {
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		waitForElementToLoad(driver, DMM_Link);
		driver.findElement(By.xpath(DMM_Link)).click();
		
		/*String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);*/
		
		String DMM_DF_Table_TR = readElement("locatorPaths.properties", "DMM_DF_Table_TR");
		waitForElementToLoad(driver, DMM_DF_Table_TR);
		
		//Selecting the respecting damage model
		findDamageModel(driver, dmgName);
		Thread.sleep(1500);
		
		//Selecting the respecting damage function
		findDamageFunction(driver, funcNumber);
		String DMM_DF_Edit = readElement("locatorPaths.properties", "DMM_DF_Edit");
		System.out.println(DMM_DF_Edit);
		
		//Click the + Button to Add Damage Function
		driver.findElement(By.xpath(DMM_DF_Edit)).click();
		Thread.sleep(1500);
		
		String DMM_DF_Edit_FunctionNum = readElement("locatorPaths.properties", "DMM_DF_Edit_FunctionNum");
		waitForElementToLoad(driver, DMM_DF_Edit_FunctionNum);
		System.out.println("Element -> " + DMM_DF_Edit_FunctionNum);
		
		driver.findElement(By.xpath(DMM_DF_Edit_FunctionNum)).click();
//		driver.findElement(By.xpath(DMM_DF_Edit_FunctionNum)).sendKeys("3");
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys("3");
		driver.findElement(By.xpath(DMM_DF_Edit_FunctionNum)).sendKeys("3");
		Thread.sleep(1000);
		
		//Clicking on Save Button
		String DMM_DF_Edit_Save = readElement("locatorPaths.properties", "DMM_DF_Edit_Save");
		driver.findElement(By.xpath(DMM_DF_Edit_Save)).click();
		
		String msg = getNotificationText(driver);
		
		Assert.assertTrue(msg.equals("Damage Function '3' has been saved."));
		System.out.println("Damage Function '" + funcNumber + "' saved Successfully.");
		System.out.println("Test Case : PASSED");
		
		
/*		
		String DMM_DF_Edit_FunctionNum = driver.findElement(By.xpath(DMM_DF_Edit_FunctionNum)).getText();
		waitForElementToLoad(driver, DMM_DF_Edit_FunctionNum);
		driver.findElement(By.xpath(DMM_DF_Edit_FunctionNum)).sendKeys("101");*/
		
		/*
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.CONTROL,"a").perform();
		keyboardAndMouseActions.sendKeys("3");
		
		//Clicking on Save Button
		String DMM_DF_Save = readElement("locatorPaths.properties", "DMM_DF_Save");
		driver.findElement(By.xpath(DMM_DF_Save)).click();
		*/
		
	/*	
		//Checking Damage Model Name
		String DMM_DF_DamageModelName = readElement("locatorPaths.properties", "DMM_DF_DamageModelName");
		String damageModelName = driver.findElement(By.xpath(DMM_DF_DamageModelName)).getText();
		System.out.println("Damage Model Name is showing up as -> " + damageModelName);
		Assert.assertTrue(dmgName.equals(damageModelName));
		
		//Checking Damage Function Number
		String DMM_DF_DamageFunctionNum = readElement("locatorPaths.properties", "DMM_DF_DamageFunctionNum");
		String damageFunctionNumber = driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).getText();
		//driver.findElement(By.xpath(DMM_DF_DamageFunctionNum)).sendKeys("101");
		System.out.println("Damage Function Number is showing up as -> " + damageFunctionNumber);
		//Assert.assertTrue(damageFunctionNumber.equals("0"));
		
		
		
		//Clicking on Save Button
		String DMM_DF_Save = readElement("locatorPaths.properties", "DMM_DF_Save");
		driver.findElement(By.xpath(DMM_DF_Save)).click();
		
		
		String notifyMsg = getNotificationText(driver);
		System.out.println(notifyMsg);
		

		DMM_DF_Table_TR =   readElement("locatorPaths.properties","DMM_DF_Table_TR");
		List<WebElement> TotalRowCount = driver.findElements(By.xpath(DMM_DF_Table_TR));
		System.out.println("Number of Damage Functions are " + TotalRowCount.size());
		
		if (funcNumber == 0) {
			Assert.assertTrue(TotalRowCount.size() == 1);
			Assert.assertTrue(notifyMsg.equals("Damage Function '0' has been created."));
		}
		else if (funcNumber == 1){
			Assert.assertTrue(TotalRowCount.size() == 2);
			Assert.assertTrue(notifyMsg.equals("Damage Function '1' has been created."));
		}
		*/
		
	}
	
	
	
	
	public static void findDamageModel (WebDriver driver, String dmgName) throws Exception {
		
		String DMM_DamageModelTable_TR = readElement("locatorPaths.properties","DMM_DamageModelTable_TR");
		String DMM_DamageModelTable_TD = readElement("locatorPaths.properties","DMM_DamageModelTable_TD");
		
		System.out.println(DMM_DamageModelTable_TR);
		System.out.println(DMM_DamageModelTable_TD);
		
		List<WebElement> damageModelRowList = driver.findElements(By.xpath(readElement("locatorPaths.properties","DMM_DamageModelTable_TR")));
		int ctr = 0;
		Iterator<WebElement> itr = damageModelRowList.iterator();
		
		int size = Iterators.size(itr);
		String[] accuSummary = new String [size];
		System.out.println("Number of Damage Models listed is " + size);
		String lText;
		
		//----
		
		String DMM_DamageModelTable = readElement("locatorPaths.properties","DMM_DamageModelTable");
		WebElement table = driver.findElement(By.xpath(DMM_DamageModelTable));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		
		int row1 = 0;
		Boolean status;
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    //System.out.println(num++ + "--------------------------------");
		    int col1 = 0;
		    status = false;
		    for (WebElement cell : cells) {
		        //System.out.println("content >>   " + cell.getText());
		        String s1 = cell.getText().trim();
				status = s1.startsWith(dmgName);
				if(status)
					break;
				col1++;
		    }
		    //System.out.println("CTR1 -> " + row1 + " - " + col1);
		    if(status)
				break;
		    row1++;
		}
		String DMM_DamageModelTable_Radio = readElement("locatorPaths.properties","DMM_DamageModelTable_Radio");
		List<WebElement> allRadios = driver.findElements(By.xpath(DMM_DamageModelTable_Radio));
		
		if(row1 != 1)
			allRadios.get(row1-1).click();
		
		Thread.sleep(500);
		
	}
	
	
public static void findDamageFunction (WebDriver driver, int funcNumber) throws Exception {
		
		String DMM_DF_Table_TR = readElement("locatorPaths.properties","DMM_DF_Table_TR");
		String DMM_DF_Table_TD = readElement("locatorPaths.properties","DMM_DF_Table_TD");
		
		System.out.println(DMM_DF_Table_TR);
		System.out.println(DMM_DF_Table_TD);
		
		List<WebElement> damageFuncRowList = driver.findElements(By.xpath(readElement("locatorPaths.properties","DMM_DF_Table_TR")));
		int ctr = 0;
		Iterator<WebElement> itr = damageFuncRowList.iterator();
		System.out.println("DamageFunctionRowCount " + damageFuncRowList.size());
		
		int size = Iterators.size(itr);
		System.out.println(size);
		
		
		String[] accuSummary = new String [size];
		System.out.println("Number of Damage Functions listed is " + size);
		System.out.println(DMM_DF_Table_TR);
		String lText;
		

		String DMM_DF_Table = readElement("locatorPaths.properties","DMM_DF_Table");
		WebElement table = driver.findElement(By.xpath(DMM_DF_Table));

		// Now get all the TR elements from the table
		int num = 1;
		System.out.println("Inside printTable :- " + table.getSize());
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		
		int row1 = 0;
		Boolean status;
		for (WebElement row : allRows) {
		    List<WebElement> cells = row.findElements(By.tagName("td"));
		    //System.out.println(num++ + "--------------------------------");
		    int col1 = 0;
		    status = false;
		    for (WebElement cell : cells) {
		        System.out.println("Table Data" + cell.getText());
		        String s1 = cell.getText().trim();
				status = s1.equals(String.valueOf(funcNumber));
				if(col1 == 2 && status)
					break;
				col1++;
		    }
		    System.out.println("..................................................");
		    //System.out.println("CTR1 -> " + row1 + " - " + col1);
		    if(status)
				break;
		    row1++;
		}
		String DMM_DF_Table_Radio = readElement("locatorPaths.properties","DMM_DF_Table_Radio");
		List<WebElement> allRadios = driver.findElements(By.xpath(DMM_DF_Table_Radio));
		
		if(row1 != 1)
			allRadios.get(row1-1).click();
		
		Thread.sleep(500);
		
	}
	
	
	
	
	public static void addDamageModel (WebDriver driver) throws Exception {
		String DMM_ToolTipText = readElement("locatorPaths.properties","DMM_ToolTipText");
		
		//Waiting for the DMM Icon to be Visible and then Clicking on it...
		waitForElementToLoad(driver, DMM_ToolTipText);
		
		//Commenting Click since its not enabled
		//driver.findElement(By.xpath(DMM_ToolTipText)).click();
		
		String DMM_Link = readElement("locatorPaths.properties", "DMM_Link");
		driver.findElement(By.xpath(DMM_Link)).click();
		
		String DMM_DamageModelAdd = readElement("locatorPaths.properties", "DMM_DamageModelAdd");
		waitForElementToLoad(driver, DMM_DamageModelAdd);
		
		driver.findElement(By.xpath(DMM_DamageModelAdd)).click();
		
		/*
		String notifMessage = getNotificationText(driver);
		System.out.println(notifMessage);*/
		
	}
	
	
	//String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
	public static String readElement(String fileName, String fieldName) throws Exception {
		FileReader propertyFile = new FileReader(fileName);
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		String fieldValue = configProperty.getProperty(fieldName);
		propertyFile.close();
		return fieldValue;
		
	}
	
	public static String getNotificationText(WebDriver driver) {
		Boolean present;
		String msg = "";
		System.out.println("Inside getNotificationText");
		while(true) {
			try {
				driver.findElement(By.xpath("//span[@class='noty_text']"));
	            WebElement WE = driver.findElement(By.xpath("//span[@class='noty_text']"));
	            msg = WE.getText();
	            System.out.println("Message is -> " + msg);
	            String raw_message = msg;
	            if(msg.length() > 0)
	                   break;
			} catch (NoSuchElementException e) {
				present = false;
			}
		}
		return msg;
	}
	
	public static void waitForElementToLoad(WebDriver driver, String elementXPath) throws Exception {
		//String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
		int ctr = 0;
		while(true) {
			ctr++;
			try {
				if (ctr > 200) {
					System.out.println("Wait for Element Timed Out!!!");
					System.out.println("XPath -> " + elementXPath);
					break;
				}
				//driver.findElement(By.xpath("elementXPath"));
				Boolean isPresent = driver.findElements(By.xpath(elementXPath)).size() > 0;
				if (isPresent) {
					System.out.println("Element Visible...");
					System.out.println("XPath -> " + elementXPath);
					break;
				}
				
				
	    	  }
	    	  catch (ElementNotFoundException ENF) {
	    		  System.out.println(ENF);
	    	  }
		}
	}
	
	

}
