package com.tenwa.hessian.service;

import java.util.Map;

public interface HessianService {
	String sayHello(String message);
	public void getEndAction(String endAction,String userId,String jbpmWorkflowHistoryInfoId);
	public void getStartAction(String startAction,String userId,Map<String,String> map,String jbpmWorkflowHistoryInfoId);
	public void getStartActionBack(String startAction,String userId,Map<String,String> map,String jbpmWorkflowHistoryInfoId);
}
