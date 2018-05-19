package com.tenwa.leasing.action.contract.taxchange;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.FundAdjustInterestContractTemp;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.contract.ContractTaxpeople;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.contract.accountChange.AccountChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;


/**
 * @author admin
 * @date 2016-8-1
 * @info
 */
@WorkflowAction(name = "contractTaxChangeEndAction", description = "流程结束动作", workflowName = "开票信息变更")
@Component(value = "contractTaxChangeEndAction")
public class ContractTaxChangeEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "accountChangeService")
	private AccountChangeService accountChangeService;

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);   
		String id=variablesMap.get("contractinvoicetype.id");
		Map<String, Object> qvariablesMap=new HashMap<String, Object>();
		qvariablesMap.put("id", id);
		ContractInvoiceType cit=this.baseService.findEntityByID(ContractInvoiceType.class, id);
		ContractTaxpeople cc=	this.baseService.updateMainEntity(ContractTaxpeople.class, null, variablesMap, null, "contractInvoiceType_info");
		String taxregtype=variablesMap.get("contractinvoicetype.taxregtype");
		DictionaryData  dd =this.baseService.findEntityByID(DictionaryData.class,taxregtype);
	    cc.setTaxRegType(dd);
		cc.setContractinvoicetype(cit);
		this.baseService.saveEntity(cc);
		
		//this.baseService.updateMainEntity(ContractInvoiceType.class, qvariablesMap, variablesMap, null, "contractinvoicetype");
		
		String cust=variablesMap.get("custid");
		Map<String, Object> qvariablesMap1=new HashMap<String, Object>();
		CustInfo custinfo=this.baseService.findEntityByID(CustInfo.class, cust);
		qvariablesMap1.put("custId", custinfo);
		List<CustInfoCompany> custinfocompanylist = this.baseService.findEntityByProperties(CustInfoCompany.class, qvariablesMap1);
		CustInfoCompany custinfocompany=custinfocompanylist.get(0);
		String insuretrustcode=variablesMap.get("insuretrustcode");
		if(insuretrustcode.equals("是")){
			custinfocompany.setOrgCode(variablesMap.get("contractinvoicetype.taxregcode"));
		}
		custinfocompany.setTaxRegCode(variablesMap.get("contractinvoicetype.taxregcode"));
		custinfocompany.setTaxRegType(dd);
		custinfocompany.setTaxBank(variablesMap.get("contractinvoicetype.taxbank"));
		custinfocompany.setInvoiceAdd(variablesMap.get("contractinvoicetype.invoiceadd"));
		custinfocompany.setTaxAcc(variablesMap.get("contractinvoicetype.taxacc"));
		custinfocompany.setInvoicePhone(variablesMap.get("contractinvoicetype.invoicePhone"));
		this.baseService.saveOrUpdateEntity(custinfocompany);
		
		Map<String, String> qvariablesMap2=new HashMap<String, String>();
		qvariablesMap2.put("cust_id", cust);
		String contractids = this.tableService.getJsonArrayData("eleasing/workflow/contract/contract_tax_change/find_contractid.xml", qvariablesMap2).toString();
		JSONArray jsonArray = new JSONArray(contractids);
		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			String contractid=jsonObj.optString("id");
			Map<String, Object> qvariablesMap3=new HashMap<String, Object>();
			ContractInfo contractinfo=this.baseService.findEntityByID(ContractInfo.class,contractid);
			qvariablesMap3.put("contractId", contractinfo);
			List<ContractInvoiceType> contractinvoicetypes = this.baseService.findEntityByProperties(ContractInvoiceType.class, qvariablesMap3);
			if(contractinvoicetypes.size()==0){
				break;//退出循环，不执行下面的语句，除非集合的长度大于0才执行下面语句
			}			
			ContractInvoiceType contractinvoicetype=contractinvoicetypes.get(0);
			contractinvoicetype.setTaxRegType(dd);
			contractinvoicetype.setTaxRegCode(variablesMap.get("contractinvoicetype.taxregcode"));
			contractinvoicetype.setTaxBank(variablesMap.get("contractinvoicetype.taxbank"));
			contractinvoicetype.setInvoiceAdd(variablesMap.get("contractinvoicetype.invoiceadd"));
			contractinvoicetype.setTaxAcc(variablesMap.get("contractinvoicetype.taxacc"));
			contractinvoicetype.setInvoicePhone(variablesMap.get("contractinvoicetype.invoicePhone"));
			this.baseService.saveOrUpdateEntity(contractinvoicetype);
		}
	
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
