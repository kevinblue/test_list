package com.tenwa.test.test;

import java.net.URL;

import org.codehaus.xfire.client.Client;


public class TestClass {
	public static void main(String[] args) {
		//System.out.println("com.tenwa.kenal.entity.er.User".matches("com.tenwa.*.entity.*"));
		try{
			
			URL url = new URL("http://localhost:88/txzl/services/SSOUserService?wsdl");
			Client client = new Client(url);
			/* Object[] result =  client.invoke("modifyAccount", new Object[]{
					"{'PERNR':'9167','ENAME':'管理员3',"
					+ "'ZHR_EMAIL':'test4444@111.com','ZHR_CELL':1111,'PASSWORD':'111111'}"
			});*/
			 Object[] result =  client.invoke("disableAccount", new Object[]{"9167"
				});
			 System.out.println(result[0]);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
