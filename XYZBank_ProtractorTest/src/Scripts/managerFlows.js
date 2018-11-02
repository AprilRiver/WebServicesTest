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
	it ('TC17_Logout', function() 
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