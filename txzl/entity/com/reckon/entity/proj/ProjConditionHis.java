package com.reckon.entity.proj;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.reckon.commons.helper.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-6下午02:12:43
 * @info 项目商务条件(历史)
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "项目商务条件(历史)")
@Table(name = "PROJ_CONDITION_HIS")

public class ProjConditionHis {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ProjInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
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
	@Column(name="other_LEASE_MONEY", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal otherLeaseMoney;
	
	@ManyToOne
	@FieldName(name="利率调整方式")
	@JoinColumn(name="RATE_ADJUST_TYPE")
	private DictionaryData rateAdjustType;
	
	
	public DictionaryData getRateAdjustType() {
		return rateAdjustType;
	}

	public void setRateAdjustType(DictionaryData rateAdjustType) {
		this.rateAdjustType = rateAdjustType;
	}
	
	public BigDecimal getOtherLeaseMoney() {
		return otherLeaseMoney;
	}

	public void setOtherLeaseMoney(BigDecimal otherLeaseMoney) {
		this.otherLeaseMoney = otherLeaseMoney;
	}
	
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
	
	@ManyToOne
	@FieldName(name="等本利率计算方式")
	@JoinColumn(name="EVEN_CORPUS_RATE_TYPE") 
	private DictionaryData evenCorpusRateType;
	
	
	public DictionaryData getEvenCorpusRateType() {
		return evenCorpusRateType;
	}

	public void setEvenCorpusRateType(DictionaryData evenCorpusRateType) {
		this.evenCorpusRateType = evenCorpusRateType;
	}
	
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
	@Column(name="RENT_OR_RATE_VALUE", precision = 22, scale = Scale.GENERAL_RATE)
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
	
	@Column(name = "LAST_PLAN_DATE", length = 510)
	@FieldName(name="最后一期租金支付日")
	private String lastPlanDate;
	
	@FieldName(name="厂商保证金")
	@Column(name="FAC_CAUTION_MONEY", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal facCautionMoney; 
	
	@FieldName(name="厂商保证金比例")
	@Column(name="FAC_CAUTION_DEDUCTION_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal facCautionDeductionRatio;
	
	@FieldName(name = "保险费率")
	@Column(name="INSURE_RATIO", precision = 22, scale = Scale.GENERAL_RATE)
	private BigDecimal insureRatio;
	
	public BigDecimal getInsureRatio() {
		return insureRatio;
	}

	public void setInsureRatio(BigDecimal insureRatio) {
		this.insureRatio = insureRatio;
	}
	
	public BigDecimal getFacCautionMoney() {
		return facCautionMoney;
	}

	public void setFacCautionMoney(BigDecimal facCautionMoney) {
		this.facCautionMoney = facCautionMoney;
	}

	public BigDecimal getFacCautionDeductionRatio() {
		return facCautionDeductionRatio;
	}

	public void setFacCautionDeductionRatio(BigDecimal facCautionDeductionRatio) {
		this.facCautionDeductionRatio = facCautionDeductionRatio;
	}
	
	@FieldName(name="厂商抵扣保证金")
	@Column(name="FAC_CAUTION_DEDUCTION_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal facCautionDeductionMoney;
	
	@FieldName(name="厂商退还保证金")
	@Column(name="FAC_CAUTION_MONEY_REMAIN", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal facCautionMoneyRemain;
	
	/*
	 * 资金信息
	 * */
	
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
	
	@FieldName(name = "返点收入")
	@Column(name = "RETURN_POINT_INCOME", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal returnPointIncome;
	
	@FieldName(name = "利息补贴")
	@Column(name = "INTEREST_SUPPORT", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal interestSupport;
	
	@FieldName(name = "提前还款补偿金")
	@Column(name = "ADVANCE_REPAY_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal advanceRepayMoney;
	
	public BigDecimal getReturnPointIncome() {
		return returnPointIncome;
	}

	public void setReturnPointIncome(BigDecimal returnPointIncome) {
		this.returnPointIncome = returnPointIncome;
	}

	public BigDecimal getInterestSupport() {
		return interestSupport;
	}

	public void setInterestSupport(BigDecimal interestSupport) {
		this.interestSupport = interestSupport;
	}
	
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
	
	@FieldName(name = "前期款坐扣")
	@Column(name="DEDUCTION_TYPE", length=200)
	private String deductionType;
	
	/**
	 * 天信新加字段start
	 */
	@FieldName(name="XIRR")
	@Column(name="XIRR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal xirr;
	
	
	@ManyToOne
	@FieldName(name="手续费收取间隔")
	@JoinColumn(name="HAND_HZ")
	private DictionaryData handHz; //手续费收取间隔
	
	@ManyToOne
	@FieldName(name="手续费收取方式")
	@JoinColumn(name="HAND_TYPE")
	private DictionaryData handType; //手续费收取方式
	
	@ManyToOne
	@FieldName(name="手续费计算基数")
	@JoinColumn(name="HAND_MONEY")
	private DictionaryData handMoney; //手续费比例计算基数
	
	@FieldName(name="手续费比例")
	@Column(name="HAND_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal handRatio;
	

	@FieldName(name="宽限期利率调整值")
	@Column(name="GRACE_ADJUST", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal graceAdjust;
	
	@FieldName(name="宽限期年利率")
	@Column(name="GRACE_RATE", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal graceRate;
	
	/**
	 * 天信新加字段end
	 */
	
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
	
	@FieldName(name = "前后状态")
	@JoinColumn(name = "MOD_STATUS")
	@ManyToOne
	private DictionaryData modStatus;

	@FieldName(name = "流程模块")
	@JoinColumn(name = "MOD_REASON")
	@ManyToOne
	private DictionaryData modReason;
	
	@FieldName(name = "实际授信月数")
    @Column(name = "CREDIT_MONTHS", length = 20)
    private String creditMonths ;
	
	@FieldName(name = "保证金抵扣期数")
    @Column(name = "DC_NUM", length = 20)
    private int dcNum ;
	
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
	
	
	
	/*
	 * 保理测算新增商务条件(资金款项)
	 */
	@FieldName(name="应收账款比例")
	@Column(name="EQUIPAMT_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal equipamtRatio; 
	
	@FieldName(name="应收账款受让款比例")
	@Column(name="FACTORING_PAYOUT_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringPayoutRatio; 
	
	@FieldName(name = "咨询服务费")
	@Column(name = "COUNSELING_MONEY", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal counselingMoney;
	
	@FieldName(name="咨询服务费收入比例")
	@Column(name="COUNSELING_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal counselingRatio;
	
	@FieldName(name = "保理费收入")
	@Column(name = "FACTORING_INCOME", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal factoringIncome;
	
	@FieldName(name="保理费收入比例")
	@Column(name="FACTORING_FEE_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringFeeRatio;
	
	@FieldName(name="保险费收入比例")
	@Column(name="INSUREMONEY_RATIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal insuremoneyRatio;
	
	@FieldName(name = "其他收入")
	@Column(name = "other_fee_rec", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal otherFeeRec;
	
	@FieldName(name="其他收入比例")
	@Column(name="other_fee_rec_ratio", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal otherFeeRecRatio;
	
	@FieldName(name = "担保费收入")
	@Column(name = "Factoring_guarantee_fee", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal factoringGuaranteeFee;
	
	@FieldName(name="担保费收入比例")
	@Column(name="Factoring_guarantee_fee_ratio", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringGuaranteefeeRatio;
	
	@FieldName(name = "其他成本")
	@Column(name = "other_fee", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal otherFee;
	
	@FieldName(name="其他成本比例")
	@Column(name="other_fee_ratio", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal otherFeeRatio;
	
	@FieldName(name = "登记费收入")
	@Column(name = "Factoring_register_fee", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal factoringRegisterfee;
	
	@FieldName(name="登记费收入比例")
	@Column(name="Factoring_register_fee_ratio", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringRegisterfeeRatio;
	
	@FieldName(name = "销售额")
	@Column(name = "SALES_VOLUME", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal salesVolume;
	
	@FieldName(name="销售额比例")
	@Column(name="SALES_VOLUME_RADIO", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal salesVolumeRatio;
	
	@FieldName(name = "净融资额")
	@Column(name = "actual_fund", precision = 22, scale = Scale.DEFAULT)
	private BigDecimal actualFund;
	
	@FieldName(name="净融资额比例")
	@Column(name="actual_fund_radio", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal actualFundRadio;
	
	/*
	 * 保理测算新增商务条件(收益)
	 */
	@FieldName(name="平面利率（月）")
	@Column(name="FACTORING_FLAT_RATE_MONTH", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringFlatRateMonth; 
	
	@FieldName(name="名义利率(月)")
	@Column(name="FACTORING_YEAR_RATE_MONTH", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringYearRateMonth; 
	
	@FieldName(name="资金成本（月）")
	@Column(name="FACTORING_FUND_COST_MONTH", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringFundCostMonth;
	
	@FieldName(name="IRR(月)")
	@Column(name="FACTORING_IRR_MONTH", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringirrmonth; 
	
	@FieldName(name="Spread(月)")
	@Column(name="FACTORING_SPREAD_MONTH", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringSpreadMonth; 
	
	@FieldName(name="平面利率(年)")
	@Column(name="FACTORING_FLAT_RATE_YEAR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringFlatRateYear; 
	
	@FieldName(name="名义利率(年)")
	@Column(name="FACTORING_YEAR_RATE_YEAR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringYearRateYear; 
	
	@FieldName(name="资金成本(年)")
	@Column(name="FACTORING_FUND_COST_YEAR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringFundCostYear;
	
	@FieldName(name="IRR(年)")
	@Column(name="FACTORING_IRR_YEAR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringIrrYear; 
	
	@FieldName(name="Spread(年)")
	@Column(name="FACTORING_SPREAD_YEAR", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal factoringSpreadYear;
	
	@FieldName(name="GP")
	@Column(name="GP", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal gp; 
	
	@FieldName(name="保理期限(日)")
	@Column(name="LEASE_TERM_DAY")
	private Integer leaseTermDay;
	
	@FieldName(name="利率浮动")
	@Column(name="RATE_FLOAT", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal rateFloat; 
	
	
	
	public BigDecimal getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(BigDecimal rateFloat) {
		this.rateFloat = rateFloat;
	}

	
	public Integer getLeaseTermDay() {
		return leaseTermDay;
	}

	public void setLeaseTermDay(Integer leaseTermDay) {
		this.leaseTermDay = leaseTermDay;
	}
	

	public BigDecimal getEquipamtRatio() {
		return equipamtRatio;
	}

	public void setEquipamtRatio(BigDecimal equipamtRatio) {
		this.equipamtRatio = equipamtRatio;
	}

	public BigDecimal getFactoringPayoutRatio() {
		return factoringPayoutRatio;
	}

	public void setFactoringPayoutRatio(BigDecimal factoringPayoutRatio) {
		this.factoringPayoutRatio = factoringPayoutRatio;
	}

	public BigDecimal getCounselingMoney() {
		return counselingMoney;
	}

	public void setCounselingMoney(BigDecimal counselingMoney) {
		this.counselingMoney = counselingMoney;
	}

	public BigDecimal getCounselingRatio() {
		return counselingRatio;
	}

	public void setCounselingRatio(BigDecimal counselingRatio) {
		this.counselingRatio = counselingRatio;
	}

	public BigDecimal getFactoringIncome() {
		return factoringIncome;
	}

	public void setFactoringIncome(BigDecimal factoringIncome) {
		this.factoringIncome = factoringIncome;
	}

	public BigDecimal getFactoringFeeRatio() {
		return factoringFeeRatio;
	}

	public void setFactoringFeeRatio(BigDecimal factoringFeeRatio) {
		this.factoringFeeRatio = factoringFeeRatio;
	}

	public BigDecimal getInsuremoneyRatio() {
		return insuremoneyRatio;
	}

	public void setInsuremoneyRatio(BigDecimal insuremoneyRatio) {
		this.insuremoneyRatio = insuremoneyRatio;
	}

	public BigDecimal getOtherFeeRec() {
		return otherFeeRec;
	}

	public void setOtherFeeRec(BigDecimal otherFeeRec) {
		this.otherFeeRec = otherFeeRec;
	}

	public BigDecimal getOtherFeeRecRatio() {
		return otherFeeRecRatio;
	}

	public void setOtherFeeRecRatio(BigDecimal otherFeeRecRatio) {
		this.otherFeeRecRatio = otherFeeRecRatio;
	}

	public BigDecimal getFactoringGuaranteeFee() {
		return factoringGuaranteeFee;
	}

	public void setFactoringGuaranteeFee(BigDecimal factoringGuaranteeFee) {
		this.factoringGuaranteeFee = factoringGuaranteeFee;
	}

	public BigDecimal getFactoringGuaranteefeeRatio() {
		return factoringGuaranteefeeRatio;
	}

	public void setFactoringGuaranteefeeRatio(BigDecimal factoringGuaranteefeeRatio) {
		this.factoringGuaranteefeeRatio = factoringGuaranteefeeRatio;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getOtherFeeRatio() {
		return otherFeeRatio;
	}

	public void setOtherFeeRatio(BigDecimal otherFeeRatio) {
		this.otherFeeRatio = otherFeeRatio;
	}

	public BigDecimal getFactoringRegisterfee() {
		return factoringRegisterfee;
	}

	public void setFactoringRegisterfee(BigDecimal factoringRegisterfee) {
		this.factoringRegisterfee = factoringRegisterfee;
	}

	public BigDecimal getFactoringRegisterfeeRatio() {
		return factoringRegisterfeeRatio;
	}

	public void setFactoringRegisterfeeRatio(BigDecimal factoringRegisterfeeRatio) {
		this.factoringRegisterfeeRatio = factoringRegisterfeeRatio;
	}

	public BigDecimal getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(BigDecimal salesVolume) {
		this.salesVolume = salesVolume;
	}

	public BigDecimal getSalesVolumeRatio() {
		return salesVolumeRatio;
	}

	public void setSalesVolumeRatio(BigDecimal salesVolumeRatio) {
		this.salesVolumeRatio = salesVolumeRatio;
	}

	public BigDecimal getActualFund() {
		return actualFund;
	}

	public void setActualFund(BigDecimal actualFund) {
		this.actualFund = actualFund;
	}

	public BigDecimal getActualFundRadio() {
		return actualFundRadio;
	}

	public void setActualFundRadio(BigDecimal actualFundRadio) {
		this.actualFundRadio = actualFundRadio;
	}

	public BigDecimal getFactoringFlatRateMonth() {
		return factoringFlatRateMonth;
	}

	public void setFactoringFlatRateMonth(BigDecimal factoringFlatRateMonth) {
		this.factoringFlatRateMonth = factoringFlatRateMonth;
	}

	public BigDecimal getFactoringYearRateMonth() {
		return factoringYearRateMonth;
	}

	public void setFactoringYearRateMonth(BigDecimal factoringYearRateMonth) {
		this.factoringYearRateMonth = factoringYearRateMonth;
	}

	public BigDecimal getFactoringFundCostMonth() {
		return factoringFundCostMonth;
	}

	public void setFactoringFundCostMonth(BigDecimal factoringFundCostMonth) {
		this.factoringFundCostMonth = factoringFundCostMonth;
	}

	public BigDecimal getFactoringirrmonth() {
		return factoringirrmonth;
	}

	public void setFactoringirrmonth(BigDecimal factoringirrmonth) {
		this.factoringirrmonth = factoringirrmonth;
	}

	public BigDecimal getFactoringSpreadMonth() {
		return factoringSpreadMonth;
	}

	public void setFactoringSpreadMonth(BigDecimal factoringSpreadMonth) {
		this.factoringSpreadMonth = factoringSpreadMonth;
	}

	public BigDecimal getFactoringFlatRateYear() {
		return factoringFlatRateYear;
	}

	public void setFactoringFlatRateYear(BigDecimal factoringFlatRateYear) {
		this.factoringFlatRateYear = factoringFlatRateYear;
	}

	public BigDecimal getFactoringYearRateYear() {
		return factoringYearRateYear;
	}

	public void setFactoringYearRateYear(BigDecimal factoringYearRateYear) {
		this.factoringYearRateYear = factoringYearRateYear;
	}

	public BigDecimal getFactoringFundCostYear() {
		return factoringFundCostYear;
	}

	public void setFactoringFundCostYear(BigDecimal factoringFundCostYear) {
		this.factoringFundCostYear = factoringFundCostYear;
	}

	public BigDecimal getFactoringIrrYear() {
		return factoringIrrYear;
	}

	public void setFactoringIrrYear(BigDecimal factoringIrrYear) {
		this.factoringIrrYear = factoringIrrYear;
	}

	public BigDecimal getFactoringSpreadYear() {
		return factoringSpreadYear;
	}

	public void setFactoringSpreadYear(BigDecimal factoringSpreadYear) {
		this.factoringSpreadYear = factoringSpreadYear;
	}

	public BigDecimal getGp() {
		return gp;
	}

	public void setGp(BigDecimal gp) {
		this.gp = gp;
	}

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

	public DictionaryData getModStatus() {
		return modStatus;
	}

	public void setModStatus(DictionaryData modStatus) {
		this.modStatus = modStatus;
	}

	public DictionaryData getModReason() {
		return modReason;
	}

	public void setModReason(DictionaryData modReason) {
		this.modReason = modReason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getLastPlanDate() {
		return lastPlanDate;
	}

	public void setLastPlanDate(String lastPlanDate) {
		this.lastPlanDate = lastPlanDate;
	}

	public BigDecimal getFacCautionDeductionMoney() {
		return facCautionDeductionMoney;
	}

	public void setFacCautionDeductionMoney(BigDecimal facCautionDeductionMoney) {
		this.facCautionDeductionMoney = facCautionDeductionMoney;
	}

	public BigDecimal getFacCautionMoneyRemain() {
		return facCautionMoneyRemain;
	}

	public void setFacCautionMoneyRemain(BigDecimal facCautionMoneyRemain) {
		this.facCautionMoneyRemain = facCautionMoneyRemain;
	}

	public String getDeductionType() {
		return deductionType;
	}

	public void setDeductionType(String deductionType) {
		this.deductionType = deductionType;
	}

	public BigDecimal getXirr() {
		return xirr;
	}

	public void setXirr(BigDecimal xirr) {
		this.xirr = xirr;
	}


	public DictionaryData getHandHz() {
		return handHz;
	}

	public void setHandHz(DictionaryData handHz) {
		this.handHz = handHz;
	}

	public DictionaryData getHandType() {
		return handType;
	}

	public void setHandType(DictionaryData handType) {
		this.handType = handType;
	}

	public DictionaryData getHandMoney() {
		return handMoney;
	}

	public void setHandMoney(DictionaryData handMoney) {
		this.handMoney = handMoney;
	}

	public BigDecimal getHandRatio() {
		return handRatio;
	}

	public void setHandRatio(BigDecimal handRatio) {
		this.handRatio = handRatio;
	}

	public BigDecimal getGraceAdjust() {
		return graceAdjust;
	}

	public void setGraceAdjust(BigDecimal graceAdjust) {
		this.graceAdjust = graceAdjust;
	}

	public BigDecimal getGraceRate() {
		return graceRate;
	}

	public void setGraceRate(BigDecimal graceRate) {
		this.graceRate = graceRate;
	}

	public BigDecimal getAdvanceRepayMoney() {
		return advanceRepayMoney;
	}

	public void setAdvanceRepayMoney(BigDecimal advanceRepayMoney) {
		this.advanceRepayMoney = advanceRepayMoney;
	}
	
	
}
