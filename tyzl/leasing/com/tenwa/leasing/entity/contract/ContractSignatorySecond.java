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

@Entity
@Table(name = "CONTRACT_SIGNATORY_SECOND")
@FieldName(name="合同各方表（乙方）")
public class ContractSignatorySecond implements java.io.Serializable {
	
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@ManyToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="承租人(乙方)")
	@Column(name="CLIENT")
	private String client;

	@FieldName(name="委托代理人（乙方）")
	@Column(name="CLIENTCONSIGNER")
	private String clientconsigner;

	@FieldName(name="开户帐号（乙方）")
	@Column(name="CLIENT_ACC_NUMBER")
	private String clientAccNumber;

	@FieldName(name="邮政编码（乙方）")
	@Column(name="CLIENT_POSTCODE")
	private String clientPostcode;

	@FieldName(name="联系地址（乙方）")
	@Column(name="CLIENT_ADDRESS")
	private String clientAddress;
	
	@FieldName(name="注册地址（乙方）")
	@Column(name="CLIENT_REG_ADDRESS")
	private String clientRegAddress;

	@FieldName(name="联系人（乙方）")
	@Column(name="CLIENT_LINKMAN")
	private String clientLinkman;

	@FieldName(name="短信联系手机（乙方）")
	@Column(name="CLIENT_MOBILE_NUMBER")
	private String clientMobileNumber;

	@FieldName(name="电话（乙方）")
	@Column(name="CLIENT_TEL")
	private String clientTel;

	@FieldName(name="传真（乙方）")
	@Column(name="CLIENT_FAX")
	private String clientFax;

	@FieldName(name="EMAIL（乙方）")
	@Column(name="CLIENT_EMAIL")
	private String clientEmail;

	@FieldName(name="法定代表人（乙方）")
	@Column(name="CLIENT_PERSON")
	private String clientPerson;

	@FieldName(name="开户银行（乙方）")
	@Column(name="CLIENT_ACC_BANK")
	private String clientAccBank;

	@FieldName(name="开户户名（乙方）")
	@Column(name="CLIENT_ACC_NAME")
	private String clientAccName;
	
	@ManyToOne
	@FieldName(name="创建人")
	@JoinColumn(name="CREATOR_")
	private User creator;
	
	@FieldName(name="创建时间")
	@Column(name="CREATE_DATE", length=20)	
	private String createDate;
	
	@ManyToOne
	@FieldName(name="修改人")
	@JoinColumn(name="MODIFICATOR_")
	private User modificator;
	
	@FieldName(name="修改时间")
	@Column(name="MODIFY_DATE", length=20)
	private String modifyDate;
	
	public String getId() {
		return id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public String getClient() {
		return client;
	}

	public String getClientconsigner() {
		return clientconsigner;
	}

	public String getClientAccNumber() {
		return clientAccNumber;
	}

	public String getClientPostcode() {
		return clientPostcode;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public String getClientRegAddress() {
		return clientRegAddress;
	}

	public String getClientLinkman() {
		return clientLinkman;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public String getClientTel() {
		return clientTel;
	}

	public String getClientFax() {
		return clientFax;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public String getClientPerson() {
		return clientPerson;
	}

	public String getClientAccBank() {
		return clientAccBank;
	}

	public String getClientAccName() {
		return clientAccName;
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

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setClientconsigner(String clientconsigner) {
		this.clientconsigner = clientconsigner;
	}

	public void setClientAccNumber(String clientAccNumber) {
		this.clientAccNumber = clientAccNumber;
	}

	public void setClientPostcode(String clientPostcode) {
		this.clientPostcode = clientPostcode;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public void setClientRegAddress(String clientRegAddress) {
		this.clientRegAddress = clientRegAddress;
	}

	public void setClientLinkman(String clientLinkman) {
		this.clientLinkman = clientLinkman;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public void setClientPerson(String clientPerson) {
		this.clientPerson = clientPerson;
	}

	public void setClientAccBank(String clientAccBank) {
		this.clientAccBank = clientAccBank;
	}

	public void setClientAccName(String clientAccName) {
		this.clientAccName = clientAccName;
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
