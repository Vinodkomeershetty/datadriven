package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Data.Xls_Reader;

public class AbstractTest {

	
	public static Xls_Reader reader = new Xls_Reader(System.getProperty("user.dir")+"\\src\\Data\\Suite1.xlsx");
	public static WebDriver dr;
	public static EventFiringWebDriver driver;
	
	public static Properties OR;
	public static Properties Config;
	public static Properties AppText;
	public static boolean isLoginFlag=false;
	
	
	public void Initialize() throws IOException {
		OR = new Properties();
		FileInputStream FI = new FileInputStream(System.getProperty("user.dir")+"//src//Config//OR.properties");
		OR.load(FI);
		
		Config = new Properties();
		FI = new FileInputStream(System.getProperty("user.dir")+"//src//Config//Config.properties");
		Config.load(FI);
		
		AppText = new Properties();
		FI = new FileInputStream(System.getProperty("user.dir")+"//src//Config//AppText.properties");
		AppText.load(FI);
	}
	
	public static WebElement getWebelement(String method) {
		//System.out.println(method);
		String[] path = method.split("_");	
		
		if(path[0].contains("Xpath")) {		    
				try{
					return driver.findElement(By.xpath(OR.getProperty(method)));
				}catch(Exception e){
					//report the error
					Assert.assertTrue("Element not found "+method, false);
					System.out.println(e.getMessage());
					System.out.println(e.getStackTrace());
				}
		} else if(path[0].contains("Name")) {
	        	try{
	        		return driver.findElement(By.name(OR.getProperty(method)));
	        			//return Driver.findElement(By.xpath((path)));	
        		}catch(Exception e){
        			//report the error
        			Assert.assertTrue("Element not found "+method, false);
        			System.out.println(e.getMessage());
        			System.out.println(e.getStackTrace());
        		}
	   }
		
		// report invalid method
		return driver.findElement(By.tagName("html"));
	}
	
	
}
