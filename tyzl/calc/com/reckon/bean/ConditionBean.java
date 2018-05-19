package com.reckon.bean;

import java.math.BigDecimal;

import com.tenwa.business.entity.User;

/**
 * 
 * @author 史鸿飞
 * @version 1.0
 * @copyright (C) TENWA 2011
 * @date 2011-2-17 交易结构临时表，正式表数据传值载体
 * 
 * @copyright (C) TENWA 2013
 * @version 2.0
 * @author 孙传良
 * @date 2014-09-30 下午14:01:13
 * 
 * @copyright (C) TENWA 2014
 * @version 3.0【针对TENWA5.1租金测算的字段重新做了整合，如果在此基础上做任何修改请标注修改原因及代码注释！写好注释才能写好代码！】
 * @author 谢永伦<eastsea_20@163.com>
 * @date 2014-09-30 下午14:01:13
 */
public class ConditionBean {
	
	/**
	 * 测算前置载体属性
	 */
	private String id;//ID
	private String docId;//docId
	private String contractId;//合同信息编号
	private String projId;//项目编号
	private String custId;//客户编号
	
	
	//*********************************************************************************【授信条件开始：】
	private String equipAmt;//设备款
	private String firstPaymentRatio;//首付款比率
	private String firstPayment;//首付款
	private String equipEndValue;//期末残值
	private String cleanLeaseMoney;//净融资额 
	private String firstPaymentTotal;//期初付款总计
	private BigDecimal cleanCreditMoney;//净授信额
	private BigDecimal cleanCreditRatio;//净授信比例
	//*********************************************************************************【授信条件结束！】
	
	
	//*********************************************************************************【租金推算方式开始：】
	private String settleMethod;//租金计算方式(测算方式)
	private String rentOrRate;//租金推算方法(已知利率算租金、已知租金算利率、任意现金流)
	private BigDecimal rentOrRateValue = BigDecimal.ZERO;//测算租金/利率(已知利率算租金、已知租金算利率、任意现金流的值)
	private String periodType;// 期初（期末）支付 // 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
	private String incomeNumberYear = "0";//租金支付类型
	private int incomeNumber;//还租次数(年)
	private int grace;//宽限期数
	private int leaseTerm;// 租赁期限（月）
	private String rateFloatType;//利率计算方式(利率浮动类型)
	private String rateFloatAmt = "0"; //利率调整值
	private BigDecimal baseRate;//基准利率
	private String yearRate;//租赁年利率 
	private int remainLeaseTerm;// 剩余租赁期限（月） 特殊规则专用
	private BigDecimal remainCorpus;// 剩余本金） 特殊规则专用
	//*********************************************************************************【租金推算方式结束！】
	
	//*********************************************************************************【测算条件开始：】
	private String startDate;//起租日
	private String leaseAmtDate;//付款日(融资款/设备款支付日期)
	private String firstPlanDate;//第一期租金支付日
	private String secondPlanDate;//第二期租金支付日
	private String endDate;
	//*********************************************************************************【测算条件结束！】
	
	//*********************************************************************************【资金信息开始：】
	private String handlingChargeRatio;//咨询服务费
	private String handlingChargeMoney;//咨询服务费
	private String assetsratio;
	private String assetsmoney;
	private String adviserratio;
	private String advisermoney;
	private String managementMoneyRatio;//手续费
	private String managementMoney;//手续费
	private String cautionMoneyRatio;//保证金比例
	private String cautionMoney;//保证金
	private String cautionDeductionRatio;//保证金抵扣比例【5.1中不使用该字段】
	private String cautionDeductionMoney;//保证金抵扣金额
	private String cautionMoneyRemain;//保证金退还金额
	private String insureMoneyType;//保险计算方式
	private String insureMoney;// 保险费
	private BigDecimal insuranceLessee;//保险费(承租人)
	private BigDecimal insuranceLessor;//保险费(我司)
	private String nominalPrice;//名义货价
	private String beforeInterest;//租前息金额
	private String otherIncome;//其它收入
	private String otherExpenditure;//其它支出
	private String returnAmt;//厂商返利
	//*********************************************************************************【资金信息结束！】
	
	
	//*********************************************************************************【调息/罚息开始：】
	private String adjustStyle = "next_list";//调息生效节点(调息方式)
	private String penaRate;//罚息利率(万分之)
	private String freeDefaInterDay;//免罚息天数
	//*********************************************************************************【调息/罚息结束！】
	
	
	//*********************************************************************************【收益开始：】
	private String irr;//内部收益率(IRR)
	private String planIrr;//财务收益率
	private BigDecimal grossProfit;//项目粗利
	//*********************************************************************************【收益结束！】
	
	
	//*********************************************************************************【隐藏字段开始：】
	private int dcNum = 0;//保证金抵扣期数
	private String creditMonths = "";//实际授信月数
	
	private String creator; // 登记人
	private String createDate; // 登记时间
	private String modifyDate; // 更新日期
	private String modificator; // 更新人
	//*********************************************************************************【隐藏字段结束！】
	
	
	/***********************************************************************************
	 * 暂时未使用字段(历史版本字段)
	 */
//	private String isMerger;// 是否合并起租
//	private boolean isChangeDate = true;//租金支付表是否按照第一期租金支付日做平移 默认 需要平移
//	private String insureType; //保险计算方式(老版本的字段)
//	private BigDecimal handlingChargeMoneyRatio;
//	private String insuremoneyType; 
//	private String leaseAmt;//融资款
//	private String leaseMoney;//租赁本金
//	private String insurer; // 保险公司
//	private String pmtEndValue; //租金测算pmt公式后期测算代入的值,相当于期末残值,默认为0
//	private String endIrr;// 合同结束时的实际IRR
	
	/**
	 * 
	 * <p>ConditionBean ID。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 
	 * <p>ConditionBean ID。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * <p>流程文档号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getDocId() {
		return docId;
	}
	/**
	 * 
	 * <p>流程文档号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	/**
	 * 
	 * <p>合同号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getContractId() {
		return contractId;
	}
	/**
	 * 
	 * <p>合同号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	/**
	 * 
	 * <p>项目号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getProjId() {
		return projId;
	}
	/**
	 * 
	 * <p>项目号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setProjId(String projId) {
		this.projId = projId;
	}
	
	/**
	 * 
	 * <p>客户编号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * 
	 * <p>客户编号（不重复、唯一字段）。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	//*********************************************************************************【授信条件开始：】
	/**
	 * 
	 * <p>设备款。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getEquipAmt() {
		return equipAmt;
	}
	/**
	 * 
	 * <p>设备款。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setEquipAmt(String equipAmt) {
		this.equipAmt = equipAmt;
	}
	
	/**
	 * 
	 * <p>首付款比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getFirstPaymentRatio() {
		return firstPaymentRatio;
	}
	/**
	 * 
	 * <p>首付款比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setFirstPaymentRatio(String firstPaymentRatio) {
		this.firstPaymentRatio = firstPaymentRatio;
	}
	
	/**
	 * 
	 * <p>首付款。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getFirstPayment() {
		return firstPayment;
	}
	/**
	 * 
	 * <p>首付款。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setFirstPayment(String firstPayment) {
		this.firstPayment = firstPayment;
	}
	
	/**
	 * 
	 * <p>期末残(余)值。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getEquipEndValue() {
		return defaultZero(equipEndValue);
	}
	/**
	 * 
	 * <p>期末残(余)值。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setEquipEndValue(String equipEndValue) {
		this.equipEndValue = equipEndValue;
	}
	
	/**
	 * 
	 * <p>净融资额(测算使用的总剩余本金)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCleanLeaseMoney() {
		return defaultZero(cleanLeaseMoney);
	}
	/**
	 * 
	 * <p>净融资额(测算使用的总剩余本金)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCleanLeaseMoney(String cleanLeaseMoney) {
		this.cleanLeaseMoney = cleanLeaseMoney;
	}
	
	/**
	 * 
	 * <p>期初付款总计。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getFirstPaymentTotal() {
		return firstPaymentTotal;
	}
	/**
	 * 
	 * <p>期初付款总计。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setFirstPaymentTotal(String firstPaymentTotal) {
		this.firstPaymentTotal = firstPaymentTotal;
	}
	
	/**
	 * 
	 * <p>净授信额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getCleanCreditMoney() {
		return cleanCreditMoney;
	}
	/**
	 * 
	 * <p>净授信额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCleanCreditMoney(BigDecimal cleanCreditMoney) {
		this.cleanCreditMoney = cleanCreditMoney;
	}

	/**
	 * 
	 * <p>净授信比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getCleanCreditRatio() {
		return cleanCreditRatio;
	}
	/**
	 * 
	 * <p>净授信比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCleanCreditRatio(BigDecimal cleanCreditRatio) {
		this.cleanCreditRatio = cleanCreditRatio;
	}
	//*********************************************************************************【授信条件结束！】
	
	//*********************************************************************************【租金推算方式开始：】
	/**
	 * 
	 * <p>租金计算方式(测算方式)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%settle_method%'
	 *  NAME_	CODE_
	 * 1.不规则	irregular_rent
	 * 2.均息法	even_interest
	 * 3.等额租金	even_rent
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getSettleMethod() {
		return settleMethod;
	}
	/**
	 * 
	 * <p>租金计算方式(测算方式)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%settle_method%'
	 *  NAME_	CODE_
	 * 1.不规则	irregular_rent
	 * 2.均息法	even_interest
	 * 3.等额租金	even_rent
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}
	
	/**
	 * 
	 * <p>租金推算方法(已知利率算租金、已知租金算利率、任意现金流)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%rent_or_rate%'
	 *  NAME_	CODE_
	 * 1.按年利率计算租金	rate
	 * 2.按租金计算年利率	rent
	 * 3.已知租金规则测算	knowing_rent
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getRentOrRate() {
		return rentOrRate;
	}
	/**
	 * 
	 * <p>租金推算方法(已知利率算租金、已知租金算利率、任意现金流)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%rent_or_rate%'
	 *  NAME_	CODE_
	 * 1.按年利率计算租金	rate
	 * 2.按租金计算年利率	rent
	 * 3.已知租金规则测算	knowing_rent
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setRentOrRate(String rentOrRate) {
		this.rentOrRate = rentOrRate;
	}
	
	/**
	 * 
	 * <p>测算租金/利率(已知利率算租金、已知租金算利率、任意现金流的值)。</p>
	 * <p>。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getRentOrRateValue() {
		return rentOrRateValue;
	}
	/**
	 * 
	 * <p>测算租金/利率(已知利率算租金、已知租金算利率、任意现金流的值)。</p>
	 * <p>。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setRentOrRateValue(BigDecimal rentOrRateValue) {
		this.rentOrRateValue = rentOrRateValue;
	}
	
	/**
	 * 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
	 * @return
	 */
	public String getPeriodType() {
		return defaultZero(periodType);
	}
	/**
	 * 起租类型  注意: 期初 数字 1 字符串 period_type_1  #分割线#  期末 数字0 字符串 period_type_0
	 * @return
	 */
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	
	/**
	 * 
	 * <p>租金支付类型。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%income_number_year%'
	 *  NAME_	CODE_
	 * 1.月  付	income_1
	 * 2.季  付	income_3
	 * 3.半年付	income_6
	 * 4.双月付	income_2
	 * 5.年  付	income_12
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getIncomeNumberYear() {
		return defaultZero(incomeNumberYear);
	}
	/**
	 * 
	 * <p>租金支付类型。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%income_number_year%'
	 *  NAME_	CODE_
	 * 1.月  付	income_1
	 * 2.季  付	income_3
	 * 3.半年付	income_6
	 * 4.双月付	income_2
	 * 5.年  付	income_12
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setIncomeNumberYear(String incomeNumberYear) {
		this.incomeNumberYear = incomeNumberYear;
	}
	
	/**
	 * 
	 * <p>还租次数(年)。</p>
	 * <pre>
	 *  NAME_	CODE_
	 * 1.月  付	12次
	 * 2.季  付	4次
	 * 3.半年付	2次
	 * 4.双月付	6次
	 * 5.年  付	1次
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public int getIncomeNumber() {
		return incomeNumber;
	}
	/**
	 * 
	 * <p>还租次数(年)。</p>
	 * <pre>
	 *  NAME_	CODE_
	 * 1.月  付	12次
	 * 2.季  付	4次
	 * 3.半年付	2次
	 * 4.双月付	6次
	 * 5.年  付	1次
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setIncomeNumber(int incomeNumber) {
		this.incomeNumber = incomeNumber;
	}
	
	/**
	 * 
	 * <p>宽限期数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public int getGrace() {
		return grace;
	}
	/**
	 * 
	 * <p>宽限期数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setGrace(int grace) {
		this.grace = grace;
	}
	
	/**
	 * 
	 * <p>租赁期限(月)。</p>
	 * <pre>
	 * 1. 月付情况下 = 1 * 12
	 * 2 .季付情况下 = 3 * 4
	 * 3. 半年付情况下 = 6 * 2
	 * 4 .年付情况下 = 12 * 1
	 * 5 .双月付情况下 = 2 * 6
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public int getLeaseTerm() {
		return leaseTerm;
	}
	/**
	 * 
	 * <p>租赁期限(月)。</p>
	 * <pre>
	 * 1. 月付情况下 = 1 * 12
	 * 2 .季付情况下 = 3 * 4
	 * 3. 半年付情况下 = 6 * 2
	 * 4 .年付情况下 = 12 * 1
	 * 5 .双月付情况下 = 2 * 6
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setLeaseTerm(int leaseTerm) {
		this.leaseTerm = leaseTerm;
	}
	
	/**
	 * 
	 * <p>利率计算方式。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%rate_float_type%'
	 *  NAME_	CODE_
	 * 1.固定利率	fixed
	 * 2.按央行浮动比率	proportion
	 * 3.按央行利率加点	 add
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getRateFloatType() {
		return defaultZero(rateFloatType);
	}
	/**
	 * 
	 * <p>利率计算方式。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%rate_float_type%'
	 *  NAME_	CODE_
	 * 1.固定利率	fixed
	 * 2.按央行浮动比率	proportion
	 * 3.按央行利率加点	 add
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setRateFloatType(String rateFloatType) {
		this.rateFloatType = rateFloatType;
	}
	
	/**
	 * 
	 * <p>利率调整值。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getRateFloatAmt() {
		return defaultZero(rateFloatAmt);
	}
	/**
	 * 
	 * <p>利率调整值。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setRateFloatAmt(String rateFloatAmt) {
		this.rateFloatAmt = rateFloatAmt;
	}
	
	/**
	 * 
	 * <p>基准利率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getBaseRate() {
		return baseRate;
	}
	/**
	 * 
	 * <p>基准利率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}
	
	/**
	 * 
	 * <p>年利率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param yearRate
	 */
	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	/**
	 * 
	 * <p>年利率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param yearRate
	 */
	public String getYearRate() {
		return yearRate;
	}
	//*********************************************************************************【租金推算方式结束！】
	
	
	//*********************************************************************************【测算条件开始：】
	/**
	 * 
	 * <p>起租日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * 
	 * <p>起租日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 
	 * <p>付款日(融资款/设备款支付日期)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getLeaseAmtDate() {
		return defaultZero(leaseAmtDate);
	}
	/**
	 * 
	 * <p>付款日(融资款/设备款支付日期)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setLeaseAmtDate(String leaseAmtDate) {
		this.leaseAmtDate = leaseAmtDate;
	}
	
	/**
	 * 
	 * <p>第一期租金支付日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getFirstPlanDate() {
		return defaultZero(firstPlanDate);
	}
	/**
	 * 
	 * <p>第一期租金支付日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setFirstPlanDate(String firstPlanDate) {
		this.firstPlanDate = firstPlanDate;
	}
	
	/**
	 * 
	 * <p>第二期租金支付日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getSecondPlanDate() {
		return secondPlanDate;
	}
	/**
	 * 
	 * <p>第二期租金支付日。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setSecondPlanDate(String secondPlanDate) {
		this.secondPlanDate = secondPlanDate;
	}
	//*********************************************************************************【测算条件结束！】
	
	
	//*********************************************************************************【资金信息开始：】
	/**
	 * 
	 * <p>手续费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getHandlingChargeMoney() {
		return handlingChargeMoney;
	}
	/**
	 * 
	 * <p>手续费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setHandlingChargeMoney(String handlingChargeMoney) {
		this.handlingChargeMoney = handlingChargeMoney;
	}
	/**
	 * 
	 * <p>手续费比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getHandlingChargeRatio() {
		return defaultZero(handlingChargeRatio);
	}
	/**
	 * 
	 * <p>手续费比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setHandlingChargeRatio(String handlingChargeRatio) {
		this.handlingChargeRatio = handlingChargeRatio;
	}
	
	/**
	 * 
	 * <p>管理费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getManagementMoney() {
		return defaultZero(managementMoney);
	}
	/**
	 * 
	 * <p>管理费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setManagementMoney(String managementMoney) {
		this.managementMoney = managementMoney;
	}
	/**
	 * 
	 * <p>管理费比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getManagementMoneyRatio() {
		return defaultZero(managementMoneyRatio);
	}
	/**
	 * 
	 * <p>管理费比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setManagementMoneyRatio(String managementMoneyRatio) {
		this.managementMoneyRatio = managementMoneyRatio;
	}
	
	/**
	 * 
	 * <p>保证金。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCautionMoney() {
		return defaultZero(cautionMoney);
	}
	/**
	 * 
	 * <p>保证金。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCautionMoney(String cautionMoney) {
		this.cautionMoney = cautionMoney;
	}
	/**
	 * 
	 * <p>保证金比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCautionMoneyRatio() {
		return defaultZero(cautionMoneyRatio);
	}
	/**
	 * 
	 * <p>保证金比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCautionMoneyRatio(String cautionMoneyRatio) {
		this.cautionMoneyRatio = cautionMoneyRatio;
	}
	
	/**
	 * 
	 * <p>保证金抵扣金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCautionDeductionMoney() {
		return defaultZero(cautionDeductionMoney);
	}
	/**
	 * 
	 * <p>保证金抵扣金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCautionDeductionMoney(String cautionDeductionMoney) {
		this.cautionDeductionMoney = cautionDeductionMoney;
	}
	
	/**
	 * 
	 * <p>保证金抵扣比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCautionDeductionRatio() {
		return defaultZero(cautionDeductionRatio);
	}
	/**
	 * 
	 * <p>保证金抵扣比例。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCautionDeductionRatio(String cautionDeductionRatio) {
		this.cautionDeductionRatio = cautionDeductionRatio;
	}
	
	/**
	 * 
	 * <p>保证金退还金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCautionMoneyRemain() {
		return cautionMoneyRemain;
	}
	/**
	 * 
	 * <p>保证金退还金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCautionMoneyRemain(String cautionMoneyRemain) {
		this.cautionMoneyRemain = cautionMoneyRemain;
	}
	
	/**
	 * 
	 * <p>保险计算方式。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%insure_type%'
	 *  NAME_	CODE_
	 * 1.本司付款	insure_type1
	 * 2.客户自保	insure_type3
	 * 3.不投保	 insure_type5
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getInsureMoneyType() {
		return insureMoneyType;
	}
	/**
	 * 
	 * <p>保险计算方式。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%insure_type%'
	 *  NAME_	CODE_
	 * 1.本司付款	insure_type1
	 * 2.客户自保	insure_type3
	 * 3.不投保	 insure_type5
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setInsureMoneyType(String insureMoneyType) {
		this.insureMoneyType = insureMoneyType;
	}
	
	/**
	 * 
	 * <p>保险费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getInsureMoney() {
		return defaultZero(insureMoney);
	}
	/**
	 * 
	 * <p>保险费。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setInsureMoney(String insureMoney) {
		this.insureMoney = insureMoney;
	}
	
	/**
	 * 
	 * <p>保险费(承租人)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getInsuranceLessee() {
		return insuranceLessee;
	}
	/**
	 * 
	 * <p>保险费(承租人)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setInsuranceLessee(BigDecimal insuranceLessee) {
		this.insuranceLessee = insuranceLessee;
	}
	
	/**
	 * 
	 * <p>保险费(我司)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getInsuranceLessor() {
		return insuranceLessor;
	}
	/**
	 * 
	 * <p>保险费(我司)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setInsuranceLessor(BigDecimal insuranceLessor) {
		this.insuranceLessor = insuranceLessor;
	}
	
	/**
	 * 
	 * <p>名义货价。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getNominalPrice() {
		return defaultZero(nominalPrice);
	}
	/**
	 * 
	 * <p>名义货价。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setNominalPrice(String nominalPrice) {
		this.nominalPrice = nominalPrice;
	}
	
	/**
	 * 
	 * <p>租前息金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getBeforeInterest() {
		return beforeInterest;
	}
	/**
	 * 
	 * <p>租前息金额。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setBeforeInterest(String beforeInterest) {
		this.beforeInterest = beforeInterest;
	}
	
	/**
	 * 
	 * <p>其它收入。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getOtherIncome() {
		return defaultZero(otherIncome);
	}
	/**
	 * 
	 * <p>其它收入。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}
	
	/**
	 * 
	 * <p>其它支出。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getOtherExpenditure() {
		return defaultZero(otherExpenditure);
	}
	/**
	 * 
	 * <p>其它支出。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setOtherExpenditure(String otherExpenditure) {
		this.otherExpenditure = otherExpenditure;
	}
	
	/**
	 * 
	 * <p>厂商返利。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getReturnAmt() {
		return defaultZero(returnAmt);
	}
	/**
	 * 
	 * <p>厂商返利。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setReturnAmt(String returnAmt) {
		this.returnAmt = returnAmt;
	}
	//*********************************************************************************【资金信息结束！】
	
	
	//*********************************************************************************【调息/罚息开始：】
	/**
	 * 
	 * <p>调息生效节点(调息方式)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%adjust_style%'
	 *  NAME_	CODE_
	 * 1.次期	next_list
	 * 2.次月	next_month
	 * 3.次日	 next_day
	 * 4.次年	next_year
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getAdjustStyle() {
		return adjustStyle;
	}
	/**
	 * 
	 * <p>调息生效节点(调息方式)。</p>
	 * <pre>
	 * select * from t_dicts_datas where pid_ like '%adjust_style%'
	 *  NAME_	CODE_
	 * 1.次期	next_list
	 * 2.次月	next_month
	 * 3.次日	 next_day
	 * 4.次年	next_year
	 * </pre>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setAdjustStyle(String adjustStyle) {
		this.adjustStyle = adjustStyle;
	}
	

	/**
	 * 
	 * <p>罚息利率(万分之)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getPenaRate() {
		return defaultZero(penaRate);
	}
	/**
	 * 
	 * <p>罚息利率(万分之)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setPenaRate(String penaRate) {
		this.penaRate = penaRate;
	}
	
	/**
	 * 
	 * <p>免罚息天数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getFreeDefaInterDay() {
		return freeDefaInterDay;
	}
	/**
	 * 
	 * <p>免罚息天数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setFreeDefaInterDay(String freeDefaInterDay) {
		this.freeDefaInterDay = freeDefaInterDay;
	}
	//*********************************************************************************【调息/罚息结束！】
	
	//*********************************************************************************【收益开始：】
	/**
	 * 
	 * <p>内部收益率(IRR)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getIrr() {
		return defaultZero(irr);
	}
	/**
	 * 
	 * <p>内部收益率(IRR)。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setIrr(String irr) {
		this.irr = irr;
	}
	
	/**
	 * 
	 * <p>财务收益率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getPlanIrr() {
		return planIrr;
	}
	/**
	 * 
	 * <p>财务收益率。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setPlanIrr(String planIrr) {
		this.planIrr = planIrr;
	}
	
	/**
	 * 
	 * <p>项目粗利。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	/**
	 * 
	 * <p>项目粗利。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	//*********************************************************************************【收益结束！】
	
	//*********************************************************************************【隐藏字段开始：】

	/**
	 * 
	 * <p>保证金抵扣期数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public int getDcNum() {
		return dcNum;
	}
	/**
	 * 
	 * <p>保证金抵扣期数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setDcNum(int dcNum) {
		this.dcNum = dcNum;
	}
	
	/**
	 * 
	 * <p>GET实际授信月数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public String getCreditMonths() {
		return creditMonths;
	}
	/**
	 * 
	 * <p>SET实际授信月数。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @return
	 */
	public void setCreditMonths(String creditMonths) {
		this.creditMonths = creditMonths;
	}
	
	/**
	 * 
	 * <p>创建日期。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * 
	 * <p>创建日期。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	/**
	 * 
	 * <p>创建人。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */

	public String getCreator() {
		return creator;
	}
	/**
	 * 
	 * <p>创建人。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * 
	 * <p>修改人。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public String getModificator() {
		return modificator;
	}
	/**
	 * 
	 * <p>修改人。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public void setModificator(String modificator) {
		this.modificator = modificator;
	}
	/**
	 * 
	 * <p>修改日期。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public String getModifyDate() {
		return modifyDate;
	}
	/**
	 * 
	 * <p>修改日期。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param createDate
	 */
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * 
	 * <p>重写创建人通过User去获取。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param user
	 */
	public void setCreator(User user) {
		creator = user.getId();
	}

	/**
	 * 
	 * <p>重写创建人通过User去获取。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param user
	 */
	public void setModificator(User user) {
		modificator = user.getId();
	}
	//*********************************************************************************【隐藏字段结束！】
	
	/**
	 * 
	 * <p>设置字符串的默认值为0。</p>
	 * @author 谢永伦<eastsea_20@163.com>
	 * @param user
	 */
	private String defaultZero(String param) {
		param = (param == null ? "0" : param);
		param = (param.isEmpty() ? "0" : param);
		return param;
	}
	/*
	 * 期初为租金计划的最后一期在加一期，期末为租金计划最后一期
	 */
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getRemainLeaseTerm() {
		return remainLeaseTerm;
	}
	public void setRemainLeaseTerm(int remainLeaseTerm) {
		this.remainLeaseTerm = remainLeaseTerm;
	}
	public BigDecimal getRemainCorpus() {
		return remainCorpus;
	}
	public void setRemainCorpus(BigDecimal remainCorpus) {
		this.remainCorpus = remainCorpus;
	}
	public String getAssetsratio() {
		return assetsratio;
	}
	public void setAssetsratio(String assetsratio) {
		this.assetsratio = assetsratio;
	}
	public String getAssetsmoney() {
		return assetsmoney;
	}
	public void setAssetsmoney(String assetsmoney) {
		this.assetsmoney = assetsmoney;
	}
	public String getAdviserratio() {
		return adviserratio;
	}
	public void setAdviserratio(String adviserratio) {
		this.adviserratio = adviserratio;
	}
	public String getAdvisermoney() {
		return advisermoney;
	}
	public void setAdvisermoney(String advisermoney) {
		this.advisermoney = advisermoney;
	}
	
	
	
}