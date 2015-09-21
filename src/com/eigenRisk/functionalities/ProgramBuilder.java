package com.eigenRisk.functionalities;

import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.eigenrisk.commonutilities.Fluent_Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class ProgramBuilder {
	public static String progName;
	
	public static void clickProgramBuilder(WebDriver driver) throws Exception {
		String PB_Link = readElement("locatorPaths.properties","PB_Link");
		driver.findElement(By.xpath(PB_Link)).click();
		
		System.out.println("Clicked on PB Link");
		String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
		waitForElementToLoad(driver, PB_ProgramName);
		Thread.sleep(500);
		waitForElementToLoad(driver, PB_ProgramName);
		
		System.out.println("Wait..");
		
		String FeatureName = readElement("locatorPaths.properties","FeatureName");
		waitForElementToLoad(driver, FeatureName);
		FeatureName = driver.findElement(By.xpath(FeatureName)).getText();
		
		if(FeatureName.equals("Program Builder"))
			System.out.println("Feature Name is correctly showing up as Program Builder");
		else
			System.out.println("Feature Name is wrong, It should be Program Builder instead of " + FeatureName);
		Assert.assertTrue(FeatureName.equals("Program Builder"));
		/*
		String programName = driver.findElement(By.xpath(PB_ProgramName)).getText();
		System.out.println("Program Name is " + programName);
		Assert.assertTrue(programName.equals("New Program 1"));
		System.out.println("Verified that the Program Builder Page is loaded Successfully");
		*/
	}
	
	
	
	
	public static void checkProgramName(WebDriver driver, String proName) throws Exception {
		String PB_ProgramName = readElement("locatorPaths.properties","PB_ProgramName");
		waitForElementToLoad(driver, PB_ProgramName);
		System.out.println(PB_ProgramName);
		
		String programName = driver.findElement(By.xpath(PB_ProgramName)).getText();
		System.out.println("Program Name is " + programName);
		Assert.assertTrue(programName.startsWith(proName));
		System.out.println("Verified that the Program Name is showing up correctly as " + proName);
		
		
	}
	
	
	public static void createLayer (WebDriver driver, String x, String y) throws Exception {
		//Clicking on Add Layer Button
		String PB_AddLayerButton = readElement("locatorPaths.properties","PB_AddLayerButton");
		waitForElementToLoad(driver, PB_AddLayerButton);
		System.out.println("PB_AddLayerButton - > " + PB_AddLayerButton);
		driver.findElement(By.xpath(PB_AddLayerButton)).click();
		Thread.sleep(100);
				
		//Entering 50 in the Text Box for Add Layer Excess
		String PB_AddLayerExcess = readElement("locatorPaths.properties","PB_AddLayerExcess");
		waitForElementToLoad(driver, PB_AddLayerExcess);
		driver.findElement(By.xpath(PB_AddLayerExcess)).sendKeys(x);
				
		//Entering 50 in the Text Box for Add Layer Low
		String PB_AddLayerLow = readElement("locatorPaths.properties","PB_AddLayerLow");
		driver.findElement(By.xpath(PB_AddLayerLow)).sendKeys(y);
		
		String PB_AddButton = readElement("locatorPaths.properties","PB_AddButton");
		driver.findElement(By.xpath(PB_AddButton)).click();
		Thread.sleep(100);		
	}
	
	public static void addLayer (WebDriver driver) throws Exception {
		//Create Layer
		createLayer(driver, "25", "75");
		
		//Clicking on Add Button
		String PB_AddButton = readElement("locatorPaths.properties","PB_AddButton");
		driver.findElement(By.xpath(PB_AddButton)).click();
		System.out.println("Layer Successfully Added ");
		Thread.sleep(2000);
		
		//Saving Program
		enterNewProgramName(driver, "LayerAdd_");
		
		
	}
	
	public static void removeNewProgramName(WebDriver driver) throws Exception {
		String PB_RemoveNewProg = readElement("locatorPaths.properties","PB_RemoveNewProg");
		driver.findElement(By.xpath(PB_RemoveNewProg)).click();
		System.out.println("Removed New Program 1");
		Thread.sleep(100);
		driver.findElement(By.xpath("//input[@ng-model='model.programName']")).click();
	}
	
	public static void enterNewProgramName(WebDriver driver, String programName) throws Exception {
		programName = programName + System.currentTimeMillis();
		progName = programName;
		
		driver.findElement(By.xpath("//input[@ng-enter='saveProgramToServerWrapper()'][@placeholder='Enter Program Name']")).click();
		System.out.println("Inside Enter Program Name");
		Actions keyboardAndMouseActions = new Actions(driver);
		for(int x=0; x<20;x++) {
			keyboardAndMouseActions.sendKeys(Keys.BACK_SPACE).perform();
		}
		keyboardAndMouseActions.sendKeys(programName).perform();;
		keyboardAndMouseActions.sendKeys(Keys.ENTER).perform();;
		 
		String msg = getNotificationText(driver);
		System.out.println(msg);
		
		System.out.println("Program Saved Successfully by the name " + programName);
		//Assert.assertTrue(msg.equals("Program " + programName + " successfully saved."));
	}
	
	
	public static void shareProgram(WebDriver driver, String programName, String emailID) throws Exception {
		System.out.println("____________________Share Program__________________");
		
		String PB_ShareProgram = readElement("locatorPaths.properties","PB_ShareProgram");
		driver.findElement(By.xpath(PB_ShareProgram)).click();
		System.out.println("Clicked on Share Program...");
		
		
		String PB_ShareMailList = readElement("locatorPaths.properties","PB_ShareMailList");
		List<WebElement> eMailIDs = driver.findElements(By.xpath(PB_ShareMailList));
		int listSize = eMailIDs.size();
		
		Thread.sleep(100);

		for(int x=0; x < listSize; x++) {
			String exposureName = eMailIDs.get(x).getText();
			System.out.println(x+1 + ". " + exposureName);
			if (emailID.equals(eMailIDs.get(x).getText().trim())) {
				System.out.println(emailID + " matches " + eMailIDs.get(x).getText().trim());
				
				String PB_eMailCheckBox = readElement("locatorPaths.properties","PB_eMailCheckBox");
				List<WebElement> eMailCheckBox = driver.findElements(By.xpath(PB_eMailCheckBox));
				System.out.println("EmailId # -> " + eMailIDs.size() + " eMail CheckBox# -> " + eMailCheckBox);
				//eMailCheckBox.get(x).click();
				WebElement selectExpo = eMailCheckBox.get(x);
				
				Actions actions = new Actions(driver);
				Thread.sleep(1500);
				actions.moveToElement(selectExpo).perform();
				System.out.println("Index is " + x);
				eMailCheckBox.get(x).click();
				
				
				String PB_ShareButton = readElement("locatorPaths.properties","PB_ShareButton");
				//Clicking on Share Button
				System.out.println("Program Name is " + progName);
				driver.findElement(By.xpath(PB_ShareButton)).click();
				String msg = getNotificationText(driver);
				System.out.println("Message is " + msg);
				Assert.assertTrue(msg.equals("Program shared successfully"));
				
				break;
			}
		}
		
	}
	
	
/*	
	public static void shareProgram(WebDriver driver, String programName, String emailID) throws Exception {
		//Click on Share Program
		String PB_ShareProgram = readElement("locatorPaths.properties","PB_ShareProgram");
		driver.findElement(By.xpath(PB_ShareProgram)).click();
		System.out.println("Clicked on Share Program...");
		
		String PB_ShareMailList = readElement("locatorPaths.properties","PB_ShareMailList");
		List<WebElement> eMailIDs = driver.findElements(By.xpath(PB_ShareMailList));
		
		String PB_eMailCheckBox = readElement("locatorPaths.properties","PB_eMailCheckBox");
		List<WebElement> eMailCheckBox = driver.findElements(By.xpath(PB_eMailCheckBox));
		System.out.println("EmailId # -> " + eMailIDs.size() + " eMail CheckBox# -> " + eMailCheckBox);
		
		Actions actions = new Actions(driver);
		Thread.sleep(100);
		int rowSize = eMailIDs.size();
		for(int x=0; x < rowSize; x++) {
			System.out.println(eMailIDs.get(x));
			System.out.println(x+1 + ". " + eMailIDs.get(x).getText() + " -----------> " + emailID);
			if (emailID.equals(eMailIDs.get(x).getText().trim())) {
				System.out.println(emailID + " matches " + eMailIDs.get(x).getText().trim());
				//eMailCheckBox.get(x).click();
				WebElement selectExpo = eMailCheckBox.get(x);
				
				actions.moveToElement(selectExpo).perform();
				System.out.println("Index is " + x);
				eMailCheckBox.get(x).click();
				break;
			}
		}		
		String PB_ShareButton = readElement("locatorPaths.properties","PB_ShareButton");
		//Clicking on Share Button
		System.out.println("Program Name is " + progName);
		driver.findElement(By.xpath(PB_ShareButton)).click();
		String msg = getNotificationText(driver);
		System.out.println("Message is " + msg);
		//Assert.assertTrue(msg.equals("Program shared successfully"));
	}
	
	*/
	
	
	public static void validateSharedProgram(WebDriver driver) throws Exception {
		String PB_SelectProgram = readElement("locatorPaths.properties","PB_SelectProgram");
		
		//Clicking on Select Program
		driver.findElement(By.xpath(PB_SelectProgram)).click();
		
		String PB_SavedProgram = readElement("locatorPaths.properties","PB_SavedProgram");
		List<WebElement> savedProgram = driver.findElements(By.xpath(PB_SavedProgram));
		
		int rowSize = savedProgram.size();
		System.out.println("Row Size is " + rowSize);
		String s1="";
		
		for(int x=0; x < rowSize; x++) {
			System.out.println(x+1 + ". " + savedProgram.get(x).getText());
			
			s1 = savedProgram.get(x).getText().trim();
			Boolean status = s1.startsWith(progName);
			System.out.println("Status is " + status);
			
			if (status) {
				System.out.println("Saved Program is Equal to " + savedProgram.get(x).getText());
				savedProgram.get(x).click();
				break;
			}
		}
		System.out.println("Prog Name -> " + progName);
		System.out.println("S1 -> " + s1);
		Assert.assertTrue(s1.startsWith(progName));
		//checkProgramName(driver, progName);
		System.out.println(progName + " is listed.");
	}
	
	
	public static void deleteProgram(WebDriver driver) throws Exception {
		String PB_DeleteProgram = readElement("locatorPaths.properties","PB_DeleteProgram");
		List<WebElement> delProg = driver.findElements(By.xpath(PB_DeleteProgram));
		
		String PB_SelectProgram = readElement("locatorPaths.properties","PB_SelectProgram");
		//Clicking on Select Program
		driver.findElement(By.xpath(PB_SelectProgram)).click();
		
		String PB_SavedProgram = readElement("locatorPaths.properties","PB_SavedProgram");
		List<WebElement> savedProgram = driver.findElements(By.xpath(PB_SavedProgram));
		
		int rowSize = savedProgram.size();
		System.out.println("Row Size is " + rowSize);
		
		for(int x=0; x < rowSize; x++) {
			System.out.println(x+1 + ". " + savedProgram.get(x).getText());
			
			String s1 = savedProgram.get(x).getText().trim();
			Boolean status = s1.startsWith(progName);
			System.out.println("Status is " + status);
			
			if (status) {
				System.out.println("Saved Program is Equal to " + savedProgram.get(x).getText());
				//savedProgram.get(x).click();
				delProg.get(x).click();
				System.out.println("Deleting Program " + savedProgram.get(x).getText());
				
				//Clicking on Yes Button to confirm the delete
				String PB_DeleteProgramConf = readElement("locatorPaths.properties","PB_DeleteProgramConf");
				System.out.println(PB_DeleteProgramConf);
				
				PB_DeleteProgramConf = PB_DeleteProgramConf + "'" + progName + "'" + ")]/../div[2]/button[1]";
				System.out.println(PB_DeleteProgramConf);
				
				waitForElementToLoad(driver, PB_DeleteProgramConf);
				driver.findElement(By.xpath(PB_DeleteProgramConf)).click();
				System.out.println("Deleted Successfully...");
				break;

			}
		}
	}
	
	
	public static void detailView(WebDriver driver) throws Exception {
		String PB_DetailView = readElement("locatorPaths.properties","PB_DetailView");
		String PB_LayerEdit = readElement("locatorPaths.properties","PB_LayerEdit");
		
		//Clicking on Detail View
		driver.findElement(By.xpath(PB_DetailView)).click();
		
		waitForElementToLoad(driver, PB_LayerEdit);
		
		Boolean isPresent = false;
		try {
			driver.findElement(By.xpath(PB_LayerEdit));
			isPresent = driver.findElements(By.xpath(PB_LayerEdit)).size() > 0;
		}
		catch (ElementNotFoundException eF) {
			System.out.println("Edit Button is Hidden... " + eF);
		}
		
		System.out.println("Element Edit is " + isPresent);
		
	}
	
	
	public static void deleteAllProgram(WebDriver driver) throws Exception {
		String PB_DeleteProgram = readElement("locatorPaths.properties","PB_DeleteProgram");
		List<WebElement> delProg = driver.findElements(By.xpath(PB_DeleteProgram));
		
		String PB_SelectProgram = readElement("locatorPaths.properties","PB_SelectProgram");
		//Clicking on Select Program
		//driver.findElement(By.xpath(PB_SelectProgram)).click();
		
		String PB_SavedProgram = readElement("locatorPaths.properties","PB_SavedProgram");
		List<WebElement> savedProgram = driver.findElements(By.xpath(PB_SavedProgram));
		
		int rowSize = savedProgram.size();
		System.out.println("Row Size is -> " + rowSize);

		while(rowSize > 1) {
			System.out.println("Inside While");
			PB_SelectProgram = readElement("locatorPaths.properties","PB_SelectProgram");
			waitForElementToLoad(driver, PB_SelectProgram);
			System.out.println("1");
			
			//Clicking on Select Program
			driver.findElement(By.xpath(PB_SelectProgram)).click();
			
			System.out.println("2");
			PB_SavedProgram = readElement("locatorPaths.properties","PB_SavedProgram");
			waitForElementToLoad(driver, PB_SavedProgram);
			savedProgram = driver.findElements(By.xpath(PB_SavedProgram));
			
			System.out.println("3");
			rowSize = savedProgram.size();
			System.out.println("Row Size is -> " + rowSize);
			
			delProg = driver.findElements(By.xpath(PB_DeleteProgram));
			System.out.println(delProg.get(0).getText());
			delProg.get(0).click();
			System.out.println("Deleting Program " + savedProgram.get(0).getText());
			progName = savedProgram.get(0).getText();
			
			System.out.println("4");
			//Clicking on Yes Button to confirm the delete
			String PB_DeleteProgramConf = readElement("locatorPaths.properties","PB_DeleteProgramConf");
			System.out.println(PB_DeleteProgramConf);
			
			PB_DeleteProgramConf = PB_DeleteProgramConf + "'" + progName + "'" + ")]/../div[2]/button[1]";
			System.out.println(PB_DeleteProgramConf);
			
			waitForElementToLoad(driver, PB_DeleteProgramConf);
			driver.findElement(By.xpath(PB_DeleteProgramConf)).click();
			System.out.println("Deleted Successfully...");
			Thread.sleep(2000);
			getNotificationText(driver);
			
		}
		
	}
	
	
	public static void selectProgram(WebDriver driver) throws Exception {
		String PB_SelectProgram = readElement("locatorPaths.properties","PB_SelectProgram");
		
		//Clicking on Select Program
		driver.findElement(By.xpath(PB_SelectProgram)).click();
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
	
	public static String getNotificationText(WebDriver driver) {
		Boolean present;
		String msg = "";
		int x =1;
		while(true) {
			try {
				driver.findElement(By.xpath("//span[@class='noty_text']"));
	            WebElement WE = driver.findElement(By.xpath("//span[@class='noty_text']"));
	            msg = WE.getText();
	            System.out.println("Message is -> " + msg);
	            String raw_message = msg;
	            if(msg.length() > 0)
	                   break;
	            x++;
	            if(x > 200) {
	            	System.out.println("Timeout!!! from getNotificationText...");
	            	break;
	            }
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
					System.out.println("Element XPath -> " + elementXPath);
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
