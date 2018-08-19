package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestFrame {
	  WebDriver driver;
	  @Test
	  public void testFrame() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/main1.html");
		  //通过name切换frame
		  Utils.switchFrame("f1");
		  Utils.inputValue(driver.findElement(By.id("input1")), "111");
		  Utils.switchFrame("f2");
		  Utils.inputValue(driver.findElement(By.id("input2")), "222");
		  //通过index切换frame
		  Utils.switchFrame(0);
		  Utils.inputValue(driver.findElement(By.id("input1")), "333");
		  Utils.switchFrame(1);
		  Utils.inputValue(driver.findElement(By.id("input2")), "444");
		  //通过页面元素切换frame
		  Utils.switchToDefault();
		  WebElement we1 = driver.findElement(By.name("f1"));
		  Utils.switchFrame(we1);
		  Utils.inputValue(driver.findElement(By.id("input1")), "555");
		  Utils.switchToDefault();
		  WebElement we2 = driver.findElement(By.name("f2"));
		  Utils.switchFrame(we2);
		  Utils.inputValue(driver.findElement(By.id("input2")), "666");
		  //通过网页内容包含指定字符串来切换frame
		  Utils.switchFrameByPageSource("input1");
		  Utils.inputValue(driver.findElement(By.id("input1")), "777");
		  Utils.switchFrameByPageSource("input2");
		  Utils.inputValue(driver.findElement(By.id("input2")), "888");
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
