package Pagefactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_CaseEdit extends pf_genericmethods{
	
	@FindBy(xpath="//*[@id='j_id0:j_id1:j_id29:CreationSection']/div[2]/table/tbody/tr[1]/td[1]/select/option")
	public List<WebElement> dd_CaseType;	
	
	@FindBy(xpath="//*[@id='j_id0:j_id1:j_id29:CreationSection:j_id40']")
	public WebElement txt_openDate;
	
	@FindBy(xpath="//*[@id='j_id0:j_id1:j_id29:CreationSection:j_id41']/option")
	public List<WebElement> dd_County;	
	
	@FindBy(xpath="//*[@id='j_id0:j_id1:j_id29:j_id30']/input[1]")
	public WebElement btn_Save;
	
public pf_CaseEdit(WebDriver driver){
		
		PageFactory.initElements(driver, this);
		}
	
	
}
