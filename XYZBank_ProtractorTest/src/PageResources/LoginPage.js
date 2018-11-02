var winston = require ('winston');
const logger = winston.createLogger({
	transports: [
		new winston.transports.Console()
		]
});

var LoginPage = function()
{
	//elements
	var customer_login_button = element(by.partialButtonText("Customer "));
	var bank_manager_login_button = element(by.partialButtonText("Bank Manager "));

	
	//functions

	//verify customer login is present
	this.verify_customer_login_isPresent = function()
	{
		expect(customer_login_button.getText()).toBe('Customer Login');
		logger.info('Login Page :: Customer Login present');
	}

	//verify manager login is present

	this.verify_bank_manager_login_isPresent = function()
	{
		expect(bank_manager_login_button.getText()).toBe('Bank Manager Login');
		logger.info('Login Page :: Bank Manager Login present');
	}

	//login as customer
	this.click_customer_login_button = function()
	{
		customer_login_button.click();
		browser.sleep("1000");
		//return require('./CustomerPage.js');
		logger.info('Login Page :: Clicked bank_manager_login_button ');

	}

	//login as manger
	this.click_bank_manager_login_button = function()
	{
		bank_manager_login_button.click();
		browser.sleep("1000");
		//return require('./CustomerPage.js');
		logger.info('Login Page :: Clicked Bank Manager Login');

	}

	
}

module.exports= new LoginPage();
