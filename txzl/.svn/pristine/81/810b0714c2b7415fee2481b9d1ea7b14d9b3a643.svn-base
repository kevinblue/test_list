package com.reckon.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.GrossProfitConfig;
import com.reckon.dao.Conn;


public class GrossProfitDAOImpl {

	private static Logger logger = Logger.getLogger(FundPlanDAOImpl.class);

	private static final String crossProfitConfigColumns = " BASE_RATE,BEGIN_MONTH,CREDIT_MONTH_TYPE,END_MONTH,FLOAT_RATE,ID,START_DATE_ ";

	private Conn conn = null;

	public List<GrossProfitConfig> findGrossProfitConfig(int term) {
		List<GrossProfitConfig> result = new ArrayList<GrossProfitConfig>();
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "SELECT " + crossProfitConfigColumns + " FROM BASE_COARSE WHERE " + term + " BETWEEN BEGIN_MONTH AND END_MONTH ORDER BY START_DATE_ ASC";
			logger.info("查询粗利配置信息：" + sql);
			List<Map<String, String>> resultList = conn.executeQuery(sql, "查询历史调息记录...");
			for (Map<String, String> map : resultList) {
				GrossProfitConfig config = new GrossProfitConfig();
				config.setBaseRate(map.get("base_rate"));
				config.setBeginMonth(map.get("begin_month"));
				config.setCreditMonthType(map.get("credit_month_type"));
				config.setEndMonth(map.get("end_month"));
				config.setFloatRate(map.get("float_rate"));
				config.setId(map.get("id"));
				config.setStartDate(map.get("start_date_"));
				result.add(config);
			}
		} catch (Exception e) {
			logger.error("查询粗利配置信息错误：" + e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 
	 * 查询配置中适应起租日期的配置信息
	 * 获取固定利率或者浮动利率的R值
	 * 这个R值在计算粗利时使用，应该是一个IRR的浮动系数
	 * 
	 * @param term 租赁期限
	 * @param startDate 起租日期
	 * @return
	 */
	public GrossProfitConfig findLastGrossProfitConfig(int term, String startDate) {
		try {
			conn = (conn == null ? new Conn() : conn);
			String sql = "SELECT " + crossProfitConfigColumns + " FROM BASE_COARSE WHERE BEGIN_MONTH<=" + term + " AND END_MONTH>=" + term + " AND START_DATE_ <='" + startDate + "' ORDER BY START_DATE_ DESC";
			logger.info("查询粗利配置信息：" + sql);
			List<Map<String, String>> resultList = conn.executeQuery(sql, "查询历史调息记录...");
			if (resultList != null && resultList.size() > 0) {
				Map<String, String> map = resultList.get(0);
				GrossProfitConfig config = new GrossProfitConfig();
				config.setBaseRate(map.get("base_rate"));
				config.setBeginMonth(map.get("begin_month"));
				config.setCreditMonthType(map.get("credit_month_type"));
				config.setEndMonth(map.get("end_month"));
				config.setFloatRate(map.get("float_rate"));
				config.setId(map.get("id"));
				config.setStartDate(map.get("start_date_"));
				return config;
			}
		} catch (Exception e) {
			logger.error("查询粗利配置信息错误：" + e.getMessage(), e);
		}
		return null;
	}
}
