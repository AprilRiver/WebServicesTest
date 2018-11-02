package java.xeroAppTesting;

import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ReusableMethods extends SetUps {
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

	public static void verifyElementIsNOTPresent(WebElement webElement, String webElementName) throws IOException
	{
		if (webElement.isDisplayed())
		{	
			logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+" is  present", ExtentColor.RED));
			System.out.println("found : "+webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
		else
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( webElementName+" is NOT present", ExtentColor.GREEN));
			System.out.println("Cant identify : " +webElementName);
		}
	}

	public static void verifyItemInDropDown(WebElement menuWebElement, String menuWebElementName , String itemToBeFound) throws IOException
	{
		if (menuWebElement.isDisplayed())
		{		
			//VERIFY menu drop down is found
			//logger.log(Status.PASS, MarkupHelper.createLabel( menuWebElementName+" is present", ExtentColor.GREEN));
			System.out.println("found : "+menuWebElementName);

			//loading menu element into select
			Select select = new Select (menuWebElement);

			//getting all options in drop down
			List <WebElement> menuOptions =select.getOptions();

			//iterating through to find the desired item in drop down
			Iterator <WebElement> i = menuOptions.iterator();
			int count = 0;
			while (i.hasNext()) 
			{	
				if (itemToBeFound.equals(i.next().getText())) 
				{
					count++;
					logger.log(Status.PASS, MarkupHelper.createLabel( itemToBeFound+" is found in the drop down", ExtentColor.GREEN));
					System.out.println(itemToBeFound+ " found in drop down ");
					break;
				}
			}
			if (count == 0)
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( itemToBeFound+" is NOT found in the drop down", ExtentColor.RED));
				System.out.println(itemToBeFound+ " NOT found in drop down ");

				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
			}

		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( menuWebElement+" is  NOT present", ExtentColor.RED));
			System.out.println("Cant identify : " +menuWebElement);

			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}


	public static void  sendText (WebElement webElement, String webElementName , String text) throws IOException
	{
		if (webElement.isDisplayed())
		{		
			webElement.clear();
			webElement.click();
			webElement.sendKeys(text);
			logger.log(Status.PASS, MarkupHelper.createLabel(text+" entered in " +webElementName, ExtentColor.GREEN));
			System.out.println("entered text in  : "+webElementName);

		
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(webElementName+" Text box not found ", ExtentColor.RED));
			System.out.println("Cant identify : " +webElementName);

			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}

	public static void  clickElement (WebElement element, String webElementName) throws IOException
	{
		if (element.isDisplayed())
		{
			element.click();
			logger.log(Status.PASS, MarkupHelper.createLabel( webElementName+" is clicked", ExtentColor.GREEN));
			System.out.println("clicked :  "+webElementName);
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+" is not found", ExtentColor.RED));
			System.out.println("Cant identify  : " +webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}

	public static void  verifyText (WebElement element , String webElementName, String expectedText) throws IOException
	{
		if (element.isDisplayed())
		{	
			if (element.getText().equals(expectedText))
			{
				logger.log(Status.PASS, MarkupHelper.createLabel( webElementName+" is displayed as expected" +" = "+element.getText(), ExtentColor.GREEN));
				System.out.println("expected text in :  "+webElementName +" = "+element.getText());
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+"is NOT as expected"+" = "+element.getText(), ExtentColor.RED));
				System.out.println("text not as expected in :  "+webElementName+" = "+element.getText());
				
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
			}		
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+" not found", ExtentColor.RED));
			System.out.println("Cant identify : " +webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
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

	public static  void  verifylistHas (List list1, String expectedName) throws IOException
	{
		// copy all elements as strings in array list
		List list = new ArrayList();
		for (int i = 0 ; i <list1.size() ; i++)
		{
			String ele = ((WebElement) list1.get(i)).getText();
			list.add(ele);
		}
		if (list.contains(expectedName))
			logger.log(Status.PASS, MarkupHelper.createLabel( expectedName+" is  found", ExtentColor.GREEN));
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( expectedName+" is not found", ExtentColor.RED));
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}


	public static void  check_checkBoxesAt (String HTMLclassName, String checkBoxText , int index)
	{
		//getting list of check boxes with passed HTML class name
		List<WebElement> accounts = driver.findElements(By.name(HTMLclassName));
		logger.log(Status.PASS, MarkupHelper.createLabel("check box list  is displayed", ExtentColor.GREEN));

		// iterate through the list of check boxes
		for (int i = 0 ; i < accounts.size() ; i++)
		{
			// matches our index
			if (i == index)
			{
				// if already selected -> print checked
				boolean selected = accounts.get(i).isSelected() ;
				if (selected)
				{
					logger.log(Status.PASS, MarkupHelper.createLabel(checkBoxText+"is checked", ExtentColor.GREEN));
				}
				//else check it
				else
				{
					// check item at given index 
					if (i == index)
					{
						accounts.get(i).click();
						logger.log(Status.PASS, MarkupHelper.createLabel(checkBoxText+"is checked", ExtentColor.GREEN));
					}

				}
			}
		}
	}
	public static void  selectItemInDropDownAt (WebElement webElement, String webElementName , int index) throws IOException
	{
		if (webElement.isDisplayed())
		{	
			logger.log(Status.PASS, MarkupHelper.createLabel(webElementName+" is displayed", ExtentColor.GREEN));
			Select select = new Select(webElement);
			select.selectByIndex(index);
			logger.log(Status.PASS, MarkupHelper.createLabel("Element at "+index  +" selected " +webElementName, ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(webElementName+"not found ", ExtentColor.RED));
			System.out.println("Cant identify : " +webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}

	public static void  verifyURL (String expectedURL) throws IOException
	{
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (driver.getCurrentUrl().equals(expectedURL))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel(expectedURL +" page is displayed", ExtentColor.GREEN));
		}

		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel(expectedURL +" page is NOT displayed", ExtentColor.RED));	
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
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
	public static void  switchToFrame(WebElement frame, String frameName) throws IOException
	{
		if(frame.isDisplayed())
		{
			driver.switchTo().frame(frame);
			logger.log(Status.PASS, MarkupHelper.createLabel( frameName+" found and switched to it", ExtentColor.GREEN));
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( frameName+" not found", ExtentColor.RED));
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
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
	public static void  checkTextAnywhereOnPage( String expectedText , String verifyingWhat) throws InterruptedException, IOException
	{
		Thread.sleep(1000);

		if (driver.getPageSource().contains(expectedText))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( expectedText+ " is present", ExtentColor.GREEN));
			System.out.println(verifyingWhat +" present");
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( expectedText+ " is NOT present", ExtentColor.RED));
			System.out.println(verifyingWhat +" is NOT present");
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
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

	public static void  uploadUsingSendKeys (WebElement element, String fileName ,String address) throws IOException
	{
		if (element.isDisplayed())
		{
			element.sendKeys(address);
			logger.log(Status.PASS, MarkupHelper.createLabel( fileName+ " is uploaded", ExtentColor.GREEN));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( element+" is not found to upload ", ExtentColor.RED));
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}

	public static void selectMenuOption(WebElement webElement, String webElementName , String optionToBeClicked) throws IOException
	{
		if (webElement.isDisplayed())
		{		
			Select select = new Select(webElement);
			select.selectByVisibleText(optionToBeClicked);
			logger.log(Status.PASS, MarkupHelper.createLabel( webElementName+" is selected", ExtentColor.GREEN));
			System.out.println("selected : "+webElementName);
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+" is  NOT displayed", ExtentColor.RED));
			System.out.println("Cant identify : " +webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
		}
	}

	public static void  clickMenuOption (WebElement webElement, String webElementName ) throws IOException
	{
		if (webElement.isDisplayed())
		{		
			webElement.click();
			logger.log(Status.PASS, MarkupHelper.createLabel( webElementName+" is clicked", ExtentColor.GREEN));
			System.out.println("clicked : "+webElementName);
		}
		else
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel( webElementName+" is  NOT displayed", ExtentColor.RED));
			System.out.println("Cant identify : " +webElementName);
			
			String imagePath=takeScreenShot();
			logger.addScreenCaptureFromPath(imagePath);
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

	

	public static void  closeAllWindowsExceptCurrent ()
	{
		// get current window 
		String currentWindow=driver.getWindowHandle();

		// get  all  windows.	
		Set<String> s1=driver.getWindowHandles();		
		Iterator<String> i1=s1.iterator();		
		while(i1.hasNext())			
		{		
			// iterate through all opened windows
			String childWindow=i1.next();	

			// if not current window - close 
			if(!currentWindow.equalsIgnoreCase(childWindow))			
			{  		
				// Switching to Child window
				driver.switchTo().window(childWindow);

				// Closing the Child Window.
				driver.close();		
				logger.log(Status.PASS, MarkupHelper.createLabel("closed child window", ExtentColor.GREEN));

				System.out.println("child window closed");
			}	
		}
		driver.switchTo().window(currentWindow);
		System.out.println("switched to  main window");
	}
	
	

	public static  void  closeBrowser()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		logger.log(Status.PASS, MarkupHelper.createLabel("Firefox closed", ExtentColor.GREEN));
		System.out.println("firefox closed");
	}
}