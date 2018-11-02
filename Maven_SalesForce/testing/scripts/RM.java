package scripts;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class RM extends SetUp {

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */
	// open web page -> URL : data file of TC
	public static void  openWebPage(String pageName)
	{
		String URL = dataProp.getProperty("URL");
		driver.get(URL);
		logger.log(Status.PASS, MarkupHelper.createLabel("Opened :  "+pageName, ExtentColor.GREEN));
		System.out.println(URL +" openened");
	}


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static void sendText(WebElement webElement , String webElementName , String text)
	{
		if(webElement.isDisplayed())
		{
			webElement.click();
			webElement.clear();
			webElement.sendKeys(text);
			logger.log(Status.PASS, MarkupHelper.createLabel("Enter '" + text+ "' as "+webElementName, ExtentColor.GREEN));
		}
		else
		{
			System.out.println("Not Found : "+webElementName);
			logger.log(Status.FAIL, MarkupHelper.createLabel("Can't locate :  "+webElementName, ExtentColor.RED));
		}
	}



	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static void clickElement(WebElement webElement , String webElementName )
	{
		if(webElement.isDisplayed())
		{
			webElement.click();
			logger.log(Status.PASS, MarkupHelper.createLabel("Clicked : "+webElementName, ExtentColor.GREEN));
			System.out.println("Clicked : "+webElementName);
		}
		else
		{
			System.out.println("Not Found : "+webElementName);
			logger.log(Status.FAIL, MarkupHelper.createLabel("Can't locate :  "+webElementName, ExtentColor.RED));
		}
	}


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static void verifyText(WebElement webElement , String webElementName , String expectedText) throws IOException
	{
		if(webElement.isDisplayed())
		{
			if(webElement.getText().equals(expectedText))
			{
				logger.log(Status.PASS, MarkupHelper.createLabel("As expected : "+webElementName+" = '"+webElement.getText()+" '", ExtentColor.GREEN));
				System.out.println("As expected : "+webElementName+" = '"+webElement.getText()+" '");
			}	
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel("Deviation : '"+webElement.getText()+"' displayed instead of  '" +expectedText+ "'", ExtentColor.RED));
				System.out.println("Deviation : '"+webElement.getText()+"' displayed instead of  '" +expectedText+ "'");
				
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
			}
		}
		else
		{
			System.out.println("Element Not Found : "+webElementName);
			logger.log(Status.FAIL, MarkupHelper.createLabel("Can't locate :  "+webElementName, ExtentColor.RED));
		}
	}


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static void closeBrowser ()
	{
		driver.close();
		logger.log(Status.PASS, MarkupHelper.createLabel("Closed browser. ", ExtentColor.GREEN));
		System.out.println("Closed browser. ");

	}

}
