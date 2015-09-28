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
import com.eigenRisk.functionalities.DamageModelManager;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.ForgotPassword;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.ProgramBuilder;
import com.eigenRisk.functionalities.TopN_DataValidation;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class SmokeTestSuite {

	WebDriver driver;
	Properties prop;
	boolean openBrowser = true;
	boolean signOutFlag = true;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< SMOKE TEST SUITE >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< SMOKE TEST SUITE >########################################################");
	}
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		
//		driver = SelectBrowser.selectBrowser("chrome");
		
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		System.out.println("<<------ START ------>>");
		/*
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		*/
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n<<------ END ------>>");
		Thread.sleep(200);
		
		Thread.sleep(1000);
		//System.out.println("Going to SignOut...");
		
		
		LoginToPrism.signOut(driver);
		Thread.sleep(500);
		driver.quit();
		
	}
	

	
	//------------------------------------------------- Login ------------------------------------------------------
		
	
	
	@Test(priority = 1, enabled=false)
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
		System.out.println("Test Case : PASSED");
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	@Test(priority = 5, enabled=false)
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
		System.out.println("Test Case : PASSED");
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
	
	

	@Test(priority = 10, enabled=false)
	public void loginToPrism_ValidCredentials() throws Exception{
		System.out.println("Test Case Name :- loginToPrism\n");
		
		String userName = "abhilashtc@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + userName);
		System.out.println("Password is :- " + password);
		
		LoginToPrism.login(driver, userName, password);
		System.out.println(driver.getTitle());
		System.out.println("Test Case : PASSED");
		//LoginToPrism.signOut(driver);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}
		
	
	
	@Test(priority = 15, enabled=false)
	public void resetPassword_RegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- resetPassword_UnRegisteredEmail\n");
		ForgotPassword.resetPassword_RegisteredEmail(driver, "abhilashtc@eigenrisk.com");

	}
	
		
	@Test(priority = 20, enabled=false)
	public void click_ForgotPasswordLink() throws Exception{
		System.out.println("Test Case Name :- clickForgotPasswordLink\n");
		ForgotPassword.clickForgotPasswordLink(driver);
	}
	
	// END
	
	//----------------------------------------------------- Currency ---------------------------------------------------
	
	@Parameters({"userName"})
	@Test(priority = 25, enabled=false)
	public void EvaluateTIV_ForEachAsset() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateTIV_ForEachAsset");
		//String loginID = "skmedikonda@eigenrisk.com";
		//LoginToPrism.login(driver,loginID,"medikonda*MN#1");
		
		//Deleting all the new programs for the 1st user
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		Exposure to Load
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
		
		//AccumulationSummary.printTable(driver);
		
		
		
	}
	

	@Test(priority = 30, enabled=false)
	public void EvaluateTopN_Assets() throws Exception {
		openBrowser = false;
		TopN_DataValidation.EvaluateTopN_Assets();
		openBrowser = true;
		
	}
	
	//This test case has a dependency on EvaluateCurrencyConversionFor_CanadianDollar
	@Parameters({"userName"})
	@Test(priority = 35, enabled=false)
	public void EvaluateCurrencyConversionFor_EUR() throws Exception {
		System.out.println("TEST CASE NAME -> EvaluateCurrencyConversionFor_EURO");

		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records_TestData";
		String exposureToLoad = "5_Countries_Records …";
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
	
	
	//END
	
	//------------------------------------------------- Damage Model Manager ------------------------------------------------------
	//START
	
	
	@Test(priority = 60, enabled=false)
	public void AddDamageModelWithName() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		System.out.println("Calling Login");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Login attempt Succeded...");
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, "Test Damage Model (Auto)", "Volcano", "EigenRisk", "NewModel");
		
	}
	
	
	@Test(priority = 65, enabled=false)
	public void EditDamageModelChangePerilToEarthQuake() throws Exception {
		System.out.println("Test Case :- Edit Damage Model");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageModel(driver, dmgName);
		
	}
	
	@Test(priority = 70, enabled=false)
	public void AddDamageFunctionZero() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		
	}
	
	@Test(priority = 75, enabled=false)
	public void AddDamageFunctionOne() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 1);
		
	}
	
	
	@Test(priority = 80, enabled=false)
	public void EditDamageFunctionNumberForOneToThree() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageFunctionNumberForOneToThree(driver, dmgName, 0);
		
	}
	
	
	
	@Test(priority = 85, enabled=false)
	public void DeleteDamageFunction() throws Exception {
		System.out.println("Test Case :- DeleteDamageFunction");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageFunction(driver, dmgName, 1);
	}
	
	@Test(priority = 90, enabled=true)
	public void AddingMoreGraphRows() throws Exception {
		System.out.println("Test Case :- AddingMoreGraphRows");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)_AddingMoreGraphRows";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.AddingMoreGraphRows(driver, dmgName, 0);

		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	@Test(priority = 95, enabled=true)
	public void CopyPasteGraphDataFromExcel() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)_CopyPasteGraphDataFromExcel";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.CopyPasteGraphDataFromExcel(driver, dmgName, 0);
		
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
		
	@Test(priority = 100, enabled=true)
	public void DeleteDamageModel() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	//END

}

