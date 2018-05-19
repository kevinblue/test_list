/**
 * 项目名称：    系统名称
 * 包名：              com.message
 * 文件名：         SendMessage.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-下午12:29:53
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import com.tenwa.business.entity.message.MessageConfig;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.MD5Util;
import com.tenwa.kernal.utils.QueryUtil;
import com.tenwa.kernal.utils.ResourceUtil;
import com.tenwa.kernal.utils.WebUtil;

/**
 * 类名称： SendMessage 
 * 类描述： 创建人： Administrator 
 * 修改人： Administrator 
 * 修改时间：2013-12-2
 * 下午12:29:53 修改备注：
 * 
 * @version 1.0.0
 **/

public class SendMessageUtil 
{
	
	 /**
	 * @method sendMessage 发送短信到指定号码
	 * @param receiverMobilePhone 接收方手机号码
	 * @param msg 消息内容
	 * @return
	 * @throws Exception
	 * @returnType String
	 * @exception  
	 * @since      1.0.0
	 **/
	public static String sendMessage(String receiverMobilePhone, String msg) throws Exception 
	{
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		List<MessageConfig> messageConfigList = baseService.findEntities(MessageConfig.class);
		MessageConfig messageConfig = null;
		if(0 == messageConfigList.size()){
			messageConfig = new MessageConfig();
			messageConfig.setHost("10.1.128.21");
			messageConfig.setPort(8012);
			messageConfig.setSender("its");
			messageConfig.setSendMessageUrl("http://{host}:{port}/sendSms.cgi");
			messageConfig.setSendParamsUrl("sender={sender}&receiver={receiverMobilePhone}&msg={msg}");
		    baseService.saveEntity(messageConfig);
		}else{
			messageConfig = messageConfigList.get(0);
		}
		Map<String,String>  model = baseService.getEntityPropertiesToStringMap(messageConfig, null);
		model.put("receiverMobilePhone", receiverMobilePhone);
		model.put("msg", URLEncoder.encode(msg, "GB2312"));
		String ret_str = "";
		OutputStreamWriter wr = null;
		BufferedReader     rd = null;
		String sendMessageUrl = messageConfig.getSendMessageUrl();
		String dataParamUrl   = messageConfig.getSendParamsUrl();
		try {
			//Construct data
			String sendUrl = QueryUtil.getQueryString(model, sendMessageUrl); 
			String data    = QueryUtil.getQueryString(model, dataParamUrl); 
			//receiverMobilePhone
			// Send data
			URL url = new URL(sendUrl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			// Get the response
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GB2312"));
			String line;
			while ((line = rd.readLine()) != null) {
				ret_str += line;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		finally{
			try {
				if(null != wr)wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(null != rd)rd.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret_str;
	}
	private static  String mqTcpUrl ;
	
	public static String getMqTcpUrl() {
		if(null == mqTcpUrl){
			return ResourceUtil.getConfigValue("mqTcpUrl");
		}else{
			return mqTcpUrl;
		}
	}
	
	/**
	 * @method sendMessageByHttpClient 发送短信到指定号码
	 * @param receiverMobilePhone 接收方手机号码
	 * @param msg 消息内容
	 * @return
	 * @throws Exception
	 * @returnType String
	 * @exception  
	 * @since      1.0.0
	 **/
	public static String sendMessageByHttpClient(String receiverMobilePhone, String msg) throws Exception {

		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		List<MessageConfig> messageConfigList = baseService.findEntities(MessageConfig.class);
		
		MessageConfig messageConfig = null;
		if (0 == messageConfigList.size()) {
			messageConfig = new MessageConfig();
			messageConfig.setHost("10.1.128.21");
			messageConfig.setPort(8012);
			messageConfig.setSender("its");
			messageConfig.setSendMessageUrl("http://{host}:{port}/sendSms.cgi");
			messageConfig.setSendParamsUrl("sender={sender}&receiver={receiverMobilePhone}&msg={msg}");
			baseService.saveEntity(messageConfig);
		}else{
			messageConfig = messageConfigList.get(0);
		}
		String allowSendIps = messageConfig.getAllowSendIps() == null ? "" : messageConfig.getAllowSendIps();
		if("否".equals(messageConfig.getIssend()) || !getServerIsAllowSendMsg(allowSendIps)){
			System.out.println("短信设置为不发送短信或本机IP未在[允许发送短信的主机IP]之中!");
			return "否";
		}
		
		String sendParamsUrl = messageConfig.getSendParamsUrl();
		String[] sendParamsUrlArr = sendParamsUrl.split("&");
		
		Map<String,String>  model = new HashMap<String, String>();
		for(String str : sendParamsUrlArr){
			model.put(str.split("=")[0], str.split("=")[1]);
		}
		
		HttpClient httpClient = new HttpClient();
		String url = messageConfig.getSendMessageUrl();
		String uid = model.get("uid");
		String auth = MD5Util.getMD5EncodedPassword(messageConfig.getSender() + messageConfig.getSenderPassword());
		String content = java.net.URLEncoder.encode(msg, "gbk");
		PostMethod postMethod = new PostMethod(url);

		NameValuePair[] data = { new NameValuePair("uid", uid),
				new NameValuePair("auth", auth),
				new NameValuePair("mobile", receiverMobilePhone),
				new NameValuePair("expid", "0"),
				new NameValuePair("msg", content) };
		postMethod.setRequestBody(data);
		
		int statusCode = httpClient.executeMethod(postMethod);
		String result = "";
		if (statusCode == HttpStatus.SC_OK) {
			result = postMethod.getResponseBodyAsString();
		}else{
			result = statusCode + "";
		}
		postMethod.releaseConnection();
		return result;
	}
	
	public static List<String> getServerIpList() throws SocketException{
		List<String> hostAddrList = new ArrayList<String>();
		Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface)allNetInterfaces.nextElement();
			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress)addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					hostAddrList.add(ip.getHostAddress());
					System.out.println("本机的IP = " + ip.getHostAddress());
				}
			}
		}
		return hostAddrList;
	}

	public static boolean getServerIsAllowSendMsg(String allowIps) throws SocketException {
		boolean flag = false;
		List<String> hostAddrList = getServerIpList();
		if(hostAddrList.size() > 0){
			for(String ipStr : hostAddrList){
				if(allowIps.indexOf(ipStr) != -1){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
}
