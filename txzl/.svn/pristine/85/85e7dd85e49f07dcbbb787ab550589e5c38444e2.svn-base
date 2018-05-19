package com.tenwa.leasing.entity.contract;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.tenwa.business.entity.User;
import com.tenwa.kernal.annotation.FieldName;
/**
 * ContractSignatory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT_SIGNATORY")
@FieldName(name="合同各方表")
public class ContractSignatory implements java.io.Serializable {
	@Id
    @GeneratedValue(generator = "paymentableGenerator")     
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid") 
    @Column(length=32)
	private String id;
	
	@FieldName(name="合同编号")
	@OneToOne(targetEntity=ContractInfo.class,fetch=FetchType.LAZY)
	@JoinColumn(name="CONTRACT_ID")
	private ContractInfo contractId;
	
	@FieldName(name="出租人(甲方)")
	@Column(name="LESSOR")
	private String lessor;
	
	@FieldName(name="委托代理人（甲方）")
	@Column(name="LEASECONSIGNER")
	private String leaseconsigner;
	
	@FieldName(name="开户帐号（甲方）")
	@Column(name="LEASE_ACC_NUMBER")
	private String leaseAccNumber;
	

	@FieldName(name="电子邮件（甲方）")
	@Column(name="LEASE_EMAIL")
	private String leaseEmail;

	@FieldName(name="法定代表人（甲方）")
	@Column(name="LEASE_PERSON")
	private String leasePerson;

	@FieldName(name="联系人（甲方）")
	@Column(name="LEASE_LINKMAN")
	private String leaseLinkman;

	@FieldName(name="注册地址（甲方）")
	@Column(name="LEASE_REGISTER_ADDR")
	private String leaseRegisterAddr;

	@FieldName(name="通讯地址（甲方）")
	@Column(name="LEASE_ADDR")
	private String leaseAddr;

	@FieldName(name="邮政编码（甲方）")
	@Column(name="LEASE_POSTCODE")
	private String leasePostcode;

	@FieldName(name="电话（甲方）")
	@Column(name="LEASE_TEL",length=100)
	private String leaseTel;

	@FieldName(name="传真（甲方）")
	@Column(name="LEASE_FAX",length=100)
	private String leaseFax;

	@FieldName(name="开户银行（甲方）")
	@Column(name="LEASE_ACC_BANK")
	private String leaseAccBank;

	@FieldName(name="开户户名（甲方）")
	@Column(name="LEASE_ACC_NAME")
	private String leaseAccName;
	
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

	public void setId(String id) {
		this.id = id;
	}

	public ContractInfo getContractId() {
		return contractId;
	}

	public void setContractId(ContractInfo contractId) {
		this.contractId = contractId;
	}

	public String getLessor() {
		return lessor;
	}

	public void setLessor(String lessor) {
		this.lessor = lessor;
	}

	public String getLeaseconsigner() {
		return leaseconsigner;
	}

	public void setLeaseconsigner(String leaseconsigner) {
		this.leaseconsigner = leaseconsigner;
	}

	public String getLeaseAccNumber() {
		return leaseAccNumber;
	}

	public void setLeaseAccNumber(String leaseAccNumber) {
		this.leaseAccNumber = leaseAccNumber;
	}

	public String getLeaseEmail() {
		return leaseEmail;
	}

	public void setLeaseEmail(String leaseEmail) {
		this.leaseEmail = leaseEmail;
	}

	public String getLeasePerson() {
		return leasePerson;
	}

	public void setLeasePerson(String leasePerson) {
		this.leasePerson = leasePerson;
	}

	public String getLeaseLinkman() {
		return leaseLinkman;
	}

	public void setLeaseLinkman(String leaseLinkman) {
		this.leaseLinkman = leaseLinkman;
	}

	public String getLeaseRegisterAddr() {
		return leaseRegisterAddr;
	}

	public void setLeaseRegisterAddr(String leaseRegisterAddr) {
		this.leaseRegisterAddr = leaseRegisterAddr;
	}

	public String getLeaseAddr() {
		return leaseAddr;
	}

	public void setLeaseAddr(String leaseAddr) {
		this.leaseAddr = leaseAddr;
	}

	public String getLeasePostcode() {
		return leasePostcode;
	}

	public void setLeasePostcode(String leasePostcode) {
		this.leasePostcode = leasePostcode;
	}

	public String getLeaseTel() {
		return leaseTel;
	}

	public void setLeaseTel(String leaseTel) {
		this.leaseTel = leaseTel;
	}

	public String getLeaseFax() {
		return leaseFax;
	}

	public void setLeaseFax(String leaseFax) {
		this.leaseFax = leaseFax;
	}

	public String getLeaseAccBank() {
		return leaseAccBank;
	}

	public void setLeaseAccBank(String leaseAccBank) {
		this.leaseAccBank = leaseAccBank;
	}

	public String getLeaseAccName() {
		return leaseAccName;
	}

	public void setLeaseAccName(String leaseAccName) {
		this.leaseAccName = leaseAccName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClientconsigner() {
		return clientconsigner;
	}

	public void setClientconsigner(String clientconsigner) {
		this.clientconsigner = clientconsigner;
	}

	public String getClientAccNumber() {
		return clientAccNumber;
	}

	public void setClientAccNumber(String clientAccNumber) {
		this.clientAccNumber = clientAccNumber;
	}

	public String getClientPostcode() {
		return clientPostcode;
	}

	public void setClientPostcode(String clientPostcode) {
		this.clientPostcode = clientPostcode;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientRegAddress() {
		return clientRegAddress;
	}

	public void setClientRegAddress(String clientRegAddress) {
		this.clientRegAddress = clientRegAddress;
	}

	public String getClientLinkman() {
		return clientLinkman;
	}

	public void setClientLinkman(String clientLinkman) {
		this.clientLinkman = clientLinkman;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public String getClientTel() {
		return clientTel;
	}

	public void setClientTel(String clientTel) {
		this.clientTel = clientTel;
	}

	public String getClientFax() {
		return clientFax;
	}

	public void setClientFax(String clientFax) {
		this.clientFax = clientFax;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPerson() {
		return clientPerson;
	}

	public void setClientPerson(String clientPerson) {
		this.clientPerson = clientPerson;
	}

	public String getClientAccBank() {
		return clientAccBank;
	}

	public void setClientAccBank(String clientAccBank) {
		this.clientAccBank = clientAccBank;
	}

	public String getClientAccName() {
		return clientAccName;
	}

	public void setClientAccName(String clientAccName) {
		this.clientAccName = clientAccName;
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
	
	

}