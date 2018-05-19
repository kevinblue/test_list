package com.tenwa.business.entity.notice;

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
import com.tenwa.leasing.entity.cust.CustInfo;

@Entity
@FieldName(name = "短信表")
@Table(name = "T_SMS_NOTICE_TASKS")
public class SmsNotice {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID_", length = 32)
	private String id;

	@FieldName(name = "电话号码")
	@Column(name = "PHONE_NUMBER", length = 20, nullable = false)
	private String phoneNumber;

	@FieldName(name = "内容")
	@Column(name = "SMS_CONTENT", length = 1000, nullable = false)
	private String smsContent;
	
	@FieldName(name = "短信发送反馈信息")
	@Column(name = "SEND_RESULT", length = 256)
	private String sendResult;

	@FieldName(name = "指定发送时间")
	@Column(name = "NOTICE_TIME", length = 20)
	private String noticeTime;
	
	@FieldName(name = "实际发送时间")
	@Column(name = "SEND_TIME", length = 20)
	private String sendTime;

	@FieldName(name = "类型 : DELAYED：延时，IMMEDIATELY：立即")
	@Column(name = "SMS_TYPE", length = 20)
	private String smsType;

	@FieldName(name = "发送状态：0待发送，1已发送，2已取消发送")
	@Column(name = "SEND_FLAG", length = 5)
	private int sendFlag = 0;
	
	@ManyToOne
	@FieldName(name = "短信类型")
	@JoinColumn(name = "SMS_TEMPLATE_ID")
	private SmsTemplate smsTemplateId;
	
	@ManyToOne
	@FieldName(name = "客户ID")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custId;
	
	@FieldName(name = "发送对象[注：接收人ID]")
	@Column(name = "SEND_OBJ", length = 32)
	private String sendObj;
	
	@FieldName(name = "发送对象类型{客户：CUST,担保人:GUARANTEE,项目经理:PROJ_MANAGE}")
	@Column(name = "SEND_OBJ_TYPE", length = 20)
	private String sendObjType;
	
	@FieldName(name = "备注")
	@Column(name = "REMARK", length = 256)
	private String remark;

	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE_", length = 20)
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE_", length = 20)
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPhonenumber() {
		return phoneNumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phoneNumber = phonenumber;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public CustInfo getCustId() {
		return custId;
	}

	public void setCustId(CustInfo custId) {
		this.custId = custId;
	}

	public SmsTemplate getSmsTemplateId() {
		return smsTemplateId;
	}

	public void setSmsTemplateId(SmsTemplate smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public String getSendObj() {
		return sendObj;
	}

	public void setSendObj(String sendObj) {
		this.sendObj = sendObj;
	}

	public String getSendObjType() {
		return sendObjType;
	}

	public void setSendObjType(String sendObjType) {
		this.sendObjType = sendObjType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSendResult() {
		return sendResult;
	}

	public void setSendResult(String sendResult) {
		this.sendResult = sendResult;
	}

	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSmsType() {
		return smsType;
	}

	public void setSmsType(String smsType) {
		this.smsType = smsType;
	}

	public int getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(int sendFlag) {
		this.sendFlag = sendFlag;
	}
}
