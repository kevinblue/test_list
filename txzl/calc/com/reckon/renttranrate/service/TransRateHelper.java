package com.reckon.renttranrate.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.log4j.lf5.StartLogFactor5;

import com.reckon.bean.CashDetailsBean;
import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.InterContBean;
import com.reckon.bean.RentPlanBean;
import com.reckon.bean.SpecialRuleBean;
import com.reckon.calculation.condition.CalculationConditionImpl;
import com.reckon.calculation.utils.IRRCalculateUtil;
import com.reckon.calculation.utils.RentCalculateUtil;
import com.reckon.calculation.utils.Scale;
import com.reckon.dao.impl.FundPlanDAOImpl;
import com.reckon.entity.contract.reckon.cash.CashHelp;
import com.reckon.entity.contract.reckon.fund.ContractSpecialRulesBean;
import com.reckon.entity.interest.FundStandardInterest;
import com.reckon.util.DateUtils;
import com.reckon.util.IrrTools;
import com.reckon.util.RateTools;
import com.reckon.util.RentTools;
import com.reckon.util.tools.DateTools;
import com.tenwa.kernal.utils.UUIDUtil;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;

/**
 * @author MHY QQ:648020894
 */
public class TransRateHelper {

	private static Logger logger = Logger.getLogger(TransRateHelper.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM");

	/**
	 * 央行浮动比例调息
	 * 
	 * @param leaseTerm
	 *            租赁期限
	 * @param oldYearRateStr
	 *            原年利率
	 * @param adjust
	 *            央行利率调整信息
	 * @param adjustPoint
	 *            利率调整值
	 * @return 新年利率
	 */
	public static String getRateByProportion(int leaseTerm, String oldYearRateStr, Hashtable<String, String> adjust, String adjustPoint) {

		String rateBase = "0";// 基准利率
		String floatPoint = "0";// 基准利率上涨点，原来是5调倒6，则上张点是6-5=1

		int leaseTermValue = leaseTerm;

		if (leaseTermValue <= 6) {// 六个月以内
			rateBase = adjust.get("base_rate_half").toString();
			floatPoint = adjust.get("rate_half").toString();
		} else if (leaseTermValue <= 12) {// 一年以内
			rateBase = adjust.get("base_rate_one").toString();
			floatPoint = adjust.get("rate_one").toString();
		} else if (leaseTermValue <= 36) {// 二年到三年之间时
			rateBase = adjust.get("base_rate_three").toString();
			floatPoint = adjust.get("rate_three").toString();
		} else if (leaseTermValue <= 60) {// 四，五年之间时
			rateBase = adjust.get("base_rate_five").toString();
			floatPoint = adjust.get("rate_five").toString();
		} else {// 五年以上时
			rateBase = adjust.get("base_rate_abovefive").toString();
			floatPoint = adjust.get("rate_abovefive").toString();
		}
		// 越秀算法：新年利率 = 原年利率*调息后基准利率/调息前基准利率。

		// 计算他的利率值
		//BigDecimal adjustBefore = new BigDecimal(rateBase);
		//BigDecimal adjustAfter = adjustBefore.add(new BigDecimal(floatPoint));
		//2014-11-28:调息前基准利率=调息后基准利率 - 基准利率上涨点
		BigDecimal adjustAfter = new BigDecimal(rateBase);
		BigDecimal adjustBefore = adjustAfter.subtract(new BigDecimal(floatPoint));
		BigDecimal oldYearRate = new BigDecimal(oldYearRateStr);
		BigDecimal newYearRate = oldYearRate.multiply(adjustAfter).divide(adjustBefore, Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		
		return newYearRate.toString();
	}
	public static String getRateFloatByProportion(int leaseTerm,  List<FundStandardInterest> adjust) {
		
		BigDecimal floatPoint = BigDecimal.ZERO;// 基准利率上涨点
		
		int leaseTermValue = leaseTerm;
		for(FundStandardInterest map:adjust){
			if (leaseTermValue <= 6) {// 六个月以内
				floatPoint = map.getRateHalf().add(floatPoint);
			} else if (leaseTermValue <= 12) {// 一年以内
				floatPoint = map.getRateOne().add(floatPoint);
			} else if (leaseTermValue <= 36) {// 二年到三年之间时
				floatPoint = map.getRateThree().add(floatPoint);
			} else if (leaseTermValue <= 60) {// 四，五年之间时
				floatPoint = map.getRateFive().add(floatPoint);
			} else {// 五年以上时
				floatPoint = map.getRateAbovefive().add(floatPoint);
			}
		}
		
		return floatPoint.toString();
	}

	/**
	 * 央行浮动比例调息
	 * 
	 * @param leaseTerm
	 *            租赁期限
	 * @param adjust
	 *            央行利率调整信息
	 * @param adjustPoint
	 *            利率调整值
	 * @return 新年利率
	 */
	public static String getRateByProportion(int leaseTerm, Hashtable<String, String> adjust, String adjustPoint) {

		String rateBase = "0";// 基准利率

		int leaseTermValue = leaseTerm;

		if (leaseTermValue <= 6) {// 六个月以内
			rateBase = adjust.get("base_rate_half").toString();
		} else if (leaseTermValue <= 12) {// 一年以内
			rateBase = adjust.get("base_rate_one").toString();
		} else if (leaseTermValue <= 36) {// 二年到三年之间时
			rateBase = adjust.get("base_rate_three").toString();
		} else if (leaseTermValue <= 60) {// 四，五年之间时
			rateBase = adjust.get("base_rate_five").toString();
		} else {// 五年以上时
			rateBase = adjust.get("base_rate_abovefive").toString();
		}
		// 新年利率 = 交易结构利率调整值*调息后基准利率。
		BigDecimal adjustPointValue = new BigDecimal(1).add(new BigDecimal(adjustPoint)) ;
		BigDecimal rateBaseValue = new BigDecimal(rateBase);
		BigDecimal newYearRate = adjustPointValue.multiply(rateBaseValue).setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		
		return newYearRate.toString();
	}
	/**
	 * 按央行利率加点
	 * 
	 * @param leaseTerm
	 *            期限
	 * @param oldYearRate
	 *            年利率
	 * @return
	 */
	public static String getRateByPoint(int leaseTerm, String oldYearRate, Hashtable<String, String> adjust) {

		int leaseTermValue = leaseTerm;
		String floatPoint = "0";
		if (leaseTermValue <= 6) {
			floatPoint = adjust.get("rate_half").toString();
		} else if (leaseTermValue <= 12) {
			floatPoint = adjust.get("rate_one").toString();
		} else if (leaseTermValue <= 36) {
			floatPoint = adjust.get("rate_three").toString();
		} else if (leaseTermValue <= 60) {
			floatPoint = adjust.get("rate_five").toString();
		} else {
			floatPoint = adjust.get("rate_abovefive").toString();
		}
		// 计算他的利率值,在央行的基础上加多少点
		floatPoint = String.valueOf(Double.parseDouble(floatPoint) + Double.parseDouble(oldYearRate));
		// 返回的是%之多少
		return floatPoint;
	}
	
	/**
	 * 按央行利率加点
	 * 
	 * @param leaseTerm
	 *            期限
	 * @param adjustPoint
	 *            利率调整值
	 * @return
	 */
	public static String getRateByPoint(int leaseTerm, Hashtable<String, String> adjust, String adjustPoint) {

		int leaseTermValue = leaseTerm;
		String rateBase = "0";// 基准利率

		if (leaseTermValue <= 6) {// 六个月以内
			rateBase = adjust.get("base_rate_half").toString();
		} else if (leaseTermValue <= 12) {// 一年以内
			rateBase = adjust.get("base_rate_one").toString();
		} else if (leaseTermValue <= 36) {// 二年到三年之间时
			rateBase = adjust.get("base_rate_three").toString();
		} else if (leaseTermValue <= 60) {// 四，五年之间时
			rateBase = adjust.get("base_rate_five").toString();
		} else {// 五年以上时
			rateBase = adjust.get("base_rate_abovefive").toString();
		}
		
		// 新年利率 = 交易结构利率调整值+调息后基准利率。
		BigDecimal adjustPointValue = new BigDecimal(adjustPoint);
		BigDecimal rateBaseValue = new BigDecimal(rateBase);
		BigDecimal newYearRate = adjustPointValue.add(rateBaseValue).setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		
		// 返回的是%之多少
		return newYearRate.toString();
	}

	/**
	 * 计算出该合同的新利率
	 * 
	 * @param floatType
	 *            调息方式
	 * @param leaseRerm
	 *            期限
	 * @param oldYearRate
	 *            旧利率
	 * @param adjustValue
	 *            调整值
	 * @param adjust
	 *            央行利率
	 * @return
	 */
	public static String getNewRate(ConditionBean condition, String oldYearRate, Hashtable<String, String> adjust) {
		String newRate = "0";

		if ("proportion".equals(condition.getRateFloatType())) {// 按央行利率浮动时
			//newRate = getRateByProportion(condition.getLeaseTerm(), oldYearRate, adjust, condition.getRateFloatAmt());
			newRate = getRateByProportion(condition.getLeaseTerm(), adjust, condition.getRateFloatAmt());
		} else if ("add".equals(condition.getRateFloatType())) {// 按央利率加点时
			//newRate = getRateByPoint(condition.getLeaseTerm(), oldYearRate, adjust);
			newRate = getRateByPoint(condition.getLeaseTerm(), adjust, condition.getRateFloatAmt());
		} else {// 固定利率
			newRate = oldYearRate;
		}
		return newRate;
	}

	/**
	 * 计算出该合同的新利率
	 * 
	 * @param floatType
	 *            调息方式
	 * @param leaseRerm
	 *            期限
	 * @param oldYearRate
	 *            旧利率
	 * @param adjustValue
	 *            调整值
	 * @param adjust
	 *            央行利率
	 * @return
	 */
	public static String getNewRate(String floatType, int leaseRerm, String oldYearRate, String adjustValue, Hashtable<String, String> adjust) {
		String newRate = "0";

		if ("proportion".equals(floatType)) {// 按央行利率浮动时
			newRate = getRateByProportion(leaseRerm, oldYearRate, adjust, adjustValue);
		} else if ("add".equals(floatType)) {// 按央利率加点时
			newRate = getRateByPoint(leaseRerm, oldYearRate, adjust);
		} else {// 固定利率
			newRate = oldYearRate;
		}
		return newRate;
	}

	/**
	 * 
	 * 2011-9-14 调息改为等比上调,该方法算出在这次调息中等比上调的比例值
	 * 
	 * @param al
	 *            现金流 用HashMap key为net_flow 存的每期的值
	 * @param newRate
	 *            新年利率
	 * @param income_number_year
	 *            年还款次数
	 * @return
	 */
	public String getAdjustRatioByCash(List<Map<String, String>> al, String newRate, String income_number_year) {
		logger.info("传入测算iavg的参数:新的利率" + newRate + "--年还租次数：" + income_number_year);
		double dnewRate = 0.0;
		double dsubRate = 0.0;
		double iavg = 1.1;
		ArrayList<Map<String, String>> alcash = new ArrayList<Map<String, String>>();
		if (newRate != null && !newRate.equals("")) {
			dnewRate = Double.parseDouble(newRate);
		} else {
			logger.error("调息过程中出错:新的年利率为空!");
			return "1";
		}
		double dmaxavg = 1.9;// 初始化最大比率
		double dminavg = 0.1;// 初始化最小比率
		int flag = 0;
		while (new BigDecimal(dnewRate - dsubRate).abs().doubleValue() > 0.000000001 && flag < 100) {
			alcash = new ArrayList<Map<String, String>>();
			alcash.add(al.get(0));// 第一笔负值始终不变
			for (int i = 1; i < al.size(); i++) {// 把后续每期二分搞被法等比上调直到反推出来的年利率等于新的年利率
				Map<String, String> hm = (Map<String, String>) al.get(i);
				Map<String, String> hmTemp = new HashMap<String, String>();
				BigDecimal bigDecimal = new BigDecimal(String.valueOf(hm.get("net_flow")));

				bigDecimal = bigDecimal.multiply(new BigDecimal(iavg));
				hmTemp.put("net_flow", String.valueOf(bigDecimal));
				alcash.add(hmTemp);
			}

			String subRate = IrrTools.getRateByFlow(alcash, income_number_year);
			logger.debug("等比为" + iavg + ",测算利率为:" + subRate);
			if (subRate != null && !subRate.equals("")) {
				dsubRate = Double.parseDouble(subRate);
			}
			if (dsubRate >= 100) {
				dminavg = iavg;
				iavg = (dmaxavg + iavg) / 2;
			} else {
				if (new BigDecimal(dnewRate - dsubRate).doubleValue() < 0) {
					dmaxavg = iavg;
					iavg = (iavg + dminavg) / 2;
				} else if (new BigDecimal(dnewRate - dsubRate).doubleValue() > 0) {
					dminavg = iavg;
					iavg = (dmaxavg + iavg) / 2;
				}
			}
			flag++;
		}
		return String.valueOf(iavg);
	}
	
	/**
	 * 
	 * 等比上调的比例，等额租金法，非宽限期
	 * 
	 * @param oldRentList 租金计划列表 R1...Rs...Rn
	 * @param startList  起始期数 1...s...n
	 * @param issueRate 新每期利率i
	 * @param corpusRemain 剩余本金Cr
	 * @return 上浮系数x
	 */
	public static BigDecimal getRentFloatCoefficient(List<String> oldPlanDateList,List<String> oldRentList, List<ContractFundFundPlan> fundFundPlanList,int startIndex, BigDecimal newIssueRate, String corpusRemain,ConditionBean cb,List<CashHelp> cashHelpList) {
		//先计算出流量对应的次幂数
		Map<String,String> map=new HashMap<String, String>();
		for(CashHelp cs:cashHelpList){
			map.put(cs.getCashDate(), cs.getCashDate());
		}
		BigDecimal one = BigDecimal.ONE;
		BigDecimal daoRate = one.add(newIssueRate);
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			map.put(cfrp.getPlanDate().substring(0, 7), cfrp.getPlanDate().substring(0, 7));
		}
		for(String str:oldPlanDateList){
			map.put(str.substring(0, 7), str.substring(0, 7));
		}
		 Map<String, Integer> intmap = new TreeMap<String, Integer>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                    	int dateDiff = DateUtils.getCompareDate(obj1,obj2,"yyyy-MM");
	                        // 升序排序
	                        return dateDiff;
	                    }
	                });
		int count=0;
		for(String key : map.keySet()){
			intmap.put(key, count);
			count++;
		}
		int num=0;
		for(String key : intmap.keySet()){
			intmap.put(key, num);
			num++;
		}
		//资金折现
		BigDecimal fundDiscount=BigDecimal.ZERO;
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			BigDecimal paytype=BigDecimal.ONE;
			if(cfrp.getPayType().getId().equals("pay_type_out")){
				paytype=paytype.multiply(new BigDecimal(-1));
			}
			fundDiscount=fundDiscount.add(cfrp.getPlanMoney().multiply(paytype).divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(cfrp.getPlanDate().substring(0, 7)))),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP));
		}
		//租金折现
		BigDecimal oldrentDiscount=BigDecimal.ZERO;
		BigDecimal newrentDiscount=BigDecimal.ZERO;
		for(int i=0;i<oldPlanDateList.size();i++){
			String planDate=oldPlanDateList.get(i);
			int index=intmap.get(planDate.substring(0, 7));
			BigDecimal disCountRent= new BigDecimal(oldRentList.get(i)).divide(new BigDecimal(Math.pow(daoRate.doubleValue(),index)),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP);
			if(i<startIndex){
				oldrentDiscount=oldrentDiscount.add(disCountRent);
			}else{
				newrentDiscount=newrentDiscount.add(disCountRent);
			}
		}
		BigDecimal up=fundDiscount.multiply(new BigDecimal(-1)).subtract(oldrentDiscount);
		BigDecimal result=up.divide(newrentDiscount,Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP);
		return result;
	}
	public static BigDecimal getRentByIrr(List<String> oldPlanDateList,List<String> oldRentList, List<ContractFundFundPlan> fundFundPlanList,int startIndex, BigDecimal newIssueRate, String corpusRemain,ConditionBean cb,List<CashHelp> cashHelpList) {
		//先计算出流量对应的次幂数
		Map<String,String> map=new HashMap<String, String>();
		for(CashHelp cs:cashHelpList){
			map.put(cs.getCashDate(), cs.getCashDate());
		}
		BigDecimal one = BigDecimal.ONE;
		BigDecimal daoRate = one.add(newIssueRate);
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			map.put(cfrp.getPlanDate().substring(0, 7), cfrp.getPlanDate().substring(0, 7));
		}
		for(String str:oldPlanDateList){
			map.put(str.substring(0, 7), str.substring(0, 7));
		}
		Map<String, Integer> intmap = new TreeMap<String, Integer>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						int dateDiff = DateUtils.getCompareDate(obj1,obj2,"yyyy-MM");
						// 升序排序
						return dateDiff;
					}
				});
		int count=0;
		for(String key : map.keySet()){
			intmap.put(key, count);
			count++;
		}
		int num=0;
		for(String key : intmap.keySet()){
			intmap.put(key, num);
			num++;
		}
		//资金折现
		BigDecimal fundDiscount=BigDecimal.ZERO;
		BigDecimal feetype16=BigDecimal.ZERO;//抵扣保证金
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			if(cfrp.getFeeType().getId().equals("feetype16")){
				feetype16= cfrp.getPlanMoney().add(feetype16);
				continue;
			}
			if(DateUtils.getCompareDate(cfrp.getPlanDate(),oldPlanDateList.get(startIndex))<0 ||
				cfrp.getFeeType().getId().equals("feetype10")){
				continue;
			}
			BigDecimal paytype=BigDecimal.ONE;
			if(cfrp.getPayType().getId().equals("pay_type_out")){
				paytype=paytype.multiply(new BigDecimal(-1));
			}
			fundDiscount=fundDiscount.add(cfrp.getPlanMoney().multiply(paytype).divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(cfrp.getPlanDate().substring(0, 7)))),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP));
		}
		//剩余融资额
		BigDecimal leaseMoney=getInterestList(oldRentList, cb.getCleanCreditMoney(), new BigDecimal(cb.getIrr()).divide(new BigDecimal(1200),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP), cb.getPeriodType(), cb.getGrace(), cb.getIncomeNumberYear(), cb.getEquipEndValue(), cb.getRateAdjustType(),startIndex);
		fundDiscount=fundDiscount.add(leaseMoney.divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(oldPlanDateList.get(startIndex-1).substring(0, 7)))),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(-1)));
		//租金折现
		BigDecimal k=BigDecimal.ZERO;
		BigDecimal result=BigDecimal.ONE;
		BigDecimal two = new BigDecimal("2");
		BigDecimal tmp1 = new BigDecimal("0");
		BigDecimal tmp2 = new BigDecimal("2");
		int j=0;
		while(result.abs().compareTo(new BigDecimal(0.00000001))==1 && j < 1000){
			BigDecimal newrentDiscount=BigDecimal.ZERO;
			for(int i=startIndex;i<oldPlanDateList.size();i++){
				String planDate=oldPlanDateList.get(i);
				int index=intmap.get(planDate.substring(0, 7));//次幂数
				BigDecimal currentRent= new BigDecimal(oldRentList.get(i));//当期租金
				BigDecimal disCountRent=BigDecimal.ZERO;
				//当期剩余抵扣保证金
				BigDecimal creentDk=feetype16.subtract(new BigDecimal(oldPlanDateList.size()-i).multiply(k).multiply(currentRent));
				//剩余保证金和当前租金比较
				if(creentDk.compareTo(BigDecimal.ZERO)>0&&creentDk.compareTo(k.multiply(currentRent))<0){
					int beforeindex=intmap.get(oldPlanDateList.get(i-1));
					disCountRent=disCountRent.subtract(creentDk.divide(new BigDecimal(Math.pow(daoRate.doubleValue(),beforeindex)),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP));
				}else if(creentDk.compareTo(BigDecimal.ZERO)<0){
					disCountRent= currentRent.multiply(k).divide(new BigDecimal(Math.pow(daoRate.doubleValue(),index)),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_UP);
				}
				newrentDiscount =newrentDiscount.add(disCountRent);
			}
			result=fundDiscount.add(newrentDiscount);
			if (result.doubleValue() > 0) {
				tmp2=k;
				k = k.add(tmp1).divide(two, 20, BigDecimal.ROUND_HALF_UP);
			}
			if (result.doubleValue() < 0) {
				tmp1=k;
				k = k.add(tmp2).divide(two, 20, BigDecimal.ROUND_HALF_UP);
			}
			j++;
		}
		return k;
	}
	/**
	 * 计算剩余净融资额
	 * @param oldPlanDateList
	 * @param oldRentList
	 * @param fundFundPlanList
	 * @param startIndex
	 * @param newIssueRate
	 * @param corpusRemain
	 * @param cb
	 * @param cashHelpList
	 * @return
	 */
	public static BigDecimal getInterestList(List<String> rent_list, BigDecimal leaseMoney, BigDecimal calRate, String periodType, int sgrace, String Income_number_year, String endValue,String rateType,int startIndex) {

		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		BigDecimal corpus_overage = BigDecimal.ZERO;
		// 本金余额,
		corpus_overage = leaseMoney;

		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		// 得到宽限期利息
		int grace = getGraceInterest(sgrace, rent_list, interests);

		for (int i = grace; i < startIndex; i++) {// 循环租金list
			BigDecimal inte = BigDecimal.ZERO;
			BigDecimal corpus = BigDecimal.ZERO;
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(periodType) && i == grace) {// 第一期且是期初时

				corpus = new BigDecimal(rent_list.get(i).toString()).setScale(RentTools.getRentAccuracy(), BigDecimal.ROUND_HALF_UP);
				
				inte = BigDecimal.ZERO;
				
				corpus_overage = corpus_overage.subtract(corpus);
				
			} else {
				for(int j=0;j<12/Integer.parseInt(Income_number_year);j++){
					
					inte = corpus_overage.multiply(calRate).add(inte).setScale(6, BigDecimal.ROUND_HALF_UP);
					if(j==12/Integer.parseInt(Income_number_year)-1){
						corpus = new BigDecimal(rent_list.get(i).toString()).subtract(corpus_overage.multiply(calRate)).setScale(6, BigDecimal.ROUND_HALF_UP);
					}else{
						corpus=corpus_overage.multiply(calRate).multiply(new BigDecimal(-1)).setScale(6, BigDecimal.ROUND_HALF_UP);
					}
					corpus_overage = corpus_overage.subtract(corpus);
				}
			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rent_list.size() - 1) {
				// 本金 --总的本金-以前的本金和-期末残值
				corpus = leaseMoney.subtract(new BigDecimal(corpus_total)).subtract(new BigDecimal(endValue));
				// 利息
				inte = new BigDecimal(rent_list.get(i).toString()).subtract(corpus);

			}
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(corpus).toString();
		
			// 添加list
			interests.add(inte.toString());
			// logger.debug("合同第"+i+"利息:"+inte);
		}

		return corpus_overage;
	}
	private static int getGraceInterest(int sgrace, List<String> rent_list, List<String> interests) {
		// 判断宽限期,计算宽限期利息
		int grace = sgrace;
		for (int i = 0; i < sgrace; i++) {
			interests.add(rent_list.get(i));
		}
		return grace;
	}
	/**
	 * @param rentList
	 *            租金计划列表 R1...Rs...Rn
	 * @param start
	 *            起始期数 1...s...n
	 * @param issueRate
	 *            新每期利率i
	 * @param corpus
	 *            剩余本金Cr
	 * @return 等额租金调息后的新的租金列表
	 */
	public static List<String> getNewRentListForEvenRent(List<String> oldPlanDateList,List<String> oldRentList, List<ContractFundFundPlan> fundFundPlanList,int startIndex, BigDecimal newIssueRate, String corpusRemain,ConditionBean cb,List<CashHelp> cashHelpList) {
		//等比上调的比例，等额租金法，非宽限期
		BigDecimal coefficient =  getRentByIrr(oldPlanDateList,oldRentList,fundFundPlanList, startIndex, newIssueRate, corpusRemain,cb,cashHelpList);
		
		List<String> newRentList = new ArrayList<String>();
		for (int i = 0; i < oldRentList.size(); i++) {
			String rent = oldRentList.get(i);
			if (i < startIndex) {
				newRentList.add(rent);
			} else {
				//每期租金乘以上调比例
				newRentList.add(new BigDecimal(rent).multiply(coefficient).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_UP).toString());
			}
		}
		return newRentList;
	}

	/**
	 * 根据新的租金得到被调整的期的新的本金数组
	 * 
	 * @param newRentList
	 * @param start
	 * @param newIssueRate 期利率
	 * @return
	 */
	public static List<String> getAdjustCorpusListForEvenRent(List<String> newRentList, int startIndex, BigDecimal newIssueRate) {
		List<String> newCorpusAdjust = new ArrayList<String>();
		
		int num = newRentList.size();
		for (int i = startIndex; i < newRentList.size(); i++) {
			BigDecimal newRent = new BigDecimal(newRentList.get(i));
			//(1+期利率)的i-调整开始期项+1的次方
			//方式1计算的结果是倒叙的:int power = i - startIndex + 1;
			
			//方式2如下：减下标
			int power = num - startIndex;
			num = num - 1;
			BigDecimal powRate = BigDecimal.ONE.add(newIssueRate).pow(power);
			
			BigDecimal newCorpus = newRent.divide(powRate, Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_EVEN);
			newCorpusAdjust.add(newCorpus.toString());
		}
		
		return newCorpusAdjust;
	}

	/**
	 * @param rentList
	 *            原租金列表
	 * @param corpusList
	 *            原本金列表
	 * @param start
	 *            开始期数
	 * @param issueRate
	 *            新期利率
	 */
	public static List<BigDecimal> getNewRentListForEvenCoupus(List<BigDecimal> rentList, List<BigDecimal> corpusList, int start, BigDecimal issueRate) {
		BigDecimal corpusAll = BigDecimal.ZERO;
		List<BigDecimal> newRentList = new ArrayList<BigDecimal>();
		for (int i = 0; i < corpusList.size(); i++) {
			corpusAll = corpusAll.add(corpusList.get(i));
		}
		for (int i = 0; i < rentList.size(); i++) {
			BigDecimal rent = rentList.get(i);
			BigDecimal corpus = corpusList.get(i);
			if (i >= start - 1) {
				BigDecimal interest = corpusAll.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_EVEN);
				rent = interest.add(corpus);
			}
			newRentList.add(rent);
			corpusAll = corpusAll.subtract(corpus);
		}
		return newRentList;
	}

	/**
	 * @param corpusList
	 *            本金列表
	 * @param interestList
	 *            利息列表
	 * @param start
	 *            调息起始日期
	 * @param oldIssueRate
	 *            旧期利率
	 * @param newIssueRate
	 *            新期利率
	 */
	public static List<BigDecimal> getNewRentListForEvenInterest(List<BigDecimal> corpusList, List<BigDecimal> interestList, int start, BigDecimal oldIssueRate, BigDecimal newIssueRate) {
		BigDecimal coefficient = newIssueRate.divide(oldIssueRate, 20, BigDecimal.ROUND_HALF_EVEN);
		List<BigDecimal> newRentList = new ArrayList<BigDecimal>();

		for (int i = 0; i < corpusList.size(); i++) {
			BigDecimal corpus = corpusList.get(i);
			BigDecimal inerest = interestList.get(i);
			if (i + 1 >= start) {
				inerest = inerest.multiply(coefficient);
			}
			BigDecimal rent = inerest.add(corpus);
			newRentList.add(rent);
		}
		return newRentList;
	}

	/**
	 * 2个字符串数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<String> listAddS(List<String> arr1, List<String> arr2) {
		List<String> result = new ArrayList<String>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, new BigDecimal(result.get(i)).add(new BigDecimal(arr2.get(i))).toString());
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相加
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listAdd(List<BigDecimal> arr1, List<BigDecimal> arr2) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (arr1 != null) {
			result.addAll(arr1);
			if (arr2 != null) {
				for (int i = 0; i < arr2.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).add(arr2.get(i)));
					} else {
						result.add(arr2.get(i));
					}
				}
			}
		} else if (arr2 != null) {
			result.addAll(arr2);
		}
		return result;
	}

	/**
	 * 2个字符串数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<String> listSubS(List<String> array, List<String> arrSub) {
		List<String> result = new ArrayList<String>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, new BigDecimal(result.get(i)).subtract(new BigDecimal(arrSub.get(i))).toString());
					} else {
						result.add(BigDecimal.ZERO.subtract(new BigDecimal(arrSub.get(i))).toString());
					}
				}
			}
		}
		return result;
	}

	/**
	 * 2个数组的数字按index相减
	 * 
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static List<BigDecimal> listSub(List<BigDecimal> array, List<BigDecimal> arrSub) {
		List<BigDecimal> result = new ArrayList<BigDecimal>();
		if (array != null) {
			result.addAll(array);
			if (arrSub != null) {
				for (int i = 0; i < arrSub.size(); i++) {
					if (i < result.size()) {
						result.set(i, result.get(i).subtract(arrSub.get(i)));
					} else {
						result.add(BigDecimal.ZERO.subtract(arrSub.get(i)));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 根据总本金和本金列表，修正最后一期的本金
	 * 
	 * @param oldCorpuTotal
	 * @param newCorpusList
	 */
	public static void adjustLastCorpus(String oldCorpuTotal, List<String> newCorpusList) {
		BigDecimal oldCorpus = new BigDecimal(oldCorpuTotal);
		for (String corpus : newCorpusList) {
			oldCorpus = oldCorpus.subtract(new BigDecimal(corpus));
		}
		String lastCorpus = newCorpusList.get(newCorpusList.size() - 1);
		lastCorpus = new BigDecimal(lastCorpus).add(oldCorpus).toString();
		newCorpusList.set(newCorpusList.size() - 1, lastCorpus);
	}

	/**
	 * 等额本金 根据新利率和旧利率，等比上调利息
	 * 
	 * @param oldInterestList
	 * @param i
	 * @param oldIssueRate
	 * @param newIssueRate
	 * @return
	 */
	public static List<String> getNewInterestListForEvenCorpus(List<String> oldPlanDateList,List<String> oldInterestList, List<String> oldCorpusList,List<ContractFundFundPlan> fundFundPlanList,int startList,  BigDecimal newIssueRate,List<CashHelp> cashHelpList) {
		//BigDecimal coefficient = newIssueRate.divide(oldIssueRate, 20, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal coefficient=getEvenInterestUpRate( oldPlanDateList,oldInterestList,oldCorpusList, fundFundPlanList,startList-1,  newIssueRate, cashHelpList);
		List<String> newInterestList = new ArrayList<String>();
		for (int i = 0; i < oldInterestList.size(); i++) {
			BigDecimal inerest = new BigDecimal(oldInterestList.get(i));
			if (i + 1 >= startList) {
				inerest = inerest.multiply(coefficient).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			}
			newInterestList.add(inerest.toString());
		}
		return newInterestList;
	}
	/**
	 * 均息法 已知irr  倒推上调利息比例
	 * @return
	 */
	public static BigDecimal getEvenInterestUpRate(List<String> oldPlanDateList,List<String> oldInterestList,List<String> oldCorpusList,List<ContractFundFundPlan> fundFundPlanList,int startIndex, BigDecimal newIssueRate,List<CashHelp> cashHelpList){
		//先计算出流量对应的次幂数
		Map<String,String> map=new HashMap<String, String>();
		for(CashHelp cs:cashHelpList){
			map.put(cs.getCashDate(), cs.getCashDate());
		}
		BigDecimal one = BigDecimal.ONE;
		BigDecimal daoRate = one.add(newIssueRate);
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			map.put(cfrp.getPlanDate().substring(0, 7), cfrp.getPlanDate().substring(0, 7));
		}
		for(String str:oldPlanDateList){
			map.put(str.substring(0, 7), str.substring(0, 7));
		}
		 Map<String, Integer> intmap = new TreeMap<String, Integer>(
	                new Comparator<String>() {
	                    public int compare(String obj1, String obj2) {
	                    	int dateDiff = DateUtils.getCompareDate(obj1,obj2,"yyyy-MM");
	                        // 升序排序
	                        return dateDiff;
	                    }
	                });
		int count=0;
		for(String key : map.keySet()){
			intmap.put(key, count);
			count++;
		}
		int num=0;
		for(String key : intmap.keySet()){
			intmap.put(key, num);
			num++;
		}
		//资金折现
		BigDecimal fundDiscount=BigDecimal.ZERO;
		for(ContractFundFundPlan cfrp:fundFundPlanList){
			BigDecimal paytype=BigDecimal.ONE;
			if(cfrp.getPayType().getId().equals("pay_type_out")){
				paytype=paytype.multiply(new BigDecimal(-1));
			}
			fundDiscount=fundDiscount.add(cfrp.getPlanMoney().multiply(paytype).divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(cfrp.getPlanDate().substring(0, 7)))),Scale.RENT_SCALE,BigDecimal.ROUND_HALF_UP));
		}
	
		BigDecimal corpusDiscount = BigDecimal.ZERO;//本金折现
		BigDecimal oldinterestDiscount = BigDecimal.ZERO;//利息折现
		BigDecimal newinterestDiscount = BigDecimal.ZERO;//利息折现
		for (int i = 0; i < oldCorpusList.size(); i++) {
			BigDecimal oldCorpus = new BigDecimal(oldCorpusList.get(i));
			BigDecimal oldInterest = new BigDecimal(oldInterestList.get(i));
			if(i<startIndex){
				oldinterestDiscount=oldinterestDiscount.add(oldCorpus.divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(oldPlanDateList.get(i).substring(0, 7)))),Scale.RENT_SCALE,BigDecimal.ROUND_HALF_UP));
			}else{
				newinterestDiscount=newinterestDiscount.add(oldCorpus.divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(oldPlanDateList.get(i).substring(0, 7)))),Scale.RENT_SCALE,BigDecimal.ROUND_HALF_UP));
			}
			corpusDiscount=corpusDiscount.add(oldInterest.divide(new BigDecimal(Math.pow(daoRate.doubleValue(),intmap.get(oldPlanDateList.get(i).substring(0, 7)))),Scale.RENT_SCALE,BigDecimal.ROUND_HALF_UP));
		}
		BigDecimal up=fundDiscount.multiply(new BigDecimal(-1)).subtract(corpusDiscount).subtract(oldinterestDiscount);
		return up.divide(newinterestDiscount, Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
	}
	/**
	 * 均息法 根据新利率和旧利率，等比上调利息
	 * 
	 * @param oldInterestList
	 * @param i
	 * @param oldIssueRate
	 * @param newIssueRate
	 * @return
	 */
	public static List<String> getNewInterestListForEvenInterest(List<String> oldPlanDateList,List<String> oldInterestList, List<String> oldCorpusList,List<ContractFundFundPlan> fundFundPlanList,int startList,  BigDecimal newIssueRate,List<CashHelp> cashHelpList) {
		return getNewInterestListForEvenCorpus(oldPlanDateList,oldInterestList,oldCorpusList, fundFundPlanList,startList,  newIssueRate,cashHelpList);
	}

	/**
	 * 根据总本金和本金列表获取每期的剩余本金
	 * 
	 * @param leaseMoney
	 *            总的测算本金
	 * @param corpusList
	 *            每一期的本金
	 * @return
	 */
	public static List<String> getCorpusOvergeList(String leaseMoney, List<String> corpusList) {
		BigDecimal total = new BigDecimal(leaseMoney);// 累积每一期的本金
		List<String> corpusOvergeList = new ArrayList<String>();
		for (int i = 0; i < corpusList.size(); i++) {
			total = total.subtract(new BigDecimal(corpusList.get(i)));
			corpusOvergeList.add(total.toString());
		}
		return corpusOvergeList;
	}

	/**
	 * 
	 * ( 根据调息方式，调息开始时间计算实际调息开始日期)
	 * 
	 * @param adjustType
	 * @param adjustDate
	 * @return
	 * @throws Exception
	 */
	public static String getNewDateByAdjustType(String adjustType, String adjustDate) throws Exception {
		String sdate = adjustDate;
		if ("next_year".equals(adjustType)) {
			sdate = DateTools.getYearFirstDay(adjustDate);
		}
		if ("next_month".equals(adjustType)) {
			sdate = DateTools.getMonthFirstDay(adjustDate);
		}
		//TODO 其他俩种方式待定
		return sdate;
	}
	/**
	 * 
	 * @param adjustType 调整类型
	 * @param adjustDate 央行利息调整日
	 * @param startDate 起租日
	 * @return
	 * @throws Exception
	 */
	public static String getNewDateByAdjustType(String adjustType, String adjustDate,String startDate) throws Exception {
		String sdate = adjustDate;
		if ("next_year".equals(adjustType)||"next_year_next_list".equals(adjustType)) {
			sdate = DateTools.getYearFirstDay(adjustDate);//.substring(0, 5)+startDate.substring(5, 10);
		}
		if ("next_month".equals(adjustType)) {
			sdate = DateTools.getMonthFirstDay(adjustDate);
		}
		if ("next_day".equals(adjustType)) {
			sdate = DateTools.getNextDay(adjustDate);
		}
		//TODO 其他俩种方式待定
		return sdate;
	}

	/**
	 * 根据调息起始日期找到调息开始的期数 : 1 - n
	 * 
	 * @param planDateList
	 * @param adjustStartDate
	 * @return
	 */
	public static int getStartListFromRentPlan(List<String> planDateList, String adjustStartDate) {
		try {
			Date startDate = DateTools.dateFormart.parse(adjustStartDate);
			for (int i = 0; i < planDateList.size(); i++) {
				Date planDate = DateTools.dateFormart.parse(planDateList.get(i));
				if (!startDate.after(planDate)) {
					return i + 1;
				}
			}
		} catch (ParseException e) {
			logger.error("日期转化错误", e);
		}
		return 0;
	}

	/**
	 * 判断从起始日期开始的利率是否相等 不相等的话不可以调息
	 * 
	 * @param yearRateList
	 * @param startList
	 * @return
	 */
	public static boolean isRateEvenFromStartList(List<String> yearRateList, int startList) {
		if (yearRateList != null && startList <= yearRateList.size() && startList >= 1) {
			String rate = yearRateList.get(startList - 1);// 调整当期利率
			for (int i = startList; i < yearRateList.size(); i++) {
				String rateTemp = yearRateList.get(i);
				if (!rateTemp.equals(rate)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 根据租金计划和资金收付计划创建现金流
	 * 
	 * @param rentPlanList
	 * @param fundChargePlan
	 * @return
	 * @throws ParseException
	 */
	public static List<CashDetailsBean> createCashFlowList(List<RentPlanBean> rentPlanList, List<FundPlanBean> fundChargePlan) throws ParseException {

		// 重组现金流开始第一步，把租金计划当成默认的现金流，后边把资金收付计划合并进来
		List<CashDetailsBean> rentCashFlow = new ArrayList<CashDetailsBean>();
		for (int i = 0; i < rentPlanList.size(); i++) {
			RentPlanBean rentPlan = rentPlanList.get(i);
			Date planDate = sdf.parse(rentPlan.getPlanDate());
			CashDetailsBean cashFlow = new CashDetailsBean();
			cashFlow.setId(UUIDUtil.getUUID());
			cashFlow.setPlanDate(sdm.format(planDate));
			cashFlow.setFundIn(rentPlan.getRent());
			cashFlow.setFundInDetails("租金：" + rentPlan.getRent() + ";");
			cashFlow.setNetFlow(new BigDecimal(cashFlow.getFundIn()).subtract(new BigDecimal(cashFlow.getFundOut())).toString());
			rentCashFlow.add(cashFlow);
		}

		// 第二部：合并资金收付计划到现金流中。两层循环，确定资金收付计划在现金流中的位置
		for (int i = 0; i < fundChargePlan.size(); i++) {

			FundPlanBean fundFundPlan = fundChargePlan.get(i);
			int monthNew = Integer.parseInt(fundFundPlan.getPlanDate().substring(0, 7).replace("-", ""));
			String month = sdm.format(sdf.parse(fundFundPlan.getPlanDate()));// 看是几月份的收付计划

			CashDetailsBean cashFlowNewAdd = new CashDetailsBean();
			int newAddCashFlowLocation = -1;

			for (int j = 0; j < rentCashFlow.size(); j++) {
				CashDetailsBean cashFlowTemp = rentCashFlow.get(j);
				int monthCash = Integer.parseInt(cashFlowTemp.getPlanDate().replace("-", ""));

				if (monthNew == monthCash) {
					// 和当前的日期相等,合并到现金流中去
					if ("pay_type_in".equalsIgnoreCase(fundFundPlan.getPayType())) {
						cashFlowTemp.setFundIn(new BigDecimal(cashFlowTemp.getFundIn()).add(new BigDecimal(fundFundPlan.getPlanMoney())).toString());
						cashFlowTemp.setFundInDetails(cashFlowTemp.getFundInDetails() + fundFundPlan.getFeeTypeName() + ":" + fundFundPlan.getPlanMoney() + ";");
					}
					if ("pay_type_out".equalsIgnoreCase(fundFundPlan.getPayType())) {
						cashFlowTemp.setFundOut(new BigDecimal(cashFlowTemp.getFundOut()).add(new BigDecimal(fundFundPlan.getPlanMoney())).toString());
						cashFlowTemp.setFundOutDetails(cashFlowTemp.getFundOutDetails() + fundFundPlan.getFeeTypeName() + ":" + fundFundPlan.getPlanMoney() + ";");
					}
					cashFlowTemp.setNetFlow(new BigDecimal(cashFlowTemp.getFundIn()).subtract(new BigDecimal(cashFlowTemp.getFundOut())).toString());
					break;
				} else if (monthNew < monthCash || (monthNew > monthCash && (j + 1) == rentCashFlow.size())) {
					cashFlowNewAdd.setId(UUIDUtil.getUUID());
					cashFlowNewAdd.setPlanDate(month);
					if ("pay_type_in".equalsIgnoreCase(fundFundPlan.getPayType())) {
						cashFlowNewAdd.setFundIn("" + fundFundPlan.getPlanMoney());
						cashFlowNewAdd.setFundInDetails(fundFundPlan.getFeeTypeName() + ":" + fundFundPlan.getPlanMoney() + ";");
					}
					if ("pay_type_out".equalsIgnoreCase(fundFundPlan.getPayType())) {
						cashFlowNewAdd.setFundOut("" + fundFundPlan.getPlanMoney());
						cashFlowNewAdd.setFundOutDetails(fundFundPlan.getFeeTypeName() + ":" + fundFundPlan.getPlanMoney() + ";");
					}
					cashFlowNewAdd.setNetFlow(new BigDecimal(cashFlowNewAdd.getFundIn()).subtract(new BigDecimal(cashFlowNewAdd.getFundOut())).toString());
					newAddCashFlowLocation = j;
					break;
				}
			}
			if (newAddCashFlowLocation > -1) {
				rentCashFlow.add(newAddCashFlowLocation, cashFlowNewAdd);
			}
		}
		return rentCashFlow;
	}

	/**
	 * 根据商务条件和租金计划获取IRR
	 * 
	 * @param cb
	 * @param oldRentPlanContext
	 * @return
	 */
	public static String getPlanIrrFromRentPlan(ConditionBean cb, FundRentPlanBean oldRentPlanContext) {
		List<String> rentStrList = oldRentPlanContext.getRentList();
		List<BigDecimal> rentList = new ArrayList<BigDecimal>();
		for (int i = 0; i < rentStrList.size(); i++) {
			rentList.add(new BigDecimal(rentStrList.get(i)));
		}
		BigDecimal leaseAmt = new BigDecimal(cb.getCleanLeaseMoney());
		//起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		if (cb.getPeriodType().contains("1")) {
			BigDecimal rent0 = rentList.get(0).subtract(leaseAmt);
			rentList.set(0, rent0);
		} else if (cb.getPeriodType().contains("0")) {
			rentList.add(0, BigDecimal.ZERO.subtract(leaseAmt));
		}
		BigDecimal equipEndVlue = new BigDecimal(cb.getEquipEndValue());
		if(equipEndVlue.compareTo(BigDecimal.ZERO)==1){//期未余值先默认加到最后一期
			rentList.set(rentList.size()-1, rentList.get(rentList.size()-1).add(equipEndVlue));
		}
		logger.info("租金计划获取IRR时净流量===>"+rentList);
		BigDecimal irr = IRRCalculateUtil.getIRR(rentList);
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(new BigDecimal(100));
		return irr.setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
	}

	/**
	 * 根据现金流获取IRR
	 * 
	 * @param cashFlowList
	 * @return
	 */
	public static String getRealIrrFromCashDetail(ConditionBean cb, List<CashDetailsBean> cashFlowList) {
		List<BigDecimal> cashFlow = new ArrayList<BigDecimal>();
		for (CashDetailsBean cdb : cashFlowList) {
			cashFlow.add(new BigDecimal(cdb.getNetFlow()));
		}
		
		BigDecimal irr = IRRCalculateUtil.getIRR(cashFlow);
		irr = irr.multiply(new BigDecimal(cb.getIncomeNumberYear())).multiply(new BigDecimal(100));
		return irr.setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN).toString();
	}	
	

	/**
	 * 调息后处理财务租金计划
	 * 基本逻辑：
	 * 根据新的租金计划重建从调息起始期开始的现金流，包含剩余期项的总本金
	 * 计算该现金流的IRR（期利率）
	 * 根据新的期利率计算每期的利息以及本金，本金余额
	 * @param cb
	 * @param oldRentPlanContext
	 * @param icb
	 */
	public static void processFinacRentPlan(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) {
		
		logger.info("调息后处理财务租金计划开始...");
		int startList = icb.getStartList();
		int startIndex = startList - 1;
		int grace = cb.getGrace();
		logger.info("调息后处理财务租金计划，起始期项：" + startList);
		logger.info("调息后处理财务租金计划，宽限期：" + grace);
		
		List<String> rentList = oldRentPlanContext.getRentList();
		logger.info("调息后处理财务租金计划，租金列表：(" + rentList.size() + ")" + rentList);
		
		List<String> corpusFinacList = oldRentPlanContext.getCorpusFinacList();
		logger.info("调息后处理财务租金计划，原本金列表：(" + corpusFinacList.size() + ")" + corpusFinacList);
		
		List<String> interestFinacList = oldRentPlanContext.getInterestFinacList();
		logger.info("调息后处理财务租金计划，原利息列表：(" + interestFinacList.size() + ")" + interestFinacList);
		
		List<String> corpusOverageFinacList = oldRentPlanContext.getCorpusOverageFinacList();
		logger.info("调息后处理财务租金计划，原本金余额列表：(" + corpusOverageFinacList.size() + ")" + corpusOverageFinacList);
		
		// 宽限期内调息，直接把业务利息复制到财务利息里边，剩余的再说
		List<String> interestBusinessList = oldRentPlanContext.getInterestBusinessList();
		for (int i = startIndex; i < grace; i++) {
			logger.info("调息后处理财务租金计划，处理宽限期数据：" + i);
			interestFinacList.set(i, interestBusinessList.get(i));
		}
		
		List<String> rentRemainList = new ArrayList<String>();
		rentRemainList.addAll(rentList.subList(Math.max(startIndex, grace), rentList.size()));
		logger.info("调息后处理财务租金计划，处理非宽限期数据租金计划：(" + rentRemainList.size() + ")" + rentRemainList);
		BigDecimal corpusOverage = null;
		if(Math.max(startIndex, grace) == 0){
			corpusOverage = new BigDecimal(cb.getCleanLeaseMoney());
		}else{
			corpusOverage = new BigDecimal(corpusOverageFinacList.get(Math.max(startIndex, grace) - 1));//剩余本金
		}
		
		BigDecimal equipEndValue = new BigDecimal(cb.getEquipEndValue());	//期末余值
		BigDecimal corpusRemain = TransRateHelper.getCorpusRemain(corpusFinacList, Math.max(startIndex, grace));
		logger.info("调息后处理财务租金计划，处理非宽限期数据，起始期项：(" + Math.max(startIndex, grace) + ")本金余额：" + corpusRemain + "剩余本金：" + corpusOverage);
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		//2014-11-27:只有开始期数为第一期现金流才做期初处理
		if(cb.getPeriodType() != null && cb.getPeriodType().contains("1") && startIndex == 0){
			rentRemainList.set(0, new BigDecimal(rentRemainList.get(0)).subtract(corpusOverage).toString());
		} else {
			rentRemainList.add(0, BigDecimal.ZERO.subtract(corpusOverage).toString());
		}
		
		if(equipEndValue.compareTo(BigDecimal.ZERO)!=0){
			//期初时期末余值放到现金流新加最后一期
			if(cb.getPeriodType() != null && cb.getPeriodType().contains("1")){
				rentRemainList.add(equipEndValue.toString());
			}else{//期末时期末余值加到现金流最后一期
				rentRemainList.set(rentRemainList.size()-1,new BigDecimal(rentRemainList.get(rentRemainList.size()-1)).add(equipEndValue).toString());
			}
		}
		logger.info("调息后处理财务租金计划，需要修改的租金计划现金流：(" + rentRemainList.size() + ")" + rentRemainList);
		
		BigDecimal issueRate = IRRCalculateUtil.getIRR2(rentRemainList);
		logger.info("调息后处理财务租金计划，需要修改的租金计划现金流IRR：(" + issueRate.toString() + ")");
		
		for (int i = Math.max(startIndex, grace); i < rentList.size(); i++) {
			BigDecimal rent = new BigDecimal(rentList.get(i));
			BigDecimal newFinacInterest = corpusOverage.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if(i == grace && cb.getPeriodType() != null && cb.getPeriodType().contains("1") && i == 0){
				logger.info("调息后处理财务租金计划：期初首期调息，利息为0，本金=租金。");
				newFinacInterest = BigDecimal.ZERO;
			}
			BigDecimal newFinacCorpus = rent.subtract(newFinacInterest);
			
			corpusRemain = corpusRemain.subtract(newFinacCorpus);
			
			if(i == (rentList.size() - 1)){
				newFinacCorpus = newFinacCorpus.add(corpusRemain);
				newFinacInterest = rent.subtract(newFinacCorpus);
				corpusRemain = BigDecimal.ZERO;
			}
			corpusOverage = corpusOverage.subtract(newFinacCorpus);
			logger.info("调息后处理财务租金计划，处理租金计划：(" + rentList.size() + ")-(" + (i + 1) + ")");
			logger.info("调息后处理财务租金计划，处理租金计划：(" + newFinacCorpus + ")("+ newFinacInterest + ")(" + corpusRemain + ")");
			
			interestFinacList.set(i, newFinacInterest.toString());
			corpusFinacList.set(i, newFinacCorpus.toString());
			
			corpusOverageFinacList.set(i, corpusOverage.toString());	
						
			logger.info("调息后处理财务租金计划，新租金列表: (" + rentList.size() + ")" + rentList);
			logger.info("调息后处理财务租金计划，新财务本金列表: (" + corpusFinacList.size() + ")" + corpusFinacList);
			logger.info("调息后处理财务租金计划，新财务利息列表: (" + interestFinacList.size() + ")" + interestFinacList);
			logger.info("调息后处理财务租金计划，新财务本金余额列表: (" + corpusOverageFinacList.size() + ")" + corpusOverageFinacList);
		}
	}
	

	/**
	 * 调息开始的第一期新增的利息值
	 * 
	 * @param cb
	 * @param icb
	 * @param tcb
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal calFirstNewAddInterest(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
		logger.info("第一期合同租金计划特别构建...");
		CalculationConditionImpl condition = new CalculationConditionImpl();
		condition.copyConditionBeanValues(cb);
		
		int startList = icb.getStartList();

		// 本期计息本金
		BigDecimal corpusOverage = condition.getLeaseAmt();// 默认第一期
		List<String> corpusList=oldRentPlanContext.getCorpusBusinessList();
		for(int i=0;i<startList-1;i++){
			corpusOverage=corpusOverage.subtract(new BigDecimal(corpusList.get(i)));
		}

		// 新利率适应的天数
		String currentIssueStartDate = icb.getAdjustDate();
		String currentIssueEndDate = oldRentPlanContext.getPlanDateList().get(startList - 1);
		long days = RentCalculateUtil.getDateDiff(currentIssueStartDate, currentIssueEndDate);
		
		// 计算利率变化后变化的利息
		FundPlanDAOImpl icdi = new FundPlanDAOImpl();
		String lastYearRate = icdi.findLastTranRateYearRate(cb.getProjId(), startList);
		lastYearRate = (lastYearRate == null ? cb.getYearRate() : lastYearRate);
		BigDecimal oldDayRate = condition.getDayRate(new BigDecimal(lastYearRate)).divide(new BigDecimal(100),20,BigDecimal.ROUND_HALF_EVEN);
		BigDecimal newDayRate = condition.getDayRate(new BigDecimal(icb.getNewYearRate())).divide(new BigDecimal(100),20,BigDecimal.ROUND_HALF_EVEN);
		BigDecimal newAddInterest = corpusOverage.multiply(newDayRate.subtract(oldDayRate)).multiply(new BigDecimal(days));
		
		return newAddInterest;
	}

	/**
	 * 获取合同适用的央行基准利率
	 * 
	 * @param cb
	 * @param adjustInfo
	 * @return
	 */
	public static String getBaseRateApplyCondition(ConditionBean cb, Hashtable<String, String> adjustInfo) {
		int leaseTerm = cb.getLeaseTerm();

		String PBOCBaseRate = "0";
		if (leaseTerm <= 6) {// 六个月以内
			PBOCBaseRate = adjustInfo.get("base_rate_half").toString();
		} else if (leaseTerm <= 12) {// 一年以内
			PBOCBaseRate = adjustInfo.get("base_rate_one").toString();
		} else if (leaseTerm <= 36) {// 二年到三年之间时
			PBOCBaseRate = adjustInfo.get("base_rate_three").toString();
		} else if (leaseTerm <= 60) {// 四，五年之间时
			PBOCBaseRate = adjustInfo.get("base_rate_five").toString();
		} else {// 五年以上时
			PBOCBaseRate = adjustInfo.get("base_rate_abovefive").toString();
		}
		return PBOCBaseRate;
	}
	
	
	/**
	 * 获取合同适用的央行基准利率的浮动值（比上一次基准利率的差值）
	 * 
	 * @param cb
	 * @param adjustInfo
	 * @return
	 */
	public static String getFloatRateApplyCondition(ConditionBean cb, Hashtable<String, String> adjustInfo) {
		int leaseTerm = cb.getLeaseTerm();

		String PBOCBaseRate = "0";
		if (leaseTerm <= 6) {// 六个月以内
			PBOCBaseRate = adjustInfo.get("rate_half").toString();
		} else if (leaseTerm <= 12) {// 一年以内
			PBOCBaseRate = adjustInfo.get("rate_one").toString();
		} else if (leaseTerm <= 36) {// 二年到三年之间时
			PBOCBaseRate = adjustInfo.get("rate_three").toString();
		} else if (leaseTerm <= 60) {// 四，五年之间时
			PBOCBaseRate = adjustInfo.get("rate_five").toString();
		} else {// 五年以上时
			PBOCBaseRate = adjustInfo.get("rate_abovefive").toString();
		}
		return PBOCBaseRate;
	}
	
	/**
	 * 把一个本金列表中的值加起来，负值减掉（=加绝对值）
	 * 
	 * @param corpusList
	 * @param startIndex
	 * @return
	 */
	public static BigDecimal getCorpusRemain(List<String> corpusList, int startIndex) {
		BigDecimal result = BigDecimal.ZERO;
		for (int i = startIndex; i < corpusList.size(); i++) {
			BigDecimal corpus = new BigDecimal(corpusList.get(i));
			result = result.add(corpus.abs());
		}
		return result;
	}
	
	public static void setCashDetailsByEndValue(List<CashDetailsBean> cdbList, ConditionBean cb){
		BigDecimal equipEndValue = new BigDecimal(cb.getEquipEndValue());
		if(equipEndValue.compareTo(BigDecimal.ZERO)==1){//期未余值先默认加到最后一期
			CashDetailsBean cdb = cdbList.get(cdbList.size() - 1);
			cdb.setFundIn(equipEndValue.add(new BigDecimal(cdb.getFundIn())).toString());
			cdb.setFundInDetails(cdb.getFundInDetails() + "期末余值：" + equipEndValue.toString() + ";");
			cdb.setNetFlow(equipEndValue.add(new BigDecimal(cdb.getNetFlow())).toString());
		}
	}
	
	/*
	 * 根据央行调息记录分段计算调息第一期利息
	 * 该期计划日期：2015-12-05，上期计划日期：2015-09-05，上期年利率：7,剩余本金：100000，年计息天数：360
	 * 央行调息日期及调息后年利率(若该期内存在两次调息):【start_date:2015-10-05,year_rate:6】、【start_date:2015-11-05,year_rate:5】
	 * 分段后三次计息天数分别为：【2015-10-05 - 2015-09-05】=30，【2015-11-05 - 2015-10-05】=31，【2015-12-05 - 2015-11-05】=30
	 * newInterest = 100000*30*7/3600 + 100000*31*6/3600 + 100000*30*5/3600
	 */
	public static BigDecimal calFirstTransInterest(ConditionBean cb, FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception{
		BigDecimal interest = BigDecimal.ZERO;
		BigDecimal tmpInterest = BigDecimal.ZERO;
		int startList = icb.getStartList();	//调息开始期数
		BigDecimal corpusOverage = new BigDecimal(oldRentPlanContext.getCorpusOverageBusinessList().get(startList-1));	//该期剩余本金
		String yearRate = "";
		String startDate = "";
		String endDate = oldRentPlanContext.getPlanDateList().get(startList-1);
		long days = 0;
		BigDecimal dayRate = BigDecimal.ZERO;
		BigDecimal hundred = new BigDecimal("100");
		String evenCorpusRateType = cb.getEvenCorpusRateType();
		evenCorpusRateType = evenCorpusRateType==null ? "" : evenCorpusRateType;
		
		if(startList>1){
			yearRate = oldRentPlanContext.getYearRateList().get(startList - 2);	//取上期年利率作为该期原利率
			startDate = oldRentPlanContext.getPlanDateList().get(startList-2);
		}else{
			yearRate = cb.getYearRate();
			startDate = cb.getStartDate();
		}
		FundPlanDAOImpl fundPlanDAO = new FundPlanDAOImpl();
		// 根据本期利息计算日期区间获得的央行顺序调息记录
		List<Hashtable<String, String>> PBOCInfos = fundPlanDAO.getStandInfoByDateRange(startDate, endDate);
		
		if(PBOCInfos==null || PBOCInfos.size()==0){//无调息记录返回原来的利息
			interest = new BigDecimal(oldRentPlanContext.getInterestBusinessList().get(startList-1));
			return interest;
		}

		/*根据央行调息记录及该期日期区间和老利率分段计算利息。
		 * 该期计划日期：2015-12-05，上期计划日期：2015-09-05，上期年利率：7,剩余本金：100000，年计息天数：360
		 * 央行调息日期及调息后年利率(若该期内存在两次调息):【start_date:2015-10-05,year_rate:6】、【start_date:2015-11-05,year_rate:5】
		 * 分段后三次计息天数分别为：【2015-10-05 - 2015-09-05】=30，【2015-11-05 - 2015-10-05】=31，【2015-12-05 - 2015-11-05】=30
		 * newInterest = 100000*30*7/3600 + 100000*31*6/3600 + 100000*30*5/3600
		 */
		for(int i=0; i<PBOCInfos.size(); i++){
			Hashtable<String, String> PBOCInfo = PBOCInfos.get(i);
			days = RentCalculateUtil.getDateDiff(startDate, DateTools.getMonthFirstDay(PBOCInfo.get("start_date")));
			dayRate = RateTools.getPreDayRate(yearRate, evenCorpusRateType);
			tmpInterest = corpusOverage.multiply(dayRate).multiply(new BigDecimal(days)).divide(hundred, Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
			interest = interest.add(tmpInterest);
			logger.info("分段计息：剩余本金="+corpusOverage+",天数="+days+",日利率="+dayRate+",年利率="+yearRate);
			yearRate = TransRateHelper.getNewRate(cb, yearRate, PBOCInfo);//获得下次计算的年利率
			startDate = DateTools.getMonthFirstDay(PBOCInfo.get("start_date"));//本次的结束日期作为下次的开始日期
		}
		days = RentCalculateUtil.getDateDiff(startDate, endDate);
		dayRate = RateTools.getPreDayRate(yearRate, evenCorpusRateType);
		tmpInterest = corpusOverage.multiply(dayRate).multiply(new BigDecimal(days)).divide(hundred, Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
		interest = interest.add(tmpInterest);
		logger.info("分段计息：剩余本金="+corpusOverage+",天数="+days+",日利率="+dayRate+",年利率="+yearRate);
		
		interest = interest.setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
		logger.info("分段计息="+interest);
		return interest;
	}
//	public static Object calFirsInterestByDayRate(ConditionBean cb,
//			FundRentPlanBean oldRentPlanContext, InterContBean icb) throws Exception {
//		BigDecimal newInterest = BigDecimal.ZERO;
//		CalculationConditionImpl condition = new CalculationConditionImpl();
//		condition.copyConditionBeanValues(cb);
//		
//		int startList = icb.getStartList();
//
//		int periodType = condition.getPeriodType();
//		logger.info("periodType"+periodType);
//		if(startList==1 && periodType==0){//第一期且期初时，利息为0
//			return newInterest;
//		}
//		// 本期计息本金
//		String corpusOverage = condition.getLeaseAmt().toString();// 默认第一期
//		if (startList >= 2 && !cb.getSettleMethod().equals("even_interest")) {// 第二期以后的且非均息法
//			corpusOverage = oldRentPlanContext.getCorpusOverageBusinessList().get(startList - 2);
//		}
//
//		// 原利率适应的天数
//		String currentIssueStartDate = sdf.format(condition.getStartDate());//前一期计划日期
//		String currentIssueEndDate = oldRentPlanContext.getPlanDateList().get(startList - 1);
//		if (startList >= 2 ) {
//			currentIssueStartDate = oldRentPlanContext.getPlanDateList().get(startList - 2 );
//		}
//		
//		long days = RentCalculateUtil.getDateDiff(currentIssueStartDate, currentIssueEndDate);
//		
//		// 计算利率变化后变化的利息
//		//FundPlanDAOImpl icdi = new FundPlanDAOImpl();
//		//String lastYearRate = icdi.findLastTranRateYearRate(cb.getProjId(), startList);
//		//lastYearRate = (lastYearRate == null ? cb.getYearRate() : lastYearRate);
//		//上面注释掉是因为icdi.findLastTranRateYearRate(cb.getProjId(), startList)==null才会调用这个方法：calFirsInterestByDayRate
//		//String lastYearRate = cb.getYearRate();
//		String lastYearRate = oldRentPlanContext.getYearRateList().get(startList - 1);
//		BigDecimal oldDayRate = condition.getDayRate(new BigDecimal(lastYearRate)).divide(new BigDecimal("100"),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN);
//		logger.info("corpusOverage:"+corpusOverage +",oldDayRate:"+oldDayRate +",days:"+days );
//		//用日利率计算该期利息
//		newInterest = new BigDecimal(corpusOverage).multiply(oldDayRate).multiply(new BigDecimal(days));
//		
//		return newInterest;
//	}
	public static List<String> getNewDateByAdjustType(String adjustType, List<Map<String,String>> adjustDates) throws Exception {
		List<String> sdate = new ArrayList<String>();
		if ("next_year".equals(adjustType)) {
			for(Map<String,String> map:adjustDates){
				sdate.add(DateTools.getYearFirstDay(map.get("start_date")));
			}
		}
		if ("next_month".equals(adjustType)) {
			for(Map<String,String> map:adjustDates){
				sdate.add(DateTools.getMonthFirstDay(map.get("start_date")));
			}
		}
		if ("next_list".equals(adjustType)) {
			for(Map<String,String> map:adjustDates){
				sdate.add(map.get("start_date"));
			}
		}
		return sdate;
	}
//	public static List<String> getNewRate(ConditionBean condition, String oldYearRate, List<Map<String, String>> adjustList) {
//		List<String> newRate = new ArrayList<String>();
//		
//		if ("proportion".equals(condition.getRateFloatType())) {// 按央行利率浮动时
//			newRate = getRateByProportion(condition.getLeaseTerm(), adjustList, condition.getRateFloatAmt());
//		} else if ("add".equals(condition.getRateFloatType())) {// 按央利率加点时
//			newRate = getRateByPoint(condition.getLeaseTerm(), adjustList, condition.getRateFloatAmt());
//		}
//		return newRate;
//	}
	public static List<String> getRateByProportion(int leaseTerm, List<Map<String, String>> adjustList, String adjustPoint) {
		List<String> newYearRateList=new ArrayList<String>();
		
		String rateBase = "0";// 基准利率
		
		int leaseTermValue = leaseTerm;
		for(Map<String, String> adjust:adjustList){
			if (leaseTermValue <= 6) {// 六个月以内
				rateBase = adjust.get("base_rate_half").toString();
			} else if (leaseTermValue <= 12) {// 一年以内
				rateBase = adjust.get("base_rate_one").toString();
			} else if (leaseTermValue <= 36) {// 二年到三年之间时
				rateBase = adjust.get("base_rate_three").toString();
			} else if (leaseTermValue <= 60) {// 四，五年之间时
				rateBase = adjust.get("base_rate_five").toString();
			} else {// 五年以上时
				rateBase = adjust.get("base_rate_abovefive").toString();
			}
			// 新年利率 = 交易结构利率调整值*调息后基准利率。
			BigDecimal adjustPointValue = new BigDecimal(adjustPoint);
			adjustPointValue=adjustPointValue.add( new BigDecimal(1));
			BigDecimal rateBaseValue = new BigDecimal(rateBase);
			BigDecimal newYearRate = adjustPointValue.multiply(rateBaseValue).setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
			newYearRateList.add(newYearRate.toString());
		}
		return newYearRateList;
	}
	public static List<String> getRateByPoint(int leaseTerm, List<Map<String, String>> adjustList, String adjustPoint) {
		List<String> newYearRateList=new ArrayList<String>();
		int leaseTermValue = leaseTerm;
		String rateBase = "0";// 基准利率
		for(Map<String, String> adjust:adjustList){
			if (leaseTermValue <= 6) {// 六个月以内
				rateBase = adjust.get("base_rate_half").toString();
			} else if (leaseTermValue <= 12) {// 一年以内
				rateBase = adjust.get("base_rate_one").toString();
			} else if (leaseTermValue <= 36) {// 二年到三年之间时
				rateBase = adjust.get("base_rate_three").toString();
			} else if (leaseTermValue <= 60) {// 四，五年之间时
				rateBase = adjust.get("base_rate_five").toString();
			} else {// 五年以上时
				rateBase = adjust.get("base_rate_abovefive").toString();
			}
			// 新年利率 = 交易结构利率调整值+调息后基准利率。
			BigDecimal adjustPointValue = new BigDecimal(adjustPoint);
			BigDecimal rateBaseValue = new BigDecimal(rateBase);
			BigDecimal newYearRate = adjustPointValue.add(rateBaseValue).setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP);
			newYearRateList.add(newYearRate.toString());
		}
		
		
		// 返回的是%之多少
		return newYearRateList;
	}
	public static List<String> getNewRate(ConditionBean cb, FundStandardInterest fsi,List<ContractSpecialRulesBean> srbList) 
	{
		List<String> newRate = new ArrayList<String>();
		if ("proportion".equals(cb.getRateFloatType())) {// 按央行利率浮动时
			newRate = getRateByProportion(cb.getLeaseTerm(), fsi, srbList);
		} else if ("add".equals(cb.getRateFloatType())) {// 按央利率加点时
			newRate = getRateByPoint(cb.getLeaseTerm(), fsi, srbList);
		}
		return newRate;
	}
	private static List<String> getRateByPoint(int leaseTerm,FundStandardInterest fsi, List<ContractSpecialRulesBean> srbList) {
		List<String> newYearRateList=new ArrayList<String>();
		int leaseTermValue = leaseTerm;
		String rateBase = "0";// 基准利率
		for(ContractSpecialRulesBean srb:srbList){
			if (leaseTermValue <= 6) {// 六个月以内
				rateBase = fsi.getBaseRateHalf().toString();
			} else if (leaseTermValue <= 12) {// 一年以内
				rateBase = fsi.getBaseRateOne().toString();
			} else if (leaseTermValue <= 36) {// 二年到三年之间时
				rateBase = fsi.getBaseRateThree().toString();
			} else if (leaseTermValue <= 60) {// 四，五年之间时
				rateBase = fsi.getBaseRateFive().toString();
			} else {// 五年以上时
				rateBase = fsi.getBaseRateAbovefive().toString();
			}
			// 新年利率 = 交易结构利率调整值+调息后基准利率。
			BigDecimal adjustPointValue = new BigDecimal(srb.getRateFloatAmt().toString());
			BigDecimal rateBaseValue = new BigDecimal(rateBase);
			BigDecimal newYearRate = adjustPointValue.add(rateBaseValue).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
			newYearRateList.add(newYearRate.toString());
		}
		
		
		// 返回的是%之多少
		return newYearRateList;
	}
	private static List<String> getRateByProportion(int leaseTerm,FundStandardInterest fsi, List<ContractSpecialRulesBean> srbList) {
List<String> newYearRateList=new ArrayList<String>();
		
		String rateBase = "0";// 基准利率
		
		int leaseTermValue = leaseTerm;
		for(ContractSpecialRulesBean srb:srbList){
			if (leaseTermValue <= 6) {// 六个月以内
				rateBase = fsi.getBaseRateHalf().toString();
			} else if (leaseTermValue <= 12) {// 一年以内
				rateBase = fsi.getBaseRateOne().toString();
			} else if (leaseTermValue <= 36) {// 二年到三年之间时
				rateBase = fsi.getBaseRateThree().toString();
			} else if (leaseTermValue <= 60) {// 四，五年之间时
				rateBase = fsi.getBaseRateFive().toString();
			} else {// 五年以上时
				rateBase = fsi.getBaseRateAbovefive().toString();
			}
			// 新年利率 = 交易结构利率调整值*调息后基准利率。
			BigDecimal adjustPointValue = new BigDecimal(srb.getRateFloatAmt().toString());
			adjustPointValue=adjustPointValue.add( new BigDecimal(1));
			BigDecimal rateBaseValue = new BigDecimal(rateBase);
			BigDecimal newYearRate = adjustPointValue.multiply(rateBaseValue).setScale(Scale.GENERAL_RATE, BigDecimal.ROUND_HALF_UP);
			newYearRateList.add(newYearRate.toString());
		}
		return newYearRateList;
	}
	public static List<String> getNewRentListForEvenRentForSpecial(ConditionBean cb,  List<String> oldRentList,int startIndex, String corpusRemain, SpecialRuleBean srb, String preRate) {
		//剩余租赁期限(计算每期租金要用PMT公式)
		int remainLeaseTerm=cb.getRemainLeaseTerm();
		if(startIndex+1>=srb.getStartList()||startIndex+1<=srb.getEndList()){
			remainLeaseTerm=remainLeaseTerm-(startIndex+1-srb.getStartList())*Integer.parseInt(srb.getRegular_months());
		}
		
		String newrent = RentTools.getPMTForSpecial(preRate.toString(), new BigDecimal(remainLeaseTerm).divide(new BigDecimal(srb.getRegular_months()),Scale.GENERAL_RATE,BigDecimal.ROUND_HALF_UP)+ "", "-" + corpusRemain, startIndex==0?cb.getEquipEndValue():"0", cb.getPeriodType());
		BigDecimal rent=new BigDecimal(newrent).setScale(Scale.RENT_SCALE, BigDecimal.ROUND_HALF_EVEN);
		//如果起始期次小于调息开始期次，则从调息开始期次更改租金
		for (int i = srb.getStartList()<startIndex+1?startIndex+1:srb.getStartList(); i <= srb.getEndList(); i++) {
			oldRentList.set(i-1, rent.toString());
		}
		return oldRentList;
	}
}
