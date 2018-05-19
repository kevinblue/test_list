package com.reckon.rentcalc.service.impl.pub;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.ConditionDAOImpl;
import com.reckon.util.DictTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-4
 * @desc  ( 交易结构现金流irr更新，交易结构数据进行添加等调用处理类)
 */
public class ConditionServiceImpl {

	/**
	 * 
	 *  (  //租金测算时先删除，后新增交易结构信息)
	 * 
	 * @param tcb
	 * @param cb
	 * @return
	 * @throws Exception
	 */
	public boolean addConditionByCal(TabCalBean tcb, ConditionBean cb) throws Exception {
		// 删除
		ConditionDAOImpl cd = new ConditionDAOImpl();
		cd.deleteCondition(tcb, cb);
		// 新增
		// 数所字典转化
		cb = DictTools.getPersiDict(cb);
		cd.addCondition(tcb, cb);
		// 为了后面的计算再转向
		cb = DictTools.getReversDict(cb);
		cb.setYearRate(cb.getYearRate());
		return true;
	}

}
