package com.tenwa.leasing.entity.fivecategoryapply;

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

/**
 * FundEbankProcess entity. @author MyEclipse Persistence Tools
 */
@Entity
@FieldName(name = "五级分类申请")
@Table(name="FIVE_CATEGORY_APPLY")

public class FiveCategoryApply {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;

	@ManyToOne
	@FieldName(name = "申请人")
	@JoinColumn(name = "APPLY_USER")
	private User applyuser;
	
	@FieldName(name="流程编号")
	@Column(name="DOC_ID")
	private String docId;
	
	@FieldName(name="申请年月")
	@Column(name="APPLY_DATE")
	private String applydate ;
	
	@FieldName(name="申请编号")
	@Column(name="APPLY_NUMBER")
	private String applynumber ;
	
	@FieldName(name = "申请状态")
	@Column(name="APPLY_STATUS")
	private String applystatus;
	
	@FieldName(name = "申请次数")
	@Column(name="APPLY_TIME")
	private String applytime;

	@FieldName(name="备注")
	@Column(name="APPLY_MEMO",length=1000)
	private String applymemo;
    
	
	@FieldName(name="开始时间")
	@Column(name="START_DATE",length=1000)
	private String startdate;
	
	@FieldName(name="结束时间")
	@Column(name="END_DATE",length=1000)
	private String enddate;
	
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

	public User getApplyuser() {
		return applyuser;
	}

	public void setApplyuser(User applyuser) {
		this.applyuser = applyuser;
	}

	public String getApplydate() {
		return applydate;
	}

	public void setApplydate(String applydate) {
		this.applydate = applydate;
	}
	

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getApplynumber() {
		return applynumber;
	}

	public void setApplynumber(String applynumber) {
		this.applynumber = applynumber;
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

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	
	

}