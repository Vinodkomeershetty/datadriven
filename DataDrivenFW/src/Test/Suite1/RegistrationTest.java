package Test.Suite1;

import Data.Xls_Reader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;

import Test.AbstractTest;
import Utils.TestUtils;

@RunWith(Parameterized.class)
public class RegistrationTest extends AbstractTest{
	String firestName;
	String lastNname;
	String email;
	String telephone;
	String address1;
	String city;
	String postCode;
	String country;
	String state;
	String password;
	String confirmPassword;
	
	public RegistrationTest(String firestName,String lastNname, String email,String telephone,String address1,String city,String postCode, String country,String state,String password,String confirmPassword) {
		this.firestName	= firestName;
		this.lastNname	= lastNname;
		this.email	= email;
		this.telephone	= telephone;
		this.address1	= address1;
		this.city	= city;
		this.postCode	= postCode;
		this.country	= country;
		this.state	= state;
		this.password	= password;
		this.confirmPassword	= confirmPassword;
	}
	
	//@Before
	@BeforeClass
	public static void InitBrowser() {
		//dr = new InternetExplorerDriver();
		dr = new FirefoxDriver();
		driver = new EventFiringWebDriver(dr);
	}

	
	@Test
	public void RegistrationTest() throws IOException {
		
		Initialize();
		
		if(!TestUtils.isSkipped("RegistrationTest")) {
			// report the test case is skipped
			System.out.println("inside skipping");	
			Assume.assumeTrue(false);
		}
		
		driver.get("http://cart.interiorsinn.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		getWebelement("Xpath_Reg_form_create_acc_link").click();
		
		// firest name
		getWebelement("Xpath_Reg_form_firstname_input").sendKeys(firestName);
		
		//last name
		getWebelement("Xpath_Reg_form_lastname_input").sendKeys(lastNname);
		
		//System.exit(0);
		
		// email
		getWebelement("Xpath_Reg_form_email_input").sendKeys(email);
		
		// telephone
		getWebelement("Xpath_Reg_form_telephone_input").sendKeys(telephone);
		
		// address1
		getWebelement("Xpath_Reg_form_add1_input").sendKeys(address1);
		
		// city
		getWebelement("Xpath_Reg_form_city_input").sendKeys(city);
		
		// post code
		getWebelement("Xpath_Reg_form_postcode_input").sendKeys(postCode);
		
		// country
		getWebelement("Xpath_Reg_form_country_select").sendKeys(country);
		
		// state
		getWebelement("Xpath_Reg_form_zone_select").sendKeys(state);
		
		//Select Dropdown = new Select(getWebelement("Xpath_Reg_form_zone_select"));
		//Dropdown.selectByVisibleText(state);		
		
		// password
		getWebelement("Xpath_Reg_form_password_input").sendKeys(password);
		
		// confirm password
		getWebelement("Xpath_Reg_form_confirmpassword_input").sendKeys(confirmPassword);
		
		// agreed
		getWebelement("Xpath_Reg_form_agree_checkbox").click();
		
		// submit
		getWebelement("Xpath_Reg_form_submit_button").click();
		
		
		try {
			String verifyText = driver.findElement(By.xpath(OR.getProperty("Xpath_Reg_verify_text"))).getText();
			if(!verifyText.equals(AppText.getProperty("Xpath_Reg_verify_text"))){
				// report error
				TestUtils.takeScreenShot("Xpath_Reg_verify_text"+firestName);
				Assert.assertTrue("Registration Failed, Actual text not same as expected text", false);
			}
		} catch(Throwable e) {
			// report the error
			TestUtils.takeScreenShot("Xpath_Reg_verify_text"+firestName);
			Assert.assertTrue("Registration Failed, verification text element not found", false);
		}
		
		TestUtils.logout();
	}
	
	@Parameters
	public static Collection<Object[]> getData() {
		Object[][] data = TestUtils.getTestData("RegistrationTest");
		return Arrays.asList(data);		 
	}
	
	//@After
	@AfterClass
	public static void closeBrowser() {
		driver.close();
	}
	
	
}
