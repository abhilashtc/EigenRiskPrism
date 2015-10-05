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
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.eigenRisk.Utilities.CommonUtilities;
import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.ExposureDrive;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class Exposure_Drive {

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
		System.out.println("#############################################< Exposure Drive >########################################################");
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
		System.out.println("#############################################< Exposure Drive >########################################################");
	}
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		
//		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		driver = SelectBrowser.selectBrowser("chrome");
		DOMConfigurator.configure("log4j.xml");
//		LoginToPrism.handleFirefoxException();
		System.out.println("-------------------------------START-------------------------->>");
		
		
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n--------------------------------END------------------------->>");
		Thread.sleep(200);
		//driver.quit();
		Thread.sleep(500);
	}
	
	

	@Parameters({"userName"})
	@Test(priority = 1, enabled=false)
	public void ExposureDrive_ImportExposure_PositiveScenarios() throws Exception {
		System.out.println("TEST CASE NAME -> ExposureDrive_ImportExposure_PositiveScenarios");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println(driver.getTitle());
		
//		String exposureFile = "51_Records.xlsx";
		String exposureFile = "NegativeScenarioFile.xlsx";
		String configName = "Abhi_ALL_Fields";
		ExposureDrive.importExposure(driver,exposureFile, configName);
		
	}
	
	
	// Negative Scenarios Covered are 
	@Parameters({"userName"})
	@Test(priority = 5, enabled=true)
	public void ExposureDrive_ImportExposure_NegativeScenarios() throws Exception {
		System.out.println("TEST CASE NAME -> ExposureDrive_ImportExposure_NegativeScenarios");
		/*
		 * mandatory fields for 2-3 assets and make them null
		 * Date - few rows should have yyyyy and dd/mm/yyyy format
		 * Numbers - few numeric fields should have characters
		 * for character field, enter 3 characters for few rows. Soil type
		 * make duplicate entries for asset number field
		 * Valuation Date - dd/mm/yyyy, Currency (4)- currency conversion should happen accordingly, - Incase of garbage currency, it should show Unknown Change currency from USD to EUR
	Country code (3)
		 */
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println(driver.getTitle());
		
//		String exposureFile = "51_Records.xlsx";
		String exposureFile = "NegativeScenarioFile.xlsx";
		String configName = "Abhi_All_Fields";
		ExposureDrive.selectConfig = "true";
		ExposureDrive.importExposure(driver,exposureFile, configName);
		
	}
	

	@Parameters({"userName"})
	@Test(priority = 15, enabled=false)
	public void CreateNewConfig() throws Exception {
		System.out.println("TEST CASE NAME -> ConfigFileCreation");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println(driver.getTitle());
		
		String exposureFile = "51_Records.xlsx";
		//ExposureDrive.importExposure(driver,exposureFile);
		String configName = "Abhi_All_Fields";
		ExposureDrive.selectConfig = "false";
		ExposureDrive.selectExposureFile(driver, exposureFile, configName);
		ExposureDrive.selectConfig = "true";
		
		ExposureDrive.createConfig(driver);
	}
	
	@Parameters({"userName"})
	@Test(priority = 55, enabled=false)
	public void Evaluate_TIV_and_AssetNum_51_Records_2014() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2014");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println(driver.getTitle());

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		System.out.println("Clicked on Exposure...");
		
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties","accuSummaryTIV");
		CommonUtilities.currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2014");
		System.out.println("------------------------------------------------------------------------> For 2014");
		Thread.sleep(2500);
		
//		signOutFlag = true;
//		openBrowser = true;
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "2014", "36", "90.8 M");
		
	}
	
	@AfterTest
	public void tearDown() {
		//driver.close();
		
	}
	
	
	

}

