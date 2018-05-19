package com.reckon.dao.impl;

import java.util.List;

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

public class IrrDAOImpl {
	
	private static Logger logger = Logger.getLogger(IrrDAOImpl.class);
	
	/**
	 * 
	 * 删除原资金计划
	 * 
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean deleteCashDetail( TabCalBean tb ,ConditionBean cb) throws Exception {

		Conn conn = new Conn();
		// 报价编号
		String sql = " delete from "+ tb.getContractCashTb() +" where  doc_id='" + cb.getDocId() + "'";
		conn.executeUpdate(sql);
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
	public boolean addCashDetail(ConditionBean cb, List<FundPlanBean> fp) throws Exception {
		//为了不改变整个测算的 代码，资金计划临时性的插入到一张中间表当中，方便拼现金流
		for(FundPlanBean fund : fp){
			Conn conn = new Conn();
			StringBuffer sb = new StringBuffer();
			sb.append(" INSERT INTO FUND_FUND_PLAN_TEMP").
			append("(ID,doc_id").
			append(",FEE_TYPE,").
			append("PLAN_DATE,PLAN_MONEY,PAY_OBJ,").
			append("SETTLE_METHOD,PAY_TYPE ").
			append(") VALUES(").//sys_guid()  '" + UUIDUtil.getUUID() + "'
			append(" '"+UUIDUtil.getUUID()+"','" + cb.getDocId()).
			append("','" + fund.getFeeType()).
			append("','" + fund.getPlanDate()).
			append("','" + fund.getPlanMoney()).
			append("','" + fund.getPayObj()).
			append("','" + fund.getSettleMethod()).
			append("','" + fund.getPayType()).
			append("')");
			String replaceStr = sb.toString().replace("'null'", "null");
			sb = sb.replace(0, sb.length(), replaceStr);
			logger.info(sb.toString());
			conn.executeUpdate(sb.toString());
		}
		return true;
	}


}
