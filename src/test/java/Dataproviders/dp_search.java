package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_search {
	
//	dp for valid search
	@DataProvider(name="validSearch")
	public static Iterator<Object[]> validSearch() throws Exception{
		
		return Utility.dp_commonlogic("Search", "ValidSearch");
			
	}
	
	
//	dp for invalid search
	@DataProvider(name="invalidSearch")
	public static Iterator<Object[]> invalidSearch() throws Exception{
				return Utility.dp_commonlogic("Search", "InvalidSearch");
			
	}
}
