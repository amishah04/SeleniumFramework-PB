package commonFuntions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWrite {

	public static void main(String[] args) throws Throwable {
		FileInputStream fi = new FileInputStream("/Users/gauravsalkar/eclipse-workspace/PrimusBank/Sample.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet ws =wb.getSheet("Login");
		int rowcount =ws.getLastRowNum();
		for(int i=1;i<=rowcount;i++)
		{
			//read user name cell data
		String username =ws.getRow(i).getCell(0).getStringCellValue();
		String password =ws.getRow(i).getCell(1).getStringCellValue();
		System.out.println(username+"   "+password);
		//write some text into results cell
		ws.getRow(i).createCell(2).setCellValue("iam so lazy to practise");
		}
		fi.close();
		//create new file
		FileOutputStream fo = new FileOutputStream("E:/Results.xlsx");
		wb.write(fo);
		fo.close();
		wb.close();

	}

}
