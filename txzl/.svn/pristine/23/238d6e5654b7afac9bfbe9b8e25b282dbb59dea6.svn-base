package com.reckon.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.reckon.bean.CashConfigBean;
import com.reckon.dao.Conn;
import com.tenwa.leasing.utils.LeasingException;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 租金测算现金流处理信息)
 */
public class IrrConfigDAOImpl {

	/**
	 * 
	 *  (  //CashConfigBean
	 * 此bean实体只有日期字段值，列名，列名描述，流入还是流出，表名等文字性描述)
	 * 
	 * @param occu_type
	 * @return
	 * @throws Exception
	 */
	public List<CashConfigBean> getConfigInfo(String occu_type) throws Exception {

		Conn conn = new Conn();
		String sql = " SELECT id,fee_name,fee_name_des,fee_tbname,inOrOut,occu_date ";
		sql += " ,occu_type FROM fund_cash_config where occu_type like '%" + occu_type + "%'";

		List<CashConfigBean> ccfb_list = new ArrayList<CashConfigBean>();
		List<Map<String, String>> rs_list = conn.executeQuery(sql, "租金测算的现金流配置");
		for (Map<String, String> rs : rs_list) {
			CashConfigBean ccfb = new CashConfigBean();
			ccfb.setFee_name(rs.get("fee_name"));
			ccfb.setFee_name_des(rs.get("fee_name_des"));
			ccfb.setId(rs.get("id"));
			ccfb.setFee_tbname(rs.get("fee_tbname"));
			ccfb.setInOrOut(rs.get("inorout"));
			ccfb.setOccu_date(rs.get("occu_date"));
			ccfb.setOccu_type(rs.get("occu_type"));
			ccfb_list.add(ccfb);
		}
		// DaoUtil.closeRSOrConn(rs, conn);
		if (ccfb_list.size() == 0) {
			throw new LeasingException("没有在数据库中初始化租金测算的现金流配置!");
		}
		return ccfb_list;
	}

}
