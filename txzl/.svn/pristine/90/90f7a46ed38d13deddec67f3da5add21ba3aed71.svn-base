package com.tenwa.leasing.entity.other;


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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Entity
@Table(name = "PROJ_CREDIT_REPORT_INFO")
@FieldName(name="项目台账信息")
public class CreditReportInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 32)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ProjInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	/**
	 * 台账信息公用列
	 */
	@FieldName(name="申报项目金额（万元）")
	@Column(name="APPROVAL_ITEM_AMT",length = 300)
	private String approvalitemamt;
	
	@FieldName(name="申报净融资额（万元）")
	@Column(name="APPROVAL_ACTUAL_AMT",length = 300)
	private String approvalactualamt;
	
	@FieldName(name="核准项目金额（万元）")
	@Column(name="DECLARE_ITEM_AMT",length = 300)
	private String declareitemamt;
	
	@FieldName(name="核准实际融资金额（万元）")
	@Column(name="DECLARE_ACTUAL_AMT",length = 300)
	private String declareactualamt;
	
	@FieldName(name="交易类型")
	@Column(name="TRADE_TYPE",length = 300)
	private String tradetype;		
	
	@FieldName(name="融资目的")
	@Column(name="FINANCING_GOAL",length = 3000)
	private String financinggoal;	
	
	@FieldName(name="核准增信措施")
	@Column(name="CREDIT_MEASURES",length = 3000)
	private String creditmeasures;
	
	@FieldName(name="是否符合初选")
	@Column(name="IS_PRIMARY",length = 300)
	private String isprimary;	

	@FieldName(name="是否超区域授信")
	@Column(name="IS_EXCEED_CREDIT",length = 300)
	private String isexceedcredit;	
	
	@FieldName(name="具体超标条款")
	@Column(name="EXCESS_DETAIL",length = 3000)
	private String excessdetail;
	
	@FieldName(name="信审意见类型")
	@Column(name="OPINION_TYPE",length = 300)
	private String opiniontype;	
	
	@FieldName(name="信审意见（包括否决原因）")
	@Column(name="OPINION",length = 3000)
	private String opinion;		
	
	@FieldName(name="所在城市等级")
	@Column(name="CITY_RANK",length = 300)
	private String cityrank;		
	
	/**
	 * 台账信息-政府融资列(旅游融资列一致)
	 */
	@FieldName(name="年度确认收入")
	@Column(name="YEAR_INCOME",length = 300)
	private String yearincome;

	@FieldName(name="刚性负债本金余额（万人民币）")
	@Column(name="GXFZ",length = 300)
	private String gxfz;		
	
	@FieldName(name="刚性负债收入比")
	@Column(name="GXFZ_RATIO",length = 300)
	private String gxfzratio;	
	
	@FieldName(name="融资收入比")
	@Column(name="LEASE_RATIO",length = 300)
	private String leaseratio;	
	
	@FieldName(name="资产规模（亿元）")
	@Column(name="ASSET_AMT",length = 300)
	private String assetamt;	
	
	@FieldName(name="资产负债率")
	@Column(name="ASSET_DEBT_RATIO",length = 300)
	private String assetdebtratio;	
	
	@FieldName(name="上年度GDP（亿元）")
	@Column(name="YEAR_GDP",length = 300)
	private String yeargdp;	
	
	@FieldName(name="近三年平均GDP平均增长率")
	@Column(name="GDP_INCREASE",length = 300)
	private String gdpIncrease;	
	
	@FieldName(name="上年度公共财政预算收入（亿元）")
	@Column(name="BALANCE_INCOME",length = 300)
	private String balanceIncome;
	
	@FieldName(name="近三年一般预算平均增长率")
	@Column(name="BALANCE_INCREASE",length = 300)
	private String balanceIncrease;
	
	@FieldName(name="税收收入占比")
	@Column(name="TAX_RATIO",length = 300)
	private String taxratio;

	@FieldName(name="可支配收入（亿元）")
	@Column(name="CONTROL_INCOME",length = 300)
	private String  controlIncome;
	
	@FieldName(name="政府债务余额（亿元）")
	@Column(name="GOV_DEBT",length = 300)
	private String govdebt;
	
	@FieldName(name="财政负债率")
	@Column(name="FUZHAI_RATIO",length = 300)
	private String fuzhairatio;

	@FieldName(name="财政债务率")
	@Column(name="ZAIWU_RATIO",length = 300)
	private String zaiwuratio;
	
	@FieldName(name="财政偿债率")
	@Column(name="GOV_PAYDEBT_RATIO",length = 300)
	private String govpaydebtratio;
	
	@FieldName(name="政府模型评分")
	@Column(name="MODEL_SCORE",length = 300)
	private String modelscore;
	
	@FieldName(name="平台公司名称")
	@Column(name="PLATFORM_NAME",length = 300)
	private String platformname;
	
	@FieldName(name="平台资产规模（亿元）")
	@Column(name="PLATFORM_ASSET",length = 40)
	private String platformssset;
	
	@FieldName(name="是否发债")
	@Column(name="IS_DEBT",length = 40)
	private String isdebt;

	@FieldName(name="主体信用评级")
	@Column(name="PLATFORM_CREDITLEVEL",length = 40)
	private String platformcreditlevel;
	
	@FieldName(name="平台资产负债率")
	@Column(name="PLATFORM_DEBT",length = 300)
	private String platformdebt;
	
	@FieldName(name="近三年地方可支配财力平均增长率")
	@Column(name="BALANCE_INCREASE_RATE",length = 300)
	private String balanceIncreaseRate;
	
	/**
	 * 台账信息-教育类融资列
	 */
	@FieldName(name="主管机构")
	@Column(name="AUTHORITY",length = 300)
	private String authority;
	
	@FieldName(name="主办单位")
	@Column(name="HOST_UNIT",length = 300)
	private String hostunit;
	
	@FieldName(name="客户层次")
	@Column(name="CUSTL_EVEL",length = 300)
	private String custlevel;
	
	@FieldName(name="办学层次")
	@Column(name="SCHOOL_LEVEL",length = 300)
	private String schoollevel;
	
	@FieldName(name="可确认事业收入（万元）")
	@Column(name="CAUSE_INCOME",length = 300)
	private String causeincome;
	
	@FieldName(name="在校生合计")
	@Column(name="STUDENT_TOTAL",length = 300)
	private String studenttotal;
	
	@FieldName(name="在职教师人数")
	@Column(name="TEACHER_TOTAL",length = 300)
	private String teachertotal;
	
	@FieldName(name="师生比")
	@Column(name="TEA_STU_RATIO",length = 300)
	private String teasturatio;
	
	@FieldName(name="报到率")
	@Column(name="REPORT_RATIO",length = 300)
	private String reportratio;	
	
	@FieldName(name="实招增长率")
	@Column(name="GROWTH_RATIO",length = 300)
	private String growthratio;		
	
	@FieldName(name="预测一年租金覆盖比")
	@Column(name="RENT_YEAR_RATIO",length = 300)
	private String rentyearratio;
	
	@FieldName(name="预测租赁期平均租金覆盖比")
	@Column(name="AVG_RENTRATIO",length = 300)
	private String avgentratio;
	
	/**
	 * 台账信息-教育类融资列
	 */
	@FieldName(name="控制人从业历史")
	@Column(name="CONTROLLER_HISTORY",length = 3000)
	private String controllerhistory;
	
	@FieldName(name="租金覆盖比")
	@Column(name="RENT_RATIO",length = 300)
	private String rentratio;
	
	/**
	 * 台账信息-制药列
	 */
	@FieldName(name="行业类别")
	@Column(name="INDUSTRY_TYPE",length = 300)
	private String industrytype;
	
	@FieldName(name="收入增长率")
	@Column(name="INCOME_RATIO",length = 300)
	private String incomeratio;
	
	@FieldName(name="收入融资比")
	@Column(name="INCOME_FINNANCE",length = 300)
	private String incomefinance;	
	
	@FieldName(name="营运周期")
	@Column(name="OPERATE_CYCLE",length = 300)
	private String operatecycle;	

	@FieldName(name="综合金融负债负担水平")
	@Column(name="BURDEN_LEVEL",length = 300)
	private String burdenlevel;
	
	/**
	 * 台账信息-一医疗列
	 */
	@FieldName(name="出院者平均住院日")
	@Column(name="BED_TURNOVER",length = 300)
	private String bedturnover;
	
	@FieldName(name="病床使用率")
	@Column(name="BED_RATIO",length = 300)
	private String bedratio;	
	
	@FieldName(name="实际病床数")
	@Column(name="BED_NUM",length = 300)
	private String bednum;
	
	@FieldName(name="门诊病人次均医疗费用")
	@Column(name="MZ_FEE",length = 300)
	private String mzfee;
	
	@FieldName(name="住院病人人均医药费用")
	@Column(name="ZY_FEE",length = 300)
	private String zyfee;
	
	@FieldName(name="是否高风险项目")
	@Column(name="IS_HIGHRISK",length = 300)
	private String ishighrisk;
	
	@FieldName(name="列为高风险项目原因")
	@Column(name="HIGHRISK_REASON",length = 3000)
	private String highriskreason;
	
	@FieldName(name="客户类型")
	@Column(name="CUST_TYPE",length = 300)
	private String custtype;
	
	@FieldName(name="医院类型")
	@Column(name="HOSPITAL_TYPE",length = 300)
	private String hospitaltype;
	
	@FieldName(name="医院资质等级")
	@Column(name="HOSPITAL_LEVEL",length = 300)
	private String hospitallevel;
	
	@FieldName(name="医院模型评分")
	@Column(name="HOSPITAL_SCORE",length = 40)
	private String hospitalscore;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getApprovalitemamt() {
		return approvalitemamt;
	}

	public void setApprovalitemamt(String approvalitemamt) {
		this.approvalitemamt = approvalitemamt;
	}

	public String getApprovalactualamt() {
		return approvalactualamt;
	}

	public void setApprovalactualamt(String approvalactualamt) {
		this.approvalactualamt = approvalactualamt;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getFinancinggoal() {
		return financinggoal;
	}

	public void setFinancinggoal(String financinggoal) {
		this.financinggoal = financinggoal;
	}

	public String getCreditmeasures() {
		return creditmeasures;
	}

	public void setCreditmeasures(String creditmeasures) {
		this.creditmeasures = creditmeasures;
	}

	public String getIsprimary() {
		return isprimary;
	}

	public void setIsprimary(String isprimary) {
		this.isprimary = isprimary;
	}

	public String getIsexceedcredit() {
		return isexceedcredit;
	}

	public void setIsexceedcredit(String isexceedcredit) {
		this.isexceedcredit = isexceedcredit;
	}

	public String getExcessdetail() {
		return excessdetail;
	}

	public void setExcessdetail(String excessdetail) {
		this.excessdetail = excessdetail;
	}

	public String getOpiniontype() {
		return opiniontype;
	}

	public void setOpiniontype(String opiniontype) {
		this.opiniontype = opiniontype;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getCityrank() {
		return cityrank;
	}

	public void setCityrank(String cityrank) {
		this.cityrank = cityrank;
	}

	public String getYearincome() {
		return yearincome;
	}

	public void setYearincome(String yearincome) {
		this.yearincome = yearincome;
	}

	public String getGxfz() {
		return gxfz;
	}

	public void setGxfz(String gxfz) {
		this.gxfz = gxfz;
	}

	public String getGxfzratio() {
		return gxfzratio;
	}

	public void setGxfzratio(String gxfzratio) {
		this.gxfzratio = gxfzratio;
	}

	public String getLeaseratio() {
		return leaseratio;
	}

	public void setLeaseratio(String leaseratio) {
		this.leaseratio = leaseratio;
	}

	public String getAssetamt() {
		return assetamt;
	}

	public void setAssetamt(String assetamt) {
		this.assetamt = assetamt;
	}

	public String getAssetdebtratio() {
		return assetdebtratio;
	}

	public void setAssetdebtratio(String assetdebtratio) {
		this.assetdebtratio = assetdebtratio;
	}

	public String getYeargdp() {
		return yeargdp;
	}

	public void setYeargdp(String yeargdp) {
		this.yeargdp = yeargdp;
	}

	public String getGdpIncrease() {
		return gdpIncrease;
	}

	public void setGdpIncrease(String gdpIncrease) {
		this.gdpIncrease = gdpIncrease;
	}

	public String getBalanceIncome() {
		return balanceIncome;
	}

	public void setBalanceIncome(String balanceIncome) {
		this.balanceIncome = balanceIncome;
	}

	public String getBalanceIncrease() {
		return balanceIncrease;
	}

	public void setBalanceIncrease(String balanceIncrease) {
		this.balanceIncrease = balanceIncrease;
	}

	public String getTaxratio() {
		return taxratio;
	}

	public void setTaxratio(String taxratio) {
		this.taxratio = taxratio;
	}

	public String getControlIncome() {
		return controlIncome;
	}

	public void setControlIncome(String controlIncome) {
		this.controlIncome = controlIncome;
	}

	public String getGovdebt() {
		return govdebt;
	}

	public void setGovdebt(String govdebt) {
		this.govdebt = govdebt;
	}

	public String getFuzhairatio() {
		return fuzhairatio;
	}

	public void setFuzhairatio(String fuzhairatio) {
		this.fuzhairatio = fuzhairatio;
	}

	public String getZaiwuratio() {
		return zaiwuratio;
	}

	public void setZaiwuratio(String zaiwuratio) {
		this.zaiwuratio = zaiwuratio;
	}

	public String getGovpaydebtratio() {
		return govpaydebtratio;
	}

	public void setGovpaydebtratio(String govpaydebtratio) {
		this.govpaydebtratio = govpaydebtratio;
	}

	public String getModelscore() {
		return modelscore;
	}

	public void setModelscore(String modelscore) {
		this.modelscore = modelscore;
	}

	public String getPlatformname() {
		return platformname;
	}

	public void setPlatformname(String platformname) {
		this.platformname = platformname;
	}

	public String getPlatformssset() {
		return platformssset;
	}

	public void setPlatformssset(String platformssset) {
		this.platformssset = platformssset;
	}

	public String getIsdebt() {
		return isdebt;
	}

	public void setIsdebt(String isdebt) {
		this.isdebt = isdebt;
	}

	public String getPlatformcreditlevel() {
		return platformcreditlevel;
	}

	public void setPlatformcreditlevel(String platformcreditlevel) {
		this.platformcreditlevel = platformcreditlevel;
	}

	public String getPlatformdebt() {
		return platformdebt;
	}

	public void setPlatformdebt(String platformdebt) {
		this.platformdebt = platformdebt;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getHostunit() {
		return hostunit;
	}

	public void setHostunit(String hostunit) {
		this.hostunit = hostunit;
	}

	public String getCustlevel() {
		return custlevel;
	}

	public void setCustlevel(String custlevel) {
		this.custlevel = custlevel;
	}

	public String getSchoollevel() {
		return schoollevel;
	}

	public void setSchoollevel(String schoollevel) {
		this.schoollevel = schoollevel;
	}

	public String getCauseincome() {
		return causeincome;
	}

	public void setCauseincome(String causeincome) {
		this.causeincome = causeincome;
	}

	public String getStudenttotal() {
		return studenttotal;
	}

	public void setStudenttotal(String studenttotal) {
		this.studenttotal = studenttotal;
	}

	public String getTeachertotal() {
		return teachertotal;
	}

	public void setTeachertotal(String teachertotal) {
		this.teachertotal = teachertotal;
	}

	public String getTeasturatio() {
		return teasturatio;
	}

	public void setTeasturatio(String teasturatio) {
		this.teasturatio = teasturatio;
	}

	public String getReportratio() {
		return reportratio;
	}

	public void setReportratio(String reportratio) {
		this.reportratio = reportratio;
	}

	public String getGrowthratio() {
		return growthratio;
	}

	public void setGrowthratio(String growthratio) {
		this.growthratio = growthratio;
	}

	public String getRentyearratio() {
		return rentyearratio;
	}

	public void setRentyearratio(String rentyearratio) {
		this.rentyearratio = rentyearratio;
	}

	public String getAvgentratio() {
		return avgentratio;
	}

	public void setAvgentratio(String avgentratio) {
		this.avgentratio = avgentratio;
	}

	public String getControllerhistory() {
		return controllerhistory;
	}

	public void setControllerhistory(String controllerhistory) {
		this.controllerhistory = controllerhistory;
	}

	public String getRentratio() {
		return rentratio;
	}

	public void setRentratio(String rentratio) {
		this.rentratio = rentratio;
	}

	public String getIndustrytype() {
		return industrytype;
	}

	public void setIndustrytype(String industrytype) {
		this.industrytype = industrytype;
	}

	public String getIncomeratio() {
		return incomeratio;
	}

	public void setIncomeratio(String incomeratio) {
		this.incomeratio = incomeratio;
	}

	public String getIncomefinance() {
		return incomefinance;
	}

	public void setIncomefinance(String incomefinance) {
		this.incomefinance = incomefinance;
	}

	public String getOperatecycle() {
		return operatecycle;
	}

	public void setOperatecycle(String operatecycle) {
		this.operatecycle = operatecycle;
	}

	public String getBurdenlevel() {
		return burdenlevel;
	}

	public void setBurdenlevel(String burdenlevel) {
		this.burdenlevel = burdenlevel;
	}

	public String getBedturnover() {
		return bedturnover;
	}

	public void setBedturnover(String bedturnover) {
		this.bedturnover = bedturnover;
	}

	public String getBedratio() {
		return bedratio;
	}

	public void setBedratio(String bedratio) {
		this.bedratio = bedratio;
	}

	public String getBednum() {
		return bednum;
	}

	public void setBednum(String bednum) {
		this.bednum = bednum;
	}

	public String getMzfee() {
		return mzfee;
	}

	public void setMzfee(String mzfee) {
		this.mzfee = mzfee;
	}

	public String getZyfee() {
		return zyfee;
	}

	public void setZyfee(String zyfee) {
		this.zyfee = zyfee;
	}

	public String getIshighrisk() {
		return ishighrisk;
	}

	public void setIshighrisk(String ishighrisk) {
		this.ishighrisk = ishighrisk;
	}

	public String getHighriskreason() {
		return highriskreason;
	}

	public void setHighriskreason(String highriskreason) {
		this.highriskreason = highriskreason;
	}

	public String getCusttype() {
		return custtype;
	}

	public void setCusttype(String custtype) {
		this.custtype = custtype;
	}

	public String getHospitaltype() {
		return hospitaltype;
	}

	public void setHospitaltype(String hospitaltype) {
		this.hospitaltype = hospitaltype;
	}

	public String getHospitallevel() {
		return hospitallevel;
	}

	public void setHospitallevel(String hospitallevel) {
		this.hospitallevel = hospitallevel;
	}

	public String getHospitalscore() {
		return hospitalscore;
	}

	public void setHospitalscore(String hospitalscore) {
		this.hospitalscore = hospitalscore;
	}

	public String getDeclareitemamt() {
		return declareitemamt;
	}

	public void setDeclareitemamt(String declareitemamt) {
		this.declareitemamt = declareitemamt;
	}

	public String getDeclareactualamt() {
		return declareactualamt;
	}

	public void setDeclareactualamt(String declareactualamt) {
		this.declareactualamt = declareactualamt;
	}

	public String getBalanceIncreaseRate() {
		return balanceIncreaseRate;
	}

	public void setBalanceIncreaseRate(String balanceIncreaseRate) {
		this.balanceIncreaseRate = balanceIncreaseRate;
	}

	

}