package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmpPage {

	WebDriver driver;
	public AddEmpPage(WebDriver driver)
	{
		this.driver=driver;
		
	}
	@FindBy(id="menu_pim_viewPimModule")
	WebElement clickpim;
	
	@FindBy(id="btnAdd")
	WebElement clickaddbtn;
	
	@FindBy(xpath="//input[@id='firstName']")
	WebElement fname;
	
	@FindBy(xpath="//input[@id='middleName']")
	WebElement lname;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement mname;
	
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement savebtn;
	
	public void verifyEmp(String fname,String lname,String mname)throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(clickpim).click().perform();
		Thread.sleep(3000);
		ac.moveToElement(this.clickaddbtn).click().perform();
		Thread.sleep(3000);
		
		this.fname.sendKeys(fname);
		this.lname.sendKeys(lname);
		this.mname.sendKeys(mname);
		ac.moveToElement(this.savebtn).click().perform();
		Thread.sleep(5000);
	}
}

