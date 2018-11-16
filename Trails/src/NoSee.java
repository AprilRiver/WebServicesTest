

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class NoSee {

	public static void main(String args[])
	{
		WebDriver driver = null;
		System.setProperty("webdriver.gecko.driver", "/Users/pattulohith/eclipse-workspace/gitRepository/Trails/Utility/geckodriver");
		driver = new FirefoxDriver();
	}

}
