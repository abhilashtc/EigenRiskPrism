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
import com.eigenRisk.functionalities.ProgramBuilder;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class Program_Builder {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< PROGRAM BUILDER >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< PROGRAM BUILDER >########################################################");
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
		
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		*/
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n--------------------------------END------------------------->>");
		Thread.sleep(200);
		
		Thread.sleep(1000);
		System.out.println("Going to SignOut...");
		
		
		LoginToPrism.signOut(driver);
		Thread.sleep(500);
		driver.quit();
		
	}
	
		
	
	@Parameters({"userName"})
	@Test(priority = 1, enabled=true)
	public void PB_OpenProgramBuilder() throws Exception {
		System.out.println("TEST CASE NAME -> Open Program Builder");
//		String loginID = "qa_user55@eigenrisk.com";
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		System.out.println("Calling PB...");
		ProgramBuilder.clickProgramBuilder(driver);
		/*
		Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
*/
	}
	
	@Parameters({"userName"})
	@Test(priority = 5, enabled=true)
	public void CheckDefaultProgramName() throws Exception {
		System.out.println("TEST CASE NAME -> Check Default Program Name");
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.checkProgramName(driver, "Untitled Program");
		
	/*	Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();*/
	}
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void AddLayer() throws Exception {
		System.out.println("TEST CASE NAME -> Add Layer");
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.createLayer(driver, "75", "25");
		ProgramBuilder.enterNewProgramName(driver, "NewProgram-");
		
		/*
		ProgramBuilder.clickProgramBuilder(driver);
		//ProgramBuilder.defaultProgramName(driver);
		ProgramBuilder.addLayer(driver);
		*/
		/*
		Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
		*/
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void EnterNewProgramName() throws Exception {
		System.out.println("TEST CASE NAME -> Save Program with a Name");
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.createLayer(driver, "60", "40");
		ProgramBuilder.enterNewProgramName(driver, "NewProgram-");
		
		/*Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();*/
		
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 20, enabled=true)
	public void ShareProgram() throws Exception {
		System.out.println("TEST CASE NAME -> Share Program");
		/*EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);*/
		
		String loginID = "su@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "Global Demo Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.removeNewProgramName(driver);
		ProgramBuilder.createLayer(driver, "70", "30");
		ProgramBuilder.enterNewProgramName(driver, "NewProgram-");
		 
		ProgramBuilder.shareProgram(driver, "New Program A2", "abhilashtc@eigenrisk.com");
	
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 25, enabled=true)
	public void ValidateSharedProgram() throws Exception {
		System.out.println("TEST CASE NAME -> Validate Shared Program");
		
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "Karthika_Global_Demo_Data …";
		String exposureToLoad = "51_Records";
		
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.validateSharedProgram(driver);
		/*
		LoginToPrism.signOut(driver);
		driver.close();*/
	}
	
	@Parameters({"userName"})
	@Test(priority = 30, enabled=true) 
	public void DeleteSharedProgram() throws Exception {
		System.out.println("TEST CASE NAME -> Delete Program");
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		//ProgramBuilder.progName = "NewProgram-1439571972363";
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.deleteProgram(driver);
		System.out.println("Successfully Removed....................................");

	}

	
	@Parameters({"userName"})
	@Test(priority = 35, enabled=true)
	public void ValidateDeletedProgramExistOnSource() throws Exception {
		System.out.println("TEST CASE NAME -> Validate Deleted Program is not deleted on the Source User");
		String loginID = "su@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "Global Demo Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		//ProgramBuilder.progName = "NewProgram-1439872280893";
		System.out.println("Validating that " + ProgramBuilder.progName + " exists ");
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.validateSharedProgram(driver);
		/*
		LoginToPrism.signOut(driver);
		driver.close();*/
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 40, enabled=true) 
	public void DetailView() throws Exception {
		System.out.println("TEST CASE NAME -> Detail View");
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);

	}
	
	
	/* Only for maintenance purpose
	
	@Parameters({"userName"})
	@Test(priority = 100, enabled=true) 
	public void DeleteALLProgram() throws Exception {
		System.out.println("TEST CASE NAME -> Delete Program");
		//String loginID = "skmedikonda@eigenrisk.com";
		//LoginToPrism.login(driver,loginID,"medikonda*MN#1");
		
		//Deleting all the new programs for the 1st user
//		String loginID = "abhilashtc@eigenrisk.com";
		String loginID = "su@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		System.out.println("Clicking on Exposure");
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		
//		String exposureToLoad = "51_Records";
		String exposureToLoad = "51_Records";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		
		ProgramBuilder.clickProgramBuilder(driver);
		ProgramBuilder.deleteAllProgram(driver);
		LoginToPrism.signOut(driver);
		

	}
	
	*/
}

