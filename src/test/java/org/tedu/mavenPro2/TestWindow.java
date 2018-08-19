package org.tedu.mavenPro2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.Utils;

public class TestWindow {
	  WebDriver driver;
	  @Test
	  public void testWindow() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/newWindow.html");
		  //点击3链接
		  driver.findElement(By.partialLinkText("3")).click();
		  //获取原来窗口的句柄
		  String hdl1 = driver.getWindowHandle();
		  //使用目标窗口的target切换到链接3跳转的页面
		  Utils.switchWindow("windName");
		  driver.findElement(By.name("username")).sendKeys("121212");
		  Utils.sleep(3000);
		  //关闭链接3跳转的页面
		  driver.close();
		  //切换到原来的窗口
		  Utils.switchWindow(hdl1);
		  System.out.println(driver.getTitle());
		  driver.close();
	  }
	
	  @Test
	  public void testTwoWindow() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/newWindow.html");
		  driver.findElement(By.partialLinkText("2")).click();
		  Utils.switchWindow();
		  driver.findElement(By.id("username")).sendKeys("jack");
		  Utils.sleep(3000);
		  Utils.switchWindow();
		  System.out.println(driver.getTitle());
	  }
	  
	  @Test
	  public void testThreeWindow() {
		  driver.get("file:///D:/lhf/SeleniumJavaExample/newWindow.html");
		  driver.findElement(By.partialLinkText("2")).click();
		  driver.findElement(By.partialLinkText("3")).click();
		  Utils.switchWindow(1, "id");
		  driver.findElement(By.id("username")).sendKeys("rose");
		  Utils.sleep(3000);
		  Utils.switchWindow(3, "3");
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
