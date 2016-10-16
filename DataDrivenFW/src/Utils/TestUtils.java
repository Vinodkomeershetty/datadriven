package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;

import Test.AbstractTest;

public class TestUtils extends AbstractTest{

	
	public static String isLogin(String email,String password, String fname) {

		if(isLoginFlag) {
			TestUtils.logout();
		}
		
		driver.get("http://cart.interiorsinn.com");
		
		getWebelement("Xpath_login_link").click();
		getWebelement("Xpath_login_email").sendKeys(email);
		getWebelement("Xpath_login_password").sendKeys(password);
		getWebelement("Xpath_login_submit_button").click();

		try {
		String actual_fname = driver.findElement(By.xpath(OR.getProperty("Xpath_login_verify_fname"))).getText();
		if(!actual_fname.equals(fname)) {
			return "fail-Actual text is nost same as expected text";
		}	
		}catch(Throwable t) {
			return "fail- Xpath_login_verify_fname element not found";
		}
		isLoginFlag=true;
		return "pass";
	}
	
	public static void logout() {
		getWebelement("Xpath_Logout_link").click();
		isLoginFlag=false;
	}
	
	
	public static boolean isSkipped(String testcase) {
		int rowNum = reader.getCellRowNum("TestCases", "TCN", testcase);
		String runMode = reader.getCellData("TestCases", "RunMode", rowNum);
		if(runMode.contains("Y")) {
			return true;
		}		
		return false;
	}
	
	
	public static void takeScreenShot(String filename) {
		
		File outputFile = driver.getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(outputFile, new File(System.getProperty("user.dir")+"//src//ScreenShots//"+filename+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static Object[][] getTestData(String sheetName) {
		Object[][] data = new Object[reader.getRowCount(sheetName)-1][reader.getColumnCount(sheetName)];
		for(int row=2;row<=reader.getRowCount(sheetName);row++) {
			for(int col=0;col<reader.getColumnCount(sheetName);col++) {
				data[row-2][col]=reader.getCellData(sheetName, col, row);
			}
		}
		return data;
	}
	
}
