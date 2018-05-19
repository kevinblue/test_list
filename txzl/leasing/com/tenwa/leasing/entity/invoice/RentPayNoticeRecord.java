package com.tenwa.leasing.entity.invoice;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;

/**
 * TaxVatInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "租金支付通知书导出记录")
@Table(name="RENT_PAY_NOTICE_RECORD")
public class RentPayNoticeRecord  {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name = "标识符")
	private String id;
	
	@FieldName(name="合同表ID")
	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	@FieldName(name="租金计划表ID")
	@OneToOne(targetEntity = ContractFundRentPlan.class, fetch = FetchType.LAZY)
	@JoinColumn(name="PLAN_ID")
	private ContractFundRentPlan planId;
	
	@FieldName(name="导出次数")
	@Column(name="EXPORT_TIMES")
	private Integer exportTimes;
	
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

	public ContractFundRentPlan getPlanId() {
		return planId;
	}

	public void setPlanId(ContractFundRentPlan planId) {
		this.planId = planId;
	}

	public Integer getExportTimes() {
		return exportTimes;
	}

	public void setExportTimes(Integer exportTimes) {
		this.exportTimes = exportTimes;
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