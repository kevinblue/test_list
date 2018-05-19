package com.tenwa.leasing.entity.proj;

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

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.business.entity.base.District;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustGrade;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.cust.CustInfoCompany;

@Entity
@FieldName(name = "项目开发一览")
@Table(name = "PROJ_DEVELOPE_INFO")
public class ProjDevelopInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	
	
	@Column(name = "PROJ_NAME", length=32)
	@FieldName(name="项目名称")
	private String projName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="客户名称")
	private CustInfo custid;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGIST_CODE_ID")
	@FieldName(name="企业工商注册编码")
	private CustInfoCompany registCodeId;
	
	@ManyToOne
	@FieldName(name="项目类型")//租赁类型
	@JoinColumn(name="LEAS_FORM")
	private DictionaryData leasform;
	
	@ManyToOne
	@FieldName(name="省份[B]")
	@JoinColumn(name="PROVINCE_")
	private District province;
	
	@ManyToOne
	@FieldName(name="地级市[B]")
	@JoinColumn(name="CITY_")
	private District city;
	
	@ManyToOne
	@FieldName(name="风电场地形特征")
	@JoinColumn(name="WIND_TOPOGRAPHY")
	private DictionaryData windTopography;
	
	@ManyToOne
	@FieldName(name="项目阶段")
	@JoinColumn(name = "PROJECT_PHASE")	
	private DictionaryData projectPhase;
	
	@Column(name = "FAN_TYPE", length=32)
	@FieldName(name="机组选型")
	private String fanType;
	
	@Column(name = "FAN_SUM", length=32)
	@FieldName(name="机组数量(台)")
	private String fanSum;
	
	@Column(name = "PROJ_INSTALLED_CAPACITY", length=100)
	@FieldName(name="项目容量(万千瓦)")
	private String projInstalledCapacity;
	
	@Column(name = "INTERNET_PRICE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="上网电价（元/度）")
	private BigDecimal internetPrice;
	
	@Column(name = "BENCHMARK_PRICE", precision = 22,scale = 4)
	@FieldName(name="标杆电价（元/度）")
	private BigDecimal benchmarkPrice;
	
	@Column(name = "AVE_WIND_SPEED", length=32)
	@FieldName(name="平均风速（米/秒）")
	private String aveWindSpeed;
	
	@Column(name = "EQU_HOURS", length=32)
	@FieldName(name="年等效满负荷小时数（小时）")
	private String equHours;
	
	@Column(name = "THEORY_PRODUCTION", length=32)
	@FieldName(name="年理论发电量（万度）")
	private String theoryProduction;
	
	@Column(name = "InternetPower", length=32)
	@FieldName(name="年上网电量（万度）")
	private String internetPower;
	
	@Column(name = "PROJ_INVESTMENT", length=32)
	@FieldName(name="项目静态投资（万元）")
	private String projInvestment;
	
	@Column(name = "KW_COST", length=32)
	@FieldName(name="千瓦造价（元）")
	private String kwCost;
	
	@Column(name = "PROJ_CAPITAL", length=32)
	@FieldName(name="项目资本金（万元）")
	private String projCapital;
	
	@Column(name = "RZZL_AMOUNT", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="融资总额")
	private BigDecimal rzzlAmount;
	    
	@Column(name = "PRODUCTION_TIME", length=32)
	@FieldName(name="（预计）投产时间")
	private String productionTime;
	//12
	@ManyToOne
	@FieldName(name="项目来源")
	@JoinColumn(name="PROJ_SOURCE")
	private DictionaryData projSource;
	
	@Column(name = "PROJ_APP_STATUS", length=100)
	@FieldName(name="项目审批情况")
	private String projAppStatus;
	
	//12
	@Column(name = "NOTE", length = 4000)
	@FieldName(name="项目补充信息")
	private String note;
	
	//12
	@Column(name = "LINK_MAN", length=32)
	@FieldName(name="项目联系人")
	private String linkMan;
	//12
	@Column(name = "CONTACT_WAY", length=32)
	@FieldName(name="联系方式")
	private String contactWay;
	//12
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TX_PEOPLE")
	@FieldName(name="天信对接人")
	private User txPeople;
	//12
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGIST_DEPT")
	@FieldName(name="登记部门")
	private Department registDept;
	
	//供应链项目
	//2
	@ManyToOne
	@FieldName(name="客户质量信用等级")
	@JoinColumn(name="CUST_CREDIT_RATING")
	private DictionaryData  custCreditRating;
	
	//2
	@ManyToOne
	@FieldName(name="天信客户信用等级")
	@JoinColumn(name="TX_CREDIT_RATING")
	private CustGrade  txCreditRating;
	
	
	//2
	@ManyToOne
	@FieldName(name="与金风的年订单金额")
	@JoinColumn(name="ANNUAL_ORDER_AMOUNT")
	private DictionaryData annualOrderAmount;
	
	//2
	@Column(name = "PURCHASES_ACCOUNTED", length=32)
	@FieldName(name="金风采购量占比")
	private String purchasesAccounted;
	
	//2
	@ManyToOne
	@FieldName(name="供应商类别")
	@JoinColumn(name="SUP_CATEGORY")
	private DictionaryData  supCategory;

	@Column(name = "PROJ_SCHEDULE", length=32)
	@FieldName(name="项目进度")
	private String projSchedule;
	
	@Column(name = "INDIRECT_SUP", length=32)
	@FieldName(name="间接供应商")
	private String indirectsup;
	
	@Column(name = "PROJ_SITUATION", length=4000)
	@FieldName(name="项目基本情况")
	private String projSituation;	
	
	//项目开发类型 1：风电，2：供应链，3：其他
	@Column(name = "type", length=32)
	@FieldName(name="项目开发类型")
	private Integer type;
	
	//项目类型  集中式,分散式
	@ManyToOne
	@FieldName(name="项目分布方式")
	@JoinColumn(name="PROJ_KIND")
	private DictionaryData projKind;
	
	@Column(name = "EXPECT_SCALE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="预计限电比例")
	private BigDecimal expectScale;
	
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public CustInfo getCustid() {
		return custid;
	}

	public void setCustid(CustInfo custid) {
		this.custid = custid;
	}

	public CustInfoCompany getRegistCodeId() {
		return registCodeId;
	}

	public void setRegistCodeId(CustInfoCompany registCodeId) {
		this.registCodeId = registCodeId;
	}
	

	public DictionaryData getLeasform() {
		return leasform;
	}

	public void setLeasform(DictionaryData leasform) {
		this.leasform = leasform;
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

	public DictionaryData getWindTopography() {
		return windTopography;
	}

	public void setWindTopography(DictionaryData windTopography) {
		this.windTopography = windTopography;
	}

	

	public DictionaryData getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(DictionaryData projectPhase) {
		this.projectPhase = projectPhase;
	}

	public String getFanType() {
		return fanType;
	}

	public void setFanType(String fanType) {
		this.fanType = fanType;
	}

	public String getFanSum() {
		return fanSum;
	}

	public void setFanSum(String fanSum) {
		this.fanSum = fanSum;
	}

	public String getProjInstalledCapacity() {
		return projInstalledCapacity;
	}

	public void setProjInstalledCapacity(String projInstalledCapacity) {
		this.projInstalledCapacity = projInstalledCapacity;
	}

	public BigDecimal getInternetPrice() {
		return internetPrice;
	}

	public void setInternetPrice(BigDecimal internetPrice) {
		this.internetPrice = internetPrice;
	}

	public String getAveWindSpeed() {
		return aveWindSpeed;
	}

	public void setAveWindSpeed(String aveWindSpeed) {
		this.aveWindSpeed = aveWindSpeed;
	}

	public String getEquHours() {
		return equHours;
	}

	public void setEquHours(String equHours) {
		this.equHours = equHours;
	}

	public String getTheoryProduction() {
		return theoryProduction;
	}

	public void setTheoryProduction(String theoryProduction) {
		this.theoryProduction = theoryProduction;
	}

	public String getInternetPower() {
		return internetPower;
	}

	public void setInternetPower(String internetPower) {
		this.internetPower = internetPower;
	}

	public String getProjInvestment() {
		return projInvestment;
	}

	public void setProjInvestment(String projInvestment) {
		this.projInvestment = projInvestment;
	}

	public String getKwCost() {
		return kwCost;
	}

	public void setKwCost(String kwCost) {
		this.kwCost = kwCost;
	}

	public String getProjCapital() {
		return projCapital;
	}

	public void setProjCapital(String projCapital) {
		this.projCapital = projCapital;
	}

	public BigDecimal getRzzlAmount() {
		return rzzlAmount;
	}

	public void setRzzlAmount(BigDecimal rzzlAmount) {
		this.rzzlAmount = rzzlAmount;
	}

	public String getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}

	public DictionaryData getProjSource() {
		return projSource;
	}

	public void setProjSource(DictionaryData projSource) {
		this.projSource = projSource;
	}

	public String getProjAppStatus() {
		return projAppStatus;
	}

	public void setProjAppStatus(String projAppStatus) {
		this.projAppStatus = projAppStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public User getTxPeople() {
		return txPeople;
	}

	public void setTxPeople(User txPeople) {
		this.txPeople = txPeople;
	}

	public Department getRegistDept() {
		return registDept;
	}

	public void setRegistDept(Department registDept) {
		this.registDept = registDept;
	}

	public DictionaryData getCustCreditRating() {
		return custCreditRating;
	}

	public void setCustCreditRating(DictionaryData custCreditRating) {
		this.custCreditRating = custCreditRating;
	}

	public DictionaryData getAnnualOrderAmount() {
		return annualOrderAmount;
	}

	public void setAnnualOrderAmount(DictionaryData annualOrderAmount) {
		this.annualOrderAmount = annualOrderAmount;
	}

	public String getPurchasesAccounted() {
		return purchasesAccounted;
	}

	public void setPurchasesAccounted(String purchasesAccounted) {
		this.purchasesAccounted = purchasesAccounted;
	}

	public DictionaryData getSupCategory() {
		return supCategory;
	}

	public void setSupCategory(DictionaryData supCategory) {
		this.supCategory = supCategory;
	}

	public String getProjSchedule() {
		return projSchedule;
	}

	public void setProjSchedule(String projSchedule) {
		this.projSchedule = projSchedule;
	}

	public String getProjSituation() {
		return projSituation;
	}

	public void setProjSituation(String projSituation) {
		this.projSituation = projSituation;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getBenchmarkPrice() {
		return benchmarkPrice;
	}

	public void setBenchmarkPrice(BigDecimal benchmarkPrice) {
		this.benchmarkPrice = benchmarkPrice;
	}

	public CustGrade getTxCreditRating() {
		return txCreditRating;
	}

	public void setTxCreditRating(CustGrade txCreditRating) {
		this.txCreditRating = txCreditRating;
	}

	public DictionaryData getProjKind() {
		return projKind;
	}

	public void setProjKind(DictionaryData projKind) {
		this.projKind = projKind;
	}

	public BigDecimal getExpectScale() {
		return expectScale;
	}

	public void setExpectScale(BigDecimal expectScale) {
		this.expectScale = expectScale;
	}

	public String getIndirectsup() {
		return indirectsup;
	}

	public void setIndirectsup(String indirectsup) {
		this.indirectsup = indirectsup;
	}

	
}
