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
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class TestSuite {

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

	
	@Test(priority = 5, enabled=true)
	public void concurrentUser1() throws Exception{
		//driver = new ChromeDriver();
		String url = "http://prism.beta1.qa.eigenrisk.com/";
		String userName = "qa_user1@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		DSImportConcurrentUser.login(driver, url, userName, password);
		//LoginToPrism.login(driver);
		Thread.sleep(1500);
		ClickExposure.clickExposure(driver);
		System.out.println("2");
		DSImportConcurrentUser.importNewExposure(driver);
		System.out.println("Session completed for " + userName);
		//LoginToPrism.signOut(driver);
	}
	
	@Test(priority = 10, enabled=false)
	public void concurrentUser2() throws Exception{
		//driver = new ChromeDriver();
		EigenRiskTest.dependencyMethod = "N";
		String url = "http://prism.beta1.qa.eigenrisk.com/";
		String userName = "qa_user2@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		DSImportConcurrentUser.login(driver, url, userName, password);
		LoginToPrism.login(driver);
		Thread.sleep(3000);
		LoginToPrism.signOut(driver);
	}
	
	
	@AfterTest
	public void tearDown() {
		//driver.close();
	}

}
