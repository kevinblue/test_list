package com.tenwa.leasing.entity.SM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;

@Entity
@FieldName(name = "机器主数据")
@Table(name="T_MACHINE_MAIN_DATA")
public class MachineMainData {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id;
	
	@FieldName(name="机组编号")
	@Column(name="UNIT_NUM")
	private String unitNum;
	
	@FieldName(name="风场编号")
	@Column(name="WIND_FIELD_NUM")
	private String windFieldNum;
	
	@FieldName(name="风场名称")
	@Column(name="WIND_FIELD_NAME")
	private String windFieldName;
	
	@FieldName(name="片区")
	@Column(name="AREA")
	private String area;
	
	@FieldName(name="项目部")
	@Column(name="PROJ_DEPT")
	private String projDept;
	
	@FieldName(name="项目经理")
	@Column(name="PROJ_MANAGER")
	private String projManager;
	
	@FieldName(name="机组状态")
	@Column(name="MACHINE_STATUS")
	private String machineStatus;
	
	@FieldName(name="运行机位号")
	@Column(name="MACHINE_NUM")
	private String machineNum;
	
	@FieldName(name="SCADA风机编号")
	@Column(name="MACHINE_NUM_SCA")
	private String machineNumSCA;
	
	@FieldName(name="SCADA风场编号")
	@Column(name="FIELD_NUM_SCA")
	private String fieldNumSCA;
	
	@FieldName(name="机组容量")
	@Column(name="UNIT_CAPACITY")
	private String unitCapacity;
	
	@FieldName(name="机型")
	@Column(name="MODEL")
	private String model;
	
	@FieldName(name="轮毂高度")
	@Column(name="WHEEL_HEIGHT")
	private String WheelHeight;
	
	@FieldName(name="IP地址")
	@Column(name="ADDRESS_OF_IP")
	private String  addressOfIP;
	
	@FieldName(name="子网掩码")
	@Column(name="SUBNET_MASK")
	private String  subnetMask;
	
	@FieldName(name="基础开挖时间")
	@Column(name="EXCAVATION_TIME")
	private String  excavationTime;
	
	@FieldName(name="基础浇筑时间")
	@Column(name="CASTING_TIME")
	private String  castingTime;
	
	@FieldName(name="箱变安装时间")
	@Column(name="BOXINSTALLATION_TIME")
	private String  boxInstallationTime;
	
	@FieldName(name="接货开始时间")
	@Column(name="PICKUPSTART_TIME")
	private String  pickUpStartTime;
	
	@FieldName(name="接货结束时间")
	@Column(name="PICKUPEND_TIME")
	private String  pickUpEndTime;
	
	@FieldName(name="吊装开始时间")
	@Column(name="LIFTINGSTART_TIME")
	private String  liftingStartTime;
	
	@FieldName(name="吊装结束时间")
	@Column(name="LIFTINGEND_TIME")
	private String  liftingEndTime;
	
	@FieldName(name="上电时间")
	@Column(name="POWER_ON_TIME")
	private String  powerOnTime;
	
	@FieldName(name="静调开始时间")
	@Column(name="STATIC_START_TIME")
	private String  staticStartTime;
	
	@FieldName(name="静调结束时间")
	@Column(name="STATIC_END_TIME")
	private String  staticEndTime;
	
	@FieldName(name="动调开始时间")
	@Column(name="MOVE_START_TIME")
	private String  moveStartTime;
	
	@FieldName(name="动调结束时间")
	@Column(name="MOVE_END_TIME")
	private String  moveEndTime;
	
	@FieldName(name="试运行开始时间")
	@Column(name="COMMISSIONING_STIME")
	private String  commissioningStime;
	
	@FieldName(name="试运行结束时间")
	@Column(name="COMMISSIONING_ETIME")
	private String  commissioningEtime;
	
	@FieldName(name="预验收通过时间")
	@Column(name="PREACCEPTANCE_TIME")
	private String  PreacceptanceTime;
	
	@FieldName(name="在建转质保时间")
	@Column(name="TURN_UNDER_WARRANTY")
	private String  turnUnderWarranty;
	
	@FieldName(name="主控程序版本号")
	@Column(name="MAIN_PROGRAM_VNUM")
	private String  mainProgramVnum;
	
	@FieldName(name="变流版本号")
	@Column(name="TRANSFORMER_VNUM")
	private String  transformerVnum;
	
	@FieldName(name="变桨版本号")
	@Column(name="ISANALYZED_VNUM")
	private String  isAnalyzedVnum;
	
	@FieldName(name="初始化文件版本号")
	@Column(name="INIT_FILE_VNUM")
	private String  initFileVnum;
	
	@FieldName(name="变桨类型")
	@Column(name="PROPELLER_TYPE")
	private String  propellerType;
	
	@FieldName(name="变流类型")
	@Column(name="CONVERTER_TYPE")
	private String  converterType;
	
	@FieldName(name="冷却")
	@Column(name="COLD")
	private String  cold;
	
	@FieldName(name="叶片")
	@Column(name="LEAF_BLADE")
	private String  leafBlade;
	
	@FieldName(name="总线类型")
	@Column(name="BUS_TYPE")
	private String  busType;
	
	@FieldName(name="塔筒")
	@Column(name="TOWER_DRUM")
	private String  towerDrum;
	
	@FieldName(name="基础形式")
	@Column(name="BASIC_TYPE")
	private String  basicType;
	
	@FieldName(name="塔架连接形式")
	@Column(name="TOWER_CONNECTION")
	private String  towerConnection;
	
	@FieldName(name="塔架图号")
	@Column(name="TOWER_DRAWING")
	private String  towerDrawing;
	
	@FieldName(name="预计出质保时间")
	@Column(name="EXPECT_WARRANTY_TIME")
	private String  expectWarrantyTime;
	
	@FieldName(name="最终交接结束时间")
	@Column(name="FINHAND_END_TIME")
	private String  finHandEndTime;
	
	@FieldName(name="吊装过程验收开始时间")
	@Column(name="HOISTING_STIME")
	private String  hoistingStime ;
	
	@FieldName(name="吊装过程验收结束时间")
	@Column(name="HOISTING_ETIME")
	private String  hoistingEtime ;
	
	@FieldName(name="力矩验收开始时间")
	@Column(name="TORQUE_STIME")
	private String  torqueStime ;
	
	@FieldName(name="力矩验收结束时间")
	@Column(name="TORQUE_ETIME")
	private String  torqueEtime ;
	
	@FieldName(name="接线开始时间")
	@Column(name="WIRING_STIME")
	private String  wiringStime ;
	
	@FieldName(name="接线结束时间")
	@Column(name="WIRING_ETIME")
	private String  wiringEtime ;
	
	@FieldName(name="整体验收开始时间")
	@Column(name="ALLACCEPTANCE_STIME")
	private String allacceptanceStime ;
	
	@FieldName(name="整体验收结束时间")
	@Column(name="ALLACCEPTANCE_ETIME")
	private String  allacceptanceEtime ;
	
	@FieldName(name="内部验收开始时间")
	@Column(name="INACCEPTANCE_STIME")
	private String inacceptanceStime ;
	
	@FieldName(name="内部验收结束时间")
	@Column(name="INACCEPTANCE_ETIME")
	private String  inacceptanceEtime ;
	
	@FieldName(name="预验收开始时间")
	@Column(name="WILL_ACCEPTANCE_STIME")
	private String willacceptanceStime ;
	
	@FieldName(name="最终交接开始时间")
	@Column(name="LAST_HANDOVER_STIME")
	private String lasthandoverStime ;
	
	@FieldName(name="最终交接验收开始时间")
	@Column(name="LAST_ACCEPTANCE_STIME")
	private String lastacceptanceStime ;
	
	@FieldName(name="最终交接验收结束时间")
	@Column(name="LAST_ACCEPTANCE_ETIME")
	private String lastacceptanceEtime ;
	
	@FieldName(name="最终出质保时间")
	@Column(name="LAST_WARRANTY_TIME")
	private String  lastWarrantyTime;
	
	@FieldName(name="系统创建时间")
	@Column(name="SYST_CREATE_TIME")
	private String  systCreateTime;
	
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

	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}

	public String getWindFieldNum() {
		return windFieldNum;
	}

	public void setWindFieldNum(String windFieldNum) {
		this.windFieldNum = windFieldNum;
	}

	public String getWindFieldName() {
		return windFieldName;
	}

	public void setWindFieldName(String windFieldName) {
		this.windFieldName = windFieldName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProjDept() {
		return projDept;
	}

	public void setProjDept(String projDept) {
		this.projDept = projDept;
	}

	public String getProjManager() {
		return projManager;
	}

	public void setProjManager(String projManager) {
		this.projManager = projManager;
	}

	public String getMachineStatus() {
		return machineStatus;
	}

	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}

	public String getMachineNum() {
		return machineNum;
	}

	public void setMachineNum(String machineNum) {
		this.machineNum = machineNum;
	}

	public String getMachineNumSCA() {
		return machineNumSCA;
	}

	public void setMachineNumSCA(String machineNumSCA) {
		this.machineNumSCA = machineNumSCA;
	}

	public String getFieldNumSCA() {
		return fieldNumSCA;
	}

	public void setFieldNumSCA(String fieldNumSCA) {
		this.fieldNumSCA = fieldNumSCA;
	}

	public String getUnitCapacity() {
		return unitCapacity;
	}

	public void setUnitCapacity(String unitCapacity) {
		this.unitCapacity = unitCapacity;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getWheelHeight() {
		return WheelHeight;
	}

	public void setWheelHeight(String wheelHeight) {
		WheelHeight = wheelHeight;
	}

	public String getAddressOfIP() {
		return addressOfIP;
	}

	public void setAddressOfIP(String addressOfIP) {
		this.addressOfIP = addressOfIP;
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getExcavationTime() {
		return excavationTime;
	}

	public void setExcavationTime(String excavationTime) {
		this.excavationTime = excavationTime;
	}

	public String getCastingTime() {
		return castingTime;
	}

	public void setCastingTime(String castingTime) {
		this.castingTime = castingTime;
	}

	public String getBoxInstallationTime() {
		return boxInstallationTime;
	}

	public void setBoxInstallationTime(String boxInstallationTime) {
		this.boxInstallationTime = boxInstallationTime;
	}

	public String getPickUpStartTime() {
		return pickUpStartTime;
	}

	public void setPickUpStartTime(String pickUpStartTime) {
		this.pickUpStartTime = pickUpStartTime;
	}

	public String getPickUpEndTime() {
		return pickUpEndTime;
	}

	public void setPickUpEndTime(String pickUpEndTime) {
		this.pickUpEndTime = pickUpEndTime;
	}

	public String getLiftingStartTime() {
		return liftingStartTime;
	}

	public void setLiftingStartTime(String liftingStartTime) {
		this.liftingStartTime = liftingStartTime;
	}

	public String getLiftingEndTime() {
		return liftingEndTime;
	}

	public void setLiftingEndTime(String liftingEndTime) {
		this.liftingEndTime = liftingEndTime;
	}

	public String getPowerOnTime() {
		return powerOnTime;
	}

	public void setPowerOnTime(String powerOnTime) {
		this.powerOnTime = powerOnTime;
	}

	public String getStaticStartTime() {
		return staticStartTime;
	}

	public void setStaticStartTime(String staticStartTime) {
		this.staticStartTime = staticStartTime;
	}

	public String getStaticEndTime() {
		return staticEndTime;
	}

	public void setStaticEndTime(String staticEndTime) {
		this.staticEndTime = staticEndTime;
	}

	public String getMoveStartTime() {
		return moveStartTime;
	}

	public void setMoveStartTime(String moveStartTime) {
		this.moveStartTime = moveStartTime;
	}

	public String getMoveEndTime() {
		return moveEndTime;
	}

	public void setMoveEndTime(String moveEndTime) {
		this.moveEndTime = moveEndTime;
	}

	public String getCommissioningStime() {
		return commissioningStime;
	}

	public void setCommissioningStime(String commissioningStime) {
		this.commissioningStime = commissioningStime;
	}

	public String getCommissioningEtime() {
		return commissioningEtime;
	}

	public void setCommissioningEtime(String commissioningEtime) {
		this.commissioningEtime = commissioningEtime;
	}

	public String getPreacceptanceTime() {
		return PreacceptanceTime;
	}

	public void setPreacceptanceTime(String preacceptanceTime) {
		PreacceptanceTime = preacceptanceTime;
	}

	public String getTurnUnderWarranty() {
		return turnUnderWarranty;
	}

	public void setTurnUnderWarranty(String turnUnderWarranty) {
		this.turnUnderWarranty = turnUnderWarranty;
	}

	public String getMainProgramVnum() {
		return mainProgramVnum;
	}

	public void setMainProgramVnum(String mainProgramVnum) {
		this.mainProgramVnum = mainProgramVnum;
	}

	public String getTransformerVnum() {
		return transformerVnum;
	}

	public void setTransformerVnum(String transformerVnum) {
		this.transformerVnum = transformerVnum;
	}

	public String getIsAnalyzedVnum() {
		return isAnalyzedVnum;
	}

	public void setIsAnalyzedVnum(String isAnalyzedVnum) {
		this.isAnalyzedVnum = isAnalyzedVnum;
	}

	public String getInitFileVnum() {
		return initFileVnum;
	}

	public void setInitFileVnum(String initFileVnum) {
		this.initFileVnum = initFileVnum;
	}

	public String getPropellerType() {
		return propellerType;
	}

	public void setPropellerType(String propellerType) {
		this.propellerType = propellerType;
	}

	public String getConverterType() {
		return converterType;
	}

	public void setConverterType(String converterType) {
		this.converterType = converterType;
	}

	public String getCold() {
		return cold;
	}

	public void setCold(String cold) {
		this.cold = cold;
	}

	public String getLeafBlade() {
		return leafBlade;
	}

	public void setLeafBlade(String leafBlade) {
		this.leafBlade = leafBlade;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getTowerDrum() {
		return towerDrum;
	}

	public void setTowerDrum(String towerDrum) {
		this.towerDrum = towerDrum;
	}

	public String getBasicType() {
		return basicType;
	}

	public void setBasicType(String basicType) {
		this.basicType = basicType;
	}

	public String getTowerConnection() {
		return towerConnection;
	}

	public void setTowerConnection(String towerConnection) {
		this.towerConnection = towerConnection;
	}

	public String getTowerDrawing() {
		return towerDrawing;
	}

	public void setTowerDrawing(String towerDrawing) {
		this.towerDrawing = towerDrawing;
	}

	public String getExpectWarrantyTime() {
		return expectWarrantyTime;
	}

	public void setExpectWarrantyTime(String expectWarrantyTime) {
		this.expectWarrantyTime = expectWarrantyTime;
	}

	public String getFinHandEndTime() {
		return finHandEndTime;
	}

	public void setFinHandEndTime(String finHandEndTime) {
		this.finHandEndTime = finHandEndTime;
	}

	public String getHoistingStime() {
		return hoistingStime;
	}

	public void setHoistingStime(String hoistingStime) {
		this.hoistingStime = hoistingStime;
	}

	public String getHoistingEtime() {
		return hoistingEtime;
	}

	public void setHoistingEtime(String hoistingEtime) {
		this.hoistingEtime = hoistingEtime;
	}

	public String getTorqueStime() {
		return torqueStime;
	}

	public void setTorqueStime(String torqueStime) {
		this.torqueStime = torqueStime;
	}

	public String getTorqueEtime() {
		return torqueEtime;
	}

	public void setTorqueEtime(String torqueEtime) {
		this.torqueEtime = torqueEtime;
	}

	public String getWiringStime() {
		return wiringStime;
	}

	public void setWiringStime(String wiringStime) {
		this.wiringStime = wiringStime;
	}

	public String getWiringEtime() {
		return wiringEtime;
	}

	public void setWiringEtime(String wiringEtime) {
		this.wiringEtime = wiringEtime;
	}

	public String getAllacceptanceStime() {
		return allacceptanceStime;
	}

	public void setAllacceptanceStime(String allacceptanceStime) {
		this.allacceptanceStime = allacceptanceStime;
	}

	public String getAllacceptanceEtime() {
		return allacceptanceEtime;
	}

	public void setAllacceptanceEtime(String allacceptanceEtime) {
		this.allacceptanceEtime = allacceptanceEtime;
	}

	public String getInacceptanceStime() {
		return inacceptanceStime;
	}

	public void setInacceptanceStime(String inacceptanceStime) {
		this.inacceptanceStime = inacceptanceStime;
	}

	public String getInacceptanceEtime() {
		return inacceptanceEtime;
	}

	public void setInacceptanceEtime(String inacceptanceEtime) {
		this.inacceptanceEtime = inacceptanceEtime;
	}

	public String getWillacceptanceStime() {
		return willacceptanceStime;
	}

	public void setWillacceptanceStime(String willacceptanceStime) {
		this.willacceptanceStime = willacceptanceStime;
	}

	public String getLasthandoverStime() {
		return lasthandoverStime;
	}

	public void setLasthandoverStime(String lasthandoverStime) {
		this.lasthandoverStime = lasthandoverStime;
	}

	public String getLastacceptanceStime() {
		return lastacceptanceStime;
	}

	public void setLastacceptanceStime(String lastacceptanceStime) {
		this.lastacceptanceStime = lastacceptanceStime;
	}

	public String getLastacceptanceEtime() {
		return lastacceptanceEtime;
	}

	public void setLastacceptanceEtime(String lastacceptanceEtime) {
		this.lastacceptanceEtime = lastacceptanceEtime;
	}

	public String getLastWarrantyTime() {
		return lastWarrantyTime;
	}

	public void setLastWarrantyTime(String lastWarrantyTime) {
		this.lastWarrantyTime = lastWarrantyTime;
	}

	public String getSystCreateTime() {
		return systCreateTime;
	}

	public void setSystCreateTime(String systCreateTime) {
		this.systCreateTime = systCreateTime;
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
