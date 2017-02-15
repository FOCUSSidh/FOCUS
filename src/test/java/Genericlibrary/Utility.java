package Genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Utility {
	

//	common method
	public static  Iterator<Object[]> dp_commonlogic(String sheetname, String scriptname) throws Exception{
	
	ExcelRW excelRW = new ExcelRW(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");
		
		int rowcount = excelRW.rowcount(sheetname);
		int colcount = excelRW.Colcount(sheetname);
				
//				list create
				List<Object[]> al = new ArrayList<Object[]>();
		for(int i = 1;i<=rowcount;i++){
			
			String flag=excelRW.readcellval(sheetname, i, 2);
			String script=excelRW.readcellval(sheetname, i, 1);
			if(flag.equals("Y") && script.equals(scriptname)){
//			map
			Map<String, String> hmap = new HashMap<String,String>();
//			object array
			Object[] x=new Object[1];
			
			for(int j=0;j<colcount;j++){
				
				String key = excelRW.readcellval(sheetname, 0, j);
				String value = excelRW.readcellval(sheetname, i, j);			
				hmap.put(key,value);
				
			}			
			
//			add object array
			x[0]=hmap;
			al.add(x);
			}
		}
		
		return al.iterator();
	
	}
	
//	get the properties value
public static 	String getval(String key) throws Exception{
	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\Config.properties");
	
	Properties prop = new Properties();
	prop.load(fis);
	
	return prop.getProperty(key);
	
	
	
}


public static  Iterator<Object[]> dp_testdata(String sheetname) throws Exception{
	
	ExcelRW excelRW = new ExcelRW(System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx");
		
		int rowcount = excelRW.rowcount(sheetname);
		int colcount = excelRW.Colcount(sheetname);
				
//				list create
				List<Object[]> al = new ArrayList<Object[]>();
		for(int i = 1;i<=rowcount;i++){
			
			String flag=excelRW.readcellval(sheetname, i, 2);
			String script=excelRW.readcellval(sheetname, i, 1);
			if(flag.equals("Y")){
//			map
			Map<String, String> hmap = new HashMap<String,String>();
//			object array
			Object[] x=new Object[1];
			
			for(int j=0;j<colcount;j++){
				
				String key = excelRW.readcellval(sheetname, 0, j);
				String value = excelRW.readcellval(sheetname, i, j);			
				hmap.put(key,value);
				
			}			
			
//			add object array
			x[0]=hmap;
			al.add(x);
			}
		}
		
		return al.iterator();
	
	}


public static 	String getxPath(String key) throws Exception{
	
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\ObjectRepository.properties");
	
	Properties prop = new Properties();
	prop.load(fis);	
	return prop.getProperty(key);
}


public static void  writeExcel(String sheetname, String testcaseId, String OrderId, String columnname, String value) throws Exception{
	
	String fpath=System.getProperty("user.dir") + "\\src\\test\\resources\\Test_Data.xlsx";
	
	ExcelRW excelRW= new ExcelRW(fpath);
		
		int rowcount = excelRW.rowcount(sheetname);
		int colcount = excelRW.Colcount(sheetname);
		int column1=0;
		int row1=0;
		
		
				
			
				
			for(int j=0;j<colcount;j++){
				
				String columnName = excelRW.readcellval(sheetname, 0, j);
				if (columnName.equals(columnname)){
					column1=j;
					break;
					}			
			  }
			
			
			for (int i=1;i<rowcount;i++){
				
				String testCaseName=excelRW.readcellval(sheetname, i, 0);
				String orderId=excelRW.readcellval(sheetname, i, 3);
				String flag=excelRW.readcellval(sheetname, i, 2);
				String script=excelRW.readcellval(sheetname, i, 1);
				if (flag.equals("Y") && testCaseName.equals(testcaseId)&& orderId.equals(OrderId)){
					row1=i;

					//excelRW.writecell(sheetname, row1, column1, value);
					
					break;
				}
				
				
			}
			

			
			//excelRW.saveandclose(fpath);
		
			
	
	}





	
	

}









