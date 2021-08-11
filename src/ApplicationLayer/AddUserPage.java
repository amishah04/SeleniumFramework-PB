package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUserPage {

	WebDriver driver;
	
	public AddUserPage(WebDriver driver)
	{
		
		this.driver = driver;
		
		
	}
	
	
	@FindBy(id="menu_admin_viewAdminModule")
	WebElement clickadmin;
	
	@FindBy(id="menu_admin_UserManagement")
	WebElement clickmanagement;
	
	@FindBy(id="menu_admin_viewSystemUsers")
	WebElement clickusers;
	
	@FindBy(id="btnAdd")
	WebElement clickaddbtn;
	
	@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
	WebElement ename;
	
	@FindBy(xpath="//input[@id='systemUser_userName']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='systemUser_password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='systemUser_confirmPassword']")
	WebElement cpassword;
	
	@FindBy(xpath="//input[@id='btnSave']")
	WebElement clicksavebtn;
	
	
	public void verifyAddUser(String ename,String username,String password,String cpassword)
	throws Throwable{
	
		// mouseover 
		Actions ac = new Actions(driver);
	
	ac.moveToElement(clickadmin).perform();
	Thread.sleep(3000);
	
	ac.moveToElement(clickmanagement).perform();
	Thread.sleep(3000);
	
	ac.moveToElement(clickusers).click().perform();
	Thread.sleep(3000);
	
	ac.moveToElement(clickaddbtn).click().perform();
	Thread.sleep(3000);
	
	this.ename.sendKeys(ename);
	this.username.sendKeys(username);
	this.password.sendKeys(password);
	Thread.sleep(5000);
	this.cpassword.sendKeys(cpassword);
	Thread.sleep(4000);
	this.clicksavebtn.click();
	Thread.sleep(5000);
}}
	
