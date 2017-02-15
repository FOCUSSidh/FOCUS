package Dataproviders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import Genericlibrary.ExcelRW;
import Genericlibrary.Utility;

public class dp_login {
	
	
	@DataProvider(name="invalidLogin")
	public static Iterator<Object[]> dp_invalidLogin() throws Exception{

		return Utility.dp_commonlogic("Login","InvalidLogin");
		
	}
	
//	DP for valid login
	
	@DataProvider(name="validLogin")
	public static Iterator<Object[]> dp_validLogin() throws Exception{
		
		return Utility.dp_commonlogic("Login", "ValidLogin");
		
		
	}
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	