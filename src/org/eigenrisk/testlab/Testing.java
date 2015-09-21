package org.eigenrisk.testlab;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Testing {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://prism.beta1.qa.eigenrisk.com/exposureAnalytics");
		
		
		
		
	}
}
