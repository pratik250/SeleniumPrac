
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RandomTest 
{
	public static void test() 
	{
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.get("https://groww.in/");
		driver.quit();


//		<test name="Test2">
//		<classes>
//		<class name="org.test.groww.TestHomePageContentPrint" />
//		<class name="org.test.groww.TestLoginPageContent" />
//		</classes>
//		</test> <!-- Test -->
	}
}
