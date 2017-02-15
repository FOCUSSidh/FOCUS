package Pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_Person extends pf_genericmethods{
	
	@FindBy(xpath="//div[@id='contentWrapper']/div[2]/table/tbody/tr[1]/td[2]/div[10]/div[1]/div[1]/div[1]/table/tbody/tr/td[2]/input")
	public WebElement btn_newCase;
	
	@FindBy(xpath="//div[@id='contentWrapper']/div[2]/table/tbody/tr[1]/td[2]/div[10]/div[1]/div[1]/div[2]/table/tbody/tr")
	public List<WebElement> tbl_Case;	
	
	
	public pf_Person(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		}
	
	

}
