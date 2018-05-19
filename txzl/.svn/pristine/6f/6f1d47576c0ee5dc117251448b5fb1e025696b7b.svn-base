package com.tenwa.leasing.utils.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.leasing.excel.bean.ExeclBean;

/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Mar 13, 2011
 * @desc (读取EXECL的工具类,主要是通过索引单元格的读取)
 */
public class ReadExecl {

	private Workbook book = null;
	private Map<String, String> bookNames = new HashMap<String, String>();// 名称管理
	Logger log = null;

	public ReadExecl(String fileAddr) {
		log = Logger.getLogger(this.getClass());
		if (fileAddr != null) {
			try {
				if (fileAddr.endsWith("xlsx")) {
					book = PoiExcelUtil.readWorkbook(fileAddr, ExcelVersionEnum.VERSION2007);
				} else {
					book = PoiExcelUtil.readWorkbook(fileAddr, ExcelVersionEnum.VERSION2003);
				}
				for (int i = 0; i < book.getNumberOfNames(); i++) {
					bookNames.put(book.getNameAt(i).getNameName(), book.getNameAt(i).getRefersToFormula());
				}
			} catch (Exception e) {
				log.error(e);
			}
		}
	}

	public void bookNamesMove(int startRow, int endRow, int num) {
		//System.out.println(bookNames);
		if (bookNames.size() > 0) {
			for (String title_name : bookNames.keySet()) {
				if(!"_FilterDatabase".equals(title_name)){
				String rowcellReference = bookNames.get(title_name);
				ExeclBean bean = this.getCell(rowcellReference);
				int beforerownum = bean.getRow_num();
                System.out.println(title_name);
				if (beforerownum > startRow) {
					beforerownum = beforerownum + (endRow - startRow + 1) * num;
					rowcellReference = bean.getSheet_name() + "!$" + bean.getCol_name() + "$" + beforerownum;
				}
				bookNames.put(title_name, rowcellReference);
				}
			}
		}
	}
	public Map<String, String> getBookNames() {
		return bookNames;
	}

	public void setBookNames(Map<String, String> bookNames) {
		this.bookNames = bookNames;
	}

	/**
	 * 
	 * (根据命名单元格的名字返回此单元格的值)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @return 被命名单元格的值
	 */
	public String getValue(String cellName) {
		String value = "";
		String reference = "";
		if (this.checkCellName(cellName)) {
			reference = bookNames.get(cellName);
			ExeclBean execl = new ExeclBean();
			execl = this.getCell(reference);
			value = book.getSheetAt(execl.getSheet_index()).getRow(execl.getRow_num()).getCell(execl.getCol_num()).toString();
			return value;
		} else {
			return "没有找到该命名单元格!";
		}
	}

	/**
	 * 
	 * (对指定单元格赋值)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @param value
	 *            需要赋的值 只能是值,不能是公式
	 */
	public void setCellValue(String cellName, String value) {
		if (this.checkCellName(cellName)) {
			String reference = "";
			reference = bookNames.get(cellName);
			ExeclBean execl = new ExeclBean();
			execl = this.getCell(reference);
			Cell cell = book.getSheetAt(execl.getSheet_index()).getRow(execl.getRow_num()).getCell(execl.getCol_num());
			cell.setCellValue(value);
		}
	}

	/**
	 * 
	 * (对指定单元格赋值)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @param value
	 *            需要赋的值 只能是公式
	 */
	public void setCellFormula(String cellName, String formula) {
		if (this.checkCellName(cellName)) {
			String reference = "";
			reference = bookNames.get(cellName);
			ExeclBean execl = new ExeclBean();
			execl = this.getCell(reference);
			Cell cell = book.getSheetAt(execl.getSheet_index()).getRow(execl.getRow_num()).getCell(execl.getCol_num());
			cell.setCellFormula(formula);
		}
	}

	/**
	 * 
	 * (通过传入EXECL的绝对地址 如Sheet1!$A$1 获得对应的ExeclBean )
	 * 
	 * @param reference
	 *            EXECL的绝对地址
	 * @return ExeclBean
	 */
	public ExeclBean getCell(String reference) {
		String reference_t = reference;
		ExeclBean execl = new ExeclBean();
		if (this.checkReference(reference)) {// 验证绝对地址
			execl.setSheet_name(reference_t.substring(0, reference_t.indexOf("!")));
			execl.setCol_name(reference_t.substring(reference_t.indexOf("!$") + 2, reference_t.indexOf("$", reference_t.indexOf("!$") + 2)));
			execl.setRow_name(reference_t.substring(reference_t.indexOf("$", reference_t.indexOf("!$") + 2) + 1));
			execl.setSheet_index(book.getSheetIndex(execl.getSheet_name()));
		} else {
			log.error("error:reference=" + reference + ";不是个正确的execl绝对地址!");
		}
		return execl;
	}

	/**
	 * 
	 * (验证命名单元格是否存在)
	 * 
	 * @param cellName
	 * @return
	 */
	public boolean checkCellName(String cellName) {
		if (!cellName.equals("")) {
			try {
				String t = bookNames.get(cellName);
				if (t.length() > 1) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				log.error("验证命名单元格失败!");
				e.printStackTrace();
				log.error(e.toString());
				return false;
			}
		} else {
			log.info("error:请输入命名单元格名称");
			return false;
		}
	}

	/**
	 * 
	 * (验证绝对地址的正确与否)
	 * 
	 * @param reference
	 *            execl中的绝对地址
	 * @return 返回boolean true 为 正确地址
	 */
	public boolean checkReference(String reference) {
		Pattern p = Pattern.compile(".+[!][$][A-Z]+[$]\\d+");
		Matcher m = p.matcher(reference);
		boolean r = m.matches();
		return r;
	}

	/**
	 * 
	 * (获得命名单元格的绝对位置)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @return 命名单元格的绝对位置
	 */
	public String getCellReference(String cellName) {
		String reference = "";
		if (this.checkCellName(cellName)) {
			reference = bookNames.get(cellName);
		} else {
			log.error("没有找到(" + cellName + ")命名单元格!");
		}
		return reference;
	}

	/**
	 * 
	 * (获得指定命名单元格的列下标)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @return 下标
	 */
	public int getCellColNum(String cellName) throws Exception {
		int colNum = 0;
		try {
			System.out.println(this.getCellReference(cellName));
			colNum = this.getCell(this.getCellReference(cellName)).getCol_num();

		} catch (Exception e) {
			// Auto-generated catch block
			throw new BusinessException("读excel名称" + cellName + "出错");
		}
		return colNum;
	}

	/**
	 * 
	 * (获得指定命名单元格的行下标)
	 * 
	 * @param cellName
	 *            命名单元格的名字
	 * @return 下标
	 */
	public int getCellRowNum(String cellName) throws Exception {
		int colNum = 0;
		try {
			colNum = this.getCell(this.getCellReference(cellName)).getRow_num();
		} catch (Exception e) {
			// Auto-generated catch block
			throw new BusinessException("读excel名称" + cellName + "出错");
		}
		return colNum;
	}
}
