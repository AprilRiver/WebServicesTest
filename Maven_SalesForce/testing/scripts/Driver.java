package scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class Driver extends TestScripts{

	
	public static void callTestCasesForExecution() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
	{
		String XLpath = "./testing/Repository/ExecutionList.xls";
		
		//readSheet implementation is in setup.java
		String[][] data = readSheet(XLpath, "Sheet1");

		for (int i = 1 ; i < data.length ; i ++)
		{
			String exeFlag = data[i][1];
			// if flag = Y -> call that test case
			if (exeFlag.equals("Y"))
			{
				String testCase = data[i][0];
				// java reflection class
				Method testScript = TestScripts.class.getMethod(testCase);
				testScript.invoke(testScript);
				// saving the execution report
				extent.flush();
			}
			else
				System.out.println("skipped : "+data[i][1]);
		}

	}
	public static void main(String[] args) throws Exception {
		
		initializeExtentReport();

		readORFile("./testing/Repository/objRep.properties");
		readConfigFile("./testing/Repository/config.properties");
		
		callTestCasesForExecution();


	}
}
