package Genericlibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRW {
	XSSFWorkbook wb;
	FileInputStream fis;
//	 Initilize excel
	public ExcelRW(String fpath) throws Exception{
	 fis = new FileInputStream(fpath);
//		create a workbook
		wb = new XSSFWorkbook(fis);
		
	}
	
//	get row count
	public int rowcount(String sheetname){
		XSSFSheet sheet = wb.getSheet(sheetname);
		return sheet.getLastRowNum();
		
	}
	
//	get column count
	public int Colcount(String sheetname){
		XSSFSheet sheet = wb.getSheet(sheetname);
		return sheet.getRow(0).getLastCellNum();
	}
	
//	Read cell
	public String readcellval(String sheetname,int row,int col){
		XSSFSheet sheet = wb.getSheet(sheetname);
		XSSFCell cell = sheet.getRow(row).getCell(col);
		String celltext = null;
		if(cell.getCellType()==Cell.CELL_TYPE_STRING){
			
			 celltext = cell.getStringCellValue();
		}else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC){
			celltext = String.valueOf(cell.getNumericCellValue());
			
		}else if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
			
			celltext="";
		}
		
		return celltext;
		
		
	}
	
		
//	write to cell
	public void writecell(String sheetname,int row,int col,String val){
		XSSFSheet sheet = wb.getSheet(sheetname);
		sheet.getRow(row).getCell(col).setCellValue(val);	
		
		
	}
	
//	Save and close
	public void saveandclose(String fpath) throws Exception{
		
	FileOutputStream fos = new FileOutputStream(fpath);
		
//		write and save to excel
		wb.write(fos);
		
//		Close the streams
		fos.close();
		fis.close();
		
		
	}
	
	
	
	
	
	
	

}
