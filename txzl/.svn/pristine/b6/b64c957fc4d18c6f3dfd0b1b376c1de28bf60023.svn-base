package com.tenwa.hessian.serviceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tenwa.business.entity.User;
import com.tenwa.business.service.BaseService;
import com.tenwa.hessian.service.HessianService;
import com.tenwa.jbpm.action.JbpmBaseAction;
import com.tenwa.jbpm.entity.JBPMWorkflowHistoryInfo;
import com.tenwa.jbpm.utils.JBPMUtil;
import com.tenwa.kernal.utils.SecurityUtil;
import com.tenwa.kernal.utils.WebUtil;

@Service(value="hessianService")
public class HessianServiceImpl implements HessianService {
	

	@Resource(name = "baseService")
	private BaseService baseService;
	
	@Override
	public void getEndAction(String endAction,String userId,String jbpmWorkflowHistoryInfoId) {
		System.out.println("进入服务端方法");
		try {
			User user=this.baseService.findEntityByID(User.class,userId);
			SecurityUtil.setCurrentUser(user);
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo=this.baseService.findEntityByID(JBPMWorkflowHistoryInfo.class, jbpmWorkflowHistoryInfoId);
			JbpmBaseAction endEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(endAction);
			Map<String, String> variablesMap = JBPMUtil.getVaribalesMapByAllString(
	       			jbpmWorkflowHistoryInfo.getProcessedFormVariables(), jbpmWorkflowHistoryInfo.getProcessedFormValues());
			endEventAction.end(null, variablesMap, jbpmWorkflowHistoryInfo);
			baseService.updateFlush();
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用成功！");
		} catch (Exception e) {
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用失败！");
			e.printStackTrace();
		}
	}
	

	@Override
	public void getStartAction(String startAction,String userId,Map<String, String> map,
			String jbpmWorkflowHistoryInfoId) {
		System.out.println("进入服务端方法");
		try {
			User user=this.baseService.findEntityByID(User.class,userId);
			SecurityUtil.setCurrentUser(user);
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo=this.baseService.findEntityByID(JBPMWorkflowHistoryInfo.class, jbpmWorkflowHistoryInfoId);
			JbpmBaseAction startEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(startAction);
			Map<String, String> variablesMap = JBPMUtil.getVaribalesMapByAllString(
	       			jbpmWorkflowHistoryInfo.getProcessedFormVariables(), jbpmWorkflowHistoryInfo.getProcessedFormValues());
			startEventAction.start(null, variablesMap, jbpmWorkflowHistoryInfo);
			baseService.updateFlush();
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用成功！");
		} catch (Exception e) {
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用失败！");
			e.printStackTrace();
		}
	}
	
	@Override
	public void getStartActionBack(String startAction,String userId,Map<String, String> map,
			String jbpmWorkflowHistoryInfoId) {
		System.out.println("进入服务端方法");
		try {
			User user=this.baseService.findEntityByID(User.class,userId);
			SecurityUtil.setCurrentUser(user);
			JBPMWorkflowHistoryInfo jbpmWorkflowHistoryInfo=this.baseService.findEntityByID(JBPMWorkflowHistoryInfo.class, jbpmWorkflowHistoryInfoId);
			JbpmBaseAction startEventAction = (JbpmBaseAction)WebUtil.getApplicationContext().getBean(startAction);
			Map<String, String> variablesMap = JBPMUtil.getVaribalesMapByAllString(
	       			jbpmWorkflowHistoryInfo.getProcessedFormVariables(), jbpmWorkflowHistoryInfo.getProcessedFormValues());
			startEventAction.back(null, variablesMap, jbpmWorkflowHistoryInfo);
			baseService.updateFlush();
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用成功！");
		} catch (Exception e) {
			SecurityUtil.setCurrentUser(null);
			System.out.println("Hessian调用失败！");
			e.printStackTrace();
		}
	}


	@Override
	public String sayHello(String message) {
		
		return message;
	}
}
