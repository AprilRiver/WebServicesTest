package scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SetUp {

	public static ExtentHtmlReporter htmlReporter = null;
	public static ExtentReports extent = null ;
	public static WebDriver driver = null;
	public static ExtentTest logger = null ;
	public static Properties configProp = null;
	public static Properties dataProp = null ;
	public static Properties OR_Prop = null ;

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */


	public static void initializeExtentReport()
	{

		//String fileName = "./Reports/"+"Run_"+new SimpleDateFormat("dd-MM-yyyy@HH:mm:ss").format(new Date())+".html";
		String fileName ="./Reports/exe.html" ;
		htmlReporter =  new ExtentHtmlReporter(fileName);
		extent= new ExtentReports ();	
		System.out.println("Created Report: " +fileName);
	}


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */


	public static  ExtentTest createTestScriptReport(String testCaseName) 
	{
		// create a test case  tab and attach to report
		extent.attachReporter(htmlReporter);
		logger = extent.createTest(testCaseName);
		System.out.println("Created test tab");
		return logger ;
	}

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */// gets driver path -> from config.properties




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

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	//load object with data file contents
	public static void readDataFile(String dataFilePath)
	{
		dataProp = getLoadedPropertiesObject(dataFilePath);
	}

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	//load object with configuration file contents
	public static void readConfigFile(String configFilePath)
	{
		configProp = getLoadedPropertiesObject(configFilePath);
	}


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */
	//load object with Object Repository file contents
	public static void readORFile(String ObjRepoFilePath)
	{
		OR_Prop = getLoadedPropertiesObject(ObjRepoFilePath);
		System.out.println(ObjRepoFilePath);
	}

	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	// get locator from object repository file
	public static By getLocator(String elementKey) throws Exception {
		String locator = OR_Prop.getProperty(elementKey);
		System.out.println(elementKey+"      "+locator);
		
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


	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */

	public static String[][] readSheet(String dt_Path, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_Path);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);


		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);


		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();

		String[][] xlData = new String[iRowCount][iColCount];

		for(int i=0; i<iRowCount; i++){
			for(int j = 0; j<iColCount; j++){

				xlData[i][j]= sheet.getRow(i).getCell(j).getStringCellValue();
			}

		}

		return xlData;
	}



	/* 
	 * Name of the Method: enterText
	 * Brief Description: Enters text to text field
	 * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
	 * Created By: Automation team
	 * Creation Date: Oct 09 2018
	 * Last Modified: Oct 09 2018 
	 */
	public static void  openBrowser(String webBrowserName) 
	{	
		if (webBrowserName.equalsIgnoreCase("firefox"))
		{		 
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefox_driver_path"));
			System.out.println(configProp.getProperty("firefox_driver_path"));
			driver=new FirefoxDriver();
			//get implicit wait from config file
			int waitTime = Integer.parseInt(configProp.getProperty("implicit_wait_time"));

			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			logger.log(Status.PASS, MarkupHelper.createLabel("Opened : Firefox", ExtentColor.GREEN));
			System.out.println("Opened : Firefox");
		}
		else if (webBrowserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chrome_driver_path"));
			System.out.println(configProp.getProperty("chrome_driver_path"));
			driver=new ChromeDriver();
			//get implicit wait from config file
			int waitTime = Integer.parseInt(configProp.getProperty("implicit_wait_time"));

			driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
			logger.log(Status.PASS, MarkupHelper.createLabel("Opened : Chrome", ExtentColor.GREEN));
			System.out.println("Opened : chrome");
		}
		else if (webBrowserName.equalsIgnoreCase("IE"))
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("IE NOT ready", ExtentColor.RED));
			System.out.println("IE not ready yet ");
		}
	}


	public static String takeScreenShot() throws IOException{
		
		String reportPath=new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(new Date());
		String curDir=System.getProperty("user.dir");
		String destination=curDir+"/Reports/"+reportPath+"image.PNG";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination),true);
		return destination;
	}
}
