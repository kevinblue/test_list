package com.tenwa.leasing.action.lawmng.law_close;
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
import com.tenwa.leasing.entity.lawmng.LawApproval;
 
@WorkflowAction(name = "lawcloseEndAction", description = "流程结束动作", workflowName = "法务结案流程")
@Component(value = "lawcloseEndAction")
public class LawCloseEndAction implements JbpmBaseAction {
	@Resource(name = "tableService")
	private TableService tableService;
	
	@Override
	public void end(HttpServletRequest request, Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		/*根据法务编号update 到LAW_APPROVAL 表*/
		String lawnum = variablesMap.get("lawnum");
		HashMap<String, Object> propertiesMap = new HashMap<String,Object>();
		propertiesMap.put("lawnum", lawnum);
		List<LawApproval> list = this.tableService.findEntityByProperties(LawApproval.class, propertiesMap);
		if(list.size()> 0){
			LawApproval lawApproval = list.get(0);
			lawApproval.setCloseinfo(variablesMap.get("law_approval.closeinfo"));
			lawApproval.setClosemoney(variablesMap.get("law_approval.closemoney"));
			lawApproval.setClosememo(variablesMap.get("law_approval.closememo"));
			lawApproval.setJalawtype(variablesMap.get("law_approval.jalawtype"));
			lawApproval.setLawmoney(variablesMap.get("law_approval.lawmoney"));
			lawApproval.setNetrecmoney(variablesMap.get("law_approval.netrecmoney"));
			lawApproval.setClosedate(DateUtil.getSystemDateTime());
			this.tableService.updateEntity(lawApproval);
		}
		
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
