package Testscript;

import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Pagefactory.pf_FOCUSHomepage;
import Pagefactory.pf_Login;
import Pagefactory.pf_PersonSearchresult;
import Pagefactory.pf_personSearch;

public class script_PersonSearch extends Base{

	Logger loginlog = Logger.getLogger(script_PersonSearch.class);

	@Test(dataProvider="personSearch", dataProviderClass=Dataproviders.dp_personSearch.class, enabled=true, priority=1, groups={"ST"})

	public void personSearch(Map hm) throws Exception{

		String uid=hm.get("username").toString();
		String pwd=hm.get("pwd").toString();
		String lastName=hm.get("LastName").toString();
		String firstName=hm.get("FirstName").toString();
		String DOB=hm.get("DOB").toString();
		String Gender=hm.get("Gender").toString();

		String PID=hm.get("PID").toString();
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
		loginlog.info("Clicked All Tabs");
		startTest.log(LogStatus.PASS,"Clicked Person Search", startTest.addScreenCapture(getScreenshot()));




		pf_personSearch pf_personSearch = new pf_personSearch(fd);



		pf_personSearch.enterSearchCriteria(lastName, firstName, DOB, Gender);

		loginlog.info("Entered Search Criteria");
		startTest.log(LogStatus.PASS,"Entered Search criteria", startTest.addScreenCapture(getScreenshot()));

		pf_personSearch.search_click();


		loginlog.info("Clicked Search button");
		startTest.log(LogStatus.PASS,"Clicked Search button", startTest.addScreenCapture(getScreenshot()));


		WebElement srchresult=pf_personSearch.searchbyPersonDetail(PID, lastName,firstName, DOB, Gender,ExpectedResult, fd);



		if (srchresult!=null){
			Actual_Result=srchresult.getText();
			cv_equals(Actual_Result, ExpectedResult, "Verify Person Search");


			srchresult.click();

			loginlog.info("Selected Person");
			startTest.log(LogStatus.PASS,"Selected Person", startTest.addScreenCapture(getScreenshot()));


		}

		else {

			loginlog.info("Person Not found");
			startTest.log(LogStatus.FAIL,"Person Not found or error in the search criteria", startTest.addScreenCapture(getScreenshot()));

		}




















		/*if (lastName.equals("")&& firstName.equals("")&& DOB.equals("")&& Gender.equals("")){
			//boolean checkelementexist = pf_personSearch.checkelementexist(pf_personSearch.err_oneCriteria);


			//if (checkelementexist==true){

				Actual_Result=pf_personSearch.err_oneCriteria.getText().trim();

				//loginlog.info("No Result found");
				//startTest.log(LogStatus.PASS,"Enter one search criteria", startTest.addScreenCapture(getScreenshot()));
				cv_equals(Actual_Result, ExpectedResult, "Enter one Search Criteria");

		}

		else{	


		if (!pf_personSearch.err_Search.getText().equals("")){
			Actual_Result= pf_personSearch.err_Search.getText();
			cv_equals(Actual_Result, ExpectedResult, "No Result Found");
		}
		else{


			//pf_PersonSearchresult pf_Prsnsrchresult = new pf_PersonSearchresult(fd);

			pf_personSearch.selectval(pf_personSearch.dd_tablelength,"100");

			loginlog.info("Selected No. of Records to display");
			startTest.log(LogStatus.PASS,"Selected No. of Records to display", startTest.addScreenCapture(getScreenshot()));

			//WebElement ele1=pf_personSearch.selectPerson(PID, lastName, fd);


			 WebElement ele1 = pf_personSearch.selectPerson(PID, lastName, firstName, DOB, Gender, ExpectedResult, fd);


			if (ele1!=null){

				Actual_Result=ele1.getText();

				cv_equals(Actual_Result, ExpectedResult, "Check if Result found");

				pf_personSearch.cl_click(ele1);				

				loginlog.info("Selected Person");
				startTest.log(LogStatus.PASS,"Selected Person", startTest.addScreenCapture(getScreenshot()));

			}

			else{

				loginlog.info("Person Not found");
				startTest.log(LogStatus.FAIL,"Person Not found or error in the search criteria", startTest.addScreenCapture(getScreenshot()));

			}

			//pf_Prsnsrchresult.selectPerson(PID, lastName, fd);



		}




	}*/

	}




}
