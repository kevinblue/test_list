package com.tenwa.leasing.entity.factoring;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.proj.ProjInfo;

@Entity
@FieldName(name = "保理项目变更说明")
@Table(name = "FACTORING_CHANGE_EXPLAIN")
public class FactoringChangeExplain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@FieldName(name="标识符")
	@Column(name = "ID", nullable = false, length = 64)
	private String id;
	
	
	@FieldName(name="项目编号")
	@OneToOne(targetEntity=ProjInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="PROJ_ID")
	private ProjInfo projId;
	
	@FieldName(name = "原保理业务方案批复号")
	@Column(name = "OLD_APPROVALNO",length=100)
	private String oldApprovalNo;
	
	@FieldName(name = "变更后业务方案批复号")
	@Column(name="NEW_APPROVALNO",length=100)
	private String newApprovalNo;
	
	@FieldName(name="原业务方案审批机构")
	@Column(name="OLD_EXAMINEAGENCY", length=100)	
	private String oldExamineAgency;
	
	@FieldName(name="变更后方案审批机构")
	@Column(name="NEW_EXAMINEAGENCY", length = 100)	
	private String newExamineAgency;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR_")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR_")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	

	public ProjInfo getProjId() {
		return projId;
	}

	public void setProjId(ProjInfo projId) {
		this.projId = projId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldApprovalNo() {
		return oldApprovalNo;
	}

	public void setOldApprovalNo(String oldApprovalNo) {
		this.oldApprovalNo = oldApprovalNo;
	}

	public String getNewApprovalNo() {
		return newApprovalNo;
	}

	public void setNewApprovalNo(String newApprovalNo) {
		this.newApprovalNo = newApprovalNo;
	}

	public String getOldExamineAgency() {
		return oldExamineAgency;
	}

	public void setOldExamineAgency(String oldExamineAgency) {
		this.oldExamineAgency = oldExamineAgency;
	}

	public String getNewExamineAgency() {
		return newExamineAgency;
	}

	public void setNewExamineAgency(String newExamineAgency) {
		this.newExamineAgency = newExamineAgency;
	}

	public User getModificator() {
		return modificator;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
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

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	
}
