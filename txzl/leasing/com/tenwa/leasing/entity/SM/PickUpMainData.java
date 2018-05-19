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
@FieldName(name = "接货主数据")
@Table(name="T_PICKUP_MAIN_DATA")
public class PickUpMainData {
	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
    @FieldName(name="标识符")
	private String id_;
	
	@FieldName(name="ID")
	@Column(name="ID")
	private String id;
	
	@FieldName(name="SYSMODTIME")
	@Column(name="SYSMODTIME")
	private String sysmodtime;
	
	@FieldName(name="存放机位")
	@Column(name="存放机位")
	private String reservation;
	
	@FieldName(name="存放位置")
	@Column(name="存放位置")
	private String location;
	
	@FieldName(name="物料编码")
	@Column(name="物料编码")
	private String materialCode;
	
	@FieldName(name="物料名称")
	@Column(name="物料名称",length=2000)
	private String materialName;
	
	@FieldName(name="批次号")
	@Column(name="批次号")
	private String batchNum;
	
	@FieldName(name="序列号")
	@Column(name="序列号")
	private String s_id;
	
	@FieldName(name="数量")
	@Column(name="数量")
	private String amount;
	
	@FieldName(name="单位")
	@Column(name="单位")
	private String unit;
	
	@FieldName(name="接货工单号")
	@Column(name="接货工单号")
	private String receivingNum;
	
	@FieldName(name="片区")
	@Column(name="片区")
	private String area;
	
	@FieldName(name="风电场编号")
	@Column(name="风电场编号")
	private String windFieldNum;
	
	@FieldName(name="接货开始时间")
	@Column(name="接货开始时间")
	private String  pickUpStartTime;
	
	@FieldName(name="接货结束时间")
	@Column(name="接货结束时间")
	private String  pickUpEndTime;
	
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

	public String getId_() {
		return id_;
	}

	public void setId_(String id_) {
		this.id_ = id_;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysmodtime() {
		return sysmodtime;
	}

	public void setSysmodtime(String sysmodtime) {
		this.sysmodtime = sysmodtime;
	}

	public String getReservation() {
		return reservation;
	}

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getReceivingNum() {
		return receivingNum;
	}

	public void setReceivingNum(String receivingNum) {
		this.receivingNum = receivingNum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getWindFieldNum() {
		return windFieldNum;
	}

	public void setWindFieldNum(String windFieldNum) {
		this.windFieldNum = windFieldNum;
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
