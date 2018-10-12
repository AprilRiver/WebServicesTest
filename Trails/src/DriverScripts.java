import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DriverScripts extends AutomationScripts{

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub


		String dt_Path = "/Users/pattulohith/eclipse-workspace/Trails/src/testData1.xls";


		String[][] recdata = ReusableMethods.readSheet(dt_Path, "Sheet1");


		String testCase = recdata[0][0];



		/*Java Reflection */
		Method testScript = AutomationScripts.class.getMethod(testCase);
		testScript.invoke(testScript);
		
//
//
//		Method ts = AutomationScripts.class.getMethod("test1");
//		ts.invoke(ts);

	}

}
