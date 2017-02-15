package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_CaseCreation {
	
	@DataProvider(name="validCaseCreation")
	public static Iterator<Object[]> dp_invalidLogin() throws Exception{

		return Utility.dp_commonlogic("CaseCreation","CaseCreation");
		
	}

}
