package com.reckon.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.Conn;
import com.tenwa.kernal.utils.UUIDUtil;

/**
 * 
 * @author zhangc
 * @ClassName: FundFundChargeDAOImpl 
 * @date 2014年12月16日 下午7:36:57 
 * @Description: 生成资金计划
 */

public class FundFundChargeDAOImpl {
	
	private static Logger logger = Logger.getLogger(FundFundChargeDAOImpl.class);
	
	/**
	 * 
	 * 删除原资金计划
	 * 
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFundFundCharge( ConditionBean cb,TabCalBean tcb) throws Exception {

		Conn conn = new Conn();
		// 报价编号
		String sql = " delete from FUND_FUND_PLAN_TEMP where  doc_id=? and "+tcb.getContOrProjCName()+"=?";
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(cb.getDocId() );
		paramValue.add(tcb.getContOrProjCValue() );
		paramType.add(Types.VARCHAR);
		paramType.add(Types.VARCHAR);
		conn.executeUpdate(sql,paramValue,paramValue);
		return true;
	}

	/**
	 * 
	 * 资金计划保存到临时表中
	 * 
	 * @param cb
	 * @param fp
	 * @return  
	 */
	public boolean addFundFundCharge(ConditionBean cb, List<FundPlanBean> fp,TabCalBean tcb) throws Exception {
		//为了不改变整个测算的 代码，资金计划临时性的插入到一张中间表当中，方便拼现金流
		for(FundPlanBean fund : fp){
			//租前息不参与现金流计算IRR
			if("feetype9".equalsIgnoreCase(fund.getFeeType())){
				continue;
			}
			Conn conn = new Conn();
			StringBuffer sb = new StringBuffer();
			sb.append(" INSERT INTO FUND_FUND_PLAN_TEMP").
			append("(ID,doc_id").
			append(",FEE_TYPE,").
			append(tcb.getContOrProjCName()+",").
			append("PLAN_DATE,PLAN_MONEY,PAY_OBJ,PAY_CUST,PAY_CUST_NAME,").
			append("SETTLE_METHOD,PAY_TYPE ").
			append(") VALUES(").//sys_guid()  '" + UUIDUtil.getUUID() + "'
			append("'"+UUIDUtil.getUUID()+"','" + cb.getDocId()).
			append("','" + fund.getFeeType()).
			append("','" + tcb.getContOrProjCValue()).
			append("','" + fund.getPlanDate()).
			append("','" + fund.getPlanMoney()).
			append("','" + fund.getPayObj()).
			append("','" + fund.getPayCust()).
			append("','" + fund.getPayCustName()).
			append("','" + fund.getSettleMethod()).
			append("','" + fund.getPayType()).
			append("')");
			String replaceStr = sb.toString().replace("'null'", "null");
			sb = sb.replace(0, sb.length(), replaceStr);
			List paramValue=new ArrayList();
			List paramType=new ArrayList();
			conn.executeUpdate(sb.toString(),paramValue,paramType);
		}
		return true;
	}

	
	/**
	 * 
	 * 删除原资金计划
	 * 
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean deleteFundFundCharge( String docId) throws Exception {

		Conn conn = new Conn();
		// 报价编号
		String sql = " delete from FUND_FUND_PLAN_TEMP where  doc_id='" + docId + "'";
		List paramValue=new ArrayList();
		List paramType=new ArrayList();
		paramValue.add(docId );
		paramType.add(Types.VARCHAR);
		conn.executeUpdate(sql,paramValue,paramType);
		return true;
	}

	/**
	 * 
	 * 资金计划保存到临时表中
	 * 
	 * @param cb
	 * @param fp
	 * @return  
	 */
	public boolean addFundFundCharge(String docId, List<FundPlanBean> fp,String markId,String markValue) throws Exception {
		//为了不改变整个测算的 代码，资金计划临时性的插入到一张中间表当中，方便拼现金流
		for(FundPlanBean fund : fp){
			Conn conn = new Conn();
			StringBuffer sb = new StringBuffer();
			sb.append(" INSERT INTO FUND_FUND_PLAN_TEMP").
			append("(ID,doc_id").
			append(",FEE_TYPE,").
			append("PLAN_DATE,PLAN_MONEY,PAY_OBJ,").
			append("SETTLE_METHOD,PAY_TYPE ,"+markId).
			append(") VALUES(").//sys_guid()  '" + UUIDUtil.getUUID() + "'
			append(" ?,?").
			append(",?").
			append(",?").
			append(",?").
			append(",?").
			append(",?").
			append(",?").
			append(",?").
			append(")");
			String replaceStr = sb.toString().replace("'null'", "null");
			sb = sb.replace(0, sb.length(), replaceStr);
			logger.info(sb.toString());
			List paramValue=new ArrayList();
			List paramType=new ArrayList();
			paramValue.add(UUIDUtil.getUUID());
			paramValue.add(docId);
			paramValue.add(fund.getFeeType());
			paramValue.add(fund.getPlanDate());
			paramValue.add(fund.getPlanMoney());
			paramValue.add(fund.getPayObj());
			paramValue.add(fund.getSettleMethod());
			paramValue.add(fund.getPayType());
			paramValue.add(markValue);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			paramType.add(Types.VARCHAR);
			conn.executeUpdate(sb.toString(),paramValue,paramType);
		}
		return true;
	}

}
