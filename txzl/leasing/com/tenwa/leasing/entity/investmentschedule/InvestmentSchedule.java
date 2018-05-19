package com.tenwa.leasing.entity.investmentschedule;
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
import com.tenwa.leasing.entity.file.BaseFile;

/**
 * 
 * @author zyh
 * @date 2016-9-19下午09:33:10
 * @info 风电项目投资及进度台帐
 * @Copyright 
 * Tenwa
 */

@Entity
@FieldName(name = "风电项目投资明细")
@Table(name = "T_ACT_INVESTMENTSCH")
public class InvestmentSchedule {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "编码")
	@Column(name = "CODING", length = 50)
	private String coding;

	@FieldName(name = "设备/劳务名称")
	@Column(name = "EQUIP_LABOR", length = 50)
	private String equipLabor;

	@FieldName(name = "单位")
	@Column(name = "UNIT", length = 50)
	private String unit;

	@FieldName(name = "规格型号")
	@Column(name = "SPECIFICATION", length = 50)
	private String specification;

	@FieldName(name = "数量")
	@Column(name = "NUMBER_ONE", length = 50)
	private String numberOne;

	@FieldName(name = "单价")
	@Column(name = "UNIT_PRICE", length = 50)
	private String unitPrice;

	@FieldName(name = "合价 ")
	@Column(name = "TOTAL_PRICE", length = 50)
	private String totalPrice;

	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;
	

	@ManyToOne
	@FieldName(name="上传文件名")
	@JoinColumn(name="UPLOAD_ID")
	private BaseFile upLoadId ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public String getEquipLabor() {
		return equipLabor;
	}

	public void setEquipLabor(String equipLabor) {
		this.equipLabor = equipLabor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}


	public String getNumberOne() {
		return numberOne;
	}

	public void setNumberOne(String numberOne) {
		this.numberOne = numberOne;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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
