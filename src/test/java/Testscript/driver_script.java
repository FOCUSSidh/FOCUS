package Testscript;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Genericlibrary.Base;
import Genericlibrary.ExcelRW;
import Genericlibrary.GlobalProperties;
import Genericlibrary.Utility;
import Pagefactory.pf_FOCUSHomepage;
import Pagefactory.pf_Login;
import Pagefactory.pf_SignIn;
import Pagefactory.pf_genericmethods;

public class driver_script extends Base {

	Logger driverscript = Logger.getLogger(driver_script.class);

	@Test(dataProvider="genericLibrary", dataProviderClass=Dataproviders.dp_genericLibrary.class, enabled=true, priority=1, groups={"SIT"})

	public void driverScript (Map hm) throws Exception{


		String sheetname = hm.get("SheetName").toString();
		String testdatasheetname = hm.get("TestDataSheetName").toString();
		String workBookName = hm.get("WorkbookName").toString();   
		tcid = hm.get("TC_ID").toString();
		order=hm.get("Order").toString();
		pf_genericmethods genericmethods = new pf_genericmethods();
		Map<String, String> hmap1=null;


		startTest=extentReports.startTest(tcid+"_" + order + "_" + browser_type);
		driverscript.info("Started execution of test case " + tcid +"_" + order);
		ExcelRW excelRW1 = new ExcelRW(System.getProperty("user.dir") + "\\src\\test\\resources\\Tests\\" + workBookName + ".xlsx");
		int testdatarowcount = excelRW1.rowcount(testdatasheetname);
		int testdatacolcount = excelRW1.Colcount(testdatasheetname);
		for(int s = 1;s<=testdatarowcount;s++){

			String testDataOrder=excelRW1.readcellval(testdatasheetname, s, 0);

			if(testDataOrder.equals(order)){
				//			map
				hmap1 = new HashMap<String,String>();

				for(int t=0;t<testdatacolcount;t++){

					String key1 = excelRW1.readcellval(testdatasheetname, 0, t);
					String value1 = excelRW1.readcellval(testdatasheetname, s, t);			
					hmap1.put(key1,value1);
				}
			}
		}

		int rowcount = excelRW1.rowcount(sheetname);
		int colcount = excelRW1.Colcount(sheetname);
		String parent = fd.getWindowHandle();
		for (int i=1;i<=rowcount;i++){
			String keyword=excelRW1.readcellval(sheetname, i, 1);

			String xPath=Utility.getxPath(excelRW1.readcellval(sheetname, i, 3));
			//	String xPath= excelRW1.readcellval(sheetname, i, 4);
			String testData= excelRW1.readcellval(sheetname, i, 2);
			String stepName= excelRW1.readcellval(sheetname, i, 4);

			String testdata=null;

			if(!testData.equals("")){

				testdata = hmap1.get(testData).toString();
			}

			switch (keyword){

			case "EnterText":
				WebElement txt_EnterText = fd.findElement(By.xpath(xPath));
				genericmethods.cl_entertext(txt_EnterText, testdata);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "Click":
				WebElement btn = fd.findElement(By.xpath(xPath));
				genericmethods.cl_click(btn);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "verifyExpected":
				WebElement lbl = fd.findElement(By.xpath(xPath));
				String actual=lbl.getText();
				cv_contains(actual, testdata, stepName);
				break;

			case "selectfromDropdown":
				String ddvalue = hmap1.get(testData).toString();
				List<WebElement> dpdown=fd.findElements(By.xpath(xPath));
				genericmethods.cl_selectfromdropdown(dpdown, testdata);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "switchToiFrame":
				WebElement iFrame = fd.findElement(By.xpath(xPath));
				fd.switchTo().frame(iFrame);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "switchToDefault":
				fd.switchTo().defaultContent();
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "switch_To_Different_Window":
				String browser_title = hmap1.get(testData).toString();
				genericmethods.switchToDifferentWindow(parent, testdata, fd);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "switch_To_Parent_Window":
				fd.close();
				fd.switchTo().window(parent);
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "waitforElement":
				WebElement element=fd.findElement(By.xpath(xPath));
				waits(element);
				break;

			case "verifydataFrom_SF_WebTable":
				String flag = genericmethods.verifydataFrom_SFWebTable(xPath, testdata, fd);
				if (flag!=null){
					cv_contains(flag, testdata, stepName);
				}
				else{
					startTest.log(LogStatus.FAIL, stepName , "No Value found "  + stepName + " ." + startTest.addScreenCapture(getScreenshot()));
				}
				break;

			case "selectfromMultipicklist":

				for(String val:testdata.split(";")){
					List<WebElement> multiselectdpdown=fd.findElements(By.xpath(xPath));
					genericmethods.cl_selectfrommultipicklist(multiselectdpdown, val.trim(), fd);
				}
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;

			case "setVariable":
				String variableName = hmap1.get(testData).toString();
				WebElement variableValue=fd.findElement(By.xpath(xPath));
				hmap1.put(variableName, variableValue.getText());
				startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
				break;
				
			case "verifyCreation":
				try{
					WebElement creationId=fd.findElement(By.xpath(xPath));

					if(!creationId.getText().equals("")){
						startTest.log(LogStatus.PASS, stepName,"Passed as the Step "  + stepName+"."+ startTest.addScreenCapture(getScreenshot()) );
					}
					else{
						startTest.log(LogStatus.FAIL, stepName , "No Value found "  + stepName + " ." + startTest.addScreenCapture(getScreenshot()));
					}
				}
				
				catch(Exception e){
					startTest.log(LogStatus.INFO, stepName , stepName +": "+"Case Id not found"  + " ." + startTest.addScreenCapture(getScreenshot()));
					fd.close();
					fd.switchTo().window(parent);
					throw new ElementNotFoundException("Record Not Created");
				}


				break;

			case "wait":
				Thread.sleep(10000);
				break;

				
			case "verifydataFrom_WebTable":
				String flag1 = genericmethods.verifydataFrom_WebTable(xPath, testdata, fd);
				if (flag1!=null){
					cv_contains(flag1, testdata, stepName);
				}
				else{
					startTest.log(LogStatus.FAIL, stepName , "No Value found "  + stepName + " ." + startTest.addScreenCapture(getScreenshot()));
				}
				break;

//Verify sync to GitHub

			}

		}

	}

}