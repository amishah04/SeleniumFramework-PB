package ApplicationLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Logout {

	WebDriver driver;
	public Logout(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(id="welcome")
	WebElement clickwelcome;
	@FindBy(linkText="Logout")
	WebElement clicklogout;
	public void verifylogout()throws Throwable
	{
		Actions ac = new Actions(driver);
		
		ac.moveToElement(clickwelcome).click().perform();
		Thread.sleep(4000);
		ac.moveToElement(clicklogout).click().perform();
		Thread.sleep(4000);
	}
}

