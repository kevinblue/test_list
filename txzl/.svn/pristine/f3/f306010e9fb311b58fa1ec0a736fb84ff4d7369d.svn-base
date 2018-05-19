package com.tenwa.leasing.entity.fund.overdue;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;


/**
 * 催款函表
 * @Title: OverdueDunningNotice.java
 * @package: com.tenwa.leasing.entity.fund.overdue
 * @author: Toybaby
 * @date 2014年11月27日 下午19:00 
 * @version V5.1
 */
@Entity
@FieldName(name = "催款函")
@Table(name="OVERDUE_DUNNING_NOTICE")
public class OverdueDunningNotice {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "申请发送时间")
	@Column(name="SENT_DATE", length = 20)
	private String sendDate;
	
	@FieldName(name="逾期期次")
	@Column(name="OVERDUE_NUM",precision=22,scale=2)
	private BigDecimal overdueNum;
	
	@FieldName(name="逾期租金")
	@Column(name="OVERDUE_RENT",precision=22,scale=2)
	private BigDecimal overdueRent;
	
	@FieldName(name="罚息")
	@Column(name="penalty",precision=22,scale=2)
	private BigDecimal penalty;
	
	@FieldName(name="状态 0或null为已打印，1为已申请")
	@Column(name="STATUS")
	private Integer status;
	
	@FieldName(name="导出类型")
	@Column(name="EXPORT_TYPE", length=40)
	private String exportType;
	
	@ManyToOne
	@FieldName(name="租金计划表ID")
	@JoinColumn(name="PLAN_ID")
	private ContractFundRentPlan planId;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
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

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getOverdueNum() {
		return overdueNum;
	}

	public void setOverdueNum(BigDecimal overdueNum) {
		this.overdueNum = overdueNum;
	}

	public BigDecimal getOverdueRent() {
		return overdueRent;
	}

	public void setOverdueRent(BigDecimal overdueRent) {
		this.overdueRent = overdueRent;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public ContractFundRentPlan getPlanId() {
		return planId;
	}

	public void setPlanId(ContractFundRentPlan planId) {
		this.planId = planId;
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