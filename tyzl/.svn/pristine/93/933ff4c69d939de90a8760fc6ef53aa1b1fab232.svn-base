package com.tenwa.leasing.entity.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-3-6下午09:49:36
 * @info 合同五级划分归属表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同五级划分归属表")
@Table(name="CONTRACT_FIVE_CATEGORY")
public class ContractFiveCategory {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@FieldName(name="合同编号")
	@JoinColumn(name="CONTRACT_ID")
	@ManyToOne
	private ContractInfo contractId;
	
	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String docId;

	@FieldName(name="合同五级分类类别")
	@JoinColumn(name="CONTRACTFIVE_BUSINESS")
	@ManyToOne
	private DictionaryData contractfive_business;

	@FieldName(name="合同五级分类日期")
	@Column(name="CONTRACTFIVEDATE_BUSINESS",length=20)
	private String   contractfivedate_business;

	@FieldName(name="合同五级分类说明)")
	@Column(name="EXPLAIN_BUSINESS")
	private String explain_business;
	
	@FieldName(name="资产五级分类类别")
	@JoinColumn(name="CONTRACTFIVE_ASSETS")
	@ManyToOne
	private DictionaryData contractfive_assets;

	@FieldName(name="资产五级分类日期")
	@Column(name="CONTRACTFIVEDATE_ASSETS",length=20)
	private String   contractfivedate_assets;

	@FieldName(name="资产五级分类说明)")
	@Column(name="EXPLAIN_ASSETS")
	private String explain_assets;
	

	@FieldName(name="财务五级分类类别")
	@JoinColumn(name="CONTRACTFIVE_FINANCE")
	@ManyToOne
	private DictionaryData contractfive_finance;

	@FieldName(name="财务五级分类日期")
	@Column(name="CONTRACTFIVEDATE_FINANCE",length=20)
	private String   contractfivedate_finance;

	@FieldName(name="财务五级分类说明)")
	@Column(name="EXPLAIN_FINANCE")
	private String explain_finance;
	
	@FieldName(name="风险五级分类类别")
	@JoinColumn(name="CONTRACTFIVE_RISK")
	@ManyToOne
	private DictionaryData contractfive_risk;

	@FieldName(name="风险五级分类日期")
	@Column(name="CONTRACTFIVEDATE_RISK",length=20)
	private String   contractfivedate_risk;

	@FieldName(name="风险五级分类说明)")
	@Column(name="EXPLAIN_RISK")
	private String explain_risk;
	
	@FieldName(name="风控五级分类类别")
	@JoinColumn(name="CONTRACTFIVE_RISKMANAGER")
	@ManyToOne
	private DictionaryData contractfive_riskmanager;

	@FieldName(name="风控五级分类日期")
	@Column(name="CONTRACTFIVEDATE_RISKMANAGER",length=20)
	private String   contractfivedate_riskmanager;

	@FieldName(name="风控五级分类说明)")
	@Column(name="EXPLAIN_RISKMANAGER")
	private String explain_riskmanager;
	
	@ManyToOne
	@FieldName(name="登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;

	public String getId() {
		return id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public String getDocId() {
		return docId;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public DictionaryData getContractfive_business() {
		return contractfive_business;
	}

	public void setContractfive_business(DictionaryData contractfive_business) {
		this.contractfive_business = contractfive_business;
	}

	public String getContractfivedate_business() {
		return contractfivedate_business;
	}

	public void setContractfivedate_business(String contractfivedate_business) {
		this.contractfivedate_business = contractfivedate_business;
	}

	public String getExplain_business() {
		return explain_business;
	}

	public void setExplain_business(String explain_business) {
		this.explain_business = explain_business;
	}

	public DictionaryData getContractfive_assets() {
		return contractfive_assets;
	}

	public void setContractfive_assets(DictionaryData contractfive_assets) {
		this.contractfive_assets = contractfive_assets;
	}

	public String getContractfivedate_assets() {
		return contractfivedate_assets;
	}

	public void setContractfivedate_assets(String contractfivedate_assets) {
		this.contractfivedate_assets = contractfivedate_assets;
	}

	public String getExplain_assets() {
		return explain_assets;
	}

	public void setExplain_assets(String explain_assets) {
		this.explain_assets = explain_assets;
	}

	public DictionaryData getContractfive_finance() {
		return contractfive_finance;
	}

	public void setContractfive_finance(DictionaryData contractfive_finance) {
		this.contractfive_finance = contractfive_finance;
	}

	public String getContractfivedate_finance() {
		return contractfivedate_finance;
	}

	public void setContractfivedate_finance(String contractfivedate_finance) {
		this.contractfivedate_finance = contractfivedate_finance;
	}

	public String getExplain_finance() {
		return explain_finance;
	}

	public void setExplain_finance(String explain_finance) {
		this.explain_finance = explain_finance;
	}

	public DictionaryData getContractfive_risk() {
		return contractfive_risk;
	}

	public void setContractfive_risk(DictionaryData contractfive_risk) {
		this.contractfive_risk = contractfive_risk;
	}

	public String getContractfivedate_risk() {
		return contractfivedate_risk;
	}

	public void setContractfivedate_risk(String contractfivedate_risk) {
		this.contractfivedate_risk = contractfivedate_risk;
	}

	public String getExplain_risk() {
		return explain_risk;
	}

	public void setExplain_risk(String explain_risk) {
		this.explain_risk = explain_risk;
	}

	public DictionaryData getContractfive_riskmanager() {
		return contractfive_riskmanager;
	}

	public void setContractfive_riskmanager(DictionaryData contractfive_riskmanager) {
		this.contractfive_riskmanager = contractfive_riskmanager;
	}

	public String getContractfivedate_riskmanager() {
		return contractfivedate_riskmanager;
	}

	public void setContractfivedate_riskmanager(String contractfivedate_riskmanager) {
		this.contractfivedate_riskmanager = contractfivedate_riskmanager;
	}

	public String getExplain_riskmanager() {
		return explain_riskmanager;
	}

	public void setExplain_riskmanager(String explain_riskmanager) {
		this.explain_riskmanager = explain_riskmanager;
	}
}
