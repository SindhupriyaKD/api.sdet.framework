package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.testng.annotations.*;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataProviderClass {
	
	@DataProvider(name="data")
	public Object[][] readDataUsingDataProvider() throws Throwable, IOException
	{
		FileInputStream fis= new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\api.sdet.framework\\src\\test\\resources\\DDTRest.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		int lastRow = sh.getLastRowNum()+1;
		int lastCell = sh.getRow(0).getLastCellNum();
		Object[][] obj= new Object[lastRow][lastCell];
		for (int i = 0; i < lastRow; i++) {
			for (int j = 0; j < lastCell; j++) {
				obj[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(obj[i][j]);
			}
		}
		return obj;
	}
	

}
