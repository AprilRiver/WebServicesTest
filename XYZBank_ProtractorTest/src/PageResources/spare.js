var loginPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/LoginPage.js");
var customerPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/CustomerPage.js");
var accountPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/AccountPage.js");
var transactionsPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/TransactionsPage.js");
var managerPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/ManagerPage.js");
var manager_addCustomerPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/Manager_AddCustomerPage.js");
var manager_OpenAccountPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/Manager_OpenAccountPage.js");
var manager_CustomersPage = require("/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/PageResources/Manager_CustomersPage.js");

var data = require('/Users/pattulohith/eclipse-workspace/XYZBank_ProtractorTest/src/DataResources/data.json');

var winston = require ('winston');

const logger = winston.createLogger({
	transports: [
		new winston.transports.Console()
		]
});

describe('SPEC', function() 
		{

	beforeEach(function() 
			{
		browser.get('http://www.way2automation.com/angularjs-protractor/banking/#/login');
		logger.info('launched bank website');

			});


	it ('TC01_loginPage', function() 
			{
		loginPage.verify_customer_login_isPresent();
		loginPage.verify_bank_manager_login_isPresent();

			});



	it ('TC02_verifyCurrencyType', function() 
			{
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();
		accountPage.selectAccount(0);
		accountPage.verifyCurrency(data.expectedCurrency_1004);
		accountPage.selectAccount(1);
		accountPage.verifyCurrency(data.expectedCurrency_1005);
		accountPage.selectAccount(2);
		accountPage.verifyCurrency(data.expectedCurrency_1006);
		accountPage.clickTransactions();    
			});

	it ('TC03_verifyIntialTransaction', function() 
			{
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();

		accountPage.clickTransactions(); 
		transactionsPage.check_intial_emptyTransaction();
		browser.sleep(2000);
			});

	it ('TC04_depositMoney', function() 
			{
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

		//deposit 2000
		accountPage.depositMoney(data.depositAmount);
		accountPage.verifyBalance(data.expectedBalance);
		browser.sleep(2000);         
			});

	it ('TC05_TransactionAfterDeposit', function() 
			{
		//login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

		//deposit 2000
		//accountPage.depositMoney(data.depositAmount);

		//click transactions
		accountPage.clickTransactions(); 
		browser.sleep(2000);  

		// verify amount and transaction type
		transactionsPage.checkAmount(data.depositAmount);
		transactionsPage.checkTransactionType(data.creditTransactionType);
		browser.sleep(2000); 
			});

	it ('TC06_withdrawError', function() 
			{
		//login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

		//withdraw 2001
		accountPage.withdrawAmount(data.Invalid_WithdrawlAmount);

		//verify error message
		accountPage.verifyWinthdrawErrorMessage(data.withdraw_ErrorMessage);
		browser.sleep(2000);


			});

	it ('TC07_withdrawSuccess', function() 
			{

		//login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

		//deposit 2000
		//accountPage.depositMoney(data.depositAmount);

		//withdraw 1000
		accountPage.withdrawAmount(data.valid_WithdrawlAmount);

		//verify success message
		accountPage.verifyWinthdrawSuccessMessage(data.withdraw_SuccessMessage);
		browser.sleep(2000);

		//verify balance is 1000
		accountPage.verifyBalance(data.valid_WithdrawlAmount);
		browser.sleep(2000);


			});


	it ('TC08_TransationAfterWithdraw', function() 
			{
//		login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

//		//deposit 2000
//		accountPage.depositMoney(data.depositAmount);

//		//withdraw 1000
//		accountPage.withdrawAmount(data.valid_WithdrawlAmount);

		//check transaction type and amount
		accountPage.clickTransactions();
		browser.sleep(2000);
		transactionsPage.checkDebitedAmount(data.debitAmount);
		transactionsPage.checkDebitTransactionType(data.debitTransactionType);
		browser.sleep(2000);

			});

	it ('TC09_TransationReset', function() 
			{
//		login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();
		accountPage.verify_user_homePage();

		// select account 1006
		accountPage.selectAccount(2);

		//click transactions
		accountPage.clickTransactions();
		browser.sleep(2000);

		// verify transaction is empty
		transactionsPage.check_intial_emptyTransaction();

			});

	it ('TC10_LoginAsManager', function() 
			{
		loginPage.click_bank_manager_login_button();
		browser.sleep(2000);

		managerPage.verifyManagerOptions();

			});

	it ('TC11_AddCustomer', function() 
			{

		//log in as manager
		loginPage.click_bank_manager_login_button();
		browser.sleep(3000);

		//click on add customer
		managerPage.addCustomer();

		//enter first name , last name and pin
		manager_addCustomerPage.enterCustomerdetails_and_addCustomer();
			});

	it ('TC12_OpenAccountForDollar', function() 
			{

		//log in as manager
		loginPage.click_bank_manager_login_button();

		//click on add customer
		managerPage.openAccount();

		// select customer: harry potter - currency: dollar -> process -> verify alert
		manager_OpenAccountPage.selectCustomer_Currency_Process(data.selectHarryPotter, data.accountForDollar);
		browser.sleep(3000);

			});
	it ('TC13_OpenAccountForPound', function() 
			{

		//log in as manager
		loginPage.click_bank_manager_login_button();

		//click on add customer
		managerPage.openAccount();

		// select customer: harry potter - currency: dollar -> process -> verify alert
		manager_OpenAccountPage.selectCustomer_Currency_Process(data.selectHarryPotter, data.accountForPound);
		browser.sleep(3000);

			});

	it ('TC14_OpenAccountForRupee', function() 
			{

		//log in as manager
		loginPage.click_bank_manager_login_button();

		//click on add customer
		managerPage.openAccount();

		// select customer: harry potter - currency: dollar -> process -> verify alert
		manager_OpenAccountPage.selectCustomer_Currency_Process(data.selectHarryPotter, data.accountForRupee);
		browser.sleep(3000);

			});

	it ('TC15_deleteCustomer', function() 
			{

		//log in as manager
		loginPage.click_bank_manager_login_button();

		//click on customers
		managerPage.customers();

		// serach name 
		manager_CustomersPage.serachForCustomer(data.serach_albus);

		//delete
		manager_CustomersPage.deleteFirstCustomerInTable();
		browser.sleep(3000);

		//verify deletion
		manager_CustomersPage.verifyCustomerDelete(data.serach_albus);

		// verify Page is Same After Deletion
		//manager_CustomersPage.verifyPageisSameAfterDeletion();

			});

	it ('TC16_gotoMainPage_backButton', function() 
			{

//		login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();	

		// select account 1006
		accountPage.selectAccount(2);

		//click transactions
		accountPage.clickTransactions();
		browser.sleep(1000);

		//click back button to go to main page
		transactionsPage.clickBackButton();

		// verify main page
		accountPage.verify_user_homePage();
		browser.sleep(2000);
			});
	it ('TC16_gotoMainPage_backButton', function() 
			{

		//login as harry potter
		loginPage.click_customer_login_button();
		customerPage.selectUser();
		customerPage.clickLogin();	


		// click logout
		accountPage.logout();

		// verify redirect to customer page
		customerPage.verifyPageAsCustomerPage();
		browser.sleep(2000);
			});

		});