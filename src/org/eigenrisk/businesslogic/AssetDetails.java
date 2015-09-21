package org.eigenrisk.businesslogic;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AssetDetails {

	public static void AssetName(WebDriver driver) throws IOException {
		
		FileReader file= new FileReader(".//locatorPaths.properties");
		 Properties prop = new Properties();
		 prop.load(file);
		 WebDriverWait wait = new WebDriverWait(driver,10);
		 String text = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("assetDetailsName")))).getText();
		 //String text = driver.findElement(By.xpath(prop.getProperty("assetDetailsName"))).getText();
		 System.out.println(text);
	}
	
	public static void characteristics(WebDriver driver){
		
		List<WebElement> characterLeft = driver.findElements(By.xpath("//div[@class='pull-left ng-binding']"));
		for(WebElement e:characterLeft){
			//System.out.println(e);
			System.out.println(e.getText());
			System.out.println(characterLeft.size());
		}
		List<WebElement> characterRight = driver.findElements(By.xpath("//div[@class='pull-right ng-binding']"));
		for(WebElement e:characterRight){
			//System.out.println(e);
			System.out.println(e.getText());
			System.out.println(characterRight.size());
		}
		
	
	}

}
