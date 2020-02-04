package v2api_Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class readExcel {
	@SuppressWarnings("deprecation")
	public static Object[][] readExcel(String filePath, String sheetName) {

		File file = new File(filePath);

		String[][] value = null;
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Workbook guru99Workbook = null;
		try {
			guru99Workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Read sheet inside the workbook by its name

		Sheet sheet = guru99Workbook.getSheet(sheetName);

		// Find number of rows in excel file

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// Create a loop over all the rows of excel file to read it
		
		
		value = new String[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 1; i < rowCount + 1; i++) {

			Row row = sheet.getRow(i);

			// Create a loop to print cell values in a row

			for (int j = 0; j < row.getLastCellNum(); j++) {
				if (row.getCell(j).getCellType() == Cell.CELL_TYPE_NUMERIC) {
					long number = (long) row.getCell(j).getNumericCellValue();
					// int number1 = (int) number;
					value[i - 1][j] = String.valueOf(number);
				} else if (row.getCell(j).getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					Boolean status = row.getCell(j).getBooleanCellValue();
					value[i - 1][j] = String.valueOf(status);
				} else {
					value[i - 1][j] = row.getCell(j).getStringCellValue();
				}
			}
		}
		return value;
	}
}
