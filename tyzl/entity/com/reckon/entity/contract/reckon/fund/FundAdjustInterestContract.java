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

import com.reckon.entity.interest.FundStandardInterest;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;


/**
 * 
 * @author 孙传良
 * @date 2013-3-7上午10:53:08
 * @info 合同进行央行调息记录
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同进行央行调息记录")
@Table(name="FUND_ADJUST_INTEREST_CONTRACT")
public class FundAdjustInterestContract {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;

	@FieldName(name="央行调息编号")
	@ManyToOne(targetEntity=FundStandardInterest.class,fetch=FetchType.LAZY)
	@JoinColumn(name="ADJUST_ID")
	private FundStandardInterest adjustId;

	@FieldName(name="调息开始期项")
	@Column(name="START_LIST")
	private int startList;

	@FieldName(name="调整前利率")
	@Column(name="RATE_ORIGINAL",precision=22,scale=6)
	private BigDecimal rateOriginal;

	@FieldName(name="调整后利率")
	@Column(name="RATE_ADJUST",precision=22,scale=6)
	private BigDecimal rateAdjust;

	@FieldName(name="旧的合同IRR")
	@Column(name="OLD_IRR",precision=22,scale=6)
	private BigDecimal oldIrr;

	@FieldName(name="新的合同IRR")
	@Column(name="NEW_IRR",precision=22,scale=6)
	private BigDecimal newIrr;

	@FieldName(name="旧会计IRR")
	@Column(name="OLD_PLAN_IRR",precision=22,scale=6)
	private BigDecimal oldPlanIrr;

	@FieldName(name="新会计IRR")
	@Column(name="NEW_PLAN_IRR",precision=22,scale=6)
	private BigDecimal newPlanIrr;

	@FieldName(name="测算编号")
	@Column(name="DOC_ID")
	private String docId;

	@FieldName(name="调息时间")
	@Column(name="ADJUST_DATE",length=20)
	private String adjustDate;

	@FieldName(name="修改原因")
	@Column(name="MOD_REASON")
	private String modReason;

	@FieldName(name="状态")
	@Column(name="STATUS_")
	private String status;
	
	@FieldName(name="操作ID")
	@Column(name="PROCESSID")
	private String processId;

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

	public FundStandardInterest getAdjustId() {
		return adjustId;
	}

	public void setAdjustId(FundStandardInterest adjustId) {
		this.adjustId = adjustId;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public BigDecimal getRateOriginal() {
		return rateOriginal;
	}

	public void setRateOriginal(BigDecimal rateOriginal) {
		this.rateOriginal = rateOriginal;
	}

	public BigDecimal getRateAdjust() {
		return rateAdjust;
	}

	public void setRateAdjust(BigDecimal rateAdjust) {
		this.rateAdjust = rateAdjust;
	}

	public BigDecimal getOldIrr() {
		return oldIrr;
	}

	public void setOldIrr(BigDecimal oldIrr) {
		this.oldIrr = oldIrr;
	}

	public BigDecimal getNewIrr() {
		return newIrr;
	}

	public void setNewIrr(BigDecimal newIrr) {
		this.newIrr = newIrr;
	}

	public BigDecimal getOldPlanIrr() {
		return oldPlanIrr;
	}

	public void setOldPlanIrr(BigDecimal oldPlanIrr) {
		this.oldPlanIrr = oldPlanIrr;
	}

	public BigDecimal getNewPlanIrr() {
		return newPlanIrr;
	}

	public void setNewPlanIrr(BigDecimal newPlanIrr) {
		this.newPlanIrr = newPlanIrr;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(String adjustDate) {
		this.adjustDate = adjustDate;
	}

	public String getModReason() {
		return modReason;
	}

	public void setModReason(String modReason) {
		this.modReason = modReason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
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
