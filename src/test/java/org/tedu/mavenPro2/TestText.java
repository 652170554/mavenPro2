package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestText {
	  WebDriver driver;
	  @Test
	  public void testText() {
		  driver.get("http://localhost/ecshop/upload/index.php");
		  driver.findElement(By.id("keyword")).sendKeys("a");
		  driver.findElement(By.name("imageField")).click();
		  //断言个数是4个
		  WebElement element = driver.findElement(By.tagName("b"));
		  Utils.assertText(element, "4");
	  }
	 
	  @Test
	  public void testText1() {
		  driver.get("http://localhost/ecshop/upload/index.php");
		  WebElement info = driver.findElement(By.id("ECS_MEMBERZONE"));
		  Utils.assertContainsText(info, "欢迎光临本店","欢迎光");
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
