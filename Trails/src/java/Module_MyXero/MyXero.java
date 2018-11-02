package java.Module_MyXero;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import java.xeroAppTesting.ReusableFlows;
import java.xeroAppTesting.ReusableMethods;
import java.xeroAppTesting.SetUps;

public class MyXero extends ReusableFlows {


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
	public static void TC13_AddOrganisation() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC13");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC13_data.properties");

		//reusable flow : login -> my xero -> add organisation -> fill details
		addOrganisation();

		//click start trail
		WebElement startTrailButton = driver.findElement(getLocator("AddOrganisationPage.startTrailButton"));
		startTrailButton.click();

		Thread.sleep(2000);
		// success message
		checkTextAnywhereOnPage(dataProp.getProperty("successMessage"), "Ready to go success message");

		//check organisation added
		System.out.println(dataProp.getProperty("OrganisationName"));
		checkTextAnywhereOnPage(dataProp.getProperty("OrganisationName"), "Organisation added");
		closeBrowser();

	}

	@Test
	public static void TC14_PaidVersion() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC14");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC14_data.properties");

		//reusable flow : login -> my xero -> add organisation -> fill details
		addOrganisation();

		//click buy now
		WebElement buyNow = driver.findElement(getLocator("AddOrganisationPage.buyNow_button"));
		clickElement(buyNow, "Buy Now button");

		Thread.sleep(6000);
		//Should be able to navigate to "Purchase Plan" Subscription and Billing page		
		checkTextAnywhereOnPage("Select a plan", "Purchase Plan , Subscription , Billing Page");

		closeBrowser();
	}

	@Test
	public static void TC15_PayForStarterVersion() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC15");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC15_data.properties");

		//reusable flow : login -> my xero -> add organisation -> fill details
		addOrganisation();

		//click buy now
		WebElement buyNow = driver.findElement(getLocator("AddOrganisationPage.buyNow_button"));
		clickElement(buyNow, "Buy Now button");
		Thread.sleep(6000);

		//select starter plan
		WebElement starterPlan = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.starterPlan"));
		clickElement(starterPlan, "Starter Plan chcek box");

		// Reusable flow: click continue button -> fill billing details -> click go to review and payment -> navigate to review and payment page
		giveBillingAddress();

		// Reusable flow: verifies entered address is reflected or not
		verifyBillingAddress();

		// Reusable flow
		//giveCreditCardDetails();
		closeBrowser();

	}
	
	@Test
	public static void TC16_PayForStandardVersion() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC16");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC16_data.properties");

		//reusable flow : login -> my xero -> add organisation -> fill details
		addOrganisation();

		//click buy now
		WebElement buyNow = driver.findElement(getLocator("AddOrganisationPage.buyNow_button"));
		clickElement(buyNow, "Buy Now button");
		Thread.sleep(6000);

		//select starter plan
		WebElement standardPlan = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.standardPlan"));
		clickElement(standardPlan, "standardPlan  chcek box");

		// Reusable flow: click continue button -> fill billing details -> click go to review and payment -> navigate to review and payment page
		giveBillingAddress();

		// Reusable flow: verifies entered address is reflected or not
		verifyBillingAddress();

		// Reusable flow
		//giveCreditCardDetails();
		closeBrowser();

	}
	
	@Test
	public static void TC17_PayForPremiumVersion() throws Exception
	{
		//create test case report tab
		ExtentTest logger = createTestScriptReport("TC17");

		//give data file path
		readDataFile("./src/test/java/dataFiles/TC17_data.properties");

		//reusable flow : login -> my xero -> add organisation -> fill details
		addOrganisation();

		//click buy now
		WebElement buyNow = driver.findElement(getLocator("AddOrganisationPage.buyNow_button"));
		clickElement(buyNow, "Buy Now button");
		Thread.sleep(6000);

		//select starter plan
		WebElement PremiumPlan = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.PremiumPlan"));
		clickElement(PremiumPlan, "PremiumPlan chcek box");

		// Reusable flow: click continue button -> fill billing details -> click go to review and payment -> navigate to review and payment page
		giveBillingAddress();

		// Reusable flow: verifies entered address is reflected or not
		verifyBillingAddress();
		
		closeBrowser();

		// Reusable flow
		//giveCreditCardDetails();

	}

	
		@Test
		public static void TC18_QuickBooksVersion() throws Exception
		{
			//create test case report tab
			ExtentTest logger = createTestScriptReport("TC18");
	
			//give data file path
			readDataFile("./src/test/java/dataFiles/TC18_data.properties");
	
			//reusable flow : login -> my xero -> add organisation -> fill details
			addOrganisation();
	
			//click Move from QuickBooks for free. link
			WebElement quickBookLink = driver.findElement(By.linkText("Move from QuickBooks for free."));
			clickElement(quickBookLink, "Move from QuickBooks for free. link");
	
			//5.check the Want to convert your data?
	
			WebElement checkBox = driver.findElement(By.xpath("//input[@id='conversionCheckbox-inputEl']"));
			clickElement(checkBox, "quick books check box");
	
			Thread.sleep(1000);
	
			//click continue
	
			WebElement ContinueButton = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[2]"));
			clickElement(ContinueButton, "continue button");
	
			Thread.sleep(2000);
			//1. Should be able to see Quick books file conversion	
			checkTextAnywhereOnPage(dataProp.getProperty("expectedMessage"), "QuickBooks file conversion");
	
			closeBrowser();
		}
}


