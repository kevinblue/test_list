package com.reckon.commons.helper;


import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.util.NumberUtils;


/**
 * <p>计算项目收益工具类。</p>
 * <pre>
 * 项目收益 = 利息收入-应交税金-其他支出 +其他收入-印花税-保险+期末购买价格
 * 印花税、应交税金 2个直接置为0 
 * </pre>
 * @author sea
 * <p>2014-4-17</p>
 */
public class CalculateProjIncomeTools {
	
	private static Logger logger = Logger.getLogger(CalculateProjIncomeTools.class);
	
	/**
	 * <p>计算项目收益。</p>
	 * @author sea
	 * @param cb 交易结构信息
	 * @param oldRentPlanContext 
	 * @return
	 */
	public static BigDecimal calculateProjIncome(ConditionBean cb, FundRentPlanBean oldRentPlanContext){
		BigDecimal result = BigDecimal.ZERO;
		List<String> interestBusinessList = oldRentPlanContext.getInterestBusinessList(); //业务利息列表
		BigDecimal countInterest = BigDecimal.ZERO;//利息收入
		//利息累加
		for (int i = 0; i < interestBusinessList.size(); i++) {
			String interestBusiness = NumberUtils.nullToZero( interestBusinessList.get(i) );
			countInterest = countInterest.add( new BigDecimal(interestBusiness) );
		}
		
		//交易结构取值
		//out
		BigDecimal otherExpenditure = new BigDecimal( NumberUtils.nullToZero( cb.getOtherExpenditure() ) ); // 其它支出
		BigDecimal insureMoney = new BigDecimal( NumberUtils.nullToZero( cb.getInsureMoney() ) ); // 保险费
		//in
		BigDecimal otherIncome = new BigDecimal( NumberUtils.nullToZero( cb.getOtherIncome() ) ); // 其它收入
		BigDecimal nominalPrice = new BigDecimal( NumberUtils.nullToZero( cb.getNominalPrice() ) ); // 期末购买权 【原本意思为：名义货价】
		BigDecimal handlingChargeMoney = new BigDecimal( NumberUtils.nullToZero( cb.getHandlingChargeMoney() ) );//+手续费
		//项目收益 = 利息收入-应交税金-印花税-其他支出 -保险+期末购买价格+其他收入+手续费
		//印花税、应交税金 2个直接置为0
		//-
		result = countInterest.subtract(otherExpenditure);
		result = result.subtract(insureMoney);
		//+
		result = result.add(otherIncome);
		result = result.add(nominalPrice);
		result = result.add(handlingChargeMoney);
		
		logger.debug("利息收入:"+countInterest);
		logger.debug("其它支出:"+otherExpenditure);
		logger.debug("保险费:"+insureMoney);
		logger.debug("其它收入:"+otherIncome);
		logger.debug("期末购买权 :"+nominalPrice);
		logger.debug("手续费 :"+handlingChargeMoney);
		
		logger.info("项目收益：" + result);
		return result;
	}
	
}
