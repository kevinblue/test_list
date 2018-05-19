package com.tenwa.leasing.entity.fund;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.reckon.bean.FundPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.paymentinterface.PaymentLog;


/**
 * 
 * @author ykl
 * @date 2017-2-27下午1
 * @info 财务资金计划表，财务使用的资金计划表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "财务资金计划临时表")
@Table(name = "FINANCE_FUND_FUND_PLAN_TEMP")
public class FinanceFundFundPlanTemp  {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "收付编号")
	@Column(name = "PAYMENT_ID", length = 32)
	private String paymentId;

	@FieldName(name = "流程编号")
	@Column(name = "DOC_ID")
	private String docId;

	@FieldName(name = "合同编号")
	@Column(name = "CONTRACT_ID")
	private String contractId;

	
	@FieldName(name = "设备名称")
	@Column(name = "DEVICE_NAME")
	private String devicename;
	
	@FieldName(name="费用类型")
	@Column(name="FEE_TYPE")
	private String feeType;
	
	@FieldName(name="设备款类型")
	@Column(name="FUND_TYPE")
	private String fundType;

	@FieldName(name = "计划收付日期")
	@Column(name = "PLAN_DATE", length = 20)
	private String planDate;

	@FieldName(name = "金额")
	@Column(name = "PLAN_MONEY", precision = 22, scale = 2)
	private BigDecimal planMoney;

	@FieldName(name = "付款对象")
	@Column(name = "PAY_OBJ")
	private String payObj;
	
	@FieldName(name = "收付对象")
	@Column(name = "PAY_CUST")
	private String payCust;
	
	@FieldName(name = "付款对象显示值")
	@Column(name = "PAY_CUST_NAME")
	private String payCustName;

	
	@FieldName(name="是否抵扣")
	@Column(name="WHETHER_DEDUCT")
	private String   whetherDeduct;
	
	@FieldName(name = "是否预付款")
	@Column(name = "IS_PREPAY")
	private String isPrePay;
	
	
	@FieldName(name = "收付类型")
	@Column(name = "PAY_TYPE")
	private String payType;
	
	@FieldName(name="结算方式[D]")
	@Column(name="SETTLE_METHOD")
	private String settleMethod;

	@Transient
	@FieldName(name = "已收款")
	private BigDecimal receiverMoney;

	@Transient
	@FieldName(name = "未收款")
	private BigDecimal planbalance;
	
	@Transient
	@FieldName(name="调整")
	private BigDecimal adjustMoney;
	
	@FieldName(name="备注")
	@Column(name="FPNOTE")
	private String   fpnote;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public String getPayObj() {
		return payObj;
	}

	public void setPayObj(String payObj) {
		this.payObj = payObj;
	}

	public String getFpnote() {
		return fpnote;
	}

	public void setFpnote(String fpnote) {
		this.fpnote = fpnote;
	}



	public BigDecimal getReceiverMoney() {
		return receiverMoney;
	}

	

	public void setReceiverMoney(BigDecimal receiverMoney) {
		this.receiverMoney = receiverMoney;
	}

	public BigDecimal getPlanbalance() {
		return planbalance;
	}

	public BigDecimal getAdjustMoney() {
		return adjustMoney;
	}

	public void setPlanbalance(BigDecimal planbalance) {
		this.planbalance = planbalance;
	}

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public void setAdjustMoney(BigDecimal adjustMoney) {
		this.adjustMoney = adjustMoney;
	}


	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}


	public String getWhetherDeduct() {
		return whetherDeduct;
	}

	public void setWhetherDeduct(String whetherDeduct) {
		this.whetherDeduct = whetherDeduct;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getPayCust() {
		return payCust;
	}

	public void setPayCust(String payCust) {
		this.payCust = payCust;
	}

	public String getPayCustName() {
		return payCustName;
	}

	public void setPayCustName(String payCustName) {
		this.payCustName = payCustName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
 
