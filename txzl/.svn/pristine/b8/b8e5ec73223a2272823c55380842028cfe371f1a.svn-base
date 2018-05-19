package com.tenwa.leasing.action.sapmaindatainfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.tenwa.business.service.TableService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.kernal.annotation.WorkflowAction;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "sapMainDataStartAction", description = "流程开始动作", workflowName = "SAP主数据流程")
@Component(value = "sapMainDataStartAction")
public class SapMainDataStartAction implements JbpmBaseAction{
	
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void start(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String sapnumber = WorkflowUtil.getSapMainDataSerialNumber(variablesMap, this.tableService.getBussinessDao().getHibernateTemplate(), this.tableService.getBussinessDao().getJdbcTemplate());
		variablesMap.put("Sap_Main_Data.sapnumber", sapnumber);
		variablesMap.put("Sap_Main_Data.creator", variablesMap.get("login_realname"));
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		variablesMap.put("Sap_Main_Data.createdate", sdf.format(new Date()));
	}

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
