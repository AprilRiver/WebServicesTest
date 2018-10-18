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

public class RM {

	static ExtentHtmlReporter htmlReporter = null;
	static ExtentReports extent = null ;
	static WebDriver driver = null;
	static ExtentTest logger = null ;
	static Properties configProp = null;
	static Properties dataProp = null ;
	static Properties OR_Prop = null ;

	public static void initializeExtentReport()
	{
		htmlReporter =  new ExtentHtmlReporter("./Reports/exeReport.html");
		extent= new ExtentReports ();	
		System.out.println("Created Report");
	}

	public static  ExtentTest createTestScriptReport(String testCaseName) 
	{
		// create a test case  tab and attach to report
		extent.attachReporter(htmlReporter);
		logger = extent.createTest(testCaseName);
		System.out.println("Created test tab");
		return logger ;
	}


	public static Properties getLoadedPropertiesObject(String FilePath)
	{
		Properties propertiesObject;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(FilePath));
			propertiesObject = new Properties();
			try {
				propertiesObject.load(reader);
				reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found at " + FilePath);
		}
		return propertiesObject;
	}

	//load object with data file contents
	public static void readDataFile(String dataFilePath)
	{
		dataProp = getLoadedPropertiesObject(dataFilePath);
	}

	//load object with configuration file contents
	public static void readConfigFile(String configFilePath)
	{
		configProp = getLoadedPropertiesObject(configFilePath);
	}

	//load object with Object Repository file contents
	public static void readORFile(String ObjRepoFilePath)
	{
		OR_Prop = getLoadedPropertiesObject(ObjRepoFilePath);
	}

	// get locator from object repository file
	public static By getLocator(String elementKey) throws Exception {
		String locator = OR_Prop.getProperty(elementKey);

		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		if(locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if(locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if(locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if(locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Unknown locator type '" + locatorType + "'");
	}

	// gets driver path -> from config.properties
	public static String getDriverPath(String webBrowserName)
	{

		String propertyFilePath= "./testing/Repository/config.properties";
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			configProp = new Properties();
			try {
				configProp.load(reader);
				reader.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}	

		if (webBrowserName.equalsIgnoreCase("firefox"))
		{
			String geckoPath = configProp.getProperty("firefox_driver_path");
			if(geckoPath!= null) 
				return geckoPath;
			else 
			{
				//throw new RuntimeException("firefox driverPath is not specified in the Configuration.properties file.");
				return "cant find driver ";
			}
		}

		else if (webBrowserName.equalsIgnoreCase("chrome"))
		{
			System.out.println("chrome pending");
			return "cant find driver ";

		}
		else if (webBrowserName.equalsIgnoreCase("IE"))
		{
			System.out.println("IE pending");
			return "cant find driver ";
		}
		else return "cant find driver ";
	}

	// open browser
	public static void  openBrowser(String webBrowserName) 
	{	
		if (webBrowserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",getDriverPath(webBrowserName));
			System.out.println(getDriverPath(webBrowserName));
			driver=new FirefoxDriver();

			//get implicit wait from config file
			int waitTime = Integer.parseInt(configProp.getProperty("implicit_wait_time"));

			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			logger.log(Status.PASS, MarkupHelper.createLabel("Opened : Firefox", ExtentColor.GREEN));
			System.out.println("Opened : Firefox");
		}
		else if (webBrowserName.equalsIgnoreCase("chrome"))
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("chrome NOT ready", ExtentColor.RED));
			System.out.println("CHROME not ready yet ");
		}
		else if (webBrowserName.equalsIgnoreCase("IE"))
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("IE NOT ready", ExtentColor.RED));
			System.out.println("IE not ready yet ");
		}

	}

	// open web page -> URL : data file of TC
	public static void  openWebPage(String pageName)
	{
		String URL = dataProp.getProperty("URL");
		driver.get(URL);
		logger.log(Status.PASS, MarkupHelper.createLabel("Opened :  "+pageName, ExtentColor.GREEN));
		System.out.println(URL +" openened");
	}
	/*Name :
	 *
	 * *
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
	 * 
	 * 
	 * */
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
	 * 
	 * */
	/*Name :
	 *
	 * *
	 */
	public static void verifyText(WebElement webElement , String webElementName , String expectedText)
	{
		if(webElement.isDisplayed())
		{
			if(webElement.getText().equals(expectedText))
			{
				logger.log(Status.PASS, MarkupHelper.createLabel("As expected : "+webElementName+" = '"+webElement.getText()+" '", ExtentColor.GREEN));
				System.out.println("Deviation : '"+webElement.getText()+"' displayed instead of  '" +expectedText+ "'");
			}	
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel("Deviation : '"+webElement.getText()+"' displayed instead of  '" +expectedText+ "'", ExtentColor.RED));
				System.out.println("Deviation : '"+webElement.getText()+"' displayed instead of  '" +expectedText+ "'");
			}
		}
		else
		{
			System.out.println("Element Not Found : "+webElementName);
			logger.log(Status.FAIL, MarkupHelper.createLabel("Can't locate :  "+webElementName, ExtentColor.RED));
		}
	}


	public static void closeBrowser ()
	{
		driver.close();
		logger.log(Status.PASS, MarkupHelper.createLabel("Closed browser. ", ExtentColor.GREEN));
		System.out.println("Closed browser. ");

	}

}
