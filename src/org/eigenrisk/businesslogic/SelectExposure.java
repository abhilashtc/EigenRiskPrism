package org.eigenrisk.businesslogic;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Verify;

public class SelectExposure {
	
	
	
	public static void loadExposure(WebDriver driver) throws Exception{
	
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		
		//invoking the actions class
		
		System.out.println("LoadExposure is -> " + prop.getProperty("LoadExposureXpath"));
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(prop.getProperty("LoadExposureXpath"))).click();
		
		//using explicit waits
		System.out.println("LoadExposure is -> " + prop.getProperty("LoadSpecification"));
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("LoadSpecification")))).click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,7);
		System.out.println("LoadExposure is -> " + prop.getProperty("LoadExpoButton"));
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("LoadExpoButton")))).click();
		
		
		//long start = System.nanoTime();
		long start = System.currentTimeMillis();
		loadTimeChecker(driver, start, "Load Exposure");
		//driver.findElement(By.xpath(prop.getProperty("LoadExpoButton"))).click();
	}

	public static void loadTimeChecker(WebDriver driver, long start, String funct) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop =  new Properties();
		prop.load(file);
		//List<WebElement> assetSumm = driver.findElements(By.xpath(prop.getProperty("accumulationSummary")));
		
		String[] accuSummary = new String [3];
		int ctr = 0;
		String valChecker;
		
		//System.out.println("Inside SelectExposure.loadTimeChecker");
		Thread.sleep(2000);
		
		boolean present;
        while(true) {
               try {
                     driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
                     WebElement WE = driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
                     String msg = WE.getText();
                     //System.out.println("Message is -> " + msg);
                     
                     /*
                     WebElement loginEr = driver.findElement(By.xpath(prop.getProperty("loginError")));
                     
                     String logEr, loginStatus;
                     logEr = loginEr.getText();
                     if(logEr.length() > 0){
                     	loginStatus="FAIL";
                        break;
                     }
                     */
                     if(msg.length() > 0)
                            break;
               } catch (NoSuchElementException e) {
                     present = false;
                     //System.out.println("Inside Catch..");
                     try {
                    	 
	                     WebElement loginEr = driver.findElement(By.xpath(prop.getProperty("loginError")));
	                     String logEr, loginStatus;
	                     logEr = loginEr.getText();
	                    	 
	                     if(logEr.length() > 0){
	                     	loginStatus="FAIL";
	                     	System.out.println("Failed due to :- " + logEr);
	                        break;
	                     }
                     }
                     catch (NoSuchElementException e1) {
                    	 
                     }
               } finally {
            	   //System.out.println("Inside Finally");
               }
        }

		
		/*
		Iterator<WebElement> itr = assetSumm.iterator();
		while(true) {
			valChecker = itr.next().getText();
			if(valChecker.equals("0") || valChecker.equals("0.0")) {
				ctr++;
				if(ctr == 3) {
					ctr=0;
					itr = assetSumm.iterator();
				}
			}
			else
				break;
		}
		*/
		//long diff = System.nanoTime() - start;
        
		long diff = System.currentTimeMillis() - start;
		
		if (funct.equals("Load Exposure"))
			System.out.println("Load Exposure time is : " + diff/1000 + " seconds.");
		else if (funct.equals("Import Exposure"))
			System.out.println("Importing New Exposure time is : " + diff/1000 + " seconds.");
		else
			System.out.println("Login Activity took... " + diff/1000 + " seconds.");
		//Assert.assertTrue(diff/1000 < 10);
		int timeOut = Integer.parseInt(prop.getProperty("LoadExposureTimeout"));
		System.out.println("Timeout Settings is : " + timeOut);
		Verify.verify(diff/1000 < timeOut);
		
	}

}
