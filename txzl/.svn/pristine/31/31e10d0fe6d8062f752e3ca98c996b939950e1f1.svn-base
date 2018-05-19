package com.tenwa.leasing.entity.proj;

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

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;

@Entity
@FieldName(name = "项目建设进度SM接口数据新增")
@Table(name="T_PROJECT_PROGRESS")
public class ProjectProgress {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name="项目id号")
	@JoinColumn(name = "PROJ_id")
	private ProjDevelopInfo projId;
	

	public ProjDevelopInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjDevelopInfo projId) {
		this.projId = projId;
	}
	
	@FieldName(name="报告日期")
	@Column(name=" REPORT_DATE")
	private String reportDate;
	
	@FieldName(name="进场道路")
	@Column(name=" ACCESS_ROAD")
	private String accessRoad;
	
	@FieldName(name="场内道路")
	@Column(name="PIT_ROAD")
	private String pitRoad;
	
	
	@FieldName(name="升压站土建")
	@Column(name="CIVIL")
	private String civil;
	
	@FieldName(name="升压站设备安装")
	@Column(name="EQUIPMENT_INSTALL")
	private String equipmentInstall;
	
	@FieldName(name="集电线路基础")
	@Column(name="COLLECT_POWER_LINE_ROAD")
	private String collectPowerLineRoad;
	
	@FieldName(name="集电线路敷设")
	@Column(name="COLLECT_POWER_LINE_ROAD_LAY")
	private String collectPowerLineRoadLay;
	
	@FieldName(name="风机基础")
	@Column(name="WIND_MACHINE_BASIC")
	private String windMachineBasic;
	
	@FieldName(name="风机吊装")
	@Column(name="WIND_MACHINE_HOISTING")
	private String windMachineHoisting;
	
	@FieldName(name="箱变基础")
	@Column(name="BOX_FOUNDATION")
	private String boxFoundation;
	
	@FieldName(name="箱变安装")
	@Column(name="BOX_CHANGE_INSTALLATION")
	private String boxChangeInstallation;
	
	@FieldName(name="送出线路")
	@Column(name="EMPTY_LINE_ROAD")
	private String emptyLineRoad;
	
	@FieldName(name="风机到货")
	@Column(name="ELEC_EQUIPMENT")
	private String elecEquipment;
	
	@FieldName(name="塔架到货")
	@Column(name="TOWER")
	private String tower;
	
	
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
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getAccessRoad() {
		return accessRoad;
	}

	public void setAccessRoad(String accessRoad) {
		this.accessRoad = accessRoad;
	}

	public String getPitRoad() {
		return pitRoad;
	}

	public void setPitRoad(String pitRoad) {
		this.pitRoad = pitRoad;
	}

	public String getCollectPowerLineRoad() {
		return collectPowerLineRoad;
	}

	public void setCollectPowerLineRoad(String collectPowerLineRoad) {
		this.collectPowerLineRoad = collectPowerLineRoad;
	}

	public String getEmptyLineRoad() {
		return emptyLineRoad;
	}

	public void setEmptyLineRoad(String emptyLineRoad) {
		this.emptyLineRoad = emptyLineRoad;
	}

	public String getCivil() {
		return civil;
	}

	public void setCivil(String civil) {
		this.civil = civil;
	}

	public String getEquipmentInstall() {
		return equipmentInstall;
	}

	public void setEquipmentInstall(String equipmentInstall) {
		this.equipmentInstall = equipmentInstall;
	}

	public String getElecEquipment() {
		return elecEquipment;
	}

	public void setElecEquipment(String elecEquipment) {
		this.elecEquipment = elecEquipment;
	}

	public String getTower() {
		return tower;
	}

	public void setTower(String tower) {
		this.tower = tower;
	}

	
	
	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getCollectPowerLineRoadLay() {
		return collectPowerLineRoadLay;
	}

	public void setCollectPowerLineRoadLay(String collectPowerLineRoadLay) {
		this.collectPowerLineRoadLay = collectPowerLineRoadLay;
	}

	public String getWindMachineBasic() {
		return windMachineBasic;
	}

	public void setWindMachineBasic(String windMachineBasic) {
		this.windMachineBasic = windMachineBasic;
	}

	public String getWindMachineHoisting() {
		return windMachineHoisting;
	}

	public void setWindMachineHoisting(String windMachineHoisting) {
		this.windMachineHoisting = windMachineHoisting;
	}

	public String getBoxFoundation() {
		return boxFoundation;
	}

	public void setBoxFoundation(String boxFoundation) {
		this.boxFoundation = boxFoundation;
	}

	public String getBoxChangeInstallation() {
		return boxChangeInstallation;
	}

	public void setBoxChangeInstallation(String boxChangeInstallation) {
		this.boxChangeInstallation = boxChangeInstallation;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}
	
	
}
