package com.tenwa.leasing.entity.proj;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.file.BaseFile;


@Entity
@FieldName(name = "租赁物清单")
@Table(name="PROJ_EQUIPMENT_INFO")
public class ProjEquipmentInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@Column(name = "PROJ_NAME",length=200)
	@FieldName(name="项目名称")
	private String projname;
	
	@Column(name = "CONTRACT_LIFE",length=200)
	@FieldName(name="合同年限")
	private String contractLife;
	
	@FieldName(name="取得时间")
	@Column(name="OBTAIN_TIME",length=50)
	private String obtainTime;
	
	@FieldName(name="生产厂家")
	@Column(name="MANUFACTURER", length=200)
	private String manufacturer;
	
	@FieldName(name="设备名称")
	@Column(name = "DEVICENAME",length=200)
	private String deviceName;
	
	@FieldName(name="规格型号")
	@Column(name = "SPECIMODEL")
	private String speciModel;
	
	@FieldName(name = "数量")
	@Column(name = "QUANTUM")
	private String quantum;
	
	@FieldName(name="原值")
	@Column(name="ORIGINAL_VALUE")
	private String originalValue;
	
	@FieldName(name="净值")
	@Column(name="NETWORTH")
	private String networth;
	
	@FieldName(name="租赁资产编号")
	@Column(name="LEASE_ASSETS_NUMBER",length=200)
	private String leaseAssetsNumber;
	
	@FieldName(name="发票号码")
	@Column(name="INVOICE_NUMBER",length=200)
	private String invoiceNumber;
	
	@FieldName(name="存放位置")
	@Column(name="STORAGE_LOCATION",length=200)
	private String storageLocation;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=50)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=50)	
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


	public String getProjname() {
		return projname;
	}


	public void setProjname(String projname) {
		this.projname = projname;
	}


	public String getObtainTime() {
		return obtainTime;
	}


	public void setObtainTime(String obtainTime) {
		this.obtainTime = obtainTime;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getDeviceName() {
		return deviceName;
	}


	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}


	public String getSpeciModel() {
		return speciModel;
	}


	public void setSpeciModel(String speciModel) {
		this.speciModel = speciModel;
	}


	public String getQuantum() {
		return quantum;
	}


	public void setQuantum(String quantum) {
		this.quantum = quantum;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getOriginalValue() {
		return originalValue;
	}


	public void setOriginalValue(String originalValue) {
		this.originalValue = originalValue;
	}


	public String getNetworth() {
		return networth;
	}


	public void setNetworth(String networth) {
		this.networth = networth;
	}


	public String getLeaseAssetsNumber() {
		return leaseAssetsNumber;
	}


	public void setLeaseAssetsNumber(String leaseAssetsNumber) {
		this.leaseAssetsNumber = leaseAssetsNumber;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public String getStorageLocation() {
		return storageLocation;
	}


	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
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


	public String getContractLife() {
		return contractLife;
	}


	public void setContractLife(String contractLife) {
		this.contractLife = contractLife;
	}
	
	
}
