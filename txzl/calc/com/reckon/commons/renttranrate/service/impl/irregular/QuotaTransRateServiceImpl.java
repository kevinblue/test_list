package com.reckon.commons.renttranrate.service.impl.irregular;

import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;

/**
 * @author MHY QQ:648020894
 */
public class QuotaTransRateServiceImpl {
	
	private static Logger logger = Logger.getLogger(QuotaTransRateServiceImpl.class);
	
	/**
	 *  定额等比调息法
	 *  基本逻辑：
	 *  计算每期利息需要加的值
	 *  央行每上调0.01,利息上调X元
	 *  从调息起始期项开始给每期的利息加上上调一个利息值
	 *  期初首期调息的话不加利息加本金
	 * 
	 * @param cb
	 * @param icb
	 * @return
	 * @throws Exception
	 */
	public static void doTransRate(Condition cb, List<? extends RentPlan> rentPlanList, TransRateInfo icb) throws Exception {
		logger.info("定额等比调息法开始，首先处理业务租金计划");
		
		
		
		logger.info("定额等比调息法结束，下面处理财务租金计划");
	}
}
