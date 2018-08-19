package csv;

import org.openqa.selenium.WebDriver;
import org.tedu.mavenPro2.AdvancedSearchPage;
import org.tedu.mavenPro2.SearchResultPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;


import utils.Constants;
import utils.Utils;
import static org.testng.Assert.*;


public class TestAdvancedSearch {
  WebDriver driver;	
  AdvancedSearchPage asp;
  SearchResultPage srp;
  @Test(dataProvider = "dp")
  public void f(
		String kw,//关键字
  		String cg,//分类
  		String bd,//品牌
  		String minp,//最小价格
  		String maxp,//最大价格
  		String ext,//扩展项
  		String dt,//上市日期
  		String cl,//颜色
  		String expCount//预期结果个数
  		) {
	  asp.get();
	  srp = asp.advancedSearch(kw, cg, bd, minp, maxp, ext, dt, cl);
	  String actCount = srp.getCount();
	  assertEquals(actCount,expCount);
	  
  }

  @BeforeMethod
  @Parameters({"browser"})
  public void beforeMethod(String b) {
//		driver = Utils.openBrowser("firefox");
	    driver = Utils.openBrowser(b);
		asp = new AdvancedSearchPage(driver);
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }

  @DataProvider
  public Object[][] dp() {
    return ReadFile.getTestDataFromCSVFile(Constants.DATA_PATH, "selenium高级搜素ECshop.csv");
  }

}
