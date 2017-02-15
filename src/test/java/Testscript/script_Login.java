package Testscript;

import java.sql.Driver;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Pagefactory.pf_HomePage;
import Pagefactory.pf_SignIn;

public class script_Login extends Base{
	
	Logger loginlog = Logger.getLogger(script_Login.class);
	
	@Test(dataProvider="invalidLogin",dataProviderClass=Dataproviders.dp_login.class,enabled=true,priority=2,groups={"UAT","Reg"})
	public void  invalid_Login(Map hm){
		
		
		
		String uid = hm.get("username").toString();
		String pass = hm.get("pwd").toString();
		String exp = hm.get("exp_msg").toString();
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		
		 startTest = extentReports.startTest(tcid+"_" + order + "_" + browser_type);
//		fd.findElement(By.linkText("Sign In")).click();
//		refined code
//		object of Homepage pf
		pf_HomePage p_hp = new pf_HomePage(fd);
		p_hp.lk_signin_click();
//		another way
//		p_hp.cl_click(p_hp.lk_sigin);
		
//		object of signin pf
		pf_SignIn pf_SI = new pf_SignIn(fd);
		pf_SI.login(uid, pass);
		
//		
//		fd.findElement(By.name("logid")).sendKeys(uid);
//		fd.findElement(By.name("pswd")).sendKeys(pass);
//		fd.findElement(By.xpath("//input[@type='submit' and @value='Login']")).click();
//		
		
//		String Act_msg = fd.findElement(By.xpath("//b[contains(text(),'Sorry we were unable to complete this step because')]")).getText();
//		optimised
		String Act_msg =pf_SI.msg_loginerr.getText();
		if(Act_msg.equals(exp)){
			startTest.log(LogStatus.PASS, "Validate Login msg","Passed");
			System.out.println(" Pass");
		}else{
			startTest.log(LogStatus.FAIL, "Validate Login msg","Failed");
			System.out.println("Fail");
		}
		

		
	}
	
	
	
	
//	valid Scenario
	
@Test(dataProvider ="validLogin", dataProviderClass=Dataproviders.dp_login.class,priority=1,groups={"Reg"})
public void valid_Login(Map hm){
	
	String uid = hm.get("username").toString();
	String pass = hm.get("pwd").toString();
	String exp_uname = hm.get("exp_msg").toString();	
	tcid = hm.get("TC_ID").toString();
	order=hm.get("Order").toString();
	
	 startTest = extentReports.startTest(tcid+"_" + order + "_" + browser_type);
	
	
	pf_HomePage pf_HomePage = new pf_HomePage(fd);
	pf_HomePage.lk_signin_click();
	
	pf_SignIn pf_SignIn = new pf_SignIn(fd);
	pf_SignIn.login(uid, pass);
	
	
	
//	String Act_uname = fd.findElement(By.className("proper")).getText();
	String Act_uname = pf_HomePage.msg_uname.getText();
	
	if(Act_uname.equals(exp_uname)){
		
		System.out.println(" Pass");
	}else{
		
		System.out.println("Fail");
	}
	

	
}
	
	

}




//Check Sync with Git








