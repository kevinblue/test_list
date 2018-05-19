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



/**
 * 
 * @author 李超杰
 * @date 2013-3-4下午04:31:01
 * @info 合同变更信息
 * @Copyright Tenwa
 */
@Entity
@FieldName(name = "合同其它变更信息表")
@Table(name = "CONTRACT_CHANGE_OTHER_INFO")
public class ContractChangeOtherInfo {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="ID")
	private String id;

	@FieldName(name = "合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name = "变更新内容")
	@Column(name = "NEW_CHANGE_CONTENT", length = 2000)
	private String newChangeContent;
	
	@FieldName(name = "变更后内容")
	@Column(name = "AFTER_CHANGE_CONTENT", length = 2000)
	private String afterChangeContent; 
	
	@FieldName(name = "流程的ID")
	@Column(name = "FLOW_UNID", length = 200)
	private String flowUnid;
	
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

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getAfterChangeContent() {
		return afterChangeContent;
	}

	public void setAfterChangeContent(String afterChangeContent) {
		this.afterChangeContent = afterChangeContent;
	}

	public String getNewChangeContent() {
		return newChangeContent;
	}

	public void setNewChangeContent(String newChangeContent) {
		this.newChangeContent = newChangeContent;
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

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	
	
}
