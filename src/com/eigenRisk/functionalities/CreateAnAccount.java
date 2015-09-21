package com.eigenRisk.functionalities;

import java.io.FileReader;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAnAccount {
	public static String loginID;
	public static boolean registeredEmailFlag;
	
	public static void clickCreateAnAccount(WebDriver driver) throws Exception {
		String url = readElement("LoginDetails.properties","URL");
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(200);
		
		String xp_userName = readElement("WebElement_XPath.properties","userNameField");
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp_userName)));
		
		String createAccount = readElement("locatorPaths.properties","createAccount");
		WebElement createAccount1 = driver.findElement(By.xpath(createAccount));

		createAccount1.click();
		Thread.sleep(2000);
		
		//https://prism.beta1.qa.eigenrisk.com/auth/forgot-password
		String currentURL = driver.getCurrentUrl();
		String url_L15 = currentURL.substring(currentURL.length() - 8);

		Assert.assertTrue(url_L15.equals("register"));
		System.out.println("On Account Creation Page...");
		
	}
	
	public static void inputValidFirstName(WebDriver driver, String firstName) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_FName", firstName);
		String cURL = driver.getCurrentUrl();
		//activation-message
		String Url_1 = cURL.substring(cURL.length() - 18);
		//System.out.println("Currently on " + Url_1);
		Thread.sleep(250);
		Assert.assertTrue(Url_1.equals("activation-message"));
		System.out.println("\nAccount Creation Succeded...");
	}
	

	public static void inputInvalidFirstName(WebDriver driver, String firstName) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_FName", firstName);
		Thread.sleep(250);
		Assert.assertTrue(message.equals("First Name can contain only letters (a-z A-Z)."));
	}

	
	public static void inputUnRegisteredEmail(WebDriver driver, String email) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_email_NULL", email);
		String cURL = driver.getCurrentUrl();
		//activation-message
		String Url_1 = cURL.substring(cURL.length() - 18);
		//System.out.println("Currently on " + Url_1);
		Thread.sleep(250);
		Assert.assertTrue(Url_1.equals("activation-message"));
		System.out.println("\nAccount Creation Succeded...");
	}
	
	//Email address already in use!
	public static void inputAlreadyRegisteredEmail(WebDriver driver, String email) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_email", email);
		Thread.sleep(250);
//		Assert.assertTrue(message.equals("Email address already in use!"));
		Assert.assertTrue(message.equals("Error: This email already exists"));
	}
	
	
	public static void inputInvalidLastName(WebDriver driver, String lastName) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_LName", lastName);
		Thread.sleep(250);
		System.out.println("Invalid Last Name -> " + message);
		Assert.assertTrue(message.equals("Last Name can contain only letters (a-z A-Z)."));
	}
	
	//inputValidLastName
	public static void inputValidLastName(WebDriver driver, String lastName) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_LName", lastName);
		String cURL = driver.getCurrentUrl();
		//activation-message
		String Url_1 = cURL.substring(cURL.length() - 18);
		Thread.sleep(250);
		Assert.assertTrue(Url_1.equals("activation-message"));
		System.out.println("\nAccount Creation Succeded...");
	}
	
	
	//input_Registered_But_Not_ActivatedEmailID
	public static void input_Registered_But_Not_ActivatedEmailID(WebDriver driver, String lastName) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_LName", lastName);
		String cURL = driver.getCurrentUrl();
		//activation-message
		String Url_1 = cURL.substring(cURL.length() - 18);
		Thread.sleep(250);
		Assert.assertTrue(message.equals("Error: The User ID exists but has not been activated. Please activate your account by referring to the account activation email."));
		System.out.println("\n" + message);
	}
	
	
	
	//Password Mismatch.
	public static void inputPasswordMismatch(WebDriver driver, String repeatPassword) throws Exception{
		clickCreateAnAccount(driver);
		String message = fillAccountCreationInfo(driver, "register_cPassword", repeatPassword);
		Thread.sleep(250);
		Assert.assertTrue(message.equals("Password Mismatch."));
	}
	
	public static String fillAccountCreationInfo(WebDriver driver, String fieldName, String fieldValue) throws Exception
	{
		String fName = readElement("locatorPaths.properties","register_FName");
		WebElement fName_1 = driver.findElement(By.xpath(fName));
		fName_1.sendKeys("Tom");

		String lName = readElement("locatorPaths.properties","register_LName");
		WebElement lName_1 = driver.findElement(By.xpath(lName));
		lName_1.sendKeys("Hamilton");
		String email_id;
		
		if (registeredEmailFlag == true)
			email_id = loginID;
		else
			email_id = "tom.hamilton_" + System.currentTimeMillis() + "@eigenrisk.com";
		
		String email = readElement("locatorPaths.properties","register_email");
		WebElement email_1 = driver.findElement(By.xpath(email));
		email_1.sendKeys(email_id);
		loginID = email_id;

		String password = readElement("locatorPaths.properties","register_password");
		WebElement password_1 = driver.findElement(By.xpath(password));
		password_1.sendKeys("eigenriskQA@123");

		String cPassword = readElement("locatorPaths.properties","register_cPassword");
		WebElement cPassword_1 = driver.findElement(By.xpath(cPassword));
		cPassword_1.sendKeys("eigenriskQA@123");

		String signUp = readElement("locatorPaths.properties","register_SignUp");
		WebElement signUp_1 = driver.findElement(By.xpath(signUp));
		
		//System.out.println("Field Name is " + fieldName + " ; and value is " + fieldValue );
		
		System.out.println(readElement("locatorPaths.properties",fieldName));
	
		if(readElement("locatorPaths.properties",fieldName) != null ) {
			driver.findElement(By.xpath(readElement("locatorPaths.properties",fieldName))).clear();
			driver.findElement(By.xpath(readElement("locatorPaths.properties",fieldName))).sendKeys(fieldValue);
		}
		
		System.out.println("Test Data:-");
		System.out.println("===========");
		
		if (fieldName.equals("register_FName"))
			System.out.println("First Name 		:- " + fieldValue);
		else
			System.out.println("First Name 		:- " + "Tom");
		
		if (fieldName.equals("register_LName"))
			System.out.println("Last Name 		:- " + fieldValue);
		else
			System.out.println("Last Name 		:- " + "Hamilton");
		
		if (fieldName.equals("register_email"))
			System.out.println("Email ID 		:- " + fieldValue);
		else
			System.out.println("Email ID 		:- " + email_id);
		
		if (fieldName.equals("register_Password"))
			System.out.println("Password 		:- " + fieldValue);
		else
			System.out.println("Password 		:- " + "eigenriskQA@123");

		if (fieldName.equals("register_cPassword"))
			System.out.println("Repeat Password 	:- " + fieldValue);
		else
			System.out.println("Repeat Password 	:- " + "eigenriskQA@123");

		
		//System.out.println("First Name 		:- " + fName);
		
		Thread.sleep(3000);
		signUp_1.click();
		
		
		
		boolean present;
		int ctr=0;
		while(true) {
            try {
            	  //System.out.println("Inside While...");
            	  ctr++;
            	  if(ctr > 100)
            		  break;
            	  
                  driver.findElement(By.xpath("//p[@ng-repeat='error in registerError']"));
                  WebElement WE = driver.findElement(By.xpath("//p[@ng-repeat='error in registerError']"));
                  String msg = WE.getText();
                  System.out.println("\nNotification Message is -> " + msg);
                  if(msg.length() > 0) {
                	  return msg;
                	  //break;
                  }
            } catch (NoSuchElementException e) {
                  present = false;
            }
		}

		return "FAILED";
		
	}
	
	/*
	public static void resetPassword_UnRegisteredEmail(WebDriver driver, String emailID) throws Exception {
		
		String alertMessage = clickResetPassword(driver, emailID);
		Assert.assertTrue(alertMessage.equals("User is not registered"));
		
	}
	
	
	public static void resetPassword_RegisteredEmail(WebDriver driver, String emailID) throws Exception {
		
		String alertMessage = clickResetPassword(driver, emailID);
		Assert.assertTrue(alertMessage.equals("resetPassword_RegisteredEmail"));
		
	}
	
	public static String clickResetPassword(WebDriver driver, String emailID) throws Exception {
		//clickForgotPasswordLink(driver);
		String forgotPwdEmail = readElement("locatorPaths.properties","forgotPwdEmail");
		WebElement forgotPwd = driver.findElement(By.xpath(forgotPwdEmail));
		
		String forgotPwdReset = readElement("locatorPaths.properties","forgotPwdReset");
		WebElement forgotPwdReset1 = driver.findElement(By.xpath(forgotPwdReset));
		
		forgotPwd.sendKeys(emailID);
		forgotPwdReset1.click();
		
		boolean present;
		int ctr=0;
		while(true) {
            try {
            	  //System.out.println("Inside While...");
            	  ctr++;
            	  if(ctr > 2000)
            		  break;
            	  
                  driver.findElement(By.xpath("//div[@ng-show='response.message']"));
                  WebElement WE = driver.findElement(By.xpath("//div[@ng-show='response.message']"));
                  String msg = WE.getText();
                  System.out.println("Message is -> " + msg);
                  if(msg.length() > 0) {
                	  return msg;
                	  //break;
                  }
            } catch (NoSuchElementException e) {
                  present = false;
            }
		}

		return "FAILED";
		
	}
	*/
	public static String readElement(String fileName, String fieldName) throws Exception {
		FileReader propertyFile = new FileReader(fileName);
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		String fieldValue = configProperty.getProperty(fieldName);
		propertyFile.close();
		return fieldValue;
		
	}

}

