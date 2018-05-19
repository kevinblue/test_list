	package com.tenwa.leasing.entity.cust;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.Industry;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.District;
import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author tpf
 * @date 2013-7-28下午03:24:31
 * @info 客户信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "客户信息")
@Table(name="CUST_INFO_PERSON")
public class CustInfoPerson {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="客户ID")
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="CUST_ID")
	private CustInfo custId;
	
	@ManyToOne	
	@FieldName(name="证件类型")
	@JoinColumn(name="DOCUMENT_TYPE")
	private DictionaryData documentType;
	
	@FieldName(name = "证件号码")
	@Column(name="ID_CARD_NO", length=18)
	private String idCardNo;

	@FieldName(name = "性别[S]")
	@Column(name="SEX_", length=2)
	private String sex;
	
	@ManyToOne
	@FieldName(name="国家[B]")
	@JoinColumn(name="COUNTRY_")
	private District country;
	
	@ManyToOne
	@FieldName(name="省份[B]")
	@JoinColumn(name="PROVINCE_")
	private District province;
	
	@ManyToOne
	@FieldName(name="城市[B]")
	@JoinColumn(name="CITY_")
	private District city;
	
	@Column(name="COUNTY_", length=200)
	@FieldName(name = "区县[B]")
	private String county;
	
	@FieldName(name = "出生日期")
	@Column(name="BRITH_DATE", length=50)
	private String brithDate;
	
	@ManyToOne
	@FieldName(name="客户来源")
	@JoinColumn(name="CUST_SOURCE")
	private DictionaryData custSource;
	
	@FieldName(name="客户规模")
	@Column(name="CUST_SCALE")
	private String custScale;
	
	@ManyToOne
	@FieldName(name = "客户内部行业")
	@JoinColumn(name="CUST_KIND")
	private DictionaryData custKind;
	
	@ManyToOne
	@FieldName(name="子行业")
	@JoinColumn(name="SUB_CUST_KIND")
	private DictionaryData subCustKind;
	
	@FieldName(name = "户口所在地")
	@Column(name="DOMICILE_PLACE", length=100)
	private String domicilePlace;
	
	@ManyToOne
	@FieldName(name="客户所属行业门类[B]")
	@JoinColumn(name="INDUSTRY_")
	private Industry industry;
	
	@ManyToOne
	@FieldName(name="客户所属行业大类[B]")
	@JoinColumn(name="INDUSTRY_LEVEL_BIG")
	private Industry industryLevelBig;
	
	@ManyToOne
	@FieldName(name="客户所属行业中类[B]")
	@JoinColumn(name="INDUSTRY_LEVEL_MIDDLE")
	private Industry industryLevelMiddle;
	
	@ManyToOne
	@FieldName(name="客户所属行业小类[B]")
	@JoinColumn(name="INDUSTRY_LEVEL_SMALL")
	private Industry industryLevelSmall;
	
	@Column(name="MAIL_ADD", length=200)
	@FieldName(name = "邮寄地址")
	private String mailAdd;
	
	@FieldName(name="常住地址")
	@Column(name="OFTEN_ADDR", length=200)
	private String oftenAddr;
	
	
	@FieldName(name = "邮编")
	@Column(name="POST_CODE", length=50)
	private String postCode;
	
	@FieldName(name = "EMAIL")
	@Column(name="EMAIL_", length=50)
	private String email;
	
	@FieldName(name = "手机号码")
	@Column(name="MOBILE_NUMBER", length=200)
	private String mobileNumber;
	
	@FieldName(name = "电话号码")
	@Column(name="PHONE_", length=200)
	private String phone;
	
	@ManyToOne
	@FieldName(name = "婚姻状态[D]")
	@JoinColumn(name="MARITAL_STATUS")
	private DictionaryData maritalStatus;
	
	@ManyToOne
	@FieldName(name = "学历[D]")
	@JoinColumn(name="SCHOOL_")
	private DictionaryData school;
	
	
	@FieldName(name = "工作单位")
	@Column(name="UNIT_NAME", length=2000)
	private String unitName;
	
	@ManyToOne
	@FieldName(name = "职务[D]")
	@JoinColumn(name="UNIT_POSITION")
	private DictionaryData unitPosition;
	
	@FieldName(name = "传真")
	@Column(name="FAX_NUMBER", length=50)
	private String faxNumber;
	
	@FieldName(name = "客户概况")
	@Column(name="CEIMEMO",length=2000)
	private String ceimemo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT")
	@FieldName(name="登记部门")
	private Department department;
	
	
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
	@Column(name="MODIFY_DATE")
	private String modifyDate;
	

	@FieldName(name = "客户归属人")
	@Column(name="BELONGING_PEOPLE",length=4000)
	private String belongingPeople;
	
	@FieldName(name = "归属时间")
	@Column(name="BELONGING_DATE", length=20)
	private String belongingDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBrithDate() {
		return brithDate;
	}

	public void setBrithDate(String brithDate) {
		this.brithDate = brithDate;
	}

	public DictionaryData getCustKind() {
		return custKind;
	}

	public void setCustKind(DictionaryData custKind) {
		this.custKind = custKind;
	}

	public String getDomicilePlace() {
		return domicilePlace;
	}

	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}

	public String getMailAdd() {
		return mailAdd;
	}

	public void setMailAdd(String mailAdd) {
		this.mailAdd = mailAdd;
	}

	public String getOftenAddr() {
		return oftenAddr;
	}

	public void setOftenAddr(String oftenAddr) {
		this.oftenAddr = oftenAddr;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public DictionaryData getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(DictionaryData maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public DictionaryData getSchool() {
		return school;
	}

	public void setSchool(DictionaryData school) {
		this.school = school;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public DictionaryData getUnitPosition() {
		return unitPosition;
	}

	public void setUnitPosition(DictionaryData unitPosition) {
		this.unitPosition = unitPosition;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getCeimemo() {
		return ceimemo;
	}

	public void setCeimemo(String ceimemo) {
		this.ceimemo = ceimemo;
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

	public District getCountry() {
		return country;
	}

	public void setCountry(District country) {
		this.country = country;
	}

	public District getProvince() {
		return province;
	}

	public void setProvince(District province) {
		this.province = province;
	}

	public District getCity() {
		return city;
	}

	public void setCity(District city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Industry getIndustryLevelBig() {
		return industryLevelBig;
	}

	public void setIndustryLevelBig(Industry industryLevelBig) {
		this.industryLevelBig = industryLevelBig;
	}

	public Industry getIndustryLevelMiddle() {
		return industryLevelMiddle;
	}

	public void setIndustryLevelMiddle(Industry industryLevelMiddle) {
		this.industryLevelMiddle = industryLevelMiddle;
	}

	public Industry getIndustryLevelSmall() {
		return industryLevelSmall;
	}

	public void setIndustryLevelSmall(Industry industryLevelSmall) {
		this.industryLevelSmall = industryLevelSmall;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DictionaryData getCustSource() {
		return custSource;
	}

	public void setCustSource(DictionaryData custSource) {
		this.custSource = custSource;
	}

	public String getCustScale() {
		return custScale;
	}

	public void setCustScale(String custScale) {
		this.custScale = custScale;
	}



	public String getBelongingPeople() {
		return belongingPeople;
	}

	public void setBelongingPeople(String belongingPeople) {
		this.belongingPeople = belongingPeople;
	}

	public String getBelongingDate() {
		return belongingDate;
	}

	public void setBelongingDate(String belongingDate) {
		this.belongingDate = belongingDate;
	}

	public DictionaryData getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DictionaryData documentType) {
		this.documentType = documentType;
	}

	public DictionaryData getSubCustKind() {
		return subCustKind;
	}

	public void setSubCustKind(DictionaryData subCustKind) {
		this.subCustKind = subCustKind;
	}
	
}
