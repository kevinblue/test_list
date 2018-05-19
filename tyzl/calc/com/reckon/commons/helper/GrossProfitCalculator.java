package com.reckon.commons.helper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.reckon.bean.ConditionBean;
import com.reckon.bean.FundPlanBean;
import com.reckon.bean.FundRentPlanBean;
import com.reckon.bean.GrossProfitConfig;
import com.reckon.dao.impl.GrossProfitDAOImpl;
import com.reckon.util.NumberUtils;


/**
 * 毛利计算器
 * 
 * @author 孟海阳 QQ:648020894
 */
public class GrossProfitCalculator {
	private static Logger log = Logger.getLogger(GrossProfitCalculator.class);

	// 粗利=(实行额)×（IRR－r）×实际授信月数（租赁期限）/12/2/1.17 + 清帐期限调整贡献利润
	// 清帐期限调整贡献利润=(清帐期限调整金额,每笔支付的钱)×PBOC6个月贷款利息（年率）/360×清帐期限调整天数/2
	// 实行额==购买金额－首付款－保证金-其他收入+其他支出-前收第1期租金+保险费-厂商返利-手续费
	// 期初支出总额=购买金额（含税）－首付款－保证金－前收第1期租金

	public static BigDecimal reckonConditionSXE(ConditionBean cb, FundRentPlanBean oldRentPlanContext){
		BigDecimal result = BigDecimal.ZERO;
		log.debug("首付款:"+cb.getFirstPayment());
		log.debug("保证金:"+cb.getCautionMoney());
		log.debug("其他收入:"+cb.getOtherIncome());
		log.debug("其他支出:"+cb.getOtherExpenditure());
		log.debug("保险费:"+cb.getInsureMoney());
		log.debug("厂商返利:"+cb.getReturnAmt());
		log.debug("手续费:"+cb.getHandlingChargeMoney());
		
		result = result.add(new BigDecimal(cb.getInsureMoney()));//保险费
		result = result.add(new BigDecimal(cb.getOtherExpenditure()));// 其它支出
		result = result.subtract(new BigDecimal(cb.getFirstPayment()));// 首付款
		result = result.subtract(new BigDecimal(cb.getCautionMoney()));// 租赁保证金
		//result = result.subtract(new BigDecimal(cb.getBeforeInterest()));// 租前息  租前息不在参与实行额或者各种的流入流出测算
		result = result.subtract(new BigDecimal(cb.getReturnAmt()));// 厂商返利
		result = result.subtract(new BigDecimal(cb.getOtherIncome()));// 其它收入
		result = result.subtract(new BigDecimal(cb.getHandlingChargeMoney()));// 手续费
		
		log.debug("计算粗利区分期末期末："+cb.getPeriodType());
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		if (cb.getPeriodType().contains("1")) {// 期初（期末）支付
			int grace = cb.getGrace();
			String firstRent = oldRentPlanContext.getRentList().get(grace);
			log.debug("前收第1期租金:"+firstRent);
			result = result.subtract(new BigDecimal(firstRent));
		}
		return result;
	}
	
	
	/**
	 * 粗利计算 粗利=(实行额)×（IRR－r）×实际授信月数（租赁期限）/12/2/1.17 + 清帐期限调整贡献利润
	 * 
	 * @param cb 
	 * @param oldRentPlanContext 期初首期租金
	 * @param fundPlanList 
	 * @param PBOCInfo 
	 */
	public static BigDecimal reckonGrossProfit(ConditionBean cb, FundRentPlanBean oldRentPlanContext, List<FundPlanBean> fundPlanList, Hashtable<String, String> PBOCInfo) {
		/**
		 * 按顺序来说是：
		 * 1.租金计划
		 * 2.保险费（依赖残租金）
		 * 3.资金收付计划（依赖保险费）
		 * 4.清帐期限贡献利润（资金计划）
		 * 4.实行额（依赖保险费和租金计划）
		 * 5.粗利（依赖实行额和清帐期限贡献利润）
		 * 
		 * 实际顺序可能有点儿乱套，需要调整
		 * 
		 * */
		BigDecimal result = reckonConditionSXE(cb, oldRentPlanContext);
		//log.info("result:"+result);
		BigDecimal IRR = new BigDecimal(cb.getIrr()).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_UP);
		log.info("IRR:"+IRR);
		BigDecimal r = BigDecimal.ZERO;
		GrossProfitDAOImpl grossProfitDAOImpl = new GrossProfitDAOImpl();
		
		//sea 2014-04-16
		//1.判断保证金抵扣金额，可抵扣多少期减去相应的还租间隔的月数，抵扣的半期不考虑
		//2.期初情况下：( 还租次数 - 1 ) * 还租间隔 ，期末情况照旧
		int leaseTerm = cb.getLeaseTerm();
		//实际授信月数
		leaseTerm =  getIncomeNumber(cb, oldRentPlanContext);
		cb.setCreditMonths(String.valueOf(leaseTerm));
		//2014-05-13 这里的取值以实际授信月数为准
		GrossProfitConfig config = grossProfitDAOImpl.findLastGrossProfitConfig(leaseTerm, cb.getStartDate());
		if(config != null){
			if (cb.getRateFloatType().equals("fixed")) {
				r = new BigDecimal(config.getBaseRate());
			} else {
				r = new BigDecimal(config.getFloatRate());
			}
		}
		log.info("r:"+r);
		//按天计算:BigDecimal extraProfits = reckonExtraProfits(cb, fundPlanList, PBOCInfo);
		//按月计算
		BigDecimal extraProfits = reckonExtraProfitsByMonth(cb, fundPlanList, PBOCInfo);
		log.info("清帐期限调整贡献利润:"+extraProfits);
		
		result = IRR.subtract(r).multiply(result).multiply(new BigDecimal(leaseTerm));
		//log.info("result2:"+result);
		
		//28.08 = 12 * 2 * 1.17//12个月，2平分，1.17系数
		result = result.divide(new BigDecimal(28.08), 20, BigDecimal.ROUND_HALF_UP);
		//log.info("result3:"+result);
		 
		result = result.add(extraProfits);
		//log.info("result4:"+result);
		 
		return result.setScale(Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
		
	}
	
	/**
	 * private String creditMonths = "";//实际授信月数计算
	 * 根据期初、期末、保证金抵扣等情况计算粗利所用到的还租次数（该还租次数不是交易机构中的那个还租次数）
	 * @param cb 交易结构信息
	 * @return
	 */
	private static int getIncomeNumber(ConditionBean cb,FundRentPlanBean oldRentPlanContext){
		int incomeNumber = cb.getIncomeNumber();//还租次数
		String incomeNumberYear = cb.getIncomeNumberYear(); // 年还租次数 (付租类型:月付、季付、半年付...)
		
		// 付租类型
		if (null != cb.getIncomeNumberYear() && "income_1".equals(cb.getIncomeNumberYear())) {
			incomeNumberYear = "12";
		} else if (null != cb.getIncomeNumberYear() && "income_3".equals(cb.getIncomeNumberYear())) {
			incomeNumberYear = "4";
		} else if (null != cb.getIncomeNumberYear() && "income_6".equals(cb.getIncomeNumberYear())) {
			incomeNumberYear = "2";
		} else if (null != cb.getIncomeNumberYear() && "income_12".equals(cb.getIncomeNumberYear())) {
			incomeNumberYear = "1";
		} else if (null != cb.getIncomeNumberYear() && "income_2".equals(cb.getIncomeNumberYear())) {
			incomeNumberYear = "6";
		}
		
		log.debug("年还租次数:"+incomeNumberYear);
		Double cautionDeductionMoney = NumberUtils.parseDouble( NumberUtils.nullToZero( cb.getCautionDeductionMoney() ) ); // 保证金抵扣金额
		List<String> rentList = new ArrayList<String>(); // 租金列表
		rentList = oldRentPlanContext.getRentList();
		
		double d_total = 0;
		int num = 0;// 倒叙循环计算抵扣租金多少期
		for (int i = rentList.size() - 1; i >= 0; i--) {
			//租金累加
			d_total = d_total + Double.parseDouble(rentList.get(i));
			//租金累加值小于等于抵扣金额表示这些租金都可以完全被抵扣掉
			if (d_total <= cautionDeductionMoney) {
				num = num + 1;
			}
			//如果租金累加的值大于抵扣金额则跳出循环,部分抵扣的这里直接无视
			if (d_total > cautionDeductionMoney) {
				break;
			}
		}
		log.debug("租金抵扣多少期:"+num);
		/**
		 * income_1	月  付    年还租次数 12
		 * income_3	季  付      年还租次数 4
		 * income_6	半年付   年还租次数 2
		 * income_12 年  付    年还租次数 1
		 * income_2	双月付    年还租次数 6
		 * 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		 * 
		 * (还租次数 - 1) * (12/年还租次数)
		 * 1.判断保证金抵扣金额，可抵扣多少期减去相应的还租间隔的月数，抵扣的半期不考虑
		 * 2.期初情况下：还租次数 - 1，期末情况照旧 
		 * 最终的还租次数 * 还租间隔 
		 */
		incomeNumber = incomeNumber - num;
		if("1".equals(cb.getPeriodType()) || "period_type_1".equals(cb.getPeriodType())){//期初情况
			incomeNumber = incomeNumber - 1;
		}
		int number = NumberUtils.convertsToInt(  NumberUtils.divide( 12, Double.parseDouble( incomeNumberYear ),0 ) );
		incomeNumber = NumberUtils.convertsToInt( NumberUtils.multiply(incomeNumber,  number , 0) );
		log.info("计算粗利所用到的还租次数:"+incomeNumber);
		return incomeNumber;
	}
	
	/**
	 * 按天计算
	 * 额外利润计算器（清帐期限调整贡献利润）
	 * 不分段计算额外利润
	 * 额外利润：起租日开始到款项支付日结束，这段时间的存款利息
	 * @param cb
	 * @param oldRentPlanContext
	 * @param fundPlanList
	 * @param PBOCInfo
	 */
	public static BigDecimal reckonExtraProfits(ConditionBean cb, List<FundPlanBean> fundPlanList, Hashtable<String, String> PBOCInfo) {
		// 清帐期限调整贡献利润=(清帐期限调整金额,每笔支付的钱)×PBOC6个月贷款利息（年率）×清帐期限调整天数/360/2
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal baseSix = new BigDecimal(PBOCInfo.get("base_rate_half")).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_UP);
		for (FundPlanBean fundPlanBean : fundPlanList) {
			//支出的购买设备的款项才计算
			if ("feetype10".equalsIgnoreCase(fundPlanBean.getFeeType()) && fundPlanBean.getPayType().contains("out")) {
				//Date.parse(cb.getStartDate()), Date.parse(fundPlanBean.getPlanDate())
				long dateDiff = RentCalculateUtil.getDateDiff(new Date(),new Date());
				dateDiff = dateDiff < 0 ? 0 : dateDiff;
				log.debug(cb.getStartDate()+"【--】"+fundPlanBean.getPlanDate()+"每次清帐期限调整天数_判断前(清帐期计算天数值 <= 30 置为0)_dateDiff:"+dateDiff);
				//清帐期计算天数值 < 30 置为0
				if(dateDiff < 30){
					dateDiff = 0;
				}
				BigDecimal planMoney = new BigDecimal(fundPlanBean.getPlanMoney());
				log.info("每次的_planMoney:"+planMoney);
				BigDecimal temp = new BigDecimal(dateDiff).multiply(baseSix).multiply(planMoney);
				temp = temp.divide(new BigDecimal(720), Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				result = result.add(temp);
			}
		}
		//log.info("清帐期限调整贡献利润:"+result);
		return result;
	}
	
	/**
	 * 按月计算
	 * 额外利润计算器（清帐期限调整贡献利润）
	 * 不分段计算额外利润
	 * 额外利润：起租日开始到款项支付日结束，这段时间的存款利息
	 * @param cb
	 * @param oldRentPlanContext
	 * @param fundPlanList
	 * @param PBOCInfo
	 */
	public static BigDecimal reckonExtraProfitsByMonth(ConditionBean cb, List<FundPlanBean> fundPlanList, Hashtable<String, String> PBOCInfo) {
		// 清帐期限调整贡献利润=(清帐期限调整金额,每笔支付的钱)×PBOC6个月贷款利息（年率）×清帐期限调整天数/12/2
		BigDecimal result = BigDecimal.ZERO;
		BigDecimal baseSix = new BigDecimal(PBOCInfo.get("base_rate_half")).divide(new BigDecimal(100), 20, BigDecimal.ROUND_HALF_UP);
		for (FundPlanBean fundPlanBean : fundPlanList) {
			//支出的购买设备的款项才计算
			if ("feetype10".equalsIgnoreCase(fundPlanBean.getFeeType()) && fundPlanBean.getPayType().contains("out")) {
				//long dateDiff = RentCalculateUtil.getDateDiff(cb.getStartDate(), fundPlanBean.getPlanDate());
				long dateDiff = RentCalculateUtil.getDateDiff(new Date(),new Date());
				dateDiff = dateDiff < 0 ? 0 : dateDiff;
				log.debug(cb.getStartDate()+"【--】"+fundPlanBean.getPlanDate()+"每次清帐期限调整天数_判断前(清帐期计算天数值 <= 30 置为0)_dateDiff:"+dateDiff);
				//按月计算不取余数,例如:29/30月份=0,30/30月份=1,59/30月份=1,79/30月份=2
				dateDiff = dateDiff/30;
				BigDecimal planMoney = new BigDecimal(fundPlanBean.getPlanMoney());
				log.info("每次的_planMoney:"+planMoney);
				log.info("每次的月份:"+dateDiff);
				BigDecimal temp = new BigDecimal(dateDiff).multiply(baseSix).multiply(planMoney);
				temp = temp.divide(new BigDecimal(24), Scale.INTEREST_SCALE, BigDecimal.ROUND_HALF_UP);
				result = result.add(temp);
			}
		}
		//log.info("清帐期限调整贡献利润:"+result);
		return result;
	}
}
