package com.tenwa.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.kernal.annotation.FieldName;


@Entity
@FieldName(name = "流程运行时间")
@Table(name="Flow_Time_Log")
public class FlowTimeLog {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流程id")
	@Column(name="FLOW_UNID")
	private String flowUnid;
	
	@FieldName(name="流程名称")
	@Column(name="Flow_Name")
	private String flowName;
	
	@FieldName(name="类名称")
	@Column(name="CLASS_Name")
	private String className;
	
	@FieldName(name="方法名称")
	@Column(name="METHOD_Name")
	private String methodName;
	
	@FieldName(name="开始时间")
	@Column(name="START_TIME")
	private String startTime;
	
	@FieldName(name="结束时间")
	@Column(name="end_TIME")
	private String endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowUnid() {
		return flowUnid;
	}

	public void setFlowUnid(String flowUnid) {
		this.flowUnid = flowUnid;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
}
