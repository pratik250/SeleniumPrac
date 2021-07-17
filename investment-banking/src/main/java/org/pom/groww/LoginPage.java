package org.pom.groww;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utility.classes.ExcelData;

public class LoginPage 
{	
	@FindBy (xpath = "//span[contains(text(),'Login/Register')]")
	private WebElement loginRegisterButton;

	@FindBy (xpath = "//span[contains(text(),'Get Started')]")
	private WebElement getStartedButton;

	@FindBy (xpath = "//div[@class='child-wrapper lpw107LoginPopupWrapper']//span[@class='rodal-close']")
	private WebElement closeLoginRegisterPoPUp;

	@FindBy (xpath = "//input[@id='login_email1']")
	private WebElement emailAddress;

	@FindBy (xpath = "//span[contains(text(),'Continue')]")
	private WebElement continueButton;

	@FindBy (xpath = "//input[@id='login_password1']")
	private WebElement password;

	@FindBy (xpath = "//span[text()='Submit']")
	private WebElement submitButton;

	@FindBy (xpath = "(//input[@maxlength='1'])[1]")
	private WebElement enterPin;


	public LoginPage(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	public String loginRegisterButton() 
	{
		String loginText = loginRegisterButton.getText();
		loginRegisterButton.click();

		return loginText;
	}

	public String getStartedButton() 
	{
		String getStartedText = getStartedButton.getText();
		getStartedButton.click();

		return getStartedText;
	}

	public void email(int row, int cell) throws EncryptedDocumentException, IOException
	{
		String email = ExcelData.getExcelData("C:\\Users\\prati\\Documents\\Pratik Patil\\IB.xlsx", "groww", row, cell);
		emailAddress.sendKeys(email);
	}

	public void continueButton()
	{
		continueButton.click();
	}

	public void password(int row, int cell) throws EncryptedDocumentException, IOException
	{
		String passWd = ExcelData.getExcelData("C:\\Users\\prati\\Documents\\Pratik Patil\\IB.xlsx", "groww", row, cell);

		password.sendKeys(passWd);
	}

	public void submitButton()
	{
		submitButton.click();
	}

	public void enterPin(WebDriver driver)
	{	
		//enterPin.sendKeys("1998");
		
		driver.findElement(By.xpath("(//input[@maxlength='1'])[1]")).sendKeys("1");
		driver.findElement(By.xpath("(//input[@maxlength='1'])[2]")).sendKeys("9");
		driver.findElement(By.xpath("(//input[@maxlength='1'])[3]")).sendKeys("9");
		driver.findElement(By.xpath("(//input[@maxlength='1'])[4]")).sendKeys("8");
	}	
}