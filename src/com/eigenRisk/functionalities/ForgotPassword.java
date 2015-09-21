package com.eigenRisk.functionalities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassword {
	public static void clickForgotPasswordLink(WebDriver driver) throws Exception {
		String url = readElement("LoginDetails.properties","URL");
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(200);
		
		
		String xp_userName = readElement("WebElement_XPath.properties","userNameField");
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp_userName)));
		
		String forgotPassword = readElement("locatorPaths.properties","forgotPassword");
		WebElement forgotPwd = driver.findElement(By.xpath(forgotPassword));

		forgotPwd.click();
		Thread.sleep(2000);
		
		//https://prism.beta1.qa.eigenrisk.com/auth/forgot-password
		String currentURL = driver.getCurrentUrl();
		String url_L15 = currentURL.substring(currentURL.length() - 15);
		/*
		if(url_L15.equals("forgot-password"))
			System.out.println("Test Case Passed...");
		else
			System.out.println("Test Case Failed...");
		*/
		Assert.assertTrue(url_L15.equals("forgot-password"));
		System.out.println("Successfully Clicked on Forgot password? Link");
		
	}
	
	public static void resetPassword_UnRegisteredEmail(WebDriver driver, String emailID) throws Exception {
		
		String alertMessage = clickResetPassword(driver, emailID);
		Assert.assertTrue(alertMessage.equals("Error: The User ID does not exist"));
		
	}
	
	
	public static void resetPassword_RegisteredEmail(WebDriver driver, String emailID) throws Exception {
		String alertMessage = clickResetPassword(driver, emailID);
		Assert.assertTrue(alertMessage.equals("Mail successfully sent"));
		
	}
	
	public static String clickResetPassword(WebDriver driver, String emailID) throws Exception {
		clickForgotPasswordLink(driver);
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
	
	public static String readElement(String fileName, String fieldName) throws Exception {
		FileReader propertyFile = new FileReader(fileName);
		Properties configProperty = new Properties();
		configProperty.load(propertyFile);
		String fieldValue = configProperty.getProperty(fieldName);
		propertyFile.close();
		return fieldValue;
		
	}

}
