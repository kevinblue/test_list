package com.tenwa.leasing.entity.contract;

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
@FieldName(name = "纳税人备用账号信息")
@Table(name = "CONTRACT_TAX_PEOPLE")
public class ContractTaxpeople {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICE_ID")
	@FieldName(name = "合同开票主键ID")
	private ContractInvoiceType contractinvoicetype;

	@FieldName(name = "纳税人类别[是否一般纳税人（是/否）]")
	@JoinColumn(name="TAX_REG_Type")
	@ManyToOne
	private DictionaryData taxRegType;

	@FieldName(name = "纳税人识别号")
	@Column(name = "TAXREG_CODE", length = 50)
	private String taxregcode;
	
	
	@FieldName(name = "开户账号")
	@Column(name = "SPARE_TAX_ACC", length = 50)
	private String sparetaxacc;

	@FieldName(name = "开户银行")
	@Column(name = "SPARE_TAX_BANK", length = 100)
	private String sparetaxBank;

	@FieldName(name = "开票地址")
	@Column(name = "SPARE_INVOICE_ADD", length = 100)
	private String spareinvoiceAdd;

	@FieldName(name = "开票电话")
	@Column(name = "SPARE_INVOICE_PHONE", length = 50)
	private String spareinvoicePhone;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ContractInvoiceType getContractinvoicetype() {
		return contractinvoicetype;
	}

	public void setContractinvoicetype(ContractInvoiceType contractinvoicetype) {
		this.contractinvoicetype = contractinvoicetype;
	}

	public DictionaryData getTaxRegType() {
		return taxRegType;
	}

	public void setTaxRegType(DictionaryData taxRegType) {
		this.taxRegType = taxRegType;
	}

	public String getTaxregcode() {
		return taxregcode;
	}

	public void setTaxregcode(String taxregcode) {
		this.taxregcode = taxregcode;
	}

	public String getSparetaxacc() {
		return sparetaxacc;
	}

	public void setSparetaxacc(String sparetaxacc) {
		this.sparetaxacc = sparetaxacc;
	}

	public String getSparetaxBank() {
		return sparetaxBank;
	}

	public void setSparetaxBank(String sparetaxBank) {
		this.sparetaxBank = sparetaxBank;
	}

	public String getSpareinvoiceAdd() {
		return spareinvoiceAdd;
	}

	public void setSpareinvoiceAdd(String spareinvoiceAdd) {
		this.spareinvoiceAdd = spareinvoiceAdd;
	}

	public String getSpareinvoicePhone() {
		return spareinvoicePhone;
	}

	public void setSpareinvoicePhone(String spareinvoicePhone) {
		this.spareinvoicePhone = spareinvoicePhone;
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