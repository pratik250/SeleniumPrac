package org.test.groww;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pom.groww.HomePage;
import org.pom.groww.LoginPage;
import org.pom.groww.LogoutPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.utility.classes.ScreenRecorderUtil;
import org.utility.classes.TakesScreenShot_Selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.configuration.classes.BaseClass_POJO;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestHomePageContentPrint
{
	private WebDriver driver;
	private JavascriptExecutor jse;
	private LoginPage login;
	private HomePage home;
	private LogoutPage logout;
	int testID;
	
	static ExtentTest test;
	static ExtentHtmlReporter reporter;

	@BeforeSuite
	public void beforeSuite()
	{
		System.out.println("BeforeSuite Method ");
		// ChromeOption 
	}

	@BeforeTest
	@Parameters ("browser")
	public void launchBrowser(String browser) throws Exception
	{
		reporter = new ExtentHtmlReporter("test-output/extentReport");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		
		// Launch Browser Code here

		// ScreenRecorderUtil.startRecord("launchBrowser");
		System.out.println("Before Method");
				
		if(browser.equals("Chrome"))
		{
			//WebDriverManager.chromedriver().setup();
			driver = BaseClass_POJO.chromeBrowser();
			System.out.println("Chrome Browser ");
		}
		else if(browser.equals("Firefox"))
		{
			//WebDriverManager.firefoxdriver().setup();
			driver = BaseClass_POJO.firefoxBrowser();
			System.out.println("Firefox Browser ");
		}
		else
		{
			//If no browser is passed throw exception
			throw new Exception("Incorrect Browser");
		}

		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void createObject()
	{
		System.out.println("Before Class");
		// Open Url
		driver.get("https://groww.in/");
		// POM class Objects Created
		jse = (JavascriptExecutor)driver;
		login = new LoginPage(driver);
		home = new HomePage(driver, jse);
	}

	@BeforeMethod
	public void growwLogin() throws InterruptedException, EncryptedDocumentException, IOException
	{
		// Login into Groww App
		login.loginRegisterButton();
		login.email(1,0);
		login.continueButton();
		login.password(1,1);
		login.submitButton();
		login.enterPin(driver);

	}

	@Test(priority = 1)
	public void verifyStocksInNews()
	{
		testID = 100;
		home.stocksInNews();
	}

	@Test(priority = 2)
	public void verifyTopGainers()
	{
		testID = 200;
		home.topGainers();
	}

	@Test(priority = 3)
	public void verifyTopLosers()
	{
		testID = 300;
		home.topLosers();
	}

	@AfterMethod
	public void logoutGroww(ITestResult result) throws IOException, InterruptedException
	{
		if(ITestResult.FAILURE == result.getStatus())
		{
			TakesScreenShot_Selenium.takeScreenShot_FileName(driver, "FailTestId"+testID+".jpeg");
		}
		// Logout 
		logout = new LogoutPage(driver);
		logout.clickOnLogout();
	}

	@AfterClass
	public void objectDestroy() throws Exception
	{
		// Object Destroy or Call Garbage Collector
		jse = null;
		login = null;
		home = null;
	}

	@AfterTest
	public void afterTest()
	{
		driver.quit();
		//	ScreenRecorderUtil.stopRecord();
	}

	@AfterSuite
	public void afterSuite()
	{
		System.out.println("After Suite");
	}
}