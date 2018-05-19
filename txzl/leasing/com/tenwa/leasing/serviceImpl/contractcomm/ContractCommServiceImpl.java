package com.tenwa.leasing.serviceImpl.contractcomm;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.fund.ContractPaymentPremiseCondition;
import com.reckon.entity.proj.ProjCondition;
import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.kernal.utils.BeanFieldUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.base.OwnInfo;
import com.tenwa.leasing.entity.contract.ConTradeTransaction;
import com.tenwa.leasing.entity.contract.ContractChangeInfo;
import com.tenwa.leasing.entity.contract.ContractChangeOtherInfo;
import com.tenwa.leasing.entity.contract.ContractCustHis;
import com.tenwa.leasing.entity.contract.ContractDocList;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractEquipTmp;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquipTmp;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethodTmp;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.contract.ContractPremiseCondition;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.invoice.FundInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.FundInvoiceInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceDownloadInfo;
import com.tenwa.leasing.entity.invoice.RentInvoiceInfo;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.entity.proj.ContractNumberSettingTmp;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjEquip;
import com.tenwa.leasing.entity.proj.ProjEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquip;
import com.tenwa.leasing.entity.proj.ProjGuaranteeEquipTmp;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethod;
import com.tenwa.leasing.entity.proj.ProjGuaranteeMethodTmp;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.invoice.ProjInvoiceType;
import com.tenwa.leasing.entity.trade.TradeBasedTransactions;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.serviceImpl.invoice.TaxFundServiceIpml;
import com.tenwa.leasing.utils.WorkflowUtil;
import com.tenwa.report.dao.ReportDao;

 

@Service(value = "contractCommService")
public class ContractCommServiceImpl implements ContractCommService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name = "reportDao")
	private ReportDao reportDao;
	
	public void loadContractInfoFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo, null, "contract_info"));
		ProjDevelopInfo  pdinfo=projInfo.getDevelopid();
		ProjCondition pcon = projInfo.getProjCondition();
		CustInfo customerInfo = projInfo.getCustInfo();
		String cust_class = customerInfo.getCustClass().getCode();
		variablesMap.put("contract_info.projdeveloptype", String.valueOf(pdinfo.getType()));
		variablesMap.put("contract_info.equipamt", String.valueOf(pcon.getEquipAmt()));
		variablesMap.put("contract_info.custclass", cust_class);
		variablesMap.put("contract_info.custid", customerInfo.getId());
		variablesMap.put("contract_info.custname", customerInfo.getCustName());
		variablesMap.put("rawValue_contract_info.custname", customerInfo.getCustName());
		variablesMap.put("contract_info.custnumber", customerInfo.getCustNumber());
		variablesMap.put("contract_info.proj_id", projInfo.getProjId());
		variablesMap.put("contract_info.projid", projInfo.getId());
	}

	public void saveAndCreateContractNo(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		long contractOrder = projInfo.getContractOrder() == null ? 1 : projInfo.getContractOrder() + 1;
		String contract_id = String.format(projInfo.getProjId() + "-%02d", contractOrder);
		String contract_no="";
		
		if(projInfo.getBusinessType().getId().equals("business_type.lease")){
			contract_no=contract_id.replace("P", "PL");
		}else{
			contract_no=contract_id;
		}
		projInfo.setContractOrder(contractOrder);
		this.tableService.updateEntity(projInfo);
		variablesMap.put("contract_info.contractid", contract_id);
		//variablesMap.put("contract_info.contractnumber", contract_no);
	}

	public void loadContractInvoiceFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		ProjInvoiceType invoiceType = projInfo.getProjInvoiceType();
		if(null!=invoiceType){
		  variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(invoiceType, null, "contract_invoice_type"));
		}
	}

	@Override
	public void loadContractEquipFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		JSONArray jsonarrayStr = this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_equip.xml", queryMainObjectMap);
		variablesMap.put("json_contract_equip_str",jsonarrayStr.toString());
	}

	@Override
	public void loadContractGuaranteeEquipFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		variablesMap.put("json_contract_guarantee_equip_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_guarantee_equip.xml", queryMainObjectMap).toString());	
	}

	public void loadContractGuaranteeMethodFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		variablesMap.put("json_contract_guarantee_method_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_guarantee_method.xml", queryMainObjectMap).toString());		
    }


	public void loadContractSignatoryInfoToCreate(CustInfo customerInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,Object> propertiesMap = new HashMap<String,Object>();
		Iterator<?> it;
		//出租方基本信息
		List<OwnInfo> ownInfos= this.tableService.findEntities(OwnInfo.class);
		if(ownInfos.size()>0){
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(ownInfos.get(0),null, "contract_signatory"));//放入本方信息
			variablesMap.put("contract_signatory.lessor",ownInfos.get(0).getOwnName());//放入本方名
			Set<OwnAccount> ownAccountSet = ownInfos.get(0).getOwnAccounts();
			if(ownAccountSet != null && ownAccountSet.size()>0){
				it = ownAccountSet.iterator();
				OwnAccount ownAccount = (OwnAccount)it.next();
				variablesMap.put("contract_signatory.leaseaccnumber",ownAccount.getAccNumber());
				variablesMap.put("contract_signatory.leaseaccbank",ownAccount.getAccBank());
				variablesMap.put("contract_signatory.leaseaccname",ownAccount.getAccName());
			}
		}
		//承租方基本信息
		variablesMap.put("contract_signatory.client",customerInfo.getCustName());//承租人
		variablesMap.put("contract_signatory.clientconsigner",customerInfo.getCustName());//委托代理人
		variablesMap.put("contract_signatory.clientlinkman",customerInfo.getCustName());//联系人
		
		//银行账号
		propertiesMap.clear();
		propertiesMap.put("custId", customerInfo);
		propertiesMap.put("isMain", "是");
		List<CustInfoAccount> accountList = this.tableService.findEntityByProperties(CustInfoAccount.class, propertiesMap);
		if(accountList != null && accountList.size() > 0){
			CustInfoAccount account = accountList.get(0);
			variablesMap.put("contract_signatory.clientaccnumber", account.getAccNumber());//开户账号
			variablesMap.put("contract_signatory.clientaccbank", account.getBankName());//开户银行
			variablesMap.put("contract_signatory.clientaccname", account.getAccount());//开户户名
			variablesMap.put("contract_signatory.bankno", account.getBankNo());//行号
		}
		//重要联系人
		propertiesMap.clear();
		propertiesMap.put("custId", customerInfo);
		propertiesMap.put("mainpersonflag", "是");
		List<CustRelatedPerson> relatedPersonList = this.tableService.findEntityByProperties(CustRelatedPerson.class, propertiesMap);
		if(relatedPersonList != null && relatedPersonList.size() > 0){
			CustRelatedPerson relatedPerson = relatedPersonList.get(0);
			variablesMap.put("contract_signatory.clientlinkman", relatedPerson.getPersonname());//重要联系人
		}
		
		if("CUST_INFO_PERSON".equals(customerInfo.getCustClass().getId())){
			CustInfoPerson person = customerInfo.getCustInfoPerson();
			variablesMap.put("contract_signatory.clientpostcode", person.getPostCode() == null ? "" : person.getPostCode());//邮编
			variablesMap.put("contract_signatory.clientaddress", person.getMailAdd() == null ? "" : person.getMailAdd());//通讯地址
			variablesMap.put("contract_signatory.clientmobilenumber", person.getMobileNumber() == null ? "" : person.getMobileNumber());//短信联系手机号
			variablesMap.put("contract_signatory.clienttel", person.getPhone() == null ? "" : person.getPhone());//电话
			variablesMap.put("contract_signatory.clientfax", person.getFaxNumber() == null ? "" : person.getFaxNumber());//传真
			variablesMap.put("contract_signatory.clientemail", person.getEmail() == null ? "" : person.getEmail());//电子邮件
			variablesMap.put("contract_signatory.clientperson", customerInfo.getCustName() == null ? "" : customerInfo.getCustName());//法定代表人
		}else{
			CustInfoCompany company = customerInfo.getCustInfoCompany();
			variablesMap.put("contract_signatory.clientpostcode",company.getPostCode() == null ? "" : company.getPostCode());
			variablesMap.put("contract_signatory.clientaddress",company.getCompanyAddress() == null ? "" : company.getCompanyAddress());
			variablesMap.put("contract_signatory.clientmobilenumber",company.getMoblie() == null ? "" : company.getMoblie());
			variablesMap.put("contract_signatory.clienttel",company.getPhone() == null ? "" : company.getPhone());
			variablesMap.put("contract_signatory.clientfax",company.getFax() == null ? "" : company.getFax());
			variablesMap.put("contract_signatory.clientemail",company.getMailAddress() == null ? "" : company.getMailAddress());
			variablesMap.put("contract_signatory.clientperson",company.getPersonRep() == null ? "" : company.getPersonRep());
		}
		
		
	}

	@Override
	public void loadContractSupplierInfoByProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String>queryMainObjectMap=new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		String json_contract_supplier_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_approval/contract_supplier_info.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_supplier_str", json_contract_supplier_str);
	}
	
	@Override
	public void loadContractPremiseInfoByProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String>queryMainObjectMap=new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		CustInfo custinfo =projInfo.getCustInfo();
		String custname = custinfo.getCustName();
		String json_contract_premise_str = this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_premise.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_premise_str", json_contract_premise_str);
		variablesMap.put("Pcustname", custname);
		System.out.println(json_contract_premise_str);
	}

	@Override
	public void loadContractRentCalculationParam(String contractid, String custname,String custid, String flowunid,String process ,Map<String, String> variablesMap)throws Exception {
		variablesMap.put("framework_condition.contractid",contractid);
		variablesMap.put("framework_condition.process", process);
		variablesMap.put("framework_condition.custname",custname);
		variablesMap.put("framework_condition.custid",custid);
		variablesMap.put("framework_condition.docid",flowunid);
		variablesMap.put("framework_condition.onhireid","");	
	}

	@Override
	public void loadContractRentCalculationFromProj(String contractid,	ProjInfo projInfo, Map<String, String> variablesMap)throws Exception  {
		
		this.rentConditionData.LoadProjectContionAndRentAndFundToContractMap(contractid,projInfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","json_fund_put_config_str","json_special_regular_str","json_grace_plan_str");

	}

	@Override
	public ContractInfo saveFactoringContractInfo(Map<String, String> variablesMap,Integer ContractStatus) throws Exception {
		String proj_id = variablesMap.get("contract_info.contractid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("contractId", proj_id);
		variablesMap.put("contract_info.contractstatus", String.valueOf(ContractStatus));
		ContractInfo contractInfo = (ContractInfo)this.tableService.updateMainEntity(ContractInfo.class, queryMainObjectMap, variablesMap,null, "contract_info");
		ProjInfo pi = new ProjInfo();
		pi.setId(variablesMap.get("projidforend"));
		contractInfo.setProjId(pi);
		this.tableService.saveOrUpdateEntity(contractInfo);
		return contractInfo;
	}

	public ContractInfo saveContractInfo(Map<String, String> variablesMap,Integer ContractStatus) throws Exception {
		String proj_id = variablesMap.get("contract_info.contractid");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("contractId", proj_id);
		variablesMap.put("contract_info.contractstatus", String.valueOf(ContractStatus));
	/*	String custid=variablesMap.get("contract_info.custname");
		variablesMap.put("contract_info.custid",custid);*/
		ContractInfo contractInfo = (ContractInfo)this.tableService.updateMainEntity(ContractInfo.class, queryMainObjectMap, variablesMap,null, "contract_info");
		this.tableService.saveOrUpdateEntity(contractInfo);
		return contractInfo;
	}
	@Override
	public void saveContractRentCalculationBefore(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.saveOrLoadRentAndFinalBefore(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","json_fund_put_config_str","json_special_regular_str","json_grace_plan_str","save");
	}

	@Override
	public void saveContractEquip(ContractInfo contracinfo,	Map<String, String> variablesMap) throws Exception {
		String jsonEquipsString = variablesMap.get("json_contract_equip_str");
		this.tableService.updateOneToManyCollections(contracinfo, "contractEquips", ContractEquip.class, "contractId", jsonEquipsString,null);
	}

	@Override
	public void saveContractGuaranteeMethod(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		String jsonProjGuaranteeMethodString = variablesMap.get("json_contract_guarantee_method_str");
		this.tableService.updateOneToManyCollections(contracinfo, "contractGuaranteeMethods", ContractGuaranteeMethod.class, "contractId", jsonProjGuaranteeMethodString,null);
		
	}
	@Override
	public void saveContractGuaranteeEquip(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		String jsonprojGuaranteeEquipString = variablesMap.get("json_contract_guarantee_equip_str");
		this.tableService.updateOneToManyCollections(contracinfo, "contractGuaranteeEquips", ContractGuaranteeEquip.class, "contractId", jsonprojGuaranteeEquipString,null);
	}

	@Override
	public void saveContractInvoiceType(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		this.tableService.updateOneToOneEntity(contracinfo, "contractInvoiceType", "contractId", variablesMap,null, "contract_invoice_type");
		
	}

	@Override
	public void saveContractSignatoryInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		this.tableService.updateOneToOneEntity(contracinfo, "contractSignatory", "contractId", variablesMap,null, "contract_signatory");
	}

	@Override
	public void saveContractSupplierInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		String json_contract_supplier_str = variablesMap.get("json_contract_supplier_str");
		this.tableService.updateOneToManyCollections(contracinfo, "contractSupplierInfos", ContractSupplierInfo.class, "contractId", json_contract_supplier_str,null);

		
	}

	@Override
	public void loadContractEquip(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
	
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		//租赁物件明细
		String json_contract_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_equip_str",json_contract_equip_str);
	
	}


	
	@Override
	public void loadContractGuaranteeEquip(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		//抵押物信息
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_contract_guarantee_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_guarantee_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_equip_str", json_contract_guarantee_equip_str);
	
	}
	
	
	

	@Override
	public void loadContractGuaranteeMethod(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		//担保单位信息
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_contract_guarantee_method_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_guarantee_method.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_method_str", json_contract_guarantee_method_str);
		
	}
	
	@Override
	public void loadContractEquipTmp(ContractInfo contracInfo,Map<String, String> variablesMap,String flowunid) throws Exception {
		//租赁物件明细
		Map<String,Object> propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("contractId", contracInfo);
		List<ContractEquip>  celist=this.tableService.findEntityByProperties(ContractEquip.class, propertiesMap);
		ContractEquipTmp contractequiptmp = null;
		for(ContractEquip ce:celist){
			contractequiptmp = new ContractEquipTmp();
		  this.tableService.copyAndOverrideExistedValueFromObjectNoSet(ce, contractequiptmp);
          contractequiptmp.setOldId(ce.getId());
          contractequiptmp.setContractId(ce.getContractId());
          contractequiptmp.setDocid(flowunid);
          this.tableService.saveEntity(contractequiptmp);
		 }
	
	}

	
	
	@Override
	public void loadContractGuaranteeEquipTmp(ContractInfo contracInfo,Map<String, String> variablesMap,String flowunid) throws Exception {
		//抵押物信息
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("contractId", contracInfo);
		List<ContractGuaranteeEquip>  cgelist=this.tableService.findEntityByProperties(ContractGuaranteeEquip.class, propertiesMap);
		ContractGuaranteeEquipTmp contractguaranteeequip = null;
		for(ContractGuaranteeEquip cge:cgelist){
			contractguaranteeequip = new ContractGuaranteeEquipTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cge, contractguaranteeequip);
          contractguaranteeequip.setOldId(cge.getId());
          contractguaranteeequip.setContractId(cge.getContractId());
          contractguaranteeequip.setDocid(flowunid);
          this.tableService.saveEntity(contractguaranteeequip);
          }
		
	}
	
	@Override
	public void loadContractGuaranteeMethodTmp(ContractInfo contracInfo,Map<String, String> variablesMap,String flowunid) throws Exception {
		//担保单位信息
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("contractId", contracInfo);
		List<ContractGuaranteeMethod>  cgmlist=this.tableService.findEntityByProperties(ContractGuaranteeMethod.class, propertiesMap);
		ContractGuaranteeMethodTmp contractguaranteemethodtmp = null;
		for(ContractGuaranteeMethod cgm:cgmlist){
			contractguaranteemethodtmp = new ContractGuaranteeMethodTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cgm, contractguaranteemethodtmp);
          contractguaranteemethodtmp.setOldId(cgm.getId());
          contractguaranteemethodtmp.setContractId(cgm.getContractId());
          contractguaranteemethodtmp.setDocid(flowunid);
          this.tableService.saveEntity(contractguaranteemethodtmp);
          }
		
	}
	
	@Override
	public void loadContractNumSet(ContractInfo contracInfo,Map<String, String> variablesMap,String flowunid) throws Exception {
		//合同编号设置
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("contractId", contracInfo);
		List<ContractNumberSetting>  cnslist=this.tableService.findEntityByProperties(ContractNumberSetting.class, propertiesMap);
		ContractNumberSettingTmp contractnumbersettingtmp = null;
		for(ContractNumberSetting cns:cnslist){
			contractnumbersettingtmp = new ContractNumberSettingTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cns, contractnumbersettingtmp);
			contractnumbersettingtmp.setOldId(cns.getId());
            contractnumbersettingtmp.setDocid(flowunid);
            contractnumbersettingtmp.setProjInfo(cns.getProjInfo());
            this.tableService.saveEntity(contractnumbersettingtmp);
          }
		
	}
	
	

	@Override
	public ContractInfo loadContractInfo(Map<String,String>variablesMap)throws Exception {
		String contract_id = variablesMap.get("contract_id");
		ContractInfo contractInfo =(ContractInfo)this.tableService.findEntityByID(ContractInfo.class, contract_id);
		variablesMap.put("contract_info.equipamt",contractInfo.getContractCondition().getEquipAmt()+"");
		CustInfo customerInfo = contractInfo.getCustId();
		contractInfoExtends.getContractBaseInfo(variablesMap, contractInfo, customerInfo);
		return contractInfo;
	}

	@Override
	public void loadContractInvoice(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		ContractInvoiceType invoiceType = contracinfo.getContractInvoiceType();
		if(null!=invoiceType){
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(invoiceType, null, "contract_invoice_type"));
		
		}
	}
	@Override
	public void loadContractRentCalculationFromBefore(ContractInfo contracinfo, Map<String, String> variablesMap)throws Exception {
		this.rentConditionData.saveOrLoadRentAndFinalBefore(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","json_fund_put_config_str","json_special_regular_str","json_grace_plan_str","load");
		
	}
	@Override
	public void loadContractRentCalculationForFinance(ContractInfo contracinfo, Map<String, String> variablesMap)throws Exception {
		this.rentConditionData.saveOrLoadRentAndFinalForFinance(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","json_fund_put_config_str","json_special_regular_str","json_grace_plan_str","load");
		
	}

	@Override
	public void loadContractSupplierInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> contractSupplieMap = new HashMap<String,String>();
		contractSupplieMap.put("contract_id", contracinfo.getId());
		String json_contract_supplier_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_supplier_info.xml", contractSupplieMap).toString();
		
		variablesMap.put("json_contract_supplier_str", json_contract_supplier_str);
	
	}

	@Override
	public void loadContractSignatoryInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
	
		if(null!=contracinfo.getContractSignatory()){
			ContractSignatory contractSignatory=contracinfo.getContractSignatory();
			variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(contractSignatory, null, "contract_signatory"));
		}	
	}
	
	
	@Override
	public void saveContractChangeInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		ContractChangeInfo  contractChangeInfo=new ContractChangeInfo();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, contractChangeInfo, null, "contract_change_info");
		contractChangeInfo.setContractId(contracinfo);
		if(variablesMap.containsKey("docId")){
			contractChangeInfo.setFlowUnid(variablesMap.get("docId"));
		}
		this.tableService.saveEntity(contractChangeInfo);
	}

	public void loadFile(String strWord,String modelname, String tempkey,Map<String, String> variablesMap) throws Exception {
		Map<String,String> searchMap = new HashMap<String,String>();
		searchMap.put("modelname", modelname);
		searchMap.put("filekey", tempkey);
		searchMap.put("invalid", "是");
		String json_str = this.tableService.getJsonArrayData("/eleasing/jsp/template_word/filelist_info.xml", searchMap).toString();
		variablesMap.put(strWord, json_str);
	}
	@Override
	public void updateWordKey(String strWord, String tempkey,Map<String, String> variablesMap) throws Exception {
		String jsonTempateString = variablesMap.get(strWord);//proj_info.projEquips
		ObjectMapper jsonMapper = new ObjectMapper(); 
		List<String> fileids=new ArrayList<String>();
		if((!"".equals(jsonTempateString))&&(!"[]".equals(jsonTempateString))){
			JSONArray jsonArray = new JSONArray(jsonTempateString);
			JSONArray newjsonArray = new JSONArray();
			for(int i=0;i<jsonArray.length();i++){
				  JSONObject jsonObj = jsonArray.getJSONObject(i);
				  fileids.add(jsonObj.getString("id"));
			}  
			final int size =fileids.size();
			String[] arr = (String[])fileids.toArray(new String[size]);
			List<BaseFile> bfs=this.tableService.findEntityByIDArray(BaseFile.class,arr);
		    for( BaseFile bf:bfs){
		    	bf.setFilekey(tempkey);
		    	bf.setInvalid("是");
		    	this.tableService.updateEntity(bf);
		    }
		}
	}

	@Override
	public void updateContractConditionDataAndSaveDatatoHis(ContractInfo contracinfo, Map<String, String> variablesMap,String docId, String process) throws Exception{
		this.rentConditionData.updateContractConditionDataAndSaveDatatoHis(contracinfo, variablesMap, docId,process,  "contractcondition", "json_fund_rent_plan_str","json_fund_cash_flow_str","", "",null);
		
		
	}

	@Override
	public void updateContractFundDataAndSaveDatatoHis(ContractInfo contracinfo, Map<String, String> variablesMap,String docId, String process) throws Exception {
		 
		this.rentConditionData.updateContractFundDataAndSaveDatatoHis(contracinfo,variablesMap, docId, process, "json_fund_fund_charge_str", "");	
		
	}

	@Override
	public void saveContractCustChangeInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		String oldcustid = variablesMap.get("contract_info.oldcustid");
		Map<String,String>changeInfo=new HashMap<String,String>();
		changeInfo.put("change.custid", oldcustid);
		changeInfo.put("change.flowunid", variablesMap.get("docId"));
		changeInfo.put("change.modstatus", variablesMap.get("his_status_before"));
		ContractCustHis  contractCustHis=new ContractCustHis();
		this.tableService.copyAndOverrideExistedValueFromStringMap(changeInfo, contractCustHis, null, "change");
		contractCustHis.setContractId(contracinfo);
		this.tableService.saveEntity(contractCustHis);
		
	}
	@Override
	public void loadContractRentCalculation(ContractInfo contractid,Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.LoadContractContionAndRentAndFundToMap(contractid, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str","", "", "json_fund_fund_charge_str","json_fund_put_config_str","json_grace_plan_str","json_special_regular_str");
	}
	/**
	 * 更新联系人信息
	 * @param variablesMap
	 * @throws Exception
	 */
	@Override
    public void saveorupdateRelationPerson(Map<String, String> variablesMap) throws Exception {
		String json_relateperson_str=variablesMap.get("json_relateperson_str");
         JSONArray json_arr=new JSONArray(json_relateperson_str);
         
         List<CustRelatedPerson> list=new ArrayList<CustRelatedPerson>();
         
         for(int i=0;i<json_arr.length();i++){
        	 CustRelatedPerson crp=new CustRelatedPerson();
        	if(null!=json_arr.getJSONObject(i).get("id")&&!"".equals(json_arr.getJSONObject(i).get("id").toString())){
        	    crp=this.tableService.findEntityByID(CustRelatedPerson.class, json_arr.getJSONObject(i).get("id").toString());
        	    this.tableService.copyAndOverrideExistedValueFromStringMap(this.tableService.getStringMapByJsonObject(json_arr.getJSONObject(i)), crp, null, "");
        	}else{
        	    this.tableService.copyAndOverrideExistedValueFromStringMap(this.tableService.getStringMapByJsonObject(json_arr.getJSONObject(i)), crp, null, "");
        	}   
        	 list.add(crp);
         }
         this.tableService.saveAllEntities(list);
    }

	@Override
	public void saveContractChangeOtherInfo(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		ContractChangeOtherInfo  contractChangeOtherInfo=new ContractChangeOtherInfo();
		this.tableService.copyAndOverrideExistedValueFromStringMap(variablesMap, contractChangeOtherInfo, null, "contract_change_other_info");
		contractChangeOtherInfo.setContractId(contracinfo);
		if(variablesMap.containsKey("docId")){
			contractChangeOtherInfo.setFlowUnid(variablesMap.get("docId"));
		}
		this.tableService.saveEntity(contractChangeOtherInfo);
		
	}
	//特别付款前提
	@Override
	public void saveContractPremiseInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		String jsonContractPremiseString = variablesMap.get("json_contract_premise_str");
		if(jsonContractPremiseString!=null&&jsonContractPremiseString.length()>0){
			this.tableService.updateOneToManyCollections(contracinfo, "contractPremiseConditions", ContractPremiseCondition.class, "contractId", jsonContractPremiseString,null);
		}
	}
	@Override
	public void loadContractPremise(ContractInfo contracinfo,Map<String,String>variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_contract_premise_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_premise.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_premise_str", json_contract_premise_str);
	}
	@Override
	public void loadPremiseCondition(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_premise_condition_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_premise.xml", queryMainObjectMap).toString();
		variablesMap.put("json_premise_condition_str", json_premise_condition_str);
		
	}
	
	@Override
	public void saveContractDocList(String strWord,ContractInfo contracinfo ,Map<String, String> variablesMap) throws Exception {
		String jsonTempateString = variablesMap.get(strWord);//proj_info.projEquips
		List<ContractDocList> docList = new ArrayList<ContractDocList>();
		if((!"".equals(jsonTempateString))&&(!"[]".equals(jsonTempateString))){
			JSONArray jsonArray = new JSONArray(jsonTempateString);
			for(int i=0;i<jsonArray.length();i++){
				ContractDocList doc = new ContractDocList();
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				doc.setContractId(contracinfo);
				doc.setDocname(jsonObj.optString("filename"));
				doc.setDocType("合同文本");
				doc.setDocNo(jsonObj.optString("_id"));
				doc.setIsSubmit("√");//默认提交
				doc.setWorkflowType("1");//合同文本清单类型
				docList.add(doc);
			}
			//删除旧合同文本 数据
			String oldDocHql = "select doc from ContractDocList doc where doc.contractId= ? and doc.workflowType = '1' ";
			List<ContractDocList> oldDocList = this.tableService.findResultsByHSQL(oldDocHql, contracinfo);
			this.tableService.removeAllEntites(oldDocList);
			this.tableService.saveAllEntities(docList);
		}
	}
     //拿到客户信息的纳税人类别，纳税人识别号，开户行，开户账号，开票地址和电话
	@Override
	public void loadContractInvoiceFromCust(CustInfo custInfo,
			Map<String, String> variablesMap) throws Exception {
		
		if("CUST_INFO_COMPANY".equals(custInfo.getCustClass().getCode())){
			CustInfoCompany custinfocompany = custInfo.getCustInfoCompany();
			variablesMap.put("contract_invoice_type.taxregtype", custinfocompany.getTaxRegType()==null?"":custinfocompany.getTaxRegType().getCode());
			variablesMap.put("rawValue_contract_invoice_type.taxregtype", custinfocompany.getTaxRegType()==null?"":custinfocompany.getTaxRegType().getName());
			variablesMap.put("contract_invoice_type.taxregcode", custinfocompany.getTaxRegCode());
			variablesMap.put("contract_invoice_type.taxbank", custinfocompany.getTaxBank());
			variablesMap.put("contract_invoice_type.taxacc", custinfocompany.getTaxAcc());
			variablesMap.put("contract_invoice_type.invoiceadd", custinfocompany.getInvoiceAdd());
			variablesMap.put("contract_invoice_type.invoicephone", custinfocompany.getInvoicePhone());
		}
	}
	@Override
	public String getDeptLujingChange(String id)
			throws Exception {
		 Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		 queryMainObjectMap.put("id",id);
		 String userdept=this.tableService.getJsonArrayData("/eleasing/workflow/proj/proj_common/department_lujingchange.xml", queryMainObjectMap).toString();
		 return userdept;
	}

	@Override
	public String getcustinfo(String id) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid",id);
		String custinfo = this.tableService.getJsonArrayData("/eleasing/jsp/cust_info/cust_account/get_custinfo.xml", queryMainObjectMap).toString();
		return custinfo;
	}

	@Override
	public void loadTaxContractInfo(ContractInfo conf,
			Map<String, String> variablesMap) throws Exception {

		if(null!=conf){

			   variablesMap.put("contract_info.proj_id", conf.getProjId()==null?"":conf.getProjId().getProjId());//项目编号
			   variablesMap.put("contract_info.id", conf.getId());//合同ID
			   variablesMap.put("contract_info.contractid", conf.getContractId());//合同编号
			   variablesMap.put("contract_info.contractnumber", conf.getContractNumber());//业务合同号
			   variablesMap.put("contract_info.proj_projectname", conf.getProjectName());//项目名称 
			   variablesMap.put("contract_info.custid",conf.getCustId()==null?"":conf.getCustId().getId());//客户ID
			   variablesMap.put("contract_info.custclass",conf.getCustId()==null?"":conf.getCustId().getCustClass().getCode());//客户code			   
			   variablesMap.put("contract_info.custname",conf.getCustId()==null?"":conf.getCustId().getCustName());//客户名称
				
				
		   }
		
	}

	@Override
	public void loadTaxContractInvoiceType(ContractInvoiceType invoice,
			Map<String, String> variablesMap) throws Exception {
		 if(null!=invoice){
				variablesMap.put("contractinvoicetype.id",invoice.getId());//合同开票类型信息id
				variablesMap.put("contractInvoiceType_info.taxRegType", invoice.getTaxRegType()==null?"":invoice.getTaxRegType().getCode()); //纳税人code
				variablesMap.put("rawValue_contractInvoiceType_info.taxRegType", invoice.getTaxRegType()==null?"":invoice.getTaxRegType().getName()); //纳税人类型
				variablesMap.put("contractInvoiceType_info.taxregcode", invoice.getTaxRegCode());//纳税人编号
				variablesMap.put("contractInvoiceType_info.sparetaxBank", invoice.getTaxBank());//开户行
				variablesMap.put("contractInvoiceType_info.sparetaxacc", invoice.getTaxAcc());//开户账号
				variablesMap.put("contractInvoiceType_info.spareinvoiceAdd", invoice.getInvoiceAdd());//开票地址 
				variablesMap.put("contractInvoiceType_info.spareinvoicePhone", invoice.getInvoicePhone());//开票电话  
		  }
		  Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("id", invoice.getId());
			
			String json_tax_change_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_tax_change/contract_spare_tax_change_list.xml", queryMainObjectMap).toString();
			variablesMap.put("json_tax_change_str",json_tax_change_str);
		
	}
	/**
	 *  获得某个合同的商务信息
	  * 保证金、保证金抵扣、保证金退还、调息节点、罚息率、IRR
	 */
	@Override
	public String getContractCondition(String id) throws Exception {		
		 Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		 queryMainObjectMap.put("contract_id", id);	 
		 return  this.tableService.getJsonArrayData("/eleasing/workflow/contract/contract_comm/contract_condition_info.xml", queryMainObjectMap).toString();
		  
	}

	@Override
	public void updateContractCondition(Map<String, String> variablesMap)
			throws Exception {
		ContractCondition coninfo=this.tableService.findEntityByID(ContractCondition.class, variablesMap.get("contract_condition.id"));
		DictionaryData dict=this.tableService.findEntityByID(DictionaryData.class, variablesMap.get("adjuststyle"));
		coninfo.setAdjustStyle(dict);
		double d1 = new DecimalFormat().parse(variablesMap.get("cautiondeductionmoney")).doubleValue();
		coninfo.setCautionDeductionMoney(BigDecimal.valueOf(d1));
		double d2 = new DecimalFormat().parse(variablesMap.get("cautionmoneyremain")).doubleValue();
		coninfo.setCautionMoneyRemain(BigDecimal.valueOf(d2));
		double d3 = new DecimalFormat().parse(variablesMap.get("penarate")).doubleValue();
		coninfo.setPenaRate(BigDecimal.valueOf(d3));
		this.tableService.updateEntity(coninfo);
		
	}

	@Override
	public void saveBadAssetsInfo(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		//contracInfo:合同表/主表     badassetsInfo：合同表中定义的字段
		//contractId：在本类中定义的合同字段
		this.tableService.updateOneToOneEntity(contracinfo, "badassetsinfo", "contractId", variablesMap,null, "badassets_info");
	}
	@Override
	public void saveRiskInfo(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		this.tableService.updateOneToOneEntity(contracinfo, "riskinfo", "contractId", variablesMap,null, "risk_info");
		
	}
	@Override
	public void loadContractBadAssetsInfo(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> contractSupplieMap = new HashMap<String,String>();
		//将合同编号放进map中
		contractSupplieMap.put("contract_id", contracinfo.getId());
		//通过XML文档获取JSON数组
		String json_contract_supplier_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_badassets_info.xml", contractSupplieMap).toString();
		JSONArray json=new JSONArray(json_contract_supplier_str);
		//判断JSON数组内的值是否为空
		if (json_contract_supplier_str.length()>2) {//不为空
			//获取JSON数组第一个对象
			JSONObject o=(JSONObject) json.get(0);
			//badassets_info.financingamount：前台JSP定义的字段name属性   
			//o.get("lease_money").toString()：获取XML中的字段
			variablesMap.put("badassets_info.financingamount", o.get("lease_money").toString());
			variablesMap.put("badassets_info.financingbalance", o.get("left_corpus").toString());
			variablesMap.put("badassets_info.receiveamount", o.get("income_rent").toString());
			variablesMap.put("badassets_info.paymentamount", o.get("total_rent").toString());
		}else {//为空 ，将值全部设置为0
			variablesMap.put("badassets_info.financingamount", "0");
			variablesMap.put("badassets_info.financingbalance","0");
			variablesMap.put("badassets_info.receiveamount","0");
			variablesMap.put("badassets_info.paymentamount", "0");
		}
	}
	@Override
	public void loadContractFiveCategoryInfo(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> contractSupplieMap = new HashMap<String,String>();
		//将合同编号放进map中
		contractSupplieMap.put("contract_id", contracinfo.getId());
		//通过XML文档获取JSON数组
		String json_contract_supplier_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_badassets_info.xml", contractSupplieMap).toString();
		JSONArray json=new JSONArray(json_contract_supplier_str);
		//判断JSON数组内的值是否为空
		if (json_contract_supplier_str.length()>0) {//不为空
			//获取JSON数组第一个对象
			JSONObject o=(JSONObject) json.get(0);
			//badassets_info.financingamount：前台JSP定义的字段name属性   
			//o.get("lease_money").toString()：获取XML中的字段
			variablesMap.put("five_category.financingbalance", o.get("left_corpus").toString());
		}else {//为空 ，将值全部设置为0
			variablesMap.put("five_category.financingbalance","0");
		}
	}

	@Override
	public void saveFactoringContractEquip(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		String jsonEquipsString = variablesMap.get("json_contract_invoice_str");
		this.tableService.updateOneToManyCollections(contracinfo, "contractEquips", ContractEquip.class, "contractId", jsonEquipsString,null);
	}

	@Override
	public void loadFactoringContractEquip(ProjInfo projInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		JSONArray jsonarrayStr = this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_equip.xml", queryMainObjectMap);
		variablesMap.put("json_contract_invoice_str",jsonarrayStr.toString());
	}
	@Override
	public void loadFactoringContractEquip(ContractInfo contractInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		JSONArray jsonarrayStr = this.tableService.getJsonArrayData("eleasing/workflow/factoring/factoring_contract_change/contract_equip.xml", queryMainObjectMap);
		variablesMap.put("json_contract_invoice_str",jsonarrayStr.toString());
	}
	@Override
	public void loadContractEquipTmp(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("projInfo", projInfo);
		List<ProjEquip>  equip=this.tableService.findEntityByProperties(ProjEquip.class, propertiesMap);
		ProjEquipTmp tmp = null;
		String contractid= variablesMap.get("contract_info.contractid");
		String flowunid= variablesMap.get("flowunid");
		for(ProjEquip pe:equip){
			tmp = new ProjEquipTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pe, tmp);
			tmp.setContractId(contractid);
			tmp.setDocid(flowunid);
			tmp.setProjInfo(projInfo);
            this.tableService.saveEntity(tmp);
          }
	}
	
	@Override
	public void loadContractGuaranteeEquipTmp(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("projInfo", projInfo);
		List<ProjGuaranteeEquip>  equip=this.tableService.findEntityByProperties(ProjGuaranteeEquip.class, propertiesMap);
		ProjGuaranteeEquipTmp tmp = null;
		String contractid= variablesMap.get("contract_info.contractid");
		String flowunid= variablesMap.get("flowunid");
		for(ProjGuaranteeEquip pe:equip){
			tmp = new ProjGuaranteeEquipTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pe, tmp);
			tmp.setContractId(contractid);
			tmp.setDocid(flowunid);
			tmp.setProjInfo(projInfo);
            this.tableService.saveEntity(tmp);
          }
	}
	@Override
	public void loadContractGuaranteeMethodTmp(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,Object>propertiesMap=new HashMap<String,Object>();
		propertiesMap.put("projInfo", projInfo);
		List<ProjGuaranteeMethod>  equip=this.tableService.findEntityByProperties(ProjGuaranteeMethod.class, propertiesMap);
		ProjGuaranteeMethodTmp projguaranteemethodtmp = null;
		String contractid= variablesMap.get("contract_info.contractid");
		String flowunid= variablesMap.get("flowunid");
		for(ProjGuaranteeMethod pe:equip){
			projguaranteemethodtmp = new ProjGuaranteeMethodTmp();
			this.tableService.copyAndOverrideExistedValueFromObjectNoSet(pe, projguaranteemethodtmp);
			projguaranteemethodtmp.setContractId(contractid);
			projguaranteemethodtmp.setDocid(flowunid);
			projguaranteemethodtmp.setProjInfo(projInfo);
            this.tableService.saveEntity(projguaranteemethodtmp);
          }
	 }

	@Override
	public void loadProjPaymentPremiseCondition(ProjInfo projInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		variablesMap.put("json_payment_premise_condition_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/payment_premise_condition.xml", queryMainObjectMap).toString());
	}
	
	
	
	@Override
	public void saveProjPaymentPremiseCondition(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		// 付款前提条件
		String jsonpaymentpremisecondition = variablesMap.get("json_payment_premise_condition_str");
	    this.tableService.updateOneToManyCollections(contracinfo, "contractPayments", ContractPaymentPremiseCondition.class, "contractId", jsonpaymentpremisecondition,null);		
	}

	@Override
	public void loadContractPaymentPremiseCondition(ContractInfo contractInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		variablesMap.put("json_payment_premise_condition_str", this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/conpayment_premise_condition.xml", queryMainObjectMap).toString());
	}
	
	@Override
	public void loadSupplierorSignat(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		   String  leas_form=contractInfo.getLeasForm().getId();
		   Map<String,Object> propertiesMap = new HashMap<String,Object>();
		    if(leas_form.matches("lease_form2|lease_form6")){//承租人
			      propertiesMap.clear();
			      propertiesMap.put("contractId", contractInfo);//条件根据合同号ID查询出合同各方表中的数据ContractSignatory
			      List<ContractSignatory> csList = this.tableService.findEntityByProperties(ContractSignatory.class, propertiesMap);
			      ContractSignatory cst=null;
			      for(int i=0;csList.size()>i;i++){
			    	  cst=csList.get(0);
			    	  if(cst!=null){
			    		  variablesMap.put("contract_signatory.client",cst.getClient());
				          variablesMap.put("contract_signatory.clientaccnumber",cst.getClientAccNumber());
				    	  variablesMap.put("contract_signatory.clientaccbank",cst.getClientAccBank());
				    	  variablesMap.put("contract_signatory.clientaccname",cst.getClientAccName());
				    	  variablesMap.put("contract_signatory.client1",cst.getClient());
				    	  variablesMap.put("contract_signatory.clientaccnumber1",cst.getClientAccNumber());
				    	  variablesMap.put("contract_signatory.clientaccbank1",cst.getClientAccBank());
				    	  variablesMap.put("contract_signatory.clientaccname1",cst.getClientAccName());
			    	  }
			      }
		    }else  if(leas_form.matches("lease_form1|leas_form7")){//供应商
		      propertiesMap.clear();
		      propertiesMap.put("contractId", contractInfo);//条件根据合同号ID查询出多个供应商
		      List<ContractSupplierInfo> accountList = this.tableService.findEntityByProperties(ContractSupplierInfo.class, propertiesMap);
		       ContractSupplierInfo csinfo=null;
		       for(int i=0;accountList.size()>i;i++){
		          csinfo=accountList.get(0);
		          if(csinfo!=null){
		              CustInfo custinfo=this.tableService.findEntityByID(CustInfo.class, csinfo.getSeller());//根据custid查询出供应商名称
			          variablesMap.put("contract_signatory.client1",custinfo.getCustName());
			          variablesMap.put("contract_signatory.clientaccnumber1",csinfo.getSellerAccNumber());
			    	  variablesMap.put("contract_signatory.clientaccbank1",csinfo.getSellerAccBank());
			    	  variablesMap.put("contract_signatory.clientaccname1",csinfo.getSellerAccName());
			    	  variablesMap.put("contract_signatory.client",custinfo.getCustName());
				      variablesMap.put("contract_signatory.clientaccnumber",csinfo.getSellerAccNumber());
				      variablesMap.put("contract_signatory.clientaccbank",csinfo.getSellerAccBank());
				      variablesMap.put("contract_signatory.clientaccname",csinfo.getSellerAccName()); 
		          }
		       }
		       
		    }else{
		    	
		    	
		    }
	}

	@Override
	public void loadIncomeDiscount(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap=new HashMap<String, String>();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		variablesMap.put("json_income_discount_str", this.tableService.getJsonArrayData("eleasing/workflow/contract/finance/finance_income_discount.xml",queryMainObjectMap).toString());
	}

	@Override
	public void showContractAchives(Map<String, String> variablesMap) throws Exception {
		String contract_id=variablesMap.get("id");
		User user = (User) SecurityUtil.getPrincipal();
		String userid=user.getId();
		String creattime=DateUtil.getSystemTimeByFormat("yyyy-MM-dd");
		Map<String, String> querymap = new HashMap<String, String>();
		String addsql =""; 
				addsql = this.tableService.getTableXMLJsonData("docarchives/contract_archives_add.xml", querymap);
		try {
			String a=	addsql.replaceAll("\\{[contractid}]*\\}", contract_id);
			String b=	a.replaceAll("\\{[userid}]*\\}", userid);
			String c=	b.replaceAll("\\{[creattime}]*\\}", creattime);
			addsql=c;
	        reportDao.getJdbcTemplate().execute(addsql);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String baddsql=          "  insert into CONTRACT_FILING ( "+
		         "   id, "+
		         "   filing_status, "+
		         "   FILING_SN, filing_name,"+
		         "   comein_date, CREATOR_,CREATE_DATE,"+
		         "   contract_id "+
		         "   ) "+
		         "   (select  "+
		         "   sys_guid(), "+
		         "   '未归档', "+
		         "   c.id, c.contract_name,"+
		         "   c.signing_time, '"+user.getId()+"','"+creattime+
		         "',   c.contract_id "+
		         "   from base_file b "+
		         "   left join CONTRACT_NUMBER_SETTING c on c.id=b.file_key "+
		         "   where c.contract_id='"+contract_id+"'  and not exists (select 1 from CONTRACT_FILING cf where cf.FILING_SN = c.id))"+
		   "union (select sys_guid(),   "+
		   "                '未归档',   "+
		   "                t.id_,   "+
		   "                subStr(t.chinese_file_name_,   "+
		   "                       0,   "+
		   "                       INSTR(t.chinese_file_name_, '.', -1) - 1),   "+
		   "                t.upload_time_,   '"+user.getId()+"','"+creattime+
		   "',               ci.id   "+
		   "           from t_attachment_info_detail t   "+
		   "           left join t_attachment_info ta   "+
		   "             on t.attachment_info_id_ = ta.id_   "+
		   "           left join t_dicts_datas t1   "+
		   "             on t1.id_ = ta.attachment_file_dict_id_   "+
		   "           left join proj_info p   "+
		   "             on ta.identifier_two_ = p.proj_id   "+
		   "           left join contract_info  ci on  ci.proj_id=p.id    "+
		   "          where ta.attachment_file_dict_id_ in   "+
		   "                ('root.FileType.Type6.01',   "+
		   "                 'FinancialAdjustment.01',   "+
		   "                 'root.FileType.Type6.01',   "+
		   "                 'LegalRegulation.01',   "+
		   "                 'BusinessAdjustmentReconsider.01',   "+
		   "                 'BusinessAdjustment.01',  "+
		   "                 'root.FileType.Proposal01',   "+
		   "                 'ProjectQuotationScheme.01'  "+
		   "                 )   "+"and ci.id='"+contract_id+"'"+
		   "        and not exists (select 1 from CONTRACT_FILING cf where cf.FILING_SN = t.id_))"+
		   "union( select   "+
		   " sys_guid(),  "+
		   " '未归档',  "+
		   " a.id,  "+
		   " subStr(a.file_name, 0,INSTR(a.file_name, '.', -1) - 1),  "+
		   " a.create_date,  '"+user.getId()+"','"+creattime+
		   "', ci.id    "+
		   " from (  "+
		   " select * from base_file b where b.model_name='项目批复文件' and b.invalid_='是' and b.file_key in (  "+
		   " select id from PROJ_APPROVAL_DOC  "+
		   " ))a  "+
		   " left join PROJ_APPROVAL_DOC p on  a.file_key=p.id  "+
		   " left join proj_develope_info pdi on p.proj_id=pdi.id   "+
		   " left join proj_info pi on pi.develop_id=pdi.id   "+
		   " left join contract_info ci on ci.proj_id=pi.id  "+
		   "  where ci.id='"+contract_id+"' and not exists (select 1 from CONTRACT_FILING cf where cf.FILING_SN = a.id)  )"+
		"union ( select   "+
		 " sys_guid(),  "+
		 " '未归档',  "+
		 " a.id,  "+
		 " subStr(a.file_name, 0,INSTR(a.file_name, '.', -1) - 1),  "+
		 " a.create_date,  '"+user.getId()+"','"+creattime+
		 "', p.contract_id   "+
		 " from (  "+
		 " select * from base_file b where b.model_name='设备信息' and b.invalid_='是' and b.file_key in (  "+
		 " select id from PLEDGE  "+
		 " ))a  "+
		 " left join PLEDGE p on  a.file_key=p.id  "+
		 "  where   p.contract_id='"+contract_id+"' and not exists (select 1 from CONTRACT_FILING cf where cf.FILING_SN = a.id)  )";
		
		
	}

	@Override
	public void saveConTradeTransaction(ContractInfo contracinfo,
			Map<String, String> variablesMap) throws Exception {
		this.tableService.updateOneToOneEntity(contracinfo, "conTradeTransaction", "contractId", variablesMap,null, "contrade_transaction");
		
	}

	@Override
	public void loadTradeTransaction(ContractInfo contractid,
			Map<String, String> variablesMap) throws Exception {
		ConTradeTransaction conTradeTransaction = contractid.getConTradeTransaction();
		if(null!=conTradeTransaction){
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(conTradeTransaction, null, "contrade_transaction"));
		}
	}

	@Override
	public void loadConTradeTransactionFromProj(ProjInfo projinfo,
			Map<String, String> variablesMap) throws Exception {
		TradeBasedTransactions tradeBasedTransactions = projinfo.getTradeBasedTransactions();
		if(null!=tradeBasedTransactions){
		  variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(tradeBasedTransactions, null, "contrade_transaction"));
		}
		
	}

	@Override
	public void saveOrUpdateTiqianFundInvoiceInfo(String[] idsArray,Map<String, String> variablesMap) throws Exception {
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		for (String id : idsArray) {
			ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, id);
			//增加后台判断是否有实收开票
			//1、查询出该计划对应的实收记录
			Set<ContractFundFundCharge> cincomes=contractFundFundPlan.getFundFundCharges();
			//2、查询实收是否存在开票记录
			for(ContractFundFundCharge ci:cincomes){
				//查询实收开发票
				propertiesMapin.put("cffcId", ci);
				propertiesMapin.put("billType","invoice");
				List<FundInvoiceInfo> infoListin = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapin);
				if (infoListin.size()>0) {
					throw new BusinessException("合同号："+ci.getContractId().getContractId()+"的"+ci.getFeeType().getName()+"有实收开票记录，不能计划开票!");
				}
			}
			FundInvoiceInfo info = new FundInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("cffpId", contractFundFundPlan);
			propertiesMap.put("billType","invoice");
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffpId(contractFundFundPlan);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setMoney(contractFundFundPlan.getPlanMoney());//提交开票金额
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		String ids="";
	      if(list.size()>0){
	             this.tableService.saveOrUpdateAllEntities(list);
	             for(int y=0;y<list.size();y++){
	            	 FundInvoiceInfo finfo=list.get(y);
	            	 if(y==list.size()-1){
	            		 ids+=finfo.getId();
	            	 }else{
	            		 ids+=finfo.getId()+",";
	            	 }
	             }
	      }
	      variablesMap.put("contract_info.instrids", ids);
	}
	@Override
	public void saveOrUpdateShishouFundInvoiceInfo(String  datas,Map<String, String> variablesMap) throws Exception {
		Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
		JSONArray curArray=new JSONArray(datas);
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String pid=jobj.getString("pid");
			//计算该笔实收对应的计划金额
			String dd = "".equals(jobj.getString("planmoney"))?"0":jobj.getString("planmoney");
			BigDecimal planmoney=new BigDecimal(dd).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			mapplan.put(pid, planmoney);
			
			//计算该笔实收对应的本次实收金额
			BigDecimal factmoney=new BigDecimal(jobj.getString("factmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			if(mapfact.containsKey(pid)){
				mapfact.put(pid, mapfact.get(pid).add(factmoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
			}else{
				mapfact.put(pid, factmoney);
			}
			
			//计算该笔实收对应的计划已经申请的开票金额
			ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, pid);
			//查询出计划对应的实收信息
			BigDecimal inmoney=BigDecimal.ZERO;
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("fundFundChargePlan", contractFundFundPlan);
			List<ContractFundFundCharge> cchargelist = this.tableService.findEntityByProperties(ContractFundFundCharge.class, propertiesMap);
			for(ContractFundFundCharge cc:cchargelist){
				Map<String, Object> propertiesMap1=new HashMap<String, Object>();
				propertiesMap1.put("cffcId", cc);
				propertiesMap1.put("billType","invoice");
				propertiesMap1.put("invoiceStatus",2);//状态为已申请的金额
				List<FundInvoiceInfo> filist = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap1);
				for(FundInvoiceInfo fi:filist){
					inmoney=inmoney.add(fi.getMoney()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
				
			}
			mapinmoney.put(pid, inmoney);
		}
		
		///判断当前是否存在实收提交金额大于计划金额
		Iterator<?> iter = mapplan.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			BigDecimal val = (BigDecimal) entry.getValue();
			BigDecimal factval =mapfact.get(key).add(mapinmoney.get(key));
			//计划金额与数据库已提交金额+当前提交金额比较
			if(val.compareTo(factval)==-1){
				ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, key);
				throw new BusinessException("合同号："+contractFundFundPlan.getContractId().getContractId()+"的"+contractFundFundPlan.getFeeType().getName()+"提交金额超过计划金额，计划金额："+val+",本次提交申请金额："+mapfact.get(key)+"，已提交金额："+mapinmoney.get(key));
			}
		}
		
		List<FundInvoiceInfo> list = new ArrayList<FundInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		Map<String, Object> propertiesMapin=new HashMap<String, Object>();
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String id=jobj.getString("id");
			FundInvoiceInfo info = new FundInvoiceInfo();
			//contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, paymentidsArray[i]);
			ContractFundFundCharge contractFundFundCharge = this.tableService.findEntityByID(ContractFundFundCharge.class, id);
			
			//增加后台判断是否有实收开票
			//1、查询出该实收对应的计划记录
			ContractFundFundPlan cp=contractFundFundCharge.getFundFundChargePlan();
			//2、查询计划是否存在开票记录
			//查询实收开发票
			propertiesMapin.put("cffpId", cp);
			propertiesMapin.put("billType","invoice");
			List<FundInvoiceInfo> infoListin = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMapin);
			if (infoListin.size()>0) {
				throw new BusinessException("合同号："+cp.getContractId().getContractId()+"的"+cp.getFeeType().getName()+"有计划开票记录，不能实收开票!");
			}
			//查询实收开发票
			propertiesMap.put("cffcId", contractFundFundCharge);
			propertiesMap.put("billType","invoice");
			List<FundInvoiceInfo> infoList = this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCffcId(contractFundFundCharge);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setMoney(contractFundFundCharge.getFactMoney());//提交开票金额
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		String ids="";
          if(list.size()>0){
             this.tableService.saveOrUpdateAllEntities(list);
             for(int y=0;y<list.size();y++){
            	 FundInvoiceInfo finfo=list.get(y);
             	 if(y==list.size()-1){
            		 ids+=finfo.getId();
            	 }else{
            		 ids+=finfo.getId()+",";
            	 }
             }
          }
          variablesMap.put("contract_info.instrids", ids);
	}
	@Override
	public void deleteFundInvoiceInfo(Map<String, String> variablesMap) throws Exception {
	String tsdxml=variablesMap.get("tsdxml");
	String allid = variablesMap.get("id");
	String[] idsArray = allid.split(",");
	String datas=variablesMap.get("datas");
	JSONArray curArray=new JSONArray(datas);
    List<FundInvoiceInfo> listinfo=new ArrayList<FundInvoiceInfo>();
    List<RentInvoiceInfo> rentinfo=new ArrayList<RentInvoiceInfo>();
	Map<String, Object> propertiesMap=new HashMap<String, Object>();
	    if("planshishou".equals(tsdxml)){//资金实收
	    	for (String id : idsArray) {
	    		ContractFundFundCharge contractFundFundCharge = this.tableService.findEntityByID(ContractFundFundCharge.class, id);
				propertiesMap.clear();
				propertiesMap.put("cffcId",contractFundFundCharge);
				List<FundInvoiceInfo> list=this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
				if(list.size()>0){
					FundInvoiceInfo	info=list.get(0);
					listinfo.add(info);
				}
	    	}
	    }else if("plantiqian".equals(tsdxml)){//资金提前
	    	for (String id : idsArray) {
				ContractFundFundPlan contractFundFundPlan = this.tableService.findEntityByID(ContractFundFundPlan.class, id);
				propertiesMap.clear();
				propertiesMap.put("cffpId",contractFundFundPlan);
				List<FundInvoiceInfo> list=this.tableService.findEntityByProperties(FundInvoiceInfo.class, propertiesMap);
				if(list.size()>0){
					FundInvoiceInfo	info=list.get(0);
					listinfo.add(info);
				}
	    	}
	    }else if("renttiqian".equals(tsdxml)){//租金提前
	    	for (int y=0;y<curArray.length();y++) {
	    		JSONObject jobj=curArray.getJSONObject(y);
	    		String id=jobj.getString("id");
	    		String taxType=jobj.getString("type");
	    		ContractFundRentPlan contractfundrentplan = this.tableService.findEntityByID(ContractFundRentPlan.class, id);
	    		propertiesMap.clear();
				propertiesMap.put("cfrpId",contractfundrentplan);
				propertiesMap.put("taxType",taxType);
				List<RentInvoiceInfo> list=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				if(list.size()>0){
					for(int i =0;i<list.size();i++){
						RentInvoiceInfo	info=list.get(i);
						rentinfo.add(info);
					}
				}

	    	}
	    }else{//租金实收
	    	for (int y=0;y<curArray.length();y++) {
	    		JSONObject jobj=curArray.getJSONObject(y);
	    		String id=jobj.getString("id");
	    		String taxType=jobj.getString("type");
	    		ContractFundRentInCome contractfundrentincome = this.tableService.findEntityByID(ContractFundRentInCome.class, id);
	    		propertiesMap.clear();
				propertiesMap.put("cfriId",contractfundrentincome);
				propertiesMap.put("taxType",taxType);
				List<RentInvoiceInfo> list=this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
				if(list.size()>0){
					for(int i =0;i<list.size();i++){
						RentInvoiceInfo	info=list.get(i);
						rentinfo.add(info);
					}
				}

	    	}
	    }
	    if(listinfo.size()>0){//删除资金开票
		    this.tableService.removeAllEntites(listinfo);	
	    }
	    if(rentinfo.size()>0){//删除租金开票
		    this.tableService.removeAllEntites(rentinfo);	
	    }
	}

	@Override
	public void saveContractPlanInvoice(Map<String, String> variablesMap)
			throws Exception {
		String jsoninvoice_str=variablesMap.get("json_contract_invoice_confirm_str");
		JSONArray curArray=new JSONArray(jsoninvoice_str);
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String id=jobj.getString("id");
			sb.append(id+",");
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length() - 1);
		}
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("instrids",sb.toString());
		JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoice_confirm.xml", queryMainObjectMap);
		JSONArray newexportdata = new JSONArray();
		DictionaryData dictmoney=this.tableService.findEntityByID(DictionaryData.class,"invoicemoney.01");
		BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); 
		for(int i=0;i<exportdata.length();i++){
			JSONObject jsonObj = exportdata.getJSONObject(i);
			BigDecimal currentexportmoney=new BigDecimal(jsonObj.optString("currentmoney"));
			BigDecimal taxrate=new BigDecimal(jsonObj.optString("taxrate"));
			BigDecimal reone = BigDecimal.ONE;
			if (currentexportmoney.compareTo(leavelRent) == 1) {
				int floor = currentexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
				String serialNumber="";
				for (int j = 0; j < floor; j++) {
					BigDecimal invoicemoney=leavelRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
					invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
					serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					jsonObj1.put("taxmoney",invoicemoney);
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",leavelRent);//导出金额
					jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
					jsonObj1.remove("id");
					newexportdata.put(jsonObj1);
				}
				BigDecimal residueRent = currentexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
				if(residueRent.compareTo(BigDecimal.ZERO)==1){
					BigDecimal invoicemoney=residueRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
					invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
					serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					jsonObj1.put("taxmoney",invoicemoney);
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",residueRent);//导出金额
					jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
					jsonObj1.remove("id");
					newexportdata.put(jsonObj1);
				}
			} else {
				JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
				String serialNumber = "PF"+WorkflowUtil.getFundInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				jsonObj1.put("outno", serialNumber);//导出流水号
				jsonObj1.put("invoicemoney",currentexportmoney);//导出金额
				jsonObj1.put("fundInvoiceId",jsonObj.optString("id"));
				jsonObj1.remove("id");
				newexportdata.put(jsonObj1);
			}
		}
		Map<String,String> dictDataClassMapping = new HashMap<String,String>();
		List<FundInvoiceDownloadInfo> infos=(List<FundInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(FundInvoiceDownloadInfo.class, newexportdata.toString(), dictDataClassMapping, "");
		this.tableService.saveOrUpdateAllEntities(infos);
	}
	@Override
	public void saveOrUpdateTiqianRentInvoiceInfo(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			ContractFundRentPlan contractRentRentPlan = this.tableService.findEntityByID(ContractFundRentPlan.class, jobj.getString("id"));
			//增加后台判断是否有实收开票
			//1、查询出该计划对应的实收记录
			Set<ContractFundRentInCome> cincomes=contractRentRentPlan.getContractFundRentInComes();
			//2、查询实收是否存在开票记录
			for(ContractFundRentInCome ci:cincomes){
				//查询实收开发票
				propertiesMapin.put("cfriId", ci);
				propertiesMapin.put("billType","invoice");
				propertiesMapin.put("taxType",jobj.getString("type"));
				List<RentInvoiceInfo> infoListin = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMapin);
				if (infoListin.size()>0) {
					throw new BusinessException("合同号："+contractRentRentPlan.getContractId().getContractId()+"第"+contractRentRentPlan.getRentList()+"期有实收开票记录，不能计划开票!");
				}
			}
			RentInvoiceInfo info = new RentInvoiceInfo();
			//查询计划开发票
			propertiesMap.put("cfrpId", contractRentRentPlan);
			propertiesMap.put("billType","invoice");
			propertiesMap.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCfrpId(contractRentRentPlan);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setTaxType(jobj.getString("type"));
				info.setMoney(new BigDecimal(jobj.getString("planmoney")));
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		String ids="";
	      if(list.size()>0){
	  		this.tableService.saveOrUpdateAllEntities(list);
	             for(int y=0;y<list.size();y++){
	            	 RentInvoiceInfo finfo=list.get(y);
	            	 if(y==list.size()-1){
	            		 ids+=finfo.getId();
	            	 }else{
	            		 ids+=finfo.getId()+",";
	            	 }
	             }
	      }
	      model.put("contract_info.instrids", ids);
	}
	//租金实收
	@Override
	public void saveOrUpdateShishouRentInvoiceInfo(Map<String, String> model) throws Exception {
		String datas = model.get("datas");
		Map<String,BigDecimal> mapplan=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapfact=new HashMap<String, BigDecimal>();
		Map<String,BigDecimal> mapinmoney=new HashMap<String, BigDecimal>();
		JSONArray curArray=new JSONArray(datas);
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String pid=jobj.getString("pid");
			String type=jobj.getString("type");
			//计算该笔实收对应的计划金额
			String spid=pid+"_"+type;
			BigDecimal planmoney=new BigDecimal(jobj.getString("planmoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			mapplan.put(spid, planmoney);
			
			//计算该笔实收对应的本次实收金额
			BigDecimal incomemoney=new BigDecimal(jobj.getString("incomemoney")).setScale(2,BigDecimal.ROUND_HALF_EVEN);
			if(mapfact.containsKey(spid)){
				mapfact.put(spid, mapfact.get(spid).add(incomemoney).setScale(2,BigDecimal.ROUND_HALF_EVEN));
			}else{
				mapfact.put(spid, incomemoney);
			}
			
			//计算该笔实收对应的计划已经申请的开票金额
			ContractFundRentPlan cplan = this.tableService.findEntityByID(ContractFundRentPlan.class, pid);
			//查询出计划对应的实收信息
			BigDecimal inmoney=BigDecimal.ZERO;
			Map<String, Object> propertiesMap=new HashMap<String, Object>();
			propertiesMap.put("planId", cplan);
			List<ContractFundRentInCome> cchargelist = this.tableService.findEntityByProperties(ContractFundRentInCome.class, propertiesMap);
			for(ContractFundRentInCome cc:cchargelist){
				if(null!=cc.getRollBack()&&!"0".equals(cc.getRollBack())){
					continue;
				}
				Map<String, Object> propertiesMap1=new HashMap<String, Object>();
				propertiesMap1.put("cfriId", cc);
				propertiesMap1.put("billType","invoice");
				propertiesMap1.put("taxType",type);
				propertiesMap1.put("invoiceStatus",2);//状态为已申请的金额
				List<RentInvoiceInfo> filist = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap1);
				for(RentInvoiceInfo fi:filist){
					inmoney=inmoney.add(fi.getMoney()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				}
			}
			mapinmoney.put(spid, inmoney);
		}
		
		///判断当前是否存在实收提交金额大于计划金额
		Iterator<?> iter = mapplan.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			if(key.indexOf("penalty") != -1){continue;}
			BigDecimal val = (BigDecimal) entry.getValue();
			BigDecimal factval =mapfact.get(key).add(mapinmoney.get(key));
			//计划金额与数据库已提交金额+当前提交金额比较
			if(val.compareTo(factval)==-1){
				String cpid=key.split("_")[0];
				ContractFundRentPlan cfp = this.tableService.findEntityByID(ContractFundRentPlan.class, cpid);
				throw new BusinessException("合同号："+cfp.getContractId().getContractId()+"的第"+cfp.getRentList()+"期租金提交金额超过计划金额，<br>计划金额："+val+",本次提交申请金额："+mapfact.get(key)+"，已提交金额："+mapinmoney.get(key));
			}
		}
		
		List<RentInvoiceInfo> list = new ArrayList<RentInvoiceInfo>();
		User user = (User) SecurityUtil.getPrincipal();
		String createDate = DateUtil.getSystemDateTime();
		Map<String,Object> propertiesMap = new HashMap<String, Object>();
		Map<String,Object> propertiesMapin = new HashMap<String, Object>();
		JSONArray idsArray=new JSONArray(datas);
		for (int i=0;i<idsArray.length();i++) {
			JSONObject jobj=idsArray.getJSONObject(i);
			ContractFundRentInCome contractRentRentIncome = this.tableService.findEntityByID(ContractFundRentInCome.class, jobj.getString("id"));
			//增加后台判断是否有实收开票
			//1、查询出该实收对应的计划记录
			ContractFundRentPlan cp=contractRentRentIncome.getPlanId();
			//2、查询计划是否存在开票记录
			//查询实收开发票
			propertiesMapin.put("cfrpId", cp);
			propertiesMapin.put("billType","invoice");
			propertiesMapin.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoListin = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMapin);
			if (infoListin.size()>0) {
				throw new BusinessException("合同号："+cp.getContractId().getContractId()+"第"+cp.getRentList()+"期有计划开票记录，不能实收开票!");
			}
			
			RentInvoiceInfo info = new RentInvoiceInfo();
			//查询实收开发票
			propertiesMap.put("cfriId", contractRentRentIncome);
			propertiesMap.put("billType","invoice");
			propertiesMap.put("taxType",jobj.getString("type"));
			List<RentInvoiceInfo> infoList = this.tableService.findEntityByProperties(RentInvoiceInfo.class, propertiesMap);
			if (infoList.size() == 0) {
				info.setCfriId(contractRentRentIncome);
				info.setInvoiceStatus(2);//已申请
				info.setBillType("invoice");//发票
				info.setTaxType(jobj.getString("type"));
				info.setMoney(new BigDecimal(jobj.getString("incomemoney")));
				info.setCreator(user);
				info.setCreateDate(createDate);
			} else {
				info = infoList.get(0);
				if(info.getInvoiceStatus()==2){//已申请开票不能再申请
					throw new BusinessException("已申请开票不能再申请！！");
				}else{
					info.setInvoiceStatus(2);
					info.setModificator(user);
					info.setModifyDate(createDate);
				}
			}
			list.add(info);
		}
		String ids="";
	      if(list.size()>0){
	    		this.tableService.saveOrUpdateAllEntities(list);
	             for(int y=0;y<list.size();y++){
	            	 RentInvoiceInfo finfo=list.get(y);
	            	 if(y==list.size()-1){
	            		 ids+=finfo.getId();
	            	 }else{
	            		 ids+=finfo.getId()+",";
	            	 }
	             }
	      }
	      model.put("contract_info.instrids", ids);
	}

	@Override
	public void saveContractRentInvoice(Map<String, String> variablesMap)
			throws Exception {
		String jsoninvoice_str=variablesMap.get("json_contract_rentinvoice_list_str");
		JSONArray curArray=new JSONArray(jsoninvoice_str);
		StringBuffer sb=new StringBuffer();
		for (int i=0;i<curArray.length();i++) {
			JSONObject jobj=curArray.getJSONObject(i);
			String id=jobj.getString("id");
			sb.append(id+",");
		}
		if(sb.length()>0){
			sb.deleteCharAt(sb.length() - 1);
		}
		try {
			Map<String,String> queryMainObjectMap = new HashMap<String,String>();
			queryMainObjectMap.put("instrids",sb.toString());
			queryMainObjectMap.put("status","已申请");
			JSONArray exportdata=this.tableService.getJsonArrayData("eleasing/jsp/invoice_manage/rent_invoice/invoice_confirm.xml", queryMainObjectMap);
			JSONArray newexportdata = new JSONArray();
			DictionaryData dictmoney=this.tableService.findEntityByID(DictionaryData.class,"invoicemoney.01");
			BigDecimal leavelRent =new BigDecimal(dictmoney.getDescription()); //BigDecimal.valueOf(100);
			//同一个合同的罚息合并到rent_invoice_download_info
			Map<String,BigDecimal> cids=new HashMap<String, BigDecimal>();
			//记录合并前的 rent_invoice_info 
			Map<String,String> riis=new HashMap<String, String>();
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				if("罚息2".equals(jsonObj.optString("plantype"))){
					BigDecimal currentmoney=new BigDecimal(jsonObj.optString("currentmoney"));
					if(cids.containsKey(jsonObj.optString("cid"))){
						currentmoney=new BigDecimal(String.valueOf(cids.get(jsonObj.optString("contractid")))).add(currentmoney);
					}
					cids.put(jsonObj.optString("cid"), currentmoney);
					riis.put(jsonObj.optString("id"), jsonObj.optString("cid"));
				}
			}
			for(String key:cids.keySet()){
				JSONArray penaltyexportdata = new JSONArray();
				if(cids.get(key).compareTo(leavelRent)>0){
					throw new BusinessException("罚息合并金额【"+cids.get(key)+"】超出了单张发票限额【"+leavelRent+"】 -> 不能合并！");
				}
				String serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
				JSONObject jsonObj1=new JSONObject();
				jsonObj1.put("outno", serialNumber);//导出流水号
				jsonObj1.put("invoicemoney",cids.get(key));//导出金额
				jsonObj1.put("contractId", key);
				jsonObj1.remove("id");
				penaltyexportdata.put(jsonObj1);
				List<RentInvoiceDownloadInfo> penaltyinfos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, penaltyexportdata.toString(), null, "");
				this.tableService.saveOrUpdateAllEntities(penaltyinfos);
				for(Map.Entry<String, String> entry: riis.entrySet()){
					if(entry.getValue().equals(key)){
						RentInvoiceInfo rii=this.tableService.findEntityByID(RentInvoiceInfo.class, entry.getKey());
						rii.setRidiId(penaltyinfos.get(0));
						this.tableService.updateEntity(rii);
					}
				}
			}
			for(int i=0;i<exportdata.length();i++){
				JSONObject jsonObj = exportdata.getJSONObject(i);
				//if("罚息".equals(jsonObj.optString("plantype")))continue;
				RentInvoiceInfo ri=this.tableService.findEntityByID(RentInvoiceInfo.class, jsonObj.optString("id"));
				BigDecimal currentexportmoney=new BigDecimal(jsonObj.optString("currentmoney"));
				BigDecimal taxrate=new BigDecimal(jsonObj.optString("taxrate"));
				BigDecimal reone = BigDecimal.ONE;
				if (currentexportmoney.compareTo(leavelRent) == 1) {
					int floor = currentexportmoney.divide(leavelRent, 0, BigDecimal.ROUND_FLOOR).intValue();
					String serialNumber="";
					for (int j = 0; j < floor; j++) {
						BigDecimal invoicemoney=leavelRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
						invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
						serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("taxmoney",invoicemoney);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",leavelRent);//导出金额
						jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
						jsonObj1.remove("id");
						newexportdata.put(jsonObj1);
					}
					
					BigDecimal residueRent = currentexportmoney.subtract(leavelRent.multiply(BigDecimal.valueOf(floor)));
					if(residueRent.compareTo(BigDecimal.ZERO)==1){
						BigDecimal invoicemoney=residueRent.multiply(taxrate).divide(reone.add(taxrate), 20, BigDecimal.ROUND_HALF_UP);
						invoicemoney.setScale(2, BigDecimal.ROUND_HALF_UP);
						serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
						JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
						jsonObj1.put("taxmoney",invoicemoney);
						jsonObj1.put("outno", serialNumber);//导出流水号
						jsonObj1.put("invoicemoney",residueRent);//导出金额
						jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
						jsonObj1.remove("id");
						newexportdata.put(jsonObj1);
					}
					
				} else {
					JSONObject jsonObj1=BeanFieldUtil.deepJonsobject(jsonObj);
					String serialNumber = "PR"+WorkflowUtil.getRentInvoiceSerialNumber(null, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
					jsonObj1.put("outno", serialNumber);//导出流水号
					jsonObj1.put("invoicemoney",currentexportmoney);//导出金额
					jsonObj1.put("rentInvoiceId",jsonObj.optString("id"));
					jsonObj1.remove("id");
					newexportdata.put(jsonObj1);
				}
				
			}
			List<RentInvoiceDownloadInfo> infos=(List<RentInvoiceDownloadInfo>) this.tableService.getEntitiesByJSONArrayString(RentInvoiceDownloadInfo.class, newexportdata.toString(), null, "");
			this.tableService.saveOrUpdateAllEntities(infos);
			//this.tableService.updateFlush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
			
		}
		
	}
	
	//合同编号设置
	@Override
	public void saveContractNumberSettingInfo(ContractInfo contract,Map<String, String> variablesMap) throws Exception {
		 Map<String,Object>propertiesMap=new HashMap<String,Object>();
		 String docid= variablesMap.get("docid");
		 propertiesMap.put("contractId",contract);
		 propertiesMap.put("docid", docid);
		 JSONArray jsonarrayupdate=new JSONArray();
		 String json_contract_change_numset_str=variablesMap.get("json_contract_change_numset_str");
		List<ContractNumberSettingTmp>  cnstlist=this.tableService.findEntityByProperties(ContractNumberSettingTmp.class, propertiesMap);
		ContractNumberSetting contractNumberSetting = null;
		for(ContractNumberSettingTmp cnst:cnstlist){
			String savestatus = cnst.getSaveStatus();
			ContractNumberSetting oldid =this.tableService.findEntityByID(ContractNumberSetting.class,cnst.getOldId()==null?"000000":cnst.getOldId());
			contractNumberSetting = new ContractNumberSetting();
			if((oldid!=null)&&(!"删除".equals(savestatus))){
				 JSONArray jsonarray=new JSONArray(json_contract_change_numset_str);
				 for(int i=0;jsonarray.length()>i;i++){
						JSONObject jsonobj=jsonarray.getJSONObject(i);
						String tmpid=jsonobj.getString("id");
						if(cnst.getId().equals(tmpid)&&cnst.getOldId().equals(oldid.getId())){
							jsonobj.put("id", oldid.getId());
							jsonarrayupdate.put(jsonobj);
						}
					   }
			}else if((oldid!=null)&&("删除".equals(savestatus))){
				this.tableService.removeEntity(oldid);
			}else if((oldid==null || oldid.equals(""))&&(savestatus==null||savestatus.equals(""))){
				contractNumberSetting.setContractId(contract);
				this.tableService.copyAndOverrideExistedValueFromObjectNoSet(cnst, contractNumberSetting);
				this.tableService.saveEntity(contractNumberSetting);
			}else{}
		}
		if(jsonarrayupdate.length()>0){
			 this.tableService.updateOneToManyCollections(contract, "contractNumberSettings", ContractNumberSetting.class, "contractId",jsonarrayupdate.toString(),null);
		}
     }

	@Override
	public void loadContractNumberSetting(ContractInfo contractInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contractInfo.getId());
		variablesMap.put("json_contract_word_fundput_str",this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_number_setting.xml", queryMainObjectMap).toString());
	}
}
