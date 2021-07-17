package org.pom.groww;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor jse;

	@FindBy(xpath = "//a[text()='Explore']")
	private WebElement gotoHomePage;

	@FindBy (xpath = "//div[text()='Top Sectors']")
	private WebElement topSectors;

	@FindBy (xpath = "//span[text()='Banking']")
	private WebElement banking;

	@FindBy (xpath = "//table[@class='tb10Table']//tbody//tr")
	private List<WebElement> topSectorsTable;

	@FindBy (xpath = "//input[@placeholder='Search stocks and mutual funds']")
	private WebElement searchStocks;

	@FindBy (xpath = "(//span[contains(@class,'fw500 clrText')])[1]")
	private WebElement searchStocks_Click;

	@FindBy (xpath = "//div[@class='fs14 fw500 bso21LetterSpace cur-po p19']")
	private WebElement typeBuySell; // Buy-Sell

	@FindBy (xpath = "(//div[@class='fs14 fw500 bso21LetterSpace cur-po p19'])[1]")
	private WebElement typeBuy; // Buy

	@FindBy (xpath = "(//div[@class='fs14 fw500 bso21LetterSpace cur-po p19'])[2]")
	private WebElement typeSell; // Sell

	@FindBy (xpath = "(//span[@class='fw500 fs14 bso21LetterSpace clrText valign-wrapper cur-po ml6'])[1]")
	private WebElement orderType; // Delivery-MIS(Intraday)

	@FindBy (xpath = "//span[@class='fw500 fs14 bso21LetterSpace clrText valign-wrapper ml6 cur-po']")
	private WebElement selectSharesType; // NSE-BSE

	@FindBy (xpath = "//input[@id='inputShare']")
	private WebElement selectShareQuantity; // NSE-BSE Share Quantity

	@FindBy (xpath = "(//span[@class='fw500 fs14 bso21LetterSpace clrText valign-wrapper cur-po ml6'])[2]")
	private WebElement selectSharePriceType; // MKT-Limit-SL-SLM

	@FindBy (xpath = "//div[contains(@class,'fs20 bso21LetterSpace clrText fw500')]")
	private WebElement marketSharePrice; // MKT Price Fetch

	@FindBy (xpath = "//input[contains(@step,'0.05')]")
	private WebElement limitOrder; // Limit
	
	@FindBy (xpath = "//span[text()='BUY']")
	private WebElement buyButton;
	
	@FindBy (xpath = "//span[text()='CONFIRM ORDER']")
	private WebElement confirmOrderButton;
	

	// Constructor
	public HomePage(WebDriver driver,JavascriptExecutor jse)
	{
		this.driver = driver;
		this.jse = jse;

		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS); // implicitWait

		wait = new WebDriverWait(driver, 10); // explicit Wait
		
	}

	public void gotoHomePage()
	{
		gotoHomePage.click();
	}

	public void topSectorSection()
	{
		jse.executeScript("arguments[0].scrollIntoView(true);", topSectors);
	}

	public void banking()
	{
		banking.click();
	}

	public void searchStocks(String stockName) throws InterruptedException
	{	
		Thread.sleep(2000);
		// Not Working
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search stocks and mutual funds']")));
	//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class,'fw500 clrText')])[1]")));
		searchStocks.sendKeys(stockName);
		searchStocks_Click.click();
		System.out.println("Share Name : "+stockName);	
	}

	public String verifySearchText()
	{
		String searchTextBoxText = searchStocks.getAttribute("placeholder"); 

		return searchTextBoxText;
	}

	// Buy-Sell
	public void typeBuySell()
	{
		String order_type = typeBuySell.getText();
		System.out.println("Type of Order is (Buy-Sell): "+order_type);
	}
	public String selectBuy()
	{
		String order_type = typeBuy.getText();
		System.out.println("Order (BUY/SELL) : "+order_type);

		return order_type;
	}
	public String selectSell()
	{
		String order_type = typeSell.getText();
		System.out.println("Order (BUY/SELL) : "+order_type);

		return order_type;
	}

	// Delivery-MIS(Intraday)
	public String orderType(String type)
	{
		String typeOfOrder = null;
		
		if(type.equalsIgnoreCase("Delivery"))
		{
			typeOfOrder = orderType.getText().replaceAll("\nkeyboard_arrow_down", "");
			System.out.println("Order Type is Delivery: "+typeOfOrder);
		}
		else
		{
			orderType.click(); // MIS Order select
			typeOfOrder = orderType.getText().replaceAll("\nkeyboard_arrow_down", "");
			System.out.println("Order Type is MIS(Intraday): "+typeOfOrder);
		}
		return typeOfOrder;
	}

	// NSE-BSE -> Enter Quantity
	public String selectSharesType_EnterQuantity(String shareType,int quantity)
	{
		//NSE \nkeyboard_arrow_down changed to -> NSE Selected Here
		String type = selectSharesType.getText().replaceAll("\nkeyboard_arrow_down", "");
		System.out.println("Share Type (NSE,BSE): "+type+" Quantity : "+quantity);
		if(type.equals(shareType.toUpperCase()))
		{
			selectShareQuantity.sendKeys(Integer.toString(quantity));
		}
		
		return type;
	}

	// MKT-Limit-SL-SLM
	public String selectSharePriceType(String buySelltype,int limitPrice )
	{
		String verifyText = null;
		if(buySelltype.equalsIgnoreCase("Limit"))
		{
			verifyText = buySelltype;
			
			selectSharePriceType.click(); // when market opens only use this method
			limitOrder.click();
			limitOrder.clear();
			limitOrder.sendKeys(Integer.toString(limitPrice)); // convert int to String type
		}

		else if(buySelltype.equalsIgnoreCase("Market"))
		{
			verifyText = buySelltype;
			//selectSharePriceType.click(); // select Market type order (if mkt closed then use this )
			String currentMarketPrice = marketSharePrice.getText();
			System.out.println("Current Market Price is :"+currentMarketPrice);
		}
		String priceType = selectSharePriceType.getText().replaceAll("\nkeyboard_arrow_down", "");
		System.out.println("Share Price Type (MKT-Limit-SL-SLM) : "+priceType);
		
		return verifyText;
	}
	
	public String buyButton() 
	{
		String verifyText = buyButton.getText();
		buyButton.click();
		
		return verifyText;
	}
	
	public String confirmOrderButton()
	{
		String verifyText = confirmOrderButton.getText();
		confirmOrderButton.click();
		
		return verifyText;
		
	}
	
	public void topSectorsTable()
	{		
		for(int i=1; i<=topSectorsTable.size(); i++)
		{
			// C-Name
			WebElement bankNames = driver.findElement(By.xpath("//table[@class='tb10Table']//tbody//tr"+"["+i+"]"+"//td[1]"));
			System.out.print(bankNames.getText()+"\t");
			// C-Price
			WebElement bankPrices = driver.findElement(By.xpath("//table[@class='tb10Table']//tbody//tr"+"["+i+"]"+"//td[4]"));
			System.out.println(bankPrices.getText().replaceAll("₹","RS: "));
		}
	}
	public void nextPageForTopSectorsTable_OnlyForBanking(int totalPage)
	{
		for (int i = 1; i<=totalPage; i++) 
		{
			driver.findElement(By.xpath("//div[text()='"+i+"']")).click();
			topSectorsTable();
		}	
	}

	//-------------------------------------------------------------------------------------------------

	public void stocksInNews()
	{
		System.out.println("\nStocks in News :");

		for(int i= 1; i<=4; i++)
		{
			//div[@class='row'])[2] -> 2nd row dynamic
			// Stock Name Print
			WebElement stocksName = driver.findElement(By.xpath("(//div[@class='row'])[2]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
			System.out.println(stocksName.getText()); 
			////div[3] -> 3rd fix
			// Stock Price & % change
			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[2]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
			System.out.println(stocksPrice.getText().replaceAll("₹", "Rs: ").replaceAll("\n", "  "));
		}
	}

	public void topGainers()
	{
		System.out.println("\nTop Gainers :");
		for(int i= 1; i<=4; i++)
		{
			// //div[@class='row'])[3] -> 3rd row dynamic
			WebElement stocksInNews = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
			System.out.println(stocksInNews.getText());

			////div[3] -> 3rd fix
			// Stock Price & % change
			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
			System.out.println(stocksPrice.getText().replaceAll("₹", "Rs: ").replaceAll("\n", "  "));
		}
	}

	public void topLosers()
	{
		System.out.println("\nTop Losers :");
		for(int i= 1; i<=4; i++)
		{
			// //div[@class='row'])[4] -> 4rd row dynamic final row
			WebElement stocksInNews = driver.findElement(By.xpath("(//div[@class='row'])[4]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[2]//div[1]//div"));
			System.out.println(stocksInNews.getText());

			////div[3] -> 3rd fix
			// Stock Price & % change
			WebElement stocksPrice = driver.findElement(By.xpath("(//div[@class='row'])[3]//div[@class='col l12 valign-wrapper tpm667ListDiv']//a"+"["+i+"]"+"//div[3]"));
			System.out.println(stocksPrice.getText().replaceAll("₹", "Rs:").replaceAll("\n", "  "));
		}
	}

	public void topSectors() throws InterruptedException
	{
		//List<WebElement> totalPage = driver.findElements(By.xpath("//div[@class='pg1231Container']//div"));

		System.out.println("Top Sectors :"); // first Page Print Only
		for(int i=1; i<=7; i++)
		{
			WebElement sectorNames = driver.findElement(By.xpath("//div[@class='col l12 dashMfStockDiv']//div[@class='valign-wrapper']//a"+"["+i+"]"));
			//WebElement sectorNames = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='col l12 dashMfStockDiv']//div[@class='valign-wrapper']//a"+"["+i+"]")));

			System.out.println("Secor Name : "+sectorNames.getText());

			sectorNames.click();

			topSectorsTable();
			Thread.sleep(3000);

			//System.out.println(totalPage.size());

			gotoHomePage();

			System.out.println();
		}
	}
}