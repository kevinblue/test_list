package com.tenwa.leasing.action.contract.fiveCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractFiveCategory;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.contract.contractCancel.ContractCancelService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**   
*    
* 项目名称：tls5.1   
* 类名称：FiveCategoryEndAction   
* 类描述：   
* 创建人：rovine   
* 创建时间：2015年1月12日 上午10:32:54   
* @version        
*/
@WorkflowAction(name = "fiveCategoryEndAction", description = "流程结束动作", workflowName = "五级分类流程")
@Component(value = "fiveCategoryEndAction")
public class FiveCategoryEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCancelService")
	private ContractCancelService contractCancelService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
		ContractFiveCategory cfive=this.baseService.updateMainEntity(ContractFiveCategory.class, null, variablesMap, null, "five_category");
		String contract_id = variablesMap.get("contract_id");
		String fiveCLASS = variablesMap.get("five_category.contractfive_business");
		ContractInfo contractInfo =this.baseService.findEntityByID(ContractInfo.class, contract_id);
		DictionaryData dictionaryData =this.baseService.findEntityByID(DictionaryData.class, fiveCLASS);
		contractInfo.setFiveCLASS(dictionaryData);
		cfive.setContractId(contractInfo);
		this.baseService.saveOrUpdateEntity(cfive);
		this.baseService.saveOrUpdateEntity(contractInfo);
		Map<String,Object> condition=new HashMap<String,Object>();
		condition.put("supContractId", contract_id);
		List<ContractInfo>childern=this.baseService.findEntityByProperties(ContractInfo.class, condition);
		if(null!=childern && childern.size()>0){
			for(int i=0;i<childern.size();i++){
				ContractInfo childernContract=childern.get(i);
				childernContract.setFiveCLASS(dictionaryData);
				variablesMap.put("five_category.contractId", childernContract.getId());
				this.baseService.updateMainEntity(ContractFiveCategory.class, null, variablesMap, null, "five_category");
				this.baseService.saveOrUpdateEntity(childernContract);
			}
		}
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}
