package com.tenwa.leasing.serviceImpl.fileTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

public class GetExcelValues {

	/*public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		getExcelValue();
	}
	
	public static void getExcelValue() throws IOException{ 
		FileInputStream fis;
		try {
			fis = new FileInputStream("D:/system_uploads/wordFiles/create_report_list/2017/04/14/0dd686ae-62cd-49d0-8edd-a4d423bfad25.xls");
			Workbook wb;
				wb = new HSSFWorkbook(fis);
			Sheet sheet = wb.getSheetAt(4);  
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();  
			// suppose your formula is in B3  
			CellReference cellReference = new CellReference("14");   
			Row row = sheet.getRow(cellReference.getRow());  
			Cell cell = row.getCell(cellReference.getCol());   
			  
			CellValue cellValue = evaluator.evaluate(cell);  
			  String ee = getCellValue(cellValue).toString();
			
			
			
			
			}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
		}
	*/
	
	public  Object getCellValue(CellValue cellValue){
		switch (cellValue.getCellType()) {  
	    case Cell.CELL_TYPE_BOOLEAN:  
	        System.out.println(cellValue.getBooleanValue());  
	        return cellValue.getBooleanValue();  
	    case Cell.CELL_TYPE_NUMERIC:  
	        System.out.println(cellValue.getNumberValue());  
	        return cellValue.getNumberValue();  
	    case Cell.CELL_TYPE_STRING:  
	        System.out.println(cellValue.getStringValue());  
	        return cellValue.getStringValue();  
	    case Cell.CELL_TYPE_BLANK:  
	        return null;  
	    case Cell.CELL_TYPE_ERROR:  
	        return null;  
	  
	    // CELL_TYPE_FORMULA will never happen  
	    case Cell.CELL_TYPE_FORMULA:   
	        return null;  
        } 
		return null;
	}
	}

