package com.tenwa.leasing.entity.contract;

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
import com.tenwa.leasing.entity.proj.ProjInfo;

@Entity
@FieldName(name = "项目移交信息表")
@Table(name = "PROJ_TRANSFER_INFO")
public class ProjTransferInfo {     
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name = "项目")
	@ManyToOne(targetEntity = ProjInfo.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJ_ID")
	private ProjInfo projid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "前项目经理")
	@JoinColumn(name = "PROJ_MANAGE_FORMER")
	private User projManageFormer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@FieldName(name = "现项目经理")
	@JoinColumn(name = "PROJ_MANAGE_CURRENT")
	private User projManageCurrent;
	
	@FieldName(name = "备注")
	@Column(name = "NOTE", length = 200)
	private String note; 
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public User getProjManageFormer() {
		return projManageFormer;
	}

	public void setProjManageFormer(User projManageFormer) {
		this.projManageFormer = projManageFormer;
	}

	public User getProjManageCurrent() {
		return projManageCurrent;
	}

	public void setProjManageCurrent(User projManageCurrent) {
		this.projManageCurrent = projManageCurrent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public ProjInfo getProjid() {
		return projid;
	}

	public void setProjid(ProjInfo projid) {
		this.projid = projid;
	}
	
	
}
