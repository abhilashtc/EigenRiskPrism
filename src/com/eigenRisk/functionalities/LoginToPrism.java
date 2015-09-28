package com.eigenRisk.functionalities;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.eigenrisk.businesslogic.SelectExposure;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eigenRisk.Utilities.CommonUtilities;
import com.thoughtworks.selenium.webdriven.commands.GetText;

public class LoginToPrism {
	static String url, userName, password;
	static String xp_signInButton, xp_userName, xp_password, xp_selectExposure, xp_importNewExposure, xp_importNewExposureBrowse;
	public static WebDriver driver;
	public static String msgString;
	
	public static void handleFirefoxException() throws Exception {
		String fireFoxHandler = "./Tools/ManageFirefoxCrashWindows.exe";
		Runtime.getRuntime().exec(fireFoxHandler);
	}
	
	public static void login(WebDriver driver) throws Exception{
		//System.out.println("Inside Login");
		String warningMsg="";
		LoginToPrism.getLoginPageElements();
		LoginToPrism.getLoginDetails();
		System.out.println("Testing Environment is : " + url);
		driver.get(url);
		
		//System.out.println(xp_password+  " = " +password);
		//System.out.println(xp_signInButton);
		
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp_userName)));
		
		driver.findElement(By.xpath(xp_userName)).sendKeys(userName);
		driver.findElement(By.xpath(xp_password)).sendKeys(password);
		driver.findElement(By.xpath(xp_signInButton)).click();
		long start = System.currentTimeMillis();
		
		SelectExposure.loadTimeChecker(driver, start, "Login");
		
		System.out.println("Logging in as " + userName + " with the password " + password);
		driver.manage().window().maximize();
		//System.out.println("Sleeping for 1000 MilliSeconds...");
		//Thread.sleep(1000);
		String pageTitle = driver.getTitle();
		
//		FileReader file = new FileReader(".//LoginDetails.properties");
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		
		Properties prop =  new Properties();
		prop.load(file);
		//System.out.println("Page Title is " + pageTitle);
		
		
	}
	
	
	public static void login(WebDriver driver, String userName, String password) throws Exception{
		String warningMsg="";
		LoginToPrism.getLoginPageElements();
		//LoginToPrism.getLoginDetails();
		
		FileReader propertyFile = new FileReader("LoginDetails.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		url = configProperty.getProperty("URL");
		propertyFile.close();

		driver.get(url);
		System.out.println("Environment is : " + url);
		
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp_userName)));
		
		driver.findElement(By.xpath(xp_userName)).sendKeys(userName);
		driver.findElement(By.xpath(xp_password)).sendKeys(password);
		driver.findElement(By.xpath(xp_signInButton)).click();
		
		long start = System.currentTimeMillis();
	
		System.out.println("Logging in as " + userName + " with the password " + password);
		
		driver.manage().window().maximize();
		//Thread.sleep(2000);
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		
		try {
			System.out.println("Inside Try Block ~ login");
			String importNewExposureXpath = CommonUtilities.readElement("locatorPaths.properties", "importNewExposureXpath");
			CommonUtilities.waitForElementToLoad(driver, "");
			ProgramBuilder.waitForElementToLoad(driver, (ProgramBuilder.readElement("locatorPaths.properties","loginError")));
			WebElement logoName = driver.findElement(By.xpath(ProgramBuilder.readElement("locatorPaths.properties","loginError")));
			int logoExist = logoName.getText().length();
			System.out.println("...............");
			if (logoExist > 0) {
				System.out.println(logoName.getText());
				System.out.println(logoExist);
				msgString = logoName.getText();
			}
		}
		catch (Exception elementNotFException){
		}

		System.out.println("Exiting Login Method...");
	
	}
	
	
	public static void getLoginDetails() throws Exception {
		FileReader propertyFile = new FileReader("LoginDetails.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		
		url = configProperty.getProperty("URL");
		userName = configProperty.getProperty("login");
		password = configProperty.getProperty("password");
		
		propertyFile.close();
	}
	
	public static String getURL() throws Exception {
		FileReader propertyFile = new FileReader("LoginDetails.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		
		url = configProperty.getProperty("URL");
		
		propertyFile.close();
		return url;
	}
	
	public static void getLoginPageElements() throws Exception {
		FileReader propertyFile = new FileReader("WebElement_XPath.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		
		xp_userName = configProperty.getProperty("userNameField");
		xp_password = configProperty.getProperty("passwordField");
		xp_signInButton = configProperty.getProperty("signInButton");
		
		propertyFile.close();
	}		
	
	
	public static void signOut(WebDriver driver) throws Exception {
		Actions actions = new Actions(driver);
		Thread.sleep(2000);
		//System.out.println("Inside SignOut...");
		try {
			WebElement moveonmenu = driver.findElement(By.xpath("//img[@class='user-image']"));
			actions.moveToElement(moveonmenu);
			actions.perform();
			Thread.sleep(1000);
			/*
			actions.moveToElement(driver.findElement(By.xpath("//a[@class='ng-binding'][@ng-click='logout();']")));
			actions.perform();
			*/
			driver.findElement(By.xpath("//a[@class='ng-binding'][@ng-click='logout();']")).click();
			System.out.println("Signed Out...");
			Thread.sleep(1000);
			WebElement we = driver.findElement(By.xpath(xp_userName));
			System.out.println("Signing Out -> ");
		} catch (NoSuchElementException e) {
			String assertCond = "FAILED";
            //System.out.println("Failed to SignOut since Login Attempt Failed...");
            //Assert.assertTrue("Failed to SignOut", assertCond.equals("PASS"));
      }
	}
}
