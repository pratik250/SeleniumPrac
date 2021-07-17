package org.test.groww;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.pom.groww.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.configuration.classes.BaseClass_POJO;

public class TestLoginPage extends BaseClass_POJO
{
	private WebDriver driver;
	private LoginPage login;

	@BeforeSuite
	public void beforeSuite()
	{
		// ChromeOption 
		System.out.println("BeforeSuite Method ");
	}

	@BeforeTest
	@Parameters ("browser")
	public void launchBrowser(String browser) throws Exception
	{
		if(browser.equals("Chrome"))
		{
			//WebDriverManager.chromedriver().setup(); // change
			driver = chromeBrowser();
		}
		else if(browser.equals("Firefox"))
		{
			driver = firefoxBrowser();
		}
		else
		{
			//If no browser is passed throw exception
			throw new Exception("Incorrect Browser");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void launchUrl()
	{
		System.out.println("BeforeClass Method");
	}

	@BeforeMethod
	public void launchWebsite()
	{
		driver.get("https://groww.in/");
		login = new LoginPage(driver);
	}

	@Test (priority = 1)
	public void verifygetStartedButton()
	{
		String getStartedText = login.getStartedButton();
		System.out.println(getStartedText);
		Assert.assertEquals(getStartedText, "Get Started");
	}

	@Test (priority = 2)
	public void verifyLoginButton()
	{
		String loginText = login.loginRegisterButton();
		System.out.println(loginText);
		Assert.assertEquals(loginText, "Login/Register");
	}

	@Test (priority = 3)
	public void verifyLoginFunctionality() throws InterruptedException, EncryptedDocumentException, IOException
	{
		login.loginRegisterButton();
		login.email(1,0); // Row Cell Value 
		login.continueButton();
		login.password(1,1); // Row Cell Value 
		login.submitButton();
		login.enterPin(driver);

		Thread.sleep(2000);

		String verifyUrl = driver.getCurrentUrl();
		System.out.println(verifyUrl);
		Assert.assertEquals(verifyUrl, "https://groww.in/dashboard/explore/stocks","Url Verified....");
	}

	@BeforeMethod
	public void beforeMethod()
	{
		// here no req
	}

	@AfterClass
	public void objectDestroy()
	{
		login = null;
	}		

	@AfterTest
	public void closeBrowser()
	{
		driver.close();
	}
}