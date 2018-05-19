package com.tenwa.hessian;

import java.net.URL;

import org.codehaus.xfire.client.Client;



public class ClientTest {
	 public static String url = "http://localhost:8080/sinopharm/remoting/testService";
	    public static void  main(String[] args){
	      /*  HessianProxyFactory factory = new HessianProxyFactory();
	        try {
	        	TestService service = (TestService) factory.create(TestService.class, url);
	            System.out.println("客户端："+service.sayHello());
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }*/
	    	
	    	try {
				Client client = new Client(new URL("http://10.1.3.106:8080/test/services/remoteWorkFlowService?wsdl"));
				String message = "hello";
				Object[] results = client.invoke("getEndAction", new Object[]{"2222","22","333"});
				 //System.out.println(results[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
