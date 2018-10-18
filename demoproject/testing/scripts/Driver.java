package scripts;

import java.io.BufferedReader;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class Driver extends RF{
	static Properties properties = null;
	static BufferedReader reader;

	static void TC01() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC01");

		//give data file path
		readDataFile("./testing/dataFiles/TC01.properties");

		//Resuable flow : opens firefox -> open salesforce login page -> gives input for user name and password 
		enterUserNamePassword();

		//click login
		WebElement loginButton = driver.findElement(getLocator("salesforce.login.Button.loginButton"));
		clickElement(loginButton, "Login button");

		// verify error message is as expected
		WebElement errorMessage = driver.findElement(getLocator("salesforce.login.errorMessage"));
		verifyText(errorMessage, "Error message", dataProp.getProperty("expectedErrorMessage"));

		//close browser
		closeBrowser();
	}
	
	static void TC03() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC03");

		//give data file path
		readDataFile("./testing/dataFiles/TC03.properties");

		//Resuable flow : opens firefox -> open salesforce login page -> gives input for user name and password 
		enterUserNamePassword();

		// check remember me box
		WebElement rememberMe = driver.findElement(RM.getLocator("salesforce.login.rememberMe"));
		RM.clickElement(rememberMe, "Remember Me ");
		
		//click login
		WebElement loginButton = driver.findElement(getLocator("salesforce.login.Button.loginButton"));
		clickElement(loginButton, "Login button");
	
		//click user menu arrow
		WebElement userMenuArrow = driver.findElement(getLocator("salesforce.homePage.userMenu.arrow"));
		clickElement(userMenuArrow, "User Menu");
		
		//click logout
		WebElement logout = driver.findElement(getLocator("salesforce.homePage.userMenu.Logout"));
		clickElement(logout, "Logout");
		
		Thread.sleep(3000);
		//verify user name saved or not
		WebElement savedUserName = driver.findElement(getLocator("salesforce.login.Button.savedUserName"));
		RM.verifyText(savedUserName, "Saved user name", dataProp.getProperty("userName"));

		//close browser
		closeBrowser();
	}
	public static void main(String[] args) throws Exception {
		initializeExtentReport();

		readORFile("./testing/Repository/objRep.properties");
		readConfigFile("./testing/Repository/config.properties");
		//TC01();
		TC03();
		System.out.println("from maven");
		extent.flush();

	}
}
