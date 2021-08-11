package driverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utility.ExcelFileUtil;
import commonFuntions.FunctionLibrary;
import constant.PBconstant;

public class HybridDriverScript extends PBconstant{
	String inputpath ="/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestInput//HybridTest.xlsx";
	String outputpath ="/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestOutput//HybridResults.xlsx";
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	
		ExtentReports report;
     	ExtentTest test;
     	
	@Test
	public void startTest()throws Throwable
	{
		report= new ExtentReports("./ExtentReports/"+FunctionLibrary.generateDate()+"  "+"HybridKeyword1.html");
		//access ExcelFile util methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		boolean res=false;
		String tcres="";
		//count no of rows in TCSheet
		int TCCount = xl.rowCount(TCSheet);
		//count no of rows in TSSheet
		int TSCount = xl.rowCount(TSSheet);
		Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
		for(int i=1;i<=TCCount;i++)
		{
			//read execute cell
			String execute =xl.getCellData(TCSheet, i, 2);
			if(execute.equalsIgnoreCase("Y"))
			{
				//read tcid cell from TCSheet
			String tcid =xl.getCellData(TCSheet, i, 0);
			for(int j=1;j<=TSCount;j++)
			{
				//read tsid from TSSheet
			String tsid =xl.getCellData(TSSheet, j, 0);
			if(tcid.equalsIgnoreCase(tsid))
			{
				//read keyword cell from TSSheet
			String keyword =xl.getCellData(TSSheet, j, 4);
			if(keyword.equalsIgnoreCase("AdminLogin"))
			{
				String username = xl.getCellData(TSSheet, j, 5);
				String password = xl.getCellData(TSSheet, j, 6);
			 res =	FunctionLibrary.verifyLogin(username, password);
			}
			
			
			
			
			else if(keyword.equalsIgnoreCase("NewBranchCreation"))
			{
				String bname =xl.getCellData(TSSheet, j, 5);
				String address1 =xl.getCellData(TSSheet, j, 6);
				String address2 =xl.getCellData(TSSheet, j, 7);
				String address3 =xl.getCellData(TSSheet, j, 8);
				String area =xl.getCellData(TSSheet, j, 9);
				String zipcode =xl.getCellData(TSSheet, j, 10);
				String country =xl.getCellData(TSSheet, j, 11);
				String state =xl.getCellData(TSSheet, j, 12);
				String city =xl.getCellData(TSSheet, j, 13);
				FunctionLibrary.clickBranches();
				res =FunctionLibrary.verifynewBranch(bname, address1, address2, address3, area, zipcode, country, state, city);
			}
			
			else if(keyword.equalsIgnoreCase("UpdateBranch"))
			{
				String udatebname =xl.getCellData(TSSheet, j, 5);
				String udateaddress =xl.getCellData(TSSheet, j, 6);
				String zipcode =xl.getCellData(TSSheet, j, 10);
				FunctionLibrary.clickBranches();
				res =FunctionLibrary.verifybranchUpdate(udatebname, udateaddress, zipcode);
			}
			
			else if(keyword.equalsIgnoreCase("AdminLogout"))
			{
				res= FunctionLibrary.verifyLogout();
			}
			String tsres="";
			if(res)
			{
				//write as pass into results cell in TSSheet
				tsres="pass";
				xl.setCellData(TSSheet, j, 3, tsres, outputpath);
			}
			else
			{
				//write as Fail into results cell in TSSheet
				tsres="Fail";
				xl.setCellData(TSSheet, j, 3, tsres, outputpath);
			}
			tcres=tsres;
			}
			}
			//write as pass or fail into TCSheet
			xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//write as blocked into Results cell in TCSheet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}
	
}
