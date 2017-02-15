package Pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pf_FOCUSHomepage extends pf_genericmethods{

	@FindBy(xpath="//div[@id='AppBodyHeader']/table[1]/tbody[1]/tr[1]/td[3]/div[1]/div[2]/div[1]/div[1]/div[1]/span[1]")
	public WebElement lbl_username;
	
	@FindBy(xpath="//li[@id='AllTab_Tab']/a")
	public WebElement lnk_AllTab;
	
	@FindBy(xpath="//a[text()='Person Search']")
	public WebElement lnk_PersonSrch;


public pf_FOCUSHomepage(WebDriver driver){
	
	PageFactory.initElements(driver, this);
}

}
