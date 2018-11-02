var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');

var Manager_AddCustomerPage = function()
{

	var addCustomer = element(by.partialButtonText("Add Customer"));
	var openAccount = element(by.xpath("//div[@class='center']//button[2]"));
	var customer = element(by.xpath("//div[@class='center']//button[3]"));

	var firstName = element(by.xpath("//input[@placeholder='First Name']"));
	var lastName = element(by.xpath("//input[@placeholder='Last Name']"));
	var pinCode = element(by.xpath("//input[@placeholder='Post Code']"));

	var addCustomerButton = element(by.xpath("//button[@type='submit']"));

	// add a new customer
	this.enterCustomerdetails_and_addCustomer = function()
	{
		// enter first , last names and pin code	
		firstName.sendKeys(data.firstName);	
		lastName.sendKeys(data.lastName);	
		pinCode.sendKeys(data.pinCode);

		//click add customer
		addCustomerButton.click();
		browser.sleep(1000);

		//verify alert
		expect(browser.switchTo().alert().getText()).toContain(data.addCustomer_alertText);	
		browser.switchTo().alert().accept();
		browser.sleep(2000);

	}

}

module.exports = new Manager_AddCustomerPage();