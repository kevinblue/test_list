package com.tenwa.leasing.service.contractcomm;

import java.util.HashMap;
import java.util.Map;

import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

public interface ContractCommService {
  public void  loadContractInfoFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  saveAndCreateContractNo(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  
  public void  loadContractEquipFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractUnionCustFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void loadContractFundPut(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractGuaranteeMethodFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractGuaranteeEquipFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractInvoiceFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractSignatoryInfoToCreate(CustInfo custInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractSupplierInfoByProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractRentCalculationParam(String contractid,String custname,String custid,String flowunid,String process,Map<String, String> variablesMap)throws Exception ;
  public void  loadContractRentCalculationFromProj(String contractid,ProjInfo projInfo,Map<String, String> variablesMap)throws Exception ;		
  public void loadContractClientInfo(CustInfo customerInfo,Map<String, String> variablesMap) throws Exception;
  public ContractInfo saveContractInfo(Map<String,String>variablesMap,Integer ContractStatus)throws Exception;
  public void saveContractRentCalculationBefore(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractEquip(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractGuaranteeMethod(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractGuaranteeEquip(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractSignatoryInfo(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractSupplierInfo(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractInvoiceType(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractChangeInfo(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void saveContractSignatorySecondInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception;
  
  /**
   * 更新生成文件的filekey
   * @param strWord   存放多行的namne
   * @param tempkey   一般指合同或项目的ID
   * @param variablesMap
   * @throws Exception
   */
  public void updateWordKey(String strWord,String tempkey,Map<String,String>variablesMap)throws Exception;
  /**
   * 加载生成文件
   * @param strWord   存放多行的namne
   * @param modelname 模块名
   * @param tempkey    一般指合同或项目的ID
   * @param variablesMap
   * @throws Exception
   */
  public void loadFile(String strWord,String modelname,String tempkey,Map<String,String>variablesMap)throws Exception;
  public ContractInfo loadContractInfo( Map<String,String>variablesMap)throws Exception;
  public void  loadContractEquip(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractGuaranteeMethod(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractGuaranteeEquip(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractInvoice(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception;
  public void  loadContractRentCalculationFromBefore(ContractInfo contractid,Map<String, String> variablesMap)throws Exception ;
  public void  loadContractSignatoryInfo(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void  loadContractSupplierInfo(ContractInfo contracinfo,Map<String,String>variablesMap)throws Exception;
  public void  updateContractConditionDataAndSaveDatatoHis(ContractInfo contracinfo, Map<String,String>variablesMap,String docId,String process)throws Exception;
  public void  updateContractFundDataAndSaveDatatoHis(ContractInfo contracinfo, Map<String,String>variablesMap,String docId,String process)throws Exception;
  public void  saveContractCustChangeInfo(ContractInfo contracinfo, Map<String,String>variablesMap)throws Exception;
  public void  loadContractRentCalculation(ContractInfo contractid,Map<String, String> variablesMap)throws Exception ;
  public void saveContractRentCalculationBeforePut(ContractInfo contracinfo,
		Map<String, String> variablesMap) throws Exception;
  public void  createContractNo(Map<String, String> variablesMap) throws Exception;
  
  /**
	 * 保存项目联合承租人
	 * @param projinfo
	 * @param variablesMap
	 * @throws Exception
	 */
	public void saveContractUnionCust(ContractInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	public void saveContractUnionCust2(ContractInfo projinfo,Map<String,String>variablesMap)throws Exception;
	
	public void loadContractUnionCust(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception;
}
