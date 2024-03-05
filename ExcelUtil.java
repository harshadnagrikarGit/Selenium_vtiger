package commonutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelUtil {

	public String getDataFromExcel(String sheetName, int row, int col) throws IOException {
		
		FileInputStream fis = new FileInputStream("src\\test\\resources\\Data.xlsx");
		
//		FileInputStream fis = new FileInputStream("");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row rw = sh.getRow(row);
		Cell c1 = rw.getCell(col);
		String data = c1.getStringCellValue();	
		return data;
	}
	
}
