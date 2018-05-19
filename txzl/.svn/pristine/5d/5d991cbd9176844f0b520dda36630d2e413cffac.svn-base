package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.reckon.commons.base.RentPlan;
import com.reckon.commons.helper.bean.CalculateCondition;
import com.reckon.commons.util.DateTools;

public class RentCalculateUtil {

	private static Logger logger = Logger.getLogger(RentCalculateUtil.class);

	/**
	 * <p>
	 * 初步获取一个空的日历计划，只为生成一个空架子， 为了其他的测算做数据模板。
	 * </p>
	 * <p>
	 * 只有有还款期数编号，int类型1-n，还款日期。
	 * </p>
	 * <p>
	 * 没有租金、本金和利息等数据。
	 * </p>
	 * 
	 * @param condition
	 * @param clazs
	 *            RentPlan 接口的实现类类型，返回的租金计划中实例类型
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> getEmptyPlanModel(CalculateCondition condition, Class<T> clazs) throws Exception {
		logger.info("日期列表计算之起租日期：" + condition.getStartDate());
		logger.info("日期列表计算之首期租金计划日期：" + condition.getFirstPlanDate());
		logger.info("日期列表计算之次期租金计划日期：" + condition.getSecondPlanDate());
		String startDate = condition.getStartDate();// 起租日期，计算最后一期的还款日期
		String firstPlanDate = condition.getFirstPlanDate();// 计划第1期日期
		// String secondPlanDate = condition.getSecondPlanDate();// 计划第2期日期
		List<String> planDateList = new ArrayList<String>();
		planDateList.add(startDate);// 必须有起始日期
		planDateList.add(firstPlanDate);
		// if(firstPlanDate != null){
		// planDateList.add(firstPlanDate);
		// if(secondPlanDate != null){
		// planDateList.add(secondPlanDate);
		// }
		// } else {
		// if(condition.getPeriodType() == 0){// 期初
		// planDateList.add(startDate);//第一期和起租日期一样
		// }
		// }

		int incomeTimes = condition.getIncomeTimes();// 期数
		int grace = condition.getGrace();// 宽限期
		int issueNumber = condition.getIssueNumber();// 每期几个月
		
		logger.info("日期列表计算之期数：" + incomeTimes);
		logger.info("日期列表计算之宽限期：" + grace);
		logger.info("日期列表计算之每期几个月：" + issueNumber);
		
		// 计算结束租期日子
		Calendar calendar = Calendar.getInstance();// 推算日期的日历
		Date reference = DateTools.parseToDate(planDateList.get(planDateList.size() - 1));
		for (int i = 1; planDateList.size() < grace + incomeTimes + 1; i++) {
			calendar.setTime(reference);
			calendar.add(Calendar.MONTH, issueNumber * i);
			Date tempDate = calendar.getTime();
			while (DateTools.getDiffMonth(reference, tempDate) > issueNumber * i) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				tempDate = calendar.getTime();
			}
			String tempDateStr = DateTools.formatToDate(tempDate);
			logger.info("日期列表计算之租金计划日期列表：（" + i + "）" + tempDateStr);
			planDateList.add(tempDateStr);
		}

		T temp = null;
		List<T> result = new ArrayList<T>();
		for (int i = 1; i < planDateList.size(); i++) {
			temp = clazs.newInstance();
			temp.setRentList(i);
			temp.setStartDate(planDateList.get(i - 1));
			temp.setEndDate(planDateList.get(i));
			if (condition.getPeriodType() == 0) {// 期初
				logger.info("日期列表计算之租金计划日期(期初支付)期次：（" + i + "）" + temp.getStartDate());
				temp.setPlanDate(temp.getStartDate());
			} else {// 期初
				logger.info("日期列表计算之租金计划日期(期末支付)期次：（" + i + "）" + temp.getEndDate());
				temp.setPlanDate(temp.getEndDate());
			}
			result.add(temp);
		}
		return result;
	}

	/**
	 * <p>
	 * 起租日和首期租金支付日的差额， 如果选择期末支付
	 * </p>
	 * <p>
	 * 计算第一期缺少的天数当中的利息，从第一期利息中减去
	 * </p>
	 * <p>
	 * 如起租日期是1号，第一期租金支付日是25号，
	 * </p>
	 * <p>
	 * 那么会计算出25-30号的利息， 第一期利息需要减去这个利息。
	 * </p>
	 * 
	 * @param condition
	 * @return
	 */
	public static BigDecimal getFirstInterestDiff(CalculateCondition condition) {
		/**
		 * BigDecimal interest = BigDecimal.ZERO; Date startDate =
		 * condition.getStartDate();//起租日期 Date firstPlanDate =
		 * condition.getFirstPlanDate();//第一期租金支付日
		 * 
		 * Calendar calendar = Calendar.getInstance();
		 * calendar.setTime(startDate); if(condition.getPeriodType() ==
		 * 1){//期末的话，应该加一期，才是理论上的第一期租金支付日 calendar.add(Calendar.MONTH,
		 * condition.getIssueNumber());// 计算实际应该的第一期租金日期 }
		 * 
		 * // 计算预期的起租日期和第一期租金支付日中间的日期差 long diff =
		 * RentCalculateUtil.getDateDiff(calendar.getTime(), firstPlanDate);
		 * BigDecimal leaseAmt = condition.getLeaseAmt(); BigDecimal dayRate =
		 * condition.getDayRate(); interest =
		 * leaseAmt.multiply(dayRate).multiply(new
		 * BigDecimal(diff)).setScale(Scale.INTEREST_SCALE,
		 * BigDecimal.ROUND_HALF_UP); logger.info("租金测算[RentCalculateUtil]租前息："
		 * + interest.toString()); return interest;
		 **/
		return BigDecimal.ZERO;
	}
	
	/**
	 * 返回两时间间隔天数 bdate--开始时间字符串 edate--结束时间字符串
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDateDiff(Date startDate, Date endDate) {
		try {
			// 86400000毫秒，一天的时间
			long datediff = endDate.getTime() - startDate.getTime();
			datediff = datediff / 86400000;
			logger.info("租金测算[RentCalculateUtil]日期差：" + datediff);
			return datediff;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 等额本金法 TODO
	 * 
	 * @param condition
	 * @param corpustAdjust
	 *            已知的本金列表
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> calculateForSameCorpus(CalculateCondition condition, Map<Integer, BigDecimal> corpustAdjust, Class<T> clazs) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始等额本金测算..........");
		List<T> rentPlan = getEmptyPlanModel(condition, clazs);
		BigDecimal corpusOverage = condition.getLeaseMoney();// 融资额
		BigDecimal remainMoney = condition.getLeaseMoney();
		int grace = condition.getGrace();
		int issueCountRemain = condition.getIncomeTimes();// 未知本金的期项的期数
		for (RentPlan rp : rentPlan) {
			Integer listNum = rp.getRentList();
			if (corpustAdjust.containsKey(listNum)) {
				remainMoney = remainMoney.subtract(corpustAdjust.get(listNum));
				if (listNum > grace) {
					issueCountRemain--;
				}
			}
		}

		BigDecimal issueRate = condition.getIssueRate();// 期利率
		logger.info("租金测算[RentCalculateUtil]期利率：" + issueRate.toString());
		BigDecimal corpusEven = remainMoney.divide(new BigDecimal(issueCountRemain), Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP);// 本金
		for (RentPlan rp : rentPlan) {
			Integer num = rp.getRentList();
			BigDecimal corpusTemp = BigDecimal.ZERO;
			BigDecimal interestTemp = corpusOverage.multiply(issueRate);
			if (corpustAdjust.containsKey(num)) {
				corpusTemp = corpustAdjust.get(num);
				rp.setRentType(Type.CUSTOM);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "已知期本金：" + corpusTemp.toString());
			} else if (num > grace) {
				corpusTemp = corpusEven;
				if (num == grace + 1 && condition.getPeriodType() == 0) {
					interestTemp = BigDecimal.ZERO;
					logger.info("租金测算[RentCalculateUtil]业务首期不计息");
				}
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			} else {
				rp.setRentType(Type.GRACE);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "宽限期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			}
			BigDecimal rentTemp = corpusTemp.add(interestTemp);
			logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());

			logger.info("租金测算[RentCalculateUtil]业务" + num + "期计息本金：" + corpusOverage.toString());
			corpusOverage = corpusOverage.subtract(corpusTemp);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + num + "期本金剩余：" + corpusOverage.toString());

			if (num == rentPlan.size()) {
				corpusOverage = corpusOverage.subtract(condition.getEndMoney());
				corpusTemp = corpusTemp.add(corpusOverage);
				interestTemp = rentTemp.subtract(corpusTemp);
				corpusOverage = condition.getEndMoney();
			}

			setRentPlanInfo(rp, rentTemp, corpusTemp, interestTemp, corpusOverage);
		}
		logger.info("租金测算[RentCalculateUtil]结束等额本金测算..........");
		return rentPlan;
	}

	/**
	 * 等额本金法
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> calculateForSameCorpus(CalculateCondition condition, Class<T> clazs) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始等额本金测算..........");
		List<T> rentPlan = getEmptyPlanModel(condition, clazs);
		BigDecimal corpusOverage = condition.getLeaseMoney();// 融资额
		int incomeTimes = condition.getIncomeTimes();// 期数
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		logger.info("租金测算[RentCalculateUtil]期利率：" + issueRate.toString());
		BigDecimal corpus = corpusOverage.divide(new BigDecimal(incomeTimes), Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP);// 本金
		for (RentPlan rp : rentPlan) {
			Integer num = rp.getRentList();
			BigDecimal corpusTemp = corpus;
			BigDecimal interestTemp = corpusOverage.multiply(issueRate);
			if (num <= condition.getGrace()) {
				corpusTemp = BigDecimal.ZERO;// 宽限期内只收利息
				rp.setRentType(Type.GRACE);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "宽限期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			} else if (num == condition.getGrace() + 1 && condition.getPeriodType() == 0) {
				// 宽限期结束后第一期，如果是期初支付租金，不计利息
				interestTemp = BigDecimal.ZERO;
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务首期不计息");
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			} else {
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			}

			BigDecimal rentTemp = interestTemp.add(corpusTemp);
			logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());

			logger.info("租金测算[RentCalculateUtil]业务" + num + "期计息本金：" + corpusOverage.toString());
			corpusOverage = corpusOverage.subtract(corpusTemp);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + num + "期本金剩余：" + corpusOverage.toString());

			if (num == rentPlan.size()) {
				corpusOverage = corpusOverage.subtract(condition.getEndMoney());
				corpusTemp = corpusTemp.add(corpusOverage);
				interestTemp = rentTemp.subtract(corpusTemp);
				corpusOverage = condition.getEndMoney();
			}

			setRentPlanInfo(rp, rentTemp, corpusTemp, interestTemp, corpusOverage);
		}
		logger.info("租金测算[RentCalculateUtil]结束等额本金测算..........");
		return rentPlan;
	}

	/**
	 * 等额租金法 TODO
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> calculateForSameRent(CalculateCondition condition, Map<Integer, BigDecimal> rentAdjust, Class<T> clazs) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始等额租金测算..........");
		List<T> rentPlan = getEmptyPlanModel(condition, clazs);
		BigDecimal corpusOverage = condition.getLeaseMoney();// 融资额
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		BigDecimal rent = PMTCalculateUtil.getPMT(condition, rentAdjust);
		logger.info("租金测算[RentCalculateUtil]期利率：" + issueRate.toString());
		int grace = condition.getGrace();
		for (RentPlan rp : rentPlan) {
			Integer num = rp.getRentList();
			// 默认是宽限期，未知租金状态
			BigDecimal interestTemp = corpusOverage.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			BigDecimal rentTemp = interestTemp;
			if (rentAdjust.containsKey(num)) {
				rentTemp = rentAdjust.get(num);
				rp.setRentType(Type.CUSTOM);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "已知期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());
			} else if (num > grace) {
				rentTemp = rent;
				if (num == grace + 1 && condition.getPeriodType() == 0) {
					interestTemp = BigDecimal.ZERO;
					logger.info("租金测算[RentCalculateUtil]业务首期不计息");
				}
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());
			} else {
				rp.setRentType(Type.GRACE);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "宽限期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());
			}

			BigDecimal corpusTemp = rentTemp.subtract(interestTemp);
			logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期租金/利息：" + rentTemp.toString() + "\t\t" + interestTemp.toString());

			logger.info("租金测算[RentCalculateUtil]业务" + num + "期计息本金：" + corpusOverage.toString());
			corpusOverage = corpusOverage.subtract(corpusTemp);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + num + "期本金剩余：" + corpusOverage.toString());

			if (num == rentPlan.size()) {
				corpusOverage = corpusOverage.subtract(condition.getEndMoney());
				corpusTemp = corpusTemp.add(corpusOverage);
				interestTemp = rentTemp.subtract(corpusTemp);
				corpusOverage = condition.getEndMoney();
			}

			setRentPlanInfo(rp, rentTemp, corpusTemp, interestTemp, corpusOverage);
		}

		logger.info("租金测算[RentCalculateUtil]结束等额租金测算..........");
		return rentPlan;
	}

	/**
	 * 等额租金法
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> calculateForSameRent(CalculateCondition condition, Class<T> clazs) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始等额租金测算..........");
		List<T> rentPlan = getEmptyPlanModel(condition, clazs);
		BigDecimal corpusOverage = condition.getLeaseMoney();// 融资额
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		logger.info("租金测算[RentCalculateUtil]期利率：" + issueRate.toString());
		BigDecimal rent = PMTCalculateUtil.getPMT(condition);
		for (RentPlan rp : rentPlan) {
			Integer num = rp.getRentList();
			BigDecimal rentTemp = rent;
			BigDecimal interestTemp = corpusOverage.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			BigDecimal corpusTemp = rentTemp.subtract(interestTemp);// 本金

			if (num <= condition.getGrace()) {
				corpusTemp = BigDecimal.ZERO;// 宽限期内只收利息
				rentTemp = interestTemp;
				rp.setRentType(Type.GRACE);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "宽限期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			} else if (num == condition.getGrace() + 1 && condition.getPeriodType() == 0) {
				interestTemp = BigDecimal.ZERO;
				corpusTemp = rentTemp;
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务首期不计息");
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			} else {
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpusTemp.toString() + "\t\t" + interestTemp.toString());
			}

			logger.info("租金测算[RentCalculateUtil]业务" + num + "期计息本金：" + corpusOverage.toString());
			corpusOverage = corpusOverage.subtract(corpusTemp);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + num + "期本金剩余：" + corpusOverage.toString());

			if (num == rentPlan.size()) {
				corpusOverage = corpusOverage.subtract(condition.getEndMoney());
				corpusTemp = corpusTemp.add(corpusOverage);
				interestTemp = rentTemp.subtract(corpusTemp);
				corpusOverage = condition.getEndMoney();
			}

			setRentPlanInfo(rp, rentTemp, corpusTemp, interestTemp, corpusOverage);
		}

		logger.info("租金测算[RentCalculateUtil]结束等额租金测算..........");
		return rentPlan;
	}

	/**
	 * 均息法
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static <T extends RentPlan> List<T> calculateForSameInterest(CalculateCondition condition, Class<T> clazs) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始均息法测算..........");
		List<T> rentPlan = getEmptyPlanModel(condition, clazs);
		BigDecimal corpusOverage = condition.getLeaseMoney();// 融资额
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		logger.info("租金测算[RentCalculateUtil]期利率：" + issueRate.toString());
		int incomeTimes = condition.getIncomeTimes();// 期数
		BigDecimal corpus = corpusOverage.divide(new BigDecimal(incomeTimes), Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_UP);// 本金
		BigDecimal interest = corpusOverage.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);

		for (RentPlan rp : rentPlan) {
			Integer num = rp.getRentList();
			BigDecimal corpusTemp = corpus;
			BigDecimal interestTemp = interest;

			if (num <= condition.getGrace()) {
				corpusTemp = BigDecimal.ZERO;
				rp.setRentType(Type.GRACE);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "宽限期本金/利息：" + corpus.toString() + "\t\t" + interest.toString());
			} else if (num == condition.getGrace() + 1 && condition.getPeriodType() == 0) {
				interestTemp = BigDecimal.ZERO;
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务首期不计息");
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpus.toString() + "\t\t" + interest.toString());
			} else {
				rp.setRentType(Type.NORMAL);
				logger.info("租金测算[RentCalculateUtil]业务" + num + "正常期本金/利息：" + corpus.toString() + "\t\t" + interest.toString());
			}
			BigDecimal rentTemp = corpusTemp.add(interestTemp);

			logger.info("租金测算[RentCalculateUtil]业务" + num + "期计息本金：" + corpusOverage.toString());
			corpusOverage = corpusOverage.subtract(corpusTemp);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + num + "期本金剩余：" + corpusOverage.toString());

			if (num == rentPlan.size()) {
				corpusOverage = corpusOverage.subtract(condition.getEndMoney());
				corpusTemp = corpusTemp.add(corpusOverage);
				interestTemp = rentTemp.subtract(corpusTemp);
				corpusOverage = condition.getEndMoney();
			}

			setRentPlanInfo(rp, rentTemp, corpusTemp, interestTemp, corpusOverage);
		}

		logger.info("租金测算[RentCalculateUtil]结束均息法测算..........");
		return rentPlan;
	}

	private static void setRentPlanInfo(RentPlan rentPlan, BigDecimal rent, BigDecimal corpus, BigDecimal interest, BigDecimal corpusOverage) {
		rentPlan.setRent(rent.setScale(Scale.RENT_SCALE, RoundingMode.UP));
		rentPlan.setBusinessCorpus(corpus.setScale(Scale.CORPUS_SCALE, RoundingMode.HALF_UP));
		rentPlan.setBusinessInterest(interest.setScale(Scale.INTEREST_SCALE, RoundingMode.HALF_UP));
		
		rentPlan.setBusinessCorpusOverage(corpusOverage.setScale(Scale.CORPUS_SCALE, RoundingMode.HALF_UP));
		
		rentPlan.setOverageRent(rentPlan.getRent());
		rentPlan.setOverageCorpus(rentPlan.getBusinessCorpus());
		rentPlan.setOverageInterest(rentPlan.getBusinessInterest());
	}
}
