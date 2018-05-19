package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2013-6-25 下午3:29:56 类说明
 */

@Entity
@Table(name="PROCESS_STATUS")
@FieldName(name="流程状态")
public class ProcessStatus {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32, name = "ID_")
	private String id;

	@FieldName(name="流程名称")
	@Column(nullable = true, name = "WORK_FLOW_NAME")
	private String workFlowName;
	
	@FieldName(name="节点名称")
	@Column(nullable = true, name = "POINT_NAME")
	private String pointName;
	
	@FieldName(name="Action方法")
	@Column(nullable = true, name = "ACTION_METHOD")
	private String actionMethod;

	@FieldName(name="实体名称(包括包名)")
	@Column(name = "ENTITY_NAME")
	private String entityName;
	
	@FieldName(name="variablesMap中ID值名称")
	@Column(name = "TYPE_NAME")
	private String typeName;
	
	@FieldName(name="流程字段名称")
	@Column(name = "PROCESS_FIELD_NAME")
	private String processFieldName;
	
	@FieldName(name="是否流程中字段名称")
	@Column(name = "WORK_FLOW_FIELD_NAME")
	private String workFlowFieldName;
	
	@FieldName(name="流程状态")
	@Column(name = "PROCESS_STATUS")
	private String processStatus;
	
	@FieldName(name="是否流程中")
	@Column(name = "WORK_FLOW")
	private String workFlow;
	
	@FieldName(name="附加字段")
	@Column(name = "ADDITIONAL_FIELD")
	private String additionalField;
	
	@FieldName(name="是否启用")
	@Column(name = "FLAG_",length = 2)
	private Integer flag;

	public String getId() {
		return id;
	}

	public String getWorkFlowName() {
		return workFlowName;
	}

	public String getPointName() {
		return pointName;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getProcessFieldName() {
		return processFieldName;
	}

	public String getWorkFlowFieldName() {
		return workFlowFieldName;
	}

	public String getProcessStatus() {
		return processStatus;
	}

	public String getWorkFlow() {
		return workFlow;
	}

	public String getAdditionalField() {
		return additionalField;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setProcessFieldName(String processFieldName) {
		this.processFieldName = processFieldName;
	}

	public void setWorkFlowFieldName(String workFlowFieldName) {
		this.workFlowFieldName = workFlowFieldName;
	}

	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}

	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}

	public void setAdditionalField(String additionalField) {
		this.additionalField = additionalField;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
