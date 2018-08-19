package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestValue {
  WebDriver driver;
  @Test
  public void f() {
	  driver.get("file:///D:/lhf/SeleniumJavaExample/id.html");
	  WebElement un = driver.findElement(By.id("username"));
	  Utils.inputValue(un, "123");
	  Utils.assertValue(un, "123");
  }
  
  @Test
  public void f1() {
	  driver.get("file:///D:/lhf/SeleniumJavaExample/xpath.html");
	  WebElement c = driver.findElement(By.id("txt1"));
	  Utils.assertContainsValue(c, "hello", "!");
	  
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
