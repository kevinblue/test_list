package com.tenwa.leasing.entity.contract;

import java.math.BigDecimal;
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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.OrderBy;

import com.reckon.entity.contract.reckon.cash.ContractCashDetail;
import com.reckon.entity.contract.reckon.cash.ContractFinaCashDetail;
import com.reckon.entity.contract.reckon.condition.ContractCondition;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlan;
import com.reckon.entity.contract.reckon.fina.ContractFinaRentPlanBefore;
import com.reckon.entity.contract.reckon.fund.ContractFundRentPlanBefore;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.fund.ContractFundFundCharge;
import com.tenwa.leasing.entity.fund.ContractFundFundPlan;
import com.tenwa.leasing.entity.fund.ContractFundRentInCome;
import com.tenwa.leasing.entity.fund.ContractFundRentPlan;
import com.tenwa.leasing.entity.proj.ProjInfo;



/**
 * 
 * @author 孙传良
 * @date 2013-3-4下午04:31:01
 * @info 合同基本信息
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同信息表")
@Table(name = "CONTRACT_INFO")
public class ContractInfo {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;

	@FieldName(name = "合同编号")
	@Column(name = "CONTRACT_ID", unique = true, length = 50)
	private String contractId;

	@FieldName(name = "业务合同号")
	@Column(name = "CONTRACT_NUMBER", unique = true, length = 50)
	private String contractNumber;
	
	@FieldName(name = "投放编号")
	@Column(name = "CONTRACT_PUT_NUMBER", length = 50)
	private String contractPutNumber;

	@FieldName(name = "所属合同id")
	@Column(name = "SUP_CONTRACT_ID")
	private String supContractId;
	
	@FieldName(name = "合同状态")
	@Column(name = "CONTRACT_STATUS")
	private Integer contractStatus;

	@FieldName(name = "项目")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projId;

	@FieldName(name = "项目名称")
	@Column(name = "PROJECT_NAME", length = 200)
	private String projectName;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目类型")
	@JoinColumn(name = "PROJ_TYPE")
	private DictionaryData projType;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目规模")
	@JoinColumn(name = "PROJ_SCALE")
	private DictionaryData projScale;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目来源")
	@JoinColumn(name = "PROJ_SOURCE")
	private DictionaryData projSource;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "内部行业")
	@JoinColumn(name = "INDUSTRY_TYPE")
	private DictionaryData industryType;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "租赁类型[资产分类]")
	@JoinColumn(name = "LEAS_TYPE")
	private DictionaryData leasType;



	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "租赁形式")
	@JoinColumn(name = "LEAS_FORM")
	private DictionaryData leasForm;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "承租人")
	@JoinColumn(name = "CUST_ID")
	@Index(name="IDX_CI_CUST_ID")
	private CustInfo custId;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目出单部门")
	@JoinColumn(name = "PROJ_DEPT")
	private Department department;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "经办人")
	@JoinColumn(name = "PROJ_REGISTRAR")
	private User projRegistrar;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目经理")
	@JoinColumn(name = "PROJ_MANAGE")
	private User projManage;

	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "项目协办")
	@JoinColumn(name = "PROJ_ASSIST")
	private User projAssist;

	
	@FieldName(name = "预计结束日期")
	@Column(name = "END_DATE", length = 20)
	private String endDate;

	
	@FieldName(name = "起租日")
	@Column(name = "START_DATE_", length = 20)
	private String startDate;

	@FieldName(name = "实际起租日")
	@Column(name = "ACTUAL_START_DATE", length = 20)
	private String actualStartDate;
	
	@FieldName(name = "实际结束日")
	@Column(name = "ACTUAL_END_DATE", length = 20)
	private String actualEndDate;

	public String getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	@FieldName(name = "财务起租日")
	@Column(name = "ACCOUNTING_START_DATE", length = 20)
	private String accountingStartDate;

	

	@FieldName(name = "撤销时间")
	@Column(name = "REPEAL_DATE", length = 20)
	private String repealDate;

	@FieldName(name = "撤销原因分类")
	@JoinColumn(name = "REPEAL_REASON_TYPE")
	@ManyToOne
	private DictionaryData repealReasonType;

	@FieldName(name = "撤销原因")
	@Column(name = "REPEAL_REASON", length = 200)
	private String repealReason;

	@FieldName(name = "合同签约地点")
	@Column(name = "SIGN_PLACE", length = 200)
	private String signPlace; 
	
	@FieldName(name = "签约时间")
	@Column(name = "SIGN_DATE", length = 20)
	private String signDate; 
	
	@FieldName(name = "签约参与人")
	@Column(name = "SIGN_PEOPLE", length = 500)
	private String signPeople; 
	
	@FieldName(name = "其它商务条件")
	@Column(name = "OTHER_CONDITION", length = 2000)
	private String otherCondition;
	
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

	@FieldName(name = "推荐人")
	@Column (name = "referee_people",length = 40) 
	private String refereePeople;
	
	@FieldName(name = "主办")
	@Column (name = "hoster_value",length = 40) 
	private String hosterValue;
	
	@FieldName(name = "协办")
	@Column (name = "assistant_people",length = 40) 
	private String assistantPeople;
	
	@FieldName(name = "第三方")
	@Column (name = "third_Party",length = 40) 
	private String thirdParty;
	
	@FieldName(name = "推荐方分配比例")
	@Column (name = "referee_Proportion",length = 5) 
	private Integer refereeProportion;
	
	@FieldName(name = "主办方分配比例")
	@Column (name = "hoster_Proportion",length = 5) 
	private Integer hosterProportion;
	
	@FieldName(name = "协办分配比例")
	@Column (name = "assistant_Proportion",length = 5) 
	private Integer assistantProportion;
	
	@FieldName(name = "第三方分配比例 ")
	@Column (name = "third_Party_Proportion",length = 5) 
	private Integer thirdPartyProportion;
	
	@FieldName(name="租金余额")
	@Column(name="RENT_OVER",precision=22,scale=2)
	private BigDecimal rentOver;
	
	@FieldName(name="本金余额")
	@Column(name="CORPUS_OVER",precision=22,scale=2)
	private BigDecimal corpusOver;
	
	@FieldName(name="利息余额")
	@Column(name="INTEREST_OVER",precision=22,scale=2)
	private BigDecimal interestOver;
	
	@FieldName(name="逾期租金")
	@Column(name="OVER_RENT",precision=22,scale=2)
	private BigDecimal overRent;
	
	@FieldName(name="逾期本金")
	@Column(name="OVER_CORPUS",precision=22,scale=2)
	private BigDecimal overCorpus;
	
	@FieldName(name="逾期利息")
	@Column(name="OVER_INTEREST",precision=22,scale=2)
	private BigDecimal overInterest;
	
	@FieldName(name="逾期天数")
	@Column(name="OVER_DATE")
	private Integer overDate;
	
	@FieldName(name="合同五级分类类别")
	@JoinColumn(name="FIVE_CLASS")
	@ManyToOne
	private DictionaryData fiveCLASS;

	
	// 租金测算相关
	@FieldName(name = "商务条件")
	@OneToOne(mappedBy = "contractId", fetch = FetchType.LAZY)
	private ContractCondition contractCondition = new ContractCondition();

	@FieldName(name = "合同租金计划")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractFundRentPlan> contractFundRentPlans = new HashSet<ContractFundRentPlan>();

	@FieldName(name = "合同租金计划（原）")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractFundRentPlanBefore> contractFundRentPlansBefore = new HashSet<ContractFundRentPlanBefore>();

	@FieldName(name = "合同会计租金计划")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractFinaRentPlan> contractFinaRentPlans = new HashSet<ContractFinaRentPlan>();

	@FieldName(name = "合同会计租金计划(原)")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractFinaRentPlanBefore> contractFinaRentPlansBefore = new HashSet<ContractFinaRentPlanBefore>();

	@FieldName(name = "合同现金流")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractCashDetail> contractCashDetails = new HashSet<ContractCashDetail>();

	@FieldName(name = "合同会计现金流")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "plan_date asc")
	private Set<ContractFinaCashDetail> contractFinaCashDetails = new HashSet<ContractFinaCashDetail>();

	@FieldName(name = "合同各方信息")
	@OneToOne(mappedBy = "contractId", fetch = FetchType.LAZY)
	private ContractSignatory contractSignatory;
	
	@FieldName(name = "合同各方信息（乙方）")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractSignatorySecond> contractSignatorySeconds=new HashSet<ContractSignatorySecond>();

	@FieldName(name = "合同各方【供应商】")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractSupplierInfo> contractSupplierInfos = new HashSet<ContractSupplierInfo>();
	
	@FieldName(name = "合同开票类型信息")
	@OneToOne(mappedBy = "contractId", fetch = FetchType.LAZY)
	private ContractInvoiceType contractInvoiceType;
	
	@FieldName(name = "合同前提条件")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractPremiseCondition> contractPremiseConditions = new HashSet<ContractPremiseCondition>();

	@FieldName(name = "租赁物件")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractEquip> contractEquips = new HashSet<ContractEquip>();

	 
	@FieldName(name = "合同抵质押物件")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractGuaranteeEquip> contractGuaranteeEquips = new HashSet<ContractGuaranteeEquip>();

	@FieldName(name = "合同担保方式")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractGuaranteeMethod> contractGuaranteeMethods = new HashSet<ContractGuaranteeMethod>();

	 
	@FieldName(name = "资金计划")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractFundFundPlan> fundFundChargePlans = new HashSet<ContractFundFundPlan>();

	@FieldName(name = "资金实收")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	private Set<ContractFundFundCharge> fundFundCharges = new HashSet<ContractFundFundCharge>();

	@FieldName(name = "合同租金实收")
	@OneToMany(mappedBy = "contractId", fetch = FetchType.LAZY)
	@OrderBy(clause = "PLAN_LIST asc")
	private Set<ContractFundRentInCome> contractFundRentIncomes = new HashSet<ContractFundRentInCome>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public DictionaryData getProjType() {
		return projType;
	}

	public void setProjType(DictionaryData projType) {
		this.projType = projType;
	}

	public DictionaryData getProjScale() {
		return projScale;
	}

	public void setProjScale(DictionaryData projScale) {
		this.projScale = projScale;
	}

	public DictionaryData getProjSource() {
		return projSource;
	}

	public void setProjSource(DictionaryData projSource) {
		this.projSource = projSource;
	}

	public DictionaryData getIndustryType() {
		return industryType;
	}

	public void setIndustryType(DictionaryData industryType) {
		this.industryType = industryType;
	}

	public DictionaryData getLeasType() {
		return leasType;
	}

	public void setLeasType(DictionaryData leasType) {
		this.leasType = leasType;
	}

	public DictionaryData getLeasForm() {
		return leasForm;
	}

	public void setLeasForm(DictionaryData leasForm) {
		this.leasForm = leasForm;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getProjRegistrar() {
		return projRegistrar;
	}

	public void setProjRegistrar(User projRegistrar) {
		this.projRegistrar = projRegistrar;
	}

	public User getProjManage() {
		return projManage;
	}

	public void setProjManage(User projManage) {
		this.projManage = projManage;
	}

	public User getProjAssist() {
		return projAssist;
	}

	public void setProjAssist(User projAssist) {
		this.projAssist = projAssist;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}

	public String getAccountingStartDate() {
		return accountingStartDate;
	}

	public void setAccountingStartDate(String accountingStartDate) {
		this.accountingStartDate = accountingStartDate;
	}


	public String getRepealDate() {
		return repealDate;
	}

	public void setRepealDate(String repealDate) {
		this.repealDate = repealDate;
	}

	public DictionaryData getRepealReasonType() {
		return repealReasonType;
	}

	public void setRepealReasonType(DictionaryData repealReasonType) {
		this.repealReasonType = repealReasonType;
	}

	public String getRepealReason() {
		return repealReason;
	}

	public void setRepealReason(String repealReason) {
		this.repealReason = repealReason;
	}

	public String getSignPlace() {
		return signPlace;
	}

	public void setSignPlace(String signPlace) {
		this.signPlace = signPlace;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
	public String getSignPeople() {
		return signPeople;
	}

	public void setSignPeople(String signPeople) {
		this.signPeople = signPeople;
	}

	public String getOtherCondition() {
		return otherCondition;
	}

	public void setOtherCondition(String otherCondition) {
		this.otherCondition = otherCondition;
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

	public ContractCondition getContractCondition() {
		return contractCondition;
	}

	public void setContractCondition(ContractCondition contractCondition) {
		this.contractCondition = contractCondition;
	}

	public Set<ContractFundRentPlan> getContractFundRentPlans() {
		return contractFundRentPlans;
	}

	public void setContractFundRentPlans(
			Set<ContractFundRentPlan> contractFundRentPlans) {
		this.contractFundRentPlans = contractFundRentPlans;
	}

	public Set<ContractFundRentPlanBefore> getContractFundRentPlansBefore() {
		return contractFundRentPlansBefore;
	}

	public void setContractFundRentPlansBefore(
			Set<ContractFundRentPlanBefore> contractFundRentPlansBefore) {
		this.contractFundRentPlansBefore = contractFundRentPlansBefore;
	}

	public Set<ContractFinaRentPlan> getContractFinaRentPlans() {
		return contractFinaRentPlans;
	}

	public void setContractFinaRentPlans(
			Set<ContractFinaRentPlan> contractFinaRentPlans) {
		this.contractFinaRentPlans = contractFinaRentPlans;
	}

	public Set<ContractFinaRentPlanBefore> getContractFinaRentPlansBefore() {
		return contractFinaRentPlansBefore;
	}

	public void setContractFinaRentPlansBefore(
			Set<ContractFinaRentPlanBefore> contractFinaRentPlansBefore) {
		this.contractFinaRentPlansBefore = contractFinaRentPlansBefore;
	}

	public Set<ContractCashDetail> getContractCashDetails() {
		return contractCashDetails;
	}

	public void setContractCashDetails(Set<ContractCashDetail> contractCashDetails) {
		this.contractCashDetails = contractCashDetails;
	}

	public Set<ContractFinaCashDetail> getContractFinaCashDetails() {
		return contractFinaCashDetails;
	}

	public void setContractFinaCashDetails(
			Set<ContractFinaCashDetail> contractFinaCashDetails) {
		this.contractFinaCashDetails = contractFinaCashDetails;
	}

	public ContractSignatory getContractSignatory() {
		return contractSignatory;
	}

	public void setContractSignatory(ContractSignatory contractSignatory) {
		this.contractSignatory = contractSignatory;
	}
	
	public Set<ContractSupplierInfo> getContractSupplierInfos() {
		return contractSupplierInfos;
	}

	public void setContractSupplierInfos(Set<ContractSupplierInfo> contractSupplierInfos) {
		this.contractSupplierInfos = contractSupplierInfos;
	}
	
	public ContractInvoiceType getContractInvoiceType() {
		return contractInvoiceType;
	}

	public void setContractInvoiceType(ContractInvoiceType contractInvoiceType) {
		this.contractInvoiceType = contractInvoiceType;
	}

	public Set<ContractPremiseCondition> getContractPremiseConditions() {
		return contractPremiseConditions;
	}

	public void setContractPremiseConditions(
			Set<ContractPremiseCondition> contractPremiseConditions) {
		this.contractPremiseConditions = contractPremiseConditions;
	}

	public Set<ContractEquip> getContractEquips() {
		return contractEquips;
	}

	public void setContractEquips(Set<ContractEquip> contractEquips) {
		this.contractEquips = contractEquips;
	}

	public Set<ContractGuaranteeEquip> getContractGuaranteeEquips() {
		return contractGuaranteeEquips;
	}

	public void setContractGuaranteeEquips(
			Set<ContractGuaranteeEquip> contractGuaranteeEquips) {
		this.contractGuaranteeEquips = contractGuaranteeEquips;
	}

	public Set<ContractGuaranteeMethod> getContractGuaranteeMethods() {
		return contractGuaranteeMethods;
	}

	public void setContractGuaranteeMethods(
			Set<ContractGuaranteeMethod> contractGuaranteeMethods) {
		this.contractGuaranteeMethods = contractGuaranteeMethods;
	}

	public Set<ContractFundFundPlan> getFundFundChargePlans() {
		return fundFundChargePlans;
	}

	public void setFundFundChargePlans(Set<ContractFundFundPlan> fundFundChargePlans) {
		this.fundFundChargePlans = fundFundChargePlans;
	}

	public Set<ContractFundFundCharge> getFundFundCharges() {
		return fundFundCharges;
	}

	public void setFundFundCharges(Set<ContractFundFundCharge> fundFundCharges) {
		this.fundFundCharges = fundFundCharges;
	}

	public Set<ContractFundRentInCome> getContractFundRentIncomes() {
		return contractFundRentIncomes;
	}

	public void setContractFundRentIncomes(
			Set<ContractFundRentInCome> contractFundRentIncomes) {
		this.contractFundRentIncomes = contractFundRentIncomes;
	}

	public String getContractPutNumber() {
		return contractPutNumber;
	}

	public void setContractPutNumber(String contractPutNumber) {
		this.contractPutNumber = contractPutNumber;
	}

	public String getSupContractId() {
		return supContractId;
	}

	public void setSupContractId(String supContractId) {
		this.supContractId = supContractId;
	}

	public String getRefereePeople() {
		return refereePeople;
	}

	public void setRefereePeople(String refereePeople) {
		this.refereePeople = refereePeople;
	}

	public String getHosterValue() {
		return hosterValue;
	}

	public void setHosterValue(String hosterValue) {
		this.hosterValue = hosterValue;
	}

	public String getAssistantPeople() {
		return assistantPeople;
	}

	public void setAssistantPeople(String assistantPeople) {
		this.assistantPeople = assistantPeople;
	}

	public String getThirdParty() {
		return thirdParty;
	}

	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}

	public Integer getRefereeProportion() {
		return refereeProportion;
	}

	public void setRefereeProportion(Integer refereeProportion) {
		this.refereeProportion = refereeProportion;
	}

	public Integer getHosterProportion() {
		return hosterProportion;
	}

	public void setHosterProportion(Integer hosterProportion) {
		this.hosterProportion = hosterProportion;
	}

	public Integer getAssistantProportion() {
		return assistantProportion;
	}

	public void setAssistantProportion(Integer assistantProportion) {
		this.assistantProportion = assistantProportion;
	}

	public Integer getThirdPartyProportion() {
		return thirdPartyProportion;
	}

	public void setThirdPartyProportion(Integer thirdPartyProportion) {
		this.thirdPartyProportion = thirdPartyProportion;
	}

	public Set<ContractSignatorySecond> getContractSignatorySeconds() {
		return contractSignatorySeconds;
	}

	public void setContractSignatorySeconds(
			Set<ContractSignatorySecond> contractSignatorySeconds) {
		this.contractSignatorySeconds = contractSignatorySeconds;
	}

	public BigDecimal getRentOver() {
		return rentOver;
	}

	public void setRentOver(BigDecimal rentOver) {
		this.rentOver = rentOver;
	}

	public BigDecimal getCorpusOver() {
		return corpusOver;
	}

	public void setCorpusOver(BigDecimal corpusOver) {
		this.corpusOver = corpusOver;
	}

	public BigDecimal getInterestOver() {
		return interestOver;
	}

	public void setInterestOver(BigDecimal interestOver) {
		this.interestOver = interestOver;
	}

	public BigDecimal getOverRent() {
		return overRent;
	}

	public void setOverRent(BigDecimal overRent) {
		this.overRent = overRent;
	}

	public BigDecimal getOverCorpus() {
		return overCorpus;
	}

	public void setOverCorpus(BigDecimal overCorpus) {
		this.overCorpus = overCorpus;
	}

	public BigDecimal getOverInterest() {
		return overInterest;
	}

	public void setOverInterest(BigDecimal overInterest) {
		this.overInterest = overInterest;
	}

	public Integer getOverDate() {
		return overDate;
	}

	public void setOverDate(Integer overDate) {
		this.overDate = overDate;
	}

	public DictionaryData getFiveCLASS() {
		return fiveCLASS;
	}

	public void setFiveCLASS(DictionaryData fiveCLASS) {
		this.fiveCLASS = fiveCLASS;
	}


	
}
