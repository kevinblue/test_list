package com.reckon.entity.proj;

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

import com.reckon.bean.RentPlan;
import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * 
 * @author 孙传良
 * @date 2013-3-7下午03:26:04
 * @info 项目租金计划
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目租金计划")
@Table(name="PROJ_FUND_RENT_PLAN")
public class ProjFundRentPlan implements RentPlan,Comparable<RentPlan> {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="项目号")
	@ManyToOne(targetEntity=ProjInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
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
	
	@FieldName(name="测算计划日期")
	@Column(name="CAl_PLAN_DATE",length=20)
	private String   calPlanDate;
	
	@FieldName(name="实际还款额")
	@Column(name="ACTUAL_RENT",precision=22,scale=2)
	private BigDecimal actualRent;
	
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

	@FieldName(name="罚息余额")
	@Column(name="PENALTY_OVERAGE",precision=22,scale=2)
	@Deprecated
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

	@FieldName(name="本期以后所有的租金剩余")
	@Column(name="All_REMAIN_RENT",precision=22,scale=Scale.RENT_SCALE)
	private BigDecimal allRemainRent;
	
	@FieldName(name="本期以后所有的本金剩余")
	@Column(name="ALL_REMAIN_CORPUS",precision=22,scale=Scale.CORPUS_SCALE)
	private BigDecimal allRemainCorpus;
	
	@FieldName(name="本期以后所有的利息剩余")
	@Column(name="ALL_REMAIN_INTEREST",precision=22,scale=Scale.INTEREST_SCALE)
	private BigDecimal allRemainInterest;
	
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
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
		return penaltyOverage;
	}

	public void setPenaltyOverage(BigDecimal penaltyOverage) {
		this.penaltyOverage = penaltyOverage;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public String getCalPlanDate() {
		return calPlanDate;
	}

	public void setCalPlanDate(String calPlanDate) {
		this.calPlanDate = calPlanDate;
	}

	public BigDecimal getActualRent() {
		return actualRent;
	}

	public void setActualRent(BigDecimal actualRent) {
		this.actualRent = actualRent;
	}

	@Override
	public int compareTo(RentPlan o) {
		return this.getRentList() - o.getRentList();
	}
}
