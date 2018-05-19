package com.tenwa.leasing.action.factoring.buyer_receipt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.service.fund.fundcomm.FundCommMethod;

/**
 * 
 * @author ZHANGMUQING
 * @date 2016-11-24
 */
@WorkflowAction(name = "buyerreceiptEditAction", description = "第一步更新网银信息", workflowName = "保理买方收款流程")
@Component(value = "buyerreceiptEditAction")
public class BuyerreceiptEditAction implements JbpmBaseAction {

	
	@Resource(name = "tableService")
	private TableService tableService;

	 
	@Resource(name = "fundCommMethod")
	private FundCommMethod fundCommMethod;
	
	@Override
	public void back(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	     return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 //保存第次占用网银的金额 
		List<String> fields=new ArrayList<String>();
		fields.add("corpus");
		fields.add("interest");
		fields.add("penalty");
		fundCommMethod.updateEbankProcessFlow("json_rent_income_detail_str",fields, variablesMap);
	}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}
}
