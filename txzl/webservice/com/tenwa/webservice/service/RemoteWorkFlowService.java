package com.tenwa.webservice.service;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RemoteWorkFlowService {
	  @WebMethod
	String sayHello(String message);
	  @WebMethod
	public void getEndAction(String endAction,String userId,String jbpmWorkflowHistoryInfoId);
	  @WebMethod
	public void getStartAction(String startAction,String userId,Map<String,String> map,String jbpmWorkflowHistoryInfoId);
	  @WebMethod
	  public void getStartActionBack(String startAction,String userId,Map<String,String> map,String jbpmWorkflowHistoryInfoId);
}
