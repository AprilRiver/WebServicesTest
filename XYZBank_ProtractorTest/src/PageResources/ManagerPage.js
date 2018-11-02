var ManagerPage = function()
{
	
	var addCustomer = element(by.partialButtonText("Add Customer"));
	var openAccount = element(by.partialButtonText("Open Account"));
	var customers = element(by.partialButtonText("Customers"));
	
	var addCustomerButton = element(by.partialButtonText("Add Customer"));
	// verify manager options - add customer , open account and customer
	this.verifyManagerOptions = function()
	{
		expect(addCustomer.getText()).toBe("Add Customer");
		expect(openAccount.getText()).toBe("Open Account");
		expect(customers.getText()).toBe("Customer");
	}

	// add a new customer
	this.addCustomer = function()
	{
		addCustomer.click();
		browser.sleep(3000);

	}

	this.openAccount = function()
	{
		openAccount.click();
	}
	
	this.customers = function()
	{
		customers.click();
	}
}

module.exports = new ManagerPage();