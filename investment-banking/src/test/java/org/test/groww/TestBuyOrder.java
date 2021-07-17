package org.test.groww;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
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
import org.testng.asserts.SoftAssert;
import org.utility.classes.TakesScreenShot_Selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.configuration.classes.BaseClass_POJO;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBuyOrder
{
	private WebDriver driver;
	private LoginPage login;
	private LogoutPage logout;
	private HomePage home;
	private JavascriptExecutor jse;
	private SoftAssert soft;
	
	int testID;
	
	static ExtentTest test;
	static ExtentHtmlReporter reporter;

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
		reporter = new ExtentHtmlReporter("test-output//extentReport//Extent.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter(reporter);
		
		// Launch Browser Code here

		// ScreenRecorderUtil.startRecord("launchBrowser");

		if(browser.equals("Chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = BaseClass_POJO.chromeBrowser();
			System.out.println("Chrome Browser ");
		}
		else if(browser.equals("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@BeforeClass
	public void createObject()
	{
		// Open Url & POM class Objects Created
		driver.get("https://groww.in/");

		jse = (JavascriptExecutor)driver;
		login = new LoginPage(driver);
		home = new HomePage(driver, jse);
		logout = new LogoutPage(driver);
		soft = new SoftAssert();
	}

	@BeforeMethod
	public void loginGrowwWebsite() throws EncryptedDocumentException, IOException
	{
		
		login = new LoginPage(driver);
		login.loginRegisterButton();
		login.email(1,0); // hardcodeVal
		login.continueButton();
		login.password(1,1);
		login.submitButton();
		login.enterPin(driver);
	}

	//	@Test(priority = 1)
	//	public void verifySearchFunctionality() throws InterruptedException
	//	{
	//		String verifyText = home.verifySearchText();
	//		System.out.println(verifyText);
	//		soft.assertEquals(verifyText, "Search stocks and mutual funds","Search Text Box verified successfully.....");
	//		Thread.sleep(1200);
	//		soft.assertAll();	
	//	}

	@Test(priority = 2)//invocationCount = 2
	public void verifyStockOrderFunctionality() throws InterruptedException 
	{
		// BUY ORDER 
		home.searchStocks("jm finan");

		String buyOrderSelected = home.selectBuy(); // buy order
		Assert.assertEquals(buyOrderSelected, "BUY","Assertion : Buy Order Selected Verified");

		String buyDeliveryOrderType = home.orderType("Delivery"); // type of order Delivery-MIS
		Assert.assertEquals(buyDeliveryOrderType, "Delivery","Assertion : Delivery Order Selected Verified");

		//		String buyMISOrderType = home.orderType("MIS"); // type of order Delivery-MIS
		//		Assert.assertEquals(buyMISOrderType, "MIS","Assertion : MIS Order Selected Verified");

		String buyNSEOrder = home.selectSharesType_EnterQuantity("NSE", 1);
		Assert.assertEquals(buyNSEOrder, "NSE","Assertion : NSE Order Selected Verified");

		//		String marketPriceType = home.selectSharePriceType("Market", 45);
		//		Assert.assertEquals(marketPriceType, "Market","Assertion : Market Order Verified");

		String limitPriceType = home.selectSharePriceType("Limit", 98);
		Assert.assertEquals(limitPriceType, "Limit","Assertion : Limit Order Verified"); // limit price low < mkt

		//		String buyButton = home.buyButton();
		//		Assert.assertEquals(buyButton, "BUY","Assertion : BUY Button Verified");

		//		String confirmButton = home.confirmOrderButton();
		//		Assert.assertEquals(confirmButton, "CONFIRM ORDER","Assertion : CONFIRM ORDER Button Verified");

	}

	@AfterMethod
	public void logoutGroww(ITestResult result) throws IOException, AWTException, InterruptedException
	{
		if(ITestResult.FAILURE == result.getStatus())
		{
			TakesScreenShot_Selenium.takeScreenShot_FileName(driver, "FailTestId"+testID+".jpeg");
		}
		
		Thread.sleep(2000);
		TakesScreenShot_Selenium.captureScreenShotWithTestStepNameUsingRobotClass("Order2");
		Thread.sleep(2000);
		TakesScreenShot_Selenium.takeScreenShot_FileName(driver, "fileNameMethod2.png");
		Thread.sleep(500);
		logout.clickOnLogout();
	}

	@AfterClass
	public void objectDestroy()
	{
		// Object Destroy or Call Garbage Collector
		jse = null;
		login = null;
		home = null;
		logout = null;
		soft = null;
	}

	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}
	
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("After Suite Method ");
	}
}
