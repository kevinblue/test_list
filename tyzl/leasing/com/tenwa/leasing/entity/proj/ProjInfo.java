package com.tenwa.leasing.entity.proj;

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

import com.reckon.entity.proj.ProjCashDetail;
import com.reckon.entity.proj.ProjCondition;
import com.reckon.entity.proj.ProjFinaCashDetail;
import com.reckon.entity.proj.ProjFinaRentPlan;
import com.reckon.entity.proj.ProjFundFundPlan;
import com.reckon.entity.proj.ProjFundRentPlan;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.proj.invoice.ProjInvoiceType;

/**
 * ProjInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "1.1项目基本信息")
@Table(name = "PROJ_INFO")
public class ProjInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_DEPT")
	@FieldName(name="出单部门")
	private Department department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_SCALE")
	@FieldName(name="项目规模")
	private DictionaryData projScale;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDUSTRY_TYPE")
	@FieldName(name="内部行业")
	private DictionaryData industryType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_REGISTRAR")
	@FieldName(name="经办人(录入人员)")
	private User projRegistrar;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_MANAGE")
	@FieldName(name="项目经理")
	private User projManage;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ASSIST")
	@FieldName(name="项目协办")
	private User projAssist;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_TYPE")
	@FieldName(name="项目类型")
	private DictionaryData projType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_SOURCE")
	@FieldName(name="项目来源")
	private DictionaryData projSource;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUST_ID")
	@FieldName(name="承租人")
	private CustInfo custInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAS_FORM")
	@FieldName(name="租赁形式")
	private DictionaryData leasForm;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LEAS_TYPE")
	@FieldName(name="固定资产")
	private DictionaryData leasType;

	@Column(name = "PROJ_ID", length = 100)
	@FieldName(name="项目编号")
	private String projId;

	@Column(name = "PROJ_DATE", length = 40)
	@FieldName(name="项目日期")
	private String projDate;

	@Column(name = "PROJECT_NAME", length = 510)
	@FieldName(name="项目名称")
	private String projectName;


	@Column(name = "PROJ_STATUS", precision = 10, scale = 0)
	@FieldName(name="项目状态")
	private Integer projStatus;

	@Column(name = "REPEAL_DATE", length = 40)
	@FieldName(name="项目撤销时间")
	private String repealDate;
  
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REPEAL_REASON_TYPE")
	@FieldName(name="撤销原因分类")
	private DictionaryData RepealReasonType;
	
	@Column(name = "REPEAL_REASON", length = 510)
	@FieldName(name="撤销原因")
	private String repealReason;
	
	@Column(name = "CHANGE_DATE", length = 40)
	@FieldName(name="项目变更时间")
	private String changeDate;
	
	@Column(name = "CHANGE_MEMO", length = 2000)
	@FieldName(name="变更原因")
	private String changeMemo;
	
	@Column(name = "APPROVE_CONCLUSION", length = 510)
	@FieldName(name="信审结论")
	private String approveConclusion;
	
	@Column(name = "PRESIDENT_OPINIONS", length = 2000)
	@FieldName(name="总载意见")
	private String presidentOpinions;

	@Column(name = "APPROVE_DATE", length = 40)
    @FieldName(name="信审通过日期")
	private String approveDate;
	
	@FieldName(name="业务合同编号")
	@Column(name = "CONTRACT_ORDER", precision = 10, scale = 0)
	private Long contractOrder;

	@FieldName(name = "其它商务条件")
	@Column(name = "OTHER_CONDITION", length = 2000)
	private String otherCondition;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;
	
	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name = "MODIFY_DATE", length = 40)
	private String modifyDate;
	
	@FieldName(name = "租赁物件列表")
	@OneToMany(mappedBy = "projInfo", fetch = FetchType.LAZY)
	private Set<ProjEquip> projEquips = new HashSet<ProjEquip>();

	@FieldName(name = "项目担保方式")
	@OneToMany(mappedBy = "projInfo", fetch = FetchType.LAZY)
	private Set<ProjGuaranteeMethod> projGuaranteeMethods = new HashSet<ProjGuaranteeMethod>();

	@FieldName(name = "项目抵质押物件列表")
	@OneToMany(mappedBy = "projInfo", fetch = FetchType.LAZY)
	private Set<ProjGuaranteeEquip> projGuaranteeEquips = new HashSet<ProjGuaranteeEquip>();
	
	@FieldName(name = "联合承租人")
	@OneToMany(mappedBy = "projInfo", fetch = FetchType.LAZY)
	private Set<ProjUnionCust> projUnionCusts = new HashSet<ProjUnionCust>();
	
	
	//租金测算相关
	@FieldName(name="项目现金流")
	//mappedBy 中指定的值时实体类中的字段名 不是数据库的名字
	@OneToMany(mappedBy="projId", fetch=FetchType.LAZY)
	private Set<ProjCashDetail>  projCashDetails=new HashSet<ProjCashDetail>();
	
	
	@FieldName(name="项目会计现金流")
	@OneToMany(mappedBy="projId", fetch=FetchType.LAZY)
	private Set<ProjFinaCashDetail>  projFinaCashDetails=new HashSet<ProjFinaCashDetail>();
	
	@FieldName(name="合同租金计划")
	@OneToMany(mappedBy="projId", fetch=FetchType.LAZY)
	private Set<ProjFundRentPlan> projFundRentPlans=new HashSet<ProjFundRentPlan>();
	
	@FieldName(name="合同租金计划")
	@OneToMany(mappedBy="projId", fetch=FetchType.LAZY)
	private Set<ProjFinaRentPlan> projFinaRentPlans=new HashSet<ProjFinaRentPlan>();

	@FieldName(name="合同资金计划")
	@OneToMany(mappedBy="projId", fetch=FetchType.LAZY)
	private Set<ProjFundFundPlan> projFundFundPlans=new HashSet<ProjFundFundPlan>();
	
	@FieldName(name="商务条件")
	@OneToOne(mappedBy="projId",fetch=FetchType.LAZY) 
	private ProjCondition projCondition =new ProjCondition();
	
	// 一对一的表	
	@FieldName(name="项目发票信息")
	@OneToOne(mappedBy="projId",fetch=FetchType.LAZY) 
	private ProjInvoiceType projInvoiceType ;
	
	@FieldName(name="项目下属合同")
	@OneToMany(mappedBy="projId",fetch=FetchType.LAZY) 
	private Set<ContractInfo> contractInfos = new HashSet<ContractInfo>();
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}



	public DictionaryData getProjScale() {
		return projScale;
	}

	public void setProjScale(DictionaryData projScale) {
		this.projScale = projScale;
	}

	public DictionaryData getIndustryType() {
		return industryType;
	}

	public void setIndustryType(DictionaryData industryType) {
		this.industryType = industryType;
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

	public DictionaryData getProjType() {
		return projType;
	}

	public void setProjType(DictionaryData projType) {
		this.projType = projType;
	}

	public DictionaryData getProjSource() {
		return projSource;
	}

	public void setProjSource(DictionaryData projSource) {
		this.projSource = projSource;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public DictionaryData getLeasForm() {
		return leasForm;
	}

	public void setLeasForm(DictionaryData leasForm) {
		this.leasForm = leasForm;
	}

	public DictionaryData getLeasType() {
		return leasType;
	}

	public void setLeasType(DictionaryData leasType) {
		this.leasType = leasType;
	}

	public String getProjId() {
		return projId;
	}

	public void setProjId(String projId) {
		this.projId = projId;
	}

	public String getProjDate() {
		return projDate;
	}

	public void setProjDate(String projDate) {
		this.projDate = projDate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(Integer projStatus) {
		this.projStatus = projStatus;
	}

	public String getRepealDate() {
		return repealDate;
	}

	public void setRepealDate(String repealDate) {
		this.repealDate = repealDate;
	}

	public DictionaryData getRepealReasonType() {
		return RepealReasonType;
	}

	public void setRepealReasonType(DictionaryData repealReasonType) {
		RepealReasonType = repealReasonType;
	}

	public String getRepealReason() {
		return repealReason;
	}

	public void setRepealReason(String repealReason) {
		this.repealReason = repealReason;
	}

	public String getApproveConclusion() {
		return approveConclusion;
	}

	public void setApproveConclusion(String approveConclusion) {
		this.approveConclusion = approveConclusion;
	}

	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

	public Long getContractOrder() {
		return contractOrder;
	}

	public void setContractOrder(Long contractOrder) {
		this.contractOrder = contractOrder;
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

	public Set<ProjEquip> getProjEquips() {
		return projEquips;
	}

	public void setProjEquips(Set<ProjEquip> projEquips) {
		this.projEquips = projEquips;
	}

	public Set<ProjGuaranteeMethod> getProjGuaranteeMethods() {
		return projGuaranteeMethods;
	}

	public void setProjGuaranteeMethods(
			Set<ProjGuaranteeMethod> projGuaranteeMethods) {
		this.projGuaranteeMethods = projGuaranteeMethods;
	}

	public Set<ProjGuaranteeEquip> getProjGuaranteeEquips() {
		return projGuaranteeEquips;
	}

	public void setProjGuaranteeEquips(Set<ProjGuaranteeEquip> projGuaranteeEquips) {
		this.projGuaranteeEquips = projGuaranteeEquips;
	}

	public Set<ProjCashDetail> getProjCashDetails() {
		return projCashDetails;
	}

	public void setProjCashDetails(Set<ProjCashDetail> projCashDetails) {
		this.projCashDetails = projCashDetails;
	}

	public Set<ProjFinaCashDetail> getProjFinaCashDetails() {
		return projFinaCashDetails;
	}

	public void setProjFinaCashDetails(Set<ProjFinaCashDetail> projFinaCashDetails) {
		this.projFinaCashDetails = projFinaCashDetails;
	}

	public Set<ProjFundRentPlan> getProjFundRentPlans() {
		return projFundRentPlans;
	}

	public void setProjFundRentPlans(Set<ProjFundRentPlan> projFundRentPlans) {
		this.projFundRentPlans = projFundRentPlans;
	}

	public Set<ProjFinaRentPlan> getProjFinaRentPlans() {
		return projFinaRentPlans;
	}

	public void setProjFinaRentPlans(Set<ProjFinaRentPlan> projFinaRentPlans) {
		this.projFinaRentPlans = projFinaRentPlans;
	}

	public Set<ProjFundFundPlan> getProjFundFundPlans() {
		return projFundFundPlans;
	}

	public void setProjFundFundPlans(Set<ProjFundFundPlan> projFundFundPlans) {
		this.projFundFundPlans = projFundFundPlans;
	}

	public ProjCondition getProjCondition() {
		return projCondition;
	}

	public void setProjCondition(ProjCondition projCondition) {
		this.projCondition = projCondition;
	}

	public ProjInvoiceType getProjInvoiceType() {
		return projInvoiceType;
	}

	public void setProjInvoiceType(ProjInvoiceType projInvoiceType) {
		this.projInvoiceType = projInvoiceType;
	}

	public Set<ContractInfo> getContractInfos() {
		return contractInfos;
	}

	public void setContractInfos(Set<ContractInfo> contractInfos) {
		this.contractInfos = contractInfos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeMemo() {
		return changeMemo;
	}

	public void setChangeMemo(String changeMemo) {
		this.changeMemo = changeMemo;
	}

	public String getPresidentOpinions() {
		return presidentOpinions;
	}
	public void setPresidentOpinions(String presidentOpinions) {
		this.presidentOpinions = presidentOpinions;
	}

	public Set<ProjUnionCust> getProjUnionCusts() {
		return projUnionCusts;
	}

	public void setProjUnionCusts(Set<ProjUnionCust> projUnionCusts) {
		this.projUnionCusts = projUnionCusts;
	}	
	
}