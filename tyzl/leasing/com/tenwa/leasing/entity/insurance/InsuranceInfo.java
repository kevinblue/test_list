package com.tenwa.leasing.entity.insurance;

import java.math.BigDecimal;

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
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author cyw
 * @date 2013-3-4下午09:33:10
 * @info 保险公司信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "保险公司信息")
@Table(name="INSURANCE_INFO")
public class InsuranceInfo {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="保险单编号")
	@Column(name="INSURANCE_ID", length=50)
	private String insuranceId;//
	
	
	@FieldName(name="保险公司名称")
	@Column(name="INSURANCE_NAME",length=50)
	private String insuranceName;//
	

	@FieldName(name="险种")
	@Column(name="ISURANCE_TYPE",length=50)
	private String isuranceType;//	
	
	/*
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="投保类型")
	@JoinColumn(name="INSURER_TYPE")
	private DictionaryData insurerType;
	*/
	
	@FieldName(name="投保人")
	@Column(name="INSURER_PERSON",length=50)
	private String insurerPerson;//	
	
	
	
	@FieldName(name="第一受益人")
	@Column(name="FIRST_BENEFICIARY",length=200)
	private String firstBeneficiary;//
	
	@FieldName(name="第二受益人")
	@Column(name="SECOND_BENEFICIARY",length=200)
	private String secondBeneficiary;
	

	@FieldName(name="投保开始日期")
	@Column(name="INSURER_START_DATE",length=20)
	private String insurerStartDate;//

	@FieldName(name="投保结束日期")
	@Column(name="INSURER_END_DATE",length=20)
	private String insurerEndDate;//
	
	
	@FieldName(name="收保单日期")
	@Column(name="INSURER_GET_DATE",length=20)
	private String insurerGetDate;//
	
 
	@FieldName(name="保险费")
	@Column(name="INSURANCE_PREMIUM",precision=22,scale=2)
	private BigDecimal insurancePremium;	
	
  
	@FieldName(name="保险费率")
	@Column(name="INSURANCE_PREMIUM_RATE",precision=22,scale=2)
	private BigDecimal insurancePremiumRate;
	
	
	@FieldName(name="保险金额")
	@Column(name="INSURANCE_MONEY",precision=22,scale=2)
	private BigDecimal insuranceMoney;

 
	@FieldName(name="退保对象")
	@Column(name="SURRENDER_OBJECT",length=200)
	private String surrenderObject;

	@FieldName(name="退保金额")
	@Column(name="SURRENDER_MONEY",precision=22,scale=2)
	private BigDecimal surrenderMoney;
	
	@FieldName(name="退保日志 ")
	@Column(name="SURRENDER_LOG",length=2000)
	private String surrenderLog;//	
	
	
	
	@FieldName(name="保险公司联系方式")
	@Column(name="INSUR_COMM",length=50)
	private String insuranceCommunication;
	
	
	@FieldName(name="是否投保")//[是/否--0/1]
	@Column(name="IS_INSURE",length=2)
	private boolean isInsure;	
	
	
	@FieldName(name="租赁物件 ")
	@Column(name="LEASED_PROPERTY",length=200)
	private String leasedProperty;//	
	
	@FieldName(name="备注")
	@Column(name="MEMO",length=2000)
	private String memo;//
	 
	
	@FieldName(name="是否作废(伪删除)[是/否--0/1][S]")
	@Column(name="INVALID_",length=2)
	private String invalid;
	
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
	
	
	
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;//
	
	
	

	public String getInsurerGetDate() {
		return insurerGetDate;
	}

	public void setInsurerGetDate(String insurerGetDate) {
		this.insurerGetDate = insurerGetDate;
	}

	public BigDecimal getInsurancePremium() {
		return insurancePremium;
	}

	public void setInsurancePremium(BigDecimal insurancePremium) {
		this.insurancePremium = insurancePremium;
	}

	public BigDecimal getInsurancePremiumRate() {
		return insurancePremiumRate;
	}

	public void setInsurancePremiumRate(BigDecimal insurancePremiumRate) {
		this.insurancePremiumRate = insurancePremiumRate;
	}

	public BigDecimal getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(BigDecimal insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public String getSurrenderObject() {
		return surrenderObject;
	}

	public void setSurrenderObject(String surrenderObject) {
		this.surrenderObject = surrenderObject;
	}

	public BigDecimal getSurrenderMoney() {
		return surrenderMoney;
	}

	public void setSurrenderMoney(BigDecimal surrenderMoney) {
		this.surrenderMoney = surrenderMoney;
	}

	public String getSurrenderLog() {
		return surrenderLog;
	}

	public void setSurrenderLog(String surrenderLog) {
		this.surrenderLog = surrenderLog;
	}

	public boolean isInsure() {
		return isInsure;
	}

	public void setInsure(boolean isInsure) {
		this.isInsure = isInsure;
	}

	public String getLeasedProperty() {
		return leasedProperty;
	}

	public void setLeasedProperty(String leasedProperty) {
		this.leasedProperty = leasedProperty;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getInsurerPerson() {
		return insurerPerson;
	}

	public void setInsurerPerson(String insurerPerson) {
		this.insurerPerson = insurerPerson;
	}


	public String getInsurerStartDate() {
		return insurerStartDate;
	}

	public void setInsurerStartDate(String insurerStartDate) {
		this.insurerStartDate = insurerStartDate;
	}

	public String getInsurerEndDate() {
		return insurerEndDate;
	}

	public void setInsurerEndDate(String insurerEndDate) {
		this.insurerEndDate = insurerEndDate;
	}

	

	public String getFirstBeneficiary() {
		return firstBeneficiary;
	}

	public void setFirstBeneficiary(String firstBeneficiary) {
		this.firstBeneficiary = firstBeneficiary;
	}

	public String getSecondBeneficiary() {
		return secondBeneficiary;
	}

	public void setSecondBeneficiary(String secondBeneficiary) {
		this.secondBeneficiary = secondBeneficiary;
	}

	public String getIsuranceType() {
		return isuranceType;
	}

	public void setIsuranceType(String isuranceType) {
		this.isuranceType = isuranceType;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public String getInsuranceCommunication() {
		return insuranceCommunication;
	}

	public void setInsuranceCommunication(String insuranceCommunication) {
		this.insuranceCommunication = insuranceCommunication;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
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

	/*
	public DictionaryData getInsurerType() {
		return insurerType;
	}

	public void setInsurerType(DictionaryData insurerType) {
		this.insurerType = insurerType;
	}
*/
	
	
}
