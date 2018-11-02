
//imports
var winston = require ('winston');
var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');
const logger = winston.createLogger({
	transports: [
		new winston.transports.Console()
		]
});

//object 
var AccountPage = function()
{
	// elements 
	
	var title = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[1]/strong[1]/span[1]"));
	var balance = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/strong[2]"));
	var currency = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/strong[3]"));
	
	var transactionsButton = element(by.buttonText("Transactions"));
	var deposit = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[3]/button[2]"));
	var withdrawl = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[3]/button[3]"));
	
	var logout = element(by.partialButtonText("Logout"));
	
	var amountTextBox = element(by.xpath("//input[@placeholder='amount']"));

	var depositButton =  element(by.xpath("//button[@type='submit']"));
	var withdrawButton = element(by.xpath("//button[@type='submit']"));
	
	var withDrawErrorMessage = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[4]/div[1]/span[1]")); 
	
	//functions
	
	this.verify_user_homePage = function()
	{
		expect(title.getText()).toBe(data.HarryPotterHomePageTitle)
		logger.info('Account Page :: Harry Potter Home Page is openend');
	}
	this.selectAccount = function(indexToBeSelected)
	{
		element.all(by.options("account for account in Accounts")).then(function(accounts) {
			accounts[indexToBeSelected].click();
			logger.info('Account Page :: Desired account is selected');
			browser.sleep(4000);

		});
	}

	this.verifyCurrency = function(expectedCurrency)
	{
		expect(currency.getText()).toBe(expectedCurrency);
		logger.info('Account Page :: Currency is verified');
	}

	this.clickTransactions = function()
	{
		transactionsButton.click();
	}

	this.depositMoney = function(depositAmount)
	{
		deposit.click();
		amountTextBox.sendKeys(depositAmount);
		browser.sleep(1000);
		depositButton.click();
		logger.info('Account Page :: Money deposited');
		browser.sleep(2000);	
	}

	this.verifyBalance = function(expectedBalance)
	{
		expect(balance.getText()).toBe(expectedBalance);
		logger.info('Account Page :: Balance is verified');
	}
	
	this.withdrawAmount = function(withdrawlAmount)
	{
		withdrawl.click();
		amountTextBox.sendKeys(withdrawlAmount);
		browser.sleep(1000);
		withdrawButton.click();
	}
	
	this.verifyWinthdrawErrorMessage = function (expectedMessage)
	{
		expect(withDrawErrorMessage.getText()).toBe(expectedMessage);
	}
	
	this.verifyWinthdrawSuccessMessage = function (expectedMessage)
	{
		expect(withDrawErrorMessage.getText()).toBe(expectedMessage);
	}
	
	//logout
	this.logout = function()
	{
		logout.click();
	}

}

module.exports= new AccountPage();