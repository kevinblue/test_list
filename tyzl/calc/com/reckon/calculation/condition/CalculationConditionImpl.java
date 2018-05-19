package com.reckon.calculation.condition;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.reckon.bean.ConditionBean;
import com.reckon.calculation.utils.Scale;
import com.reckon.calculation.vo.CalculateType;
import com.reckon.calculation.vo.CalculationCondition;
import com.tenwa.leasing.utils.Tools;

public class CalculationConditionImpl implements CalculationCondition {
	
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private String id = "C20131102";

	private Date leaseAmtDate = null; // 设备款支付日期
	private Date startDate = null; // 起租日期
	private Date firstPlanDate = null; // 第一期租金支付日
	private Date secondPlanDate = null;
	private Date endDate = null; // 结束日期

	private int incomeTimes = 12; // 还租期数
	private int issueNumber = 1; // 每期几个月
	private int grace = 3; // 宽限期数，只收利息，由于本金不变所以利息相同
	private int periodType = 1; // 支付时机：期初0/期末1
	private int issueRateReCal = 1; // 是否复算，利率复算，季付时
	private int isCalBeforeInterest = 0; // 是否计算租前息
	private CalculateType calculateType = CalculateType.EVEN_CORPUS; // 测算操作类型，调息？

	// **********************************各种款项********************************************
	private BigDecimal leaseAmt = new BigDecimal(10000000);// 融资款
	private BigDecimal cleanLeaseMoney = new BigDecimal(10000000); // 净融资额
	private BigDecimal financeCorpus = new BigDecimal(10000000);// 财务本金
	private BigDecimal yearRate = new BigDecimal(0.12).setScale(5, BigDecimal.ROUND_HALF_UP); // 租赁年利率

	// **********************************计算现金流********************************************
	// 期初支出
	private BigDecimal equipAmt = new BigDecimal(12000000); // 设备款
	private BigDecimal otherExpenditure = new BigDecimal(3000); // 其它支出
	// 期初收入
	private BigDecimal otherIncome = new BigDecimal(2000); // 其它收入
	private BigDecimal returnAmt = new BigDecimal(4000); // 厂商返利
	private BigDecimal handlingChargeMoney = new BigDecimal(5000); // 手续费
	private BigDecimal firstPayment = new BigDecimal(2000000); // 首付款
	private BigDecimal cautionMoney = new BigDecimal(15000); // 租赁保证金[130]=承租人保证金[60]+供应商保证金[70]
	private BigDecimal managementMoney = new BigDecimal(6000); // 管理费
	private BigDecimal beforeInterest = new BigDecimal(0); // 租前息
	private BigDecimal gpsMoney = new BigDecimal(7000); // GPS费用---------
	private BigDecimal consultingMoney = new BigDecimal(0); // 咨询费--------
	private BigDecimal insureMoney = new BigDecimal(1000); // 保险费--------

	// 期末支出
	private BigDecimal custCautionMoney = new BigDecimal(7000); // 承租人保证金
	private BigDecimal supplierCautionMoney = new BigDecimal(8000); // 供应商保证金
	private BigDecimal cautionDeductionMoney = new BigDecimal(0); // 保证金抵扣金额
	private BigDecimal expectRent = new BigDecimal(0); // 预收租金金额
	private BigDecimal expectRentDeduction = new BigDecimal(0); // 预收租金抵扣
	// 支出保证金抵扣金额+支出保证金退还=[50]+80=[60]+[70]=承租人保证金+供应商保证金
	// 支出预收租金抵扣+支出预收租金退还=[30]+10=[40]=预收租金金额
	// 期末收入
	private BigDecimal nominalPrice = new BigDecimal(100); // 留够价款
	private BigDecimal equipEndValue = new BigDecimal(0); // 设备残值，期末余值
	// **********************************计算现金流********************************************

	private BigDecimal irr = new BigDecimal(0); // 内部收益率
	private BigDecimal planIrr = new BigDecimal(0); // 财务收益率
	private BigDecimal endIrr = new BigDecimal(0); // 合同结束IRR
	
/*你大爷的！写个构造方法把日期都给初始化成2013年，你是想让每个合同都按2013年去计算莫！你是有多爱2013年！
 * 我给你注释了放着！让后面人都看看你有多坑人！
 * ps：sea加的注释 
	public CalculationConditionImpl() {
		try {
			this.leaseAmtDate = sdf.parse("2013-01-31");// 设备款支付日期
			this.startDate = sdf.parse("2013-01-31"); // 起租日期
			this.firstPlanDate = sdf.parse("2013-02-28"); // 第一期租金支付日
			this.secondPlanDate = sdf.parse("2013-03-31");
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	}
*/
	public int getLeaseTerm() {
		return (this.incomeTimes + this.grace) * this.issueNumber;
	}
	
	public int getYearIncomeTimes() {
		int issueNumber = this.getIssueNumber();// 每期几个月
		return 12 / issueNumber;// 每年期数
	}
	
	public BigDecimal getDefaultIssueRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		int yearIncomeTimes = getYearIncomeTimes();// 每年期数
		return yearRate.divide(new BigDecimal(yearIncomeTimes), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	  * <p>计算利率。例如：月付利率=年利率/（12/1）,季付利率=年利率/（12/4）。</p>
	  * @author sea
	  * @param yearRate
	  * @return yearRate/每年期数(每年期数=12/偿还间隔)
	 */
	public BigDecimal getDefaultIssueRate(BigDecimal yearRate) {
		int yearIncomeTimes = getYearIncomeTimes();// 每年期数
		return yearRate.divide(new BigDecimal(yearIncomeTimes), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * 获得每天利率，根据年利率等值计算，默认一年360天
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getDayRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		BigDecimal dayRate = yearRate.divide(new BigDecimal(360), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
		return dayRate;
	}
	
	/**
	 * 同一Condition下，换一个yearRate来计算天利率
	 * 
	 * @param condition
	 * @return
	 */
	public BigDecimal getDayRate(BigDecimal yearRate) {
		BigDecimal dayRate = yearRate.divide(new BigDecimal(360), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);
		return dayRate;
	}
	
	public BigDecimal getIssueRate() {
		BigDecimal yearRate = this.getYearRate();// 年利率
		int issueNumber = this.getIssueNumber();// 每期几个月
		BigDecimal issueRate = getDefaultIssueRate();
		// 是否复算，复算期租息率=(1+年租息率*365/360/12)^3-1;（3次方是每期几个月）
		if (this.getIssueRateReCal() == 1) {
			issueRate = yearRate.multiply(new BigDecimal(365));
			issueRate = issueRate.divide(new BigDecimal(4320), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);// 4320=360*12
			issueRate = issueRate.add(new BigDecimal(1));
			issueRate = issueRate.pow(issueNumber);
			issueRate = issueRate.subtract(new BigDecimal(1));
		}
		return issueRate;
	}

	/**
	  * 
	  * <p>同一Condition下，换一个yearRate来计算期利率。</p>
	  * <p>操作1：年利率换算期利率。例如：月付利率=年利率/（12/1）,季付利率=年利率/（12/3）。</p>
	  * <p>操作2：判断是否复算，复算期租息率=(1+年租息率*365/360/12)3次方-1（3次方是每期几个月）。</p>
	  * @author sea edit注释，方法未做更改
	  * @param yearRate 年利率
	  * @return BigDecimal格式期利率
	 */
	public BigDecimal getIssueRate(BigDecimal yearRate) {
		int issueNumber = this.getIssueNumber();// 每期几个月
		//1 计算利率。例如：月付利率=年利率/（12/12）,季付利率=年利率/（12/3）
		BigDecimal issueRate = getDefaultIssueRate(yearRate);
		
		/*欧力士无复算需求,该操作注释掉,以免造成错误
		//2 是否复算，复算期租息率=(1+年租息率*365/360/12)3次方-1（3次方是每期几个月）
		if (this.getIssueRateReCal() == 0) {//是否复算,0:复算 1:不复算
			issueRate = yearRate.multiply(new BigDecimal(365));
			issueRate = issueRate.divide(new BigDecimal(4320), Scale.RATE_SCALE, BigDecimal.ROUND_HALF_EVEN);// 4320=360*12
			issueRate = issueRate.add(new BigDecimal(1));
			issueRate = issueRate.pow(issueNumber);
			issueRate = issueRate.subtract(new BigDecimal(1));
		}
		*/
		return issueRate;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getLeaseAmt() {
		return leaseAmt;
	}

	public void setLeaseAmt(BigDecimal leaseAmt) {
		this.leaseAmt = leaseAmt;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getCleanLeaseMoney() {
		return cleanLeaseMoney;
	}

	public void setCleanLeaseMoney(BigDecimal cleanLeaseMoney) {
		this.cleanLeaseMoney = cleanLeaseMoney;
	}

	public int getGrace() {
		return grace;
	}

	public void setGrace(int grace) {
		this.grace = grace;
	}

	public BigDecimal getNominalPrice() {
		return nominalPrice;
	}

	public void setNominalPrice(BigDecimal nominalPrice) {
		this.nominalPrice = nominalPrice;
	}

	public BigDecimal getEquipEndValue() {
		return equipEndValue;
	}

	public void setEquipEndValue(BigDecimal equipEndValue) {
		this.equipEndValue = equipEndValue;
	}

	public BigDecimal getCautionMoney() {
		return cautionMoney;
	}

	public void setCautionMoney(BigDecimal cautionMoney) {
		this.cautionMoney = cautionMoney;
	}

	public BigDecimal getHandlingChargeMoney() {
		return handlingChargeMoney;
	}

	public void setHandlingChargeMoney(BigDecimal handlingChargeMoney) {
		this.handlingChargeMoney = handlingChargeMoney;
	}

	public BigDecimal getInsureMoney() {
		return insureMoney;
	}

	public void setInsureMoney(BigDecimal insureMoney) {
		this.insureMoney = insureMoney;
	}

	public BigDecimal getManagementMoney() {
		return managementMoney;
	}

	public void setManagementMoney(BigDecimal managementMoney) {
		this.managementMoney = managementMoney;
	}

	public BigDecimal getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}

	public BigDecimal getCustCautionMoney() {
		return custCautionMoney;
	}

	public void setCustCautionMoney(BigDecimal custCautionMoney) {
		this.custCautionMoney = custCautionMoney;
	}

	public BigDecimal getSupplierCautionMoney() {
		return supplierCautionMoney;
	}

	public void setSupplierCautionMoney(BigDecimal supplierCautionMoney) {
		this.supplierCautionMoney = supplierCautionMoney;
	}

	public BigDecimal getExpectRent() {
		return expectRent;
	}

	public void setExpectRent(BigDecimal expectRent) {
		this.expectRent = expectRent;
	}

	public BigDecimal getGpsMoney() {
		return gpsMoney;
	}

	public void setGpsMoney(BigDecimal gpsMoney) {
		this.gpsMoney = gpsMoney;
	}

	public BigDecimal getConsultingMoney() {
		return consultingMoney;
	}

	public void setConsultingMoney(BigDecimal consultingMoney) {
		this.consultingMoney = consultingMoney;
	}

	public BigDecimal getBeforeInterest() {
		return beforeInterest;
	}

	public void setBeforeInterest(BigDecimal beforeInterest) {
		this.beforeInterest = beforeInterest;
	}

	public BigDecimal getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(BigDecimal otherIncome) {
		this.otherIncome = otherIncome;
	}

	public BigDecimal getOtherExpenditure() {
		return otherExpenditure;
	}

	public void setOtherExpenditure(BigDecimal otherExpenditure) {
		this.otherExpenditure = otherExpenditure;
	}

	public BigDecimal getIrr() {
		return irr;
	}

	public void setIrr(BigDecimal irr) {
		this.irr = irr;
	}

	public BigDecimal getPlanIrr() {
		return planIrr;
	}

	public void setPlanIrr(BigDecimal planIrr) {
		this.planIrr = planIrr;
	}

	public BigDecimal getEndIrr() {
		return endIrr;
	}

	public void setEndIrr(BigDecimal endIrr) {
		this.endIrr = endIrr;
	}

	public int getPeriodType() {
		return periodType;
	}

	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}

	public BigDecimal getEquipAmt() {
		return equipAmt;
	}

	public void setEquipAmt(BigDecimal equipAmt) {
		this.equipAmt = equipAmt;
	}

	public BigDecimal getFirstPayment() {
		return firstPayment;
	}

	public void setFirstPayment(BigDecimal firstPayment) {
		this.firstPayment = firstPayment;
	}

	public BigDecimal getCautionDeductionMoney() {
		return cautionDeductionMoney;
	}

	public void setCautionDeductionMoney(BigDecimal cautionDeductionMoney) {
		this.cautionDeductionMoney = cautionDeductionMoney;
	}

	public BigDecimal getExpectRentDeduction() {
		return expectRentDeduction;
	}

	public void setExpectRentDeduction(BigDecimal expectRentDeduction) {
		this.expectRentDeduction = expectRentDeduction;
	}

	public Date getLeaseAmtDate() {
		return leaseAmtDate;
	}

	public void setLeaseAmtDate(Date leaseAmtDate) {
		this.leaseAmtDate = leaseAmtDate;
	}

	public Date getFirstPlanDate() {
		return firstPlanDate;
	}

	public void setFirstPlanDate(Date firstPlanDate) {
		this.firstPlanDate = firstPlanDate;
	}

	public int getIssueRateReCal() {
		return issueRateReCal;
	}

	public void setIssueRateReCal(int issueRateReCal) {
		this.issueRateReCal = issueRateReCal;
	}

	public int getIncomeTimes() {
		return incomeTimes;
	}

	public void setIncomeTimes(int incomeTimes) {
		this.incomeTimes = incomeTimes;
	}

	public int getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}

	public CalculateType getCalculateType() {
		return calculateType;
	}

	public void setCalculateType(CalculateType calculateType) {
		this.calculateType = calculateType;
	}

	public int getIsCalBeforeInterest() {
		return isCalBeforeInterest;
	}

	public void setIsCalBeforeInterest(int isCalBeforeInterest) {
		this.isCalBeforeInterest = isCalBeforeInterest;
	}

	public BigDecimal getFinanceCorpus() {
		return financeCorpus;
	}

	public void setFinanceCorpus(BigDecimal financeCorpus) {
		this.financeCorpus = financeCorpus;
	}

	public Date getSecondPlanDate() {
		return secondPlanDate;
	}

	public void setSecondPlanDate(Date secondPlanDate) {
		this.secondPlanDate = secondPlanDate;
	}
	
	@Override
	public void copyConditionBeanValues(ConditionBean cb) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		this.id = cb.getProjId();

		if (cb.getStartDate() != null && cb.getStartDate().length() > 0) {
			this.startDate = dateFormat.parse(Tools.getDBDateStr( cb.getStartDate() ));// 起租日期，计算最后一期的还款日期
		}
//		if (cb.getAccountingStartDate() != null && cb.getAccountingStartDate().length() > 0) {
//			this.leaseAmtDate = dateFormat.parse(cb.getAccountingStartDate()); // 设备款支付日期
//		}
		if (cb.getFirstPlanDate() != null && cb.getFirstPlanDate().length() > 0) {
			this.firstPlanDate = dateFormat.parse(cb.getFirstPlanDate()); // 第1期租金支付日
		}
		if (cb.getSecondPlanDate() != null && cb.getSecondPlanDate().length() > 0) {
			this.secondPlanDate = dateFormat.parse(cb.getSecondPlanDate()); // 第2期租金支付日
		}
//		if (cb.getEndDate() != null && cb.getEndDate().length() > 0) {
//			this.endDate = dateFormat.parse(cb.getEndDate()); // 结束日期
//		}

		this.incomeTimes = cb.getIncomeNumber(); // 还租期数
		this.issueNumber = 12 / Integer.parseInt(cb.getIncomeNumberYear()); // 每期几个月
		this.grace = cb.getGrace(); // 宽限期数，只收利息，由于本金不变所以利息相同
		// 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
		this.periodType = Integer.parseInt(cb.getPeriodType()); // 支付时机 
		this.periodType = this.periodType == 1 ? 0 : 1;// 原有的和这个接口定义的意思正好相反
//		this.isCalBeforeInterest = Integer.parseInt(cb.getIsBeforeInterest());// 是否计算租前息0：不计算，非零：计算
//		this.isCalBeforeInterest = this.isCalBeforeInterest == 1 ? 0 : 1;
//		this.issueRateReCal = Integer.parseInt(cb.getIfretry()); // 是否复算，季付时，原有的和这个接口定义的意思正好相反
		this.issueRateReCal = this.issueRateReCal == 0 ? 1 : 0;// 是否复算利率0：不复算，非零：复算
		this.calculateType = CalculateType.EVEN_CORPUS; // 测算操作类型，调息？

		this.cleanLeaseMoney = new BigDecimal(cb.getCleanLeaseMoney()); // 净融资额
		this.leaseAmt = cleanLeaseMoney; // 融资款
		this.yearRate = new BigDecimal(cb.getYearRate()).divide(new BigDecimal(100)).setScale(Scale.RATE_SCALE, BigDecimal.ROUND_HALF_UP); // 租赁年利率
		// **********************************计算现金流********************************************
		// 期初收入
		this.otherIncome = new BigDecimal(cb.getIncomeNumber()); // 其它收入
		this.returnAmt = new BigDecimal(cb.getReturnAmt()); // 厂商返利
		this.handlingChargeMoney = new BigDecimal(cb.getHandlingChargeMoney()); // 手续费
		this.firstPayment = new BigDecimal(cb.getFirstPayment()); // 首付款
		this.cautionMoney = new BigDecimal(cb.getCautionMoney()); // 租赁保证金[130]=承租人保证金[60]+供应商保证金[70]
		this.managementMoney = new BigDecimal(cb.getManagementMoney()); // 管理费
		
		
		this.beforeInterest = cb.getBeforeInterest() == null||cb.getBeforeInterest().length()==0 ? this.beforeInterest : new BigDecimal(cb.getBeforeInterest()); // 租前息
//		this.gpsMoney = new BigDecimal(cb.getGpsMoney() == null ? "0" : cb.getGpsMoney()); // GPS费用---------
		this.consultingMoney = new BigDecimal(0); // 咨询费--------
		this.insureMoney = new BigDecimal(cb.getInsureMoney()); // 保险费--------

		// 期初支出
		this.equipAmt = new BigDecimal(cb.getEquipAmt()); // 设备款
		this.otherExpenditure = new BigDecimal(cb.getOtherExpenditure()); // 其它支出

		// 期末收入
		this.nominalPrice = new BigDecimal(cb.getNominalPrice()); // 留够价款
		this.equipEndValue = new BigDecimal(cb.getEquipEndValue()); // 设备残值，期末余值
		// 期末支出
		this.cautionDeductionMoney = new BigDecimal(cb.getCautionDeductionMoney()); // 保证金抵扣金额
		//this.expectRent = new BigDecimal(cb.getExpectRent()); // 预收租金金额
		//this.expectRentDeduction = new BigDecimal(cb.getExpectRentDeduction()); // 预收租金抵扣
		// 支出保证金抵扣金额+支出保证金退还=[50]+80=[60]+[70]=承租人保证金+供应商保证金
		// 支出预收租金抵扣+支出预收租金退还=[30]+10=[40]=预收租金金额
		// **********************************计算现金流********************************************
		this.irr = new BigDecimal(cb.getIrr()); // 内部收益率
		this.planIrr = cb.getPlanIrr() == null ? BigDecimal.ZERO : new BigDecimal(cb.getPlanIrr() == null || cb.getPlanIrr().length()<=0 ? "0" : cb.getPlanIrr() ); // 财务收益率
		this.endIrr = cb.getPlanIrr() == null ? BigDecimal.ZERO : new BigDecimal(cb.getPlanIrr() == null || cb.getPlanIrr().length()<=0 ? "0" : cb.getPlanIrr()  ); // 合同结束IRR
		
	}
}
