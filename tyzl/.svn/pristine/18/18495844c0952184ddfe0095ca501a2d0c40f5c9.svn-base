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
 * @author 作者 E-mail:
 * @version 创建时间：2013-11-12 下午6:27:18 类说明
 */
@Entity
@Table(name = "T_EXCLUSION_INFO")
@FieldName(name = "流程互斥表")
public class ExclusionInfo {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@FieldName(name = "A流程名称")
	@Column(name = "WORK_FLOW_NAME_A")
	private String workFlowNameA;

	@FieldName(name = "A流程名称")
	@Column(name = "WORK_FLOW_NAME_B")
	private String workFlowNameB;

	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR")
	@ManyToOne
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE", length = 20)
	private String createDate;

	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR")
	@ManyToOne
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public String getWorkFlowNameA() {
		return workFlowNameA;
	}

	public String getWorkFlowNameB() {
		return workFlowNameB;
	}

	public User getCreator() {
		return creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public User getModificator() {
		return modificator;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWorkFlowNameA(String workFlowNameA) {
		this.workFlowNameA = workFlowNameA;
	}

	public void setWorkFlowNameB(String workFlowNameB) {
		this.workFlowNameB = workFlowNameB;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setModificator(User modificator) {
		this.modificator = modificator;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

}
