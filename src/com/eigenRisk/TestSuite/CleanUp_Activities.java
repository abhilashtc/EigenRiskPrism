package com.eigenRisk.TestSuite;

import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.eigenrisk.commonutilities.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.eigenRisk.cleanUp.CleanUpExposures;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.HelpButton;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.ProgramBuilder;

public class CleanUp_Activities {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		System.out.println("#############################################< CLEAN UP >########################################################");
	}
	
	@AfterTest
	public void afterTest() throws Exception {
		System.out.println("#############################################< CLEAN UP >########################################################");
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
		System.out.println("Going to SignOut...");
		
		
		LoginToPrism.signOut(driver);
		Thread.sleep(500);
		driver.quit();
		
	}
	
		
	
	@Parameters({"userName"})
	@Test(priority = 1, enabled=true)
	public void deleteAllExposures_for_abhilashtc() throws Exception {
		System.out.println("Test Case -> deleteAllExposures_for_abhilashtc");
//		String loginID = "qa_user55@eigenrisk.com";
		String loginID = "abhilashtc@eigenrisk.com";
		
		LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		/*
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		*/
		//HelpButton.DownloadTemplate(driver);
		CleanUpExposures.deleteExposures(driver);
		/*
		Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
*/
	}
	
	@Parameters({"userName"})
	@Test(priority = 1, enabled=false)
	public void SetTemplateName() throws Exception {
		System.out.println("Download Template -> Excel");
//		String loginID = "qa_user55@eigenrisk.com";
		String loginID = "abhilashtc@eigenrisk.com";
		
		//LoginToPrism.login(driver,loginID,"eigenriskQA@123");
		/*
		String exposureToLoad = "Karthika_Global_Demo_Data";
		Exposure_Import_Load.loadExposure(driver, exposureToLoad);
		Thread.sleep(500);
		*/
//		HelpButton.DownloadTemplate(driver);
		//HelpButton.addEventsToTemplate();
		/*
		Thread.sleep(500);
		LoginToPrism.signOut(driver);
		driver.close();
*/
	}
	
}
