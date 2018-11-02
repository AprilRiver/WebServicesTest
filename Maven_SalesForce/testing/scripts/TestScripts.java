package scripts;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class TestScripts extends RF{
	
	@BeforeSuite
	public static void driverMethod()
	{
		initializeExtentReport();
		readORFile("./testing/Repository/objRep.properties");
		readConfigFile("./testing/Repository/config.properties");
	}
	
	@AfterMethod
	public static void afterEveryTC()
	{
		closeBrowser();
		extent.flush();
		System.out.println("from test ng maven");
	}
	
	@Test
	public static void TC01_LoginErrorMessage() throws Exception
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

	}

	@Test
	public static void TC03_CheckRememberMe() throws Exception
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

	}

}
