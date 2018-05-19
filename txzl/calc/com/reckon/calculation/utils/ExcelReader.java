package com.reckon.calculation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;

import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.exception.BusinessException;

/**
 * 
 * 读取首列是标记位的excel数据
 * 
 * @author Administrator
 * 
 */
public class ExcelReader {

	private static HSSFDataFormatter formatter = new HSSFDataFormatter();

	public static List<Map<String, String>> readExcelDatas(InputStream fis,ExcelVersionEnum excelVersionEnum) throws Exception {
		List<Map<String, String>> excelData = new ArrayList<Map<String, String>>();
		if (fis != null) {
			Workbook wb = null;
			switch (excelVersionEnum) {
			case VERSION2007:
				wb = new XSSFWorkbook(fis);
				break;
			default:
				wb = new HSSFWorkbook(fis);
				break;
			}
			Sheet sheet=null;
			int sheetnum = wb.getNumberOfSheets();
			for(int i=0;i<sheetnum;i++){
				if("租金计划".equals(wb.getSheetAt(i).getSheetName())){
					sheet=wb.getSheetAt(i);
				}
			}
			Map<String, String> fieldMapping = new HashMap<String, String>();
			fieldMapping.put("rentlist", "期项");
			fieldMapping.put("plandate", "计划日期");
			fieldMapping.put("rent", "租金");
			fieldMapping.put("businesscorpus", "本金");
			fieldMapping.put("businessinterest", "利息");
			fieldMapping.put("yearrate", "年利率");
			if(sheet==null){
				throw new BusinessException("Excle表中【租金计划】和【"+wb.getSheetAt(0).getSheetName()+"】不一致！");
			}
			int totalRow = sheet.getLastRowNum();
			int startRow=0;
			int startCol=0;
			Row row = null;
			ot:for(int i=0;i<=totalRow;i++){
				if(null!=sheet.getRow(i)){
					for(int m=0;m<=sheet.getRow(i).getLastCellNum();m++){
						String str=getCellFormatValue(sheet.getRow(i).getCell(m));
						if("期项".equals(str)){
							row=sheet.getRow(i);
							startRow=i;
							break ot;
						}
					}
				}
			}
			for(int i=0;i<=row.getLastCellNum();i++){
				if(null!=row.getCell(i)){
					String str=getCellFormatValue(row.getCell(i));
					if("期项".equals(str)){
						startCol=i;
						break;
					}
				}
			}
			Map<Integer, String> fieldMappingTwo = new HashMap<Integer, String>();
			for(int j=startCol;j<=row.getLastCellNum();j++){
				String str=getCellFormatValue(row.getCell(j));
				for(String key:fieldMapping.keySet()){
					if(fieldMapping.get(key).equals(str)){
						fieldMappingTwo.put(j,key);
					}
				}
			}

			oo:for (int i = startRow+1; i <= totalRow; i++) {
				Row rowIndex=sheet.getRow(i);
				Map<String, String> content = new HashMap<String, String>();
				int j = startCol;
				Boolean flag = false;
				while (j < row.getLastCellNum()) {
					if(null==rowIndex.getCell(j)||getCellFormatValue(rowIndex.getCell(j)).trim().length()==0||"租金".equals(getCellFormatValue(rowIndex.getCell(j)).trim())){
						break oo;
					}
					String value = getCellFormatValue(rowIndex.getCell(j));
					if(value != null && value.trim().length() > 0){
						if(!flag){
							flag = true;
						}
					}
					String field = fieldMappingTwo.get(j);
					content.put(field, value);
					j++;
				}
				if(flag){
					excelData.add(content);
				}
			}
		}
		return excelData;
	}

	public static List<Map<String, String>> readExcelDatas(File file) throws Exception {
		List<Map<String, String>> excelData = null;
		if (file != null) {
			FileInputStream fis = new FileInputStream(file);
			String suffix =  file.getName().substring(file.getName().lastIndexOf(".")+1);
			excelData = readExcelDatas(fis,ExcelVersionEnum.valueOf(suffix));
			fis.close();
		}
		return excelData;
	}

	public static String fomartExcelDatasToString(List<Map<String, String>> datas) throws IOException {
		JSONArray ja = new JSONArray();
		if (datas != null) {
			for (Map<String, String> row : datas) {
				ja.put(row);
			}
		}
		return ja.toString();
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC: {// 数字
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						cellvalue = new SimpleDateFormat("yyyy年MM月dd日").format(cell.getDateCellValue());
					} else {
						cellvalue = formatter.formatCellValue(cell).trim();
						cellvalue = cellvalue.replaceAll("[,|¥|€|(US$)|$|\\s+|\\*]", "");
					}
					break;
				}
				case HSSFCell.CELL_TYPE_FORMULA: {// 计算公式
					switch (cell.getCachedFormulaResultType()) {
						case HSSFCell.CELL_TYPE_STRING: {
							RichTextString str = cell.getRichStringCellValue();
							if (str != null) {
								cellvalue = str.toString();
							}
							break;
						}
						case HSSFCell.CELL_TYPE_NUMERIC: {
							CellStyle style = cell.getCellStyle();
							if (style == null) {
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									cellvalue = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
								} else {
									cellvalue = formatter.formatCellValue(cell).trim();
									cellvalue = cellvalue.replaceAll("[,|¥|€|(US$)|$|\\s+|\\*]", "");
								}
							}else if("yyyy\"年\"mm\"月\"dd\"日\";@".equals(style.getDataFormatString())){
								cellvalue=new SimpleDateFormat("yyyy年MM月dd日").format(cell.getDateCellValue());
							} else {
								cellvalue = formatter.formatRawCellContents(cell.getNumericCellValue(), style.getDataFormat(), style.getDataFormatString()).trim();
								cellvalue = cellvalue.replaceAll("[,|¥|€|(US$)|$|\\s+|\\*]", "");
							}
							break;
						}
						case HSSFCell.CELL_TYPE_BOOLEAN: {// 布尔
							cellvalue = String.valueOf(cell.getBooleanCellValue());
							break;
						}
						case HSSFCell.CELL_TYPE_ERROR: {// 错误值
							cellvalue = ErrorEval.getText(cell.getErrorCellValue());
							break;
						}
					}
					break;
				}
				case HSSFCell.CELL_TYPE_BOOLEAN: {// 布尔
					cellvalue = String.valueOf(cell.getBooleanCellValue()).trim();
					break;
				}
				case HSSFCell.CELL_TYPE_STRING: {// 文本
					cellvalue = cell.getRichStringCellValue().getString().trim();
					break;
				}
			}
		}
		return cellvalue;
	}

	public static void main(String[] args) {
		try {
			File file = new File("E:/不规则租金计划模板.xls");
			List<Map<String, String>> results = readExcelDatas(file);
			String result = fomartExcelDatasToString(results);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
