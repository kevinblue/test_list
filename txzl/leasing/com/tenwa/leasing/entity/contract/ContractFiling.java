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

@Entity
@FieldName(name = "合同归档表")
@Table(name = "CONTRACT_FILING")
public class ContractFiling {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "系统流水号")
	@Column(name = "FILING_SN")
	private String filingSn;
	
	@FieldName(name = "系统流水号")
	@Column(name = "FILING_NAME")
	private String filingName;
	
	@ManyToOne
	@FieldName(name = "合同编号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@ManyToOne
	@FieldName(name = "档案类型")
	@JoinColumn(name = "FILLING_TYPE")
	private DictionaryData fillingType;
	
	@FieldName(name = "档案编号")
	@Column(name = "FILLING_NUMBER")
	private String fillingNumber;
	
	@FieldName(name = "柜号")
	@Column(name = "CONTAINER_NUMBER")
	private String containerNumber;
	
	@FieldName(name = "归档状态")	//
	@Column(name = "FILING_STATUS", columnDefinition="varchar(20) default '未归档'")
	private String filingStatus; 
	
	@ManyToOne
	@FieldName(name = "归档人")
	@JoinColumn(name = "FILLING_USER")
	private User fillingUser;
	
	@FieldName(name = "生效时间")
	@Column(name = "COMEIN_DATE")
	private String comeinDate;
	
	@FieldName(name = "归档时间")
	@Column(name = "FILING_DATE")
	private String filingDate;
	
	@FieldName(name = "页数")
	@Column(name = "PAGE")
	private String page;
	
	@FieldName(name = "备注")
	@Column(name = "NOTE_", length = 2000)
	private String note;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
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

	public String getFilingSn() {
		return filingSn;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public DictionaryData getFillingType() {
		return fillingType;
	}

	public String getFillingNumber() {
		return fillingNumber;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

	public User getFillingUser() {
		return fillingUser;
	}

	public String getComeinDate() {
		return comeinDate;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public String getNote() {
		return note;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFilingSn(String filingSn) {
		this.filingSn = filingSn;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public void setFillingType(DictionaryData fillingType) {
		this.fillingType = fillingType;
	}

	public void setFillingNumber(String fillingNumber) {
		this.fillingNumber = fillingNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public void setFillingUser(User fillingUser) {
		this.fillingUser = fillingUser;
	}

	public void setComeinDate(String comeinDate) {
		this.comeinDate = comeinDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getFilingName() {
		return filingName;
	}

	public void setFilingName(String filingName) {
		this.filingName = filingName;
	}

	
}
