package Test.Suite1;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import Test.AbstractTest;
import Utils.TestUtils;

@RunWith(Parameterized.class)
public class LoginTest extends AbstractTest{

	String email;
	String password;
	String fname;
	
	public LoginTest(String email,String password,String fname) {
		this.email=email;
		this.password=password;
		this.fname=fname;		
	}
	
	@Parameters
	public static Collection<Object[]> getData() {
		Object[][] data = TestUtils.getTestData("LoginTest");
		return Arrays.asList(data);		 
	}
	
	//@Before
	@BeforeClass
	public static void InitBrowser() {
		//dr = new InternetExplorerDriver();
		dr = new FirefoxDriver();
		driver = new EventFiringWebDriver(dr);
	}

	@Test
	public void TestLogin() throws IOException {
		// initialize the resources like [OR, XlS_Reader etc] (Don't miss this statement)
		Initialize();
		
		if(!TestUtils.isSkipped("LoginTest")) {
			// report the test case is skipped
			System.out.println("inside skipping");	
			Assume.assumeTrue(false);
		}
		
		String result = TestUtils.isLogin(email,password,fname);
		if(result.contains("fail")) {
			// report error
			TestUtils.takeScreenShot("Xpath_login_verify_text"+fname);
			Assert.assertTrue(result, false);
		}
		
		TestUtils.logout();
	}
	
	//@After
	@AfterClass
	public static void closeBrowser() {
		driver.close();
	}

	
	
}
