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
@FieldName(name = "风电投资明细")
@Table(name = "WIND_POWER_INVESTMENT_DETAILS")
public class WindPowerInvestmentDetails {
	private static final long serialVersionUID = 1L;
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
	
	@FieldName(name = "编码")
	@Column(name="code")
	private String code;
	
	@FieldName(name = "设备/劳务名称")
	@Column(name="EQUIP_NAME")
	private String equipName;
	
	@FieldName(name = "单位")
	@Column(name="UNIT")
	private String unit;
	
	@FieldName(name = "规格型号")
	@Column(name="TYPE")
	private String type;
	
	@FieldName(name = "数量")
	@Column(name="AMOUNT")
	private String amount;
	
	@FieldName(name = "单价/元")
	@Column(name="PRICE")
	private String price;
	
	@FieldName(name = "合价/万元")
	@Column(name="TOTAL")
	private String total;
	
	@FieldName(name = "备注")
	@Column(name="REMARK",length=2000)
	private String remark;
	
	@FieldName(name = "上传文件名")
	@ManyToOne(targetEntity = BaseFile.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "UP_ID")
	private BaseFile upLoadId;

	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE_", length=20)	
	private String createDate;
	
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	@ManyToOne(fetch=FetchType.LAZY)
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE_", length=20)	
	private String modifyDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ProjDevelopInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjDevelopInfo projId) {
		this.projId = projId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BaseFile getUpLoadId() {
		return upLoadId;
	}

	public void setUpLoadId(BaseFile upLoadId) {
		this.upLoadId = upLoadId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
