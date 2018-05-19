package com.tenwa.leasing.entity.global;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

/**
 * 
 * @author luojian
 * @date 2017-2-21下午02:04:24
 * @info 合同资金计划表(历史)
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "单个项目月（年）度运行报表")
@Table(name="T_ANNUAL_OPERATION")
public class AnnualOperation {


	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;
		
	@FieldName(name="数据抓取ID")
	@Column(name="OPEN_ID",length=64)
	private String   openId;
	
	@FieldName(name="风场ID")
	@Column(name="WF_ID",length=64)
	private String   wfId;
	
	@FieldName(name="月报/年报")
	@Column(name="REPORT_TYPE",length=64)
	private String   reporttype;

	@FieldName(name="编号")
	@Column(name="REPORT_DATE",length=200)
	private String   reportDate;
	
	@FieldName(name="平均风速")
	@Column(name="WIND_SPEED",length=200)
	private String   windSpeed;
	
	@FieldName(name="温度（统计时段内）")
	@Column(name="TEMPE_RATURE",length=200)
	private String   tempeRature;
	
	@FieldName(name="等效利用小时数")
	@Column(name="ELECTRIC_DATE",length=200)
	private String   electricDate;
	
	@FieldName(name="机组可利用率")
	@Column(name="DATEAVAIL_ABILITY",length=200)
	private String   dateavailAbility;	
	
	@FieldName(name="故障次数")
	@Column(name="FAULT_NUMS",length=200)
	private String   faultNums;
	
	@FieldName(name="故障停机总时长")
	@Column(name="FAULT_DATE",length=200)
	private String   faultDate;
	
	@FieldName(name="平均无故障时间")
	@Column(name="MTBF",length=200)
	private String   mtbf;
	
	@FieldName(name="故障排除时间")
	@Column(name="MTTR",length=200)
	private String   mttr;
	
	@FieldName(name="维护损失电量")
	@Column(name="MAINTAIN_LOSS",length=200)
	private String   maintainLoss;
	
	@FieldName(name="限功率损失电量")
	@Column(name="LIMIT_LOSS",length=200)
	private String   limitLoss;
	
	@FieldName(name="故障损失电量")
	@Column(name="fault_loss",length=200)
	private String   faultLoss;
	
	@FieldName(name="损失电量合计")
	@Column(name="TOTAL_LOSS",length=200)
	private String   totalLoss;
	
	@FieldName(name="机组发电量")
	@Column(name="POWER",length=2000)
	private String   power;
	
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getTempeRature() {
		return tempeRature;
	}

	public void setTempeRature(String tempeRature) {
		this.tempeRature = tempeRature;
	}

	public String getElectricDate() {
		return electricDate;
	}

	public void setElectricDate(String electricDate) {
		this.electricDate = electricDate;
	}

	public String getDateavailAbility() {
		return dateavailAbility;
	}

	public void setDateavailAbility(String dateavailAbility) {
		this.dateavailAbility = dateavailAbility;
	}

	public String getFaultNums() {
		return faultNums;
	}

	public void setFaultNums(String faultNums) {
		this.faultNums = faultNums;
	}

	public String getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(String faultDate) {
		this.faultDate = faultDate;
	}

	public String getMtbf() {
		return mtbf;
	}

	public void setMtbf(String mtbf) {
		this.mtbf = mtbf;
	}

	public String getMttr() {
		return mttr;
	}

	public void setMttr(String mttr) {
		this.mttr = mttr;
	}

	public String getMaintainLoss() {
		return maintainLoss;
	}

	public void setMaintainLoss(String maintainLoss) {
		this.maintainLoss = maintainLoss;
	}

	public String getLimitLoss() {
		return limitLoss;
	}

	public void setLimitLoss(String limitLoss) {
		this.limitLoss = limitLoss;
	}

	public String getFaultLoss() {
		return faultLoss;
	}

	public void setFaultLoss(String faultLoss) {
		this.faultLoss = faultLoss;
	}

	public String getTotalLoss() {
		return totalLoss;
	}

	public void setTotalLoss(String totalLoss) {
		this.totalLoss = totalLoss;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
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

	public String getWfId() {
		return wfId;
	}

	public void setWfId(String wfId) {
		this.wfId = wfId;
	}

	public String getReporttype() {
		return reporttype;
	}

	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}

	

	
}
