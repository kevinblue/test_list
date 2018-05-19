package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.commons.helper.Scale;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;
import com.reckon.util.DateUtils;
import com.reckon.util.tools.NumTools;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;


/**
 * @author MHY QQ:648020894
 */
public class PmtNextMonthServiceImpl implements TransRateService {
	static Logger logger = Logger.getLogger(PmtNextListServiceImpl.class);
	/**
	 * 
	 * ( 次月调息处理类)
	 * 
	 * @param cb
	 *            原商务条件
	 * @param oldRentPlanContext
	 *            调整计划信息
	 * @param icb
	 *            央行基准利率信息
	 * @return
	 * @throws Exception
	 */
	@Override
	public void processPmtTranRate(ConditionBean cb,FundRentPlanBean oldRentPlanContext, InterContBean icb, List<SpecialRuleBean> srb)throws Exception {
		logger.info("pmt 次月调息.....");
		int startList = icb.getStartList();

		// 处理利息增量
		//获取调整之后第一期的年利率
		BigDecimal newYearRate = new BigDecimal("0");
		//判断开始期次在哪个SpecialRuleBean中，再获取对应的年利率。
		for(int i=0;i<srb.size();i++){
			SpecialRuleBean sb=srb.get(i);
			if(sb.getStartList()<=startList&&sb.getEndList()>=startList){
				newYearRate=new BigDecimal(icb.getNewYearRateList().get(i));
			}
		}
		//利率调整值
		BigDecimal adjustYearRate = newYearRate.subtract(new BigDecimal(oldRentPlanContext.getYearRateList().get(startList-1)));
		//获取利率调整天数
		//调息日早于起租日，则startdate为起租日
		String startDate ;
		if(DateUtils.getDiffDays(icb.getAdjustDate(),cb.getStartDate())>0){
			startDate = cb.getStartDate();
		}else{
			startDate = icb.getAdjustDate();
		}
		String endDate = startList==1?cb.getLeaseAmtDate():oldRentPlanContext.getPlanDateList().get(startList-1);
		int days = DateUtils.getDiffDays(startDate, endDate);
		//
		//获取剩余租金
		BigDecimal corpusRemain = new BigDecimal(NumTools.getSumCorpusOverage(oldRentPlanContext.getCorpusBusinessList(), icb.getStartList()<=0?1:icb.getStartList()));//剩余本金//剩余本金初始化
		//利息调整值
		BigDecimal adjustInterest = new BigDecimal(days).multiply(adjustYearRate).multiply(corpusRemain).divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_EVEN);
		logger.info("处理第一期的利息增量=" + adjustInterest.toString());
		
//		// 当期利息刷新加上变化的利息
		String transFirstInterest = oldRentPlanContext.getInterestBusinessList().get(startList - 1);;
//		FundPlanDAOImpl icdi = new FundPlanDAOImpl();
//		String lastYearRate = icdi.findLastTranRateYearRate(cb.getProjId(), startList);
//		if(lastYearRate==null){//未调过息
//			//调整后当期利息=调整当期剩余本金×调息前年租息率÷360×执行旧利率天数+调整当期剩余本金×调息后年租息率÷360×执行新利率天数
//			//为了满足上面公式，该期未做过调息，调整前当期利息需按日利率重新计算得到。这样 原利息+第一期的利息增量就与上面公式一样
////			transFirstInterest = TransRateHelper.calFirsInterestByDayRate(cb, oldRentPlanContext, icb).toString();
//		}else{//调过息,之前调息时该期利息已按日利率计算得到，只要加利息增量就可以了。不按日利率分段计算利息是，由于调息第一期有2次以上调息时，处理相当麻烦。
//			transFirstInterest = oldRentPlanContext.getInterestBusinessList().get(startList - 1);
//		}
		//本金不变，租金=本金+利息
		transFirstInterest = new BigDecimal(transFirstInterest).add(adjustInterest).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP).toString();
		String transFirstCorpus = oldRentPlanContext.getCorpusBusinessList().get(startList - 1);
		String transFirstRent = new BigDecimal(transFirstCorpus).add(new BigDecimal(transFirstInterest)).toString();
		
		// 赋值给租金计划
		oldRentPlanContext.getRentList().set(startList - 1, transFirstRent);
		oldRentPlanContext.getInterestBusinessList().set(startList - 1, transFirstInterest);
		oldRentPlanContext.getYearRateList().set(startList - 1, newYearRate.toString());
		
		// 加一期调用次期的算法
		int newStartList = icb.getStartList() + 1;
		icb.setStartList(newStartList);
		processPmtTranRateNextList(cb, oldRentPlanContext, icb,srb);
		icb.setStartList(startList);// 还原开始日期

		logger.info("pmt 次月调息结束.....");
	}

	/**
	 * 调次期的方法调息
	 */
	private void processPmtTranRateNextList(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb, List<SpecialRuleBean> srb) throws Exception {
		PmtNextListServiceImpl tranRateService = new PmtNextListServiceImpl();
		tranRateService.processPmtTranRate(cb, oldRentPlanContext, icb, srb);
	}
}
