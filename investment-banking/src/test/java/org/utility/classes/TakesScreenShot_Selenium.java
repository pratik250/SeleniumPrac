package org.utility.classes;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class TakesScreenShot_Selenium 
{	
	// By Using Date Class
	public static void takeScreenShot_Date(WebDriver driver) throws IOException, InterruptedException 
	{
		Date date = new Date();
		String fileName = date.toString().replace(" ","-").replace(":","-").replace("-IST", "");
		Thread.sleep(2000);
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("./ScreenShots/Screen-Shot-"+fileName+".png");
		Files.copy(source, destination);
	}
	// With File 
	public static void takeScreenShot_FileName(WebDriver driver,String fileName) throws IOException
	{
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(file, new File("./ScreenShots/"+fileName));
	}
	
	public static String captureScreenShotWithTestStepNameUsingRobotClass(String fileName) throws IOException, AWTException
	{

		// Creating Robot class object
		Robot robotClassObject = new Robot();

		// Get screen size
		Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

		// Capturing screenshot by providing size
		BufferedImage tmp = robotClassObject.createScreenCapture(screenSize); 

		// Defining destination file path
		String path="./ScreenShots/"+fileName+".png";

		// To copy temp image in to permanent file
		ImageIO.write(tmp, "png",new File(path)); 
		return path;

	}
	
	// With BYTES
	public static void takeScreenShot_Bytes(WebDriver driver,String fname) throws IOException
	{
		byte[] byteArray = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		FileOutputStream fos = new FileOutputStream(new File("./ScreenShots/"+fname));
		fos.write(byteArray);
		fos.close();
	}
}	