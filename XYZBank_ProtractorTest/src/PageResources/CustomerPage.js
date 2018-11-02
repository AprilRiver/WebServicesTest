
//imports
var winston = require ('winston');
var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');
const logger = winston.createLogger({
	transports: [
		new winston.transports.Console()
		]
});

// object 
var CustomerPage = function()
{
	// elements 

	var dropdown = element(by.xpath("//select[@id='userSelect']"));
	var loginButton = element(by.buttonText('Login'));

	// functions
	
	//verify page is customer page
	this.verifyPageAsCustomerPage = function()
	{
		expect(browser.getPageSource()).toContain("Your Name");
	}
	
	//login as harry potter 
	this.selectUser = function()
	{
		dropdown.click();
		browser.sleep("1000");
		var options = element.all(by.tagName('option')).then(function(options)
				{ 
					options[data.selectHarryPotter].click();  	
				});
		//return require('./CustomerPage.js');
		logger.info('Customer Page :: selected harry ');
	}

	// click login
	this.clickLogin = function()
	{
	loginButton.click();
	logger.info('Customer Page :: clicked login');
	}
}

module.exports= new CustomerPage();