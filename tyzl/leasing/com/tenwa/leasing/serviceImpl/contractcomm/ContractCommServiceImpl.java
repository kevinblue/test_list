package com.tenwa.leasing.serviceImpl.contractcomm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.reckon.service.RentConditionDataOperator;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.base.OwnAccount;
import com.tenwa.leasing.entity.base.OwnInfo;
import com.tenwa.leasing.entity.contract.ContractChangeInfo;
import com.tenwa.leasing.entity.contract.ContractCustHis;
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeEquip;
import com.tenwa.leasing.entity.contract.ContractGuaranteeMethod;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.contract.ContractSignatory;
import com.tenwa.leasing.entity.contract.ContractSignatorySecond;
import com.tenwa.leasing.entity.contract.ContractSupplierInfo;
import com.tenwa.leasing.entity.contract.ContractUnionCust;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoAccount;
import com.tenwa.leasing.entity.cust.CustInfoClique;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.cust.CustRelatedPerson;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ProjUnionCust;
import com.tenwa.leasing.entity.proj.invoice.ProjInvoiceType;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.service.contractcomm.ContractInfoExtends;
import com.tenwa.leasing.utils.WorkflowUtil;

@Service(value = "contractCommService")
public class ContractCommServiceImpl implements ContractCommService {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "RentConditionDataService")
	private RentConditionDataOperator rentConditionData;
	
	@Resource(name = "contractInfoExtends")
	private ContractInfoExtends contractInfoExtends;
	
	@Resource(name="baseService")
	private BaseService baseService;

	
	public void loadContractInfoFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo, null, "contract_info"));
		CustInfo customerInfo = projInfo.getCustInfo();
		String cust_class = customerInfo.getCustClass().getCode();
		variablesMap.put("contract_info.custclass", cust_class);
		variablesMap.put("contract_info.custid", customerInfo.getId());
		variablesMap.put("contract_info.custname", customerInfo.getCustName());
		 variablesMap.put("contract_info.custnumber", customerInfo.getCustNumber());
		variablesMap.put("contract_info.proj_id", projInfo.getProjId());
		variablesMap.put("contract_info.projid", projInfo.getId());
	}

	public void saveAndCreateContractNo(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		long contractOrder = projInfo.getContractOrder() == null ? 1 : projInfo.getContractOrder() + 1;
		String contractid=WorkflowUtil.getContractInfoSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		String contract_id = String.format(contractid + "-%04d", contractOrder);
		projInfo.setContractOrder(contractOrder);
		this.tableService.updateEntity(projInfo);
		variablesMap.put("contract_info.contractid",contract_id);
		//variablesMap.put("contract_info.contractnumber", contract_id);
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
		variablesMap.put("json_contract_equip_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/proj_equip.xml", queryMainObjectMap).toString());		
	}
	
	@Override
	public void loadContractUnionCustFromProj(ProjInfo projInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("proj_id", projInfo.getId());
		variablesMap.put("json_union_cust_str", this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_union_cust.xml", queryMainObjectMap).toString());		
	}
	
	@Override
	public void loadContractUnionCust(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		if("".equals(contractInfo.getSupContractId())||null==contractInfo.getSupContractId()){
			queryMainObjectMap.put("contract_id", contractInfo.getId());
			
		}else{
			queryMainObjectMap.put("contract_id", contractInfo.getSupContractId());
		}
		
		
		variablesMap.put("json_union_cust_str", this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_union_cust_2.xml", queryMainObjectMap).toString());		
	}
	
	@Override
	public void loadContractFundPut(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("id", contractInfo.getId());
		JSONArray array=this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_fund_put.xml", queryMainObjectMap);
		
		variablesMap.put("put_info.putnum",array.getJSONObject(0).getString("contract_put_number"));	
		variablesMap.put("put_info.putallequipamt",array.getJSONObject(0).getString("amtsup"));	
		variablesMap.put("put_info.putcurequipamt",array.getJSONObject(0).getString("equip_amt"));	
		variablesMap.put("put_info.putcurrate",array.getJSONObject(0).getString("putcurrate"));	
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
		}else if("CUST_INFO_CLIQUE".equals(customerInfo.getCustClass().getId())){
			CustInfoClique clique = customerInfo.getCustInfoClique();
			variablesMap.put("contract_signatory.clientpostcode",clique.getPostCode() == null ? "" : clique.getPostCode());
			variablesMap.put("contract_signatory.clientaddress",clique.getCompanyAddress() == null ? "" : clique.getCompanyAddress());
			variablesMap.put("contract_signatory.clientmobilenumber",clique.getMoblie() == null ? "" : clique.getMoblie());
			variablesMap.put("contract_signatory.clienttel",clique.getPhone() == null ? "" : clique.getPhone());
			variablesMap.put("contract_signatory.clientfax",clique.getFax() == null ? "" : clique.getFax());
			variablesMap.put("contract_signatory.clientemail",clique.getMailAddress() == null ? "" : clique.getMailAddress());
			variablesMap.put("contract_signatory.clientperson",clique.getPersonRep() == null ? "" : clique.getPersonRep());
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
		
		this.rentConditionData.LoadProjectContionAndRentAndFundToContractMap(contractid,projInfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str");

	}

	@Override
	public ContractInfo saveContractInfo(Map<String, String> variablesMap,Integer ContractStatus) throws Exception {
		String proj_id = variablesMap.get("contract_info.id");
		Map<String,Object> queryMainObjectMap = new HashMap<String,Object>();
		queryMainObjectMap.put("id", proj_id);
		variablesMap.put("contract_info.contractstatus", String.valueOf(ContractStatus));
		ContractInfo contractInfo = (ContractInfo)this.tableService.updateMainEntity(ContractInfo.class, queryMainObjectMap, variablesMap,null, "contract_info");
		return contractInfo;
	}

	@Override
	public void saveContractRentCalculationBefore(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.saveOrLoadRentAndFinalBefore(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","save");
	}

	@Override
	public void saveContractRentCalculationBeforePut(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
		this.rentConditionData.saveOrLoadRentAndFinalBefore(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","saveput");
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
		
		//在rate_info中查出数据传入contract_invoice_type
		List<Map <String,Object>> c= this.baseService.queryListBySql("select * from rate_info where leas_Form=? and tax_Reg_Type=?",variablesMap.get("contract_info.projtype"),variablesMap.get("contract_invoice_type.taxregtype"));
	
		//contract_info.custid  CustInfoClique
		if(null!=c&&c.size()>0){
			variablesMap.put("contract_invoice_type.interestRate", String.valueOf(c.get(0).get("interest_rate")));
			variablesMap.put("contract_invoice_type.interestInvoiceType", String.valueOf(c.get(0).get("interest_invoice_type")));
			variablesMap.put("contract_invoice_type.rentRate", String.valueOf(c.get(0).get("rent_rate")));
			variablesMap.put("contract_invoice_type.corpusRate", String.valueOf(c.get(0).get("corpus_rate")));
			variablesMap.put("contract_invoice_type.corpusInvoiceType", String.valueOf(c.get(0).get("corpus_invoice_type")));
			variablesMap.put("contract_invoice_type.serviceRate", String.valueOf(c.get(0).get("service_rate")));
			variablesMap.put("contract_invoice_type.serviceInvoiceType", String.valueOf(c.get(0).get("service_invoice_type")));
			variablesMap.put("contract_invoice_type.handRate", String.valueOf(c.get(0).get("hand_rate")));
			variablesMap.put("contract_invoice_type.handInvoiceType", String.valueOf(c.get(0).get("hand_invoice_type")));
			variablesMap.put("contract_invoice_type.firstpaymentRate", String.valueOf(c.get(0).get("firstpayment_rate")));
			variablesMap.put("contract_invoice_type.firstpaymentInvoiceType", String.valueOf(c.get(0).get("firstpayment_invoice_type")));
			variablesMap.put("contract_invoice_type.insurancelessorRate", String.valueOf(c.get(0).get("insurancelessor_rate")));
			variablesMap.put("contract_invoice_type.insurancelessorInvoiceType", String.valueOf(c.get(0).get("insurancelessor_invoice_type")));
			variablesMap.put("contract_invoice_type.nominalRate", String.valueOf(c.get(0).get("nominal_rate")));
			variablesMap.put("contract_invoice_type.nominalInvoiceType", String.valueOf(c.get(0).get("nominal_invoice_type")));
			variablesMap.put("contract_invoice_type.returnamtRate", String.valueOf(c.get(0).get("returnamt_rate")));
			variablesMap.put("contract_invoice_type.returnamtInvoiceType", String.valueOf(c.get(0).get("returnamt_invoice_type")));
			variablesMap.put("contract_invoice_type.rentInvoiceType", String.valueOf(c.get(0).get("rent_invoice_type")));
			/*新添加*/
			variablesMap.put("contract_invoice_type.assetSratio", String.valueOf(c.get(0).get("asset_sratio")));
			variablesMap.put("contract_invoice_type.assetsMoney", String.valueOf(c.get(0).get("assets_money")));
			variablesMap.put("contract_invoice_type.adviserRatio", String.valueOf(c.get(0).get("adviser_ratio")));
			variablesMap.put("contract_invoice_type.adviserMoney", String.valueOf(c.get(0).get("adviser_money")));
		}
		
		List<Map <String,Object>> cn= this.baseService.queryListBySql("select * from (select cic.cust_id custid,cic.tax_reg_code,cic.tax_bank,cic.tax_acc,cic.invoice_add,cic.invoice_phone from cust_info_company cic union all select cicl.cust_id,cicl.tax_reg_code,cicl.tax_bank,cicl.tax_acc,cicl.invoice_add,cicl.invoice_phone from cust_info_clique cicl ) where custid=? ",
				variablesMap.get("contract_info.custid"));
		
		if(null!=cn&&cn.size()>0){
			variablesMap.put("contract_invoice_type.tax_reg_code",String.valueOf(cn.get(0).get("tax_reg_code")));
			variablesMap.put("contract_invoice_type.tax_bank", String.valueOf(cn.get(0).get("tax_bank")));
			variablesMap.put("contract_invoice_type.tax_acc", String.valueOf(cn.get(0).get("tax_acc")));
			variablesMap.put("contract_invoice_type.invoice_add", String.valueOf(cn.get(0).get("invoice_add")));
			variablesMap.put("contract_invoice_type.invoice_phone", String.valueOf(cn.get(0).get("invoice_phone")));
		}
	
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
		
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_contract_guarantee_equip_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_guarantee_equip.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_equip_str", json_contract_guarantee_equip_str);
		
	}

	@Override
	public void loadContractGuaranteeMethod(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception {
	
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("contract_id", contracinfo.getId());
		String json_contract_guarantee_method_str = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_comm/contract_guarantee_method.xml", queryMainObjectMap).toString();
		variablesMap.put("json_contract_guarantee_method_str", json_contract_guarantee_method_str);
		
	}

	@Override
	public ContractInfo loadContractInfo(Map<String,String>variablesMap)throws Exception {
		String contract_id = variablesMap.get("contract_id");
		ContractInfo contractInfo =(ContractInfo)this.tableService.findEntityByID(ContractInfo.class, contract_id);
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
		this.rentConditionData.saveOrLoadRentAndFinalBefore(contracinfo, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str", "json_finance_rent_plan_str", "json_finance_cash_flow_str", "json_fund_fund_charge_str","load");
		
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
		this.rentConditionData.LoadContractContionAndRentAndFundToMap(contractid, variablesMap, "", "json_fund_rent_plan_str", "json_fund_cash_flow_str","", "", "json_fund_fund_charge_str");
	}
	@Override
	public void createContractNo(Map<String, String> variablesMap) throws Exception {
		String audingcontractid = WorkflowUtil.getAuditingContractId(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("contract_info.contractnumber",audingcontractid);
	}
	public void loadContractClientInfo(CustInfo customerInfo,Map<String, String> variablesMap) throws Exception {
		String custid=customerInfo.getId();
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("cust_id", custid);
		variablesMap.put("json_contract_signatory_str", this.tableService.getJsonArrayData("eleasing/workflow/proj/proj_common/load_cust_clientInfo.xml", queryMainObjectMap).toString());		
			
	}
	@Override
	public void saveContractSignatorySecondInfo(ContractInfo contracinfo,Map<String, String> variablesMap) throws Exception{
		String json_contract_signatory_str=variablesMap.get("json_contract_signatory_str");
		if(null!=json_contract_signatory_str){
			this.tableService.updateOneToManyCollections(contracinfo, "contractSignatorySeconds", ContractSignatorySecond.class, "contractId", json_contract_signatory_str,null);
		}
		
	}
	
	@Override
	public void saveContractUnionCust(ContractInfo contractInfo,Map<String,String>variablesMap) throws Exception {
		String custType=variablesMap.get("custid2");
		CustInfo cust=null;
		if(custType!=null&&!custType.equals("")){
			 String custTypes[]=custType.split(",");
			 List<ContractUnionCust> custTypeInfos=new ArrayList<ContractUnionCust>();
			 for (String temp:custTypes) {
				 cust = this.tableService.findEntityByID(CustInfo.class, temp);
				 ContractUnionCust unioncust=new ContractUnionCust();
				 unioncust.setContractInfo(contractInfo);
				 unioncust.setUnioncust(cust);
				 unioncust.setUnioncustclass(cust.getCustClass().getId());
				 custTypeInfos.add(unioncust);
			}
			 this.tableService.saveAllEntities(custTypeInfos);
		}
		
	}
	
	@Override
	public void saveContractUnionCust2(ContractInfo contractInfo,Map<String,String>variablesMap) throws Exception {
		
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("contractInfo", contractInfo);
		this.tableService.removeAllEntites(this.tableService.findEntityByProperties(ContractUnionCust.class, propertiesMap)); 
		String custType=variablesMap.get("custid2");
		CustInfo cust=null;
		if(custType!=null&&!custType.equals("")){
			 String custTypes[]=custType.split(",");
			 List<ContractUnionCust> custTypeInfos=new ArrayList<ContractUnionCust>();
			 for (String temp:custTypes) {
				 ContractUnionCust unioncust=new ContractUnionCust();
				 cust = this.tableService.findEntityByID(CustInfo.class, temp);
				 unioncust.setContractInfo(contractInfo);
				 unioncust.setUnioncust(cust);
				 unioncust.setUnioncustclass(cust.getCustClass().getId());
				 custTypeInfos.add(unioncust);
			}
			 this.tableService.saveAllEntities(custTypeInfos);
		}
		
	}
}
