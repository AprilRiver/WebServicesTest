package java.Module_Login;

import org.junit.Before;
import org.openqa.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import java.xeroAppTesting.ReusableFlows;
import java.xeroAppTesting.ReusableMethods;
import java.xeroAppTesting.SetUps;

public class LoginPageTestScripts extends ReusableFlows {

	@BeforeSuite
	public static void beforeSuite()
	{
		initializeExtentReport();
		readORFile("./src/test/java/repository/objRep.properties");
		readConfigFile("./src/test/java/repository/config.properties");
	}
	@AfterMethod
	public static void afterEachMethod()
	{
		extent.flush();
	}

	@Test
	public static void TC01_validLogin() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC01");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC01_data.properties");

		// resuable flow
		enterEmailPasswordLogin();

		//User's Home Page should be displayed
		String welcomeText = dataProp.getProperty("welcomeText");
		checkTextAnywhereOnPage(welcomeText ,"Home Page");

		closeBrowser();

	}

	@Test
	public static void TC02_incorrectPassword() throws Exception
	{
		//create test case report tab
		com.aventstack.extentreports.ExtentTest logger = createTestScriptReport("TC02");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC02_data.properties");

		// resuable flow -> invalid password
		enterEmailPasswordLogin();

		Thread.sleep(3000);
		// Error message should be displayed
		String expectedErrorMessage = dataProp.getProperty("expectedErrorMessage");
		checkTextAnywhereOnPage(expectedErrorMessage, "expected Error Message");
		closeBrowser();
	}
	@Test
	public static void TC03_IncorrectUserName() throws Exception
	{
		//create test case report tab
		com.aventstack.extentreports.ExtentTest logger = createTestScriptReport("TC03");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC03_data.properties");

		// resuable flow -> invalid password
		enterEmailPasswordLogin();

		Thread.sleep(3000);
		// Error message should be displayed
		String expectedErrorMessage = dataProp.getProperty("expectedErrorMessage");
		checkTextAnywhereOnPage(expectedErrorMessage , "Error message");
		closeBrowser();
	}
	@Test
	public static void TC04_ForgotPassword() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC04");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC04_data.properties");

		//open login page 
		openxero("firefox");

		//click forgot password
		WebElement forgot_password = driver.findElement(getLocator("login.forgotpasswordLink"));
		clickElement(forgot_password, "Forgot password link");


		//enter reset  email
		WebElement email = driver.findElement(getLocator("login.forgotPassword.resetEmail"));
		sendText(email, "Email", dataProp.getProperty("email"));

		//click send link button
		WebElement sendLinkButton = driver.findElement(getLocator("login.forgotPAssword.sendLinkButton"));
		clickElement(sendLinkButton, "Send link button");

		Thread.sleep(3000);

		// Error message should be displayed
		String expectedErrorMessage = dataProp.getProperty("expectedMessage");
		checkTextAnywhereOnPage(expectedErrorMessage ,"Error message");
		checkTextAnywhereOnPage(dataProp.getProperty("email") , "email");

		closeBrowser();
	}

}
