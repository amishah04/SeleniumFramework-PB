package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBconstant {

	public static Properties config;
	public static WebDriver driver;
	
	
	@BeforeMethod
	public void setUp()throws Throwable
	{
		config = new Properties();
		config.load(new FileInputStream("/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/PropertyFile/Environment.properties"));
	if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		Reporter.log("executing on ChromeBrowser",true);
		System.setProperty("webdriver.chrome.driver", "//Users//gauravsalkar//chromedriver"); 
		driver = new ChromeDriver();
		driver.get(config.getProperty("Url"));
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}

	else
	{
		Reporter.log("Browser value is not matching",true);
	}
	}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
