package scripts;

import java.io.BufferedReader;
import java.util.Properties;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class Driver extends RF{

	
	public static void main(String[] args) throws Exception {
		initializeExtentReport();

		readORFile("./testing/Repository/objRep.properties");
		readConfigFile("./testing/Repository/config.properties");
		//TC01();
		//TC03();
		System.out.println("from maven");
		extent.flush();

	}
}
