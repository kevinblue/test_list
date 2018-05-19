package com.tenwa.leasing.serviceImpl.overdue;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.TableService;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.lawmng.LawApproval;
import com.tenwa.leasing.service.overdue.FundOverCommService;


@Service(value = "fundOverCommService")
public class FundOverCommServiceImpl implements FundOverCommService {

	@Resource(name = "tableService")
	private TableService tableService;
	
	/* 合同信息*/
	@Override
	public void loadFundOverContractInfo(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", contractInfo.getCustId().getId());
		variablesMap.put("json_turnover_detail_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/contract_list.xml", queryMainObjectMap).toString());	
	}
	@Override
	public void loadFundOverContractInfo(CustInfo custInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", custInfo.getId());
		variablesMap.put("json_turnover_detail_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/contract_list.xml", queryMainObjectMap).toString());	
	}
	@Override
	public void loadCustColumnInfo(CustInfo custInfo,Map<String, String> variablesMap) throws Exception {
		String keyword="";
		String  keyword2="";
		if(custInfo.getCustClass().getCode().equalsIgnoreCase("CUST_INFO_COMPANY")){
			variablesMap.put("companycustname", custInfo.getCustName());
			variablesMap.put("orgcode", custInfo.getCustInfoCompany()==null?null:custInfo.getCustInfoCompany().getOrgCode());
			keyword ="承租客户:"+custInfo.getCustName();
			keyword2 =custInfo.getCustInfoCompany()==null?null:custInfo.getCustInfoCompany().getOrgCode();
			variablesMap.put("keyword",keyword );
			variablesMap.put("keyword2",keyword2 );
		}else{
			variablesMap.put("personcustname", custInfo.getCustName());
			variablesMap.put("idcardno", custInfo.getCustInfoPerson()==null?null:custInfo.getCustInfoPerson().getIdCardNo());
			keyword ="承租客户:"+custInfo.getCustName() ;
			keyword2 =custInfo.getCustInfoPerson()==null?null:custInfo.getCustInfoPerson().getIdCardNo() ;
			variablesMap.put("keyword",keyword );
			variablesMap.put("keyword2",keyword2 );
		}	
	}
	/* 业务人员记录信息 */
	@Override
	public void loadFundOveDunningRecord(ContractInfo contractInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", contractInfo.getCustId().getId());
		queryMainObjectMap.put("iscsorbns", "bns");
		variablesMap.put("json_dunning_record_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml", queryMainObjectMap).toString());	
	}
	@Override
	public void loadFundOveDunningRecord(CustInfo custInfo,Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", custInfo.getId());
		queryMainObjectMap.put("iscsorbns", "bns");
		variablesMap.put("json_dunning_record_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml", queryMainObjectMap).toString());	
	}
	/* 催收人员记录信息*/
	@Override
	public void loadFundOveCSRecord(ContractInfo contractInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", contractInfo.getCustId().getId());
		queryMainObjectMap.put("iscsorbns", "cs");
		variablesMap.put("json_cs_record_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml", queryMainObjectMap).toString());
	}
	@Override
	public void loadFundOveCSRecord(CustInfo custInfo,
			Map<String, String> variablesMap) throws Exception {
		Map<String,String> queryMainObjectMap = new HashMap<String,String>();
		queryMainObjectMap.put("custid", custInfo.getId());
		queryMainObjectMap.put("iscsorbns", "cs");
		variablesMap.put("json_cs_record_str", this.tableService.getJsonArrayData("eleasing/jsp/fund/fund_overdue/overdue_dunning_record.xml", queryMainObjectMap).toString());
	}
	@Override
	public void loadLawInfoDetail(Map<String, String> variablesMap)
			throws Exception {
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("lawnum",variablesMap.get("lawnum"));
		List<LawApproval> list = this.tableService.findEntityByProperties(LawApproval.class, propertiesMap);
		if(list.size()>0){
			LawApproval lawApproval = list.get(0);
			variablesMap.put("law_approval.lawnum",lawApproval.getLawnum());
			variablesMap.put("lawid",lawApproval.getId());
			//DictionaryData lawtypename = this.tableService.findEntityByID(DictionaryData.class,lawApproval.getLawtype());
/*			variablesMap.put("law_approval.lawtype",lawtypename.getId());
			variablesMap.put("rawValue_law_approval.lawtype",lawtypename.getName());*/
			variablesMap.put("law_approval.lawmemo",lawApproval.getLawmemo());
		}
	}
	
	
}
