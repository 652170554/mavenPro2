package utils;

public class Constants {
    //ie驱动文件
	public static final String IE_DRIVER = "D:\\soft\\IEDriverServer.exe";
	//chrome驱动文件
	public static final String CHROME_DRIVER = "D:\\soft\\chromedriver.exe";
	//默认等待时间
	public static final long WAIT_TIME = 30;
	//显示等待默认等待超时时间
	public static final int EXPLICIT_WAIT = 30;
	//默认下载文件路径
	public static final String DOWNLOAD_PATH = System.getProperty("user.dir")+
			"\\download";
	//截图文件路径
	public static final String SCREENSHOT = System.getProperty("user.dir")
			+"\\screenshots";
	public static final String DATA_PATH = System.getProperty("user.dir")
			+"\\data";
}
