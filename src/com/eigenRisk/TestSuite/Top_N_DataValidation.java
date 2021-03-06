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

import com.eigenRisk.Utilities.CommonUtilities;
import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.ForgotPassword;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.ProgramBuilder;
import com.eigenRisk.functionalities.TopN_DataValidation;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class Top_N_DataValidation {

	WebDriver driver;
	Properties prop;
	boolean openBrowser = true;
	boolean signOutFlag = true;
	String browser = "Chrome";
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< TOP-N, CURRENCY EVALUATION >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< TOP-N, CURRENCY EVALUATION >########################################################");
	}
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		if(openBrowser) {
			FileReader file = new FileReader("Config.properties");
			prop = new Properties();
			prop.load(file);
			
			if(browser.equals("Chrome")) {
				driver = SelectBrowser.selectBrowser("chrome");
				System.out.println("Opening the Chrome Browser");
				//browser = "Chrome";
			}
			else {
				driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
				LoginToPrism.handleFirefoxException();
				System.out.println("Opening the Firefox Browser");
			}
			DOMConfigurator.configure("log4j.xml");
			
			System.out.println("-------------------------------START-------------------------->>");
		}
		//String loginID = "qa_user55@eigenrisk.com";
		//LoginToPrism.login(driver,loginID,"eigenriskQA@123");
//		
//		String exposureToLoad = "Karthika_Global_Demo_Data";
//		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		if(signOutFlag) {
			System.out.println("\n--------------------------------END------------------------->>");
			Thread.sleep(200);
			
			Thread.sleep(1000);
			System.out.println("Going to SignOut...");
			
			LoginToPrism.signOut(driver);
			Thread.sleep(500);
			driver.quit();
		}
		else
			System.out.println("Not Signing Out!!! since execution is going to continue in the current browser");
	}
	
		
	
	@Parameters({"userName"})
	@Test(priority = 1, enabled=true)
	public void ExportTopN_ToExcel() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateTIV_ForEachAsset");
		//String loginID = "skmedikonda@eigenrisk.com";
		//LoginToPrism.login(driver,loginID,"medikonda*MN#1");
		
		//Deleting all the new programs for the 1st user
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		
		Thread.sleep(500);
		//Clicking on Top10
		String topNXpath = ProgramBuilder.readElement("locatorPaths.properties", "topNXpath");
		driver.findElement(By.xpath(topNXpath)).click();
		Thread.sleep(500);
		
		//Clicking on 100
		String topN_100 = ProgramBuilder.readElement("locatorPaths.properties", "topN_100");
		driver.findElement(By.xpath(topN_100)).click();
		
		//Adding Measures to Top N Settings
		TopN_Settings.validateFlag = false;
		Thread.sleep(500);
		TopN_Settings.click_Settings(driver, "TopN");
		TopN_Settings.addMultipleMeasures(driver, "Value Buildings", "Value Contents", "Value Business Interruption", "TopN");
		TopN_Settings.click_Settings(driver, "TopN");
		TopN_Settings.removeMultipleMeasures(driver, "Damage (%)", "Intensity", "Ground Up Loss", "TopN");
		TopN_Settings.validateFlag = true;
		
		boolean exportResult = TopN_DataValidation.exportToExcel(driver);
		System.out.println("exportResult is -> " + exportResult);
//		browser = "Firefox";
		Assert.assertTrue(exportResult);
		
		//AccumulationSummary.printTable(driver);
		
		
		
	}
	
	@Test(priority = 5, enabled=true)
	public void EvaluateTIV_For_Top_100_Assets() throws Exception {
		openBrowser = false;
		TopN_DataValidation.EvaluateTopN_Assets();
		openBrowser = true;
		
		
		/*
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		System.out.println("------------------------------------------------------------------------> For 2011");
		Thread.sleep(2500);
		AccumulationSummary.accumulationSummary(driver);
		*/
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 10, enabled=true)
	public void EvaluateCurrencyConversionFor_CAD() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_CAD");
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "5_Countries_Records �";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		
		Thread.sleep(500);
		//Clicking on Top10
		String topNXpath = ProgramBuilder.readElement("locatorPaths.properties", "topNXpath");
		driver.findElement(By.xpath(topNXpath)).click();
		Thread.sleep(500);
		
		//Clicking on 100
		String topN_100 = ProgramBuilder.readElement("locatorPaths.properties", "topN_100");
		driver.findElement(By.xpath(topN_100)).click();
		
		
		//Adding Measures to Top N Settings
//		TopN_DataValidation.addRequiredTopNSettings(driver);
		
		//Print Top N
		//Thread.sleep(2000);
//		TopN_DataValidation.printTable(driver);
		
		String currency = "CAD";
		String assetNum = "9834";
		String expectedCurrencyValue = "6.7 M";
		TopN_DataValidation.evaluateLocalCurrency(currency, assetNum, expectedCurrencyValue, driver, "EvaluateCurrencyConversionFor_CAD");
		System.out.println("Local Currency Evaluation Passed for Asset# " + assetNum + " for the local Currency " + currency);
		
//		signOutFlag = false;
//		openBrowser = false;
	}
	
	
	//This test case has a dependency on EvaluateCurrencyConversionFor_CanadianDollar
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void EvaluateCurrencyConversionFor_EUR() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_EURO");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "5_Countries_Records �";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		String currency = "EUR";
		String assetNum = "13887";
		String expectedCurrencyValue = "3.8 M";
		TopN_DataValidation.evaluateLocalCurrency(currency, assetNum, expectedCurrencyValue, driver, "EvaluateCurrencyConversionFor_EUR");
		System.out.println("Local Currency Evaluation Passed for Asset# " + assetNum + " for the local Currency " + currency);
		
	}
	
	
	
	//This test case has a dependency on EvaluateCurrencyConversionFor_CanadianDollar
	@Parameters({"userName"})
	@Test(priority = 20, enabled=true)
	public void EvaluateCurrencyConversionFor_USD() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_US_Dollar");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "5_Countries_Records �";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		String currency = "USD";
		String assetNum = "511";
		String expectedCurrencyValue = "8.4 M";
		TopN_DataValidation.evaluateLocalCurrency(currency, assetNum, expectedCurrencyValue, driver, "EvaluateCurrencyConversionFor_USD");
		System.out.println("Local Currency Evaluation Passed for Asset# " + assetNum + " for the local Currency " + currency);
		
	}

	
	
	//This test case has a dependency on EvaluateCurrencyConversionFor_CanadianDollar
	@Parameters({"userName"})
	@Test(priority = 25, enabled=true)
	public void EvaluateCurrencyConversionFor_BBD() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_BarbadosDollar");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "5_Countries_Records �";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		String currency = "BBD";
		String assetNum = "14141";
		String expectedCurrencyValue = "6.9 M";
		TopN_DataValidation.evaluateLocalCurrency(currency, assetNum, expectedCurrencyValue, driver, "EvaluateCurrencyConversionFor_BBD");
		System.out.println("Local Currency Evaluation Passed for Asset# " + assetNum + " for the local Currency " + currency);
		
	}

	
	
	//This test case has a dependency on EvaluateCurrencyConversionFor_CanadianDollar
	@Parameters({"userName"})
	@Test(priority = 30, enabled=true)
	public void EvaluateCurrencyConversionFor_JPY() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_JapaneseYen");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "5_Countries_Records �";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		String currency = "JPY";
		String assetNum = "17987";
		String expectedCurrencyValue = "3.4 M";
		TopN_DataValidation.evaluateLocalCurrency(currency, assetNum, expectedCurrencyValue, driver, "EvaluateCurrencyConversionFor_JPY");
		System.out.println("Local Currency Evaluation Passed for Asset# " + assetNum + " for the local Currency " + currency);
//		
//		signOutFlag = true;
//		openBrowser = true;
		
	}

	
	@Parameters({"userName"})
	@Test(priority = 35, enabled=true)
	public void CheckCountriesListedInContributionWidget() throws Exception {
		System.out.println("TEST CASE NAME -> CheckCountriesListedInContributionWidget");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);
		TopN_DataValidation.checkListedCountries(driver);
		
//		signOutFlag = false;
//		openBrowser = false;
		
	}
	
	

	@Parameters({"userName"})
	@Test(priority = 38, enabled=true)
	public void Evaluate_TIV_and_AssetNum_51_Records_Today() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2011");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		//com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "Today");
		System.out.println("------------------------------------------------------------------------> For Today");
		Thread.sleep(2500);
		
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "Today", "51", "109.9 M");
		
//		signOutFlag = false;
//		openBrowser = false;
		
	}
	
	
	
	@Parameters({"userName"})
	@Test(priority = 40, enabled=true)
	public void Evaluate_TIV_and_AssetNum_51_Records_2011() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2011");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties","accuSummaryTIV");
		CommonUtilities.currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		System.out.println("------------------------------------------------------------------------> For 2011");
		//Thread.sleep(2500);
		
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "2011", "5", "30.8 M");
		
	}
	

	@Parameters({"userName"})
	@Test(priority = 45, enabled=true)
	public void Evaluate_TIV_and_AssetNum_51_Records_2012() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2012");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties","accuSummaryTIV");
		CommonUtilities.currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2012");
		System.out.println("------------------------------------------------------------------------> For 2012");
		Thread.sleep(2500);
		
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "2012", "15", "56.2 M");
		
	}
	

	@Parameters({"userName"})
	@Test(priority = 50, enabled=true)
	public void Evaluate_TIV_and_AssetNum_51_Records_2013() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2013");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties","accuSummaryTIV");
		CommonUtilities.currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2013");
		System.out.println("------------------------------------------------------------------------> For 2013");
		Thread.sleep(2500);
		
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "2013", "25", "75.2 M");
		
	}
	
	

	@Parameters({"userName"})
	@Test(priority = 55, enabled=true)
	public void Evaluate_TIV_and_AssetNum_51_Records_2014() throws Exception {
		System.out.println("TEST CASE NAME -> Evaluate_TIV_and_AssetNum_51_Records_2014");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
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
	
	

	
	
/*
	@Parameters({"userName"})
	@Test(priority = 60, enabled=true)
	public void accumulationSummary_51_Records_2015() throws Exception {
		System.out.println("TEST CASE NAME -> accumulationSummary_51_Records_2015");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Loaded Exposure -> " + exposureToLoad);
		Thread.sleep(500);

		
		String accuSummaryTIV = CommonUtilities.readElement("locatorPaths.properties","accuSummaryTIV");
		CommonUtilities.currentAccuTIV = driver.findElement(By.xpath(accuSummaryTIV)).getText();
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2015");
		System.out.println("------------------------------------------------------------------------> For 2015");
		Thread.sleep(2500);
		signOutFlag = true;
		openBrowser = true;
		
		System.out.println("Calling Validate TIV for 2015");
		CommonUtilities.validateTIV_Assets_InAccuSummary(driver, "2015", "46", "105.7 M");
		
		
		
	}
	
	
*/
	
	/*
	@Test(priority = 15, enabled=true)
	public void EvaluateAccumulationSummary() throws Exception {
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2012");
		System.out.println("------------------------------------------------------------------------> For 2012");
		Thread.sleep(2500);
		AccumulationSummary.accumulationSummary(driver);
		
	}
	

	@Test(priority = 15, enabled=true)
	public void accumulationSummary_2013() throws Exception {
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2013");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2013");
		Thread.sleep(2500);
		//AccumulationSummary.topN_Assets(driver);
		AccumulationSummary.accumulationSummary(driver);
		
	}
	

	@Test(priority = 25, enabled=true)
	public void accumulationSummary_2014() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		
		
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//-----------
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2014");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2014");
		Thread.sleep(2500);
		//AccumulationSummary.topN_Assets(driver);
		AccumulationSummary.accumulationSummary(driver);
		
	}
	
	

	@Test(priority = 35, enabled=true)
	public void accumulationSummary_2015() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		
		
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//-----------
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2015");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2015");
		Thread.sleep(2500);
		//AccumulationSummary.topN_Assets(driver);
		AccumulationSummary.accumulationSummary(driver);
		
	}
	
	
	

	@Parameters({"userName"})
	@Test(priority = 10, enabled=true)
	public void topN_AddSingleMeasure() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Single Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addSingleMeasure(driver, "Value Buildings", "accuSummary");
	}
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void topN_AddMultipleMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Multiple Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addMultipleMeasures(driver, "Value Buildings", "Value Contents", "Area", "AccuSummary");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 20, enabled=true)
	public void topN_AddMultipleMeasuresOneByOne() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Multiple Measures One by One...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addMultipleMeasuresOneByOne(driver, "Value Buildings", "Value Contents", "Area", "AccuSummary");
	}
	
	
	
	@Parameters({"userName"})
	@Test(priority = 25, enabled=true)
	public void topN_AddAllMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add All Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addAllMeasures(driver, "AccuSummary");
	}
	

	
	@Parameters({"userName"})
	@Test(priority = 30, enabled=true)
	public void topN_RemoveSingleMeasure() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Single Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeSingleMeasure(driver, "TIV", "AccuSummary");
	}
	
	

	@Parameters({"userName"})
	@Test(priority = 35, enabled=true)
	public void topN_RemoveMultipleMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Test Case Name -> Remove Multiple Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeMultipleMeasures(driver, "TIV", "Intensity", "Ground Up Loss", "AccuSummary");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 40, enabled=true)
	public void topN_RemoveMultipleMeasuresOneByOne() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Multiple Measures One by One...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeMultipleMeasuresOneByOne(driver, "TIV", "Intensity", "Ground Up Loss", "AccuSummary");
	}
	
	@Parameters({"userName"})
	@Test(priority = 45, enabled=true)
	public void topN_RemoveAllMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Measures...");
		TopN_Settings.click_Settings(driver, "AccuSummary");
		//TopN_Settings.checkDefaultMeasures(driver);
		System.out.println("Clicked on Settings...");
		TopN_Settings.removeAllMeasures(driver, "AccuSummary");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 50, enabled=true)
	public void setTopNDigit() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		//TopN_Settings.click_Settings(driver);
		//TopN_Settings.checkDefaultMeasures(driver);
		System.out.println("Test Case Name -> Setting Top Number...");
		TopN_Settings.setTopNDigit(driver, "3");
	}
	
	@Parameters({"userName"})
	@Test(priority = 55, enabled=true)
	public void validateTop10() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		//TopN_Settings.click_Settings(driver);
		//TopN_Settings.checkDefaultMeasures(driver);
		//TopN_Settings.setTopNDigit(driver, "3");
		
		System.out.println("Test Case Name -> Validate Top 10...");
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(3000);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		AccumulationSummary.printTable(driver);
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 60, enabled=true)
	public void topN_ClickResetButton() throws Exception {
		String loginID = "qa_user55@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		String sMeasures[] = {	"# Assets",
								"TIV",
								"Ground Up Loss",
								//"# People"
							};
		

		TopN_Settings.click_Settings(driver, "AccuSummary");
		TopN_Settings.checkDefaultMeasures(driver,sMeasures, "accuSummary");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 65, enabled=true)
	public void topN_ClickApplyButton() throws Exception {
		String loginID = "qa_user55@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		String sMeasures[] = {	"# Assets",
								"TIV",
								"Ground Up Loss",
								"Value Buildings",
								"Value Contents",
								"Area"
							};
		

		TopN_Settings.click_Settings(driver, "AccuSummary");
		
		TopN_Settings.addMultipleMeasuresOneByOne(driver, "Value Buildings", "Value Contents", "Area", "AccuSummary");
		
		String Header = "//div[@id='nspopover-1']";
		String selectExposureXpath = Header + TopN_Settings.readElement("locatorPaths.properties","TopNSettingsApply");
		driver.findElement(By.xpath(selectExposureXpath)).click();
		Thread.sleep(200);
		TopN_Settings.click_Settings(driver, "AccuSummary");
		
		TopN_Settings.checkDefaultMeasures(driver,sMeasures, "accuSummary");
	}
	
	@Parameters({"userName"})
	@Test(priority = 70, enabled=true)
	public void topN_ClickCancelButton() throws Exception {
		String loginID = "qa_user55@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		String sMeasures[] = {	"# Assets",
								"TIV",
								"Ground Up Loss",
								"Value Buildings",
								"Value Contents",
								"Area"
							};
		

		TopN_Settings.click_Settings(driver, "AccuSummary");
		TopN_Settings.addMultipleMeasuresOneByOne(driver, "Value Buildings", "Value Contents", "Area", "AccuSummary");
		
		TopN_Settings.checkDefaultMeasures(driver,sMeasures, "accuSummary");
	}
	
*/	
}

