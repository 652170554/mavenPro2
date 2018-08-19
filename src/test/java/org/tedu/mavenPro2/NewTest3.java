package org.tedu.mavenPro2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class NewTest3 {
  @Test(dataProvider = "dp")
  public void f(String kw, String count) {
	  System.out.println("kw:"+kw+"  count:"+count);
  }

  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }

  @DataProvider
  public Object[][] dp() {
    return ReadFile.getTestDataFromExcel("D:\\lhf\\selenium", "selenium测试数据.xlsx", "Sheet1");
  }

}
