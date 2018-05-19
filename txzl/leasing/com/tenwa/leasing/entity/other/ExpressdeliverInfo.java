package com.tenwa.leasing.entity.other;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.DictionaryData;
import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
import com.tenwa.leasing.entity.contract.ContractInfo;

@Entity
@FieldName(name = "快递寄送信息表")
@Table(name="T_EXPRESS_INFO")
public class ExpressdeliverInfo {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(length = 32)
	@FieldName(name="标识符")
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;//
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="寄送文件类型")
	@JoinColumn(name="SEND_FILE_TYPE")
	private DictionaryData sendFileType;
	
	@FieldName(name = "寄送日期")
	@Column(name = "DELIVER_DATE", length = 20)
	private String deliverDate;
	
	@FieldName(name = "快递单号")
	@Column(name = "DELIVER_NUMBER", unique = true, length = 50)
	private String deliverNumber;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name="快递公司")
	@JoinColumn(name="EXPRESS_COMPANY")
	private DictionaryData expressCompany;
	
	@FieldName(name = "寄送单位")
	@Column(name = "SENDUNIT", length = 1000)
	private String sendunit;
	

	@FieldName(name = "收件人")
	@Column(name = "RECIPIENT")
	private String recipient;
	
	@FieldName(name = "签收人")
	@Column(name = "SIGN_PERSON", length = 1000)
	private String signperson;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@FieldName(name = "寄件人")
	@JoinColumn(name = "SENDER")
	private User sender;
	
	@FieldName(name = "联系电话")
	@Column(name = "TELEPHONE" )
	private String telephone;
	
	@FieldName(name = "收件地址")
	@Column(name = "RECIPIENT_ADDRESS")
	private String recipientAddress;
	
	@FieldName(name = "电话确认收到快递日期")
	@Column(name = "PHONE_SURE_GET_DELIVER")
	private String phoneSureGetDeliver;
	
	@FieldName(name = "快递签收日期")
	@Column(name = "EXPRESS_DELIVER_DATE")
	private String expressDeliverDate;
	
	@FieldName(name = "备注")
	@Column(name = "REMARKS", length = 1000)
	private String remarks;
	
	@ManyToOne
	@FieldName(name = "创建人")
	@JoinColumn(name = "CREATOR_")
	private User creator;

	@FieldName(name = "创建时间")
	@Column(name = "CREATE_DATE")
	private String createDate;

	@ManyToOne
	@FieldName(name = "修改人")
	@JoinColumn(name = "MODIFICATOR_")
	private User modificator;

	@FieldName(name = "修改时间")
	@Column(name = "MODIFY_DATE")
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

	public DictionaryData getSendFileType() {
		return sendFileType;
	}

	public void setSendFileType(DictionaryData sendFileType) {
		this.sendFileType = sendFileType;
	}

	public String getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getDeliverNumber() {
		return deliverNumber;
	}

	public void setDeliverNumber(String deliverNumber) {
		this.deliverNumber = deliverNumber;
	}

	public DictionaryData getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(DictionaryData expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getPhoneSureGetDeliver() {
		return phoneSureGetDeliver;
	}

	public void setPhoneSureGetDeliver(String phoneSureGetDeliver) {
		this.phoneSureGetDeliver = phoneSureGetDeliver;
	}

	public String getExpressDeliverDate() {
		return expressDeliverDate;
	}

	public void setExpressDeliverDate(String expressDeliverDate) {
		this.expressDeliverDate = expressDeliverDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	public String getSendunit() {
		return sendunit;
	}

	public void setSendunit(String sendunit) {
		this.sendunit = sendunit;
	}

	public String getSignperson() {
		return signperson;
	}

	public void setSignperson(String signperson) {
		this.signperson = signperson;
	}

	
}
