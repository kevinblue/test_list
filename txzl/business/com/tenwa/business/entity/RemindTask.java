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


@FieldName(name = "提醒任务表")
@Table(name = "REMIND_TASK")
@Entity
public class RemindTask {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "流程发起参数1")
	@Column(name = "DEPLOYPROP_PDID")
	private String  deploypropPdid;

	@FieldName(name = "流程发起参数2")
	@Column(name = "WORKFLOW_PARAMS")
	private String workflowParams;
	
	@FieldName(name = "流程名称")
	@Column(name = "WORKFLOW_NAME",length = 100)
	private String workflowName;
	
	@FieldName(name = "合同编号")
	@Column(name = "CONTRACT_ID",length = 100)
	private String contractId;
	
	@FieldName(name = "合同id")
	@Column(name = "CID",length = 32)
	private String cId;

	@FieldName(name = "发起人")
	@Column(name = "REMIND_USER", length = 500)
	private String remindUser;
	
	@FieldName(name = "项目名称")
	@Column(name = "PROJECT_NAME", length = 200)
	private String projectName;
	
	@FieldName(name="流程实例ID")
	@Column(name="DOC_ID")
	private String docID;
	
	@FieldName(name = "开始执行日期")
	@Column(name = "START_DATE_", length = 20)
	private String startDate;

	@FieldName(name = "状态【0:未发起;1:已发起】")
	@Column(name = "STATUS")
	private String status;
	
	@FieldName(name="备用主键2")
	@Column(name="KEY_TWO")
	private String keyTwo ;
	
	@FieldName(name="备用主键3")
	@Column(name="KEY_THREE")
	private String keyThree ;
	
	@FieldName(name="备用主键1")
	@Column(name="key_One")
	private String keyOne ;
	
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

	public String getDeploypropPdid() {
		return deploypropPdid;
	}

	public void setDeploypropPdid(String deploypropPdid) {
		this.deploypropPdid = deploypropPdid;
	}

	public String getWorkflowParams() {
		return workflowParams;
	}

	public void setWorkflowParams(String workflowParams) {
		this.workflowParams = workflowParams;
	}

	public String getRemindUser() {
		return remindUser;
	}

	public void setRemindUser(String remindUser) {
		this.remindUser = remindUser;
	}


	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getKeyTwo() {
		return keyTwo;
	}

	public void setKeyTwo(String keyTwo) {
		this.keyTwo = keyTwo;
	}

	public String getKeyThree() {
		return keyThree;
	}

	public void setKeyThree(String keyThree) {
		this.keyThree = keyThree;
	}

	public String getKeyOne() {
		return keyOne;
	}

	public void setKeyOne(String keyOne) {
		this.keyOne = keyOne;
	}

	public String getWorkflowName() {
		return workflowName;
	}

	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

}
