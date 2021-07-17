package com.configuration.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass_POJO 
{
	public static WebDriver chromeBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prati\\Documents\\Pratik Patil\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		return driver;
	}
	
	public static WebDriver firefoxBrowser()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\prati\\Documents\\Pratik Patil\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		return driver;
	}
	
	public static WebDriver operaBrowser()
	{
		return null ;
	}
}
