package Testscript;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Pagefactory.pf_CaseEdit;
import Pagefactory.pf_FOCUSHomepage;
import Pagefactory.pf_Login;
import Pagefactory.pf_Person;
import Pagefactory.pf_personSearch;

public class script_CaseCreation extends Base{
	Logger loginlog = Logger.getLogger(script_CaseCreation.class);
	
	@Test(dataProvider="validCaseCreation", dataProviderClass=Dataproviders.dp_CaseCreation.class, enabled=true, priority=2, groups={"ST"})
	
	public void caseCreation(Map hm) throws Exception{
		
		String uid=hm.get("username").toString();
		String pwd=hm.get("pwd").toString();
		String lastName=hm.get("LastName").toString();
		String firstName=hm.get("FirstName").toString();
		String PID=hm.get("PID").toString();
		String caseType=hm.get("CaseType").toString();
		String caseopendate=hm.get("OpenDate").toString();
		String county=hm.get("County").toString();
		
		String ExpectedResult=hm.get("Expected").toString().trim();	
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		String Actual_Result;
		
		startTest=extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		loginlog.info("Started execution of test case " + tcid +"_" + order);
		
		pf_Login pf_Login = new pf_Login(fd);
		pf_Login.Login(uid, pwd);
		
		loginlog.info("Entered Login Details");
		startTest.log(LogStatus.PASS,"Entered Login Details", startTest.addScreenCapture(getScreenshot()));
		
		pf_FOCUSHomepage pf_FOCUSHomepage = new pf_FOCUSHomepage(fd);	
		pf_FOCUSHomepage.lnk_AllTab.click();
		
		startTest.log(LogStatus.PASS,"Clicked All Tabs", startTest.addScreenCapture(getScreenshot()));
		
		pf_FOCUSHomepage.lnk_PersonSrch.click();
		loginlog.info("Clicked Person Search");
		startTest.log(LogStatus.PASS,"Clicked Person Search", startTest.addScreenCapture(getScreenshot()));
		
		pf_personSearch pf_personSearch = new pf_personSearch(fd);
		
		WebElement we= pf_personSearch.personSearch(lastName, firstName, PID, fd);
		
		loginlog.info("Entered Search criteria and performed Search");
		startTest.log(LogStatus.PASS,"Entered Search criteria and performed Search", startTest.addScreenCapture(getScreenshot()));
		
		if (we!=null){
			we.click();
			pf_Person Person = new pf_Person(fd);
			Person.btn_newCase.click();
			pf_CaseEdit Case = new pf_CaseEdit(fd);
			Case.cl_selectfromdropdown(Case.dd_CaseType,caseType);
			Case.txt_openDate.clear();
			Case.cl_entertext(Case.txt_openDate,caseopendate);
			Case.cl_selectfromdropdown(Case.dd_County,county);
			Case.btn_Save.click();
			startTest.log(LogStatus.PASS,"Case created successfully", startTest.addScreenCapture(getScreenshot()));
			
		}
		
		else{
			
			loginlog.info("Entered Search criteria and performed Search");
			startTest.log(LogStatus.FAIL,"No Person found", startTest.addScreenCapture(getScreenshot()));
			
		}
		
		
		
		
		
		
		
		
	}
	

}
