package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApplicationLayer.AddEmpPage;
import ApplicationLayer.AddUserPage;
import ApplicationLayer.LoginPage;
import ApplicationLayer.Logout;

public class TestScript {
	WebDriver driver;
	@BeforeMethod
	public void adminlogin()throws Throwable
	{
		System.setProperty("webdriver.chrome.driver", "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/CommonDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://orangehrm.qedgetech.com/");
		Thread.sleep(3000);
		
		
		LoginPage login =PageFactory.initElements(driver, LoginPage.class);
		login.verifylogin("Admin", "Qedge123!@#");
	}
	
	
	@Test(priority=1)
	public void uservalidation()throws Throwable
	{
		
		AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
		
		user.verifyAddUser("Akash demo", "Akhilesh789", "Rangaakhi@123456", "Rangaakhi@123456");
		
		String expected="viewSystemUsers";
		String actual =driver.getCurrentUrl();
		
		if(actual.contains(expected))
		{
			Reporter.log("User added success::"+expected+"   "+actual,true);
		}
		else
		{
			Reporter.log("User added fail::"+expected+"   "+actual,true);
		}
	}
	@Test(priority=0)
	public void empvalidation()throws Throwable
	{
		AddEmpPage emp = PageFactory.initElements(driver, AddEmpPage.class);
		emp.verifyEmp("Akhilesh", "Ranga", "pappasani");
		String expected ="empNumber";
		String actual =driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("Add emp success::"+expected+"   "+actual,true);
		}
		else
		{
			Reporter.log("Add emp Fail::"+expected+"   "+actual,true);
		}
	}
	@AfterMethod
	public void tearDown()throws Throwable
	{
		Logout logout= PageFactory.initElements(driver, Logout.class);
		logout.verifylogout();
		driver.close();
}}
