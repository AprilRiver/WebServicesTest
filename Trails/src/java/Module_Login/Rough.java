package java.Module_Login;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



import java.xeroAppTesting.ReusableFlows;
import java.xeroAppTesting.ReusableMethods;
import java.xeroAppTesting.SetUps;

public class Rough extends ReusableFlows {
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
	public static void TC07_verifyPrivacyPolicyLink() throws Exception
	{
		//create test case report tab
		com.aventstack.extentreports.ExtentTest logger = createTestScriptReport("TC07");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC06_data.properties");

		//open browser
		openBrowser("chrome");

		//open sigup page
		openWebPage(dataProp.getProperty("URL"));

		String currentWindow=driver.getWindowHandle();

		//click terms of use link
		WebElement termsOfUseLink = driver.findElement(getLocator("SignUp.termsOfUse_link"));
		clickElement(termsOfUseLink, "terms of use");

		// go to newly openend window
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		System.out.println("switched to new tab");
		
		//click  privacy link
		driver.findElement(By.linkText("www.xero.com/privacy/ ")).click();
		
		//verify privacy policy page is disaplyed
		checkTextAnywhereOnPage("Privacy notice" , "privacy policy page");			
		
	}
}
