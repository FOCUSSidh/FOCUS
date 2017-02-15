package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_Login extends pf_genericmethods {
	
	@FindBy(id="username")
	public WebElement txt_username;
	
	@FindBy(id="password")
	public WebElement txt_password;
	
	@FindBy(id="Login")
	public WebElement btn_login;
	
public pf_Login(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}

public void btn_signin_click(){
	cl_click(btn_login);
}

public void Login(String uname, String pwd){
	cl_entertext(txt_username, uname);
	cl_entertext(txt_password, pwd);
	cl_click(btn_login);
}
	

}
