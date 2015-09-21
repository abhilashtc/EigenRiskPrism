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

public class DeleteAllExposures {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< EXPOSURE - LOAD >########################################################");
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
		Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);
	}
	
	
	@Parameters({"userName"})
	@Test(priority = 15, enabled=true)
	public void DeleteAllExposure(@Optional("qa_user55@eigenrisk.com") String loginID) throws Exception{
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
		LoginToPrism.signOut(driver);
	}
	
}

