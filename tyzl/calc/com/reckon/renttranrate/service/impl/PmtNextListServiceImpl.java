package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.Scale;
import com.reckon.commons.helper.RentCalculateHelper;
import com.reckon.rentcalc.service.impl.pub.RentPlanContrCalServiceImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;
import com.reckon.util.tools.NumTools;


/**
 * @author MHY QQ:648020894
 */
public class PmtNextListServiceImpl implements TransRateService {
	
	private static Logger logger = Logger.getLogger(PmtNextListServiceImpl.class);
	
	/**
	 * 
	 *  (  次期的调息处理)
	 * 
	 * @param cb
	 * @param icb
	 * @return
	 * @throws Exception
	 */
	public void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		if (cb.getSettleMethod().equals("even_rent")) {// 等额租金
			evenRentTranRate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("even_interest")) {//次期的均息法调息
			evenInterestTranRate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("even_corpus")) {//次期的等额本金调息方法
			evenCoupusTranRate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("special_regular")) {//次期的分段测算调息
			specialRegulartranrate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("irregular_rent")) {
			//irregularTranRate(cb, oldRentPlanContext, icb);
			logger.warn("不规则租金测算方式(" + cb.getSettleMethod() + ")!不能批量调息!");
		} else {
			logger.warn("未知的租金测算方式(" + cb.getSettleMethod() + ")!不能调息!");
		}
	}


	/**
	 * 
	 *  次期的等额租金调息方法
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public void evenRentTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		logger.info("pmt 次期调息.....");
		int startList = icb.getStartList();
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		String leaseAmt = cb.getCleanLeaseMoney();
		String equipEndValue = cb.getEquipEndValue();
		String leaseMoney = new BigDecimal(leaseAmt).subtract(new BigDecimal(equipEndValue)).toString(); //租赁本金
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		// 宽限期等比上调利息
		BigDecimal newIssueRate = condition.getIssueRate(new BigDecimal(icb.getNewYearRate()).divide(new BigDecimal(100), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN));
		
		int grace = cb.getGrace();
		for (int i = startList - 1; i < grace; i++) {
			String corpus = oldRentPlanContext.getCorpusBusinessList().get(i);
			String newInterest =  new BigDecimal(leaseAmt).multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
			String newRent = new BigDecimal(newInterest).add(new BigDecimal(corpus)).toString();
			
			oldRentPlanContext.getInterestBusinessList().set(i, newInterest);
			oldRentPlanContext.getInterestFinacList().set(i, newInterest);
			oldRentPlanContext.getRentList().set(i, newRent);
			oldRentPlanContext.getYearRateList().set(i, icb.getNewYearRate());
		}
		
		// 非宽限期，公式带入
		int startIndex = Math.max(startList - 1, grace);
		String corpusRemain = NumTools.getSumCorpusOverage(oldCorpusList, startIndex + 1);
		
		BigDecimal endValue = new BigDecimal(equipEndValue);
		//有期末余值时，getNewRentListForEvenRent传入的corpusRemain参数需加上期末余值再减去期末余值的现值
		if(endValue.compareTo(BigDecimal.ZERO)!=0){
			BigDecimal one = BigDecimal.ONE;
			BigDecimal daoRate = one.divide(one.add(newIssueRate), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
			//期末余值减去期末余值的现值
			endValue = endValue.subtract(endValue.multiply(daoRate.pow(oldRentList.size() - startIndex ))).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP);
			corpusRemain = new BigDecimal(corpusRemain).add(endValue).toString();
			logger.info("剩余的本金-期末余值的现值="+corpusRemain);
		}
		//根据等比上调的比例，获得新的租金
		List<String> newRentList = TransRateHelper.getNewRentListForEvenRent(oldRentList, startIndex, newIssueRate, corpusRemain);
		List<String> newInterestList = new ArrayList<String>(oldInterestList);
		List<String> newCorpusList = new ArrayList<String>(oldCorpusList);
		BigDecimal corpusRemainSum = TransRateHelper.getCorpusRemain(oldCorpusList, startIndex);
		BigDecimal corpusOverage = null;
		if(Math.max(startIndex, grace) == 0){
			corpusOverage = new BigDecimal(cb.getCleanLeaseMoney());
		}else{
			corpusOverage = new BigDecimal(oldRentPlanContext.getCorpusOverageBusinessList().get(Math.max(startIndex, grace) - 1));//剩余本金
		}
		for (int i = startIndex; i < newRentList.size(); i++) {
			BigDecimal rent = new BigDecimal(newRentList.get(i));
			BigDecimal newInterest = corpusOverage.multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if(i == grace && cb.getPeriodType() != null && cb.getPeriodType().contains("1") && i == 0){
				logger.info("调息后租金计划：期初首期调息，利息为0，本金=租金。");
				newInterest = BigDecimal.ZERO;
			}
			BigDecimal newCorpus = rent.subtract(newInterest);
			
			corpusRemainSum = corpusRemainSum.subtract(newCorpus);
			
			if(i == (newRentList.size() - 1)){
				newCorpus = newCorpus.add(corpusRemainSum);
				newInterest = rent.subtract(newCorpus);
				corpusRemainSum = BigDecimal.ZERO;
			}
			corpusOverage = corpusOverage.subtract(newCorpus);
			
			newInterestList.set(i, newInterest.toString());
			newCorpusList.set(i, newCorpus.toString());			
		}
		
		List<String> newCorpusOvergeList = TransRateHelper.getCorpusOvergeList(leaseAmt, newCorpusList);
		//设置新的年利率
		List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
		for (int i = startIndex; i < oldRentList.size(); i++) {
			newYearRateList.set(i, icb.getNewYearRate());
		}
		
		// 设置他的合同号
		oldRentPlanContext.setProjOrCont(cb.getProjId());
		oldRentPlanContext.setCreator(cb.getCreator());
		oldRentPlanContext.setYearRate(cb.getYearRate());
		//oldRentPlanContext.setQuotId(cb.getQuotId());
		//oldRentPlanContext.setOnHireId(cb.getOnhireId());
		
		//等额租金法，财务与业务本金和利息需要一样。
		oldRentPlanContext.setRentList(newRentList);
		oldRentPlanContext.setYearRateList(newYearRateList);
		
		oldRentPlanContext.setCorpusBusinessList(newCorpusList);
		oldRentPlanContext.setInterestBusinessList(newInterestList);
		oldRentPlanContext.setCorpusOverageBusinessList(newCorpusOvergeList);
		
		oldRentPlanContext.setCorpusFinacList(newCorpusList);
		oldRentPlanContext.setInterestFinacList(newInterestList);
		oldRentPlanContext.setCorpusOverageFinacList(newCorpusOvergeList);
	}
	/**
	 * 次期分段规则调息（等额本金 、等额租金混合调息）
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 * @throws Exception
	 */
	public void specialRegulartranrate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		logger.info("pmt 次期调息.....");
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		int startList = icb.getStartList();
		// 非宽限期，公式带入
		int grace = cb.getGrace();
		int startIndex = Math.max(startList - 1, grace);
		String corpusRemain = NumTools.getSumCorpusOverage(oldCorpusList, startIndex + 1);//剩余本金
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		List<SpecialRulesBean> srb=icb.getSrb();
		int remainLeaseTerm=0;
		for(int i=0;i<srb.size();i++){
			if(srb.get(i).getStartList()>=startList){
				remainLeaseTerm+=(srb.get(i).getEndList()-srb.get(i).getStartList()+1)*Integer.parseInt(srb.get(i).getRegular_months());
			}else if(startList>=srb.get(i).getStartList()&&startList<=srb.get(i).getEndList()){
				remainLeaseTerm+=(srb.get(i).getEndList()-startList+1)*Integer.parseInt(srb.get(i).getRegular_months());
			}
		}
		cb.setRemainLeaseTerm(remainLeaseTerm);//初始化剩余租赁期限
		cb.setRemainCorpus(new BigDecimal(corpusRemain));//初始化剩余本金
		cb.setYearRate(icb.getNewYearRate());
		FundRentPlanBean frpb=new FundRentPlanBean();
		for(int i=0;i<srb.size();i++){
			if(srb.get(i).getEndList()<startList){
				continue;
			}
			//已知本金比例
			if("regular_settlemethod.knowcorpusrate".equals(srb.get(i).getRegular_settlemethod())){
				RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
				rpcs.getKnowingCorpusPlanForSpecial(cb, frpb, srb.get(i),startList);
			}
			//等额本息
			else if("regular_settlemethod.evenrent".equals(srb.get(i).getRegular_settlemethod())){
				RentPlanContrCalServiceImpl rpcs = new RentPlanContrCalServiceImpl();
				rpcs.getEqualRentPlanForSpecial(cb, frpb, srb.get(i),startList);
			}
		}
		List<String> newRentList = frpb.getRentList();
		List<String> newInterestList = frpb.getInterestBusinessList();
		List<String> newCorpusList = frpb.getCorpusBusinessList();
		List<String> newYearRateList = frpb.getYearRateList();
		List<String> rentlist= oldRentList.subList(0, startIndex);
		rentlist.addAll(newRentList);
		List<String> corpuslist=oldCorpusList.subList(0, startIndex);
		corpuslist.addAll(newCorpusList);
		List<String> interestlist=oldInterestList.subList(0, startIndex);
		interestlist.addAll(newInterestList);
		List<String> yearratelist=oldYearRateList.subList(0, startIndex);
		yearratelist.addAll(newYearRateList);
		//最后一期本金 角分差处理
		BigDecimal corpusOverage = BigDecimal.ZERO;
		for (int i = 0; i < corpuslist.size()-1; i++) {
			BigDecimal coupus = new BigDecimal(corpuslist.get(i));
			corpusOverage = corpusOverage.add(coupus);
		}
		corpuslist.set(corpuslist.size()-1, new BigDecimal(cb.getCleanLeaseMoney()).subtract(corpusOverage).toString());		
		// 设置他的合同号
		oldRentPlanContext.setProjOrCont(cb.getProjId());
		oldRentPlanContext.setCreator(cb.getCreator());
		oldRentPlanContext.setYearRate(cb.getYearRate());
		
		//等额租金法，财务与业务本金和利息需要一样。
		oldRentPlanContext.setRentList(rentlist);
		oldRentPlanContext.setYearRateList(yearratelist);
		
		oldRentPlanContext.setCorpusBusinessList(corpuslist);
		oldRentPlanContext.setInterestBusinessList(interestlist);
		
		oldRentPlanContext.setCorpusFinacList(corpuslist);
		oldRentPlanContext.setInterestFinacList(interestlist);
	}

	/**
	 * 
	 *  次期的均息法调息
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public void evenInterestTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {

		int startListIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		BigDecimal oldIssueRate = condition.getIssueRate(new BigDecimal(icb.getOldYearRate()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_EVEN));
		BigDecimal newIssueRate = condition.getIssueRate(new BigDecimal(icb.getNewYearRate()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_EVEN));
		
		List<String> newInterestList = TransRateHelper.getNewInterestListForEvenInterest(oldInterestList, startListIndex + 1, oldIssueRate, newIssueRate);
		List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
		List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
		
		for (int i = startListIndex; i < oldRentList.size(); i++) {
			newYearRateList.set(i, icb.getNewYearRate());
		}

		// 设置他的合同号
		oldRentPlanContext.setProjOrCont(cb.getProjId());
		oldRentPlanContext.setCreator(cb.getCreator());
		oldRentPlanContext.setYearRate(cb.getYearRate());
		//oldRentPlanContext.setQuotId(cb.getQuotId());
		//oldRentPlanContext.setOnHireId(cb.getOnhireId());
		
		//均息调息不影响本金和每期本金余额，只影响每期的业务利息和财务利息
		oldRentPlanContext.setRentList(newRentList);
		oldRentPlanContext.setInterestBusinessList(newInterestList);
		oldRentPlanContext.setYearRateList(newYearRateList);
	}
	
	
	/**
	 * 次期的等额本金调息方法
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public void evenCoupusTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {

		int startListIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		BigDecimal oldIssueRate = condition.getIssueRate(new BigDecimal(icb.getOldYearRate()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_EVEN));
		BigDecimal newIssueRate = condition.getIssueRate(new BigDecimal(icb.getNewYearRate()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_EVEN));
		
		List<String> newInterestList = TransRateHelper.getNewInterestListForEvenCorpus(oldInterestList, startListIndex + 1, oldIssueRate, newIssueRate);
		List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
		List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
		
		for (int i = startListIndex; i < oldRentList.size(); i++) {
			newYearRateList.set(i, icb.getNewYearRate());
		}

		// 设置他的合同号
		oldRentPlanContext.setProjOrCont(cb.getProjId());
		oldRentPlanContext.setCreator(cb.getCreator());
		oldRentPlanContext.setYearRate(cb.getYearRate());
		//oldRentPlanContext.setQuotId(cb.getQuotId());
		//oldRentPlanContext.setOnHireId(cb.getOnhireId());
		
		//等本调息不影响本金和每期本金余额，只影响每期的业务利息和财务利息
		oldRentPlanContext.setRentList(newRentList);
		oldRentPlanContext.setInterestBusinessList(newInterestList);
		oldRentPlanContext.setInterestFinacList(newInterestList);
		oldRentPlanContext.setYearRateList(newYearRateList);
	}
	

	public void irregularTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		evenRentTranRate(cb, oldRentPlanContext, icb);
	}
}
