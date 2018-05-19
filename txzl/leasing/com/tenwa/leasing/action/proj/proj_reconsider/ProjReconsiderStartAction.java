package com.tenwa.leasing.action.proj.proj_reconsider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.EvaluationModelData;
import com.tenwa.leasing.entity.proj.ProjCreditWorkFlowInfo;
import com.tenwa.leasing.entity.proj.ProjDevelopInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
import com.tenwa.leasing.entity.proj.ReserveRatioData;
import com.tenwa.leasing.service.Proj.proComm.ProCommService;
import com.tenwa.leasing.service.Proj.projReconsider.ProjReconsiderService;
import com.tenwa.leasing.utils.WorkflowUtil;


@WorkflowAction(name = "projReconsiderStartAction", description = "流程开始动作", workflowName = "项目复议流程")
@Component(value = "projReconsiderStartAction")
public class ProjReconsiderStartAction implements JbpmBaseAction {

	@Resource(name = "tableService")
	private TableService tableService;
	
	@Resource(name = "projReconsiderService")
	private ProjReconsiderService projReconsiderService;
	
	@Resource(name = "proCommService")
	private ProCommService proCommService;
	
	@Override
	public void start(HttpServletRequest request,Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception {
		String proj_id = variablesMap.get("proj_id");
		//流程冲突
		variablesMap.put("workFlowFlag", WorkflowUtil.checkWorkFlowConflict(this.tableService, jbpmWorkflowHistoryInfo, proj_id)); 
		String docId = jbpmWorkflowHistoryInfo.getHistoryProcessInstanceImpl().getDbid() + "";
		variablesMap.put("docId", docId);
		ProjInfo projInfo =proCommService.loadProjInfo(variablesMap);
		variablesMap.putAll(this.tableService.getEntityPropertiesToStringMap(projInfo,null, "proj_info"));
		proCommService.loadProjRentCalculation(projInfo, variablesMap);
		proCommService.loadProjPaymentPremiseCondition(projInfo, variablesMap);
		proCommService.loadRentCalculationParam(proj_id, projInfo.getCustInfo().getCustName(), projInfo.getCustInfo().getId(), docId, variablesMap);
		proCommService.loadProjEquipment(projInfo, variablesMap);//租赁物明细
		proCommService.loadProjGuaranteeMethod(projInfo, variablesMap);//担保单位信息
		proCommService.loadProjGuaranteeEquipment(projInfo, variablesMap);//抵押物
		proCommService.loadProjInvoice(projInfo, variablesMap);//租金发票类型
		String developid = projInfo.getDevelopid().getId();
		ProjDevelopInfo developInfo= this.tableService.findEntityByID(ProjDevelopInfo.class, developid);
		//获取type类型
		Integer name = developInfo.getType();
		String type = name.toString();
		variablesMap.put("type", type);
		
		
/*		ProjCreditWorkFlowInfo projCreditWorkFlowInfo=null;
		List<ProjCreditWorkFlowInfo> projCreditWorkFlowInfos=tableService.findResultsByHSQL("from ProjCreditWorkFlowInfo where projId=?", projInfo);
		if(null!=projCreditWorkFlowInfos&&projCreditWorkFlowInfos.size()>0){
			projCreditWorkFlowInfo = projCreditWorkFlowInfos.get(0);
		}
		String creditGradeJson=projCreditWorkFlowInfo.getCreditGradeJson();
		String creditReportJson=projCreditWorkFlowInfo.getCreditReportJson();//尽职调查报告
		JSONObject jsonObject = new JSONObject(creditReportJson);
		Iterator it = jsonObject.keys();  
	       // 遍历jsonObject数据，添加到Map对象  
	       while (it.hasNext())  
	       {    
	           String key = String.valueOf(it.next());  
	           String value = (String) jsonObject.get(key);  
	           variablesMap.put(key, value);  
	       } 
		String docListJson=projCreditWorkFlowInfo.getDocListJson();
		variablesMap.put("json_credit_grade_str", creditGradeJson);*/
/*		variablesMap.put("json_document_list_str", docListJson);*/
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
