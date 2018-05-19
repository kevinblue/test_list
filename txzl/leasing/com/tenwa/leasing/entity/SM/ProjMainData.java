package com.tenwa.leasing.entity.SM;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

/**
 * 
 * @author AngelKuma
 * @date 2017/03/10
 * @info 客户主数据
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目主数据")
@Table(name="T_PROJ_MAIN_DATA")
public class ProjMainData {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="项目编号")
	@Column(name="项目编号")
	private String projNum;
	
	@FieldName(name="项目状态")
	@Column(name="项目状态")
	private String projStatus;
	
	@FieldName(name="项目阶段")
	@Column(name="项目阶段")
	private String projStage;
	
	@FieldName(name="项目容量")
	@Column(name="项目容量")
	private String projCapacity;
	
	@FieldName(name="机组数量")
	@Column(name="机组数量")
	private String setsNum;
	
	@FieldName(name="维度类型")
	@Column(name="维度类型")
	private String latitudeType;
 
	@FieldName(name="纬度")
	@Column(name="纬度")
	private String latitude;
	
	@FieldName(name="经度类型")
	@Column(name="经度类型")
	private String longitudeType;
	
	@FieldName(name="经度")
	@Column(name="经度")
	private String longitude;
	
	@FieldName(name="片区")
	@Column(name="片区")
	private String district;
	
	@FieldName(name="项目名称")
	@Column(name="项目名称")
	private String projName;
	
	@FieldName(name="项目经理")
	@Column(name="项目经理")
	private String projManager;
	
	@FieldName(name="业主单位")
	@Column(name="业主单位")
	private String ownerUnit;
 
	@FieldName(name="合同主体")
	@Column(name="合同主体")
	private String contractSubject;
	
	@FieldName(name="合同签订日期")
	@Column(name="合同签订日期")
	private String contractSigningDate;
	
	@FieldName(name="质保期开始时间")
	@Column(name="质保期开始日期")
	private String warrantyStarttime;
	
	@FieldName(name="质保期时长")
	@Column(name="质保期时长")
	private String warrantyDuration;
	
	@FieldName(name="风场可用率")
	@Column(name="风场可用率")
	private String WindFieldAvailability;
	
	@FieldName(name="机组可用率")
	@Column(name="机组可用率")
	private String UnitAvailability;
	
	@FieldName(name="单台功率曲线")
	@Column(name="单台功率曲线")
	private String SinglePowerCurve;
	
	@FieldName(name="风场功率曲线")
	@Column(name="风场功率曲线")
	private String WindPowerCurve;
	
	@FieldName(name="功率曲线说明")
	@Column(name="功率曲线说明",length=2000)
	private String PowerCurveDescription ;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE")
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name = "更新日期")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjNum() {
		return projNum;
	}

	public void setProjNum(String projNum) {
		this.projNum = projNum;
	}

	public String getProjStatus() {
		return projStatus;
	}

	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}

	public String getProjStage() {
		return projStage;
	}

	public void setProjStage(String projStage) {
		this.projStage = projStage;
	}

	public String getProjCapacity() {
		return projCapacity;
	}

	public void setProjCapacity(String projCapacity) {
		this.projCapacity = projCapacity;
	}

	public String getSetsNum() {
		return setsNum;
	}

	public void setSetsNum(String setsNum) {
		this.setsNum = setsNum;
	}

	public String getLatitudeType() {
		return latitudeType;
	}

	public void setLatitudeType(String latitudeType) {
		this.latitudeType = latitudeType;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitudeType() {
		return longitudeType;
	}

	public void setLongitudeType(String longitudeType) {
		this.longitudeType = longitudeType;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getOwnerUnit() {
		return ownerUnit;
	}

	public void setOwnerUnit(String ownerUnit) {
		this.ownerUnit = ownerUnit;
	}

	public String getContractSubject() {
		return contractSubject;
	}

	public void setContractSubject(String contractSubject) {
		this.contractSubject = contractSubject;
	}

	public String getContractSigningDate() {
		return contractSigningDate;
	}

	public void setContractSigningDate(String contractSigningDate) {
		this.contractSigningDate = contractSigningDate;
	}

	public String getWarrantyStarttime() {
		return warrantyStarttime;
	}

	public void setWarrantyStarttime(String warrantyStarttime) {
		this.warrantyStarttime = warrantyStarttime;
	}

	public String getWarrantyDuration() {
		return warrantyDuration;
	}

	public void setWarrantyDuration(String warrantyDuration) {
		this.warrantyDuration = warrantyDuration;
	}

	public String getWindFieldAvailability() {
		return WindFieldAvailability;
	}

	public void setWindFieldAvailability(String windFieldAvailability) {
		WindFieldAvailability = windFieldAvailability;
	}

	public String getUnitAvailability() {
		return UnitAvailability;
	}

	public void setUnitAvailability(String unitAvailability) {
		UnitAvailability = unitAvailability;
	}

	public String getSinglePowerCurve() {
		return SinglePowerCurve;
	}

	public void setSinglePowerCurve(String singlePowerCurve) {
		SinglePowerCurve = singlePowerCurve;
	}

	public String getWindPowerCurve() {
		return WindPowerCurve;
	}

	public void setWindPowerCurve(String windPowerCurve) {
		WindPowerCurve = windPowerCurve;
	}

	public String getPowerCurveDescription() {
		return PowerCurveDescription;
	}

	public void setPowerCurveDescription(String powerCurveDescription) {
		PowerCurveDescription = powerCurveDescription;
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
