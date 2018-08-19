package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestChecked {
	  WebDriver driver;
	  @Test
	  public void f() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/check.html");
		  WebElement chk1 = driver.findElement(By.id("m1"));
		  Utils.assertChecked(chk1);
		  WebElement chk2 = driver.findElement(By.id("m2"));
		  Utils.assertNotChecked(chk2);
	  }
	
	  @BeforeMethod
	  public void beforeMethod() {
		  driver = Utils.openBrowser("firefox");
	  }
	
	  @AfterMethod
	  public void afterMethod() {
		  driver.quit();
	  }

}
