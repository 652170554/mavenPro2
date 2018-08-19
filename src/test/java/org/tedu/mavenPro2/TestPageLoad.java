package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestPageLoad {
	  WebDriver driver;
	  @Test
	  public void testPageLoad() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/link.html");
		  Utils.sleep(3000);
		  driver.findElement(By.partialLinkText("链接到id.html")).click();
		  Utils.waitForPageLoad30();
	  }
	  
	  @Test
	  public void testClickAndWait(){
		  driver.get("file:///D:/lhf/SeleniumJavaExample/link.html");
		  WebElement we1 = driver.findElement(By.linkText("链接到id.html"));
		  Utils.clickAndWait(we1);
	  }
	  
	  @Test
	  public void testInputValue(){
		  driver.get("file:///D:/lhf/SeleniumJavaExample/id.html");
		  WebElement we1 = driver.findElement(By.id("username"));
		  Utils.inputValue(we1, "1212");
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
