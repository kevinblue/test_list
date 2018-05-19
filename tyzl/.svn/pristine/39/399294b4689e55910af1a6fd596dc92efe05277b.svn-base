package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


/**
 * 
 * @author 孙传良
 * @date 2013-8-5下午11:51:35
 * @info  项目经理区域划分
 * @Copyright 
 * Tenwa
 */
@Entity
@FieldName(name = "项目经理区域划分")
@Table(name="MANAGER_DISTRICT")
public class ManagerDistrict {

	@Id
	@OrderBy
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@ManyToOne
	@FieldName(name = "人员")
	@JoinColumn(name="manager_")
	private User manager;
	
	@ManyToOne
	@FieldName(name = "角色")
	@JoinColumn(name="manager_Role")
	private Role managerRole;
	
	@ManyToOne
	@FieldName(name="所属区域[D]")
	@JoinColumn(name="district_")
	private DictionaryData district;
	
	@ManyToOne
	@FieldName(name = "登记人")
	@JoinColumn(name="CREATOR_")
	private User creator;

	@FieldName(name = "登记时间")
	@Column(name="CREATE_DATE", length = 20)
	private String createDate;
	
	@ManyToOne
	@FieldName(name = "登记部门")
	@JoinColumn(name="CREATOR_DEPT")
	private Department creatorDept;

	@ManyToOne
	@FieldName(name = "更新人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;

	@FieldName(name = "更新时间")
	@Column(name="MODIFY_DATE", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public DictionaryData getDistrict() {
		return district;
	}

	public void setDistrict(DictionaryData district) {
		this.district = district;
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

	public Department getCreatorDept() {
		return creatorDept;
	}

	public void setCreatorDept(Department creatorDept) {
		this.creatorDept = creatorDept;
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

	public void setManagerRole(Role managerRole) {
		this.managerRole = managerRole;
	}

	public Role getManagerRole() {
		return managerRole;
	}
	
}
