package com.tenwa.leasing.service.Proj.projCredit;   

import java.util.Map;

import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;


/**
 * 
 * @ClassName: ProjCreditService 
 * @Description: 项目信审
 * @author zhangc
 * @date 2014-11-25 上午9:42:11
 */
public interface ProjCreditService {
	
	/**
	 * @Title: updateProjCreditInfo
	 * @Description: 项目信审结束
	 * @param variablesMap
	 * @throws Exception    
	 * @return void
	 */
	public void updateProjCreditInfo(Map<String, String> variablesMap) throws Exception;
	/**
	 * @Title: updateProjCreditInfo
	 * @Description: 项目复议结束-实质性风险发生变化
	 * @param variablesMap
	 * @throws Exception    
	 * @return void
	 */
	public void updateProjCreditInfo01(Map<String, String> variablesMap) throws Exception;
	/**
	 * @Title: updateProjCreditInfo
	 * @Description: 项目信审结束-	实质性风险未发生变化(方案优化)
	 * @param variablesMap
	 * @throws Exception    
	 * @return void
	 */
	public void updateProjCreditInfo03(Map<String, String> variablesMap) throws Exception;
	/**
	 * @Title: updateProjCreditInfo
	 * @Description: 项目信审结束-实质性风险未发生变化(方案劣化)
	 * @param variablesMap
	 * @throws Exception    
	 * @return void
	 */
	public void updateProjCreditInfo02(Map<String, String> variablesMap) throws Exception;
	/**
	 * 
	 * @Title: getProjCreditInfo
	 * @Description: 项目信审流程发起
	 * @param variablesMap
	 * @throws Exception    
	 * @return Map<String,String>
	 */
	public void getProjCreditInfo(Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
	/**
	 * 
	 * @Title: getFactoringProjCreditInfo
	 * @Description:保理项目信审流程发起
	 * @param variablesMap
	 * @throws Exception    
	 * @return Map<String,String>
	 */
	public void getFactoringProjCreditInfo(Map<String, String> variablesMap,JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
   
	/**
	 * @Description:保存保理信审信息
	 * @param variablesMap
	 * @param projStatusCode
	 * @throws Exception
	 */
	public void updateFactoringProjCreditInfo(Map<String, String> variablesMap,Integer projStatusCode) throws Exception ;
	/**
	 * @Description:保存保理复议信息
	 * @param variablesMap
	 * @param projStatusCode
	 * @throws Exception
	 */
	public void updateFactoringReconsiderInfo(Map<String, String> variablesMap,Integer projStatusCode) throws Exception;
	/**
	 * @Description:加载保理复议信息
	 * @param variablesMap
	 * @param jbpmWorkflowHistoryInfo
	 * @throws Exception
	 */
	public void getFactoringProjReconsiderInfo(Map<String, String> variablesMap,
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo) throws Exception;
	
}
  
