package com.reckon.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.util.tools.NumTools;


/**
 * 
 * @author SHIHONGFEI
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17
 * @desc ( 利息计算)
 */
public class InterestTools {
	private static Logger logger = Logger.getLogger(InterestTools.class);

	/**
	 * 
	 * @param rentList
	 *            租金list
	 * @param leaseMoney
	 *            要测算的本金
	 * @param calRate
	 *            计算的利率 qzOrqm 期初还是期未
	 * @return
	 */

	/**
	 * 租金利息算法
	 * 
	 * @Title: getInterestList
	 * @Description:
	 * @param
	 * @param rentList租金List
	 * @param
	 * @param leaseMoney总测算本金
	 * @param
	 * @param calRate测算利率
	 * @param
	 * @param qzOrqm期初或者期末
	 * @param
	 * @return
	 * @return List利息list
	 * @throws
	 */
	public List<String> getInterestList(List<String> rentList, String leaseMoney, String calRate, String qzOrqm) {

		logger.info("传过来计算利率：" + calRate);
		// 用于保留利息
		List<String> interests = new ArrayList<String>();
		String corpus_total = "0";
		// 该期的利息
		String inte = "";
		String corpus = "";
		String corpus_overage = "";
		// 本金余额
		corpus_overage = NumTools.formatNumberDoubleScale(leaseMoney, 2);

		for (int i = 0; i < rentList.size(); i++) {// 循环租金list
			//起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if ("1".equals(qzOrqm) && i == 0) {// 第一期且是期初时
				corpus = rentList.get(i).toString();
				inte = "0";
			} else {
				// 利息
				inte = String.valueOf(Double.parseDouble(corpus_overage) * Double.parseDouble(calRate));// 剩余本金*利率
				inte = NumTools.formatNumberDoubleScale(inte, 2);
				// 本金
				corpus = String.valueOf(Double.parseDouble(rentList.get(i).toString()) - Double.parseDouble(inte));// 租金-利息
				corpus = NumTools.formatNumberDoubleScale(corpus, 2);

			}

			// 最后一期的利息=剩余的利息总合,本金仍然=租金-利息
			if (i == rentList.size() - 1) {
				// 本金 --总的本金-以前的本金和
				corpus = String.valueOf(Double.parseDouble(leaseMoney) - Double.parseDouble(corpus_total));
				corpus = NumTools.formatNumberDoubleScale(corpus, 2);

				inte = String.valueOf(Double.parseDouble(rentList.get(i).toString()) - Double.parseDouble(corpus));
				inte = NumTools.formatNumberDoubleScale(inte, 2);

			}

			corpus_total = String.valueOf(Double.parseDouble(corpus_total) + Double.parseDouble(corpus));
			corpus_total = NumTools.formatNumberDoubleScale(corpus_total, 2);

			// 本金余额
			corpus_overage = String.valueOf(Double.parseDouble(corpus_overage) - Double.parseDouble(corpus));
			corpus_overage = NumTools.formatNumberDoubleScale(corpus_overage, 2);
			// 添加list
			interests.add(inte);

		}

		return interests;
	}

	/**
	 * 根据传入参数 先按照frpb_old中的数据计算出在本次计算前的手续费分摊情况 然后按照frpb的数据再重新分摊剩余的手续费
	 * 
	 * @param cb
	 *            商务条件
	 * @param frpb
	 *            本次计算出来的租金计划数据
	 * @param frpb_old
	 *            本次计算前的租金计划数据
	 * @param startList
	 *            本次计算租金的开始期项
	 * @return 返回全部的手续分摊情况
	 */
	public List<String> getHandlingChargeMoneyShare(ConditionBean cb, FundRentPlanBean frpb, FundRentPlanBean frpb_old, int startList) {
		List<String> hand_interest_share = new ArrayList<String>();
		List<String> corpus_overage = new ArrayList<String>();
		BigDecimal handling_charge_money_total = new BigDecimal(cb.getHandlingChargeMoney());// 总手续费
		//注释 new BigDecimal(cb.getCalTotalByCont())
		BigDecimal sum_corpus = new BigDecimal(0);
		if (handling_charge_money_total.compareTo(BigDecimal.ZERO) == 0) {// 偷懒做法,如果没有手续费就返回0
			logger.debug("需要分摊的手续费为0");
			for (int i = 0; i < corpus_overage.size(); i++) {
				hand_interest_share.add("0");
			}
		} else {// 开始计算手续费分摊值
			//起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			int period_type = Integer.parseInt(cb.getPeriodType());// 期初/期末
			int grace = cb.getGrace();// 宽限期
			// 按照原有的租金计划计算本次手续费分摊,只有在不是第一次测算时才有原租金计划 所以需要判断
			if (frpb_old != null && frpb_old.getCorpusOverageBusinessList().size() > 0) {
				corpus_overage = new ArrayList<String>(frpb_old.getCorpusOverageBusinessList());
			} else {
				corpus_overage = new ArrayList<String>(frpb.getCorpusOverageBusinessList());
			}
			// 先按照原租金计划计算出手续费分摊值,才知道本次的
			hand_interest_share = InterestTools.getHandlingChargeMoneyShare(handling_charge_money_total, sum_corpus, corpus_overage, grace, period_type);
			if (startList != 1) {// 如果不是第一期开始的就需要计算剩余的重新分摊
				// 根据开始期项计算出需要分摊的手续费(因为变更和调息时之前的分摊不能动)
				BigDecimal share_handling_charge_money = new BigDecimal(NumTools.getSumCorpusOverage(hand_interest_share, startList));// 需要分摊的剩余手续费
				// 根据开始期数计算宽限期
				if (startList > grace) {// 如果开始期数在宽限期之后那就直接设置宽限期没有
					if (startList - grace != 1) {
						period_type = 0;// 如果不在宽限期内,且不是正常租金计划第一期的时候都认为是期末
					}
					grace = 0;
				} else {
					grace = grace - startList;
				}
				// 计算新的剩余本金 从开始期数计算剩余本金
				corpus_overage = new ArrayList<String>(frpb.getCorpusOverageBusinessList());
				for (int i = 0; i < startList; i++) {
					corpus_overage.remove(0);
				}
				// 从开始期数计算本金总和
				sum_corpus = new BigDecimal(NumTools.getSumCorpusOverage(frpb.getCorpusBusinessList(), startList));
				// 按照新的条件计算手续费分摊数据
				List<String> share_hand_interest_temp = InterestTools.getHandlingChargeMoneyShare(share_handling_charge_money, sum_corpus, corpus_overage, grace, period_type);
				// 移除掉原手续费分摊的开始期项后的数据并加入新计算的数据
				for (int i = 0; i < share_hand_interest_temp.size(); i++) {
					hand_interest_share.set(startList - 1 + i, share_hand_interest_temp.get(i));
				}
			}
		}
		return hand_interest_share;
	}

	/**
	 * 2013-1-6 孙传良 手续费分摊算法 <Br>
	 * 根据上一期剩余本金占所有剩余本金总和的比例计算当期手续费<Br>
	 * 如果是期初那么第一期不分摊手续费(因为在第一期的时候没有利息收入所以不分摊手续费)<Br>
	 * 如果手续费为0,则返回的每期手续费分摊为0
	 * 
	 * @param hand_change_money
	 * @param sum_corpus
	 * @param corpus_overage
	 * @param grace
	 * @param period_type
	 * @return
	 */
	public static List<String> getHandlingChargeMoneyShare(BigDecimal hand_change_money, BigDecimal sum_corpus, List<String> corpus_overage, int grace, int period_type) {
		List<String> interest = new ArrayList<String>();
		List<String> corpus_overage_temp = new ArrayList<String>(corpus_overage);
		// 如果是期初就把期初的那一期的剩余本金移除
		if (period_type == 1) {
			corpus_overage_temp.remove(grace);
		}
		// 把总本金作为第一期的剩余本金
		corpus_overage_temp.add(0, sum_corpus.toString());
		// 移除最后一期为0的剩余本金
		corpus_overage_temp.remove(corpus_overage_temp.size() - 1);
		// 计算剩余本金总和
		BigDecimal sum_corpus_overage = new BigDecimal(NumTools.getSumCorpusOverage(corpus_overage_temp, 1));
		BigDecimal share_money = new BigDecimal(0);
		for (int i = 0; i < corpus_overage_temp.size(); i++) {
			// 在之前的处理中已经把剩余本金往后延了一期所以这里取当期
			BigDecimal this_corpus_overage = new BigDecimal(corpus_overage_temp.get(i).toString());
			BigDecimal temp_money;
			if (i != corpus_overage_temp.size()) {// 按照比例计算手续费分摊值
				temp_money = hand_change_money.multiply(this_corpus_overage.divide(sum_corpus_overage, 20, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {// 最后一期的时候倒减 以 保证总手续费相等.
				temp_money = hand_change_money.subtract(share_money).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
			interest.add(temp_money.toString());
			share_money = share_money.add(temp_money);
		}
		// 如果是期初则把期初的那一期设置为0
		if (period_type == 1) {
			interest.add(grace, "0");
		}
		// 测试输出
		if (logger.isDebugEnabled()) {
			logger.debug("总本金:" + sum_corpus + "剩余本金总和:" + sum_corpus_overage + "手续费:" + hand_change_money);
			for (int i = 0; i < interest.size(); i++) {
				logger.debug("第" + (i + 1) + "期:剩余本金:" + corpus_overage.get(i) + "分摊手续:" + interest.get(i));
			}
		}
		return interest;
	}

	/**
	 * 根据会计租金计划和财务租金计划和截止期数,计算出剩余还需要重新分摊的手续费
	 * 
	 * @param handling_charge_money
	 *            总手续费
	 * @param interest_list
	 *            财务租金计划-利息
	 * @param finac_interest_list
	 *            会计租金计划-利息
	 * @param end_list
	 *            截止期数
	 * @return 返回剩余的需要分摊的手续费
	 */
	@Deprecated
	public static BigDecimal getShareHandlingChargeMoney(BigDecimal handling_charge_money, List<String> interest_list, List<String> finac_interest_list, int end_list) {
		BigDecimal share_handling_charge_money = new BigDecimal("0");
		BigDecimal interest = new BigDecimal(NumTools.getSumCorpusOverage(interest_list, 1));
		BigDecimal finac_interest = new BigDecimal(NumTools.getSumCorpusOverage(finac_interest_list, 1));
		if (finac_interest.subtract(interest).compareTo(handling_charge_money) != 0) {
			logger.error("会计租金计划利息总和减去财务租金计划利息总和不等于商务条件手续费!");
			return null;
		}
		interest = new BigDecimal(NumTools.getSumCorpusOverage(interest_list, end_list));
		finac_interest = new BigDecimal(NumTools.getSumCorpusOverage(finac_interest_list, end_list));
		share_handling_charge_money = finac_interest.subtract(interest).setScale(2, BigDecimal.ROUND_HALF_UP);
		if (share_handling_charge_money.compareTo(BigDecimal.ZERO) < 0) {
			logger.error("计算需要分摊的手续费出现负数![总手续费:" + handling_charge_money + ";开始期项:" + end_list + ";会计利息和:" + finac_interest + ";财务利息和:" + interest + "]");
			return null;
		}
		return share_handling_charge_money;
	}
}
