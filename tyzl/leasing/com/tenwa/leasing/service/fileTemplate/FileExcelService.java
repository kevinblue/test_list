package com.tenwa.leasing.service.fileTemplate;

import java.util.Map;

import com.tenwa.exception.BusinessException;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.file.BaseFileTemplate;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.VatInvoiceInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;

/**
 * 
 * @author XU
 * @version 创建时间：2013-8-9 下午02:34:47
 */
public interface FileExcelService {
	
	
	/**
	 * @param fundEbankData  网银表信息
	 * @param model
	 * @param rowIndex
	 * @return 上传网银信息,校验上传字段合法性
	 * @throws Exception
	 */
	 public Object checkFundEbankInfoImport(FundEbankData fundEbankData,Map<String, String> model,Integer rowIndex) throws Exception ;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public  void exportFundInvoiceBefore(Map<String,Object> model) throws Exception;
	
	/**
	 * @param model
	 * @throws BusinessException
	 */
	public void exportFundChargeInvoiceBefore(Map<String, Object> model) throws BusinessException;
	
	/**
	 * 
	* @xuyunlong 作者 E-mail: 
	* @version 创建时间：2013-10-15 下午02:35:12 
	* 类说明卡扣数据上传初始化
	 */
	// public Object initCardHire(ContractCardRentIncome card,Map<String,String> model,Integer rowIndex) throws Exception;
	public Object checkEbankInfoDataImport(FundEbankData fed,Map<String,String> model,Integer rowIndex)throws Exception;

	 /**
	  * 
	 * @author 作者 wu: 
	 * @version 创建时间：2013-12-21 下午02:56:35 
	 * 租赁物件信息
	  */
	 public Object checkEquipInfoImport(ProjEquip equip,Map<String,String> model,Integer rowIndex) throws Exception;
	 /**
	 * @param down
	 * @param model
	 * @param rowIndex
	 * @return
	 * @throws Exception
	 */
	public Object checkFundInvoiceDownloadInfoImport(FundInvoiceDownloadInfo down,
			Map<String, String> model, Integer rowIndex) throws Exception;

	/**
	 * @param model
	 * @throws BusinessException
	 * @throws Exception 
	 */
	public void exportFundInvoiceAfter(Map<String, Object> model)
			throws BusinessException, Exception;

	/**
	 * @param model
	 * @throws BusinessException
	 */
	public void exportRentInvoiceBefore(Map<String, Object> model)
			throws BusinessException;

	/**
	 * @param model
	 * @throws BusinessException
	 */
	public void exportRentIncomeInvoiceBefore(Map<String, Object> model)
			throws BusinessException;

	/**
	 * @param model
	 * @throws Exception
	 */
	public void exportRentInvoiceAfter(Map<String, Object> model) throws Exception;

	/**
	 * @param taxFui
	 * @param model
	 * @param rowIndex
	 * @return
	 * @throws Exception
	 */
	public Object checkRentInvoiceDownloadInfoImport(RentInvoiceDownloadInfo taxFui,
			Map<String, String> model, Integer rowIndex) throws Exception;
	
	
	/**
	 * @param taxVui
	 * @param model
	 * @param rowIndex
	 * @return
	 * @throws Exception
	 */
	public Object checkVatInvoiceDownloadInfoImport(VatInvoiceInfo vatInvoiceInfo,
			Map<String, String> model, Integer rowIndex) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	
	public void exportFundReceiptAfter(Map<String, Object> model) throws Exception;
	
	public String exportFundReceiptBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception;

	public String exportRentReceiptBefore(FileTemplateService fi,Map<String, String> pageMap,BaseFileTemplate ft,String modename) throws Exception;
	
	/**
	 * @param model
	 * @throws Exception
	 */
	public void exportRentReceiptAfter(Map<String, Object> model) throws Exception;

	public String createvndr(String custname)throws Exception;
	public String createmanufacturer(String custname)throws Exception;
}
