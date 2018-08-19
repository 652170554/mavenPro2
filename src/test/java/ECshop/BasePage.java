package ECshop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import utils.Utils;

public class BasePage {
     WebDriver driver;
     String url;
     
     public BasePage(WebDriver driver){
    	 this.driver = driver;
    	 PageFactory.initElements(driver, this);
     }
     
     public BasePage(WebDriver driver,String title){
    	 this.driver = driver;
    	 Utils.explictWaitTitle(title);
    	 PageFactory.initElements(driver, this);
     }
     
     /**
      * 打开网页
      */
     public void get(){
    	 driver.get(url);
    	 driver.manage().window().maximize();
     }
     
     /**
      * 获得网页标题
      * @return
      */
     public String getTitle(){
    	 return driver.getTitle();
     }
     
     /**
      * 获得网址
      * @return
      */
     public String getURL(){
    	 return driver.getCurrentUrl();
     }
     
     /**
      * 获得网页源码
      * @return
      */
     public String getPageSource(){
    	 return driver.getPageSource();
     }
     
     
}
