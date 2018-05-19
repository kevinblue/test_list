package com.tenwa.leasing.action.proj.proj_credit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoClique;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "projCreditStartAction", description = "流程开始动作", workflowName = "项目信审流程")
@Component(value = "projCreditStartAction")
public class ProjCreditStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		
		//根据客户名称获得客户信息
		String cust_id = variablesMap.get("cust_id");
		String leasform=variablesMap.get("leasform");
    	String leasformText=variablesMap.get("leasformText");
		CustInfo custInfo = (CustInfo) this.tableService.findEntityByID(CustInfo.class, cust_id);
		//获得项目编号
		String proj_id = WorkflowUtil.getProjInfoSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		//将项目编号，客户id，客房名称，项目申请日期,客户编号号，客户类别放到Map中  
		variablesMap.put("proj_info.projid", proj_id);
		variablesMap.put("proj_info.custInfo", cust_id);
		variablesMap.put("proj_info.custname", custInfo.getCustName());
	    variablesMap.put("proj_info.projdate",DateUtil.getSystemDate());			
		variablesMap.put("proj_info.custnumber", custInfo == null ? "": custInfo.getCustNumber());
		variablesMap.put("proj_info.custclass",custInfo.getCustClass().getId());
		variablesMap.put("cust_info.custclass",custInfo.getCustClass() == null ? "" : custInfo.getCustClass().getName());
		
		variablesMap.put("proj_info.leasform", leasform);
		variablesMap.put("rawValue_proj_info.leasform", leasformText);
		//根据租赁形式判断项目类型，直租和回租起作用
		/*if(("lease_form1").equals(leasform)||("lease_form2").equals(leasform)){
	    variablesMap.put("proj_info.projtype", proj_type);
	    variablesMap.put("rawValue_proj_info.projtype", proj_typeText);
		}*/
	
		
		//项目来源从客户带出来proj_info.projsource
		//个人
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("custId", custInfo);
		if("CUST_INFO_PERSON".equals(custInfo.getCustClass().getId())){
			List<CustInfoPerson> cp=this.tableService.findEntityByProperties(CustInfoPerson.class, propertiesMap);
			if(cp.size()>0){
				variablesMap.put("proj_info.projsource",cp.get(0).getCustSource()== null ? "" :cp.get(0).getCustSource().getId());
				variablesMap.put("rawValue_proj_info.projsource",cp.get(0).getCustSource()== null ? "" :cp.get(0).getCustSource().getName());
				variablesMap.put("proj_info.industrytype",cp.get(0).getCustKind()== null ? "" :cp.get(0).getCustKind().getId());
				variablesMap.put("rawValue_proj_info.industrytype",cp.get(0).getCustKind()== null ? "" :cp.get(0).getCustKind().getName());
			}
		}else if("CUST_INFO_COMPANY".equals(custInfo.getCustClass().getId())){//法人
			List<CustInfoCompany> cc=this.tableService.findEntityByProperties(CustInfoCompany.class, propertiesMap); 
			if(cc.size()>0){
				//法人信息
				variablesMap.put("proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getId());
				variablesMap.put("rawValue_proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getName());
				variablesMap.put("proj_info.industrytype",cc.get(0).getCustKind()== null ? "" :cc.get(0).getCustKind().getId());
				variablesMap.put("rawValue_proj_info.industrytype",cc.get(0).getCustKind()== null ? "" :cc.get(0).getCustKind().getName());
				//法人纳税信息
				variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(cc.get(0),null, "rent_invoice_type"));
			}
		}else 
		{//集团
			List<CustInfoClique> cc=this.tableService.findEntityByProperties(CustInfoClique.class, propertiesMap); 
			if(cc.size()>0){
				//集团信息
				variablesMap.put("proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getId());
				variablesMap.put("rawValue_proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getName());
				//集团纳税信息
				variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(cc.get(0),null, "rent_invoice_type"));
			}
		}
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, custInfo.getCustName(), cust_id, flowunid, variablesMap);
		
		
//		String proj_id = variablesMap.get("proj_id");
//		//流程冲突
//		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
//		variablesMap.put("docId", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
//		projCreditService.getProjCreditInfo(variablesMap,jbpmWorkflowHistoryInfo);
	}

	@Override
	public void back(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public void end(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		 
		
	}

	@Override
	public String save(HttpServletRequest request,	Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程冲突第二步
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap); 
		return null;
	}

	
	
	
}
