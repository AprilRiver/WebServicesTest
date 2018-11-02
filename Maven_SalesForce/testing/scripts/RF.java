package scripts;

import org.openqa.selenium.WebElement;

public class RF extends RM{

	/*
	 * Resuable flow :
	 * opens firefox -> open salesforce login page -> gives input for user name and password 
	 * input sources-->
	 * user name , password : test case's data file
	 * web elements : object repository file
	 * browser configurations : config file
	 * */
	public static void enterUserNamePassword() throws Exception
	{
		RM.openBrowser("chrome");

		// reads salesforce URL from test case's data file 
		// passing web page name
		RM.openWebPage("salesforce");

		//click user name -> userName: test case's data file
		WebElement userName = driver.findElement(getLocator("salesforce.login.textBox.userName"));
		sendText(userName,"user name ",dataProp.getProperty("userName"));

		//click password -> password: test case's data file
		WebElement password = driver.findElement(getLocator("salesforce.login.textBox.password"));
		sendText(password,"password",dataProp.getProperty("password"));	
	}
}
