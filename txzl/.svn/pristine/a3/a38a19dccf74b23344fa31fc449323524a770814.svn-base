package com.reckon.service.impl;


import java.util.List;

import com.reckon.bean.TabCalBean;
import com.reckon.dao.Conn;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.beforeinterest.BeforeInterestCalProcess;

public class BeforeInterestDaoImpl {
	public boolean addBeforeInterestCalProcess(TabCalBean tcb,List<BeforeInterestCalProcess> list) throws Exception {
		String sql = "";
		Conn conn = new Conn();
		for (int i = 0; i < list.size(); i++) {

			sql += " INSERT INTO BEFORE_INTEREST_CALPROCESS (" ;
			sql += " id,";
			sql += tcb.getContOrProjCName()+",";
			sql += " DOC_ID,";
			sql += " QUOT_ID,";
			sql += " LIST,";
			sql += " FUND_PUT_DATE,";
			sql += " MONEY,";
			sql += " START_DATE,";
			sql += " END_DATE,";
			sql += " DAYS,";
			sql += " YEAR_RATE,";
			sql += " INTEREST";
			sql += " ) VALUES (";
			sql += " '" + UUIDUtil.getUUID() + "',";
			sql += " '" + tcb.getContOrProjCValue() + "',";
			sql += " '" + tcb.getDocId() + "',";
			sql += " '"+tcb.getQuot_id()+"',";
			sql += list.get(i).getList() + ",";
			sql +="'"+ list.get(i).getFundPutDate() + "',";
			sql += list.get(i).getMoney() + ",";
			sql +="'"+ list.get(i).getStartDate() + "',";
			sql +="'"+ list.get(i).getEndDate() + "',";
			sql += list.get(i).getDays() + ",";
			sql += list.get(i).getYearRate() + ",";
			sql += list.get(i).getInterest();
			sql += ") ";

			conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "|=====|table:" + tcb.getBeforeInterest() + "添加租前息计算过程语句...");
			sql = "";
		}

		return true;
	}
	public boolean deleteBeforeInterest(TabCalBean tcb) throws Exception {
		Conn conn = new Conn();
		String sql = " delete BEFORE_INTEREST_CALPROCESS  where " + tcb.getContOrProjCName() + "='" + tcb.getContOrProjCValue() + "' and doc_id='" + tcb.getDocId() +"'";
		conn.executeUpdate(sql, "流程类型:" + tcb.getCalType() + "删除租前息计算过程...");
		return true;
	}
}
