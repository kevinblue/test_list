package com.reckon.irr.web;

import org.apache.log4j.Logger;

import com.reckon.irr.service.impl.ActuIrrServiceImpl;
import com.reckon.util.tools.NumTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-6-9
 * @desc  ( 实际irr测算)
 */
public class ActuIrrDo {
	Logger logger = Logger.getLogger(ActuIrrDo.class);

	/**
	 * 
	 *  (  根据合同号算出他的实际irr)
	 * 
	 * @param contract_id
	 * @return
	 */
	public String calActuIrrByContractId(String contract_id) {
		String irr = "0";
		logger.info("进入实际irr测算方法..");
		try {
			ActuIrrServiceImpl asi = new ActuIrrServiceImpl();
			irr = asi.calActuIrrByContractId(contract_id);
			irr = NumTools.formatNumberDoubleScale(irr, 8);
			irr = String.valueOf(Double.parseDouble(irr) * 100);
		} catch (Exception e) {
			// : handle exception
			irr = "0";
			logger.info("实际irr测算出错：" + e.getMessage());
		}
		return irr;
	}

}
