package com.yt.webs;


import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.db.DataBaseManager;

/**
 * 第一步 解析请求过来的xml文件
 * 
 * 第二步：插入数据库
 * 
 */
public class GetZiXunDateServer implements IGetZiXunDateServer {

	private static final Logger log = Logger.getLogger(GetZiXunDateServer.class);
	private static DataBaseManager db = DataBaseManager.getInstance();
	private int rowCount = 0;// 返回值
	private String str ="";
	public String getZiXunDate(String para) {
		log.info("开始执行。。。。" + getDate());
	
		try {
			log.info("第一步：获取请求的参  报文并且解析" + para);
			Document doc = DocumentHelper.parseText(para);
			Element rootElt = doc.getRootElement(); // 获取根节点
			String complaintid = rootElt.elementTextTrim("complaintid");// 工单ID
			String dealContent = rootElt.elementTextTrim("dealContent");// 处理内容
			String dealState = rootElt.elementTextTrim("dealState");// 处理状态
			String dealUser = rootElt.elementTextTrim("dealUser");// 处理人
			String dealTime = rootElt.elementTextTrim("dealTime");// 处理时间
			Role r = new Role();
			r.setComplaintid(complaintid);
			r.setDealContent(dealContent);
			r.setDealState(dealState);
			r.setDealUser(dealUser);
			r.setDealTime(dealTime);

			log.info("第二步：插入数据");
			rowCount = InsertRole(r);// 调用该方法获取结果集\
		
			 if(rowCount==0){
				 
				 str+="保护成功"; 
			 }else{
				 str+="操作失败"; 
			 }
			log.info("rowCount====" + rowCount);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			 str+="解释XLM数据错误"; 
			e.printStackTrace();

		}
		String result_xml = "<?xml version='1.0' encoding='GBK'?>"+
"<TranData>"+
"<Head>"+
"<Flag>"+rowCount+"</Flag>"+//<!—0-交易成功；1-交易失败-->
"<Desc>"+str+"</Desc>"+//<!-- 交易结果描述 -->
"</Head><Body></Body></TranData>";
		str="";
		rowCount = 0;

		return result_xml;
	}

	/**
	 * 
	 * 根据请求过来的参数连接oracle插入数据
	 * 
	 * 把结果集拼接成XML文件返回报文
	 * 
	 */
	public int InsertRole(Role r) {
		// 加载oracle数据库连接

		Connection conn = null;
		PreparedStatement inspstmt = null;

		int i = 0;

		try {
			conn = db.getConnection();

			String sql = "insert into se_hrsl_work_tem(tuid,create_date,complaintid,dealcontent,dealState,dealuser, dealtime)values(seq_default.nextval,sysdate,?,?,?,?,?)";
			inspstmt = conn.prepareStatement(sql);
			inspstmt.setString(1, r.getComplaintid());
			inspstmt.setString(2, r.getDealContent());
			inspstmt.setString(3, r.getDealState());
			inspstmt.setString(4, r.getDealUser());
			inspstmt.setString(5, r.getDealTime());
		
			i = inspstmt.executeUpdate();
			// 处理结果
			if (i > 0) {
				i = 0;
				System.out.println("操作成功");
			} else {
				i = 1;
				System.out.println("操作失败");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("插入数据库异常", e);
			i = 1;
			 str+="插入数据库异常"; 
		} finally {
			log.info("关闭数据库连接");

			try {
				if (conn != null) {
					conn.close();
				}
				if (inspstmt != null) {
					inspstmt.close();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;

	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 获取当前时间
		return df.format(new Date());
	}

}
