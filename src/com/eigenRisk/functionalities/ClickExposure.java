package com.eigenRisk.functionalities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.commonutilities.Fluent_Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickExposure {
	public static void clickExposure(WebDriver driver) throws IOException, Exception{
		
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		Fluent_Wait.webDriverWait(driver, prop.getProperty("selectExposureXpath"));
		WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		actions.moveToElement(selectExpo).perform();
		//actions.perform();
		//Thread.sleep(2000);
		
	}
	
	public static void importNewExposure(WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		setExposurePath();
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		System.out.println(prop.getProperty("importNewExposureXpath"));
		Fluent_Wait.webDriverWait(driver, prop.getProperty("importNewExposureXpath"));
		
		WebElement importNewExpo = driver.findElement(By.xpath(prop.getProperty("importNewExposureXpath")));
		//importNewExpo.click();
		//driver.findElement(By.xpath(prop.getProperty("importNewExposureXpath"))).click();
		Thread.sleep(1000);
		actions.moveToElement(importNewExpo).perform();
		importNewExpo.click();
		//actions.perform();
		
		
		
		Thread.sleep(1000);
		//Runtime.getRuntime().exec("./Tools/Warning_Recording_Diff_Keyboard.exe");
		
		String LaptopMake = prop.getProperty("LaptopMake");
		String ReadExposurePath;
		
		if (LaptopMake.equals("HP"))
			ReadExposurePath = "./Tools/ReadExposurePath_HP.exe";
		else
			ReadExposurePath = "./Tools/ReadExposurePath_Lenovo.exe";
		
		Runtime.getRuntime().exec(ReadExposurePath);
		Thread.sleep(2000);
		
		//Thread.sleep(3000);
		Boolean isPresent = driver.findElements(By.xpath(prop.getProperty("importNewExposureBrowse"))).size() > 0;
		System.out.println(">>>>>>>--> " + isPresent);
		System.out.println(prop.getProperty("importNewExposureBrowse"));
		//WebElement selectFile = driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse")));
		driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse"))).click();
		
		WebElement importButton = driver.findElement(By.xpath(prop.getProperty("importNewExposureButton")));
		//driver.findElement(By.xpath(prop.getProperty("importNewExposureButton"))).click();
		
		System.out.println(prop.getProperty("importNewExposureButton"));
		actions.moveToElement(importButton).perform();
		actions.moveToElement(importButton).perform();
		//Thread.sleep(150);
		importButton.click();
		importButton.click();
		
		driver.findElement(By.xpath("//button[contains(text(),\"Import\")]")).click();
		long start = System.currentTimeMillis();
		SelectExposure.loadTimeChecker(driver, start, "Import New Exposure");
		//Thread.sleep(7000);
		
		System.out.println("WHILE");
		boolean present;
		while(true) {
			try {
				driver.findElement(By.xpath("//span[@class='noty_text']"));
                WebElement WE = driver.findElement(By.xpath("//span[@class='noty_text']"));
                String msg = WE.getText();
                System.out.println("Message is -> " + msg);
                if(msg.length() > 0)
                       break;
				/*
				driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
				WebElement WE = driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
				String msg = WE.getText();
				System.out.println("Message is -> " + msg);
				if(msg.length() > 0)
					break;
				*/
			} catch (NoSuchElementException e) {
				present = false;
			}
		}
	
		
	}
	
	public static void importNewExposure(WebDriver driver, String importNewExposureFile) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		setExposurePath(importNewExposureFile);
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		System.out.println(prop.getProperty("importNewExposureXpath"));
		Fluent_Wait.webDriverWait(driver, prop.getProperty("importNewExposureXpath"));
		
		WebElement importNewExpo = driver.findElement(By.xpath(prop.getProperty("importNewExposureXpath")));
		//importNewExpo.click();
		//driver.findElement(By.xpath(prop.getProperty("importNewExposureXpath"))).click();
		Thread.sleep(1000);
		actions.moveToElement(importNewExpo).perform();
		importNewExpo.click();
		//actions.perform();
		
		long start = System.currentTimeMillis();
		
		Thread.sleep(1000);
		//Runtime.getRuntime().exec("./Tools/Warning_Recording_Diff_Keyboard.exe");
		
		String LaptopMake = prop.getProperty("LaptopMake");
		String ReadExposurePath;
		
		if (LaptopMake.equals("HP"))
			ReadExposurePath = "./Tools/ReadExposurePath_HP.exe";
		else
			ReadExposurePath = "./Tools/ReadExposurePath_Lenovo.exe";
		
		Runtime.getRuntime().exec(ReadExposurePath);
		Thread.sleep(2000);
		
		//Thread.sleep(3000);
		Boolean isPresent = driver.findElements(By.xpath(prop.getProperty("importNewExposureBrowse"))).size() > 0;
		System.out.println(">>>>>>>--> " + isPresent);
		System.out.println(prop.getProperty("importNewExposureBrowse"));
		//WebElement selectFile = driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse")));
		driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse"))).click();

		SelectExposure.loadTimeChecker(driver, start, "Import New Exposure");
		Thread.sleep(7000);
	
		
	}
	
	public static void loadExposure(WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(prop.getProperty("LoadExposureXpath"))).click();
		
	}
	
	public static void setExposurePath() throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("importNewExposureFile")));
		
		
		
		File currentDirFile = new File(".");
		String exposureLoc = currentDirFile.getAbsolutePath();
		
		//System.out.println(exposureLoc);
		//System.out.println("Project Path is " + exposureLoc);
		exposureLoc = exposureLoc + prop.getProperty("importNewExposureFile");
		
		//System.out.println(exposureLoc);
		exposureLoc = exposureLoc.replace(".", "");
		exposureLoc = exposureLoc.replace("xls", ".xls");
		
		//exposureLoc.replace("\"", "$");
		
		//System.out.println(exposureLoc);
		exposureLoc = exposureLoc.replace("/", "\\");
		//System.out.println("Loading New Exposure from " + exposureLoc);
		
		
		BufferedWriter out = new BufferedWriter(new FileWriter("C:/TestRun/FileToOpen.txt")); 
		//out.newLine();
		out.write(exposureLoc); 
		out.close();
	}
	
	
	public static void setExposurePath(String importNewExposureFile) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("importNewExposureFile")));
		
		
		
		File currentDirFile = new File(".");
		String exposureLoc = currentDirFile.getAbsolutePath();
		
		//System.out.println(exposureLoc);
		//System.out.println("Project Path is " + exposureLoc);
		exposureLoc = exposureLoc + importNewExposureFile;
		
		//System.out.println(exposureLoc);
		exposureLoc = exposureLoc.replace(".", "");
		exposureLoc = exposureLoc.replace("xls", ".xls");
		exposureLoc = exposureLoc.replace("zip", ".zip");
		
		//exposureLoc.replace("\"", "$");
		
		//System.out.println(exposureLoc);
		exposureLoc = exposureLoc.replace("/", "\\");
		//System.out.println("Loading New Exposure from " + exposureLoc);
		
		
		BufferedWriter out = new BufferedWriter(new FileWriter("C:/TestRun/FileToOpen.txt")); 
		//out.newLine();
		out.write(exposureLoc); 
		out.close();
	}
	
}
