package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ApplicationLayer.AddUserPage;
import ApplicationLayer.LoginPage;
import ApplicationLayer.Logout;
import Utility.ExcelFileUtil;

public class DriverScriptPOM {
	
	WebDriver driver;
	
	//check excel file
	String inputpath ="/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestInput/addusers.xlsx";
	String outputpath ="/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestOutput/PomResults.xlsx";
	
	
	
	
	@BeforeTest
	public void adminLogin()throws Throwable
	{
		System.setProperty("webdriver.chrome.driver", "//Users//gauravsalkar//chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		Thread.sleep(3000);
		LoginPage login =PageFactory.initElements(driver, LoginPage.class);
		login.verifylogin("Admin", "Qedge123!@#");
	}
	@Test
	public void uservalidation()throws Throwable
	{
		AddUserPage user =PageFactory.initElements(driver, AddUserPage.class);
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc =xl.rowCount("Users");
		for(int i=1;i<=rc; i++)
		{
			String ename =xl.getCellData("Users", i, 0);
			String username = xl.getCellData("Users", i, 1);
			String password =xl.getCellData("Users", i, 2);
			String cpassword =xl.getCellData("Users", i, 3);
			user.verifyAddUser(ename, username, password, cpassword);
			String expected="viewSystemUsers";
			String actual =driver.getCurrentUrl();
			if(actual.contains(expected))
			{
				Reporter.log("User added success::"+expected+"   "+actual,true);
				xl.setCellData("users", i, 4, "Pass", outputpath);
			}
			else
			{
				Reporter.log("User added fail::"+expected+"   "+actual,true);
				xl.setCellData("users", i, 4, "fail", outputpath);
			}
		}
	}
	@AfterTest
	public void tearDown()throws Throwable
	{
		Logout logout =PageFactory.initElements(driver, Logout.class);
		logout.verifylogout();
		driver.close();
	}

}
