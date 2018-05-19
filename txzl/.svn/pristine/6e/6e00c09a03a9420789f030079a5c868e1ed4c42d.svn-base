package com.tenwa.leasing.entity.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
/**
 * ContractPremiseCondition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_PREMISE_CONDITION")
@FieldName(name="合同前提信息表")
public class ContractPremiseCondition implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="支付方式")
	@Column(name="PAY_TYPE",length=100)
	private String payType;
	
	@FieldName(name="费用类型")
	@JoinColumn(name="FEE_TYPE")
	@ManyToOne
	private DictionaryData feeType;

	@ManyToOne
	@FieldName(name="贸易类型")
	@JoinColumn(name="VNDR_TYPE")
	private DictionaryData vndrtype;
	
	@FieldName(name="收付款编号")
	@Column(name="PAYMENT_NUMBER",length=100)
	private String paymentNumber;
	
	@FieldName(name="供应商")
	@Column(name="PAY_CUST",length=100)
	private String payCust;
	
	@FieldName(name="金额")
	@Column(name="PREMISE_MONEY",length=100)
	private String premiseMoney;
	
	@FieldName(name="付款条件")
	@Column(name="PREMISE",length=2000)
	private String premise;
	
	@FieldName(name="付款备注")
	@Column(name="PREMISE_NOTE",length=2000)
	private String premiseNote;
	
	@FieldName(name="收付款编号")
	@Column(name="PAYMENT_ID",length=2000)
	private String paymentId;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getPremise() {
		return premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public String getPremiseNote() {
		return premiseNote;
	}

	public void setPremiseNote(String premiseNote) {
		this.premiseNote = premiseNote;
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

	public DictionaryData getVndrtype() {
		return vndrtype;
	}

	public void setVndrtype(DictionaryData vndrtype) {
		this.vndrtype = vndrtype;
	}

	public String getPayCust() {
		return payCust;
	}

	public void setPayCust(String payCust) {
		this.payCust = payCust;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public DictionaryData getFeeType() {
		return feeType;
	}

	public void setFeeType(DictionaryData feeType) {
		this.feeType = feeType;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getPremiseMoney() {
		return premiseMoney;
	}

	public void setPremiseMoney(String premiseMoney) {
		this.premiseMoney = premiseMoney;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	
}