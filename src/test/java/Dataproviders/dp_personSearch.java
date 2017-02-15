package Dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Genericlibrary.Utility;

public class dp_personSearch {
	
	@DataProvider(name="personSearch")
	public static Iterator<Object[]> dp_invalidLogin() throws Exception{

		return Utility.dp_commonlogic("PersonSearch","ValidSearch");
		
	}

}
