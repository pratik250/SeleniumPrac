package org.pom.groww;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutPage
{
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy (xpath = "//i[@class='material-icons downArrow']")
	private WebElement downArrow;
	
	@FindBy (xpath = "//div[text()='Log Out']")
	private WebElement logoutButton;
	
	public LogoutPage(WebDriver driver)
	{
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}
	
	public void clickOnLogout()
	{
		downArrow.click();
		logoutButton.click();
	}
}
