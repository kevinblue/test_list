package com.reckon.calculation.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.SpecialRulesBean;
import com.reckon.calculation.vo.CalculationCondition;
import com.reckon.calculation.vo.RentPlanInfo;


public class RentCalculateUtil {

	private static Logger logger = Logger.getLogger(RentCalculateUtil.class);
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 计算两个日期之间的差的月数。
	 * 月付时：1月31号第一期，则第二期是2月28号或者29号。
	 * 这个需要判断，不能直接加一个月，否则会变成3月2号的
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static int getDiffMonth(Date beginDate, Date endDate) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		int beginYear = calendar.get(Calendar.YEAR);
		int beginMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(endDate);
		int endYear = calendar.get(Calendar.YEAR);
		int endMonth = calendar.get(Calendar.MONTH);
		int difMonth = (endYear-beginYear)*12+(endMonth-beginMonth);
		return difMonth;
	}
	

//	/**
//	 * 初步获取一个空的日历计划，只为生成一个空架子，为了其他的测算做数据模板。 只有有还款期数编号，int类型1-n，还款日期。
//	 * 没有租金、本金和利息等数据。
//	 * 
//	 * @param condition
//	 * @return
//	 * @throws ParseException
//	 */
//	public static List<RentPlanInfo> getEmptyPlanModel(CalculationCondition condition) throws Exception {
//		int incomeTimes = condition.getIncomeTimes();// 期数
//		int issueNumber = condition.getIssueNumber();// 每期几个月
//		int grace = condition.getGrace();// 宽限期
//
//		Calendar calendar = Calendar.getInstance();// 推算日期的日历，推算前需要先找到第1期租金实际支付日
//		Date startDate = condition.getStartDate();// 起租日期，计算最后一期的还款日期
//		Date firstPlanDate = condition.getFirstPlanDate();// 计划第1期日期
//		if(firstPlanDate == null){
//			calendar.setTime(startDate);
//			calendar.add(Calendar.MONTH, issueNumber * condition.getPeriodType());
//			firstPlanDate = calendar.getTime();
//			while(getDiffMonth(startDate, firstPlanDate) > issueNumber * condition.getPeriodType()){
//				calendar.add(Calendar.DAY_OF_MONTH, -1);
//				firstPlanDate = calendar.getTime();
//			}
//		}
//
//		// 计算结束租期日子
//
//		RentPlanInfo temp = null;
//		List<RentPlanInfo> result = new ArrayList<RentPlanInfo>();
//		// 计算期数
//		for (int i = 0; i < grace + incomeTimes; i++) {
//			temp = new RentPlanInfo();
//			temp.setId(i + 1);// 设置期数
//			temp.setBusinessId(condition.getId());// 设置业务ID
//			if(i == 0){
//				temp.setStartDate(startDate);
//				temp.setEndDate(firstPlanDate);
//			} else {
//				temp.setStartDate(result.get(i - 1).getEndDate());
//				calendar.setTime(firstPlanDate);
//				calendar.add(Calendar.MONTH, issueNumber * i);
//				Date endDateTemp = calendar.getTime();
//				while(getDiffMonth(firstPlanDate, endDateTemp) > issueNumber * i){
//					calendar.add(Calendar.DAY_OF_MONTH, -1);
//					endDateTemp = calendar.getTime();
//				}
//				temp.setEndDate(endDateTemp);
//			}
//			result.add(temp);
//			logger.info("租金测算[RentCalculateUtil]获取租金计划模板：" + dateFormat.format(temp.getEndDate()));
//		}
//		return result;
//	}
	
	/**
	 * 初步获取一个空的日历计划，只为生成一个空架子，为了其他的测算做数据模板。 只有有还款期数编号，int类型1-n，还款日期。
	 * 没有租金、本金和利息等数据。
	 * 压栈处理租金计划日期
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static List<RentPlanInfo> getEmptyPlanModel(CalculationCondition condition) throws Exception {

		Date startDate = condition.getStartDate();// 起租日期，计算最后一期的还款日期
		Date firstPlanDate = condition.getFirstPlanDate();// 计划第1期日期
		Date secondPlanDate = condition.getSecondPlanDate();// 计划第2期日期
		List<Date> planDateList = new ArrayList<Date>();
		planDateList.add(startDate);// 必须有起始日期
		if(firstPlanDate != null){
			planDateList.add(firstPlanDate);
			if(secondPlanDate != null){
				planDateList.add(secondPlanDate);
			}
		} else {
			// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
			if(condition.getPeriodType() == 1){// 期初
				planDateList.add(startDate);//第一期和起租日期一样
			}
		}
		
		int incomeTimes = condition.getIncomeTimes();// 期数
		int grace = condition.getGrace();// 宽限期
		int issueNumber = condition.getIssueNumber();// 每期几个月
		// 计算结束租期日子
		Calendar calendar = Calendar.getInstance();// 推算日期的日历
		Date reference = planDateList.get(planDateList.size() - 1);
		for (int i = 1; planDateList.size() < grace + incomeTimes + 1; i++) {
			calendar.setTime(reference);
			calendar.add(Calendar.MONTH, issueNumber * i);
			Date tempDate = calendar.getTime();
			while(getDiffMonth(reference, tempDate) > issueNumber * i){
				calendar.add(Calendar.DAY_OF_MONTH, -1);
				tempDate = calendar.getTime();
			}
			planDateList.add(tempDate);
		}
		
		RentPlanInfo temp = null;
		List<RentPlanInfo> result = new ArrayList<RentPlanInfo>();
		for (int i = 1; i < planDateList.size(); i++){
			temp = new RentPlanInfo();
			temp.setId(i);
			temp.setStartDate(planDateList.get(i - 1));
			temp.setEndDate(planDateList.get(i));
			result.add(temp);
		}
		return result;
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
	 * 返回两时间间隔天数 bdate--开始时间字符串 edate--结束时间字符串
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDateDiff(String startDate, String endDateStr) {
		try {
			// 86400000毫秒，一天的时间
			Date beginDate = dateFormat.parse(startDate);
			Date endDate = dateFormat.parse(endDateStr);
			
			long datediff = endDate.getTime() - beginDate.getTime();
			datediff = datediff / 86400000;
			logger.info("租金测算[RentCalculateUtil]日期差：" + datediff);
			return datediff;
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 起租日和首期租金支付日的差额，
	 * 如果选择期末支付 计算第一期缺少的天数当中的利息，从第一期利息中减去
	 * 如起租日期是1号，第一期租金支付日是25号，那么会计算出25-30号的利息，
	 * 第一期利息需要减去这个利息。
	 * 
	 * @param condition
	 * @return
	 */
	public static BigDecimal getFirstInterestDiff(CalculationCondition condition) {
		/**
		BigDecimal interest = new BigDecimal(0);
		Date startDate = condition.getStartDate();//起租日期
		Date firstPlanDate = condition.getFirstPlanDate();//第一期租金支付日

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		if(condition.getPeriodType() == 1){//期末的话，应该加一期，才是理论上的第一期租金支付日
			calendar.add(Calendar.MONTH, condition.getIssueNumber());// 计算实际应该的第一期租金日期
		}

		// 计算预期的起租日期和第一期租金支付日中间的日期差
		long diff = RentCalculateUtil.getDateDiff(calendar.getTime(), firstPlanDate);
		BigDecimal leaseAmt = condition.getLeaseAmt();
		BigDecimal dayRate = condition.getDayRate();
		interest = leaseAmt.multiply(dayRate).multiply(new BigDecimal(diff)).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
		logger.info("租金测算[RentCalculateUtil]租前息：" + interest.toString());
		return interest;
		**/
		return BigDecimal.ZERO;
	}

	/**
	 * 等额本金法
	 * 
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static List<RentPlanInfo> calculateForSameCorpus(CalculationCondition condition,List<String> corpusList) throws Exception {
		logger.info("租金测算[RentCalculateUtil]开始等额本金测算..........");
		List<RentPlanInfo> rentPlan = getEmptyPlanModel(condition);
		BigDecimal leaseAmt = condition.getCleanLeaseMoney();// 融资额
		int incomeTimes = condition.getIncomeTimes();// 期数

		BigDecimal issueRate = condition.getIssueRate();// 期利率
		BigDecimal leaseAmtRemain = leaseAmt;// 剩余本金
		for (int i = 0; i < rentPlan.size(); i++) {
			BigDecimal corpus =( corpusList == null  || corpusList.size() <= 0 )
					?  leaseAmt.subtract(condition.getEquipEndValue()).divide(new BigDecimal(incomeTimes), Scale.CORPUS_SCALE, BigDecimal.ROUND_HALF_EVEN)
				    : new BigDecimal(corpusList.get(i)) 
					;// 本金
			BigDecimal interest = leaseAmtRemain.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			if (i < condition.getGrace()) {
				corpus = new BigDecimal(0);// 宽限期内只收利息
				logger.info("租金测算[RentCalculateUtil]业务" + (i + 1) + "宽限期本息：" + corpus.toString() + "\t\t" + interest.toString());
			} else {
				// 宽限期结束后第一期，如果是期初支付租金，不计利息
				if (i == condition.getGrace()) {
					if (condition.getPeriodType() == 0) {
						interest = new BigDecimal("0");
						logger.info("租金测算[RentCalculateUtil]业务首期不计息");
					}
				}
				logger.info("租金测算[RentCalculateUtil]业务" + (i + 1) + "正常期本息：" + corpus.toString() + "\t\t" + interest.toString());
			}
			
			if(i == 0){
				interest = interest.add(getFirstInterestDiff(condition));
			}
			if(i == rentPlan.size() -1){
				corpus = leaseAmtRemain.subtract(condition.getEquipEndValue());
			}
			rentPlan.get(i).setBusinessInterest(interest);// 该期利息=剩余本金*期利率
			rentPlan.get(i).setBusinessCorpus(corpus);// 该期本金
			rentPlan.get(i).setRent(interest.add(corpus));// 该期租金
			rentPlan.get(i).setBusinessRemain(leaseAmtRemain);// 该期计息本金
			logger.info("租金测算[RentCalculateUtil]业务" + (i + 1) + "期计息本金：" + leaseAmtRemain.toString());
			leaseAmtRemain = leaseAmtRemain.subtract(corpus);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]业务" + (i + 1) + "期本金剩余：" + leaseAmtRemain.toString());
		}

		/*RentPlanInfo lastPlan = rentPlan.get(rentPlan.size() - 1);
		lastPlan.setBusinessCorpus(lastPlan.getBusinessCorpus().add(leaseAmtRemain));
		lastPlan.setRent(lastPlan.getBusinessCorpus().add(lastPlan.getBusinessInterest()));*/
		logger.info("租金测算[RentCalculateUtil]结束等额本金测算..........");
		return rentPlan;
	}
	
	
	/**
	 * 根据租金计划和商务条件获取财务IRR，和财务收益率不一样
	 * 
	 * @param rentPlan
	 * @param condition
	 * @return
	 * @throws ParseException
	 */
	public static BigDecimal getFinanceYearRate(CalculationCondition condition, List<RentPlanInfo> rentPlan) throws ParseException {
		List<BigDecimal> inflowPour = new ArrayList<BigDecimal>();
		for (int i = condition.getGrace(); i < rentPlan.size(); i++) {//财务irr的计算不算宽限期的
			RentPlanInfo rpi = rentPlan.get(i);
			inflowPour.add(rpi.getRent());
		}
		
		BigDecimal leaseAmt = new BigDecimal(-1).multiply(condition.getLeaseAmt());
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		if(condition.getPeriodType() <= 0){//期末的话把总本金加在数据列表的第一个，为了配合getIRR方法
			inflowPour.add(0, leaseAmt);
		} else {
			inflowPour.set(0, inflowPour.get(0).add(leaseAmt));//期初的话与第一期租金合并
		}
		BigDecimal irr = IRRCalculateUtil.getIRR(inflowPour, condition);
		return irr;
	}

	/**
	 * 根据商务条件和租金计划填充租金计划中的财务本金和财务利息
	 * 
	 * @param condition 商务条件
	 * @param rentPlan 租金计划数据列表
	 * @throws ParseException
	 */
	public static void calculateFinacesPlan(CalculationCondition condition, List<RentPlanInfo> rentPlan) throws ParseException {
		logger.info("租金测算[RentCalculateUtil]财务本金利息计算开始........");
		BigDecimal irr = getFinanceYearRate(condition, rentPlan);
		BigDecimal issueRateFina = irr.divide(new BigDecimal(12 / condition.getIssueNumber()), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal issueRate = condition.getIssueRate();// 期利率
		BigDecimal leaseAmtRemain = condition.getLeaseAmt();// 剩余本金
		for (int i = 0; i < rentPlan.size(); i++) {
			RentPlanInfo rpi = rentPlan.get(i);
			BigDecimal cwInterest = new BigDecimal(0);
			BigDecimal cwCorpus = new BigDecimal(0);
			if(i < condition.getGrace()){
				cwInterest = leaseAmtRemain.multiply(issueRate).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
			} else {
				cwInterest = leaseAmtRemain.multiply(issueRateFina).setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				if (i == condition.getGrace()) {
					if (condition.getPeriodType() == 0) {
						// 宽限期结束，期初付款第一期，不计利息
						cwInterest = new BigDecimal("0");
						logger.info("租金测算[RentCalculateUtil]财务首期不计息");
					}
				}
			}
			if(i == 0 && condition.getGrace() > 0){
				cwInterest = cwInterest.add(getFirstInterestDiff(condition));
			}
			
			cwCorpus = rpi.getRent().subtract(cwInterest);

			rpi.setFinanceInterest(cwInterest);// 财务利息
			rpi.setFinanceCorpus(cwCorpus);// 财务本金
			rpi.setFinanceRemain(leaseAmtRemain);// 财务计息本金剩余
			
			//****************************************************************
			/**
			 * 临时的，有可能会永远的这样做：直接把业务的数据复制过来（保证数据准确，除均息法外）
			 * */
			rpi.setFinanceInterest(rpi.getBusinessInterest());// 财务利息
			rpi.setFinanceCorpus(rpi.getBusinessCorpus());// 财务本金
			rpi.setFinanceRemain(rpi.getBusinessRemain());// 财务计息本金剩余
			// 后边的568行最后一期处理也不需要了
			//****************************************************************
			
			logger.info("租金测算[RentCalculateUtil]财务" + (i + 1) + "期本息:" + cwCorpus.toString() + "\t\t" + cwInterest.toString());
			logger.info("租金测算[RentCalculateUtil]财务" + (i + 1) + "计息本金:" + leaseAmtRemain.toString());
			leaseAmtRemain = leaseAmtRemain.subtract(cwCorpus);// 剩余本金重置
			logger.info("租金测算[RentCalculateUtil]财务" + (i + 1) + "本金剩余量:" + leaseAmtRemain.toString());
		}
		// RentPlanInfo lastPlan = rentPlan.get(rentPlan.size() - 1);
		// lastPlan.setFinanceCorpus(lastPlan.getFinanceCorpus().add(leaseAmtRemain));
		logger.info("租金测算[RentCalculateUtil]财务本金利息计算结束........");
	}
	public static List<RentPlanInfo> getEmptyPlanModelForSpecial(CalculationCondition condition,List<SpecialRulesBean> srb) throws Exception {
		
		Date startDate=condition.getStartDate();
		List<Date> planDateList = new ArrayList<Date>();
		planDateList.add(startDate);
		Calendar calendar = Calendar.getInstance();// 推算日期的日历
		Date reference = startDate;
		for(SpecialRulesBean sb:srb){
			for(int i=sb.getStartList();i<=sb.getEndList();i++){
				calendar.setTime(planDateList.get(i-1));
				calendar.add(Calendar.MONTH, Integer.parseInt(sb.getRegular_months()));
				int day=calendar.get(Calendar.DATE);
				Calendar calendarTwo = Calendar.getInstance();
				calendarTwo.setTime(reference);
				int daytwo=calendarTwo.get(Calendar.DATE);
				if(checkIsLastDayOfMonth(calendar.getTime())){
					planDateList.add(calendar.getTime());
				}else{
					if(daytwo-day==0){
						planDateList.add(calendar.getTime());
					}
					for(int j=0;j<daytwo-day;j++){
						calendar.add(Calendar.DATE, 1);
						if(checkIsLastDayOfMonth(calendar.getTime())){
							planDateList.add(calendar.getTime());
							break;
						}
						if(j==daytwo-day-1){
							planDateList.add(calendar.getTime());
						}
					}
				}
			}
		}
		RentPlanInfo temp = null;
		List<RentPlanInfo> result = new ArrayList<RentPlanInfo>();
		for (int i = 1; i < planDateList.size(); i++){
			temp = new RentPlanInfo();
			temp.setId(i);
			temp.setStartDate(planDateList.get(i - 1));
			temp.setEndDate(planDateList.get(i));
			result.add(temp);
		}
		return result;
	}
	public static Boolean checkIsLastDayOfMonth(Date date){
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);
		int currentDay = aCalendar.get(Calendar.DAY_OF_MONTH);
		int currentMaxDay = getCurrentMonthMaxDay(date);
		if(currentDay >= currentMaxDay){
			return true;
		}else{
			return false;
		}
	}
	public static int getCurrentMonthMaxDay(Date currentDay){
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(currentDay);
		aCalendar.add(Calendar.MONTH, 1);
		aCalendar.set(Calendar.DATE, 1);
		aCalendar.add(Calendar.DATE, -1);
		return aCalendar.get(Calendar.DAY_OF_MONTH);
	}
	public static Date getCurrentMonthDay(Date currentDay,int day){
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(currentDay);
		aCalendar.set(Calendar.DAY_OF_MONTH, day);
		return aCalendar.getTime();
	}
}