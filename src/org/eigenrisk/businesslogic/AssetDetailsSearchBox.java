package org.eigenrisk.businesslogic;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssetDetailsSearchBox {
	

	public static void assetDetailsSearchBox(WebDriver driver,String contentToSearch){
		try{
		FileReader file = new FileReader(".//locatorPaths.properties");
		Properties prop = new Properties();
		prop.load(file);;
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("searchBox")))).sendKeys(contentToSearch);
		}
		//driver.findElement(By.xpath("searchBox")).sendKeys(contentToSearch);
		catch(IOException e){
			
			e.printStackTrace();
		}
		
	}
	

}
