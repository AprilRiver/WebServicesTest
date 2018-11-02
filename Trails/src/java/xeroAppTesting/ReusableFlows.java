package java.xeroAppTesting;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReusableFlows extends ReusableMethods {


	public static void openxero(String browser)
	{
		openBrowser(browser);
		driver.get(dataProp.getProperty("URL"));
	}
	public static void enterEmailPasswordLogin() throws Exception
	{
		openxero("firefox");

		//get data input of login and password
		String email_input = dataProp.getProperty("email");
		String password_input = dataProp.getProperty("passowrd");

		//enter email
		WebElement email = driver.findElement(getLocator("login.email"));
		sendText(email, "email", email_input);

		//enter password
		WebElement password = driver.findElement(getLocator("login.password"));
		sendText(password, "password", dataProp.getProperty("password"));

		//click login
		WebElement login = driver.findElement(getLocator("login.loginButton"));
		login.click();

	}

	public static void addOrganisation() throws Exception
	{
		// reusable flow
		enterEmailPasswordLogin();

		//Click on my Xero from the top left menu
		WebElement menu = driver.findElement(getLocator("HomePage.TopLeftMenu"));
		clickElement(menu, "Menu");
		
		//click my xero
		WebElement MyXero = driver.findElement(getLocator("HomePage.TopLeftMenu.MyXero"));
		clickElement(MyXero, "My Xero");

		//click add organisation button
		WebElement addOrganisation = driver.findElement(getLocator("MyXero.AddOrganisationButton"));
		clickElement(addOrganisation, "Add Organisation button");

		Thread.sleep(3000);
		//enter org name
		WebElement orgName = driver.findElement(getLocator("AddOrganisationPage.orgName"));
		sendText(orgName, "organisation name", dataProp.getProperty("OrganisationName"));

		//enter org work
		WebElement orgWork = driver.findElement(getLocator("AddOrganisationPage.orgWork"));
		sendText(orgWork, "organisation Work", dataProp.getProperty("OrganisationDo"));

		//country
		WebElement country = driver.findElement(getLocator("AddOrganisationPage.country"));
		country.click();

		Thread.sleep(2000);
		//select country
		WebElement US = driver.findElement(getLocator("AddOrganisationPage.country.us"));
		US.click();

		//timezone
		WebElement timeZone = driver.findElement(getLocator("AddOrganisationPage.timeZone"));
		clickElement(timeZone, "TIME ZONE");

		timeZone.sendKeys("Pac");
		Thread.sleep(2000);
		timeZone.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
	}

	public static void giveBillingAddress() throws Exception
	{
		//select continue to billing plan
		WebElement continueToBillingButton = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.continueToBillingButton"));
		clickElement(continueToBillingButton, "Continue To Billing Button");

		//check prefilled - Person to bill
		WebElement Person_to_bill = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.PersonToBill"));
		verifyText(Person_to_bill, "Person to bill", dataProp.getProperty("OrganisationName"));

		//check prefilled - email
		WebElement email = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.email"));
		verifyText(email, "email", dataProp.getProperty("email"));

		//check prefilled - phone
		WebElement phone = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.phone"));
		verifyText(phone, "Phone number", "6504769864");

		// fill street
		WebElement street = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.street"));
		sendText(street, "street", dataProp.getProperty("street"));

		//fill city
		WebElement city = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.city"));
		sendText(city, "city", dataProp.getProperty("city"));

		//fill state
		//		WebElement state = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.state"));
		//		sendText(state, "state", dataProp.getProperty("state"));

		//fill zip
		WebElement zip = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.zip"));
		sendText(zip, "zip", dataProp.getProperty("zip"));

		//click Continue to Review & Pay
		WebElement Continue_to_Review_Pay_button = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.payButton"));
		clickElement(Continue_to_Review_Pay_button, "Continue to Review & Pay");

	}
	
	public static void giveCreditCardDetails() throws Exception
	{
//		//enter credit card details
//		WebElement cardNumber = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.Payment.cardNumber")); 
//		sendText(cardNumber, "card number", dataProp.getProperty("cardNumber"));
//
//		WebElement expiry = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.Payment.expiry")); 
//		sendText(expiry, "expiry",  dataProp.getProperty("expiry"));
//
//		WebElement CVV = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.Payment.CVV")); 
//		sendText(CVV, "CVV",  dataProp.getProperty("CVV"));
//
//		WebElement NameOnCard = driver.findElement(getLocator("AddOrganisationPage.BuyNow.PaymentOptionsPage.Billing.Payment.NameOnCard")); 
//		sendText(NameOnCard, "NameOnCard", "NameOnCard");
	}

	public static void verifyBillingAddress() throws InterruptedException, IOException
	{
		checkTextAnywhereOnPage(dataProp.getProperty("street"), "Entered street name");
		checkTextAnywhereOnPage(dataProp.getProperty("city"), "Entered city name");
		checkTextAnywhereOnPage(dataProp.getProperty("zip"), "Entered zip name");
	}
}