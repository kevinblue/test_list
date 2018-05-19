package com.tenwa.jbpm.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;


public interface JbpmBaseAction 
{  
	   
     /**
      * 开始动作逻辑操作
     * @param variablesMap
     */
    public void start(HttpServletRequest request,Map<String,String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
	   
    /**
     * 退回到该节点动作逻辑操作(触发)
    * @param variablesMap
    */
    public void back(HttpServletRequest request,Map<String,String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
    /**
     * 结束动作逻辑操作
     * @param variablesMap
     */
    public void end(HttpServletRequest request,Map<String,String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
    /**
     * 保存操作
     * @param variablesMap
     */
    public String save(HttpServletRequest request,Map<String,String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
    /**
     * 丢弃流程实例(删除操作触发)
     * @param processInsanceDataMap
     */
    public String delete(HttpServletRequest request,Map<String,String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
}
