

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
	
});