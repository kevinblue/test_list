package com.tenwa.leasing.action.rent.rentred;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.rent.rentred.RentRedService;
import com.tenwa.leasing.utils.WorkflowUtil;



/**
 * @author lichaojie
 * @date 2014-12-04
 * @info 
 */
@WorkflowAction(name = "rentRedEndAction", description = "流程结束动作", workflowName = "租金红冲流程")
@Component(value = "rentRedEndAction")
public class RentRedEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	

	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//保存实收红冲
		fundCommMethod.saveFundRentIncome("json_rent_red_detail_str", variablesMap);
		//更新被红冲的资金收付
		fundCommMethod.saveRentRebackFlag("json_rent_red_detail_str", variablesMap);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	
	 /**
	 * (non-Javadoc)
	 * @see com.business.action.JbpmBaseAction#back(javax.servlet.http.HttpServletRequest, java.util.Map)
	 **/
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
