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
		
		String xml = "<Body>"
			+ "<complaintid>777666</complaintid>"//<!--工单ID（必填项，由我方下发到机构的咨诉工单）-->
			+ "<dealContent>豆浆机看得到1</dealContent>"//<!--处理内容-->
			+ "<dealState>处理完成2</dealState>"//<!--处理状态-->
			+ "<dealUser>80013</dealUser>"//<!--处理人-->
			+ "<dealTime>2015-12-25 11:45:66</dealTime>"//<!--处理时间(yyyy-mm-dd hh24:mi:ss)-->
			+ "</Body>";
		System.out.println(hc.requst_webs_getCtiDate(xml));
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
		String uri="http://localhost:8080/ZRzixunWebService/services/GetZiXunDateServer?wsdl";
		Client client;
		try {
			client=new Client(new URL(uri));
			Object params[]={request_xml};
			result_xml=(String)client.invoke("getZiXunDate", params)[0];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_xml;
	}
}
