package Testscript;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Pagefactory.pf_HomePage;
import Pagefactory.pf_SignIn;

public class script_Search extends  Base{

	Logger searchlog = Logger.getLogger(script_Search.class); 
	
	@Test(dataProvider="invalidSearch", dataProviderClass=Dataproviders.dp_search.class,enabled=true,priority=4,groups={"Smk","Reg"})
	public void Invalid_Search(Map hm) throws Exception{
		
		String uname = hm.get("Uname").toString();
		String pwd = hm.get("password").toString();
		String schtext = hm.get("searchtext").toString();
		String exp = hm.get("Expected").toString();
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		
		 startTest = extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		 
		searchlog.info("Staredt execution of test case " + tcid +"_" + order);
//		click on signin
		pf_HomePage pf_HomePage = new pf_HomePage(fd);
		pf_HomePage.lk_signin_click();
		searchlog.info("Clicked on Sign in link and the snapshot is stored at " + getScreenshot());
		startTest.log(LogStatus.PASS, "Click on Sign in","Clicked on Sign in link" + startTest.addScreenCapture(getScreenshot()) );
//		Enter user details
		pf_SignIn pf_SignIn = new pf_SignIn(fd);
		pf_SignIn.login(uname, pwd);
		searchlog.info("Entered login details");
		startTest.log(LogStatus.PASS, "Enter login details","Entered login details" + startTest.addScreenCapture(getScreenshot()) );
//		Search for book
		pf_HomePage.searchoperation(schtext);
		Thread.sleep(10000);
		String Actual = pf_HomePage.msg_searcherror.getText();
		searchlog.info("Actual search error message is: " + Actual);
		startTest.log(LogStatus.PASS, "fetch actual val","Actual search error message is: " + Actual + " "+ startTest.addScreenCapture(getScreenshot()));
		if(Actual.equals(exp)){
			System.out.println("pass");
			searchlog.info("Passed as the error message as per the requirments");
			startTest.log(LogStatus.PASS, "Validate search msg","Passed as the error message as per the requirments" + startTest.addScreenCapture(getScreenshot()));
		}else{
			System.out.println("Fail");
			searchlog.info("Failed as Actual err msg is " + Actual + " and expected is " + exp);
			startTest.log(LogStatus.FAIL, "Validate search msg","Failed as Actual err msg is " + Actual + " and expected is " + exp + startTest.addScreenCapture(getScreenshot()));
		}
		
		
		
		
	}
	
//	Valid Search
	
	@Test(dataProvider="validSearch",dataProviderClass=Dataproviders.dp_search.class,priority=3,enabled=true, groups={"Reg"})
	public void ValidSearch(Map hm) throws Exception{
	
		String uname = hm.get("Uname").toString();
		String pwd = hm.get("password").toString();
		String schtext = hm.get("searchtext").toString();
		String exp = hm.get("Expected").toString();
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		
		 startTest = extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		
		pf_HomePage pf_HomePage = new pf_HomePage(fd);
		pf_HomePage.lk_signin_click();
		cv_contains(fd.getTitle(), "rediff", "Sigin Page title validation");
//		OR
//		if(fd.getTitle().contains("rediff")){
//			startTest.log(LogStatus.PASS, "Sigin Page title validation" , "Passed as the values are matching" + startTest.addScreenCapture(getScreenshot()));
//		}else{
//			
//			startTest.log(LogStatus.FAIL, "Sigin Page title validation" , "Failes as the actual value is" + fd.getTitle()+ " and the expected is rediff"   + startTest.addScreenCapture(getScreenshot()));
//		}
		
		pf_SignIn pf_SignIn = new pf_SignIn(fd);
		pf_SignIn.login(uname, pwd);
		
		pf_HomePage.searchoperation(schtext);
		
		String Act = pf_HomePage.msg_srchcount.getText();
		
		
		//cv_equals(Act, exp, "Search count validation");
//		OR
//		if(Act.equals(exp)){
//			startTest.log(LogStatus.PASS,  , "Passed as the values are matching" + startTest.addScreenCapture(getScreenshot()));
//			
//		}else{
//			
//			startTest.log(LogStatus.FAIL, "Search count validation" , "Failes as the actual value is" + Act + " and the expected is " + exp  + startTest.addScreenCapture(getScreenshot()));
//		}
//		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
