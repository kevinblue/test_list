package com.tenwa.leasing.entity.proj;

import java.io.Serializable;
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
import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
import com.tenwa.leasing.entity.file.BaseFile;


@Entity
@FieldName(name = "1.2项目设备信息临时表")
@Table(name = "PROJ_EQUIP_TMP")
public class ProjEquipTmp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", nullable = false, length = 64)
	@FieldName(name="标识符")
	private String id;

	/**
	 * 新增列
	 */
	@Column(name = "ORIGINALTOTAL", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="原值")
	private BigDecimal originaltotal;

	
	
	@Column(name = "CONTRACTID")
	@FieldName(name="合同编号id")
	private String contractId;
	
	
	@Column(name = "DOC_ID")
	@FieldName(name="当前流程ID")
	private String docid;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_EQUIP_ID")
	@FieldName(name="租赁物ID")
	private ProjEquip  projEquipId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_TYPE")
	@FieldName(name="设备类型")
	private DictionaryData deviceType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VNDR")
	@FieldName(name="供应商")
	private CustInfo  vndr;
	
	@Column(name = "BRAND_", length = 510)
	@FieldName(name="品牌")
	private String brand;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGENT")
	@FieldName(name="代理商")
	private CustInfo  agent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANUFACTURER")
	@FieldName(name="生产商")
	private CustInfo  manufacturer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	@FieldName(name="项目")
	private ProjInfo projInfo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VNDR_TYPE")
	@FieldName(name="贸易类型")
	private DictionaryData vndrType;  

	@Column(name = "EQUIP_NAME", length = 510)
	@FieldName(name="设备名称")
	private String equipName;

	@Column(name = "MODEL_", length = 510)
	@FieldName(name="型号/规格")
	private String model;

	@Column(name = "EQUIP_ID", length = 510)
	@FieldName(name="设备编号")
	private String equipId;
	
	@FieldName(name = "买方名称")
	@Column(name="PURCHASER_NAME", length=100)
	private String purchasername;
	

	@FieldName(name = "发票号")
	@Column(name="INVOICE_CODE", length=100)
	private String invoicecode;
	
	@FieldName(name = "发票到期日")
	@Column(name="INVOICE_EXPIREDATE", length=100)
	private String invoiceexpiredate;
	
	
	@FieldName(name = "发票日期")
	@Column(name="INVOICE_DATE", length=100)
	private String invoicedate;

	
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
	@FieldName(name="总价")
	private BigDecimal total;

	@Column(name = "NOWTOTAL", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="现值")
	private BigDecimal nowtotal;

	@Column(name = "EQUIP_PRICE", precision = 22,scale = Scale.DEFAULT)
	@FieldName(name="交易价格/发票金额")
	private BigDecimal equipPrice;

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

	@Column(name = "EQUIP_PERIOD", length = 40)
	@FieldName(name="质量保证期")
	private String equipPeriod;
	
	public String getPurchasername() {
		return purchasername;
	}

	public void setPurchasername(String purchasername) {
		this.purchasername = purchasername;
	}

	public String getInvoicecode() {
		return invoicecode;
	}

	public void setInvoicecode(String invoicecode) {
		this.invoicecode = invoicecode;
	}

	public String getInvoiceexpiredate() {
		return invoiceexpiredate;
	}

	public void setInvoiceexpiredate(String invoiceexpiredate) {
		this.invoiceexpiredate = invoiceexpiredate;
	}

	public String getInvoicedate() {
		return invoicedate;
	}

	public void setInvoicedate(String invoicedate) {
		this.invoicedate = invoicedate;
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
	
	
	

	public DictionaryData getVndrType() {
		return vndrType;
	}

	public void setVndrType(DictionaryData vndrType) {
		this.vndrType = vndrType;
	}

	public ProjInfo getProjInfo() {
		return projInfo;
	}

	public void setProjInfo(ProjInfo projInfo) {
		this.projInfo = projInfo;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public BigDecimal getEquipNum() {
		return equipNum;
	}

	public void setEquipNum(BigDecimal equipNum) {
		this.equipNum = equipNum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEquipPeriod() {
		return equipPeriod;
	}

	public void setEquipPeriod(String equipPeriod) {
		this.equipPeriod = equipPeriod;
	}
	
	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public ProjEquip getProjEquipId() {
		return projEquipId;
	}

	public void setProjEquipId(ProjEquip projEquipId) {
		this.projEquipId = projEquipId;
	}

	public BigDecimal getOriginaltotal() {
		return originaltotal;
	}

	public void setOriginaltotal(BigDecimal originaltotal) {
		this.originaltotal = originaltotal;
	}
	
}