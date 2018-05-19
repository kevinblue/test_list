package com.tenwa.business.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.tenwa.business.entity.notice.EmailNotice;
import com.tenwa.business.entity.notice.SmsNotice;
import com.tenwa.business.entity.notice.SmsTemplate;
import com.tenwa.business.model.Chart;

public interface QuartzJobService {
	
	//创建短信
	public void createCollectionMsg(Map<String, String> sqlMap) throws Exception;
	
	//创建邮件
	public void createCollectionEmail(Map<String, String> sqlMap) throws Exception;
	
	//发送短信
	public void doSendCollectionMsg(String queryMsgHql) throws Exception;
	
	//发送邮件
	public void doSendCollectionEmail(String queryEmailHql) throws Exception;
	
}
