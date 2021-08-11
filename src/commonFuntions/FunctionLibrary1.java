package commonFuntions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.PBconstant1;

public class FunctionLibrary1 extends PBconstant1 {

	// method of login
	
public static boolean verifyLogin(String username, String password) throws Throwable
{
	driver.findElement(By.xpath(config.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.xpath(config.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.xpath(config.getProperty("Objlogin"))).click();
	Thread.sleep(4000);

	// compare 
	String expected = "Primus BANK";
	String actual = driver.getTitle();
	
	if(actual.contains(expected))
	{
		Reporter.log("Login Success: "+ expected + "  "+ actual,true);
		return true;
	}
	else {
		Reporter.log("Login Fail: "+ expected + "  "+ actual,true);
		return false;
		
	}
}
	
// method for branch click

public static void clickBranches () throws Throwable
{
	
driver.findElement(By.xpath(config.getProperty("clickBranches"))).click();
Thread.sleep(4000);
}
	
//method for new branch creation

public static boolean verifynewBranch (String bname, String add1, String add2, String add3, String area, String zipcode, String country, String city, String state) throws Throwable 
{
	
driver.findElement(By.xpath(config.getProperty("clickNewBranch"))).click();
Thread.sleep(4000);

	driver.findElement(By.xpath(config.getProperty("Branchname"))).sendKeys(bname);
	driver.findElement(By.xpath(config.getProperty("Address1"))).sendKeys(add1);
	driver.findElement(By.xpath(config.getProperty("Address2"))).sendKeys(add2);
	driver.findElement(By.xpath(config.getProperty("Address3"))).sendKeys(add3);
	driver.findElement(By.xpath(config.getProperty("Area"))).sendKeys(area);
	driver.findElement(By.xpath(config.getProperty("ZipCode"))).sendKeys(zipcode);
	new Select(driver.findElement(By.xpath(config.getProperty("Country")))).selectByVisibleText(country);
	Thread.sleep(4000);
	new Select(driver.findElement(By.xpath(config.getProperty("State")))).selectByVisibleText(city);
	Thread.sleep(4000);
	new Select(driver.findElement(By.xpath(config.getProperty("City")))).selectByVisibleText(state);;
	Thread.sleep(4000);
	driver.findElement(By.xpath(config.getProperty("submitbtn"))).click();
	Thread.sleep(4000);

//capture alert message
	String expectedalert = driver.switchTo().alert().getText();
	driver.switchTo().alert().accept();
	String actualalert = "New Branch with id";
	if(expectedalert.contains(actualalert))
	{
	Reporter.log("New Branch created::"+expectedalert+"    "+actualalert,true);
	return true;
}
else {

	Reporter.log("New Branch Not created::"+expectedalert+"    "+actualalert,true);
	return false;
	
}
}
// method for branch updation
public static boolean verifybranchUpdate (String brname, String addup, String zipup, String countryup, String stateup, String cityup)
{
	
driver.findElement(By.xpath(config.getProperty("clickEdit"))).click();
driver.findElement(By.xpath(config.getProperty("BranchNameUpdate"))).sendKeys("brname");
driver.findElement(By.xpath(config.getProperty("Addressupdate"))).sendKeys("");
driver.findElement(By.xpath(config.getProperty("zipcodeupdate"))).sendKeys("");
new Select(driver.findElement(By.xpath(config.getProperty("countryupdate")))).selectByVisibleText(countryup);
new Select(driver.findElement(By.xpath(config.getProperty("stateupdate")))).selectByVisibleText(stateup);
new Select(driver.findElement(By.xpath(config.getProperty("cityupdate")))).selectByVisibleText(cityup);
driver.findElement(By.xpath(config.getProperty("updatebtn"))).click();

//capture alert message

String actualalert = driver.switchTo().alert().getText();
driver.switchTo().alert().accept();
String exectedalert = "Branch updated";

if(actualalert.contains(exectedalert))
{
Reporter.log("Branch update Success" + actualalert + "  "+ exectedalert, true);	
return true;

}
else {
	Reporter.log("Branch update fail" + actualalert + "  "+ exectedalert, true);	
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



