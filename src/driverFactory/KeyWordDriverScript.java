package driverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utility.ExcelFileUtil;
import commonFuntions.FunctionLibrary;
import constant.PBconstant;

public class KeyWordDriverScript extends PBconstant {

	
	String inputpath = "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestInput//Controller.xlsx";
	
	String outputpath = "/Users/gauravsalkar/eclipse-workspace/Selenium_Frameworks/TestOutput/KeywordResults.xlsx";
	
	String TCSheet = "TestCases";
	String TSSheet = "TestSteps";
	

	ExtentReports report;
	ExtentTest test;
	@Test
	public void startTest()throws Throwable
	{
		report= new ExtentReports("./ExtentReports/"+FunctionLibrary.generateDate()+"  "+"Keyword.html");
		//access ExcelFile util methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		
		boolean res=false;
		
		// DECLARE TESTCASE results
		
		String tcres="";
	
		
		//count no of rows in TCSheet
		int TCCount = xl.rowCount(TCSheet);
		
		//count no of rows in TSSheet
		int TSCount = xl.rowCount(TSSheet);
		
		// print
		Reporter.log("No of rows in TCSheet::"+TCCount+"  "+"No of rows in TSSheet::"+TSCount,true);
		
		//Executing TCSheet 
		for(int i=1;i<=TCCount;i++)
		{
			// generate report
			test=report.startTest("KeywordFramework");
			
			//read execute cell
			String execute =xl.getCellData(TCSheet, i, 2);
			
			if(execute.equalsIgnoreCase("Y"))
			{
			
				//read tcid cell from TCSheet
			String tcid =xl.getCellData(TCSheet, i, 0);
			
			for(int j=1;j<=TSCount;j++)
			{
				//read description cell
			String Description= xl.getCellData(TSSheet, j, 2);
				
			//read tsid from TSSheet
			String tsid =xl.getCellData(TSSheet, j, 0);
			
			if(tcid.equalsIgnoreCase(tsid))
			{
				//read keyword cell from TSSheet
			String keyword =xl.getCellData(TSSheet, j, 3);
			
			if(keyword.equalsIgnoreCase("AdminLogin"))
			{
			 res =FunctionLibrary.verifyLogin("Admin", "Admin");
			
			 test.log(LogStatus.INFO, Description);
			}
			
			else if(keyword.equalsIgnoreCase("NewBranchCreation"))
			{
				FunctionLibrary.clickBranches();
				res =FunctionLibrary.verifynewBranch("Kadiri", "Tanakal", "Madanapalli", "Yerracheru", "Anantapur", "12365", "INDIA", "Delhi", "Delhi");
				test.log(LogStatus.INFO, Description);
			}
			
			else if(keyword.equalsIgnoreCase("UpdateBranch"))
			{
				FunctionLibrary.clickBranches();
				
				res =FunctionLibrary.verifybranchUpdate("Kukatpalli", "Hyderabad", "25874");
				test.log(LogStatus.INFO, Description);
			}
			
			else if(keyword.equalsIgnoreCase("AdminLogout"))
			{
				res= FunctionLibrary.verifyLogout();
				test.log(LogStatus.INFO, Description);
			}
			
			
		
			
			// null
			String tsres="";
			

			
			if(res)
			{
				//write as pass into results cell in TSSheet
				tsres="pass";
				xl.setCellData(TSSheet, j, 4, tsres, outputpath);
				test.log(LogStatus.PASS, Description);
			}
			else
			{
				//write as Fail into results cell in TSSheet
				tsres="Fail";
				xl.setCellData(TSSheet, j, 4, tsres, outputpath);
				test.log(LogStatus.FAIL, Description);
			}
			
			
			tcres=tsres;
			}
			
			
			report.endTest(test);
			report.flush();
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
