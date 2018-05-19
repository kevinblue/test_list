package com.tenwa.leasing.action.rent.penaltyadjust;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.rent.penaltyadjust.PenaltyAdjustService;
import com.tenwa.leasing.service.rent.rentincome.RentIncomeService;
import com.tenwa.leasing.utils.WorkflowUtil;



/**
 * 罚息减免流程
 * @Title: PenaltyAdjustEndAction.java
 * @package: com.tenwa.leasing.action.rent.penaltyadjust
 * @author: tpf
 * @date 2014年12月9日 下午3:05:24 
 * @version V5.1
 */
@WorkflowAction(name = "penaltyAdjustEndAction", description = "流程结束动作", workflowName = "罚息免除流程")
@Component(value = "penaltyAdjustEndAction")
public class PenaltyAdjustEndAction implements JbpmBaseAction {
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
		//保存租金回笼
	    fundCommMethod.saveFundRentIncome("json_rent_penalty_adjust_detail_str", variablesMap);
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
