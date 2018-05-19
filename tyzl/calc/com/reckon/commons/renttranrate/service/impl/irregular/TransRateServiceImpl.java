package com.reckon.commons.renttranrate.service.impl.irregular;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.commons.base.Condition;
import com.reckon.commons.base.RentPlan;
import com.reckon.commons.bean.TransRateInfo;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.commons.helper.RentCalculateUtil;
import com.reckon.commons.helper.bean.CalculateCondition;

public class TransRateServiceImpl {
	
	private static Logger logger = Logger.getLogger(TransRateServiceImpl.class);
	
	
	/**
	 * 直调，按剩余期次数，剩余本金数，新年利率。
	 * 直接生成新的租金计划，链接到之前的列表中。
	 * 
	 * @param cb
	 * @param rentPlan
	 * @param startList
	 * @param newYearRate
	 * @throws Exception
	 */
	public static void doTransRate(Condition condition, List<RentPlan> rentPlan, TransRateInfo icb) throws Exception {
		logger.info("pmt 次期调息.....");
		
		int startList = icb.getStartList();
		BigDecimal yearRate = icb.getNewYearRate();
		int startIndex = startList - 1;
		
		RentPlan pre = rentPlan.get(startIndex - 1);
		BigDecimal leaseMoney = startList == 1 ? condition.getLeaseMoney() : pre.getBusinessCorpusOverage();
		
		CalculateCondition cc1 = new CalculateCondition(condition);
		cc1.setYearRate(yearRate.divide(new BigDecimal(100)));
		cc1.setLeaseMoney(leaseMoney);
		cc1.setEndMoney(condition.getEquipEndValue());
		cc1.setIncomeTimes(rentPlan.size() - startIndex);
		cc1.setStartDate(rentPlan.get(startIndex).getPlanDate());
		cc1.setGrace(0);
		List<? extends RentPlan> newAddRentPlan = RentCalculateUtil.calculateForSameRent(cc1, rentPlan.get(0).getClass());
		
		for(int i = startIndex; i < rentPlan.size(); i++){
			RentPlan rp = rentPlan.get(i);
			RentPlan nrp = newAddRentPlan.get(i - startIndex);
			rp.setRent(nrp.getRent());
			rp.setBusinessCorpus(nrp.getBusinessCorpus());
			rp.setBusinessInterest(nrp.getBusinessInterest());
			rp.setBusinessCorpusOverage(nrp.getBusinessCorpusOverage());
			rp.setOverageRent(nrp.getRent());
			rp.setOverageCorpus(nrp.getBusinessCorpus());
			rp.setOverageInterest(nrp.getBusinessInterest());
			rp.setYearRate(yearRate);
		}
		
		CalculateCondition cc2 = new CalculateCondition(condition);
		cc2.setGrace(0);
		cc2.setIncomeTimes(rentPlan.size());
		List<? extends RentPlan> newRentPlanModel = RentCalculateUtil.getEmptyPlanModel(cc2, rentPlan.get(0).getClass());
		for (int i = 0; i < newRentPlanModel.size(); i++) {
			rentPlan.get(i).setRentList(newRentPlanModel.get(i).getRentList());
			rentPlan.get(i).setPlanDate(newRentPlanModel.get(i).getPlanDate());
			rentPlan.get(i).setStartDate(newRentPlanModel.get(i).getStartDate());
			rentPlan.get(i).setEndDate(newRentPlanModel.get(i).getEndDate());
		}
		RentCalculateHelper.fillOtherInfoOfRentPlan(condition, rentPlan);
	}
}
