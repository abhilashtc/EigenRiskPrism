/**
 * 
 */
package com.eigenRisk.functionalities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.eigenRisk.Utilities.CommonUtilities;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;

/**
 * @author abhilashtc
 *
 */
public class ExposureDrive {
	public static void importExposure(WebDriver driver, String exposureFile) throws Exception {
		CommonUtilities.setExposurePath(exposureFile);
		System.out.println("Inside importExposure Drive");
		String importNewExposureXpath = CommonUtilities.readElement("locatorPaths.properties", "importNewExposureXpath");
		CommonUtilities.waitForElementToLoad(driver, "importNewExposureXpath");
		
		String EX_Import = CommonUtilities.readElement("locatorPaths.properties", "EX_Import");
		System.out.println("XPath (EX_Import) ->" + EX_Import);
		driver.findElement(By.xpath(EX_Import)).click();
		
		System.out.println("Clicked on Import Exposure Button...");
		
		/*
		//Deleting the Local Queue
		try {
			String EX_LocalQueueDel = CommonUtilities.readElement("", "EX_LocalQueueDel");
			CommonUtilities.waitForElementToLoad(driver, EX_LocalQueueDel);
			driver.findElement(By.xpath(EX_LocalQueueDel)).click();
			String EX_LocalQueueDelConf = CommonUtilities.readElement("", "EX_LocalQueueDelConf");
			CommonUtilities.waitForElementToLoad(driver, EX_LocalQueueDelConf);
			driver.findElement(By.xpath(EX_LocalQueueDelConf)).click();
		}
		catch (ElementNotFoundException e) {
			System.out.println("Local Queue is empty, hence nothing to delete...");
		}
		*/
		
		
		System.out.println(">> EX_Import_Browse");
		String EX_Import_Browse = CommonUtilities.readElement("", "EX_Import_Browse");
		CommonUtilities.waitForElementToLoad(driver, EX_Import_Browse);
		System.out.println("<< EX_Import_Browse");
		
		System.out.println("XPath (EX_Import_Browse)->" + EX_Import_Browse);
		driver.findElement(By.xpath(EX_Import_Browse)).click();
		Thread.sleep(12000);
		getPathDetails();
		Thread.sleep(5000);
		
		String EX_ImportGo = CommonUtilities.readElement("", "EX_ImportGo");
		driver.findElement(By.xpath(EX_ImportGo)).click();
		
		CommonUtilities.getNotificationText(driver);
		String msg = CommonUtilities.getNotificationText(driver);
		
		System.out.println("Msg-> " + msg);
		if(msg.equals("Failed Validations")) {
			String EX_ErrorExcel = CommonUtilities.readElement("", "EX_ErrorExcel");
			CommonUtilities.waitForElementToLoad(driver, EX_ErrorExcel);
			System.out.println("CommonUtilities.elementExist ->" + CommonUtilities.elementExist);
			System.out.println(driver.findElement(By.xpath(EX_ErrorExcel)).getText());
			if(CommonUtilities.elementExist == true) {
				System.out.println("Export Error to Excel Exists...");
				driver.findElement(By.xpath(EX_ErrorExcel)).click();
				System.out.println("Exported the Error Excel...");
			}
			
		}
		
		
		
	}
	
	public static void getPathDetails() throws Exception {
		Thread.sleep(1000);
		String LaptopMake = "Lenovo";
		String ReadExposurePath;
		
		if (LaptopMake.equals("HP"))
			ReadExposurePath = "./Tools/ReadExposurePath_HP.exe";
		else
			ReadExposurePath = "./Tools/ReadExposurePath_Lenovo.exe";
		
		Runtime.getRuntime().exec(ReadExposurePath);
		Thread.sleep(2000);
		
		
	}

}
