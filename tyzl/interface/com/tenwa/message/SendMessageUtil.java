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
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.tenwa.business.entity.message.MessageConfig;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.utils.FileUtil;
import com.tenwa.kernal.utils.QueryUtil;
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
		InputStreamReader isr=null;
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
			isr=new InputStreamReader(conn.getInputStream(),"GB2312");
			rd = new BufferedReader(isr);
			String line;
			while ((line = rd.readLine()) != null) {
				ret_str += line;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		finally{
			    if(null!=isr)FileUtil.safeCloseInputStreamReader(isr);
				if(null != wr)FileUtil.safeCloseOutputStreamWriter(wr);
			 
				if(null != rd)FileUtil.safeCloseBufferedReader(rd);
			 
		}
		return ret_str;
	}

}
