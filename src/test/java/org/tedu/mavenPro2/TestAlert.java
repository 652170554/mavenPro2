package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestAlert {
	  WebDriver driver;
	  @Test
	  public void testAlert() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/alert.html");
		  Utils.assertAlertNotPresent();
		  driver.findElement(By.id("clk")).click();;
		  Utils.assertAlertPresent();
		  Utils.assertAlertText("Welcome!");
		  
	  }
	  
	  @Test
	  public void testAlert1() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/alert.html");
		  driver.findElement(By.id("clk")).click();
		  Utils.assertAlertContainsText("Wel","come","!");
	  }
	
	  @Test
	  public void testAlert2() {
		  driver.get("http://localhost/ecshop/upload/user.php");
		  driver.findElement(By.name("submit")).click();
		  Utils.assertAlertContainsText("用户名不能为空","登录密码不能为空。");
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
