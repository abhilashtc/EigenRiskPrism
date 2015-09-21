package org.eigenrisk.businesslogic;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TopN {
	
	public static void selectTopN(WebDriver driver) throws IOException{
		FileReader fis = new FileReader(".//locatorPaths.properties");
		
		Properties prop = new Properties();
		prop.load(fis);
		
		WebDriverWait wait =new WebDriverWait(driver,5); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("topNXpath")))).click();		
				//driver.findElement(By.xpath(prop.getProperty("topNXPath"))).click();
		
	}

}
