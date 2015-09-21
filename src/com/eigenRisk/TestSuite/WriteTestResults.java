package com.eigenRisk.TestSuite;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.commonutilities.SelectBrowser;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
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

public class WriteTestResults {

	WebDriver driver;
	Properties prop;
	String exposureToImport = "TestData/Karthika_Global_Demo_Data V 0 8.xlsx";
	String exposureToDelete = "Karthika_Global_Demo_Data";
	
	/*@BeforeTest
	public void beforeTest() throws Exception {
		
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		
		System.out.println("#############################################< TOP N >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		
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
	
		*/
	@Parameters({"userName"})
	@Test(priority = 5, enabled=true)
	public void WriteTestResults() throws Exception {
		System.out.println("Creating Emailable Report...");
		Reporter.getCurrentTestResult();
		Thread.sleep(1500);
		org.eigenrisk.commonutilities.CreateTestReport.moveEmailableReport();
	}
	
}