package com.tenwa.leasing.entity.asset;

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

@Entity
@FieldName(name = "资产巡视计划申请")
@Table(name="ASSETMNG_APPLY")
public class AssetMngApply {

	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne
	@FieldName(name = "资产专员")
	@JoinColumn(name = "APPLY_USER")
	private User applyuser;
	
	@FieldName(name="申请年月")
	@Column(name="APPLY_DATE")
	private String applydate ;
	
	@FieldName(name="申请编号")
	@Column(name="APPLY_NUMBER")
	private String applynumber ;
	
	@FieldName(name = "申请状态")
	@Column(name="APPLY_STATUS")
	private String applystatus;

	@FieldName(name="备注")
	@Column(name="APPLY_MEMO")
	private String applymemo;

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

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}

	public String getApplystatus() {
		return applystatus;
	}

	public void setApplystatus(String applystatus) {
		this.applystatus = applystatus;
	}

	public String getApplymemo() {
		return applymemo;
	}

	public void setApplymemo(String applymemo) {
		this.applymemo = applymemo;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getApplynumber() {
		return applynumber;
	}

	public void setApplynumber(String applynumber) {
		this.applynumber = applynumber;
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

	public User getApplyuser() {
		return applyuser;
	}

	public void setApplyuser(User applyuser) {
		this.applyuser = applyuser;
	}
    
	
}