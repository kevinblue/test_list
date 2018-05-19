package com.tenwa.leasing.action.contract.contractOnhire;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.contract.ContractInvoiceType;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.contract.contractOnhire.ContractOnhireService;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * @author Jason
 * @date 2013-4-24
 * @info 
 */
@WorkflowAction(name = "contractOnhireEndActio", description = "流程结束动作", workflowName = "合同起租流程")
@Component(value = "contractOnhireEndActio")
public class ContractOnhireEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Resource(name = "contractOnhireService")
	private ContractOnhireService contractOnhireService;
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService,variablesMap);    
		//获取参数
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		 this.contractOnhireService.updateContractOnhire(variablesMap);
		
		//保存项目考核的信息 
		String contractPutNumber = variablesMap.get("contract_info.contractputnumber");
		String referee = variablesMap.get("referee");
		String hoster = variablesMap.get("hoster");
		String assistant = variablesMap.get("assistant");
		String thirdParty  = variablesMap.get("thirdParty");
		Integer referee_Proportion =Integer.parseInt(variablesMap.get("referee_Proportion"));
		Integer hoster_Proportion = Integer.parseInt(variablesMap.get("hoster_Proportion"));
		Integer assistant_Proportion = Integer.parseInt( variablesMap.get("assistant_Proportion"));
		Integer third_Party_Proportion = Integer.parseInt(variablesMap.get("third_Party_Proportion"));
		String sql="from ContractInfo where contractPutNumber='"+contractPutNumber+"' ";
		List <ContractInfo> proList = baseService.findResultsByHSQL(sql);
	    for(ContractInfo pcn:proList){
	    	pcn.setRefereePeople(referee);
	        pcn.setRefereeProportion(referee_Proportion);
	        pcn.setHosterValue(hoster);
	        pcn.setHosterProportion(hoster_Proportion);     
            pcn.setAssistantPeople(assistant);
	        pcn.setAssistantProportion(assistant_Proportion);
	        pcn.setThirdParty(thirdParty);
	        pcn.setThirdPartyProportion(third_Party_Proportion);	
	    }
		//得到租金开票类型
		DictionaryData dd=new DictionaryData();
		String  taxRegTypeText =variablesMap.get("rawValue_contract_invoice_type.taxregtype");
		String  taxRegTypeValue =variablesMap.get("contract_invoice_type.taxregtype");
		dd.setId(taxRegTypeValue);
		dd.setName(taxRegTypeText);
	    String taxRegCode = variablesMap.get("contract_invoice_type.taxregcode");
	    String taxBank = variablesMap.get("contract_invoice_type.taxbank");
	    String taxAcc = variablesMap.get("contract_invoice_type.taxacc");
	    String invoicePhone = variablesMap.get("contract_invoice_type.invoicephone");
	    String invoiceAdd = variablesMap.get("contract_invoice_type.invoiceadd");
	    String custId = variablesMap.get("contract_info.custid"); 
		String hsql="from CustInfoCompany where custId='"+custId+"' ";
		List <CustInfoCompany> list = baseService.findResultsByHSQL(hsql);
		for(CustInfoCompany custInfoCompany:list){
			custInfoCompany.setTaxRegType(dd); //DictionaryData
			custInfoCompany.setTaxRegCode(taxRegCode);
			custInfoCompany.setTaxBank(taxBank);
			custInfoCompany.setTaxAcc(taxAcc);
			custInfoCompany.setInvoicePhone(invoicePhone);
			custInfoCompany.setInvoiceAdd(invoiceAdd);
		}
	}
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
	}

}
