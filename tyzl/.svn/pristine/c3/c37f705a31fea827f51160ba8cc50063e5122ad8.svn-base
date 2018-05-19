package com.reckon.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tenwa.kernal.utils.StringUtil;
import com.tenwa.kernal.utils.WebUtil;


/**
 * 
 * @author tracywindy
 */
public class Conn {

	private JdbcTemplate jdbcTemplate;

	private Logger logger = Logger.getLogger(Conn.class);

	private void initResource() {
		if (null == jdbcTemplate) {
			jdbcTemplate = (JdbcTemplate) WebUtil.getApplicationContext().getBean("jdbcTemplate");
		}
	}

	/**
	 * 执行查询
	 * 
	 * @param sql
	 *            查询的SQL语句
	 * @param mesage
	 *            输出SQL语句的前置消息字符
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> executeQuery(String sql,List value,List valuetype, String... mesage) throws Exception {
		String temp = "";
		for (int i = 0; i < mesage.length; i++) {
			temp += mesage[i] + " ";
		}
		if (temp.equals("")) {
			if (logger.isDebugEnabled()) {
				logger.debug("执行的查询SQL语句为:" + sql);
			}
		} else {
			logger.info(temp + ":" + sql);
		}
		initResource();
		List<Map<String, String>> rs = new ArrayList<Map<String, String>>();
		for (Map<String, Object> rowMap : jdbcTemplate.queryForList(sql)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String keyString : rowMap.keySet()) {
				map.put(keyString.toLowerCase(), StringUtil.nullToString(rowMap.get(keyString)));
			}
			rs.add(map);
		}
		return rs;
	}

	/**
	 * 执行的修改语句
	 * 
	 * @param sql
	 *            update或delete的SQL语句
	 * @param mesage
	 *            输出SQL语句的前置消息字符
	 * @return
	 * @throws Exception
	 */
	public int executeUpdate(String sql,List value,List valuetype, String... mesage) throws Exception {
		String temp = "";
		for (int i = 0; i < mesage.length; i++) {
			temp += mesage[i] + " ";
		}
		if (temp.equals("")) {
			if (logger.isDebugEnabled()) {
				logger.debug("更新或者删除SQL语句为:" + sql);
			}
		} else {
			logger.info(temp + ":" + sql);
		}
		initResource();
		return jdbcTemplate.update(sql,value.toArray());
	}

	/*** closeResources ***/
	public void close() {
		/*
		 * try { if (rs != null) rs.close(); if (stmt != null) stmt.close(); if
		 * (conn != null) conn.close(); rs = null; stmt = null; conn = null; }
		 * catch (Exception e) { logger.error(e.getMessage()); }
		 */
	}
}
