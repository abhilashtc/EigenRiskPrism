package org.eigenrisk.commonutilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckElementStatus {

	public static void isElementsPresent(WebDriver driver, String xpath) {

		if (driver.findElements(By.xpath(xpath)).size() != 0) {
			System.out.println("Element is Present");
		} else {
			System.out.println("Element is Absent");
		}
	}

	public static void isElementPresent(WebDriver driver, String xpath) {
		if (driver.findElement(By.xpath(xpath)) != null) {
			System.out.println("Element is Present");
		} else {
			System.out.println("Element is Absent");
		}

	}

	public static void isElementVisible(WebDriver driver, String xpath) {
		if (driver.findElement(By.xpath(xpath)).isDisplayed()) {
			System.out.println("Element is Visible");
		} else {
			System.out.println("Element is InVisible");
		}

	}

	public static void isElementEnabled(WebDriver driver, String xpath) {
		if (driver.findElement(By.xpath(xpath)).isEnabled()) {
			System.out.println("Element is Enable");
		} else {
			System.out.println("Element is Disabled");
		}
	}

	public static void isTextPresent(WebDriver driver, String text) {
		if (driver.getPageSource().contains(text)) {
			System.out.println("Text is present");
		} else {
			System.out.println("Text is absent");
		}

	}

}
