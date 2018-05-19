package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-3-7上午11:23:47
 * @info 租金测算现金流项目配置表
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "租金测算现金流项目配置表")
@Table(name="FUND_CASH_CONFIG")
public class FundCashConfig {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="费用类型列名")
	@Column(name="FEE_NAME")
	private String feeName;


	@FieldName(name="费用类型描述")
	@Column(name="FEE_NAME_DES")
	private String feeNameDes;

	@FieldName(name="表名")
	@Column(name="FEE_TBNAME")
	private String feeTbname;

	@FieldName(name="流入流出类型")
	@Column(name="INOROUT")
	private String inOrOut;

	@FieldName(name="相对应的日期字段")
	@Column(name="OCCU_DATE")
	private String occuDate;

	@FieldName(name="类型")
	@Column(name="OCCU_TYPE")
	private String occuType;

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

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public String getFeeNameDes() {
		return feeNameDes;
	}

	public void setFeeNameDes(String feeNameDes) {
		this.feeNameDes = feeNameDes;
	}

	public String getFeeTbname() {
		return feeTbname;
	}

	public void setFeeTbname(String feeTbname) {
		this.feeTbname = feeTbname;
	}

	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getOccuDate() {
		return occuDate;
	}

	public void setOccuDate(String occuDate) {
		this.occuDate = occuDate;
	}

	public String getOccuType() {
		return occuType;
	}

	public void setOccuType(String occuType) {
		this.occuType = occuType;
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
