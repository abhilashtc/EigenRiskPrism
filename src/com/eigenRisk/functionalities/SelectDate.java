package com.eigenRisk.functionalities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectDate{
	public static void clickOnDateFilter (WebDriver driver, String year) throws Exception {
		
		
		FileReader file = new FileReader(".//locatorPaths.properties");
		
		//reading properties file
		Properties prop =  new Properties();
		prop.load(file);
		WebElement selectExpo = driver.findElement(By.xpath(prop.getProperty("calendar")));
		
		//invoking the actions class
		
		//System.out.println("LoadExposure is -> " + prop.getProperty("LoadExposureXpath"));
		String year1 = "//span[starts-with(.,'" + year + "')]";
		System.out.println("...................................................Year1 XPath -> " + year1);
		
		Actions action = new Actions(driver);
		action.moveToElement(selectExpo).perform();
		driver.findElement(By.xpath(prop.getProperty("calendar"))).click();
		driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@ng-click='toggleMode()']")).click();
		driver.findElement(By.xpath(year1)).click();
		driver.findElement(By.xpath("//span[starts-with(.,'January')]")).click();
		driver.findElement(By.xpath("//span[starts-with(.,'01')]")).click();
		
		Thread.sleep(1000);
//		WebElement wEl = driver.findElement(By.xpath("//span[@title='Select Analysis Date']"));
//		System.out.println("Current Date is --> " + wEl.getText());
		
		
		
		//strong[@class='ng-binding']
		
		//using explicit waits
		/*System.out.println("LoadExposure is -> " + prop.getProperty("LoadSpecification"));
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("LoadSpecification")))).click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,7);
		System.out.println("LoadExposure is -> " + prop.getProperty("LoadExpoButton"));
		wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("LoadExpoButton")))).click();
		//driver.findElement(By.xpath(prop.getProperty("LoadExpoButton"))).click();
*/	
		}
}
