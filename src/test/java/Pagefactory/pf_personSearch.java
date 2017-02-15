package Pagefactory;



import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Testscript.script_PersonSearch;

public class pf_personSearch extends pf_genericmethods {
	
	@FindBy(xpath="//div[@id='test']/table/tbody/tr[1]/td[2]/input")
	public WebElement txt_LastName;
	
	@FindBy(xpath="//div[@id='test']/table/tbody/tr[2]/td[2]/input")
	public WebElement txt_FirstName;
	
	@FindBy(xpath="//div[@id='test']/table/tbody/tr[3]/td[2]/input")
	public WebElement txt_DOB;
	
	@FindBy(xpath="//*[@id='test']/table/tbody/tr[4]/td[2]/select/option")
	public List<WebElement> dd_Gender;
	
	@FindBy(xpath="//div[@id='tablePerson_length']/label/select/option")
	public List<WebElement> dd_tablelength;
	
	
	@FindBy(xpath="//input[@id='pg:frm:pb:searchBtn']")
	public WebElement btn_Search;
	
	@FindBy(xpath="//*[@id='pg:frm:resultTableWrap']/center")
	public WebElement err_Search;
	
	@FindBy(xpath="//*[@class='messageTable']/tbody/tr[1]/td[2]/div[1]")
	public WebElement err_oneCriteria;
	
	
	@FindBy(xpath="//*[@id='tablePerson']/tbody/tr")
	public List<WebElement> tbl_Prsnsrchresult;
	
public pf_personSearch(WebDriver driver){
	
	PageFactory.initElements(driver, this);
	}

public void enterSearchCriteria(String lastName, String firstName, String DOB, String Gender){
	
	cl_entertext(txt_LastName, lastName);
	cl_entertext(txt_FirstName, firstName);
	cl_entertext(txt_DOB, DOB);
	if (!Gender.equals("")){
		selectval(dd_Gender, Gender);
	}
	
	
}
	
public void search_click(){
	
	cl_click(btn_Search);
		
	}

public boolean checkelementexist (WebElement ele){
	
	boolean exist= false;
	try{
		if (!ele.getText().equals("")){
			exist= true;
		}
		
	}
	
	catch (Exception e){
	System.out.println(e);
	
	}
			
	return exist;
	
	}

	
public void selectval(List<WebElement> we,String value){
	
	cl_selectfromdropdown(we,value);

}	
	


public WebElement selectPerson(String PID, String LastName,String FirstName, String DOB, String Gender,String Expected, WebDriver driver) throws Exception{
	
	
	
	try{
		boolean empty = tbl_Prsnsrchresult.isEmpty();
		
	if(!empty){
	
	int tblsize = tbl_Prsnsrchresult.size();
	
	
	for (int i=0;i<tblsize; i++){
		WebElement lnk_LastName=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[3]/a"));
		
		String lstName = lnk_LastName.getText();
		
		WebElement lnk_PID=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[2]/a"));
		
		String PersonID = lnk_PID.getText();
		
        WebElement txt_FirstName=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[4]"));
		
		String frstName = txt_FirstName.getText();
		
        WebElement txt_DOB=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[6]"));
		
		String DateofBirth = txt_DOB.getText();
		
        WebElement txt_Gender=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[7]"));
		
		String Gen = txt_Gender.getText();
		
		
		if (Expected.equals(PersonID) && !Expected.equals("")){
			
			System.out.println("Element found");
			
			return lnk_PID;
			
		}
		
		else if (Expected.equals(lstName)&& !Expected.equals("")){
				if (!DOB.equals("")&& !Gender.equals("")){
					if (DOB.equals(DateofBirth)&& Gender.equals(Gen)){
						return lnk_LastName;
					}
				}
				
				else if (DOB.equals("")&& !Gender.equals("")){
					if (Gender.equals(Gen)){
						return lnk_LastName;
					}
				}
				
				else if (!DOB.equals("")&& Gender.equals("")){
					if (DOB.equals(DateofBirth)){
						return lnk_LastName;
					}
				}
				
				else {
					return lnk_LastName;
				}
			}
				
			
			
		
		
		
		
		
	/*	if (PID!=""){
			
			if (PersonID.equals(PID)){
				
//				lnk_LastName.click();
				return lnk_PID;
				
			}
			
			
			
		}
		
		else if(!LastName.equals("")&& !FirstName.equals("")&& !DOB.equals("")&& !Gender.equals("")){
				
				if (((lstName.contains(LastName)|| LastName.contains(lstName)) && 
                		((frstName.contains(FirstName)|| frstName.contains(FirstName)))&& DOB.equals(DateofBirth)
                		&& Gender.equals(txt_Gender))){
				
				return lnk_LastName;
				}		
			}
		
		*/
		
		
	}
	
	
	
	
	}
	}
	
	catch(Exception e){
		throw (e);
	}
	return null;
	
	
	

}

String Actual_Result;




public WebElement searchbyPersonDetail(String PID, String LastName,String FirstName, String DOB, String Gender, String ExpectedResult, WebDriver driver) throws Exception{
	
	if (LastName.equals("")&& FirstName.equals("")&& DOB.equals("")&& Gender.equals("")){
		
			
			return err_oneCriteria;
			
			//loginlog.info("No Result found");
			//startTest.log(LogStatus.PASS,"Enter one search criteria", startTest.addScreenCapture(getScreenshot()));
			//bs.cv_equals(Actual_Result, ExpectedResult, "Enter one Search Criteria");
		
	}
	
	else{	
	
	
	if (err_Search != null && !err_Search.getText().equals("")){
		return err_Search;
	}
	else{
		
	      selectval(dd_tablelength,"100");
		  WebElement ele1 =selectPerson(PID, LastName, FirstName, DOB, Gender, ExpectedResult, driver);
		  return ele1;	
		
	     }
	
}
}

public WebElement personSearch(String lastName, String firstName, String PID, WebDriver driver) throws Exception{
	
	cl_entertext(txt_LastName, lastName);
	cl_entertext(txt_FirstName, firstName);
	btn_Search.click();
	selectval(dd_tablelength,"100");
	
	try{
		boolean empty = tbl_Prsnsrchresult.isEmpty();
		
	if(!empty){
	
	int tblsize = tbl_Prsnsrchresult.size();
	
	
	for (int i=0;i<tblsize; i++){
		
		
		WebElement lnk_PID=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[2]/a"));
		
		String PersonID = lnk_PID.getText();     
		
		
		if (PID.equals(PersonID)){
			
			return lnk_PID;
			
		}
		
		
			}
	
		
	}
	

	}	
		
	catch(Exception e){
		throw (e);
	}
	
	return null;
	
}

}