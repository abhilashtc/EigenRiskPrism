package org.eigenrisk.testlab;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.eigenRisk.functionalities.AccumulationSummary;
import com.google.common.base.Verify;

public class Beta1TestSuite_1 {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void setUp() throws IOException {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		
	}

	
	@Test(priority = 5, enabled=false)
	public void loginToPrism() throws Exception{
		LoginToPrism.login(driver);
		//Thread.sleep(100);
		LoginToPrism.signOut(driver);
		System.out.println("Closing the WebDriver...");
		driver.close();
	}
	
	@Test(priority = 7, enabled=false)
	public void loginToPrismInvalidUserName() throws Exception{
		//driver = new ChromeDriver();
		//EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver,"abhilash.tc1@eigenrisk.com","eigenriskQA@123");
		//Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
		
	}
	
	@Test(priority = 9, enabled=false)
	public void loginToPrismInvalidPassword() throws Exception{
		//driver = new ChromeDriver();
		//EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver,"abhilash.tc@eigenrisk.com","eigenriskQA@1234");
		Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
	}
	

	@Test(priority = 15, enabled=false)
	public void importNewExposure() throws Exception {
		//EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		//ClickExposure.setExposurePath();
		
		Thread.sleep(2000);
		ClickExposure.clickExposure(driver);
		Thread.sleep(1000);
		ClickExposure.importNewExposure(driver);
		
		LoginToPrism.signOut(driver);
		driver.close();
		//AccumulationSummary.accumulationSummary(driver);
	}
	
	
	
	@Test(priority = 25, enabled=true)
	public void loadExposure() throws Exception {
		/*EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);*/
		
		
		LoginToPrism.login(driver,"abhilashtc@eigenrisk.com","eigenriskQA@123");
		Assert.assertTrue(LoginToPrism.msgString.length() > 0);
		Exposure_Import_Load.clickExposure(driver);
		Thread.sleep(250);
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		//-----------
		Thread.sleep(500);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2011");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2011");
		Thread.sleep(2500);
		//AccumulationSummary.topN_Assets(driver);
		AccumulationSummary.accumulationSummary(driver);
		
		/*
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2012");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2012");
		AccumulationSummary.topN_Assets(driver);
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2013");
		//AccumulationSummary.accumulationSummary(driver);
		System.out.println("------------------------------------------------------------------------> For 2013");
		AccumulationSummary.topN_Assets(driver);
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2014");
		AccumulationSummary.accumulationSummary(driver);
		
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2015");
		AccumulationSummary.accumulationSummary(driver);*/
		
		//ClickExposure.clickExposure(driver);
		//ClickExposure.loadExposure(driver);
	}

	
	@Test(priority = 35, enabled=false)
	public void topN_Assets() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		AccumulationSummary.topN_Assets(driver);
		//ClickExposure.loadExposure(driver);
		
	}
	
	
	@Test(priority = 45, enabled=false)
	public void ExportToExcel() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		
		AccumulationSummary.topN_Assets(driver);
		AccumulationSummary.ExportToExcel(driver);
		//ClickExposure.loadExposure(driver);
		
	}
	
	@Test(priority = 55, enabled=false)
	public void SelectDate() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		Thread.sleep(1000);
		//SelectExposure.loadExposure(driver);
		com.eigenRisk.functionalities.SelectDate.clickOnDateFilter(driver, "2014");
		
		//AccumulationSummary.topN_Assets(driver);
		//AccumulationSummary.ExportToExcel(driver);
		//ClickExposure.loadExposure(driver);
		
	}
	
	/*
	@Test(priority = 51, enabled=true)
	public void Test1() throws Exception {
		System.out.println("Inside Test1");
		Verify.verify(10 < 1);
		System.out.println("After Verify");
	}
	
	@Test(priority = 52, enabled=true)
	public void Test2() throws Exception {
		System.out.println("Inside Test2");
		Assert.assertEquals(21, 10);
		System.out.println("After Assert");
	}
	
	@Test(priority = 53, enabled=true)
	public void Test3() throws Exception {
		System.out.println("Inside Test3");
		Verify.verify(10 < 1);
		System.out.println("After Verify");
	}
		*/
	
	
	@Test(priority = 53, enabled=false)
	public void setTopNDigit() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		AccumulationSummary.setTopNDigit(driver, "11");
	}
	
	@Test(priority = 65, enabled=false)
	public void AccumulationSummary() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
		SelectExposure.loadExposure(driver);
		
		Thread.sleep(8000);
		AccumulationSummary.accumulationSummary(driver);
		
		//AccumulationSummary.topN_Assets(driver);
		//AccumulationSummary.ExportToExcel(driver);
		//ClickExposure.loadExposure(driver);
		
	}
	
	@Test(priority = 70, enabled=false)
	public void TopNSettings() throws Exception {
		EigenRiskTest.dependencyMethod = "N";
		//LoginToPrism.login(driver);
		
		
		String url = "https://prism.beta1.qa.eigenrisk.com/exposureAnalytics";
		String userName = "abhilashtc@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		DSImportConcurrentUser.login(driver, url, userName, password);
		
		Thread.sleep(2000);
		//SelectExposure.loadExposure(driver);
		System.out.println("Logged In...");
		DSImportConcurrentUser.importNewExposure(driver);
		Thread.sleep(3000);
		System.out.println("ImportNewExposure");
		TopN_Settings.Top_N_Settings(driver);
		//SelectExposure.loadExposure(driver);
		
		Thread.sleep(8000);
		//AccumulationSummary.accumulationSummary(driver);
		
		//AccumulationSummary.topN_Assets(driver);
		//AccumulationSummary.ExportToExcel(driver);
		//ClickExposure.loadExposure(driver);
		
	}

	@AfterTest
	public void tearDown() {
		//driver.close();
	}

}
