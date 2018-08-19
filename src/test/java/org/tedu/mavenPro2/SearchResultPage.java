package org.tedu.mavenPro2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
    WebDriver driver;
    
    //搜索结果个数
    @FindBy(how = How.TAG_NAME,using="b")
    @CacheLookup
    WebElement count;
    
    public SearchResultPage(WebDriver driver){
    	this.driver = driver;
    	PageFactory.initElements(driver, this);
    }
    
    //获取搜索结果
    public String getCount(){
		return count.getText();
    	
    }
}
