package driverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.ExcelFileUtil;

public class DataDrivenDriverScript {


	String inputpath = "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestInput//LoginData.xlsx";
	
	String outputpath = "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestOutput/DataDrivenResults.xlsx";
	
	
	WebDriver driver;
	Properties config;
	ExtentReports report;
	ExtentTest test;
	File screen;
	
	
	
	@BeforeTest
	public void setUp () throws Throwable, IOException
	{
		report = new ExtentReports("./ExtentReports/Datadriven.html");
		config = new Properties();
		config.load(new FileInputStream("/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/PropertyFile/OR.properties"));
	
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	 {
 		 
		System.setProperty("webdriver.chrome.driver","/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/CommonDrivers/chromedriver" );
		driver = new ChromeDriver();
	
	 }
		else
		{
			Reporter.log("Browser Key Value is not matching", true);
		}
	}
	
	@Test
	public void verifyLogin () throws Throwable
	{
		// create object to access excel util method 
		
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		// count number of rows in a sheet 
		int rc = xl.rowCount("Login");	
		Reporter.log("Nos of rows:" +rc, true);
		
		//Nos OF Rows
		for (int i = 1; i <=rc; i++)
		{
			test = report.startTest("Validate Login");
				
			driver.get(config.getProperty("Url"));
		// read cell from login sheet 
			
			String username = xl.getCellData("Login", i, 0);
			String password = xl.getCellData("Login", i, 1);
			driver.findElement(By.xpath(config.getProperty("Enteruser"))).sendKeys(username);
			driver.findElement(By.xpath(config.getProperty("Enterpass"))).sendKeys(password);
			driver.findElement(By.xpath(config.getProperty("Clicklogin"))).click();
			Thread.sleep(5000);
			
			String expected = "dashboard";
			String actual = driver.getCurrentUrl();
			if (actual.contains(expected))
			{
				
				Reporter.log("Login success ::"+ expected + "  "+ actual, true);
				
				//extent report
				test.log(LogStatus.PASS, "Login success ::"+ expected + "  "+ actual);
				
				xl.setCellData("Login", i, 2, "Login success", outputpath);
				//write as pass into status cell
				xl.setCellData("Login", i, 3, "Pass", outputpath);
				
			}
			else 
			{
				//capture error message
				String message =driver.findElement(By.xpath(config.getProperty("Errormessage"))).getText();
				
				//take screen shot for fail test
				screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screen, new File("./Screens/"+i+"  "+"Loginpage.png"));
				Reporter.log(message+"  "+expected+"    "+actual,true);
				test.log(LogStatus.FAIL, message+"   "+ expected+"   "+actual);
				
				//write as error message into results cell
				xl.setCellData("Login", i, 2, message, outputpath);
				
				//write as fail into status cell
				xl.setCellData("Login", i, 3, "Fail", outputpath);
				
			}
			report.endTest(test);	
			report.flush();
			
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}