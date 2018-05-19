package com.tenwa.leasing.action.cust.cust_grade;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.reckon.entity.contract.reckon.fund.FundRentAdjust;
import com.reckon.service.RentConditionDataOperator;
import com.reckon.service.impl.RentCalculateServiceImpl;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.AppStaticUtil;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.leasing.entity.contract.ContractEndInfo;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustGrade;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;


/**
 * 评级终止流程
 * @Title: ContractTerminateStartAction.java
 * @package: com.tenwa.leasing.action.contract.terminate
 * @author: tpf
 * @date 2014年12月11日 上午11:25:05 
 * @version V5.1
 */


@WorkflowAction(name = "custGradeEndAction", description = "流程结束动作", workflowName = "客户信用评级审核")
@Component(value = "custGradeEndAction")
public class CustGradeEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService  tableService;
	
	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		WorkflowUtil.deleteWorkFlowConflict(this.baseService, variablesMap);  
		User user  =  SecurityUtil.getPrincipal();//当前人
		String custId=variablesMap.get("custid");
		String allScore = variablesMap.get("all_Score");
		String advantage = variablesMap.get("cust_grade.advantage");//客户信用优势
		String inferiority = variablesMap.get("cust_grade.inferiority");//客户信用劣势
		String implementation = variablesMap.get("cust_grade.implementation");//客户盈利资产项目的实施情况
		String forecast = variablesMap.get("cust_grade.forecast");//客户未来信用等级预测
		String conclusion = variablesMap.get("cust_grade.conclusion");//客户评级结论
		String custid = variablesMap.get("custid");
		CustInfo custInfo=  this.tableService.findEntityByID(CustInfo.class, custid);
		CustInfoCompany custInfoCompany =custInfo.getCustInfoCompany();
		String custResult = variablesMap.get("cust_grade.grade_result");
		String gradeDate =variablesMap.get("cust_grade.createdate");
		CustGrade custGrade = new  CustGrade();
		custGrade.setAllScore(allScore);
		custGrade.setAdvantage(advantage);
		custGrade.setInferiority(inferiority);
		custGrade.setImplementation(implementation);
		custGrade.setForecast(forecast);
		custGrade.setConclusion(conclusion);
		custGrade.setGradeResult(custResult);
		custGrade.setCreateDate(DateUtil.getSystemDateTime());
		custGrade.setGradeDate(gradeDate);
		custGrade.setCreator(user);
		custGrade.setCustId(tableService.findEntityByID(CustInfo.class, custId));
		tableService.saveEntity(custGrade);
		
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
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}