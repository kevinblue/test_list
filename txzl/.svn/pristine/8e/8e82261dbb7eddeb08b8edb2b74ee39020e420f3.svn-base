package com.tenwa.leasing.entity.fund;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OrderBy;

import com.reckon.bean.RentPlan;
import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
/**
 * 
 * @author ykl
 * @date 2017-2-27下午05:45:11
 * @info 合同租金计划
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "财务租金计划")
@Table(name="FINANCE_FUND_RENT_PLAN")
public class FinanceFundRentPlan implements RentPlan,Comparable<RentPlan> {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	@ManyToOne
	private ContractInfo contractId;
	
	@FieldName(name="报价编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="客户初始报价测算编号")
	@Column(name="QUOT_ID")
	@Deprecated
	private String quotId;
	
	@FieldName(name="多次起租编号")
	@Column(name="ONHIRE_ID")
	@Deprecated
	private String onhireId;
	
	@FieldName(name="期项")
	@Column(name="RENT_LIST")
	private Integer rentList;
	
	@FieldName(name="承付日期")
	@Column(name="PLAN_DATE",length=20)
	private String   planDate;
	
	@FieldName(name="租金")
	@Column(name="RENT",precision=22,scale=2)
	private BigDecimal rent;

	@FieldName(name="租金调整值")
	@Column(name="RENT_ADJUST",precision=22,scale=2)
	private BigDecimal rentAdjust;

	@FieldName(name="财务本金")
	@Column(name="CORPUS",precision=22,scale=2)
	private BigDecimal corpus;
	
	@FieldName(name="业务本金")
	@Column(name="CORPUS_BUSINESS",precision=22,scale=2)
	private BigDecimal corpusBusiness;

	@FieldName(name="年利率")
	@Column(name="YEAR_RATE",precision=22,scale=6)
	private BigDecimal yearRate;

	@FieldName(name="租息")
	@Column(name="INTEREST",precision=22,scale=2)
	private BigDecimal interest;
	
	@FieldName(name="逾期信息")
	@Column(name="OVERDUE_INFORMATION", length=3000)	
	private String overdueInformation;
	
	@FieldName(name="业务租息")
	@Column(name="INTEREST_BUSINESS",precision=22,scale=2)
	private BigDecimal interestBusiness;

	@FieldName(name="财务租金余额")
	@Column(name="RENT_OVERAGE",precision=22,scale=2)
	private BigDecimal rentOverage;

	@FieldName(name="财务本金余额")
	@Column(name="CORPUS_OVERAGE",precision=22,scale=2)
	private BigDecimal corpusOverage;
	
	@FieldName(name="财务租息余额")
	@Column(name="INTEREST_OVERAGE",precision=22,scale=2)
	private BigDecimal interestOverage;
	
	@Transient
	@FieldName(name="当期租金余额")
	private BigDecimal curRentOverage; 
	@Transient    
	@FieldName(name="当期本金余额")
	private BigDecimal curCorpusOverage;
	@Transient
	@FieldName(name="当期租息余额")
	private BigDecimal curInterestOverage;
	
	@Transient
	@FieldName(name="当期实收本金")
	private BigDecimal curCorpusIncome;
	
	@Transient
	@FieldName(name="当期实收租息")
	private BigDecimal curinterestIncome;
	
	@Transient
	@FieldName(name="当期实收罚息")
	private BigDecimal curpenaltyIncome;
	
	@Transient
	@FieldName(name="当期租息调整")
	private BigDecimal curinterestAdjustIncome;
	
	@Transient
	@FieldName(name="当期罚息调整")
	private BigDecimal curpenaltyAdjustIncome;
	
	@Transient
	@FieldName(name="当期状态")
	private DictionaryData planStatus;
	        
	@Transient
	@FieldName(name="回笼次数据")
	private Integer hireList;
	
	@ManyToOne
	@FieldName(name="结算方式[D]")
	@JoinColumn(name="SETTLE_METHOD")
	private DictionaryData settleMethod;
 
	@FieldName(name="罚息余额")
	@Column(name="PENALTY_OVERAGE",precision=22,scale=2)
	private BigDecimal penaltyOverage;

	@FieldName(name="罚息")
	@Column(name="PENALTY",precision=22,scale=2)
	private BigDecimal penalty;
	
	@FieldName(name="状态")
	@JoinColumn(name="STATUS_")
	@ManyToOne
	private DictionaryData status;

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
	
	@FieldName(name="租金实收表")
	@OneToMany(mappedBy="planIdFinance",fetch=FetchType.LAZY) 
	@OrderBy(clause="HIRE_DATE asc")
	private Set<ContractFundRentInCome> contractFundRentInComes = new HashSet<ContractFundRentInCome>();
	
	@FieldName(name="流程状态")
	@Column(name="WORK_FLAG", length=2, columnDefinition="INT DEFAULT 0")
	private Integer workFlag;

	@FieldName(name="本期以后所有的租金剩余")
	@Column(name="All_REMAIN_RENT",precision=22,scale=Scale.RENT_SCALE)
	private BigDecimal allRemainRent;
	
	@FieldName(name="本期以后所有的本金剩余")
	@Column(name="ALL_REMAIN_CORPUS",precision=22,scale=Scale.CORPUS_SCALE)
	private BigDecimal allRemainCorpus;
	
	@FieldName(name="本期以后所有的利息剩余")
	@Column(name="ALL_REMAIN_INTEREST",precision=22,scale=Scale.INTEREST_SCALE)
	private BigDecimal allRemainInterest;
	
	@FieldName(name="保证金退还(保理测算)")
	@Column(name="cautionmoney_Remain",precision=22,scale=2)
	private BigDecimal cautionmoneyRemain;
	
	
	public String getOverdueInformation() {
		return overdueInformation;
	}

	public void setOverdueInformation(String overdueInformation) {
		this.overdueInformation = overdueInformation;
	}

	public BigDecimal getCautionmoneyRemain() {
		return cautionmoneyRemain;
	}

	public void setCautionmoneyRemain(BigDecimal cautionmoneyRemain) {
		this.cautionmoneyRemain = cautionmoneyRemain;
	}

	
	public BigDecimal getAllRemainRent() {
		return allRemainRent;
	}

	public void setAllRemainRent(BigDecimal allRemainRent) {
		this.allRemainRent = allRemainRent;
	}

	public BigDecimal getAllRemainCorpus() {
		return allRemainCorpus;
	}

	public void setAllRemainCorpus(BigDecimal allRemainCorpus) {
		this.allRemainCorpus = allRemainCorpus;
	}

	public BigDecimal getAllRemainInterest() {
		return allRemainInterest;
	}

	public void setAllRemainInterest(BigDecimal allRemainInterest) {
		this.allRemainInterest = allRemainInterest;
	}

	@Override
	public boolean equals(Object obj) {
		FinanceFundRentPlan cp=(FinanceFundRentPlan)obj;
		if(this.rentList.equals(cp.getRentList())){
			return true;
		}else{
			return false;
		}
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

	public String getQuotId() {
		return quotId;
	}

	public void setQuotId(String quotId) {
		this.quotId = quotId;
	}

	public String getOnhireId() {
		return onhireId;
	}

	public void setOnhireId(String onhireId) {
		this.onhireId = onhireId;
	}

	public Integer getRentList() {
		return rentList;
	}

	public void setRentList(Integer rentList) {
		this.rentList = rentList;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getRentAdjust() {
		return rentAdjust;
	}

	public void setRentAdjust(BigDecimal rentAdjust) {
		this.rentAdjust = rentAdjust;
	}

	public BigDecimal getCorpus() {
		return corpus;
	}

	public void setCorpus(BigDecimal corpus) {
		this.corpus = corpus;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRentOverage() {
		return rentOverage;
	}

	public void setRentOverage(BigDecimal rentOverage) {
		this.rentOverage = rentOverage;
	}

	public BigDecimal getCorpusOverage() {
		return corpusOverage;
	}

	public void setCorpusOverage(BigDecimal corpusOverage) {
		this.corpusOverage = corpusOverage;
	}

	public BigDecimal getInterestOverage() {
		return interestOverage;
	}

	public void setInterestOverage(BigDecimal interestOverage) {
		this.interestOverage = interestOverage;
	}

	public BigDecimal getPenaltyOverage() {
		if(null==this.penaltyOverage){
			return BigDecimal.ZERO;
		}else{
			return penaltyOverage;
		}
	}

	public void setPenaltyOverage(BigDecimal penaltyOverage) {
		if(null==penaltyOverage){
			this.penaltyOverage = BigDecimal.ZERO;
		}else{
			this.penaltyOverage = penaltyOverage;
		}
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public DictionaryData getStatus() {
		return status;
	}

	public void setStatus(DictionaryData status) {
		this.status = status;
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

	public BigDecimal getCorpusBusiness() {
		return corpusBusiness;
	}

	public void setCorpusBusiness(BigDecimal corpusBusiness) {
		this.corpusBusiness = corpusBusiness;
	}

	public BigDecimal getInterestBusiness() {
		return interestBusiness;
	}

	public void setInterestBusiness(BigDecimal interestBusiness) {
		this.interestBusiness = interestBusiness;
	}

	public Set<ContractFundRentInCome> getContractFundRentInComes() {
		return contractFundRentInComes;
	}

	public void setContractFundRentInComes(Set<ContractFundRentInCome> contractFundRentInComes) {
		this.contractFundRentInComes = contractFundRentInComes;
	}
	
	public DictionaryData getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(DictionaryData planStatus) {
		this.planStatus = planStatus;
	}

	public BigDecimal getCurRentOverage() {
		return curRentOverage;
	}

	public BigDecimal getCurCorpusOverage() {
		return curCorpusOverage;
	}

	public BigDecimal getCurInterestOverage() {
		return curInterestOverage;
	}

	public void setCurRentOverage(BigDecimal curRentOverage) {
		this.curRentOverage = curRentOverage;
	}

	public void setCurCorpusOverage(BigDecimal curCorpusOverage) {
		this.curCorpusOverage = curCorpusOverage;
	}

	public void setCurInterestOverage(BigDecimal curInterestOverage) {
		this.curInterestOverage = curInterestOverage;
	}

	public Integer getHireList() {
		return hireList;
	}

	public void setHireList(Integer hireList) {
		this.hireList = hireList;
	}

	public BigDecimal getCurCorpusIncome() {
		return curCorpusIncome;
	}

	public BigDecimal getCurinterestIncome() {
		return curinterestIncome;
	}

	public BigDecimal getCurpenaltyIncome() {
		return curpenaltyIncome;
	}

	public BigDecimal getCurinterestAdjustIncome() {
		return curinterestAdjustIncome;
	}

	public BigDecimal getCurpenaltyAdjustIncome() {
		return curpenaltyAdjustIncome;
	}

	public void setCurCorpusIncome(BigDecimal curCorpusIncome) {
		this.curCorpusIncome = curCorpusIncome;
	}

	public void setCurinterestIncome(BigDecimal curinterestIncome) {
		this.curinterestIncome = curinterestIncome;
	}

	public void setCurpenaltyIncome(BigDecimal curpenaltyIncome) {
		this.curpenaltyIncome = curpenaltyIncome;
	}

	public void setCurinterestAdjustIncome(BigDecimal curinterestAdjustIncome) {
		this.curinterestAdjustIncome = curinterestAdjustIncome;
	}

	public void setCurpenaltyAdjustIncome(BigDecimal curpenaltyAdjustIncome) {
		this.curpenaltyAdjustIncome = curpenaltyAdjustIncome;
	}

	public Integer getWorkFlag() {
		return workFlag;
	}

	public void setWorkFlag(Integer workFlag) {
		this.workFlag = workFlag;
	}

	@Override
	public int compareTo(RentPlan o) {
		return this.getRentList() - o.getRentList();
	}

	public DictionaryData getSettleMethod() {
		return settleMethod;
	}

	public void setSettleMethod(DictionaryData settleMethod) {
		this.settleMethod = settleMethod;
	}
	
	
}
