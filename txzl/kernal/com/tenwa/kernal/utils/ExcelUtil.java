package com.tenwa.kernal.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.EnumUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.tenwa.business.model.ExcelVersionEnum;

public class ExcelUtil {
	public enum STYLE {
		ALL, TITLE, TEXT, NUMBER, DATE,REPORT
	}

	private Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

	private Workbook workbook = null;
	private int currentRowIndex = 0;
	private Sheet currentSheet = null;

	private int currentColumnIndex = 0;

	public ExcelUtil() {
		if (this.getWorkbook() == null) {
			setWorkbook(PoiExcelUtil.createExcelWorkbook(ExcelVersionEnum.VERSION2007));
		}
		enableAllDefaultStyle();
	}
	
	public int getCurrentRowIndex(){
		return this.currentRowIndex;
	}
	
	public int getCurrentColumnIndex(){
		return this.currentColumnIndex;
	}

	public ExcelUtil(Workbook workbook) {
		if (workbook != null)
			this.setWorkbook(workbook);
		enableAllDefaultStyle();
	}

	public void addSheet(String sheetName) {
		currentSheet = PoiExcelUtil.createExcelSheet(getWorkbook());
		currentSheet.setColumnWidth(0, 256*255);
		currentRowIndex = 0;
		currentColumnIndex = 0;
		if (sheetName != null && !"".equals(sheetName)) {
			if(getWorkbook().getSheet(sheetName) != null){
				sheetName += ("_" + getWorkbook().getSheetIndex(currentSheet));
			}
			getWorkbook().setSheetName(getWorkbook().getSheetIndex(currentSheet), sheetName);
		}
	}
	
	public void merge(int startRow,int startCol,int endRow,int endCol){
		
		currentSheet.addMergedRegion(new CellRangeAddress(startRow,endRow,startCol,endCol));
	}

	private Cell addCell(int rowIndex, int columnIndex, CellStyle style) {
		Row row = currentSheet.getRow(rowIndex);
		if (row == null) {
			row = PoiExcelUtil.createExcelRow(currentSheet, rowIndex);
		}
		Cell cell = row.getCell(columnIndex);
		if (cell == null) {
			cell = PoiExcelUtil.createExcelCell(row, columnIndex);
		}
		cell.setCellStyle(style);
		return cell;
	}

	public void addCell(int rowIndex, int columnIndex, Object cellValue, String styleName) {
		CellStyle style = styles.get(STYLE.TEXT);
		if (styleName != null && styles.containsKey(styleName)) {
			STYLE tempStyle = EnumUtils.getEnum(STYLE.class, styleName);
			if (tempStyle != null) {
				addCell(rowIndex, columnIndex, cellValue, tempStyle);
				return;
			}
			style = styles.get(styleName);
		}

		Class<?> clazz = cellValue.getClass();
		if (ClassUtils.isAssignable(clazz, Number.class)) {
			createNumberCell(rowIndex, columnIndex, cellValue, style);
		} else if (ClassUtils.isAssignable(clazz, java.util.Date.class) || ClassUtils.isAssignable(clazz, Calendar.class)) {
			createDateCell(rowIndex, columnIndex, cellValue, style);
		} else {
			createTextCell(rowIndex, columnIndex, cellValue, style);
		}
	}

	public void addCell(int rowIndex, int columnIndex, Object cellValue, STYLE style) {
		if (style == null)
			style = STYLE.TEXT;

		switch (style) {
		case TEXT:
		case TITLE:
		default:
			createTextCell(rowIndex, columnIndex, cellValue, styles.get(style.name()));
			break;
		case NUMBER:
			createNumberCell(rowIndex, columnIndex, cellValue, styles.get(style.name()));
			break;
		case DATE:
			createDateCell(rowIndex, columnIndex, cellValue, styles.get(style.name()));
			break;
		}
	}

	public void addCell(Object cellValue, STYLE style) {
		addCell(currentRowIndex, currentColumnIndex++, cellValue, style);
	}

	public void addCell(Object cellValue, STYLE style, String pattern) {
		CellStyle excelStyle = styles.get(style.name());
		short orgFormat = excelStyle.getDataFormat();
		if (pattern != null && !"".equals(pattern)) {			
			excelStyle.setDataFormat(createDataFormat(pattern));
		}
		addCell(currentRowIndex, currentColumnIndex++, cellValue, style);			
		excelStyle.setDataFormat(orgFormat);			
	}

	private Cell createDateCell(int rowIndex, int columnIndex, Object cellValue, CellStyle style) {
		String value = StringUtil.nullToString(cellValue);
		SimpleDateFormat format = new SimpleDateFormat();

		format.applyPattern("yyyy-MM-dd");

		try {
			value = format.format(format.parse(value));
		} catch (ParseException e) {
			value = cellValue.toString();
		}
		Cell cell = addCell(rowIndex, columnIndex, style);
		cell.setCellValue(value);
		return cell;
	}

	private Cell createNumberCell(int rowIndex, int columnIndex, Object cellValue, CellStyle style) {

		String value = StringUtil.nullToString(cellValue);
		double doubleValue = 0.0;
		Cell cell = addCell(rowIndex, columnIndex, style);
		cell.setCellStyle(style);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		if (value.matches("^([-]){0,1}([0-9]){1,}([.]){0,1}([0-9]){0,}$")) {
			doubleValue = Double.parseDouble(value);
			cell.setCellValue(doubleValue);
		} else {
			cell.setCellValue(value);
		}
		return cell;

	}

	private Cell createTextCell(int rowIndex, int columnIndex, Object cellValue, CellStyle style) {
		String value = StringUtil.nullToString(cellValue);
		Cell cell = addCell(rowIndex, columnIndex, style);
		cell.setCellValue(value);
		return cell;
	}

	public void addNewRow() {
		Row row = currentSheet.getRow(currentRowIndex + 1);
		if (row == null) {
			row = PoiExcelUtil.createExcelRow(currentSheet, currentRowIndex + 1);
		}
		currentRowIndex++;
		currentColumnIndex = 0;
	}

	public short createDataFormat(String pattern) {
		short formatIndex = HSSFDataFormat.getBuiltinFormat(pattern);
		if (formatIndex == -1) {
			DataFormat format = getWorkbook().createDataFormat();
			formatIndex = format.getFormat(pattern);
		}
		return formatIndex;
	}

	public CellStyle createCustomizeStyle(String styleName) {
		CellStyle style = null;
		if (styles.containsKey(styleName))
			style = styles.get(styleName);
		else {
			style = PoiExcelUtil.createCellStyle(getWorkbook());
			//setStyleName(style, styleName);
			styles.put(styleName, style);
		}
		return style;

	}

	private void enableReportStyle(){
		if(!styles.containsKey(STYLE.REPORT.name())){
			CellStyle style = createCustomizeStyle(STYLE.REPORT.name());
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setWrapText(false);
			Font f = PoiExcelUtil.createFont(this.workbook);
			f.setFontHeightInPoints((short)18);
			style.setFont(f);
		}
	}
	private void enableTextStyle() {
		if (!styles.containsKey(STYLE.TEXT.name())) {
			CellStyle style = PoiExcelUtil.getExportContentStyle(getWorkbook());
			//setStyleName(style, STYLE.TEXT.name());
			styles.put(STYLE.TEXT.name(), style);
		}
	}

	private void enableNumberStyle() {
		if (!styles.containsKey(STYLE.NUMBER.name())) {
			CellStyle style = PoiExcelUtil.getExportContentNumberStyle(getWorkbook());
			//setStyleName(style, STYLE.NUMBER.name());
			style.setDataFormat(createDataFormat("#.##"));
			styles.put(STYLE.NUMBER.name(), style);
		}
	}

	private void enableDateStyle() {
		if (!styles.containsKey(STYLE.DATE.name())) {
			CellStyle style = PoiExcelUtil.getExportContentDateStyle(getWorkbook());
			style.setDataFormat(createDataFormat("yyyy-mm-dd"));
			//setStyleName(style, STYLE.DATE.name());
			styles.put(STYLE.DATE.name(), style);
		}
	}

	private void enableTitleStyle() {
		if (!styles.containsKey(STYLE.TITLE.name())) {
			CellStyle style = PoiExcelUtil.getExportContentTitleStyle(getWorkbook());
			//setStyleName(style, STYLE.TITLE.name());
			styles.put(STYLE.TITLE.name(), style);
		}
	}

	public void autoSizeColumn(int startCol,int endCol){
		for(int i = startCol; i <= endCol; i++){
			this.currentSheet.autoSizeColumn(i, true);
		}
	}
	
	public void enableColumnAutoSize(){
		
		this.currentSheet.autoSizeColumn(this.currentColumnIndex, true);
	}

	private void enableAllDefaultStyle() {
		this.enableTextStyle();
		this.enableNumberStyle();
		this.enableDateStyle();
		this.enableTitleStyle();
		this.enableReportStyle();
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

}
