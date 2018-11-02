
//imports
var winston = require ('winston');
var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');
const logger = winston.createLogger({
	transports: [
		new winston.transports.Console()
		]
});

//object 
var TransactionsPage = function()
{
	// elements

	var firstRowAmount = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]"));
	var firstRowTransactionType = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]"));
	
	
	var secondRowAmount = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[2]"));
	var secondRowTransactionType = element(by.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr[2]/td[3]"));
		
	var backButton = element(by.partialButtonText("Back"));

	//functions

	this.check_intial_emptyTransaction = function()
	{
		expect(browser.getPageSource()).not.toContain("creditTransactionType");
	}

	// credit
	this.checkAmount = function (depositAmount)
	{
		expect(firstRowAmount.getText()).toBe(depositAmount);
		logger.info('Transactions Page :: transcation amount verfied');
		console.log("Amount = " +firstRowAmount.getText());
	}
	// credit 

	this.checkTransactionType = function (type)
	{
		expect(firstRowTransactionType.getText()).toBe(type);
		logger.info('Transactions Page :: transcation type verfied');
		console.log("Transaction Type = " +firstRowTransactionType.getText());
	}
	
	//debit
	this.checkDebitedAmount = function(withdrawlAmount)
	{
		expect(secondRowAmount.getText()).toBe(withdrawlAmount);
		logger.info('Transactions Page :: transcation amount verfied');
		console.log("Amount = " +secondRowAmount.getText());
	}
	
	//debit
	this.checkDebitTransactionType = function (type)
	{
		expect(secondRowTransactionType.getText()).toBe(type);
		logger.info('Transactions Page :: transcation type verfied');
		console.log("Transaction Type = " +secondRowTransactionType.getText());
	}
	
	//click back button
	this.clickBackButton = function()
	{
		backButton.click();
	}

}

module.exports= new TransactionsPage();