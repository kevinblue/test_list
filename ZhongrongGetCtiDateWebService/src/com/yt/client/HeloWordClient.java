package com.yt.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class HeloWordClient {

	public static void main(String[] args) {
		HeloWordClient hc=new HeloWordClient();
//		String message="你好，我是杨涛";
//		String age="10";
//		System.out.println(hc.getWSResult(message,age));
		
		String request_xml="<Body><StartDate>2013-08-26 18:00:00</StartDate><EndDate>2013-08-30 23:00:00</EndDate></Body>";
		System.out.println(hc.requst_webs_getCtiDate(request_xml));
	}
	
	public String getWSResult(String message, String age){
		String uri="http://localhost:8080/Zhongronggetctidatewebservice/services/GetCtiDateServer?wsdl";
		Client client;
		String result="";
		try {
			client=new Client(new URL(uri));
			Object params[]={message, age};
			result=(String)client.invoke("example", params)[0];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String requst_webs_getCtiDate(String request_xml){
		String result_xml="";
		String uri="http://localhost:8080/ZhongrongGetCtiDateWebService/services/GetCtiDateServer?wsdl";
		Client client;
		try {
			client=new Client(new URL(uri));
			Object params[]={request_xml};
			result_xml=(String)client.invoke("getCtiDate", params)[0];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_xml;
	}
}
