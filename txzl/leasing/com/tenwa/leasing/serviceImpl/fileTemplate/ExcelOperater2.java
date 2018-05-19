package com.tenwa.leasing.serviceImpl.fileTemplate;
import java.io.FileInputStream;   
import java.io.InputStream;   
import jxl.Cell;   
import jxl.Sheet;   
import jxl.Workbook;   
public class ExcelOperater2    
{   
    public static String[] getExcelValue2(String fileadress,String fileindex,String projid,String flowId){   
        jxl.Workbook readwb = null;   
        try{   
            InputStream instream = new FileInputStream(fileadress);   //"E:/testfile/租赁系统-经评模型模板 - 副本.xls"
            readwb = Workbook.getWorkbook(instream); 
            Sheet readsheet = readwb.getSheet(4); //指定sheet 
            String  jsonBankLoansStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"1"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            String  jsonFinanceLeaseStr = "{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"tableTitle"+'"'+":"+'"'+"2"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            String startyear = readsheet.getCell(4,25).getContents().trim();
            String  reserveRatioStr ="{"+'"'+"fileOrder"+'"'+":"+'"'+fileindex+'"'+","+'"'+"yearnum"+'"'+":"+'"'+"13"+'"'+","+'"'+"projId"+'"'+":"+'"'+projid+'"'+","+'"'+"flowId"+'"'+":"+'"'+flowId+'"'+ ","+'"'+"startyear"+'"'+":"+'"'+startyear+'"'+","+'"'+"status"+'"'+":"+'"'+"1"+'"';
            for(int i=4;i<=23;i++){
            	String element="";
            	Cell cell = readsheet.getCell(4,i); 
            		 element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cell.getContents().trim()+'"';
            	jsonBankLoansStr += element;
            }
            for(int i=4;i<=23;i++){
            	String element="";
            	Cell cell = readsheet.getCell(9,i); 
            		element = ","+'"'+"subject"+(i-3)+'"'+":"+'"'+cell.getContents().trim()+'"';
            	jsonFinanceLeaseStr += element;
            }
            for(int i=4;i<=17;i++){
            	String element="";
            	Cell cell = readsheet.getCell(i,26); 
            	if(i==17){
            		element = ","+'"'+"dscr"+'"'+":"+'"'+cell.getContents().trim()+'"';
            		reserveRatioStr += element;
            	}else{
            		element = ","+'"'+"year"+(i-3)+'"'+":"+'"'+cell.getContents().trim()+'"';
            		System.out.println("偿债备付率=================="+cell.getContents().trim());
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
            readwb.close();   
        }   
    } 
    
   /* public static void main(String[] args) {
    	getExcelValue("E:/testfile/租赁系统-经评模型模板 - 副本.xls","1","11111111111111111111","22222");
	}*/
    
}   