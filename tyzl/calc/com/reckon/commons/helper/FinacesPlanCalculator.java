package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.bean.CalculateCondition;

public class FinacesPlanCalculator {

	private static Logger logger = Logger.getLogger(FinacesPlanCalculator.class);
	
	/**
	 * 根据租金计划和商务条件获取财务IRR，和财务收益率不一样
	 * 
	 * @param rentPlan
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> BigDecimal getFinanceYearRate(CalculateCondition condition, List<T> rentPlan) throws ParseException {
		List<BigDecimal> inflowPour = new ArrayList<BigDecimal>();
		for (int i = condition.getGrace(); i < rentPlan.size(); i++) {//财务irr的计算不算宽限期的
			RentPlan rpi = rentPlan.get(i);
			inflowPour.add(rpi.getRent());
		}
		
		BigDecimal leaseAmt = new BigDecimal(-1).multiply(condition.getLeaseMoney());
		if(condition.getPeriodType() > 0){//期末的话把总本金加在数据列表的第一个，为了配合getIRR方法
			inflowPour.add(0, leaseAmt);
		} else {
			inflowPour.set(0, inflowPour.get(0).add(leaseAmt));//期初的话与第一期租金合并
		}
		BigDecimal irr = IRRCalculateUtil.getYearIRR(inflowPour, condition.getIssueNumber());
		return irr;
	}

	
	/**
	 * 根据商务条件和租金计划填充租金计划中的财务本金和财务利息
	 * 
	 * @param condition 商务条件
	 * @param rentPlan 租金计划数据列表
	 * @throws ParseException
	 */
	public static <T extends RentPlan> void processFinacesRentPlan(CalculateCondition condition, List<T> rentPlan) throws ParseException {
		processFinacesRentPlan(condition, rentPlan, 1);
	}
	
	
	/**
	 * 处理财务租金计划
	 * 基本逻辑：
	 * 根据新的租金计划重建从调息起始期开始的现金流，包含剩余期项的总本金
	 * 计算该现金流的IRR（期利率）
	 * 根据新的期利率计算每期的利息以及本金，本金余额
	 * @param condition
	 * @param rentPlan
	 * @param icb
	 */
	public static void processFinacesRentPlan(CalculateCondition condition, List<? extends RentPlan> rentPlan, int startList) {
		
		logger.info("处理财务租金计划开始...");
		int startIndex = startList - 1;
		int grace = condition.getGrace();
		logger.info("处理财务租金计划，起始期项：" + startList);
		logger.info("处理财务租金计划，宽限期：" + grace);
		
		for (int i = startIndex; i < grace; i++) {
			logger.info("处理财务租金计划，处理宽限期数据：" + i);
			rentPlan.get(i).setFinanceInterest(rentPlan.get(i).getBusinessInterest());
		}
		
		List<BigDecimal> rentRemainList = new ArrayList<BigDecimal>();
		for (int i = Math.max(startIndex, grace); i < rentPlan.size(); i++) {
			rentRemainList.add(rentPlan.get(i).getRent());
		}
		logger.info("处理财务租金计划，处理非宽限期数据租金计划：(" + rentRemainList.size() + ")" + rentRemainList);
		
		BigDecimal corpusRemain = rentPlan.get(Math.max(startIndex, grace)).getBusinessCorpusOverage();
		logger.info("处理财务租金计划，处理非宽限期数据，起始期项：(" + Math.max(startIndex, grace) + ")本金余额：" + corpusRemain);
		
		if(condition.getPeriodType() == 0){
			rentRemainList.set(0, rentRemainList.get(0).subtract(corpusRemain));
		} else {
			rentRemainList.add(0, BigDecimal.ZERO.subtract(corpusRemain));
		}
		logger.info("处理财务租金计划，需要修改的租金计划现金流：(" + rentRemainList.size() + ")" + rentRemainList);
		
		BigDecimal issueRate = IRRCalculateUtil.getIRR(rentRemainList);
		logger.info("处理财务租金计划，需要修改的租金计划现金流IRR：(" + issueRate.toString() + ")");
		
		for (int i = Math.max(startIndex, grace); i < rentPlan.size(); i++) {
			RentPlan rp = rentPlan.get(i);
			BigDecimal rent = rp.getRent();
			BigDecimal newFinacInterest = corpusRemain.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			
			if(i == grace && condition.getPeriodType() == 0){
				logger.info("处理财务租金计划：期初首期调息，利息为0，本金=租金。");
				newFinacInterest = BigDecimal.ZERO;
			}
			BigDecimal newFinacCorpus = rent.subtract(newFinacInterest);
			corpusRemain = corpusRemain.subtract(newFinacCorpus);
			
			if(i == (rentPlan.size() - 1)){
				corpusRemain = BigDecimal.ZERO;
				rent = rent.add(corpusRemain);
				newFinacCorpus = newFinacCorpus.add(corpusRemain);
			}
			
			rp.setFinanceInterest(newFinacInterest);
			rp.setFinanceCorpus(newFinacCorpus);
			rp.setFinanceCorpusOverage(corpusRemain);
			
			logger.info("处理财务租金计划，新租金列表:" + rp.getRentList() + "/" +  + rentPlan.size());
			logger.info("处理财务租金计划，新财务本金:" + newFinacCorpus);
			logger.info("处理财务租金计划，新财务利息:" + newFinacInterest);
			logger.info("处理财务租金计划，新财务本金余额:" + corpusRemain);
		}
	}
}
