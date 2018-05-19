package com.tenwa.hessian;

import com.caucho.hessian.client.HessianProxyFactory;
import com.tenwa.hessian.service.HessianService;

public class HessianClient {
   public static void serverEndAction(String endAction,String userId,String jbpmWorkflowHistoryInfoId){
	   HessianProxyFactory factory = new HessianProxyFactory();
	   try {
    	    String url = "http://127.0.0.1:8080/sinopharm/remoting/hessianService";  
	       	HessianService service = (HessianService) factory.create(HessianService.class, url);
	       	service.getEndAction(endAction,userId,jbpmWorkflowHistoryInfoId);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
