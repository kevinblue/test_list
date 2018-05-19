package com.tenwa.leasing.action.contract.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.leasing.entity.contract.ContractBorrowHis;
import com.tenwa.leasing.entity.contract.ContractDocList;
import com.tenwa.leasing.entity.contract.ContractDocListBorrow;
import com.tenwa.leasing.service.contract.contractChange.ContractChangeService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "contractBackEndAction", description = "流程结束动作", workflowName = "合同归还")
@Component(value = "contractBackEndAction")
public class ContractBackEndAction implements JbpmBaseAction {

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractChangeService")
	private ContractChangeService contractChangeService;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		String doc_id = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+"";
		variablesMap.put("docId", doc_id);
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);
	
		//是否同意归还
		String isagree = variablesMap.get("processedResult");
		if("JbpmCommonAdvise_agree".equals(isagree)){//同意归还2,不同意归还1，即任然是借出状态
	    	variablesMap.put("borrowingstatus", "2");
	    	String json_contract_back_str=variablesMap.get("json_contract_back_str");
			saveContractBackHis(json_contract_back_str,variablesMap);
		}else{
			variablesMap.put("borrowingstatus", "1");
		}
		
		
	}
	private void saveContractBackHis(String jsonstr,Map<String, String> variablesMap) throws JSONException, Exception {
		JSONArray jsonArray = new JSONArray(jsonstr);
		List<ContractBorrowHis> list=new ArrayList<ContractBorrowHis>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject =(JSONObject) jsonArray.get(i);
			//保存借出文件信息			
				ContractBorrowHis cb=this.baseService.findEntityByID(ContractBorrowHis.class, jsonObject.getString("id"));
				cb.setFileStatus("2");
				cb.setBackMan(jsonObject.getString("backman"));
				cb.setBackdate(jsonObject.getString("backdate"));
				cb.setMemoback(jsonObject.getString("memoback"));
				if(jsonObject.getString("borrowingstatus").equals("归还")){
					cb.setBorrowingStatus(variablesMap.get("borrowingstatus"));
					list.add(cb);
				}
				
		}
		if(list.size()>0){
		this.baseService.saveOrUpdateAllEntities(list);}
	}
	
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

}