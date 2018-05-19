package com.tenwa.leasing.entity.cust;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
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
 * @date 2013-7-28下午03:24:06
 * @info 法人客户信息
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "法人客户信息")
@Table(name="CUST_INFO_COMPANY")
public class CustInfoCompany {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;

	@FieldName(name="客户ID")
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name="CUST_ID")
	
	private CustInfo custId;
	
	@Column(name="ORG_CODE", length=50)
	@FieldName(name="组织结构代码")
	private String orgCode;	
	
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
	
	@ManyToOne
	@FieldName(name="客户内部行业")
	@JoinColumn(name="CUST_KIND")
	private DictionaryData custKind;
	
	@ManyToOne
	@FieldName(name="子行业")
	@JoinColumn(name="SUB_CUST_KIND")
	private DictionaryData subCustKind;
	
	@ManyToOne
	@FieldName(name="医院类别")
	@JoinColumn(name="hospital_type")
	private DictionaryData hospitalType;
	
	@ManyToOne
	@FieldName(name="行政级别")
	@JoinColumn(name="admin_level")
	private DictionaryData adminLevel;
	
	@ManyToOne
	@FieldName(name="客户来源")
	@JoinColumn(name="CUST_SOURCE")
	private DictionaryData custSource;
	
	@ManyToOne
	@FieldName(name="与集团关系")
	@JoinColumn(name="GROUP_RELATIONSHIP")
	private DictionaryData groupRelationship;
	
	@ManyToOne
	@FieldName(name="客户规模")
	@JoinColumn(name="CUST_SCALE")
	private DictionaryData custScale;
	
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
	
	@ManyToOne
	@FieldName(name="企业性质[D]")
	@JoinColumn(name="OWNER_SHIP")
	private DictionaryData ownerShip;
	
	
	@ManyToOne
	@FieldName(name="纳税人类别")
	@JoinColumn(name="TAX_REG_Type")
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
	
	
	@FieldName(name = "成立时间")
	@Column(name="ESTABLISH_DATE", length=50)
	private String establishdate;
	
	
	@FieldName(name = "开票地址")
	@Column(name="INVOICE_ADD", length=100)
	private String invoiceAdd;
	
	@FieldName(name = "开票电话")
	@Column(name="INVOICE_PHONE", length=100)
	private String invoicePhone;
	
	@FieldName(name = "法人代表")
	@Column(name="PERSON_REP", length=100)
	private String personRep;
	
	@FieldName(name = "主营业务收入(万元)")
	@Column(name="MAIN_REVENUE", length=100)
	private String mainRevenue;
	
	@ManyToOne	
	@FieldName(name="证件类型")
	@JoinColumn(name="DOCUMENT_TYPE")
	private DictionaryData documentType;
	
	@FieldName(name = "法人身份证号")
	@Column(name="PERSON_IDCARD", length=18)
	private String personIdcard;
	
	@FieldName(name = "手机")
	@Column(name="MOBLIE",length=50)
	private String moblie;
	
	@FieldName(name = "电话")
	@Column(name="PHONE",length=50)
	private String phone;
	
	@FieldName(name = "传真")
	@Column(name="FAX",length=50)
	private String fax;
	
	@FieldName(name = "邮编")
	@Column(name="POST_CODE",length=50)
	private String postCode;
	
	@FieldName(name = "客户归属人")
	@Column(name="BELONGING_PEOPLE",length=4000)
	private String belongingPeople;
	
	@FieldName(name = "归属时间")
	@Column(name="BELONGING_DATE", length=20)
	private String belongingDate;
	
	@FieldName(name = "客户实际负责人")
	@Column(name="cust_actual_people",length=50)
	private String custActualPeople;
	
	@FieldName(name = "员工人数")
	@Column(name="emp_number",length=50)
	private String empNumber;
	
	@FieldName(name = "是否有贷款卡")
	@Column(name="credit_card_yesorno",length=2)
	private String creditCardYesorno;
	
	@FieldName(name = "贷款卡号")
	@Column(name="credit_card",length=50)
	private String creditCard;
	
	@FieldName(name = "邮寄地址")
	@Column(name="MAILA_DDRESS",length=500)
	private String mailAddress;
	
	@FieldName(name = "公司地址")
	@Column(name="COMPANY_ADDRESS",length=1000)
	private String companyAddress;
	
	@FieldName(name = "公司网址")
	@Column(name="COMPANY_URL",length=1000)
	private String companyURL;

	@FieldName(name = "注册地址")
	@Column(name="REG_ADDRESS",length=1000)
	private String regAddress;
	
	@FieldName(name = "营业执照号")
	@Column(name="BUS_LICENSE",length=50)
	private String busLicense;
	
	@FieldName(name = "注册资本")
	@Column(name="REG_CAPITAL",length=200)
	private String regCapital;
	
	@FieldName(name = "经营方式")
	@Column(name="OPERATE",length=1000)
	private String operate;
	
	@FieldName(name = "经营方式(主营)")
	@Column(name="OPERATE_MASTER",length=1000)
	private String operateMaster;
	
	@FieldName(name = "经营方式(兼营)")
	@Column(name="operate_Minor",length=1000)
	private String operateMinor;
	
	@FieldName(name = "客户概况")
	@Column(name="CUST_PROBABLY",length=1000)
	private String custProbably;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEPARTMENT")
	@FieldName(name="登记部门")
	private Department department;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
	private String modifyDate;
	
	
	
	public String getEstablishdate() {
		return establishdate;
	}

	public void setEstablishdate(String establishdate) {
		this.establishdate = establishdate;
	}

	public DictionaryData getCustScale() {
		return custScale;
	}

	public void setCustScale(DictionaryData custScale) {
		this.custScale = custScale;
	}

	public String getMainRevenue() {
		return mainRevenue;
	}

	public void setMainRevenue(String mainRevenue) {
		this.mainRevenue = mainRevenue;
	}

	public DictionaryData getCustKind() {
		return custKind;
	}

	public void setCustKind(DictionaryData custKind) {
		this.custKind = custKind;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public DictionaryData getOwnerShip() {
		return ownerShip;
	}

	public void setOwnerShip(DictionaryData ownerShip) {
		this.ownerShip = ownerShip;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
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

	public String getPersonRep() {
		return personRep;
	}

	public void setPersonRep(String personRep) {
		this.personRep = personRep;
	}

	public String getPersonIdcard() {
		return personIdcard;
	}

	public void setPersonIdcard(String personIdcard) {
		this.personIdcard = personIdcard;
	}

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyURL() {
		return companyURL;
	}

	public void setCompanyURL(String companyURL) {
		this.companyURL = companyURL;
	}

	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getBusLicense() {
		return busLicense;
	}

	public void setBusLicense(String busLicense) {
		this.busLicense = busLicense;
	}


	public String getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(String regCapital) {
		this.regCapital = regCapital;
	}

	public String getOperateMaster() {
		return operateMaster;
	}

	public void setOperateMaster(String operateMaster) {
		this.operateMaster = operateMaster;
	}

	public String getOperateMinor() {
		return operateMinor;
	}

	public void setOperateMinor(String operateMinor) {
		this.operateMinor = operateMinor;
	}

	public String getCustProbably() {
		return custProbably;
	}

	public void setCustProbably(String custProbably) {
		this.custProbably = custProbably;
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

	public DictionaryData getCustSource() {
		return custSource;
	}

	public void setCustSource(DictionaryData custSource) {
		this.custSource = custSource;
	}



	public String getBelongingPeople() {
		return belongingPeople;
	}

	public void setBelongingPeople(String belongingPeople) {
		this.belongingPeople = belongingPeople;
	}

	public String getCustActualPeople() {
		return custActualPeople;
	}

	public void setCustActualPeople(String custActualPeople) {
		this.custActualPeople = custActualPeople;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getCreditCardYesorno() {
		return creditCardYesorno;
	}

	public void setCreditCardYesorno(String creditCardYesorno) {
		this.creditCardYesorno = creditCardYesorno;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
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

	public String getBelongingDate() {
		return belongingDate;
	}

	public void setBelongingDate(String belongingDate) {
		this.belongingDate = belongingDate;
	}

	public DictionaryData getSubCustKind() {
		return subCustKind;
	}

	public void setSubCustKind(DictionaryData subCustKind) {
		this.subCustKind = subCustKind;
	}

	public DictionaryData getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(DictionaryData hospitalType) {
		this.hospitalType = hospitalType;
	}

	public DictionaryData getAdminLevel() {
		return adminLevel;
	}

	public void setAdminLevel(DictionaryData adminLevel) {
		this.adminLevel = adminLevel;
	}

	public DictionaryData getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DictionaryData documentType) {
		this.documentType = documentType;
	}

	public DictionaryData getGroupRelationship() {
		return groupRelationship;
	}

	public void setGroupRelationship(DictionaryData groupRelationship) {
		this.groupRelationship = groupRelationship;
	}

}
