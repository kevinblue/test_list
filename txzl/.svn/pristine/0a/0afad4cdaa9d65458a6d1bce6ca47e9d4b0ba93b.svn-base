
 /**
 * 项目名称：    系统名称
 * 包名：              com.tenwa.business.entity.message
 * 文件名：         MessageBase.java
 * 版本信息：    1.0.0
 * 创建日期：     2013-12-2-下午01:49:29
 * Copyright：2013XX公司-版权所有
 **/

package com.tenwa.business.entity.message;


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
 * 类名称：     MessageBase
 * 类描述：     
 * 创建人：     Administrator
 * 修改人：     Administrator
 * 修改时间：2013-12-2 下午01:49:29
 * 修改备注：
 * @version 1.0.0
 **/

@FieldName(name="短信发送信息关联表")
@Entity
@Table(name="MSG_SEND_RELATIVE")
public class MsgSendRelative {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32,name="ID_")
    private String id ;
	
	@Column(name = "CONTRACT_ID")
	@FieldName(name="合同编号")
	private String contractId;
	
	@Column(name = "CUST_ID",nullable=true)
	@FieldName(name="客户编号")
	private String custId;
	
	@Column(name = "SMS_ID",nullable=true)
	@FieldName(name="短信发送编号")
	private String smsId;
	
	@Column(name = "PHONENUMBER",nullable=true)
	@FieldName(name="客户手机号")
	private String phonenumber;
	
	@Column(name = "PLAN_ID")
	@FieldName(name="租金计划编号")
	private String planId;
	
	@Column(name = "PLAN_LIST")
	@FieldName(name="租金计划期次")
	private String planList;
	
	@Column(name = "MSG_SEND_JOBNAME")
	@FieldName(name="发送定时代理任务名")
	private String msgSendJobname;
	
	@Column(name = "MSG_SEND_TRIGGERNAME")
	@FieldName(name="发送定时代理计划名")
	private String msgSendTriggername;
	
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getMsgSendJobname() {
		return msgSendJobname;
	}
	public void setMsgSendJobname(String msgSendJobname) {
		this.msgSendJobname = msgSendJobname;
	}
	public String getMsgSendTriggername() {
		return msgSendTriggername;
	}
	public void setMsgSendTriggername(String msgSendTriggername) {
		this.msgSendTriggername = msgSendTriggername;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanList() {
		return planList;
	}
	public void setPlanList(String planList) {
		this.planList = planList;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name = "MSG_TYPE",nullable=true)
	@FieldName(name="短信类型")
	private String msg_type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODIFICATOR")
	@FieldName(name="修改人")
	private User modificator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATOR")
	@FieldName(name="创建人")
	private User creator;

	@Column(name = "CREATE_DATE", length = 40)
	@FieldName(name="创建时间")
	private String createDate;

	@Column(name = "MODIFY_DATE", length = 40)
	@FieldName(name="修改时间")
	private String modifyDate;
	
	public String getId() {
		return id;
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
