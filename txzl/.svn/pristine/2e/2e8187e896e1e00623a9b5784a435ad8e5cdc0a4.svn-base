package com.tenwa.leasing.serviceImpl.ebank;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tenwa.business.model.ExcelVersionEnum;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.PoiExcelUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.service.ebank.FundEbankImportService;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value = "fundEbankImportService")
public class FundEbankImportServiceImpl implements FundEbankImportService {

	@Resource(name = "tableService")
	TableService tableService;

	/**
	 * 工商银行网银导入
	 */
	@Override
	public String importIDBCEbank(MultipartFile multipartFile,
			Map<String, String> modelData) throws Exception {
		InputStream is = multipartFile.getInputStream();
		Workbook wb = null;
		String importFileName = multipartFile.getOriginalFilename()
				.toLowerCase();
		if (importFileName.endsWith("xlsx")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
		} else if (importFileName.endsWith("xls")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
		} else {
			throw new BusinessException("请上传excel版本网银");
		}

		Sheet sheet = wb.getSheetAt(0);
		// 校验是否添加银行账号
		if (!((Row) sheet.getRow(0)).getCell(0).getStringCellValue()
				.startsWith("账号")) {
			throw new BusinessException("错误");
		}
		Cell accountCell = sheet.getRow(0).getCell(1);
		String accountCellValue = "";
		if(accountCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			accountCellValue = new DecimalFormat("0").format(accountCell.getNumericCellValue())+"";
		}else{
			accountCellValue =  accountCell.getStringCellValue();
		}
		// 校验银行账号是否填写
		if (null==((Row) sheet.getRow(0)).getCell(1)||"".equals(accountCellValue)) {
			throw new BusinessException("EXCEL第1行本方银行账号不能为空！");
		}
		
		String ownAccNumber = ((Row) sheet.getRow(0)).getCell(1)
				.getStringCellValue().trim();// 本方银行账号
		String ownaccount = ((Row) sheet.getRow(1)).getCell(1)
				.getStringCellValue().trim();// 本方账户名
		String coin = ((Row) sheet.getRow(3)).getCell(1).getStringCellValue();// 币种

		// 校验本方银行账号
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("accNumber", ownAccNumber);
		List<OwnAccount> ownaccnumberList = this.tableService
				.findEntityByProperties(OwnAccount.class, propertiesMap);
		if (ownaccnumberList.size() > 0) {
		
		} else {
			throw new BusinessException(" 第1行本方账号(" + ownAccNumber
					+ ")在系统中未维护,请在本方信息中维护该账号信息!");
		}
		// 导入数据
		List<FundEbankData> list = new ArrayList<FundEbankData>();
		int count = 0;
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 5; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row=(Row) sheet.getRow(i);
			if (row.getCell(8).getNumericCellValue() == 0) {
				continue;
			}
			FundEbankData fundEbankData = new FundEbankData();
			
			String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null, tableService.getBussinessDao().getHibernateTemplate(), tableService.getBussinessDao().getJdbcTemplate());// 网银编号
			fundEbankData.setEbdataId(ebankNumber);
			fundEbankData.setOwnAccNumber(ownAccNumber);
			fundEbankData.setOwnAccount(ownaccount);
			fundEbankData.setOwnBank(ownaccnumberList.get(0).getAccBank());
			fundEbankData.setMoneyType(coin);
/*			if (null == row.getCell(5) || "".equals(row.getCell(5).getStringCellValue().trim())) {
				throw new BusinessException("第" + (i + 1) + "行对方银行账号不能为空！");
			}*/
			fundEbankData.setClientAccNumber(null == row.getCell(5)?"": row.getCell(5).getStringCellValue());
			fundEbankData.setClientAccount(null==row.getCell(4)?"":row.getCell(4).getStringCellValue());
			fundEbankData.setSummary(null == row.getCell(6)?"":row.getCell(6).getStringCellValue());
			fundEbankData.setFactDate(dateformat.format(row.getCell(0).getDateCellValue()));
			fundEbankData.setNoWithMoney(BigDecimal.ZERO);
			fundEbankData.setFactMoney(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));
			fundEbankData.setMayOpeMoney(BigDecimal.valueOf(row.getCell(8).getNumericCellValue()));
			fundEbankData.setCreator(SecurityUtil.getPrincipal());
			fundEbankData.setUploaddate(DateUtil.getSystemDateTime());
			list.add(fundEbankData);
			count++;
		}
		this.tableService.saveAllEntities(list);
		return "{message:\"成功导入" + count + "条记录！\"}";
	}

	/**
	 * 南京银行网银导入
	 */
	@Override
	public String importNJEbank(MultipartFile multipartFile,
			Map<String, String> modelData) throws Exception {
		InputStream is = multipartFile.getInputStream();
		Workbook wb = null;
		String importFileName = multipartFile.getOriginalFilename()
				.toLowerCase();
		if (importFileName.endsWith("xlsx")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
		} else if (importFileName.endsWith("xls")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
		} else {
			throw new BusinessException("请上传excel版本网银");
		}

		Sheet sheet = wb.getSheetAt(0);
		// 校验银行账号是否填写
		if (null==((Row) sheet.getRow(0)).getCell(0)&&!((Row) sheet.getRow(0)).getCell(0).getStringCellValue()
				.startsWith("账号")) {
			throw new BusinessException("EXCEL第1行必须包含本方银行账号！");
		}

		// 校验银行账号是否填写
		Cell accountCell = sheet.getRow(0).getCell(1);
		// 校验银行账号是否填写
		String accountCellValue = "";
		if(accountCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			accountCellValue = new DecimalFormat("0").format(accountCell.getNumericCellValue())+"";
		}else{
			accountCellValue =  accountCell.getStringCellValue();
		}
		if (null==((Row) sheet.getRow(0)).getCell(1)||"".equals(accountCellValue)) {
			throw new BusinessException("EXCEL第1行本方银行账号不能为空！");
		}
		
		String ownAccNumber = accountCellValue.trim();// 本方银行账号

		// 校验本方银行账号
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("accNumber", ownAccNumber);
		List<OwnAccount> ownaccnumberList = this.tableService
				.findEntityByProperties(OwnAccount.class, propertiesMap);
		if (ownaccnumberList.size() > 0) {
		} else {
			throw new BusinessException("第1行本方账号(" + ownAccNumber
					+ ")在系统中未维护,请在本方信息中维护该账号信息!");
		}

		List<FundEbankData> list = new ArrayList<FundEbankData>();
		int count = 0;// 插入数据条目数

		for (int i = 4; i < sheet.getPhysicalNumberOfRows(); i++) {
			
			Row row=(Row) sheet.getRow(i);
			if ("".equals(row.getCell(3).getStringCellValue().trim())) {
				continue;
			}
			FundEbankData fundEbankData = new FundEbankData();
			
			String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,
					tableService.getBussinessDao().getHibernateTemplate(),
					tableService.getBussinessDao().getJdbcTemplate());//网银编号
			fundEbankData.setEbdataId(ebankNumber);
			
			fundEbankData.setOwnAccNumber(ownAccNumber);
			fundEbankData.setOwnAccount(ownaccnumberList.get(0).getAccName());
			fundEbankData.setOwnBank(ownaccnumberList.get(0).getAccBank());
/*			if ("".equals(row.getCell(8).getStringCellValue())) {
				throw new BusinessException("第" + (i+1) + "行对方银行账号不能为空！");
			}*/
			
			fundEbankData.setClientAccNumber(null==row.getCell(8)?"":row.getCell(8).getStringCellValue());
			fundEbankData.setClientAccount(null==row.getCell(7)?"":row.getCell(7).getStringCellValue());
			fundEbankData.setSummary(null==row.getCell(1)?"":row.getCell(1).getStringCellValue());
			fundEbankData.setFactDate(row.getCell(0).getStringCellValue());
			fundEbankData.setNoWithMoney(BigDecimal.ZERO);
			fundEbankData.setFactMoney(BigDecimal.valueOf(Double.valueOf(row.getCell(4).getStringCellValue().replace(",", ""))));
			fundEbankData.setMayOpeMoney(BigDecimal.valueOf(Double.valueOf(row.getCell(4).getStringCellValue().replace(",", ""))));
			fundEbankData.setCreator(SecurityUtil.getPrincipal());
			fundEbankData.setUploaddate(DateUtil.getSystemDateTime());
			list.add(fundEbankData);
			count++;
		}
		this.tableService.saveAllEntities(list);
		return "{message:\"成功导入" + count + "条记录！\"}";
	}

	/**
	 * 民生银行网银导入
	 */
	@Override
	public String importCMBCEbank(MultipartFile multipartFile,
			Map<String, String> modelData) throws Exception {
		InputStream is = multipartFile.getInputStream();
		Workbook wb = null;
		String importFileName = multipartFile.getOriginalFilename()
				.toLowerCase();
		if (importFileName.endsWith("xlsx")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
		} else if (importFileName.endsWith("xls")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
		} else {
			throw new BusinessException("请上传excel版本网银");
		}
		
		Sheet sheet = wb.getSheetAt(0);

		if (null==((Row) sheet.getRow(0)).getCell(0)&&!((Row) sheet.getRow(0)).getCell(0).getStringCellValue().startsWith("账号")) {
			throw new BusinessException("EXCEL第1行必须包含本方银行账号！");
		}
		Cell accountCell = sheet.getRow(0).getCell(1);
		// 校验银行账号是否填写
		String accountCellValue = "";
		if(accountCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			accountCellValue = new DecimalFormat("0").format(accountCell.getNumericCellValue())+"";
		}else{
			accountCellValue =  accountCell.getStringCellValue();
		}
		if (null==((Row) sheet.getRow(0)).getCell(1)||"".equals(accountCellValue)) {
			throw new BusinessException("EXCEL第1行本方银行账号不能为空！");
		}
		String ownAccNumber = accountCellValue.trim();// 本方银行账号
		String ownaccount = ((Row) sheet.getRow(1)).getCell(1)
				.getStringCellValue().trim();// 本方账户名

		List<FundEbankData> list = new ArrayList<FundEbankData>();

		// 校验本方银行账号
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("accNumber", ownAccNumber);
		List<OwnAccount> ownaccnumberList = this.tableService
				.findEntityByProperties(OwnAccount.class, propertiesMap);
		if (ownaccnumberList.size() > 0) {
		} else {
			throw new BusinessException("第1行本方账号(" + ownAccNumber
					+ ")在系统中未维护,请在本方信息中维护该账号信息!");
		}
		int count = 0;

		for (int i = 3; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row=(Row) sheet.getRow(i);
			if (row.getCell(3).getNumericCellValue() == 0) {
				continue;
			}
			
			String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,
					tableService.getBussinessDao().getHibernateTemplate(),
					tableService.getBussinessDao().getJdbcTemplate());
			FundEbankData fundEbankData = new FundEbankData();
			fundEbankData.setEbdataId(ebankNumber);
			fundEbankData.setOwnAccNumber(ownAccNumber);
			fundEbankData.setOwnAccount(ownaccount);
			fundEbankData.setOwnBank(ownaccnumberList.get(0).getAccBank());
			if (null==row.getCell(6)||"".equals(row.getCell(6).getStringCellValue())) {
				throw new BusinessException("第" + (i+1)  + "行对方银行账号不能为空！");
			}
			fundEbankData.setClientAccNumber(null==row.getCell(6)?"":row.getCell(6).getStringCellValue());
			fundEbankData.setNoWithMoney(BigDecimal.ZERO);
			fundEbankData.setClientAccount(null==row.getCell(7)?"":row.getCell(7).getStringCellValue());
			fundEbankData.setSummary(null==row.getCell(5)?"":row.getCell(5).getStringCellValue());
			fundEbankData.setFactDate(row.getCell(0).getStringCellValue());
			fundEbankData.setMayOpeMoney(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
			fundEbankData.setFactMoney(BigDecimal.valueOf(row.getCell(3).getNumericCellValue()));
			fundEbankData.setCreator(SecurityUtil.getPrincipal());
			fundEbankData.setUploaddate(DateUtil.getSystemDateTime());
			list.add(fundEbankData);
			count++;
		}
		this.tableService.saveAllEntities(list);
		return "{message:\"成功导入" + count + "条记录！\"}";
	}

	/**
	 * 交通银行网银导入
	 */
	@Override
	public String importCOMMEbank(MultipartFile multipartFile,
			Map<String, String> modelData) throws Exception {
		InputStream is = multipartFile.getInputStream();
		Workbook wb = null;
		String importFileName = multipartFile.getOriginalFilename().toLowerCase();
		if (importFileName.endsWith("xlsx")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2007);
		} else if (importFileName.endsWith("xls")) {
			wb = PoiExcelUtil.readWorkbook(is, ExcelVersionEnum.VERSION2003);
		} else {
			throw new BusinessException("请上传excel版本网银");
		}

		Sheet sheet = wb.getSheetAt(0);

		if (null==((Row) sheet.getRow(0)).getCell(0)&&!((Row) sheet.getRow(0)).getCell(0).getStringCellValue()
				.startsWith("账号")) {
			throw new BusinessException("EXCEL第1行必须包含本方银行账号！");
		}

		Cell accountCell = sheet.getRow(0).getCell(1);
		String accountCellValue = "";
		if(accountCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
			accountCellValue = new DecimalFormat("0").format(accountCell.getNumericCellValue())+"";
		}else{
			accountCellValue =  accountCell.getStringCellValue();
		}
		// 校验银行账号是否填写
		if (null==((Row) sheet.getRow(0)).getCell(1)||"".equals(accountCellValue)) {
			throw new BusinessException("EXCEL第1行本方银行账号不能为空！");
		}
		
		String ownAccNumber = ((Row) sheet.getRow(0)).getCell(1)
				.getStringCellValue().trim();// 本方银行账号

		List<FundEbankData> list = new ArrayList<FundEbankData>();

		// 校验本方银行账号
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("accNumber", ownAccNumber);
		List<OwnAccount> ownaccnumberList = this.tableService
				.findEntityByProperties(OwnAccount.class, propertiesMap);
		if (ownaccnumberList.size() > 0) {
		} else {
			throw new BusinessException("第1行本方账号(" + ownAccNumber
					+ ")在系统中未维护,请在本方信息中维护该账号信息!");
		}
		
		int count = 0;

		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			Row row=(Row) sheet.getRow(i);
			String ebankNumber = WorkflowUtil.getEbankNoInfoSerialNumber(null,
					tableService.getBussinessDao().getHibernateTemplate(),
					tableService.getBussinessDao().getJdbcTemplate());
			FundEbankData fundEbankData = new FundEbankData();
			fundEbankData.setEbdataId(ebankNumber);
			fundEbankData.setOwnAccNumber(ownAccNumber);
			fundEbankData.setOwnAccount(ownaccnumberList.get(0).getAccName());
			fundEbankData.setOwnBank(ownaccnumberList.get(0).getAccBank());
			if (null==row.getCell(5)||"".equals(row.getCell(5).getStringCellValue())) {
				throw new BusinessException("第" + (i+1)  + "行对方银行账号不能为空！");
			}
			fundEbankData.setClientAccNumber(null==row.getCell(5)?"":row.getCell(5).getStringCellValue());
			fundEbankData.setCoin(row.getCell(7).getStringCellValue());
			fundEbankData.setClientAccount(null==row.getCell(6)?"":row.getCell(6).getStringCellValue());
			fundEbankData.setSummary(null==row.getCell(1)?"":row.getCell(1).getStringCellValue());
			fundEbankData.setFactDate(row.getCell(0).getStringCellValue());
			fundEbankData.setNoWithMoney(BigDecimal.ZERO);
			fundEbankData.setFactMoney(BigDecimal.valueOf(Double.valueOf(row.getCell(8).getStringCellValue().replace(",", ""))));
			fundEbankData.setMayOpeMoney(BigDecimal.valueOf(Double.valueOf(row.getCell(8).getStringCellValue().replace(",", ""))));
			fundEbankData.setCreator(SecurityUtil.getPrincipal());
			fundEbankData.setUploaddate(DateUtil.getSystemDateTime());
			list.add(fundEbankData);
			count++;
		}
		this.tableService.saveAllEntities(list);
		return "{message:\"成功导入" + count + "条记录！\"}";
	}

}
