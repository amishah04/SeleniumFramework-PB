package commonFuntions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//read excel path
				FileInputStream fi = new FileInputStream("/Users/gauravsalkar/eclipse-workspace/PrimusBank/Sample.xlsx");
				//get wb from file
				XSSFWorkbook wb = new XSSFWorkbook(fi);
				//get sheet from wb
				XSSFSheet ws =wb.getSheetAt(0);
				//get row from sheet
				XSSFRow row = ws.getRow(0);
				//count no of rows
				int rowcount =ws.getLastRowNum();
				System.out.println("No of rows are::"+rowcount);
					//read cell data
				String username =ws.getRow(5).getCell(0).getStringCellValue();
				String password =ws.getRow(4).getCell(1).getStringCellValue();
				System.out.println(username+"   "+password);
				fi.close();
				wb.close();
			

	}

}
