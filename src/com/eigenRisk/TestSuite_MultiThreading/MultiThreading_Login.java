package com.eigenRisk.TestSuite_MultiThreading;

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
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
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


public class MultiThreading_Login {

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
		System.out.println("#############################################< MULTI-THREADING (LOGIN) >########################################################");
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
		System.out.println("#############################################< MULTI-THREADING (LOGIN) >########################################################");
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
		
		Thread.sleep(1000);
		LoginToPrism.signOut(driver);
		Thread.sleep(1000);
		driver.quit();
	}
	
	/*
    @Parameters({ "userName" })
    @Test
    public void optionTest(@Optional("optional value") String value) {
        System.out.println("This is: Test " + value);
    }
	*/
	
	@Parameters({ "userName" })
	@Test(priority = 5, enabled=true)
	public void loginActivity(@Optional("qa_user55@eigenrisk.com") String loginID) throws Exception{
		System.out.println("Test Case Name :- loginActivity\n");
		System.out.println("Login ID is > " + loginID);
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		Thread.sleep(250);
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		long id = Thread.currentThread().getId();
        System.out.println(">>>> Thread id is: " + id);

	}
	
	@Parameters({ "userName", "exposureToLoad" })
	@Test(priority = 5, enabled=true)
	public void importExposure_ValidFile_Excel(@Optional("qa_user55@eigenrisk.com") String loginID, @Optional("TestData/Karthika_Global_Demo_Data V 0 8.xlsx") String exposureToLoad) throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_Excel\n");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.importNewExposure(driver,exposureToLoad);
		Assert.assertTrue(Exposure_Import_Load.notify_msg1.contains(Exposure_Import_Load.notify_msg2));
		/*
		Thread.sleep(1500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		Thread.sleep(2500);
		AssetList.accumulationSummary(driver, exposureToLoad);
		*/
	}
	
	@Parameters({"userName"})
	@Test(priority = 6, enabled=true)
	public void importExposure_1MillionCSV(@Optional("qa_user55@eigenrisk.com") String loginID) throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_Excel\n");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		String exposureToLoad = "TestData/asset_1_Million.zip";
		Exposure_Import_Load.importNewExposure(driver,exposureToLoad);
		Assert.assertTrue(Exposure_Import_Load.notify_msg1.contains(Exposure_Import_Load.notify_msg2));

	}
	
	
	@Parameters({ "userName"})
	@Test(priority = 15, enabled=true)
	public void loadExposureExcel(@Optional("qa_user55@eigenrisk.com") String loginID) throws Exception{
		System.out.println("Test Case Name :- loadExposure\n");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		Thread.sleep(250);
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Thread.sleep(1000);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		
		Thread.sleep(2000);
		
	}
	
	
	@Parameters({ "userName"})
	@Test(priority = 15, enabled=true)
	public void loadExposureCSV(@Optional("qa_user4@eigenrisk.com") String loginID) throws Exception{
		System.out.println("Test Case Name :- loadExposure\n");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		Thread.sleep(250);
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Thread.sleep(1000);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "asset_1_Million";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		
		Thread.sleep(2000);
		
	}
	
}

