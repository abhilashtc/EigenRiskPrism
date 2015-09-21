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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.eigenRisk.functionalities.AccumulationSummary;
import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.CreateAnAccount;
import com.eigenRisk.functionalities.DSImportConcurrentUser;
import com.eigenRisk.functionalities.EigenRiskTest;
import com.eigenRisk.functionalities.ForgotPassword;
import com.eigenRisk.functionalities.LoginToPrism;
import com.eigenRisk.functionalities.TopN_Settings;
import com.google.common.base.Verify;

public class LoginPage_CreateAccount {

	WebDriver driver;
	Properties prop;
	
	@BeforeTest
	public void beforeTest() throws Exception {
		/*
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);////
		
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		*/
		System.out.println("#############################################< LOGIN - CREATE ACCOUNT >########################################################");
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
		System.out.println("#############################################< LOGIN - CREATE ACCOUNT >########################################################");
	}
	
	@BeforeMethod
	public void openBrowser() throws Exception {
		FileReader file = new FileReader("Config.properties");
		prop = new Properties();
		prop.load(file);
		driver = SelectBrowser.selectBrowser(prop.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
		LoginToPrism.handleFirefoxException();
		System.out.println("<<================================START================================>>");
	}
	
	@AfterMethod
	public void closeBrowser() throws Exception {
		System.out.println("\n<<--------------------------------END------------------------->>");
		Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);
	}
	
	
	@Test(priority = 1, enabled=false)
	public void clickCreateAnAccountLink() throws Exception{
		System.out.println("Test Case Name :- clickCreateAnAccountLink\n");
		CreateAnAccount.clickCreateAnAccount(driver);
	}
	
	
	//ValidFirstName
	@Test(priority = 5, enabled=false)
	public void input_ValidFirstName() throws Exception{
		System.out.println("Test Case Name :- ValidFirstName\n");
		String firstName = "Tommy";
		CreateAnAccount.inputValidFirstName(driver, firstName);
	}
	
	//InvalidFirstName
	@Test(priority = 10, enabled=false)
	public void input_InvalidFirstName() throws Exception{
		System.out.println("Test Case Name :- InvalidFirstName\n");
		String firstName = "Tom1";
		CreateAnAccount.inputInvalidFirstName(driver, firstName);
	}
	

	
	//ValidLastName
	@Test(priority = 15, enabled=true)
	public void input_ValidLastName() throws Exception{
		System.out.println("Test Case Name :- ValidLastName\n");
		String lastName = "Hamilton";
		CreateAnAccount.inputValidLastName(driver, lastName);
	}

	
	//Already Registered but not activated email id
	@Test(priority = 17, enabled=true)
	public void CreateAccount_Registered_But_Not_ActivatedEmailID() throws Exception{
		System.out.println("Test Case Name :- ValidLastName\n");
		String lastName = "Hamilton";
		CreateAnAccount.registeredEmailFlag = true;
		CreateAnAccount.input_Registered_But_Not_ActivatedEmailID(driver, lastName);
		CreateAnAccount.registeredEmailFlag = false;
	}
	
	@Test(priority = 18, enabled=false)
	public void loginToPrism_Registered_But_Not_ActivatedEmailID() throws Exception{
		System.out.println("Test Case Name :- loginToPrism_Registered_But_Not_ActivatedEmailID\n");
		//String userName = "qa_userInvalid@eigenrisk.com";
		String password = "eigenriskQA@123";
		
		System.out.println("Test Data>>");
		System.out.println("-----------");
		System.out.println("User Name is :- " + CreateAnAccount.loginID);
		System.out.println("Password is :- " + password);
		
//		LoginToPrism.login(driver,CreateAnAccount.loginID,password);
		LoginToPrism.login(driver,"tom.hamilton_1438930036113@eigenrisk.com",password);
		System.out.println(">>> " + LoginToPrism.msgString);
//		Assert.assertTrue(LoginToPrism.msgString.equals(userName + " is not registered. Please create an account !!"));
		Assert.assertTrue(LoginToPrism.msgString.equals("Error: The User ID exists but has not been activated. Please activate your account by referring to the account activation email."));
		System.out.println(">>> " + LoginToPrism.msgString);
		/*Thread.sleep(200);
		driver.quit();
		Thread.sleep(500);*/
	}

			
	//InvalidLastName
	@Test(priority = 20, enabled=false)
	public void input_InvalidLastName() throws Exception{
		System.out.println("Test Case Name :- InvalidLastName\n");
		String lastName = "Hamilton23";
		CreateAnAccount.inputInvalidLastName(driver, lastName);
	}

	
	//inputAlreadyRegisteredEmail
	@Test(priority = 25, enabled=false)
	public void input_AlreadyRegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- AlreadyRegisteredEmail\n");
		String email = "abhilashtc@eigenrisk.com";
		CreateAnAccount.inputAlreadyRegisteredEmail(driver, email);
	}
	
	//inputUnRegisteredEmail
	@Test(priority = 30, enabled=true)
	public void input_UnRegisteredEmail() throws Exception{
		System.out.println("Test Case Name :- UnRegisteredEmail\n");
		String email = "abhilashtc@eigenrisk.com";
		CreateAnAccount.inputUnRegisteredEmail(driver, email);
	}
	
	//inputPasswordMismatch
	@Test(priority = 35, enabled=false)
	public void input_PasswordMismatch() throws Exception{
		System.out.println("Test Case Name :- Password Mismatch\n");
		String password2 = "abhilashtc@eigenrisk.com";
		CreateAnAccount.inputPasswordMismatch(driver, password2);
	}
}

