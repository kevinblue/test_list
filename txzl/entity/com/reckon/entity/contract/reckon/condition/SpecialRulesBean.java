package com.reckon.entity.contract.reckon.condition;


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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.proj.ProjInfo;
@Entity
@FieldName(name = "分段配置")
@Table(name = "SPECIAL_RULES_BEAN")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialRulesBean {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name = "合同编号")
	@OneToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "项目编号")
	@OneToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name="开始期次")
	@Column(name="START_LIST")
	private Integer startList;//开始期项
	
	@FieldName(name="结束期次")
	@Column(name="End_LIST")
	private Integer endList ;//截止期项
	
	@ManyToOne
	@FieldName(name="计算方式")
	@JoinColumn(name="REGULAR_SETTLEMETHOD")
	private DictionaryData regularSettlemethod;//计算方式
	
	@ManyToOne
	@FieldName(name="支付频率")
	@JoinColumn(name="REGULAR_INCOMENUMBERYEAR")
	private DictionaryData regularIncomenumberyear;
	
	@FieldName(name="对应金额")
	@Column(name="REGULAR_PLANMONEY")
	private BigDecimal regularPlanmoney;//对应金额
	
	@FieldName(name="间隔月数")
	@Column(name="REGULAR_MONTHS")
	private Integer regularMonths;//间隔月数
	

	@ManyToOne
	@FieldName(name="利息计算方式")
	@JoinColumn(name="RATEFLOAT_TYPE")
	private DictionaryData ratefloattype;
	
	@FieldName(name="利率调整值")
	@Column(name="RATE_FLOAT_AMT", precision = 22,scale = Scale.DEFAULT)
	private BigDecimal rateFloatAmt;
	
	@FieldName(name="基准利率")
	@Column(name="BASE_RATE", precision = 22,scale = Scale.GENERAL_RATE)
	private BigDecimal baseRate;
	
	@FieldName(name="租赁年利率")
	@Column(name="YEAR_RATE")
	private String yearRate;//租赁年利率
	
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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public Integer getStartList() {
		return startList;
	}

	public void setStartList(Integer startList) {
		this.startList = startList;
	}

	public Integer getEndList() {
		return endList;
	}

	public void setEndList(Integer endList) {
		this.endList = endList;
	}

	public DictionaryData getRegularSettlemethod() {
		return regularSettlemethod;
	}

	public void setRegularSettlemethod(DictionaryData regularSettlemethod) {
		this.regularSettlemethod = regularSettlemethod;
	}

	public DictionaryData getRegularIncomenumberyear() {
		return regularIncomenumberyear;
	}

	public void setRegularIncomenumberyear(DictionaryData regularIncomenumberyear) {
		this.regularIncomenumberyear = regularIncomenumberyear;
	}

	public BigDecimal getRegularPlanmoney() {
		return regularPlanmoney;
	}

	public void setRegularPlanmoney(BigDecimal regularPlanmoney) {
		this.regularPlanmoney = regularPlanmoney;
	}

	public Integer getRegularMonths() {
		return regularMonths;
	}

	public void setRegularMonths(Integer regularMonths) {
		this.regularMonths = regularMonths;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
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

	public DictionaryData getRatefloattype() {
		return ratefloattype;
	}

	public void setRatefloattype(DictionaryData ratefloattype) {
		this.ratefloattype = ratefloattype;
	}

	public BigDecimal getRateFloatAmt() {
		return rateFloatAmt;
	}

	public void setRateFloatAmt(BigDecimal rateFloatAmt) {
		this.rateFloatAmt = rateFloatAmt;
	}

	public BigDecimal getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(BigDecimal baseRate) {
		this.baseRate = baseRate;
	}
	
	

	
}
