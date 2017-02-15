package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class pf_SignIn extends pf_genericmethods{
	
	@FindBy(name="logid")
	public WebElement txt_username;
	
	@FindBy(name="pswd")
	public WebElement txt_pwd;
	
	@FindBy(xpath="//input[@type='submit' and @value='Login']")
	public WebElement bt_login;
	
	@FindBy(xpath="//b[contains(text(),'Sorry we were unable to complete this step because')]")
	public WebElement msg_loginerr;
	
	
	public pf_SignIn(WebDriver driver){		
		PageFactory.initElements(driver, this);		
	}
	
	
		public void login(String uname,String pwd){
			
			cl_entertext(txt_username, uname);
			cl_entertext(txt_pwd, pwd);
			cl_click(bt_login);
			
//			
//			txt_username.sendKeys(uname);
//			txt_pwd.sendKeys(pwd);
//			bt_login.click();
			
		}
	
	
	
	
	

}
