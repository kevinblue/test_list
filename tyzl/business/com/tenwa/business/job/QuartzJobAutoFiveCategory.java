
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.job
 * 文件名：         QuartzJobTest.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-2-21-上午11:00:16
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tenwa.business.entity.Dictionary;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.service.BaseService;
import com.tenwa.kernal.annotation.QuartzJob;
import com.tenwa.kernal.utils.DateUtil;
import com.tenwa.kernal.utils.WebUtil;


 /**
 * 类名称：     QuartzJobAutoFiveCategory
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2015-11-16 上午11:00:16
 * 修改备注：
 * @version 1.0.0
 **/
@QuartzJob(description="定时合同五级分类")
public class QuartzJobAutoFiveCategory implements Job {

	/**
	 * (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 **/

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//使用spring的事物
		BaseService baseService = (BaseService)WebUtil.getApplicationContext().getBean("baseService");
		//使用springAOP事物管理的service做一些事情
		System.out.println(">>>当前时间:"+DateUtil.getSystemDateTime());
		
		Dictionary dict = new Dictionary();
		dict.setId("five_class");
		
		Map<String, Object> propertiesMap = new HashMap<String, Object>();
		propertiesMap.put("belongDictionary", dict);
		
		Map<String, String> formulaMap = new HashMap<String, String>();
		try {
			List<Map<String, Object>> dictDataList = baseService.queryListBySql("SELECT ID_ AS ID, PROP_ONE_ AS PROPONE FROM T_DICTS_DATAS TDD WHERE TDD.PID_ = 'five_class'", null);
			for(Map<String, Object> dictData : dictDataList){
				formulaMap.put(dictData.get("id").toString(), dictData.get("propone").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String deleteCfcSql = "DELETE FROM CONTRACT_FIVE_CATEGORY CFC"
				+ " WHERE EXISTS (SELECT CI.ID FROM CONTRACT_INFO CI WHERE CI.CONTRACT_STATUS = 31 AND NVL(CI.IS_AUTO_CATEGORY, '是') = '是')";
		
		String contractOverdueDaySql = "               SELECT CI.ID, "
				+ "                      NVL(CC.OVER_DUE_DAY, 0) AS OVERDUEDAY,"
				+ "                      CI.CONTRACT_ID"
				+ "                 FROM CONTRACT_INFO CI"
				+ "                 LEFT JOIN ("
				+ "                   SELECT NVL(MAX(TRUNC(SYSDATE - TO_DATE(CFRP.PLAN_DATE, 'YYYY-MM-DD'), 0)), 0) AS OVER_DUE_DAY,"
				+ "                          CI.CUST_ID"
				+ "                     FROM CONTRACT_FUND_RENT_PLAN CFRP"
				+ "                     LEFT JOIN (SELECT NVL(SUM(INCOME.RENT), 0) AS RENT,"
				+ "                                       INCOME.PLAN_LIST,"
				+ "                                       INCOME.CONTRACT_ID"
				+ "                                       FROM CONTRACT_FUND_RENT_INCOME INCOME"
				+ "                                 GROUP BY INCOME.CONTRACT_ID, INCOME.PLAN_LIST) INCOME"
				+ "                       ON CFRP.CONTRACT_ID = INCOME.CONTRACT_ID"
				+ "                      AND CFRP.RENT_LIST = INCOME.PLAN_LIST"
				+ "                     LEFT JOIN CONTRACT_INFO CI ON CFRP.CONTRACT_ID = CI.ID"
				+ "                    WHERE CFRP.RENT > NVL(INCOME.RENT, 0) AND CI.CONTRACT_STATUS = 31"
				+ "                    GROUP BY CI.CUST_ID"
				+ "                 ) CC ON CI.CUST_ID = CC.CUST_ID"
				+ "                WHERE CI.CONTRACT_STATUS = 31";
		
		String insertCfcSql = "INSERT INTO CONTRACT_FIVE_CATEGORY CFC (ID, CONTRACT_ID, CONTRACTFIVE_BUSINESS, CONTRACTFIVEDATE_BUSINESS, EXPLAIN_BUSINESS, CREATOR_, CREATE_DATE) "
				+ " VALUES(SYS_GUID(), ?, ?, ?, ?, 'Administrator', ?)";
		
		try {
			//1、删除之前的自动分类数据
			baseService.updateBySql(deleteCfcSql, null);
			//2、查出自动分类合同的逾期天数
			List<Map<String, Object>> overdueDayList = baseService.queryListBySql(contractOverdueDaySql, null);
			
			List<String> insertCfcSqlList = new ArrayList<String>();
			
			ScriptEngine se = new ScriptEngineManager().getEngineByName("JavaScript");
			
			String nowDate = DateUtil.getSystemDate();
			String nowTime = DateUtil.getSystemDateTime();
			
			//3、生成插入sql
			for(Map<String, Object> map : overdueDayList){
				insertCfcSqlList.add("INSERT INTO CONTRACT_FIVE_CATEGORY CFC (ID, CONTRACT_ID, CONTRACTFIVE_BUSINESS, CONTRACTFIVEDATE_BUSINESS, EXPLAIN_BUSINESS, CREATOR_, CREATE_DATE) "
						+ " VALUES(SYS_GUID(), '" + map.get("id") + "', '"
						+ getFiveCategory(se, map.get("overdueday").toString(), formulaMap) + "','" + nowDate + "', '" + "合同" + map.get("contract_id") + "五级自动分类" + "', 'Administrator', '" + nowTime + "')");
			}
			//4、执行插入操作
			if(insertCfcSqlList != null && insertCfcSqlList.size() > 0){
				for(String insertSql : insertCfcSqlList){
					baseService.updateBySql(insertSql, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static String getFiveCategory(ScriptEngine se, String overdueDay, Map<String, String> formulaMap) throws ScriptException{
		String formulaStr = "";
		String returnKey = "";
		for(String key : formulaMap.keySet()){
			formulaStr = formulaMap.get(key).toString();
			if((Boolean)se.eval(formulaStr.replace("N", overdueDay))){
				returnKey = key;
				break;
			}
		}
		return returnKey;
	}
}
