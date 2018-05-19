package com.tenwa.business.entity.notice;

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
import com.tenwa.leasing.entity.contract.ContractInfo;
import com.tenwa.leasing.entity.cust.CustInfo;
@Entity
@FieldName(name = "邮件任务表")
@Table(name = "T_EMAIL_NOTICE_TASKS")
public class EmailNotice {
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID_", length = 32)
	private String id;

	@ManyToOne(targetEntity = ContractInfo.class, fetch = FetchType.LAZY)
	@FieldName(name = "合同号")
	@JoinColumn(name = "CONTRACT_ID")
	private ContractInfo contractInfo;

	@ManyToOne(targetEntity = CustInfo.class, fetch = FetchType.LAZY)
	@FieldName(name = "客户编号")
	@JoinColumn(name = "CUST_ID")
	private CustInfo custInfo;

	@FieldName(name = "接收人邮件地址")
	@Column(name = "EMAIL_ADDRESS", length = 64)
	private String emailAddress;

	@FieldName(name = "发件人地址")
	@Column(name = "SENDER_ADDRESS", length = 64)
	private String senderAddress;

	@FieldName(name = "附件地址")
	@Column(name = "FILE_ADDRESS", length = 256)
	private String fileAddress;
	
	@FieldName(name = "附件显示/下载名称")
	@Column(name = "FILE_NAMES", length = 256)
	private String fileNames;
	
	@FieldName(name = "邮件标题")
	@Column(name = "EMAIL_TITLE", length = 1000)
	private String emailTitle;

	@FieldName(name = "邮件内容")
	@Column(name = "EMAIL_CONTENT", length = 1000)
	private String emailContent;
	
	@FieldName(name = "发送反馈信息")
	@Column(name = "SEND_RESULT", length = 256)
	private String sendResult;

	@FieldName(name = "指定发送时间")
	@Column(name = "NOTICE_TIME", length = 20)
	private String noticeTime;
	
	@FieldName(name = "实际发送时间")
	@Column(name = "SEND_TIME", length = 20)
	private String sendTime;

	@FieldName(name = "类型 : DELAYED：延时，IMMEDIATELY：立即")
	@Column(name = "EMAIL_TYPE", length = 20)
	private String emailType;
	
	@FieldName(name = "邮件的内容类型[租金还款提醒][租金催收提醒]")
	@Column(name = "CONTENT_TYPE", length = 30)
	private String contentType;
	
	@FieldName(name = "发送对象")
	@Column(name = "SEND_OBJ", length = 32)
	private String sendObj;
	
	@FieldName(name = "发送状态：0待发送，1已发送，2已取消发送")
	@Column(name = "SEND_FLAG",columnDefinition = "varchar(10) DEFAULT 0")
	private String sendFlag;
	
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

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public CustInfo getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(CustInfo custInfo) {
		this.custInfo = custInfo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		this.fileAddress = fileAddress;
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

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
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

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSendObj() {
		return sendObj;
	}

	public void setSendObj(String sendObj) {
		this.sendObj = sendObj;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
}
