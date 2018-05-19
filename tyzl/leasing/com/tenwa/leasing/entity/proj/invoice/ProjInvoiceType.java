package com.tenwa.leasing.entity.proj.invoice;

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
 * @author 孙传良
 * @date 2013-3-6下午02:50:53
 * @info
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目开票类型信息")
@Table(name="PROJ_INVOICE_TYPE")
public class ProjInvoiceType {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ProjInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name="租金发票类型")
	@JoinColumn(name="RENT_INVOICE_TYPE")
	@ManyToOne
	private DictionaryData rentInvoiceType;

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
	
	
	@FieldName(name="发票种类")
	@JoinColumn(name="INVOICE_TYPE")
	@ManyToOne
	private DictionaryData invoiceType;
	
	@FieldName(name="开票说明")
	@Column(name="INVOICE_MEMO", length=2000)	
	private String invoicememo;

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

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public DictionaryData getRentInvoiceType() {
		return rentInvoiceType;
	}

	public void setRentInvoiceType(DictionaryData rentInvoiceType) {
		this.rentInvoiceType = rentInvoiceType;
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
