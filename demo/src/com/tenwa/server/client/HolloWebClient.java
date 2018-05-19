package com.tenwa.server.client;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.xfire.client.Client;

public class HolloWebClient {

	public static void main(String[] args) {
		HolloWebClient hc=new HolloWebClient();
		StringBuffer sb=new StringBuffer();
		sb.append("<?xml version='1.0' encoding='GBK'?>");
		sb.append("<ufinterface>");
		sb.append("<bill>");
		sb.append("<Flag>0</Flag>");
		sb.append("<Desc>交易结果</Desc>");
		sb.append("</bill>");
		sb.append("<billhead>");
		sb.append("<pk>000</pk>");
		sb.append("<flow_id>6789</flow_id>");
		sb.append("<pk_paybill>1111</pk_paybill>");
		sb.append("<org_name>222</org_name>");
		sb.append("<bill_no>333</bill_no>");
		sb.append("<pay_way>4444</pay_way>");
		sb.append("<billmaker_date>5555</billmaker_date>");
		sb.append("<account_no>6666</account_no>");
		sb.append("<account_name>777</account_name>");
		sb.append("<pay_amt>888</pay_amt>");
		sb.append("<dept_name>99</dept_name>");
		sb.append("<operator>10</operator>");
		sb.append("<source_bill>11</source_bill>");
		sb.append("<prj_no>12</prj_no>");
		sb.append("<settle_date>13</settle_date>");
		sb.append("</billhead>");
		sb.append("</ufinterface>");
		
		String request_xml="<Body><StartDate>2013-08-26 18:00:00</StartDate><EndDate>2013-08-30 23:00:00</EndDate></Body>";
		System.out.println(hc.requst_webs_getCtiDate(sb.toString()));
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
		String uri="http://localhost:8080/demo/services/FundRequestERP?wsdl";
		//String uri="http://10.122.9.237:9080/Tw_DataSync/services/FundRequestERP?wsdl";
		//String uri="http://10.122.9.237:9080/Tw_DataSync/services/FundRequestERP?wsdl";
		Client client;
		try {
			client=new Client(new URL(uri));
			Object params[]={request_xml};
			result_xml=(String)client.invoke("example", params)[0];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_xml;
	}
	
	public String requst_fund_getCtiDate(String request_xml){
		String result_xml="";
		//String uri="http://localhost:8080/demo/services/HelloWebService?wsdl";
		String uri="http://10.122.1.251:80/service/XChangeServlet?wsdl";
		Client client;
		try {
			client=new Client(new URL(uri));
			Object params[]={request_xml};
			result_xml=(String)client.invoke("sendDocument", params)[0];
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result_xml;
	}
}
