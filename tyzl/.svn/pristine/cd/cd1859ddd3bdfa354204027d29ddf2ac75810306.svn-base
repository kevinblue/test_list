package com.reckon.entity.contract.reckon.condition;

import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 孙传良
 * @date 2013-3-17下午05:57:39
 * @info
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同商务条件")
@Table(name = "CONTRACT_CONDITION_TEMP")
//implements Condition 
public class ContractConditionTemp {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name = "合同号")
	@Column(name = "CONTRACT_ID")
	private String contractId;
	
	@Column(name = "DOC_ID", length = 510)
	@FieldName(name="文档号")
	private String docId;
	
	/*
	 * 授信条件
	 * */
	@FieldName(name = "设备款")
	@Column(name="EQUIP_AMT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal equipAmt;
	
	@FieldName(name = "首付款")
	@Column(name="FIRST_PAYMENT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal firstPayment;
		
	@FieldName(name = "首付比例")
	@Column(name="FIRST_PAYMENT_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal firstPaymentRatio;
	
	@FieldName(name = "期末余值")
	@Column(name="EQUIP_END_VALUE", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal equipEndValue;
	
	@FieldName(name = "净融资额")
	@Column(name="CLEAN_LEASE_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal cleanLeaseMoney;
	
	@FieldName(name = "期初付款总计")
	@Column(name="FIRST_PAYMENT_TOTAL", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal firstPaymentTotal;
	
	@FieldName(name = "净授信额")
	@Column(name="CLEAN_CREDIT_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal cleanCreditMoney;
	
	@FieldName(name = "净授信比例")
	@Column(name="CLEAN_CREDIT_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal cleanCreditRatio;
	
	/*
	 * 租金推算方式
	 * */
	
	@ManyToOne
	@FieldName(name="租金计算方式")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
	
	@ManyToOne
	@FieldName(name="租金推算方法")
	@JoinColumn(name="RENT_OR_RATE")
	private DictionaryData rentOrRate;
	
	@FieldName(name="测算租金/利率")
	@Column(name="RENT_OR_RATE_VALUE", precision = 22)
	private BigDecimal rentOrRateValue;
	
	@ManyToOne
	@FieldName(name="期初/期末")
	@JoinColumn(name="PERIOD_TYPE")
	private DictionaryData periodType;
	
	@ManyToOne
	@FieldName(name="租金支付类型")
	@JoinColumn(name="INCOME_NUMBER_YEAR")
	private DictionaryData incomeNumberYear;
	
	@FieldName(name="还租次数(年)")
	@Column(name="INCOME_NUMBER")
	private Integer incomeNumber;
	
	@FieldName(name="宽限期")
	@Column(name="GRACE")
	private Integer grace;
	
	@FieldName(name="租赁期限(月)")
	@Column(name="LEASE_TERM")
	private Integer leaseTerm;
	
	@ManyToOne
	@FieldName(name="利率计算方式")
	@JoinColumn(name="RATE_FLOAT_TYPE") 
	private DictionaryData rateFloatType;
	
	@FieldName(name="利率调整值")
	@Column(name="RATE_FLOAT_AMT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal rateFloatAmt;
	
	@FieldName(name="基准利率")
	@Column(name="BASE_RATE", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal baseRate;
	
	@FieldName(name="年利率")
	@Column(name="YEAR_RATE", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal yearRate;
	
	/*
	 * 测算条件
	 * */
	@Column(name = "START_DATE", length = 510)
	@FieldName(name="起租日")
	private String startDate; 
	
	@Column(name = "LEASE_AMT_DATE", length = 510)
	@FieldName(name="付款日")
	private String leaseAmtDate;
	
	@Column(name = "FIRST_PLAN_DATE", length = 510)
	@FieldName(name="第一期租金支付日")
	private String firstPlanDate;
	
	@Column(name = "SECOND_PLAN_DATE", length = 510)
	@FieldName(name="第二期租金支付日")
	private String secondPlanDate;
	
	/*
	 * 资金信息
	 * */
	
	@FieldName(name="资产管理费比例")
	@Column(name="ASSETS_RATIO", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal assetsRatio;
	
	@FieldName(name="资产管理费")
	@Column(name="ASSETS_MONEY", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal assetsMoney;
	
	@FieldName(name="财务顾问费比例")
	@Column(name="ADVISER_RATIO", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal adviserRatio;
	
	@FieldName(name="财务顾问费")
	@Column(name="ADVISER_MONEY", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal adviserMoney;
	
	@FieldName(name="手续费")
	@Column(name="HANDLING_CHARGE_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal handlingChargeMoney;
	
	@FieldName(name="手续费比例")
	@Column(name="HANDLING_CHARGE_MONEY_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal handlingChargeMoneyRatio;
	
	@FieldName(name="管理费")
	@Column(name="MANAGEMENT_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal managementMoney;
	
	@FieldName(name="管理费比例")
	@Column(name="MANAGEMENT_MONEY_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal managementMoneyRatio;
	
	@FieldName(name="保证金")
	@Column(name="CAUTION_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal cautionMoney; 
	
	@FieldName(name="保证金比例")
	@Column(name="CAUTION_DEDUCTION_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal cautionDeductionRatio;
	
	@FieldName(name="保证金抵扣金额")
	@Column(name="CAUTION_DEDUCTION_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal cautionDeductionMoney;
	
	@FieldName(name="保证金退还金额")
	@Column(name="CAUTION_MONEY_REMAIN", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal cautionMoneyRemain;
	
	@ManyToOne
	@FieldName(name="保险计算方式")
	@JoinColumn(name="INSURE_MONEY_TYPE")
	private DictionaryData insureMoneyType;
	 
	@FieldName(name="保险费")
	@Column(name="INSURE_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal insureMoney;
	
	@FieldName(name="保险费(承租人)")
	@Column(name="INSURANCE_LESSEE", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal insuranceLessee;
	
	@FieldName(name="保险费(我司)")
	@Column(name="insurance_lessor", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal insuranceLessor;
	
	@FieldName(name="名义货价")
	@Column(name="NOMINAL_PRICE", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal nominalPrice; 
	
	@FieldName(name="租前息金额")
	@Column(name="BEFORE_INTEREST", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal beforeInterest;
	
	@FieldName(name="其他收入")
	@Column(name="OTHER_INCOME", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal otherIncome;
	
	@FieldName(name="其他支出")
	@Column(name="OTHER_EXPENDITURE", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal otherExpenditure;
	
	@FieldName(name = "厂商返利")
	@Column(name = "RETURN_AMT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal returnAmt;
	
	/*
	 * 调息/罚息
	 * */
	
	@ManyToOne
	@FieldName(name="调息生效节点")
	@JoinColumn(name="ADJUST_STYLE")
	private DictionaryData adjustStyle; 
	
	@FieldName(name="罚息利率")
	@Column(name="PENA_RATE", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal penaRate;
	
	@FieldName(name="免罚息天数")
	@Column(name="FREE_DEFA_INTER_DAY")
	private Integer freeDefaInterDay;
	
	/*
	 * 收益
	 * */
	@FieldName(name="内部收益率")
	@Column(name="IRR", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal irr; 
	
	@FieldName(name="财务收益率")
	@Column(name="PLAN_IRR", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal planIrr;
	
	@FieldName(name="项目粗利")
	@Column(name="GROSS_PROFIT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal grossProfit;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	/*
	 * 预留字段
	 * */
	
	@FieldName(name = "预留字段一String")
	@Column(name="RESERVED_FIELD1", length=20)
	private String reservedField1;
	
	@FieldName(name = "预留字段二String")
	@Column(name="RESERVED_FIELD2", length=20)
	private String reservedField2;
	
	@FieldName(name = "预留字段三String")
	@Column(name="RESERVED_FIELD3", length=20)
	private String reservedField3;
	
	@FieldName(name = "预留字段四String")
	@Column(name="RESERVED_FIELD4", length=20)
	private String reservedField4;
	
	@FieldName(name="预留字段五BigDecimal")
	@Column(name="RESERVED_FIELD5", precision = 22)
	private BigDecimal reservedField5;
	
	@FieldName(name="预留字段六BigDecimal")
	@Column(name="RESERVED_FIELD6", precision = 22)
	private BigDecimal reservedField6;
	
	@FieldName(name="预留字段七BigDecimal")
	@Column(name="RESERVED_FIELD7", precision = 22)
	private BigDecimal reservedField7;
	
	@FieldName(name="预留字段八BigDecimal")
	@Column(name="RESERVED_FIELD8", precision = 22)
	private BigDecimal reservedField8;
	
	@FieldName(name="预留字段九Integer")
	@Column(name="RESERVED_FIELD9")
	private Integer reservedField9;
	
	@FieldName(name="预留字段十Integer")
	@Column(name="RESERVED_FIELD10")
	private Integer reservedField10;
	
	@FieldName(name="预留字段十一Integer")
	@Column(name="RESERVED_FIELD11")
	private Integer reservedField11;
	
	@FieldName(name="预留字段十二Integer")
	@Column(name="RESERVED_FIELD12")
	private Integer reservedField12;
	
	@FieldName(name = "实际授信月数")
    @Column(name = "CREDIT_MONTHS", length = 20)
    private String creditMonths ;
	
	@FieldName(name = "保证金抵扣期数")
    @Column(name = "DC_NUM", length = 20)
    private int dcNum ;
	
	@FieldName(name = "预计结束日期")
	@Column(name = "END_DATE", length = 20)
	private String endDate;
	
	@FieldName(name = "已知租金法json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "JSON_KNOWING_RENT_PLAN_STR")
	private String json_knowing_rent_plan_str;
	
	@FieldName(name = "已知本金法json")
	@Lob 
	@Type(type = "org.hibernate.type.StringClobType")
	@Basic(fetch=FetchType.LAZY)
	@Column(name = "JSON_KNOWING_CORPUS_PLAN_STR")
	private String json_knowing_corpus_plan_str;

	public String getJson_knowing_rent_plan_str() {
		return json_knowing_rent_plan_str;
	}

	public void setJson_knowing_rent_plan_str(String json_knowing_rent_plan_str) {
		this.json_knowing_rent_plan_str = json_knowing_rent_plan_str;
	}

	public String getJson_knowing_corpus_plan_str() {
		return json_knowing_corpus_plan_str;
	}

	public void setJson_knowing_corpus_plan_str(String json_knowing_corpus_plan_str) {
		this.json_knowing_corpus_plan_str = json_knowing_corpus_plan_str;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreditMonths() {
		return creditMonths;
	}

	public void setCreditMonths(String creditMonths) {
		this.creditMonths = creditMonths;
	}

	public int getDcNum() {
		return dcNum;
	}

	public void setDcNum(int dcNum) {
		this.dcNum = dcNum;
	}
	
	public BigDecimal getReturnAmt() {
		return returnAmt;
	}

	public void setReturnAmt(BigDecimal returnAmt) {
		this.returnAmt = returnAmt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	public BigDecimal getFirstPaymentRatio() {
		return firstPaymentRatio;
	}

	public void setFirstPaymentRatio(BigDecimal firstPaymentRatio) {
		this.firstPaymentRatio = firstPaymentRatio;
	}

	public BigDecimal getEquipEndValue() {
		return equipEndValue;
	}

	public void setEquipEndValue(BigDecimal equipEndValue) {
		this.equipEndValue = equipEndValue;
	}

	public BigDecimal getCleanLeaseMoney() {
		return cleanLeaseMoney;
	}

	public void setCleanLeaseMoney(BigDecimal cleanLeaseMoney) {
		this.cleanLeaseMoney = cleanLeaseMoney;
	}

	public BigDecimal getFirstPaymentTotal() {
		return firstPaymentTotal;
	}

	public void setFirstPaymentTotal(BigDecimal firstPaymentTotal) {
		this.firstPaymentTotal = firstPaymentTotal;
	}

	public BigDecimal getCleanCreditMoney() {
		return cleanCreditMoney;
	}

	public void setCleanCreditMoney(BigDecimal cleanCreditMoney) {
		this.cleanCreditMoney = cleanCreditMoney;
	}

	public BigDecimal getCleanCreditRatio() {
		return cleanCreditRatio;
	}

	public void setCleanCreditRatio(BigDecimal cleanCreditRatio) {
		this.cleanCreditRatio = cleanCreditRatio;
	}

	public DictionaryData getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(DictionaryData settleMethod) {
		this.settleMethod = settleMethod;
	}

	public DictionaryData getRentOrRate() {
		return rentOrRate;
	}

	public void setRentOrRate(DictionaryData rentOrRate) {
		this.rentOrRate = rentOrRate;
	}

	public BigDecimal getRentOrRateValue() {
		return rentOrRateValue;
	}

	public void setRentOrRateValue(BigDecimal rentOrRateValue) {
		this.rentOrRateValue = rentOrRateValue;
	}

	public DictionaryData getPeriodType() {
		return periodType;
	}

	public void setPeriodType(DictionaryData periodType) {
		this.periodType = periodType;
	}

	public DictionaryData getIncomeNumberYear() {
		return incomeNumberYear;
	}

	public void setIncomeNumberYear(DictionaryData incomeNumberYear) {
		this.incomeNumberYear = incomeNumberYear;
	}

	public Integer getIncomeNumber() {
		return incomeNumber;
	}

	public void setIncomeNumber(Integer incomeNumber) {
		this.incomeNumber = incomeNumber;
	}

	public Integer getGrace() {
		return grace;
	}

	public void setGrace(Integer grace) {
		this.grace = grace;
	}

	public Integer getLeaseTerm() {
		return leaseTerm;
	}

	public void setLeaseTerm(Integer leaseTerm) {
		this.leaseTerm = leaseTerm;
	}

	public DictionaryData getRateFloatType() {
		return rateFloatType;
	}

	public void setRateFloatType(DictionaryData rateFloatType) {
		this.rateFloatType = rateFloatType;
	}

	public BigDecimal getRateFloatAmt() {
		return rateFloatAmt;
	}

	public void setRateFloatAmt(BigDecimal rateFloatAmt) {
		this.rateFloatAmt = rateFloatAmt;
	}

	public BigDecimal getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getLeaseAmtDate() {
		return leaseAmtDate;
	}

	public void setLeaseAmtDate(String leaseAmtDate) {
		this.leaseAmtDate = leaseAmtDate;
	}

	public String getFirstPlanDate() {
		return firstPlanDate;
	}

	public void setFirstPlanDate(String firstPlanDate) {
		this.firstPlanDate = firstPlanDate;
	}
	
	
	
	public String getSecondPlanDate() {
		return secondPlanDate;
	}

	public void setSecondPlanDate(String secondPlanDate) {
		this.secondPlanDate = secondPlanDate;
	}

	public BigDecimal getHandlingChargeMoney() {
		return handlingChargeMoney;
	}

	public void setHandlingChargeMoney(BigDecimal handlingChargeMoney) {
		this.handlingChargeMoney = handlingChargeMoney;
	}

	public BigDecimal getHandlingChargeMoneyRatio() {
		return handlingChargeMoneyRatio;
	}

	public void setHandlingChargeMoneyRatio(BigDecimal handlingChargeMoneyRatio) {
		this.handlingChargeMoneyRatio = handlingChargeMoneyRatio;
	}

	public BigDecimal getManagementMoney() {
		return managementMoney;
	}

	public void setManagementMoney(BigDecimal managementMoney) {
		this.managementMoney = managementMoney;
	}

	public BigDecimal getManagementMoneyRatio() {
		return managementMoneyRatio;
	}

	public void setManagementMoneyRatio(BigDecimal managementMoneyRatio) {
		this.managementMoneyRatio = managementMoneyRatio;
	}

	public BigDecimal getCautionMoney() {
		return cautionMoney;
	}

	public void setCautionMoney(BigDecimal cautionMoney) {
		this.cautionMoney = cautionMoney;
	}

	public BigDecimal getCautionDeductionRatio() {
		return cautionDeductionRatio;
	}

	public void setCautionDeductionRatio(BigDecimal cautionDeductionRatio) {
		this.cautionDeductionRatio = cautionDeductionRatio;
	}

	public BigDecimal getCautionDeductionMoney() {
		return cautionDeductionMoney;
	}

	public void setCautionDeductionMoney(BigDecimal cautionDeductionMoney) {
		this.cautionDeductionMoney = cautionDeductionMoney;
	}

	public BigDecimal getCautionMoneyRemain() {
		return cautionMoneyRemain;
	}

	public void setCautionMoneyRemain(BigDecimal cautionMoneyRemain) {
		this.cautionMoneyRemain = cautionMoneyRemain;
	}

	
	
	public DictionaryData getInsureMoneyType() {
		return insureMoneyType;
	}

	public void setInsureMoneyType(DictionaryData insureMoneyType) {
		this.insureMoneyType = insureMoneyType;
	}

	public BigDecimal getInsureMoney() {
		return insureMoney;
	}

	public void setInsureMoney(BigDecimal insureMoney) {
		this.insureMoney = insureMoney;
	}

	public BigDecimal getInsuranceLessee() {
		return insuranceLessee;
	}

	public void setInsuranceLessee(BigDecimal insuranceLessee) {
		this.insuranceLessee = insuranceLessee;
	}

	public BigDecimal getInsuranceLessor() {
		return insuranceLessor;
	}

	public void setInsuranceLessor(BigDecimal insuranceLessor) {
		this.insuranceLessor = insuranceLessor;
	}

	public BigDecimal getNominalPrice() {
		return nominalPrice;
	}

	public void setNominalPrice(BigDecimal nominalPrice) {
		this.nominalPrice = nominalPrice;
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

	public DictionaryData getAdjustStyle() {
		return adjustStyle;
	}

	public void setAdjustStyle(DictionaryData adjustStyle) {
		this.adjustStyle = adjustStyle;
	}

	public BigDecimal getPenaRate() {
		return penaRate;
	}

	public void setPenaRate(BigDecimal penaRate) {
		this.penaRate = penaRate;
	}

	public Integer getFreeDefaInterDay() {
		return freeDefaInterDay;
	}

	public void setFreeDefaInterDay(Integer freeDefaInterDay) {
		this.freeDefaInterDay = freeDefaInterDay;
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

	public BigDecimal getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}
	
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getReservedField1() {
		return reservedField1;
	}

	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}

	public String getReservedField2() {
		return reservedField2;
	}

	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}

	public String getReservedField3() {
		return reservedField3;
	}

	public void setReservedField3(String reservedField3) {
		this.reservedField3 = reservedField3;
	}

	public String getReservedField4() {
		return reservedField4;
	}

	public void setReservedField4(String reservedField4) {
		this.reservedField4 = reservedField4;
	}

	public BigDecimal getReservedField5() {
		return reservedField5;
	}

	public void setReservedField5(BigDecimal reservedField5) {
		this.reservedField5 = reservedField5;
	}

	public BigDecimal getReservedField6() {
		return reservedField6;
	}

	public void setReservedField6(BigDecimal reservedField6) {
		this.reservedField6 = reservedField6;
	}

	public BigDecimal getReservedField7() {
		return reservedField7;
	}

	public void setReservedField7(BigDecimal reservedField7) {
		this.reservedField7 = reservedField7;
	}

	public BigDecimal getReservedField8() {
		return reservedField8;
	}

	public void setReservedField8(BigDecimal reservedField8) {
		this.reservedField8 = reservedField8;
	}

	public Integer getReservedField9() {
		return reservedField9;
	}

	public void setReservedField9(Integer reservedField9) {
		this.reservedField9 = reservedField9;
	}

	public Integer getReservedField10() {
		return reservedField10;
	}

	public void setReservedField10(Integer reservedField10) {
		this.reservedField10 = reservedField10;
	}

	public Integer getReservedField11() {
		return reservedField11;
	}

	public void setReservedField11(Integer reservedField11) {
		this.reservedField11 = reservedField11;
	}

	public Integer getReservedField12() {
		return reservedField12;
	}

	public void setReservedField12(Integer reservedField12) {
		this.reservedField12 = reservedField12;
	}

	public BigDecimal getAssetsRatio() {
		return assetsRatio;
	}

	public void setAssetsRatio(BigDecimal assetsRatio) {
		this.assetsRatio = assetsRatio;
	}

	public BigDecimal getAssetsMoney() {
		return assetsMoney;
	}

	public void setAssetsMoney(BigDecimal assetsMoney) {
		this.assetsMoney = assetsMoney;
	}

	public BigDecimal getAdviserRatio() {
		return adviserRatio;
	}

	public void setAdviserRatio(BigDecimal adviserRatio) {
		this.adviserRatio = adviserRatio;
	}

	public BigDecimal getAdviserMoney() {
		return adviserMoney;
	}

	public void setAdviserMoney(BigDecimal adviserMoney) {
		this.adviserMoney = adviserMoney;
	}
	
}
