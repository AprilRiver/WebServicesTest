package java.Module_HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class HomePage extends ReusableFlows{

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
	public static void TC10_HomePageElements() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC10");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC10_data.properties");

		// reusable flow
		enterEmailPasswordLogin();

		//click Dashboard
		WebElement Dashboard = driver.findElement(getLocator("HomePage.DashBoard"));
		clickElement(Dashboard, "Dashboard");

		//verify Dashboard page
		checkTextAnywhereOnPage("Welcome to Xero" , "Dash board");
		logger.log(Status.PASS, MarkupHelper.createLabel(" DashBoard Page displayed", ExtentColor.GREEN));


		//click Accounts
		WebElement Accounts = driver.findElement(getLocator("HomePage.Accounts"));
		clickElement(Accounts, "Accounts");

		//click Bank Accounts

		WebElement BankAccounts = driver.findElement(getLocator("HomePage.BankAccounts"));
		clickElement(BankAccounts, "Bank Accounts");

		//verify Accounts page
		checkTextAnywhereOnPage("Bank Accounts" , "Accounts page");
		logger.log(Status.PASS, MarkupHelper.createLabel(" Accounts Page displayed", ExtentColor.GREEN));


		//click Reports
		WebElement Reports = driver.findElement(getLocator("HomePage.Reports"));
		clickElement(Reports, "Reports");

		//click All Reports

		WebElement AllReports = driver.findElement(getLocator("HomePage.AllReports"));
		clickElement(AllReports, "All Reports");

		//verify Reports page
		checkTextAnywhereOnPage("Reports" ,"Reports page");
		logger.log(Status.PASS, MarkupHelper.createLabel(" Reports Page displayed", ExtentColor.GREEN));

		//click Contacts
		WebElement Contacts = driver.findElement(getLocator("HomePage.Contacts"));
		clickElement(Contacts, "Contacts");

		//click All Contacts

		WebElement AllContacts = driver.findElement(getLocator("HomePage.AllContacts"));
		clickElement(AllContacts, "All Contacts");

		//verify contacts page
		checkTextAnywhereOnPage("Contacts" ,"contacts page");
		logger.log(Status.PASS, MarkupHelper.createLabel(" Contacts Page displayed", ExtentColor.GREEN));

		//click settings
		WebElement Settings = driver.findElement(getLocator("HomePage.Settings"));
		clickElement(Settings, "Settings");

		//verify settings drop down
		checkTextAnywhereOnPage("General Settings","Settings Menu");

		//click +
		WebElement quicklaunchTab = driver.findElement(getLocator("HomePage.quicklaunchTab"));
		clickElement(quicklaunchTab, "+");

		//verify + drop down
		checkTextAnywhereOnPage("Invoice","+ menu");

		//click files tab
		WebElement files = driver.findElement(getLocator("HomePage.filesTab"));
		clickElement(files, "Files icon");

		//verify files page
		checkTextAnywhereOnPage("Files","Files Page");

		//click notifications tab
		WebElement notifications = driver.findElement(getLocator("HomePage.notificationsTab"));
		clickElement(notifications, "notifications icon");

		//switch to frame
		driver.switchTo().frame("post_office_frame");

		//verify notifications page
		checkTextAnywhereOnPage("Notifications","Notifications Page");

		driver.switchTo().defaultContent();

		//		//click serach tab
		//		WebElement search = driver.findElement(getLocator("HomePage.searchIcon"));
		//		search.click();

		//		Thread.sleep(2000);
		//		//switch to serach frame
		//		driver.switchTo().frame("GlobalSearchApp");
		//		if (driver.findElement(By.id("queryInput")).isDisplayed())
		//		{
		//			logger.log(Status.PASS, MarkupHelper.createLabel( "search field area is shown", ExtentColor.GREEN));
		//			System.out.println("search field area is shown");
		//		}
		//		else
		//		{
		//			logger.log(Status.FAIL, MarkupHelper.createLabel( "search field area NOT is shown", ExtentColor.RED));
		//			System.out.println("search field area is NOT shown");
		//		}

		//click help tab
		WebElement help = driver.findElement(getLocator("HomePage.helpIcon"));
		clickElement(help, "help icon");

		Thread.sleep(2000);
		//switch to serach frame
		checkTextAnywhereOnPage("Help", "help field area ");
		checkTextAnywhereOnPage("Help Center", "Help Center");

		//close
		closeBrowser();
	}

	@Test
	public static void TC11_Logout() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC11");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC11_data.properties");

		// reusable flow
		enterEmailPasswordLogin();

		//click user menu
		WebElement userMenu = driver.findElement(getLocator("HomePage.userMenu"));
		clickElement(userMenu, "User Menu");

		Thread.sleep(2000);
		//click logout
		WebElement logout = driver.findElement(getLocator("HomePage.userMenu.logout"));
		clickElement(logout, "logout");

		//Welcome to xero page is displayed with<username> populated and remember username checked .
		//UserName <userName> should be displayed 

		checkTextAnywhereOnPage("Welcome to Xero", "Welcome to Xero page");

		//check saved - user name filed 	
		WebElement userName = driver.findElement(getLocator("HomePage.savedUserName"));
		verifyText(userName, "Saved user name ", dataProp.getProperty("email"));

		closeBrowser();
	}

	@Test
	public static void TC12_UploadDP() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC12");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC12_data.properties");

		// reusable flow
		enterEmailPasswordLogin();

		//click user menu
		WebElement userMenu = driver.findElement(getLocator("HomePage.userMenu"));
		clickElement(userMenu, "User Menu");


		//click profile
		WebElement profile = driver.findElement(getLocator("HomePage.userMenu.Profile"));
		clickElement(profile, "profile");

		//click upload image button
		WebElement uploadimageButton = driver.findElement(getLocator("ProfilePage.uploadimageButton"));
		//uploadimageButton.click();
		//uploadimageButton.sendKeys(dataProp.getProperty("imageAddress"));

		//click save

		WebElement saveButton = driver.findElement(getLocator("ProfilePage.saveButton"));
		saveButton.click();

		closeBrowser();		
	}

}