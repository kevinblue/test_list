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

/**
 * TaxVatInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "进项发票合同")
@Table(name="VAT_INVOICE_CONTRACT")
public class VatInvoiceContract  {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name = "标识符")
	private String id;
	
	@FieldName(name = "发票号")
	@ManyToOne(targetEntity=VatInvoiceInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "INVOICE_ID")
	private VatInvoiceInfo invoiceId;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="创建时间")
	@Column(name="REGISTERED_AMT", length=22)	
	BigDecimal  registeredAmt;
	
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


	public VatInvoiceInfo getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(VatInvoiceInfo invoiceId) {
		this.invoiceId = invoiceId;
	}


	public ContractInfo getContractId() {
		return contractId;
	}


	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
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


	public BigDecimal getRegisteredAmt() {
		return registeredAmt;
	}


	public void setRegisteredAmt(BigDecimal registeredAmt) {
		this.registeredAmt = registeredAmt;
	}

}