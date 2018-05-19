package com.tenwa.leasing.entity.insurance;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author lichaojie
 * @date 2013-7-28下午03:23:41
 * @info 保险理赔
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "保险理赔")
@Table(name="INSURANCE_CLAIM")
public class InsuranceClaim {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="保险单号")
	@ManyToOne(targetEntity=InsuranceInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="INSURANCE_ID")
	private InsuranceInfo insuranceId;
	
	
	@FieldName(name="商业赔案号")
	@Column(name="RECORD_NUMBER",length=50)
	private String recordNumber;
	
	@FieldName(name="商业保险保单号")
	@Column(name="POLICY_NUMBER",length=50)
	private String policyNumber;
	
	@FieldName(name="报案人")
	@Column(name="REPORTER",length=50)
	private String reporter;
	
	@FieldName(name="出险时间")
	@Column(name="CLAIMS_BEGINDATE",length=50)
	private String claimsBegindate;
	
	@FieldName(name="出险地点")
	@Column(name="CLAIMS_ADDRESS",length=50)
	private String claimsAddress;
	
	@FieldName(name="出险原因")
	@Column(name="CLAIMS_INFO",length=50)
	private String claimsInfo;
	
	@FieldName(name="出险经过及损失情况")
	@Column(name="CLAIMS_AFTER",length=50)
	private String claimsAfter;
	
	@FieldName(name="保险业务经办人")
	@Column(name="CLAIMS_MANAGER",length=50)
	private String claimsManager;
	
	@FieldName(name="赔付险种")
	@Column(name="DANGER",length=50)
	private String danger;
	
	@FieldName(name="保险公司赔偿时间")
	@Column(name="CLAIMS_TIME",length=50)
	private String claimsTime;
		
	@FieldName(name="理赔金额(元)")
	@Column(name="CLAIMS_MONEY",precision=22,scale=2)
	private BigDecimal claimsMoney;
	
	@FieldName(name="赔偿金额处理方式")
	@Column(name="CLAIMS_MONEY_DEAL",length=50)
	private String claimsMoneyDeal;
	
	@FieldName(name="备注")
	@Column(name="REMARK",length=50)
	private String remark;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public InsuranceInfo getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(InsuranceInfo insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getClaimsBegindate() {
		return claimsBegindate;
	}

	public void setClaimsBegindate(String claimsBegindate) {
		this.claimsBegindate = claimsBegindate;
	}

	public String getClaimsAddress() {
		return claimsAddress;
	}

	public void setClaimsAddress(String claimsAddress) {
		this.claimsAddress = claimsAddress;
	}

	public String getClaimsInfo() {
		return claimsInfo;
	}

	public void setClaimsInfo(String claimsInfo) {
		this.claimsInfo = claimsInfo;
	}

	public String getClaimsAfter() {
		return claimsAfter;
	}

	public void setClaimsAfter(String claimsAfter) {
		this.claimsAfter = claimsAfter;
	}

	public String getClaimsManager() {
		return claimsManager;
	}

	public void setClaimsManager(String claimsManager) {
		this.claimsManager = claimsManager;
	}

	public String getDanger() {
		return danger;
	}

	public void setDanger(String danger) {
		this.danger = danger;
	}

	public String getClaimsTime() {
		return claimsTime;
	}

	public void setClaimsTime(String claimsTime) {
		this.claimsTime = claimsTime;
	}

	public BigDecimal getClaimsMoney() {
		return claimsMoney;
	}

	public void setClaimsMoney(BigDecimal claimsMoney) {
		this.claimsMoney = claimsMoney;
	}

	public String getClaimsMoneyDeal() {
		return claimsMoneyDeal;
	}

	public void setClaimsMoneyDeal(String claimsMoneyDeal) {
		this.claimsMoneyDeal = claimsMoneyDeal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
}
