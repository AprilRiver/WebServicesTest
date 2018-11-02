var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');

var Manager_OpenAccountPage = function()
{

	var customer_dropdown = element(by.xpath("//select[@id='userSelect']"));
	var currency_dropdown = element(by.xpath("//select[@id='currency']"));
	var process_button = element(by.partialButtonText("Process"));


	this.selectCustomer_Currency_Process = function(customerToBeSelected , currencyToBeSelected)
	{
		//select customer
		customer_dropdown.click();
		var customerList = element.all(by.tagName('option')).then(function(customerList)
				{ 
			customerList[customerToBeSelected].click();  	
				});

		//select currency
		currency_dropdown.sendKeys(currencyToBeSelected);
		
		//click process button
		process_button.click();
		browser.sleep(1000);
		
		// verify alert
		expect(browser.switchTo().alert().getText()).toContain(data.alert_openAccount);
		browser.sleep(1000);
		browser.switchTo().alert().accept();

	}

}

module.exports = new Manager_OpenAccountPage();