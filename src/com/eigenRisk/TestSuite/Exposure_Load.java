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
import org.openqa.selenium.By;
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

public class Exposure_Load {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< EXPOSURE - LOAD >###############################################");
		
		//loadDependencyExposure();
		
	}
	
	public static void loadDependencyExposure() throws Exception {

		FileReader file = new FileReader("Config.properties");
		Properties prop = new Properties();
		prop.load(file);
		WebDriver driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		System.out.println("Importing the required exposures\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		//Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
//		Exposure_Import_Load.importNewExposure(driver,"TestData/Karthika_Global_Demo_Data_15K.xlsx");
		Exposure_Import_Load.importNewExposure(driver,"TestData/AshwiniTopN-EmptyIssue.xlsx");
		Thread.sleep(750);
		
		String cancelButton = "//button[@ng-click='cancel()']";
		Exposure_Import_Load.importNewExposure(driver,"TestData/Srikanth-30-Records-IssueFile.xlsx");
		Thread.sleep(750);
		
		Exposure_Import_Load.waitForElementToLoad(driver, cancelButton);
		driver.findElement(By.xpath(cancelButton)).click();
		Thread.sleep(500);
		
		Exposure_Import_Load.importNewExposure(driver,"TestData/asset_1_Million.zip");
		driver.close();
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< EXPOSURE - LOAD >########################################################");
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
		LoginToPrism.signOut(driver);
		Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);
	}
	
	
	@Test(priority = 5, enabled=true)
	public void loadExposure_Excel() throws Exception{
		System.out.println("Test Case Name :- loadExposure_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		System.out.println("Validating Login... " + LoginToPrism.msgString);
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "Global_Demo_Data _15K";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		
		Thread.sleep(3000);
	}
	
	
	@Test(priority = 15, enabled=true)
	public void loadExposure_CSV() throws Exception{
		System.out.println("Test Case Name :- loadExposure_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		//Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "asset_1_Million";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		
		Assert.assertTrue(Exposure_Import_Load.notify_conditon);
		Thread.sleep(1000);
//		LoginToPrism.signOut(driver);
	}
	
	@Test(priority = 20, enabled=false)
	public void load_DeleteSingleExposure() throws Exception{
		System.out.println("Test Case Name :- DeleteExposure\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
			
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		//exposureset_DuplicateAssets
		//Karthika_Global_Demo_Data
		String exposureToDelete = "exposureset_DuplicateAssets";
		
		Exposure_Import_Load.DeleteSingleExposure(driver, exposureToDelete);
			
		Thread.sleep(1000);
//		LoginToPrism.signOut(driver);
	}
	
	
	@Test(priority = 10, enabled=false)
	public void loadExposure_CheckAccumulationSummary_2011() throws Exception{
		System.out.println("Test Case Name :- loadExposure_CheckAccumulationSummary_2011\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		
		Thread.sleep(2500);
		AccumulationSummary.accumulationSummary(driver, exposureToLoad);
		Thread.sleep(1000);
//		LoginToPrism.signOut(driver);
	}
	
	
	@Test(priority = 20, enabled=false)
	public void importExposure_ValidFile_CSV() throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/exposureset_DuplicateAssets.zip");
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains(Exposure_Import_Load.notify_msg2));
		
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=false)
	public void load_DeleteAllExposure(@Optional("qa_user55@eigenrisk.com") String loginID) throws Exception{
		System.out.println("Test Case Name :- DeleteExposure\n");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
			
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		//exposureset_DuplicateAssets
		//Karthika_Global_Demo_Data
		String exposureToDelete = "exposureset_DuplicateAssets";
		
		Exposure_Import_Load.DeleteAllExposure(driver, exposureToDelete);
			
		Thread.sleep(1000);
//		LoginToPrism.signOut(driver);
	}
	
	
	@Test(priority = 20, enabled=false)
	public void load_ListExposures() throws Exception{
		System.out.println("Test Case Name :- Load-List Exposures\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		Exposure_Import_Load.listAllExposures(driver);
		
		Thread.sleep(1000);
//		LoginToPrism.signOut(driver);
	}
	
	
	/*@Test(priority = 10, enabled=false)
	public void importExposure_InvalidFile_Excel() throws Exception{
		System.out.println("Test Case Name :- importExposure_InvalidFile_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.importNewExposure(driver,"TestData/InvalidExcel_Karthika_Global_Demo_Data.xlsx");
		
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		
		Assert.assertTrue(Exposure_Import_Load.notify_msg2.contains("Invalid Data/Not Standard Format"));
		//Import Process failed for InvalidExcel_Karthika_Global_Demo_Data : Invalid Data/Not Standard Format
		
	}
	
	
	
	@Test(priority = 15, enabled=false)
	public void importExposure_50MB_ValidFile_Excel() throws Exception{
		System.out.println("Test Case Name :- importExposure_50MB_ValidFile_Excel\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/50MB_Global_Demo_Data V 0 7.xlsx");
		
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains("File size exeeded the limit (50MB)"));
		
	}
	
	
	@Test(priority = 20, enabled=false)
	public void importExposure_ValidFile_CSV() throws Exception{
		System.out.println("Test Case Name :- importExposure_ValidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		//Exposure_Import_Load.importNewExposure(driver);
		Exposure_Import_Load.importNewExposure(driver,"TestData/exposureset_DuplicateAssets.zip");
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains(Exposure_Import_Load.notify_msg2));
		
	}
	
	@Test(priority = 25, enabled=false)
	public void importExposure_InvalidFile_CSV() throws Exception{
		System.out.println("Test Case Name :- importExposure_InvalidFile_CSV\n");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");

		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(500);
		Exposure_Import_Load.importNewExposure(driver,"TestData/CorruptedAsset.zip");
		
		System.out.println(Exposure_Import_Load.notify_msg1);
		System.out.println(Exposure_Import_Load.notify_msg2);
		
		Assert.assertTrue(Exposure_Import_Load.raw_message.contains("Invalid Data/Not Standard Format"));
		//Import Process failed for InvalidExcel_Karthika_Global_Demo_Data : Invalid Data/Not Standard Format
		
	}
	
	
	@Test(priority = 7, enabled=false)
	public void resetPassword_UnRegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- resetPassword_UnRegisteredEmail\n");
		ForgotPassword.resetPassword_UnRegisteredEmail(driver, "abhilashtc@test");
	}
	
	@Test(priority = 8, enabled=false)
	public void resetPassword_RegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- resetPassword_UnRegisteredEmail\n");
		ForgotPassword.resetPassword_RegisteredEmail(driver, "abhilashtc@eigenrisk.com");

	}
	*/
}

