package com.reckon.calculation.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;


public class RentAdjustmentUtil {

	/**
	 * 等额本金
	 * @param rentPlan 租金计划列表
	 * @param calculateType 租金测算的模型
	 * @return
	 * @throws ParseException 
	 */
	public static List<RentPlanInfo> updateForSameCorpus(CalculationCondition condition, List<RentPlanInfo> rentPlan) throws ParseException {
		BigDecimal leaseAmt = condition.getLeaseAmt();// 融资额
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		
		// 计算固定本金后，剩余期的本金总和
		BigDecimal newAmtRemain = leaseAmt;
		int issueAdjust = 0;
		for (int i = 0; i < rentPlan.size(); i++) {
			if(rentPlan.get(i).getCorpusAdjust().longValue() > 0) {
				//已固定值，不算数
				newAmtRemain = newAmtRemain.subtract(rentPlan.get(i).getCorpusAdjust());
			} else if (i < condition.getGrace()) {
				//宽限期，不计算
			} else {
				//未调整本金的期数个数，为了下边的平均本金
				issueAdjust++;
			}
		}
		
		//新的每期的本金
		BigDecimal evenCorpus = newAmtRemain.divide(new BigDecimal(issueAdjust) , Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal leaseAmtRemain = leaseAmt;// 剩余本金
		for (int i = 0; i < rentPlan.size(); i++) {
			BigDecimal businessCorpus = evenCorpus;
			BigDecimal businessInterest = leaseAmtRemain.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			
			if(rentPlan.get(i).getCorpusAdjust().longValue() > 0) {//本金固定
				//本金调整的话，本金按调整的来，利息需要判断是不是期初支付，期初支付租金则不计利息
				businessCorpus = rentPlan.get(i).getCorpusAdjust();
			} else if (i < condition.getGrace()) {
				//宽限期本金为0，利息自然计算
				businessCorpus = new BigDecimal("0");
			} else {
				businessCorpus = evenCorpus;
			}
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			// 宽限期结束第一期，如果是期初支付租金，不计利息
			if (i == condition.getGrace() && condition.getPeriodType() == 1) {
				businessInterest = new BigDecimal("0");
			}
			
			rentPlan.get(i).setBusinessInterest(businessInterest);// 该期利息=剩余本金*期利率
			rentPlan.get(i).setBusinessCorpus(businessCorpus);// 该期本金
			rentPlan.get(i).setRent(businessInterest.add(businessCorpus));// 该期租金
			rentPlan.get(i).setBusinessRemain(leaseAmtRemain);// 该期计息本金
			leaseAmtRemain = leaseAmtRemain.subtract(businessCorpus);// 剩余本金重置
		}
		
		RentPlanInfo lastPlan = rentPlan.get(rentPlan.size() - 1);
		lastPlan.setBusinessCorpus(lastPlan.getBusinessCorpus().add(leaseAmtRemain));
		return rentPlan;
	}
}
