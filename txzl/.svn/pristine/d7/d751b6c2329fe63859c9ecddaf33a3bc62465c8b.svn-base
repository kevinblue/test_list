package com.tenwa.leasing.action.contract.assetsnetmonitorreport;

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
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApply;
import com.tenwa.leasing.entity.assetnetworkmonitor.AssetNetMonitorApplyDetail;
import com.tenwa.leasing.service.contractcomm.ContractCommService;
import com.tenwa.leasing.utils.WorkflowUtil;

@WorkflowAction(name = "netAssetsMonitorReporStartAction", description = "流程开始动作", workflowName = "网络资产监控")
@Component(value = "netAssetsMonitorReporStartAction")
public class NetAssetsMonitorReporStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;

	@Resource(name = "contractCommService")
	private ContractCommService contractCommService;

	@Override
	public void back(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String delete(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		// 删除流程互斥
		WorkflowUtil.deleteWorkFlowConflict(this.tableService, variablesMap);
		String applyid = variablesMap.get("applyid");//申请Id
		AssetNetMonitorApply assetNetMonitorApply=this.tableService.findEntityByID(AssetNetMonitorApply.class, applyid);
		
		assetNetMonitorApply.setApplystatus("未审核");
		this.tableService.updateEntity(assetNetMonitorApply);
		return null;
	}

	@Override
	public void end(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {

	}

	@Override
	public String save(HttpServletRequest request,
			Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		return null;
	}

	@Override
	public void start(HttpServletRequest request, Map<String, String> variablesMap, JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String applyid = variablesMap.get("applyid");//申请Id
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, applyid)); 
		AssetNetMonitorApply assetNetMonitorApply=this.tableService.findEntityByID(AssetNetMonitorApply.class, applyid);
		
		assetNetMonitorApply.setApplystatus("审核中");
		this.tableService.updateEntity(assetNetMonitorApply);
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(assetNetMonitorApply, null, ""));
		variablesMap.put("applyusername", assetNetMonitorApply.getApplyuser().getRealname());
		variablesMap.put("applyid", assetNetMonitorApply.getId());
		variablesMap.put("applyno", assetNetMonitorApply.getApplynumber());
		variablesMap.put("modifydate", assetNetMonitorApply.getModifyDate()==null?"":assetNetMonitorApply.getModifyDate());
		variablesMap.put("creator", assetNetMonitorApply.getCreator().getRealname());
		variablesMap.put("modificatorname", assetNetMonitorApply.getModificator()==null?"":assetNetMonitorApply.getModificator().getRealname());		
		//申请基本信息
//		Map<String,Object> propertiesMap=new HashMap<String, Object>();
//	    List<AssetNetMonitorApplyDetail> assetNetMonitorApplyDetaillist=this.tableService.findEntityByProperties(AssetNetMonitorApplyDetail.class, propertiesMap);
//		//申请详细
	    
//	    String json_csut_apply_list_str=this.tableService.getCollectionEntitiesPropertiesToJsonArrayString(assetNetMonitorApplyDetaillist, null, "");
//		variablesMap.put("json_csut_apply_list_str", json_csut_apply_list_str);
	}
}
