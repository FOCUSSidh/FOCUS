package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_HomePage extends pf_genericmethods {
	
	@FindBy(linkText="Sign In")
	public WebElement lk_sigin;
	
	@FindBy(className="proper")
	public WebElement msg_uname;
	
	@FindBy(id="srchword")
	public WebElement txt_srch;
	
	@FindBy(className="newsrchbtn")
	public WebElement btn_srchbutton;
	
	@FindBy(className="sorrymsg")
	public WebElement msg_searcherror;
	
	@FindBy(id="find")
	public WebElement msg_srchcount;
	
	
	public pf_HomePage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void lk_signin_click(){
		
		cl_click(lk_sigin);
		
		
	}
	
	
//	search functionality
	public void searchoperation(String srchitem){
		cl_entertext(txt_srch, srchitem);
		cl_click(btn_srchbutton);
		
	}
	
	
	
	
}


