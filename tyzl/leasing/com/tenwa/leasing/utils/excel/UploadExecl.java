package com.tenwa.leasing.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.leasing.utils.StrTools;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 21, 2011
 * @desc (读取EXECL的工具类,返回二维数组格式)
 */
public class UploadExecl {

	private HSSFWorkbook wb = null;
	private HSSFSheet templateSheet = null;
	private HSSFRow templateRow = null;
	private HSSFCell cell = null;
	private String[][] obj = null;
	private String[] rowName = null;
	private boolean flag = false;
	private String errMsg = "";
	
	public UploadExecl() {
		
	}

	public void setExecl(String fileName) throws Exception {
		flag = true;
		if (fileName != null) {
			File file = new File(StrTools.fileNameCheck(fileName));
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				wb = new HSSFWorkbook(fis);
			
			templateSheet = wb.getSheetAt(0);

			int iLastRow = templateSheet.getPhysicalNumberOfRows();
			int iLastCol = templateSheet.getRow(0).getPhysicalNumberOfCells();
			obj = new String[iLastRow - 1][iLastCol];
			rowName = new String[iLastCol];
			HSSFRow firstRow = templateSheet.getRow(0);

			for (int i = 0; i < iLastCol; i++) {
				rowName[i] = firstRow.getCell(i).getStringCellValue();
			}
			for (int j = 1; j < iLastRow; j++) {
				templateRow = templateSheet.getRow(j);
				for (int k = 0; k < iLastCol; k++) {

					cell = templateRow.getCell(k);
					if (cell != null) {

						String strValue = "";
						int iType = cell.getCellType();
						if (iType == HSSFCell.CELL_TYPE_STRING) {
							strValue = cell.getStringCellValue();
						} else if (iType == HSSFCell.CELL_TYPE_NUMERIC) {
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								strValue = dateToString(cell.getDateCellValue(), "yyyy-MM-dd HH:mm");
							} else {
								strValue = String.valueOf(cell.getNumericCellValue());
							}
						} else if (iType == HSSFCell.CELL_TYPE_FORMULA) {
							strValue = String.valueOf(cell.getCellFormula());
						} else if (iType == HSSFCell.CELL_TYPE_BOOLEAN) {
							strValue = String.valueOf(cell.getBooleanCellValue());
						} else if (iType == HSSFCell.CELL_TYPE_BLANK) {
							strValue = String.valueOf("");
						} else if (iType == HSSFCell.CELL_TYPE_ERROR) {
							strValue = "";
						} else {
							flag = false;
							errMsg = "第" + (j) + "行，第" + (k + 1) + "列";
						}
						obj[j - 1][k] = strValue;
					} else {
						obj[j - 1][k] = "";
					}
				}
			}} catch (Exception e) {
			   throw new	BusinessException(e.getMessage());
			}finally{
				if(null!=fis){FileUtil.safeCloseInputStream(fis);}
			}
		}
			
	}

	public String dateToString(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		String str = format.format(date);
		return str;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public boolean getFlag() {
		return flag;
	}

	public String getObjectValue(int row, int col) {
		return obj[row][col].toString();
	}

	public String[][] getObject() {
		return obj;
	}

	public void deleteFile(String fileName)  throws Exception{
		File file = new File(StrTools.fileNameCheck(fileName));
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			}
		}
	}

	public String getValue(int iRow, String varName) {
		int iFlag = -1;
		for (int i = 0; i < rowName.length; i++) {
			if (varName.equals(rowName[i])) {
				iFlag = i;
				break;
			}
		}
		if (iFlag != -1) {
			return obj[iRow][iFlag].toString();
		} else {
			return null;
		}
	}
}
