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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSImportConcurrentUser {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String url = "https://prism.beta1.qa.eigenrisk.com/exposureAnalytics";
		
		FileReader propertyFile = new FileReader("LoginDetails.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		
		url = configProperty.getProperty("URL");
		String userName;
		String password = "eigenriskQA@123";
		
		propertyFile.close();
		
		
		int concurrentUsers = 2;
		int userStartCtr=25;
		int userLimit = concurrentUsers + userStartCtr;
		
		for(; userStartCtr < userLimit;userStartCtr++) {
			System.out.println("--------------------------------------------");
			WebDriver driver = new FirefoxDriver();
			userName = "qa_user" + userStartCtr +"@eigenrisk.com";
			
			System.out.println(userName);
			login(driver, url, userName, password);
			Thread.sleep(1500);
			ClickExposure.clickExposure(driver);
			Thread.sleep(1000);
			
			long start = System.currentTimeMillis();
			//System.out.println("Start time is " + start);
			
			importNewExposure(driver);
			
			WebElement TopN = driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
			while(TopN.getText().equals("0"))
				TopN = driver.findElement(By.xpath("//span[@class='accumulationSummaryDetail ng-binding']"));
			
			long diff = System.currentTimeMillis() - start;
			System.out.println("Import Exposure & Load Exposure time is : " + diff/1000 + " seconds.");
			
			System.out.println("Session completed for " + userName);
		}
		System.out.println("*************************************");
		
	}
	
	
	
	public static void login (WebDriver driver, String url, String userName, String password) throws Exception {
		LoginToPrism.getLoginPageElements();
		//LoginToPrism.getLoginDetails();

		driver.get(url);
		/*System.out.println("Testing Environment is : " + url);
		System.out.println(xp_userName+  " = " +userName);
		//System.out.println(xp_password+  " = " +password);
		System.out.println(xp_signInButton);*/
		
		FileReader propertyFile = new FileReader("WebElement_XPath.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		
		String xp_userName = configProperty.getProperty("userNameField");
		String xp_password = configProperty.getProperty("passwordField");
		String xp_signInButton = configProperty.getProperty("signInButton");
		/*
		String userName = "qa_user52@eigenrisk.com";
		String password = "eigenriskQA@123";
		*/
		propertyFile.close();
		
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp_userName)));
		
		driver.findElement(By.xpath(xp_userName)).sendKeys(userName);
		driver.findElement(By.xpath(xp_password)).sendKeys(password);
		driver.findElement(By.xpath(xp_signInButton)).click();
		
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}
	
	public static void importNewExposure(WebDriver driver) throws Exception {
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		setExposurePath();
		//WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("selectExposureXpath")));
		System.out.println("Exposure Path Set");
		
		Thread.sleep(1000);
		Actions actions = new Actions(driver);
		//System.out.println(prop.getProperty("D_ImportNewExposure"));
		Fluent_Wait.webDriverWait(driver, prop.getProperty("D_ImportNewExposure"));
		
		System.out.println("1");
		
		WebElement importNewExpo = driver.findElement(By.xpath(prop.getProperty("D_ImportNewExposure")));
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
		//System.out.println(">>>>>>>--> " + isPresent);
		//System.out.println(prop.getProperty("importNewExposureBrowse"));
		WebElement selectFile = driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse")));
		driver.findElement(By.xpath(prop.getProperty("importNewExposureBrowse"))).click();

		//SelectExposure.loadTimeChecker(driver, start, "Import New Exposure");
		Thread.sleep(1000);
		
		WebElement importButton = driver.findElement(By.xpath(prop.getProperty("importNewExposureButton")));
		//driver.findElement(By.xpath(prop.getProperty("importNewExposureButton"))).click();
		
		//System.out.println(prop.getProperty("importNewExposureButton"));
		actions.moveToElement(importButton).perform();
		Thread.sleep(500);
		importButton.click();
		//driver.findElement(By.xpath(prop.getProperty("importNewExposureButton"))).click();
		int cnt;
		/*
		for (cnt=0; cnt < 2; cnt++)
			importButton.click();
		
		System.out.println("Importing " + cnt + " Times");
		*/
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
//		exposureLoc = exposureLoc + prop.getProperty("importNewExposureFile");
		exposureLoc = exposureLoc + prop.getProperty("AssetZipFile");
		
		//System.out.println(exposureLoc);
		exposureLoc = exposureLoc.replace(".", "");
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
