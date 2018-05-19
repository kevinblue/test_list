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

import com.reckon.calculation.utils.Scale;
import com.tenwa.business.entity.Department;
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;
import com.tenwa.leasing.entity.proj.ProjInfo;

/**
 * ContractEquip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_EQUIP")
@FieldName(name="合同设备表")
public class ContractEquip implements java.io.Serializable {
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_TYPE")
	@FieldName(name="设备类型")
	private DictionaryData deviceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VNDR")
	@FieldName(name="供应商")
	private CustInfo  vndr;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT")
	@FieldName(name="代理商")
	private CustInfo  agent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER")
	@FieldName(name="生产商")
	private CustInfo  manufacturer;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	

	@Column(name = "BRAND_", length = 510)
	@FieldName(name="品牌")
	private String brand;

	@Column(name = "EQUIP_NAME", length = 510)
	@FieldName(name="设备名称")
	private String equipName;

	@Column(name = "MODEL_", length = 510)
	@FieldName(name="型号/规格")
	private String model;

	@Column(name = "EQUIP_ID", length = 510)
	@FieldName(name="设备编号")
	private String equipId;

	@Column(name = "EQUIP_NUM", precision = 10,scale = Scale.DEFAULT)
	@FieldName(name="数量")
	private BigDecimal equipNum;

	@Column(name = "PRICE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="单价")
	private BigDecimal price;

	@Column(name = "UNIT", length = 50)
	@FieldName(name="单位")
	private String unit;
	
	@Column(name = "TOTAL", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="总价")//设备原值
	private BigDecimal total;

	@Column(name = "NOWTOTAL", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="现值")
	private BigDecimal nowtotal;

	@Column(name = "EQUIP_PRICE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="交易价格")
	private BigDecimal equipPrice;

	@Column(name = "build_up_stop", length = 100)
	@FieldName(name="建造起止期")
	private String buildupstop;
	
	@Column(name = "start_stop_use_cycle", length = 100)
	@FieldName(name="起止使用周期")
	private String startstopusecycle;

    @Column(name = "EQUIP_PLACE", length = 510)
	@FieldName(name="设置地址")
	private String equipPlace;

	@Column(name = "EQUIP_DELIVERY_PLACE", length = 510)
	@FieldName(name="交付地址")
	private String equipDeliveryPlace;

	@Column(name = "EQUIP_DELIVERY_DATE", length = 40)
	@FieldName(name="交付时间")
	private String equipDeliveryDate;

	@Column(name = "CENOTE", length = 4000)
	@FieldName(name="备注")
	private String cenote;
	
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
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;
	
	public String getBuildupstop() {
		return buildupstop;
	}

	public void setBuildupstop(String buildupstop) {
		this.buildupstop = buildupstop;
	}

	public String getStartstopusecycle() {
		return startstopusecycle;
	}

	public void setStartstopusecycle(String startstopusecycle) {
		this.startstopusecycle = startstopusecycle;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DictionaryData getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DictionaryData deviceType) {
		this.deviceType = deviceType;
	}

	public CustInfo getVndr() {
		return vndr;
	}

	public void setVndr(CustInfo vndr) {
		this.vndr = vndr;
	}

	public CustInfo getAgent() {
		return agent;
	}

	public void setAgent(CustInfo agent) {
		this.agent = agent;
	}

	public CustInfo getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(CustInfo manufacturer) {
		this.manufacturer = manufacturer;
	}
	

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}



	public BigDecimal getEquipNum() {
		return equipNum;
	}

	public void setEquipNum(BigDecimal equipNum) {
		this.equipNum = equipNum;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getNowtotal() {
		return nowtotal;
	}

	public void setNowtotal(BigDecimal nowtotal) {
		this.nowtotal = nowtotal;
	}

	public BigDecimal getEquipPrice() {
		return equipPrice;
	}

	public void setEquipPrice(BigDecimal equipPrice) {
		this.equipPrice = equipPrice;
	}

	public String getEquipPlace() {
		return equipPlace;
	}

	public void setEquipPlace(String equipPlace) {
		this.equipPlace = equipPlace;
	}

	public String getEquipDeliveryPlace() {
		return equipDeliveryPlace;
	}

	public void setEquipDeliveryPlace(String equipDeliveryPlace) {
		this.equipDeliveryPlace = equipDeliveryPlace;
	}

	public String getEquipDeliveryDate() {
		return equipDeliveryDate;
	}

	public void setEquipDeliveryDate(String equipDeliveryDate) {
		this.equipDeliveryDate = equipDeliveryDate;
	}

	public String getCenote() {
		return cenote;
	}

	public void setCenote(String cenote) {
		this.cenote = cenote;
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

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}


}