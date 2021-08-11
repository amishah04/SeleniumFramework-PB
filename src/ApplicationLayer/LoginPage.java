package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	
	public static WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		
		this.driver=driver;
		
	}
	
	// maintain OR 
	
	@FindBy(name="txtUsername")
	WebElement username;
	
	@FindBy(name="txtPassword")
	WebElement password;
	
	
	@FindBy(name="Submit")
	WebElement loginbtn;
	
	//method for login
	public void verifylogin(String username, String password) throws Throwable
	
	{
		System.setProperty("webdriver.chrome.driver", "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/CommonDrivers/chromedriver");
		driver.get("http://orangehrm.qedgetech.com/");
		Thread.sleep(4000);
		
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		this.loginbtn.click();;
	}
	
	
}
