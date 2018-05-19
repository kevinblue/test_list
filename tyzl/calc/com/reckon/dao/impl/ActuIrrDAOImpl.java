package com.reckon.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.dao.Conn;


/**
 * 
 * @author      SCLICX
 * @version     2.0
 * @copyright   (C) Tenwa 2011
 * @date        Dec 5, 2011
 * @desc        查询出构建对应合同现金流的全部数据
 */
public class ActuIrrDAOImpl {
	Logger	logger	= Logger.getLogger(ActuIrrDAOImpl.class);


	/**
	 * 
	 * select fact_date,SUM(fact_money) fact_money from (
	 * --查询资金的实收
	 * select convert(varchar(10),fact_date,120)fact_date,
	 * case when settle_method='付款' and fact_money>0 then 0-fact_money else fact_money end fact_money from fund_fund_charge  where contract_id='' 
	 * union all
	 * --未收到和部分的资金收款数据按照计划构建现金流
	 * select plan_date,case when pay_type='付款' then 0-surplus_money else surplus_money end plan_money from vi_fund_fund_charge_plan  
	 * where contract_id='' and ( status like'未%' or status like '部分%') 
	 * union all
	 * --查询租金的实收
	 * select hire_date,rent from fund_rent_income where contract_id=''
	 * union all
	 * --未收到和部分的租金收款数据按照计划构建现金流
	 * select plan_date,rent_overage from vi_fund_rent_plan where contract_id='' and ( status like'未%' or status like '部分%') 
	 * ) rs group by fact_date
	 * 
	 * @param contract_id
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String,List<String>> getActuCash(String contract_id) throws Exception {
        List paramValue=new ArrayList();
        List paramType=new ArrayList();
		Conn conn = new Conn();
		paramValue.add(contract_id);
		paramValue.add(contract_id);
		paramValue.add(contract_id);
		paramValue.add(contract_id);
		paramType.add(Types.VARCHAR);
		paramType.add(Types.VARCHAR);
		paramType.add(Types.VARCHAR);
		paramType.add(Types.VARCHAR);
		String sql="";
		sql="SELECT FACT_DATE,SUM(FACT_MONEY) FACT_MONEY "+
				  " FROM ( "+
				  "      SELECT CASE WHEN last_MONEY=0 THEN fact_date ELSE plan_DATE END FACT_DATE,"+
				  "           CASE"+
				  "              WHEN pay_type = 'pay_type_out' THEN"+
				  "               0 - CASE WHEN last_MONEY=0 THEN plan_money ELSE last_MONEY end"+
				  "              ELSE"+
				  "              CASE WHEN last_MONEY=0 THEN plan_money ELSE last_MONEY end"+
				  "            END FACT_MONEY"+
				  "       FROM ("+
				  "     SELECT  cffp.plan_DATE,cffc.fact_date,cffp.pay_type,cffp.plan_money,nvl(cffp.plan_money,0)-nvl(cffc.FACT_MONEY,0)last_MONEY"+
				  "       	FROM contract_fund_fund_plan  cffp LEFT JOIN"+
				  "               (SELECT payment_id,SUM(FACT_MONEY) FACT_MONEY,MAX(fact_date)fact_date"+ 
				  "              FROM contract_fund_fund_charge WHERE CONTRACT_ID = ? "+
				  "             GROUP BY payment_id)  cffc ON (cffp.id=cffc.payment_id) "+  
				  "               WHERE cffp.CONTRACT_ID = ? "+
				  "      )tt "+
				  "    UNION ALL"+
				  "     SELECT CASE WHEN last_MONEY=0 THEN fact_date ELSE plan_DATE END plan_DATE,"+
				  "            CASE WHEN last_MONEY=0 THEN rent ELSE last_MONEY END  FACT_MONEY"+
				  "     FROM ("+
				  "       SELECT  cfrp.plan_DATE,cfri.fact_date,cfrp.rent,nvl(cfrp.rent,0)-nvl(cfri.rent,0)last_MONEY "+
				  "    	FROM contract_fund_rent_plan  cfrp LEFT JOIN"+
				  "            (SELECT plan_id,SUM(rent) rent,MAX(hire_date)fact_date "+
				  "             FROM contract_fund_rent_income WHERE CONTRACT_ID = ? "+
				  "             GROUP BY plan_id)  cfri ON (cfrp.id=cfri.plan_id)"+
				  "               WHERE cfrp.CONTRACT_ID = ? "+
				  "       )"+
				  "        ) RS"+
				  " GROUP BY FACT_DATE ORDER BY FACT_DATE";
		List<Map<String, String>> rs_list = conn.executeQuery(sql,paramValue,paramType,"查询合同",contract_id,"的全部现金流数据");
		List<String> date_list = new ArrayList<String>() ;
		List<String>  money_list = new ArrayList<String>() ;
		Hashtable<String,List<String>>  ht = new Hashtable<String,List<String>>();
		for (Map<String, String> rs:rs_list) {
			date_list.add(rs.get("fact_date"));
			money_list.add(rs.get("fact_money"));
			
		}
		ht.put("fact_date", date_list);
		ht.put("fact_money", money_list);
		//DaoUtil.closeRSOrConn(rs, conn);
		return ht;
	}
}
