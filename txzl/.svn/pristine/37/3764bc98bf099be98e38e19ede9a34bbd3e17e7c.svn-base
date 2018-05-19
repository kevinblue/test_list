package com.tenwa.leasing.serviceImpl.fileTemplate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
public class ExcelOperater    
{   
    public static String[] getExcelValue(String fileadress,String fileindex,String projid,String flowId){   
        jxl.Workbook readwb = null;  
        FileInputStream fis;
        GetExcelValues getexcelvalues =new GetExcelValues();
        try{   
            fis = new FileInputStream(fileadress);
		     HSSFWorkbook wb;
			wb = new HSSFWorkbook(fis);
			HSSFSheet sheet = wb.getSheetAt(4);  
			FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();  
			CellReference cellReference = new CellReference("E26");   
			Row row = sheet.getRow(cellReference.getRow());  
			org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellReference.getCol());   
			CellValue cellValue = evaluator.evaluate(cell); 
			String startyear =getexcelvalues.getCellValue(cellValue).toString();      
            String  jsonBankLoansStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"1"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            String  jsonFinanceLeaseStr = "{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"2"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            String  reserveRatioStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"yearnum"+'"'+":"+'"'+"13"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+ ","+'"'+"startyear"+'"'+":"+'"'+startyear+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            for(int i=5;i<=24;i++){
            	String element="";
            	CellReference cellReference1 = new CellReference("E"+i);   
    			Row row1 = sheet.getRow(cellReference1.getRow());  
    			Cell cell1 = row1.getCell(cellReference.getCol());   
    			CellValue cellValue1 = evaluator.evaluate(cell1); 
    			double ee = cellValue1.getNumberValue();
    			String cellvalue =getexcelvalues.getCellValue(cellValue1).toString(); 
                element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cellvalue.trim()+'"';
            	jsonBankLoansStr += element;
            }
           for(int i=5;i<=24;i++){
            	String element="";
            	CellReference cellReference1 = new CellReference("J"+i);   
    			Row row1 = sheet.getRow(cellReference1.getRow());  
    			org.apache.poi.ss.usermodel.Cell cell1 = row1.getCell(cellReference.getCol());   
    			CellValue cellValue1 = evaluator.evaluate(cell1); 
    			String cellvalue =getexcelvalues.getCellValue(cellValue1).toString();
            	element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cellvalue.trim()+'"';
            	jsonFinanceLeaseStr += element;
            }
            for(int i=4;i<=17;i++){
            	String[] letter = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V"};
            	String element="";
            	CellReference cellReference1 = new CellReference(letter[i]+"27");   
    			Row row1 = sheet.getRow(cellReference1.getRow());  
    			org.apache.poi.ss.usermodel.Cell cell1 = row1.getCell(cellReference.getCol());   
    			CellValue cellValue1 = evaluator.evaluate(cell1); 
    			String cellvalue =getexcelvalues.getCellValue(cellValue1).toString();
            	if(i==17){
            		element = ","+'"'+"dscr"+'"'+":"+'"'+cellvalue.trim()+'"';
            		reserveRatioStr += element;
            	}else{
            		element = ","+'"'+"year"+(i-3)+'"'+":"+'"'+cellvalue.trim()+'"';
            		System.out.println("偿债备付率=================="+cellvalue.trim());
            		reserveRatioStr += element;
            	}
            		
            }
           
            jsonBankLoansStr+="}";
            jsonFinanceLeaseStr+="}";
            reserveRatioStr+="}";
            String[] jesonArray = new String[]{jsonBankLoansStr,jsonFinanceLeaseStr,reserveRatioStr};
          System.out.println("银行=================="+jsonBankLoansStr);
          System.out.println("租赁=================="+jsonFinanceLeaseStr);
          System.out.println("偿债备付率=================="+reserveRatioStr);
          return jesonArray;
        } catch (Exception e) {   
            e.printStackTrace();   
          return null;
        } finally {   
        }   
    } 
    
   /* public static void main(String[] args) {
    	getExcelValue("E:/testfile/租赁系统-经评模型模板(全年标准-标准).xls","1","11111111111111111111","22222");
    	//"E:/testfile/租赁系统-经评模型模板(全年标准-标准).xls"
	}*/
   
}   