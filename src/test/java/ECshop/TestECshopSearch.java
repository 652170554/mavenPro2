package ECshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import static org.testng.Assert.*;
public class TestECshopSearch {
	WebDriver driver;
  @Test
  public void f() {
	  driver.get("http://localhost/ecshop/upload/");
	  driver.findElement(By.id("keyword")).sendKeys("移动");
	  driver.findElement(By.name("imageField")).click();
	  try {
		Thread.sleep(3000);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  String c = driver.findElement(By.xpath(".//*[@id='pager']/span/b")).getText();
	  assertEquals(c,"3");
	  driver.findElement(By.id("keyword")).clear();
	  driver.findElement(By.id("keyword")).sendKeys("移动话费");
	  driver.findElement(By.name("imageField")).click();
	  try {
		Thread.sleep(3000);
	} catch (Exception e) {
		e.printStackTrace();
	}
	  String c1 = driver.findElement(By.xpath(".//*[@id='pager']/span/b")).getText();
	  assertEquals(c1,"0");
	  String t1 = driver.findElement(By
			  .xpath("html/body/div[7]/div[2]/div[1]/div/div")).getText();
	  assertEquals(t1,"无法搜索到您要找的商品！");
  }

  @BeforeMethod
  public void beforeMethod() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("dom.ipc.plugins.enabled", false);
		driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

}
