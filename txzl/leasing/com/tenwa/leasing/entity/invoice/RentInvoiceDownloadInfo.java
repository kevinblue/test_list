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
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * @author rovine
 *	2014-12-1
 *  租金发票信息
 *  
 */
@Entity
@FieldName(name = "租金发票导入数据存储")
@Table(name="RENT_INVOICE_DOWNLOAD_INFO")
public class RentInvoiceDownloadInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号--本金一次性开票存入")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="RentInvoiceInfo表的主键ID")
	@ManyToOne(targetEntity=RentInvoiceInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="RENT_INVOICE_ID")
	private RentInvoiceInfo rentInvoiceId;
	
	@FieldName(name="发票号")
	@Column(name="INVOICE_NO", length=50)
	private String invoiceNo;
	
	@FieldName(name="发票代码")
	@Column(name="INVOICE_CODE", length=50)
	private String invoiceCode;
	
	@FieldName(name="导出流水编号")
	@Column(name="OUT_NO", length=50)
	private String outNo;
	
	@FieldName(name="含税金额")
	@Column(name="TAX_INCLUDED_MONEY",precision=22,scale=2)
	private BigDecimal taxIncludedMoney;
	
	@FieldName(name="税率")
	@Column(name="TAX_RATE",precision=22,scale=2)
	private BigDecimal taxRate;
	
	@FieldName(name="税额")
	@Column(name="TAX_MONEY",precision=22,scale=2)
	private BigDecimal taxMoney;
	
	@FieldName(name="开票日期")
	@Column(name="INVOICE_DATE", length=200)
	private String invoiceDate;
	
	@FieldName(name="开票金额")
	@Column(name="INVOICE_MONEY", precision=22,scale=2)
	private BigDecimal invoiceMoney;
	
	
	@FieldName(name="开票类类型")
	@Column(name="INVOICE_TYPE", length=100)
	private String invoiceType;
	
	
	@FieldName(name="开户行")
	@Column(name="TAX_BANK", length=100)
	private String taxBank;
	
	@FieldName(name="开户帐号")
	@Column(name="TAX_ACC", length=100)
	private String taxAcc;
	
	@FieldName(name="电话")
	@Column(name="TAX_TEL", length=50)
	private String taxTel;
	
	@FieldName(name="纳税人识别号")
	@Column(name="TAX_REG_CODE", length=100)
	private String taxRegCode;
	
	@FieldName(name="地址")
	@Column(name="TAX_ADDR", length=200)
	private String taxAddr;
	
	@FieldName(name="快递单号")
	@Column(name="EMS_NO", length=50)
	private String emsNo;
	
	@FieldName(name="寄送时间")
	@Column(name="SEND_DATE", length=20)
	private String sendDate;
	
	@FieldName(name="导出人")
	@Column(name="EXP_USER", length=50)
	private String exportUser;
	
	@FieldName(name="导出时间")
	@Column(name="EXPORT_DATE", length=50)
	private String exportDate;
	
	@FieldName(name="红冲状态，红冲状态-1 代表‘红冲’ 0代表‘正常’， 1代表被红冲")
	@Column(name="HC_STATUS",columnDefinition = "varchar(10) DEFAULT 0")
	private String hcStatus;
	
	@FieldName(name = "是否导出,0--未导出  1--已导出")
	@Column(name = "IS_EXPORT",columnDefinition = "varchar(10) DEFAULT 0")
	private String isExport;
	
	@FieldName(name = "是否回导,0--未回导  1--已回导")
	@Column(name = "IS_BACKIMPORT",columnDefinition = "varchar(10) DEFAULT 0")
	private String isBackImport;
	
	@ManyToOne
	@FieldName(name="上传文件名")
	@JoinColumn(name="UPLOAD_ID")
	private BaseFile upLoadId ;

	@FieldName(name = "作废标志")
	@Column(name = "IS_CANCEL",length=50)
	private String isCancel;
	
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

	public RentInvoiceInfo getRentInvoiceId() {
		return rentInvoiceId;
	}

	public void setRentInvoiceId(RentInvoiceInfo rentInvoiceId) {
		this.rentInvoiceId = rentInvoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public BigDecimal getTaxIncludedMoney() {
		return taxIncludedMoney;
	}

	public void setTaxIncludedMoney(BigDecimal taxIncludedMoney) {
		this.taxIncludedMoney = taxIncludedMoney;
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

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
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

	public String getTaxTel() {
		return taxTel;
	}

	public void setTaxTel(String taxTel) {
		this.taxTel = taxTel;
	}

	public String getTaxRegCode() {
		return taxRegCode;
	}

	public void setTaxRegCode(String taxRegCode) {
		this.taxRegCode = taxRegCode;
	}

	public String getTaxAddr() {
		return taxAddr;
	}

	public void setTaxAddr(String taxAddr) {
		this.taxAddr = taxAddr;
	}

	public String getEmsNo() {
		return emsNo;
	}

	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getExportUser() {
		return exportUser;
	}

	public void setExportUser(String exportUser) {
		this.exportUser = exportUser;
	}

	public String getExportDate() {
		return exportDate;
	}

	public void setExportDate(String exportDate) {
		this.exportDate = exportDate;
	}

	public String getHcStatus() {
		return hcStatus;
	}

	public void setHcStatus(String hcStatus) {
		this.hcStatus = hcStatus;
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

	public String getIsBackImport() {
		return isBackImport;
	}

	public void setIsBackImport(String isBackImport) {
		this.isBackImport = isBackImport;
	}

	public String getIsExport() {
		return isExport;
	}

	public void setIsExport(String isExport) {
		this.isExport = isExport;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	
}
