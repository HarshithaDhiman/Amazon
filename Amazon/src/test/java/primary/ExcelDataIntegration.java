package primary;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataIntegration extends LoadProperties{
	public static FileInputStream fis;
	public static XSSFSheet sh;
	public static XSSFWorkbook workbook;
	static LoadProperties lp = new LoadProperties();

	public static XSSFSheet readExcelData(String sheet) throws IOException {
		try {
			fis = new FileInputStream(".//ExcelData//TestData.xlsx");
		}
		catch(Exception e) {
			ColorLog.i("ExcelIntegration Class File","The data file is not found in the specified path");
		}
		try {
			workbook = new XSSFWorkbook(fis);
			sh = workbook.getSheet(sheet);
		}
		catch(Exception e) {
			ColorLog.i("ExcelIntegration Class File","The sheet of ExcelFile is not there, Please check!");
		}
		return sh;
	}


}
