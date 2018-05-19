package com.reckon.renttranrate.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.Scale;
import com.reckon.entity.contract.reckon.cash.CashHelp;
import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;
import com.reckon.util.DateUtils;
import com.reckon.util.IrrTools;
import com.reckon.util.RateTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;


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
//	public void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<ContractFundFundPlan> fundFundPlanList,List<CashHelp> cashHelpList) throws Exception {
//		if (cb.getSettleMethod().equals("even_rent")||cb.getSettleMethod().equals("excel_import")) {// 等额租金
//			evenRentTranRate(cb, oldRentPlanContext, icb,fundFundPlanList,cashHelpList);
//		} else if (cb.getSettleMethod().equals("even_interest")) {//次期的均息法调息
//			evenInterestTranRate(cb, oldRentPlanContext, icb,fundFundPlanList,cashHelpList);
//		} else if (cb.getSettleMethod().equals("even_corpus")) {//次期的等额本金调息方法
//			evenCoupusTranRate(cb, oldRentPlanContext, icb,fundFundPlanList,cashHelpList);
//		} else if (cb.getSettleMethod().equals("irregular_rent")) {
//			//irregularTranRate(cb, oldRentPlanContext, icb);
//			logger.warn("不规则租金测算方式(" + cb.getSettleMethod() + ")!不能批量调息!");
//		} else {
//			logger.warn("未知的租金测算方式(" + cb.getSettleMethod() + ")!不能调息!");
//		}
//	}


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
	public void evenRentTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<ContractFundFundPlan> fundFundPlanList,List<CashHelp> cashHelpList) throws Exception {
		logger.info("pmt 次期调息.....");
		int startList = icb.getStartList();
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		String leaseAmt = cb.getCleanLeaseMoney();
		String equipEndValue = cb.getEquipEndValue();
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		List<String> oldPlanDateList = oldRentPlanContext.getPlanDateList();
		// 非宽限期，公式带入
		int grace = cb.getGrace();
		int startIndex = Math.max(startList - 1, grace);
		String corpusRemain = NumTools.getSumCorpusOverage(oldCorpusList, startIndex + 1);
		//modify by liaobo 2016-07-23
		//根据新的irr 和 剩余本金 倒推租金  
		List<String> newRentList = TransRateHelper.getNewRentListForEvenRent(oldPlanDateList,oldRentList,fundFundPlanList, startIndex, new BigDecimal(icb.getNewIrr()).divide(new BigDecimal(1200),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP) , corpusRemain,cb,cashHelpList);
	/*	List<String> cashFlow = new ArrayList<String>();
		if("0".equals(cb.getPeriodType())){//期末
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).toString());
			cashFlow.addAll(newRentList);
		}else{//期初
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).add(new BigDecimal(newRentList.get(0))).toString());
			cashFlow.addAll(1, newRentList);
		}*/
		
		//根据新的现金流 倒推年利率
		List<String> newRentList2=new ArrayList<String>();

		for(int i=startIndex;i<newRentList.size();i++){
			newRentList2.add(newRentList.get(i));
		}
		List<String> cashFlow = new ArrayList<String>();
		if("0".equals(cb.getPeriodType())){//期末
			cashFlow.add(new BigDecimal(corpusRemain).multiply(new BigDecimal(-1)).toString());
			cashFlow.addAll(newRentList2);
		}else{//期初
			cashFlow.add(new BigDecimal(corpusRemain).multiply(new BigDecimal(-1)).add(new BigDecimal(newRentList.get(0))).toString());
			cashFlow.addAll(1, newRentList2);
		}
		
		String newYearRate=IrrTools.getIRRCalYearRate(cashFlow, String.valueOf(12 / Integer.parseInt(cb.getIncomeNumberYear())), "12");
		icb.setNewYearRate(newYearRate);
		// 期利率
		BigDecimal newIssueRate = new BigDecimal(icb.getNewYearRate()).divide(new BigDecimal(1200),20,BigDecimal.ROUND_HALF_UP);
		
		for (int i = startList - 1; i < grace; i++) {
			String corpus = oldRentPlanContext.getCorpusBusinessList().get(i);
			String newInterest =  new BigDecimal(leaseAmt).multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
			String newRent = new BigDecimal(newInterest).add(new BigDecimal(corpus)).toString();
			
			oldRentPlanContext.getInterestBusinessList().set(i, newInterest);
			oldRentPlanContext.getInterestFinacList().set(i, newInterest);
			oldRentPlanContext.getRentList().set(i, newRent);
			oldRentPlanContext.getYearRateList().set(i, icb.getNewYearRate());
		}
		
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
		
		
		List<String> newInterestList = new ArrayList<String>(oldInterestList);
		List<String> newCorpusList = new ArrayList<String>(oldCorpusList);
		BigDecimal corpusOverage = null;
		if(Math.max(startIndex, grace) == 0){
			corpusOverage = new BigDecimal(cb.getCleanLeaseMoney());
		}else{
			corpusOverage = new BigDecimal(oldRentPlanContext.getCorpusOverageBusinessList().get(Math.max(startIndex, grace) - 1));//剩余本金
		}
		for (int i = startIndex; i < newRentList.size(); i++) {
			BigDecimal rent = new BigDecimal(newRentList.get(i));
			BigDecimal inte = BigDecimal.ZERO;
			BigDecimal corpus = BigDecimal.ZERO;
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if(i == grace && cb.getPeriodType() != null && cb.getPeriodType().contains("1") && i == 0){
				logger.info("调息后租金计划：期初首期调息，利息为0，本金=租金。");
				inte = BigDecimal.ZERO;
			}else{
				for(int j=0;j<12/Integer.parseInt(cb.getIncomeNumberYear());j++){
					
					inte = corpusOverage.multiply(newIssueRate).add(inte).setScale(2, BigDecimal.ROUND_HALF_UP);
					if(j==12/Integer.parseInt(cb.getIncomeNumberYear())-1){
						corpus = new BigDecimal(newRentList.get(i).toString()).subtract(corpusOverage.multiply(newIssueRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
					}else{
						corpus=corpusOverage.multiply(newIssueRate).multiply(new BigDecimal(-1)).setScale(2, BigDecimal.ROUND_HALF_UP);
					}
					
					corpusOverage = corpusOverage.subtract(corpus);
				}
			}
			BigDecimal newCorpus = rent.subtract(inte);
			
			
			if(i == (newRentList.size() - 1)){
				newCorpus = newCorpus.add(corpusOverage);
				inte = rent.subtract(newCorpus);
				corpusOverage = BigDecimal.ZERO;
			}
			
			newInterestList.set(i, inte.toString());
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
	 * 
	 *  次期的均息法调息
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public void evenInterestTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<ContractFundFundPlan> fundFundPlanList,List<CashHelp> cashHelpList) throws Exception {

		int startListIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		
		List<String> newInterestList = TransRateHelper.getNewInterestListForEvenInterest(oldRentPlanContext.getPlanDateList(),oldInterestList, oldCorpusList,fundFundPlanList,startListIndex + 1,new BigDecimal(icb.getNewIrr()).divide(new BigDecimal(1200),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP),cashHelpList);
		List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
		//根据新的现金流 倒推年利率
		List<String> cashFlow = new ArrayList<String>();
		if("0".equals(cb.getPeriodType())){//期末
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).toString());
			cashFlow.addAll(newRentList);
		}else{//期初
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).add(new BigDecimal(newRentList.get(0))).toString());
			cashFlow.addAll(1, newRentList);
		}
		
		String newYearRate=IrrTools.getIRRCalYearRate(cashFlow, String.valueOf(12 / Integer.parseInt(cb.getIncomeNumberYear())), "12");
		
		icb.setNewYearRate(newYearRate);//设置新的年利率
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
	public void evenCoupusTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<ContractFundFundPlan> fundFundPlanList,List<CashHelp> cashHelpList) throws Exception {

		int startListIndex = icb.getStartList() - 1;// 调息起始期在list中的位置
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		List<String> newInterestList = TransRateHelper.getNewInterestListForEvenInterest(oldRentPlanContext.getPlanDateList(),oldInterestList, oldCorpusList,fundFundPlanList,startListIndex + 1,new BigDecimal(icb.getNewIrr()).divide(new BigDecimal(1200),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP),cashHelpList);
		List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
		//根据新的现金流 倒推年利率
		List<String> cashFlow = new ArrayList<String>();
		if("0".equals(cb.getPeriodType())){//期末
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).toString());
			cashFlow.addAll(newRentList);
		}else{//期初
			cashFlow.add(new BigDecimal(cb.getCleanLeaseMoney()).multiply(new BigDecimal(-1)).add(new BigDecimal(newRentList.get(0))).toString());
			cashFlow.addAll(1, newRentList);
		}
		
		String newYearRate=IrrTools.getIRRCalYearRate(cashFlow, String.valueOf(12 / Integer.parseInt(cb.getIncomeNumberYear())), "12");
		icb.setNewYearRate(newYearRate);//设置新的年利率
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
	

	public void irregularTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,List<ContractFundFundPlan> fundFundPlanList,List oldcashDetailList) throws Exception {
		evenRentTranRate(cb, oldRentPlanContext, icb,fundFundPlanList,oldcashDetailList);
	}

	

	/**
	 * (次期的调息处理)
	 * 天信只有special_regular测算方式
	 * 不规则的调息 
	 */
	@Override
	public void processPmtTranRate(ConditionBean cb,FundRentPlanBean oldRentPlanContext, InterContBean icb, List<SpecialRuleBean> srb)throws Exception {
		if (cb.getSettleMethod().equals("even_rent")) {// 次期的等额租金
//			evenRentTranRate(cb, oldRentPlanContext, icb,srb);
		} else if (cb.getSettleMethod().equals("even_interest")) {//次期的均息法调息
//			evenInterestTranRate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("even_corpus")) {//次期的等额本金调息方法
//			evenCoupusTranRate(cb, oldRentPlanContext, icb);
		} else if (cb.getSettleMethod().equals("excel_import")) {
			//irregularTranRate(cb, oldRentPlanContext, icb);
			logger.warn("不规则租金测算方式(" + cb.getSettleMethod() + ")!不能批量调息!");
		} else if(cb.getSettleMethod().equals("special_regular")){//次期的分段测算调息
			specialRegularTranRate(cb, oldRentPlanContext, icb,srb);
		}else {
			logger.warn("未知的租金测算方式(" + cb.getSettleMethod() + ")!不能调息!");
		}
	}

	private void specialRegularTranRate(ConditionBean cb,FundRentPlanBean oldRentPlanContext, InterContBean icb,List<SpecialRuleBean> srb) throws Exception{
		cb.setRemainLeaseTerm(cb.getLeaseTerm());
//		BigDecimal corpusRemain = new BigDecimal(NumTools.getRemainCorpus(cb.getcleanleaseMoney(),oldRentPlanContext.getCorpusBusinessList(), icb.getStartList()<=0?1:icb.getStartList()));//剩余本金//剩余本金初始化
		BigDecimal corpusRemain = new BigDecimal(NumTools.getSumCorpusOverage(oldRentPlanContext.getCorpusBusinessList(), icb.getStartList()<=0?1:icb.getStartList()));//剩余本金//剩余本金初始化
		cb.setRemainCorpus(corpusRemain);
		for(int i=0;i<srb.size();i++){
			if(srb.get(i).getEndList()<icb.getStartList()){
				//重置剩余租赁期限
				if(srb.get(i).getEndList()<=icb.getStartList()){
					cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(srb.get(i).getEndList()-srb.get(i).getStartList()+1)*Integer.parseInt(srb.get(i).getRegular_months()));
				}
				continue;
			}
			//指定租金
			if("special_regular.knowingrent".equals(srb.get(i).getRegular_settlemethod())){
				namedRentTranRate(cb, oldRentPlanContext, icb,srb.get(i),i);
			}
			//指定本金
			else if("special_regular.knowingcorpus".equals(srb.get(i).getRegular_settlemethod())){
				namedCorpusTranRate(cb, oldRentPlanContext, icb,srb.get(i),i);
			}
			//等额本金
			else if("special_regular.evencorpus".equals(srb.get(i).getRegular_settlemethod())){
				evenCoupusTranRateForSpecial(cb, oldRentPlanContext, icb,srb.get(i),i);
			}
			//等额租金
			else if("special_regular.evenrent".equals(srb.get(i).getRegular_settlemethod())){
				evenRentTranRateForSpecial(cb, oldRentPlanContext, icb, srb.get(i),i);
			}
		}
	}

	/**
	 * 分段测算 等额租金 调息   租金*比例系数 调整利息 本金=租金-利息
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 * @param rbicblist
	 * @param srb
	 * @throws Exception
	 */
	public void evenRentTranRateForSpecial(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,SpecialRuleBean srb,int index) throws Exception {
		logger.info("pmt 次期调息.....");
		int startList = icb.getStartList()<=0?1:icb.getStartList();
		if(startList<=srb.getEndList()){
			if(startList<srb.getStartList()){
				startList=srb.getStartList();
			}
			String leaseAmt = cb.getCleanLeaseMoney();
			
			// 原始租金计划信息
			List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
			List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
			List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
			List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
			
			//期利率
			BigDecimal newIssueRate = new BigDecimal(icb.getNewYearRateList().get(index)).multiply(new BigDecimal(cb.getRateType())).divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_EVEN);
			newIssueRate=newIssueRate.divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(srb.getRegular_months()));
			
			int startIndex = startList - 1;
			BigDecimal corpusRemain = cb.getRemainCorpus();//剩余本金
			
			//根据等比上调的比例，获得新的租金
			List<String> newRentList = TransRateHelper.getNewRentListForEvenRentForSpecial(cb,  oldRentList, startIndex, corpusRemain.toString(), srb,newIssueRate.toString());;
			List<String> newInterestList = new ArrayList<String>(oldInterestList);
			List<String> newCorpusList = new ArrayList<String>(oldCorpusList);
			//设置新的年利率
			List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
			
			for (int i = startIndex<srb.getStartList()-1?srb.getStartList()-1:startIndex; i < srb.getEndList(); i++) {
				BigDecimal rent = new BigDecimal(newRentList.get(i));
				BigDecimal newInterest = corpusRemain.multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				//天信只有第一笔租金支付日
//				if(i==0){
//					int day=DateUtils.days360(cb.getTheoryFirstPlanDate(), cb.getFirstPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}else if(i==1){
//					int day=DateUtils.days360(cb.getTheorySecondPlanDate(), cb.getSecondPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}
				// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
				if(cb.getPeriodType() != null && cb.getPeriodType().contains("1") && i == 0){
					logger.info("调息后租金计划：期初首期调息，利息为0，本金=租金。");
					newInterest = BigDecimal.ZERO;
				}
				BigDecimal newCorpus = rent.subtract(newInterest);
				
				corpusRemain = corpusRemain.subtract(newCorpus);
				
				if(i == (newRentList.size() - 1)){
					newCorpus = newCorpus.add(corpusRemain);
					newInterest = rent.subtract(newCorpus);
					corpusRemain = BigDecimal.ZERO;
				}
				
				newInterestList.set(i, newInterest.toString());
				newCorpusList.set(i, newCorpus.toString());		
				newYearRateList.set(i, icb.getNewYearRateList().get(index));
			}
//			
			List<String> newCorpusOvergeList = TransRateHelper.getCorpusOvergeList(leaseAmt, newCorpusList);
			cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(srb.getEndList()-srb.getStartList()+1)*Integer.parseInt(srb.getRegular_months()));
			cb.setRemainCorpus(corpusRemain);
//			// 设置他的合同号
			oldRentPlanContext.setProjOrCont(cb.getProjId());
			oldRentPlanContext.setCreator(cb.getCreator());
			oldRentPlanContext.setYearRate(cb.getYearRate());
			//oldRentPlanContext.setQuotId(cb.getQuotId());
			//oldRentPlanContext.setOnHireId(cb.getOnhireId());
			
//			//等额租金法，财务与业务本金和利息需要一样。
			oldRentPlanContext.setRentList(newRentList);
			oldRentPlanContext.setYearRateList(newYearRateList);
			
			oldRentPlanContext.setCorpusBusinessList(newCorpusList);
			oldRentPlanContext.setCorpusFinacList(newCorpusList);
			oldRentPlanContext.setInterestBusinessList(newInterestList);
			oldRentPlanContext.setInterestFinacList(newInterestList);
			oldRentPlanContext.setCorpusOverageBusinessList(newCorpusOvergeList);
			oldRentPlanContext.setCorpusOverageFinacList(newCorpusOvergeList);
//			
		}
	}

	/**
	 * 特殊规则 等额本金调息   本金按 剩余本金/剩余期数 重新计算  调整利息  租金=本金+利息 
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 * @param rbicblist
	 * @param srb
	 * @throws Exception
	 */
	public void evenCoupusTranRateForSpecial(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,SpecialRuleBean srb,int index) throws Exception {
		
		int startListIndex = icb.getStartList()<=0?1:icb.getStartList() - 1;// 调息起始期在list中的位置
		if(startListIndex+1>icb.getStartList()){
			startListIndex=icb.getStartList()-1;
		}
		
		// 原始租金计划信息
		List<String> oldRentList = oldRentPlanContext.getRentList();// 租金列表
		List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();// 本金列表
		List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();// 利息列表
		List<String> oldYearRateList = oldRentPlanContext.getYearRateList();// 年利率列表
		
		String leaseAmt = cb.getCleanLeaseMoney();
		//期利率
		BigDecimal newIssueRate = new BigDecimal(icb.getNewYearRateList().get(index)).multiply(new BigDecimal(cb.getRateType())).divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_EVEN);
		newIssueRate=newIssueRate.divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(srb.getRegular_months()));
		//剩余本金
		BigDecimal corpusRemain = cb.getRemainCorpus();
		//每期间隔月份
		int regular_months=Integer.parseInt(srb.getRegular_months());
		List<String> newInterestList = new ArrayList<String>(oldInterestList); 
		List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
		
		//剩余租赁期限(计算每期本金要用)
		int remainLeaseTerm=cb.getRemainLeaseTerm();
		if(startListIndex+1>=srb.getStartList()&&startListIndex+1<=srb.getEndList()){
			remainLeaseTerm=remainLeaseTerm-(startListIndex+1-srb.getStartList())*Integer.parseInt(srb.getRegular_months());
		}
		BigDecimal newCorpus = corpusRemain.divide(new BigDecimal(remainLeaseTerm/regular_months),Scale.CORPUS_SCALE,BigDecimal.ROUND_HALF_EVEN);
		for (int i = startListIndex<srb.getStartList()-1?srb.getStartList()-1:startListIndex; i < srb.getEndList(); i++) {
			BigDecimal newInterest = corpusRemain.multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//			if(i==0){
//				int day=DateUtils.days360(cb.getTheoryFirstPlanDate(), cb.getFirstPlanDate());
//				BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//				BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//				newInterest=newInterest.add(adjustInterest);
//			}else if(i==1){
//				int day=DateUtils.days360(cb.getTheorySecondPlanDate(), cb.getSecondPlanDate());
//				BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//				BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//				newInterest=newInterest.add(adjustInterest);
//			}
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if(cb.getPeriodType() != null && cb.getPeriodType().contains("1") && i == 0){
				logger.info("调息后租金计划：期初首期调息，利息为0，本金=租金。");
				newInterest = BigDecimal.ZERO;
			}
			
			corpusRemain = corpusRemain.subtract(newCorpus);
			
			if(i == (cb.getIncomeNumber() - 1)){
				newCorpus = newCorpus.add(corpusRemain);
				oldRentList.set(i, newCorpus.add(newInterest).toString());
				corpusRemain = BigDecimal.ZERO;
			}
			
			newInterestList.set(i, newInterest.toString());
			oldCorpusList.set(i, newCorpus.toString());
			newYearRateList.set(i, icb.getNewYearRateList().get(index));
		}
		cb.setRemainCorpus(corpusRemain);
		cb.setRemainLeaseTerm(remainLeaseTerm-(srb.getEndList()-srb.getStartList()+1)*Integer.parseInt(srb.getRegular_months()));
		List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
		//这里的oldCorpusList已经是newCorpusList
		List<String> newCorpusOvergeList = TransRateHelper.getCorpusOvergeList(leaseAmt, oldCorpusList);
		
		// 设置他的合同号
		oldRentPlanContext.setProjOrCont(cb.getProjId());
		oldRentPlanContext.setCreator(cb.getCreator());
		oldRentPlanContext.setYearRate(cb.getYearRate());
		oldRentPlanContext.setYearRateList(newYearRateList);
		//oldRentPlanContext.setQuotId(cb.getQuotId());
		//oldRentPlanContext.setOnHireId(cb.getOnhireId());
		
		//等本调息不影响本金和每期本金余额，只影响每期的业务利息和财务利息
		oldRentPlanContext.setRentList(newRentList);
		
		oldRentPlanContext.setInterestBusinessList(newInterestList);
		oldRentPlanContext.setInterestFinacList(newInterestList);
		oldRentPlanContext.setCorpusBusinessList(oldCorpusList);
		oldRentPlanContext.setCorpusFinacList(oldCorpusList);
		oldRentPlanContext.setCorpusOverageBusinessList(newCorpusOvergeList);
		oldRentPlanContext.setCorpusOverageFinacList(newCorpusOvergeList);
	}
	/**
	 * 次期的指定租金调息方法  租金不变  调整利息  本金=租金-利息
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 * @throws Exception
	 */
	public void namedRentTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,SpecialRuleBean specialRulesBean,int index)throws Exception {
		int startList = icb.getStartList()<=0?1:icb.getStartList();
		if(startList<=specialRulesBean.getEndList()){
			if(startList<specialRulesBean.getStartList()){
				startList=specialRulesBean.getStartList();
			}
			List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();
			List<String> oldRentList = oldRentPlanContext.getRentList();
			List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();
			List<String> oldYearRateList = oldRentPlanContext.getYearRateList();
			
			List<String> newInterestList = new ArrayList<String>(oldInterestList);
			List<String> newCorpusList = new ArrayList<String>(oldCorpusList);
			
			//剩余本金
			BigDecimal corpusRemain = cb.getRemainCorpus();
			//期利率
			BigDecimal newIssueRate = new BigDecimal(icb.getNewYearRateList().get(index)).multiply(new BigDecimal(cb.getRateType())).divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_EVEN);
			newIssueRate=newIssueRate.divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(specialRulesBean.getRegular_months()));
			
			List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
			for (int j = startList<specialRulesBean.getStartList()?specialRulesBean.getStartList()-1:startList-1; j < specialRulesBean.getEndList(); j++) {
				BigDecimal newInterest = corpusRemain.multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//				if(j==0){
//					int day=DateUtils.days360(cb.getTheoryFirstPlanDate(), cb.getFirstPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}else if(j==1){
//					int day=DateUtils.days360(cb.getTheorySecondPlanDate(), cb.getSecondPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}
				if(cb.getPeriodType() != null && cb.getPeriodType().contains("1") && j == 0){
					// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
					newInterest = BigDecimal.ZERO;
				}
				BigDecimal newCorpus = new BigDecimal(oldRentList.get(j)).subtract(newInterest);
				
				corpusRemain = corpusRemain.subtract(newCorpus);
				
				if(j == (cb.getIncomeNumber() - 1)){
					newCorpus = newCorpus.add(corpusRemain);
					oldRentList.set(j, newCorpus.add(newInterest).toString());
					corpusRemain = BigDecimal.ZERO;
				}
				
				newInterestList.set(j, newInterest.toString());
				newCorpusList.set(j, newCorpus.toString());
				newYearRateList.set(j, icb.getNewYearRateList().get(index));
			}
			cb.setRemainCorpus(corpusRemain);
			cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(specialRulesBean.getEndList()-specialRulesBean.getStartList()+1)*Integer.parseInt(specialRulesBean.getRegular_months()));
			
			String leaseAmt = cb.getCleanLeaseMoney();
			List<String> newCorpusOvergeList = TransRateHelper.getCorpusOvergeList(leaseAmt, oldCorpusList);
			// 设置他的合同号
			oldRentPlanContext.setProjOrCont(cb.getProjId());
			oldRentPlanContext.setCreator(cb.getCreator());
			oldRentPlanContext.setYearRate(cb.getYearRate());
			oldRentPlanContext.setYearRateList(newYearRateList);
			
			//改变利息、本金和剩余本金
			oldRentPlanContext.setInterestBusinessList(newInterestList);
			oldRentPlanContext.setInterestFinacList(newInterestList);
			oldRentPlanContext.setCorpusBusinessList(newCorpusList);
			oldRentPlanContext.setCorpusFinacList(newCorpusList);
			oldRentPlanContext.setCorpusOverageBusinessList(newCorpusOvergeList);
			oldRentPlanContext.setCorpusOverageFinacList(newCorpusOvergeList);
			
		}
	}
	/**
	 * 次期的指定本金调息方法    本金不变   调整利息   租金=本金+利息
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 * @throws Exception
	 */
	public void namedCorpusTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb,SpecialRuleBean srb,int index)throws Exception {
		int startList = icb.getStartList()<=0?1:icb.getStartList();
		if(startList<=srb.getEndList()){
			if(startList<srb.getStartList()){
				startList=srb.getStartList();
			}
			List<String> oldCorpusList = oldRentPlanContext.getCorpusBusinessList();
			List<String> oldInterestList = oldRentPlanContext.getInterestBusinessList();
			List<String> oldYearRateList = oldRentPlanContext.getYearRateList();
			
			List<String> newInterestList = new ArrayList<String>(oldInterestList);
			
			BigDecimal corpusRemain = cb.getRemainCorpus();//剩余本金
			//期利率
			BigDecimal newIssueRate = new BigDecimal(icb.getNewYearRateList().get(index)).multiply(new BigDecimal(cb.getRateType())).divide(new BigDecimal(36000), 20, BigDecimal.ROUND_HALF_EVEN);
			newIssueRate=newIssueRate.divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(srb.getRegular_months()));
			List<String> newYearRateList = new ArrayList<String>(oldYearRateList);
			for (int j = startList>=srb.getStartList()?startList-1:srb.getStartList()-1; j <srb.getEndList(); j++) {
				BigDecimal newInterest = corpusRemain.multiply(newIssueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//				if(j==0){
//					int day=DateUtils.days360(cb.getTheoryFirstPlanDate(), cb.getFirstPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}else if(j==1){
//					int day=DateUtils.days360(cb.getTheorySecondPlanDate(), cb.getSecondPlanDate());
//					BigDecimal dayRate=newIssueRate.divide(new BigDecimal(360),Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
//					BigDecimal adjustInterest=corpusRemain.multiply(dayRate).multiply(new BigDecimal(day)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//					newInterest=newInterest.add(adjustInterest);
//				}
				if(cb.getPeriodType() != null && cb.getPeriodType().contains("1") && j == 0){
					// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
					newInterest = BigDecimal.ZERO;
				}
				corpusRemain = corpusRemain.subtract(new BigDecimal(oldCorpusList.get(j)));
				newInterestList.set(j, newInterest.toString());
				newYearRateList.set(j, icb.getNewYearRateList().get(index));
			}
			List<String> newRentList = TransRateHelper.listAddS(newInterestList, oldCorpusList);
			cb.setRemainLeaseTerm(cb.getRemainLeaseTerm()-(srb.getEndList()-srb.getStartList()+1)*Integer.parseInt(srb.getRegular_months()));
			cb.setRemainCorpus(corpusRemain);
			
			// 设置他的合同号
			oldRentPlanContext.setProjOrCont(cb.getProjId());
			oldRentPlanContext.setCreator(cb.getCreator());
			oldRentPlanContext.setYearRate(cb.getYearRate());
			oldRentPlanContext.setYearRateList(newYearRateList);
			//本金列表和本金剩余列表都不变
			oldRentPlanContext.setRentList(newRentList);
			oldRentPlanContext.setInterestBusinessList(newInterestList);
			oldRentPlanContext.setInterestFinacList(newInterestList);
			
		}
	}


}
