package constant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBconstant1 {

	public static Properties config;
	public static WebDriver driver;
	
	@BeforeMethod
	public void setup () throws Throwable, IOException
	{
		config = new Properties();
		config.load(new FileInputStream("/Users/gauravsalkar/eclipse-workspace/PrimusBank/PropertyFile/Environment1.properties"));
	
	if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		// print
		Reporter.log("executing on ChromeBrowser", true);
		System.setProperty("webdriver.chrome.driver", "//Users//gauravsalkar//chromedriver");
		driver = new ChromeDriver();
		driver.get(config.getProperty("Url"));
		Thread.sleep(3000);

	}
	
	else {
		
		Reporter.log("Browser value is not matching", true);
	}

	}
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}
	
	
	
	
	
	
}
