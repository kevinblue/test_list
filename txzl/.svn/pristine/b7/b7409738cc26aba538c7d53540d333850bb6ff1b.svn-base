package com.tenwa.leasing.action.proj.proj_credit;

import java.util.ArrayList;
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
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.EvaluationModelData;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ReserveRatioData;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projCredit.ProjCreditService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "projCreditStartAction", description = "流程开始动作", workflowName = "项目信审流程")
@Component(value = "projCreditStartAction")
public class ProjCreditStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "projCreditService")
	private ProjCreditService projCreditService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String proj_id = variablesMap.get("proj_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		variablesMap.put("docId", jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "");
		projCreditService.getProjCreditInfo(variablesMap,jbpmWorkflowHistoryInfo);
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		proCommService.loadProjInvoice(projInfo, variablesMap); //租金开票类型
		String developid = projInfo.getDevelopid().getId();
		ProjDevelopInfo developInfo= this.tableService.findEntityByID(ProjDevelopInfo.class, developid);
		//获取type类型
		Integer name = developInfo.getType();
		String type = name.toString();
		variablesMap.put("type", type);
		variablesMap.put("proj_info.projdeveloptype", type);
		variablesMap.put("proj_id", proj_id);
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
		
		//删除流程删除经评模型数据
		 List<EvaluationModelData> emdListNew = new ArrayList<EvaluationModelData>();
		   List<ReserveRatioData> rrdListNew = new ArrayList<ReserveRatioData>();
		   List<BaseFile> bfListNew = new ArrayList<BaseFile>();
		   HashMap<String, Object> propertiesMap = new HashMap<String, Object>();
		 String projId = variablesMap.get("proj_id");
		  propertiesMap.put("projId", projId);
	      propertiesMap.put("status", "1");
	      List<EvaluationModelData> emdList = this.tableService.findEntityByProperties(EvaluationModelData.class,propertiesMap);
	      List<ReserveRatioData> rrdList = this.tableService.findEntityByProperties(ReserveRatioData.class,propertiesMap);
	      propertiesMap.clear();
	      propertiesMap.put("fileNumber", projId);
	      propertiesMap.put("invalid", "是");
	      List<BaseFile> bfList = this.tableService.findEntityByProperties(BaseFile.class,propertiesMap);
         for(EvaluationModelData emd:emdList){
       	  emd.setStatus("2");
       	 emdListNew.add(emd);
         };
         for(ReserveRatioData rrd:rrdList){
      	      rrd.setStatus("2");
      	      rrdListNew.add(rrd);
	       };
	       for(BaseFile bf:bfList){
	    	   bf.setInvalid("否");
	    	   bfListNew.add(bf);
		       };
	     this.tableService.updateAllEntities(emdListNew);
	     this.tableService.updateAllEntities(rrdListNew);
	     this.tableService.updateAllEntities(bfListNew);
		return null;
	}

	
	
	
}
