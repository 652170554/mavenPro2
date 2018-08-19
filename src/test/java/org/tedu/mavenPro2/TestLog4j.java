package org.tedu.mavenPro2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import utils.*;
public class TestLog4j {
	WebDriver driver;
  @Test
  public void f() {
	  driver.get("https://www.baidu.com/");
	  Log.info("baidu page is loaded");
	  try {
		Thread.sleep(2000);
	} catch (Exception e) {
		Log.error("catched InterruptedException");
		e.printStackTrace();
	}
  }

  @BeforeMethod
  public void beforeMethod() {
	  Log.info("Try to open browser");
	  driver = new FirefoxDriver();
	  Log.info("Browser is opened");
  }

  @AfterMethod
  public void afterMethod() {
	  Log.info("Try to close browser");
	  driver.quit();
	  Log.info("Browser is closed");
  }

}
