package com.reckon.rentcalc.service.impl.evencorpus;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.TabCalBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;
import com.reckon.dao.impl.RentPlanContrCalDAOImpl;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.rentcalc.service.impl.pub.RentalServiceImpl;


/**
 * 
 * @author SCLICX
 * @version 1.0
 * @copyright (C) Tenwa 2011
 * @date Nov 29, 2011
 * @desc 均息法的租金计划变更实现类
 */
public class EvenCorpusPlanContrCalServiceImpl extends RentPlanContrCalServiceImpl {
	static Logger logger = Logger.getLogger(EvenCorpusPlanContrCalServiceImpl.class);

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public FundRentPlanBean rentPlanCalByConditionAndTab(ConditionBean cb, TabCalBean tcb, int startList, FundRentPlanBean frpb) throws Exception {
		int fromIssue = startList;// 调整开始期

		cb.setCleanLeaseMoney(cb.getCleanLeaseMoney());// 原融资额
		CalculationCondition condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);

		BigDecimal newLeaseAmt = new BigDecimal(cb.getCleanLeaseMoney());
		for (int i = 0; i < fromIssue; i++) {
			// 减去调整前的本金，得到新测算开始的本金
			newLeaseAmt = newLeaseAmt.subtract(new BigDecimal(frpb.getCorpusBusinessList().get(i).toString()));
		}
		
		Date firstPlanDate = sdf.parse(frpb.getPlanDateList().get(fromIssue-1).toString());// 将调整开始那一期的日期作为第一期租金支付日
		CalculationCondition conditionNew = new CalculationConditionImpl();
		conditionNew.copyConditionBeanValues(cb);
		conditionNew.setLeaseAmt(newLeaseAmt);// 新的剩余测算本金
		conditionNew.setStartDate(condition.getStartDate());// 起租日期
		conditionNew.setFirstPlanDate(firstPlanDate);// 第一期租金支付日

		if (fromIssue > condition.getGrace()) {
			// 宽限期后边开始调的新测算就跳过宽限期
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if (fromIssue > (condition.getGrace() + 1) && conditionNew.getPeriodType() == 1) {
				// 从宽限期后边第1期后的开始调整切是期初付款的，新测算需要计算利息，设置成期末就行了
				conditionNew.setPeriodType(1);
			}
			conditionNew.setGrace(0);
		} else {
			// 设置剩余的宽限期从
			// 例如从第3期开始调整，宽限期是3期，那么新测算需要1个宽限期
			conditionNew.setGrace(fromIssue - condition.getGrace() + 1);
		}
		List<RentPlanInfo> newRentPlan = RentCalculateUtil.calculateForSameCorpus(conditionNew,null);
		rentPlanEvenCorpusCal(newRentPlan, frpb, conditionNew);
		
		RentPlanContrCalDAOImpl rpcd = new RentPlanContrCalDAOImpl();
		rpcd.deleteRentPlan(tcb.getRentPlan_tb(), tcb, startList);
		rpcd.addRentPlan(tcb.getRentPlan_tb(), frpb, tcb, startList);
		return frpb;
	}

	/**
	 * 
	 *  (  均息法下的租金测算,计算出租本息)
	 * 
	 * @param cb
	 *            交易结构bean
	 * @param frpb
	 *            租金计划bean
	 * @throws Exception
	 */
	public void rentPlanEvenCorpusCal(List<RentPlanInfo> newPlanInfo, FundRentPlanBean frpb, CalculationCondition condition) throws Exception {

		List<String> rentList = new ArrayList<String>();
		List<String> corpus = new ArrayList<String>();
		List<String> interest = new ArrayList<String>();
		List<String> yearRate = new ArrayList<String>();
		List<String> planDate = new ArrayList<String>();
		List<String> corpusOverageList = new ArrayList<String>();

		for (RentPlanInfo rpi : newPlanInfo) {
			rentList.add(rpi.getRent().toString());
			corpus.add(rpi.getBusinessCorpus().toString());
			interest.add(rpi.getBusinessInterest().toString());
			yearRate.add(condition.getYearRate().multiply(new BigDecimal(100)).toString());
			corpusOverageList.add(rpi.getBusinessRemain().toString());
			planDate.add(sdf.format(rpi.getEndDate()));
		}
		List<String> rentRemainList = new RentalServiceImpl().getRentRemainList(rentList);

		frpb.setColumn_1(corpus);
		frpb.setColumn_2(interest);
		frpb.setCorpusBusinessList(corpus);
		frpb.setInterestBusinessList(interest);
		frpb.setRentList(rentList);
		frpb.setYearRateList(yearRate);
		frpb.setPlanDateList(planDate);
		frpb.setCorpusOverageBusinessList(corpusOverageList);
		frpb.setAllRemainRentList(rentRemainList);
	}
}
