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
 * @version 创建时间：2013-11-12 下午6:33:51
 * 类说明
 */

@Entity
@Table(name="T_WORK_FLOW_FLAG")
@FieldName(name="流程互斥表")
public class WorkFlowFlag {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="流程实例")
	@Column(name="WORK_FLOW_INSTANCE")
	private String workFlowInstance;
	
	@FieldName(name="流程名称")
	@Column(name="WORK_FLOW_NAME")
	private String workFlowName;
	
	/**
	 * 业务编号，统一为ProjectIno或ContractInfo的主键ID
	 */
	@FieldName(name="业务编号")
	@Column(name="WORK_NUMBER")
	private String workNumber;
	
	@FieldName(name="预留二")
	@Column(name="KEY_TWO")
	private String keyTwo;

	@FieldName(name="预留三")
	@Column(name="KEY_THREE")
	private String keyThree;

	@FieldName(name="预留四")
	@Column(name="KEY_FOUR")
	private String keyFour;

	
	@FieldName(name="预留五")
	@Column(name="KEY_FIVE")
	private String keyFive;
	
	@FieldName(name="发起人")
	@Column(name="BEGIN_USER")
	private String beginUser;
	

	@FieldName(name="当前人")
	@Column(name="CUR_USER")
	private String currentUser;
	
	
	/**
	 * 状态: 0,禁用; 启用,1;
	 * 禁用表示流程已完毕，启用表示在运行中
	 */
	@FieldName(name="状态")
	@Column(name="STATUS")
	private int status;


	public String getId() {
		return id;
	}


	public String getWorkFlowInstance() {
		return workFlowInstance;
	}


	public String getWorkFlowName() {
		return workFlowName;
	}


	public String getWorkNumber() {
		return workNumber;
	}


	public String getKeyTwo() {
		return keyTwo;
	}


	public String getKeyThree() {
		return keyThree;
	}


	public String getKeyFour() {
		return keyFour;
	}


	public String getKeyFive() {
		return keyFive;
	}


	public String getBeginUser() {
		return beginUser;
	}


	public String getCurrentUser() {
		return currentUser;
	}


	public int getStatus() {
		return status;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setWorkFlowInstance(String workFlowInstance) {
		this.workFlowInstance = workFlowInstance;
	}


	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}


	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}


	public void setKeyTwo(String keyTwo) {
		this.keyTwo = keyTwo;
	}


	public void setKeyThree(String keyThree) {
		this.keyThree = keyThree;
	}


	public void setKeyFour(String keyFour) {
		this.keyFour = keyFour;
	}


	public void setKeyFive(String keyFive) {
		this.keyFive = keyFive;
	}


	public void setBeginUser(String beginUser) {
		this.beginUser = beginUser;
	}


	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}


	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
  
