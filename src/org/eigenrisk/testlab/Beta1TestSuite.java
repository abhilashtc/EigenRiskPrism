package org.eigenrisk.testlab;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.eigenrisk.businesslogic.AssetDetails;
import org.eigenrisk.businesslogic.AssetDetailsSearchBox;
import org.eigenrisk.businesslogic.Login;
import org.eigenrisk.businesslogic.SelectExposure;
import org.eigenrisk.businesslogic.TopN;
import org.eigenrisk.commonutilities.SelectBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.LoginToPrism;

public class Beta1TestSuite {

	WebDriver driver;
	Properties prop;
	
	
	

	@BeforeTest
	public void setUp() throws IOException {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		
	}

	
	@Test(priority = 1)
	public void loginTest() throws IOException {
		driver.get(prop.getProperty("url"));
		System.out.println(driver.getTitle());
		driver.manage().window().maximize();
		Login.login(driver,"skmedikonda@eigenrisk.com","medikondaFILMS");

	}

	@Test(priority = 5)
	public void loadExposureTest() throws Exception {
		SelectExposure.loadExposure(driver);
	}

	@Test(priority = 10, enabled = false)
	public void topNTest() throws IOException {

		TopN.selectTopN(driver);
	}

	@Test(priority = 15)
	public void assetNameTest() throws IOException {
		AssetDetails.AssetName(driver);

	}

	@Test(priority = 20,enabled=false)
	public void assetDetailsSearchBoxTest() {

		AssetDetailsSearchBox.assetDetailsSearchBox(driver,"Area");

	}
	
	@Test(priority = 25)
	public void assetCharacteristicsTest(){
		
		AssetDetails.characteristics(driver);
		
	}
	
	@Test(priority = 30)
	public void loginToPrismTest() throws Exception{
		EigenRiskTest.dependencyMethod = "N";
		LoginToPrism.login(driver);
	}
	

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
