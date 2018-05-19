package com.tenwa.leasing.action.factoring.saler_receipt;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.vouchersFactory.ReceivablesVoucherService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "salerreceiptEndAction", description = "流程结束动作", workflowName = "保理卖方收款流程")
@Component(value = "salerreceiptEndAction")
public class SalerreceiptEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "receivablesVoucherService")
	private ReceivablesVoucherService receivablesVoucherService;	
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@SuppressWarnings("unused")
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		
		
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//保存实际投放
	    fundCommMethod.saveFundFundCharge("json_collection_current_str", variablesMap);
	    //删除网银过程表
	    fundCommMethod.deleteEbankProcessFlow(variablesMap);
	    //收款凭证
//	   receivablesVoucherService.createVoucherReceiveReceivables(variablesMap);
		  /*
		   * 凭证测试
		   * */
		// fundCollectionVoucherService.createVoucherReceiveFund(contractInfo, json_collection_current_str, fundEbankData);
		  //throw new BusinessException("33");
	}
	   
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}
}