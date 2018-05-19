package com.tenwa.leasing.action.factoring.buyer_receipt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.BaseService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.base.FundEbankData;
import com.tenwa.leasing.entity.base.FundEbankProcess;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.service.fund.fundcollection.FundCollectionService;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.vouchersFactory.FundCollectionVoucherService;
import com.tenwa.leasing.service.vouchersFactory.ReceivablesVoucherService;
import com.tenwa.leasing.service.vouchersFactory.RentIncomeVoucherService;
import com.tenwa.leasing.utils.WorkflowUtil;



/**
 * @author ZHANGMUQING
 * @date 2016-11-24
 * @info 
 */
@WorkflowAction(name = "buyerreceiptEndAction", description = "流程结束动作", workflowName = "保理买方收款流程")
@Component(value = "buyerreceiptEndAction")
public class BuyerreceiptEndAction implements JbpmBaseAction {
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name="rentIncomeVoucherService")
	private RentIncomeVoucherService rentIncomeVoucherService;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		//保存租金回笼
	    fundCommMethod.saveFundRentIncome("json_rent_income_detail_str", variablesMap);
	    //删除网银过程表
	    fundCommMethod.deleteEbankProcessFlow(variablesMap);
	    //租金回笼生成凭证
	    rentIncomeVoucherService.createVoucherReceiveRent(variablesMap);
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
