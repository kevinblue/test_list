package com.yt.webs;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;






public class TTTT {
	public static void main(String[] args) {
		String xml = "<Body>"
				+ "<complaintid>123</complaintid>"//<!--工单ID（必填项，由我方下发到机构的咨诉工单）-->
				+ "<dealContent>豆浆机看得到</dealContent>"//<!--处理内容-->
				+ "<dealState>处理完成</dealState>"//<!--处理状态-->
				+ "<dealUser>8001</dealUser>"//<!--处理人-->
				+ "<dealTime>2015-12-25 11:45:66</dealTime>"//<!--处理时间(yyyy-mm-dd hh24:mi:ss)-->
				+ "</Body>";
		
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootElt = doc.getRootElement(); // 获取根节点
			String complaintid = rootElt.elementTextTrim("complaintid");//工单ID
			String dealContent = rootElt.elementTextTrim("dealContent");//处理内容
			String dealState = rootElt.elementTextTrim("dealState");// 处理状态
			String dealUser = rootElt.elementTextTrim("dealUser");// 处理人
			String dealTime = rootElt.elementTextTrim("dealTime");//处理时间

			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
