package com.tenwa.leasing.entity.contract;

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
import com.tenwa.leasing.entity.proj.ProjInfo;


/**
 * 
 * @author 李超杰
 * @date 2014-11-14下午02:50:53
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "合同开票类型信息")
@Table(name="CONTRACT_INVOICE_TYPE")
public class ContractInvoiceType {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "纳税人类别[是否一般纳税人（是/否）]")
	@JoinColumn(name="TAX_REG_Type")
	@ManyToOne
	private DictionaryData taxRegType;
	
	@FieldName(name = "纳税人识别号/国税登记号")
	@Column(name="TAX_REG_CODE", length=50)
	private String taxRegCode;
	
	@FieldName(name = "开户行")
	@Column(name="TAX_BANK", length=100)
	private String taxBank;
	
	@FieldName(name = "开户账号")
	@Column(name="TAX_ACC", length=50)
	private String taxAcc;
	
	@FieldName(name = "开票地址")
	@Column(name="INVOICE_ADD", length=100)
	private String invoiceAdd;
	
	@FieldName(name = "开票电话")
	@Column(name="INVOICE_PHONE", length=50)
	private String invoicePhone;
	
	//新添加两种发票和税率类型
	@FieldName(name="资产管理税率")
	@JoinColumn(name="ASSET_SRATIO")
	@ManyToOne
	private DictionaryData assetSratio;
	
	@FieldName(name="资产管理费发票类型")
	@JoinColumn(name="ASSETS_MONEY")
	@ManyToOne
	private DictionaryData assetsMoney;
	
	@FieldName(name="财务顾问税率")
	@JoinColumn(name="ADVISER_RATIO")
	@ManyToOne
	private DictionaryData adviserRatio;
	
	@FieldName(name="财务顾问费发票类型")
	@JoinColumn(name="ADVISER_MONEY")
	@ManyToOne
	private DictionaryData adviserMoney;
		
	
	@FieldName(name="租金发票类型")
	@JoinColumn(name="RENT_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData rentInvoiceType;
	
	@FieldName(name="开票方式")
	@JoinColumn(name="INVOICE_TYPE")
	@ManyToOne
	private DictionaryData invoiceType;
	
	@FieldName(name="开票说明")
	@Column(name="INVOICE_MEMO", length=2000)	
	private String invoicememo;
	
	@FieldName(name = "利息税率")
	@JoinColumn(name="INTEREST_RATE")
	@ManyToOne
	private DictionaryData interestRate;
	
	@FieldName(name="利息发票类型")
	@JoinColumn(name="INTEREST_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData interestInvoiceType;
	
	@FieldName(name = "租金税率")
	@JoinColumn(name="RENT_RATE")
	@ManyToOne
	private DictionaryData rentRate;
	

	
	@FieldName(name = "本金税率")
	@JoinColumn(name="CORPUS_RATE")
	@ManyToOne
	private DictionaryData corpusRate;
	
	@FieldName(name="本金发票类型")
	@JoinColumn(name="CORPUS_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData corpusInvoiceType;
	
	@FieldName(name = "咨询服务费税率")
	@JoinColumn(name="SERVICE_RATE")
	@ManyToOne
	private DictionaryData serviceRate;
	
	@FieldName(name="咨询服务费发票类型")
	@JoinColumn(name="SERVICE_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData serviceInvoiceType;
	
	@FieldName(name = "手续费税率")
	@JoinColumn(name="HAND_RATE")
	@ManyToOne
	private DictionaryData handRate;
	
	@FieldName(name="手续费发票类型")
	@JoinColumn(name="HAND_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData handInvoiceType;
	
	@FieldName(name = "首付款税率")
	@JoinColumn(name="FIRSTPAYMENT_RATE")
	@ManyToOne
	private DictionaryData firstpaymentRate;
	
	@FieldName(name="首付款发票类型")
	@JoinColumn(name="FIRSTPAYMENT_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData firstpaymentInvoiceType;
	
	@FieldName(name = "保险费税率")
	@JoinColumn(name="INSURANCELESSOR_RATE")
	@ManyToOne
	private DictionaryData insurancelessorRate;
	
	@FieldName(name="保险费发票类型")
	@JoinColumn(name="INSURANCELESSOR_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData insurancelessorInvoiceType;
	
	@FieldName(name = "名义留购价税率")
	@JoinColumn(name="NOMINAL_RATE")
	@ManyToOne
	private DictionaryData nominalRate;
	
	@FieldName(name="名义留购价发票类型")
	@JoinColumn(name="NOMINAL_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData nominalInvoiceType;
	
	@FieldName(name = "厂商返利")
	@JoinColumn(name="RETURNAMT_RATE")
	@ManyToOne
	private DictionaryData returnamtRate;
	
	@FieldName(name="厂商返利发票类型")
	@JoinColumn(name="RETURNAMT_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData returnamtInvoiceType;

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
	
	

	public DictionaryData getAssetSratio() {
		return assetSratio;
	}

	public void setAssetSratio(DictionaryData assetSratio) {
		this.assetSratio = assetSratio;
	}

	public DictionaryData getAssetsMoney() {
		return assetsMoney;
	}

	public void setAssetsMoney(DictionaryData assetsMoney) {
		this.assetsMoney = assetsMoney;
	}

	public DictionaryData getAdviserRatio() {
		return adviserRatio;
	}

	public void setAdviserRatio(DictionaryData adviserRatio) {
		this.adviserRatio = adviserRatio;
	}

	public DictionaryData getAdviserMoney() {
		return adviserMoney;
	}

	public void setAdviserMoney(DictionaryData adviserMoney) {
		this.adviserMoney = adviserMoney;
	}

	public DictionaryData getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(DictionaryData interestRate) {
		this.interestRate = interestRate;
	}

	public DictionaryData getInterestInvoiceType() {
		return interestInvoiceType;
	}

	public void setInterestInvoiceType(DictionaryData interestInvoiceType) {
		this.interestInvoiceType = interestInvoiceType;
	}

	public DictionaryData getRentRate() {
		return rentRate;
	}

	public void setRentRate(DictionaryData rentRate) {
		this.rentRate = rentRate;
	}

	public DictionaryData getCorpusRate() {
		return corpusRate;
	}

	public void setCorpusRate(DictionaryData corpusRate) {
		this.corpusRate = corpusRate;
	}

	public DictionaryData getCorpusInvoiceType() {
		return corpusInvoiceType;
	}

	public void setCorpusInvoiceType(DictionaryData corpusInvoiceType) {
		this.corpusInvoiceType = corpusInvoiceType;
	}

	public DictionaryData getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(DictionaryData serviceRate) {
		this.serviceRate = serviceRate;
	}

	public DictionaryData getServiceInvoiceType() {
		return serviceInvoiceType;
	}

	public void setServiceInvoiceType(DictionaryData serviceInvoiceType) {
		this.serviceInvoiceType = serviceInvoiceType;
	}

	public DictionaryData getHandRate() {
		return handRate;
	}

	public void setHandRate(DictionaryData handRate) {
		this.handRate = handRate;
	}

	public DictionaryData getHandInvoiceType() {
		return handInvoiceType;
	}

	public void setHandInvoiceType(DictionaryData handInvoiceType) {
		this.handInvoiceType = handInvoiceType;
	}

	public DictionaryData getFirstpaymentRate() {
		return firstpaymentRate;
	}

	public void setFirstpaymentRate(DictionaryData firstpaymentRate) {
		this.firstpaymentRate = firstpaymentRate;
	}

	public DictionaryData getFirstpaymentInvoiceType() {
		return firstpaymentInvoiceType;
	}

	public void setFirstpaymentInvoiceType(DictionaryData firstpaymentInvoiceType) {
		this.firstpaymentInvoiceType = firstpaymentInvoiceType;
	}

	public DictionaryData getInsurancelessorRate() {
		return insurancelessorRate;
	}

	public void setInsurancelessorRate(DictionaryData insurancelessorRate) {
		this.insurancelessorRate = insurancelessorRate;
	}

	public DictionaryData getInsurancelessorInvoiceType() {
		return insurancelessorInvoiceType;
	}

	public void setInsurancelessorInvoiceType(
			DictionaryData insurancelessorInvoiceType) {
		this.insurancelessorInvoiceType = insurancelessorInvoiceType;
	}

	public DictionaryData getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(DictionaryData nominalRate) {
		this.nominalRate = nominalRate;
	}

	public DictionaryData getNominalInvoiceType() {
		return nominalInvoiceType;
	}

	public void setNominalInvoiceType(DictionaryData nominalInvoiceType) {
		this.nominalInvoiceType = nominalInvoiceType;
	}

	public DictionaryData getReturnamtRate() {
		return returnamtRate;
	}

	public void setReturnamtRate(DictionaryData returnamtRate) {
		this.returnamtRate = returnamtRate;
	}

	public DictionaryData getReturnamtInvoiceType() {
		return returnamtInvoiceType;
	}

	public void setReturnamtInvoiceType(DictionaryData returnamtInvoiceType) {
		this.returnamtInvoiceType = returnamtInvoiceType;
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

	public DictionaryData getTaxRegType() {
		return taxRegType;
	}

	public void setTaxRegType(DictionaryData taxRegType) {
		this.taxRegType = taxRegType;
	}

	public String getTaxRegCode() {
		return taxRegCode;
	}

	public void setTaxRegCode(String taxRegCode) {
		this.taxRegCode = taxRegCode;
	}

	public String getTaxBank() {
		return taxBank;
	}

	public void setTaxBank(String taxBank) {
		this.taxBank = taxBank;
	}

	public String getTaxAcc() {
		return taxAcc;
	}

	public void setTaxAcc(String taxAcc) {
		this.taxAcc = taxAcc;
	}

	public String getInvoiceAdd() {
		return invoiceAdd;
	}

	public void setInvoiceAdd(String invoiceAdd) {
		this.invoiceAdd = invoiceAdd;
	}

	public String getInvoicePhone() {
		return invoicePhone;
	}

	public void setInvoicePhone(String invoicePhone) {
		this.invoicePhone = invoicePhone;
	}

	public DictionaryData getRentInvoiceType() {
		return rentInvoiceType;
	}

	public void setRentInvoiceType(DictionaryData rentInvoiceType) {
		this.rentInvoiceType = rentInvoiceType;
	}

	public DictionaryData getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(DictionaryData invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoicememo() {
		return invoicememo;
	}

	public void setInvoicememo(String invoicememo) {
		this.invoicememo = invoicememo;
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
