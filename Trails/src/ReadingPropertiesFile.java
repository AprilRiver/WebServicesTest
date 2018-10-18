import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadingPropertiesFile {

	// create outside any method coz all nedd to use
	static  Properties properties;
	// specify property file address 
	static  String propertyFilePath= "/Users/pattulohith/eclipse-workspace/gitRepository/Trails/src/objRepo.properties";

	// read all contents of prop file into  - > properties object

	public static void readPropFileContents(){

		/*simple for understanding:
		 * BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
		 *Properties properties = new Properties();
		 *properties.load(reader);
		 * reader.close();
		 */
		BufferedReader reader;
		try {

			//load file contents into reader
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {

				// through reader load/pass contents into properties object
				properties.load(reader);
				System.out.println("loaded prop file into prop obj");

				//close reader to finish loading
				reader.close();
				System.out.println("closed reader");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	//using properties object get geckodriverPath
	public static String getDriverPath(){
		System.out.println("about to get gecko");
		// pass key i.e exact name in prop file -> it'll match name and fetch value - > assign to a string
		String geckoPath = properties.getProperty("geckodriverPath");
		System.out.println(geckoPath);
		if(geckoPath!= null) 
			return geckoPath;

		// coz without driver nothing can  be done - > exception -> end exe
		else 
		{
			System.out.println("cant read value");
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");	
		}
	}

	public static String getURL()
	{
		return properties.getProperty("URL");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readPropFileContents();

	}

}
