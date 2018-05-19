package com.tenwa.leasing.entity.base;

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

@Entity
@FieldName(name = "税率信息")
@Table(name = "RATE_INFO")
public class RateInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "租赁形式")
	@JoinColumn(name = "LEAS_FORM")
	private DictionaryData leasForm;

	@ManyToOne
	@FieldName(name="纳税人类别")
	@JoinColumn(name="TAX_REG_Type")
	private DictionaryData taxRegType;
	
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
	
	@FieldName(name="租金发票类型")
	@JoinColumn(name="RENT_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData rentInvoiceType;
	
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
	

	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DictionaryData getLeasForm() {
		return leasForm;
	}

	public void setLeasForm(DictionaryData leasForm) {
		this.leasForm = leasForm;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DictionaryData getRentInvoiceType() {
		return rentInvoiceType;
	}

	public void setRentInvoiceType(DictionaryData rentInvoiceType) {
		this.rentInvoiceType = rentInvoiceType;
	}


	public DictionaryData getCorpusInvoiceType() {
		return corpusInvoiceType;
	}

	public void setCorpusInvoiceType(DictionaryData corpusInvoiceType) {
		this.corpusInvoiceType = corpusInvoiceType;
	}


	public DictionaryData getServiceInvoiceType() {
		return serviceInvoiceType;
	}

	public void setServiceInvoiceType(DictionaryData serviceInvoiceType) {
		this.serviceInvoiceType = serviceInvoiceType;
	}


	public DictionaryData getHandInvoiceType() {
		return handInvoiceType;
	}

	public void setHandInvoiceType(DictionaryData handInvoiceType) {
		this.handInvoiceType = handInvoiceType;
	}

	public DictionaryData getNominalInvoiceType() {
		return nominalInvoiceType;
	}

	public void setNominalInvoiceType(DictionaryData nominalInvoiceType) {
		this.nominalInvoiceType = nominalInvoiceType;
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

	public DictionaryData getServiceRate() {
		return serviceRate;
	}

	public void setServiceRate(DictionaryData serviceRate) {
		this.serviceRate = serviceRate;
	}

	public DictionaryData getHandRate() {
		return handRate;
	}

	public void setHandRate(DictionaryData handRate) {
		this.handRate = handRate;
	}

	public DictionaryData getNominalRate() {
		return nominalRate;
	}

	public void setNominalRate(DictionaryData nominalRate) {
		this.nominalRate = nominalRate;
	}

	public DictionaryData getTaxRegType() {
		return taxRegType;
	}

	public void setTaxRegType(DictionaryData taxRegType) {
		this.taxRegType = taxRegType;
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

	public void setInsurancelessorInvoiceType(DictionaryData insurancelessorInvoiceType) {
		this.insurancelessorInvoiceType = insurancelessorInvoiceType;
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

}