package com.reckon.renttranrate.service.impl;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.dao.impl.FundPlanDAOImpl;
import com.reckon.renttranrate.service.TransRateHelper;
import com.reckon.renttranrate.service.TransRateService;


/**
 * @author MHY QQ:648020894
 */
public class QuotaTransRateServiceImpl implements TransRateService {
	
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
	public void processPmtTranRate(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		logger.info("定额等比调息法开始，首先处理业务租金计划");
		
		int startList = icb.getStartList();
		//循环下标
		int startIndex = startList - 1;
		logger.info("定额等比调息法，起始期项是: " + startList);
		
		int grace = cb.getGrace();
		logger.info("定额等比调息法，宽限期是: " + grace);
		
		String adjustId = icb.getAdjustId();
		logger.info("定额等比调息法，调息基准利率ID: " + adjustId);
		
		FundPlanDAOImpl fundPlanDAO = new FundPlanDAOImpl();
		Hashtable<String, String> PBOCInfo = fundPlanDAO.findPBOCBAseRateInfoById(adjustId);
		logger.info("定额等比调息法，查询当前央行基准利率信息: " + PBOCInfo);
		
		//央行调息的上浮值（与上次的基础利率的差值）
		String floatRate = TransRateHelper.getFloatRateApplyCondition(cb, PBOCInfo);
		
		logger.info("定额等比调息法，央行利率上浮值是: " + floatRate + "个百分点。");
		
		// transRateMoney*floatRate/100/0.01 = transRateMoney*floatRate
//		logger.info("定额等比调息法，利息增长额系数: " + cb.getTransRateMoney());
//		if(cb.getTransRateMoney() != null && cb.getTransRateMoney().length() > 0){
//			BigDecimal transRateMoney = new BigDecimal(cb.getTransRateMoney());
//			
//			//sea edit 2014-04-01 等额调息的 央行调息利率调整值除上0.01再乘以 （定额调息的套系数，每上涨0.01加多少钱）
//			transRateMoney = new BigDecimal(floatRate).divide(new BigDecimal(0.01), 20,BigDecimal.ROUND_HALF_UP).multiply(transRateMoney).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
//			logger.info("定额等比调息法，每期应加的利息值是: " + transRateMoney.toString());
//			
//			List<String> rentList = oldRentPlanContext.getRentList();
//			logger.info("定额等比调息法，原租金列表: (" + rentList.size() + ")" + rentList);
//			
//			List<String> interestBusinessList = oldRentPlanContext.getInterestBusinessList();
//			logger.info("定额等比调息法，原利息列表: (" + interestBusinessList.size() + ")" + interestBusinessList);
//			
//			List<String> corpusBusinessList = oldRentPlanContext.getCorpusBusinessList();
//			logger.info("定额等比调息法，原本金列表: (" + corpusBusinessList.size() + ")" + corpusBusinessList);
//			
//			List<String> corpusOverageBusinessList = oldRentPlanContext.getCorpusOverageBusinessList();
//			logger.info("定额等比调息法，原本金列表: (" + corpusOverageBusinessList.size() + ")" + corpusOverageBusinessList);
//			
//			List<String> yearRateList = oldRentPlanContext.getYearRateList();// 年利率
//			logger.info("定额等比调息法，原年利率列表: (" + yearRateList.size() + ")" + yearRateList);
//			
//			//把一个本金列表中的值加起来，负值减掉（=加绝对值）
//			BigDecimal corpusRemain = TransRateHelper.getCorpusRemain(corpusBusinessList, startIndex);
//			
//			/**
//			 * 定额调息情况下，调息开始期项之后每期租金加上定额值，构建新的现金流做IRR测算，用新的IRR做本利金拆分的依据
//			 * sea edit 2014-04-03 
//			 */
//			//构建现金流 net_flow集合
//			List<Map<String, String>> alCash = new ArrayList<Map<String, String>>();
//			//添加现金流第0期
//			Map<String, String> map0 = new HashMap<String, String>();
//			map0.put("net_flow", String.valueOf( corpusRemain.multiply(new BigDecimal(-1)) ) );
//			alCash.add(map0);
//			for (int i = startIndex; i < rentList.size(); i++) {
//				//定额调息情况下，只有租金加上定额值，利息还是和其他方式一样调整
//				BigDecimal newRent = new BigDecimal(rentList.get(i)).add(transRateMoney);
//				rentList.set(i, newRent.toString());
//				
//				//现金流其余期项
//				Map<String, String> mapTemp = new HashMap<String, String>();
//				mapTemp.put("net_flow", String.valueOf( newRent ) );
//				alCash.add(mapTemp);
//			}
//			//计算年利率
//			String incomeNumberYear  = cb.getIncomeNumberYear(); // 年还租次数
//			String yearRate = IrrTools.getRateByFlow(alCash, incomeNumberYear);//租赁年利率
//			logger.debug("定额调息情况下计算IRR值:"+yearRate);
//			icb.setNewYearRate(yearRate);
//			
//			/**
//			 *定额调息情况下:构建现金流计算IRR作为本利金拆分的利率,
//			 *原因:定额调息情况下该合同的租金是加上一定的额度,
//			 *因此当前的年利率不能直接拿来做本利金拆分,
//			 *需重新根据租金构建现金流计算IRR再做本利金拆分
//			 *sea edit
//			 */
//			CalculationConditionImpl condition = new CalculationConditionImpl();
//			BigDecimal newIssueRate = condition.getIssueRate(new BigDecimal(icb.getNewYearRate()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_EVEN));
//			logger.debug("调息时使用的新期利率:"+newIssueRate);
//			logger.debug("rentList.size():"+rentList.size());
//			logger.debug("startIndex:"+startIndex);
//			int num = rentList.size();
//			for (int i = startIndex; i < rentList.size(); i++) {
//				
//				BigDecimal newRent = new BigDecimal( rentList.get(i) );
//				//新的业务本金计算方式与等额租金中调息计算逻辑一致
//				//(1+期利率)的i-调整开始期项+1的次方
//				//方式1计算的结果是倒叙的:int power = i - startIndex + 1;
//				
//				//方式2如下：减下标
//				int power = num - startIndex;
//				num = num - 1;
//				BigDecimal powRate = BigDecimal.ONE.add(newIssueRate).pow(power);
//				BigDecimal newCorpus = newRent.divide(powRate, Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_EVEN);
//				
//				//剩余本金做累减操作
//				corpusRemain = corpusRemain.subtract(newCorpus);
//				
//				//sea edit 最后一期：剩余本金为0 ,最后一期本金 = 当期本金 + 剩余本金,最后一期租金等于 = 档期租金 + 剩余本金,最后一期利息值不变
//				if(i == (rentList.size() - 1)){
//					newRent = newRent.add(corpusRemain);
//					newCorpus = newCorpus.add(corpusRemain);
//					corpusRemain = BigDecimal.ZERO;
//				}
//				
//				//利息值处理
//				BigDecimal newInterest = newRent.subtract(newCorpus);
//				//封装
//				rentList.set(i, newRent.toString());
//				corpusBusinessList.set(i, newCorpus.toString());
//				interestBusinessList.set(i, newInterest.toString());
//				corpusOverageBusinessList.set(i, corpusRemain.toString());
//				yearRateList.set(i, yearRate.toString());
//			}
//			logger.info("定额等比调息法，新租金列表: (" + rentList.size() + ")" + rentList);
//			logger.info("定额等比调息法，新业务本金列表: (" + corpusBusinessList.size() + ")" + corpusBusinessList);
//			logger.info("定额等比调息法，新业务利息列表: (" + interestBusinessList.size() + ")" + interestBusinessList);
//			logger.info("定额等比调息法，新业务本金余额列表: (" + corpusOverageBusinessList.size() + ")" + corpusOverageBusinessList);
//			logger.info("定额等比调息法，新业务年利率列表: (" + yearRateList.size() + ")" + yearRateList);
//		} else {
//			logger.info("定额等比调息法，调息结束，参数值不合格，无法调息。");
//		}
//		logger.info("定额等比调息法结束，下面处理财务租金计划");
	}
}
