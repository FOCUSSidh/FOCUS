package Genericlibrary;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Testscript.ElementNotFoundException;

public class Base {

	public WebDriver fd;
	public static ExtentReports extentReports;
	public ExtentTest startTest;
	public String tcid;
	public String order;
	public String browser_type;
	public Utility ut;
	
	@BeforeSuite(groups={"Smk","UAT","Reg","ST","SIT"})
	public void create_Report(){
		
		extentReports = new ExtentReports("C:\\DEFocus\\Report\\DEFOCUS_"+get_datetimestamp() +".html",false);
		
	}
	
	@Parameters({"browser"})
	@BeforeMethod(groups={"Smk","UAT","Reg","ST","SIT"})
	public void launchApp(String btype) throws Exception{
		browser_type=btype;
		if(btype.equals("ff")){
			
			fd=new FirefoxDriver();
		}else if(btype.equals("ch")){
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\chromedriver.exe" );
			
			// "C:\\Users\\siagrawal\\chromedriver.exe"
			fd=new ChromeDriver();
		}else if(btype.equals("ie")){
			System.setProperty("webdriver.ie.driver", "E:\\drivers\\IEDriverServer.exe");
			fd=new InternetExplorerDriver();
			
		}
//		fd= new FirefoxDriver();
		fd.get(Utility.getval(Utility.getval("env")));
		fd.manage().window().maximize();
		fd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	
	@AfterMethod(groups={"Smk","UAT","Reg", "ST","SIT"})
	public void cloaseApp(ITestResult result) throws Exception{	
		
		
		
		if(result.getStatus() == ITestResult.FAILURE){
			String []message=result.getThrowable().toString().split(":");
			startTest.log(LogStatus.FAIL, "Test Case Failed" , message[1].trim() +  startTest.addScreenCapture(getScreenshot()));
			
		}if(result.getStatus() == ITestResult.SKIP){
			startTest.log(LogStatus.FAIL, "Test Case Skip" , result.getThrowable().toString() +  startTest.addScreenCapture(getScreenshot()));
			
		}if(result.getStatus() == ITestResult.SUCCESS){
			startTest.log(LogStatus.PASS, "Test Case Passed" , startTest.addScreenCapture(getScreenshot()));
			
		}
		
		fd.close();
		
		extentReports.endTest(startTest);
		extentReports.flush();
		
		
	}
	
	public String get_datetimestamp(){
		Date date = new Date();
//		format date
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
//		
		 String format = dateFormat.format(date);
		 return format;
	}
	
//	capture snapshot
	public String getScreenshot() throws Exception{
		
		TakesScreenshot sc=(TakesScreenshot)fd;
		File screenshotAs = sc.getScreenshotAs(OutputType.FILE);
		
		String fpath = Utility.getval("Screenshot_path") + tcid + "_" + order + "_" + get_datetimestamp() +".png";
		FileUtils.copyFile(screenshotAs, new File(fpath));
		return fpath;
				
	}
	
	
	public void waits(WebElement ele){
		WebDriverWait wait = new WebDriverWait(fd,120);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
//	Common Validation
//	equals
	public void cv_equals(String actual,String expected,String stepname) throws Exception{
		
		ut=new Utility();
		
		
		if(actual.equals(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
			//ut.writeExcel(sheetname, testcaseId,Order, "Status", "Pass");
			
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
			//ut.writeExcel(sheetname, testcaseId,Order, "Status", "Fail");
		}
		
				
	}
	
	
//	contains
public void cv_contains(String actual,String expected,String stepname) throws Exception{
		
		if(actual.contains(expected)){
			startTest.log(LogStatus.PASS, stepname , "Passed as the Step "  + stepname + " ." + startTest.addScreenCapture(getScreenshot()));
			
		}else{
			
			startTest.log(LogStatus.FAIL, stepname , "Failed the Step " +stepname+ " as the actual value is " + actual + " and the expected is " + expected  + startTest.addScreenCapture(getScreenshot()));
		}
		
				
	}
	
}
