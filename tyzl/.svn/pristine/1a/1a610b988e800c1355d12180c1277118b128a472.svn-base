package com.reckon.entity.contract.fund;

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

import com.reckon.bean.FundPlan;
import com.reckon.commons.util.DateTools;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午12:05:49
 * @info 合同资金计划表(临时)
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同资金计划表(临时)")
@Table(name="CONTRACT_FUND_FUND_PLAN_TEMP")
@Deprecated
public class ContractFundFundPlanTemp implements FundPlan {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="收付编号")
	@Column(name="PAYMENT_ID")
	private String   paymentId;

	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String   docId;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId ;
	
	@FieldName(name="费用类型")
	@JoinColumn(name="FEE_TYPE")
	@ManyToOne
	private DictionaryData feeType;
	
	@FieldName(name="计划收付日期")
	@Column(name="PLAN_DATE",length=20)
	private String   planDate;

	@FieldName(name="金额")
	@Column(name="PLAN_MONEY",precision=22,scale=2)
	private BigDecimal planMoney;

	@ManyToOne
	@FieldName(name="结算方式[D]")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
	
	
	@FieldName(name = "是否预付款")
	@Column(name = "IS_PREPAY")
	private String isPrePay;
	
	@FieldName(name = "付款对象显示值")
	@Column(name = "PAY_CUST_NAME")
	private String payCustName;
	
	@FieldName(name="付款对象")
	@Column(name="PAY_OBJ")
	private String payObj ;

	@FieldName(name="收付类型")
	@JoinColumn(name="PAY_TYPE")
	@ManyToOne
	private DictionaryData payType;

	@FieldName(name="备注")
	@Column(name="FPNOTE")
	private String   fpnote;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public DictionaryData getFeeType() {
		return feeType;
	}

	public void setFeeType(DictionaryData feeType) {
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

	public DictionaryData getPayType() {
		return payType;
	}

	public void setPayType(DictionaryData payType) {
		this.payType = payType;
	}

	public String getFpnote() {
		return fpnote;
	}

	public void setFpnote(String fpnote) {
		this.fpnote = fpnote;
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
	
	public DictionaryData getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(DictionaryData settleMethod) {
		this.settleMethod = settleMethod;
	}

	public String getIsPrePay() {
		return isPrePay;
	}

	public void setIsPrePay(String isPrePay) {
		this.isPrePay = isPrePay;
	}

	@Override
	public int compareTo(FundPlan o) {
		long dateDiff = DateTools.getDateDiff(this.getPlanDate(), o.getPlanDate());
		int netFlowDiff = this.getPlanMoney().compareTo(o.getPlanMoney());
		if(dateDiff == 0){
			return netFlowDiff;
		} else{
			return (int)dateDiff;
		}
	}
}
