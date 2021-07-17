package org.test.groww;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pom.groww.HomePage;
import org.pom.groww.LoginPage;
import org.utility.classes.ExcelData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllTestCode 
{
//	<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
//	<dependency>
//		<groupId>commons-io</groupId>
//		<artifactId>commons-io</artifactId>
//		<version>2.10.0</version>
//	</dependency>
//
//	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
//	<dependency>
//		<groupId>org.apache.poi</groupId>
//		<artifactId>poi</artifactId>
//		<version>5.0.0</version>
//	</dependency>
//
//	<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
//	<dependency>
//		<groupId>org.apache.poi</groupId>
//		<artifactId>poi-ooxml</artifactId>
//		<version>5.0.0</version>
//	</dependency>

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException 
	{
		String filePath = "C:\\Users\\prati\\Documents\\Pratik Patil\\VelocityData.xlsx";
		String data = ExcelData.getExcelData(filePath, "velocity1", 1, 1);
		System.out.println(data);
//		WebDriverManager.chromedriver().setup();
//
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://groww.in/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//		JavascriptExecutor jse = (JavascriptExecutor)driver;


		// Login into Groww App
//		LoginPage login = new LoginPage(driver);
//		login.loginRegisterButton();
//		login.email("Pratikpatil698@gmail.com");
//		login.continueButton();
//		login.password();
//		login.submitButton();
//		login.enterPin(driver);

		// HomePage
//		HomePage home = new HomePage(driver,jse);
//
//		System.out.println("Top Sectors Banking Table \n");
//		home.topSectorSection();
//		home.banking();
//		home.topSectorsTable();
//		home.nextPageForTopSectorsTable_OnlyForBanking(3);
//		home.topSectorsTable();
//		home.gotoHomePage(); // back to homePage
//		
//		System.out.println("Search Stock : ");
//		
//		Thread.sleep(2000);
//		home.searchStocks("Wipro");
//		home.typeBuySell();
//		home.orderType("Delivery");
//		home.selectSharesType_EnterQuantity("NSE",1);
//		home.selectSharePriceType("market", 600);
//		
//		String price = driver.findElement(By.xpath("//div[@class='fs20 bso21LetterSpace clrText fw500']")).getText();
//		System.out.println(price);
//
//		System.out.println("\nStocks in News :");
// 
//		for(int i= 1; i<=4; i++)
//		{
//			//div[@class='row'])[2] -> 2nd row dynamic
//			// Stock Name Print
//			WebElement stocksName = driver.findElement(By.xpath("(//div[@class='row'])[2]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
//			System.out.println(stocksName.getText()); 
//			////div[3] -> 3rd fix
//			// Stock Price & % change
//			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[2]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
//			System.out.println(stocksPrice.getText().replaceAll("₹", "₹ ").replaceAll("\n", "  "));
//		}
//
//		System.out.println("\nTop Gainers :");
//		for(int i= 1; i<=4; i++)
//		{
//			// //div[@class='row'])[3] -> 3rd row dynamic
//			WebElement stocksInNews = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
//			System.out.println(stocksInNews.getText());
//
//			////div[3] -> 3rd fix
//			// Stock Price & % change
//			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
//			System.out.println(stocksPrice.getText().replaceAll("₹", "₹ ").replaceAll("\n", "  "));
//		}
//
//		System.out.println("\nTop Losers :");
//		for(int i= 1; i<=4; i++)
//		{
//			// //div[@class='row'])[4] -> 4rd row dynamic final row
//			WebElement stocksInNews = driver.findElement(By.xpath("(//div[@class='row'])[4]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
//			System.out.println(stocksInNews.getText());
//
//			////div[3] -> 3rd fix
//			// Stock Price & % change
//			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
//			System.out.println(stocksPrice.getText().replaceAll("₹", "₹ ").replaceAll("\n", "  "));
//		}


//		System.out.println("Top Sectors :");
//		for(int i=1; i<=7; i++)
//		{
//			WebElement sectorNames = driver.findElement(By.xpath("//div[@class='col l12 dashMfStockDiv']//div[@class='valign-wrapper']//a"+"["+i+"]"));
//
//			System.out.println("Secor Name : "+sectorNames.getText());
//			Thread.sleep(1000);
//
//			sectorNames.click();
//			Thread.sleep(1000);
//
//			home.topSectorsTable();
//			Thread.sleep(2000);
//
//			home.gotoHomePage();
//			Thread.sleep(1000);
//
//			System.out.println();
//		}
//		
		// driver.close();
	}
}
