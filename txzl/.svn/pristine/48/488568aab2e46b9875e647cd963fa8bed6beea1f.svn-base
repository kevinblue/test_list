package com.tenwa.leasing.serviceImpl.fileTemplate;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;

import java.io.*;

public class readExcel {
	
   public  String[] getExcelValue(String fileadress,String fileindex,String projid,String flowId,String fileName) throws IOException {
       HSSFWorkbook hw = new HSSFWorkbook(new FileInputStream(fileadress));
       HSSFSheet hsheet = hw.getSheet("输出结果");
       HSSFRow row = hsheet.getRow(25);//行
	   HSSFCell cell = row.getCell(4);//列
	   String startyear = this.getCellValue(cell);
       String  jsonBankLoansStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"1"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"'+","+'"'+"fileName"+'"'+":"+'"'+fileName+'"';
       String  jsonFinanceLeaseStr = "{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"2"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"'+","+'"'+"fileName"+'"'+":"+'"'+fileName+'"';
       String  reserveRatioStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"yearnum"+'"'+":"+'"'+"13"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+ ","+'"'+"startyear"+'"'+":"+'"'+startyear+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"'+","+'"'+"fileName"+'"'+":"+'"'+fileName+'"';
       for (int i = 4; i <= 23; i++) {
    	   String element="";
    	   HSSFRow hrow = hsheet.getRow(i);//行
    	   HSSFCell hcell = hrow.getCell(4);//列
    	   String cellValue = null;
    	   try {
    		   cellValue = this.getCellValue(hcell);
		   } catch (Exception e) {
			   cellValue ="0.00";
			   element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cellValue+'"';
	           jsonBankLoansStr += element;
	           e.printStackTrace();
	           continue;
		   }
    	   if(cellValue==null){
    		   cellValue="0.00";
    	   };
    	  /* if(i==14||i==17||i==20){
    		   cellValue="";
    		   element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cellValue+'"';
    		   jsonBankLoansStr += element;
    		   continue;
    	   };*/
    	   float   cellValueNum   =   (float)(Math.round(Float.valueOf(cellValue.trim())*100))/100;
           element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+String.valueOf(cellValueNum)+'"';
           jsonBankLoansStr += element;
       }
       System.out.println("银行=================="+jsonBankLoansStr);
       for (int j = 4; j <= 23; j++) {
    	   String element="";
    	   HSSFRow hrow = hsheet.getRow(j);//行
    	   HSSFCell hcell = hrow.getCell(9);//列
    	   String cellValue = null;
    	   try {
    		   cellValue = this.getCellValue(hcell);
		   } catch (Exception e) {
			   cellValue ="0.00";
			   element = ","+'"'+"subject"+(j-3)+'"'+":"+'"'+cellValue+'"';
	           jsonFinanceLeaseStr += element;
	           e.printStackTrace();
	           continue;
		   }
    	   if(cellValue==null){
    		   cellValue="0.00";
    	   };
    	  /* if(j==14||j==17||j==20){
    		   cellValue="";
    		   element = ","+'"'+"subject"+(j-3)+'"'+":"+'"'+cellValue+'"';
	           jsonFinanceLeaseStr += element;
    		   continue;
    	   };*/
    	   float   cellValueNum   =   (float)(Math.round(Float.valueOf(cellValue.trim())*100))/100;
           element = ","+'"'+"subject"+(j-3)+'"'+":"+'"'+String.valueOf(cellValueNum)+'"';
           jsonFinanceLeaseStr += element;
       }
      System.out.println("租赁=================="+jsonFinanceLeaseStr);
       for (int k = 4; k < 20; k++) {
    	   String element="";
    	   HSSFRow hrow = hsheet.getRow(26);//行
    	   HSSFCell hcell = hrow.getCell(k);//列
    	   String cellValue = null;
    	   try {
    		   cellValue = this.getCellValue(hcell);
			} catch (Exception e) {
				cellValue ="0.00";
				if(k==19){
		       		element = ","+'"'+"dscr"+'"'+":"+'"'+cellValue.trim()+'"';
		       		reserveRatioStr += element;
			       	}else{
			       		element = ","+'"'+"year"+(k-3)+'"'+":"+'"'+cellValue.trim()+'"';
			       		reserveRatioStr += element;
			    }
				e.printStackTrace();
				continue;
			}
    	   if(cellValue==null){
    		   cellValue="0.00";
    	   }
           if(k==19){
        	   float   cellValueNum  =  (float)(Math.round(Float.valueOf(cellValue.trim())*10000))/10000;
       		element = ","+'"'+"dscr"+'"'+":"+'"'+String.valueOf(cellValueNum)+'"';
       		reserveRatioStr += element;
	       	}else{
	       		float   cellValueNum  =  (float)(Math.round(Float.valueOf(cellValue.trim())*100))/100;
	       		element = ","+'"'+"year"+(k-3)+'"'+":"+'"'+String.valueOf(cellValueNum)+'"';
	       		reserveRatioStr += element;
	       	}
       }
       System.out.println("偿债备付率=================="+reserveRatioStr);
       jsonBankLoansStr+="}";
       jsonFinanceLeaseStr+="}";
       reserveRatioStr+="}";
       String[] jesonArray = new String[]{jsonBankLoansStr,jsonFinanceLeaseStr,reserveRatioStr};
       return jesonArray;
   }

   public String getCellValue(HSSFCell cell) {
       String value = null;
       if (cell != null) {
           switch (cell.getCellType()) {
           case HSSFCell.CELL_TYPE_FORMULA:
               try {
                   value = String.valueOf(cell.getNumericCellValue());
               } catch (IllegalStateException e) {
                   value = String.valueOf(cell.getRichStringCellValue());
               }
               break;
           case HSSFCell.CELL_TYPE_NUMERIC:
               value = String.valueOf(cell.getNumericCellValue());
               break;
           case HSSFCell.CELL_TYPE_STRING:
               value = String.valueOf(cell.getRichStringCellValue());
               break;
           }
       }
       return value;
   }

  /* public static void main(String[] args) {
       try {
    	   readExcel fts = new readExcel();
           fts.getExcelValue("D:/5d2192e8-1a75-4b49-bae3-e06f39a8857f.xls","2","8a89808b5b6a0a07015b6a21114d001b","37558");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }*/

}