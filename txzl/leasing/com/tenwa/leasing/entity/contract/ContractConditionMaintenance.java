package com.tenwa.leasing.entity.contract;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;



/**
 * 
 * @author ganwei
 * @date 2016-03-18
 * @info 合同条件 维护表
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同条件维护表")
@Table(name = "CONTRACT_CONDITION_MAINTENANCE")
public class ContractConditionMaintenance {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="ID")
	private String id;
	
	@FieldName(name = "条件名称")
	@Column(name = "CONDITION_NAME", length = 255)
	private String conditionname;
	
	@FieldName(name = "合同类型")
	@Column(name = "CONTRACT_TYPE", length = 255)
	private String contracttype;
	
	@FieldName(name = "款项类别")
	@Column(name = "PAYMENT_CATEGORY", length = 255)
	private String paymentcategory;
	
	@FieldName(name = "条件类型")
	@Column(name = "CONDITION_TYPE", length = 255)
	private String conditiontype; 

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConditionname() {
		return conditionname;
	}

	public void setConditionname(String conditionname) {
		this.conditionname = conditionname;
	}

	public String getContracttype() {
		return contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	public String getPaymentcategory() {
		return paymentcategory;
	}

	public void setPaymentcategory(String paymentcategory) {
		this.paymentcategory = paymentcategory;
	}

	public String getConditiontype() {
		return conditiontype;
	}

	public void setConditiontype(String conditiontype) {
		this.conditiontype = conditiontype;
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
