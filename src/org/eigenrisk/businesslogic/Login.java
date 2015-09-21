package org.eigenrisk.businesslogic;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	public static void login(WebDriver driver,String userName,String passWord) throws IOException{
		FileReader file = new FileReader(".//LocatorPaths.properties");
		Properties prop = new Properties();
		prop.load(file);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("userNamePath"))).sendKeys(userName);
		driver.findElement(By.xpath(prop.getProperty("passWordPath"))).sendKeys(passWord);
		driver.findElement(By.xpath(prop.getProperty("loginPath"))).click();
		
	}
	
	

}
