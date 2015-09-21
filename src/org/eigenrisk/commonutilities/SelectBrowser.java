package org.eigenrisk.commonutilities;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {
	static WebDriver driver;
	
	public static  WebDriver selectBrowser(String browser){
		
		if(browser.equalsIgnoreCase("firefox")){
			
				driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			
			System.setProperty("webdriver.chrome.driver", ".//Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("ie")){
			
			System.setProperty("webdriver.ie.driver", ".//Driver/IEDriverServer.exe");
		}
		
		return driver;
		
		
		
		
	}
	

}
