package commonFuntions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Countrowscells {

	public static void main(String[] args) throws Throwable {
		
			// read path of excel file
			FileInputStream fi = new FileInputStream("/Users/gauravsalkar/eclipse-workspace/PrimusBank/Sample.xlsx");
			//get workbook from file
			XSSFWorkbook wb = new XSSFWorkbook(fi);
			//get sheet from wb
			XSSFSheet ws =wb.getSheet("Login");
			//get first row from sheet
			XSSFRow row =ws.getRow(0);
			//count no of rows in a sheet
			int rowcount =ws.getLastRowNum();
			//count no of cells in first row
			int cellcount =row.getLastCellNum();
			System.out.println("No of rows::"+rowcount+"  "+"No of cells in first row::"+cellcount);
			//close file
			fi.close();
			wb.close();
		
	}

}
