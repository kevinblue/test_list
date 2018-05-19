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


/**
 * 
 * 读取首列是标记位的excel数据
 * 
 * @author Administrator
 * 
 */
public class ExcelReader {

	private static HSSFDataFormatter formatter = new HSSFDataFormatter();

	public static List<Map<String, String>> readExcelDatas(InputStream fis,ExcelVersionEnum excelVersionEnum) throws IOException {
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
			Sheet sheet1 = wb.getSheetAt(0);
			Row row = sheet1.getRow(0);

			Map<String, String> fieldMapping = new HashMap<String, String>();
			Sheet sheet2 = wb.getSheetAt(1);
			int configRowsNum = sheet2.getLastRowNum();
			for (int i = 0; i <= configRowsNum; i++) {
				String key = sheet2.getRow(i).getCell(0).getStringCellValue();
				String value = sheet2.getRow(i).getCell(1).getStringCellValue();
				fieldMapping.put(key, value);
			}

			int rowNum = sheet1.getLastRowNum();
			int colNum = 6;//row.getPhysicalNumberOfCells();//定死取前六行

			Map<String, String> titles = new HashMap<String, String>();
			for (int i = 0; i < colNum; i++) {
				titles.put(String.valueOf(i), getCellFormatValue(row.getCell(i)));
			}

			for (int i = 1; i <= rowNum; i++) {
				Map<String, String> content = new HashMap<String, String>();
				row = sheet1.getRow(i);
				int j = 0;
				Boolean flag = false;
				while (j < colNum) {
					String value = getCellFormatValue(row.getCell(j));
					if(value != null && value.trim().length() > 0){
						if(!flag){
							flag = true;
						}
					}
					String title = titles.get(String.valueOf(j));//中文标题KEY
					String field = fieldMapping.get(title);
					title = field == null ? title : field;//转换成英文的KEY
					content.put(title, value);
					j++;
				}
				if(flag){
					excelData.add(content);
				}
			}
		}
		return excelData;
	}

	public static List<Map<String, String>> readExcelDatas(File file) throws IOException {
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
						cellvalue = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
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
