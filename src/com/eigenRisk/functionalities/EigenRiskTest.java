package com.eigenRisk.functionalities;

import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EigenRiskTest {
	
	public static String testCaseName, testCaseResult, testResultDesc, testCaseWarning, dependencyMethod;
	public static String err;
	public static int flag=0;

	public static void main(String[] args) throws Exception {
		/*System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		SignIn.driver = new ChromeDriver();
		*/

		System.out.println("*****************START RUN******************");
		
//Test Cases
		//Test Case 1
		EigenRiskTest.runLoginToPrism();
		
		//Test Case 2
		//EigenRiskTest.runLoadExposure();
		
		//Test Case 3
		//EigenRiskTest.runLoadSavedFile();
		
		//Test Case 4
		//EigenRiskTest.runSwitchToUS_DemoData();
		
		//Test Case 5
		//EigenRiskTest.runTopAssets();

		System.out.println("*****************END RUN******************");
		//LoginToPrism.driver.close();

	}
	
	
	public static WebDriver getDriver() throws Exception{
		FileReader propertyFile = new FileReader("LoginDetails.properties");
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		WebDriver driver = null;
		String wBrowser = configProperty.getProperty("WebBrowser");
		
		System.out.println("Web Browser to be used is :- " + wBrowser);
		wBrowser = wBrowser.toUpperCase();
		
		if (wBrowser.equalsIgnoreCase("FIREFOX")) {
			driver = new FirefoxDriver();
		}
		if (wBrowser.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}
	
	public static void ResetTestSummary() {
		EigenRiskTest.testCaseName="";
		EigenRiskTest.testCaseResult="";
		if(EigenRiskTest.flag == 1)
			if (EigenRiskTest.dependencyMethod.equals("N") )
				EigenRiskTest.testResultDesc = "";

		EigenRiskTest.dependencyMethod="N";
		EigenRiskTest.testCaseWarning = "";
		EigenRiskTest.flag = 1;
	}
	
	public static void runLoginToPrism() throws Exception {
		EigenRiskTest.ResetTestSummary();
		EigenRiskTest.testCaseName="Login to Prism";
				
		LoginToPrism.driver = EigenRiskTest.getDriver();
		LoginToPrism.login(LoginToPrism.driver);
		//System.out.println(LoginToPrism.driver.getTitle());
		LoginToPrism.driver.close();
	}
	
	//In Progress
	public static void runSwitchToUS_DemoData() throws Exception {
		EigenRiskTest.ResetTestSummary();

		//Dependency Program 
		EigenRiskTest.dependencyMethod = "Y";
		EigenRiskTest.testCaseName="Login to Prism ~ (Invoked from runLoadExposure)";
		LoginToPrism.driver = EigenRiskTest.getDriver();
		LoginToPrism.login(LoginToPrism.driver);


		if (EigenRiskTest.testCaseResult.equals("PASS")) {
			System.out.println("Dependency Test Case (Login) - PASSED");
			EigenRiskTest.ResetTestSummary();
			EigenRiskTest.testCaseName = "Switch to US Demo Data";

//			LoadNewExposure.loadSavedFile(LoginToPrism.driver);
			System.out.println(LoginToPrism.driver.getTitle());
			//LoginToPrism.driver.close();
		}
		else {
			//System.out.println("Dependency Test Case (Login) - FAILED");
			EigenRiskTest.testCaseName="Load Demo Data";
			EigenRiskTest.testResultDesc = "Dependency Script (Login to Prism) Failed; Details:-" + EigenRiskTest.err;
			
		}

		EigenRiskTest.printTestResult();
	}
	
	public static void runLoadExposure() throws Exception {
		//Load Exposure
		EigenRiskTest.ResetTestSummary();

		//Dependency Program 
		EigenRiskTest.dependencyMethod = "Y";
		EigenRiskTest.testCaseName="Login to Prism ~ (Invoked from runLoadExposure)";
		LoginToPrism.driver = EigenRiskTest.getDriver();
		LoginToPrism.login(LoginToPrism.driver);
		
		
		if (EigenRiskTest.testCaseResult.equals("PASS")) {
			EigenRiskTest.testResultDesc = EigenRiskTest.testResultDesc + "Dependency Test Case (Login) - PASSED";
			//System.out.println("Dependency Test Case (Login) - PASSED");
			EigenRiskTest.ResetTestSummary();
			EigenRiskTest.testCaseName = "Load Exposure";

//			LoadNewExposure.loadNewExposures(LoginToPrism.driver);
			System.out.println(LoginToPrism.driver.getTitle());
			//LoginToPrism.driver.close();
		}
		else {
			//System.out.println("Dependency Test Case (Login) - FAILED");
			EigenRiskTest.testCaseName="Load Exposure";
			EigenRiskTest.testResultDesc = "Dependency Script (Login to Prism) Failed; Details:-" + EigenRiskTest.err;
			EigenRiskTest.printTestResult();
		}
		//LoginToPrism.driver.close();
	}
	
	public static void runTopAssets() throws Exception {
		//Load Exposure
		EigenRiskTest.ResetTestSummary();

		//Dependency Program 
		EigenRiskTest.dependencyMethod = "Y";
		EigenRiskTest.testCaseName="Login to Prism ~ (Invoked from Top N Widget)";
		LoginToPrism.driver = EigenRiskTest.getDriver();
		LoginToPrism.login(LoginToPrism.driver);


		if (EigenRiskTest.testCaseResult.equals("PASS")) {
			EigenRiskTest.testResultDesc = EigenRiskTest.testResultDesc + "Dependency Test Case (Login) - PASSED";
			//System.out.println("Dependency Test Case (Login) - PASSED");
			EigenRiskTest.ResetTestSummary();
			EigenRiskTest.testCaseName = "Top N Assets";
			
//			TopWidgets.defaultTop10Assets(LoginToPrism.driver);
			//LoadNewExposure.loadNewExposures(LoginToPrism.driver);
			//TopWidgets.defaultTop10Assets(LoginToPrism.driver);
			
			System.out.println(LoginToPrism.driver.getTitle());
			//LoginToPrism.driver.close();
		}
		else {
			//System.out.println("Dependency Test Case (Login) - FAILED");
			EigenRiskTest.testCaseName="Top N Assets";
			EigenRiskTest.testResultDesc = "Dependency Script (Login to Prism) Failed; Details:-" + EigenRiskTest.err;
			EigenRiskTest.printTestResult();
		}
		//LoginToPrism.driver.close();
	}
	
	public static void runLoadSavedFile() throws Exception {
		//Load Exposure
		EigenRiskTest.ResetTestSummary();

		//Dependency Program 
		EigenRiskTest.dependencyMethod = "Y";
		EigenRiskTest.testCaseName="Login to Prism ~ (Invoked from runLoadExposure)";
		LoginToPrism.driver = EigenRiskTest.getDriver();
		LoginToPrism.login(LoginToPrism.driver);


		if (EigenRiskTest.testCaseResult.equals("PASS")) {
			System.out.println("Dependency Test Case (Login) - PASSED");
			EigenRiskTest.ResetTestSummary();
			EigenRiskTest.testCaseName = "Load Saved File";

//			LoadNewExposure.loadSavedFile(LoginToPrism.driver);
			System.out.println(LoginToPrism.driver.getTitle());
			//LoginToPrism.driver.close();
		}
		else {
			//System.out.println("Dependency Test Case (Login) - FAILED");
			EigenRiskTest.testCaseName="Load Demo Data";
			EigenRiskTest.testResultDesc = "Dependency Script (Login to Prism) Failed; Details:-" + EigenRiskTest.err;
			
		}

		EigenRiskTest.printTestResult();
	}
	
	public static void printTestResult(){
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Test Case Name : " + EigenRiskTest.testCaseName);
		System.out.println("Test Result : " + EigenRiskTest.testCaseResult);
		System.out.println("Description : " + EigenRiskTest.testResultDesc);
		System.out.println("---------------------------------------------------------------------------");

	}
}

