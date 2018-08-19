package utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.*;

public class Utils {
    public static WebDriver driver;
    //是否接受弹框中的要求，默认值是true代表接受，就点击确定或是按钮
    public static boolean acceptNextAlert = true;
    
    //打开指定浏览器
    public static WebDriver openBrowser(String browser){
    	
    	try {
    		if(browser.equalsIgnoreCase("firefox")){
        		FirefoxProfile profile = new FirefoxProfile();
        		profile.setPreference("security.mixed_content.block_active_content", false);
        		profile.setPreference("security.mixed_content.block_display_content", true);
        		profile.setPreference("browser.download.manager.showWhenStarting", false);
        		profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
        				"application/octet-stream,application/vnd.ms-execl,text/csv,application/zip");
        		profile.setPreference("browser.download.dir", "Constants.DOWNLOAD_PATH");
        		//启动firefox
        		driver = new FirefoxDriver(profile);
        	}else if(browser.equalsIgnoreCase("ie")){
        		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        		ieCapabilities.setCapability("nativeEvent", true);
        		ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
        		ieCapabilities.setCapability("ignoreProtectedModeSettings",true);
        		ieCapabilities.setCapability("disable-popup-blocking", true);
        		ieCapabilities.setCapability(InternetExplorerDriver
        				.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        		ieCapabilities.setCapability("requireWindowFocus", false);
        		ieCapabilities.setCapability("enablePersistentHover", false);
        		System.setProperty("webdriver.ie.driver",Constants.IE_DRIVER);
        		driver = new InternetExplorerDriver(ieCapabilities);
        	} else if(browser.equalsIgnoreCase("chrome")){
        		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        		capabilities.setCapability("chrome.swiches", Arrays.asList("--incongito"));
        		ChromeOptions options = new ChromeOptions();
        		options.addArguments("--test--type");
        		options.addArguments("enable-automation");
        		options.addArguments("--disable-infobars");
        		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        		System.setProperty("webdriver.chrome.driver",Constants.CHROME_DRIVER);
        		driver = new ChromeDriver(capabilities);
        	}else{
        		Log.error("invalid browser type:"+browser);
        	}
        	Log.info("Browser is opened,type is:"+browser);
        	//Maximize
        	driver.manage().window().maximize();
        	//set wait time
        	driver.manage().timeouts().implicitlyWait(Constants.WAIT_TIME,
        			TimeUnit.SECONDS);
        	driver.manage().timeouts().implicitlyWait(Constants.WAIT_TIME,
        			TimeUnit.SECONDS);
        	driver.manage().timeouts().pageLoadTimeout(Constants.WAIT_TIME,
        			TimeUnit.SECONDS);
        	driver.manage().timeouts().setScriptTimeout(Constants.WAIT_TIME,
        			TimeUnit.SECONDS);
        	return driver;
		} catch (Exception e) {
			Log.error("Unable to open browser");
			Log.error(e.getMessage());
			return null;
		}
    }
    
    
    /**
     * 截图
     * @param sTestCaseName
     */
    public static void takeScreenshot(String sTestCaseName){
    	DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh_mm_ss");
    	Date date = new Date();
    	File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	File destFile = new File(Constants.SCREENSHOT+"\\"+sTestCaseName+
    			"\\"+sTestCaseName+"#"+dateformat.format(date)+".png");
    	try {
			FileUtils.copyFile(file, destFile);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		}
    }
    
    /**
     * 等待固定秒数
     * @param time
     */
    public static void sleep(long time){
    	try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
			Log.error("The sleep thread is interrupted.");
		}
    }
    
    /**
     * 等待页面加载完毕
     * 
     */
    public static void waitForPageLoad(){
    	String js = "return document.readyState;";
    	while(!"complete".equals((String)executeJS(js))){
    		Log.info("Page is loading...");
    		sleep(1000);
    	}
    	Log.info("The page is loaded.");
    }

    /**
     * 执行js
     * @param js
     * @return
     */
	public static String executeJS(String js) {
		String t1 = null;
		try {
			t1 = (String) ((JavascriptExecutor)driver).executeScript(js);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("executeJS is interrupted.");
		}
		return t1;
	}
    
	
	public static void executeJS(String js,WebElement element,String s1,String s2) {
		try {
			JavascriptExecutor jse = ((JavascriptExecutor)driver);
			jse.executeScript(js, element,s1,s2);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("executeJS is interrupted.");
		}
	}
	/**
	 * 最多等待页面加载30秒
	 */
   public static void waitForPageLoad30(){
	   String js = "return document.readState;";
	   boolean flag = false;
	   //最多等待30秒
	   for(int i=1;i<=30;i++){
		   sleep(1000);
		   if("complete".equals((String)executeJS(js))){
			   flag = true;
			   Log.info("Page is loaded in "+i+"seconds.");
			   break;
		   }
	   }
	   if(!flag){
		   Log.error("Page is not loaded in 30 seconds.");
	   }
   }
   
   /**
    * 显示等待，等待网页标题包含预期字符串
    * 
    */
   public static void explictWaitTitle(final String title){
	   WebDriverWait wait =  new WebDriverWait(driver,Constants.EXPLICIT_WAIT);
	   try {
		   wait.until(new ExpectedCondition<Boolean>(){
			   @Override
			   public Boolean apply(WebDriver d){
				   return d.getTitle().toLowerCase().contains(title.toLowerCase());
			   }
		   });
		
	} catch (Exception e) {
		Log.error("Title is not correct.");
		throw new IllegalStateException(driver.getTitle());
	}
   }
   
   /**
    * 获得元素的页面状态
    * @param element
    * @return true代表可见可用，false代表不可见不可用
    */
   public static boolean getElementStatus(WebElement element){
	   if(!element.isDisplayed()){
		   Log.error("The element is not displayed: "+element.toString());
		   takeScreenshot("Utils-getElementStatus");
	   }else if(!element.isEnabled()){
		   Log.error("The element is not disabled:"+element.toString());
		   takeScreenshot("Utils-getElementStatus");
	   }
	   return element.isDisplayed()&&element.isEnabled();
   }
   
   /**
    * 选择下拉框的选项
    * @param element 下拉框元素
    * @param flag 
    *     byvalue--通过value属性值选择
    *     byindex--通过编号选择
    *     byvisibletext--通过文本选择
    * @param data 选项的具体只
    */
   public static void selectDropDown(WebElement element,String flag,String data){
	   if(getElementStatus(element)){
		   Select select = new Select(element);
		   Log.info("Select option in drop down list "+flag);
		   if(flag.equalsIgnoreCase("byvalue")){
			   select.selectByValue(data);
		   }else if(flag.equalsIgnoreCase("byindex")){
			   select.selectByIndex(Integer.parseInt(data));
		   }else{
			   select.selectByVisibleText(data);
		   }
		   Log.info(data+" is selected in drop down list"+element.toString());
	   }else{
		   Log.error("The drop down list is not displayed or disabled");
	   }
   }
   
   /**
    * 点击页面元素
    * @param element
    */
   public static void click(WebElement element){
	   try {
		    element.click();
		    Log.info("The element is clicked");
		} catch (Exception e) {
            e.printStackTrace();
            Log.error("Method[click]"+e.getMessage());
		}
   }
   
   /**
    * 点击页面元素，并且等待新网页加载完毕
    * @param element
    */
   public static void clickAndWait(WebElement element){
	   click(element);
	   waitForPageLoad();
   }
   
   /**
    * 向指定页面元素输入数据
    * @param element
    * @param value
    */
   public static void inputValue(WebElement element,String value){
		try {
			   if(getElementStatus(element)){
				   if(value!=null){
					   element.clear();
					   element.sendKeys(value);
					   Log.info(value+" is input to element.");
				   }
			   }else{
				   Log.error("value is null");
			   }
		} catch (Exception e) {
           e.printStackTrace();
           Log.error(e.getMessage());
		}
	  
   }
   
   /**
    * 切换到默认窗口
    */
   public static void switchToDefault(){
	   driver.switchTo().defaultContent();
	   Log.info("Switched to default content.");
   }
   
  /**
   * 使用id或name属性切换frame
   * @param idOrName
   */
   public static void switchFrame(String idOrName){
	   try {
		   switchToDefault();
		   driver.switchTo().frame(idOrName);
		   Log.info("Switched to frame,idOrName: "+idOrName);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("No frame to switch.");
		}
   }
   
   /**
    * 通过编号切换frame
    * @param index
    */
   public static void switchFrame(int index){
	   try {
		   switchToDefault();
		   driver.switchTo().frame(index);
		   Log.info("Switched to frame,index: "+index);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("No frame to switch.");
		}
   }
   
   /**
    * 通过其他方法定位frame,在切换frame
    * @param frameElement
    */
   public static void switchFrame(WebElement frameElement){
	   try {
		   if(getElementStatus(frameElement)){
				switchToDefault();
				driver.switchTo().frame(frameElement);
			    Log.info("Switched to frame.");
		   }
		
	} catch (Exception e) {
		 e.printStackTrace();
		 Log.error("No frame to switch.");
	}
   }
   
   /**
    * 通过网页内容包含指定字符串来切换frame
    * @param pageSource
    */
   public static void switchFrameByPageSource(String pageSource){
	   switchToDefault();
	   List<WebElement> frames = driver.findElements(By.tagName("frame"));
	   if(frames.size()>0){
		   boolean flag = false;
		   for(int i=0;i<frames.size();i++){
			   switchFrame(i);
			   if(driver.getPageSource().contains(pageSource)){
				   flag = true;
				   break;
			   }
		   }
		   if(flag){
			  Log.info("Switch to frame contains:"+pageSource); 
		   }else{
			  Log.error("No frame contains:"+pageSource);
		   }
	   }else{
		   Log.error("No frame to switch.");
	   }
	   
   }
   
   /**
    * 通过窗口名称或句柄来切换窗口
    * @param nameOrHdl
    */
   public static void switchWindow(String nameOrHdl){
	   try {
		   driver.switchTo().window(nameOrHdl);
		   Log.info("MethodSwitchWindowByNameOrHdl:Switched to new window.");
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("The window cannot be found:"+nameOrHdl);
		}
   }
   
   /**
    * 只适用于总共两个窗口的切换
    */
   public static void switchWindow(){
	   String org = driver.getWindowHandle();
	   Set<String> allWindows = driver.getWindowHandles();
	   if(allWindows.size() == 2){
		   Iterator<String> it = allWindows.iterator();
		   while(it.hasNext()){
			   String currentWindow = it.next();
			   if(!currentWindow.equals(org)){
				   driver.switchTo().window(currentWindow);
			   }
		   }
		   Log.info("MethodTwoWindow:Switched to new window.");
	   }else{
		   Log.error("There are not two windows.");
	   }
   }
   
   /**
    * 切换到符合指定条件的新窗口
    * @param type
    *      1.ByTitle包含信息
    *      2.ByURL包含信息
    *      3.ByPageSource包含信息
    * @param value
    */
   public static void switchWindow(int type,String value){
	   String org = driver.getWindowHandle();
	   Set<String> allWindows = driver.getWindowHandles();
	   if(allWindows.size() > 1){
		   boolean flag = false;//未切换到目标窗口
		   Iterator<String> it = allWindows.iterator();
		   while(it.hasNext()){
			   String currentWindow = it.next();
			   if(!currentWindow.equals(org)){
				   driver.switchTo().window(currentWindow);
				   String currentValue;
				   switch(type){
				   case 1:
					   currentValue = driver.getTitle();
					   Log.info("Switch window by title value");
					   break;
				   case 2:
					   currentValue = driver.getCurrentUrl();
					   Log.info("Switch window by url value");
					   break;
				   default:
					   currentValue = driver.getPageSource();
					   Log.info("Switch window by page source value");
					   break;
				   }
				   if(currentValue.contains(value)){
					   flag = true;
					   break;
				   }
			   }
		   }
		   if(flag){
			   Log.info("Switched to target window.");
		   }else{
			   Log.error("Can not find target window contains: "+value);
		   }
	   }else{
		   Log.error("Only one window,no other windows.");
	   }
   }
   
   /**
    * 判断页面元素是否存在
    * @param by
    * @return
    * true代表真实存在
    * false代表不存在
    */
   public static boolean isElementPresent(By by){
	   try {
		   driver.findElement(by);
		   Log.info("Can find the element");
		   return true;
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Can not find the element.");
			return false;
		}
   }
   
   
   /**
    * 断言页面元素出现|存在
    * @param by
    */
   public static void assertElementPresent(By by){
	   assertTrue(isElementPresent(by));
   }
   
   /**
    * 断言页面元素不出现|不存在|消失
    * @param by
    */
   public static void assertElementNotPresent(By by){
	   assertFalse(isElementPresent(by));
   }
   
   
   /**
    * 断言是否弹出对话框
    * @return
    * true代表弹框出现
    * false代表弹框未出现或消失
    */
   public static boolean isAlertPresent(){
	   try {
			driver.switchTo().alert();
			Log.info("The alert dialog can be found.");
			return true;
		} catch (Exception e) {
			Log.error("The alert dialog cannot be found.");
			return false;
		}
   }
   
   /**
    * 断言弹框出现|存在
    */
   public static void assertAlertPresent(){
	   assertTrue(isAlertPresent());
   }
   
   /**
    * 断言弹框不出现|不存在|消失
    */
   public static void assertAlertNotPresent(){
	   assertFalse(isAlertPresent());
   }
   
   /**
    * 获得弹框内容，并且关闭弹框
    * 默认是“确定”或“是”
    * 如果点击“取消”或“否”，先给acceptNextAlert赋值为false,在调用该方法
    * @return 弹框中的文本内容
    */
   public static String closeAlertAndGetItsText(){
	   try {
		   Alert alert = driver.switchTo().alert();
		   String alertText = alert.getText();
		   if(acceptNextAlert){
			   Log.info("Accept the alert.");
			   alert.accept();
		   }
		   else{
			   Log.info("Dismiss the alert.");
			   alert.dismiss();
		   }
		   Log.info("The text in the alert is:"+alertText);
		   return alertText;
		} finally {
			acceptNextAlert = true;
		}
   }
   
   /**
    * 断言弹框文本等于预期值，并且通过点击“确定”或“是”来关闭弹框
    * @param expTxt
    */
   public static void assertAlertText(String expTxt){
	   try {
		   String txt = closeAlertAndGetItsText();
		   assertEquals(txt,expTxt);
		} catch (Exception e) {
			e.printStackTrace();
			Log.error("Alert txt is not equals to expected text.");
		}
   }
   
   /**
    * 断言弹框内容正确，并且点击“取消”或“否”关闭弹框
    * @param expText
    */
   public static void assertAlertTextAndDismiss(String expText){
	   acceptNextAlert = false;
	   assertAlertText(expText);
   }
   
   /**
    * 断言弹框的文本中包含预期的那些文本字符串，并且点击“确定”或“是”来关闭弹框
    * @param expTexts
    */
   public static void assertAlertContainsText(String... expTexts){
	   //获得实际弹框文本并关闭弹框
	   String actText = closeAlertAndGetItsText();
	   for(String expText:expTexts){
		   try {
			  assertTrue(actText.contains(expText));
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("Actual alert text:["+actText+
						"] does not contains expText:["+expText+"].");
			}
	   }
   }
   
   
   public static void assertAlertContainsAndDismiss(String... expTexts){
	   acceptNextAlert = false;
	   assertAlertContainsText(expTexts);
   }
   
   /**
    * 断言元素中的文本等于预期值
    * @param element
    * @param expText
    */
   public static void assertText(WebElement element,String expText){
	   if(getElementStatus(element)){
		   if(expText!=null){
			   try {
				  assertEquals(element.getText(), expText);
				} catch (Exception e) {
					e.printStackTrace();
					Log.error(e.getMessage());
				}
		   }
		   else{
			   Log.error("Expected text:"+expText+" is null.");
		   }
	   }
   }
   
   /**
    * 断言元素的文本包含那些预期值
    * @param element
    * @param expTexts
    */
   public static void assertContainsText(WebElement element,String...expTexts){
	    if(getElementStatus(element)){
	    	for(String expText:expTexts){
	    		try {
					assertTrue(element.getText().contains(expText));
				} catch (Exception e) {
					e.printStackTrace();
					Log.error("Actual alert text:["+element.getText()+
							"] does not contains expText:["+expText+"].");
				}
	    	}
	    }
   }
   
   /**
    * 断言value属性值等于预期值
    * @param element
    * @param expValue
    */
   public static void assertValue(WebElement element,String expValue){
	    assertAttribute(element,"value",expValue);
   }

   /**
    * 断言指定属性等于属性值
    * @param element
    * @param attributeValue
    * @param expValue
    */
   public static void assertAttribute(WebElement element, String attributeValue,
		 String expValue) {
	    if(getElementStatus(element)){
	    	if(expValue!=null){
	    		try {
					assertEquals(element.getAttribute(attributeValue),expValue);
				} catch (Exception e) {
					e.printStackTrace();
					Log.error(e.getMessage());
				}
	    	}
	    	else{
	    			Log.error("Expected value:["+expValue+"] is null.");
	    	}
	    }
   }
   
   /**
    * 断言文本框中包含指定的一些预期字符串
    * @param element
    * @param expValues
    */
   public static void assertContainsValue(WebElement element,String...expValues){
	   assertContainsAttribute(element,"value",expValues);
   }

   /**
    * 断言指定属性值包含一些预期值
    * @param element
    * @param string
    * @param expValues
    */
   public static void assertContainsAttribute(WebElement element, String attributeValue,
		String[] expValues) {
	   if(getElementStatus(element)){
		   for(String expValue:expValues){
			   try {
				   assertTrue(element.getAttribute(attributeValue).contains(expValue));
				} catch (Exception e) {
					e.printStackTrace();
					Log.error("Actual attribute["+attributeValue+"] value does not "
							+ "contains expected values:"+expValue+"].");
				}
		   }
	   }
   }
   
   /**
    * 断言复选框或单选按钮被选中
    * @param element
    */
   public static void assertChecked(WebElement element){
	   if(getElementStatus(element)){
		   try {
			   assertTrue(element.isSelected());
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("The checkbox or radiobutton is not checked.");
			}
	   }
   }
   
   /**
    * 断言复选框或单选按钮未被选中
    * @param element
    */
   public static void assertNotChecked(WebElement element){
	   if(getElementStatus(element)){
		   try {
			   assertFalse(element.isSelected());
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("The checkbox or radiobutton is not checked.");
			}
	   }
   }
   
   /**
    * 断言下拉框或列表框的当前选项等于预期选项
    * 一般适用于只有一个选项被选中的情况
    * @param element
    * @param expOption
    */
   public static void assertSelectedOption(WebElement element,String expOption){
	   if(getElementStatus(element)){
		   try {
			   String actOption = new Select(element).getFirstSelectedOption()
					   .getText();
			   assertEquals(actOption, expOption);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getMessage());
			}
	   }
   }
   
   /**
    * 断言下拉框或列表框中的所有选项中包含一些预期文本
    * @param element
    * @param expOptions
    */
   public static void assertOptionsContains(WebElement element,
		   String...expOptions){
	   if(getElementStatus(element)){
		   //遍历每个要检查存在的预期文本
		   for(String expOption:expOptions){
			   //默认标记为没有找到
			   boolean flag = false;
			   //遍历所有选项
			   for(WebElement option:new Select(element).getOptions()){
				   String actOption = option.getText();
				   if(actOption.contains(expOption)){
					   flag = true;//找到了
					   break;
				   }
			   }
			   try {
				   assertTrue(flag);
				} catch (Exception e) {
					e.printStackTrace();
					Log.error("All options does not contains "
							+ "expected option:"+expOption);
					fail(e.getMessage());
				}
		   }
	   }
   }
   
   /**
    * 断言下拉框或列表框可以多选
    * @param element
    */
   public static void assertMultiple(WebElement element){
	   if(getElementStatus(element)){
		   try {
			   assertTrue(new Select(element).isMultiple());
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("The drop list is not multiple.");
				fail(e.getMessage());
			}
		   
	   }
   }
   
   /**
    * 断言下拉框或列表框不可以多选
    * @param element
    */
   public static void assertNotMultiple(WebElement element){
	   if(getElementStatus(element)){
		   try {
			   assertFalse(new Select(element).isMultiple());
			} catch (Exception e) {
				e.printStackTrace();
				Log.error("The drop list is not multiple.");
				fail(e.getMessage());
			}
		   
	   }
   }
   
   /**
    * 断言下拉框或列表框的多个当前选项中 包含预期文本
    * @param element
    * @param expOptions
    */
   public static void assertSelectedContains(WebElement element,
		   String...expOptions){
	   if(getElementStatus(element)){
		   for(String expOption:expOptions){
			   boolean flag = false;
			   //遍历所有被选中的选项
			   for(WebElement option:new Select(element).getAllSelectedOptions()){
				   String actOption = option.getText();
				   if(actOption.contains(expOption)){
					   flag =true;
					   break;
				   }
			   }
			   try {
				   assertTrue(flag);
				} catch (Exception e) {
					e.printStackTrace();
					Log.error("Selected options does not contains:"+expOption);
					fail("Selected options does not contains:"+expOption);
				}
		   }
	   }
   }
   
   /**
    * 断言下拉框的所有选项的个数正确
    * @param element
    * @param expCount
    */
   public static void assertOptionsCount(WebElement element,int expCount){
	    if(getElementStatus(element)){
	    	int actCount = new Select(element).getOptions().size();
	    	try {
				assertEquals(actCount, expCount);
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getMessage());
				fail("All options count:"+e.getMessage());
			}
	    }
   }
   
   public static void assertSelectedOptionsCount(WebElement element,int expCount){
	   if(getElementStatus(element)){
		   int actCount = new Select(element).getAllSelectedOptions().size();
		   try {
			    assertEquals(actCount, expCount);;
			} catch (Exception e) {
				e.printStackTrace();
				Log.error(e.getMessage());
				fail("All selected options count:"+e.getMessage());
			}
	   }
   }
}
