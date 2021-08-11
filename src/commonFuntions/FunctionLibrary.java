package commonFuntions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.PBconstant;

public class FunctionLibrary extends PBconstant {

	// boolean type methods
		//method for login
		public static boolean verifyLogin(String username,String password)throws Throwable
		{
		driver.findElement(By.xpath(config.getProperty("Objuser"))).sendKeys(username);	
		driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("Objlogin"))).click();
		Thread.sleep(4000);
		String expected ="adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			Reporter.log("Login success::"+expected+"    "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"    "+actual,true);
			return false;
		}
		}
		
		//method for branch click
		public static void clickBranches() throws Throwable
		{
		driver.findElement(By.xpath(config.getProperty("clickBranches"))).click();
		Thread.sleep(4000);
		}
			
		//method for new branch creation
		public static boolean verifynewBranch(String bname,String address1,String address2,
		String address3,String area,String zipcode,String country,String state,String city)throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("clicknewbranch"))).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath(config.getProperty("Branchname"))).sendKeys(bname);
			driver.findElement(By.xpath(config.getProperty("Address1"))).sendKeys(address1);
			driver.findElement(By.xpath(config.getProperty("Address2"))).sendKeys(address2);
			driver.findElement(By.xpath(config.getProperty("Address3"))).sendKeys(address3);
			driver.findElement(By.xpath(config.getProperty("Area"))).sendKeys(area);
			driver.findElement(By.xpath(config.getProperty("zipcode"))).sendKeys(zipcode);
			new Select(driver.findElement(By.xpath(config.getProperty("country")))).selectByVisibleText(country);
			Thread.sleep(4000);
			new Select(driver.findElement(By.xpath(config.getProperty("state")))).selectByVisibleText(state);
			Thread.sleep(4000);
			new Select(driver.findElement(By.xpath(config.getProperty("city")))).selectByVisibleText(city);
			Thread.sleep(4000);
			driver.findElement(By.xpath(config.getProperty("submitbtn"))).click();
			Thread.sleep(5000);
			//capture alert message
			String expectedalert =driver.switchTo().alert().getText();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			String actualalert = "New Branch with id";
			if(expectedalert.contains(actualalert))
			{
				Reporter.log("New Branch created::"+expectedalert+"    "+actualalert,true);
				return true;
			}
			else
			{
				Reporter.log("New Branch Not created::"+expectedalert+"    "+actualalert,true);
				return false;	
			}
		}
		//method for branch updation
		public static boolean verifybranchUpdate(String branchname,String address,String zipcode) throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("clickEdit"))).click();
			Thread.sleep(4000);
			WebElement branch =driver.findElement(By.xpath(config.getProperty("branchnameupdate")));
			branch.clear();
			branch.sendKeys(branchname);
			Thread.sleep(4000);
			WebElement objaddress=driver.findElement(By.xpath(config.getProperty("addressupdate")));
			objaddress.clear();
			objaddress.sendKeys(address);
			Thread.sleep(4000);
			WebElement Objzip = driver.findElement(By.xpath(config.getProperty("zipcodeupdate")));
			Objzip.clear();
			Objzip.sendKeys(zipcode);
			Thread.sleep(4000);
			driver.findElement(By.xpath(config.getProperty("updatebtn"))).click();
			Thread.sleep(4000);
			String expextedalert =driver.switchTo().alert().getText();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			String actualalert ="Branch updated";
			if(expextedalert.contains(actualalert))
			{
				Reporter.log("Branch update Success::"+expextedalert+"   "+actualalert);
				return true;
			}
			else
			{
				Reporter.log("Branch update Fail::"+expextedalert+"   "+actualalert);
				return false;
			}
		}
		//method for logout
		public static boolean verifyLogout()throws Throwable
		{
			driver.findElement(By.xpath(config.getProperty("objlogout"))).click();
			Thread.sleep(5000);
			
			if(driver.findElement(By.xpath(config.getProperty("Objlogin"))).isDisplayed())
			{
				Reporter.log("Admin logout success",true);
				return true;
			}
			else
			{
				Reporter.log("Admin logout Fail",true);
				return false;

			}
		}
		//method for date genarate
		public static String generateDate()
		{
			Date date =new Date();
			DateFormat df = new SimpleDateFormat("YYYY_MM_hh dd_mm_ss");
			return df.format(date);
		}
	}


