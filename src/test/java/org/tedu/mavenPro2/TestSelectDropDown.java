package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestSelectDropDown {
	  WebDriver driver;
	  @Test
	  public void testSelectDropDown() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/select.html");
		  WebElement we1 = driver.findElement(By.id("menus_navlist"));
		  Utils.selectDropDown(we1, "byvisibaletext", "用户评论");
		  Utils.selectDropDown(we1, "byvalue", "users");
		  Utils.selectDropDown(we1, "byindex", "4");
		  Utils.sleep(3000);
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
