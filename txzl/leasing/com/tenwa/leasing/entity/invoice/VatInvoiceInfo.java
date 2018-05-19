package com.tenwa.leasing.entity.invoice;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;

/**
 * TaxVatInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "进项开票信息")
@Table(name="VAT_INVOICE_INFO")
public class VatInvoiceInfo  {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name = "标识符")
	private String id;
	
	@FieldName(name = "发票号")
	@Column(name = "INVOICE_NO", length = 200)
	private String invoiceNo;
	
	@FieldName(name="合同ID")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "合同编号")
	@Column(name = "CONTRACT_NUMBER", length = 200)
	private String contractnumber;
	
	@FieldName(name="发票金额")
	@Column(name="INVOICE_MONEY",precision=22,scale=2)
	private BigDecimal invoiceMoney;
	
	@FieldName(name="登记日期")
	@Column(name="RECORD_DATE", length=20)	
	private String recordDate;
	
	@FieldName(name="购货单位")
	@Column(name="PURCHASE_UNITS", length = 200)
	private String purchasenits;
	
	@FieldName(name="供应商")
	@ManyToOne(targetEntity=CustInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="SUPPLIER")
	private CustInfo supplier;
	
	@FieldName(name = "货物名称")
	@Column(name = "GOODS_NAME", length = 200)
	private String goodsName;
	
	@FieldName(name = "备注")
	@Column(name = "memo", length = 200)
	private String memo;
	
	@FieldName(name="发票状态 (null||0)：未提交||1：确认中||2：已确认||3：已退回")
	@Column(name="INVOICE_STATUS")
	private Integer invoiceStatus;
	
	@FieldName(name="含税金额")
	@Column(name="CORPUS_TAX_MONEY",precision=22,scale=2)
	private BigDecimal corpusTaxMoney;
	
	@FieldName(name="税率")
	@Column(name="TAX_RATE",precision=22,scale=2)
	private BigDecimal taxRate;
	
	@FieldName(name="税额")
	@Column(name="TAX_MONEY",precision=22,scale=2)
	private BigDecimal taxMoney;
	
	@FieldName(name="资金投放计划")
	@ManyToOne(targetEntity=ContractFundFundPlan.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_FUND_FUND_PLAN_ID")
	private ContractFundFundPlan ContractFundFundPlanId;
	
	@FieldName(name="认证结果")
	@Column(name="CERTIFICATION_RESULTS", length=200)
	private String certificationResults;

	@FieldName(name="认证时间")
	@Column(name="CERTIFICATION_DATE", length=20)
	private String certificationDate;
	
	@FieldName(name="开票时间")
	@Column(name="INVOICE_DATE", length=20)
	private String invoiceDate;

	
	@ManyToOne
	@FieldName(name="上传文件名")
	@JoinColumn(name="UPLOAD_ID")
	private BaseFile upLoadId ;
	
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


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public ContractInfo getContractId() {
		return contractId;
	}


	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}


	public String getContractnumber() {
		return contractnumber;
	}


	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}


	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}


	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}


	public String getRecordDate() {
		return recordDate;
	}


	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}


	public String getPurchasenits() {
		return purchasenits;
	}


	public void setPurchasenits(String purchasenits) {
		this.purchasenits = purchasenits;
	}


	public CustInfo getSupplier() {
		return supplier;
	}


	public void setSupplier(CustInfo supplier) {
		this.supplier = supplier;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getMemo() {
		return memo;
	}


	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}


	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}


	public BigDecimal getCorpusTaxMoney() {
		return corpusTaxMoney;
	}


	public void setCorpusTaxMoney(BigDecimal corpusTaxMoney) {
		this.corpusTaxMoney = corpusTaxMoney;
	}


	public BigDecimal getTaxRate() {
		return taxRate;
	}


	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}


	public BigDecimal getTaxMoney() {
		return taxMoney;
	}


	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}


	public ContractFundFundPlan getContractFundFundPlanId() {
		return ContractFundFundPlanId;
	}


	public void setContractFundFundPlanId(
			ContractFundFundPlan contractFundFundPlanId) {
		ContractFundFundPlanId = contractFundFundPlanId;
	}


	public String getCertificationResults() {
		return certificationResults;
	}


	public void setCertificationResults(String certificationResults) {
		this.certificationResults = certificationResults;
	}


	public String getCertificationDate() {
		return certificationDate;
	}


	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}


	public String getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public BaseFile getUpLoadId() {
		return upLoadId;
	}


	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
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


	@Override
	public String toString() {
		return "VatInvoiceInfo [id=" + id + ", invoiceNo=" + invoiceNo
				+ ", contractnumber=" + contractnumber + ", invoiceMoney="
				+ invoiceMoney + ", recordDate=" + recordDate
				+ ", purchasenits=" + purchasenits + ", supplier=" + supplier
				+ ", goodsName=" + goodsName + ", memo=" + memo + ", creator="
				+ creator + ", createDate=" + createDate + ", modificator="
				+ modificator + ", modifyDate=" + modifyDate
				+ ", invoiceStatus=" + invoiceStatus + ", corpusTaxMoney="
				+ corpusTaxMoney + ", taxRate=" + taxRate + ", taxMoney="
				+ taxMoney + ", certificationResults=" + certificationResults
				+ ", certificationDate=" + certificationDate + ", invoiceDate="
				+ invoiceDate + ", upLoadId=" + upLoadId + "]";
	}

}