package com.eigenRisk.TestSuite;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.eigenrisk.businesslogic.AssetDetails;
import org.eigenrisk.businesslogic.AssetDetailsSearchBox;
import org.eigenrisk.businesslogic.Login;
import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.businesslogic.TopN;
import org.eigenrisk.commonutilities.SelectBrowser;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class LoginPage {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		/*
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		*/
		System.out.println("#############################################< LOGIN PAGE >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		/*
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		*/
		System.out.println("#############################################< LOGIN PAGE >########################################################");
	}
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		System.out.println("-------------------------------START-------------------------->>");
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n--------------------------------END------------------------->>");
		Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);
	}
	
	
	@Test(priority = 5, enabled=true)
	public void loginToPrism_ValidCredentials() throws Exception{
		System.out.println("Test Case Name :- loginToPrism\n");
		
		String userName = "abhilashtc@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver, userName, password);
		LoginToPrism.signOut(driver);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	@Test(priority = 7, enabled=true)
	public void loginToPrism_InvalidUserName() throws Exception{
		System.out.println("Test Case Name :- loginToPrismInvalidUserName\n");
		String userName = "qa_userInvalid@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver,userName,password);
		System.out.println(">>> " + LoginToPrism.msgString);
//		Assert.assertTrue(LoginToPrism.msgString.equals(userName + " is not registered. Please create an account !!"));
		Assert.assertTrue(LoginToPrism.msgString.equals("Error: The User ID does not exist"));
		System.out.println(">>> " + LoginToPrism.msgString);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	
	@Test(priority = 9, enabled=true)
	public void loginToPrism_InvalidPassword() throws Exception{
		System.out.println("Test Case Name :- loginToPrismInvalidPassword\n");
		String userName = "abhilashtc@eigenrisk.com";
		String password = "eigenriskQA@1234";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver,userName,password);
		System.out.println(">>> " + LoginToPrism.msgString);
		Thread.sleep(200);
		
		System.out.println("Message String is " + LoginToPrism.msgString);
		
		Assert.assertTrue(LoginToPrism.msgString.equals("Authentication Failed"));
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	
	@Test(priority = 10, enabled=true)
	public void loginToPrism_BlankUserName() throws Exception{
		System.out.println("Test Case Name :- loginToPrismBlankUserName\n");
		String userName = "";
		String password = "eigenriskQA@1234";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver,userName,password);
		String currentURL = driver.getCurrentUrl();
		String url_l10 = currentURL.substring(currentURL.length() - 10);
		
		boolean flag = false;
		if(url_l10.equals("auth/login") && userName.length() < 1)
			flag = true;
		else
			flag = false;
			
		
		/*
   	 	String cUrl_l10 = currentURL.substring(currentURL.length() - 10);
		*/
		
		
		
		
		Assert.assertTrue(flag);
		System.out.println(">>> " + LoginToPrism.msgString);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	
	@Test(priority = 11, enabled=true)
	public void loginToPrism_BlankPassword() throws Exception{
		System.out.println("Test Case Name :- loginToPrismBlankPassword\n");
		String userName = "qa_user55@eigenrisk.com";
		String password = "";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver,userName,password);
		System.out.println(">>> " + LoginToPrism.msgString);
		
		String currentURL = driver.getCurrentUrl();
		String url_l10 = currentURL.substring(currentURL.length() - 10);
		
		boolean flag = false;
		if(url_l10.equals("auth/login") && password.length() < 1)
			flag = true;
		else
			flag = false;
			
		Assert.assertTrue(flag);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	

	@AfterTest
	public void tearDown() {
		//driver.close();
		
	}
	
	
	

}

