package com.reckon.renttranrate.service.impl;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.TabCalBean;
import com.reckon.dao.impl.ConditionDAOImpl;


/**
 * @author MHY QQ:648020894
 */
public class ConditionTranServicesImpl {

	/**
	 * 
	 *  (  调息时用于计算现金流明细的交易结构bean的构建)
	 * 
	 * @param cbCal测算时的实体bean
	 * @param wheresql所要查询的表
	 * @return
	 * @throws Exception
	 */
	public ConditionBean getCondCashBean(ConditionBean cbCal, String wheresql, TabCalBean tcb) throws Exception {
		ConditionDAOImpl cdi = new ConditionDAOImpl();
		ConditionBean cb = cdi.getConditionBeanByContract(wheresql, tcb);
		
		// 由于他此时的当前操作人，操作时间，修改人，修改时间变化，故在此要重新设置
		cb.setDocId(cbCal.getDocId());
		cb.setCreator(cb.getCreator());
		cb.setModificator(cb.getCreator());
		cb.setModifyDate(cb.getModifyDate());

		return cb;
	}
}
