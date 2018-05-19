package com.tenwa.leasing.action.proj.proj_factoring;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;
import com.tenwa.leasing.entity.cust.CustInfoPerson;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

/**
 * 
 * @author admin
 * @date 2016-11-15
 * @info保理业务立项的Action
 * @Copyright 
 * Tenwa
 */
@WorkflowAction(name = "projFactoringStartAction", description = "流程开始动作", workflowName = "保理业务立项")
@Component(value = "projFactoringStartAction")
public class ProjFactoringStartAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	
	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//根据客户名称获得客户信息
		String cust_id = variablesMap.get("cust_id");
		CustInfo custInfo =  this.tableService.findEntityByID(CustInfo.class, cust_id);
		//获得项目编号
		String proj_id = WorkflowUtil.getProjInfoFactoringSerialNumber(variablesMap,this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		//将项目编号，客户id，客房名称，项目申请日期,客户编号号，客户类别放到Map中  
		variablesMap.put("proj_info.projid", proj_id);
		variablesMap.put("proj_info.custInfo", cust_id);
		variablesMap.put("proj_info.custname", custInfo.getCustName());
		//variablesMap.put("proj_info.projectname", custInfo.getCustName()+"项目");
	    variablesMap.put("proj_info.projdate",DateUtil.getSystemDate());			
		variablesMap.put("proj_info.custnumber", custInfo == null ? "": custInfo.getCustNumber());
		variablesMap.put("proj_info.custclass",custInfo.getCustClass().getId());
		variablesMap.put("cust_info.custclass",custInfo.getCustClass() == null ? "" : custInfo.getCustClass().getName());
		variablesMap.put("proj_info.businesstype", "business_type.factoring");
		variablesMap.put("rawValue_proj_info.businesstype","保理");
		
		//获取保理基本信息
		proCommService.loadProjDevelopInfo(variablesMap);
		//保理业务发起立项默认项目类型和项目来源默认为空。
		variablesMap.put("proj_info.projtype", "");
		variablesMap.put("rawValue_proj_info.projtype", "");
		variablesMap.put("proj_info.projsource", "");
		variablesMap.put("rawValue_proj_info.projsource", "");

		String sql="select * from cust_grade t where t.create_date =(select max(c.create_date) from cust_grade c where c.cust_id = '"+cust_id+"')";//查询评级结果
		List<Map<String, Object>>  listgrade=this.tableService.queryListBySql(sql, null);//获取客户评级结果集
		String graderesult="";
		if(null!=listgrade&&listgrade.size()>0){
			 graderesult=listgrade.get(0).get("grade_result").toString();
		 }
		variablesMap.put("proj_info.graderesult", graderesult);
		
		//项目来源从客户带出来proj_info.projsource
		//个人
		Map<String, Object> propertiesMap=new HashMap<String, Object>();
		propertiesMap.put("custId", custInfo);
		if("CUST_INFO_PERSON".equals(custInfo.getCustClass().getId())){
			List<CustInfoPerson> cp=this.tableService.findEntityByProperties(CustInfoPerson.class, propertiesMap);
			if(cp.size()>0){
				variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(cp.get(0),null, "proj_info"));

				/*variablesMap.put("proj_info.projsource",cp.get(0).getCustSource()== null ? "" :cp.get(0).getCustSource().getId());
				variablesMap.put("rawValue_proj_info.projsource",cp.get(0).getCustSource()== null ? "" :cp.get(0).getCustSource().getName());*/
			}
		}else{//法人
			List<CustInfoCompany> cc=this.tableService.findEntityByProperties(CustInfoCompany.class, propertiesMap); 
			if(cc.size()>0){
				//法人信息
/*				variablesMap.put("proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getId());
				variablesMap.put("rawValue_proj_info.projsource",cc.get(0).getCustSource()== null ? "" :cc.get(0).getCustSource().getName());
*/				//供应商信息
				variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(cc.get(0),null, "proj_info"));
				variablesMap.put("cust_info.custscalename", cc.get(0).getCustScale()==null?"":cc.get(0).getCustScale().getName());
			}
		}
		String flowunid=jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid()+ "";
		proCommService.loadRentCalculationParam(proj_id, custInfo.getCustName(), cust_id, flowunid, variablesMap);
		//获得当前登录人所属部门
		String pro_user_name= proCommService.getUsersDept();
		JSONArray jsonarray1=new JSONArray(pro_user_name);
		for(int i=0;i<jsonarray1.length();i++){
			JSONObject jsonobject1=jsonarray1.getJSONObject(i);
			variablesMap.put("proj_info.department", jsonobject1.getString("id"));
			variablesMap.put("rawValue_proj_info.department", jsonobject1.getString("name"));
		}
		//获得当前登录人部门路径
		String user_proj_route = proCommService.getDeptLujing();
		JSONArray jsonarray2=new JSONArray(user_proj_route);
		for(int i=0;i<jsonarray2.length();i++){
			JSONObject jsonobject2=jsonarray2.getJSONObject(i);
			variablesMap.put("proj_info.deptroute", jsonobject2.getString("id"));
			variablesMap.put("rawValue_proj_info.deptroute", jsonobject2.getString("name"));
		}
	}
	
	@Override
	public String delete(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		//删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);  
		return null;
	}

	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {}

	@Override
	public String save(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}
	
	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
	}
}
