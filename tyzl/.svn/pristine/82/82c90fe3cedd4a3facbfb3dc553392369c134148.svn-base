package com.reckon.rentcalc.service.impl.pub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.SpecialRulesBean;
import com.reckon.calculation.utils.Scale;
import com.reckon.util.InterestTools;
import com.reckon.util.RateTools;
import com.reckon.util.RentTools;
import com.reckon.util.tools.NumTools;
import com.tenwa.leasing.utils.LeasingException;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-3-3
 * @desc  ( 租金测算利息计算类,如有宽限期，延迟期等利息特殊算法也在此类中重构方法来实现)
 */
public class InterestCalServiceImpl {

	private static Logger logger = Logger.getLogger(InterestCalServiceImpl.class);

	/**
	 * 
	 *  (正常租金测算 获得 利息list列表)
	 * 
	 * @param rent_list
	 * @param leaseMoney
	 * @param calRate
	 * @param periodType
	 * @param sgrace
	 * @param Income_number_year
	 * @param endValue
	 * @return
	 */
	public List<String> getInterestList(List<String> rent_list, String leaseMoney, String calRate, String periodType, int sgrace, String Income_number_year, String endValue) {

		// 第一期的特别处理，包括期初期末等利息的特别处理

		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "0";
		String corpus = "0";
		String corpus_overage = "0";
		// 本金余额,
		corpus_overage = new BigDecimal(leaseMoney).toString();

		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		// logger.debug("合同租金计划利率:"+calRate);
		String preRate = RateTools.getPreRate(calRate, Income_number_year);
		// logger.debug("合同租金计划利率:"+preRate);
		// 得到宽限期利息
		int grace = getGraceInterest(sgrace, rent_list, interests);

		for (int i = grace; i < rent_list.size(); i++) {// 循环租金list
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(periodType) && i == grace) {// 第一期且是期初时

				// 第一期利息计算，如有特别的计算则可在此写方法
				// firstCorpusAndInteCal(corpus, inte,
				// rent_list.get(i).toString());
				// 期初时第一期本金的精度和租金的精度是一样的
				corpus = new BigDecimal(rent_list.get(i).toString()).setScale(RentTools.getRentAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
				inte = "0";

			} else {
				
				logger.debug("i:"+i+"<---->"+preRate);
				// 利息
				inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(preRate)).toString();// 剩余本金*利率

				// 本金, 租金-利息
				corpus = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(inte).setScale(2,BigDecimal.ROUND_HALF_UP)).toString();// 租金-利息
			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rent_list.size() - 1) {
				// 本金 --总的本金-以前的本金和-期末残值
				corpus = new BigDecimal(leaseMoney).subtract(new BigDecimal(corpus_total)).subtract(new BigDecimal(endValue)).toString();

				// 2011-06-27修改
				// corpus = new BigDecimal(leaseMoney).subtract(new
				// BigDecimal(corpus_total)).toString();
				// .subtract(new BigDecimal(endValue)).toString();

				// 利息
				inte = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(corpus)).toString();

			}
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(new BigDecimal(corpus)).toString();

			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus)).toString();

			// 添加list
			interests.add(inte);
			// logger.debug("合同第"+i+"利息:"+inte);
		}

		return interests;
	}

	/**
	 * 
	 *  ( 租金调整下的利息测算方法)
	 * 
	 * @param rent_list
	 *            租金列表
	 * @param leaseMoney
	 *            最原始的测算本金,如果宽限期不调整则和corpusCon相同
	 * @param corpusCon
	 *            宽限期调整过之后的测算总本金
	 * @param year_rate
	 *            年利率集合
	 * @param periodType
	 *            期初/期末
	 * @param sgrace
	 *            宽限期
	 * @param Income_number_year
	 *            年还款次数
	 * @param endValue
	 *            期末残值
	 * @param rentAdjustList<String>
	 *            调整租金
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getInterestList(List<String> rent_list, String leaseMoney, String corpusCon, List<String> year_rate, String periodType, int sgrace, String Income_number_year, String endValue, String[] rentAdjustList) {

		// 第一期的特别处理，包括期初期末等利息的特别处理

		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "0";
		String corpus = "0";
		String corpus_overage = "0";
		// 本金余额,
		corpus_overage = new BigDecimal(corpusCon).toString();// 9950

		// 1.根据年利率等信息，
		// 中的getPreRate得到测算的租金利率
		String preRate = "";// RateTools.getPreRate(calRate,
							// Income_number_year);

		// 得到宽限期利息
		int grace = sgrace;
		interests = getGraceInterest(year_rate, leaseMoney, Arrays.asList(rentAdjustList), grace, Income_number_year);
		leaseMoney = corpus_overage;// 9950
		for (int i = grace; i < rent_list.size(); i++) {// 循环租金list
			preRate = RateTools.getPreRate(year_rate.get(i), Income_number_year);
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(periodType) && i == grace) {// 第一期且是期初时

				// 第一期利息计算，如有特别的计算则可在此写方法
				// firstCorpusAndInteCal(corpus, inte,
				// rent_list.get(i).toString());

				corpus = rent_list.get(i).toString();
				inte = "0";

			} else {

				// 利息
				inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(preRate)).toString();// 剩余本金*利率

				// 本金, 租金-利息
				corpus = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(inte)).toString();// 租金-利息
			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rent_list.size() - 1) {
				// 本金 --总的本金-以前的本金和-期末残值
				corpus = new BigDecimal(leaseMoney).subtract(new BigDecimal(corpus_total)).subtract(new BigDecimal(endValue)).toString();

				// 2011-06-27修改
				// corpus = new BigDecimal(leaseMoney).subtract(new
				// BigDecimal(corpus_total)).toString();
				// .subtract(new BigDecimal(endValue)).toString();

				// 利息
				inte = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(corpus)).toString();

			}
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(new BigDecimal(corpus)).toString();

			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus)).toString();

			// 添加list
			interests.add(inte);

		}

		return interests;
	}

	/**
	 * 
	 *   宽限期利息)
	 * 
	 * @param cb
	 * @param rent_list
	 * @param interests
	 * @return
	 */
	private int getGraceInterest(int sgrace, List<String> rent_list, List<String> interests) {
		// 判断宽限期,计算宽限期利息
		int grace = sgrace;
		for (int i = 0; i < sgrace; i++) {
			interests.add(rent_list.get(i));
		}
		return grace;
	}

	/**
	 * 
	 *  (租金调整下的宽限期利息方法)
	 * 
	 * @param cb
	 * @param l_rent_adjust
	 * @param grace
	 * @return
	 */
	private List<String> getGraceInterest(List<String> year_rate, String corpus, List<String> l_rent_adjust, int grace, String income_number_year) {
		List<String> devGrace = new ArrayList<String>();
		String preRate = "";
		// 因为宽限期不多,就每一期单独计算
		/*
		 * for(int i=0;i<grace;i++){ devGrace.add(new
		 * BigDecimal(corpus).multiply(new BigDecimal(preRate)).toString());
		 * if(!l_rent_adjust.get(i).toString().equals("")){//如果调整就要计算剩余本金
		 * 
		 * corpus=new BigDecimal(corpus).subtract(new
		 * BigDecimal(l_rent_adjust.get(i).toString()) .subtract(new
		 * BigDecimal(corpus).multiply(new BigDecimal(preRate)))).toString(); }
		 * } //cb.setCalTotalByCont(corpus);//宽限期之后的本金总值 return devGrace;
		 */
		// 2011-11-14 宽限期调整 不对后续测试有任何影响
		for (int i = 0; i < grace; i++) {
			preRate = RateTools.getPreRate(year_rate.get(i), income_number_year);
			if (l_rent_adjust.get(i).toString().equals("")) {
				devGrace.add(new BigDecimal(corpus).multiply(new BigDecimal(preRate)).toString());
			} else {
				devGrace.add(l_rent_adjust.get(i).toString());
			}
		}
		return devGrace;
	}

	/**
	 * 
	 *  (  得到财务利息列表)
	 * 
	 * @param cb
	 * @param rent_list
	 * @param leaseMoney
	 * @return
	 */
	public List<String> getFinacInterestList(ConditionBean cb, List<String> rent_list, String leaseMoney) {
		// [KEY:scl]2012-12-24 因为出现 手续费分摊在后面过高 所以采用 不包含宽限期的隐藏IRR做本息计算 所以在宽限期的时候
		// 不分摊手续费
		// String preRate = RateTools.getPreRate(cb.getPlan_irr(), cb
		// .getIncome_number_year());
//		String preRate = RateTools.getPreRate(cb.getNoGraceIRR(), cb.getIncomeNumberYear(), cb.getIfretry());
//		logger.info("财务利率：" + cb.getNoGraceIRR() + "总本金:" + leaseMoney + "计算利率：" + preRate);

		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "0";
		String corpus = "0";
		String corpus_overage = "0";
		// 本金余额
		corpus_overage = new BigDecimal(leaseMoney).toString();

		// [KEY:scl]2012-12-24 手续费分摊比例
		//注释cb.getGraceMultiply()
		BigDecimal rent_total = new BigDecimal(NumTools.getSumCorpusOverage(rent_list, 1)).multiply(new BigDecimal(0));
		for (int i = 0; i < rent_list.size(); i++) {// 循环租金list
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(cb.getPeriodType()) && i == 0) {// 第一期且是期初时
				// 第一期利息计算，如有特别的计算则可在此写方法调用
				corpus = rent_list.get(i).toString();
				inte = "0";
			} else {
				if (cb.getGrace() > i) {// 在会计租金计划中宽限期的利息就等于租金
					// 利息=租金+手续/总期数[key:scl]马经理调整的在宽限期中手续费分摊额为手续/总期数
					// inte=new BigDecimal(rent_list.get(i).toString()).add(
					// new BigDecimal(cb.getHandling_charge_money()).divide(
					// new BigDecimal(cb.getIncome_number()).add(new
					// BigDecimal(cb.getGrace())),
					// 20,BigDecimal.ROUND_HALF_UP)).setScale(2,
					// BigDecimal.ROUND_HALF_UP).toString();
					// [KEY:scl]2012-12-24 款期限的手续费分摊为 租金/总租金*手续费
					inte = new BigDecimal(rent_list.get(i).toString()).add(new BigDecimal(rent_list.get(i).toString()).divide(rent_total, 20, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(cb.getHandlingChargeMoney()))).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				} else {
					// 利息
					inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(0)).setScale(RentTools.getInterestAccuracy(), BigDecimal.ROUND_HALF_UP).toString();// 剩余本金*利率
				}
				// 本金, 租金-利息
				corpus = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(inte)).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();// 租金-利息
			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rent_list.size() - 1) {
				// 本金 --总的本金-以前的本金和-期末残值
				//注释1行 corpus = new BigDecimal(cb.getCalTotalByFinac()).subtract(new BigDecimal(corpus_total)).subtract(new BigDecimal(cb.getPmtEndValue())).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
				// logger.info("cb.getPmtEndValue():财务利息："+cb.getPmtEndValue());

				// 2011-06-27修改
				// corpus = new BigDecimal(leaseMoney).subtract(new
				// BigDecimal(corpus_total)).toString();
				// .subtract(new BigDecimal(endValue)).toString();

				// 利息
				inte = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(corpus)).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();

			}
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(new BigDecimal(corpus)).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();

			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus)).setScale(RentTools.getAccuracy(), BigDecimal.ROUND_HALF_UP).toString();

			// 添加list
			interests.add(inte);
		}

		return interests;
	}

	/**
	 * 2013-1-6 孙传良 在计算会计利息的时候 采用财务利息加手续费分摊值来计算 会计利息
	 * 
	 * @param cb
	 *            商务条件bean
	 * @param frpb
	 *            租金计划bean 本次测算出来的bean
	 * @param frpb_old
	 *            租金计划bean 数据库中bean 主要用于计算已分摊手续费
	 * @param startList
	 *            计算开始期项(调息和租金计划变更的话就是对于的开始期项,如果是租金测算就是1)
	 * @return 返回会计利息
	 */
	public List<String> getFinacInterestListNew(ConditionBean cb, FundRentPlanBean frpb, FundRentPlanBean frpb_old, int startList) {
		List<String> interest = new ArrayList<String>();
		BigDecimal handling_charge_money_total = new BigDecimal(cb.getHandlingChargeMoney());// 总手续费
		if (handling_charge_money_total.compareTo(BigDecimal.ZERO) == 0) {// 偷懒做法,如果没有手续费就返回财务利息
																			// 因为
																			// 财务利息和会计利息就差在手续费上
			interest = new ArrayList<String>(frpb.getInterestBusinessList());
		} else {// 开始计算手续费分摊值
			List<String> handling_charge_money_share = new ArrayList<String>();
			handling_charge_money_share = new InterestTools().getHandlingChargeMoneyShare(cb, frpb, frpb_old, startList);
			for (int i = 0; i < frpb.getInterestBusinessList().size(); i++) {
				interest.add(NumTools.calculationStr(frpb.getInterestBusinessList().get(i).toString(), handling_charge_money_share.get(i).toString(), NumTools.ADD, 2));
			}
		}
		return interest;
	}

	/**
	 * 
	 *  scl 不规则租金调整,根据利率List和rent_list算每期的利息
	 * 
	 * @param rent_list
	 *            租金集合
	 * @param year_rate_list
	 *            利率集合 需要转换为对应的租金间隔的利率
	 * @param leaseMoney
	 *            剩余本金
	 * @param calRate
	 *            精确小数位
	 * @param periodType
	 *            期初/期末
	 * @return interests 利息集合
	 */
	public List<String> getInterestList(List<String> rent_list, List<String> year_rate_list, String leaseMoney, String calRate, String periodType) {

		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "0";
		String corpus = "0";
		String corpus_overage = "0";
		// 本金余额,
		corpus_overage = new BigDecimal(leaseMoney).toString();

		for (int i = 0; i < rent_list.size(); i++) {// 循环租金list
			// 第一期的特别处理，包括期初期末等利息的特别处理
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(periodType) && i == 0) {// 第一期且是期初时

				// 第一期利息计算，如有特别的计算则可在此写方法
				// firstCorpusAndInteCal(corpus, inte,
				// rent_list.get(i).toString());

				corpus = rent_list.get(i).toString();
				inte = "0";

			} else {

				// 利息
				inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(year_rate_list.get(i).toString())).toString();// 剩余本金*利率

				// 本金, 租金-利息
				corpus = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(inte)).toString();// 租金-利息
			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rent_list.size() - 1) {
				// 本金 --总的本金-以前的本金和
				corpus = new BigDecimal(leaseMoney).subtract(new BigDecimal(corpus_total)).toString();

				// 利息
				inte = new BigDecimal(rent_list.get(i).toString()).subtract(new BigDecimal(corpus)).toString();

			}
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(new BigDecimal(corpus)).toString();

			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus)).toString();

			// 添加list
			interests.add(inte);

		}

		return interests;
	}

	/**
	 * scl  (  获得均息法下的利息列表 均息法 不区分期初期末的利息)
	 * 
	 * @param leaseMoney
	 *            总本金
	 * @param lease_term
	 *            租赁期限
	 * @param Income_number
	 *            还款次数
	 * @param endValue
	 *            剩余本金
	 * @param calRate
	 *            年利率
	 * @return
	 */
	public List<String> getInterestList(String leaseMoney, int leaseTerm, int incomeNumber, String endValue, String yearRate) {
		List<String> interestList = new ArrayList<String>();
		//本金*（期限/12）*（年利率/100）/还款次数
		//本金*期限*年利率/12/100/还款次数
		BigDecimal leaseAmt = new BigDecimal(leaseMoney);
		BigDecimal interest = leaseAmt.multiply(new BigDecimal(yearRate)).multiply(new BigDecimal(leaseTerm));
		interest = interest.divide(new BigDecimal(1200).multiply(new BigDecimal(incomeNumber)), Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
		for (int i = 0; i < incomeNumber; i++) {
			interestList.add(interest.toString());
		}
		logger.debug("本金总额" + leaseMoney + "租赁期限" + leaseTerm + "还款次数" + incomeNumber + "利息" + interest);
		return interestList;
	}
	/**
	 * 分段测算 已知本金比例
	 * @param corpus_list
	 * @param leaseMoney
	 * @param cb
	 * @param specialRulesBean
	 * @param calRate
	 * @param periodType
	 * @param sgrace
	 * @param Income_number_year
	 * @param endValue
	 * @param isRent
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getKnowingCorpusInterestList(List<String> corpus_list, String leaseMoney,ConditionBean cb, SpecialRulesBean  specialRulesBean,BigDecimal calRate, String periodType, int sgrace, String Income_number_year, String endValue,int ... str)  throws Exception {
		
		// 第一期的特别处理，包括期初期末等利息的特别处理
		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		// 该期的利息
		String inte = "0";
		String corpus_overage = "0";
		// 本金余额,
		corpus_overage = cb.getRemainCorpus().toString();
		int ss=null!=str?str[0]: specialRulesBean.getStartList();
		String preRate = calRate.divide(new BigDecimal("100"), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(specialRulesBean.getRegular_months())).divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).toString();
		String tmpRate=preRate;//期利率
		for (int i = ss,j=0; i <= specialRulesBean.getEndList(); i++,j++) {// 循环本金list
			if ("1".equals(periodType) && i == 1) {// 第一期且是期初时
				inte = "0";
			}else{
				// 利息
				inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(tmpRate)).setScale(RentTools.getInterestAccuracy(), BigDecimal.ROUND_HALF_UP).toString();// 剩余本金*利率
			}
			
			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus_list.get(j))).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
			
			// 添加list
			interests.add(inte);
		}
		if(new BigDecimal(corpus_overage).compareTo(BigDecimal.ZERO)<0){
			throw new LeasingException("测算失败：第"+specialRulesBean.getStartList()+"期到第"+specialRulesBean.getEndList()+"期分段规则设置不合理，本金比例过大！");
		}else{
			cb.setRemainCorpus(new BigDecimal(corpus_overage));
		}
		return interests;
	}
	/**
	 * 分段测算 
	 * @param rent_list
	 * @param leaseMoney
	 * @param cb
	 * @param specialRulesBean
	 * @param calRate
	 * @param periodType
	 * @param sgrace
	 * @param Income_number_year
	 * @param endValue
	 * @param isRent
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getInterestList(List<String> rent_list, String leaseMoney,ConditionBean cb, SpecialRulesBean  specialRulesBean,BigDecimal calRate, String periodType, int sgrace, String Income_number_year, String endValue,int...str) {
		
		// 第一期的特别处理，包括期初期末等利息的特别处理
		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "0";
		String corpus = "0";
		String corpus_overage = "0";
		// 本金余额,
		corpus_overage = cb.getRemainCorpus().toString();
		
		String preRate = calRate.divide(new BigDecimal("100"), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(specialRulesBean.getRegular_months())).divide(new BigDecimal(12),Scale.RATE_SCALE,BigDecimal.ROUND_HALF_EVEN).toString();
		// 得到宽限期利息
		String tmpRate=preRate;
		int startlist=specialRulesBean.getStartList();
		if(null!=str){
			if(str[0]>=startlist&&str[0]<=specialRulesBean.getEndList()){
				startlist=str[0];
			}
		}
		for (int i = startlist,j=0; i <= specialRulesBean.getEndList(); i++,j++) {// 循环租金list
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(periodType) && i == 1) {// 第一期且是期初时
				// 期初时第一期本金的精度和租金的精度是一样的
				corpus = new BigDecimal(rent_list.get(j).toString()).setScale(RentTools.getRentAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
				inte = "0";
				
			} else {
				
				logger.debug("i:"+i+"<---->"+tmpRate);
				// 利息
				inte = new BigDecimal(corpus_overage).multiply(new BigDecimal(tmpRate)).setScale(RentTools.getInterestAccuracy(), BigDecimal.ROUND_HALF_UP).toString();// 剩余本金*期利率
				// 本金, 租金-利息
				corpus = new BigDecimal(rent_list.get(j).toString().replaceAll(",", "")).subtract(new BigDecimal(inte).setScale(2,BigDecimal.ROUND_HALF_UP)).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP).toString();// 租金-利息
			}
			
			// 计算中的本金和
			corpus_total = new BigDecimal(corpus_total).add(new BigDecimal(corpus)).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
			
			// 本金余额
			corpus_overage = new BigDecimal(corpus_overage).subtract(new BigDecimal(corpus)).setScale(RentTools.getCorpusAccuracy(), BigDecimal.ROUND_HALF_UP).toString();
			
			// 添加list
			interests.add(inte);
			// logger.debug("合同第"+i+"利息:"+inte);
		}
		cb.setRemainCorpus(new BigDecimal(corpus_overage));
		return interests;
	}
}
