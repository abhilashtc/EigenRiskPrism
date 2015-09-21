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
import com.eigenRisk.functionalities.DamageModelManager;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.ForgotPassword;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.ProgramBuilder;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class Damage_ModelManager {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< DAMAGE MODEL MANAGER >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< DAMAGE MODEL MANAGER >########################################################");
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
		System.out.println("-------------------------------START-------------------------->>");
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
		System.out.println("\n--------------------------------END------------------------->>");
		Thread.sleep(200);
		
		Thread.sleep(1000);
		//System.out.println("Going to SignOut...");
		
		
		LoginToPrism.signOut(driver);
		Thread.sleep(500);
		driver.quit();
		
	}
	
	/*	
	@Test(priority = 1, enabled=true)
	public void CheckingDMMToolTipText() throws Exception {
		System.out.println("Test Case :- Hover over DMM icon");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.checkDMM_ToolTipText(driver);
		
	}*/
	
	@Test(priority = 5, enabled=true)
	public void NavigatingToDMM_And_CheckDamageModel_and_DamageFunction() throws Exception {
		System.out.println("Test Case :- Navigating to Damage Model Manager");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		
		com.eigenRisk.functionalities.DamageModelManager.Check_DamageModel_DamageFunction(driver);
		
	}
	
	@Test(priority = 10, enabled=true)
	public void SaveDamageModelWithBlankName() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Blank Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelBlankName(driver);
		
	}
	
	/*@Test(priority = 15, enabled=true)
	public void AddDamageModelWithoutAnyData() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithoutAnyData(driver);
		
	}*/
	
	@Test(priority = 20, enabled=true)
	public void AddDamageModelWithName() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		System.out.println("Calling Login");
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Login attempt Succeded...");
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, "Test Damage Model (Auto)", "Volcano", "EigenRisk", "NewModel");
		
	}
	
	@Test(priority = 25, enabled=true)
	public void AddDamageModelWithExistingModelName() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		Thread.sleep(1000);
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, "Test Damage Model (Auto)", "Volcano", "EigenRisk", "ExistingModel");
		
	}
	
	@Test(priority = 30, enabled=true)
	public void EditDamageModel() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageModel(driver, dmgName);
		
	}
	
	@Test(priority = 35, enabled=true)
	public void AddDamageFunctionZero() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		
	}
	
	@Test(priority = 40, enabled=true)
	public void AddDamageFunctionOne() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 1);
		
	}
	
	
	@Test(priority = 45, enabled=true)
	public void EditDamageFunctionNumberForOneToThree() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageFunctionNumberForOneToThree(driver, dmgName, 0);
		
	}
	
	
	@Test(priority = 50, enabled=true)
	public void EditDamageModelAddGraphWithValidData() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageModelGraphValidData(driver, dmgName, 3);
		
	}
	
	@Test(priority = 55, enabled=true)
	public void EditDamageModelAddGraphWithInValidData() throws Exception {
		System.out.println("Test Case :- EditDamageModelGraphWithInValidData");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.editDamageModelGraphInValidData(driver, dmgName, 1);
		
	}
	
	@Test(priority = 60, enabled=true)
	public void DeleteDamageFunction() throws Exception {
		System.out.println("Test Case :- DeleteDamageFunction");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageFunction(driver, dmgName, 1);
		
	}
	
	@Test(priority = 65, enabled=true)
	public void AddingMoreGraphRows() throws Exception {
		System.out.println("Test Case :- AddingMoreGraphRows");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)_AddingMoreGraphRows";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		//driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.AddingMoreGraphRows(driver, dmgName, 0);

		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	@Test(priority = 67, enabled=true)
	public void AddingMoreGraphRows_with_1_Mean_0_STD() throws Exception {
		System.out.println("Test Case :- AddingMoreGraphRows_with_1_Mean_0_STD");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)_AddingMoreGraphRows_with_1_Mean_0_STD";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		//driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		DamageModelManager.Mean_1_STD_0 = true;
		Thread.sleep(1500);
		DamageModelManager.addGraphRows = false;
		com.eigenRisk.functionalities.DamageModelManager.AddingMoreGraphRows(driver, dmgName, 0);
		DamageModelManager.addGraphRows = true;

		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	@Test(priority = 70, enabled=true)
	public void CopyPasteGraphDataFromExcel() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)_CopyPasteGraphDataFromExcel";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		//driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.CopyPasteGraphDataFromExcel(driver, dmgName, 0);
		
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	
	@Test(priority = 75, enabled=true)
	public void AddingDuplicateGraphRows() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)_AddingDuplicateGraphRows";
		com.eigenRisk.functionalities.DamageModelManager.addDamageModelWithName(driver, dmgName, "Volcano", "EigenRisk", "NewModel");
		System.out.println("Created Damage Model");
		//driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		com.eigenRisk.functionalities.DamageModelManager.addDamageFunction(driver, dmgName, 0);
		System.out.println("Created Damage Function");
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.AddingDuplicateGraphRows(driver, dmgName, 0);
		driver.findElement(By.xpath("//i[@class='fa fa-th']")).click();
		
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
	
	
	@Test(priority = 100, enabled=true)
	public void DeleteDamageModel() throws Exception {
		System.out.println("Test Case :- Add Damage Model with Name");
		String loginID = "abhilashtc@eigenrisk.com";
		//Login to Application
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		//String exposureToLoad = "Karthika_Global_Demo_Data";
		//Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//Thread.sleep(500);
		//String dmgName = "Sample Damage Function for All Perils";
		String dmgName = "Test Damage Model (Auto)";
		
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
		Thread.sleep(1500);
		com.eigenRisk.functionalities.DamageModelManager.deleteDamageModel(driver, dmgName);
	}
	
}
