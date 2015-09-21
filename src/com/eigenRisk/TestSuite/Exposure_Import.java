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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.ForgotPassword;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class Exposure_Import {

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
		System.out.println("#############################################< EXPOSURE - IMPORT >########################################################");
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
		System.out.println("#############################################< EXPOSURE - IMPORT >########################################################");
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
	public void importExposure_ValidFile_Excel() throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		//Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/Karthika_Global_Demo_Data V 0 8.xlsx");
		
		/*System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);*/
		
		Assert.assertTrue(Exposure_Import_Load.notify_msg1.contains(Exposure_Import_Load.notify_msg2));
	}
	
	@Test(priority = 10, enabled=true)
	public void importExposure_InvalidFile_Excel() throws Exception{
		System.out.println("Test Case Name :- importExposure_InvalidFile_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		//Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.importNewExposure(driver,"TestData/InvalidExcel_Karthika_Global_Demo_Data.xlsx");
		/*
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		*/
		Assert.assertTrue(Exposure_Import_Load.notify_msg2.contains("Invalid Data/Not Standard Format"));
		//Import Process failed for InvalidExcel_Karthika_Global_Demo_Data : Invalid Data/Not Standard Format
	}
	
	
	
	@Test(priority = 15, enabled=true)
	public void importExposure_50MB_ValidFile_Excel() throws Exception{
		System.out.println("Test Case Name :- importExposure_50MB_ValidFile_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		//Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/50MB_Global_Demo_Data V 0 7.xlsx");
		
		/*System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		*/
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains("File size exeeded the limit (50MB)"));
		
	}
	
	
	@Test(priority = 20, enabled=true)
	public void importExposure_ValidFile_CSV() throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		//Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/asset_1_Million.zip");
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains(Exposure_Import_Load.notify_msg2));
		
	}
	
	@Test(priority = 25, enabled=true)
	public void importExposure_InvalidFile_CSV() throws Exception{
		System.out.println("Test Case Name :- importExposure_InvalidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
//		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.importNewExposure(driver,"TestData/CorruptedAsset.zip");
		
		/*System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);*/
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains("Import Process failed for CorruptedAsset : Invalid Data Format. Please try and import the file again by checking the Validation option for more details"));
		//Import Process failed for InvalidExcel_Karthika_Global_Demo_Data : Invalid Data/Not Standard Format
		
	}
	
	/*
	@Test(priority = 25, enabled=true)
	public void readErrorOnImport() throws Exception{
		System.out.println("Test Case Name :- importExposure_InvalidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		//Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
//		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.readErrorOnImport(driver);
		//Exposure_Import_Load.importNewExposure(driver,"TestData/CorruptedAsset.zip");
		
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		
		//Assert.assertTrue(Exposure_Import_Load.raw_message.contains("Import Process failed for CorruptedAsset : Invalid Data Format. Please try and import the file again by checking the Validation option for more details"));
		//Import Process failed for InvalidExcel_Karthika_Global_Demo_Data : Invalid Data/Not Standard Format
		
	}
	*/
	/*
	@Test(priority = 7, enabled=true)
	public void resetPassword_UnRegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- resetPassword_UnRegisteredEmail\n");
		ForgotPassword.resetPassword_UnRegisteredEmail(driver, "abhilashtc@test");
	}
	
	@Test(priority = 8, enabled=true)
	public void resetPassword_RegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- resetPassword_UnRegisteredEmail\n");
		ForgotPassword.resetPassword_RegisteredEmail(driver, "abhilashtc@eigenrisk.com");

	}*/
	
}

