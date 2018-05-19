package com.tenwa.leasing.entity.finacial;

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
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 收款统计详情信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "付款统计表aptable")
@Table(name="PAYMENT_COUNT_DETAIL_AP")
public class 	PaymentCountDetailAP{
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "主表ID")
	@Column(name="REPORT_ID", length=200)
	private String reportid;
	
	@FieldName(name = "付款统计")
	@Column(name="PAYMENT_COUNT", length=200)
	private String paymentCount;
	
	@FieldName(name = "金额")
	@Column(name="AMOUNT", length=200)
	private String amount;
	
	@FieldName(name = "时间")
	@Column(name="PAYMENT_TIME", length=200)
	private String paymentTime;
	
	@FieldName(name = "供应商名称")
	@Column(name="SUPPORT_UNIT", length=200)
	private String supportUnit;
	
	
	
	@ManyToOne
	@FieldName(name="收款类型")//合同资金收付类型
	@JoinColumn(name="RECEIPT_TYPE")
	private DictionaryData receiptType;

	@ManyToOne
	@FieldName(name="计划项名称")//资金计划表收付类型..现金流类型
	@JoinColumn(name="PLAN_NAME")
	private DictionaryData planNameId;
	
	@FieldName(name = "资金安排表款项类型")  //资金安排表款项类型,回款、资金下拨...
	@Column(name="FUND_TYPE", length=200)
	private String fundtype;
	
	
	@ManyToOne
	@JoinColumn(name = "DI_ID")
	@FieldName(name="资金预实表id")
	private DepositInterest diid;
	
	@ManyToOne
	@JoinColumn(name = "CFFP_ID")
	@FieldName(name="资金实收id")
	private ContractFundFundPlan cffpId;
	
	@ManyToOne
	@JoinColumn(name = "CFRP_ID")
	@FieldName(name="租金实收id")
	private ContractFundRentPlan cfrpid;
	
	@FieldName(name = "备注")
	@Column(name="NOTE_", length=2000)
	private String note;
	
	@FieldName(name = "银承")
	@Column(name="SILVER", length=2000)
	private String silver;

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

	public String getPaymentCount() {
		return paymentCount;
	}

	public void setPaymentCount(String paymentCount) {
		this.paymentCount = paymentCount;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getSupportUnit() {
		return supportUnit;
	}

	public void setSupportUnit(String supportUnit) {
		this.supportUnit = supportUnit;
	}


	

	public DictionaryData getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(DictionaryData receiptType) {
		this.receiptType = receiptType;
	}

	
	public DictionaryData getPlanNameId() {
		return planNameId;
	}

	public void setPlanNameId(DictionaryData planNameId) {
		this.planNameId = planNameId;
	}

	public DepositInterest getDiid() {
		return diid;
	}

	public void setDiid(DepositInterest diid) {
		this.diid = diid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getSilver() {
		return silver;
	}

	public void setSilver(String silver) {
		this.silver = silver;
	}

	public ContractFundFundPlan getCffpId() {
		return cffpId;
	}

	public void setCffpId(ContractFundFundPlan cffpId) {
		this.cffpId = cffpId;
	}

	public ContractFundRentPlan getCfrpid() {
		return cfrpid;
	}

	public void setCfrpid(ContractFundRentPlan cfrpid) {
		this.cfrpid = cfrpid;
	}

	public String getFundtype() {
		return fundtype;
	}

	public void setFundtype(String fundtype) {
		this.fundtype = fundtype;
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

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	

	
	
}
