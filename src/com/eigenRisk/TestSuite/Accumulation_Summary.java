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

public class Accumulation_Summary {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< ACCUMULATION SUMMARY >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< ACCUMULATION SUMMARY >########################################################");
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
//		String loginID = "qa_user55@eigenrisk.com";
//		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
//		
//		String exposureToLoad = "Karthika_Global_Demo_Data";
//		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n--------------------------------END------------------------->>");
		Thread.sleep(200);
		
		Thread.sleep(1000);
		System.out.println("Going to SignOut...");
		
		/*
		LoginToPrism.signOut(driver);
		Thread.sleep(500);
		driver.quit();
		*/
	}
	
		
	
	@Parameters({"userName"})
	@Test(priority = 1, enabled=false)
	public void topN_CheckDefaultMeasures() throws Exception {
		System.out.println("Test Case -> Check Default Measures");
		String loginID = "abhilashtc@eigenrisk.com";
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");

		String sMeasures[] = {	"# Assets",
								"TIV",
								"Ground Up Loss",
								//"# People"
							};
		

		TopN_Settings.click_Settings(driver, "AccuSummary");
		TopN_Settings.checkDefaultMeasures(driver,sMeasures, "accuSummary");
	}
	
	@Test(priority = 5, enabled=true)
	public void accumulationSummary_2011() throws Exception {
		System.out.println("Test Case -> Accumulation Summary 2011");
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		System.out.println(LoginToPrism.msgString);
		//Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		System.out.println("1.........");
		Exposure_Import_Load.clickExposure(driver);
		System.out.println("2......");
		Thread.sleep(250);
		String exposureToLoad = "51_Records_TestData";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		System.out.println("3...........");
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		System.out.println("------------------------------------------------------------------------> For 2011");
		Thread.sleep(2500);
		AccumulationSummary.accumulationSummary(driver);
	}
	
	@Test(priority = 15, enabled=false)
	public void accumulationSummary_2012() throws Exception {
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
	

	@Test(priority = 15, enabled=false)
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
	

	@Test(priority = 25, enabled=false)
	public void accumulationSummary_2014() throws Exception {
		/*EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);*/
		
		
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
	
	

	@Test(priority = 35, enabled=false)
	public void accumulationSummary_2015() throws Exception {
		/*EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);*/
		
		
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
	@Test(priority = 10, enabled=false)
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
	@Test(priority = 15, enabled=false)
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
	@Test(priority = 20, enabled=false)
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
	@Test(priority = 25, enabled=false)
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
	@Test(priority = 30, enabled=false)
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
	@Test(priority = 35, enabled=false)
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
	@Test(priority = 40, enabled=false)
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
	@Test(priority = 45, enabled=false)
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
	@Test(priority = 50, enabled=false)
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
	@Test(priority = 55, enabled=false)
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
	@Test(priority = 60, enabled=false)
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
	@Test(priority = 65, enabled=false)
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
	@Test(priority = 70, enabled=false)
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
	
	
}

