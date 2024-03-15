package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.eval.NumericValueEval;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Base.Baseproject;
import pages.SignupPage;

public class Excelutility {
	public File file;
	public FileInputStream fis;
	public XSSFWorkbook wb;
	public Sheet sheet;

	public Excelutility(String filepath, int sheetIndex) throws Exception {
		file = new File(filepath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheetAt(sheetIndex);
	}

	public Excelutility(String filepath, String sheetname) throws Exception {
		file = new File(filepath);
		fis = new FileInputStream(file);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);
	}

	public int getRowscount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getcolscount(int rowsNo) {
		return sheet.getRow(rowsNo).getPhysicalNumberOfCells();
	}

	public String getcellvalue(int rowNo, int colNo) {
		CellType cellType = sheet.getRow(rowNo).getCell(colNo).getCellType();
		Cell cell = sheet.getRow(rowNo).getCell(colNo);
		String value = null;
		switch (cellType) {
		case NUMERIC:
			value=String.valueOf(cell.getNumericCellValue());
			break;
		case STRING:
		value=	cell.getStringCellValue();
			break;
		case BOOLEAN:
			value=String.valueOf(cell.getBooleanCellValue());
			break;
		case ERROR:
			value=String.valueOf(cell.getErrorCellValue());
			break;
		}
		return  value;
//	return sheet.getRow(rowNo).getCell(colNo).getStringCellValue();
	}

	public List<HashMap<String, String>> readExcelsheetdata() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		for (int i = 0; i < getRowscount(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < getcolscount(i); j++) {
				map.put(getcellvalue(0, j), getcellvalue(i, j));

			}
			list.add(map);
		}
		return list;

	}

	public void quitEcel() throws Exception {
		wb.close();
		fis.close();
	}
}
