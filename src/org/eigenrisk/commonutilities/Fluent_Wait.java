package org.eigenrisk.commonutilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class Fluent_Wait {
	public static void webDriverWait(WebDriver driver, String wElement) {
        FluentWait fWait = new FluentWait(driver);
        fWait.withTimeout(100, TimeUnit.SECONDS);
        fWait.pollingEvery(4, TimeUnit.SECONDS);
        WebElement loginTBox = (WebElement) fWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(wElement)));
        //System.out.println("Inside webDriverWait...");
    }


}
