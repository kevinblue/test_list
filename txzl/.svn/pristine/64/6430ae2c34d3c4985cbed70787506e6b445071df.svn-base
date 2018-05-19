package com.tenwa.leasing.entity.factoring;

import java.io.Serializable;

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
import com.tenwa.leasing.entity.contract.ContractEquip;
import com.tenwa.leasing.entity.contract.ContractInfo;

@Entity
@FieldName(name = "保理争议申请表")
@Table(name = "FACTORING_CONTROVERSY")
public class FactoringControversy implements Serializable {
	
	private static final long serialVersionUID = 1842602351876048320L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "合同编号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "发票号")
	@JoinColumn(name="INVOICE_CODE")
	private ContractEquip invoicecode;
	
	@FieldName(name="争议申请编号")
	@Column(name="APPLICATION_NUMBER", length=20)	
	private String applicationNumber;
	
	@FieldName(name="争议说明")
	@Column(name="EXPLAINATION", length = 4000)	
	private String explaination;
	
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

	public ContractEquip getInvoicecode() {
		return invoicecode;
	}

	public void setInvoicecode(ContractEquip invoicecode) {
		this.invoicecode = invoicecode;
	}

	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	public String getExplaination() {
		return explaination;
	}

	public void setExplaination(String explaination) {
		this.explaination = explaination;
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