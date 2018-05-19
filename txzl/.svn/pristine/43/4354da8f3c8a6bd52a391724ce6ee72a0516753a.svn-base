package com.tenwa.leasing.action.contract.sign;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ContractNumberSetting;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractSignStartAction", description = "流程开始动作", workflowName = "合同签约流程")
@Component(value = "contractSignStartAction")
public class ContractSignStartAction implements JbpmBaseAction {
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		
		try{
			String uafdi = request.getParameter("uploadAttachmentFileDetailId");
			String contract_id = variablesMap.get("contract_id");
			String flowUnid = variablesMap.get("framework_condition.docid");
			Map<String, Object> propertiesMap = new HashMap<String, Object>();
			ContractInfo ci = new ContractInfo();
			ci.setId(contract_id);
			propertiesMap.put("contractId", ci);
			List<ContractNumberSetting> cnslist = this.tableService.findEntityByProperties(ContractNumberSetting.class, propertiesMap);
			for(ContractNumberSetting cns :cnslist){
				propertiesMap.clear();
				propertiesMap.put("filekey", cns.getId());
				propertiesMap.put("flowUnid", flowUnid);
				List<BaseFile> bflist = this.tableService.findEntityByProperties(BaseFile.class, propertiesMap);
				if(bflist.size()>0){
					for(BaseFile bfde:bflist){
						bfde.setInvalid("否");
						this.tableService.updateEntity(bfde);
					}
					
				}
			}
			return null;
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String contract_id = variablesMap.get("contract_id");
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, contract_id)); 
		ContractInfo contractInfo=this.contractCommService.loadContractInfo( variablesMap);
		CustInfo customerInfo=contractInfo.getCustId();
		this.contractCommService.loadContractEquip(contractInfo, variablesMap);//租赁物件明细
		this.contractCommService.loadContractGuaranteeMethod(contractInfo, variablesMap); //担保单位信息
		this.contractCommService.loadContractGuaranteeEquip(contractInfo, variablesMap);//抵押物信息
		this.contractCommService.loadContractInvoice(contractInfo, variablesMap);//租金开票类型
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";;
		contractCommService.loadContractRentCalculationParam(contract_id, customerInfo.getCustName(), customerInfo.getId(), flowunid,"cont_process", variablesMap);
		//商务条件，租金计划，资金计划，现金流
		this.contractCommService.loadContractRentCalculationFromBefore(contractInfo, variablesMap);
		//合同各方
		this.contractCommService.loadContractSignatoryInfo(contractInfo, variablesMap);
		//供应商信息
		this.contractCommService.loadContractSupplierInfo(contractInfo, variablesMap);
		//加载合同文件
		this.contractCommService.loadFile("json_contract_word_str", "合同管理", contract_id, variablesMap);
		//加载付款前提
		//加载付款前提
		this.contractCommService.loadContractPremise(contractInfo, variablesMap);
		//加载租金计划中付款前提条件
	   this.contractCommService.loadContractPaymentPremiseCondition(contractInfo, variablesMap);
			
	}
}
