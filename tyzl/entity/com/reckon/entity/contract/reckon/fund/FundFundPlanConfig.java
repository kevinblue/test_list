package com.reckon.entity.contract.reckon.fund;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author zhangc
 * @ClassName: FundFundPlanConfig 
 * @date 2014年12月18日 上午11:37:07 
 * @Description: 资金计划配置
 */
@Entity
@FieldName(name = "资金计划配置表")
@Table(name="FUND_FUND_PLAN_CONFIG")
public class FundFundPlanConfig {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name = "费用类型名称")
	@Column(name = "FEE_TYPE_NAME")
	private String feeTypeName;
	
	@FieldName(name = "费用类型")
	@Column(name = "FEE_TYPE")
	private String feeType;

	@FieldName(name = "商务报价字段名")
	@Column(name = "FEE_TYPE_FIELD")
	private String feeTypeFied;
	
	@FieldName(name = "费用收付日期")
	@Column(name = "FEE_TYPE_DATE")
	private String feeTypeDate;
	
	@FieldName(name = "收入类型")
	@Column(name = "PAY_TYPE_CODE")
	private String payTypeCode;
	
	@FieldName(name = "收入类型名称")
	@Column(name = "PAY_TYPE_NAME")
	private String payTypeName;
	
	@FieldName(name = "收入次数")
	@Column(name = "PAYMENT_NUMB")
	private String paymentNumb;
	
	@FieldName(name = "付款类型")
	@Column(name = "SETTLE_METHOD")
	private String settleMethod;
	
	@FieldName(name = "付款类型名称")
	@Column(name = "SETTLE_METHOD_NAME")
	private String settleMethodName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFeeTypeName() {
		return feeTypeName;
	}

	public void setFeeTypeName(String feeTypeName) {
		this.feeTypeName = feeTypeName;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getFeeTypeFied() {
		return feeTypeFied;
	}

	public void setFeeTypeFied(String feeTypeFied) {
		this.feeTypeFied = feeTypeFied;
	}

	public String getFeeTypeDate() {
		return feeTypeDate;
	}

	public void setFeeTypeDate(String feeTypeDate) {
		this.feeTypeDate = feeTypeDate;
	}

	public String getPayTypeCode() {
		return payTypeCode;
	}

	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public String getPaymentNumb() {
		return paymentNumb;
	}

	public void setPaymentNumb(String paymentNumb) {
		this.paymentNumb = paymentNumb;
	}

	public String getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(String settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getSettleMethodName() {
		return settleMethodName;
	}

	public void setSettleMethodName(String settleMethodName) {
		this.settleMethodName = settleMethodName;
	}
	
	
}
