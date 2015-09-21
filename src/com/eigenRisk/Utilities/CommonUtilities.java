package com.eigenRisk.Utilities;

import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class CommonUtilities {
	public static String currentAccuTIV;

	public static void takeScreenShot(WebDriver driver, String targetFile) throws Exception {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
//        FileUtils.copyFile(scrFile, new File("F:\\ScreenShots\\screenshot.png"));
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		timeStamp = tokenizeTime(timeStamp);
		targetFile = targetFile + "_" + timeStamp + ".png";
		FileUtils.copyFile(scrFile, new File(targetFile));
        System.out.println("Screen Shot Captured... @ " + targetFile);
	}
	
	public static String tokenizeTime(String timeStamp) throws Exception{
		System.out.println("Inside tokenizeTime " + timeStamp);
		String year = timeStamp.substring(0, 4);
		String month = timeStamp.substring(4, 6);
		String day = timeStamp.substring(6, 8);
		
		String tIME = timeStamp.substring(9, 15);
		String time1 = tIME.substring(0, 2) + "-" + tIME.substring(3, 5) + "-" + tIME.substring(5, 6);
		
		return(month + "~" + day + "~" + year + "_" + time1);
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
					System.out.println("Element Visible... " + elementXPath);
					break;
				}
				
				
	    	}
	    	catch (ElementNotFoundException ENF) {
	    		System.out.println(ENF);
	    	}
		}
	}
	
	
	public static void waitForTIV_ToChange(WebDriver driver, String currentTIV, String currency) throws Exception {
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties", "accuSummaryTIV");
		
		int ctr = 1;
		while(true) {
			String tiv = driver.findElement(By.xpath(accuSummaryTIV)).getText();
			if(!currentTIV.equals(tiv)) {
				System.out.println("TIV has changed from " + currentTIV + " to " + tiv + " and expected is " + currency);
				break;
			}
			
		}
	}
	
	public static void validateTIV_Assets_InAccuSummary(WebDriver driver, String year, String expectedAssets, String expectedTIV) throws Exception {
		System.out.println("<Inside validateTIV_Assets_InAccuSummary>");
		String accuSummaryAssets = readElement("locatorPaths.properties","accuSummaryAssets");
		String accuSummaryTIV = readElement("locatorPaths.properties","accuSummaryTIV");
		
		
		System.out.println("Waiting for TIV to change");
//		currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		System.out.println("TIV-> " + currentAccuTIV);
		if(!year.equals("Today")) {
			waitForTIV_ToChange(driver, currentAccuTIV, "USD");
			System.out.println("TIV Changed");
		}
		
		
		System.out.println("XPAth (Assets) -> " + accuSummaryAssets);
		System.out.println("XPAth (TIV) -> " + accuSummaryTIV);
		
		String assetNum = driver.findElement(By.xpath(accuSummaryAssets)).getText();
		currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		System.out.println(expectedAssets + " - " + assetNum);
		System.out.println(expectedTIV + " - " + currentAccuTIV);
		
		Assert.assertTrue(expectedAssets.equals(assetNum));
		System.out.println("Expected Number of Assets is matching with that on Accumulation Summary [" + expectedAssets + ", " + assetNum + "]");
		
		Assert.assertTrue(expectedTIV.equals(currentAccuTIV));
		System.out.println("Expected Number of Assets is matching with that on Accumulation Summary [" + expectedTIV + ", " + currentAccuTIV + "]");
		
		System.out.println("Test Case Passed...");
		System.out.println("***************************************************************************************************");
		
	}
}
