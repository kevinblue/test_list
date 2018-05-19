package com.tenwa.leasing.action.fund.fund_payment;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;
import com.tenwa.leasing.service.vouchersFactory.FundPaymentVoucherService;
import com.tenwa.leasing.utils.PaymentInterface;
import com.tenwa.leasing.utils.WorkflowUtil;



@WorkflowAction(name = "fundPaymentEndAction", description = "流程结束动作", workflowName = "付款流程")
@Component(value = "fundPaymentEndAction")
public class FundPaymentEndAction implements JbpmBaseAction {
	Logger logger = Logger.getLogger(FundPaymentEndAction.class);
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "fundPaymentVoucherService")
	private FundPaymentVoucherService fundPaymentVoucherService;
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {  
		    //删除流程互斥
			WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
			//保存客户类型
			fundCommMethod.updateCustInfoFromPayMent(variablesMap);
			//保存实际投放
		    fundCommMethod.saveFundFundCharge("json_payment_current_str", variablesMap);
		  //导入第三方数据库,并根据状态保存到本方数据库。
		    Map<String,String> map=PaymentInterface.payment(1,variablesMap,null);
		    fundCommMethod.savePaymentLog(map,variablesMap);
		   //生成凭证
//		    fundPaymentVoucherService.createVoucherReceiveFund(variablesMap);
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
		// TODO Auto-generated method stub
		
	}
}