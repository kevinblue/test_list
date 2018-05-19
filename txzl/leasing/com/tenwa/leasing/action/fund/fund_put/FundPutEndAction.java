package com.tenwa.leasing.action.fund.fund_put;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.exception.BusinessException;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.vouchersFactory.FundPutVoucherService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "fundPutEndAction", description = "流程结束动作", workflowName = "实际投放")
@Component(value = "fundPutEndAction")
public class FundPutEndAction implements JbpmBaseAction {
	Logger logger = Logger.getLogger(FundPutEndAction.class);
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name="fundPutVoucherService")
	private FundPutVoucherService fundPutVoucherService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {  
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		//保存客户类型
		fundCommMethod.updateCustInfoFromDelivery(variablesMap);
		//保存实际投放
		fundCommMethod.saveFundFundCharge("json_put_current_str", variablesMap);
	    //保存货扣资金
	    fundCommMethod.saveFundFundCharge("json_caution_money_refund_detail_str", variablesMap);
	    //保存货扣租金
	    fundCommMethod.saveFundRentIncome("json_rent_income_detail_str", variablesMap);
	    //保存放款资料清单
	   // fundCommMethod.saveFundPutFileList(variablesMap);   
	    //投放凭证
	    //fundPutVoucherService.createVoucherReceiveFundPut(variablesMap);
	    //保存付款前提条件
	    fundCommMethod.savePremiseCondition(variablesMap);
	    
	    //保存付款信息到付款日志表中
	    //fundCommMethod.savePaymentLog(null, variablesMap);
	    //获取付款流程收款方信息,插入到第三方数据库表中
	   fundCommMethod.savePaymentEndDate("json_put_current_str", variablesMap);
	    
	    //throw  new  BusinessException("错了");

	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		return null;
	}
	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
