package Pagefactory;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_PersonSearchresult extends pf_genericmethods{
	
	@FindBy(xpath="//div[@id='tablePerson_length']/label/select/option")
	List<WebElement> dd_tablelength;
	
	@FindBy(xpath="//*[@id='tablePerson']/tbody/tr")
	List<WebElement> tbl_Prsnsrchresult;
	
	public pf_PersonSearchresult(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		}
	
	public void select(String val){
		int size = dd_tablelength.size();
		
		for(WebElement xyz: dd_tablelength){
			
			
			
			if(xyz.getText().equals(val)){
				
				xyz.click();
				break;
				
			}
	}
		

 }
	
	public WebElement selectPerson(String PID, String LastName, WebDriver driver) throws Exception{
		
		
		
		try{
			boolean empty = tbl_Prsnsrchresult.isEmpty();
			
		if(!empty){
		
		int tblsize = tbl_Prsnsrchresult.size();
		
		
		for (int i=0;i<tblsize; i++){
			WebElement lnk_LastName=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[3]/a"));
			
			String Name = lnk_LastName.getText();
			
			WebElement lnk_PID=driver.findElement(By.xpath("//*[@id='tablePerson']/tbody/tr["+(i+1)+"]/td[2]/a"));
			
			String PersonID = lnk_PID.getText();
			
			if (PID!=""){
				
				if (Name.equals(LastName)&& PersonID.equals(PID)){
					
//					lnk_LastName.click();
					return lnk_PID;
					
				}
				
				
				
			}
			
			else{
				
                    if ((PersonID.equals(PID))&& (Name.contains(LastName)|| LastName.contains(Name))){
					
					return lnk_LastName;
					
					
				}
				
				
				
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
