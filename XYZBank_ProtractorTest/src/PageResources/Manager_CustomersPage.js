var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');

var Manager_CustomersPage = function()
{
	var searchCustomers_textBox = element(by.xpath("//input[@placeholder='Search Customer']"));
	var firstDelete = element(by.partialButtonText("Delete"));
	//var intialPageURL = browser.getCurrentUrl();


	//serach for customer
	this.serachForCustomer= function(customerName)
	{
		searchCustomers_textBox.sendKeys(customerName);
		browser.sleep(2000);
	}

	this.deleteFirstCustomerInTable = function()
	{
		firstDelete.click();
	}

	this.verifyCustomerDelete = function(customerName)
	{
		expect(browser.getPageSource()).not.toContain(customerName);
	}

//	this.verifyPageisSameAfterDeletion()
//	{
//		expect(intialPageURL).toBe(browser.getCurrentUrl);
//	}
}
module.exports =new Manager_CustomersPage();