package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_genericLibrary {
	
	
	@DataProvider(name="genericLibrary")
	public static Iterator<Object[]> dp_invalidLogin() throws Exception{

		return Utility.dp_testdata("ExecutionManager");
		
	}
	

}
