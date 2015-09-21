package com.eigenRisk.cleanUp;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.eigenRisk.functionalities.ClickExposure;
import com.eigenRisk.functionalities.Exposure_Import_Load;
import com.eigenRisk.functionalities.ProgramBuilder;

public class CleanUpExposures {
	public static void deleteExposures(WebDriver driver) throws Exception {
		//Thread.sleep(2000);
		System.out.println("Inside CleanUpExposures()");
		Exposure_Import_Load.clickExposure(driver);
		
		
		String LoadExposureXpath = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureXpath");
		ProgramBuilder.waitForElementToLoad(driver, LoadExposureXpath);
		
		Thread.sleep(2000);
		List<WebElement> TotalColumnCount = driver.findElements(By.xpath(LoadExposureXpath));
		int listSize = TotalColumnCount.size();
		
		String LoadExposureDelete = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureDelete");
		List<WebElement> list_LoadExposureDelete = driver.findElements(By.xpath(LoadExposureDelete));
		int listExpDelSize = list_LoadExposureDelete.size();
		
		String LoadExposureDeleteConfirm = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureDeleteConfirm");
		List<WebElement> exposureDeleteConfirm = driver.findElements(By.xpath(LoadExposureDeleteConfirm));
		int exposureDeleteConfirmSize = exposureDeleteConfirm.size();
		
		System.out.println(listSize + " ;; " + listExpDelSize + " -- " + exposureDeleteConfirmSize);
		
		Boolean notify_conditon = false;
		Thread.sleep(100);
		for(int x=0, y=0; x < listSize; x++) {
			System.out.println("X -> " + x + ", List Size -> " + listSize);
			System.out.println(x + ". " + TotalColumnCount.get(y).getText());
			list_LoadExposureDelete.get(y).click();
			exposureDeleteConfirm.get(y).click();
			Thread.sleep(1000);
			System.out.println("Deleted ->" + TotalColumnCount.get(y).getText());
			String exposureName = TotalColumnCount.get(y).getText();
			String msg = ProgramBuilder.getNotificationText(driver);
			System.out.println(msg);
			
			Exposure_Import_Load.clickExposure(driver);
			LoadExposureXpath = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureXpath");
			ProgramBuilder.waitForElementToLoad(driver, LoadExposureXpath);
			
			TotalColumnCount = driver.findElements(By.xpath(LoadExposureXpath));
			listSize = TotalColumnCount.size();
			if(listSize > 0)
				x = 1;
			
			LoadExposureDelete = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureDelete");
			list_LoadExposureDelete = driver.findElements(By.xpath(LoadExposureDelete));
			listExpDelSize = list_LoadExposureDelete.size();
			
			LoadExposureDeleteConfirm = ProgramBuilder.readElement("locatorPaths.properties","LoadExposureDeleteConfirm");
			exposureDeleteConfirm = driver.findElements(By.xpath(LoadExposureDeleteConfirm));
			exposureDeleteConfirmSize = exposureDeleteConfirm.size();
			
		}
			
			/*
			if(exposureToLoad.contains(exposureName)) {
				TotalColumnCount.get(x).click();
				System.out.println("Clicked on " + TotalColumnCount.get(x));
				notify_conditon = true;
				break;
			}
			*/
		System.out.println("-----------------------------------------------------");
		
	}

}
