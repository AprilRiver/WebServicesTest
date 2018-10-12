import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ReusableMethods {


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
}
