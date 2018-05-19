package com.tenwa.leasing.entity.cust;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:23:41
 * @info 客户信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "客户信息")
@Table(name="CUST_INFO")
public class CustInfo {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="客户编号")
	@Column(name="CUST_NUMBER", length=50)
	private String custNumber;
	
	@FieldName(name="客户名称")
	@Column(name="CUST_NAME", length=100)
	private String custName;
	
	@ManyToOne
	@FieldName(name = "客户类别[法律性质的个人/法人/经销商][D]")
	@JoinColumn(name="cust_class")
	private DictionaryData custClass;
 
	@ManyToOne
	@FieldName(name = "客户经理")
	@JoinColumn(name="cust_manage")
	private User custManage;
	
	@FieldName(name="是否作废(伪删除)[是/否][S]")
	@Column(name="INVALID_",length=2)
	private String invalid;
	
	@FieldName(name="是否草稿[是/否][S]")
	@Column(name="DRAFT_",length=2)
	private String draft;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length=20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	@JsonIgnore
	@FieldName(name = "客户类别[业务性质的承租/担保等][D]")
	@OneToMany(mappedBy="custId",  fetch=FetchType.LAZY)
	private Set<CustTypeInfo> custType = new HashSet<CustTypeInfo>();
	
	@JsonIgnore
	@OneToOne(mappedBy="custId",  fetch=FetchType.LAZY)
	@FieldName(name="法人客户")
	private CustInfoCompany custInfoCompany;
	
	@JsonIgnore
	@OneToOne(mappedBy="custId",  fetch=FetchType.LAZY)
	@FieldName(name="集团客户")
	private CustInfoClique custInfoClique;
	
	@JsonIgnore
	@OneToOne(mappedBy="custId", fetch=FetchType.LAZY)
	@FieldName(name="自然人客户")
	private CustInfoPerson custInfoPerson;
	
	
	public CustInfoClique getCustInfoClique() {
		return custInfoClique;
	}

	public void setCustInfoClique(CustInfoClique custInfoClique) {
		this.custInfoClique = custInfoClique;
	}

	public Set<CustTypeInfo> getCustType() {
		return custType;
	}

	public void setCustType(Set<CustTypeInfo> custType) {
		this.custType = custType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustNumber() {
		return custNumber;
	}

	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public DictionaryData getCustClass() {
		return custClass;
	}

	public void setCustClass(DictionaryData custClass) {
		this.custClass = custClass;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
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

	public CustInfoCompany getCustInfoCompany() {
		return custInfoCompany;
	}

	public void setCustInfoCompany(CustInfoCompany custInfoCompany) {
		this.custInfoCompany = custInfoCompany;
	}

	public CustInfoPerson getCustInfoPerson() {
		return custInfoPerson;
	}

	public void setCustInfoPerson(CustInfoPerson custInfoPerson) {
		this.custInfoPerson = custInfoPerson;
	}

	public String getDraft() {
		return draft;
	}

	public void setDraft(String draft) {
		this.draft = draft;
	}

	public User getCustManage() {
		return custManage;
	}

	public void setCustManage(User custManage) {
		this.custManage = custManage;
	}
	
	
}
