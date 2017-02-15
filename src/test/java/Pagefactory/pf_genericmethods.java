package Pagefactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class pf_genericmethods {

	//	to enter text in textbox
	public void cl_entertext(WebElement ele,String text){
		ele.sendKeys(text);
	}



	//	to click on any element
	public void cl_click(WebElement ele){
		ele.click();
	}

	// select an Element from dropdown
	public void cl_selectfromdropdown(List<WebElement> ele,String value){
		int size = ele.size();

		for(WebElement xyz: ele){



			if(xyz.getText().equals(value)){

				xyz.click();
				break;

			}
		}



	}

	public void cl_selectfrommultipicklist(List<WebElement> ele,String value,WebDriver driver ){
		int size = ele.size();

		for(WebElement xyz: ele){



			if(xyz.getText().equals(value)){

				Actions action = new Actions(driver);
				action.moveToElement(xyz).doubleClick().build().perform();
				break;

			}
		}



	}




	public void keyWords(String Key, WebDriver driver){

		switch (Key){

		case "EnterText":



		}

	}

	public void switchToDifferentWindow(String parent, String testdata, WebDriver driver ){
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> it = windowHandles.iterator();
		while(it.hasNext()){

			String handle = it.next();
			if(!handle.equals(parent)){

				driver.switchTo().window(handle);
				//System.out.println(fd.getCurrentUrl());
				String title = driver.getTitle();

				if (title.contains(testdata)){
					break;
				}

			}

		}

	}

	public String verifydataFrom_SFWebTable(String xPath, String testdata,WebDriver driver){
		List<WebElement> rowelement = driver.findElements(By.xpath(xPath));
		String colxPath= xPath+"[1]/th";
		List<WebElement> colelement = driver.findElements(By.xpath(colxPath));
		int row=rowelement.size();
		int col=colelement.size();
		int flag=0;
		for (int r=1;r<row;r++){
			//xPath1=
			String xpath1=xPath+"["+ (r+1) +"]"+"/th";
			String val=driver.findElement(By.xpath(xpath1)).getText();
			System.out.println(val);
			if (val.equalsIgnoreCase(testdata)){
				flag=1;
				driver.findElement(By.xpath(xpath1)).click();
				return val;

			}
			else{

				for (int c=1;c<col;c++){

					String xpath2=xPath+"["+ (r+1) +"]"+"/td["+(c)+"]";
					String val1=driver.findElement(By.xpath(xpath2)).getText();
					if (val1.equalsIgnoreCase(testdata)){
						flag=1;
						driver.findElement(By.xpath(xpath2)).click();
						return val1;

					}

				}


			}



			//startTest.log(LogStatus.FAIL, stepName , "Failed as the Step "  + stepName + " ." + startTest.addScreenCapture(getScreenshot()));



		}

		return null;

	}


	public String verifydataFrom_WebTable(String xPath, String testdata,WebDriver driver){
		List<WebElement> rowelement = driver.findElements(By.xpath(xPath));
		String colxPath= xPath+"[1]/td";
		List<WebElement> colelement = driver.findElements(By.xpath(colxPath));
		int row=rowelement.size();
		int col=colelement.size();
		int flag=0;
		for (int r=1;r<=row;r++){

			//This is to test Github sync
			for (int c=1;c<=col;c++){

				String xpath2=xPath+"["+ (r) +"]"+"/td["+(c)+"]";
				String val1=driver.findElement(By.xpath(xpath2)).getText();
				if (val1.equalsIgnoreCase(testdata)){
					flag=1;
				String	xpath3=xpath2+"/a";
					driver.findElement(By.xpath(xpath3)).click();
					return val1;

				}

			}



		}

		return null;

	}




}
