package com.eigenRisk.TestSuite;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.commonutilities.SelectBrowser;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;

public class Top_N_List {

	WebDriver driver;
	Properties prop;
	String exposureToImport = "TestData/Karthika_Global_Demo_Data V 0 8.xlsx";
	String exposureToDelete = "Karthika_Global_Demo_Data";
	
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
		System.out.println("#############################################< TOP N >########################################################");
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
		System.out.println("#############################################< TOP N >########################################################");
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
		//String loginID = "qa_user55@eigenrisk.com";
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
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
	@Test(priority = 5, enabled=true)
	public void topN_CheckDefaultMeasures() throws Exception {
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Test Case Name -> Check Default Measures...");

		String sMeasures[] = {	"TIV",
								"Ground Up Loss",
								"Damage (%)",
								"Intensity",
								//"# People"
							};
		

		TopN_Settings.click_Settings(driver, "TopN");
		TopN_Settings.checkDefaultMeasures(driver,sMeasures, "TopN");
	}
	
	@Parameters({"userName"})
	@Test(priority = 10, enabled=true)
	public void topN_AddSingleMeasure() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Single Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addSingleMeasure(driver, "Value Buildings", "TopN");
	}
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void topN_AddMultipleMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Multiple Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addMultipleMeasures(driver, "Value Buildings", "Value Contents", "Area", "TopN");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 20, enabled=true)
	public void topN_AddMultipleMeasuresOneByOne() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add Multiple Measures One by One...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addMultipleMeasuresOneByOne(driver, "Value Buildings", "Value Contents", "Area", "TopN");
	}
	
	
	
	@Parameters({"userName"})
	@Test(priority = 25, enabled=true)
	public void topN_AddAllMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Add All Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.addAllMeasures(driver, "TopN");
	}
	

	
	@Parameters({"userName"})
	@Test(priority = 30, enabled=true)
	public void topN_RemoveSingleMeasure() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Single Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeSingleMeasure(driver, "TIV", "TopN");
	}
	
	

	@Parameters({"userName"})
	@Test(priority = 35, enabled=true)
	public void topN_RemoveMultipleMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		
		System.out.println("Test Case Name -> Remove Multiple Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeMultipleMeasures(driver, "TIV", "Intensity", "Ground Up Loss", "TopN");
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 40, enabled=true)
	public void topN_RemoveMultipleMeasuresOneByOne() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Multiple Measures One by One...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		TopN_Settings.removeMultipleMeasuresOneByOne(driver, "TIV", "Intensity", "Ground Up Loss", "TopN");
	}
	
	@Parameters({"userName"})
	@Test(priority = 45, enabled=true)
	public void topN_RemoveAllMeasures() throws Exception {
		//login_ImportExposure(driver, exposureToImport);
		
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		System.out.println("Test Case Name -> Remove Measures...");
		TopN_Settings.click_Settings(driver, "TopN");
		//TopN_Settings.checkDefaultMeasures(driver);
		System.out.println("Clicked on Settings...");
		TopN_Settings.removeAllMeasures(driver, "TopN");
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
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(3000);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		AccumulationSummary.printTable(driver);
	}
	
	
}

