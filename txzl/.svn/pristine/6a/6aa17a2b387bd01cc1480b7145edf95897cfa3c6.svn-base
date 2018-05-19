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
 * @author 孙传良
 * @date 2013-3-7下午02:11:52
 * @info 合同租金计划变更信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同租金计划变更信息")
@Table(name="FUND_RENT_ADJUST")
public class FundRentAdjust {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="流程实例ID")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="多次起租编号")
	@Column(name="ONHIRE_ID")
	@Deprecated
	private String onhireId;
	
	@ManyToOne
	@FieldName(name="变更类型")
	@JoinColumn(name="ADJUST_TYPE")
	private DictionaryData adjustType;

	@FieldName(name="开始期项")
	@Column(name="START_LIST")
	private Integer startList;

	@ManyToOne
	@FieldName(name="付租类型")
	@JoinColumn(name="INCOME_NUMBER_YEAR")
	private DictionaryData incomeNumberYear;

	@FieldName(name="约定终止日")
	@Column(name="PAYDAY_ADJUST",length=20)
	private String paydayAdjust;

	@FieldName(name="手续费")
	@Column(name="HANDLING_CHARGE",precision=22,scale=2)
	private BigDecimal handlingCharge;

	@FieldName(name="调整后期数")
	@Column(name="ADJUST_LIST")
	private Integer adjustList;

	@FieldName(name="预计租赁年利率")
	@Column(name="YEAR_RATE",precision=22,scale=6)
	private BigDecimal yearRate;

	@FieldName(name="利息手续费")
	@Column(name="INTEREST_HANDLING_CHARGE",precision=22,scale=2)
	private BigDecimal interestHandlingCharge;

	@FieldName(name="逾期租金")
	@Column(name="DUN_RENT",precision=22,scale=2)
	private BigDecimal dunRent;
	
	//折现年利率 未到期租金 未到期租金(折现) sea2014-03-03新增三个字段
	@FieldName(name="折现年利率")
	@Column(name="SALE_RATE",precision=22,scale=6)
	private BigDecimal saleRate;
	
	@FieldName(name="未到期租金")
	@Column(name="RENT_OVERAGE",precision=22,scale=2)
	private BigDecimal rentOverage;
	
	@FieldName(name="未到期租金(折现)")
	@Column(name="RENT_SALE_OVERAGE",precision=22,scale=2)
	private BigDecimal rentSaleOverage;
	
	@FieldName(name="未到期本金")
	@Column(name="CORPUS_OVERAGE",precision=22,scale=2)
	private BigDecimal corpusOverage;
	
	@FieldName(name="未到期利息")
	@Column(name="CORPUS_INTEREST",precision=22,scale=2)
	private BigDecimal corpusInterest;

	@FieldName(name="逾期罚息")
	@Column(name="DUN_PENALTY",precision=22,scale=2)
	private BigDecimal dunPenalty;

	@FieldName(name="商定罚息")
	@Column(name="AGREED_PENALTY",precision=22,scale=2)
	private BigDecimal agreedPenalty;

	@FieldName(name="其他应退")
	@Column(name="OTHER_OUT",precision=22,scale=2)
	private BigDecimal otherOut;

	@FieldName(name="其他应收")
	@Column(name="OTHER_IN",precision=22,scale=2)
	private BigDecimal otherIn;

	@FieldName(name="合同债权总计")
	@Column(name="CONTRACT_TOTAL",precision=22,scale=2)
	private BigDecimal contractTotal;
	
	@FieldName(name="商定利息")
	@Column(name="AGREED_INTEREST",precision=22,scale=2)
	private BigDecimal agreedInterest;
	
	@FieldName(name="资产所有权")
	@Column(name="ASSET_OWNERSHIP")
	private String assetOwnership;
	
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
	
	//add By zhangc
	@FieldName(name = "变更前内容")
	@Column(name = "BEFORE_CHANGE_CONTENT", length = 2000)
	private String beforeChangeContent;
	
	@FieldName(name = "变更后内容")
	@Column(name = "AFTER_CHANGE_CONTENT", length = 2000)
	private String afterChangeContent; 

	@FieldName(name = "变更说明")
	@Column(name = "CHANGE_INSTRUCTION", length = 2000)
	private String changeInstruction;
	
	

	public String getBeforeChangeContent() {
		return beforeChangeContent;
	}

	public void setBeforeChangeContent(String beforeChangeContent) {
		this.beforeChangeContent = beforeChangeContent;
	}

	public String getAfterChangeContent() {
		return afterChangeContent;
	}

	public void setAfterChangeContent(String afterChangeContent) {
		this.afterChangeContent = afterChangeContent;
	}

	public String getChangeInstruction() {
		return changeInstruction;
	}

	public void setChangeInstruction(String changeInstruction) {
		this.changeInstruction = changeInstruction;
	}

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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getOnhireId() {
		return onhireId;
	}

	public void setOnhireId(String onhireId) {
		this.onhireId = onhireId;
	}

	public DictionaryData getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(DictionaryData adjustType) {
		this.adjustType = adjustType;
	}

	public Integer getStartList() {
		return startList;
	}

	public void setStartList(Integer startList) {
		this.startList = startList;
	}

	public DictionaryData getIncomeNumberYear() {
		return incomeNumberYear;
	}

	public void setIncomeNumberYear(DictionaryData incomeNumberYear) {
		this.incomeNumberYear = incomeNumberYear;
	}

	public String getPaydayAdjust() {
		return paydayAdjust;
	}

	public void setPaydayAdjust(String paydayAdjust) {
		this.paydayAdjust = paydayAdjust;
	}

	public BigDecimal getHandlingCharge() {
		return handlingCharge;
	}

	public void setHandlingCharge(BigDecimal handlingCharge) {
		this.handlingCharge = handlingCharge;
	}

	public Integer getAdjustList() {
		return adjustList;
	}

	public void setAdjustList(Integer adjustList) {
		this.adjustList = adjustList;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getInterestHandlingCharge() {
		return interestHandlingCharge;
	}

	public void setInterestHandlingCharge(BigDecimal interestHandlingCharge) {
		this.interestHandlingCharge = interestHandlingCharge;
	}

	public BigDecimal getDunRent() {
		return dunRent;
	}

	public void setDunRent(BigDecimal dunRent) {
		this.dunRent = dunRent;
	}

	public BigDecimal getCorpusOverage() {
		return corpusOverage;
	}

	public void setCorpusOverage(BigDecimal corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public BigDecimal getDunPenalty() {
		return dunPenalty;
	}

	public void setDunPenalty(BigDecimal dunPenalty) {
		this.dunPenalty = dunPenalty;
	}

	public BigDecimal getAgreedPenalty() {
		return agreedPenalty;
	}

	public void setAgreedPenalty(BigDecimal agreedPenalty) {
		this.agreedPenalty = agreedPenalty;
	}

	public BigDecimal getOtherOut() {
		return otherOut;
	}

	public void setOtherOut(BigDecimal otherOut) {
		this.otherOut = otherOut;
	}

	public BigDecimal getOtherIn() {
		return otherIn;
	}

	public void setOtherIn(BigDecimal otherIn) {
		this.otherIn = otherIn;
	}

	public BigDecimal getContractTotal() {
		return contractTotal;
	}

	public void setContractTotal(BigDecimal contractTotal) {
		this.contractTotal = contractTotal;
	}

	public BigDecimal getAgreedInterest() {
		return agreedInterest;
	}

	public void setAgreedInterest(BigDecimal agreedInterest) {
		this.agreedInterest = agreedInterest;
	}

	public String getAssetOwnership() {
		return assetOwnership;
	}

	public void setAssetOwnership(String assetOwnership) {
		this.assetOwnership = assetOwnership;
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

	public BigDecimal getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(BigDecimal saleRate) {
		this.saleRate = saleRate;
	}

	public BigDecimal getRentOverage() {
		return rentOverage;
	}

	public void setRentOverage(BigDecimal rentOverage) {
		this.rentOverage = rentOverage;
	}

	public BigDecimal getRentSaleOverage() {
		return rentSaleOverage;
	}

	public void setRentSaleOverage(BigDecimal rentSaleOverage) {
		this.rentSaleOverage = rentSaleOverage;
	}

	public BigDecimal getCorpusInterest() {
		return corpusInterest;
	}

	public void setCorpusInterest(BigDecimal corpusInterest) {
		this.corpusInterest = corpusInterest;
	}
	
	

}
